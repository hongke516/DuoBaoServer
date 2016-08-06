package com.fozoto.duobao.service;

import com.fozoto.duobao.model.Issue;

/**
 * Created by qingyan on 16-7-28.
 */
public interface IIssueService extends IHelpService<Issue> {

    /**
     * 通过商品id查询正在进行的夺宝的期
     * 注意只查询over=true的结果,所以才只有一个值
     * @param goodsId 商品id
     * @return Issue
     */
    public Issue getByGoods(int goodsId);
}
