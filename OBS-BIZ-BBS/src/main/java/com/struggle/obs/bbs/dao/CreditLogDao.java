/**
 * 
 */
package com.struggle.obs.bbs.dao;

import com.struggle.obs.bean.credit.CreditLog;
import com.struggle.obs.formbean.CreditLogForm;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.dao.GenericDao;

/**
 * @author tangyh 2014年9月9日 下午3:36:16
 */
public interface CreditLogDao extends GenericDao<CreditLog, Long> {

	/**
	 * 2014年9月9日 下午3:42:40
	 * @param pageNum
	 * @param pageSize
	 * @param pages
	 * @param creditLogForm
	 * @return Page<CreditLog>
	 */
	Page<CreditLog> getPageList(Integer pageNum, Integer pageSize,
			Integer pages, CreditLogForm creditLogForm);

}
