package com.struggle.obs.bbs.service.impl;

import java.util.ArrayList;
import java.util.Collection;
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
import com.struggle.obs.bbs.dao.TopicDao;
import com.struggle.obs.bbs.dao.UserDao;
import com.struggle.obs.bbs.service.ForumService;
import com.struggle.obs.bean.bbs.Forum;
import com.struggle.obs.bean.bbs.Topic;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.ForumFrom;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;
import com.struggle.obs.syscom.util.UserInThreadLocal;

/**
 * @author tangyh 2014年8月19日 下午11:38:19
 */
@Service
public class ForumServiceImpl implements ForumService {

	private static Log LOGGER = LogFactory.getLog(ForumServiceImpl.class);

	@Autowired
	private ForumDao forumDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TopicDao topicDao;

	@Override
	@Transactional(readOnly = true)
	public List<Forum> findTopList() {
		try {
			return forumDao.findTopList(ConstantDefine.NO_DEL_FLAG);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询顶层版块失败", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Forum> findAllByNoDel() {
		try {
			return forumDao.findAllByNoDel(ConstantDefine.NO_DEL_FLAG);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询所有未删除版块失败", e);
		}
	}

	@Override
	@Transactional
	public void save(Forum forum, User moderator) {
		if (forum == null) {
			throw new OBSException("不能保存空板块");
		}
		try {
			/*
			 * 父类板块 如果父板块id为空，即保存为顶级板块; 否则给该板块设置父类板块
			 */
			if (forum.getParent() != null) {
				// 不是顶级板块
				Forum parent = forumDao.findByPK(forum.getParent().getId());
				forum.setParent(parent);// 父版块
			}

			forum.setModerator(moderator);// 版主，默认创建人就是版主,从session中获取
			forum.setAddUser(UserInThreadLocal.getLoginName());//

			forumDao.save(forum);
			// 设置位置为id -->充分利用Hibernate特性
			forum.setPosition(forum.getId().intValue());
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("保存失败", e);
		}
	}

	@Override
	@Transactional
	public void update(Forum forum) {
		if (forum == null || forum.getId() == null) {
			throw new OBSException("修改失败");
		}
		try {
			Forum updateForum = forumDao.findByPK(forum.getId());
			updateForum.setUpdateDate(new Date());
			updateForum.setUpdateUser(UserInThreadLocal.getLoginName());
			updateForum.setName(forum.getName());
			updateForum.setDescription(forum.getDescription());
			// 版主
			if (forum.getModerator() != null
					&& forum.getModerator().getId() != null) {
				User moderator = userDao.findByPK(forum.getModerator().getId());
				updateForum.setModerator(moderator);
			}

			// 顶级板块没有父板块可以修改, 父板块没有改变也不需要修改
			if (forum.getParent() != null && //
					updateForum.getParent() != null && //
					forum.getParent().getId() //
					!= updateForum.getParent().getId()) {
				Forum parent = forumDao.findByPK(forum.getParent().getId());
				updateForum.setParent(parent);
			}

		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("修改失败", e);
		}
	}

	private void deleteChilren(Forum deleteForum) {
		// 执行删除操作
		deleteForum.setDeleteFlag(ConstantDefine.DELETE_FLAG);
		// 删除子类板块
		// List<Forum> children = forumDao.findByParentId(
		// deleteForum.getId(), ConstantDefine.NO_DEL_FLAG);
		Set<Forum> children = deleteForum.getChildren();
		if (children != null) {
			for (Forum child : children) {
				deleteChilren(child);
			}
		}
	}

	@Override
	@Transactional
	public void delete(Forum forum, boolean isDeleteFlag) {
		if (forum == null || forum.getId() == null) {
			throw new OBSException("删除失败");
		}
		try {
			Forum deleteForum = forumDao.findByPK(forum.getId());
			deleteForum.setUpdateUser(UserInThreadLocal.getLoginName());
			if (isDeleteFlag) {
				deleteChilren(deleteForum);
			} else {

				// 如果父类被删除，提示先恢复父类
				if (deleteForum.getParent() != null
						&& ConstantDefine.DELETE_FLAG.equals(deleteForum
								.getParent().getDeleteFlag())) {
					throw new OBSException("请先恢复父类版块 ["
							+ deleteForum.getParent().getName() + "]！");
				} else {
					// 顶级版块或父类没有被删除的，直接恢复
					deleteForum.setDeleteFlag(ConstantDefine.NO_DEL_FLAG);

				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("删除失败", e);
		}
	}

	@Override
	@Transactional
	public void move(Forum forum, boolean isUp, Long parentId) {
		if (forum == null || forum.getId() == null) {
			throw new OBSException("移动板块失败");
		}
		try {
			Forum moveForm = forumDao.findByPK(forum.getId()); // 当前需要移动的Forum

			Forum other = forumDao.findByPosition(moveForm.getPosition(), isUp,
					parentId);// 获取另一个板块
			// 最下面的不能下移，最上面的不能上移
			if (other == null) {
				return;
			}
			// 交换position的值
			int temp = moveForm.getPosition();
			moveForm.setPosition(other.getPosition());
			other.setPosition(temp);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("移动板块失败", e);
		}
	}

	@Override
	public List<Forum> findForum(Long parentId) {
		try {
			// 如果父id为空，就查询顶层版块
			if (parentId == null) {
				return forumDao.findTopList(ConstantDefine.NO_DEL_FLAG);
			} else {
				// 查询未删除板块
				return forumDao.findForum(parentId, ConstantDefine.NO_DEL_FLAG);
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询版块失败", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Forum> findAllDel(Long parentId) {
		try {
			// 如果父id为空，就查询所有版块
			if (parentId == null) {
				return forumDao.findAllByNoDel(ConstantDefine.DELETE_FLAG);
			} else {
				// 查询指定已删除板块
				return forumDao.findForum(parentId, ConstantDefine.DELETE_FLAG);
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询版块失败", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Forum findById(Long id) {
		if (id == null) {
			throw new OBSException("查询版块失败");
		}
		try {
			return forumDao.findByPK(id);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询版块失败", e);
		}
	}

	/**
	 * 2014年8月22日 上午12:33:08
	 * 
	 * @see com.struggle.obs.bbs.service.ForumService#findAll()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Forum> findAll() {
		try {
			List<Forum> forums = forumDao
					.findTopList(ConstantDefine.NO_DEL_FLAG);
			List<Forum> list = new ArrayList<Forum>();
			walkForumTreeList(forums, "┣", list);
			return list;
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询版块失败", e);
		}

	}

	/**
	 * 遍历板块树，把遍历出的版块信息放到指定的集合中 2014年8月22日 上午12:43:50
	 * 
	 * @param topList
	 *            需要遍历的集合
	 * @param prefix
	 *            填充的符号
	 * @param list
	 *            需要存放的集合
	 */
	private void walkForumTreeList(Collection<Forum> topList, String prefix,
			List<Forum> list) {
		for (Forum top : topList) {
			// 顶点
			Forum copy = new Forum(); // 使用副本，因为原对象在Session中
			copy.setId(top.getId());
			copy.setParent(top.getParent());
			copy.setName(prefix + top.getName());
			list.add(copy); // 把副本添加到同一个集合中

			// 子树
			walkForumTreeList(top.getChildren(), "　" + prefix, list); // 使用全角的空格
		}
	}

	/**
	 * 2014年8月22日 下午4:05:36
	 * 
	 * @see com.struggle.obs.bbs.service.ForumService#getForumFrom()
	 */
	@Override
	public ForumFrom getForumFrom() {
		try {
			//今日帖子数:查询Forum表:所有parentId不为null的sum(todayTopicCount)
			//SELECT SUM(f.topicCount) FROM Forum f WHERE f.parent IS NOT NULL 
			Integer todayTopicCount  = forumDao.getCount(ConstantDefine.TODAY_TOPIC_COUNT);; 
			//昨日帖子数:查询Topic表:所有addDate=new Date()-1天的 Count
			Integer yesterdayTopicCount  = topicDao.getYesterdayTopicCount();
			//总帖子数：查询Forum表:所有parentId不为null的sum(topicCount)
			//SELECT SUM(f.totalTopicCount) FROM Forum f WHERE f.parent IS NOT NULL 
			Integer totalTopicCount = forumDao.getCount(ConstantDefine.TOTAL_TOPIC_COUNT);
			//会员数量：查询User表：所有user数量
			Integer totalUserCount = userDao.getUserCount();
			//新会员:User表:最新注册的用户
			User newUser = userDao.getNewUser();
			ForumFrom forumFrom = new ForumFrom(todayTopicCount ,
					yesterdayTopicCount , totalTopicCount , totalUserCount, newUser);
			return forumFrom;
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			return null;
		}

	}

	/** 
	 * 2014年8月23日 上午11:30:20
	 * @see com.struggle.obs.bbs.service.ForumService#resetForumData()
	 */
	@Override
	@Transactional
	public void resetForumData() {
		try{
			List<Forum> forums = forumDao.findTopList(ConstantDefine.NO_DEL_FLAG);
			if(forums != null){
				for (Forum forum : forums){
					if(forum != null){
						Set<Forum> children = forum.getChildren();
						if(children != null){
							//先查询出子版块的数据
							for(Forum child: children){
								if(child != null){
									//查询Topic中指定forumId的总数
									int childTopicCount = topicDao.getTopicCount(child.getId());//帖子数量
									int childReplyCount = topicDao.getReplyCount(child.getId());//回复数量
									//查询Topic中指定forumId的帖子数量+回复数量
									int childArticleCount = childTopicCount + childReplyCount;//文章数量
									//查询Topic中指定forumId的addDate=newDate的数量
									int childTodayTopciCount=topicDao.getTodayTopicCount(child.getId());//今日帖子数量
									//最后回复
									Topic childLastTopic = topicDao.getLastTopic(child.getId());
									child.setArticleCount(childArticleCount);
									child.setTopicCount(childTopicCount);
									child.setTodayTopciCount(childTodayTopciCount);
									child.setLastTopic(childLastTopic);
								}
							}
							int articleCount = 0;
							int topicCount = 0;
							int todayTopciCount=0;
							Topic lastTopic = forum.getLastTopic();
							//在根据子版块数据计算出父版块的数据
							for (Forum child: children){
								if(child != null){
									articleCount += child.getArticleCount();
									topicCount += child.getTopicCount();
									todayTopciCount += child.getTodayTopciCount();
									if(lastTopic==null){
										lastTopic = child.getLastTopic();
									}
									if(child.getLastTopic() != null){
										//如果子板块的最后回复时间大于自己的最后回复时间。就赋值
										if(child.getLastTopic().getAddDate().after(lastTopic.getAddDate())){
											lastTopic = child.getLastTopic();
										}
										
									}
								}
							}
						forum.setArticleCount(articleCount);
						forum.setLastTopic(lastTopic);
						forum.setTodayTopciCount(todayTopciCount);
						forum.setTopicCount(topicCount);
						}
					}
				}
			}
		}catch(HibernateException e){
			LOGGER.error(e.getMessage());
			throw new OBSException("重置版块数据失败", e);
		}
	}
	public static void main(String[] args) {
		Date d1 = new Date(1111111131111L);
		System.out.println(d1);
		Date d12 =new Date(1111111111111L);
		System.out.println(d12);
		System.out.println(d1.after(d12));
		
	}

}
