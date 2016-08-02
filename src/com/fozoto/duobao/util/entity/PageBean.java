package com.fozoto.duobao.util.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qingyan on 16-2-8.
 * 分页查询辅助类
 */
@Component("PageBean")
@Scope("prototype")
public class PageBean<T> implements Serializable {

    /**
     * 默认构造函数
     */
    public PageBean() {}

    /**
     * 当前页
     */
    private int currentPage=1;

    /**
     * 每页显示数量
     */
    private int pageSize=10;

    /**
     * 总记录数
     */
    private long allRows;

    /**
     * 总共几页
     */
    private long allPage;

    /**
     * 当前页的结果集
     */
    private List<T> queryResultList;

    public int getPageSize() {
        return pageSize;
    }

    public long getAllRows() {
        return allRows;
    }

    public long getAllPage() {
        return allPage;
    }

    public List<T> getQueryResultList() {
        return queryResultList;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setAllRows(long allRows) {
        this.allRows = allRows;
    }

    public void setAllPage(long allPage) {
        this.allPage = allPage;
    }

    public void setQueryResultList(List<T> queryResultList) {
        this.queryResultList = queryResultList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", allRows=" + allRows +
                ", allPage=" + allPage +
                '}';
    }
}
