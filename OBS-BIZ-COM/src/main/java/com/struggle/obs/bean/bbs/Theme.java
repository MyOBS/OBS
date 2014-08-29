package com.struggle.obs.bean.bbs;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.struggle.obs.syscom.bean.BaseModelSub;

/**
 * 主题
 * 投票/悬赏/活动//
 * @author tangyh
 *  2014年8月19日 下午2:39:39
 */
@Entity
@Table(name = "theme")
public class Theme extends BaseModelSub{
	/** 主题名称 */
	private String name;
	/** 版块 */
	private Forum forum;
	/** 帖子 */
	private Set<Topic> topics = new HashSet<Topic>();
	
	public Theme() {
	}
	
	/** 主题名称 */
	@Column(name="name", nullable=false, unique=true)
	public String getName() {
		return name;
	}
	/** 主题名称 */
	public void setName(String name) {
		this.name = name;
	}
	/** 版块 */
	@ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name = "forumId")
	public Forum getForum() {
		return forum;
	}
	/** 版块 */
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	
	/** 帖子 */
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="theme",fetch=FetchType.LAZY)
	public Set<Topic> getTopics() {
		return topics;
	}
	/** 帖子 */
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Theme [name=");
		builder.append(name);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
	
}
