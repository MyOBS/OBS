/**
 * 
 */
package com.struggle.obs.bean.credit;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 积分规则
 * @author tangyh
 *  2014年8月28日 下午9:16:51
 */
@Entity
public class CreditRule extends Credit{
	/** 动作名称 */
	private String actionName;
	/** 周期范围代码 */
	private String cycleRangeCode;
	/** 周期范围名称 */
	private String cycleRangeName;
	/** 周期内最多奖励次数 */
	private String cycleCount;
	/** 是否系统奖励 */
	private boolean systemReward = false;
	/**
	 * 动作名称 
	 * @return 
	 */
	@Column(name="actionName",length=10)
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param 动作名称 actionName
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * 周期范围代码 
	 * @return 
	 */
	@Column(name="cycleRangeCode",length=3)
	public String getCycleRangeCode() {
		return cycleRangeCode;
	}
	/**
	 * @param 周期范围代码 cycleRangeCoed
	 */
	public void setCycleRangeCode(String cycleRangeCode) {
		this.cycleRangeCode = cycleRangeCode;
	}
	/**
	 * 周期范围名称 
	 * @return 
	 */
	@Column(name="cycleRangeName",length=20)
	public String getCycleRangeName() {
		return cycleRangeName;
	}
	/**
	 * @param 周期范围名称 cycleRangeName
	 */
	public void setCycleRangeName(String cycleRangeName) {
		this.cycleRangeName = cycleRangeName;
	}
	/**
	 * 周期内最多奖励次数 <br>
	 * NONE:不限次数
	 * @return 
	 */
	@Column(name="cycleCount",length=4)
	public String getCycleCount() {
		return cycleCount;
	}
	/**
	 * NONE:不限次数
	 * @param 周期内最多奖励次数 cycleCount
	 */
	public void setCycleCount(String cycleCount) {
		this.cycleCount = cycleCount;
	}
	/**
	 * 是否系统奖励 <br>
	 * true(1)：是 ;false(0):不是
	 * @return 
	 */
	@Column(name="systemReward",length=1)
	public boolean isSystemReward() {
		return systemReward;
	}
	
	/**
	 * @param 是否系统奖励 systemReward
	 */
	public void setSystemReward(boolean systemReward) {
		this.systemReward = systemReward;
	}
	public CreditRule() {
	}
	

}
