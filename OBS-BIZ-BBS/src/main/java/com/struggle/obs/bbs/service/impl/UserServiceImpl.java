package com.struggle.obs.bbs.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.struggle.obs.bbs.dao.BaseProfileDao;
import com.struggle.obs.bbs.dao.ContactProfileDao;
import com.struggle.obs.bbs.dao.EduProfileDao;
import com.struggle.obs.bbs.dao.InfoProfileDao;
import com.struggle.obs.bbs.dao.RealNameVerifyDao;
import com.struggle.obs.bbs.dao.UserDao;
import com.struggle.obs.bbs.dao.WorkProfileDao;
import com.struggle.obs.bbs.service.UserService;
import com.struggle.obs.bean.credit.UserCredit;
import com.struggle.obs.bean.user.BaseProfile;
import com.struggle.obs.bean.user.ContactProfile;
import com.struggle.obs.bean.user.EduProfile;
import com.struggle.obs.bean.user.InfoProfile;
import com.struggle.obs.bean.user.RealNameVerify;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.bean.user.WorkProfile;
import com.struggle.obs.formbean.CreditLogForm;
import com.struggle.obs.formbean.UserForm;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;
import com.struggle.obs.syscom.util.MD5;
import com.struggle.obs.syscom.util.UserInThreadLocal;
import com.struggle.obs.syscom.util.Utils;

