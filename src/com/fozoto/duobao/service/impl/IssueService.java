package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.dao.IIssueDAO;
import com.fozoto.duobao.model.Issue;
import com.fozoto.duobao.service.IIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Service("IssueService")
@Transactional
@Scope("prototype")
public class IssueService extends BaseService<Issue> implements IIssueService {

    @Autowired
    private IIssueDAO iIssueDAO;
}
