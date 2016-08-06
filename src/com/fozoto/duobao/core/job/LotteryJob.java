package com.fozoto.duobao.core.job;

import com.fozoto.duobao.core.util.TimeUtil;
import com.fozoto.duobao.model.*;
import com.fozoto.duobao.service.*;
import com.fozoto.duobao.util.OkHttpClientManager;
import com.fozoto.duobao.util.entity.Cqssc;
import com.fozoto.duobao.util.entity.CqsscData;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 将重庆时时彩结果定时写入数据库
 * http://t.apiplus.cn/newly.do?code=cqssc&format=json
 * 重庆时时彩每天120期
 * 10:01-22:01每十分钟获取一次
 * 22:06-01:56每五分钟获取一次彩票结果
 * Created by qingyan on 16-7-30.
 */
@Component("LotteryJob")
public class LotteryJob{

    private String currentCode;         // 当前中奖号码
    private String currentExpect;       // 当前时时彩期号
    private String nextExpect;          // 时时彩下一期期号

    @Resource(name = "CalculatorService")
    private ICalculatorService calculatorService;
    @Resource(name = "IssueService")
    private IIssueService issueService;
    @Resource(name = "LuckyService")
    private ILuckyService luckyService;
    @Resource(name = "FullService")
    private IFullService fullService;
    @Resource(name = "GoodsService")
    private IGoodsService goodsService;
    @Resource(name = "AnnalService")
    private IAnnalService annalService;
    @Resource(name = "GamesterService")
    private IGamesterService gamesterService;

    private boolean isNew;

    @Autowired
    private ILotteryService lotteryService;

    @Schedules({@Scheduled(cron = "40 1/10 10-21 * * ?"),
            @Scheduled(cron = "40 1/5 22-23 * * ?"),
            @Scheduled(cron = "40 1/5 0-1 * * ?")})
    public void execute(){
        String url = "http://t.apiplus.cn/newly.do?code=cqssc&format=json";
        Lottery lottery = null;
        try {
            String result = OkHttpClientManager.getAsString(url);
            Gson gson = new Gson();

            Cqssc cqssc = gson.fromJson(result, Cqssc.class);

            CqsscData data = cqssc.getData().get(0);

            System.err.println("第" + data.getExpect() + "期的结果是: " + data.getOpencode());
            String[] codes = data.getOpencode().split(",");
            StringBuilder code = new StringBuilder();
            for (int i = 1; i < codes.length; i++) {
                code.append(codes[i]);
            }

            String nowCode = code.toString();
            if (!nowCode.equals(currentCode)) {
                currentCode = nowCode;
                currentExpect = data.getExpect();
                nextExpect = String.valueOf(Long.parseLong(currentExpect)+1);
                lottery = getLottery(data, nowCode);
                isNew = true;
            } else {
                isNew = false;
            }
        }  catch (Exception e) {
            System.err.println("出现错误了");
            lottery = new Lottery();
            lottery.setExpect(nextExpect);
            lottery.setError("true");
            lottery.setCode("0000");
            isNew = true;
        } finally {
            if (isNew) {
                try {
                    lotteryService.add(lottery);
                    // 数值B
                    final long numB = Long.parseLong(lottery.getCode());
                    List<Full> fulls = fullService.getAll(Full.class);
                    if (fulls!=null) {
                        new Thread(() -> {
                            // 所有夺宝商品购买完应该存放在Full中
                            for (Full full : fulls) {
                                // 等待揭晓的期的id
                                int issueId = full.getFullId();
                                // 等待揭晓的期
                                Issue issue = issueService.get(Issue.class, issueId);
                                // 等待揭晓的期对应的夺宝商品
                                Goods goods = goodsService.getByIssue(Goods.class, issueId).get(0);
                                List<Annal> annals = annalService.getLast50Annals(goods.getId(), issueId);
                                // 数值A,由最后50条夺宝记录产生
                                long numA = 0;
                                for (Annal annal : annals) {
                                    numA += annal.getNum();
                                }
                                // 计算结果
                                long resultNum = (numA+numB)%goods.getTotal()+10000001;
                                // 幸运记录
                                Annal luckyAnnal = annalService.getByNumAndIssue(resultNum, issueId);
                                // 本期幸运者
                                Gamester gamester = gamesterService.get(Gamester.class, luckyAnnal.getGamester().getId());

                                // 保存计算结果
                                Calculator calculator = new Calculator();
                                calculator.setGoods(goods);
                                calculator.setNumA(numA);
                                calculator.setNumB(numB);
                                calculator.setResult(resultNum);
                                calculator.setIssue(issue);
                                try {
                                    calculatorService.add(calculator);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                // 保存幸运者
                                Lucky lucky = new Lucky();
                                lucky.setGoods(goods);
                                lucky.setGamester(gamester);
                                lucky.setIssue(issue);
                                lucky.setTime(TimeUtil.getTime().toString());
                                try {
                                    luckyService.add(lucky);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                // 更新该期的状态为已完成夺宝
                                issue.setLucky(lucky);
                                issue.setCalculator(calculator);
                                issue.setFinish(TimeUtil.getTime().toString());
                                issue.setOver("true");
                                try {
                                    issueService.update(issue);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                // 删除full, 以后解决
                            }
                        }).start();
                    }
                } catch (Exception e) {
                    System.err.println("保存lottery失败!");
                    System.err.println(e.getMessage());
                }
            }
            System.err.println("进入finally代码块"+ TimeUtil.getTime().toString());
        }
    }

    private Lottery getLottery(CqsscData data, String code) {
        Lottery lottery = new Lottery();
        lottery.setCode(code);
        lottery.setStamp(data.getOpentimestamp());
        lottery.setTime(data.getOpentime());
        lottery.setExpect(data.getExpect());
        lottery.setError("false");
        System.err.println("本期的中奖号码是" + code);
        return lottery;
    }
}
