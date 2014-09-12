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
 * 个人信息
 * @author tangyh
 *  2014年8月29日 上午12:09:50
 */
@Entity
public class InfoProfile  extends BaseModel {
	/** 所属用户 */
	private User user;
	/** 邮寄地址 */
	private String address;
	/** 邮编 */
	private String zipcode;
	/** 个人主页 */
	private String site;
	/** 自我介绍 */
	private String bio;
	
	/**
	 * 邮寄地址 
	 * @return 
	 */
	@Column(name="address", length = 60)
	public String getAddress() {
		return address;
	}
	/**
	 * @param 邮寄地址 address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 邮编 
	 * @return 
	 */
	@Column(name="zipcode", length = 6)
	public String getZipcode() {
		return zipcode;
	}
	/**
	 * @param 邮编 zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * 个人主页 
	 * @return 
	 */
	@Column(name="site", length = 100)
	public String getSite() {
		return site;
	}
	/**
	 * @param 个人主页 site
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * 自我介绍 
	 * @return 
	 */
	@Column(name="bio", length = 255)
	public String getBio() {
		return bio;
	}
	/**
	 * @param 自我介绍 bio
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}
	/**
	 * 所属用户 
	 * @return 
	 */
	@OneToOne(mappedBy="infoProfile", cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	public User getUser() {
		return user;
	}
	/**
	 * @param 所属用户 user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	public InfoProfile() {
	}

}
