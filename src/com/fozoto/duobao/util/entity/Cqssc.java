package com.fozoto.duobao.util.entity;

import java.util.List;

/**
 * 重庆时时彩
 * http://t.apiplus.cn/newly.do?code=cqssc&format=json
 * Created by qingyan on 16-7-29.
 */
public class Cqssc {
    private int rows;
    private String code;
    private String info;
    private List<CqsscData> data;

    public Cqssc() {
    }

    public Cqssc(int rows, String code, String info, List<CqsscData> data) {
        this.rows = rows;
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<CqsscData> getData() {
        return data;
    }

    public void setData(List<CqsscData> data) {
        this.data = data;
    }

}
