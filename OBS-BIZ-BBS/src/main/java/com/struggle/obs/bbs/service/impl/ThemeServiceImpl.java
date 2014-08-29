package com.struggle.obs.bbs.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.struggle.obs.bbs.dao.ThemeDao;
import com.struggle.obs.bbs.service.ThemeService;
import com.struggle.obs.bean.bbs.Theme;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;

/**
 * @author tangyh 2014年8月19日 下午11:38:12
 */
@Service
public class ThemeServiceImpl implements ThemeService {
	@Autowired
	private ThemeDao themeDao;
	private static Log LOGGER = LogFactory.getLog(ThemeServiceImpl.class);

	@Override
	public List<Theme> findAllNoDel(Long forumId) {
		if(forumId == null){
			throw new OBSException("查询分类失败");
		}
		try {
			return themeDao.findByForumId(forumId, ConstantDefine.NO_DEL_FLAG);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询主题失败", e);
		}
	}
}
