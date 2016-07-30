package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.dao.ILuckyDAO;
import com.fozoto.duobao.model.Lucky;
import com.fozoto.duobao.service.ILuckyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Service("LuckyService")
@Transactional
@Scope("prototype")
public class LuckyService extends BaseService<Lucky> implements ILuckyService {

    @Autowired
    private ILuckyDAO luckyDAO;
}
