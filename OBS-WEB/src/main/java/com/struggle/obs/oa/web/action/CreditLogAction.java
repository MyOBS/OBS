/**
 * 
 */
package com.struggle.obs.oa.web.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.struggle.obs.bean.base.Consts;
import com.struggle.obs.bean.credit.CreditLog;
import com.struggle.obs.bean.credit.CreditRule;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.CreditLogForm;
import com.struggle.obs.oa.web.util.BaseAction;
import com.struggle.obs.oa.web.util.WebUtils;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;

/**
 * 积分日志
 * 
 * @author tangyh 2014年9月9日 下午2:39:15
 */
@Controller
@Scope("prototype")
@Namespace("/control/creditlog")
@ParentPackage("OBS-WEB")
public class CreditLogAction extends BaseAction<CreditLog> {
	private static final long serialVersionUID = -8258221559623468322L;
	/** 登录用户 */
	private User userBean;
	/** 标签 */
	private String tab = "creditUI";
	/** 积分记录表单对象 */
	private CreditLogForm creditLogForm;
	/** 积分规则集合 */
	private Page<CreditRule> creditRulePager;
	/** 积分集合 */
	private List<Consts> creditList;
	/** 操作集合 */
	private List<CreditRule> creditRuleList;
	/** 系统奖励集合 */
	private List<CreditRule> sysCreditRuleList;
	/**
	 * 跳转到积分<br>
	 * 2014年9月1日 上午12:35:42
	 * 
	 * @return String
	 */
	@Action(value = "creditUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/creditUI.jsp") })
	public String creditUI() {
		try {
			// 我的积分
			userBean = WebUtils.getUser(getRequest());
			if (userBean != null) {
				userBean = userService.getUserById(userBean.getId());
			}
			// 默认查询前10条
			pageSize = 10;
			pager = creditLogService.getPageList(pageNum, pageSize, pages,
					creditLogForm,userBean);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 兑换<br>
	 * 2014年9月9日 下午2:46:56
	 * 
	 * @return String
	 */
	@Action(value = "exchangeUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/creditUI.jsp") })
	public String exchangeUI() {
		try {
			// 我的积分
			userBean = WebUtils.getUser(getRequest());
			if (userBean != null) {
				userBean = userService.getUserById(userBean.getId());
			}
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	@Action(value = "exchange", results = { @Result(name = SUCCESS, type = "redirectAction", location = "exchangeUI", params = {
			"tab", "exchangeUI","result","${result}","message","${message}" }) })
	public String exchange() {
		try {
			User user = WebUtils.getUser(getRequest());
			userService.exchangeCredit(user, creditLogForm);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 积分记录<br>
	 * 2014年9月9日 下午2:46:43
	 * 
	 * @return String
	 */
	@Action(value = "log", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/creditUI.jsp") })
	public String log() {
		try {
			creditRuleList = creditRuleService.findAllNoSys();
			creditList = constsService.findByRecType(ConstantDefine.CREDIT);
			User loginUser = WebUtils.getUser(getRequest());
			pager = creditLogService.getPageList(pageNum, pageSize, pages,
					creditLogForm, loginUser);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}
	/**
	 * 系统奖励<br>
	 * 2014年9月10日 下午2:14:59
	 * @return String
	 */
	@Action(value = "sysLog", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/creditUI.jsp") })
	public String sysLog() {
		try {
			User user = WebUtils.getUser(getRequest());
			sysCreditRuleList = creditRuleService.findSystemReward(user);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 积分规则<br>
	 * 2014年9月9日 下午2:46:29
	 * 
	 * @return String
	 */
	@Action(value = "rule", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/creditUI.jsp") })
	public String rule() {
		try {
			pageSize = 300;
			creditRulePager = creditRuleService.getPageList(pageNum, pageSize,
					pages, creditLogForm);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 登录用户
	 * 
	 * @return
	 */
	public User getUserBean() {
		return userBean;
	}

	/**
	 * @param 登录用户
	 *            userBean
	 */
	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

	/**
	 * 标签
	 * 
	 * @return
	 */
	public String getTab() {
		return tab;
	}

	/**
	 * @param 标签
	 *            tab
	 */
	public void setTab(String tab) {
		this.tab = tab;
	}

	/**
	 * 积分记录表单对象
	 * 
	 * @return
	 */
	public CreditLogForm getCreditLogForm() {
		return creditLogForm;
	}

	/**
	 * @param 积分记录表单对象
	 *            creditLogForm
	 */
	public void setCreditLogForm(CreditLogForm creditLogForm) {
		this.creditLogForm = creditLogForm;
	}

	/**
	 * 积分规则集合
	 * 
	 * @return
	 */
	public Page<CreditRule> getCreditRulePager() {
		return creditRulePager;
	}

	/**
	 * @param 积分规则集合
	 *            creditRulePager
	 */
	public void setCreditRulePager(Page<CreditRule> creditRulePager) {
		this.creditRulePager = creditRulePager;
	}

	/**
	 * 积分集合 
	 * @return 
	 */
	public List<Consts> getCreditList() {
		return creditList;
	}

	/**
	 * @param 积分集合 creditList
	 */
	public void setCreditList(List<Consts> creditList) {
		this.creditList = creditList;
	}

	/**
	 * 操作集合 
	 * @return 
	 */
	public List<CreditRule> getCreditRuleList() {
		return creditRuleList;
	}

	/**
	 * @param 操作集合 creditRuleList
	 */
	public void setCreditRuleList(List<CreditRule> creditRuleList) {
		this.creditRuleList = creditRuleList;
	}

	/**
	 * 系统奖励集合 
	 * @return 
	 */
	public List<CreditRule> getSysCreditRuleList() {
		return sysCreditRuleList;
	}

	/**
	 * @param 系统奖励集合 sysCreditRuleList
	 */
	public void setSysCreditRuleList(List<CreditRule> sysCreditRuleList) {
		this.sysCreditRuleList = sysCreditRuleList;
	}

}
