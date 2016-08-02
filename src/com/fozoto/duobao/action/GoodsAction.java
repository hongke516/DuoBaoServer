package com.fozoto.duobao.action;

import com.fozoto.duobao.core.util.TimeUtil;
import com.fozoto.duobao.model.Detail;
import com.fozoto.duobao.model.Goods;
import com.fozoto.duobao.model.Shape;
import com.fozoto.duobao.service.IDetailService;
import com.fozoto.duobao.service.IGoodsService;
import com.fozoto.duobao.service.IShapeService;
import com.fozoto.duobao.util.entity.PageBean;
import com.fozoto.duobao.util.entity.PromptInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Result(name = "error", location = "/WEB-INF/content/util/error.jsp")
public class GoodsAction extends ActionSupport {
    private static final Logger log = Logger.getLogger(GoodsAction.class);

    public Goods goods;

    public ModifyGoods modifyGoods;

    // 商品id，用以与前台交互
    private int goodsId;

    // 当前第几页
    public int page = 1;

    public int size = 10;

    private String result;

    private String line;

    @Autowired
    public PageBean<Goods> goodsPage;

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
     * 保存商品
     */
    @Action(value = "create",
            results = {@Result(name = SUCCESS, location = "detail", type = "redirectAction", params = {"goodsId", "${goodsId}", "promptInfo.title", "${promptInfo.title}"}),
                    @Result(name = ERROR, location = "/WEB-INF/content/util/error.jsp"),
                    @Result(name = INPUT, location = "/WEB-INF/content/goods.jsp"),})
    public String create() throws Exception {
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
                                if (detailService.add(detail))
                                    details.add(detail);
                            } else {
                                promptInfo.setTitle("输入图片地址太长!");
                                promptInfo.setMessage("输入图片地址大于255个字节,请重新输入!");
                            }
                        }
                    }
                    promptInfo.setTitle("商品添加成功！");
                    goodsId = goods.getId();
                    return SUCCESS;
                } else
                    return ERROR;
            }
        }
        return INPUT;
    }

    /**
     * 通过商品id获取商品的所有信息(包括商品、滚动图片、详情图片）
     */
    @Action(value = "detail",
            results = @Result(name = SUCCESS, location = "/WEB-INF/content/goods-detail.jsp")
    )
    public String detail() throws Exception {
        if (goodsId > 0 && goodsId < 2147483647) {
            if (goods == null) {
                goods = goodsService.get(Goods.class, goodsId);
            }
            if (shapes == null) {
                shapes = shapeService.getByGoods(Shape.class, goods);
            }
            if (details == null) {
                details = detailService.getByGoods(Detail.class, goods);
            }
            promptInfo.setTitle("商品查询结果");
            if (goods != null && shapes != null && details != null) {
                return SUCCESS;
            }
        }
        return ERROR;
    }

    /**
     * 通过商品id获取商品的基本信息
     */
//    @Action(value = "select", results = {
//            @Result(location = "/WEB-INF/content/goods-select.jsp")
//    })
    @Action(value = "select", results = {
            @Result(type = "json", params = {"root", "result"})
    })

    public String select() {
        if (goodsId > 0 && goodsId < 2147483647) {
            goods = goodsService.get(Goods.class, goodsId);
            if (goods != null) {
                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                result = gson.toJson(goods);
                if (result != null) {
                    return SUCCESS;
                }
            }
        }
        return ERROR;
    }

    /**
     * 获取商品基本信息分页显示
     */
/*
//    @Action(value = "page", results = {
//            @Result(name = SUCCESS, location = "/WEB-INF/content/goods-manage.jsp"),
//            @Result(name = ERROR, location = "/WEB-INF/content/util/error.jsp")
//    })

//    @Action(value = "page", results = {
//            @Result(name = "ajax", type = "json", params = {"goodsPage", "${goodsPage}"}),
//            @Result(name = SUCCESS, location = "/WEB-INF/content/goods-manage.jsp")
//        })

//     params = {"root", "${}""includeProperties", "goodsPage"})


//    @Action(value = "page", results = {
//            @Result(name = SUCCESS, type = "json", params = {"root", "goodsPage", "includeProperties", "goodsPage.*"})
//    })

//    @Action(value = "page")
//    @JSON
*/

    /*
    @Action(value = "page", results = {
            @Result(name = SUCCESS, type = "json", params = {"root", "result"})
    })*/

