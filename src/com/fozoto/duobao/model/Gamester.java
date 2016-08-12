package com.fozoto.duobao.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 一元夺宝的用户
 * Created by qingyan on 16-7-27.
 */
@Entity(name = "Gamester")
@Scope("prototype")
public class Gamester implements Serializable{

    public static final String GAMESTER_COOKIE = "Gamester_cookie";
    public static final String LANDING_GAMESTER = "landingGamester";
    public static final int ADMIN_POWER = 5;

    private int id;                 // 主键
    private String ip;              // ip地址
    private String account;         // 账号
    private String password;        // 密码
    private String tip;             // 密码提示
    private String repassword;      // 再次输入密码
    private boolean remember;        // 记住密码
    private String nickname;        // 昵称
    private String phone;           // 手机号
    private String portrait;        // 头像
    private String time;            // 注册时间
    private int stone;              // 宝石
    private int money;              // 余额
    private int power;              // 用户权限
    private String city;            // 用户所在城市(由ip地址查询得到)

    @JSONField(serialize=false)
    private List<Lucky> luckies;    // 用户的幸运商品
    @JSONField(serialize=false)
    private List<Annal> annals;     // 用户的所有夺宝记录
    @JSONField(serialize=false)
    private Cart cart;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }

    public String getPortrait() {
        return portrait;
    }

    public int getStone() {
        return stone;
    }

    public int getMoney() {
        return money;
    }

    // Lucky负责维护与用户的关系
    @OneToMany(targetEntity = Lucky.class, mappedBy = "gamester", cascade = CascadeType.ALL)
    public List<Lucky> getLuckies() {
        return luckies;
    }

    // Annal负责维护关系
    @OneToMany(targetEntity = Annal.class, cascade = CascadeType.ALL, mappedBy = "gamester")
    public List<Annal> getAnnals() {
        return annals;
    }

    public String getTime() {
        return time;
    }

    @Transient
    public String getRepassword() {
        return repassword;
    }

    public String getTip() {
        return tip;
    }

    @Transient
    public boolean getRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAnnals(List<Annal> annals) {
        this.annals = annals;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void setLuckies(List<Lucky> luckies) {
        this.luckies = luckies;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Gamester{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", tip='" + tip + '\'' +
                ", repassword='" + repassword + '\'' +
                ", remember=" + remember +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", portrait='" + portrait + '\'' +
                ", time='" + time + '\'' +
                ", stone=" + stone +
                ", money=" + money +
                ", power=" + power +
                ", city='" + city + '\'' +
                '}';
    }

    // Cart负责维护关系
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Cart.class, mappedBy = "gamester")
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
