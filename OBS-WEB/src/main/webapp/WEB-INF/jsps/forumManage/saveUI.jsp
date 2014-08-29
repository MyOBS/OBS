<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>版块设置</title>
    <%@ include file="/WEB-INF/jsps/public/commons.jspf" %>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/> 版块设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">

    <s:form action="%{id == null ? 'save' : 'update'}" method="post">
    	<s:hidden name="id"></s:hidden>   	
    	<s:if test="id == null">
    	<!-- 保存才显示这个字段 -->
    		<s:hidden name="parentId"></s:hidden>   	
    	</s:if>
        <div class="ItemBlock_Title1"> 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${basePath}style/blue/images/item_point.gif" /> 版块信息 </DIV>
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">版块名称</td>
                        <td><s:textfield name="model.name" cssClass="InputStyle" /> *</td>
                    </tr>
                    <s:if test="id != null && parentId!=null">
                    <!-- 修改才显示这个字段 -->
	                    <tr>
	                        <td width="100">父板块</td>
	                        <td>
	                        	<s:select list="forumList" listKey="id" listValue="name" name="parentId"></s:select>
	                        </td>
	                    </tr>
                    </s:if>
                    <tr>
                        <td>版块说明</td>
                        <td><s:textarea name="model.description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <a href="javascript:document.forms[0].submit();"><img src="${basePath}style/images/save.png"/></a>
            <a href="javascript:history.go(-1);"><img src="${basePath}style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description">
	说明：<br />
	1，新添加的版块默认显示在最下面。<br />
</div>

</body>
</html>
