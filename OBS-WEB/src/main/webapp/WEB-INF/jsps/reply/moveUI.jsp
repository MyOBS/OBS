<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>帖子移动</title>
    <%@ include file="/WEB-INF/jsps/public/commons.jspf" %>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 帖子移动
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
	
	<s:form action="move" cssStyle="margin: 0; padding: 0;">
		<s:hidden name="model.id" ></s:hidden>
		
		<div id="PageHead"></div>
		<center>
			<div class="ItemBlock_Title1">
				<div width=85% style="float:left">
					<font class="MenuPoint"> &gt; </font>
					<a href="${basePath }control/bbs/forum/list.action">论坛</a>
					<font class="MenuPoint"> &gt; </font>
					<a href="${basePath }control/bbs/forum/forum.action?id=${model.forum.parent.id}">${model.forum.parent.name }</a>
					<font class="MenuPoint"> &gt; </font>
					<a href="${basePath }control/bbs/topic/list.action?topicFrom.forumId=${model.forum.id}">${model.forum.name }</a>
					<font class="MenuPoint"> &gt; </font>
					<a href="${basePath }control/bbs/reply/list.action?replyFrom.topicId=${model.id}">${model.title }</a>
					<font class="MenuPoint"> &gt; </font>
					帖子移动
				</div>
			</div>
			<div class="ItemBlockBorder">
				<table border="0" cellspacing="1" cellpadding="1" width="100%" id="InputArea">
					<tr>
						<td class="InputAreaBg" height="30" width="80px"><div class="InputTitle">板块</div></td>
						<td class="InputAreaBg"><div class="InputContent">
						<s:if test="forumList!=null">
							<select name="forumId" >
								<s:iterator value="forumList">
									<s:if test="parent==null">
										<optgroup label="${name }" id="${id }"></optgroup>
									</s:if>
									<s:else>
										<option value="${id }" 
										<s:if test="id==model.forum.id">selected="selected"</s:if>
										>${name }</option>
									</s:else>
								</s:iterator>
							</select>
						</s:if>
						</div>${message } </td>
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
