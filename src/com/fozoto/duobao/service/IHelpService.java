package com.fozoto.duobao.service;

import com.fozoto.duobao.model.Goods;

import java.util.List;

/**
 * Created by qingyan on 16-7-31.
 */
public interface IHelpService<T> extends IBaseService<T> {

    public List<T> getByGoods(Class<T> clazz, Goods goods);
}
