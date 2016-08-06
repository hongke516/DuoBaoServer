package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.model.Full;
import com.fozoto.duobao.service.IFullService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-8-4.
 */
@Service("FullService")
@Transactional
@Scope("prototype")
public class FullService extends BaseService<Full> implements IFullService {
}
