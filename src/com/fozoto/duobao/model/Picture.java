package com.fozoto.duobao.model;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 商品的图片描述:
 *  type:   0 滚动图片
 *          1 滚动图片的缩略图
 *          2 详情图片
 * Created by qingyan on 16-8-14.
 */
@Entity(name = "Picture")
@Scope("prototype")
public class Picture implements Serializable {
    public final static int SHAPE = 0;      // 滚动图片
    public final static int PREVIEW = 1;    // 滚动图片的缩略图
    public final static int DETAILS = 2;    // 详情图片

    private int id;             // 主键
    private String image;       // 图片地址
    private int type;           // 图片类型
    // 外键（多对一，外键放在多的一方）
    private Goods goods;        // 图片所属商品

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public int getType() {
        return type;
    }

    @ManyToOne(targetEntity = Goods.class, cascade = CascadeType.REFRESH, optional = false)
    public Goods getGoods() {
        return goods;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", type=" + type +
                '}';
    }
}