//    @Action(value = "page", results = @Result(location = ))
    @Action(value = "page", results = @Result(location = "/WEB-INF/content/goods-manage.jsp"))
    public String page() throws Exception {
        System.out.println("进入page了page=" + page + ",size=" + size);
        if (page > 0 && size > 0) {
//            goodsPage = goodsService.getPaginationService(Goods.class, page, size, null, null);
            goodsPage = goodsService.getPaginationService(Goods.class, page, size, null, null);
            if (goodsPage != null) {
                log.debug("总共有" + goodsPage.getAllRows() + "条记录");
//                try {
//                    // 此处用Gson可能会出错,因为转换时发现OneToMany的字段没有添加@Expose
//                    Gson gson = new Gson();
//                    log.debug(goodsPage.toString());
//                    result = gson.toJson(goodsPage);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                log.debug("进入page了,结果为SUCCESS, 结果为: " + result);
                return SUCCESS;
            }
        }
        return ERROR;
    }

    @Action(value = "delete", results =
    @Result(location = "/goods/page", type = "redirect"))
    public String delete() throws Exception {
        System.out.println("delete --> goodsId=" + goodsId);
        boolean flag = goodsService.delete(Goods.class, goodsId);
        if (flag) {
            System.out.println("delete()删除成功了");
            return SUCCESS;
        }
        return ERROR;
    }

    @Action(value = "modify", results = {
            @Result(location = "/goods/page", type = "redirect")
    })
    public String modify() {
        if (modifyGoods != null) {
            try {
                log.debug("需要修改的商品id为:" + modifyGoods.getId());
                Goods updateGoods = goodsService.get(Goods.class, modifyGoods.getId());

                updateGoods.setPer(modifyGoods.getPer());
                updateGoods.setTotal(modifyGoods.getTotal());
                updateGoods.setPrice(modifyGoods.getPrice());
                updateGoods.setRetime(TimeUtil.getTime().toString());
                updateGoods.setAvailable(modifyGoods.getAvailable());
//                modifyGoods.setTrait(goods.getTrait());
//                modifyGoods.setIntro(goods.getIntro());
//                modifyGoods.setRemind(goods.getRemind());
//                modifyGoods.setExplains(goods.getExplains());
//                modifyGoods.setTime(goods.getTime());
//                modifyGoods.setImage(goods.getImage());
                goodsService.update(updateGoods);
                return SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ERROR;
    }

    //    @Action(value = "update", results = @Result(type = "chain", location = "/goods/alter"))
    @Action(value = "update", results = {
            @Result(location = "/WEB-INF/content/goods-update.jsp")
    })
    public String update() {
        if (goodsId > 0 && goodsId < 2147483647) {
            goods = goodsService.get(Goods.class, goodsId);
            shapes = shapeService.getByGoods(Shape.class, goods);
            details = detailService.getByGoods(Detail.class, goods);
            line = "\n";
            if (goods != null) {
                return SUCCESS;
            }
        }
        return ERROR;
    }

    @Action(value = "alter",
            results = {@Result(name = SUCCESS, location = "detail", type = "redirectAction", params = {"goodsId", "${goods.id}", "promptInfo.title", "${promptInfo.title}"}),
                    @Result(name = INPUT, location = "/WEB-INF/content/goods.jsp")})
    public String alter() {
        if (goods!=null) {
            log.debug("需要更新的商品id为:"+ goods.getId());
            Goods alterGoods = goodsService.get(Goods.class, goods.getId());
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
                goodsService.update(alterGoods);
                return SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ERROR;
    }

    public ModifyGoods getModifyGoods() {
        return modifyGoods;
    }

    public void setModifyGoods(ModifyGoods modifyGoods) {
        this.modifyGoods = modifyGoods;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    private class ModifyGoods {
        private int id;         // 主键
        private int per;        // 每份多少人次
        private int total;      // 本期商品总需人次
        private int price;      // 商品价格 price = per * total
        private String available;    // 该商品是否参与夺宝(true/false)
        private String retime;  // 商品重新参与夺宝的时间

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPer() {
            return per;
        }

        public void setPer(int per) {
            this.per = per;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getAvailable() {
            return available;
        }

        public void setAvailable(String available) {
            this.available = available;
        }

        public String getRetime() {
            return retime;
        }

        public void setRetime(String retime) {
            this.retime = retime;
        }
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
}
