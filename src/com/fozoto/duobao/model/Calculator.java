package com.fozoto.duobao.model;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 商品的夺宝记录详情
 * Created by qingyan on 16-7-27.
 */
@Entity(name = "Calculator")
@Scope("prototype")
public class Calculator implements Serializable{

    private int id;                 //主键
    private List<Number> numbers;   // 本商品最后50条夺宝记录
    private Goods goods;            // 所属商品
    private String numA;            // 本商品最后50条夺宝记录的计算结果
    private String numB;            // 重庆时时彩开奖的号码
    private String result;          // 计算结果

    private Lucky lucky;            // 幸运号码

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    // Number负责维护关系
    @OneToMany(targetEntity = Number.class, cascade = CascadeType.ALL, mappedBy = "calculator")
    public List<Number> getNumbers() {
        return numbers;
    }

    @OneToOne(targetEntity = Goods.class, cascade = CascadeType.ALL, optional = false)
    public Goods getGoods() {
        return goods;
    }

    // Lucky负责维护关系
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "calculator", targetEntity = Lucky.class)
    public Lucky getLucky() {
        return lucky;
    }

    public String getNumA() {
        return numA;
    }

    public String getNumB() {
        return numB;
    }

    public String getResult() {
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }

    public void setLucky(Lucky lucky) {
        this.lucky = lucky;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setNumA(String numA) {
        this.numA = numA;
    }

    public void setNumB(String numB) {
        this.numB = numB;
    }

    public void setResult(String result) {
        this.result = result;
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
