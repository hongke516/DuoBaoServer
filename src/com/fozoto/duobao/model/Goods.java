package com.fozoto.duobao.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.Expose;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 夺宝商品
 * Created by qingyan on 16-7-27.
 */
@Entity(name = "Goods")
@Scope("prototype")
public class Goods implements Serializable{

    @Expose
    private int id;         // 主键
    @Expose
    private int per;        // 每份多少人次
    @Expose
    private int total;      // 本期商品总需人次
    @Expose
    private int price;      // 商品价格 price = per * total
    @Expose
    private String trait;   // 本期商品的额外描述,如十元商品、百元商品、海购商品，这里是图片地址等
    @Expose
    private String intro;   // 文字介绍
    @Expose
    private String remind;  // 额外的红字提醒
    @Expose
    private String image;   // 商品的图片
    @Expose
    private String explains; // 重要说明
    @Expose
    private String available;    // 该商品是否参与夺宝(新品/下架)
    @Expose
    private String time;    // 商品创建时间
    @Expose
    private String retime;  // 商品重新参与夺宝的时间
    @Expose
    private String cate;        // 商品所属分类

    @JSONField(serialize=false)
    private List<Lucky> luckies;    // 商品幸运的号码
    @JSONField(serialize=false)
    private List<Number> numbers;   // 商品所有的夺宝号码
    @JSONField(serialize=false)
    private Calculator calculator;  // 本商品的计算详情
    @JSONField(serialize=false)
    private List<Annal> annals;     // 本商品所有夺宝记录
    @JSONField(serialize=false)
    private List<Issue> issues;     // 本商品的期数
    @JSONField(serialize=false)
    private Cart cart;
    @JSONField(serialize=false)
    private List<Picture> pictures;        // 商品的图片描述

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public int getPer() {
        return per;
    }

    public int getTotal() {
        return total;
    }

    public int getPrice() {
        return price;
    }

    public String getTrait() {
        return trait;
    }

    @Column(columnDefinition = "TEXT")
    public String getIntro() {
        return intro;
    }

    @Column(columnDefinition = "TEXT")
    public String getRemind() {
        return remind;
    }

    @Column(columnDefinition = "TEXT")
    public String getExplains() {
        return explains;
    }

    public String getImage() {
        return image;
    }

    // Lucky负责维护关系
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Lucky.class, mappedBy = "goods")
    public List<Lucky> getLuckies() {
        return luckies;
    }

    // Number负责维护关系
    @OneToMany(targetEntity = Number.class, cascade = CascadeType.ALL, mappedBy = "goods")
    public List<Number> getNumbers() {
        return numbers;
    }

    // Calculator负责维护关系
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Calculator.class, mappedBy = "goods")
    public Calculator getCalculator() {
        return calculator;
    }

    // Annal负责维护关系
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Annal.class, mappedBy = "goods")
    public List<Annal> getAnnals() {
        return annals;
    }

    // Issue负责维护关系
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Issue.class, mappedBy = "goods")
    public List<Issue> getIssues() {
        return issues;
    }

    // Picture负责维护关系
    @OneToMany(targetEntity = Picture.class, cascade = CascadeType.ALL, mappedBy = "goods")
    public List<Picture> getPictures() {
        return pictures;
    }

    // Cart负责维护关系
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Cart.class, mappedBy = "goods")
    public Cart getCart() {
        return cart;
    }

    public String getAvailable() {
        return available;
    }

    public String getTime() {
        return time;
    }

    public String getRetime() {
        return retime;
    }

    public String getCate() {
        return cate;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRetime(String retime) {
        this.retime = retime;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public void setAnnals(List<Annal> annals) {
        this.annals = annals;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }

    public void setLuckies(List<Lucky> luckies) {
        this.luckies = luckies;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPer(int per) {
        this.per = per;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", per=" + per +
                ", total=" + total +
                ", price=" + price +
                ", trait='" + trait + '\'' +
                ", intro='" + intro + '\'' +
                ", remind='" + remind + '\'' +
                ", image='" + image + '\'' +
                ", explains='" + explains + '\'' +
                ", available='" + available + '\'' +
                ", time='" + time + '\'' +
                ", retime='" + retime + '\'' +
                ", cate='" + cate + '\'' +
                '}';
    }

}
