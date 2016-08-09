package com.fozoto.duobao.dao.impl;

import com.fozoto.duobao.dao.IAdDAO;
import com.fozoto.duobao.model.Ad;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-8-8.
 */
@Repository
@Transactional
@Scope("prototype")
public class AdDAO extends BaseDAO<Ad> implements IAdDAO {
}
