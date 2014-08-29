package com.struggle.obs.bbs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.ReplyDao;
import com.struggle.obs.bean.bbs.Reply;
import com.struggle.obs.formbean.ReplyFrom;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.bean.QueryHelper;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;
import com.struggle.obs.syscom.util.ConstantDefine;

/**
 * @author tangyh 2014年8月19日 下午11:41:40
 */
@Repository
public class ReplyDaoImpl extends GenericDaoImpl<Reply, Long> implements
		ReplyDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Reply> findByTopicId(Long topicId, String deleteFlag) {
		String hql = "FROM Reply r WHERE r.topic.id=? AND r.deleteFlag=?";
		return getSession().createQuery(hql).setParameter(0, topicId)
				.setParameter(1, deleteFlag).list();
	}

	/**
	 * 2014年8月20日 下午11:56:40
	 * 
	 * @see com.struggle.obs.bbs.dao.ReplyDao#getFloor(java.lang.Long)
	 */
	@Override
	public int getFloor(Long topicId) {
		String hql = "SELECT max(r.floor + 1) FROM Reply r WHERE r.topic.id=?";
		// 因为floor是Integer类型，所以这里返回了Ineger类型
		Integer max = (Integer) getSession().createQuery(hql)
				.setParameter(0, topicId).uniqueResult();
		// 第一个回复的人是2楼，楼主是1楼
		if (max == null) {
			return 2;
		}
		return max.intValue();
	}

	/**
	 * 2014年8月20日 下午11:56:40
	 * 
	 * @see com.struggle.obs.bbs.dao.ReplyDao#getPageList(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer,
	 *      com.struggle.obs.formbean.ReplyFrom)
	 */
	@Override
	public Page<Reply> getPageList(Integer pageNum, Integer pageSize,
			Integer pages, ReplyFrom replyFrom) {
		QueryHelper<Reply, Long> queryHelper = new QueryHelper<Reply, Long>(
				Reply.class, "r");

		queryHelper.addCondition("r.deleteFlag=?", ConstantDefine.NO_DEL_FLAG)//
				.addCondition("r.topic.id=?", replyFrom.getTopicId());
		// 分页条件0, 属于某个帖子 1， 只看该作者 2，倒叙浏览
		// 只查看某个作者的
		queryHelper.addCondition(replyFrom.getUserId() != null,//
				"r.author.id=?", replyFrom.getUserId());

		// 默认是按照 楼层排序 升序
		queryHelper.addOrderProperty("r.floor", replyFrom.isAsc());

		return queryHelper.preparePageBean(this, pageNum, pageSize, pages);
	}

	/**
	 * 2014年8月21日 下午7:35:36
	 * 
	 * @see com.struggle.obs.bbs.dao.ReplyDao#getLastReply(java.lang.Long)
	 */
	@Override
	public Reply getLastReply(Long topicId) {
		String hql = " FROM Reply r WHERE r.topic.id=? AND r.deleteFlag=? ORDER BY r.addDate DESC";
		return (Reply) getSession().createQuery(hql).setParameter(0, topicId)
				.setParameter(1, ConstantDefine.NO_DEL_FLAG).setFirstResult(0)
				.setMaxResults(1).uniqueResult();
	}

	/**
	 * 2014年8月21日 下午8:00:16
	 * 
	 * @see com.struggle.obs.bbs.dao.ReplyDao#getReplyCount(java.lang.Long)
	 */
	@Override
	public int getReplyCount(Long topicId) {
		String hql = "SELECT COUNT(r.id) FROM Reply r WHERE r.topic.id=? AND r.deleteFlag=?";
		Long count = (Long) getSession().createQuery(hql)
				.setParameter(0, topicId)
				.setParameter(1, ConstantDefine.NO_DEL_FLAG).uniqueResult();
		if (count == null) {
			return 0;
		}
		return count.intValue();
	}

}
