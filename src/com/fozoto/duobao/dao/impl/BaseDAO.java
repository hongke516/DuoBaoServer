package com.fozoto.duobao.dao.impl;

import com.fozoto.duobao.dao.IBaseDAO;
import com.fozoto.duobao.util.entity.PageBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by qingyan on 16-1-18.
 */
@Transactional
@Repository
@Scope("prototype")
public class BaseDAO<T extends Serializable> implements IBaseDAO<T> {

    /**
     * 默认构造函数
     */
    public BaseDAO() {}

    /**
     * 反射得到class
     */
    private Class<T> entityClass;
    public BaseDAO(Class<T> clazz) {
        this.setEntityClass(clazz);
    }

    @PersistenceContext
    protected EntityManager em;

    /**
     * 保存实体
     * @param t 实体
     * @throws Exception
     */
    public void save(T t){
        em.persist(t);
    }

    /**
     * 更新实体
     * @param t 实体
     */
    public void update(T t) {
        em.merge(t);
    }

    /**
     * 只更新数据表的一个修改的字段
     * @param entityClass 数据表名
     * @param entityIdKey 数据表的id键名
     * @param entityIdValue 查询数据表的id的键值
     * @param modifyKey 需要修改的字段名
     * @param modifyValue 需要修改的字段的值
     */
    @Override
    public void updateOneAttribute(Class<T> entityClass, Object entityIdKey, Object entityIdValue, Object modifyKey, Object modifyValue) {
        String entityname= getEntityName(entityClass);
        Query query=em.createQuery("update "+entityname+" as o set o."+modifyKey+"='"+modifyValue+"' where o."+entityIdKey+"='"+entityIdValue+"'");
        query.executeUpdate();
    }

    /**
     * 更新对象的单个属性
     * @param entityClass 对象
     * @param updateSql 形如 "logReadingCount"=1
     * @param entityId 对象主键
     */
    @Override
    public void updateAttribute(Class<T> entityClass, String updateSql, Object entityId) {
        String entityname= getEntityName(entityClass);
        Query query=em.createQuery("update "+entityname+" as o set o."+updateSql+" where o.pid="+entityId);
        query.executeUpdate();
    }

    /**
     * 删除实体
     * @param entityClass 实体类型
     * @param entityId 实体数组
     */
    public void delete(Class<T> entityClass, Object entityId) {
        delete(entityClass,new Object[]{entityId});
    }

    /**
     * 删除多个实体
     * @param entityClass 实体类型
     * @param entitys 实体id数组
     */
    public void delete(Class<T> entityClass, Object[] entitys) {
        for(Object id:entitys){
            em.remove(em.getReference(entityClass, id));
        }
    }

    /**
     * 获取实体
     * @param entityClass 实体类型
     * @param entityId 实体id
     * @return
     */
    @Transactional(readOnly=true,propagation= Propagation.NOT_SUPPORTED)
    public <T> T find(Class<T> entityClass, Object entityId) {
        return em.find(entityClass, entityId);
    }

    /**
     * 根据实体的条件来查询数据
     * @param entityClass
     * @param term
     * @return
     */
    /**
     * 得到一个实例,通过  key=mailbox,value=3123@qq.com就是mailbox=3123@qq.com的条件
     */
    @Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
    public List<T> find(Class<T> entityClass, LinkedHashMap<String,String> term) {
        List<T> list;
        String entityname= getEntityName(entityClass);
        Query query=em.createQuery("select o from "+entityname+" o "+"where "+buildAnd(term));
        list=query.getResultList();
        return list;
    }

    /**
     * 查找所有
     *
     * @param entityClass 需要查找的对象
     * @return List<T>
     */
    @Override
    @Transactional(readOnly=true,propagation= Propagation.NOT_SUPPORTED)
    public List<T> findAll(Class<T> entityClass) {
        String entityname= getEntityName(entityClass);
        Query query=em.createQuery("from "+entityname);
        List<T> list = query.getResultList();
        return list;
    }

