/**
 * 
 */
package com.struggle.obs.bean.user;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.struggle.obs.syscom.bean.BaseModel;

/**
 * 统计信息
 * 
 * @author tangyh 2014年8月29日 上午12:10:25
 */
@Entity
public class StatisticsProfile extends BaseModel {
	/** 所属用户 */
	private User user;
	/** 发帖数量 */
	private int topicCount;
	/** 回复数量 */
	private int replyCount;
	/** 好友数量 */
	private int friendCount;
	/** 本次登录时间 */
	private Date loginTime;
	/** 本次登录Ip */
	private String loginIp;
	/** 本次登录地址 */
	private String loginAddress;
	/** 上次登录时间 */
	private Date lastLoginTime;
	/** 上次登录Ip */
	private String lastLoginIp;
	/** 上次登录地址 */
	private String lastLoginAddress;
	/** 注册地点 */
	private String regAddress;
	/** 注册ip */
	private String regIp;
	/** 上次发表时间 */
	private Date lastPublishTime;
	
	/**
	 * 注册地点 
	 * @return 
	 */
	@Column(name="regAddress", length = 20)
	public String getRegAddress() {
		return regAddress;
	}

	/**
	 * @param 注册地点 regAddress
	 */
	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	/**
	 * 注册ip 
	 * @return 
	 */
	@Column(name="regIp", length = 40)
	public String getRegIp() {
		return regIp;
	}

	/**
	 * @param 注册ip regIp
	 */
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	/**
	 * 上次发表时间 
	 * @return 
	 */
	@Column(name="lastPublishTime")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastPublishTime() {
		return lastPublishTime;
	}

	/**
	 * @param 上次发表时间 lastPublishTime
	 */
	public void setLastPublishTime(Date lastPublishTime) {
		this.lastPublishTime = lastPublishTime;
	}

	/**
	 * 本次登录时间
	 * 
	 * @return
	 */
	@Column(name = "loginTime")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * @param 本次登录时间
	 *            loginTime
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * 本次登录Ip
	 * 
	 * @return
	 */
	@Column(name = "loginIp", length = 40)
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * @param 本次登录Ip
	 *            loginIp
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	/**
	 * 本次登录地址
	 * 
	 * @return
	 */
	@Column(name = "loginAddress", length = 20)
	public String getLoginAddress() {
		return loginAddress;
	}

	/**
	 * @param 本次登录地址
	 *            loginAddress
	 */
	public void setLoginAddress(String loginAddress) {
		this.loginAddress = loginAddress;
	}

	/**
	 * 上次登录时间
	 * 
	 * @return
	 */
	@Column(name = "lastLoginTime")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param 上次登录时间
	 *            lastLoginTime
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 上次登录Ip
	 * 
	 * @return
	 */
	@Column(name = "lastLoginIp", length = 40)
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	/**
	 * @param 上次登录Ip
	 *            lastLoginIp
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * 上次登录地址
	 * 
	 * @return
	 */
	@Column(name = "lastLoginAddress", length = 20)
	public String getLastLoginAddress() {
		return lastLoginAddress;
	}

	/**
	 * @param 上次登录地址
	 *            lastLoginAddress
	 */
	public void setLastLoginAddress(String lastLoginAddress) {
		this.lastLoginAddress = lastLoginAddress;
	}

	/**
	 * 发帖数量
	 * 
	 * @return
	 */
	@Column(name = "topicCount", length = 10)
	public int getTopicCount() {
		return topicCount;
	}

	/**
	 * @param 发帖数量
	 *            topicCount
	 */
	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}

	/**
	 * 回复数量
	 * 
	 * @return
	 */
	@Column(name = "replyCount", length = 12)
	public int getReplyCount() {
		return replyCount;
	}

	/**
	 * @param 回复数量
	 *            replyCount
	 */
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	/**
	 * 好友数量
	 * 
	 * @return
	 */
	@Column(name = "friendCount", length = 4)
	public int getFriendCount() {
		return friendCount;
	}

	/**
	 * @param 好友数量
	 *            friendCount
	 */
	public void setFriendCount(int friendCount) {
		this.friendCount = friendCount;
	}

	/**
	 * 所属用户
	 * 
	 * @return
	 */
	@OneToOne(mappedBy = "statisticsProfile", cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	public User getUser() {
		return user;
	}

	/**
	 * @param 所属用户
	 *            user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public StatisticsProfile() {
	}

}
