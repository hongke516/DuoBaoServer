package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.dao.IShapeDAO;
import com.fozoto.duobao.model.Shape;
import com.fozoto.duobao.service.IShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Service("ShapeService")
@Transactional
@Scope("prototype")
public class ShapeService extends HelpService<Shape> implements IShapeService {

    @Autowired
    private IShapeDAO shapeDAO;
}
