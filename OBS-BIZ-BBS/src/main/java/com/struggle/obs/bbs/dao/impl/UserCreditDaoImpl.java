/**
 * 
 */
package com.struggle.obs.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.struggle.obs.bbs.dao.UserCreditDao;
import com.struggle.obs.bean.credit.UserCredit;
import com.struggle.obs.syscom.dao.impl.GenericDaoImpl;

/**
 * @author tangyh 2014年8月29日 下午11:31:23
 */
@Repository
public class UserCreditDaoImpl extends GenericDaoImpl<UserCredit, Long>
		implements UserCreditDao {

}
