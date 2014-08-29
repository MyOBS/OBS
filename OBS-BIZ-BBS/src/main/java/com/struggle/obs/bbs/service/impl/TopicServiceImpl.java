package com.struggle.obs.bbs.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.struggle.obs.bbs.dao.ForumDao;
import com.struggle.obs.bbs.dao.ReplyDao;
import com.struggle.obs.bbs.dao.ThemeDao;
import com.struggle.obs.bbs.dao.TopicDao;
import com.struggle.obs.bbs.dao.TypeDao;
import com.struggle.obs.bbs.dao.UserDao;
import com.struggle.obs.bbs.service.ReplyService;
import com.struggle.obs.bbs.service.TopicService;
import com.struggle.obs.bean.bbs.Forum;
import com.struggle.obs.bean.bbs.Reply;
import com.struggle.obs.bean.bbs.Theme;
import com.struggle.obs.bean.bbs.Topic;
import com.struggle.obs.bean.bbs.Type;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.TopicFrom;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;
import com.struggle.obs.syscom.util.UserInThreadLocal;
import com.struggle.obs.syscom.util.Utils;

/**
 * @author tangyh 2014年8月19日 下午11:38:34
 */
@Service
public class TopicServiceImpl implements TopicService {
	private static Log LOGGER = LogFactory.getLog(TopicServiceImpl.class);
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private TypeDao typeDao;
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private ThemeDao themeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ForumDao forumDao;

	@Override
	@Transactional(readOnly = true)
	public Page<Topic> getPageList(Integer pageNum, Integer pageSize,
			Integer pages, TopicFrom topicFrom) {
		if (topicFrom == null) {
			topicFrom = new TopicFrom();
		}
		try {
			return topicDao.getPageList(pageNum, pageSize, pages, topicFrom);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询帖子列表失败", e);
		}
	}

	@Override
	@Transactional
	public void save(Topic topic, TopicFrom topicFrom) {
		if (topic == null || topicFrom == null) {
			throw new OBSException("发帖失败");
		}
		if (topicFrom.getForumId() == null) {
			throw new OBSException("请为该帖子指定板块失败");
		}
		try {
			topic.setAddUser(UserInThreadLocal.getLoginName());
			topic.setLookCount(0);// 查看数量
			topic.setReplyCount(0);// 回复数量
			topic.setIp(topicFrom.getIp());
			topic.setLastReplyTime(new Date());
			if (topicFrom.getTypeId() != null) {
				Type type = typeDao.findByPK(topicFrom.getTypeId());
				// 分类
				topic.setType(type);
			}
			if (topicFrom.getThemeId() != null) {
				Theme theme = themeDao.findByPK(topicFrom.getThemeId());
				// 主题
				topic.setTheme(theme);
			}
			if (topicFrom.getForumId() != null) {
				Forum forum = forumDao.findByPK(topicFrom.getForumId());
				// 所属版块
				topic.setForum(forum);
			}
			if (topicFrom.getUserId() != null) {
				User author = userDao.findByPK(topicFrom.getUserId());
				// 发帖人
				topic.setAuthor(author);
			}
			topicDao.save(topic);

			// 保存一条帖子后，更新板块的最后发帖人 ,发帖数量,文章数量, 今日发帖数量
			if (topic.getForum() != null && topic.getForum().getId() != null) {
				Forum forum = forumDao.findByPK(topic.getForum().getId());
				updateForumIn(topic, forum, 0);
			}
			updateTypeIn(topic);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("发帖失败", e);
		}
	}

	

