package com.struggle.obs.formbean;

import java.util.Date;

/**
 * 帖子表单对象
 * 
 * @author tangyh 2014年8月19日 下午3:22:25
 */
public class TopicFrom {
	/**
	 * ip
	 */
	private String ip;
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 升降序 true升序 ，false降序
	 */
	private boolean asc = false;
	/**
	 * 版块id
	 */
	private Long forumId;
	
	/**
	 * 分类id
	 */
	private Long typeId;
	/**
	 * 主题id
	 */
	private Long themeId;
	/**
	 * newest:最新/updateDate Desc<br>
	 * hot:人气/回复+查看数量 DESC<br>
	 * addDate :AddDate DESC<br>
	 * look:查看 DESC<br>
	 * 
	 */
	private String orderBy;
	/**
	 * 摘要<br>
	 * 0:普通贴<br>
	 * -1：精华贴<br>
	 * 1：本版置顶贴<br>
	 * 2：分类置顶贴<br>
	 * 3：全局置顶<br>
	 * 10:全部
	 */
	private int digest = 10;
	/**
	 * 开始时间
	 */
	private Date startDate;
	/**
	 * 结束时间
	 */
	private Date endDate;

	/**
	 * 分类id
	 * 
	 * @return
	 */
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * @param 分类id
	 *            typeId
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * 主题id
	 * 
	 * @return
	 */
	public Long getThemeId() {
		return themeId;
	}

	/**
	 * @param 主题id
	 *            themeId
	 */
	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}

	/**
	 * 最新：updateDateDesc<br>
	 * 人气：回复+查看数量DESC<br>
	 * AddDateDESC<br>
	 * 查看DESC<br>
	 * 
	 * @return
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param 最新
	 *            ：updateDateDesc<br>
	 *            人气：回复+查看数量DESC<br>
	 *            AddDateDESC<br>
	 *            查看DESC<br>
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 摘要:<br>
	 * 0:普通贴<br>
	 * -1：精华贴<br>
	 * 1：本版置顶贴<br>
	 * 2：分类置顶贴<br>
	 * 3：全局置顶贴<br>
	 * 10:全部
	 * @return
	 */
	public int getDigest() {
		return digest;
	}

	/**
	 * @param 摘要
	 *            :<br>
	 *            0:普通贴<br>
	 *            -1：精华贴<br>
	 *            1：本版置顶贴<br>
	 *            2：分类置顶贴<br>
	 *            3：全局置顶贴<br>
	 *            10:全部
	 */
	public void setDigest(int digest) {
		this.digest = digest;
	}

	/**
	 * 开始时间
	 * 
	 * @return
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param 开始时间
	 *            startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 结束时间
	 * 
	 * @return
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param 结束时间
	 *            endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 版块id 
	 * @return 
	 */
	public Long getForumId() {
		return forumId;
	}

	/**
	 * @param 版块id forumId
	 */
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	/**
	 * 升降序true升序，false降序 
	 * @return 
	 */
	public boolean isAsc() {
		return asc;
	}

	/**
	 * @param 升降序true升序，false降序 asc
	 */
	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	/**
	 * ip 
	 * @return 
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 用户id 
	 * @return 
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param 用户id userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
