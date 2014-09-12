/**
 * 
 */
package com.struggle.obs.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.InfoProfileDao;
import com.struggle.obs.bean.user.InfoProfile;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;

/**
 * @author tangyh 2014年9月8日 下午2:31:52
 */
@Repository
public class InfoProfileDaoImpl extends GenericDaoImpl<InfoProfile, Long>
		implements InfoProfileDao {

}
