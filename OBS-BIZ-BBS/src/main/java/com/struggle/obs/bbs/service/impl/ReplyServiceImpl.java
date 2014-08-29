package com.struggle.obs.bbs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.struggle.obs.bbs.dao.ForumDao;
import com.struggle.obs.bbs.dao.ReplyDao;
import com.struggle.obs.bbs.dao.TopicDao;
import com.struggle.obs.bbs.dao.UserDao;
import com.struggle.obs.bbs.service.ReplyService;
import com.struggle.obs.bean.bbs.Forum;
import com.struggle.obs.bean.bbs.Reply;
import com.struggle.obs.bean.bbs.Topic;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.ReplyFrom;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;
import com.struggle.obs.syscom.util.UserInThreadLocal;
import com.struggle.obs.syscom.util.Utils;

/**
 * @author tangyh 2014年8月19日 下午11:38:28
 */
@Service
public class ReplyServiceImpl implements ReplyService {
	private static Log LOGGER = LogFactory.getLog(ReplyServiceImpl.class);
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private ForumDao forumDao;
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void deleteByTopicId(Long topicId) {
		if (topicId == null) {
			throw new OBSException("删除回复失败");
		}
		try {
			// 删除回复时，需要将帖子的回复数量，最后回复贴，做出修改
			List<Reply> replys = replyDao.findByTopicId(topicId,
					ConstantDefine.NO_DEL_FLAG);
			if (replys != null) {
				for (Reply replyDelete : replys) {
					replyDelete.setDeleteFlag(ConstantDefine.DELETE_FLAG);
					replyDelete.setUpdateUser(UserInThreadLocal.getLoginName());
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("删除回复失败", e);
		}

	}

	/**
	 * 2014年8月20日 下午11:23:51
	 * 
	 * @see com.struggle.obs.bbs.service.ReplyService#getPageList(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer,
	 *      com.struggle.obs.formbean.ReplyFrom)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Reply> getPageList(Integer pageNum, Integer pageSize,
			Integer pages, ReplyFrom replyFrom) {
		if (replyFrom == null) {
			replyFrom = new ReplyFrom();
		}
		try {
			return replyDao.getPageList(pageNum, pageSize, pages, replyFrom);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询回复列表失败", e);
		}
	}

	/**
	 * 2014年8月20日 下午11:23:51
	 * 
	 * @see com.struggle.obs.bbs.service.ReplyService#save(com.struggle.obs.bean.bbs.Reply,
	 *      java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional
	public void save(Reply reply, ReplyFrom replyFrom) {
		if (reply == null || replyFrom == null
				|| replyFrom.getTopicId() == null) {
			throw new OBSException("保存回复失败");
		}
		try {
			reply.setAddUser(UserInThreadLocal.getLoginName());
			reply.setIp(replyFrom.getIp());// 回复ip

			// 回复人
			if (replyFrom.getUserId() != null) {
				User author = userDao.findByPK(replyFrom.getUserId());
				reply.setAuthor(author);
			}

			// 父回复
			if (replyFrom.getParentId() != null) {
				Reply parent = replyDao.findByPK(replyFrom.getParentId());
				reply.setParent(parent);
			}

			if (replyFrom.getTopicId() != null) {
				// 帖子id=topicId的所有回复中楼层最大值+1
				int floor = replyDao.getFloor(replyFrom.getTopicId());
				reply.setFloor(floor);

			}

			// 所属帖子
			Topic topic = topicDao.findByPK(replyFrom.getTopicId());
			if (topic != null) {
				reply.setTopic(topic);
				replyDao.save(reply);

				// 修改帖子的最后回复人，回复数量等信息
				topic.setLastReply(reply);
				topic.setReplyCount(Utils.switchInt(topic.getReplyCount()) + 1);
				topic.setLastReplyTime(new Date());

				// 修改版块的 文章数量
				// Forum forum = forumDao.findByPK(topic.getForum().getId());
				if (topic.getForum() != null) {
					topic.getForum()
							.setArticleCount(
									Utils.switchInt(topic.getForum()
											.getArticleCount()) + 1);
					if (topic.getForum().getParent() != null) {
						topic.getForum()
								.getParent()
								.setArticleCount(
										Utils.switchInt(topic.getForum()
												.getParent().getArticleCount()) + 1);
					}
				}
			}

		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("保存回复失败", e);
		}
	}

	/**
	 * 2014年8月20日 下午11:23:51
	 * 
	 * @see com.struggle.obs.bbs.service.ReplyService#findById(java.lang.Long)
	 */
	@Override
	@Transactional
	public Reply findById(Long id) {
		if (id == null) {
			throw new OBSException("查询回复失败");
		}
		try {
			return replyDao.findByPK(id);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询回复失败", e);
		}
	}

	/**
	 * 2014年8月20日 下午11:23:51
	 * 
	 * @see com.struggle.obs.bbs.service.ReplyService#update(com.struggle.obs.bean.bbs.Reply)
	 */
	@Override
	@Transactional
	public void update(Reply reply) {
		if (reply == null || reply.getId() == null) {
			throw new OBSException("修改回复失败");
		}
		try {
			Reply replyUpdate = replyDao.findByPK(reply.getId());
			replyUpdate.setUpdateDate(new Date());
			replyUpdate.setUpdateUser(UserInThreadLocal.getLoginName());
			replyUpdate.setContent(reply.getContent());
			replyUpdate.setTitle(reply.getTitle());
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("修改回复失败", e);
		}
	}

	/**
	 * 2014年8月20日 下午11:23:51
	 * 
	 * @see com.struggle.obs.bbs.service.ReplyService#delete(com.struggle.obs.bean.bbs.Reply)
	 */
	@Override
	@Transactional
	public void delete(Reply reply) {
		if (reply == null || reply.getId() == null) {
			throw new OBSException("删除回复失败");
		}
		try {
			Reply replyDelete = replyDao.findByPK(reply.getId());
			deleteChilren(replyDelete);

		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("删除回复失败", e);
		}
	}

	/**
	 * 2014年8月21日 上午12:18:00
	 * 
	 * @param replyDelete
	 *            void
	 */
	private void deleteChilren(Reply replyDelete) {
		replyDelete.setDeleteFlag(ConstantDefine.DELETE_FLAG);
		replyDelete.setUpdateUser(UserInThreadLocal.getLoginName());
		updateReplyCount(replyDelete);

		// 删除其实子类回复
		Set<Reply> children = replyDelete.getChildren();
		if (children != null) {
			for (Reply reply : children) {
				deleteChilren(reply);
			}
		}
	}

	/**
	 * 修改所属帖子的回复数量和最后回复 2014年8月21日 下午9:09:04
	 * 
	 * @param replyDelete
	 *            void
	 */
	private void updateReplyCount(Reply replyDelete) {
		// 删除回复时，需要将帖子的回复数量，最后回复贴，做出修改
		Topic topic = replyDelete.getTopic();
		if (topic != null) {
			// 回复数减-1
			topic.setReplyCount(Utils.switchInt(topic.getReplyCount()) - 1);
			// 如果该回复(replyDelete)所属帖子的最后回复=自己(replyDelete),就将所属帖子的最后回复修改为之前的回复
			if (topic.getLastReply() != null
					&& topic.getLastReply().getId() == replyDelete.getId()) {
				// 查询出上一个最后回复
				Reply reply = replyDao.getLastReply(topic.getId());
				if (reply != null) {
					topic.setLastReply(reply);// 最后回复
					topic.setLastReplyTime(reply.getAddDate());// 最后回复时间
				}
			}

			// 减少版块的文章数
			Forum forum = topic.getForum();
			if (forum != null) {
				forum.setArticleCount(Utils.switchInt(forum.getArticleCount()) - 1);
			}
			if(forum.getParent() != null){
				forum.getParent().setArticleCount(Utils.switchInt(forum.getParent().getArticleCount()) - 1);
			}
		}
	}

}
