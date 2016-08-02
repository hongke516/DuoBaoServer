package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.model.Gamester;
import com.fozoto.duobao.service.IUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Service("UserService")
@Transactional
@Scope("prototype")
public class UserService extends BaseService<Gamester> implements IUserService {

//    @Autowired
//    private IUserDAO userDAO;
}
