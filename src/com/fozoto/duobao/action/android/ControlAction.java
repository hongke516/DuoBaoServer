package com.fozoto.duobao.action.android;

import com.fozoto.duobao.core.entity.Time;
import com.fozoto.duobao.core.util.TimeUtil;
import com.fozoto.duobao.model.Annal;
import com.fozoto.duobao.model.Gamester;
import com.fozoto.duobao.model.Goods;
import com.fozoto.duobao.model.Issue;
import com.fozoto.duobao.model.json.Consume;
import com.fozoto.duobao.service.*;
import com.fozoto.duobao.service.impl.IssueService;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by qingyan on 16-8-3.
 */
@Namespace("/android/control")
@Controller("AndroidControlAction")
@Scope("singleton")
@ParentPackage(value = "json-default")
@Result(name = "error", location = "/android/error", type = "redirect")
public class ControlAction extends BaseAction {

    // 用户从android客户端发来的消费信息
    private Consume consume;

    @Resource(name = "GamesterService")
    private IGamesterService gamesterService;

    @Resource(name = "AnnalService")
    private IAnnalService annalService;

    @Resource(name = "GoodsService")
    private IGoodsService goodsService;

    @Resource(name = "IssueService")
    private IIssueService issueService;

    private synchronized boolean updateIssue() {
        Issue issue =  issueService.get(Issue.class, consume.getIssueId());
        Goods goods = goodsService.get(Goods.class, consume.getGoodsId());
        Gamester gamester = gamesterService.get(Gamester.class, consume.getGamesterId());
        if (!issue.getOver().equals("true")) {
            // 本期未完成
            int rest = goods.getTotal() - issue.getDone();
            if (rest<consume.getPiece()) {
                // 用户购买的数量小于剩余夺宝次数
                for (int i=0; i<consume.getPiece(); i++) {
                    Annal annal = new Annal();
                    annal.setIssue(issue);
                    annal.setTime(TimeUtil.getTime().toString());
                    annal.setGamester(gamester);
                    annal.setGoods(goods);
                    //annal.setNum();
                }
            }
        }
        return false;
    }

    public String buy() {


        return ERROR;
    }


}
