/**
 * 
 */
package com.struggle.obs.bbs.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.CreditLogDao;
import com.struggle.obs.bean.credit.CreditLog;
import com.struggle.obs.formbean.CreditLogForm;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.bean.QueryHelper;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;
import com.struggle.obs.syscom.util.ConstantDefine;
import com.struggle.obs.syscom.util.Utils;

/**
 * @author tangyh 2014年9月9日 下午3:36:52
 */
@Repository
public class CreditLogDaoImpl extends GenericDaoImpl<CreditLog, Long> implements
		CreditLogDao {

	/**
	 * 2014年9月9日 下午3:43:20
	 * 
	 * @see com.struggle.obs.bbs.dao.CreditLogDao#getPageList(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer,
	 *      com.struggle.obs.formbean.CreditLogForm)
	 */
	@Override
	public Page<CreditLog> getPageList(Integer pageNum, Integer pageSize,
			Integer pages, CreditLogForm creditLogForm) {
		QueryHelper<CreditLog, Long> queryHelper = new QueryHelper<CreditLog, Long>(
				CreditLog.class, "cl");
		queryHelper.addCondition("cl.deleteFlag=?", ConstantDefine.NO_DEL_FLAG);
		queryHelper.addCondition("cl.creditRule.systemReward=?",
				creditLogForm.isSystemReward());

		queryHelper.addCondition(creditLogForm.getUserId() != null,
				"cl.user.id=?", creditLogForm.getUserId());
		queryHelper.addCondition(
				!Utils.isEmptyOrNull(creditLogForm.getCredit()), "cl."
						+ creditLogForm.getCredit() + " IS NOT NULL");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (!Utils.isEmptyOrNull(creditLogForm.getStartDate())) {
				queryHelper.addCondition("cl.addDate>=?",
						sdf.parse(creditLogForm.getStartDate()));
			}
			if (!Utils.isEmptyOrNull(creditLogForm.getEndDate())) {
				Date endDate = Utils.modifyDate(creditLogForm.getEndDate(),
						"yyyy-MM-dd", Calendar.DATE, 1);
				queryHelper.addCondition("cl.addDate<?", endDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		queryHelper.addCondition(
				!Utils.isEmptyOrNull(creditLogForm.getOperation()),
				"cl.creditRule.actionName=?", creditLogForm.getOperation());
		queryHelper.addCondition(!Utils.isEmptyOrNull(creditLogForm.getIoe()),
				"cl.ioe=?", creditLogForm.getIoe());

		queryHelper.addOrderProperty("cl.addDate", false);

		return queryHelper.preparePageBean(this, pageNum, pageSize, pages);
	}

}
