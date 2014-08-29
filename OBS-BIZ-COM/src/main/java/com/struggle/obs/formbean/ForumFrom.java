/**
 * 
 */
package com.struggle.obs.formbean;

import com.struggle.obs.bean.user.User;

/**
 * @author tangyh
 *  2014年8月22日 下午3:52:13
 */
public class ForumFrom {
	/** 今日帖子数量 */
	private Integer todayTopicCount;
	/** 昨日帖子数量 */
	private Integer yesterdayTopicCount;
	/** 总帖子数量 */
	private Integer totalTopicCount;
	/** 总会员数数量 */
	private Integer totalUserCount;
	/**  新会员 */
	private User newUser;
	
	/**
	 * 今日帖子数量 
	 * @return 
	 */
	public Integer getTodayTopicCount() {
		return todayTopicCount;
	}
	/**
	 * @param 今日帖子数量 todayTopicCount
	 */
	public void setTodayTopicCount(Integer todayTopicCount) {
		this.todayTopicCount = todayTopicCount;
	}
	/**
	 * 昨日帖子数量 
	 * @return 
	 */
	public Integer getYesterdayTopicCount() {
		return yesterdayTopicCount;
	}
	/**
	 * @param 昨日帖子数量 yesterdayTopicCount
	 */
	public void setYesterdayTopicCount(Integer yesterdayTopicCount) {
		this.yesterdayTopicCount = yesterdayTopicCount;
	}
	/**
	 * 总帖子数量 
	 * @return 
	 */
	public Integer getTotalTopicCount() {
		return totalTopicCount;
	}
	/**
	 * @param 总帖子数量 totalTopicCount
	 */
	public void setTotalTopicCount(Integer totalTopicCount) {
		this.totalTopicCount = totalTopicCount;
	}
	/**
	 * 总会员数数量 
	 * @return 
	 */
	public Integer getTotalUserCount() {
		return totalUserCount;
	}
	/**
	 * @param 总会员数数量 totalUserCount
	 */
	public void setTotalUserCount(Integer totalUserCount) {
		this.totalUserCount = totalUserCount;
	}
	/**
	 * 
	 * @param todayTopicCount 今日帖子数
	 * @param yesterdayTopicCount 昨日帖子数
	 * @param totalTopicCount 总帖子数
	 * @param totalUserCount 总会员数
	 * @param newUser 新用户
	 */
	public ForumFrom(Integer todayTopicCount, Integer yesterdayTopicCount,
			Integer totalTopicCount, Integer totalUserCount, User newUser) {
		super();
		this.todayTopicCount = todayTopicCount;
		this.yesterdayTopicCount = yesterdayTopicCount;
		this.totalTopicCount = totalTopicCount;
		this.totalUserCount = totalUserCount;
		this.newUser = newUser;
	}
	public ForumFrom() {
	}
	/**
	 * 新会员 
	 * @return 
	 */
	public User getNewUser() {
		return newUser;
	}
	/**
	 * @param 新会员 newUser
	 */
	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}
	
}
