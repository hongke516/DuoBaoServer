package com.fozoto.duobao.model;

import java.io.Serializable;

/**
 * 当某期商品夺宝买满了,等待时时彩计算结果揭晓幸运者,存放这期的id
 * Created by qingyan on 16-8-4.
 */
public class Full implements Serializable{
    private int id;
    private int fullId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFullId() {
        return fullId;
    }

    public void setFullId(int fullId) {
        this.fullId = fullId;
    }
}
