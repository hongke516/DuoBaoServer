package com.fozoto.duobao.action;

import com.fozoto.duobao.core.util.TimeUtil;
import com.fozoto.duobao.model.*;
import com.fozoto.duobao.service.*;
import com.fozoto.duobao.util.entity.PageBean;
import com.fozoto.duobao.util.entity.PromptInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingyan on 16-7-30.
 */
@Namespace("/goods")
@Controller("GoodsAction")
@Scope("prototype")
@ParentPackage(value = "json-default")
@Results({
        @Result(name = "error", location = "/WEB-INF/content/util/prompt-info.jsp"),
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
    // 前台输入的商品滚动图片的缩略图,以换行分割
    public String inputPreviews;
    // 前台输入的商品详情图片,以换行分割
    public String inputDetails;

    // 商品的图片
    private List<Picture> pictures;

    @Autowired
    public PromptInfo promptInfo;

    @Resource(name = "GoodsService")
    private IGoodsService goodsService;

    @Resource(name = "PictureService")
    private IPictureService pictureService;

    /**
     * 新增商品
     */
    @Action(value = "create",
            results = {
                    @Result(name = SUCCESS, location = "detail", type = "redirectAction", params = {"goodsId", "${goodsId}"}),
            })
    public String create(){
        // 管理员权限,才能操作
        if (adminPower()) {
            if (goods != null) {
                // 检查输入
                if (checkGoods(goods)) {
                    // 设置商品的添加时间
                    goods.setTime(TimeUtil.getTime().toString());
                    goods.setRetime(TimeUtil.getTime().toString());
                    // 保存商品
                    boolean flag;
                    try {
                        flag = goodsService.add(goods);
                    } catch (Exception e) {
                        e.printStackTrace();
                        promptInfo.setTitle("保存商品失败");
                        promptInfo.setMessage("请检查输入信息是否有误!");
                        return ERROR;
                    }
                    if (flag) {

                        try {
                            savePictures(pictures);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return ERROR;
                        }

                        promptInfo.setTitle("商品添加成功！");
                        promptInfo.setMessage("恭喜您商品添加成功!");
                        goodsId = goods.getId();

                        // 商品保存后,该商品的夺宝就开始了
                        Issue issue = new Issue();
                        issue.setGoods(goods);
                        issue.setStart(TimeUtil.getTime().toString());
                        issue.setOver("false");
                        issue.setDone(0);

                        try {
                            if (issueService.add(issue)) {
                                return SUCCESS;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            promptInfo.setTitle("保存新的一期错误!");
                            promptInfo.setMessage("新的一期开始时出现错误!");
                            return ERROR;
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
                pictures = pictureService.getByGoods(Picture.class, goodsId);
                if (pictures != null) {
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
            String whereSql = " cate=" + sort;
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
                    pictures = pictureService.getByGoods(Picture.class, goodsId);
                    line = "\n";
                    if (pictures != null) {
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
            results = {@Result(name = SUCCESS, location = "detail", type = "redirectAction", params = {"goodsId", "${goods.id}"}),
                    @Result(name = INPUT, location = "/WEB-INF/content/goods.jsp")})
    public String alter() {
        // 管理员权限,才能操作
        if (adminPower()) {
            if (goods != null) {
//                log.debug("需要更新的商品id为:" + goods.getId());
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
                            // 删除该商品的所有图片
                            boolean isSuccess = pictureService.deleteByGoods(Picture.class, alterGoods.getId());
                            if (isSuccess) {
                                try {
                                    savePictures(pictures);
                                    return SUCCESS;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return ERROR;
                                }
                            }
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

    private void savePictures(List<Picture> pictures) throws Exception{
        pictures = new ArrayList<>();
        // 依赖商品先建立,然后才能保存滚动图片
        if (!StringUtils.isEmpty(inputShapes)) {
            //log.debug("inputShapes={" + inputShapes + "}");
            String[] shapeImages = inputShapes.split("\r\n");
            for (String image : shapeImages) {
                if (!StringUtils.isEmpty(image) && image.length() < 255) {
                    //log.debug("image=" + image);
                    Picture shapePicture = new Picture();
                    shapePicture.setImage(image);
                    shapePicture.setType(Picture.SHAPE);
                    shapePicture.setGoods(goods);
                    if (pictureService.add(shapePicture)) {
                        pictures.add(shapePicture);
                    } else {
                        promptInfo.setTitle("保存滚动图片失败!");
                        promptInfo.setMessage("保存滚动图片失败!,请检查输入是否有误!");
                    }
                } else {
                    promptInfo.setTitle("输入图片地址太长!");
                    promptInfo.setMessage("滚动图片的输入图片地址大于255个字节,请重新输入!");

                }
            }
        } else {
            promptInfo.setTitle("滚动图片不能为空");
            promptInfo.setMessage("对不起, 滚动图片还未输入!");

        }

        // 依赖商品先建立,然后才能保存滚动图片的缩略图
        if (!StringUtils.isEmpty(inputPreviews)) {
            //log.debug("inputPreviews={" + inputPreviews + "}");
            String[] previewsImages = inputPreviews.split("\r\n");
            for (String image : previewsImages) {
                if (!StringUtils.isEmpty(image) && image.length() < 255) {
                    //log.debug("image=" + image);
                    Picture previewPicture = new Picture();
                    previewPicture.setImage(image);
                    previewPicture.setType(Picture.PREVIEW);
                    previewPicture.setGoods(goods);
                    if (pictureService.add(previewPicture)) {
                        pictures.add(previewPicture);
                    } else {
                        promptInfo.setTitle("保存缩略图片失败!");
                        promptInfo.setMessage("保存滚动的缩略图片失败!,请检查输入是否有误!");
                    }
                } else {
                    promptInfo.setTitle("输入图片地址太长!");
                    promptInfo.setMessage("缩略图的输入图片地址大于255个字节,请重新输入!");
                }
            }
        } else {
            promptInfo.setTitle("缩略图不能为空");
            promptInfo.setMessage("对不起, 滚动图片的缩略图还未输入!");
        }

        // 依赖商品先建立,然后才能保存商品详情图片
        if (!StringUtils.isEmpty(inputDetails)) {
            //log.debug("inputDetails={" + inputDetails + "}");
            String[] detailImages = inputDetails.split("\r\n");
            for (String image : detailImages) {
                if (!StringUtils.isEmpty(image) && image.length() < 255) {
                    //log.debug("image=" + image);
                    Picture detailPicture = new Picture();
                    detailPicture.setGoods(goods);
                    detailPicture.setType(Picture.DETAILS);
                    detailPicture.setImage(image);
                    if (pictureService.add(detailPicture)) {
                        pictures.add(detailPicture);
                    } else {
                        promptInfo.setTitle("保存详情图片失败!");
                        promptInfo.setMessage("保存详情图片失败!,请检查输入是否有误!");
                    }
                } else {
                    promptInfo.setTitle("输入图片地址太长!");
                    promptInfo.setMessage("详情图片的输入图片地址大于255个字节,请重新输入!");
                }
            }
        } else {
            promptInfo.setTitle("详情图片不能为空");
            promptInfo.setMessage("对不起, 详情图片还未输入!");
        }
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

    public String getInputPreviews() {
        return inputPreviews;
    }

    public void setInputPreviews(String inputPreviews) {
        this.inputPreviews = inputPreviews;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
