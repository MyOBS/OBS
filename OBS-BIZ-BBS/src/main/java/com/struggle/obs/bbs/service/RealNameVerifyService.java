/**
 * 
 */
package com.struggle.obs.bbs.service;

import com.struggle.obs.bean.user.User;

/**
 * @author tangyh
 *  2014年9月8日 下午11:05:53
 */
public interface RealNameVerifyService {

	/**
	 * 2014年9月8日 下午11:07:19
	 * @param realNameVerifyId 实名认证id
	 * @param auditUser 审核人
	 * @param auditStatu 状态
	 */
	void verifyAudit(Long realNameVerifyId, User auditUser, String auditStatu);

}
