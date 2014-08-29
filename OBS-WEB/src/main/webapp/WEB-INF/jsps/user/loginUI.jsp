<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>OBS</title>
    <%@ include file="/WEB-INF/jsps/public/commons.jspf" %>
	<link href="${basePath}style/blue/login.css" type=text/css rel=stylesheet>
	<script type="text/javascript">
		$(function(){
			document.forms[0].loginName.focus();
		});
		
		// 在被嵌套时就刷新上级窗口
		if(window.parent != window){
// 			window.parent.location.reload(true);
			window.parent.location = window.location;
		}
	</script>
</head>

<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 class=PageBody >



<!-- 显示表单 -->
<s:form action="admin_login" namespace="/" method="post" focusElement="loginNameInput">
    <div id="CenterAreaBg"> 
        <div id="CenterArea">
            <div id="LogoImg"><img border="0" src="${basePath}style/blue/images/logo.png" /></div>
            <div id="LoginInfo">
                <table border=0 CELLSPACING=0 CELLPADDING=0 width=100%>
                	<tr>
                		<td colspan="3"><!-- 显示错误 -->
							<font color="red"><s:fielderror/>
							</font>
							${message }
                		</td>
                	</tr>
                    <tr>
                        <td width=45 class="Subject"><img border="0" src="${basePath}style/blue/images/login/userId.gif" /></td>
                        <td>
                        	<s:textfield name="loginName" size="20" tabindex="1" cssClass="TextField required" id="loginNameInput" />
                        </td>
                        <td rowspan="2" style="padding-left:10px;">
                        	<%--
                        	<input type="image" tabindex="3" src="${basePath}style/blue/images/login/userLogin_button.gif" />
                        	 --%>
                        	<img border="0" src="${basePath}style/blue/images/login/userLogin_button.gif" onclick="javascript:document.forms[0].submit();"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="Subject"><img border="0" src="${basePath}style/blue/images/login/password.gif" /></td>
                        <td><s:password name="passWord" id="aa" size="20" tabindex="2" showPassword="false" cssClass="TextField required" /></td>
                    </tr>
                </table>
            </div>
            <div id="CopyRight"><a href="javascript:void(0)">&copy; 2010 版权所有 struggle</a></div>
        </div>
    </div>
    </s:form>
</body>

</html>