/**
 * @author tangyh 2014年8月19日 下午11:38:42
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	private static Log LOGGER = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private BaseProfileDao baseProfileDao;
	@Autowired
	private ContactProfileDao contactProfileDao;
	@Autowired
	private EduProfileDao eduProfileDao;
	@Autowired
	private WorkProfileDao workProfileDao;
	@Autowired
	private InfoProfileDao infoProfileDao;
	@Autowired
	private RealNameVerifyDao realNameVerifyDao;

	@Transactional(readOnly = true)
	public User getUserById(Long id) {
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
	public void update(User user, int tab) {
		if (user == null || user.getId() == null) {
			throw new OBSException("update(User user):修改的用户不存在或者ID为空");
		}
		try {
			User updateUser = userDao.findByPK(user.getId());
			if (updateUser.getDeleteFlag() == ConstantDefine.DELETE_FLAG
					|| updateUser == null || updateUser.getId() == null) {
				throw new OBSException("你想要修改的用户不存在");
			}
			updateUser.setUpdateUser(UserInThreadLocal.getLoginName());
			if (user.getPassWord() != null
					&& !"".equals(user.getPassWord().trim())) {
				// 密码采用MD5加密
				updateUser.setPassWord(MD5.MD5Encode(user.getPassWord()));
			}
			updateUser.setUpdateDate(new Date());
			boolean isSave = false;
			if (tab == 1 && user.getBaseProfile() != null) {
				// 基础资料
				BaseProfile baseProfile = new BaseProfile();
				if (updateUser.getBaseProfile() != null
						&& updateUser.getBaseProfile().getId() != null) {
					baseProfile = baseProfileDao.findByPK(updateUser
							.getBaseProfile().getId());
				} else {
					isSave = true;
				}
				baseProfile.setBirthCity(user.getBaseProfile().getBirthCity());// 出生地
				baseProfile.setBirthday(user.getBaseProfile().getBirthday());// 生日
				baseProfile.setGender(user.getBaseProfile().getGender());// 性别
				baseProfile.setRealName(user.getBaseProfile().getRealName());// 真实姓名
				baseProfile
						.setResideCity(user.getBaseProfile().getResideCity());// 居住地
				if (isSave) {
					updateUser.setBaseProfile(baseProfile);
				}

			} else if (tab == 2 && user.getContactProfile() != null) {
				// 联系方式
				ContactProfile contactProfile = new ContactProfile();
				if (updateUser.getContactProfile() != null
						&& updateUser.getContactProfile().getId() != null) {
					contactProfile = contactProfileDao.findByPK(updateUser
							.getContactProfile().getId());
				} else {
					isSave = true;
				}
				contactProfile.setEmail(user.getContactProfile().getEmail());
				contactProfile.setMobile(user.getContactProfile().getMobile());
				contactProfile.setQq(user.getContactProfile().getQq());

				if (isSave) {
					updateUser.setContactProfile(contactProfile);
				}
			} else if (tab == 3 && user.getEduProfile() != null) {
				// 教育情况
				EduProfile edu = new EduProfile();
				if (updateUser.getEduProfile() != null
						&& updateUser.getEduProfile().getId() != null) {
					edu = eduProfileDao.findByPK(updateUser.getEduProfile()
							.getId());
				} else {
					isSave = true;
				}
				edu.setDirectionCode(user.getEduProfile().getDirectionCode());
				edu.setDirectionName(user.getEduProfile().getDirectionName());
				edu.setEducationCode(user.getEduProfile().getEducationCode());
				edu.setEducationName(user.getEduProfile().getEducationName());
				edu.setGraduateSchool(user.getEduProfile().getGraduateSchool());

				if (isSave) {
					updateUser.setEduProfile(edu);
				}
			} else if (tab == 4 && user.getWorkProfile() != null) {
				// 工作情况
				WorkProfile workProfile = new WorkProfile();
				if (updateUser.getWorkProfile() != null
						&& updateUser.getWorkProfile().getId() != null) {
					workProfile = workProfileDao.findByPK(updateUser
							.getWorkProfile().getId());
				} else {
					isSave = true;
				}
				workProfile.setCompany(user.getWorkProfile().getCompany());
				workProfile.setLift(user.getWorkProfile().getLift());
				workProfile
						.setOccupation(user.getWorkProfile().getOccupation());
				workProfile.setPosition(user.getWorkProfile().getPosition());
				workProfile.setWage(user.getWorkProfile().getWage());
				if (isSave) {
					updateUser.setWorkProfile(workProfile);
				}
			} else if (tab == 5 && user.getInfoProfile() != null) {
				// 个人信息
				InfoProfile infoProfile = new InfoProfile();
				if (updateUser.getInfoProfile() != null
						&& updateUser.getInfoProfile().getId() != null) {
					infoProfile = infoProfileDao.findByPK(updateUser
							.getInfoProfile().getId());
				} else {
					isSave = true;
				}
				infoProfile.setAddress(user.getInfoProfile().getAddress());
				infoProfile.setBio(user.getInfoProfile().getBio());
				infoProfile.setSite(user.getInfoProfile().getSite());
				infoProfile.setZipcode(user.getInfoProfile().getZipcode());
				if (isSave) {
					updateUser.setInfoProfile(infoProfile);
				}
			} else if (tab == 6 && user.getRealNameVerify() != null) {
				// 实名认证
				RealNameVerify rnVerify = new RealNameVerify();
				if (updateUser.getRealNameVerify() != null
						&& updateUser.getRealNameVerify().getId() != null) {
					rnVerify = realNameVerifyDao.findByPK(updateUser
							.getRealNameVerify().getId());
				} else {
					isSave = true;
				}
				rnVerify.setVerifyTime(new Date());
				rnVerify.setAudit(ConstantDefine.UNAUDITED);
				rnVerify.setAddress(user.getRealNameVerify().getAddress());
				rnVerify.setBirthCity(user.getRealNameVerify().getBirthCity());
				rnVerify.setDirectionCode(user.getRealNameVerify()
						.getDirectionCode());
				rnVerify.setEmail(user.getRealNameVerify().getEmail());
				rnVerify.setGender(user.getRealNameVerify().getGender());
				rnVerify.setMobile(user.getRealNameVerify().getMobile());
				rnVerify.setQq(user.getRealNameVerify().getQq());
				rnVerify.setRealName(user.getRealNameVerify().getRealName());
				rnVerify.setResideCity(user.getRealNameVerify().getResideCity());
				if (isSave) {
					updateUser.setRealNameVerify(rnVerify);
				}
			}
		} catch (HibernateException e) {
			throw new OBSException("修改用户信息失败", e);
		}
	}

	@Override
	@Transactional
	public void delete(User user) {
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
			LOGGER.error(e.getMessage());
			throw new OBSException("删除用户信息失败", e);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public List<User> findAllByNoDel() {
		try {
			return userDao.findAllByNoDel(ConstantDefine.NO_DEL_FLAG);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("删除用户信息失败", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public User findAdminByPwd(User user) {
		if (user == null) {
			return null;
		}
		try {
			// 这里登录的是管理员帐号
			user.setAdmin(true);
			user.setPassWord(MD5.MD5Encode(user.getPassWord()));
			user = userDao.findAdminByPwd(user);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询用户信息失败", e);
		}
		return user;
	}

	/**
	 * 2014年9月1日 下午10:31:34
	 * 
	 * @see com.struggle.obs.bbs.service.UserService#updateHeadPhoto(com.struggle.obs.bean.user.User,
	 *      java.lang.String)
	 */
	@Override
	@Transactional
	public void updateHeadPhoto(User user, String imageName) {
		if (user == null) {
			throw new OBSException("修改头像失败");
		}
		try {
			User updateUser = userDao.findByPK(user.getId());
			if (updateUser == null) {
				throw new OBSException("修改头像失败");
			}
			updateUser.setUpdateUser(UserInThreadLocal.getLoginName());
			updateUser.setHeadPhoto(imageName);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("修改头像失败", e);
		}
	}

	/**
	 * 2014年9月8日 下午9:03:47
	 * 
	 * @see com.struggle.obs.bbs.service.UserService#findByNoAudit(com.struggle.obs.formbean.UserForm)
	 */
	@Transactional(readOnly = true)
	public Page<User> findByNoAudit(Integer pageNum, Integer pageSize,
			Integer pages, UserForm userForm) {
		if (userForm == null) {
			userForm = new UserForm();
		}
		try {
			return userDao.findByNoAudit(pageNum, pageSize, pages, userForm);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询未审核用户信息失败", e);
		}

	}

	/**
	 * 2014年9月12日 上午10:46:03
	 * 
	 * @see com.struggle.obs.bbs.service.UserService#exchangeCredit(com.struggle.obs.bean.user.User,
	 *      com.struggle.obs.formbean.CreditLogForm)
	 */
	@Override
	@Transactional
	public void exchangeCredit(User user, CreditLogForm cLForm) {
		if (user == null || user.getId() == null || cLForm == null) {
			throw new OBSException("兑换失败");
		}
		try {
			User uUser = userDao.findByPK(user.getId());
			
			if (!uUser.getPassWord().equals(
					MD5.MD5Encode(Utils.switchStr(cLForm.getPassword())))) {
				throw new OBSException("密码错误");
			}
			if (uUser != null) {
				UserCredit uCredit = uUser.getUserCredit();
				if (uCredit == null) {
					throw new OBSException("余额不足");
				}
				Long money = Utils.switchInt(uCredit.getMoney());
				Long gold = Utils.switchInt(uCredit.getGold());

				if ("1".equals(cLForm.getExchange())) {
					// 1:用金币兑换金钱; money+ gold-
					Long needGold = Utils.transformGold(cLForm.getStartValue());
					if (gold < needGold) {
						throw new OBSException("金币不足");
					}
					uCredit.setGold(gold - needGold);
					uCredit.setMoney(money
							+ Utils.switchInt(cLForm.getStartValue()));
				} else if ("2".equals(cLForm.getExchange())) {
					// 2： 用金钱兑换金币 money- gold+
					Long needMoney = Utils.transformMoney(cLForm
							.getStartValue());
					if (money < needMoney) {
						throw new OBSException("金钱不足");
					}
					uCredit.setMoney(money - needMoney);
					uCredit.setGold(gold
							+ Utils.switchInt(cLForm.getStartValue()));
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("兑换失败", e);
		}
	}

}
