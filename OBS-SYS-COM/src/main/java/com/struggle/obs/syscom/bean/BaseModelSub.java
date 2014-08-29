package com.struggle.obs.syscom.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.struggle.obs.syscom.util.ConstantDefine;


@MappedSuperclass
public class BaseModelSub extends BaseModel {
	protected Date updateDate = new Date();
	protected String updateUser;
	protected String addUser;
	protected Date addDate = new Date();
	protected String deleteFlag = ConstantDefine.NO_DEL_FLAG;
	
	
	@Column(name = "DELETE_FLAG", length=1)
	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name = "UPDATE_DATE")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "UPDATE_USER", length=20)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "ADD_USER", length = 20)
	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	@Column(name = "ADD_DATE")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

}
