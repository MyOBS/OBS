package com.struggle.obs.syscom.exception;

public class OBSException extends RuntimeException{

	private static final long serialVersionUID = 5561001305990407419L;
	private String errMsg;
	/**
	 * OBSException 构造方法
	 * @param errMsg 消息
	 * @param e 异常
	 */
	public OBSException(String errMsg,Throwable e) {
		super(e);
		this.errMsg = errMsg;
	}
	/**
	 * OBSException 构造方法
	 * @param errMsg 消息
	 */
	public OBSException(String errMsg) {
		this.errMsg = errMsg;
	}	
	/**
	 * OBSException 构造方法
	 * @param e 异常
	 */
	public OBSException(Throwable e) {
		super(e);
	}
	public String getErrMsg() {
		return this.errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
