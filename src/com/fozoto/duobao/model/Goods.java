package com.fozoto.duobao.model;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.List;

/**
 * 夺宝商品
 * Created by qingyan on 16-7-27.
 */
@Entity(name = "Goods")
@Scope("prototype")
public class Goods {

    private int id;         // 主键
    private int per;        // 每份多少人次
    private int total;      // 本期商品总需人次
    private int price;      // 商品价格 price = per * total
    private String trait;   // 本期商品的额外描述,如十元商品、百元商品、海购商品等
    private String intro;   // 文字介绍
    private String remind;   // 额外的红字提醒
    private String image;   // 商品的图片

    private List<Lucky> luckies;    // 商品幸运的号码
    private List<Number> numbers;   // 商品所有的夺宝号码
    private List<Detail> details;   // 商品的图片描述详情
    private List<Shape> shapes;     // 商品的形状,用以向用户展示商品
    private Calculator calculator;  // 本商品的计算详情
    private List<Annal> annals;     // 本商品所有夺宝记录


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

    public String getIntro() {
        return intro;
    }

    public String getRemind() {
        return remind;
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

    // Detail负责维护关系
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Detail.class, mappedBy = "goods")
    public List<Detail> getDetails() {
        return details;
    }

    // Shape负责维护关系
    @OneToMany(targetEntity = Shape.class, cascade = CascadeType.ALL, mappedBy = "goods")
    public List<Shape> getShapes() {
        return shapes;
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

    public void setAnnals(List<Annal> annals) {
        this.annals = annals;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
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
                '}';
    }

}
