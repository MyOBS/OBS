/**
 * 
 */
package com.struggle.obs.bbs.service;

import com.struggle.obs.bean.bbs.Score;

/**
 * @author tangyh
 *  2014年8月29日 下午11:03:52
 */
public interface ScoreService {


	/**
	 * 保存评分
	 * 2014年8月29日 下午11:09:46
	 * @param score 评分
	 * @param sendAuthor 是否通知作者
	 * @param topicId 帖子Id
	 * @param replyId 回复Id
	 */
	void save(Score score, Boolean sendAuthor, Long topicId, Long replyId);

}
