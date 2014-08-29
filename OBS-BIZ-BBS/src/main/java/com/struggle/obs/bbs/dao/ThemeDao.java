package com.struggle.obs.bbs.dao;

import java.util.List;

import com.struggle.obs.bean.bbs.Theme;
import com.struggle.obs.syscom.dao.GenericDao;

/**
 * @author tangyh
 *  2014年8月19日 下午11:42:20
 */
public interface ThemeDao extends GenericDao<Theme, Long>{

	/**
	 * 2014年8月20日 下午9:27:06
	 * @param forumId
	 * @param noDelFlag
	 * @return
	 */
	List<Theme> findByForumId(Long forumId, String deleteFlag);

}
