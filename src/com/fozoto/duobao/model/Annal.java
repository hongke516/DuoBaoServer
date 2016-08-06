package com.fozoto.duobao.model;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 夺宝记录
 * Created by qingyan on 16-7-27.
 */
@Entity(name = "Annal")
@Scope("prototype")
public class Annal implements Serializable{

    private int id;             // 主键
    private long num;           // 夺宝号码
    private String time;        // 该夺宝号码被购买时产生的时间
    private Issue issue;        // 第几期
    private Gamester gamester;  // 本条记录所对应的用户
    private Goods goods;        // 本条记录的商品

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Issue.class, optional = false)
    public Issue getIssue() {
        return issue;
    }

    @ManyToOne(targetEntity = Gamester.class, cascade = CascadeType.REFRESH, optional = false)
    public Gamester getGamester() {
        return gamester;
    }

    @ManyToOne(targetEntity = Goods.class, cascade = CascadeType.REFRESH, optional = false)
    public Goods getGoods() {
        return goods;
    }

    public long getNum() {
        return num;
    }

    public String getTime() {
        return time;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public void setGamester(Gamester gamester) {
        this.gamester = gamester;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Annal{" +
                "id=" + id +
                ", num=" + num +
                ", time='" + time + '\'' +
                ", issue=" + issue +
                ", gamester=" + gamester +
                ", goods=" + goods +
                '}';
    }
}
