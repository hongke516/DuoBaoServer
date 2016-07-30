package com.fozoto.duobao.dao;

import com.fozoto.duobao.model.Lottery;

/**
 * Created by qingyan on 16-7-28.
 */
public interface ILotteryDAO extends IBaseDAO<Lottery> {

    /**
     * 根据时时彩期号查询得到Lottery实体
     *
     * @param clazz Lottery.class
     * @param expect 时时彩第几期
     * @return Lottery实体
     */
    public Lottery get(Class<Lottery> clazz, String expect);
}
