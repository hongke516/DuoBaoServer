package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.model.Goods;
import com.fozoto.duobao.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Service("GoodsService")
@Transactional
@Scope("prototype")
public class GoodsService extends BaseService<Goods> implements IGoodsService {

    @Autowired
    private IGoodsService goodsService;
}
