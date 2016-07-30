package com.fozoto.duobao.dao.impl;

import com.fozoto.duobao.dao.IShapeDAO;
import com.fozoto.duobao.model.Shape;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Repository
@Transactional
@Scope("prototype")
public class ShapeDAO extends BaseDAO<Shape> implements IShapeDAO {
}
