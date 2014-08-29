<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>查看帖子：${topicBean.title}</title>
<%@ include file="/WEB-INF/jsps/public/commons.jspf"%>
<link type="text/css" rel="stylesheet"
	href="${basePath}style/blue/forum.css" />
<script type="text/javascript"
	src="${basePath}script/fckeditor/fckeditor.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		var fck = new FCKeditor("content");
		fck.Width = "90%";
		fck.ToolbarSet = "bbs";
		fck.BasePath = "${basePath}script/fckeditor/";
		fck.ReplaceTextarea();
	});

	function updateDigest(digest) {
		var str = "";
		if (digest == -2){
			str = "公告";
		}else if (digest == -1){
			str = "精华";
		}else  	if (digest == 0){
			str = "普通";
		}else if (digest == 1){
			str = "局部置顶";
		}else  if (digest == 2){
			str = "版块置顶";
		}else if (digest == 3){
			str = "全局置顶";
		}
		var r = confirm('要把本主题设为' + str + '吗？');
		if(!r){
			return;
		}
		var id = ${topicBean.id};
				
		var params = {
			'model.id' : id,
			'model.digest':digest
		};

		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${basePath}control/bbs/topic/digest.action",
			data : params,
			success : function(msg) {
				alert(msg.Message);
			}
		});

	}
