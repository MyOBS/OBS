package com.struggle.obs.syscom.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.bean.QueryHelper;
import com.struggle.obs.syscom.dao.GenericDao;

@SuppressWarnings("unchecked")
public class GenericDaoImpl<T, PK extends Serializable> implements
		GenericDao<T, PK> {

	private Class<T> persistentClass;
	@Autowired
	protected SessionFactory sessionFactory;

	public GenericDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T findByPK(PK pk) throws HibernateException {
		return findByPK(pk, false);
	}

	public T findByPK(PK pk, boolean lock) throws HibernateException {
		T entity = null;
		Session session = getSession();
		entity = (T) session.get(getPersistentClass(), pk);
		return entity;
	}

	@Override
	public T create(T entity) throws HibernateException {
		Session session = getSession();
		session.save(entity);
		return entity;
	}

	@Override
	public void update(T entity) throws HibernateException {
		Session session = getSession();
		session.update(entity);
	}

	@Override
	public void delete(T entity) throws HibernateException {
		Session session = getSession();
		session.delete(entity);
	}

	@Override
	public <P> P save(P entity) throws HibernateException {
		Session session = getSession();
		session.save(entity);
		return entity;
	}

	@Override
	public <P> P delete(P entity, long pk) throws HibernateException {
		Session session = getSession();
		P obj = (P) session.get(entity.getClass(), pk);
		if (obj != null) {
			session.delete(obj);
		}
		return obj;
	}

	@Override
	public <P> P update(P entity, long pk) throws HibernateException {
		Session session = getSession();
		session.update(entity);
		P obj = (P) session.get(entity.getClass(), pk);
		return obj;
	}

	@Override
	public List<T> findAll() throws HibernateException {
		return getSession()
				.createQuery(
						"FROM " + persistentClass.getSimpleName()
								+ " t ").list();
	}
	
	@Override
	public List<T> findAllByNoDel(String deleteFlag) throws HibernateException {
		return getSession().createQuery( //
				"FROM " + persistentClass.getSimpleName() //
						+ " t WHERE t.deleteFlag=?") //
				.setParameter(0, deleteFlag).list();
	}

	@Override
	public List<T> findByIds(Long[] ids, String deleteFlag)
			throws HibernateException {
		return getSession()
				.createQuery(//
						"FROM "
								+ persistentClass.getSimpleName()//
								+ " t WHERE t.id IN (:ids) AND t.deleteFlag=:deleteFlag")
				.setParameterList("ids", ids)
				.setParameter("deleteFlag", deleteFlag).list();
	}

	

	@Override
	public List<T> findByParentId(PK parentPk, String deleteFlag)
			throws HibernateException {
		return getSession().createQuery( //
				"FROM " + persistentClass.getSimpleName() //
						+ " t WHERE t.parent.id=? and t.deleteFlag=?") //
				.setParameter(0, parentPk).setParameter(1, deleteFlag).list();
	}

	// 公共的查询分页信息的方法（最终版）
	public Page<T> getPageBean(int pageNum, int pageSize, int pages,
			QueryHelper<T, PK> queryHelper) {
		// 0, 参数列表
		List<Object> parameters = queryHelper.getParameters();

		// 1,查询总记录数量
		Query countQuery = getSession().createQuery(
				queryHelper.getCountQueryHql());
		if (parameters != null) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long totalRecord = (Long) countQuery.uniqueResult(); // 执行查询

		// 2,查询本页的数据列表
		Query listQuery = getSession().createQuery(
				queryHelper.getListQueryHql()); // 创建查询对象
		if (parameters != null) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List<T> list = listQuery.list(); // 执行查询

		// 创建page对象
		return new Page<T>(pageNum, pageSize, pages, totalRecord.intValue(),
				list);

	}

	/**
	 * 向query设置参数
	 * 
	 * @param query
	 *            Hiberanate的Query对象
	 * @param params
	 *            参数列表
	 */
	protected void setQueryParams(Query query, List<Object> params) {
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
	}
}
