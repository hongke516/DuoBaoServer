package com.fozoto.duobao.action;

import com.fozoto.duobao.action.bean.Piece;
import com.fozoto.duobao.action.service.IndexService;
import com.fozoto.duobao.model.Ad;
import com.fozoto.duobao.model.Goods;
import com.fozoto.duobao.model.Issue;
import com.fozoto.duobao.model.Picture;
import com.fozoto.duobao.service.IGoodsService;
import com.fozoto.duobao.service.IIssueService;
import com.fozoto.duobao.service.IPictureService;
import com.fozoto.duobao.util.entity.PageBean;
import com.fozoto.duobao.util.entity.PromptInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by qingyan on 16-8-10.
 */
@Namespace("/")
@Controller("HomeAction")
@Scope("prototype")
@Results({
        @Result(name = "error", location = "/WEB-INF/content/util/prompt-info.jsp"),
        @Result(name = "input", location = "/goods-add", type = "redirect")
})
public class IndexAction extends BaseAction {

    @Autowired
    private PageBean<Ad> adPageBean;

    // 优选商品
    private List<Piece> greatPiece;

    // 手机平板
    private List<Piece> phonePiece;

    // 电脑办公
    private List<Piece> pcPiece;

    // 数码影音
    private List<Piece> digitalPiece;

    // 女性时尚
    private List<Piece> womenPiece;

    // 美食天地
    private List<Piece> foodPiece;

    // 潮流新品
    private List<Piece> newPiece;

    // 网易周边
    private List<Piece> yiPiece;

    // 其他商品
    private List<Piece> otherPiece;

    /**
     * 分类
     */
    private String sort;
    private int page;   // 第几页
    private int size;   // 每页几条数据
    private List<Piece> sortPiece;
    private int num;    // 总页数
    private int all;    // 总记录数

    /**
     * 详情
     */
    private Issue issue;
    private List<Picture> pictures;
    private Piece piece;
    private int goodsId;
    @Resource(name = "GoodsService")
    private IGoodsService goodsService;
    @Resource(name = "IssueService")
    private IIssueService issueService;
    @Resource(name = "PictureService")
    private IPictureService pictureService;


    @Autowired
    private IndexService indexService;

    @Autowired
    private PromptInfo promptInfo;

