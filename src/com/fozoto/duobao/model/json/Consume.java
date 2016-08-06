package com.fozoto.duobao.model.json;

/**
 * Created by qingyan on 16-8-4.
 */
public class Consume {

    // 用户购买了多少份
    private int piece;
    // 用户购买的商品
    private int goodsId;
    // 用户购买的是哪期
    private int issueId;
    // 哪个用户购买的
    private int gamesterId;

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getGamesterId() {
        return gamesterId;
    }

    public void setGamesterId(int gamesterId) {
        this.gamesterId = gamesterId;
    }
}
