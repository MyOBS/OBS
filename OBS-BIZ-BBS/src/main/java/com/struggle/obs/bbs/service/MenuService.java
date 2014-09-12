package com.struggle.obs.bbs.service;

import java.util.Collection;
import java.util.List;

import com.struggle.obs.bean.base.Menu;

/**
 * @author tangyh
 *  2014年8月19日 下午11:40:59
 */
public interface MenuService {
    /**
     * 查询所有顶级的菜单
     * @return
     */
    List<Menu> findTopList();
    /**
     * 查询所有菜单对应的URL集合（不重复）
     * @return
     */
    Collection<String> getAllUrls();
	/**
	 * 初始化菜单列表
	 * 2014年9月1日 上午12:10:03 void
	 */
	void initMenuData();
}