</script>
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${basePath}style/images/title_arrow.gif" /> 查看主题
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--内容显示-->
	<div id="MainArea">
		<div id="PageHead"></div>
		<div class="ItemBlock_Title1" style="width: 98%">
			<font class="MenuPoint"> &gt; </font> <a
				href="${basePath }control/bbs/forum/list.action">论坛</a> <font
				class="MenuPoint"> &gt; </font> <a
				href="${basePath }control/bbs/forum/forum.action?id=${topicBean.forum.parent.id}">${topicBean.forum.parent.name }</a>
			<font class="MenuPoint"> &gt; </font> <a
				href="${basePath }control/bbs/topic/list.action?topicFrom.forumId=${topicBean.forum.id}">${topicBean.forum.name }</a>
			<font class="MenuPoint"> &gt;</font> <a
				href="${basePath }control/bbs/reply/list.action?replyFrom.topicId=${topicBean.id}">${topicBean.title }</a>
			<span style="margin-left: 30px;"> <a
				href="${basePath }control/bbs/topic/saveUI.action?topicFrom.forumId=${topicBean.forum.id}">
					<img align="absmiddle"
					src="${basePath}style/blue/images/button/publishNewTopic.png" />
			</a>
			</span>
		</div>

		<div class="ForumPageTableBorder dataContainer" datakey="replyList">

			<!--显示主题标题等-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
					<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>本帖主题：${topicBean.title}</b></td>
					<td class="ForumPageTableTitle" align="right"
						style="padding-right: 12px;"><s:a cssClass="detail"
							action="saveUI.action?replyFrom.topicId=%{topicBean.id}">
							<img border="0" src="${basePath}style/images/reply.gif" />
							回复
						</s:a> <a
						href="${basePath }control/bbs/topic/moveUI.action?model.id=${topicBean.id}"><img
							border="0" src="${basePath}style/images/edit.gif" />移动到其他版块</a> 
							
							<a href="javascript:updateDigest(-1);"><img
							border="0" src="${basePath}style/images/topicType_-1.gif" />精华</a> 
							<a href="javascript:updateDigest(1);"><img
							border="0" src="${basePath}style/images/topicType_1.gif" />置顶1</a> 
							<a href="javascript:updateDigest(2);" ><img
							border="0" src="${basePath}style/images/topicType_2.gif" />置顶2</a> 
							<a href="javascript:updateDigest(3);" ><img
							border="0" src="${basePath}style/images/topicType_3.gif" />置顶3</a> 
							<a href="javascript:updateDigest(-2);" ><img
							border="0" src="${basePath}style/images/topicType_-2.gif" />公告</a>
							 <a href="javascript:updateDigest(0);" ><img
							border="0" src="${basePath}style/images/topicType_0.gif" />普通</a>
							</td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine">
					<td colspan="4"></td>
				</tr>
			</table>

			<!-- ~~~~~~~~~~~~~~~ 显示主帖（主帖只在第1页显示） ~~~~~~~~~~~~~~~ -->
			<s:if test="pager.pageNum == 1">
				<div class="ListArea">
					<table border="0" cellpadding="0" cellspacing="1" width="100%">
						<tr>
							<td rowspan="3" width="130" class="PhotoArea" align="center"
								valign="top">
								<!--作者头像-->
								<div class="AuthorPhoto">
									<img border="0" width="110" height="110"
										src="${basePath}style/images/defaultAvatar.gif"
										onerror="this.onerror=null; this.src='${basePath}style/images/defaultAvatar.gif';" />
								</div> <!--作者名称-->
								<div class="AuthorName">${topicBean.author.loginName}</div>
							</td>
							<td align="center">
								<ul class="TopicFunc">
									<!--操作列表-->
									<li class="TopicFuncLi"><a class="detail"
										href="${basePath }control/bbs/topic/updateUI.action?model.id=${topicBean.id }&topicFrom.forumId=${topicBean.forum.id}"><img
											border="0" src="${basePath}style/images/edit.gif" />编辑</a> <a
										class="detail"
										href="${basePath }control/bbs/topic/delete.action?model.id=${topicBean.id }&topicFrom.forumId=${topicBean.forum.id}"><img
											border="0" src="${basePath}style/images/delete.gif" />删除</a></li>
									<!-- 文章的标题 -->
									<li class="TopicSubject">${topicBean.title}</li>
								</ul>
							</td>
						</tr>
						<tr>
							<!-- 文章内容 -->
							<td valign="top" align="center">
								<div class="Content" style="height: auto;">${topicBean.content}</div>
							</td>
						</tr>
						<tr>
							<!--显示楼层等信息-->
							<td class="Footer" height="28" align="center" valign="bottom">
								<ul style="margin: 0px; width: 98%;">
									<li style="float: left; line-height: 18px;">只看该作者</li>
									<li style="float: left; line-height: 18px;"><font
										color=#C30000>[楼主]</font> <s:date name="topicBean.addDate"
											format="yyyy-MM-dd HH:mm:ss" /></li>
									<li style="float: left; line-height: 18px;"><font
										color=#C30000>[点评]</font> <font color=#C30000>[ <s:a
												action="saveUI?replyFrom.topicId=%{topicBean.id}">
									回复</s:a>]
									</font> <font color=#C30000>[支持]</font> <font color=#C30000>[反对]</font>
									</li>
									<li style="float: right;"><a href="javascript:scroll(0,0)">
											<img border="0" src="${basePath}style/images/top.gif" />
									</a></li>
									<li style="float: right;"><a href="">评分</a> <a href="">举报</a>
									</li>
								</ul>
							</td>
						</tr>
					</table>
				</div>
			</s:if>
			<!-- ~~~~~~~~~~~~~~~ 显示主帖结束 ~~~~~~~~~~~~~~~ -->


			<!-- ~~~~~~~~~~~~~~~ 显示回复列表 ~~~~~~~~~~~~~~~ -->
			<s:iterator value="pager.resultList" status="status">
				<div class="ListArea template">
					<table border="0" cellpadding="0" cellspacing="1" width="100%">
						<tr>
							<td rowspan="3" width="130" class="PhotoArea" align="center"
								valign="top">
								<!--作者头像-->
								<div class="AuthorPhoto">
									<img border="0" width="110" height="110"
										src="${basePath}style/images/defaultAvatar.gif"
										onerror="this.onerror=null; this.src='${basePath}style/images/defaultAvatar.gif';" />
								</div> <!--作者名称-->
								<div class="AuthorName">${author.loginName}</div>
							</td>
							<td align="center">
								<ul class="TopicFunc">
									<!--操作列表-->
									<li class="TopicFuncLi"><s:a class="detail"
											action="updateUI.action?model.id=%{id }&replyFrom.topicId=%{topicBean.id}">
											<img border="0" src="${basePath}style/images/edit.gif" />编辑</s:a>
										<s:a class="detail"
											href="delete.action?model.id=%{id }&replyFrom.topicId=%{topicBean.id}">
											<img border="0" src="${basePath}style/images/delete.gif" />删除</s:a>
									</li>
									<!-- 文章表情与标题 -->
									<li class="TopicSubject">${parent != null ? "回复" : ""}${parent.floor}${parent != null ? "楼：" : ""}${parent != null ? parent.content :title }
									</li>
								</ul>
							</td>
						</tr>
						<tr>
							<!-- 文章内容 -->
							<td valign="top" align="center">
								<div class="Content" style="height: auto;">${content}</div>
							</td>
						</tr>
						<tr>
							<!--显示楼层等信息-->
							<td class="Footer" height="28" align="center" valign="bottom">
								<ul style="margin: 0px; width: 98%;">
									<li style="float: left; line-height: 18px;">只看该作者</li>
									<li style="float: left; line-height: 18px;"><font
										color=#C30000>[ <s:if test="floor==2">沙发</s:if> <s:elseif
												test="floor==3">地板</s:elseif> <s:elseif test="floor==4">板凳</s:elseif>
											<s:else>${floor }楼</s:else> ]
									</font> <s:date name="addDate" format="yyyy-MM-dd HH:mm:ss" /></li>
									<li style="float: left; line-height: 18px;"><font
										color=#C30000>[点评]</font> <font color=#C30000> [<s:a
												action="saveUI?replyFrom.topicId=%{topicBean.id}&replyFrom.parentId=%{id }">
									回复</s:a>]
									</font> <font color=#C30000>[支持]</font> <font color=#C30000>[反对]</font>
									</li>
									<li style="float: right;"><a href="javascript:scroll(0,0)">
											<img border="0" src="${basePath}style/images/top.gif" />
									</a></li>
									<li style="float: right;"><a href="">评分</a> <a href="">举报</a>
									</li>
								</ul>
							</td>
						</tr>
					</table>
				</div>
			</s:iterator>
			<!-- ~~~~~~~~~~~~~~~ 显示回复列表结束 ~~~~~~~~~~~~~~~ -->
		</div>


		<!--分页信息-->
		<%@ include file="/WEB-INF/jsps/public/pageView.jspf"%>
		<s:form action="list?replyFrom.topicId=%{replyFrom.topicId}"></s:form>

		<div class="ForumPageTableBorder" style="margin-top: 25px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
					<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>快速回复</b></td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine">
					<td colspan="3"></td>
				</tr>
			</table>
		</div>

		<!--快速回复-->
		<div class="QuictReply">
			<s:form action="save" method="post">
				<s:hidden name="replyFrom.topicId" value="%{topicBean.id}"></s:hidden>
				<div style="padding-left: 3px;">
					<table border="0" cellspacing="1" width="98%" cellpadding="5"
						class="TableStyle">
						<tr height="30" class="Tint">
							<td width="50px" class="Deep"><b>标题</b></td>
							<td class="no_color_bg"><input type="text" name="title"
								class="InputStyle" value="" style="width: 90%" /></td>
						</tr>
						<tr class="Tint" height="200">
							<td valign="top" rowspan="2" class="Deep"><b>内容</b></td>
							<td valign="top" class="no_color_bg"><textarea
									name="content" style="width: 95%; height: 300px"></textarea></td>
						</tr>
						<tr height="30" class="Tint">
							<td class="no_color_bg" colspan="2" align="center">
								<%--
						<input type="image" src="${basePath}style/blue/images/button/submit.PNG" style="margin-right:15px;"/>
					 --%> <a href="javascript:document.forms[1].submit();"><img
									alt="提交" src="${basePath}style/blue/images/button/submit.PNG" /></a>
							</td>
						</tr>
					</table>
				</div>
			</s:form>
		</div>
	</div>

	<div class="Description">
		说明：<br /> 1，主帖只在第一页显示。<br />
		2，只有是管理员才可以进行“移动”、“编辑”、“删除”、“精华”、“置顶”的操作。<br /> 3，删除主帖，就会删除所有的跟帖（回复）。<br />
	</div>

</body>
</html>
