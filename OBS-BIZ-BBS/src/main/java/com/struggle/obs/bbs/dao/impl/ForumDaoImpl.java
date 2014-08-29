package com.struggle.obs.bbs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.ForumDao;
import com.struggle.obs.bean.bbs.Forum;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;
import com.struggle.obs.syscom.util.ConstantDefine;

/**
 * @author tangyh
 *  2014年8月19日 下午11:41:30
 */
@Repository
public class ForumDaoImpl extends GenericDaoImpl<Forum, Long> implements
		ForumDao {

	@Override
	public Forum findByPosition(Integer position, boolean isUp, Long parentId) {
		Query query = createQuery(position, isUp, parentId);
		return (Forum) query.setFirstResult(0).setMaxResults(1).uniqueResult();
	}

	/**
	 * 创建Query
	 * 
	 * @param position
	 * @param isUp
	 * @param parentId
	 * @return
	 */
	private Query createQuery(Integer position, boolean isUp, Long parentId) {
		StringBuilder hql = new StringBuilder("FROM Forum f WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (isUp) {
			// 如果是向上移动
			hql.append(" AND f.position<? ");
		} else {
			// 否则是向下移动
			hql.append(" AND f.position>? ");
		}
		params.add(position);
		if (parentId == null) {
			// parentId为空表示顶层板块
			hql.append(" AND f.parent IS NULL ");
		} else {
			hql.append(" AND f.parent.id=? ");
			params.add(parentId);
		}
		if (isUp) {
			// 如果是向上移动
			hql.append(" ORDER BY f.position DESC");
		} else {
			// 否则是向下移动
			hql.append(" ORDER BY f.position ASC");
		}
		Query query = getSession().createQuery(hql.toString());
		this.setQueryParams(query, params);
		return query;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> findTopList(String deleteFlag) {
		String hql = "FROM Forum f WHERE f.parent IS NULL AND f.deleteFlag=? ORDER BY f.position";
		return getSession().createQuery(hql)
				.setParameter(0, deleteFlag).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> findForum(Long parentId, String deleteFlag) {
		String hql = "FROM Forum f WHERE f.parent.id=? AND f.deleteFlag=? ORDER BY f.position";
		return getSession().createQuery(hql).setParameter(0, parentId)
				.setParameter(1, deleteFlag).list();
	}

	/** 
	 * 2014年8月23日 上午9:43:36
	 * @see com.struggle.obs.bbs.dao.ForumDao#getCount(int)
	 */
	@Override
	public Integer getCount(int stat) {
		StringBuilder hql = new StringBuilder();
		if(stat == 1){
			//stat==1表示今日帖子数
			hql.append("SELECT SUM(f.todayTopciCount) ");
		}else if(stat == 2){
			//stat==2表示总帖子数
			hql.append("SELECT SUM(f.topicCount) ");
		}else {
			return 0;
		}
		hql.append(" FROM Forum f WHERE f.parent IS NOT NULL AND f.deleteFlag=? ");
		Long count = (Long) getSession().createQuery(hql.toString()).setParameter(0, ConstantDefine.NO_DEL_FLAG).uniqueResult();
		if(count != null){
			return count.intValue();	
		}
		return 0;
	}

}

