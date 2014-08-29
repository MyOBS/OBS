package com.struggle.obs.bbs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.ThemeDao;
import com.struggle.obs.bean.bbs.Theme;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;

/**
 * @author tangyh 2014年8月19日 下午11:41:44
 */
@Repository
public class ThemeDaoImpl extends GenericDaoImpl<Theme, Long> implements
		ThemeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Theme> findByForumId(Long forumId, String deleteFlag) {
		String hql = "FROM Theme t WHERE t.deleteFlag=? AND t.forum.id=? ";
		return getSession().createQuery(hql).setParameter(0, deleteFlag)
				.setParameter(1, forumId).list();
	}

}
