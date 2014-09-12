<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>修改头像</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@ include file="/WEB-INF/jsps/public/commons.jspf"%>    
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/> 个人信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--  -->
<div id=MainArea>
    <form action="${basePath}control/user/updateHeadPhoto.action" enctype="multipart/form-data" method="post">
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${basePath}style/blue/images/item_point.gif" />修改头像</DIV> 
        </div>
        <s:hidden name="model.id"/>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
        	
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr>
                        <td width="150">用户</td>
                        <td>${model.loginName }</td>
						<td rowspan="5" align="right">
							 <img alt="头像" src="${basePath }${sessionScope.user.getHeadPhoto120FullPath() }">
							 <s:if test='result == "FAIL"'>
								 <span style="color: red;">${message }</span>
							 </s:if>
						</td>
                    </tr>
					<tr>
                        <td>更改头像</td>
                        <td><input type="file" name="image" class="InputStyle" style="width: 400px;"/></td>
                    </tr>
                </table>
            </div>
            
        </div>
       
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <a href="javascript:;" onclick="document.forms[0].submit();"><img alt="提交" src="${basePath}style/images/save.png"></a>
            <a href="javascript:history.go(-1);"><img src="${basePath}style/images/goBack.png"/></a>
        </div>
    </form>
</div>

<div class="Description">
	验证规则：<br />
	1，可以修改自已的头像，在右侧是头像的预览。<br />
</div>

</body>
</html>