    @Action(results = {
            @Result(location = "/WEB-INF/content/home.jsp")
    })
    @Override
    public String execute() throws Exception {
        adPageBean = indexService.listAd();
        if (adPageBean == null) {
            promptInfo.setTitle("暂无广告");
            promptInfo.setMessage("目前还没有首页滚动广告");
            promptInfo.setTogo("/ad");
            return ERROR;
        }
        greatPiece = indexService.listPieceByCate(1, 5, "优选商品");
        if (greatPiece == null) {
            promptInfo.setTitle("优选商品");
            promptInfo.setMessage("目前还没有优选商品的商品");
            promptInfo.setTogo("/goods-add");
            return ERROR;
        }
        phonePiece = indexService.listPieceByCate(1, 5, "手机平板");
        if (phonePiece == null) {
            promptInfo.setTitle("手机平板");
            promptInfo.setMessage("目前还没有手机平板的商品");
            promptInfo.setTogo("/goods-add");
            return ERROR;
        }
        pcPiece = indexService.listPieceByCate(1, 5, "电脑办公");
        if (pcPiece == null) {
            promptInfo.setTitle("电脑办公");
            promptInfo.setMessage("目前还没有电脑办公的商品");
            promptInfo.setTogo("/goods-add");
            return ERROR;
        }
        digitalPiece = indexService.listPieceByCate(1, 5, "数码影音");
        if (digitalPiece == null) {
            promptInfo.setTitle("数码影音");
            promptInfo.setMessage("目前还没有数码影音的商品");
            promptInfo.setTogo("/goods-add");
            return ERROR;
        }
        womenPiece = indexService.listPieceByCate(1, 5, "女性时尚");
        if (womenPiece == null) {
            promptInfo.setTitle("女性时尚");
            promptInfo.setMessage("目前还没有女性时尚的商品");
            promptInfo.setTogo("/goods-add");
            return ERROR;
        }
        foodPiece = indexService.listPieceByCate(1, 5, "美食天地");
        if (foodPiece == null) {
            promptInfo.setTitle("美食天地");
            promptInfo.setMessage("目前还没有美食天地的商品");
            promptInfo.setTogo("/goods-add");
            return ERROR;
        }
        newPiece = indexService.listPieceByCate(1, 5, "潮流新品");
        if (newPiece == null) {
            promptInfo.setTitle("潮流新品");
            promptInfo.setMessage("目前还没有潮流新品的商品");
            promptInfo.setTogo("/goods-add");
            return ERROR;
        }
        yiPiece = indexService.listPieceByCate(1, 5, "网易周边");
        if (yiPiece == null) {
            promptInfo.setTitle("网易周边");
            promptInfo.setMessage("目前还没有网易周边的商品");
            promptInfo.setTogo("/goods-add");
            return ERROR;
        }
        otherPiece = indexService.listPieceByCate(1, 5, "其他商品");
        if (yiPiece == null) {
            promptInfo.setTitle("其他商品");
            promptInfo.setMessage("目前还没有其他商品的商品");
            promptInfo.setTogo("/goods-add");
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 按商品类型分类
     */
    @Action(value = "cate", results = {
            @Result(location = "/WEB-INF/content/sort.jsp")
    })
    public String cate() {
        if (!StringUtils.isEmpty(sort)) {
            sortPiece = indexService.listPieceByCate(page, size, sort);
            if (sortPiece != null) {
                num = sortPiece.get(0).getNum();
                all = sortPiece.get(0).getAll();
                return SUCCESS;
            }
        }
        return ERROR;
    }

    /**
     * 所有商品分类
     */
    @Action(value = "all", results = {
            @Result(location = "/WEB-INF/content/sort.jsp")
    })
    public String all() {
        sortPiece = indexService.listPieceAll(page, size);
        if (sortPiece != null) {
            num = sortPiece.get(0).getNum();
            all = sortPiece.get(0).getAll();
            return SUCCESS;
        }
        return ERROR;
    }

    @Action(value = "detail", results = {
            @Result(location = "/WEB-INF/content/detail.jsp")
    })
    public String detail() {
        if (checkInt(goodsId)) {
            Goods goods = goodsService.get(Goods.class, goodsId);
            Issue issue = issueService.onDuobao(goodsId);
            if (goods!=null && issue!=null) {
                piece = indexService.getPiece(goods, issue);
                pictures = pictureService.getByGoods(Picture.class, goodsId);
                if (piece != null && pictures!=null) {
                    return SUCCESS;
                }
            }
        }
        promptInfo.setTitle("商品信息出现错误!");
        promptInfo.setMessage("请联系我方工作人员,将尽快修复!");
        return ERROR;
    }

    public PageBean<Ad> getAdPageBean() {
        return adPageBean;
    }

    public void setAdPageBean(PageBean<Ad> adPageBean) {
        this.adPageBean = adPageBean;
    }

    public List<Piece> getPhonePiece() {
        return phonePiece;
    }

    public void setPhonePiece(List<Piece> phonePiece) {
        this.phonePiece = phonePiece;
    }

    public List<Piece> getPcPiece() {
        return pcPiece;
    }

    public void setPcPiece(List<Piece> pcPiece) {
        this.pcPiece = pcPiece;
    }

    public List<Piece> getDigitalPiece() {
        return digitalPiece;
    }

    public void setDigitalPiece(List<Piece> digitalPiece) {
        this.digitalPiece = digitalPiece;
    }

    public List<Piece> getWomenPiece() {
        return womenPiece;
    }

    public void setWomenPiece(List<Piece> womenPiece) {
        this.womenPiece = womenPiece;
    }

    public List<Piece> getFoodPiece() {
        return foodPiece;
    }

    public void setFoodPiece(List<Piece> foodPiece) {
        this.foodPiece = foodPiece;
    }

    public List<Piece> getNewPiece() {
        return newPiece;
    }

    public void setNewPiece(List<Piece> newPiece) {
        this.newPiece = newPiece;
    }

    public List<Piece> getYiPiece() {
        return yiPiece;
    }

    public void setYiPiece(List<Piece> yiPiece) {
        this.yiPiece = yiPiece;
    }

    public PromptInfo getPromptInfo() {
        return promptInfo;
    }

    public void setPromptInfo(PromptInfo promptInfo) {
        this.promptInfo = promptInfo;
    }

    public List<Piece> getGreatPiece() {
        return greatPiece;
    }

    public void setGreatPiece(List<Piece> greatPiece) {
        this.greatPiece = greatPiece;
    }

    public List<Piece> getOtherPiece() {
        return otherPiece;
    }

    public void setOtherPiece(List<Piece> otherPiece) {
        this.otherPiece = otherPiece;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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

    public List<Piece> getSortPiece() {
        return sortPiece;
    }

    public void setSortPiece(List<Piece> sortPiece) {
        this.sortPiece = sortPiece;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }
}
