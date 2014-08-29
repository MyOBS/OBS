package com.struggle.obs.bean.bbs;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.struggle.obs.bean.user.User;
import com.struggle.obs.syscom.bean.BaseModelSub;

/**
 * 文章
 * @author tangyh
 *
 */
@MappedSuperclass
public class Article extends BaseModelSub {
	/** 标题 */
	protected String title;
	/** 内容 */
	protected String content;
	/** ip */
	protected String ip;
	/** 作者 */
	protected User author;
	
	/** 标题 */
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	/** 标题 */
	public void setTitle(String title) {
		this.title = title;
	}
	/** 内容 */
	@Column(name="content")
	// c3p0 0.9.2-pre7 发布后，这个问题视乎可以解决
//	@Lob
	@org.hibernate.annotations.Type(type="text")
	public String getContent() {
		return content;
	}
	/** 内容 */
	public void setContent(String content) {
		this.content = content;
	}
	/** ip */
	@Column(name="ip")
	public String getIp() {
		return ip;
	}
	/** ip */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/** 作者 */
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "authorId")
	public User getAuthor() {
		return author;
	}
	/** 作者 */
	public void setAuthor(User author) {
		this.author = author;
	}
	
}
