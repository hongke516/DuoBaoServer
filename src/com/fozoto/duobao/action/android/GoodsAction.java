package com.fozoto.duobao.action.android;

import com.alibaba.fastjson.JSON;
import com.fozoto.duobao.model.Goods;
import com.fozoto.duobao.service.IGoodsService;
import com.fozoto.duobao.util.entity.PageBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import java.util.LinkedHashMap;

/**
 * Created by qingyan on 16-7-30.
 */
@Namespace("/android/goods")
@Controller("AndroidGoodsAction")
@Scope("prototype")
@ParentPackage(value = "json-default")
@Result(name = "error", location = "/android/error", type = "redirect")
public class GoodsAction extends BaseAction{
    private static final Logger log = Logger.getLogger(GoodsAction.class);

    // 商品
    public Goods goods;

    // 商品id，用以与前台交互
    private int id;

    // 当前第几页
    public int page = 1;

    // 每页多少条记录
    public int size = 10;

    // 返回的json对象
    private Object result;

    @Autowired
    public PageBean<Goods> goodsPage;

    @Resource(name = "GoodsService")
    private IGoodsService goodsService;

    @Override
    public String create() {
        return null;
    }

    /**
     * http://localhost:8080/android/goods/info?id=1
     * 根据id查询单个商品的信息
     * 参数:id
     * 返回:商品的json信息
     */
    @Action(value = "info",
            results = @Result(type = "json", params = {"root", "result", "contentType", "application/json"})
    )
    @Override
    public String info() {
        if (id > 0 && id < 2147483647) {
            goods = goodsService.get(Goods.class, id);
            if (goods != null) {

                // 方式一:fastjson(result是Object对象)
                // 在不需要的属性上@JSONField(serialize=false)
                result = com.alibaba.fastjson.JSONObject.toJSON(goods);

                // 使用Gson在需要的属性上@Expose
                // 使用Gson但是失败了,因为struts2对result进行序列化了,相当于两次序列化
                // 得到的json对象有反斜杠,没法用
                // Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                // Object test = gson.toJson(goods);
                //log.debug(result);
                if (result!=null) {
                    return SUCCESS;
                }
            }
        }
        return ERROR;
    }

    /**
     * http://localhost:8080/android/goods/list?page=1&size=5
     * 根据输入的当前页page和每页显示数size,
     * 获取available为new的正在参与夺宝的商品,下架商品无法查询到
     * 参数:page和size
     * 返回:分页信息
     */
    @Action(value = "list",
            results = @Result(type = "json", params = {"root", "result", "contentType", "application/json"})
    )
    @Override
    public String list() {
        if (checkInt(page) && checkInt(size)) {
            LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
            lhm.put("retime", "desc");
            goodsPage = goodsService.getPaginationService(Goods.class, page, size, "available='新品'", lhm);
            if (goodsPage != null) {
                log.debug("总共有" + goodsPage.getAllRows() + "条记录");
                result = JSON.toJSON(goodsPage);
                return SUCCESS;
            }
        }
        return ERROR;
    }

    private boolean checkInt(int i) {
        return i > 0 && i < 2147483647;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PageBean<Goods> getGoodsPage() {
        return goodsPage;
    }

    public void setGoodsPage(PageBean<Goods> goodsPage) {
        this.goodsPage = goodsPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
