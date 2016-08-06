package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.model.Gamester;
import com.fozoto.duobao.service.IGamesterService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Service("GamesterService")
@Transactional
@Scope("prototype")
public class GamesterService extends BaseService<Gamester> implements IGamesterService {

//    @Autowired
//    private IGamesterDAO userDAO;
}
