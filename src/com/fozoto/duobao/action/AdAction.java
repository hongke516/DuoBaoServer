package com.fozoto.duobao.action;

import com.fozoto.duobao.model.Ad;
import com.fozoto.duobao.service.IAdService;
import com.fozoto.duobao.util.entity.PageBean;
import com.fozoto.duobao.util.entity.PromptInfo;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by qingyan on 16-8-10.
 */
@Namespace("/ad")
@Controller("AdAction")
@Scope("prototype")
@Results({
        @Result(name = "error", location = "/WEB-INF/content/util/prompt-info.jsp")
})
public class AdAction extends BaseAction {

    @Resource(name = "AdService")
    private IAdService adService;

    @Autowired
    private PromptInfo promptInfo;

    @Autowired
    private PageBean<Ad> adPageBean;

    private Ad ad;

    private int adId;

    @Action(value = "create", results = {
            @Result(name = "success", location = "/ad/list", type = "redirect")
    })
    public String create() {
        if (adminPower()) {
            if (ad != null) {
                try {
                    if (adService.add(ad)) {
                        return SUCCESS;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            promptInfo.setTitle("添加失败");
            promptInfo.setMessage("出现错误,请重新添加");
        } else {
            lowPower(promptInfo);
        }
        return ERROR;
    }

    @Action(value = "list", results = @Result(location = "/WEB-INF/content/ad.jsp"))
    public String list() {
        adPageBean = adService.getPaginationService(Ad.class, 1, 10, null, null);
        if (adPageBean != null) {
            return SUCCESS;
        }
        promptInfo.setTitle("获取失败");
        promptInfo.setMessage("数据库没有图片了");
        return ERROR;
    }

    @Action(value = "delete", results = {
            @Result(name = "success", location = "/ad/list", type = "redirect")
    })
    public String delete() {
        if (adminPower()) {
            if (adId > 0) {
                if (adService.delete(Ad.class, adId)) {
                    return SUCCESS;
                }
            }
            promptInfo.setTitle("删除失败");
            promptInfo.setMessage("所删的图片不存在!");
        } else {
            lowPower(promptInfo);
        }
        return ERROR;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public PromptInfo getPromptInfo() {
        return promptInfo;
    }

    public void setPromptInfo(PromptInfo promptInfo) {
        this.promptInfo = promptInfo;
    }

    public PageBean<Ad> getAdPageBean() {
        return adPageBean;
    }

    public void setAdPageBean(PageBean<Ad> adPageBean) {
        this.adPageBean = adPageBean;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }
}
