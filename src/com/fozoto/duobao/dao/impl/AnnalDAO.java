package com.fozoto.duobao.dao.impl;

import com.fozoto.duobao.dao.IAnnalDAO;
import com.fozoto.duobao.model.Annal;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by qingyan on 16-7-28.
 */
@Repository
@Transactional
@Scope("prototype")
public class AnnalDAO extends BaseDAO<Annal> implements IAnnalDAO {
    /**
     * 根据商品和期的id查询最后五十条夺宝记录
     *
     * @param goodsId 商品id
     * @param issueId 期的id
     * @return 最后50条夺宝记录
     */
    @Override
    public List<Annal> getLast50Annals(int goodsId, int issueId) {
        String sql = "from Annal where goods_id="+goodsId+" and issue_id="+issueId+" desc limit 50";
        Query query=em.createQuery(sql);
        return query.getResultList();
    }

    /**
     * 通过某期某号码查询夺宝记录
     *
     * @param num     夺宝号码
     * @param issueId 某期
     * @return Annal
     */
    @Override
    public Annal getByNumAndIssue(long num, int issueId) {
        String sql = "from Annal where num="+num+" and issue_id="+issueId;
        Query query=em.createQuery(sql);
        return (Annal)query.getResultList().get(0);
    }
}
