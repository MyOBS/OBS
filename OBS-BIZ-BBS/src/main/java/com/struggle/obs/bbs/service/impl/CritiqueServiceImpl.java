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

import com.struggle.obs.bbs.dao.CritiqueDao;
import com.struggle.obs.bbs.dao.ReplyDao;
import com.struggle.obs.bbs.dao.ReplyOpinionDao;
import com.struggle.obs.bbs.dao.TopicDao;
import com.struggle.obs.bbs.dao.UserDao;
import com.struggle.obs.bbs.service.CritiqueService;
import com.struggle.obs.bean.bbs.Critique;
import com.struggle.obs.bean.bbs.Reply;
import com.struggle.obs.bean.bbs.ReplyOpinion;
import com.struggle.obs.bean.bbs.Topic;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;
import com.struggle.obs.syscom.util.Utils;

/**
 * @author tangyh 2014年8月29日 下午11:01:19
 */
@Service
public class CritiqueServiceImpl implements CritiqueService {
	private static Log LOGGER = LogFactory.getLog(CritiqueServiceImpl.class);
	@Autowired
	private CritiqueDao critiqueDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private ReplyOpinionDao replyOpinionDao;

	/**
	 * 2014年8月31日 下午6:10:59
	 * 
	 * @see com.struggle.obs.bbs.service.CritiqueService#save(com.struggle.obs.bean.bbs.Critique,
	 *      java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional
	public void save(Critique critique, Long topicId, Long replyId) {
		if (critique == null || critique.getUser() == null) {
			throw new OBSException("保存点评失败");
		}
		try {

			if (Utils.IsNaturalNum(replyId)) {
				// 1,保存回复的点评
				Reply reply = replyDao.findByPK(replyId);
				critique.setReply(reply);
			} else {
				// 2,保存帖子的点评
				Topic topic = topicDao.findByPK(topicId);
				critique.setTopic(topic);
			}
			// 设置user
			User user = userDao.findByPK(critique.getUser().getId());
			critique.setAddUser(user != null ? user.getLoginName() : "");
			critique.setUser(user);

			critiqueDao.save(critique);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("保存点评失败", e);
		}

	}

	/**
	 * 2014年8月31日 下午10:33:24
	 * 
	 * @see com.struggle.obs.bbs.service.CritiqueService#updateOpinion(java.lang.Long,
	 *      java.lang.Long, java.lang.String)
	 */
	@Override
	@Transactional
	public void updateOpinion(Long replyId, Long userId, String opinion) {
		String opinionStr = "";
		if (ConstantDefine.SUPPORT.equals(opinion)) {
			opinionStr = "支持";
		} else if (ConstantDefine.AGAINST.equals(opinion)) {
			opinionStr = "反对";
		}
		if (!Utils.IsNaturalNum(replyId) || !Utils.IsNaturalNum(userId)) {
			throw new OBSException(opinionStr + "失败");
		}
		try {
			// 1，检查是否已经支持或者反对过
			ReplyOpinion replyOpinion = replyOpinionDao.findByUserIdAndReplyId(
					userId, replyId);
			if (replyOpinion != null) {
				throw new OBSException("您已经对该条回复 投过票 了!");
			}
			// 2,
			Reply reply = replyDao.findByPK(replyId);
			if (ConstantDefine.SUPPORT.equals(opinion)) {
				// 支持数+1
				reply.setSupport(Utils.switchInt(reply.getSupport()) + 1);
			} else if (ConstantDefine.AGAINST.equals(opinion)) {
				// 反对数+1
				reply.setAgainst(Utils.switchInt(reply.getAgainst()) + 1);
			}
			// 3,保存回复观点记录
			ReplyOpinion ro = new ReplyOpinion();
			ro.setUser(userDao.findByPK(userId));
			ro.setReply(replyDao.findByPK(replyId));
			replyOpinionDao.save(ro);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException(opinionStr + "失败", e);
		}
	}

}
