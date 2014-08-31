/**
 * 
 */
package com.struggle.obs.bbs.service;

import java.util.List;

import com.struggle.obs.bean.base.Consts;

/**
 * @author tangyh
 *  2014年8月29日 下午9:41:27
 */
public interface ConstsService {

	/**
	 * 根据recType类型来查询常量表中数据集合
	 * 2014年8月29日 下午9:41:37
	 * @param scoreReaon
	 * @return List<Consts>
	 */
	List<Consts> findByRecType(String scoreReaon);

	/**
	 * 保存
	 * 2014年8月29日 下午10:25:37
	 * @param c2 void
	 */
	void save(Consts consts);

}
