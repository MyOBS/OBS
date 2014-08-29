package com.struggle.obs.bbs.dao;

import java.util.List;

import com.struggle.obs.bean.bbs.Reply;
import com.struggle.obs.formbean.ReplyFrom;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.dao.GenericDao;

/**
 * @author tangyh
 *  2014年8月19日 下午11:42:15
 */
public interface ReplyDao extends GenericDao<Reply, Long>{

	/**
	 * 2014年8月20日 上午12:39:32
	 * @param topicId
	 * @param deleteFlag 
	 * @return
	 */
	List<Reply> findByTopicId(Long topicId, String deleteFlag);

	/**
	 * 楼层
	 * 2014年8月20日 下午11:43:36
	 * @param topicId
	 * @return int
	 */
	int getFloor(Long topicId);

	/**
	 * 2014年8月20日 下午11:43:42
	 * @param pageNum
	 * @param pageSize
	 * @param pages
	 * @param replyFrom
	 * @return Page<Reply>
	 */
	Page<Reply> getPageList(Integer pageNum, Integer pageSize, Integer pages,
			ReplyFrom replyFrom);

	/**
	 * 查询出该帖子的上一个最后回复
	 * 2014年8月21日 下午7:34:40
	 * @param topicId  帖子id
	 * @return Reply
	 */
	Reply getLastReply(Long topicId);

	/**
	 * 某个帖子下的回复数
	 * 2014年8月21日 下午7:59:25
	 * @param topicId
	 * @return int
	 */
	int getReplyCount(Long topicId);

}
