<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title></title>
	<LINK href="${basePath}style/blue/statusbar.css" type=text/css rel=stylesheet>
</head>

<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>

<div id="StatusBar">
    <div id="Online">
    	在线人员：共 <span class="OnlineUser" id="onlineUserNum"></span> 人<span class="OnlineView">
        <a href="javascript:void(0)">[查看在线名单]</a>
</span></div>

    <div id="Info">
    	<a href="${basePath}" title = "首页" target=_blank >首页</a> |
        <a href="${basePath}/bbs" title = "BBS" target=_blank >BBS</a>
    </div>

    <DIV id=DesktopText>
        <a href="javascript:void(0)"><img border="0" src="${basePath}style/images/top/text.gif"/> 便笺</a>

        <span id=TryoutInfo>

        </span>
        <span id="Version">
            <a href="javascript:void(0)">
            	<img border="0" width="11" height="11" src="${basePath}style/images/top/help.gif" />
            	<img border="0" width="40" height="11" src="${basePath}style/blue/images/top/version.gif" />
            </a>
        </span>
    </DIV>
</div>

</body>
</html>
