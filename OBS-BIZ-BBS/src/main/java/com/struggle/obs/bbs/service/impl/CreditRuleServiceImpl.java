/**
 * 
 */
package com.struggle.obs.bbs.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.struggle.obs.bbs.dao.CreditRuleDao;
import com.struggle.obs.bbs.service.CreditRuleService;
import com.struggle.obs.bean.credit.CreditRule;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.CreditLogForm;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.exception.OBSException;

/**
 * @author tangyh 2014年9月9日 下午3:39:47
 */
@Service
public class CreditRuleServiceImpl implements CreditRuleService {
	private static Log LOGGER = LogFactory.getLog(ForumServiceImpl.class);
	@Autowired
	private CreditRuleDao creditRuleDao;

	/**
	 * 2014年9月9日 下午3:41:05
	 * 
	 * @see com.struggle.obs.bbs.service.CreditRuleService#getPageList(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer,
	 *      com.struggle.obs.formbean.CreditLogForm)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<CreditRule> getPageList(Integer pageNum, Integer pageSize,
			Integer pages, CreditLogForm creditLogForm) {
		if (creditLogForm == null) {
			creditLogForm = new CreditLogForm();
		}
		try {
			//rule
			creditLogForm.setSystemReward(true);
			return creditRuleDao.getPageList(pageNum, pageSize, pages,
					creditLogForm);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询失败", e);
		}
	}

	/**
	 * 2014年9月10日 上午11:31:24
	 * 
	 * @see com.struggle.obs.bbs.service.CreditRuleService#save(com.struggle.obs.bean.credit.CreditRule)
	 */
	@Override
	@Transactional
	public void save(CreditRule cr) {
		if (cr == null) {
			throw new OBSException("保存失败");
		}
		try {
			creditRuleDao.save(cr);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("保存失败", e);
		}

	}

	/**
	 * 2014年9月10日 下午1:58:45
	 * 
	 * @see com.struggle.obs.bbs.service.CreditRuleService#findAll()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CreditRule> findAllNoSys() {
		try {
			return creditRuleDao.findAll(false);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询失败", e);
		}
	}

	/**
	 * 2014年9月10日 下午2:39:42
	 * 
	 * @see com.struggle.obs.bbs.service.CreditRuleService#findSystemReward()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CreditRule> findSystemReward(User user) {
		if(user == null){
			throw new OBSException("查询失败");
		}
		try {
			List<CreditRule> creditRules = creditRuleDao.findSystemReward(user.getId());
			if (creditRules != null) {
				for (CreditRule cr : creditRules) {
					String cycleRangeCode = cr.getCycleRangeCode();// 周期奖励编码
					int cycleCount = 0;// 周期内奖励总次数
					if ("1".equals(cycleRangeCode)) {
						// 每天:查询最后奖励时间内的次数
						cycleCount = creditRuleDao.getCycleCount(cr.getLastRewardTime(),
								cr.getId(),user.getId());
					} else if ("2".equals(cycleRangeCode)) {
						// 一次性
						try {
							cycleCount = Integer.valueOf(cr.getCycleCount());
						} catch (Exception e) {
							cycleCount = 0;
						}
					} else if ("max".equals(cycleRangeCode)) {
						// 不限次数
						cycleCount = cr.getTotalCount();
					}
					cr.setCycleTotalCount(cycleCount);
				}
			}
			return creditRules;
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询失败", e);
		}
	}
}
