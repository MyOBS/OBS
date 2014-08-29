<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>OBS</title>
	<%@ include file="/WEB-INF/jsps/public/commons.jspf"%>	
	<script type="text/javascript" src="${basePath}script/jquery_treeview/jquery.cookie.js"></script>
</head>

	<frameset rows="100,*,25" framespacing=0 border=0 frameborder="0">
		<frame noresize name="TopMenu" scrolling="no" src="${basePath}home_top.action">
		<frameset cols="180,*" id="resize">
			<frame noresize name="menu" scrolling="yes" src="${basePath}home_left.action">
			<frame noresize name="right" scrolling="yes" src="${basePath}home_right.action">
		</frameset>
		<frame noresize name="status_bar" scrolling="no" src="${basePath}home_bottom.action">
	</frameset>

	<noframes><body>
</body>
</noframes></html>



