package com.struggle.obs.bbs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.TypeDao;
import com.struggle.obs.bean.bbs.Type;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;

/**
 * @author tangyh 2014年8月19日 下午11:41:58
 */
@Repository
public class TypeDaoImpl extends GenericDaoImpl<Type, Long> implements TypeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> findByForumId(Long forumId, String deleteFlag) {
		String hql = "FROM Type t WHERE t.deleteFlag=? AND t.forum.id=? ";
		return getSession().createQuery(hql).setParameter(0, deleteFlag)
				.setParameter(1, forumId).list();
	}


}
