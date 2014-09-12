package com.struggle.obs.syscom.util;

/**
 * 常量类
 * @author tangyh
 *  2014年8月29日 下午8:38:03
 */
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
	
	/** 评分理由：用于常量表的recType */
	public static final String SCORE_REAON = "score_reaon";
	/** 技术方向：用于常量表的recType */
	public static final String EDU_DIRECTION = "edu_direction";
	/** 学历：用于常量表的recType */
	public static final String EDU_EDUCATION = "edu_education";
	/** 审核状态：用于常量表的recType */
	public static final String AUDIT_STATU = "audit_statu";
	/** 积分：用于常量表的recType */
	public static final String CREDIT = "credit";
	/** 支持 */
	public static final String SUPPORT = "support";
	/** 反对 */
	public static final String AGAINST = "against";
	/**
	 * 审核状态:未审核<br>
	 * 1:未审核：用户保存实名认证信息<br>
	 * 2:已启动：用户点击实名认证审核申请<br>
	 * 3:已审核：管理员确认审核通过<br>
	 * 4:已驳回：管理员驳回用户审核<br>
	 * 5:已取消：用户启动后，可以取消审核
	 */
	public static final String UNAUDITED = "1";
	/**
	 * 审核状态:已启动<br>
	 * 1:未审核：用户保存实名认证信息<br>
	 * 2:已启动：用户点击实名认证审核申请<br>
	 * 3:已审核：管理员确认审核通过<br>
	 * 4:已驳回：管理员驳回用户审核<br>
	 * 5:已取消：用户启动后，可以取消审核
	 */
	public static final String HAS_STARTED = "2";
	/**
	 * 审核状态:已审核<br>
	 * 1:未审核：用户保存实名认证信息<br>
	 * 2:已启动：用户点击实名认证审核申请<br>
	 * 3:已审核：管理员确认审核通过<br>
	 * 4:已驳回：管理员驳回用户审核<br>
	 * 5:已取消：用户启动后，可以取消审核
	 */
	public static final String HAS_AUDITE = "3";
	/**
	 * 审核状态:已驳回<br>
	 * 1:未审核：用户保存实名认证信息<br>
	 * 2:已启动：用户点击实名认证审核申请<br>
	 * 3:已审核：管理员确认审核通过<br>
	 * 4:已驳回：管理员驳回用户审核<br>
	 * 5:已取消：用户启动后，可以取消审核
	 */
	public static final String HAS_DISMISSED = "4";
	/**
	 * 审核状态:已取消<br>
	 * 1:未审核：用户保存实名认证信息<br>
	 * 2:已启动：用户点击实名认证审核申请<br>
	 * 3:已审核：管理员确认审核通过<br>
	 * 4:已驳回：管理员驳回用户审核<br>
	 * 5:已取消：用户启动后，可以取消审核
	 */
	public static final String HAS_CANCELED = "5";

}
