package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.dao.IDetailDAO;
import com.fozoto.duobao.model.Detail;
import com.fozoto.duobao.service.IDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Service("DetailService")
@Transactional
@Scope("prototype")
public class DetailService extends HelpService<Detail> implements IDetailService {

    @Autowired
    private IDetailDAO detailDAO;

}
