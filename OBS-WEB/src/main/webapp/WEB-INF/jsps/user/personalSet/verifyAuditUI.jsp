<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>认证</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@ include file="/WEB-INF/jsps/public/commons.jspf"%>    
    <script src="${basePath}script/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(function(){
    	var tab = "${verifyTab}";
    	chooseTab(tab);
    	var result = "${result}";
    	if(result == "OK"){
    		alert("修改成功");
    	}
    })
    	function chooseTab(tab){
    		if(tab == 6){
    			//实名认证	
    			$("#tab6").css("display", "block");
    			$("#tab7").css("display", "none");
    			$("#tab8").css("display", "none");
    		}else if(tab == 7){
    			// 大学生认证	
    			$("#tab7").css("display", "block");
    			$("#tab6").css("display", "none");
    			$("#tab8").css("display", "none");
    			
    		}else if(tab == 8){
    			// 学员认证	
    			$("#tab8").css("display", "block");
    			$("#tab6").css("display", "none");
    			$("#tab7").css("display", "none");
    		}
    			
    	}
    	
    </script>
    <style type="text/css">
    	#Title a:HOVER {
			color: red;
		}
    </style>
</head>
<body>

<!-- 标题显示  -->

<div id="Title_bar">
    <div id="Title_bar_Head" style=" " >
        <div id="Title_Head"></div>
        <div id="Title" ><!--页面标题 -->
            <a href="javascript:;" onclick="chooseTab(6);">
            <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/> 实名审核	</a>
        </div>
        <div id="Title_End"></div>
    </div>
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题 -->
             <a href="javascript:;" onclick="chooseTab(7);">
             <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/> 大学生审核	</a>
        </div>
        <div id="Title_End"></div>
    </div>
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题 -->
             <a href="javascript:;" onclick="chooseTab(8);">
             <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/> 学员审核	</a>
        </div>
         <div id="Title_End"></div>
    </div>
</div>

<!-- 实名认证 -->
<div id="tab6" >
<div id="MainArea">
    <s:form action="verifyAuditUI" namespace="/control/user" method="post">
        <div class="ItemBlock_Title1"> <DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${basePath}style/blue/images/item_point.gif" /><span id="tabName">实名认证	</span>   </DIV> 
        </div>
        
        <!--表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
           技术方向<s:select list="directionList" listKey="recValue" listValue="recName" headerKey="" headerValue="不限" name="userForm.directionCode" ></s:select>
            用户名   <s:textfield name="userForm.loginName" cssClass="InputStyle" cssStyle="width:80px;"/>
            性别   <s:select list="#{'': '全部','M':'男','F':'女','S':'保密' }" name="userForm.gender" ></s:select>
            出生地  <s:textfield name="userForm.birthCity" cssClass="InputStyle" cssStyle="width:80px;"/>
             居住地     <s:textfield name="userForm.resideCity" cssClass="InputStyle" cssStyle="width:80px;"/>
              状态<s:select list="auditList" listKey="recValue"  headerKey="" headerValue="不限" listValue="recName" name="userForm.auditStatu"></s:select>
              <input type="submit" value="查询"/>
                <table cellpadding="0" cellspacing="0" class="mainForm">
	                <s:hidden name="model.id" value="%{model.id}"></s:hidden>
	                <s:hidden name="verifyTab" value="6"></s:hidden>
	                <tr height="25">
						<td> 用户名 </td>
						<td> 真实姓名 </td>
						<td> 性别 </td>
						<td> 手机 </td>
						<td> 居住地 </td>
						<td> 出生地 </td>
						<td> qq </td>
						<td> 邮箱 </td>
						<td> 技术方向 </td>
						<td> 审核状态 </td>
						<td> 操作 </td>
					</tr>
	                <s:iterator value="pager.resultList" status="status">
					<tr height="25">
						<td> ${loginName } </td>
						<td> ${realNameVerify.realName } </td>
						<td> 
						<s:if test='realNameVerify.gender=="M"'>男</s:if>
						<s:elseif test='realNameVerify.gender=="F"'>女</s:elseif>
						<s:elseif test='realNameVerify.gender=="S"'>保密</s:elseif>
						</td>
						<td> ${realNameVerify.mobile } </td>
						<td> ${realNameVerify.resideCity } </td>
						<td> ${realNameVerify.birthCity } </td>
						<td> ${realNameVerify.qq } </td>
						<td> ${realNameVerify.email } </td>
						<td> 
							<s:select list="directionList" disabled="true" listKey="recValue" listValue="recName" name="realNameVerify.directionCode" value="realNameVerify.directionCode"></s:select>
						</td>
						<td> 
						<s:if test="realNameVerify.audit==1">未审核</s:if>
						<s:elseif test="realNameVerify.audit==2"><font color="red">已启动</font></s:elseif>
						<s:elseif test="realNameVerify.audit==3">已审核</s:elseif>
						<s:elseif test="realNameVerify.audit==4">已驳回</s:elseif>
						<s:elseif test="realNameVerify.audit==5">已取消</s:elseif>
						</td>
						<td> 
						<!-- 已启动状态 才允许审核或者驳回 -->
						<s:if test="realNameVerify.audit==2">
							<a href="${basePath }control/user/verifyAudit.action?auditStatu=3&realNameVerifyId=${realNameVerify.id}">审核</a> 
							<a href="${basePath }control/user/verifyAudit.action?auditStatu=4&realNameVerifyId=${realNameVerify.id}">驳回</a> 
						</s:if>
						</td>
					</tr>
					</s:iterator>
					<tr>
				<td colspan="11">
					<%@ include file="/WEB-INF/jsps/public/pageView.jspf"%>
				</td>					
					</tr>
					
                </table>
            </div>
        </div>
       
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <a href="javascript:history.go(-1);"><img src="${basePath}style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description">
	验证规则：<br />
	1，已启动状态的才能通过审核.<br />
	2，已启动状态的才能驳回.<br />
</div>
</div>
<!-- 大学生认证	 -->
<div id="tab7" style="display: none;">
<div id="MainArea_2" >
   <s:form action="" namespace="/control/user" method="post">
        <div class="ItemBlock_Title1"> <DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${basePath}style/blue/images/item_point.gif" /><span id="tabName">大学生认证	</span>   </DIV> 
        </div>
        
        <!--表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                 
                  <s:hidden name="verifyTab" value="7"></s:hidden>
                	<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							用户名
						</td>
						<td></td>
					</tr>
					
					
                </table>
            </div>
        </div>
       
        <!-- 表单操作 -->
        <div id="InputDetailBar_2">
            <a href="javascript:;" onclick="document.forms[1].submit();"><img src="${basePath}style/images/save.png"/></a>
            <a href="javascript:history.go(-1);"><img src="${basePath}style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description_2">
</div>
</div>
<!-- 学员认证	 -->
<div id="tab8" style="display: none;">
<div id="MainArea_3" >
    <s:form action="" namespace="/control/user" method="post">
        <div class="ItemBlock_Title1"> <DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${basePath}style/blue/images/item_point.gif" /><span id="tabName">学员认证</span>   </DIV> 
        </div>
        
        <!--表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                  <s:hidden name="verifyTab" value="8"></s:hidden>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							用户名
						</td>
						<td></td>
					</tr>
					
                </table>
            </div>
        </div>
       
        <!-- 表单操作 -->
        <div id="InputDetailBar_3">
            <a href="javascript:;" onclick="document.forms[2].submit();"><img src="${basePath}style/images/save.png"/></a>
            <a href="javascript:history.go(-1);"><img src="${basePath}style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description_3">
</div>
</div>
</body>
</html>
