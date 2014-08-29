package com.struggle.obs.syscom.util;

public class ConstantDefine {
     /**
     * 字符编码： UTF-8
     */
    public static final String CHAR_CODE = "UTF-8";
    /**
     * 删除标记
     */
    public static final String DELETE_FLAG = "D";
    /**
     * 未删除标记
     */
    public static final String NO_DEL_FLAG = "N";
    /**
     * 无参数传递 NO_PARAM
     */
    public static final String NO_PARAM = "No parameter.";
    
    /** session User */
	public static final String SESSION_USER = "user";
	/** 0:普通贴*/
	public static final int TYPE_NORMAL = 0;

	/** 1：精华贴 */
	public static final int TYPE_BEST = 1;

	/** 2：置顶贴 */
	public static final int TYPE_TOP = 2;
	
	public static final String JSONMAP_RESULT = "Result";
	public static final String JSONMAP_MESSAGE = "Message";
	/**
     * action执行成功消息标志
     */
    public static final String MESSAGE_OK = "OK";
   
    /**
	 * Action执行失败消息标志
	 */
	public static final String MESSAGE_FAIL = "FAIL";
    
	/**
	 * Action执行成功消息
	 */
	public static final String OPERATE_SUCCESS = "操作成功";
	
	/**
	 * Action执行失败消息
	 */
	public static final String OPERATE_FAIL = "操作失败";
	
	/**
	 * newest:最新/updateDate Desc<br>
	 */
	public static final String ORDERBY_NEWEST = "newest";
	/** hot:人气/回复+查看数量 DESC<br> */
	public static final String ORDERBY_HOT = "hot";
	/** addDate :AddDate DESC<br> */
	public static final String ORDERBY_ADDDATE = "addDate";
	/** look:查看 DESC<br> */
	public static final String ORDERBY_LOOK = "look";
	/** 普通帖 0 */
	public static final int DIGESET_GENERAL = 0;
	/** 精华帖 -1 */
	public static final int DIGESET_ESSENCE = -1;
	/** 公告 -2 */
	public static final int DIGESET_NOTICE = -2;
	/** 置顶帖 1 */
	public static final int DIGESET_TOP_1 = 1;
	/** 置顶帖 2 */
	public static final int DIGESET_TOP_2 = 2;
	/** 置顶帖 3 */
	public static final int DIGESET_TOP_3 = 3;
	/**
	 * 今日帖子数量
	 */
	public static final int TODAY_TOPIC_COUNT = 1;
	/**
	 * 总帖子数量
	 */
	public static final int TOTAL_TOPIC_COUNT = 2;
	
}
