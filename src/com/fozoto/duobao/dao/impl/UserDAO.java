package com.fozoto.duobao.dao.impl;

import com.fozoto.duobao.dao.IUserDAO;
import com.fozoto.duobao.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Repository
@Transactional
@Scope("prototype")
public class UserDAO extends BaseDAO<User> implements IUserDAO{
}
