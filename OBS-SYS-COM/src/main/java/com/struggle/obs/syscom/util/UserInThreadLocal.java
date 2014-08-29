package com.struggle.obs.syscom.util;

/**
 * 
 * @author tangyh
 * @create 2014年2月11日 下午3:44:58
 * @version 0.0.1
 */
public class UserInThreadLocal {

	/**
	 * 用户属性
	 */
	private static String loginName = new String();

	/**
	 * 初始化用户属性
	 */
	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
		/**
		 * 同步初始用户属性
		 */
		@Override
		protected synchronized String initialValue() {
			return loginName;
		}
	};

	/**
	 * 获取通Web程序登录程序的用户登录名 <br>
	 * 
	 * @return 用户登录名
	 */
	public static String getLoginName() {
		return threadLocal.get();
	}

	/**
	 * 设置用户ID
	 * 
	 * @param userID
	 */
	public static void setLoginName(String loginName) {
	    UserInThreadLocal.loginName = loginName;
		threadLocal.set(loginName);
	}
}
