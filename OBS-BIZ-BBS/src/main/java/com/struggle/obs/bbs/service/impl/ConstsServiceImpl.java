/**
 * 
 */
package com.struggle.obs.bbs.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.struggle.obs.bbs.dao.ConstsDao;
import com.struggle.obs.bbs.service.ConstsService;
import com.struggle.obs.bean.base.Consts;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.Utils;

/**
 * @author tangyh 2014年8月29日 下午10:26:06
 */
@Service
public class ConstsServiceImpl implements ConstsService {
	private static Log LOGGER = LogFactory.getLog(ConstsServiceImpl.class);
	
	@Autowired
	private ConstsDao constsDao;
	 
	/**
	 * 2014年8月29日 下午10:26:34
	 * 
	 * @see com.struggle.obs.bbs.service.ConstsService#findByRecType(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Consts> findByRecType(String scoreReaon) {
		if(Utils.isEmptyOrNull(scoreReaon)){
			throw new OBSException("查询失败");
		}
		try {
			return constsDao.findByRecType(scoreReaon);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
    		throw new OBSException("查询失败",e);
		}
	}

	/**
	 * 2014年8月29日 下午10:26:34
	 * 
	 * @see com.struggle.obs.bbs.service.ConstsService#save(com.struggle.obs.bean.base.Consts)
	 */
	@Override
	@Transactional
	public void save(Consts consts) {
		if(consts == null){
			throw new OBSException("保存失败");
		}
		try {
			constsDao.save(consts);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
    		throw new OBSException("保存失败",e);
		}
	}

}
