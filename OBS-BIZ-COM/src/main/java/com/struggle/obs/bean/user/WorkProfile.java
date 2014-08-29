/**
 * 
 */
package com.struggle.obs.bean.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.struggle.obs.syscom.bean.BaseModel;

/**
 * 工作情况
 * @author tangyh
 *  2014年8月29日 上午12:09:30
 */
@Entity
public class WorkProfile  extends BaseModel{
	/** 所属用户 */
	private User user;
	/** 公司 */ 
	private String company;
	/** 职业 */ 
	private String occupation;
	/** 职位 */ 
	private String position;
	/** 工资 */ 
	private String wage;
	/** 年限 */ 
	private int lift;
	
	/**
	 * 公司 
	 * @return 
	 */
	@Column(name="company", length = 30)
	public String getCompany() {
		return company;
	}
	/**
	 * @param 公司 company
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * 职业 
	 * @return 
	 */
	@Column(name="occupation", length = 20)
	public String getOccupation() {
		return occupation;
	}
	/**
	 * @param 职业 occupation
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	/**
	 * 职位 
	 * @return 
	 */
	@Column(name="position", length = 20)
	public String getPosition() {
		return position;
	}
	/**
	 * @param 职位 position
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 工资 月薪
	 * @return 
	 */
	@Column(name="wage", length = 9)
	public String getWage() {
		return wage;
	}
	/**
	 * @param 工资 月薪
	 */
	public void setWage(String wage) {
		this.wage = wage;
	}
	/**
	 * 年限 
	 * @return 
	 */
	@Column(name="lift", length = 2)
	public int getLift() {
		return lift;
	}
	/**
	 * @param 年限 lift
	 */
	public void setLift(int lift) {
		this.lift = lift;
	}
	/**
	 * 所属用户 
	 * @return 
	 */
	@OneToOne(mappedBy="workProfile", cascade=CascadeType.REFRESH)
	public User getUser() {
		return user;
	}
	/**
	 * @param 所属用户 user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	public WorkProfile() {
	}

}
