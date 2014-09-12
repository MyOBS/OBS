/**
 * 
 */
package com.struggle.obs.bean.credit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * 积分规则
 * 
 * @author tangyh 2014年8月28日 下午9:16:51
 */
@Entity
public class CreditRule extends Credit {
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
	/** 排序规则 */
	private int sort;
	/** 总次数：不与数据库关联 */
	private int totalCount;
	/** 周期内总奖励次数：不与数据库关联 */
	private int cycleTotalCount;
	/** 最后奖励时间 ：不与数据库关联 */
	private Date lastRewardTime;

	/**
	 * 最后奖励时间：不与数据库关联
	 * 
	 * @return
	 */
	@Transient
	public Date getLastRewardTime() {
		return lastRewardTime;
	}

	/**
	 * @param 最后奖励时间
	 *            ：不与数据库关联 lastRewardTime
	 */
	public void setLastRewardTime(Date lastRewardTime) {
		this.lastRewardTime = lastRewardTime;
	}

	/**
	 * 总次数：不与数据库关联
	 * 
	 * @return
	 */
	@Transient
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param 总次数
	 *            ：不与数据库关联 totalCount
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 周期内总奖励次数：不与数据库关联
	 * 
	 * @return
	 */
	@Transient
	public int getCycleTotalCount() {
		return cycleTotalCount;
	}

	/**
	 * @param 周期内总奖励次数
	 *            ：不与数据库关联 cycleCount
	 */
	public void setCycleTotalCount(int cycleTotalCount) {
		this.cycleTotalCount = cycleTotalCount;
	}

	/**
	 * 动作名称
	 * 
	 * @return
	 */
	@Column(name = "actionName", length = 10)
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param 动作名称
	 *            actionName
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * 周期范围代码
	 * 
	 * @return
	 */
	@Column(name = "cycleRangeCode", length = 3)
	public String getCycleRangeCode() {
		return cycleRangeCode;
	}

	/**
	 * @param 周期范围代码
	 *            cycleRangeCoed
	 */
	public void setCycleRangeCode(String cycleRangeCode) {
		this.cycleRangeCode = cycleRangeCode;
	}

	/**
	 * 周期范围名称
	 * 
	 * @return
	 */
	@Column(name = "cycleRangeName", length = 20)
	public String getCycleRangeName() {
		return cycleRangeName;
	}

	/**
	 * @param 周期范围名称
	 *            cycleRangeName
	 */
	public void setCycleRangeName(String cycleRangeName) {
		this.cycleRangeName = cycleRangeName;
	}

	/**
	 * 周期内最多奖励次数 <br>
	 * NONE:不限次数
	 * 
	 * @return
	 */
	@Column(name = "cycleCount", length = 4)
	public String getCycleCount() {
		return cycleCount;
	}

	/**
	 * NONE:不限次数
	 * 
	 * @param 周期内最多奖励次数
	 *            cycleCount
	 */
	public void setCycleCount(String cycleCount) {
		this.cycleCount = cycleCount;
	}

	/**
	 * 是否系统奖励 <br>
	 * true(1)：是 ;false(0):不是
	 * 
	 * @return
	 */
	@Column(name = "systemReward", length = 1)
	public boolean isSystemReward() {
		return systemReward;
	}

	/**
	 * @param 是否系统奖励
	 *            systemReward
	 */
	public void setSystemReward(boolean systemReward) {
		this.systemReward = systemReward;
	}

	public CreditRule() {
	}

	/**
	 * @param actionName
	 *            动作名称
	 * @param cycleRangeCode
	 *            周期范围编码
	 * @param cycleRangeName
	 *            周期范围名称
	 * @param cycleCount
	 *            周期内奖励次数
	 * @param systemReward
	 *            是否系统值 true=是， false=否
	 * @param sort
	 *            排序号
	 */
	public CreditRule(String actionName, String cycleRangeCode,
			String cycleRangeName, String cycleCount, boolean systemReward,
			int sort) {
		super();
		this.actionName = actionName;
		this.cycleRangeCode = cycleRangeCode;
		this.cycleRangeName = cycleRangeName;
		this.cycleCount = cycleCount;
		this.systemReward = systemReward;
		this.sort = sort;
	}

	/**
	 * 排序规则
	 * 
	 * @return
	 */
	@Column(name = "sort")
	public int getSort() {
		return sort;
	}

	/**
	 * @param 排序规则
	 *            sort
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	/**
	 * @param actionName 动作名称
	 * @param totalCount 总次数
	 * @param money 金钱
	 * @param experience 经验
	 * @param gold 金币
	 * @param campaignContribution 宣传贡献
	 * @param frozenGold 冻结金币
	 * @param lastRewardTime 最后奖励时间
	 * @param cycleRangeCode 周期范围编码
	 */
	public CreditRule(String actionName, int totalCount, Long money,
			Long experience, Long gold, Long campaignContribution,
			Long frozenGold, Date lastRewardTime, String cycleRangeCode,Long id) {
		this.actionName = actionName;
		this.totalCount = totalCount;
		this.money = money;
		this.experience = experience;
		this.gold = gold;
		this.campaignContribution = campaignContribution;
		this.frozenGold = frozenGold;
		this.lastRewardTime = lastRewardTime;
		this.cycleRangeCode = cycleRangeCode;
		this.id = id;
	}

}
