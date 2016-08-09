package com.fozoto.duobao.action;

import com.fozoto.duobao.core.util.TimeUtil;
import com.fozoto.duobao.model.Gamester;
import com.fozoto.duobao.service.IGamesterService;
import com.fozoto.duobao.util.DigestUtil;
import com.fozoto.duobao.util.entity.PromptInfo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by qingyan on 16-8-9.
 */
@Namespace("/gamester")
@Controller("GamesterAction")
@Scope("prototype")
@ParentPackage(value = "json-default")
@Result(name = "error", location = "/android/error", type = "redirect")
public class GamesterAction extends ActionSupport {

    @Resource(name = "GamesterService")
    private IGamesterService gamesterService;

    private Gamester gamester;

    //加密方式
    private String algorithm ="SHA";

    @Autowired
    private PromptInfo promptInfo;

    private Object result;

    @Action(value = "register",
            results = @Result(type = "json", params = {"root", "result", "contentType", "application/json"})
    )
    public String register() {
        if (gamester != null) {
            try {
                // 刚注册的用户权限等级为1
                gamester.setPower(1);
                // 余额
                gamester.setMoney(0);
                // 宝石
                gamester.setStone(0);
                // 注册IP
                gamester.setIp(ServletActionContext.getRequest().getRemoteAddr());
                // 注册时间
                gamester.setTime(TimeUtil.getTime().toString());
                // 密码加密
                gamester.setPassword(toHashedPassword(gamester.getPassword()));
                if (gamesterService.add(gamester)) {
                    result = "{\"result\":\"ok\"}";
                    return SUCCESS;
                } else {
                    promptInfo.setTitle("注册失败");
                    promptInfo.setMessage("对不起,您的输入信息有误!");
                    result = "{\"result\":\"error\"}";
                    return ERROR;
                }
            } catch (Exception e) {
                e.printStackTrace();
                promptInfo.setTitle("注册失败");
                promptInfo.setMessage("对不起,您的输入信息有误!");
                result = "{\"result\":\"error\"}";
                return ERROR;
            }
        } else {
            promptInfo.setTitle("注册失败");
            promptInfo.setMessage("对不起,您的输入信息有误!");
            result = "{\"result\":\"error\"}";
            return ERROR;
        }
    }

    /**
     * 用户登录
     */
    @Action(value = "login",
            results = @Result(type = "json", params = {"root", "result", "contentType", "application/json"})
    )
    public String login() {
        // gamester是用户输入的数据
        if (gamester != null) {
            boolean isRemember = gamester.getRemember();
            System.out.println("用户提交的登录信息"+gamester.toString());
            // 得到数据库里的用户gamester
            gamester = isGamesterCorrect(gamester.getAccount(), gamester.getPassword());
            if (gamester!=null) {
                System.out.println("是否记住密码"+isRemember);
                // 用户选择了记住密码
                if (isRemember) {
                    System.out.println("用户登录时选择记住密码");
                    // 将登录信息存cookie
                    Cookie cookie = this.addCookie(gamester);
                    // 添加cookie到response中,不进行这一步,cookie会添加失败
                    ServletActionContext.getResponse().addCookie(cookie);
                    System.out.println("加入cookie成功");
                }
                System.out.println(gamester.toString());
                // 将用户信息放入session
                if (addToSession(gamester)) {
                    System.out.println("在login的时候加入session");
                    result = "{\"result\":\"ok\"}";
                    return SUCCESS;
                }
            }
        }
        result = "{\"result\":\"error\"}";
        return ERROR;
    }

    /**
     * 用户退出登录
     */
    @Action(value = "logout",
            results = @Result(type = "json", params = {"root", "result", "contentType", "application/json"})
    )
    public String logout() {
        try {
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute(Gamester.LANDING_GAMESTER);
            result = "{\"result\":\"ok\"}";
            System.out.println("用户注销成功!");
            return SUCCESS;
        } catch (Exception e){
            e.printStackTrace();
            result = "{\"result\":\"error\"}";
            return ERROR;
        }
    }

