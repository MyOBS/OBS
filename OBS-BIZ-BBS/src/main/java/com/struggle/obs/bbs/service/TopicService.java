package com.struggle.obs.bbs.service;

import com.struggle.obs.bean.bbs.Topic;
import com.struggle.obs.formbean.TopicFrom;
import com.struggle.obs.syscom.bean.Page;

/**
 * @author tangyh
 *  2014年8月19日 下午11:41:11
 */
public interface TopicService {

	/**
	 * 获取分页数据
	 * 2014年8月19日 下午4:02:08
	 * @param pageNum 当前页
	 * @param pageSize 每页显示条数
	 * @param pages 每页显示页码数量
	 * @param topicFrom 帖子表单对象，用于传递参数
	 * @return Page<Topic>
	 */
	Page<Topic> getPageList(Integer pageNum, Integer pageSize, Integer pages,
			TopicFrom topicFrom);

	/**
	 * 保存帖子，并修改该帖子所在版块的 最后发帖人，发帖数量，文章数量，今日发帖数量等信息
	 * 2014年8月19日 下午11:57:55
	 * @param topic 帖子
	 * @param topicFrom 参数对象<br>
	 * 需要对topicFrom对象的如下参数赋值:<br>
	 * ip,forumId, typeId, themeId, userId
	 * 
	 */
	void save(Topic topic ,TopicFrom topicFrom);
	/**
	 * 修改帖子内容
	 * 2014年8月20日 下午11:06:01
	 * @param topic
	 * @param typeId 分类id
	 */
	void update(Topic topic, Long typeId);
	/**
	 * 删除帖子，并且删除其回复
	 * 2014年8月20日 下午11:06:04
	 * @param topic
	 */
	void delete(Topic topic);
	
	/**
	 * 查看帖子，并且更新帖子的查看数量
	 * 2014年8月20日 下午10:44:35
	 * @param id 帖子id
	 * @param isLook 帖子id
	 * @return
	 */
	Topic findById(Long id, boolean isLook);

	/**
	 * 将帖子移动到指定板块
	 * 2014年8月22日 上午1:10:34
	 * @param topic
	 * @param forumId void
	 */
	void move(Topic topic, Long forumId);

	/**
	 * 获取某版块的最后发的帖子
	 * 2014年8月22日 下午3:04:26
	 * @param forumId
	 * @return Topic
	 */
	Topic getLastTopic(long forumId);
	
	/**
	 * 重新计算所有帖子的 回复数，最后回复，最后回复时间 <br>
	 * 2014年8月23日 上午11:28:45 
	 */
	void resetTopicData();

	/**
	 * 修改帖子的摘要<br>
	 * <ul>
	 * <li>digest:-2 公告</li>
	 * <li>digest:-1 精华帖</li>
	 * <li>digest:0 普通贴</li>
	 * <li>digest:1 置顶1</li>
	 * <li>digest:2 置顶2</li>
	 * <li>digest:3 置顶3</li>
	 * </ul>
	 * 2014年8月23日 下午3:30:34
	 * @param topic void
	 */
	void updateDigest(Topic topic);
}
