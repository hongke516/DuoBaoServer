package com.fozoto.duobao.action.service;

import com.fozoto.duobao.action.bean.Piece;
import com.fozoto.duobao.model.Ad;
import com.fozoto.duobao.model.Goods;
import com.fozoto.duobao.model.Issue;
import com.fozoto.duobao.service.IAdService;
import com.fozoto.duobao.service.IGoodsService;
import com.fozoto.duobao.service.IIssueService;
import com.fozoto.duobao.util.entity.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingyan on 16-8-10.
 */
@Service("HomeService")
@Transactional
@Scope("prototype")
public class IndexService {
    private Logger log = Logger.getLogger(IndexService.class);

    @Resource(name = "AdService")
    private IAdService adService;

    @Resource(name = "GoodsService")
    private IGoodsService goodsService;

    @Resource(name = "IssueService")
    private IIssueService issueService;

    /**
     * 首页滚动的所有广告图片(最多10张图片)
     * @return PageBean<Ad>
     */
    public PageBean<Ad> listAd() {
        PageBean<Ad> adPageBean = adService.getPaginationService(Ad.class, 1, 10, null, null);
        if (adPageBean != null) {
            return adPageBean;
        }
        return null;
    }

    /**
     * 获取指定页的Goods正在夺宝的集合
     * @param page 第几页
     * @param size 每页几条数据
     * @return PageBean<Goods>
     */
    public PageBean<Goods> listGoods(int page, int size) {
        if (checkInt(page) && checkInt(size)) {
            PageBean<Goods> goodsPage = goodsService.getPaginationService(Goods.class, page, size, "available='新品'", null);
            if (goodsPage != null) {
                // log.debug("总共有" + goodsPage.getAllRows() + "条记录");
                return goodsPage;
            }
        }
        return null;
    }

    public PageBean<Goods> listGoods(int page, int size, String cate) {
        if (checkInt(page) && checkInt(size) && !StringUtils.isEmpty(cate)) {
            String wheresql = " available='新品' and cate='"+cate+"'";
            PageBean<Goods> goodsPage = goodsService.getPaginationService(Goods.class, page, size, wheresql, null);
            if (goodsPage != null) {
                // log.debug("总共有" + goodsPage.getAllRows() + "条记录");
                return goodsPage;
            }
        }
        return null;
    }

    /**
     * 获取正在夺宝的商品的期
     * @param goodsId 商品id
     * @return Issue
     */
    public Issue getIssueOnDuobao(int goodsId) {
        if (checkInt(goodsId)) {
            Issue issue = issueService.onDuobao(goodsId);
            if (issue != null) {
                return issue;
            }
        }
        return null;
    }

    /**
     * 获取商品的夺宝历史记录的期
     * @param goodsId 商品id
     * @return List<Issue>
     */
    public List<Issue> getIssueOutDuobao(int goodsId) {
        if (checkInt(goodsId)) {
            List<Issue> issues = issueService.outDuobao(goodsId);
            if (issues.size()>0) {
                return issues;
            }
        }
        return null;
    }

    /**
     * 获取在首页展示的每份商品
     * @param goods 商品
     * @param issue 期
     * @return Piece
     */
    private Piece getPiece(Goods goods, Issue issue, PageBean<Goods> goodsPageBean) {
        if (goods != null && issue != null) {
            Piece piece = new Piece();
            piece.setIntro(goods.getIntro());
            piece.setImage(goods.getImage());
            piece.setDone(issue.getDone());
            piece.setTotal(goods.getTotal());
            piece.setGoodsId(goods.getId());
            piece.setIssueId(issue.getId());
            piece.setTrait(goods.getTrait());
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(0);
            piece.setDegree(Integer.parseInt(numberFormat.format((float)issue.getDone()/(float)goods.getTotal()*100)));
            piece.setLast(goods.getTotal()-issue.getDone());
            piece.setPage(goodsPageBean.getCurrentPage());
            piece.setSize(goodsPageBean.getPageSize());
            piece.setNum((int)goodsPageBean.getAllPage());
            piece.setAll((int) goodsPageBean.getAllRows());
            return piece;
        }
        return null;
    }

    public List<Piece> listPieceByCate(int page, int size, String cate) {
//        log.debug("进入listPieceByCate了");
        try {
            PageBean<Goods> goodsPageBean = listGoods(page, size, cate);
            List<Goods> goodses = goodsPageBean.getQueryResultList();
            List<Issue> issues = new ArrayList<>();
            if (goodses.size()>0) {
                for (Goods goods : goodses) {
                    Issue issue = getIssueOnDuobao(goods.getId());
                    issues.add(issue);
//                    log.debug(issue.toString());
                }
//                log.debug(goodses.toString());
                List<Piece> pieces = listPiece(goodses, issues, goodsPageBean);
                if (pieces.size()>0) {
//                    log.debug(pieces.toString());
                    return pieces;
                }
            } else {
                log.error("listPieceByCate <--> goodses为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Piece> listPieceAll(int page, int size) {
//        log.debug("进入listPieceByCate了");
        try {
            PageBean<Goods> goodsPageBean = listGoods(page, size);
            List<Goods> goodses = goodsPageBean.getQueryResultList();
            List<Issue> issues = new ArrayList<>();
            if (goodses.size()>0) {
                for (Goods goods : goodses) {
                    Issue issue = getIssueOnDuobao(goods.getId());
                    issues.add(issue);
//                    log.debug(issue.toString());
                }
//                log.debug(goodses.toString());
                List<Piece> pieces = listPiece(goodses, issues, goodsPageBean);
                if (pieces.size()>0) {
//                    log.debug(pieces.toString());
                    return pieces;
                }
            } else {
                log.error("listPieceAll <--> goodses为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取正在夺宝的Piece的集合, 注意goodes的size和issues的size一样大小
     * @param goodses 商品集合
     * @param issues 期集合
     * @return List<Piece>
     */
    public List<Piece> listPiece(List<Goods> goodses, List<Issue> issues, PageBean<Goods> goodsPageBean) {
        if (goodses.size()>0 && issues.size()>0 && (goodses.size() == issues.size())) {
            List<Piece> pieces = new ArrayList<>();
            for (int i=0; i<goodses.size(); i++) {
                Piece piece = getPiece(goodses.get(i), issues.get(i), goodsPageBean);
                pieces.add(piece);
            }
            return pieces;
        }
        return null;
    }

    private boolean checkInt(int i) {
        return i > 0 && i < 2147483647;
    }

}
