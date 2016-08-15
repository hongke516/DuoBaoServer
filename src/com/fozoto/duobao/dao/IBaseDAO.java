package com.fozoto.duobao.dao;

import com.fozoto.duobao.util.entity.PageBean;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by qingyan on 16-1-18.
 */
public interface IBaseDAO<T> {
    /**
     * 保存实体
     * @param t 实体
     * @throws Exception
     */
    void save(T t) throws Exception;

    /**
     * 保存实体的集合
     * @param ts 实体得到几个
     * @throws Exception
     */
    public void saveList(List<T> ts) throws Exception;

    /**
     * 更新实体
     * @param t 实体
     */
    void update(T t);

    /**
     * 只更新数据表的一个修改的字段
     * @param entityClass 数据表名
     * @param entityIdKey 数据表的id键名
     * @param entityIdValue 查询数据表的id的键值
     * @param modifyKey 需要修改的字段名
     * @param modifyValue 需要修改的字段的值
     */
    void updateOneAttribute(Class<T> entityClass, Object entityIdKey, Object entityIdValue, Object modifyKey, Object modifyValue);

    /**
     * 更新对象的单个属性
     * @param entityClass 对象
     * @param updateSql 形如 "logReadingCount"=1
     * @param entityId 对象主键
     */
    void updateAttribute(Class<T> entityClass, String updateSql, Object entityId);
    /**
     * 删除实体
     * @param entityClass 实体类型
     * @param entityId 实体数组
     */
    void delete(Class<T> entityClass, Object entityId);
    /**
     * 删除多个实体
     * @param entityClass 实体类型
     * @param entitys 实体id数组
     */
    void delete(Class<T> entityClass, Object[] entitys);
    /**
     * 获取实体
     * @param entityClass 实体类型
     * @param entityId 实体id
     * @return T
     */
    <T> T find(Class<T> entityClass, Object entityId);

    /**
     * 根据实体的条件来查询数据
     * @param entityClass 实体类
     * @param term LinkedHashMap<String,String> term
     * @return List<T>
     */
    List<T> find(Class<T> entityClass, LinkedHashMap<String, String> term);

    /**
     * 查找所有
     * @param entityClass 需要查找的对象
     * @return List<T>
     */
    List<T> findAll(Class<T> entityClass);

    /**
     * 获取分页数据
     * @param entityClass 实体类
     * @param firstindex 开始索引,从0开始
     * @param maxresult 需要获取的记录数
     * @param whereSql 长得像   "praise = "+0   或   "user_pid="+1
     * @param orderby 查找实现排序 根据key(实体属性)  和value(ASC/DESC) 形如 linkedHashMap.put("logCreateTime", "desc");     linkedHashMap.put("logCreateTime", "asc");
     * @return PageBean<T>
     */
    PageBean<T> getScrollData(Class<T> entityClass, int firstindex, int maxresult, String whereSql, LinkedHashMap<String, String> orderby);

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

}
