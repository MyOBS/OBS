/**
 * 
 */
package com.struggle.obs.bean.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.struggle.obs.syscom.bean.BaseModel;

/**
 * 联系方式
 * @author tangyh
 *  2014年8月29日 上午12:09:12
 */
@Entity
public class ContactProfile  extends BaseModel{
	/** 手机 */  
	private String mobile;
	/** qq */  
	private String qq;
	/** email */  
	private String email;
	/** 所属用户 */
	private User user;
	
	
	/**
	 * 手机 
	 * @return 
	 */
	@Column(name="mobile", length = 11)
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param 手机 mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * qq 
	 * @return 
	 */
	@Column(name="qq", length = 11)
	public String getQq() {
		return qq;
	}

	/**
	 * @param qq qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * email 
	 * @return 
	 */
	@Column(name="email", length = 64)
	public String getEmail() {
		return email;
	}

	/**
	 * @param email email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	/**
	 * 所属用户 
	 * @return 
	 */
	@OneToOne(mappedBy="contactProfile", cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	public User getUser() {
		return user;
	}

	/**
	 * @param 所属用户 user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	public ContactProfile() {
	}

}
