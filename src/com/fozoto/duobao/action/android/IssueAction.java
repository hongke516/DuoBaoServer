package com.fozoto.duobao.action.android;

import com.alibaba.fastjson.JSON;
import com.fozoto.duobao.model.Issue;
import com.fozoto.duobao.service.IIssueService;
import com.fozoto.duobao.util.entity.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.LinkedHashMap;

/**
 * Created by qingyan on 16-8-2.
 */
@Namespace("/android/issue")
@Controller("AndroidIssueAction")
@Scope("prototype")
@ParentPackage(value = "json-default")
@Result(name = "error", location = "/android/error", type = "redirect")
public class IssueAction extends BaseAction{
    private static final Logger log = Logger.getLogger(IssueAction.class);

    // 第几期的id
    private int id;

    // 商品id
    private int goodsId;

    // 夺宝第几期
    private Issue issue;

    // 当前第几页
    public int page = 1;

    // 每页多少条记录
    public int size = 10;

    @Autowired
    private PageBean<Issue> issuePage;

    // 输出json的对象
    private Object result;

    @Resource(name = "IssueService")
    private IIssueService issueService;

    @Override
    public String create() {
        return null;
    }

    /**
     * http://localhost:8080/android/issue/info?id=2&goodsId=1
     * 根据id和商品id查询单期信息
     * 参数:id
     * 返回:单期的json信息
     */
    @Action(value = "info",
            results = @Result(type = "json", params = {"root", "result", "contentType", "application/json"})
    )
    @Override
    public String info() {
        log.debug("issue执行了info()方法");
        if (checkInt(goodsId)) {

            issue = issueService.getByGoods(goodsId);
            if (issue != null) {
                log.debug(issue.toString());
                result = JSON.toJSON(issue);
                if (result != null) {
                    log.debug(result);
                    return SUCCESS;
                }
            }
        }
        return ERROR;
    }

    /**
     * http://localhost:8080/android/issue/list?page=1&size=5
     * 根据输入的当前页page和每页显示数size,
     * 获取over为true的已经夺宝完成的期数,正在夺宝的期无法查询
     * 参数:page和size
     * 返回:分页信息
     */
    @Action(value = "list",
            results = @Result(type = "json", params = {"root", "result", "contentType", "application/json"})
    )
    @Override
    public String list() {
        if (checkInt(page) && checkInt(size)) {
            LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
            lhm.put("start", "desc");
            issuePage = issueService.getPaginationService(Issue.class, page, size, "over='true'", lhm);
            if (issuePage != null) {
                log.debug("总共有" + issuePage.getAllRows() + "条记录");
                result = JSON.toJSON(issuePage);
                return SUCCESS;
            }
        }
        return ERROR;
    }

    private boolean checkInt(int i) {
        return i > 0 && i < 2147483647;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public PageBean<Issue> getIssuePage() {
        return issuePage;
    }

    public void setIssuePage(PageBean<Issue> issuePage) {
        this.issuePage = issuePage;
    }
}
