package com.fozoto.duobao.action.bean;

/**
 * 展示在首页的每份商品
 * Created by qingyan on 16-8-10.
 */
public class Piece {
    private String intro;   // 商品介绍
    private String image;   // 商品的图片
    private String trait;   // 商品额外描述图片地址
    private String remind;  // 额外的红字提醒
    private String explains;    // 重要提醒
    private int total;      // 商品总需人次
    private int done;       // 商品已完成购买人次
    private int last;       // 商品剩余人次
    private float degree;     // 商品进度
    private int goodsId;    // 哪个商品
    private int issueId;    // 哪一期
    private int page;       // 当前第几页
    private int size;       // 每页显示几条记录
    private int num;        // 总共有几页
    private int all;        // 总共有几条记录

    public String getIntro() {
        return intro;
    }

    public String getImage() {
        return image;
    }

    public int getTotal() {
        return total;
    }

    public int getDone() {
        return done;
    }

    public float getDegree() {
        return degree;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
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

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "intro='" + intro + '\'' +
                ", image='" + image + '\'' +
                ", trait='" + trait + '\'' +
                ", remind='" + remind + '\'' +
                ", explains='" + explains + '\'' +
                ", total=" + total +
                ", done=" + done +
                ", last=" + last +
                ", degree=" + degree +
                ", goodsId=" + goodsId +
                ", issueId=" + issueId +
                ", page=" + page +
                ", size=" + size +
                ", num=" + num +
                ", all=" + all +
                '}';
    }
}
