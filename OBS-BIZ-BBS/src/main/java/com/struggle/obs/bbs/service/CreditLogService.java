/**
 * 
 */
package com.struggle.obs.bbs.service;

import com.struggle.obs.bean.credit.CreditLog;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.CreditLogForm;
import com.struggle.obs.syscom.bean.Page;

/**
 * @author tangyh
 *  2014年9月9日 下午3:35:24
 */
public interface CreditLogService {

	/**
	 * 2014年9月9日 下午3:40:33
	 * @param pageNum
	 * @param pageSize
	 * @param pages
	 * @param creditLogForm
	 * @return Page<CreditLog>
	 */
	Page<CreditLog> getPageList(Integer pageNum, Integer pageSize,
			Integer pages, CreditLogForm creditLogForm, User user);

}
