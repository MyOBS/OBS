/**
 * 
 */
package com.struggle.obs.bean.credit;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.struggle.obs.syscom.bean.BaseModelSub;

/**
 * 积分
 * @author tangyh
 *  2014年8月28日 下午9:11:26
 */
@MappedSuperclass
public class Credit extends BaseModelSub {
	/** 金钱  */
	protected Long money;
	/**  经验 */
	protected Long experience;
	/** 金币  */
	protected Long gold;
	/**  宣传贡献 */
	protected Long campaignContribution;
	/**  冻结金币 */
	protected Long frozenGold;
	/**
	 * 金钱 
	 * @return 
	 */
	@Column(name="money")
	public Long getMoney() {
		return money;
	}
	/**
	 * @param 金钱 money
	 */
	public void setMoney(Long money) {
		this.money = money;
	}
	/**
	 * 经验 
	 * @return 
	 */
	@Column(name="experience")
	public Long getExperience() {
		return experience;
	}
	/**
	 * @param 经验 experience
	 */
	public void setExperience(Long experience) {
		this.experience = experience;
	}
	/**
	 * 金币 
	 * @return 
	 */
	@Column(name="gold")
	public Long getGold() {
		return gold;
	}
	/**
	 * @param 金币 gold
	 */
	public void setGold(Long gold) {
		this.gold = gold;
	}
	/**
	 * 宣传贡献 
	 * @return 
	 */
	@Column(name="campaignContribution")
	public Long getCampaignContribution() {
		return campaignContribution;
	}
	/**
	 * @param 宣传贡献 campaignContribution
	 */
	public void setCampaignContribution(Long campaignContribution) {
		this.campaignContribution = campaignContribution;
	}
	/**
	 * 冻结金币 
	 * @return 
	 */
	@Column(name="frozenGold")
	public Long getFrozenGold() {
		return frozenGold;
	}
	/**
	 * @param 冻结金币 frozenGold
	 */
	public void setFrozenGold(Long frozenGold) {
		this.frozenGold = frozenGold;
	}
}
