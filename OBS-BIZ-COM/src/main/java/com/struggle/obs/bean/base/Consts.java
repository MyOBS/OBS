/**
 * 
 */
package com.struggle.obs.bean.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.struggle.obs.syscom.bean.BaseModel;

/**
 * 常量表
 * @author tangyh
 *  2014年8月27日 下午3:34:09
 */
@Entity
@Table(name="consts")
public class Consts extends BaseModel{
	/** 记录类型 */
	private String recType;
	/** 记录值 */
	private String recValue;
	/** 记录名称 */
	private String recName;
	/** 描述 */
	private String descript;
	/** 顺序号  */
	private int orderId;
	/**
	 * 记录类型 
	 * @return 
	 */
	@Column(name="recType", nullable=false,length=50)
	public String getRecType() {
		return recType;
	}
	/**
	 * @param 记录类型 recType
	 */
	public void setRecType(String recType) {
		this.recType = recType;
	}
	/**
	 * 记录值 
	 * @return 
	 */
	@Column(name="recValue",length=5)
	public String getRecValue() {
		return recValue;
	}
	/**
	 * @param 记录值 recValue
	 */
	public void setRecValue(String recValue) {
		this.recValue = recValue;
	}
	/**
	 * 记录名称 
	 * @return 
	 */
	@Column(name="recName",length=20)
	public String getRecName() {
		return recName;
	}
	/**
	 * @param 记录名称 recName
	 */
	public void setRecName(String recName) {
		this.recName = recName;
	}
	/**
	 * 描述 
	 * @return 
	 */
	@Column(name="descript",length=255)
	public String getDescript() {
		return descript;
	}
	/**
	 * @param 描述 descript
	 */
	public void setDescript(String descript) {
		this.descript = descript;
	}
	/**
	 * 顺序号 
	 * @return 
	 */
	@Column(name="orderId",length=5)
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @param 顺序号 orderId
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Consts() {
	}
	/**
	 * 
	 * @param recType 记录类型
	 * @param recValue 记录值
	 * @param recName 记录名称
	 * @param descript 描述
	 * @param orderId 排序号
	 */
	public Consts(String recType, String recValue, String recName,
			String descript, int orderId) {
		super();
		this.recType = recType;
		this.recValue = recValue;
		this.recName = recName;
		this.descript = descript;
		this.orderId = orderId;
	}
	
}
