package com.fozoto.duobao.model;

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
    private String finish;      // 本期是否完成了  true/false
    private String start;       // 本期开始的时间

    private List<Number> numbers;   // 本期的所有夺宝号码
    private Lucky lucky;        // 本期的幸运
    private List<Annal> annal;        // 本期商品的夺宝所有记录

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

    // Number负责维护与用户的关系
    @OneToMany(targetEntity = Number.class, mappedBy = "issue", cascade = CascadeType.ALL)
    public List<Number> getNumbers() {
        return numbers;
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

    public void setAnnal(List<Annal> annal) {
        this.annal = annal;
    }

    public void setLucky(Lucky lucky) {
        this.lucky = lucky;
    }

    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
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
                ", done=" + done +
                ", finish='" + finish + '\'' +
                ", start='" + start + '\'' +
                '}';
    }
}
