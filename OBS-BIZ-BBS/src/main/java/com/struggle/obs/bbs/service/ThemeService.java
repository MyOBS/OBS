package com.struggle.obs.bbs.service;

import java.util.List;

import com.struggle.obs.bean.bbs.Theme;

/**
 * @author tangyh
 *  2014年8月19日 下午11:37:38
 */
public interface ThemeService {
	
	/**
	 * 查找所有未删除的主题
	 * 2014年8月19日 下午11:37:45
	 * @return
	 */
	List<Theme> findAllNoDel(Long forumId);

}
