/**
 * 
 */
package com.struggle.obs.bbs.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.CreditRuleDao;
import com.struggle.obs.bean.credit.CreditRule;
import com.struggle.obs.formbean.CreditLogForm;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.bean.QueryHelper;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;
import com.struggle.obs.syscom.util.ConstantDefine;
import com.struggle.obs.syscom.util.Utils;

/**
 * @author tangyh 2014年9月9日 下午3:38:45
 */
@Repository
public class CreditRuleDaoImpl extends GenericDaoImpl<CreditRule, Long>
		implements CreditRuleDao {

	/**
	 * 2014年9月9日 下午3:43:24
	 * 
	 * @see com.struggle.obs.bbs.dao.CreditRuleDao#getPageList(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer,
	 *      com.struggle.obs.formbean.CreditLogForm)
	 */
	@Override
	public Page<CreditRule> getPageList(Integer pageNum, Integer pageSize,
			Integer pages, CreditLogForm creditLogForm) {
		QueryHelper<CreditRule, Long> queryHelper = new QueryHelper<CreditRule, Long>(
				CreditRule.class, "cr");

		queryHelper.addCondition("cr.deleteFlag=?", ConstantDefine.NO_DEL_FLAG);
		queryHelper.addCondition("cr.systemReward=?", creditLogForm.isSystemReward());
		
		queryHelper.addOrderProperty("cr.sort", true);

		return queryHelper.preparePageBean(this, pageNum, pageSize, pages);
	}

	/**
	 * 2014年9月10日 下午2:11:06
	 * 
	 * @see com.struggle.obs.bbs.dao.CreditRuleDao#findAll(boolean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CreditRule> findAll(boolean isSystem) {
		String hql = "FROM CreditRule c WHERE c.deleteFlag=? AND c.systemReward=? ORDER BY c.sort";
		return getSession().createQuery(hql)
				.setParameter(0, ConstantDefine.NO_DEL_FLAG)
				.setParameter(1, isSystem).list();
	}

	/**
	 * 2014年9月10日 下午3:45:29
	 * 
	 * @see com.struggle.obs.bbs.dao.CreditRuleDao#findSystemReward()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CreditRule> findSystemReward(Long userId) {
		// String hql = " SELECT c.*, "
		// +
		// " (SELECT cl.addDate FROM CreditLog cl WHERE cl.creditRule.id=c.id ORDER BY cl.addDate DESC LIMIT 1) as lastRewardTime, "
		// +
		// " (SELECT COUNT(cl2.id) FROM CreditLog cl2 WHERE cl2.creditRule.id=c.id ) as totalCount "
		// + "  	 FROM  CreditRule c "
		// +
		// " WHERE c.systemReward=? AND (SELECT COUNT(cl2.id)  FROM CreditLog cl2 WHERE cl2.creditRule.id=c.id ) > 0; ";
		// String sql = " SELECT c.*, "
		// +
		// " (SELECT cl.add_Date FROM CreditLog cl WHERE cl.creditRuleId=c.id ORDER BY cl.add_Date DESC LIMIT 1) as lastRewardTime, "
		// +
		// " (SELECT COUNT(cl2.id) FROM CreditLog cl2 WHERE cl2.creditRuleId=c.id ) as totalCount "
		// + "  	 FROM  CreditRule c "
		// +
		// " WHERE c.systemReward=1 AND (SELECT COUNT(cl2.id)  FROM CreditLog cl2 WHERE cl2.creditRuleId=c.id ) > 0; ";
		// return
		// getSession().createSQLQuery(sql).addEntity(CreditRule.class).list();
		String sql = " SELECT c.actionName, "
				+ " (SELECT COUNT(cl2.id) FROM CreditLog cl2 WHERE cl2.creditRuleId=c.id AND cl2.userId=" + userId + ") as totalCount, "
				+ "  c.money,c.experience,c.gold,c.campaignContribution,c.frozenGold, "
				+ " (SELECT cl.add_Date FROM CreditLog cl WHERE cl.creditRuleId=c.id AND cl.userId=" + userId + " ORDER BY cl.add_Date DESC LIMIT 1) as lastRewardTime, "
				+ " c.cycleRangeCode, c.id "
				+ "  	 FROM  CreditRule c "
				+ " WHERE c.systemReward=1 AND (SELECT COUNT(cl2.id)  FROM CreditLog cl2 WHERE cl2.creditRuleId=c.id AND cl2.userId=" + userId + " ) > 0; ";

		List<Object[]> list = getSession().createSQLQuery(sql).list();
		List<CreditRule> creditRules = trans2Bean(list);
		return creditRules;
	}

	/**
	 * 2014年9月10日 下午4:59:49
	 * 
	 * @param list
	 *            void
	 * @throws ParseException
	 */
	private List<CreditRule> trans2Bean(List<Object[]> list) {
		List<CreditRule> creditRuleList = new ArrayList<CreditRule>();
		if (list != null) {
			for (Object[] obj : list) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					creditRuleList.add(new CreditRule(String.valueOf(obj[0]),
							Integer.parseInt(obj[1].toString()), //
							Long.valueOf(obj[2].toString()),//
							Long.valueOf(obj[3].toString()),//
							Long.valueOf(obj[4].toString()),//
							Long.valueOf(obj[5].toString()),//
							Long.valueOf(obj[6].toString()), //
							sdf.parse(obj[7].toString()),//
								String.valueOf(obj[8]),	//
								Long.valueOf(obj[9].toString())
									));
				} catch (Exception e) {
					return null;
				}
			}
		}
		return creditRuleList;
	}

	/**
	 * 2014年9月10日 下午3:45:29
	 * 
	 * @see com.struggle.obs.bbs.dao.CreditRuleDao#getCycleCount(java.util.Date,
	 *      java.lang.Long)
	 */
	@Override
	public int getCycleCount(Date lastRewardTime, Long creditRuleId, Long userId) {
		Date endDate = Utils.modifyDate(lastRewardTime, Calendar.DATE, 1);
		String hql = "SELECT COUNT(*) FROM CreditLog cr WHERE  cr.addDate >= ? AND cr.addDate<? AND cr.creditRule.id=? "
				+ "AND cr.deleteFlag=?  AND cr.user.id=? ";
		Long count = (Long) getSession().createQuery(hql)
				.setDate(0, lastRewardTime).setDate(1, endDate)
				.setParameter(2, creditRuleId)
				.setParameter(3, ConstantDefine.NO_DEL_FLAG)
				.setParameter(4, userId).uniqueResult();
		if (count != null) {
			return count.intValue();
		}
		return 0;
	}

}