	@Override
	@Transactional
	public void update(Topic topic, Long typeId) {
		if (topic == null || topic.getId() == null) {
			throw new OBSException("修改帖子失败");
		}
		try {
			Topic topicUpdate = topicDao.findByPK(topic.getId());
			topicUpdate.setUpdateDate(new Date());
			topicUpdate.setUpdateUser(UserInThreadLocal.getLoginName());
			topicUpdate.setContent(topic.getContent());
			topicUpdate.setTitle(topic.getTitle());
			if (typeId != null && !typeId.equals(topicUpdate.getType().getId())) {
				Type type = typeDao.findByPK(typeId);
				if (type != null) {
					// 主题分类
					topicUpdate.setType(type);
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("修改帖子失败", e);
		}
	}

	@Override
	@Transactional
	public void delete(Topic topic) {
		if (topic == null || topic.getId() == null) {
			throw new OBSException("修改帖子失败");
		}
		try {
			Topic topicDelete = topicDao.findByPK(topic.getId());
			if (topicDelete != null) {
				topicDelete.setUpdateDate(new Date());
				topicDelete.setUpdateUser(UserInThreadLocal.getLoginName());
				topicDelete.setDeleteFlag(ConstantDefine.DELETE_FLAG);

				// 删除帖子时，需要将文章数量，最后发帖id，做出相应减少
				Forum forum = topicDelete.getForum();
				updateForumOut(topicDelete, forum);

				updateTypeOut(topicDelete);

				// 同时删除他的回复
				replyService.deleteByTopicId(topicDelete.getId());
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("删除帖子失败", e);
		}
	}
	
	@Override
	@Transactional
	public Topic findById(Long id, boolean isLook) {
		if (id == null) {
			throw new OBSException("查询帖子失败");
		}
		try {
			Topic topic = topicDao.findByPK(id);
			if (isLook) {
				// 查看数量+1
				topic.setLookCount(Utils.switchInt(topic.getLookCount()) + 1);
			}
			return topic;
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询帖子失败", e);
		}
	}

	/**
	 * 2014年8月22日 上午1:51:28
	 * 
	 * @see com.struggle.obs.bbs.service.TopicService#move(com.struggle.obs.bean.bbs.Topic,
	 *      java.lang.Long)
	 */
	@Override
	@Transactional
	public void move(Topic topic, Long forumId) {
		if (topic == null || forumId == null) {
			throw new OBSException("移动帖子失败");
		}
		try {
			topic = topicDao.findByPK(topic.getId());
			if (topic == null) {
				throw new OBSException("移动帖子失败");
			}
			if (topic.getForum() != null && topic.getForum().getId() != null) {
				// 帖子移动后，减少原有版块的 文章数量，回帖数量，今日回帖数量，最后回帖人，最后回帖时间;
				Forum oldForum = forumDao.findByPK(topic.getForum().getId());
				// 原来所属板块和即将移到的板块相同，则不需要移动
				if (forumId != topic.getForum().getId()) {
					updateForumOut(topic, oldForum);
					updateTypeOut(topic);
					
					Forum newForum = forumDao.findByPK(forumId);
					if (newForum.getParent() == null) {
						throw new OBSException("不能移动到顶级板块");
					}

					// 同时增加新版块的信息
					int replyCount = replyDao.getReplyCount(topic.getId());
					updateForumIn(topic, newForum, replyCount);
					updateTypeIn(topic);
					
					// 设置新板块
					topic.setForum(newForum);
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("移动帖子失败", e);
		}
	}

	/**
	 * 获取某版块的最后发的帖子 2014年8月22日 下午3:04:51
	 * 
	 * @see com.struggle.obs.bbs.service.TopicService#getLastTopic(long)
	 */
	@Override
	@Transactional(readOnly = true)
	public Topic getLastTopic(long forumId) {
		try {
			Topic lastTopic = topicDao.getLastTopic(forumId);
			return lastTopic;
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("获取帖子失败", e);
		}
	}

	/**
	 * 重新计算所有帖子的 回复数，最后回复，最后回复时间 <br>
	 * 2014年8月23日 上午11:01:03
	 */
	@Transactional
	public void resetTopicData() {
		try {
			List<Topic> topics = topicDao
					.findAllByNoDel(ConstantDefine.NO_DEL_FLAG);
			if (topics != null) {
				for (Topic topic : topics) {
					if (topic != null) {
						int replyCount = replyDao.getReplyCount(topic.getId());
						Reply lastReply = replyDao.getLastReply(topic.getId());
						topic.setReplyCount(replyCount);
						topic.setLastReply(lastReply);
						if (lastReply != null) {
							topic.setLastReplyTime(lastReply.getAddDate());
						}
						topic.setLookCount(0);
					}
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("重置帖子数据失败", e);
		}

	}

	/**
	 * 2014年8月23日 下午3:34:01
	 * 
	 * @see com.struggle.obs.bbs.service.TopicService#updateDigest(com.struggle.obs.bean.bbs.Topic)
	 */
	@Override
	@Transactional
	public void updateDigest(Topic topic) {
		if (topic == null || topic.getId() == null || topic.getDigest() == null) {
			throw new OBSException("设置失败");
		}
		try {
			Topic updateTopic = topicDao.findByPK(topic.getId());
			updateTopic.setDigest(topic.getDigest());
			updateTopic.setUpdateDate(new Date());
			updateTopic.setUpdateUser(UserInThreadLocal.getLoginName());
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("设置失败", e);
		}
	}
	

	/**
	 * 保存，移动进一条帖子后，分类的发帖数量+1
	 * 2014年8月24日 上午11:31:33
	 * 
	 * @param topic
	 *            void
	 */
	private void updateTypeIn(Topic topic) {
		if (topic.getType() != null && topic.getType().getId() != null) {
			Type moveInType = typeDao.findByPK(topic.getType().getId());
			moveInType.setTopicCount(moveInType.getTopicCount() + 1);
		}
	}

	/**
	 *  删除一条帖子后，分类的发帖数量-1
	 * 2014年8月24日 上午11:33:39
	 * @param topicDelete void
	 */
	private void updateTypeOut(Topic topicDelete) {
		if (topicDelete.getType() != null
				&& topicDelete.getType().getId() != null) {
			Type deleteType = typeDao.findByPK(topicDelete.getType()
					.getId());
			deleteType.setTopicCount(deleteType.getTopicCount() - 1);
		}
	}

	/**
	 * 修改板块以及父版块信息，删除，移动前使用 2014年8月22日 上午2:03:22
	 * 
	 * @param topic
	 * @param forum
	 *            void
	 */
	private void updateForumOut(Topic topic, Forum forum) {
		if (forum != null) {
			// 今日帖子数量-1
			if (Utils.formatSysDate().equals(
					Utils.formatDate(topic.getAddDate()))) {
				forum.setTodayTopciCount(Utils.switchInt(forum
						.getTodayTopciCount()) - 1);
			}
			// 帖子数量-1
			forum.setTopicCount(Utils.switchInt(forum.getTopicCount()) - 1);

			// 文章数量=帖子数量+回复数量 ; 删除一篇帖子后，文章数=原来文章数-被删除帖子数-被删除回复数
			int replyCount = replyDao.getReplyCount(topic.getId());
			forum.setArticleCount(Utils.switchInt(forum.getArticleCount())
					- replyCount - 1);
			// 最后发帖
			Topic lastTopic = topicDao.getLastTopic(forum.getId());
			forum.setLastTopic(lastTopic);
		}
		if (forum.getParent() != null) {
			updateForumOut(topic, forum.getParent());
		}
		updateTypeOut(topic);
	}

	/**
	 * 修改板块以及父版块信息， 保存，移动后使用 2014年8月22日 上午2:08:09
	 * 
	 * @param topic
	 *            帖子
	 * @param forum
	 *            版块
	 * @param replyCount
	 *            帖子的回复数量
	 */
	private void updateForumIn(Topic topic, Forum forum, int replyCount) {
		if (forum != null) {
			if (Utils.formatDate(new Date()).equals(
					Utils.formatDate(topic.getAddDate()))) {
				forum.setTodayTopciCount(Utils.switchInt(forum
						.getTodayTopciCount()) + 1);
			}
			forum.setTopicCount(Utils.switchInt(forum.getTopicCount()) + 1);

			// new文章数 = old文章数+帖子数量+回复数量
			forum.setArticleCount(Utils.switchInt(forum.getArticleCount())
					+ replyCount + 1);
			// 最后回帖 为自身
			forum.setLastTopic(topic);
		}
		if (forum.getParent() != null) {
			updateForumIn(topic, forum.getParent(), replyCount);
		}
	}

}
