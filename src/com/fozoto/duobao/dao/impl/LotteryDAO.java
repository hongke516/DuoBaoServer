package com.fozoto.duobao.dao.impl;

import com.fozoto.duobao.dao.ILotteryDAO;
import com.fozoto.duobao.model.Lottery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

/**
 * Created by qingyan on 16-7-28.
 */
@Repository
@Transactional
@Scope("prototype")
public class LotteryDAO extends BaseDAO<Lottery> implements ILotteryDAO {
    /**
     * 根据时时彩期号查询得到Lottery实体
     *
     * @param clazz Lottery.class
     * @param expect 时时彩第几期
     * @return Lottery实体
     */
    @Override
    public Lottery get(Class<Lottery> clazz, String expect) {
        String entityName= getEntityName(clazz);
        Query query=em.createQuery("from "+entityName+" where "+Lottery.EXPECT+"='"+expect+"'");
        if (query.getResultList().size() == 0) {
            return null;
        }
        return (Lottery) query.getResultList().get(0);
    }
}
