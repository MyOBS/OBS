/**
 * 
 */
package com.struggle.obs.oa.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.struggle.obs.bean.bbs.Reply;
import com.struggle.obs.bean.bbs.Topic;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.ReplyFrom;
import com.struggle.obs.oa.web.util.BaseAction;
import com.struggle.obs.oa.web.util.WebUtils;
import com.struggle.obs.syscom.exception.OBSException;

/**
 * @author tangyh 2014年8月20日 下午9:45:52
 */
@Controller
@Scope("prototype")
@Namespace("/control/bbs/reply")
@ParentPackage("OBS-WEB")
public class ReplyAction extends BaseAction<Reply> {

	private static final long serialVersionUID = 2724556950617414778L;

	/** 帖子 */
	private Topic topicBean;
	/** 帖子id */
	private Long topicId;
	/** 表单 回复对象 */
	private ReplyFrom replyFrom;
	/** 父回复id */
	private Long parentId;
	/** 是否为点击链接产生查看 */
	private boolean look;
	

	/**
	 * 查看帖子
	 */
	@Action(value = "list", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/reply/list.jsp") })
	public String list() {
		try {
			if (replyFrom != null) {
				topicBean = topicService.findById(replyFrom.getTopicId(), look);
				// 分页条件0, 属于某个帖子 1， 只看该作者 2，倒叙浏览
				pager = replyService.getPageList(pageNum, pageSize, pages,
						replyFrom);
			}
		} catch (OBSException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到保存
	 * 
	 * @return
	 */
	@Action(value = "saveUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/reply/saveUI.jsp", params = {
			"replyFrom.topicId", "${replyFrom.topicId}" }) })
	public String saveUI() {
		try {
			if (replyFrom != null) {
				topicBean = topicService.findById(replyFrom.getTopicId(), false);
			}
		} catch (OBSException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action(value = "save", results = { @Result(name = SUCCESS, type = "redirectAction", location = "list", params = {
			"replyFrom.topicId", "${replyFrom.topicId}" }) })
	public String save() {
		try {
			if (replyFrom != null) {
				String ip = getRequest().getRemoteAddr();
				replyFrom.setIp(ip);
				User user = WebUtils.getUser(getRequest());
				if (user != null) {
					replyFrom.setUserId(user.getId());
				}
				replyService.save(model, replyFrom);
			}
		} catch (OBSException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到修改
	 */
	@Action(value = "updateUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/reply/saveUI.jsp") })
	public String updateUI() {
		if (model != null && replyFrom != null) {
			topicBean = topicService.findById(replyFrom.getTopicId(), false);
			model = replyService.findById(model.getId());
		}
		return SUCCESS;
	}

	/**
	 * 修改
	 */
	@Action(value = "update", results = { @Result(name = SUCCESS, type = "redirectAction", location = "list", params = {
			"replyFrom.topicId", "${replyFrom.topicId}" }) })
	public String update() {
		try {
			replyService.update(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除
	 */
	@Action(value = "delete", results = { @Result(name = SUCCESS, type = "redirectAction", location = "list", params = {
			"replyFrom.topicId", "${replyFrom.topicId}" }) })
	public String delete() {
		try {
			replyService.delete(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 帖子
	 * 
	 * @return
	 */
	public Topic getTopicBean() {
		return topicBean;
	}

	/**
	 * @param 帖子
	 *            topicBean
	 */
	public void setTopicBean(Topic topicBean) {
		this.topicBean = topicBean;
	}

	/**
	 * 帖子id
	 * 
	 * @return
	 */
	public Long getTopicId() {
		return topicId;
	}

	/**
	 * @param 帖子id
	 *            topicId
	 */
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	/**
	 * 表单回复对象
	 * 
	 * @return
	 */
	public ReplyFrom getReplyFrom() {
		return replyFrom;
	}

	/**
	 * @param 表单回复对象
	 *            replyFrom
	 */
	public void setReplyFrom(ReplyFrom replyFrom) {
		this.replyFrom = replyFrom;
	}

	/**
	 * 父回复id
	 * 
	 * @return
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param 父回复id
	 *            parentId
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 是否为点击链接产生查看 
	 * @return 
	 */
	public boolean isLook() {
		return look;
	}

	/**
	 * @param 是否为点击链接产生查看 isLook
	 */
	public void setLook(boolean isLook) {
		this.look = isLook;
	}


	
	
}
