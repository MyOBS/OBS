package com.struggle.obs.bbs.service;

import java.util.List;

import com.struggle.obs.bean.bbs.Type;

/**
 * @author tangyh
 *  2014年8月19日 下午11:41:15
 */
public interface TypeService {

	List<Type> findAllNoDel(Long forumId);
	
	void resetTypeData();}
