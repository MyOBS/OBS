package com.struggle.obs.bbs.service;

import java.util.List;

import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.CreditLogForm;
import com.struggle.obs.formbean.UserForm;
import com.struggle.obs.syscom.bean.Page;

/**
 * @author tangyh 2014年8月19日 下午11:41:24
 */
public interface UserService {
	void save(User user);

	void update(User user, int tab);

	User getUserById(Long id);

	void delete(User user);

	List<User> findAllByNoDel();

	/**
	 * 根据登录名和密码查找用户
	 * 
	 * @param user
	 * @return
	 */
	User findAdminByPwd(User user);

	/**
	 * 修改用户头像 2014年9月1日 下午10:31:05
	 * 
	 * @param user
	 * @param imageName
	 *            void
	 */
	void updateHeadPhoto(User user, String imageName);

	/**
	 * 查出所有未审核用户 2014年9月8日 下午9:02:49
	 * 
	 * @param userForm
	 * @return List<User>
	 */
	Page<User> findByNoAudit(Integer pageNum, Integer pageSize, Integer pages,
			UserForm userForm);

	/**
	 * 用户积分兑换
	 * 2014年9月12日 上午10:45:14
	 * @param user 兑换用户
	 * @param creditLogForm 参数bean
	 */
	void exchangeCredit(User user, CreditLogForm creditLogForm);
}
