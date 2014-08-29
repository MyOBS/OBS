/**
 * 
 */
package com.struggle.obs.bean.bbs;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.struggle.obs.bean.user.User;
import com.struggle.obs.syscom.bean.BaseModel;

/**
 * 回复观点表：一个用户只能对一个回复"支持"/"反对"一次。
 * @author tangyh
 *  2014年8月29日 上午12:02:43
 */
@Entity
public class ReplyOpinion extends BaseModel{
	/** 用户 */
	private User user;
	/** 回复 */
	private Reply reply;
	
	public ReplyOpinion() {
	}

	/**
	 * user 
	 * @return 
	 */
	@ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}

	/**
	 * @param user user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * reply 
	 * @return 
	 */
	@ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name = "replyId")
	public Reply getReply() {
		return reply;
	}

	/**
	 * @param reply reply
	 */
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
}
