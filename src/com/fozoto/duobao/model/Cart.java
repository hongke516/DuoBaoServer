package com.fozoto.duobao.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by qingyan on 16-8-12.
 */
@Entity(name = "Cart")
@Scope("prototype")
public class Cart implements Serializable{
    public final static String CART_SESSION = "cart_session";

    private int id;
    private int total;      // 总共需要多少钱=每份单价*购买个数
    private int num;        // 该商品购买个数
    private int status;     // 该清单是否完成交易(0未完成/1完成交易)
    private Goods goods;    // 该清单属于哪个商品
    private Gamester gamester;  // 由谁创建的清单
    private Issue issue;    // 该清单是哪一期的

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public int getTotal() {
        return total;
    }

    public int getNum() {
        return num;
    }

    public int getStatus() {
        return status;
    }

    @OneToOne(targetEntity = Goods.class, cascade = CascadeType.REFRESH, optional = false)
    public Goods getGoods() {
        return goods;
    }

    @OneToOne(targetEntity = Gamester.class, cascade = CascadeType.REFRESH, optional = false)
    public Gamester getGamester() {
        return gamester;
    }

    @OneToOne(targetEntity = Issue.class, cascade = CascadeType.REFRESH, optional = false)
    public Issue getIssue() {
        return issue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setGamester(Gamester gamester) {
        this.gamester = gamester;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", total=" + total +
                ", num=" + num +
                ", status=" + status +
                ", goods=" + goods +
                ", gamester=" + gamester +
                ", issue=" + issue +
                '}';
    }
}
