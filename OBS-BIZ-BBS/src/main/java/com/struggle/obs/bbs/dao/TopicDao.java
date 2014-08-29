package com.struggle.obs.bbs.dao;

import org.hibernate.HibernateException;

import com.struggle.obs.bean.bbs.Topic;
import com.struggle.obs.formbean.TopicFrom;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.dao.GenericDao;

/**
 * @author tangyh
 *  2014年8月19日 下午11:42:27
 */
public interface TopicDao extends GenericDao<Topic, Long>{

	Page<Topic> getPageList(Integer pageNum, Integer pageSize, Integer pages,
			TopicFrom topicFrom) throws HibernateException;

	/**
	 * 获取某版块的最后发的帖子
	 * 2014年8月21日 下午7:59:57
	 * @param forumId
	 * @return Topic
	 */
	Topic getLastTopic(Long forumId);

	/**
	 * 昨日帖子数量
	 * 2014年8月23日 上午9:42:14
	 * @return Integer
	 */
	Integer getYesterdayTopicCount();

	/**
	 * 指定版块的总回复数量
	 * 2014年8月23日 下午1:47:25
	 * @param forumId
	 * @return int
	 */
	int getReplyCount(Long forumId);

	/**
	 * 指定版块的帖子数量
	 * 2014年8月23日 下午1:47:30
	 * @param forumId
	 * @return int
	 */
	int getTopicCount(Long forumId);

	/**
	 * 指定版块的今日帖子数量
	 * 2014年8月23日 下午1:47:47
	 * @param forumId
	 * @return int
	 */
	int getTodayTopicCount(Long forumId);

	/**
	 * 根据分类id查找帖子数量
	 * 2014年8月24日 上午11:42:17
	 * @param typeId
	 * @param deleteFlag N/D/null("")
	 * <ul>
	 * 	<li>N:未删除</li>
	 * 	<li>D:已删除</li>
	 * 	<li>Null(""):所有</li>
	 * </ul>
	 * @return int
	 */
	int getTopicCountByTypeId(Long typeId,String deleteFlag);


}
