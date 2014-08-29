package com.struggle.obs.syscom.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;

import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.bean.QueryHelper;

public interface GenericDao<T, PK extends Serializable> {

	T findByPK(PK pk) throws HibernateException;

	T findByPK(PK pk, boolean lock) throws HibernateException;

	T create(T entity) throws HibernateException;

	void update(T entity) throws HibernateException;

	void delete(T entity) throws HibernateException;

	/**
	 * 根据父类Id，查询所有子类, 该方法只能给父类调用
	 * @param parentPk  父id
	 * @param deleteFlag 删除标记
	 */ 
	List<T> findByParentId(PK parentPk, String deleteFlag) throws HibernateException;

	/** 查询所有 */
	List<T> findAll() throws HibernateException;

	/**
	 * 查询所有为删除的 deleteFlag="N" 未删除 deleteFlag="D" 已删除
	 * 
	 * @param deleteFlag
	 *            删除标记
	 */
	List<T> findAllByNoDel(String deleteFlag) throws HibernateException;
	
	/**
	 * 2014年8月20日 上午12:47:48
	 * @param ids id数组
	 * @param deleteFlag 删除标记
	 * @return
	 * @throws HibernateException
	 */
	List<T> findByIds(Long[] ids, String deleteFlag) throws HibernateException;

	<P> P save(P entity) throws HibernateException;

	<P> P delete(P entity, long pk) throws HibernateException;

	<P> P update(P entity, long pk) throws HibernateException;

	Page<T> getPageBean(int pageNum, int pageSize, int pages,
			QueryHelper<T, PK> queryHelper) throws HibernateException;
}
