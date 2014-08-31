/**
 * 
 */
package com.struggle.obs.bbs.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.struggle.obs.bbs.dao.ReplyDao;
import com.struggle.obs.bbs.dao.ScoreDao;
import com.struggle.obs.bbs.dao.TopicDao;
import com.struggle.obs.bbs.dao.UserCreditDao;
import com.struggle.obs.bbs.dao.UserDao;
import com.struggle.obs.bbs.service.ScoreService;
import com.struggle.obs.bean.bbs.Reply;
import com.struggle.obs.bean.bbs.Score;
import com.struggle.obs.bean.bbs.Topic;
import com.struggle.obs.bean.credit.UserCredit;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.UserInThreadLocal;
import com.struggle.obs.syscom.util.Utils;

/**
 * @author tangyh 2014年8月29日 下午11:03:58
 */
@Service
public class ScoreServiceImpl implements ScoreService {
	private static Log LOGGER = LogFactory.getLog(ScoreServiceImpl.class);
	@Autowired
	private ScoreDao scoreDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserCreditDao userCreditDao;

	/**
	 * 2014年8月29日 下午11:12:36
	 * 
	 * @see com.struggle.obs.bbs.service.ScoreService#save(com.struggle.obs.bean.bbs.Score,
	 *      java.lang.Boolean, java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional
	public void save(Score score, Boolean sendAuthor, Long topicId, Long replyId) {
		if(score == null || score.getUser() == null){
			throw new OBSException("保存评分失败");
		}
		try {
			if(Utils.IsNaturalNum(replyId)){
				//给回复评分
				//1，首先判断该用户是否已经对该回复已经评过分了
				Score replyScore = scoreDao.findByUserIdAndReplyId(score.getUser().getId(), replyId);
				if(replyScore != null){
					throw new OBSException("抱歉，您不能对同一个回复重复评分");
				}
				
				//2，给该回复增加金钱
				Reply reply = replyDao.findByPK(replyId);
				if(reply == null){
					throw new OBSException("回复不存在");
				}
				reply.setMoney(Utils.switchInt(reply.getMoney()) + score.getMoney());
				
				//3，给回复的作者加钱
				if(reply.getAuthor() != null){
					User author = reply.getAuthor();
					setUserCredit(score, author);
				}
				//4,将回复与积分关联
				score.setReply(reply);
			}else {
				//给帖子评分
				//1，首先判断该用户是否已经对该帖子已经评过分了
				Score topicScore = scoreDao.findByUserIdAndTopicId(score.getUser().getId(), topicId);
				if(topicScore != null){
					throw new OBSException("抱歉，您不能对同一个回复帖子评分");
				}
				
				//2，给该帖子增加金钱
				Topic topic = topicDao.findByPK(topicId);
				if(topic == null){
					throw new OBSException("帖子不存在");
				}
				topic.setMoney(Utils.switchInt(topic.getMoney()) + score.getMoney());
				
				//3,给作者加金钱
				if(topic.getAuthor() != null){
					User author = topic.getAuthor();
					setUserCredit(score, author);
				}
				//4,将帖子与积分关联
				score.setTopic(topic);
			}
			User user = userDao.findByPK(score.getUser().getId());
			score.setAddUser(user != null ? user.getLoginName() : "");
			score.setUser(user);
			scoreDao.save(score);
			
			//4,判断是否通知作者
			if(sendAuthor != null && sendAuthor){
				//TODO 通知作者需要设计好消息表之后才能做
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("保存评分失败", e);
		}
	}

	/**
	 * 增加作者(楼主或者层主)的金钱
	 * 2014年8月30日 下午2:50:27
	 * @param score
	 * @param author void
	 */
	private void setUserCredit(Score score, User author) {
		UserCredit userCredit = author.getUserCredit();
		if(userCredit != null){
			userCredit.setMoney(score.getMoney() + userCredit.getMoney());
		}else {
			userCredit = new UserCredit();
			userCredit.setMoney(score.getMoney());
			userCredit.setAddUser(UserInThreadLocal.getLoginName());
			userCreditDao.save(userCredit);
			author.setUserCredit(userCredit);
		}
	}
	
}
