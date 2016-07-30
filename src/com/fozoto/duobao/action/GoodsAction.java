package com.fozoto.duobao.action;

import com.fozoto.duobao.model.Goods;
import com.fozoto.duobao.service.IGoodsService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;

/**
 * Created by qingyan on 16-7-30.
 */
@Controller("GoodsAction")
@Scope("prototype")
public class GoodsAction extends ActionSupport {

    private Goods goods;

    @Resource(name = "GoodsService")
    private IGoodsService goodsService;

    public String createGoods () throws Exception{
        if (goods != null) {
            goodsService.add(goods);
            return SUCCESS;
        } else {
            return INPUT;
        }
    }

    public String queryGoods () throws Exception{
        //goodsService.get(Goods.class, id);
        return SUCCESS;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }


}
