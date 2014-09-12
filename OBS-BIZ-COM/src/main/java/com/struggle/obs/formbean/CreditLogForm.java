/**
 * 
 */
package com.struggle.obs.formbean;

/**
 * 积分日志
 * @author tangyh
 *  2014年9月9日 下午2:52:33
 */
public class CreditLogForm {
	private Long userId;
	private Long creditLogId;
	/** 积分 */
	private String credit;
	/** 开始时间 */
	private String startDate;
	/** 结束时间 */
	private String endDate;
	/** 收支 */
	private String ioe;
	/** 操作 */
	private String operation;
	/** 登录密码 */
	private String password;
	/** 兑换类型: 1:用金币兑换金钱; 2： 用金钱兑换金币 */
	private String exchange;
	/** 兑换前值 */
	private Long startValue;
	/** 兑换后值 */
	private Long endValue;
	/** 系统值: default=true */
	private boolean systemReward = true;
	
	/**
	 * @param id
	 */
	public CreditLogForm(Long userId) {
		this.userId = userId;
	}
	/**
	 * creditLogId 
	 * @return 
	 */
	public Long getCreditLogId() {
		return creditLogId;
	}
	/**
	 * @param creditLogId creditLogId
	 */
	public void setCreditLogId(Long creditLogId) {
		this.creditLogId = creditLogId;
	}
	/**
	 * 积分 
	 * @return 
	 */
	public String getCredit() {
		return credit;
	}
	/**
	 * @param 积分 credit
	 */
	public void setCredit(String credit) {
		this.credit = credit;
	}
	/**
	 * 开始时间 
	 * @return 
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param 开始时间 startDate
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * 结束时间 
	 * @return 
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param 结束时间 endDate
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * 收支 
	 * @return 
	 */
	public String getIoe() {
		return ioe;
	}
	/**
	 * @param 收支 ioe
	 */
	public void setIoe(String ioe) {
		this.ioe = ioe;
	}
	/**
	 * 操作 
	 * @return 
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * @param 操作 operation
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	/**
	 * 登录密码 
	 * @return 
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param 登录密码 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 兑换类型 1:用金币兑换金钱; 2： 用金钱兑换金币
	 * @return 
	 */
	public String getExchange() {
		return exchange;
	}
	/**
	 * @param 兑换类型 1:用金币兑换金钱; 2： 用金钱兑换金币
	 */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	/**
	 * 兑换前值 
	 * @return 
	 */
	public Long getStartValue() {
		return startValue;
	}
	/**
	 * @param 兑换前值 startValue
	 */
	public void setStartValue(Long startValue) {
		this.startValue = startValue;
	}
	/**
	 * 兑换后值 
	 * @return 
	 */
	public Long getEndValue() {
		return endValue;
	}
	/**
	 * @param 兑换后值 endValue
	 */
	public void setEndValue(Long endValue) {
		this.endValue = endValue;
	}
	/**
	 * userId 
	 * @return 
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public CreditLogForm() {
	}
	/**
	 * 系统值 : default=true
	 * @return 
	 */
	public boolean isSystemReward() {
		return systemReward;
	}
	/**
	 * @param 系统值 : default=true
	 */
	public void setSystemReward(boolean systemReward) {
		this.systemReward = systemReward;
	}
	
}
