package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.dao.ILotteryDAO;
import com.fozoto.duobao.model.Lottery;
import com.fozoto.duobao.service.ILotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Service("LotteryService")
@Transactional
@Scope("prototype")
public class LotteryService extends BaseService<Lottery> implements ILotteryService {

    @Autowired
    private ILotteryDAO lotteryDAO;
}
