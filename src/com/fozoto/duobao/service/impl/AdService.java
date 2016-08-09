package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.model.Ad;
import com.fozoto.duobao.service.IAdService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-8-8.
 */
@Service("AdService")
@Transactional
@Scope("prototype")
public class AdService extends BaseService<Ad> implements IAdService {
}
