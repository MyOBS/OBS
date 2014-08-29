package com.struggle.obs.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.UserDao;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;

/**
 * @author tangyh 2014年8月19日 下午11:42:02
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	@Override
	public User findAdminByPwd(User user) {
		return (User) getSession()
				.createQuery(//
						"FROM User u WHERE u.loginName=? and u.passWord=? and u.isAdmin=?")
				//
				.setParameter(0, user.getLoginName())
				.setParameter(1, user.getPassWord())
				.setParameter(2, user.isAdmin()).uniqueResult();
	}

	/**
	 * 2014年8月23日 上午9:59:27
	 * 
	 * @see com.struggle.obs.bbs.dao.UserDao#getUserCount()
	 */
	@Override
	public Integer getUserCount() {
		String hql = "SELECT COUNT(u.id) FROM User u ";
		Long count = (Long) getSession().createQuery(hql).uniqueResult();
		if (count != null) {
			return count.intValue();
		}
		return 0;
	}

	/**
	 * 2014年8月23日 上午9:59:27
	 * 
	 * @see com.struggle.obs.bbs.dao.UserDao#getNewUser()
	 */
	@Override
	public User getNewUser() {
		String hql = "FROM User u ORDER BY u.addDate DESC";
		return (User) getSession().createQuery(hql).setFirstResult(0)
				.setMaxResults(1).uniqueResult();

	}
}
