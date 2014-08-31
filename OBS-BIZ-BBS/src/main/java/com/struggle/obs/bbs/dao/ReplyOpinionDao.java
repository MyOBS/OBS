/**
 * 
 */
package com.struggle.obs.bbs.dao;

import com.struggle.obs.bean.bbs.ReplyOpinion;
import com.struggle.obs.syscom.dao.GenericDao;

/**
 * @author tangyh
 *  2014年8月31日 下午10:47:57
 */
public interface ReplyOpinionDao extends GenericDao<ReplyOpinion, Long>{

	/**
	 * 根据用户id和回复id 查找记录<br>
	 * 2014年8月31日 下午10:49:10
	 * @param userId
	 * @param replyId
	 * @return ReplyOpinion
	 */
	ReplyOpinion findByUserIdAndReplyId(Long userId, Long replyId);

}
