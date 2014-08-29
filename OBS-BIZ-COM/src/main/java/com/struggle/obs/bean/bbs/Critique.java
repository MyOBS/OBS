/**
 * 
 */
package com.struggle.obs.bean.bbs;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.struggle.obs.bean.user.User;
import com.struggle.obs.syscom.bean.BaseModelSub;

/**
 * 点评：每个用户对同一帖子可以多次点评<br>
 * id,content,topicId,replyId,userId
 * @author tangyh 2014年8月27日 下午4:09:46
 */
@Entity
public class Critique extends BaseModelSub {
	/** 内容 */
	private String content;
	/** 所属帖子 */
	private Topic topic;
	/** 用户 */
	private User user;
	/** 回复 */
	private Reply reply;
	public Critique() {
	}
	/**
	 * 内容 
	 * @return 
	 */
	@Column(name="content")
	public String getContent() {
		return content;
	}
	/**
	 * @param 内容 content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 所属帖子 
	 * @return 
	 */
	@ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name = "topicId")
	public Topic getTopic() {
		return topic;
	}
	/**
	 * @param 所属帖子 topic
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	/**
	 * 用户 
	 * @return 
	 */
	@ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	/**
	 * @param 用户 user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * 回复 
	 * @return 
	 */
	@ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name = "replyId")
	public Reply getReply() {
		return reply;
	}
	/**
	 * @param 回复 reply
	 */
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
}
