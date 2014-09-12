/**
 * 
 */
package com.struggle.obs.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.ContactProfileDao;
import com.struggle.obs.bean.user.ContactProfile;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;

/**
 * @author tangyh 2014年9月8日 下午2:30:31
 */
@Repository
public class ContactProfileDaoImpl extends GenericDaoImpl<ContactProfile, Long>
		implements ContactProfileDao {

}
