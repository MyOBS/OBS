package com.struggle.obs.oa.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.struggle.obs.bean.user.User;
import com.struggle.obs.oa.web.util.BaseAction;
import com.struggle.obs.oa.web.util.WebUtils;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;

@Controller
@Scope("prototype")
@ParentPackage("OBS-WEB")
public class LoginAction extends BaseAction<User> {

	private static final long serialVersionUID = -1032904756458435870L;

	@Action(value = "admin_login", results = {
			@Result(name = NONE, type="redirectAction", location = "adminLogin"),
//			@Result(name = NONE, location = "/WEB-INF/jsps/user/loginUI.jsp"),
			@Result(name = SUCCESS, type = "redirectAction", location = "home_index")
			})
	public String login() {
		try{
			User user = userService.findAdminByPwd(model);
			if (user == null) {
				//<s:fielderror/>  用于前台显示错误信息
				//addFieldError("loginName", "用户名或密码不正确！");
				message = "用户名或密码不正确！";
				return NONE;
			} else {
				// 登录用户
				WebUtils.addUser(this.getRequest(), user);
//				ActionContext.getContext().getSession().put("user", user);
				return SUCCESS;
			}
		}catch(OBSException e){
			message = "服务器好像罢工了...";
			//addFieldError("loginName", "服务器好像罢工了...");
		}
		return NONE;
	}
	
	@Action(value = "admin_logout", results = {@Result(name=SUCCESS, location="/WEB-INF/jsps/user/logout.jsp")})
	public String loginout(){
		getRequest().getSession().removeAttribute(ConstantDefine.SESSION_USER);
		return SUCCESS;
	}
}
