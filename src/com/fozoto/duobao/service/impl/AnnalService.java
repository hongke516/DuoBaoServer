package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.dao.IAnnalDAO;
import com.fozoto.duobao.model.Annal;
import com.fozoto.duobao.service.IAnnalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Service("AnnalService")
@Transactional
@Scope("prototype")
public class AnnalService extends BaseService<Annal> implements IAnnalService {
    @Autowired
    private IAnnalDAO annalDAO;

}
