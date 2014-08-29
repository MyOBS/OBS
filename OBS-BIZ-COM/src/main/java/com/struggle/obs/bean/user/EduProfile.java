/**
 * 
 */
package com.struggle.obs.bean.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.struggle.obs.syscom.bean.BaseModel;

/**
 * 教育情况
 * 
 * @author tangyh 2014年8月29日 上午12:10:05
 */
@Entity
public class EduProfile extends BaseModel {
	/** 所属用户 */
	private User user;
	/** 毕业校院 */
	private String graduateSchool;
	/** 学历编码 */
	private String educationCode;
	/** 学历名称 */
	private String educationName;
	/** 技术方向编码：.net,java,c++,c#等， 该值从Consts表中获取 */
	private String directionCode;
	/** 技术方向名称：.net,java,c++,c#等， 该值从Consts表中获取 */
	private String directionName;

	/**
	 * graduateSchool 毕业校院
	 * 
	 * @return
	 */
	@Column(name = "graduateSchool", length = 15)
	public String getGraduateSchool() {
		return graduateSchool;
	}

	/**
	 * @param graduateSchool
	 *            graduateSchool 毕业校院
	 */
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	/**
	 * 学历 编码
	 * 初中，高中，专科，本科，研究生，博士生，该值从Consts表中获取
	 * 
	 * @return
	 */
	@Column(name = "educationCode", length = 2)
	public String getEducationCode() {
		return educationCode;
	}

	/**
	 * @param 学历编码
	 *   初中，高中，专科，本科，研究生，博士生，该值从Consts表中获取
	 */
	public void setEducationCode(String educationCode) {
		this.educationCode = educationCode;
	}
	
	/**
	 * 学历名称 初中，高中，专科，本科，研究生，博士生，该值从Consts表中获取
	 * @return 
	 */
	@Column(name = "educationName", length = 8)
	public String getEducationName() {
		return educationName;
	}

	/**
	 * @param 学历名称 初中，高中，专科，本科，研究生，博士生，该值从Consts表中获取
	 */
	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	/**
	 * 技术方向编码：.net，java，c++，c#等，该值从Consts表中获取
	 * 
	 * @return
	 */
	@Column(name = "directionCode", length = 2)
	public String getDirectionCode() {
		return directionCode;
	}

	/**
	 * @param 技术方向编码
	 *            ：.net，java，c++，c#等，该值从Consts表中获取 directionCode
	 */
	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	/**
	 * 技术方向名称：.net,java,c++,c#等，该值从Consts表中获取
	 * 
	 * @return
	 */
	@Column(name = "directionName", length = 10)
	public String getDirectionName() {
		return directionName;
	}

	/**
	 * @param 技术方向名称
	 *            ：.net,java,c++,c#等，该值从Consts表中获取 directionName
	 */
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}

	/**
	 * 所属用户
	 * 
	 * @return
	 */
	@OneToOne(mappedBy = "eduProfile", optional=true, cascade = CascadeType.REFRESH)
	public User getUser() {
		return user;
	}

	/**
	 * @param 所属用户
	 *            user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public EduProfile() {
	}

}
