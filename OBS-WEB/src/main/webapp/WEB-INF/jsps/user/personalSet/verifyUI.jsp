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
            <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/> 实名认证	</a>
        </div>
        <div id="Title_End"></div>
    </div>
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题 -->
             <a href="javascript:;" onclick="chooseTab(7);">
             <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/> 大学生认证	</a>
        </div>
        <div id="Title_End"></div>
    </div>
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题 -->
             <a href="javascript:;" onclick="chooseTab(8);">
             <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/> 学员认证	</a>
        </div>
         <div id="Title_End"></div>
    </div>
</div>

<!-- 实名认证 -->
<div id="tab6" >
<div id="MainArea">
    <s:form action="verify" namespace="/control/user" method="post">
        <div class="ItemBlock_Title1"> <DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${basePath}style/blue/images/item_point.gif" /><span id="tabName">实名认证	</span>   </DIV> 
        </div>
        
        <!--表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                <s:hidden name="model.id" value="%{model.id}"></s:hidden>
                <s:hidden name="verifyTab" value="6"></s:hidden>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							用户名
						</td>
						<td>${model.loginName }</td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							真实姓名
						</td>
						<td><input type="text" name="model.realNameVerify.realName"  value="${model.realNameVerify.realName ==null ? model.baseProfile.realName :model.realNameVerify.realName }" class="InputStyle" />
						 *</td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							性别
						</td>
						<td><s:select list="#{'S':'保密','M':'男','F':'女' }" class="InputStyle" name="model.realNameVerify.gender" value="%{model.realNameVerify.gender==null?model.baseProfile.gender:model.realNameVerify.gender}"></s:select> </td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							手机
						</td>
						<td><input type="text" class="InputStyle" name="model.realNameVerify.mobile" value="${model.realNameVerify.mobile==null ? model.contactProfile.mobile : model.realNameVerify.mobile }" />* </td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							邮寄地址
						</td>
						<td><input type="text" name="model.realNameVerify.address" class="InputStyle" value="${model.realNameVerify.address==null ? model.infoProfile.address : model.realNameVerify.address }" /> </td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							出生地
						</td>
						<td><input type="text" name="model.realNameVerify.birthCity" class="InputStyle" value="${model.realNameVerify.birthCity==null ? model.baseProfile.birthCity : model.realNameVerify.birthCity }" /> </td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							居住地
						</td>
						<td><input type="text" name="model.realNameVerify.resideCity" class="InputStyle" value="${model.realNameVerify.resideCity == null ? model.baseProfile.resideCity : model.realNameVerify.resideCity}" /> </td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							QQ
						</td>
						<td><input type="text" name="model.realNameVerify.qq" class="InputStyle" value="${model.realNameVerify.qq ? model.contactProfile.qq :model.realNameVerify.qq}" /> </td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							Email
						</td>
						<td><input type="text" name="model.realNameVerify.email" class="InputStyle" value="${model.realNameVerify.email ? model.contactProfile.email : model.realNameVerify.email }" /> </td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							技术方向
						</td>
						<td>
						<s:select list="directionList" listKey="recValue" listValue="recName" name="model.realNameVerify.directionCode"
						 value="%{model.realNameVerify.directionCode ==null ? model.eduProfile.directionCode : model.realNameVerify.directionCode}"></s:select>
						</td>
						<td></td>
					</tr>
					
                </table>
            </div>
        </div>
       
        <!-- 表单操作 -->
        <div id="InputDetailBar">
        	<s:if test="model.realNameVerify.audit==1 || model.realNameVerify.audit==5">
	        	<a href="${basePath }control/user/verifyAudit.action?auditStatu=2&realNameVerifyId=${model.realNameVerify.id}&url=verifyUI">申请审核</a>
        	</s:if>
        	<s:if test="model.realNameVerify.audit==2">
	        	<a href="${basePath }control/user/verifyAudit.action?auditStatu=5&realNameVerifyId=${model.realNameVerify.id}&url=verifyUI">取消审核</a>
        	</s:if>
        	<s:if test="model.realNameVerify == null || model.realNameVerify.audit==1">
            <a href="javascript:;" onclick="document.forms[0].submit();"><img src="${basePath}style/images/save.png"/></a>
            </s:if>
            <a href="javascript:history.go(-1);"><img src="${basePath}style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description">
	验证规则：<br />
	1，真实姓名不能为空。<br />
	2，手机不能为空.<br />
	3，申请审核后，内容将不能修改，请认真填写.<br />
	4，申请审核后，若管理员尚未确认，可取消审核.<br />
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
                  <s:hidden name="model.id" value="%{model.id}"></s:hidden>
                  <s:hidden name="verifyTab" value="7"></s:hidden>
                	<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							用户名
						</td>
						<td>${model.loginName }</td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							手机
						</td>
						<td><input type="text" name="model.contactProfile.mobile"  value="${model.contactProfile.mobile }" class="InputStyle" />
						 *</td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							qq
						</td>
						<td><input type="text" name="model.contactProfile.qq"  value="${model.contactProfile.qq }" class="InputStyle" /></td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							电子邮件
						</td>
						<td> <input type="text" name="model.contactProfile.email"  value="${model.contactProfile.email }" class="InputStyle" /></td>
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
                <s:hidden name="model.id" value="%{model.id}"></s:hidden>
                  <s:hidden name="verifyTab" value="8"></s:hidden>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							用户名
						</td>
						<td>${model.loginName }</td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							毕业校院
						</td>
						<td><input type="text" name="model.eduProfile.graduateSchool"  value="${model.eduProfile.graduateSchool }" class="InputStyle" />
						 *</td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							
						</td>
						<td>
						
						</td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							技术方向
						</td>
						<td>
						<s:select list="directionList" listKey="recValue" listValue="recName" name="model.eduProfile.directionCode"
						 value="%{model.eduProfile.directionCode }"></s:select></td>
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
