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
    private Issue issue;        // 第几期
    private User user;          // 本条记录所对应的用户
    private Number number;      // 本条记录的夺宝号码
    private Goods goods;        // 本条记录的商品

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Issue.class, optional = false)
    public Issue getIssue() {
        return issue;
    }

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, optional = false)
    public User getUser() {
        return user;
    }

    @OneToOne(targetEntity = Number.class, cascade = CascadeType.ALL, optional = false)
    public Number getNumber() {
        return number;
    }

    @ManyToOne(targetEntity = Goods.class, cascade = CascadeType.ALL, optional = false)
    public Goods getGoods() {
        return goods;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Annal{" +
                "id=" + id +
                '}';
    }
}
