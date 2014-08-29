<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>【${forumBean.name}】中的主题列表</title>
    <%@ include file="/WEB-INF/jsps/public/commons.jspf" %>
	<link type="text/css" rel="stylesheet" href="${basePath}style/blue/forum.css" />
	<script type="text/javascript">
		function onSortByChange( selectedValue ){
			if(selectedValue == ""){
				$("select[name=asc]").attr("disabled", "disabled");	
			}else{
				$("select[name=asc]").removeAttr("disabled");	
			}
		}

		$(function(){
			if($("select[name=orderBy]").val() == ""){
				$("select[name=asc]").attr("disabled", "disabled");		
			}
		});
		
		function changeTheme(themeId){
			$("#btn_ThemeId").val(themeId);
			$("form:first").submit();
		}
		function changeType(typeId){
			if(typeId==0){
				typeId = "";
			}
			$("#btn_TypeId").val(typeId);
			$("form:first").submit();
		}
	</script>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/> 【${forumBean.name}】中的主题列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<s:form action="list.action">
<s:hidden name="topicFrom.forumId"></s:hidden>
<s:hidden name="topicFrom.typeId" id="btn_TypeId"></s:hidden>
<s:hidden name="topicFrom.themeId" id="btn_ThemeId"></s:hidden>
<div id="MainArea">
	<div id="PageHead"></div>
<!-- 	<center> -->
		<div class="ItemBlock_Title1" style="width: 98%;">
			<font class="MenuPoint"> &gt; </font>
			<a href="${basePath }control/bbs/forum/list.action">论坛</a>
			<font class="MenuPoint"> &gt; </font>
			<a href="${basePath }control/bbs/forum/forum.action?id=${forumBean.parent.id}">${forumBean.parent.name }</a>
			<font class="MenuPoint"> &gt; </font>
			<a href="${basePath }control/bbs/topic/list.action?topicFrom.forumId=${forumBean.id}">${forumBean.name}</a>
			<span style="margin-left:30px;">
				<s:a action="saveUI?topicFrom.forumId=%{forumBean.id}">
					<img align="absmiddle" src="${basePath}style/blue/images/button/publishNewTopic.png"/>
				</s:a>
			</span>
		</div>
		<div>
			<a href="javascript:changeType(0);">全部</a>
			<s:iterator value="typeList">
				<a href="javascript:changeType(${id });">|${ name} [${topicCount }]  </a>
			</s:iterator>
			<s:select list="themeList" listKey="id" listValue="name" headerKey="" headerValue="全部主题" onchange="changeTheme(this.value)" value="topicFrom.themeId"></s:select>
		</div>
		<div class="ForumPageTableBorder" >
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<!--表头-->
				<tr align="center" valign="middle">
					<td width="3" class="ForumPageTableTitleLeft">
						<img border="0" width="1" height="1" src="${basePath}style/images/blank.gif" />
					</td>
					<td width="50" class="ForumPageTableTitle"><!--状态/图标-->&nbsp;</td>
					<td class="ForumPageTableTitle">主题</td>
					<td width="130" class="ForumPageTableTitle">作者</td>
					<td width="100" class="ForumPageTableTitle">回复数/查看</td>
					<td width="100" class="ForumPageTableTitle">最后回复</td>
					<td width="3" class="ForumPageTableTitleRight">
						<img border="0" width="1" height="1" src="${basePath}style/images/blank.gif" />
					</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="8"></td></tr>
				<tr height=3><td colspan=8></td></tr>
					
				<!--主题列表-->
				<tbody class="dataContainer" datakey="topicList">
				<s:iterator value="pager.resultList">
					<tr height="35" id="d0" class="template">
				
						<td></td>
						<td class="ForumTopicPageDataLine" align="center"><img src="${basePath}style/images/topicType_${digest}.gif" /></td>
						
						<td class="Topic">
						<%--
							<s:a cssClass="Default" action="topic_show?id=%{id}">${title}</s:a>
						 --%>
						 <a href="${basePath }control/bbs/reply/list.action?replyFrom.topicId=${id}&look=true">${title}</a>
						</td>
						
						<td class="ForumTopicPageDataLine">
							<ul class="ForumPageTopicUl">
								<li class="Author">${author.loginName}</li>
								<li class="CreateTime"><s:date name="addDate" format="yyyy-MM-dd"/></li>
							</ul>
						</td>
						<td class="ForumTopicPageDataLine Reply" align="center" width="10%"><b>${replyCount}</b>/<b>${lookCount}</b></td>
						<td class="ForumTopicPageDataLine" align="left">
							<ul class="ForumPageTopicUl" >
								<li class="Author" style="text-align:left;">${lastReply.author.loginName}</li>
								<li class="CreateTime" style="text-align:left;">
								<s:date name="lastReplyTime" format="MM-dd HH:mm:dd"/></li>
							</ul>
						</td>
						<td></td>
					</tr>
				</s:iterator>	
					
				</tbody>
				<!--主题列表结束-->	
					
				<tr height="3"><td colspan="9"></td></tr>
				
			</table>
			
			<!--其他操作-->
			<div id="TableTail">
				<div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
						<tr valign=bottom>
							<td></td>
							<td>
								
								<s:select name="topicFrom.digest" list="#{10:'全部',0: '普通贴' , -1:'精华贴'}"/>

								<s:select name="topicFrom.orderBy" onchange="onSortByChange(this.value)"
									list="#{'':'默认排序(所有置顶帖在前面，并按最后更新时间降序排列)', 'newest':'最新',
									 'hot':'回复/查看','look':'查看' ,'addDate':'发帖时间'}"									
								/>
								
								<s:select name="topicFrom.asc" list="#{false:'降序', true:'升序'}"/>
								
								<a href="javascript:document.forms[0].submit();"><img src="${basePath}style/blue/images/button/submit.PNG" alt="提交"/></a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			
		</div>
<!-- 	</center> -->
</div>
</s:form>

<!--分页信息-->
<%@ include file="/WEB-INF/jsps/public/pageView.jspf" %>

<div class="Description">
	说明：<br />
	1，主题默认按最后更新的时间降序排列。最后更新时间是指主题最后回复的时间，如果没有回复，就是主题发表的时间。<br />
	2，帖子有普通、置顶、精华之分。置顶贴始终显示在最上面，精华贴用不同的图标标示。<br />
</div>

</body>
</html>