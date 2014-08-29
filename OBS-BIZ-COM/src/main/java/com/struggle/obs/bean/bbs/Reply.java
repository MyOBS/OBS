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

/**
 * 回复
 * @author tangyh
 *
 */
@Entity
@Table(name="reply")
public class Reply extends Article{
	/** 楼层 */
	private Integer floor;
	/** 支持数 */
	private Integer support;
	/** 反对数 */
	private Integer against;
	/** 所属帖子 */
	private Topic topic;
	/** 父回复 */
	private Reply parent;
	/** 子回复 */
	private Set<Reply> children = new HashSet<Reply>();
	/** 点评 */
	private Set<Critique> critiques = new HashSet<Critique>();
	
	/**
	 * 点评 
	 * @return 
	 */
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="reply",fetch=FetchType.LAZY)
	public Set<Critique> getCritiques() {
		return critiques;
	}
	/**
	 * @param 点评 critiques
	 */
	public void setCritiques(Set<Critique> critiques) {
		this.critiques = critiques;
	}
	/**
	 * 支持数 
	 * @return 
	 */
	@Column(name="support")
	public Integer getSupport() {
		return support;
	}
	/**
	 * @param 支持数 support
	 */
	public void setSupport(Integer support) {
		this.support = support;
	}
	/**
	 * 反对数 
	 * @return 
	 */
	@Column(name="against")
	public Integer getAgainst() {
		return against;
	}
	/**
	 * @param 反对数 against
	 */
	public void setAgainst(Integer against) {
		this.against = against;
	}
	/** 楼层 */
	@Column(name="floor")
	public Integer getFloor() {
		return floor;
	}
	/** 楼层 */
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	/** 所属帖子 */
	@ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name = "topicId")
	public Topic getTopic() {
		return topic;
	}
	/** 所属帖子 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	/** 父回复 */
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="parentId")
	public Reply getParent() {
		return parent;
	}
	/** 父回复 */
	public void setParent(Reply parent) {
		this.parent = parent;
	}
	/** 子回复 */
	@OneToMany(cascade={CascadeType.ALL},mappedBy="parent",fetch=FetchType.LAZY)
	public Set<Reply> getChildren() {
		return children;
	}
	/** 子回复 */
	public void setChildren(Set<Reply> children) {
		this.children = children;
	}
	
}
