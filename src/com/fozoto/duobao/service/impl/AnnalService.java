package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.dao.IAnnalDAO;
import com.fozoto.duobao.model.Annal;
import com.fozoto.duobao.service.IAnnalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qingyan on 16-7-28.
 */
@Service("AnnalService")
@Transactional
@Scope("prototype")
public class AnnalService extends BaseService<Annal> implements IAnnalService {
    @Autowired
    private IAnnalDAO annalDAO;

    /**
     * 根据商品和期的id查询最后五十条夺宝记录
     *
     * @param goodsId 商品id
     * @param issueId 期的id
     * @return 最后50条夺宝记录
     */
    @Override
    public List<Annal> getLast50Annals(int goodsId, int issueId) {
        return annalDAO.getLast50Annals(goodsId, issueId);
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
        return annalDAO.getByNumAndIssue(num, issueId);
    }
}
