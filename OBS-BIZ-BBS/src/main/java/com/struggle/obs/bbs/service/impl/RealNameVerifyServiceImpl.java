/**
 * 
 */
package com.struggle.obs.bbs.service.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.struggle.obs.bbs.dao.RealNameVerifyDao;
import com.struggle.obs.bbs.service.RealNameVerifyService;
import com.struggle.obs.bean.user.RealNameVerify;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.syscom.exception.OBSException;

/**
 * @author tangyh 2014年9月8日 下午11:06:02
 */
@Service
public class RealNameVerifyServiceImpl implements RealNameVerifyService {
	private static Log LOGGER = LogFactory
			.getLog(RealNameVerifyServiceImpl.class);
	@Autowired
	private RealNameVerifyDao realNameVerifyDao;

	/**
	 * 2014年9月8日 下午11:07:38
	 * 
	 * @see com.struggle.obs.bbs.service.RealNameVerifyService#verifyAudit(java.lang.Long,
	 *      com.struggle.obs.bean.user.User)
	 */
	@Override
	@Transactional
	public void verifyAudit(Long realNameVerifyId, User auditUser, String auditStatu) {
		if(realNameVerifyId == null){
			throw new OBSException("审核失败");
		}
		try {
			RealNameVerify rnVerify = realNameVerifyDao.findByPK(realNameVerifyId);
			if(rnVerify == null){
				throw new OBSException("审核失败");
			}
			rnVerify.setAudit(auditStatu);
			rnVerify.setAuditTime(new Date());
			rnVerify.setAuditUser(auditUser.getLoginName());
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("审核失败", e);
		}

	}

}
