package com.fozoto.duobao.dao.impl;

import com.fozoto.duobao.dao.IHelpDAO;
import com.fozoto.duobao.model.Goods;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by qingyan on 16-7-31.
 */
@Repository
@Transactional
@Scope("prototype")
public class HelpDAO<T extends Serializable> extends BaseDAO<T> implements IHelpDAO<T> {
    /**
     * 通过goodsId查询T
     *
     * @param clazz Issue的反射
     * @param goodsId 需要商品的id
     * @return List<T>
     */
    @Override
    public List<T> getByGoods(Class<T> clazz, int goodsId) {
        String entityName= getEntityName(clazz);
        String sql = "from "+entityName+" where goods_id="+goodsId;
        Query query=em.createQuery(sql);
        return query.getResultList();
    }

    /**
     * 通过自身id和goodsId查询T
     *
     * @param clazz   Issue的反射
     * @param id      T的id
     * @param goodsId 需要商品的id
     * @return T
     */
    @Override
    public T getByGoods(Class<T> clazz, int id, int goodsId) {
        String entityName= getEntityName(clazz);
        String sql = "from "+entityName+" where id="+id+" and goods_id="+goodsId;
        Query query=em.createQuery(sql);
        return (T)query.getResultList().get(0);
    }

    /**
     * 通过Issue的id查询T
     *
     * @param clazz   T
     * @param issueId 期的id
     * @return List<T>
     */
    @Override
    public List<T> getByIssue(Class<T> clazz, int issueId) {
        String entityName= getEntityName(clazz);
        String sql = "from "+entityName+" where issue_id="+issueId;
        Query query=em.createQuery(sql);
        return query.getResultList();
    }

    /**
     * 通过Issue的id和T的id查询T
     *
     * @param clazz   T
     * @param id      T的id
     * @param issueId 期的id
     * @return T
     */
    @Override
    public T getByIssue(Class<T> clazz, int id, int issueId) {
        String entityName= getEntityName(clazz);
        String sql = "from "+entityName+" where id="+id+" and issue_id="+issueId;
        Query query=em.createQuery(sql);
        return (T)query.getResultList().get(0);
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
        try {
            String entityName= getEntityName(clazz);
            String sql = "delete from "+entityName+" where goods_id="+goodsId;
            em.createQuery(sql).executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
