package com.fozoto.duobao.model;

import org.springframework.context.annotation.Scope;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 首页里的自动滚动的几张广告图片
 * Created by qingyan on 16-8-8.
 */
@Entity(name = "Ad")
@Scope("prototype")
public class Ad implements Serializable{
    private int id;             // 主键
    private String image;       // 图片地址

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
