/**
 * 
 */
package com.struggle.obs.bbs.service;

import com.struggle.obs.bean.bbs.Critique;

/**
 * @author tangyh
 *  2014年8月29日 下午11:01:07
 */
public interface CritiqueService {

	/**
	 * 保存帖子/回复的点评<br>
	 * topicId==null则表示保存回复的点评<br>
	 * replyId==null则表示保存帖子的点评
	 * 2014年8月31日 下午6:09:37
	 * @param critique
	 * @param topicId
	 * @param replyId void
	 */
	void save(Critique critique, Long topicId, Long replyId);

	/**
	 * 修改回复的支持/反对数量
	 * 2014年8月31日 下午10:32:42
	 * @param replyId
	 * @param userId
	 * @param opinion void
	 */
	void updateOpinion(Long replyId, Long userId, String opinion);

}
