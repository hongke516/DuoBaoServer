package com.fozoto.duobao.core.job;

import com.fozoto.duobao.core.util.TimeUtil;
import com.fozoto.duobao.model.Lottery;
import com.fozoto.duobao.service.ILotteryService;
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

import java.io.IOException;

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
