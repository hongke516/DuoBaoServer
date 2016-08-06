package com.fozoto.duobao.model;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 夺宝幸运
 * Created by qingyan on 16-7-27.
 */
@Entity(name = "Lucky")
@Scope("prototype")
public class Lucky implements Serializable{

    private int id;             // 主键
    private Issue issue;        // 哪一期
    private Gamester gamester;  // 幸运的用户
    private Goods goods;        // 幸运的商品
    private String time;        // 揭晓时间

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @OneToOne(targetEntity = Issue.class, cascade = CascadeType.ALL, optional = false)
    public Issue getIssue() {
        return issue;
    }

    @ManyToOne(targetEntity = Gamester.class, cascade = CascadeType.REFRESH, optional = false)
    public Gamester getGamester() {
        return gamester;
    }

    public String getTime() {
        return time;
    }

    @ManyToOne(targetEntity = Goods.class, optional = false, cascade = CascadeType.REFRESH)
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
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

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Lucky{" +
                "id=" + id +
                ", time='" + time + '\'' +
                '}';
    }
}
