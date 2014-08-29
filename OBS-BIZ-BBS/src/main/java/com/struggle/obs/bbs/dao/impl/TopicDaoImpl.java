package com.struggle.obs.bbs.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.TopicDao;
import com.struggle.obs.bean.bbs.Topic;
import com.struggle.obs.formbean.TopicFrom;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.bean.QueryHelper;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;
import com.struggle.obs.syscom.util.ConstantDefine;
import com.struggle.obs.syscom.util.Utils;

/**
 * @author tangyh 2014年8月19日 下午11:41:48
 */
@Repository
public class TopicDaoImpl extends GenericDaoImpl<Topic, Long> implements
		TopicDao {

	@Override
	public Page<Topic> getPageList(Integer pageNum, Integer pageSize,
			Integer pages, TopicFrom topic) throws HibernateException {

		QueryHelper<Topic, Long> queryHelper = new QueryHelper<>(Topic.class,
				"t");

		queryHelper.addCondition("t.deleteFlag=?", ConstantDefine.NO_DEL_FLAG);
		//digest取值：3: 置顶3 2:置顶2 1:置顶1 0: 普通贴 -1:精华帖 -2:公告
		//公告和置顶3在所有版块都可以查询出来， 置顶2，在同一大版块能查询出来，置顶1只能在自己所在版块可以查出来
		// where
		// 查询精华帖或者普通贴时，也要把置顶1一起查出来
		queryHelper.addCondition(topic.getDigest() <= 0, "(t.digest=? OR t.digest=?)",
				topic.getDigest(), ConstantDefine.DIGESET_TOP_1);
		
		// 分类
		queryHelper.addCondition(topic.getTypeId() != null, "t.type.id=?",
				topic.getTypeId());
		// 主题
		queryHelper.addCondition(topic.getThemeId() != null, "t.theme.id=?",
				topic.getThemeId());
		// 开始时间
		queryHelper.addCondition(topic.getStartDate() != null, "t.addDate>=?",
				topic.getStartDate());
		// 结束时间
		queryHelper.addCondition(topic.getEndDate() != null, "t.addDate<=?",
				topic.getEndDate());
		
		// 板块
//		queryHelper.addCondition(topic.getForumId() != null, "t.forum.id=?",
//				topic.getForumId());
//		queryHelper.addCondition(topic.getForumId() != null, "t.forum.id=? OR t.digest=-2  OR t.digest=3",
//				topic.getForumId());
		String whereHql = "t.forum.id=? OR t.digest=-2  OR t.digest=3 OR ( t.digest=2 AND ? IN ( "
				+ " SELECT f3.id FROM Forum  f3 WHERE f3.parent.id = "
				+ " (SELECT f2.parent.id FROM Forum  f2 WHERE f2.id= t.forum.id) " 
				+ ") ) ";
		
		queryHelper.addCondition(topic.getForumId() != null, whereHql,
				topic.getForumId(),topic.getForumId());
		
		// 所有排序均要 公告 置顶3, 置顶2, 置顶1 DESC,
		// 默认时，按最后修改时间 DESC
		queryHelper
				.addOrderProperty(
						"(CASE t.digest WHEN -2 THEN 4 WHEN 3 THEN 3 WHEN 2 THEN 2 WHEN 1 THEN 1 ELSE 0 END)",
						false)//
				.addOrderProperty(Utils.isEmptyOrNull(topic.getOrderBy()),
						"t.lastReplyTime", topic.isAsc());

		// 最新回复,即最后修改时间
		queryHelper.addOrderProperty(
				ConstantDefine.ORDERBY_NEWEST.equals(topic.getOrderBy()),
				"t.lastReplyTime", topic.isAsc());
		// 按人气
		queryHelper.addOrderProperty(
				ConstantDefine.ORDERBY_HOT.equals(topic.getOrderBy()),
				"t.replyCount + t.lookCount", topic.isAsc());
		// 按发帖时间
		queryHelper.addOrderProperty(
				ConstantDefine.ORDERBY_ADDDATE.equals(topic.getOrderBy()),
				"t.addDate", topic.isAsc());
		// 按查看次数
		queryHelper.addOrderProperty(
				ConstantDefine.ORDERBY_LOOK.equals(topic.getOrderBy()),
				"t.lookCount", topic.isAsc());

		return queryHelper.preparePageBean(this, pageNum, pageSize, pages);
	}

	/**
	 * 2014年8月21日 下午8:05:05
	 * 
	 * @see com.struggle.obs.bbs.dao.TopicDao#getLastTopic(java.lang.Long)
	 */
	@Override
	public Topic getLastTopic(Long forumId) {
		String hql = "FROM Topic t WHERE t.forum.id=? AND t.deleteFlag=? ORDER BY t.addDate DESC";
		return (Topic) getSession().createQuery(hql).setParameter(0, forumId)
				.setParameter(1, ConstantDefine.NO_DEL_FLAG).setFirstResult(0)
				.setMaxResults(1).uniqueResult();
	}

	/**
	 * 2014年8月23日 上午9:49:03
	 * 
	 * @see com.struggle.obs.bbs.dao.TopicDao#getYesterdayTopicCount()
	 */
	@Override
	public Integer getYesterdayTopicCount() {
		// DB2
		// String hql =
		// "SELECT COUNT(t.id) FROM Topic t WHERE to_char(t.addDate,'yyyy-MM-dd') = to_char(?,'yyyy-MM-dd')";
		// MySQL
		// String hql =
		// "SELECT COUNT(t.id) FROM Topic t WHERE  DATE_FORMAT(t.addDate,'%Y-%m-%d') = DATE_FORMAT(?,'%Y-%m-%d')";
		// 目测兼容DB2 Orcale MYSQL
		String hql = "SELECT COUNT(t.id) FROM Topic t WHERE  t.addDate >= ? AND t.addDate < ?";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		// 获取昨天的日期
		Date yesterDay = cal.getTime();
		Long count = (Long) getSession().createQuery(hql).setDate(0, yesterDay)
				.setDate(1, new Date()).uniqueResult();
		if (count != null) {
			return count.intValue();
		}
		return 0;
	}

	/**
	 * 2014年8月23日 下午1:48:59
	 * 
	 * @see com.struggle.obs.bbs.dao.TopicDao#getReplyCount(java.lang.Long)
	 */
	@Override
	public int getReplyCount(Long forumId) {
		String hql = "SELECT SUM(r.replyCount) FROM Topic r WHERE r.forum.id=? AND r.deleteFlag=?";
		Long count = (Long) getSession().createQuery(hql)
				.setParameter(0, forumId)
				.setParameter(1, ConstantDefine.NO_DEL_FLAG).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	/**
	 * 2014年8月23日 下午1:48:59
	 * 
	 * @see com.struggle.obs.bbs.dao.TopicDao#getTopicCount(java.lang.Long)
	 */
	@Override
	public int getTopicCount(Long forumId) {
		String hql = "SELECT COUNT(r.id) FROM Topic r WHERE r.forum.id=? AND r.deleteFlag=?";
		Long count = (Long) getSession().createQuery(hql)
				.setParameter(0, forumId)
				.setParameter(1, ConstantDefine.NO_DEL_FLAG).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	/**
	 * 2014年8月23日 下午1:48:59
	 * 
	 * @see com.struggle.obs.bbs.dao.TopicDao#getTodayTopicCount(java.lang.Long)
	 */
	@Override
	public int getTodayTopicCount(Long forumId) {
		String hql = "SELECT COUNT(r.id) FROM Topic r WHERE r.forum.id=? AND r.deleteFlag=? AND r.addDate >= ? AND r.addDate < ?";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		// 获取昨天的日期
		Date tomorrow = cal.getTime();
		Long count = (Long) getSession().createQuery(hql)
				.setParameter(0, forumId)
				.setParameter(1, ConstantDefine.NO_DEL_FLAG)
				.setDate(2, new Date()).setDate(3, tomorrow).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	/** 
	 * 2014年8月24日 上午11:43:31
	 * @see com.struggle.obs.bbs.dao.TopicDao#getTopicCountByTypeId(java.lang.Long)
	 */
	@Override
	public int getTopicCountByTypeId(Long typeId, String deleteFlag) {
		String  hql = "SELECT COUNT(t.id) FROM Topic t WHERE t.type.id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(typeId);
		if(Utils.isEmptyOrNull(deleteFlag)){
		}else {
			hql += " AND t.deleteFlag=? ";
			params.add(deleteFlag);
		}
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		Long count = (Long) query.uniqueResult();
		if(count != null){
			return count.intValue();
		}
		return 0;
	}

}
