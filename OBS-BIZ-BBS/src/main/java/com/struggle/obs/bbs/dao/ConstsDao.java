/**
 * 
 */
package com.struggle.obs.bbs.dao;

import java.util.List;

import com.struggle.obs.bean.base.Consts;
import com.struggle.obs.syscom.dao.GenericDao;

/**
 * @author tangyh
 *  2014年8月29日 下午10:29:50
 */
public interface ConstsDao extends GenericDao<Consts, Long> {

	/**
	 * 2014年8月29日 下午10:50:18
	 * @param scoreReaon
	 * @return List<Consts>
	 */
	List<Consts> findByRecType(String scoreReaon);

}
