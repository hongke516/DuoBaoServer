package com.fozoto.duobao.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by qingyan on 16-8-12.
 */
@Namespace("/cart")
@Controller("CartAction")
@Scope("prototype")
@Results({
        @Result(name = "error", location = "/WEB-INF/content/util/prompt-info.jsp"),
        @Result(name = "input", location = "/goods-add", type = "redirect")
})
public class CartAction extends ActionSupport {

    /**
     * 用户点击立即购买或加入清单,将触发
     */
//    public String add() {
//        HttpSession session = ServletActionContext.getRequest().getSession();
//
//    }
}
