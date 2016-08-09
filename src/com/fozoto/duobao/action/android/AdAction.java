package com.fozoto.duobao.action.android;

import com.alibaba.fastjson.JSON;
import com.fozoto.duobao.model.Ad;
import com.fozoto.duobao.service.IAdService;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by qingyan on 16-8-8.
 */
@Namespace("/android/ad")
@ParentPackage(value = "json-default")
@Controller("AndroidAdAction")
@Scope("prototype")
@Result(name = "error", location = "/android/error", type = "redirect")
public class AdAction extends BaseAction{
    private final Logger log = Logger.getLogger(AdAction.class);

    private Object result;

    @Resource(name = "AdService")
    private IAdService adService;

    @Action(value = "/get",
            results = @Result(name = "success", type = "json", params = {"root", "result", "contentType", "application/json"})
    )
    public String get() {
        System.out.println("进入ad了");
        List<Ad> ads = adService.getAll(Ad.class);
        log.debug("进入了ad");
        if (ads != null) {
            result = JSON.toJSON(ads);
            System.out.println(ads.toString());
            if (result != null) {
                System.out.println("result的结果是:"+result);
                return SUCCESS;
            }
        }
        return ERROR;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
