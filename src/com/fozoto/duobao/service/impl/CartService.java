package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.model.Cart;
import com.fozoto.duobao.service.ICartService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-8-12.
 */
@Service("CartService")
@Transactional
@Scope("prototype")
public class CartService extends BaseService<Cart> implements ICartService{
}
