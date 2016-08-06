package com.fozoto.duobao.action.android;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 * Created by qingyan on 16-8-2.
 */
@Namespace("/android")
@ParentPackage(value = "json-default")
public abstract class BaseAction extends ActionSupport{

    private Object err;

    public String create() {
        return null;
    }

    public String info() {
        return null;
    }

    public String list() {
        return null;
    }

    @Action(value = "/error",
            results = @Result(type = "json", params = {"root", "err", "contentType", "application/text"})
    )
    public String error() {
        err = -1;
        return SUCCESS;
    }

    public Object getErr() {
        return err;
    }

    public void setErr(Object err) {
        this.err = err;
    }
}
