<%@page import="java.util.*" pageEncoding="UTF-8"%>
<%!private final static transient org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog("404_jsp");%>
<%
	//String errorUrl = RequestUtil.getErrorUrl(request);
	response.addHeader("__404_error","true");
%>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<h2>找不到该页面</h2>
		<br/>
		<b>url:<%  
  String   path   =   request.getContextPath();  
  String   basePath   =   request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
  out.println(basePath);  
  %></b>
	</body>
	
</html>