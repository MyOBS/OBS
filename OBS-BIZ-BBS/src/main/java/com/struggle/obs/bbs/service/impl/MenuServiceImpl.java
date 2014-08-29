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
 * @author tangyh
 *  2014年8月19日 下午11:38:23
 */
@Service
public class MenuServiceImpl implements MenuService{
	
	private static Log LOGGER = LogFactory.getLog(MenuServiceImpl.class);
    
    @Autowired
    private MenuDao menuDao;

    @Override
    @Transactional(readOnly=true)
    public List<Menu> findTopList() {
    	LOGGER.debug("执行MenuServiceImpl.findTopList()");
    	try{
    		return menuDao.findTopList();
    	}catch(HibernateException e){
    		throw new OBSException("查询失败",e);
    	}
    }

    @Override
    @Transactional(readOnly=true)
    public Collection<String> getAllUrls() {
    	LOGGER.debug("执行MenuServiceImpl.getAllUrls()");
    	try{
    		return menuDao.getAllUrls();
    	}catch(HibernateException e){
    		throw new OBSException("查询失败",e);
    	}
    }

}
