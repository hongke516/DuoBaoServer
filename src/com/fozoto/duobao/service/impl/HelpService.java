package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.dao.IHelpDAO;
import com.fozoto.duobao.model.Goods;
import com.fozoto.duobao.service.IHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qingyan on 16-7-31.
 */
@Service("HelpService")
@Transactional
@Scope("prototype")
public class HelpService <T extends Serializable> extends BaseService<T> implements IHelpService<T> {

    @Autowired
    public IHelpDAO<T> helpDAO;

    /**
     * 通过goodsId查询T
     *
     * @param clazz Issue的反射
     * @param goodsId 需要商品的id
     * @return List<T>
     */
    @Override
    public List<T> getByGoods(Class<T> clazz, int goodsId) {
        return helpDAO.getByGoods(clazz, goodsId);
    }

    /**
     * 通过自身id和goodsId查询T
     *
     * @param clazz Issue的反射
     * @param id T的id
     * @param goodsId 需要商品的id
     * @return T
     */
    public T getByGoods(Class<T> clazz, int id, int goodsId) {
        return helpDAO.getByGoods(clazz, id, goodsId);
    }

    /**
     * 通过issueId查询T
     *
     * @param clazz   Issue的反射
     * @param issueId 需要商品的id
     * @return List<T>
     */
    @Override
    public List<T> getByIssue(Class<T> clazz, int issueId) {
        return helpDAO.getByIssue(clazz, issueId);
    }

    /**
     * 通过自身id和issueId查询T
     *
     * @param clazz   Issue的反射
     * @param id      T的id
     * @param issueId 需要商品的id
     * @return T
     */
    @Override
    public T getByIssue(Class<T> clazz, int id, int issueId) {
        return helpDAO.getByIssue(clazz, id, issueId);
    }

    /**
     * 根据商品id进行删除T
     *
     * @param clazz   T
     * @param goodsId 商品id
     * @return true成功删除/false删除失败
     */
    @Override
    public boolean deleteByGoods(Class<T> clazz, int goodsId) {
        return helpDAO.deleteByGoods(clazz, goodsId);
    }
}
