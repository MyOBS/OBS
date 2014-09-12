/**
 * 
 */
package com.struggle.obs.bbs.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.struggle.obs.bbs.dao.CreditLogDao;
import com.struggle.obs.bbs.service.CreditLogService;
import com.struggle.obs.bean.credit.CreditLog;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.CreditLogForm;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.exception.OBSException;

/**
 * @author tangyh
 *  2014年9月9日 下午3:35:40
 */
@Service
public class CreditLogServiceImpl implements CreditLogService {
	private static Log LOGGER = LogFactory.getLog(CreditLogServiceImpl.class);
	@Autowired
	private CreditLogDao creditLogDao;

	/** 
	 * 2014年9月9日 下午3:41:02
	 * @see com.struggle.obs.bbs.service.CreditLogService#getPageList(java.lang.Integer, java.lang.Integer, java.lang.Integer, com.struggle.obs.formbean.CreditLogForm)
	 */
	@Override
	public Page<CreditLog> getPageList(Integer pageNum, Integer pageSize,
			Integer pages, CreditLogForm creditLogForm, User user) {
		if (creditLogForm == null) {
			creditLogForm = new CreditLogForm();
		}
		try {
			//log creditUI
			if(user != null){
				creditLogForm.setUserId(user.getId());
			}
			creditLogForm.setSystemReward(false);	
			return creditLogDao.getPageList(pageNum, pageSize, pages,
					creditLogForm);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("", e);
		}
	}
}
