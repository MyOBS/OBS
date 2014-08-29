package com.struggle.obs.bbs.service;

import com.struggle.obs.bean.bbs.Reply;
import com.struggle.obs.formbean.ReplyFrom;
import com.struggle.obs.syscom.bean.Page;

/**
 * @author tangyh
 *  2014年8月19日 下午11:41:03
 */
public interface ReplyService {

	/**
	 * 将某个帖子的回复全部删除
	 * 2014年8月20日 上午12:31:52
	 * @param topicId 帖子id
	 */
	void deleteByTopicId(Long topicId);

	/**
	 * 回复分页数据
	 * 2014年8月20日 下午10:01:33
	 * @param pageNum 当前页
	 * @param pageSize 每页显示条数
	 * @param pages 每页显示页码数量
	 * @param replyFrom 帖子表单对象，用于传递参数
	 * @return Page<Reply>
	 */
	Page<Reply> getPageList(Integer pageNum, Integer pageSize, Integer pages,
			ReplyFrom replyFrom);

	/**
	 * 保存回复，并且更新帖子的 最后回复人,帖子数量等信息
	 * 2014年8月20日 下午10:37:41
	 * @param reply
	 * @param replyFrom
	 */
	void save(Reply reply, ReplyFrom replyFrom);

	/**
	 * 查找回复
	 * 2014年8月20日 下午10:38:35
	 * @param id
	 * @return
	 */
	Reply findById(Long id);

	/**
	 * 修改回复
	 * 2014年8月20日 下午10:41:23
	 * @param reply
	 */
	void update(Reply reply);

	/**
	 * 删除回复，并且删除其子回复
	 * 2014年8月20日 下午10:42:04
	 * @param reply
	 */
	void delete(Reply reply);

}
