package com.fozoto.duobao.action;

import com.fozoto.duobao.model.Gamester;
import com.fozoto.duobao.util.entity.PromptInfo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * Created by qingyan on 16-8-10.
 */
public class BaseAction extends ActionSupport{

    private Gamester landedGamester;


    protected boolean adminPower() {
        try {
            HttpSession session = ServletActionContext.getRequest().getSession();
            landedGamester = (Gamester) session.getAttribute(Gamester.LANDING_GAMESTER);
            if (landedGamester != null) {
                if (landedGamester.getPower() >= Gamester.ADMIN_POWER) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 权限不足
     */
    protected void lowPower(PromptInfo promptInfo) {
        promptInfo.setTitle("权限不足!");
        promptInfo.setMessage("对不起,这个需要管理员权限才能操作,您当前的权限不足!");
        promptInfo.setTogo("${pageContext.request.contextPath}/gamester/login");
    }

    protected boolean checkInt(int i) {
        return i > 0 && i < 2147483647;
    }

    public Gamester getLandedGamester() {
        return landedGamester;
    }

    public void setLandedGamester(Gamester landedGamester) {
        this.landedGamester = landedGamester;
    }
}
