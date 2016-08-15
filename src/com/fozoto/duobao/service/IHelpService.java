package com.fozoto.duobao.service;

import com.fozoto.duobao.model.Goods;

import java.util.List;

/**
 * Created by qingyan on 16-7-31.
 */
public interface IHelpService<T> extends IBaseService<T> {

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
     * 通过issueId查询T
     *
     * @param clazz Issue的反射
     * @param issueId 需要商品的id
     * @return List<T>
     */
    public List<T> getByIssue(Class<T> clazz, int issueId);

    /**
     * 通过自身id和issueId查询T
     *
     * @param clazz Issue的反射
     * @param id T的id
     * @param issueId 需要商品的id
     * @return T
     */
    public T getByIssue(Class<T> clazz, int id, int issueId);

    /**
     * 根据商品id进行删除T
     * @param clazz T
     * @param goodsId 商品id
     * @return true成功删除/false删除失败
     */
    public boolean deleteByGoods(Class<T> clazz, int goodsId);
}
