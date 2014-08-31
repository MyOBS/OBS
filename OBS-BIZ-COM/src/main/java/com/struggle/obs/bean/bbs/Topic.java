package com.struggle.obs.bean.bbs;

import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 帖子主题
 * 
 * @author tangyh
 * 
 */
@Entity
@Table(name = "topic")
public class Topic extends Article {
	/** 最后回复时间 */
	private Date lastReplyTime;
	/** 摘要
	 * 0:普通贴
	 * -1：精华贴
	 * -2:公告
	 * 1：本版置顶贴
	 * 2：分类置顶贴
	 * 3：全局置顶贴
	 */
	private Integer digest = 0;
	/** 回复数 */
	private Integer replyCount;
	/** 查看数量 */
	private Integer lookCount;
	/** 所属板块 */
	private Forum forum;
	/** 最后回复帖 */
	private Reply lastReply;
	/** 回复 */
	private Set<Reply> replys = new HashSet<Reply>();
	/** 主题 */
	private Theme theme;
	/** 分类 */
	private Type type;
	/** 帖子获得的金钱 */
	private Long money;
	/** 评分 */
	private Set<Score> scores = new HashSet<Score>();
	/** 点评 */
	private Set<Critique> critiques = new HashSet<Critique>();
	/**
	 * 帖子获得的金钱 
	 * @return 
	 */
	@Column(name="money")
	public Long getMoney() {
		return money;
	}
	/**
	 * @param 帖子获得的金钱 money
	 */
	public void setMoney(Long money) {
		this.money = money;
	}
	/**
	 * 评分 
	 * @return 
	 */
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="topic",fetch=FetchType.LAZY)
	@OrderBy("addDate DESC")
	public Set<Score> getScores() {
		return scores;
	}
	/**
	 * @param 评分 scores
	 */
	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}
	
	/**
	 * 点评 
	 * @return 
	 */
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="topic",fetch=FetchType.LAZY)
	@OrderBy("addDate DESC")
	public Set<Critique> getCritiques() {
		return critiques;
	}
	/**
	 * @param 点评 critiques
	 */
	public void setCritiques(Set<Critique> critiques) {
		this.critiques = critiques;
	}
	/** 摘要
	 * 0:普通贴
	 * -1：精华贴
	 * -2:公告
	 * 1：本版置顶贴
	 * 2：分类置顶贴
	 * 3：全局置顶贴
	 */
	@Column(name="digest")
	public Integer getDigest() {
		return digest;
	}
	/** 摘要
	 * 0:普通贴
	 * -1：精华贴
	 * -2:公告
	 * 1：本版置顶贴
	 * 2：分类置顶贴
	 * 3：全局置顶贴
	 */
	public void setDigest(Integer digest) {
		this.digest = digest;
	}
	/** 回复数 */
	@Column(name="replyCount")
	public Integer getReplyCount() {
		return replyCount;
	}
	/** 回复数 */
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}
	
	/**
	 * 查看数量 
	 * @return 
	 */
	@Column(name="lookCount")
	public Integer getLookCount() {
		return lookCount;
	}
	/**
	 * @param 查看数量 lookCount
	 */
	public void setLookCount(Integer lookCount) {
		this.lookCount = lookCount;
	}
	
	/** 所属板块 */
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "forumId")
	public Forum getForum() {
		return forum;
	}
	/** 所属板块 */
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	
	/** 最后回复帖 */
	//optional 属性是定义该关联类是否必须存在,值为 false 时,关联类双方都必 须存在,如果关系被维护端不存在,查询的结果为 null.值为 true 时关系被维护端可以不存在，
	@OneToOne(cascade = CascadeType.REFRESH, optional = true,fetch=FetchType.LAZY)
	@JoinColumn(name = "lastReplyId")
	public Reply getLastReply() {
		return lastReply;
	}
	/** 最后回复帖 */
	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}
	
	/** 回复 */
	//查询帖子时，不需要查询出回复，所以使用LAZY
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="topic",fetch=FetchType.LAZY)
	public Set<Reply> getReplys() {
		return replys;
	}
	/** 回复 */
	public void setReplys(Set<Reply> replys) {
		this.replys = replys;
	}
	/** 主题  */
	@ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name = "themeId")
	public Theme getTheme() {
		return theme;
	}
	/** 主题  */
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	/** 分类  */
	@ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name = "typeId")
	public Type getType() {
		return type;
	}
	/** 分类  */
	public void setType(Type type) {
		this.type = type;
	}
	/**
	 * 最后回复时间 
	 * @return 
	 */
	@Column(name = "lastReplyTime")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getLastReplyTime() {
		return lastReplyTime;
	}
	/**
	 * @param 最后回复时间 lastReplyTime
	 */
	public void setLastReplyTime(Date lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}
	
}
