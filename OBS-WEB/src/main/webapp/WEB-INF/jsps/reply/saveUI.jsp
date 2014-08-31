<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>帖子回复</title>
    <%@ include file="/WEB-INF/jsps/public/commons.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/fckeditor/fckeditor.js" charset="utf-8"></script>
    <script type="text/javascript">
		$(function(){
			var fck = new FCKeditor("content");
			fck.Width = "99%";
			fck.Height = "100%";
			fck.ToolbarSet = "bbs";
			fck.BasePath = "${basePath}fckeditor/";
			//fck.Config['SkinPath'] = "${pageContext.request.contextPath}/scriipt/fckeditoreditor/skins/office2003/";
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 帖子回复
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
	
	<s:form action="%{model.id == null ? 'save' :'update'}" cssStyle="margin: 0; padding: 0;">
		<s:hidden name="replyFrom.topicId" ></s:hidden>
		<s:hidden name="model.id" ></s:hidden>
		<s:hidden name="replyFrom.parentId" ></s:hidden>
		<s:hidden name="replyFrom.replyId" ></s:hidden>
		
		<div id="PageHead"></div>
		<center>
			<div class="ItemBlock_Title1">
				<div width=85% style="float:left">
					<font class="MenuPoint"> &gt; </font>
					<a href="${basePath }control/bbs/forum/list.action">论坛</a>
					<font class="MenuPoint"> &gt; </font>
					<a href="${basePath }control/bbs/forum/forum.action?id=${topicBean.forum.parent.id}">${topicBean.forum.parent.name }</a>
					<font class="MenuPoint"> &gt; </font>
					<a href="${basePath }control/bbs/topic/list.action?topicFrom.forumId=${topicBean.forum.id}">${topicBean.forum.name }</a>
					<font class="MenuPoint"> &gt; </font>
					<a href="${basePath }control/bbs/reply/list.action?replyFrom.topicId=${topicBean.id}">${topicBean.title }</a>
					<font class="MenuPoint"> &gt; </font>
					帖子回复
				</div>
			</div>
			<div class="ItemBlockBorder">
				<table border="0" cellspacing="1" cellpadding="1" width="100%" id="InputArea">
					<tr>
						<td class="InputAreaBg" height="30" width="80px"><div class="InputTitle">帖子主题</div></td>
						<td class="InputAreaBg"><div class="InputContent">${topicBean.title}</div></td>
					</tr>
					<tr>
						<td class="InputAreaBg" height="30"><div class="InputTitle">标题</div></td>
						<td class="InputAreaBg"><div class="InputContent">
						
							<s:textfield name="model.title" cssClass="InputStyle" cssStyle="width:100%" value="回复：%{replyFrom.parentId!=null ? topicBean.content : topicBean.title}"/></div>
						</td>
					</tr>
					<tr height="240">
						<td class="InputAreaBg"><div class="InputTitle">内容</div></td>
						<td class="InputAreaBg">
							<div class="InputContent">
								<s:textarea name="content" value="%{model.content}" cssStyle="width:650px;height:200px;"></s:textarea>
							</div>
						</td>
					</tr>
					<tr height="30">
						<td class="InputAreaBg" colspan="2" align="center">
							<a href="javascript:document.forms[0].submit();"><img alt="提交" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG"/></a>
							<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/blue/images/button/goBack.png"/></a>
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
