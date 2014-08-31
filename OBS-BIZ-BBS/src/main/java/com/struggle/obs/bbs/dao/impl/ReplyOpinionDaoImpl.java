/**
 * 
 */
package com.struggle.obs.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.ReplyOpinionDao;
import com.struggle.obs.bean.bbs.ReplyOpinion;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;

/**
 * @author tangyh 2014年8月31日 下午10:48:05
 */
@Repository
public class ReplyOpinionDaoImpl extends GenericDaoImpl<ReplyOpinion, Long>
		implements ReplyOpinionDao {

	/**
	 * 2014年8月31日 下午10:49:35
	 * 
	 * @see com.struggle.obs.bbs.dao.ReplyOpinionDao#findByUserIdAndReplyId(java.lang.Long,
	 *      java.lang.Long)
	 */
	@Override
	public ReplyOpinion findByUserIdAndReplyId(Long userId, Long replyId) {
		String hql = "FROM ReplyOpinion ro WHERE ro.user.id=? AND ro.reply.id=?";
		return (ReplyOpinion) getSession().createQuery(hql)
				.setParameter(0, userId).setParameter(1, replyId)
				.uniqueResult();
	}

}
