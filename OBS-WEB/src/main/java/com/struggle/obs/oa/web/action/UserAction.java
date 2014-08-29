package com.struggle.obs.oa.web.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.struggle.obs.bean.user.User;
import com.struggle.obs.oa.web.util.BaseAction;
import com.struggle.obs.syscom.exception.OBSException;

@Controller
@Scope("prototype")
@Namespace("/control/user")
@ParentPackage("OBS-WEB")
public class UserAction extends BaseAction<User> {

    private static final long serialVersionUID = 4473632066963634326L;
    
    private List<User> userList;
    
    /**
     * 查询 后台管理员
     */
    @Action(value = "list", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsps/user/userList.jsp") })
    public String list() {
        try {
            userList = this.userService.findAllByNoDel();
        } catch (OBSException e) {
        	//TODO 日志
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
            this.userService.update(this.model);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
  
    
    
    //------------------------------
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
    

}
