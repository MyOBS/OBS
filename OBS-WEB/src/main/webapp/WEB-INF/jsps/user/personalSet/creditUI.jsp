<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>积分</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/jsps/public/commons.jspf"%>
<script src="${basePath}script/My97DatePicker/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		var tab = "${tab}";
		chooseTab(tab, false);
		var result = "${result}";
		if (result == "OK") {
			alert("修改成功");
		}
	})
	function chooseTab(tab, isLoad) {
		if (tab == "creditUI") {
			// 我的积分
			$("#tab6").css("display", "block");
			$("#tab7").css("display", "none");
			$("#tab8").css("display", "none");
			$("#tab9").css("display", "none");
			$("#tab10").css("display", "none");
			if (isLoad) {
				location.href = "${basePath}control/creditlog/creditUI.action?tab=creditUI";
			}
		} else if (tab == "exchangeUI") {
			// 兑换
			$("#tab7").css("display", "block");
			$("#tab6").css("display", "none");
			$("#tab8").css("display", "none");
			$("#tab10").css("display", "none");
			$("#tab9").css("display", "none");
			if (isLoad) {
				location.href = "${basePath}control/creditlog/exchangeUI.action?tab=exchangeUI";
			}
		} else if (tab == "log") {
			// 积分日志	
			$("#tab8").css("display", "block");
			$("#tab6").css("display", "none");
			$("#tab7").css("display", "none");
			$("#tab9").css("display", "none");
			$("#tab10").css("display", "none");
			if (isLoad) {
				location.href = "${basePath}control/creditlog/log.action?tab=log";
			}
		} else if (tab == "rule") {
			// 积分规则
			$("#tab9").css("display", "block");
			$("#tab6").css("display", "none");
			$("#tab7").css("display", "none");
			$("#tab8").css("display", "none");
			$("#tab10").css("display", "none");
			if (isLoad) {
				location.href = "${basePath}control/creditlog/rule.action?tab=rule";
			}
		} else if (tab == "sysLog") {
			// 积分规则
			$("#tab10").css("display", "block");
			$("#tab6").css("display", "none");
			$("#tab7").css("display", "none");
			$("#tab8").css("display", "none");
			$("#tab9").css("display", "none");
			if (isLoad) {
				location.href = "${basePath}control/creditlog/rule.action?tab=rule";
			}
		}

	}
