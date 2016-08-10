package com.fozoto.duobao.action;

import com.fozoto.duobao.core.util.TimeUtil;
import com.fozoto.duobao.model.*;
import com.fozoto.duobao.service.IDetailService;
import com.fozoto.duobao.service.IGoodsService;
import com.fozoto.duobao.service.IIssueService;
import com.fozoto.duobao.service.IShapeService;
import com.fozoto.duobao.util.entity.PageBean;
import com.fozoto.duobao.util.entity.PromptInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by qingyan on 16-7-30.
 */
@Namespace("/goods")
@Controller("GoodsAction")
@Scope("prototype")
@ParentPackage(value = "json-default")
//@Results({
//        @Result(name = "error", location = "/WEB-INF/content/util/error.jsp")
//})
@Results({
        @Result(name = "error", location = "/android/error", type = "redirect"),
        @Result(name = "power", location = "/WEB-INF/content/util/prompt-info.jsp")
})
public class GoodsAction extends BaseAction {
    private static final Logger log = Logger.getLogger(GoodsAction.class);

    // 商品,用于在浏览器显示
    public Goods goods;

    // 商品id，用以与前台交互
    private int goodsId;

    // 当前第几页
    public int page = 1;

    // 每页显示几条数据
    public int size = 10;

    // 商品的分类信息
    public String sort;

    // json结果返回
    private String result;

    // 控制修改多个Shape和Detail,在textarea换行
    private String line;



    @Autowired
    public PageBean<Goods> goodsPage;

    @Resource(name = "IssueService")
    private IIssueService issueService;

    // 前台输入的商品滚动图片,以换行分割
    public String inputShapes;
    // 前台输入的商品详情图片,以换行分割
    public String inputDetails;

    private List<Shape> shapes;

    private List<Detail> details;

    @Autowired
    public PromptInfo promptInfo;

    @Resource(name = "GoodsService")
    private IGoodsService goodsService;

    @Resource(name = "ShapeService")
    private IShapeService shapeService;

    @Resource(name = "DetailService")
    private IDetailService detailService;

    /**
     * 新增商品
     */
    @Action(value = "create",
            results = {@Result(name = SUCCESS, location = "detail", type = "redirectAction", params = {"goodsId", "${goodsId}", "promptInfo.title", "${promptInfo.title}"}),
                    @Result(name = ERROR, location = "/WEB-INF/content/util/error.jsp"),
                    @Result(name = INPUT, location = "/WEB-INF/content/goods.jsp"),})
    public String create() throws Exception {
        // 管理员权限,才能操作
        if (adminPower()) {
            if (goods != null) {
                // 检查输入
                if (checkGoods(goods)) {
                    // 设置商品的添加时间
                    goods.setTime(TimeUtil.getTime().toString());
                    goods.setRetime(TimeUtil.getTime().toString());
                    // 保存商品
                    boolean flag = goodsService.add(goods);
                    if (flag) {
                        // 依赖商品先建立,然后才能保存Shape
                        if (!StringUtils.isEmpty(inputShapes)) {
                            //log.debug("inputShapes={" + inputShapes + "}");
                            String[] shapeImages = inputShapes.split("\r\n");
                            shapes = new ArrayList<>();
                            for (String image : shapeImages) {
                                if (image.length() < 255) {
                                    //log.debug("image=" + image);
                                    Shape shape = new Shape();
                                    shape.setGoods(goods);
                                    shape.setImage(image);
                                    // 保存商品滚动图片到数据库
                                    if (shapeService.add(shape))
                                        // 保存商品滚动图片成功后, 返回给前台查看保存结果
                                        shapes.add(shape);
                                    else {
                                        return ERROR;
                                    }
                                } else {
                                    promptInfo.setTitle("输入图片地址太长!");
                                    promptInfo.setMessage("输入图片地址大于255个字节,请重新输入!");
                                }
                            }
                        }

                        // 依赖商品先建立,然后才能保存Detail
                        if (!StringUtils.isEmpty(inputDetails)) {
                            //log.debug("inputDetails={" + inputDetails + "}");
                            String[] detailImages = inputDetails.split("\n");
                            details = new ArrayList<>();
                            for (String image : detailImages) {
                                if (image.length() < 255) {
                                    //log.debug("image=" + image);
                                    Detail detail = new Detail();
                                    detail.setGoods(goods);
                                    detail.setImage(image);
                                    // 保存商品详情图片到数据库
                                    if (detailService.add(detail)) {
                                        details.add(detail);
                                    } else {
                                        return ERROR;
                                    }

                                } else {
                                    promptInfo.setTitle("输入图片地址太长!");
                                    promptInfo.setMessage("输入图片地址大于255个字节,请重新输入!");
                                }
                            }
                        }
                        promptInfo.setTitle("商品添加成功！");
                        goodsId = goods.getId();

                        // 商品保存后,该商品的夺宝就开始了
                        Issue issue = new Issue();
                        issue.setGoods(goods);
                        issue.setStart(TimeUtil.getTime().toString());
                        issue.setOver("false");
                        issue.setDone(0);
                        if (issueService.add(issue)) {
                            return SUCCESS;
                        }
                    }
                }
            }
            return ERROR;
        } else {
            lowPower(promptInfo);
            return "power";
        }
    }

