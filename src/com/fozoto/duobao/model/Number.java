package com.fozoto.duobao.model;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;

/**
 * 夺宝号码
 * Created by qingyan on 16-7-27.
 */
@Entity(name = "Number")
@Scope("prototype")
public class Number {
    private int id;             // 主键
    private String num;         // 夺宝号码
    private Issue issue;        // 夺宝号码属于哪期
    private Goods goods;        // 该夺宝号码所属商品
    private User user;          // 该夺宝号码属于谁
    private String time;        // 该夺宝号码被购买时产生的时间

    private Lucky lucky;        // 幸运号码
    private Calculator calculator;  // 计算详情
    private Annal annal;        // 夺宝记录

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public String getNum() {
        return num;
    }

    @ManyToOne(cascade = CascadeType.ALL, optional = false, targetEntity = Issue.class)
    public Issue getIssue() {
        return issue;
    }

    @ManyToOne(cascade = CascadeType.ALL, optional = false, targetEntity = User.class)
    public User getUser() {
        return user;
    }

    public String getTime() {
        return time;
    }

    // Lucky负责维护关系
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "number", targetEntity = Lucky.class)
    public Lucky getLucky() {
        return lucky;
    }

    @ManyToOne(targetEntity = Goods.class, cascade = CascadeType.ALL)
    public Goods getGoods() {
        return goods;
    }

    // Calculator负责维护关系
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Calculator.class, mappedBy = "number")
    public Calculator getCalculator() {
        return calculator;
    }

    // Annal负责维护关系
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "number", targetEntity = Annal.class)
    public Annal getAnnal() {
        return annal;
    }

    public void setAnnal(Annal annal) {
        this.annal = annal;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setLucky(Lucky lucky) {
        this.lucky = lucky;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Number{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

}
