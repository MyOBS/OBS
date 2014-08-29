/**
 * 
 */
package com.struggle.obs.bean.user;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.struggle.obs.syscom.bean.BaseModel;

/**
 * 用户基本资料
 * @author tangyh
 *  2014年8月27日 下午4:56:55
 */
@Entity
public class BaseProfile extends BaseModel{

	/** 所属用户 */
	private User user;
	/** M:男 F:女 S:保密  */
	private String gender; 
	/** 生日 */
	private Date birthday; 
	/** 出生地 */
	private String birthCity;
	/** 居住地 */
	private String resideCity;
	/** 昵称 */
	private  String realName;
	
	/**
	 * M:男F:女S:保密 
	 * @return 
	 */
	@Column(name="gender", length=1)
	public String getGender() {
		return gender;
	}

	/**
	 * @param M:男F:女S:保密 gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * 生日 yyyy-MM-dd
	 * @return 
	 */
	@Column(name="birthday", length=10)
	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param 生日 yyyy-MM-dd
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 出生地 
	 * @return 
	 */
	@Column(name="birthCity", length=60)
	public String getBirthCity() {
		return birthCity;
	}

	/**
	 * @param 出生地 birthCity
	 */
	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	/**
	 * 居住地 
	 * @return 
	 */
	@Column(name="resideCity", length=60)
	public String getResideCity() {
		return resideCity;
	}

	/**
	 * @param 居住地 resideCity
	 */
	public void setResideCity(String resideCity) {
		this.resideCity = resideCity;
	}

	/**
	 * 昵称 
	 * @return 
	 */
	@Column(name="realName", length=16)
	public String getRealName() {
		return realName;
	}

	/**
	 * @param 昵称 realName
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 所属用户 
	 * @return 
	 */
	@OneToOne(mappedBy="baseProfile", cascade=CascadeType.REFRESH)
	public User getUser() {
		return user;
	}

	/**
	 * @param 所属用户 user
	 */
	public void setUser(User user) {
		this.user = user;
	}



	public BaseProfile() {
	}
	
	
}
