<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>论坛</title>
<%@ include file="/WEB-INF/jsps/public/commons.jspf"%>
<link type="text/css" rel="stylesheet"
	href="${basePath}style/blue/forum.css" />
<style type="text/css">
body,ul,ol,li,dl,dd,p,h1,h2,h3,h4,h5,h6,form,fieldset,.pr,.pc {
	margin: 0px;
	padding: 0px;
}

body,input,button,select,textarea {
	font: 12px/1.5 "Microsoft yahei", "微软雅黑", Arial, Helvetica, sans-serif,
		"宋体";
	color: #444;
}

* {
	outline: medium none;
}

* {
	word-wrap: break-word;
}

td,th,div {
	word-break: break-all;
}

.bmw .bm_h {
	line-height: 40px;
	white-space: nowrap;
}

.bm_h h2 {
	font-size: 17px;
}

.fl .bm_c {
	margin: 0px;
	border-width: medium 1px 1px;
	border-style: none solid solid;
	border-color: -moz-use-text-color #CCC #CCC;
	-moz-border-top-colors: none;
	-moz-border-right-colors: none;
	-moz-border-bottom-colors: none;
	-moz-border-left-colors: none;
	border-image: none;
	box-shadow: 0px 1px 3px #DDD;
	border-radius: 0px;
}

.fl .bm_c,#online .bm_c,.lk .bm_c {
	padding-top: 0px;
	padding-bottom: 0px;
}

.bm_c {
	padding: 10px;
}

td,th,div {
	word-break: break-all;
	word-wrap: break-word;
}
</style>
</head>
<body>
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${basePath}style/images/title_arrow.gif" /> 论坛
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<div id="MainArea">
		<!-- 	<center> -->
		<div class="ForumPageTableBorder" style="margin-top: 25px;">

			<div class="mn">
				<div class="drag">
					<!--[diy=diy4]-->
					<div id="diy4" class="area"></div>
					<!--[/diy]-->
				</div>
	昨日帖子：${forumFrom.yesterdayTopicCount }|今日帖子：${forumFrom.todayTopicCount } |帖子：${forumFrom.totalTopicCount }| 会员：${forumFrom.totalUserCount }|欢迎新会员:<a href="">${forumFrom.newUser.loginName }</a>
				<div class="fl bm">
					<s:iterator value="forumList">
						<div class="bm bmw  flg cl">
							<div class="bm_h cl">
								<span class="o"> <img id="category_63_img"
									src="${basePath}style/images/collapsed_no.gif" title="收起/展开" alt="收起/展开"
									onclick="toggle_collapse('category_63');" />
								</span> <span class="y">分区版主: <a
									href="space-username-%E9%99%88%E9%9B%AA%E5%8D%8E%E8%80%81%E5%B8%88.html"
									class="notabs" c="1">${moderator.loginName }</a></span>
								<h2>
									<a href="${basePath }control/bbs/forum/forum.action?model.id=${id}" style="">${name }</a>
								</h2>
							</div>
							<div id="category_63" class="bm_c" style="">
								<table cellspacing="0" cellpadding="0" class="fl_tb" width="80%">

									<s:iterator value="children" status="index">
										<s:if test="#index.odd">
											<!--奇数行 -->
											<tr>
										</s:if>
										<td class="fl_g" width="50%" style=" border-bottom:1px #ccc dashed; padding: 10px;">
											<div class="fl_icn_g" style="width: 50px;">
												<a href="forum-65-1.html"><img
													src="style/images/forum/common_${id }_icon.png"
													align="left" alt="" /></a>
											</div>
											<dl style="margin-left: 50px;">
												<dt>
													<a href="${basePath }control/bbs/topic/list.action?topicFrom.forumId=${id}" style="color: #CC0066;">${name }</a>
													<s:if test="todayTopciCount>0">
														<em class="xw0 xi1" title="今日"> (${todayTopciCount })
														</em>
													</s:if>
												</dt>
												<dd>
													<span class="xi2"> ${topicCount }</span> <span class="xg1">
														/${articleCount }</span>
												</dd>
												<dd>
													<a
														href="forum.php?mod=redirect&amp;tid=28175&amp;goto=lastpost#lastpost">最后发表:
														<span title="2014-8-18 19:37">${lastTopic.addDate }</span>
													</a>
												</dd>
											</dl>
										</td>
										<s:if test="#index.even">
											<!-- 偶数行 -->
											</tr>
										</s:if>
									</s:iterator>
								</table>
							</div>
						</div>
					</s:iterator>

				</div>
			</div>

		</div>
		<!-- 	</center> -->
	</div>
</body>
</html>
