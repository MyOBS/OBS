/**
 * 
 */
package com.struggle.obs.bean.credit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.struggle.obs.bean.user.User;

/**
 * 积分日志
 * 
 * @author tangyh 2014年8月28日 下午9:15:43
 */
@Entity
public class CreditLog extends Credit {
	/** 用户 */
	private User user;
	/** 操作 */
	private CreditRule creditRule;
	/** 收支: 1:收入 ;0:支出 */
	private String ioe;
	/** 详情 */
	private String details;
	/** url */
	private String url;
	

	/**
	 * 用户
	 * 
	 * @return
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}

	/**
	 * @param 用户
	 *            user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 操作
	 * 
	 * @return
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "creditRuleId")
	public CreditRule getCreditRule() {
		return creditRule;
	}

	/**
	 * @param 操作
	 *            creditRule
	 */
	public void setCreditRule(CreditRule creditRule) {
		this.creditRule = creditRule;
	}

	/**
	 * 详情
	 * 
	 * @return
	 */
	@Column(name = "details",length=100)
	public String getDetails() {
		return details;
	}

	/**
	 * @param 详情
	 *            details
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * url
	 * 
	 * @return
	 */
	@Column(name = "url",length=100)
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	

	public CreditLog() {
	}

	/**
	 * 收支:1:收入;0:支出 
	 * @return 
	 */
	@Column(name="ioe", length=1)
	public String getIoe() {
		return ioe;
	}

	/**
	 * @param 收支:1:收入;0:支出 ioe
	 */
	public void setIoe(String ioe) {
		this.ioe = ioe;
	}

}
