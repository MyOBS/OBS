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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.struggle.obs.bean.user.User;
import com.struggle.obs.syscom.bean.BaseModelSub;

/**
 * 版块
 * 
 * @author tangyh
 * 
 */
@Entity
@Table(name = "forum")
public class Forum extends BaseModelSub {
	/** 板块名称 */
	private String name;
	/** 描述 */
	private String description;
	/** 位置 */
	private Integer position = 0;
	/** 帖子数量 */
	private Integer topicCount;
	/** 文章数量:帖子+回复 */
	private Integer articleCount;
	/**
	 * 今日主题数: 数据库中需要写一个触发器，每天晚上12点，自动将所有板块表的 今日主题数 置0
	 */
	private Integer todayTopciCount;

	/** 版主 */
	private User moderator;
	/** 父板块 */
	private Forum parent;
	/** 子板块 */
	private Set<Forum> children = new HashSet<Forum>();

	/** 最后发表主题 */
	private Topic lastTopic;
	/** 帖子 */
	private Set<Topic> topics = new HashSet<Topic>();

	/** 主题 */
	private Set<Theme> themes = new HashSet<Theme>();
	/** 分类 */
	private Set<Type> types = new HashSet<Type>();

	public Forum() {
	}

	public Forum(Long id) {
		this.id = id;
	}

	/** 板块名称 */
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	/** 板块名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 描述 */
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	/** 描述 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** 位置 */
	@Column(name = "position")
	public Integer getPosition() {
		return position;
	}

	/** 位置 */
	public void setPosition(Integer position) {
		this.position = position;
	}

	/** 帖子数量 */
	@Column(name = "topicCount")
	public Integer getTopicCount() {
		return topicCount;
	}

	/** 帖子数量 */
	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	/** 文章数量:帖子+回复 */
	@Column(name = "articleCount")
	public Integer getArticleCount() {
		return articleCount;
	}

	/** 文章数量:帖子+回复 */
	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	/** 今日主题数 */
	@Column(name = "todayTopciCount")
	public Integer getTodayTopciCount() {
		return todayTopciCount;
	}

	/** 今日主题数 */
	public void setTodayTopciCount(Integer todayTopciCount) {
		this.todayTopciCount = todayTopciCount;
	}

	/** 版主 */
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "moderator")
	public User getModerator() {
		return moderator;
	}

	/** 版主 */
	public void setModerator(User moderator) {
		this.moderator = moderator;
	}

	/** 最后发表主题 */
	// optional 属性是定义该关联类是否必须存在,值为 false 时,关联类双方都必 须存在,如果关系被维护端不存在,查询的结果为
	// null.值为 true 时关系被维护端可以不存在，
	@OneToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "lastTopicId")
	public Topic getLastTopic() {
		return lastTopic;
	}

	/** 最后发表主题 */
	public void setLastTopic(Topic lastTopic) {
		this.lastTopic = lastTopic;
	}

	/** 父板块 */
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "parentId")
	public Forum getParent() {
		return parent;
	}

	/** 父板块 */
	public void setParent(Forum parent) {
		this.parent = parent;
	}

	/** 子板块 */
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "parent", fetch = FetchType.LAZY)
	public Set<Forum> getChildren() {
		return children;
	}

	/** 子板块 */
	public void setChildren(Set<Forum> children) {
		this.children = children;
	}

	/** 帖子 */
	// 查询板块时，不需要查询出帖子，所以使用LAZY
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "forum", fetch = FetchType.LAZY)
	public Set<Topic> getTopics() {
		return topics;
	}

	/** 帖子 */
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	/** 主题 */
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "forum", fetch = FetchType.LAZY)
	public Set<Theme> getThemes() {
		return themes;
	}

	/** 主题 */
	public void setThemes(Set<Theme> themes) {
		this.themes = themes;
	}

	/** 分类 */
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "forum", fetch = FetchType.LAZY)
	public Set<Type> getTypes() {
		return types;
	}

	/** 分类 */
	public void setTypes(Set<Type> types) {
		this.types = types;
	}

}
