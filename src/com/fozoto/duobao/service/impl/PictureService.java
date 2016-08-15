package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.model.Picture;
import com.fozoto.duobao.service.IPictureService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-8-14.
 */
@Service("PictureService")
@Transactional
@Scope("prototype")
public class PictureService extends HelpService<Picture> implements IPictureService {
}
