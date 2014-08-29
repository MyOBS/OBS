<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>版块列表</title>
    <%@ include file="/WEB-INF/jsps/public/commons.jspf" %>
    <style type="text/css">
    	.disabled{
    		color: gray;
    		cursor: pointer;
    	}
    	.myButton{
    		border: 1px #87A3C1 solid;
    		padding: 3px 5px;
    		background-color: #E6F5FF;
    		
    	}
    	.myButton:HOVER {
			text-decoration:none;
    		color: #004779;
		}
    </style>
    <script type="text/javascript">
    	
    	function unDelete(id) {
            var params = {  
                'model.id': id
            };
            $.ajax({
			    type: "POST", 
			    dataType: "json", 
			    url: "unDelete.action",
			    data: params,
			    success:function(msg) {
			    	$('#message').css("color","red").html(msg.Message);
			    	if(msg.Result == "OK"){
			    		window.location.href="${basePath}control/bbs/forum/unDeleteUI.action?parentId=${parentId}";
			    	}
			    }
			});
        }
    </script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${basePath}style/images/title_arrow.gif"/> 版块管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle" >
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="200px">版块名称</td>
                <td width="250px">版块说明</td>
                <td width="80px">创建时间</td>
                <td width="100px">创建人</td>
                <td width="80px">修改时间</td>
                <td width="100px">修改人</td>
                <td width="100px">版主帐号</td>
                <td width="200">父板块</td>
                <td width="150">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="forumList">
        <s:iterator value="forumList" status="status">
			<tr class="TableDetail1 template">
				<td><s:a action="%{deleteUI ? 'unDeleteUI.action' :'manageList.action'}?parentId=%{id }">${name}</s:a>&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td><s:date name="addDate" format="yyyy-MM-dd"/>&nbsp;</td>
				<td>${addUser}&nbsp;</td>
				<td><s:date name="updateDate" format="yyyy-MM-dd"/>&nbsp;</td>
				<td>${updateUser}&nbsp;</td>
				<td>${moderator.loginName}&nbsp;</td>
				<td>${parent.name}&nbsp;</td>
				<td>
				<s:if test="!deleteUI">
					<s:a action="delete?model.id=%{id}&parentId=%{parent.id}" onclick="return delConfirm()">删除</s:a>
					<s:a action="updateUI?model.id=%{id}&parentId=%{parent.id}">修改</s:a>
					
					<!-- 最上面的不能上移 -->
					<s:if test="#status.first">
						<span class="disabled">上移</span>
					</s:if>
					<s:else>
						<s:a action="move?model.id=%{id}&up=true&parentId=%{parent.id}">上移</s:a>
					</s:else>
					
					<!-- 最下面的不能下移 -->
					<s:if test="#status.last">
						<span class="disabled">下移</span>
					</s:if>
					<s:else>
						<s:a action="move?model.id=%{id}&up=false&parentId=%{parent.id}">下移</s:a>
					</s:else>
				</s:if>
				<s:else>
					<s:a href="javascript:unDelete(%{id });" cssStyle="color:red;">恢复</s:a>
				</s:else>	
				</td>
			</tr>
        </s:iterator>

        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
	        <s:if test="!deleteUI">
	            <s:a action="saveUI?parentId=%{parentId}"><img src="${basePath}style/images/createNew.png" /></s:a>
	            <s:a action="unDeleteUI" cssClass="myButton">已删除板块</s:a>
	            <s:if test="parentId!=null">
		            <s:a action="%{historyUrl}" cssClass="myButton">返回</s:a>
	            </s:if>
	        </s:if>
	        <s:else>
	            <s:a action="%{historyUrl}" cssClass="myButton">返回</s:a>
	        </s:else>
	        <span id="message"></span>
        </div>
    </div>
</div>

<div class="Description">
	说明：<br />
	1，显示的列表按其位置排列。<br />
	2，可以通过上移与下移功能调整顺序。最上面的不能上移，最下面的不能下移。<br />
</div>

</body>
</html>
