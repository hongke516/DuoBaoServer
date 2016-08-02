package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.dao.IHelpDAO;
import com.fozoto.duobao.model.Goods;
import com.fozoto.duobao.service.IHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qingyan on 16-7-31.
 */
@Service("HelpService")
@Transactional
@Scope("prototype")
public class HelpService <T extends Serializable> extends BaseService<T> implements IHelpService<T> {

    @Autowired
    public IHelpDAO<T> helpDAO;

    @Override
    public List<T> getByGoods(Class<T> clazz, Goods goods) {
        return helpDAO.getByGoods(clazz, goods);
    }
}
