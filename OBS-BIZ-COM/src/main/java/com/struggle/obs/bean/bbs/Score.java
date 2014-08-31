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
 * 评分：每个用户只能对同一个帖子一次评分<br>
 * id,content,topicId,userId
 * @author tangyh 2014年8月27日 下午4:10:09
 */
@Entity
public class Score extends BaseModelSub {
	/** 评分给予的金钱 */
	private Long money;
	/** 理由 */
	private String reaon;
	/** 所属帖子 */
	private Topic topic;
	/** 所属回复 */
	private Reply reply;
	/** 评分人 */
	private User user;
	public Score() {
	}
	/**
	 * 评分给予的金钱 
	 * @return 
	 */
	@Column(name="money",length=3)
	public Long getMoney() {
		return money;
	}
	/**
	 * @param 评分给予的金钱 money
	 */
	public void setMoney(Long money) {
		this.money = money;
	}
	/**
	 * 理由 
	 * @return 
	 */
	@Column(name="reaon",length=255)
	public String getReaon() {
		return reaon;
	}
	/**
	 * @param 理由 reaon
	 */
	public void setReaon(String reaon) {
		this.reaon = reaon;
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
	 * 所属回复 
	 * @return 
	 */
	@ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name = "replyId")
	public Reply getReply() {
		return reply;
	}
	/**
	 * @param 所属回复 reply
	 */
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	/**
	 * 评分人 
	 * @return 
	 */
	@ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	/**
	 * @param 评分人 user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/** 
	 * 2014年8月30日 上午11:20:31
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Score [money=");
		builder.append(money);
		builder.append(", reaon=");
		builder.append(reaon);
		builder.append("]");
		return builder.toString();
	}
	
}
