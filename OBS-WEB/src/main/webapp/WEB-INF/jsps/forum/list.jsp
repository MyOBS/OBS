<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>论坛</title>
	<%@ include file="/WEB-INF/jsps/public/commons.jspf" %>	
	<link type="text/css" rel="stylesheet" href="${basePath}style/blue/forum.css" />
</head>
<body>
<div id="Title_bar">
	<div id="Title_bar_Head">
		<div id="Title_Head"></div>
		<div id="Title">
			<!--页面标题-->
			<img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/> 论坛 </div>
		<div id="Title_End"></div>
	</div>
</div>
<div id="MainArea">
<!-- 	<center> -->
		<div class="ForumPageTableBorder" style="margin-top: 25px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr><td colspan="6" align="left">
				今日帖子：${model.todayTopciCount } |帖子：${model.topicCount } |文章：${model.articleCount }
				</td></tr>
				<tr  class="template">
					<td colspan="5" align="left">
						${model.name }
					</td>
					<td colspan="1" align="right">
						分区版主：${model.moderator.loginName } 
						 <img id="category_63_img" src="${basePath}style/images/collapsed_no.gif" title="收起/展开" alt="收起/展开"
									onclick="toggle_collapse('category_63');" />
					</td>
				</tr>
				<!--表头-->
				<tr align="center" valign="middle">
					<td colspan="3" class="ForumPageTableTitleLeft">版块</td>
					<td width="80" class="ForumPageTableTitle">主题数</td>
					<td width="80" class="ForumPageTableTitle">文章数</td>
					<td width="270" class="ForumPageTableTitle">最后发表的主题</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="9"></td></tr>
				<tr height="3"><td colspan="9"></td></tr>
			
				<!--版面列表-->
				<tbody class="dataContainer" datakey="forumList">
				<s:iterator value="model.children">
					<tr height="78" align="center" class="template">
						<td width="3"></td>
						<td width="75" class="ForumPageTableDataLine">
							<img src="${basePath}style/images/forumpage3.gif" />
						</td>
						<td class="ForumPageTableDataLine">
							<ul class="ForumPageTopicUl">
								<li class="ForumPageTopic">
								<a class="ForumPageTopic" href="${basePath }control/bbs/topic/list.action?topicFrom.forumId=${id}">${name}</a></li>
								<li class="ForumPageTopicMemo">${description}</li>
								<li class="ForumPageTopicMemo">版主：${moderator.loginName}</li>
							</ul>
						</td>
						<td class="ForumPageTableDataLine"><b>${topicCount}</b></td>
						<td class="ForumPageTableDataLine"><b>${articleCount}</b></td>
						<td class="ForumPageTableDataLine">
							<ul class="ForumPageTopicUl">
								<li><font color="#444444">┌ 主题：</font> 
									<s:a cssClass="ForumTitle" action="topic_show?id=%{lastTopic.id}">${lastTopic.title}</s:a>
								</li>
								<li><font color="#444444">├ 作者：</font> ${lastTopic.author.loginName}</li>
								<li><font color="#444444">└ 时间：</font> <s:date name="lastTopic.addDate" format="yyyy-MM-dd"/></li>
							</ul>
						</td>
						<td width="3">
						
						</td>
					</tr>
				</s:iterator>	
					
				</tbody>
				<!-- 版面列表结束 -->
				
				<tr height="3"><td colspan="9"></td></tr>
			</table>
		</div>
<!-- 	</center> -->
</div>
</body>
</html>
