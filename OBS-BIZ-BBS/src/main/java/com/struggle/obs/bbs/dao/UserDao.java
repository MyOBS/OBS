package com.struggle.obs.bbs.dao;


import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.UserForm;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.dao.GenericDao;

/**
 * 用户Dao
 * @author tangyh
 *  2014年8月19日 下午11:42:35
 */
public interface UserDao extends GenericDao<User, Long>{

	/**
	 * 根据密码查找管理员用户
	 * 2014年8月23日 上午9:42:49
	 * @param user
	 * @return User
	 */
	User findAdminByPwd(User user);

	/**
	 * 用户数量
	 * 2014年8月23日 上午9:42:27
	 * @return Integer
	 */
	Integer getUserCount();

	/**
	 * 查找最新注册的用户
	 * 2014年8月23日 上午9:43:04
	 * @return User
	 */
	User getNewUser();

	/**
	 * 2014年9月8日 下午9:05:39
	 * @param userForm
	 * @return List<User>
	 */
	Page<User> findByNoAudit(Integer pageNum, Integer pageSize,
			Integer pages, UserForm userForm);
}
