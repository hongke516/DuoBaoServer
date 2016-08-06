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
     *
     * @param clazz Issue的反射
     * @param goodsId 需要商品的id
     * @return List<T>
     */
    public List<T> getByGoods(Class<T> clazz, int goodsId);

    /**
     * 通过自身id和goodsId查询T
     *
     * @param clazz Issue的反射
     * @param id T的id
     * @param goodsId 需要商品的id
     * @return T
     */
    public T getByGoods(Class<T> clazz, int id, int goodsId);

    /**
     * 通过Issue的id查询T
     * @param clazz T
     * @param issueId 期的id
     * @return List<T>
     */
    public List<T> getByIssue(Class<T> clazz, int issueId);

    /**
     * 通过Issue的id和T的id查询T
     * @param clazz T
     * @param id T的id
     * @param issueId 期的id
     * @return T
     */
    public T getByIssue(Class<T> clazz, int id, int issueId);
}
