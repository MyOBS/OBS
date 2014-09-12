/**
 * 
 */
package com.struggle.obs.bbs.service;

import java.util.List;

import com.struggle.obs.bean.credit.CreditRule;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.CreditLogForm;
import com.struggle.obs.syscom.bean.Page;

/**
 * @author tangyh
 *  2014年9月9日 下午3:38:17
 */
public interface CreditRuleService {

	/**
	 * 2014年9月9日 下午3:40:27
	 * @param pageNum
	 * @param pageSize
	 * @param pages
	 * @param creditLogForm
	 * @return Page<CreditRule>
	 */
	Page<CreditRule> getPageList(Integer pageNum, Integer pageSize,
			Integer pages, CreditLogForm creditLogForm);

	/**
	 * 2014年9月10日 上午11:30:59
	 * @param cr1 void
	 */
	void save(CreditRule cr);


	/**
	 * 2014年9月10日 下午2:10:02
	 * @return List<CreditRule>
	 */
	List<CreditRule> findAllNoSys();

	/**
	 * 2014年9月10日 下午2:39:30
	 * @return List<CreditRule>
	 */
	List<CreditRule> findSystemReward(User user);

}
