package com.fozoto.duobao.dao.impl;

import com.fozoto.duobao.dao.IPictureDAO;
import com.fozoto.duobao.model.Picture;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-8-14.
 */
@Repository
@Transactional
@Scope("prototype")
public class PictureDAO extends HelpDAO<Picture> implements IPictureDAO {
}
