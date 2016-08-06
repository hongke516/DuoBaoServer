package com.fozoto.duobao.service;

import com.fozoto.duobao.model.Annal;

import java.util.List;

/**
 * Created by qingyan on 16-7-28.
 */
public interface IAnnalService extends IBaseService<Annal> {

    /**
     * 根据商品和期的id查询最后五十条夺宝记录
     * @param goodsId 商品id
     * @param issueId 期的id
     * @return 最后50条夺宝记录
     */
    public List<Annal> getLast50Annals (int goodsId, int issueId);

    /**
     * 通过某期某号码查询夺宝记录
     * @param num 夺宝号码
     * @param issueId 某期
     * @return Annal
     */
    public Annal getByNumAndIssue(long num, int issueId);

}
