/**
 * 
 */
package com.struggle.obs.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.BaseProfileDao;
import com.struggle.obs.bean.user.BaseProfile;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;

/**
 * @author tangyh 2014年9月8日 下午2:29:33
 */
@Repository
public class BaseProfileDaoImpl extends GenericDaoImpl<BaseProfile, Long>
		implements BaseProfileDao {


}
