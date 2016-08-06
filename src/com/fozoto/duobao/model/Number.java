package com.fozoto.duobao.model;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by qingyan on 16-8-4.
 */
@Entity(name = "Number")
@Scope("prototype")
public class Number implements Serializable {
    private int id;
    private long num;
    private Issue issue;
    private Goods goods;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public long getNum() {
        return num;
    }

    @ManyToOne(targetEntity = Issue.class, cascade = CascadeType.REFRESH, optional = false)
    public Issue getIssue() {
        return issue;
    }

    @ManyToOne(targetEntity = Goods.class, cascade = CascadeType.REFRESH, optional = false)
    public Goods getGoods() {
        return goods;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
