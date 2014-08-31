<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>查看帖子：${topicBean.title}</title>
<%@ include file="/WEB-INF/jsps/public/commons.jspf"%>
<link type="text/css" rel="stylesheet"
	href="${basePath}style/blue/forum.css" />
<style type="text/css">
#fwin_rate, #fwin_comment {
	border: 1px solid red;
	background-color: #CCC;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fckeditor/fckeditor.js"
	charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		//隐藏评分/点评框
		$("#fwin_rate").hide();
		$("#fwin_rate").hide();
				
		//隐藏空的帖子点评列表
		var isTopicCritique = ${topicBean.critiques == null || empty topicBean.critiques};
		if(isTopicCritique){
			$("#topicCritiqueContent").hide();
		}
		//隐藏空的回复点评列表
		<s:iterator value="pager.resultList">
			var isNull =  ${(critiques == null) || (empty critiques)};
			if(isNull){
				var id = ${id};
				$("#replyCritiqueContent_" + id).hide();		
			}
		</s:iterator>
	
		//隐藏空的帖子评分列表
		var isTopicScore = ${topicBean.scores == null || empty topicBean.scores};
		if(isTopicScore){
			$("#topicScoreContent").hide();
		}
		//隐藏空的回复评分列表
		<s:iterator value="pager.resultList">
			var isNull =  ${(scores == null) || (empty scores)};
			if(isNull){
				var id = ${id};
				$("#replyScoreContent_" + id).hide();		
			}
		</s:iterator>
		
		//评分成功与否提示
		var result = "${result}";
		if(result != null && result == "FAIL"){
			var message = "${message}";
			alert(message);
		}

		var fck = new FCKeditor("content");
		fck.Width = "90%";
		fck.ToolbarSet = "bbs";
		fck.BasePath = "${basePath}fckeditor/";
		fck.ReplaceTextarea();
		
		
		
		//实时统计点评字数 
		$("#commentmessage").keyup(function(){
			var length = 200;
	         var content_len = $("#commentmessage").val().length;
	         var in_len = length-content_len;
	        
	         // 当用户输入的字数大于制定的数时，让提交按钮失效
	         // 小于制定的字数，就可以提交
	         if(in_len >=0){
	            $("#checklen").html(in_len);
	            $("#commentsubmit").attr("disabled",false);
	            // 可以继续执行其他操作
	         }else{
	            $("#checklen").html(in_len);
	            $("#commentsubmit").attr("disabled",true);
	            return false;
	         }
		});
		
	});

	function updateDigest(digest) {
		//设置精华,公告贴
		var str = "";
		if (digest == -2) {
			str = "公告";
		} else if (digest == -1) {
			str = "精华";
		} else if (digest == 0) {
			str = "普通";
		} else if (digest == 1) {
			str = "局部置顶";
		} else if (digest == 2) {
			str = "版块置顶";
		} else if (digest == 3) {
			str = "全局置顶";
		}
		var r = confirm('要把本主题设为' + str + '吗？');
		if (!r) {
			return;
		}
		var id = ${topicBean.id};

		var params = {
			'model.id' : id,
			'model.digest' : digest
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
	function changeReaon(value) {
		$("#scoreReaon").val(value);
	}
	//显示or隐藏点评
	function onComment(hidden, topicId,replyId){
		if(hidden){
			//隐藏
			$("#fwin_comment").hide();
		}else{
			//显示
			$("#fwin_comment").show();
			if(topicId != null  && topicId != ""){
				$("#commentTopicId").val(topicId);
				$("#commentReplyId").val("");	
				$("#commentmessage").val("");
	            $("#checklen").html(200);
			}
			if(replyId != null && replyId != ""){
				$("#commentReplyId").val(replyId);	
				$("#commentmessage").val("");
	            $("#checklen").html(200);
			}
		}
	}
	//显示or隐藏评分
	function divHidden(hidden,scoreReplyId) {
		if (hidden) {
			//隐藏
			$("#fwin_rate").hide();
		} else {
			//显示
			$("#fwin_rate").show();
			
			if(scoreReplyId == null){
				scoreReplyId = "";
			}
			$("#scoreReplyId").val(scoreReplyId);
		}
	}

	//评分
	function submitScore() {
		$("#rateform").submit();
		/*
		var userId = ${user.id};
		var scoreReplyId = $("#scoreReplyId").val();
		var params = {
			'score.money' : $("#scoreMoney").val(),
			'score.reaon' : $("#scoreReaon").val(),
			'score.user.id' : userId,
			'sendAuthor' : $("#scoreSendAuthor").attr("checked"),
			'topicId' : $("#scoreTopicId").val(),
			'replyId' : scoreReplyId
		};
		
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${basePath}control/bbs/topic/score.action",
			data : params,
			success : function(msg) {
				if(msg.Result == "OK"){
					//隐藏评分框
					$("#fwin_rate").hide();
					
					if(scoreReplyId == null || scoreReplyId == ""){
						$("#topicScoreContent").show();
						//在评分列表追加 该评分
						var trStr = '<tr><td><a href="${user.id}">${user.loginName}</a></td><td>'
						+ $("#scoreMoney").val() + '</td><td>' + $("#scoreReaon").val() + '</td></tr>';
						$(trStr).insertAfter("#topicScoreContentList");
						
// 						var topicPeopleSum = 0;
// 						$("#topicPeopleSum").html("参与人数(" + topicPeopleSum + ")");
// 						var topicMoneySum = 0;
// 						$("#topicMoneySum").html("金钱 +" + topicMoneySum);
					}else {
						//对回复的评分
						$("#replyScoreContent_" + scoreReplyId).show();
						
						var trStr = '<tr><td><a href="${user.id}">${user.loginName}</a></td><td>'
							+ $("#scoreMoney").val() + '</td><td>' + $("#scoreReaon").val() + '</td></tr>';
						$(trStr).insertAfter("#replyScoreContentList");
					}
				}else if(msg.Result == "FAIL"){
					alert(msg.Message);				
				}
			}
		});
		*/
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
					<img src="${basePath}style/blue/images/button/publishNewTopic.png" />
			</a>
			</span>
		</div>

		<!-- 评分对话框start -->
		<div
			style="position: fixed; z-index: 201; left: 59px; top: 22px; display: none;"
			class="fwinmask" id="fwin_rate">

			<table class="fwin" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="t_l"></td>
						<td class="t_c" style="cursor: move"
							onmousedown="dragMenu($('fwin_rate'), event, 1)"
							ondblclick="hideWindow('rate')"></td>
						<td class="t_r"></td>
					</tr>
					<tr>
						<td class="m_l" style="cursor: move">&nbsp;&nbsp;</td>
						<td style="" class="m_c" id="fwin_content_rate"><div
								class="tm_c" id="floatlayout_topicadmin">
								<h3 style="cursor: move;" id="fctrl_rate" class="flb">
									<em id="return_rate">评分</em> <span> <a
										href="javascript:;" class="flbc" onclick="divHidden(true)"
										title="关闭">关闭</a></span>
								</h3>
								<form id="rateform" method="post" autocomplete="off"
									action="${basePath }control/bbs/topic/score.action">
									<s:hidden id="scoreTopicId" name="topicId"
										value="%{topicBean.id}" />
									<s:hidden id="scoreReplyId" name="replyId" value="" />
									<s:hidden id="scoreUserId" name="score.user.id" value="%{user.id}" />
									<div class="c">
										<table class="dt mbm" cellpadding="0" cellspacing="0">
											<tbody>
												<tr>
													<th>&nbsp;</th>
													<th width="65">&nbsp;</th>
													<th width="65"><span style="font-size: 12px">评分区间</span></th>
													<th width="55"><span style="font-size: 12px">今日剩余</span></th>
												</tr>
												<tr>
													<td>金钱</td>
													<td><input type="number" name="score.money"
														id="scoreMoney" max="15" min="1" value="1"
														style="width: 50px;" /></td>
													<td>1 ~ 15</td>
													<td>129</td>
												</tr>
											</tbody>
										</table>

										<div class="tpclg">
											<h4>可选评分理由:</h4>
											<table class="reason_slct" cellpadding="0" cellspacing="0">
												<tbody>
													<tr>
														<td><select multiple="multiple"
															onchange="changeReaon(this.value);">
																<s:iterator value="consts">
																	<option value="${recName}">${recName }</option>
																</s:iterator>
														</select></td>
													</tr>
													<tr>
														<td><input name="score.reaon" id="scoreReaon"
															class="px" type="text"></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<p class="o pns">
										<s:checkbox name="sendAuthor" id="labelSendAuthor" />
										<s:label for="labelSendAuthor" value="通知作者"></s:label>
										<button name="ratesubmit" type="button" value="true"
											class="pn pnc" onclick="submitScore();">
											<span>确定</span>
										</button>
									</p>
								</form>
							</div></td>
						<td class="m_r" style="cursor: move"></td>
					</tr>
					<tr>
						<td class="b_l"></td>
						<td class="b_c" style="cursor: move"></td>
						<td class="b_r"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- 评分对话框end -->

		<!-- 点评对话框start -->
		<div style="position: fixed; z-index: 201; left: 353px; top: 82px; display: none;" class="fwinmask" id="fwin_comment">
		<table class="fwin" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td class="t_l"></td>
					<td class="t_c" style="cursor: move"></td>
					<td class="t_r"></td>
				</tr>
				<tr>
					<td class="m_l" style="cursor: move">&nbsp;&nbsp;</td>
					<td  style="" class="m_c" id="fwin_content_comment">
						<form  method="post" autocomplete="off" id="commentform"
							action="${basePath }control/bbs/reply/comment.action" >
							<div class="f_c">
								<h3 style="cursor: move;" id="fctrl_comment" class="flb">
									<em  id="return_comment">点评</em> <span> <a
										href="javascript:;" class="flbc"
										onclick="onComment(true,'','')" title="关闭">关闭</a></span>
								</h3>
								<s:hidden id="commentTopicId" name="topicId" value="%{topicBean.id}" />
								<s:hidden id="commentReplyId" name="replyId" />
								<s:hidden name="critique.user.id" value="%{user.id }" />
								<div class="c">
									<div class="tedt">
										<div class="bar cm">
										</div>
										<div class="area">
											<textarea  rows="2" cols="50" name="critique.content"
												id="commentmessage" style="overflow: auto"></textarea>
										</div>
									</div>
									<div  id="seccheck_comment"></div>
								</div>
							</div>
							<div class="o pns cl">
								<input type="submit" id="commentsubmit" value="发布"/>
								<span class="y">还可输入 <strong  id="checklen">200</strong>
									个字符
								</span>
							</div>
						</form>

					</td>
					<td class="m_r" style="cursor: move"></td>
				</tr>
				<tr>
					<td class="b_l"></td>
					<td class="b_c" style="cursor: move"></td>
					<td class="b_r"></td>
				</tr>
			</tbody>
		</table>
	</div>
		<!-- 点评对话框end -->

		<div class="ForumPageTableBorder dataContainer" datakey="replyList">

			<!--显示主题标题等-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
					<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>本帖主题：${topicBean.title}</b> |查看：${topicBean.replyCount } |回复：${topicBean.lookCount}</td>
					<td class="ForumPageTableTitle" align="right" style="padding-right: 12px;">
					<s:a cssClass="detail" action="saveUI.action?replyFrom.topicId=%{topicBean.id}">
							<img border="0" src="${basePath}style/images/reply.gif" />
							回复
						</s:a> 
						<a
						href="${basePath }control/bbs/topic/moveUI.action?model.id=${topicBean.id}"><img
							border="0" src="${basePath}style/images/edit.gif" />移动到其他版块</a> <a
						href="javascript:updateDigest(-1);"><img border="0"
							src="${basePath}style/images/topicType_-1.gif" />精华</a> <a
						href="javascript:updateDigest(1);"><img border="0"
							src="${basePath}style/images/topicType_1.gif" />置顶1</a> <a
						href="javascript:updateDigest(2);"><img border="0"
							src="${basePath}style/images/topicType_2.gif" />置顶2</a> <a
						href="javascript:updateDigest(3);"><img border="0"
							src="${basePath}style/images/topicType_3.gif" />置顶3</a> <a
						href="javascript:updateDigest(-2);"><img border="0"
							src="${basePath}style/images/topicType_-2.gif" />公告</a> <a
						href="javascript:updateDigest(0);"><img border="0"
							src="${basePath}style/images/topicType_0.gif" />普通</a></td>
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
								<!-- 点评  -->
								<div id="topicCritiqueContent" align="left">
									<h3 style="color: #F26C4F; background-color: #EDEDF1;">点评</h3>
									<table>

										<tbody>
											<tr>
												<td width="100px" id="topicPeopleSum"> 点评人 </td>
												<td width="80px" id="topicMoneySum"> 内容 </td>
												<td>时间</td>
											</tr>
										</tbody>
										<tbody id="topicCritiqueContentList">
											<s:iterator value="topicBean.critiques">
												<tr>
													<td><a href="?${user.id }">${user.loginName }</a></td>
													<td>${content }</td>
													<td><s:date name="addDate" format="yyyy-MM-dd HH:mm:ss"/> </td>
												</tr>
											</s:iterator>
										</tbody>

									</table>
								</div>
								<!-- 评分 -->
								<div id="topicScoreContent" align="left">
									<h3 style="color: #F26C4F; background-color: #EDEDF1;">评分</h3>
									<table>

										<tbody>
											<tr>
												<td width="100px" id="topicPeopleSum">参与人数 ( ${ topicBean.scores.size()}
													)</td>
												<td width="80px" id="topicMoneySum">金钱 +
													${topicBean.money }</td>
												<td>理由</td>
											</tr>
										</tbody>
										<tbody id="topicScoreContentList">
											<s:iterator value="topicBean.scores">
												<tr>
													<td><a href="?${user.id }">${user.loginName }</a></td>
													<td>+${money }</td>
													<td>${reaon }</td>
												</tr>
											</s:iterator>
										</tbody>

									</table>
								</div>
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
										color=#C30000><a href="javascript:;" onclick="onComment(false,'${topicBean.id}', '')">[点评]</a></font> <font color=#C30000>[ <s:a
												action="saveUI?replyFrom.topicId=%{topicBean.id}">
									回复</s:a>] </font>
									</li>
									<li style="float: right;"><a href="javascript:scroll(0,0)">
											<img border="0" src="${basePath}style/images/top.gif" />
									</a></li>
									<li style="float: right;"><a href="javascript:;"
										onclick="divHidden(false,'');">评分</a> <a href="">举报</a></li>
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
								<!-- 点评  -->
								<div id="replyCritiqueContent_${id }" align="left">
									<h3 style="color: #F26C4F; background-color: #EDEDF1;">点评</h3>
									<table>

										<tbody>
											<tr>
												<td width="100px" id="topicPeopleSum"> 点评人 </td>
												<td width="80px" id="topicMoneySum"> 内容 </td>
												<td>时间</td>
											</tr>
										</tbody>
										<tbody id="replyCritiqueContentList_${id }">
											<s:iterator value="critiques">
												<tr>
													<td><a href="?${user.id }">${user.loginName }</a></td>
													<td>${content }</td>
													<td><s:date name="addDate" format="yyyy-MM-dd HH:mm:ss"/> </td>
												</tr>
											</s:iterator>
										</tbody>

									</table>
								</div>
								<!-- 评分 -->
								<div id="replyScoreContent_${id }" align="left">
									<h3 style="color: #F26C4F; background-color: #EDEDF1;">评分</h3>
									<table>
										<tbody>
											<tr>
												<td width="100px">参与人数 (${scores.size() } )</td>
												<td width="80px">金钱 +${money }</td>
												<td>理由</td>
											</tr>
										</tbody>
										<tbody id="replyScoreContentList_${id }">
											<s:iterator value="scores">
												<tr>
													<td><a href="?${user.id }">${user.loginName }</a></td>
													<td>+${money }</td>
													<td>${reaon }</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
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
										color=#C30000><a href="javascript:;" onclick="onComment(false,'', '${id}')">[点评]</a></font> <font color=#C30000> [<s:a
												action="saveUI?replyFrom.topicId=%{topicBean.id}&replyFrom.parentId=%{id }">
									回复</s:a>]
									</font> <font color=#C30000><a href="${basePath }control/bbs/reply/opinion.action?opinion=support&userId=${user.id}&replyId=${id}&topicId=${topicBean.id}">[支持 ${support }]</a></font> 
									<font color=#C30000><a href="${basePath }control/bbs/reply/opinion.action?opinion=against&userId=${user.id}&replyId=${id}&topicId=${topicBean.id}">[反对${against }]</a></font>
									</li>
									<li style="float: right;"><a href="javascript:scroll(0,0)">
											<img border="0" src="${basePath}style/images/top.gif" />
									</a></li>
									<li style="float: right;"><a href="javascript:;"
										onclick="divHidden(false,${id});">评分</a> <a href="">举报</a></li>
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
							<td valign="top" class="no_color_bg">
							<textarea name="content" style="width: 95%; height: 300px"></textarea></td>
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
