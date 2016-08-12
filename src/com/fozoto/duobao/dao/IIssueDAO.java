package com.fozoto.duobao.dao;

import com.fozoto.duobao.model.Issue;

import java.util.List;

/**
 * Created by qingyan on 16-7-28.
 */
public interface IIssueDAO extends IHelpDAO<Issue> {

    /**
     * 通过商品id查询正在进行的夺宝的期
     * 注意只查询over=true的结果,所以才只有一个值
     * @param goodsId 商品id
     * @return Issue
     */
    public Issue onDuobao(int goodsId);

    /**
     * 根据商品id获取已经夺宝完成的期
     * @param goodsId 商品id
     * @return List<Issue>
     */
    public List<Issue> outDuobao(int goodsId);
}
