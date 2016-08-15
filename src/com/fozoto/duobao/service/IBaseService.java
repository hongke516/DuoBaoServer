package com.fozoto.duobao.service;

import com.fozoto.duobao.util.entity.PageBean;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by qingyan on 16-1-30.
 */
public interface IBaseService<T> {

    /**
     * 添加对象到数据库
     * @param t 对象
     * @return true add成功;false add失败
     */
    boolean add(T t) throws Exception;

    /**
     * 保存实体的集合
     * @param ts 实体得到几个
     * @throws Exception
     */
    public boolean addList(List<T> ts) throws Exception;

    /**
     * 从数据库删除对象
     * @param entityClass 删除对象的class
     * @param entityId 根据对象的id进行删除
     * @return true delete成功;false delete失败
     */
    boolean delete(Class<T> entityClass, Object entityId);

    /**
     * 更新T对象
     * @param t T的对象
     * @return true更新成功
     */
    boolean update(T t) throws Exception;

    /**
     * 根据对象id查询对象
     * @param entityClass 对象class
     * @param entityId 对象id
     * @return T 对象
     */
    T get(Class<T> entityClass, Object entityId);

    /**
     * 根据LinkedHashMap查询,
     * 第一个String是数据表里的字段;
     * 第二个String是需要被验证的值 与 数据库里那张表的字段的值是否匹配
     * @param entityClass 对象class
     * @param term LinkedHashMap<String,String> term
     * @return List<T> 对象集合
     */
    List<T> get(Class<T> entityClass, LinkedHashMap<String, String> term);

    /**
     * 获取所有的T
     * @param entityClass T class
     * @return List<T>
     */
    List<T> getAll(Class<T> entityClass);

    /**
     * 获取总共几个数据
     * @param entityClass 实体
     * @return long
     */
    long getCount(Class<T> entityClass);

    /**
     * 按条件获取总共几个数据
     * @param entityClass 实体
     * @param contact 条件
     * @return long
     */
    long getCount(Class<T> entityClass, LinkedHashMap<String, String> contact);

    /**
     * 按条件获取总共几个数据
     * @param entityClass 实体
     * @param contact 像user_pid=1的LinkedHashMap
     * @param withoutEqual 像logCreateTime like '2016-02%'的LinkedHashMap
     * @return long
     */
    long getCount(Class<T> entityClass, LinkedHashMap<String, String> contact, LinkedHashMap<String, String> withoutEqual);

    /**
     * 更新对象的单个属性
     * @param entityClass 对象
     * @param updateSql 形如 "logReadingCount"=1
     * @param entityId 对象主键
     */
    void updateAttribue(Class<T> entityClass, String updateSql, Object entityId);

    /**
     * 得到查询的结果
     * @param entityClass 实体类
     * @param currentPage 当前是第几页
     * @param everyPage  每页显示几条记录
     * @param whereSql  长得像   "praise = "+0   或   "user_pid="+1
     * @param orderby 形如 linkedHashMap.put("logCreateTime", "desc");     linkedHashMap.put("logCreateTime", "asc");
     * @return PageBean<T>
     */
    PageBean<T> getPaginationService(Class<T> entityClass,
                                     int currentPage, int everyPage, String whereSql, LinkedHashMap<String, String> orderby);
}
