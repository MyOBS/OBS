/**
 * 
 */
package com.struggle.obs.bbs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.ConstsDao;
import com.struggle.obs.bean.base.Consts;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;

/**
 * @author tangyh 2014年8月29日 下午10:30:37
 */
@Repository
public class ConstsDaoImpl extends GenericDaoImpl<Consts, Long> implements
		ConstsDao {

	/**
	 * 2014年8月29日 下午10:50:29
	 * 
	 * @see com.struggle.obs.bbs.dao.ConstsDao#findByRecType(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Consts> findByRecType(String scoreReaon) {
		String hql = "FROM Consts c WHERE c.recType=? ORDER BY c.orderId ASC";
		return getSession().createQuery(hql).setParameter(0, scoreReaon).list();
	}

}
