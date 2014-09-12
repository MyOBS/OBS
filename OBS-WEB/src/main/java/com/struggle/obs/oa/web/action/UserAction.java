package com.struggle.obs.oa.web.action;

import java.io.File;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.struggle.obs.bean.base.Consts;
import com.struggle.obs.bean.user.User;
import com.struggle.obs.formbean.UserForm;
import com.struggle.obs.oa.web.util.BaseAction;
import com.struggle.obs.oa.web.util.WebUtils;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;
import com.struggle.obs.syscom.util.Utils;

@Controller
@Scope("prototype")
@Namespace("/control/user")
@ParentPackage("OBS-WEB")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 4473632066963634326L;

	private List<User> userList;
	/** 图片 */
	private File image;
	/** 图片名称 */
	private String imageFileName;
	/** 文件类型 */
	private String imageContentType;
	/** 学历集合 */
	private List<Consts> educationList;
	/** 技术方向集合 */
	private List<Consts> directionList;
	/** 审核状态集合 */
	private List<Consts> auditList;
	/** 实名认证id */
	private Long realNameVerifyId;
	/**
	 * 个人资料选项卡标志，默认等于1<br>
	 * 1:基本资料<br>
	 * 2:联系方式<br>
	 * 3:教育情况<br>
	 * 4:工作情况<br>
	 * 5:个人信息<br>
	 */
	private int tab = 1;
	/**
	 * 认证选项卡标志，默认等于6<br>
	 * 6:实名认证<br>
	 * 7:大学生认证<br>
	 * 8:学员认证
	 */
	private int verifyTab = 6;
	/** 用户表单对象 */
	private UserForm userForm;
	/** 审核状态 */
	private String auditStatu;
	private String url;

	/**
	 * 查询 后台管理员
	 */
	@Action(value = "list", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/userList.jsp") })
	public String list() {
		try {
			userList = this.userService.findAllByNoDel();
		} catch (OBSException e) {
			// TODO 日志
			e.printStackTrace();
			e.getMessage();
		}
		return SUCCESS;
	}

	/**
	 * 保存 后台管理员
	 */
	@Action(value = "save", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/userList.jsp") })
	public String save() {
		try {
			this.userService.save(this.model);
		} catch (OBSException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 修改 后台管理员
	 */
	@Action(value = "update", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/userList.jsp") })
	public String update() {
		try {
			this.userService.update(this.model, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除 后台管理员
	 */
	@Action(value = "delete", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/userList.jsp") })
	public String delete() {
		try {
			this.userService.delete(this.model);
		} catch (OBSException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到修改头像<br>
	 * 2014年9月1日 上午12:35:20
	 * 
	 * @return String
	 */
	@Action(value = "updateHeadPhotoUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/updateUserInfoUI.jsp") })
	public String updateHeadPhotoUI() {
		result = null;
		try {
			model = WebUtils.getUser(getRequest());
			// model = userService.getUserById(model.getId());
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 上传图片时，图片超过最大值，会报错<br>
	 * Stacktraces<br>
	 * No result defined for action com.struggle.obs.oa.web.action.UserAction
	 * and result input<br>
	 * 2014年9月5日 下午4:39:48
	 * 
	 * @return String
	 */
	@Action(value = "updateHeadPhoto", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/updateUserInfoUI.jsp") })
	public String updateHeadPhoto() {
		try {
			if (image == null || imageContentType == null
					|| imageFileName == null) {
				throw new OBSException("请选择头像");
			}
			// 0,获取图片后缀，生成图片名称
			String imageName = WebUtils.getTimeStr();
			String ext = WebUtils.getExt(imageFileName);
			String imagePullName = imageName + "." + ext;

			// 1,验证文件大小和格式
			Utils.validateImageSize(image);
			Utils.validateImageType(imageContentType, ext);

			// 2,储存图片名称到数据库
			userService.updateHeadPhoto(model, imagePullName);

			model = userService.getUserById(model.getId());
			// 3,保存图片到服务器硬盘
			WebUtils.saveImageFile(this.getRequest(), image, imagePullName,
					ext, model.getId());
			// 3,将用户信息重新放到session中
			WebUtils.removeUser(getRequest());
			WebUtils.addUser(getRequest(), model);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		} catch (Exception e) {
			result = ConstantDefine.MESSAGE_FAIL;
			message = "修改头像失败";
		}
		return SUCCESS;
	}

	/**
	 * 跳转到个人资料<br>
	 * 2014年9月1日 上午12:35:34
	 * 
	 * @return String
	 */
	@Action(value = "profileUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/profileUI.jsp") })
	public String profileUI() {
		try {
			// 技术方向集合
			directionList = constsService
					.findByRecType(ConstantDefine.EDU_DIRECTION);
			// 学历集合
			educationList = constsService
					.findByRecType(ConstantDefine.EDU_EDUCATION);
			User sessionUser = WebUtils.getUser(getRequest());
			if (sessionUser != null) {
				model = userService.getUserById(sessionUser.getId());
			}
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 1)基本资料<br>
	 * 2014年9月8日 下午12:18:25
	 * 
	 * @return String
	 */
	@Action(value = "baseProfile", results = { @Result(name = SUCCESS, type = "redirectAction", location = "profileUI", params = {
			"tab", "${tab}", "result", "${result}" }) })
	public String baseProfile() {
		result = ConstantDefine.MESSAGE_OK;
		try {
			userService.update(model, tab);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 2)联系方式<br>
	 * 2014年9月8日 下午12:18:25
	 * 
	 * @return String
	 */
	@Action(value = "contactProfile", results = { @Result(name = SUCCESS, type = "redirectAction", location = "profileUI", params = {
			"tab", "${tab}", "result", "${result}" }) })
	public String contactProfile() {
		result = ConstantDefine.MESSAGE_OK;
		try {
			userService.update(model, tab);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 3)教育情况<br>
	 * 2014年9月8日 下午12:18:25
	 * 
	 * @return String
	 */
	@Action(value = "eduProfile", results = { @Result(name = SUCCESS, type = "redirectAction", location = "profileUI", params = {
			"tab", "${tab}", "result", "${result}" }) })
	public String eduProfile() {
		result = ConstantDefine.MESSAGE_OK;
		try {
			userService.update(model, tab);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 4)工作情况<br>
	 * 2014年9月8日 下午12:18:25
	 * 
	 * @return String
	 */
	@Action(value = "workProfile", results = { @Result(name = SUCCESS, type = "redirectAction", location = "profileUI", params = {
			"tab", "${tab}", "result", "${result}" }) })
	public String workProfile() {
		result = ConstantDefine.MESSAGE_OK;
		try {
			userService.update(model, tab);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 5)个人信息<br>
	 * 2014年9月8日 下午12:18:25
	 * 
	 * @return String
	 */
	@Action(value = "infoProfile", results = { @Result(name = SUCCESS, type = "redirectAction", location = "profileUI", params = {
			"tab", "${tab}", "result", "${result}" }) })
	public String infoProfile() {
		result = ConstantDefine.MESSAGE_OK;
		try {
			userService.update(model, tab);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到认证界面<br>
	 * 2014年9月1日 上午12:35:37
	 * 
	 * @return String
	 */
	@Action(value = "verifyUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/verifyUI.jsp") })
	public String verifyUI() {
		try {
			// 技术方向集合
			directionList = constsService
					.findByRecType(ConstantDefine.EDU_DIRECTION);
			User sessionUser = WebUtils.getUser(getRequest());
			if (sessionUser != null) {
				model = userService.getUserById(sessionUser.getId());
			}
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	@Action(value = "verify", results = { @Result(name = SUCCESS, type = "redirectAction", location = "verifyUI", params = {
			"verifyTab", "${verifyTab}", "result", "${result}" }) })
	public String verify() {
		result = ConstantDefine.MESSAGE_OK;
		try {
			userService.update(model, verifyTab);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 认证审核 2014年9月8日 下午8:38:59
	 * 
	 * @return String
	 */
	@Action(value = "verifyAuditUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/verifyAuditUI.jsp") })
	public String verifyAuditUI() {
		try {
			// 根据 技术方向/用户名/性别/qq/出生地/居住地 查找
			pager = userService.findByNoAudit(pageNum, pageSize, pages,
					userForm);
			// 技术方向集合
			directionList = constsService
					.findByRecType(ConstantDefine.EDU_DIRECTION);
			//审核状态
			auditList = constsService
					.findByRecType(ConstantDefine.AUDIT_STATU);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 认证审核 2014年9月8日 下午10:56:35
	 * 
	 * @return String
	 */
	@Action(value = "verifyAudit", results = { @Result(name = SUCCESS, type = "redirectAction", location = "verifyAuditUI", params = {
			"pageNum", "${pageNum}" }),@Result(name = NONE, type = "redirectAction", location = "verifyUI", params = {
					"verifyTab", "${verifyTab}", "result", "${result}" })  })
	public String verifyAudit() {
		try {
			// 审核人
			User auditUser = WebUtils.getUser(getRequest());
			realNameVerifyService.verifyAudit(realNameVerifyId, auditUser, auditStatu);
		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		if("verifyUI".equals(url)){
			result =  ConstantDefine.MESSAGE_OK;
			verifyTab = 6;
			return NONE;
		}
		return SUCCESS;
	}

	/**
	 * 跳转到角色界面<br>
	 * 2014年9月1日 上午12:35:47
	 * 
	 * @return String
	 */
	@Action(value = "setRoleUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/setRoleUI.jsp") })
	public String setRoleUI() {
		try {

		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到隐身筛选<br>
	 * 2014年9月1日 上午12:35:50
	 * 
	 * @return String
	 */
	@Action(value = "privacyUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/privacyUI.jsp") })
	public String privacyUI() {
		try {

		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到密码安全界面<br>
	 * 2014年9月1日 上午12:35:53
	 * 
	 * @return String
	 */
	@Action(value = "passwordUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/passwordUI.jsp") })
	public String passwordUI() {
		try {

		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到访问推广界面<br>
	 * 2014年9月1日 上午12:35:56
	 * 
	 * @return String
	 */
	@Action(value = "promotionUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/promotionUI.jsp") })
	public String promotionUI() {
		try {

		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到QQ绑定界面<br>
	 * 2014年9月1日 上午12:35:56
	 * 
	 * @return String
	 */
	@Action(value = "qqconnectUI", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/personalSet/qqconnectUI.jsp") })
	public String qqconnectUI() {
		try {

		} catch (OBSException e) {
			e.printStackTrace();
			result = ConstantDefine.MESSAGE_FAIL;
			message = e.getErrMsg();
		}
		return SUCCESS;
	}

	// ------------------------------
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * 图片
	 * 
	 * @return
	 */
	public File getImage() {
		return image;
	}

	/**
	 * @param 图片
	 *            image
	 */
	public void setImage(File image) {
		this.image = image;
	}

	/**
	 * 图片名称
	 * 
	 * @return
	 */
	public String getImageFileName() {
		return imageFileName;
	}

	/**
	 * @param 图片名称
	 *            imageFileName
	 */
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	/**
	 * 文件类型
	 * 
	 * @return
	 */
	public String getImageContentType() {
		return imageContentType;
	}

	/**
	 * @param 文件类型
	 *            imageContentType
	 */
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	/**
	 * 学历集合
	 * 
	 * @return
	 */
	public List<Consts> getEducationList() {
		return educationList;
	}

	/**
	 * @param 学历集合
	 *            educationList
	 */
	public void setEducationList(List<Consts> educationList) {
		this.educationList = educationList;
	}

	/**
	 * 技术方向集合
	 * 
	 * @return
	 */
	public List<Consts> getDirectionList() {
		return directionList;
	}

	/**
	 * @param 技术方向集合
	 *            directionList
	 */
	public void setDirectionList(List<Consts> directionList) {
		this.directionList = directionList;
	}

	/**
	 * 个人资料选项卡标志<br>
	 * 1:基本资料<br>
	 * 2:联系方式<br>
	 * 3:教育情况<br>
	 * 4:工作情况<br>
	 * 5:个人信息<br>
	 * 
	 * @return
	 */
	public int getTab() {
		return tab;
	}

	/**
	 * @param 个人资料选项卡标志
	 * <br>
	 *            1:基本资料<br>
	 *            2:联系方式<br>
	 *            3:教育情况<br>
	 *            4:工作情况<br>
	 *            5:个人信息<br>
	 *            tab
	 */
	public void setTab(int tab) {
		this.tab = tab;
	}

	/**
	 * 认证选项卡标志，默认等于6<br>
	 * 6:实名认证<br>
	 * 7:大学生认证<br>
	 * 8:学员认证
	 * 
	 * @return
	 */
	public int getVerifyTab() {
		return verifyTab;
	}

	/**
	 * @param 认证选项卡标志
	 *            ，默认等于6<br>
	 *            6:实名认证<br>
	 *            7:大学生认证<br>
	 *            8:学员认证 verifyTab
	 */
	public void setVerifyTab(int verifyTab) {
		this.verifyTab = verifyTab;
	}

	/**
	 * 用户表单对象
	 * 
	 * @return
	 */
	public UserForm getUserForm() {
		return userForm;
	}

	/**
	 * @param 用户表单对象
	 *            userForm
	 */
	public void setUserForm(UserForm userForm) {
		this.userForm = userForm;
	}

	/**
	 * 实名认证id 
	 * @return 
	 */
	public Long getRealNameVerifyId() {
		return realNameVerifyId;
	}

	/**
	 * @param 实名认证id realNameVerifyId
	 */
	public void setRealNameVerifyId(Long realNameVerifyId) {
		this.realNameVerifyId = realNameVerifyId;
	}

	/**
	 * 审核状态 
	 * @return 
	 */
	public String getAuditStatu() {
		return auditStatu;
	}

	/**
	 * @param 审核状态 auditStatu
	 */
	public void setAuditStatu(String auditStatu) {
		this.auditStatu = auditStatu;
	}

	/**
	 * url 
	 * @return 
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 审核状态集合 
	 * @return 
	 */
	public List<Consts> getAuditList() {
		return auditList;
	}

	/**
	 * @param 审核状态集合 auditList
	 */
	public void setAuditList(List<Consts> auditList) {
		this.auditList = auditList;
	}
	

}
