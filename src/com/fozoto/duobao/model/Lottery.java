package com.fozoto.duobao.model;

import org.springframework.context.annotation.Scope;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 重庆时时彩
 * 获取地址: http://t.apiplus.cn/newly.do?code=cqssc&format=json
 * Created by qingyan on 16-7-28.
 */
@Entity(name = "Lottery")
@Scope("prototype")
public class Lottery implements Serializable{
    private int id;             // 主键
    private String expect;      // 重庆时时彩期号
    private String code;        // 中奖号码
    private String time;        // 开奖时间
    private String stamp;       // 开奖时间戳
    private String error;       // 重庆时时彩出现错误,无法获取中奖号码(true:错误;false:无错)

    public final static String EXPECT = "expect";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public String getExpect() {
        return expect;
    }

    public String getCode() {
        return code;
    }

    public String getTime() {
        return time;
    }

    public String getStamp() {
        return stamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    @Override
    public String toString() {
        return "Lottery{" +
                "id=" + id +
                ", expect='" + expect + '\'' +
                ", code='" + code + '\'' +
                ", time='" + time + '\'' +
                ", stamp='" + stamp + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
