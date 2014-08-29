package com.struggle.obs.bbs.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.MenuDao;
import com.struggle.obs.bean.base.Menu;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;

/**
 * @author tangyh
 *  2014年8月19日 下午11:41:35
 */
@Repository
@SuppressWarnings("unchecked")
public class MenuDaoImpl extends GenericDaoImpl<Menu, Long> implements MenuDao{

    @Override
    public List<Menu> findTopList() {
        return getSession().createQuery(//
                "FROM Menu p WHERE p.parent IS NULL")//
                .list();
    }

    @Override
    public Collection<String> getAllUrls() {
        return getSession().createQuery(//
                "SELECT DISTINCT p.url FROM Menu p WHERE p.url IS NOT NULL")//
                .list();
    }

}