    /**
     * 通过商品id获取商品的所有信息(包括商品、滚动图片、详情图片）
     */
    @Action(value = "detail",
            results = @Result(name = SUCCESS, location = "/WEB-INF/content/goods-detail.jsp")
    )
    public String detail() {
        if (checkInt(goodsId)) {
            goods = goodsService.get(Goods.class, goodsId);
            if (goods != null) {
                shapes = shapeService.getByGoods(Shape.class, goodsId);
                details = detailService.getByGoods(Detail.class, goodsId);
                promptInfo.setTitle("商品查询结果");
                if (shapes != null && details != null) {
                    return SUCCESS;
                }
            }

        }
        return ERROR;
    }

    /**
     * 通过商品id获取商品的基本信息
     */
    @Action(value = "select", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String select() {
        if (goodsId > 0 && goodsId < 2147483647) {
            goods = goodsService.get(Goods.class, goodsId);
            if (goods != null) {
                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                result = gson.toJson(goods);
                // log.debug(result);
                if (result != null) {
                    return SUCCESS;
                }
            }
        }
        return ERROR;
    }

    /**
     * 查询所有商品
     */
    @Action(value = "page", results = @Result(location = "/WEB-INF/content/goods-manage.jsp"))
    public String page() throws Exception {
        // log.debug("进入page了page=" + page + ",size=" + size);
        if (checkInt(page) && checkInt(size)) {
            goodsPage = goodsService.getPaginationService(Goods.class, page, size, null, null);
            if (goodsPage != null) {
                // log.debug("总共有" + goodsPage.getAllRows() + "条记录");
                return SUCCESS;
            }
        }
        return ERROR;
    }

    /**
     * 按商品的分类查询该分类所有商品
     */
    @Action(value = "cate", results = @Result(location = "/WEB-INF/content/goods-cate.jsp"))
    public String cate() {
        // log.debug("进入cate了, 要查询的分类信息为sort=" + sort + ", page="+page+", size=" + size);

        if (checkInt(page) && checkInt(size) && !StringUtils.isEmpty(sort)) {
            String whereSql = " cate="+sort;
            goodsPage = goodsService.getPaginationService(Goods.class, page, size, whereSql, null);
            if (goodsPage != null) {
                // log.debug("总共有" + goodsPage.getAllRows() + "条记录");
                return SUCCESS;
            }
        }
        return ERROR;
    }

    @Action(value = "delete", results =
    @Result(location = "/goods/page", type = "redirect"))
    public String delete() throws Exception {
        // 管理员权限,才能操作
        if (adminPower()) {
            boolean flag = goodsService.delete(Goods.class, goodsId);
            if (flag) {
                log.debug("删除" + goodsId + "成功了");
                return SUCCESS;
            }
            return ERROR;
        } else {
            lowPower(promptInfo);
            return "power";
        }
    }

    @Action(value = "modify", results = {
            @Result(location = "/goods/page", type = "redirect")
    })
    public String modify() {
        // 管理员权限,才能操作
        if (adminPower()) {
            if (goods != null) {
                try {
                    log.debug("需要修改的商品id为:" + goods.getId());
                    Goods updateGoods = goodsService.get(Goods.class, goods.getId());
                    if (updateGoods != null) {
                        updateGoods.setPer(goods.getPer());
                        updateGoods.setTotal(goods.getTotal());
                        updateGoods.setPrice(goods.getPrice());
                        updateGoods.setCate(goods.getCate());
                        // 只有为新品时才更新重返夺宝时间
                        if (!updateGoods.getAvailable().equals(goods.getAvailable())) {
                            updateGoods.setAvailable(goods.getAvailable());
                            updateGoods.setRetime(TimeUtil.getTime().toString());
                        }
                        boolean flag = goodsService.update(updateGoods);
                        if (flag) {
                            return SUCCESS;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return ERROR;
        } else {
            lowPower(promptInfo);
            return "power";
        }
    }

    //    @Action(value = "update", results = @Result(type = "chain", location = "/goods/alter"))
    @Action(value = "update", results = {
            @Result(location = "/WEB-INF/content/goods-update.jsp")
    })
    public String update() {
        // 管理员权限,才能操作
        if (adminPower()) {
            if (goodsId > 0 && goodsId < 2147483647) {
                goods = goodsService.get(Goods.class, goodsId);
                if (goods != null) {
                    shapes = shapeService.getByGoods(Shape.class, goodsId);
                    details = detailService.getByGoods(Detail.class, goodsId);
                    line = "\n";
                    if (shapes != null && details != null) {
                        return SUCCESS;
                    }
                }
            }
            return ERROR;
        } else {
            lowPower(promptInfo);
            return "power";
        }
    }

    @Action(value = "alter",
            results = {@Result(name = SUCCESS, location = "detail", type = "redirectAction", params = {"goodsId", "${goods.id}", "promptInfo.title", "${promptInfo.title}"}),
                    @Result(name = INPUT, location = "/WEB-INF/content/goods.jsp")})
    public String alter() {
        // 管理员权限,才能操作
        if (adminPower()) {
            if (goods != null) {
                log.debug("需要更新的商品id为:" + goods.getId());
                Goods alterGoods = goodsService.get(Goods.class, goods.getId());
                if (alterGoods != null) {
                    alterGoods.setPer(goods.getPer());
                    alterGoods.setTotal(goods.getTotal());
                    alterGoods.setPrice(goods.getPrice());
                    alterGoods.setRetime(TimeUtil.getTime().toString());
                    alterGoods.setAvailable(goods.getAvailable());
                    alterGoods.setTrait(goods.getTrait());
                    alterGoods.setIntro(goods.getIntro());
                    alterGoods.setRemind(goods.getRemind());
                    alterGoods.setExplains(goods.getExplains());
                    // 首次夺宝时间不变
                    alterGoods.setTime(alterGoods.getTime());
                    alterGoods.setImage(goods.getImage());
                    try {
                        boolean flag = goodsService.update(alterGoods);
                        if (flag) {
                            return SUCCESS;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return ERROR;
        } else {
            lowPower(promptInfo);
            return "power";
        }
    }



    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    private boolean checkInt(int i) {
        return i > 0 && i < 2147483647;
    }

    /**
     * 对输入的商品的数据进行服务器端的校验
     */
    private boolean checkGoods(Goods goods) {
        if (goods != null) {
            if (goods.getImage().length() < 255 &&
                    goods.getIntro().length() < 65535 &&
                    goods.getPer() < 2147483647 &&
                    goods.getPrice() < 2147483647 &&
                    goods.getTotal() < 2147483647 &&
                    goods.getRemind().length() < 65535 &&
                    goods.getTrait().length() < 255 &&
                    goods.getExplains().length() < 655535
                    ) {
                return true;
            }
        }
        return false;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getInputShapes() {
        return inputShapes;
    }

    public void setInputShapes(String inputShapes) {
        this.inputShapes = inputShapes;
    }

    public String getInputDetails() {
        return inputDetails;
    }

    public void setInputDetails(String inputDetails) {
        this.inputDetails = inputDetails;
    }

    public PromptInfo getPromptInfo() {
        return promptInfo;
    }

    public void setPromptInfo(PromptInfo promptInfo) {
        this.promptInfo = promptInfo;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
