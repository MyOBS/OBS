package com.struggle.obs.oa.web.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.struggle.obs.bean.bbs.Forum;
import com.struggle.obs.bean.bbs.Theme;
import com.struggle.obs.bean.bbs.Topic;
import com.struggle.obs.bean.bbs.Type;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.TopicFrom;
import com.struggle.obs.oa.web.util.BaseAction;
import com.struggle.obs.oa.web.util.WebUtils;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;

/**
 * @author tangyh 2014年8月18日 下午11:34:14
 */
@Controller
@Scope("prototype")
@Namespace("/control/bbs/topic")
@ParentPackage("OBS-WEB")
public class TopicAction extends BaseAction<Topic> {
	private static final long serialVersionUID = -329328781215382949L;

	// 不能和Topic对象中属性重名，否则会被推到栈底。
	/** 版块 */
	private Forum forumBean;
	/** 板块id */
	private Long forumId;
	/** 帖子表单对象 */
	private TopicFrom topicFrom;
	private List<Forum> forumList;
	/** 分类列表 */
	private List<Type> typeList;
	/** 主题列表 */
	private List<Theme> themeList;

	/**
	 * 帖子列表
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/topic/list.jsp") })
	public String list() {
		try {
			typeList = typeService.findAllNoDel(topicFrom.getForumId());
			themeList = themeService.findAllNoDel(topicFrom.getForumId());
			forumBean = forumService.findById(topicFrom.getForumId());
			pager = topicService.getPageList(pageNum, pageSize, pages,
					topicFrom);
		} catch (NullPointerException e) {
			
		} catch (OBSException e) {
			e.printStackTrace();
			e.getMessage();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到保存
	 * 
	 * @return
	 */
	@Action(value = "saveUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/topic/saveUI.jsp") })
	public String saveUI() {
		if (topicFrom != null) {
			forumBean = forumService.findById(topicFrom.getForumId());
			// 查询出某个板块下的分类和主题
			typeList = typeService.findAllNoDel(topicFrom.getForumId());
			themeList = themeService.findAllNoDel(topicFrom.getForumId());
		}
		return SUCCESS;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action(value = "save", results = { @Result(name = SUCCESS, type = "redirectAction", location = "list", params = {
			"topicFrom.forumId", "${topicFrom.forumId}" }) })
	public String save() {
		try {
			String ip = getRequest().getRemoteAddr();// 当前请求中的IP
			topicFrom.setIp(ip);
			User user = WebUtils.getUser(getRequest());// 获取session中的用户信息
			if (user != null) {
				topicFrom.setUserId(user.getId());
			}
			topicService.save(model, topicFrom);
		} catch (OBSException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到修改
	 */
	@Action(value = "updateUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/topic/saveUI.jsp") })
	public String updateUI() {
		try {
			if (topicFrom != null && model != null) {
				typeList = typeService.findAllNoDel(topicFrom.getForumId());
				model = topicService.findById(model.getId(), false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 修改
	 */
	@Action(value = "update", results = { @Result(name = SUCCESS, type = "redirectAction", location = "list", params = {
			"topicFrom.forumId", "${topicFrom.forumId}" }) })
	public String update() {
		try {
			topicService.update(model, topicFrom.getTypeId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除
	 */
	@Action(value = "delete", results = { @Result(name = SUCCESS, type = "redirectAction", location = "list", params = {
			"topicFrom.forumId", "${topicFrom.forumId}" }) })
	public String delete() {
		try {
			topicService.delete(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value = "moveUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/reply/moveUI.jsp") })
	public String moveUI() {
		message = "";
		forumList = forumService.findAll();
		model = topicService.findById(model.getId(), false);
		return SUCCESS;
	}

	/**
	 * 重定向到其他包下的Action
	 * */
	@Action(value = "move", results = {
			@Result(name = SUCCESS, type = "redirect", location = "/control/bbs/reply/list.action", params = {
					"replyFrom.topicId", "${model.id}" }),
			@Result(name = NONE, location = "moveUI") })
	public String move() {
		try {
			topicService.move(model, forumId);
		} catch (OBSException e) {
			e.printStackTrace();
			message = e.getErrMsg();
			return NONE;
		}
		return SUCCESS;
	}

	/**
	 * 置顶，精华，还原
	 */
	@Action(value = "digest", results = { @Result(name = SUCCESS, type = "json", params = {
			"root", "responseJson" }) })
	public String updateDigest() {
		try {
			topicService.updateDigest(model);
		} catch (NullPointerException e) {
			result = ConstantDefine.MESSAGE_FAIL;
			message = ConstantDefine.OPERATE_FAIL;
		} catch (OBSException e) {
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		setRestlt(result, message);
		return SUCCESS;
	}

	/**
	 * 版块
	 * 
	 * @return
	 */
	public Forum getForumBean() {
		return forumBean;
	}

	/**
	 * @param 版块
	 *            forumBean
	 */
	public void setForumBean(Forum forumBean) {
		this.forumBean = forumBean;
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	/**
	 * topicFrom
	 * 
	 * @return
	 */
	public TopicFrom getTopicFrom() {
		return topicFrom;
	}

	/**
	 * @param topicFrom
	 *            topicFrom
	 */
	public void setTopicFrom(TopicFrom topicFrom) {
		this.topicFrom = topicFrom;
	}

	/**
	 * 分类列表
	 * 
	 * @return
	 */
	public List<Type> getTypeList() {
		return typeList;
	}

	/**
	 * @param 分类列表
	 *            typeList
	 */
	public void setTypeList(List<Type> typeList) {
		this.typeList = typeList;
	}

	/**
	 * 主题列表
	 * 
	 * @return
	 */
	public List<Theme> getThemeList() {
		return themeList;
	}

	/**
	 * @param 主题列表
	 *            themeList
	 */
	public void setThemeList(List<Theme> themeList) {
		this.themeList = themeList;
	}

	/**
	 * forumList
	 * 
	 * @return
	 */
	public List<Forum> getForumList() {
		return forumList;
	}

	/**
	 * @param forumList
	 *            forumList
	 */
	public void setForumList(List<Forum> forumList) {
		this.forumList = forumList;
	}

}
