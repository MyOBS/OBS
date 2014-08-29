package com.struggle.obs.bbs.dao;

import java.util.List;

import com.struggle.obs.bean.bbs.Type;
import com.struggle.obs.syscom.dao.GenericDao;

/**
 * @author tangyh
 *  2014年8月19日 下午11:42:31
 */
public interface TypeDao extends GenericDao<Type, Long>{

	/**
	 * 2014年8月20日 下午9:27:44
	 * @param forumId
	 * @param noDelFlag
	 * @return
	 */
	List<Type> findByForumId(Long forumId, String deleteFlag);

}
