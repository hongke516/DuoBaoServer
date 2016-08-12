package com.fozoto.duobao.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 一元夺宝 第几期
 * Created by qingyan on 16-7-27.
 */
@Entity(name = "Issue")
@Scope("prototype")
public class Issue implements Serializable{
    private int id;             // 夺宝第几期
    private Goods goods;        // 这期夺宝对应的商品
    private int done;           // 本期已经被购买的人次
    private String over;        // 本期是否完成了  true完成/false未完成
    private String start;       // 本期开始的时间
    private String finish;      // 本期完成时间
    @JSONField(serialize=false)
    private Calculator calculator;  // 本期计算结果
    @JSONField(serialize=false)
    private Lucky lucky;        // 本期的幸运
    @JSONField(serialize=false)
    private List<Annal> annal;        // 本期商品的夺宝所有记录
    @JSONField(serialize=false)
    private List<Number> numbers;   // 所有随机号码
    @JSONField(serialize=false)
    private Cart cart;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @ManyToOne(targetEntity = Goods.class, cascade = CascadeType.REFRESH)
    public Goods getGoods() {
        return goods;
    }

    public int getDone() {
        return done;
    }

    @Column(length = 10)
    public String getFinish() {
        return finish;
    }

    @Column(length = 30)
    public String getStart() {
        return start;
    }

    // Lucky负责维护关系
    @OneToOne(targetEntity = Lucky.class, cascade = CascadeType.ALL, mappedBy = "issue")
    public Lucky getLucky() {
        return lucky;
    }

    @OneToMany(targetEntity = Annal.class, cascade = CascadeType.ALL, mappedBy = "issue")
    public List<Annal> getAnnal() {
        return annal;
    }

    // Calculator负责维护关系
    @OneToOne(targetEntity = Calculator.class, cascade = CascadeType.ALL, mappedBy = "issue")
    public Calculator getCalculator() {
        return calculator;
    }

    public String getOver() {
        return over;
    }


    @OneToMany(targetEntity = Number.class, cascade = CascadeType.ALL, mappedBy = "issue")
    public List<Number> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public void setAnnal(List<Annal> annal) {
        this.annal = annal;
    }

    public void setLucky(Lucky lucky) {
        this.lucky = lucky;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", goods=" + goods +
                ", done=" + done +
                ", over='" + over + '\'' +
                ", start='" + start + '\'' +
                ", finish='" + finish + '\'' +
                '}';
    }

    // Cart负责维护关系
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Cart.class, mappedBy = "issue")
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
