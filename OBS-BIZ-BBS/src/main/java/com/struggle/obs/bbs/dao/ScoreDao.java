/**
 * 
 */
package com.struggle.obs.bbs.dao;

import com.struggle.obs.bean.bbs.Score;
import com.struggle.obs.syscom.dao.GenericDao;

/**
 * @author tangyh
 *  2014年8月29日 下午11:04:37
 */
public interface ScoreDao extends GenericDao<Score, Long>{

	/**
	 * 根据 用户id和帖子id 查找评分<br>
	 * 2014年8月30日 下午3:11:53
	 * @param userId
	 * @param topicId
	 * @return Score
	 */
	Score findByUserIdAndTopicId(Long userId, Long topicId);

	/**
	 * 根据 用户id和回复id 查找评分<br>
	 * 2014年8月30日 下午3:12:01
	 * @param userId
	 * @param replyId
	 * @return Score
	 */
	Score findByUserIdAndReplyId(Long userId, Long replyId);

}
