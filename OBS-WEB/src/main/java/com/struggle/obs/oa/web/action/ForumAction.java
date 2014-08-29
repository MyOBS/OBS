package com.struggle.obs.oa.web.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.struggle.obs.bean.bbs.Forum;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.ForumFrom;
import com.struggle.obs.oa.web.util.BaseAction;
import com.struggle.obs.oa.web.util.SiteUrl;
import com.struggle.obs.oa.web.util.WebUtils;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;

@Controller
@Scope("prototype")
@Namespace("/control/bbs/forum")
@ParentPackage("OBS-WEB")
public class ForumAction extends BaseAction<Forum> {
	private static final long serialVersionUID = 3226328891129981913L;

	/** 版块集合 */
	private List<Forum> forumList;
	/** 父id */
	private Long parentId;
	/** 是否上移： true为上移， false为下移 */
	private boolean up;
	/** 标识是否为删除管理界面 */
	private boolean deleteUI;
	/** 版块表单对象 */
	private ForumFrom forumFrom;

	/**
	 * 板块管理列表
	 * 
	 * @return
	 */
	@Action(value = "manageList", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/forumManage/list.jsp") })
	public String manageList() {
		try {
			deleteUI = false;
			// forumList = this.forumService.findTopList();
			forumList = this.forumService.findForum(parentId);
			historyUrl = SiteUrl.readUrl("control.bbs.forum.manageList");
		} catch (OBSException e) {
			e.printStackTrace();
			e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到保存
	 * 
	 * @return
	 */
	@Action(value = "saveUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/forumManage/saveUI.jsp") })
	public String saveUI() {
		return SUCCESS;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action(value = "save", results = { @Result(name = SUCCESS, type = "redirectAction", location = "manageList", params = {
			"parentId", "${parentId}" }) })
	public String save() {
		try {
			// 从session中获取登录用户，用作版主
			User moderator = WebUtils.getUser(getRequest());
			if (parentId != null) {
				model.setParent(new Forum(parentId));
			}
			forumService.save(model, moderator);
		} catch (OBSException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到修改
	 */
	@Action(value = "updateUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/forumManage/saveUI.jsp") })
	public String updateUI() {
		if (model != null) {
			model = forumService.findById(model.getId());
			forumList = forumService.findTopList();
		}
		return SUCCESS;
	}

	/**
	 * 修改
	 */
	@Action(value = "update", results = { @Result(name = SUCCESS, type = "redirectAction", location = "manageList", params = {
			"parentId", "${parentId}" }) })
	public String update() {
		try {
			if (parentId != null) {
				model.setParent(new Forum(parentId));
			}
			forumService.update(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除
	 */
	@Action(value = "delete", results = { @Result(name = SUCCESS, type = "redirectAction", location = "manageList", params = {
			"parentId", "${parentId}" }) })
	public String delete() {
		try {
			forumService.delete(model, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 移动
	 */
	@Action(value = "move", results = { @Result(name = SUCCESS, type = "redirectAction", location = "manageList", params = {
			"parentId", "${parentId}" }) })
	public String move() {
		try {
			forumService.move(model, up, parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到删除列表
	 */
	@Action(value = "unDeleteUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/forumManage/list.jsp") })
	public String unDeleteUI() {
		try {
			deleteUI = true;
			forumList = forumService.findAllDel(parentId);

			if (parentId == null) {
				historyUrl = SiteUrl.readUrl("control.bbs.forum.manageList");
			} else {
				historyUrl = SiteUrl.readUrl("control.bbs.forum.unDeleteUI");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 恢复
	 */
	@Action(value = "unDelete", results = { @Result(name = SUCCESS, type = "json", params = {
			"root", "responseJson" }) })
	public String unDelete() {
		try {
			forumService.delete(model, false);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		setRestlt(result, message);
		return SUCCESS;
	}

	// ----------------------------版块----------------------------------------

	/**
	 * 所有板块列表
	 * 2014年8月23日 上午9:02:47
	 * @return String
	 */
	@Action(value = "list", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/forum/forumList.jsp") })
	public String list() {
		try {
			forumFrom = forumService.getForumFrom();
			forumList = this.forumService.findTopList();
		} catch (OBSException e) {
			e.printStackTrace();
			e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 子板块列表
	 * 2014年8月23日 上午9:03:13
	 * @return String
	 */
	@Action(value = "forum", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/forum/list.jsp") })
	public String forum() {
		try {
			model = forumService.findById(model.getId());
		} catch (OBSException e) {
			e.printStackTrace();
			e.getErrMsg();
		}
		return SUCCESS;
	}

	// -------------------------getset-------------------------------------------

	public List<Forum> getForumList() {
		return forumList;
	}

	public void setForumList(List<Forum> forumList) {
		this.forumList = forumList;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDeleteUI() {
		return deleteUI;
	}

	public void setDeleteUI(boolean deleteUI) {
		this.deleteUI = deleteUI;
	}

	/**
	 * 版块表单对象 
	 * @return 
	 */
	public ForumFrom getForumFrom() {
		return forumFrom;
	}

	/**
	 * @param 版块表单对象 forumFrom
	 */
	public void setForumFrom(ForumFrom forumFrom) {
		this.forumFrom = forumFrom;
	}

}