    /**
     * 获取分页数据
     * @param entityClass 实体类
     * @param firstindex 开始索引,从0开始
     * @param maxresult 需要获取的记录数
     * @param whereSql 长得像   "praise = "+0   或   "user_pid="+1
     * @param orderby 查找实现排序 根据key(实体属性)  和value(ASC/DESC) 形如 linkedHashMap.put("logCreateTime", "desc");     linkedHashMap.put("logCreateTime", "asc");
     * @return PageBean<T>
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
    public PageBean<T> getScrollData(Class<T> entityClass,
                                     int firstindex, int maxresult, String whereSql, LinkedHashMap<String,String> orderby){
        PageBean<T> pageBean = new PageBean<>();
        String entityname = getEntityName(entityClass);
        //select * from user order by onlinetime desc,id asc;
        Query query=em.createQuery("select o from "+entityname+" o "+(whereSql==null? "":" where "+whereSql)+buildOrderby(orderby));
        query.setFirstResult(firstindex).setMaxResults(maxresult);
        pageBean.setQueryResultList(query.getResultList());
        query=em.createQuery("select count(o) from "+entityname+" o "+(whereSql==null? "":" where "+whereSql));
        pageBean.setAllRows((long) query.getSingleResult());

        return pageBean;
    }

    /**
     * 获取总共几个数据
     *
     * @param entityClass 实体
     * @return long
     */
    @Override
    public long getCount(Class<T> entityClass) {
        String entityname = getEntityName(entityClass);
        Query query=em.createQuery("select count(o) from "+entityname+" o");
        return (long)query.getSingleResult();
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
        String entityname = getEntityName(entityClass);
        Query query=em.createQuery("select count(o) from "+entityname+" o where "+buildAnd(contact));
        return (long)query.getSingleResult();
    }

    /**
     * 按条件获取总共几个数据
     * @param entityClass 实体
     * @param contact 像user_pid=1的LinkedHashMap
     * @param withoutEqual 像logCreateTime like '2016-02%'的LinkedHashMap
     * @return long
     */
    @Override
    public long getCount(Class<T> entityClass, LinkedHashMap<String, String> contact, LinkedHashMap<String, String> withoutEqual) {
        String entityname = getEntityName(entityClass);
        Query query=em.createQuery("select count(o) from "+entityname+" o where "+buildAnd(contact)+" and "+buildAnd2(withoutEqual));
        return (long)query.getSingleResult();
    }

    /**
     * 组装and语句
     * @param term
     * @returnh
     */
    protected  String buildAnd(LinkedHashMap<String,String> term){
        StringBuffer orderbyql=new StringBuffer();
        if(term!=null&&term.size()>0){
            for(String key:term.keySet()){
                orderbyql.append(key).append("=").append("'").append(term.get(key)).append("'").append(" and ");
            }
            orderbyql.setLength(orderbyql.length()-5);
        }
        return orderbyql.toString();
    }

    protected  String buildAnd2(LinkedHashMap<String,String> term){
        StringBuffer orderbyql=new StringBuffer();
        if(term!=null&&term.size()>0){
            for(String key:term.keySet()){
                orderbyql.append(key).append("'").append(term.get(key)).append("'").append(" and ");
            }
            orderbyql.setLength(orderbyql.length()-5);
        }
        return orderbyql.toString();
    }

    /**
     * 组装Order by语句
     * @param orderby
     * @return
     */
    protected String buildOrderby(LinkedHashMap<String,String> orderby){
        StringBuffer orderbyql=new StringBuffer();
        if(orderby!=null&&orderby.size()>0){
            orderbyql.append(" order by ");
            for(String key:orderby.keySet()){
                orderbyql.append(key).append(" ").append(orderby.get(key)).append(",");
            }
            orderbyql.deleteCharAt(orderbyql.length()-1);
        }
        return orderbyql.toString();
    }
    /**
     * 获取类的简单名称
     * @param entityClass
     * @return
     */
    protected String getEntityName(Class<T> entityClass){
        String entityname=entityClass.getSimpleName();
        Entity entity=entityClass.getAnnotation(Entity.class);
        if(entity.name()!=null&&!"".equals(entity.name())){
            entityname=entity.name();
        }
        return entityname;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}