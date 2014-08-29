package com.struggle.obs.oa.web.util;

import java.io.Serializable;

import com.struggle.obs.bean.user.User;

public class OBSSession implements Serializable{

	private static final long serialVersionUID = -8042712955407644487L;
	
	private String loginName;
	private User user;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
