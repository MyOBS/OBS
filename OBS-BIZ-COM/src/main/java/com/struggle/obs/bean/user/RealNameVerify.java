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
 * 实名认证
 * @author tangyh
 *  2014年8月29日 上午12:12:11
 */
@Entity
public class RealNameVerify extends BaseModel{
	/** 所属用户 */
	private User user;
	/** 真实姓名 */
	private String realName;
	/** 性别 */
	private String gender; 
	/** 手机 */
	private String mobile;
	/** 邮寄地址 */
	private String address;
	/** 出生地 */
	private String birthCity;
	/** 居住地 */
	private String resideCity;
	/** qq */
	private String qq;
	/** 邮箱 */
	private String email;
	/** 技术方向编码：.net,java,c++,c#等， 该值从Consts表中获取 */
	private String directionCode;
	/** 技术方向名称：.net,java,c++,c#等， 该值从Consts表中获取 */
	private String directionName;
	/** 认证时间 */
	private Date verifyTime;
	/** 审核时间 */
	private Date auditTime;
	/** 审核状态:
	 * 1:未审核：用户保存实名认证信息
	 * 2:已启动：用户点击实名认证审核申请
	 * 3:已审核：管理员确认审核通过
	 * 4:已驳回：管理员驳回用户审核
	 * 5:已取消：用户启动后，可以取消审核
	 */
	private String audit;
	/** 审核人 */
	private String auditUser;
	
	/**
	 * 真实姓名 
	 * @return 
	 */
	@Column(name="realName", length=30)
	public String getRealName() {
		return realName;
	}
	/**
	 * @param 真实姓名 realName
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 * 性别 
	 * @return 
	 */
	@Column(name="gender", length=30)
	public String getGender() {
		return gender;
	}
	/**
	 * @param 性别 gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 手机 
	 * @return 
	 */@Column(name="mobile", length=11)
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
	 * 邮寄地址 
	 * @return 
	 */
	@Column(name="address", length=60)
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
	 * qq 
	 * @return 
	 */
	@Column(name="qq", length=12)
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
	 * 邮箱 
	 * @return 
	 */
	@Column(name="email", length=64)
	public String getEmail() {
		return email;
	}
	/**
	 * @param 邮箱 email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 技术方向编码：.netjavac++c#等，该值从Consts表中获取 
	 * @return 
	 */
	@Column(name="directionCode", length=2)
	public String getDirectionCode() {
		return directionCode;
	}
	/**
	 * @param 技术方向编码：.netjavac++c#等，该值从Consts表中获取 directionCode
	 */
	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}
	/**
	 * 技术方向名称：.netjavac++c#等，该值从Consts表中获取 
	 * @return 
	 */
	@Column(name="directionName", length=10)
	public String getDirectionName() {
		return directionName;
	}
	/**
	 * @param 技术方向名称：.netjavac++c#等，该值从Consts表中获取 directionName
	 */
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}
	/**
	 * 所属用户 
	 * @return 
	 */
	@OneToOne(mappedBy="realNameVerify", cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	public User getUser() {
		return user;
	}
	/**
	 * @param 所属用户 user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	public RealNameVerify() {
	}
	/**
	 * 认证时间 
	 * @return 
	 */
	@Column(name="verifyTime")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getVerifyTime() {
		return verifyTime;
	}
	/**
	 * @param 认证时间 verifyTime
	 */
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	/**
	 * 审核时间 
	 * @return 
	 */
	@Column(name="auditTime")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getAuditTime() {
		return auditTime;
	}
	/**
	 * @param 审核时间 auditTime
	 */
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	/**
	 *  审核状态
	 * 1:未审核：用户保存实名认证信息
	 * 2:已启动：用户点击实名认证审核申请
	 * 3:已审核：管理员确认审核通过
	 * 4:已驳回：管理员驳回用户审核
	 * 5:已取消：用户启动后，可以取消审核
	 * @return 
	 */
	@Column(name="audit")
	public String getAudit() {
		return audit;
	}
	/**
	 * 1:未审核：用户保存实名认证信息
	 * 2:已启动：用户点击实名认证审核申请
	 * 3:已审核：管理员确认审核通过
	 * 4:已驳回：管理员驳回用户审核
	 * 5:已取消：用户启动后，可以取消审核
	 * @param 审核状态
	 */
	public void setAudit(String audit) {
		this.audit = audit;
	}
	/**
	 * 审核人 
	 * @return 
	 */
	@Column(name="auditUser")
	public String getAuditUser() {
		return auditUser;
	}
	/**
	 * @param 审核人 auditUser
	 */
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	
	

}
