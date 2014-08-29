package com.struggle.obs.bbs.service;

import java.util.List;

import com.struggle.obs.bean.bbs.Forum;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.ForumFrom;

/**
 * @author tangyh 2014年8月19日 下午11:40:53
 */
public interface ForumService {

	/**
	 * 查询顶层板块
	 * 
	 * @return List<Forum>
	 */
	List<Forum> findTopList();

	/**
	 * 查询所有板块 2014年8月20日 下午11:18:44
	 * 
	 * @return List<Forum>
	 */
	List<Forum> findAllByNoDel();

	/**
	 * 保存版块
	 * 
	 * @param forum
	 *            板块
	 * @param moderator
	 *            版主
	 */
	void save(Forum forum, User moderator);

	/**
	 * 修改板块
	 * 
	 * @param forum
	 *            板块 修改
	 */
	void update(Forum forum);

	/**
	 * isDeleteFlag=true 表示执行删除操作<br>
	 * isDeleteFlag=false 表示执行恢复删除操作<br>
	 * 删除时，子类板块一并删除；恢复时，只恢复自己
	 * 
	 * @param forum
	 *            板块 删除
	 * @param 是否为删除
	 * 
	 */
	void delete(Forum forum, boolean isDeleteFlag);

	/**
	 * 移动:isUp=true表示上移，false表示下移
	 * 
	 * @param forum
	 *            需要移动的板块
	 * @param isUp
	 *            是否上移：true表示上移，false表示下移
	 * @param parentId
	 *            父ID
	 */
	void move(Forum forum, boolean isUp, Long parentId);

	/**
	 * 根据父Id查找其子类板块
	 * 
	 * @param parentId
	 *            父ID
	 * @return List<Forum>
	 */
	List<Forum> findForum(Long parentId);

	/**
	 * 根据父id查找已经被删除的板块
	 * 
	 * @param parentId
	 *            父id
	 * @return
	 */
	List<Forum> findAllDel(Long parentId);

	/**
	 * 根据id查找版块
	 * 
	 * @param id
	 * @return
	 */
	Forum findById(Long id);

	/**
	 * 查询出所有板块，并且排好序
	 * 2014年8月22日 上午12:32:24
	 * @return List<Forum>
	 */
	List<Forum> findAll();

	/**
	 * 用于表单显示的获取版块相关数据
	 * 2014年8月22日 下午3:57:16
	 * @return ForumFrom
	 */
	ForumFrom getForumFrom();
	
	/**
	 * 重新计算所有帖子的 回复数，最后回复，最后回复时间 <br>
	 * 2014年8月23日 上午11:28:45 
	 */
	void resetForumData();
}
