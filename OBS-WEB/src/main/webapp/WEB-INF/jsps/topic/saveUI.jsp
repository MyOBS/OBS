<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>发表新主题</title>
    <%@ include file="/WEB-INF/jsps/public/commons.jspf" %>
	<link type="text/css" rel="stylesheet" href="${basePath}style/blue/forum.css" />
	<script type="text/javascript" src="${basePath}fckeditor/fckeditor.js" charset="utf-8"></script>
    <script type="text/javascript">
		$(function(){
			var fck = new FCKeditor("content");
			fck.Width = "99%";
			fck.Height = "100%";
			fck.ToolbarSet = "bbs";
			fck.BasePath = "${basePath}fckeditor/";
			//fck.Config['SkinPath'] = "${basePath}scriipt/fckeditoreditor/skins/office2003/";
			//fck.Config['SkinPath'] = "http://bbs.itcast.cn:80/widgets/fckeditor/editor/skins/office2003/";
			fck.ReplaceTextarea();
		});
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/> 发表新主题
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">

	<s:form action="save" cssStyle="margin: 0; padding: 0;">
		<s:hidden name="topicFrom.forumId"></s:hidden>
		
		<div id="PageHead"></div>
		<center>
			<div class="ItemBlock_Title1">
				<div width=85% style="float:left">
					<font class="MenuPoint"> &gt; </font>
					<a href="${basePath }control/bbs/forum/list.action">论坛</a>
					<font class="MenuPoint"> &gt; </font>
					<a href="${basePath }control/bbs/forum/forum.action?id=${forumBean.parent.id}">${forumBean.parent.name }</a>
					<font class="MenuPoint"> &gt; </font>
					<s:a action="list.action?topicFrom.forumId=%{forumBean.id}">${forumBean.name}</s:a>
					<font class="MenuPoint"> &gt; </font>
					发表新主题
				</div>
			</div>
			<div class="ItemBlockBorder">
				<table border="0" cellspacing="1" cellpadding="1" width="100%" id="InputArea">
					<tr>
						<td class="InputAreaBg" height="30" width="50px"><div class="InputTitle">分类</div></td>
						<td class="InputAreaBg"><div class="InputContent">
						<s:if test="typeList!=null">
							<s:select list="typeList" listKey="id" listValue="name" name="topicFrom.typeId" headerKey="" headerValue="请选择主题分类"></s:select>
						</s:if>
						</div></td>
					</tr>
					<tr>
						<td class="InputAreaBg" height="30" width="50px"><div class="InputTitle">主题</div></td>
						<td class="InputAreaBg"><div class="InputContent">
						<s:if test="themeList!=null">
							<s:select list="themeList" listKey="id" listValue="name" name="topicFrom.themeId" headerKey="" headerValue="请选择主题分类"></s:select>
						</s:if>
						</div></td>
					</tr>
					<tr>
						<td class="InputAreaBg" height="30" width="50px"><div class="InputTitle">标题</div></td>
						<td class="InputAreaBg"><div class="InputContent">
							<s:textfield name="model.title" cssClass="InputStyle" cssStyle="width:100%"/></div>
						</td>
					</tr>
					<tr height="240">
						<td class="InputAreaBg"><div class="InputTitle">内容</div></td>
						<td class="InputAreaBg">
							<div class="InputContent">
								<s:textarea name="content" value="%{model.title}" cssStyle="width:650px;height:200px"></s:textarea>
							</div>
						</td>
						
					</tr>
					<tr height="30">
						<td class="InputAreaBg" colspan="2" align="center">
							
							<a href="javascript:document.forms[0].submit();"><img src="${basePath}style/blue/images/button/submit.PNG"/></a>
							<a href="javascript:history.go(-1);"><img src="${basePath}style/blue/images/button/goBack.png"/></a>
						</td>
					</tr>
				</table>
			</div>
		</center>
	</s:form> 
</div>

<div class="Description">
	说明：<br />
	
</div>

</body>
</html>
