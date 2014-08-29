package com.struggle.obs.bbs.service;


import java.util.List;

import com.struggle.obs.bean.user.User;

/**
 * @author tangyh
 *  2014年8月19日 下午11:41:24
 */
public interface UserService {
	void save(User user);
	void update(User user);
	User getUserById(Long id);
	void delete(User user);
	List<User> findAllByNoDel();
	/**
	 * 根据登录名和密码查找用户
	 * @param user
	 * @return
	 */
	User findAdminByPwd(User user);
}
