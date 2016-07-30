package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.dao.INumberDAO;
import com.fozoto.duobao.model.Number;
import com.fozoto.duobao.service.INumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Service("NumberService")
@Transactional
@Scope("prototype")
public class NumberService extends BaseService<Number> implements INumberService {

    @Autowired
    private INumberDAO numberDAO;
}
