package com.struggle.obs.bbs.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.struggle.obs.bbs.dao.UserDao;
import com.struggle.obs.bbs.service.UserService;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;
import com.struggle.obs.syscom.util.MD5;
import com.struggle.obs.syscom.util.UserInThreadLocal;

/**
 * @author tangyh
 *  2014年8月19日 下午11:38:42
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	private static Log LOGGER = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	public User getUserById(Long id) {
		LOGGER.debug("执行UserServiceImpl.getUserById(Long id)");
		if (id == null || id == 0L) {
			throw new OBSException("用户不存在");
		}
		try {
			return this.userDao.findByPK(id);
		} catch (HibernateException e) {
			throw new OBSException("获取用户信息失败", e);
		}
	}

	@Transactional
	public void save(User user) {
		LOGGER.debug("执行UserServiceImpl.save(User user)");
		if (user == null) {
			throw new OBSException("不能保存空用户");
		}
		try {
			System.out.println("ddd");
			user.setAddUser(UserInThreadLocal.getLoginName());
			// 密码采用MD5加密
			user.setPassWord(MD5.MD5Encode(user.getPassWord()));
			this.userDao.create(user);
		} catch (HibernateException e) {
			throw new OBSException("保存用户信息失败", e);
		}
	}

	@Override
	@Transactional
	public void update(User user) {
		LOGGER.debug("执行UserServiceImpl.update(User user)");
		if (user == null || user.getId() == null) {
			throw new OBSException("update(User user):修改的用户不存在或者ID为空");
		}
		try {
			User updateUser = userDao.findByPK(user.getId());
			if (updateUser.getDeleteFlag() == ConstantDefine.DELETE_FLAG
					|| updateUser == null || updateUser.getId() == null) {
				throw new OBSException("你想要修改的用户不存在");
			}
			updateUser.setLoginName(user.getLoginName());
			updateUser.setUpdateUser(UserInThreadLocal.getLoginName());
			if (user.getPassWord() != null
					&& !"".equals(user.getPassWord().trim())) {
				// 密码采用MD5加密
				updateUser.setPassWord(MD5.MD5Encode(user.getPassWord()));
			}
			updateUser.setUpdateDate(new Date());
		} catch (HibernateException e) {
			throw new OBSException("修改用户信息失败", e);
		}
	}

	@Override
	@Transactional
	public void delete(User user) {
		LOGGER.debug("执行UserServiceImpl.delete(User user)");
		if (user == null || user.getId() == null) {
			throw new OBSException("删除的用户不存在或者ID为空");
		}
		try {
			User deleteUser = userDao.findByPK(user.getId());
			if (deleteUser.getDeleteFlag() == ConstantDefine.DELETE_FLAG
					|| deleteUser == null || deleteUser.getId() == null) {
				throw new OBSException("你想要删除的用户不存在");
			}
			deleteUser.setDeleteFlag(ConstantDefine.DELETE_FLAG);
			deleteUser.setUpdateUser(UserInThreadLocal.getLoginName());
			deleteUser.setUpdateDate(new Date());
		} catch (HibernateException e) {
			throw new OBSException("删除用户信息失败", e);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public List<User> findAllByNoDel() {
		LOGGER.debug("执行UserServiceImpl.findAllByNoDel()");
		try {
			return userDao.findAllByNoDel(ConstantDefine.NO_DEL_FLAG);
		} catch (HibernateException e) {
			throw new OBSException("删除用户信息失败", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public User findAdminByPwd(User user) {
		LOGGER.debug("执行UserServiceImpl.findAdminByPwd(User user)");
		if (user == null) {
			return null;
		}
		try {
			// 这里登录的是管理员帐号
			user.setAdmin(true);
			user.setPassWord(MD5.MD5Encode(user.getPassWord()));
			user = userDao.findAdminByPwd(user);
		} catch (HibernateException e) {
			throw new OBSException("查询用户信息失败", e);
		}
		return user;
	}

}
