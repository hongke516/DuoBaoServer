package com.fozoto.duobao.dao.impl;

import com.fozoto.duobao.dao.IGamesterDAO;
import com.fozoto.duobao.model.Gamester;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Repository
@Transactional
@Scope("prototype")
public class GamesterDAO extends BaseDAO<Gamester> implements IGamesterDAO {
}
