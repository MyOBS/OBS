package com.struggle.obs.bbs.dao;

import java.util.List;

import com.struggle.obs.bean.bbs.Forum;
import com.struggle.obs.syscom.dao.GenericDao;

/**
 * @author tangyh
 *  2014年8月19日 下午11:42:07
 */
public interface ForumDao extends GenericDao<Forum, Long> {

	/**
	 * 根据自己的位置查找另一个板块<br>
	 * isUp=true表示查找上一个板块，isUp为false代表查找下一个板块
	 * 
	 * @param position
	 *            当前板块的位置
	 * @param isUp
	 *            true代表向上移动 ，false代表向下移动
	 * @param parentId
	 *            父id
	 * 
	 * @return 上一个或者下一个板块
	 */
	Forum findByPosition(Integer position, boolean isUp, Long parentId);

	/**
	 * 查询顶层板块 <br>
	 * deleteFlag="N" 返回未删除版块<br>
	 * deleteFlag="D" 返回已删除版块
	 * 
	 * @param deleteFlag
	 *            删除标记
	 * @return 顶层板块
	 */
	List<Forum> findTopList(String deleteFlag);

	/**
	 * 根据父Id查找其子类板块 <br>
	 * deleteFlag="N" 返回未删除版块 <br>
	 * deleteFlag="D" 返回已删除版块
	 * 
	 * @param parentId
	 *            父id
	 * @param deleteFlag
	 *            删除标记
	 * @return
	 */
	List<Forum> findForum(Long parentId, String deleteFlag);

	/**
	 * 2014年8月23日 上午9:40:59
	 * @param stat  
	 * <ul>
	 * 	<li>1:查询今日帖子数量</li>
	 * 	<li>2:查询总帖子数量</li>
	 * </ul>
	 * @return Integer
	 */
	Integer getCount(int stat);

}
