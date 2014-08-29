package com.struggle.obs.oa.web.util;

import javax.servlet.http.HttpServletRequest;

import com.struggle.obs.bean.user.User;
import com.struggle.obs.syscom.util.ConstantDefine;

public class WebUtils {
	/**
	 * 获取登录员工
	 */
	public static void addUser(HttpServletRequest request, User user){
		request.getSession().setAttribute(ConstantDefine.SESSION_USER, user);
	}
	
	/**
	 * 获取登录用户
	 */
	public static User getUser(HttpServletRequest request){
		return (User) request.getSession().getAttribute(ConstantDefine.SESSION_USER);
	}
	
}
