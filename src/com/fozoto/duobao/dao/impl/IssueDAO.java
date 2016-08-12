package com.fozoto.duobao.dao.impl;

import com.fozoto.duobao.dao.IIssueDAO;
import com.fozoto.duobao.model.Issue;
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
public class IssueDAO extends HelpDAO<Issue> implements IIssueDAO {
    /**
     * 通过商品id查询正在进行的夺宝的期
     * 注意只查询over=true的结果,所以才只有一个值
     * @param goodsId 商品id
     * @return Issue
     */
    @Override
    public Issue onDuobao(int goodsId) {
        String sql = "from Issue where over='false' and goods_id="+goodsId;
        Query query=em.createQuery(sql);
        return (Issue)query.getResultList().get(0);
    }

    /**
     * 根据商品id获取已经夺宝完成的期
     * @param goodsId 商品id
     * @return List<Issue>
     */
    @Override
    public List<Issue> outDuobao(int goodsId) {
        String sql = "from Issue where over='true' and goods_id="+goodsId;
        Query query=em.createQuery(sql);
        return query.getResultList();
    }
}
