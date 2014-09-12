/**
 * 
 */
package com.struggle.obs.formbean;

/**
 * @author tangyh
 *  2014年9月8日 下午8:52:28
 */
public class UserForm {
	
	private Long userId;
	/** 审核状态 */
	private String auditStatu;
	/**
	 * 技术方向
	 */
	private String directionCode;
	/**
	 * 用户名
	 */
	private String loginName;
	/**
	 * 性别:M:男  F:女  S:保密
	 */
	private String gender;
	/**
	 * 出生地
	 */
	private String birthCity;
	/**
	 * 居住地 
	 */
	private String resideCity;
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
	/**
	 * 技术方向 
	 * @return 
	 */
	public String getdirectionCode() {
		return directionCode;
	}
	/**
	 * @param 技术方向 direction
	 */
	public void setdirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}
	/**
	 * 用户名 
	 * @return 
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param 用户名 loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * 性别 :M:男  F:女  S:保密
	 * @return 
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param 性别 :M:男  F:女  S:保密
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 出生地 
	 * @return 
	 */
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
	public String getResideCity() {
		return resideCity;
	}
	/**
	 * @param 居住地 address
	 */
	public void setResideCity(String resideCity) {
		this.resideCity = resideCity;
	}
	/**
	 * 审核状态 
	 * @return 
	 */
	public String getAuditStatu() {
		return auditStatu;
	}
	/**
	 * @param 审核状态 auditStatu
	 */
	public void setAuditStatu(String auditStatu) {
		this.auditStatu = auditStatu;
	}
	/**
	 * 技术方向 
	 * @return 
	 */
	public String getDirectionCode() {
		return directionCode;
	}
	/**
	 * @param 技术方向 directionCode
	 */
	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

}
