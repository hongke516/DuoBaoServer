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
     * @param clazz
     * @param goods 需要查询的商品
     * @return List<T>
     */
    @Override
    public List<T> getByGoods(Class<T> clazz, Goods goods) {
        String entityName= getEntityName(clazz);
        String sql = "from "+entityName+" where goods_id="+goods.getId();
        Query query=em.createQuery(sql);
        return query.getResultList();
    }
}
