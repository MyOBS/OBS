package com.struggle.obs.bbs.service.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.struggle.obs.bbs.dao.MenuDao;
import com.struggle.obs.bbs.service.MenuService;
import com.struggle.obs.bean.base.Menu;
import com.struggle.obs.syscom.exception.OBSException;

/**
 * @author tangyh 2014年8月19日 下午11:38:23
 */
@Service
public class MenuServiceImpl implements MenuService {

	private static Log LOGGER = LogFactory.getLog(MenuServiceImpl.class);

	@Autowired
	private MenuDao menuDao;

	@Override
	@Transactional(readOnly = true)
	public List<Menu> findTopList() {
		try {
			return menuDao.findTopList();
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询失败", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<String> getAllUrls() {
		try {
			return menuDao.getAllUrls();
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询失败", e);
		}
	}

	/**
	 * 2014年9月1日 上午12:10:32
	 * 
	 * @see com.struggle.obs.bbs.service.MenuService#initMenuData()
	 */
	@Override
	@Transactional
	public void initMenuData() {
		try {
			Menu m1 = new Menu();
			m1.setIcon("Information");
			m1.setName("个人信息");
			menuDao.save(m1);
			
			Menu m2 = new Menu();
			m2.setName("修改头像");
			m2.setUrl("control/user/updateHeadPhotoUI.action");
			m2.setParent(m1);
			menuDao.save(m2);
			
			Menu m3 = new Menu();
			m3.setName("个人资料");
			m3.setUrl("control/user/profile.action");
			m3.setParent(m1);
			menuDao.save(m3);
			
			Menu m4 = new Menu();
			m4.setName("认证");
			m4.setUrl("control/user/verify.action");
			m4.setParent(m1);
			menuDao.save(m4);
			
			Menu m5 = new Menu();
			m5.setName("积分");
			m5.setUrl("control/user/credit.action");
			m5.setParent(m1);
			menuDao.save(m5);
			
			Menu m6 = new Menu();
			m6.setName("角色");
			m6.setUrl("control/user/setRole.action");
			m6.setParent(m1);
			menuDao.save(m6);
			
			Menu m7 = new Menu();
			m7.setName("隐私筛选");
			m7.setUrl("control/user/privacy.action");
			m7.setParent(m1);
			menuDao.save(m7);
			
			Menu m8 = new Menu();
			m8.setName("密码安全");
			m8.setUrl("control/user/password.action");
			m8.setParent(m1);
			menuDao.save(m8);
			
			Menu m9 = new Menu();
			m9.setName("访问推广");
			m9.setUrl("control/user/promotion.action");
			m9.setParent(m1);
			menuDao.save(m9);
			
			Menu m10 = new Menu();
			m10.setName("QQ绑定");
			m10.setUrl("control/user/qqconnect.action");
			m10.setParent(m1);
			menuDao.save(m10);
			
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("初始化菜单失败", e);
		}
	}

}
