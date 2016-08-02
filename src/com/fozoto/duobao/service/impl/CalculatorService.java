package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.dao.ICalculatorDAO;
import com.fozoto.duobao.model.Calculator;
import com.fozoto.duobao.service.ICalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Service("CalculatorService")
@Transactional
@Scope("prototype")
public class CalculatorService extends BaseService<Calculator> implements ICalculatorService {
    @Autowired
    private ICalculatorDAO calculatorDAO;
}
