package com.fozoto.duobao.service.impl;

import com.fozoto.duobao.dao.IBaseDAO;
import com.fozoto.duobao.util.entity.PageBean;
import com.fozoto.duobao.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by qingyan on 16-1-30.
 */
@Service("BaseService")
@Transactional
@Scope("prototype")
public class BaseService<T extends Serializable> implements IBaseService<T> {
    @Autowired
    private IBaseDAO<T> baseDAO;

    /**
     * 添加对象到数据库
     * @param t 对象
     * @return boolean
     * @exception Exception
     */
    @Override
    public boolean add(T t) throws Exception {
        boolean flag = false;
        if(t != null) {
            baseDAO.save(t);
            flag = true;
        }
        return flag;
    }

    /**
     * 保存实体的集合
     *
     * @param ts 实体得到几个
     * @throws Exception
     */
    @Override
    public boolean addList(List<T> ts) throws Exception {
        if (ts != null) {
            for (T t : ts) {
                ts.add(t);
            }
            return true;
        }
        return false;
    }

    /**
     * 从数据库删除对象
     * @param entityClass 删除对象的class
     * @param entityId 根据对象的id进行删除
     * @return true delete成功;false delete失败
     */
    @Override
    public boolean delete(Class<T> entityClass, Object entityId) {
        boolean flag = false;
        if(entityClass != null) {
            baseDAO.delete(entityClass, entityId);
            flag = true;
        }
        return flag;
    }

    /**
     * 更新T对象
     * @param t           T的对象
     * @return true更新成功
     */
    @Override
    public boolean update(T t) {
        boolean flag = false;
        if(t != null) {
            baseDAO.update(t);
            flag = true;
        }
        return flag;
    }

    /**
     * 根据对象id查询对象
     *
     * @param entityClass 对象class
     * @param entityId    对象id
     * @return T 对象
     */
    @Override
    public T get(Class<T> entityClass, Object entityId) {
        return baseDAO.find(entityClass, entityId);
    }

    /**
     * 根据LinkedHashMap查询,
     * 第一个String是数据表里的字段;
     * 第二个String是需要被验证的值 与 数据库里那张表的字段的值是否匹配
     *
     * @param entityClass 对象class
     * @param term        LinkedHashMap<String,String> term
     * @return List<T> 对象集合
     */
    @Override
    public List<T> get(Class<T> entityClass, LinkedHashMap<String, String> term) {
        return baseDAO.find(entityClass, term);
    }

    /**
     * 获取所有的T
     *
     * @param entityClass T class
     * @return List<T>
     */
    @Override
    public List<T> getAll(Class<T> entityClass) {
        return baseDAO.findAll(entityClass);
    }

    /**
     * 获取总共几个数据
     *
     * @param entityClass 实体
     * @return long
     */
    @Override
    public long getCount(Class<T> entityClass) {
        return baseDAO.getCount(entityClass);
    }

    /**
     * 按条件获取总共几个数据
     *
     * @param entityClass 实体
     * @param contact     条件
     * @return long
     */
    @Override
    public long getCount(Class<T> entityClass, LinkedHashMap<String, String> contact) {
        return baseDAO.getCount(entityClass, contact);
    }

    /**
     * 按条件获取总共几个数据
     *
     * @param entityClass  实体
     * @param contact      像user_pid=1的LinkedHashMap
     * @param withoutEqual 像logCreateTime like '2016-02%'的LinkedHashMap
     * @return long
     */
    @Override
    public long getCount(Class<T> entityClass, LinkedHashMap<String, String> contact, LinkedHashMap<String, String> withoutEqual) {
        return baseDAO.getCount(entityClass, contact, withoutEqual);
    }

    /**
     * 更新对象的单个属性
     *
     * @param entityClass 对象
     * @param updateSql   形如 "logReadingCount"=1
     * @param entityId    对象主键
     */
    @Override
    public void updateAttribue(Class<T> entityClass, String updateSql, Object entityId) {
        baseDAO.updateAttribute(entityClass, updateSql, entityId);
    }

    /**
     * 得到查询的结果
     *
     * @param entityClass 实体类
     * @param currentPage 当前是第几页
     * @param everyPage   每页显示几条记录
     * @param whereSql    长得像   "praise = "+0   或   "user_pid="+1
     * @param orderby     形如 linkedHashMap.put("logCreateTime", "desc");
     * @return PageBean<T>
     */
    @Override
    public PageBean<T> getPaginationService(Class<T> entityClass, int currentPage,
                                            int everyPage, String whereSql, LinkedHashMap<String, String> orderby) {
        //数据校验
        if(currentPage<1) {
            //跳到首页
            currentPage=1;
        }
        //每页的第一条数据
        int firstIndex = (currentPage-1)*everyPage;
        //System.out.println("firstIndex="+firstIndex);
        //取回pageBean
        try {
            PageBean pageBean = baseDAO.getScrollData(entityClass, firstIndex, everyPage, whereSql, orderby);
            //分页总页数
            long allPageNum;
            if (pageBean.getAllRows()%everyPage==0) {
                allPageNum= pageBean.getAllRows()/everyPage;
                pageBean.setAllPage(pageBean.getAllRows()/everyPage);
            } else {
                pageBean.setAllPage(pageBean.getAllRows()/everyPage+1);
                allPageNum = pageBean.getAllRows()/everyPage+1;
            }
            //pageBean.setAllPage(allPageNum);
            //当前页
            if(currentPage>allPageNum) {
                //pageBean = baseDAO.getScrollData(entityClass, firstIndex, everyPage, whereSql, orderby);
                pageBean.setCurrentPage(1);
            } else {
                pageBean.setCurrentPage(currentPage);
            }
            //每页显示几条数据
            pageBean.setPageSize(everyPage);
            return pageBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
