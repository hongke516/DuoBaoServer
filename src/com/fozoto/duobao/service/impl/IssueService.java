package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.dao.IIssueDAO;
import com.fozoto.duobao.model.Issue;
import com.fozoto.duobao.service.IIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qingyan on 16-7-28.
 */
@Service("IssueService")
@Transactional
@Scope("prototype")
public class IssueService extends HelpService<Issue> implements IIssueService {

    @Autowired
    private IIssueDAO iIssueDAO;

    /**
     * 通过商品id查询正在进行的夺宝的期
     * 注意只查询over=true的结果,所以才只有一个值
     *
     * @param goodsId 商品id
     * @return Issue
     */
    @Override
    public Issue onDuobao(int goodsId) {
        return iIssueDAO.onDuobao(goodsId);
    }

    /**
     * 根据商品id获取已经夺宝完成的期
     *
     * @param goodsId 商品id
     * @return List<Issue>
     */
    @Override
    public List<Issue> outDuobao(int goodsId) {
        return iIssueDAO.outDuobao(goodsId);
    }
}


