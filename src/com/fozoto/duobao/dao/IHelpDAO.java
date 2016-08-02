package com.fozoto.duobao.dao;

import com.fozoto.duobao.model.Goods;
import com.fozoto.duobao.model.Shape;

import java.util.List;

/**
 * Created by qingyan on 16-7-31.
 */
public interface IHelpDAO<T> extends IBaseDAO<T>{

    /**
     * 通过goodsId查询T
     * @param goods 需要查询的商品
     * @return List<T>
     */
    public List<T> getByGoods(Class<T> clazz, Goods goods);
}