    /**
     * 用户使用cookie登录
     */
    @Action(value = "cookie",
            results = @Result(type = "json", params = {"root", "result", "contentType", "application/json"})
    )
    public String cookie() {
        System.out.println("进入cookie登录了");
        Cookie[] cookies = ServletActionContext.getRequest().getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Gamester.GAMESTER_COOKIE.equals(cookie.getName())) {
                    String value = cookie.getValue();
                    // 判断cookie是否存在
                    if (StringUtils.isNotBlank(value)) {
                        String[] split = value.split(",");
                        String account = split[0];
                        String password = split[1];
                        System.out.println("得到cookie里的账户和密码了: account="+account+", password="+password);
                        // 用户使用cookie登录
                        gamester = this.isGamesterCorrectByCookie(account, password);
                        if(gamester != null) {
                            if (addToSession(gamester)) {
                                System.out.println("在cookie登录的时候加入session");
                                // 成功登录
                                result = "{\"result\":\""+gamester.getNickname()+"\"}";
                                return SUCCESS;
                            }
                        }
                    }
                }
            }
        }
        result = "{\"result\":\"error\"}";
        return ERROR;
    }

    /**
     * 检查账号是否可用
     */
    @Action(value = "check",
            results = @Result(type = "json", params = {"root", "result", "contentType", "application/json"})
    )
    public String check() {
        System.out.println("进入check了");
        if (gamester != null) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("account", gamester.getAccount());
            List<Gamester> gamesters = gamesterService.get(Gamester.class, linkedHashMap);
            if (gamesters.size() > 0) {
                result = "{\"result\":\"error\"}";
            } else {
                result = "{\"result\":\"ok\"}";
            }
            System.out.println("结果为:" + result);
            return SUCCESS;
        }
        return ERROR;
    }

    // 用户名和密码是否正确
    private Gamester isGamesterCorrect(String account, String password) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("account", account);
        // 对密码加密
        linkedHashMap.put("password", toHashedPassword(password));
        List<Gamester> userList = gamesterService.get(Gamester.class, linkedHashMap);
        if(userList.size() == 1) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    // 用户名和密码是否正确
    private Gamester isGamesterCorrectByCookie(String account, String password) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("account", account);
        // cookie里的密码已经是加密后的密码了
        linkedHashMap.put("password", password);
        List<Gamester> userList = gamesterService.get(Gamester.class, linkedHashMap);
        if(userList.size() == 1) {
            return userList.get(0);
        } else {
            return null;
        }
    }


    /**
     * 添加cookie
     */
    private Cookie addCookie(Gamester gamester) {
        // 这个gamester已经是从数据库里拿出来的了.不需再加密
        //cookie里存放的是加密后的密码,而不是用户输入的密码
        Cookie cookie = new Cookie(Gamester.GAMESTER_COOKIE, gamester.getAccount() + "," + gamester.getPassword());
        //System.out.printf("添加Cookie"+cookie.getName());
        //记住密码7年有效
        cookie.setMaxAge(60*60*24*7*365);
        //不设置路径,只能在当前登录页面拿到cookie,在首页拿不到cookie
        cookie.setPath("/");
        return cookie;
    }

    /**
     * 对密码进行加密
     *
     * @param password 用户输入的密码
     * @return String 加密后的密码
     */
    private String toHashedPassword(String password) {
        try {
            return DigestUtil.digestString(password, algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 将用户信息放入session
    private boolean addToSession(Gamester gamester) {
        try {
            HttpSession session = ServletActionContext.getRequest().getSession();

            System.out.println("正在登录的用户放入session:"+gamester.toString());
            //将用户信息放入session
            session.setAttribute(Gamester.LANDING_GAMESTER, gamester);

            //---------------判断用户是否已经登录了,待实现,安全登录功能-------------------------------------
            //boolean flag = SessionListener.isAlreadyEnter(session, gamester);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Gamester getGamester() {
        return gamester;
    }

    public void setGamester(Gamester gamester) {
        this.gamester = gamester;
    }

    public PromptInfo getPromptInfo() {
        return promptInfo;
    }

    public void setPromptInfo(PromptInfo promptInfo) {
        this.promptInfo = promptInfo;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
