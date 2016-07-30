package com.fozoto.duobao.dao.impl;

import com.fozoto.duobao.dao.IIssueDAO;
import com.fozoto.duobao.model.Issue;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qingyan on 16-7-28.
 */
@Repository
@Transactional
@Scope("prototype")
public class IssueDAO extends BaseDAO<Issue> implements IIssueDAO {
}
