package com.fozoto.duobao.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by qingyan on 16-7-31.
 */
@ParentPackage(value = "json-default")
@Results({
        @Result(name = "success", type = "json", params = {"result", "${result}"})
})
public class HelloWorldAction extends ActionSupport {

    private String name;
    private String result;

    @Action("test")
    public String test() throws Exception{
        System.out.println("进入HelloWorldAction了");
        System.out.println("name="+name);
        result = "成功返回";
        return SUCCESS;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
