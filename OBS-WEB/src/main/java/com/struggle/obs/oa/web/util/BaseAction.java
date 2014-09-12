package com.struggle.obs.oa.web.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.struggle.obs.bbs.service.ConstsService;
import com.struggle.obs.bbs.service.CreditLogService;
import com.struggle.obs.bbs.service.CreditRuleService;
import com.struggle.obs.bbs.service.CritiqueService;
import com.struggle.obs.bbs.service.ForumService;
import com.struggle.obs.bbs.service.RealNameVerifyService;
import com.struggle.obs.bbs.service.ReplyService;
import com.struggle.obs.bbs.service.ScoreService;
import com.struggle.obs.bbs.service.ThemeService;
import com.struggle.obs.bbs.service.TopicService;
import com.struggle.obs.bbs.service.TypeService;
import com.struggle.obs.bbs.service.UserService;
import com.struggle.obs.syscom.bean.Page;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    private static final long serialVersionUID = 2273572500215301931L;

    protected T model;

    @SuppressWarnings("unchecked")
    public BaseAction() {
        try {
            // 通过反射获取model的真实类型
            ParameterizedType pt = (ParameterizedType) this.getClass()
                    .getGenericSuperclass();
            Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
            // 通过反射创建model的实例
            model = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public T getModel() {
        return model;
    }

//    public void setModel(T model) {
//        this.model = model;
//    }


    // ---------------注入Service----------------------
    /** 用户Service */
    @Autowired
    protected UserService userService;
    /** 版块Service */
    @Autowired
    protected ForumService forumService;
    /** 帖子Service */
    @Autowired
    protected TopicService topicService;
    /** 回复Service  */
    @Autowired
    protected ReplyService replyService;
    /** 分类Service */
    @Autowired
    protected TypeService typeService;
    /** 主题Service  */
    @Autowired
    protected ThemeService themeService;
    /** 常量Service */
    @Autowired
    protected ConstsService constsService;
    /** 评分  */
    @Autowired
    protected ScoreService scoreService;
    /**  点评 */
    @Autowired
    protected CritiqueService critiqueService;
    @Autowired
    protected RealNameVerifyService realNameVerifyService;
    @Autowired
    protected CreditLogService creditLogService;
    @Autowired
    protected CreditRuleService creditRuleService;

    // ---------------Action公共字段----------------------

    /**
     * 当前页 defautl=1
     */
    protected Integer pageNum = 1; // 当前页
    /**
     * 每页显示多少条记录 Default=30
     */
    protected Integer pageSize = 5; // 每页显示多少条记录
    /**
     * 每页页码数量 Default=10
     */
    protected Integer pages = 10; //每页页码数量
    protected Page<T> pager;    //分页集合
    /**
     * Default：操作成功
     */
    protected String message = ConstantDefine.OPERATE_SUCCESS;
    /**
     * Default：OK
     */
    protected String result;
    /**
     * 用于存放历史url地址，即返回时的URL地址
     */
    protected String historyUrl;
	/** 返回消息 */
	protected Map<String, Object> responseJson;
    /**
     * 与页面传递数据的josn信息
     */
    protected Map<String, Object> jsonMap;
    
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

	public Map<String, Object> getJsonMap() {
        return this.jsonMap;
    }

    public void setJsonMap(Map<String, Object> jsonMap) {
        this.jsonMap = jsonMap;
    }
    public String getMessage() {
    	return message;
    }
    
    public void setMessage(String message) {
    	this.message = message;
    }
    
    public Map<String, Object> getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(Map<String, Object> responseJson) {
		this.responseJson = responseJson;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public String getHistoryUrl() {
		return historyUrl;
	}

	public void setHistoryUrl(String historyUrl) {
		this.historyUrl = historyUrl;
	}
	
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	
	public Page<T> getPager() {
		return pager;
	}
	public void setPager(Page<T> pager) {
		this.pager = pager;
	}


	/**
	 * @Title: setRestlt
	 * @Description: 设置返回值
	 * @return void 返回类型
	 * @throws
	 */
	protected void setRestlt(String result, String message) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(ConstantDefine.JSONMAP_RESULT, result);
		resultMap.put(ConstantDefine.JSONMAP_MESSAGE, message);
		this.setResponseJson(resultMap);
	}

    // ----------------Web对象获取---------------------


	/**
     * 【获得Request请求对象】
     * 
     * @return（HttpServletRequest对象）
     */
    protected HttpServletRequest getRequest() {
        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            request.setCharacterEncoding(ConstantDefine.CHAR_CODE);
        } catch (UnsupportedEncodingException e) {
            throw new OBSException("getRequest()：获取request失败", e);
        }
        return request;
    }

    /**
     * 【获得Response响应对象】
     * 
     * @return (HttpServletResponse对象)
     */
    protected HttpServletResponse getResponse() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding(ConstantDefine.CHAR_CODE);
        response.setContentType("text/html;charset=" + ConstantDefine.CHAR_CODE);
        return response;
    }

    /**
     * 【获得Session会话对象】
     * 
     * @return（HttpSession对象）
     */
    protected HttpSession getSession() {
        return ServletActionContext.getRequest().getSession();
    }

    /**
     * 设置Request的Attribute函数
     * 
     * @param key
     *            键
     * @param value
     *            值
     */
    protected void setRequestAttribute(String key, Object value) {
        this.getRequest().setAttribute(key, value);
    }

    /**
     * 取得Request的Attribute存储值
     * 
     * @param key
     * @return
     */
    protected Object getRequestAttribute(String key) {
        return this.getRequest().getAttribute(key);
    }

    /**
     * 取得Request的Parameter存储值
     * 
     * @param key
     * @return
     */
    protected String getParameter(String key) {
        return this.getRequest().getParameter(key);
    }

    /**
     * 设置Session的Attribute函数
     * 
     * @param key
     * @param value
     */
    protected void setSessionAttribute(String key, Object value) {
        this.getSession().setAttribute(key, value);
    }

    /**
     * 取得Session的Attribute存储值
     * 
     * @param key
     * @return
     */
    protected Object getSessionAttribute(String key) {
        return this.getSession().getAttribute(key);
    }

    /**
     * 【取得ServletContext对象】
     * 
     * @return
     */
    protected ServletContext getServletContext() {
        return ServletActionContext.getServletContext();
    }

}
