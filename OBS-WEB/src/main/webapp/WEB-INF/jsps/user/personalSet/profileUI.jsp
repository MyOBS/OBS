<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>个人资料</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@ include file="/WEB-INF/jsps/public/commons.jspf"%>    
    <script src="${basePath}script/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(function(){
    	var tab = "${tab}";
    	chooseTab(tab);
    	var result = "${result}";
    	if(result == "OK"){
    		alert("修改成功");
    	}
    })
    	function chooseTab(tab){
    		if(tab == 1){
    			//基本资料
    			$("#tab1").css("display", "block");
    			$("#tab2").css("display", "none");
    			$("#tab3").css("display", "none");
    			$("#tab4").css("display", "none");
    			$("#tab5").css("display", "none");
    		}else if(tab == 2){
    			// 联系方式
    			$("#tab2").css("display", "block");
    			$("#tab1").css("display", "none");
    			$("#tab3").css("display", "none");
    			$("#tab4").css("display", "none");
    			$("#tab5").css("display", "none");
    			
    		}else if(tab == 3){
    			// 教育情况
    			$("#tab3").css("display", "block");
    			$("#tab2").css("display", "none");
    			$("#tab1").css("display", "none");
    			$("#tab4").css("display", "none");
    			$("#tab5").css("display", "none");
    		}else if(tab == 4){
    			//工作情况
    			$("#tab4").css("display", "block");
    			$("#tab2").css("display", "none");
    			$("#tab3").css("display", "none");
    			$("#tab1").css("display", "none");
    			$("#tab5").css("display", "none");
    		}else if(tab == 5){
    			//个人信息
    			$("#tab5").css("display", "block");
    			$("#tab2").css("display", "none");
    			$("#tab3").css("display", "none");
    			$("#tab4").css("display", "none");
    			$("#tab1").css("display", "none");
    			
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
            <a href="javascript:;" onclick="chooseTab(1);">
            <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/> 基本资料</a>
        </div>
        <div id="Title_End"></div>
    </div>
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题 -->
             <a href="javascript:;" onclick="chooseTab(2);">
             <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/>  联系方式</a>
        </div>
        <div id="Title_End"></div>
    </div>
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题 -->
             <a href="javascript:;" onclick="chooseTab(3);">
             <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/>  教育情况</a>
        </div>
         <div id="Title_End"></div>
    </div>
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题 -->
             <a href="javascript:;" onclick="chooseTab(4);">
             <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/>  工作情况</a>
        </div>
       <div id="Title_End"></div>
    </div>
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题 -->
             <a href="javascript:;" onclick="chooseTab(5);">
             <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/>  个人信息</a>
        </div>
         <div id="Title_End"></div>
    </div>
</div>

<!-- 基本资料 -->
<div id="tab1" >
<div id="MainArea">
    <s:form action="baseProfile" namespace="/control/user" method="post">
        <div class="ItemBlock_Title1"> <DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${basePath}style/blue/images/item_point.gif" /><span id="tabName">基本资料</span>   </DIV> 
        </div>
        
        <!--表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                <s:hidden name="model.id" value="%{model.id}"></s:hidden>
                <s:hidden name="tab" value="1"></s:hidden>
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
						<td><input type="text" name="model.baseProfile.realName"  value="${model.baseProfile.realName }" class="InputStyle" />
						 *</td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							性别
						</td>
						<td><s:select list="#{'S':'保密','M':'男','F':'女' }" class="InputStyle" name="model.baseProfile.gender" value="%{model.baseProfile.gender}"></s:select> </td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							生日
						</td>
						<td><input type="text" class="Wdate InputStyle"   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="model.baseProfile.birthday" value="${model.baseProfile.birthday }" /> </td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							出生地
						</td>
						<td><input type="text" name="model.baseProfile.birthCity" class="InputStyle" value="${model.baseProfile.birthCity }" /> </td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							居住地
						</td>
						<td><input type="text" name="model.baseProfile.resideCity" class="InputStyle" value="${model.baseProfile.resideCity }" /> </td>
						<td></td>
					</tr>
                </table>
            </div>
        </div>
       
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <a href="javascript:;" onclick="document.forms[0].submit();"><img src="${basePath}style/images/save.png"/></a>
            <a href="javascript:history.go(-1);"><img src="${basePath}style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description">
	验证规则：<br />
	1，真实姓名不能为空。<br />
	2，生日必须为yyyy-MM-dd格式。<br />
</div>
</div>
<!-- 联系方式 -->
<div id="tab2" style="display: none;">
<div id="MainArea_2" >
   <s:form action="contactProfile" namespace="/control/user" method="post">
        <div class="ItemBlock_Title1"> <DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${basePath}style/blue/images/item_point.gif" /><span id="tabName">联系方式</span>   </DIV> 
        </div>
        
        <!--表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                  <s:hidden name="model.id" value="%{model.id}"></s:hidden>
                  <s:hidden name="tab" value="2"></s:hidden>
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
<!-- 教育情况 -->
<div id="tab3" style="display: none;">
<div id="MainArea_3" >
    <s:form action="eduProfile" namespace="/control/user" method="post">
        <div class="ItemBlock_Title1"> <DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${basePath}style/blue/images/item_point.gif" /><span id="tabName">教育情况</span>   </DIV> 
        </div>
        
        <!--表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                <s:hidden name="model.id" value="%{model.id}"></s:hidden>
                  <s:hidden name="tab" value="3"></s:hidden>
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
							学历
						</td>
						<td>
						<s:select list="educationList" listKey="recValue" listValue="recName" name="model.eduProfile.educationCode" value="%{model.eduProfile.educationCode }"></s:select>
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
<!-- 工作情况 -->
<div id="tab4" style="display: none;">
<div id="MainArea_4" >
    <s:form action="workProfile" namespace="/control/user" method="post">
        <div class="ItemBlock_Title1"> <DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${basePath}style/blue/images/item_point.gif" /><span id="tabName">工作情况</span>   </DIV> 
        </div>
        
        <!--表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                <s:hidden name="model.id" value="%{model.id}"></s:hidden>
                  <s:hidden name="tab" value="4"></s:hidden>
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
							公司
						</td>
						<td><input type="text" name="model.workProfile.company"  value="${model.workProfile.company }" class="InputStyle" />
						 *</td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							职业
						</td>
						<td><input type="text" name="model.workProfile.occupation"  value="${model.workProfile.occupation }" class="InputStyle" /></td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							职位
						</td>
						<td><input type="text" name="model.workProfile.position"  value="${model.workProfile.position }" class="InputStyle" /></td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							年限
						</td>
						<td><input type="text" name="model.workProfile.lift"  value="${model.workProfile.lift }" class="InputStyle" /></td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							月薪
						</td>
						<td><input type="text" name="model.workProfile.wage"  value="${model.workProfile.wage }" class="InputStyle" /></td>
						<td></td>
					</tr>
                </table>
            </div>
        </div>
       
        <!-- 表单操作 -->
        <div id="InputDetailBar_4">
            <a href="javascript:;" onclick="document.forms[3].submit();"><img src="${basePath}style/images/save.png"/></a>
            <a href="javascript:history.go(-1);"><img src="${basePath}style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description_4">
</div>
</div>
<!-- 个人信息 -->
<div id="tab5" style="display: none;">
<div id="MainArea_5" >
  <s:form action="infoProfile" namespace="/control/user" method="post">
        <div class="ItemBlock_Title1"> <DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${basePath}style/blue/images/item_point.gif" /><span id="tabName">个人信息</span>   </DIV> 
        </div>
        
        <!--表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<s:hidden name="model.id" value="%{model.id}"></s:hidden>
                	  <s:hidden name="tab" value="5"></s:hidden>
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
							邮寄地址
						</td>
						<td><input type="text" name="model.infoProfile.address"  value="${model.infoProfile.address }" class="InputStyle" />
						 </td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							邮编
						</td>
						<td><input type="text" name="model.infoProfile.zipcode"  value="${model.infoProfile.zipcode }" class="InputStyle" /> </td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							个人主页
						</td>
						<td><input type="text" name="model.infoProfile.site"  value="${model.infoProfile.site }" class="InputStyle" /> </td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							自我介绍
						</td>
						<td>
						<s:textarea name="model.infoProfile.bio" value="%{model.infoProfile.bio }" cssClass="InputStyle"></s:textarea>
						</td>
						<td></td>
					</tr>
                </table>
            </div>
        </div>
       
        <!-- 表单操作 -->
        <div id="InputDetailBar_5">
            <a href="javascript:;" onclick="document.forms[4].submit();"><img src="${basePath}style/images/save.png"/></a>
            <a href="javascript:history.go(-1);"><img src="${basePath}style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description_5">
</div>
</div>
</body>
</html>
