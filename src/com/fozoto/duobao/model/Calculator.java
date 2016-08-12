package com.fozoto.duobao.model;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 商品的夺宝计算详情
 * Created by qingyan on 16-7-27.
 */
@Entity(name = "Calculator")
@Scope("prototype")
public class Calculator implements Serializable{

    private int id;                 // 主键
    private Goods goods;            // 所属商品
    private long numA;              // 本商品最后50条夺宝记录的计算结果
    private long numB;              // 重庆时时彩开奖的号码
    private long result;            // 计算结果
    private Issue issue;            // 计算结果属于哪一期

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @OneToOne(targetEntity = Goods.class, cascade = CascadeType.REFRESH, optional = false)
    public Goods getGoods() {
        return goods;
    }

    @OneToOne(targetEntity = Issue.class, cascade = CascadeType.REFRESH, optional = false)
    public Issue getIssue() {
        return issue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public long getNumA() {
        return numA;
    }

    public void setNumA(long numA) {
        this.numA = numA;
    }

    public long getNumB() {
        return numB;
    }

    public void setNumB(long numB) {
        this.numB = numB;
    }

    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "id=" + id +
                ", numA='" + numA + '\'' +
                ", numB='" + numB + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
