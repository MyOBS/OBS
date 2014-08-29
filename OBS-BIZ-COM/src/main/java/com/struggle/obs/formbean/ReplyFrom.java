/**
 * 
 */
package com.struggle.obs.formbean;

/**
 * 帖子表单对象
 * 
 * @author tangyh 2014年8月20日 下午9:58:29
 */
public class ReplyFrom {
	/** 排序:默认true 升序 */
	private boolean asc = true;
	/** 作者id */
	private Long userId;
	/** 帖子id */
	private Long topicId;
	/** 父id */
	private Long parentId;
	/** ip */
	private String  ip;
	/** 回复id */
	private Long replyId;


	/**
	 * 回复id 
	 * @return 
	 */
	public Long getReplyId() {
		return replyId;
	}

	/**
	 * @param 回复id replyId
	 */
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	/**
	 * 排序 
	 * @return 
	 */
	public boolean isAsc() {
		return asc;
	}

	/**
	 * @param 排序 asc
	 */
	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	/**
	 * 作者id
	 * 
	 * @return
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param 作者id
	 *            userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ReplyFrom() {
	}
	
	
	
	/**
	 * 帖子id 
	 * @return 
	 */
	public Long getTopicId() {
		return topicId;
	}

	/**
	 * @param 帖子id topicId
	 */
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	/**
	 * 父id 
	 * @return 
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param 父id parentId
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	 * 2014年8月20日 下午11:37:25
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReplyFrom [asc=");
		builder.append(asc);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", topicId=");
		builder.append(topicId);
		builder.append(", parentId=");
		builder.append(parentId);
		builder.append(", ip=");
		builder.append(ip);
		builder.append("]");
		return builder.toString();
	}


}