</script>
<style type="text/css">
#Title a:HOVER {
	color: red;
}
</style>
</head>
<body>

	<!-- 标题显示  -->

	<div id="Title_bar">
		<div id="Title_bar_Head" style="">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题 -->
				<a href="javascript:;" onclick="chooseTab('creditUI',true);"> <img
					border="0" width="13" height="13"
					src="${basePath}style/images/title_arrow.gif" />我的积分
				</a>
			</div>
			<div id="Title_End"></div>
		</div>
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题 -->
				<a href="javascript:;" onclick="chooseTab('exchangeUI',true);">
					<img border="0" width="13" height="13"
					src="${basePath}style/images/title_arrow.gif" /> 兑换
				</a>
			</div>
			<div id="Title_End"></div>
		</div>
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题 -->
				<a href="javascript:;" onclick="chooseTab('log',true);"> <img
					border="0" width="13" height="13"
					src="${basePath}style/images/title_arrow.gif" /> 积分记录
				</a>
			</div>
			<div id="Title_End"></div>
		</div>
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题 -->
				<a href="javascript:;" onclick="chooseTab('rule',true);"> <img
					border="0" width="13" height="13"
					src="${basePath}style/images/title_arrow.gif" /> 积分规则
				</a>
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!-- 我的积分 -->
	<s:if test="tab=='creditUI'">
		<div id="tab6">
			<div id="MainArea">
				<s:form action="creditUI" namespace="/control/creditlog"
					method="post">
					<div class="ItemBlock_Title1">
						<DIV CLASS="ItemBlock_Title1">
							<IMG BORDER="0" WIDTH="4" HEIGHT="7"
								SRC="${basePath}style/blue/images/item_point.gif" /><span
								id="tabName">我的积分 </span>  
						</DIV>
					</div>

					<!--表单内容显示 -->
					<div class="ItemBlockBorder">
						<div class="ItemBlock">
							<table cellpadding="0" cellspacing="0" class="mainForm">
								<tr>
									<td colspan="4">金币: ${userBean.userCredit.gold == null ? 0 : userBean.userCredit.gold }
									</td>
								</tr>
								<tr>
									<td>金钱: ${userBean.userCredit.money== null ? 0 : userBean.userCredit.money  }
									</td>
									<td>经验: ${userBean.userCredit.experience== null ? 0 : userBean.userCredit.experience  }</td>
									<td>宣传贡献: ${userBean.userCredit.campaignContribution== null ? 0 : userBean.userCredit.campaignContribution  }
									</td>
									<td>冻结金币: ${userBean.userCredit.frozenGold == null ? 0 : userBean.userCredit.frozenGold }</td>
								</tr>
								<tr>
									<td colspan="4">积分: ${userBean.userCredit.experience== null ? 0 : userBean.userCredit.experience }
										( 总积分=经验 + 好友数 )</td>
								</tr>
							</table>

							<table cellpadding="0" cellspacing="0" class="mainForm">
								<s:hidden name="tab" value="creditUI"></s:hidden>
								<tr>
									<td colspan="10">积分记录</td>
									<td><a
										href="${basePath }control/creditlog/log.action?tab=log">查看更多>></a></td>
								</tr>
								<tr height="25">
									<td>操作</td>
									<td>积分变更</td>
									<td>详情</td>
									<td>变更时间</td>
								</tr>
								<s:iterator value="pager.resultList" status="status">
									<tr height="25">
										<td>${creditRule.actionName }</td>
										<td><s:if test="money!=null">
								金钱 <s:if test="ioe==1">+</s:if>
												<s:else>-</s:else> ${money }
							</s:if> <s:elseif test="experience!=null">
								经验 <s:if test="ioe==1">+</s:if>
												<s:else>-</s:else> ${experience }
							</s:elseif> <s:elseif test="gold!=null">
								金币 <s:if test="ioe==1">+</s:if>
												<s:else>-</s:else> ${gold }
							</s:elseif> <s:elseif test="campaignContribution!=null">
								宣传贡献<s:if test="ioe==1">+</s:if>
												<s:else>-</s:else> ${campaignContribution }
							</s:elseif> <s:elseif test="frozenGold!=null">
								冻结金币<s:if test="ioe==1">+</s:if>
												<s:else>-</s:else> ${frozenGold }
							</s:elseif></td>
										<td><a href="${url }">${details }</a></td>
										<td><s:date name="addDate" format="yyyy-MM-dd HH:mm" /></td>

									</tr>
								</s:iterator>

							</table>
						</div>
					</div>

					<!-- 表单操作 -->
					<div id="InputDetailBar">
						<a href="javascript:history.go(-1);"><img
							src="${basePath}style/images/goBack.png" /></a>
					</div>
				</s:form>
			</div>

			<div class="Description">
				验证规则：<br /> 1，已启动状态的才能通过审核.<br /> 2，已启动状态的才能驳回.<br />
			</div>
		</div>
	</s:if>
	<s:elseif test="tab=='exchangeUI'">
		<!-- 兑换	 -->
		<div id="tab7" style="display: none;">
			<div id="MainArea_2">
				<s:form action="exchange" namespace="/control/creditlog" method="post" id="exchangeUI">
					<div class="ItemBlock_Title1">
						<DIV CLASS="ItemBlock_Title1">
							<IMG BORDER="0" WIDTH="4" HEIGHT="7"
								SRC="${basePath}style/blue/images/item_point.gif" /><span
								id="tabName">兑换</span>  
						</DIV>
					</div>

					<!--表单内容显示 -->
					<div class="ItemBlockBorder">
						<div class="ItemBlock">
							<s:hidden name="tab" value="exchange"></s:hidden>
							<table cellpadding="0" cellspacing="0" class="mainForm">
								<tr>
									<td colspan="4">金币: ${userBean.userCredit.gold == null ? 0 : userBean.userCredit.gold }
									</td>
								</tr>
								<tr>
									<td>金钱: ${userBean.userCredit.money== null ? 0 : userBean.userCredit.money  }
									</td>
									<td>经验: ${userBean.userCredit.experience== null ? 0 : userBean.userCredit.experience  }</td>
									<td>宣传贡献: ${userBean.userCredit.campaignContribution== null ? 0 : userBean.userCredit.campaignContribution  }
									</td>
									<td>冻结金币: ${userBean.userCredit.frozenGold == null ? 0 : userBean.userCredit.frozenGold }</td>
								</tr>
								<tr>
									<td colspan="4"><script type="text/javascript">
										$(function() {
											$("#startValue").change(function() {
																var startValue = $("#startValue").val();
																if (startValue == "money") {
																	$("#endValue").val("gold");
																	//1:用金币兑换金钱; 2： 用金钱兑换金币 
																	$("#exchange").val(1);
																} else if (startValue == "gold") {
																	$("#endValue").val("money");
																	$("#exchange").val(2);
																}
															});

											$("#startInput").keydown(function(){
												updateInput();
											});
											
											$("#startInput").blur(function(){
												updateInput();
											});
											function updateInput() {
												var startInput = $("#startInput").val();
												var ex = /^\d+$/;
												if (ex.test(startInput)) {
													//类型
													var exchange = $("#exchange").val();
													if(exchange == "1"){
														//1:用金币兑换金钱;
														var endInput = Math.floor(parseInt(startInput)/50) + 1;
														$("#endInput").val(endInput);
													}else if(exchange =="2"){
														// 2： 用金钱兑换金币 
														var endInput =parseInt(startInput) * 50*1.01;
														$("#endInput").val(Math.floor(endInput));
													}
													
												} else {
													$("#startInput").val("");
													$("#endInput").val("");
												}
											}
										});
										
									</script> 

									兑换<input name="creditLogForm.startValue"
										id="startInput" class="InputStyle" style="width: 50px;" /> <s:select
											list="#{'money':'金钱','gold':'金币' }" id="startValue"></s:select>
										所需<input name="creditLogForm.endValue" disabled="disabled"
										id="endInput" class="InputStyle" style="width: 50px;" /> <s:select
											disabled="true" list="#{'gold':'金币','money':'金钱' }"
											id="endValue"></s:select> 兑换后最低余额 50，积分交易税 1.00% 
											<s:hidden name="creditLogForm.exchange" value="1" id="exchange" /></td>
								</tr>
								<tr>
									<td colspan="4">登录密码 <s:password
											name="creditLogForm.password" id="password" cssClass="InputStyle"></s:password>
									</td>
								</tr>
								<tr>
									<td colspan="4">
									
									<s:if test="result=='FAIL'">
										<font color="red">${message }</font>
									</s:if>
									</td>
								</tr>

							</table>
						</div>
					</div>

					<!-- 表单操作 -->
					<div id="InputDetailBar_2">
						<a href="javascript:$('#exchangeUI').submit();" onclick=""><img
							src="${basePath}style/images/save.png" /></a> <a
							href="javascript:history.go(-1);"><img
							src="${basePath}style/images/goBack.png" /></a>
					</div>
				</s:form>
			</div>
			<div class="Description_2"></div>
		</div>
	</s:elseif>
	<s:elseif test="tab=='log'">
		<!-- 积分记录	 -->
		<div id="tab8" style="display: none;">
			<div id="MainArea_3">
				<s:form action="log" namespace="/control/creditlog" method="post"
					id="logForm">
					<div class="ItemBlock_Title1">
						<DIV CLASS="ItemBlock_Title1">
							<IMG BORDER="0" WIDTH="4" HEIGHT="7"
								SRC="${basePath}style/blue/images/item_point.gif" /><span
								id="tabName">积分记录</span>  
						</DIV>
					</div>

					<!--表单内容显示 -->
					<div class="ItemBlockBorder">
						<div class="ItemBlock">
							<table cellpadding="0" cellspacing="0" class="mainForm">
								<s:hidden name="tab" value="log"></s:hidden>
								<tr>
									<td colspan="4">积分<s:select list="creditList"
											listKey="recValue" listValue="recName" headerKey=""
											headerValue="不限" name="creditLogForm.credit"></s:select> 时间范围
										<input type="text" class="Wdate InputStyle"
										style="width: 100px;" id="startDate"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})"
										name="creditLogForm.startDate"
										value="${creditLogForm.startDate }" /> -<input type="text"
										class="Wdate InputStyle" style="width: 100px;" id="endDate"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})"
										name="creditLogForm.endDate" value="${creditLogForm.endDate }" />
										收支<s:select list="#{1:'支入',0:'支出' }" headerKey=""
											headerValue="不限" name="creditLogForm.ioe"></s:select> 操作<s:select
											list="creditRuleList" listKey="actionName"
											listValue="actionName" headerKey="" headerValue="不限"
											name="creditLogForm.operation"></s:select> <a
										href="${basePath }control/creditlog/sysLog.action?tab=sysLog"><font
											color="red">系统奖励</font></a> <input type="submit" value="查询" />
									</td>
								</tr>
								<tr>
									<td>操作</td>
									<td>积分变更</td>
									<td>详情</td>
									<td>变更时间</td>
								</tr>
								<s:iterator value="pager.resultList" status="status">
									<tr height="25">
										<td>${creditRule.actionName }</td>
										<td><s:if test="money!=null">
								金钱 <s:if test="money>=0">+</s:if>
												<s:else>-</s:else> ${money }
							</s:if> <s:elseif test="experience!=null">
								经验 <s:if test="experience>=0">+</s:if>
												<s:else>-</s:else> ${experience }
							</s:elseif> <s:elseif test="gold!=null">
								金币 <s:if test="gold>=0">+</s:if>
												<s:else>-</s:else> ${gold }
							</s:elseif> <s:elseif test="campaignContribution!=null">
								宣传贡献<s:if test="campaignContribution>=0">+</s:if>
												<s:else>-</s:else> ${campaignContribution }
							</s:elseif> <s:elseif test="frozenGold!=null">
								冻结金币<s:if test="frozenGold>=0">+</s:if>
												<s:else>-</s:else> ${frozenGold }
							</s:elseif></td>
										<td>${details }</td>
										<td><s:date name="addDate" format="yyyy-MM-dd HH:mm" /></td>

									</tr>
								</s:iterator>
								<tr>
									<td colspan="11"><%@ include
											file="/WEB-INF/jsps/public/pageView.jspf"%>
									</td>
								</tr>

							</table>
						</div>
					</div>

					<!-- 表单操作 -->
					<div id="InputDetailBar_3">
						<a href="javascript:history.go(-1);"><img
							src="${basePath}style/images/goBack.png" /></a>
					</div>
				</s:form>
			</div>
			<div class="Description_3"></div>
		</div>
	</s:elseif>
	<s:elseif test="tab=='sysLog'">
		<!-- 积分记录	 -->
		<div id="tab10" style="display: none;">
			<div id="MainArea_3">
				<s:form action="sysLog" namespace="/control/creditlog" method="post"
					id="logForm">
					<div class="ItemBlock_Title1">
						<DIV CLASS="ItemBlock_Title1">
							<IMG BORDER="0" WIDTH="4" HEIGHT="7"
								SRC="${basePath}style/blue/images/item_point.gif" /><span
								id="tabName">积分记录</span>  
						</DIV>
					</div>

					<!--表单内容显示 -->
					<div class="ItemBlockBorder">
						<div class="ItemBlock">
							<table cellpadding="0" cellspacing="0" class="mainForm">
								<s:hidden name="tab" value="log"></s:hidden>
								<tr>
									<td colspan="9"><a
										href="${basePath }control/creditlog/log.action?tab=log"><font
											color="red">积分收益</font></a></td>
								</tr>
								<tr>
									<td>动作名称</td>
									<td>总次数</td>
									<td>周期次数</td>
									<td>金钱</td>
									<td>经验</td>
									<td>金币</td>
									<td>宣传贡献</td>
									<td>冻结金币</td>
									<td>最后奖励时间</td>
								</tr>
								<s:iterator value="sysCreditRuleList" status="status">
									<tr height="25">
										<td>${actionName }</td>
										<td>${totalCount }</td>
										<td>${cycleTotalCount}</td>
										<td>${money }</td>
										<td>${experience }</td>
										<td>${gold }</td>
										<td>${campaignContribution }</td>
										<td>${frozenGold }</td>
										<td><s:date name="lastRewardTime"
												format="yyyy-MM-dd HH:mm" /></td>

									</tr>
								</s:iterator>
								<tr>
									<td colspan="11"></td>
								</tr>

							</table>
						</div>
					</div>

					<!-- 表单操作 -->
					<div id="InputDetailBar_3">
						<a href="javascript:history.go(-1);"><img
							src="${basePath}style/images/goBack.png" /></a>
					</div>
				</s:form>
			</div>
			<div class="Description_3"></div>
		</div>
	</s:elseif>
	<s:elseif test="tab=='rule'">
		<!-- 积分规则	 -->
		<div id="tab9" style="display: none;">
			<div id="MainArea_3">
				<s:form action="rule" namespace="/control/creditlog" method="post">
					<div class="ItemBlock_Title1">
						<DIV CLASS="ItemBlock_Title1">
							<IMG BORDER="0" WIDTH="4" HEIGHT="7"
								SRC="${basePath}style/blue/images/item_point.gif" /><span
								id="tabName">积分规则</span>  
						</DIV>
					</div>

					<!--表单内容显示 -->
					<div class="ItemBlockBorder">
						<div class="ItemBlock">
							<table cellpadding="0" cellspacing="0" class="mainForm">
								<s:hidden name="tab" value="rule"></s:hidden>
								<tr>
									<td>动作名称</td>
									<td>周期范围</td>
									<td>周期内最多奖励次数</td>
									<td>金钱</td>
									<td>经验</td>
									<td>金币</td>
									<td>宣传贡献</td>
									<td>冻结金币</td>
								</tr>
								<s:iterator value="creditRulePager.resultList" status="status">
									<tr height="25">
										<td>${actionName }</td>
										<td>${cycleRangeName }</td>
										<td>${cycleCount=="max" ? "不限次数" : cycleCount }</td>
										<td>${money }</td>
										<td>${experience }</td>
										<td>${gold }</td>
										<td>${campaignContribution }</td>
										<td>${frozenGold }</td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="11"><%@ include
											file="/WEB-INF/jsps/public/pageView.jspf"%>
									</td>
								</tr>

							</table>
						</div>
					</div>

					<!-- 表单操作 -->
					<div id="InputDetailBar_3">
						<a href="javascript:history.go(-1);"><img
							src="${basePath}style/images/goBack.png" /></a>
					</div>
				</s:form>
			</div>
			<div class="Description_3"></div>
		</div>
	</s:elseif>
</body>
</html>
