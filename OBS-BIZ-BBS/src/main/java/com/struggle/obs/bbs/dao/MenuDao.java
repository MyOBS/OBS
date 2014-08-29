package com.struggle.obs.bbs.dao;

import java.util.Collection;
import java.util.List;

import com.struggle.obs.bean.base.Menu;
import com.struggle.obs.syscom.dao.GenericDao;

/**
 * @author tangyh
 *  2014年8月19日 下午11:42:11
 */
public interface MenuDao extends GenericDao<Menu, Long> {

    List<Menu> findTopList();

    Collection<String> getAllUrls();

}
