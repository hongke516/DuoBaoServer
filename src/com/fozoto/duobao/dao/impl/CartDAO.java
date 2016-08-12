package com.fozoto.duobao.dao.impl;

import com.fozoto.duobao.dao.ICartDAO;
import com.fozoto.duobao.model.Cart;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-8-12.
 */
@Repository
@Transactional
@Scope("prototype")
public class CartDAO extends BaseDAO<Cart> implements ICartDAO {
}
