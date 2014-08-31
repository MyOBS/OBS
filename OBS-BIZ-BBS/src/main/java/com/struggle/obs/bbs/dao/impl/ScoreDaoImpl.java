/**
 * 
 */
package com.struggle.obs.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.ScoreDao;
import com.struggle.obs.bean.bbs.Score;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;
import com.struggle.obs.syscom.util.ConstantDefine;

/**
 * @author tangyh 2014年8月29日 下午11:05:01
 */
@Repository
public class ScoreDaoImpl extends GenericDaoImpl<Score, Long> implements
		ScoreDao {

	/**
	 * 2014年8月30日 下午3:12:50
	 * 
	 * @see com.struggle.obs.bbs.dao.ScoreDao#findByUserIdAndTopicId(java.lang.Long,
	 *      java.lang.Long)
	 */
	@Override
	public Score findByUserIdAndTopicId(Long userId, Long topicId) {
		String hql = "FROM Score s WHERE s.topic.id=? AND s.user.id=? AND s.deleteFlag=?";
		return (Score) getSession().createQuery(hql).setParameter(0, topicId)
				.setParameter(1, userId)
				.setParameter(2, ConstantDefine.NO_DEL_FLAG).uniqueResult();
	}

	/**
	 * 2014年8月30日 下午3:12:50
	 * 
	 * @see com.struggle.obs.bbs.dao.ScoreDao#findByUserIdAndReplyId(java.lang.Long,
	 *      java.lang.Long)
	 */
	@Override
	public Score findByUserIdAndReplyId(Long userId, Long replyId) {
		String hql = "FROM Score s WHERE s.reply.id=? AND s.user.id=? AND s.deleteFlag=?";
		return (Score) getSession().createQuery(hql).setParameter(0, replyId)
				.setParameter(1, userId)
				.setParameter(2, ConstantDefine.NO_DEL_FLAG).uniqueResult();
	}

}
