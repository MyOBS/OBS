/**
 * 
 */
package com.struggle.obs.bbs.dao;

import java.util.Date;
import java.util.List;

import com.struggle.obs.bean.credit.CreditRule;
import com.struggle.obs.formbean.CreditLogForm;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.dao.GenericDao;

/**
 * @author tangyh 2014年9月9日 下午3:38:25
 */
public interface CreditRuleDao extends GenericDao<CreditRule, Long> {

	/**
	 * 2014年9月9日 下午3:42:43
	 * @param pageNum
	 * @param pageSize
	 * @param pages
	 * @param creditLogForm
	 * @return Page<CreditRule>
	 */
	Page<CreditRule> getPageList(Integer pageNum, Integer pageSize,
			Integer pages, CreditLogForm creditLogForm);

	/**
	 * 2014年9月10日 下午2:10:55
	 * @param b
	 * @return List<CreditRule>
	 */
	List<CreditRule> findAll(boolean isSystem);

	/**
	 * 2014年9月10日 下午3:44:39
	 * @return List<CreditRule>
	 */
	List<CreditRule> findSystemReward(Long userId);

	/**
	 * 2014年9月10日 下午3:44:44
	 * @param lastRewardTime 最后奖励时间
	 * @param creditRuleId 积分规则Id
	 */
	int getCycleCount(Date lastRewardTime, Long creditRuleId, Long userId);

}
