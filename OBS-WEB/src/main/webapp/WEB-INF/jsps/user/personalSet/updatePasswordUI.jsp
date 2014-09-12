<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>密码安全</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <%@ include file="/WEB-INF/jsps/public/commons.jspf"%>    
    <script type="text/javascript">
    </script>
</head>
<body>

<!-- 标题显示  -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题 -->
            <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/>  修改密码
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="${basePath}System_User/list.html">
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${basePath}style/blue/images/item_point.gif" />修改密码   </DIV>  -->
        </div>
        
        <!--表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr height="50">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							请输入原密码
						</td>
						<td><input type="password" name="oldPassword" class="InputStyle" /> *</td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							请填写新密码
						</td>
						<td><input type="password" name="password" class="InputStyle" /> *</td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${basePath}style/blue/images/item_point.gif" />
							再次输入新密码
						</td>
						<td><input type="password" name="password2" class="InputStyle" /></td>
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
    </form>
</div>

<div class="Description">
	验证规则：<br />
	1，旧密码不能为空。<br />
	2，新密码不能为空。<br />
	3，再次输入的密码要和新密码一致。<br />
</div>

</body>
</html>
