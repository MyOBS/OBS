<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>导航菜单</title>
		<%@ include file="/WEB-INF/jsps/public/commons.jspf"%>    
		<link type="text/css" rel="stylesheet" href="${basePath}style/blue/menu.css" />
		<script type="text/javascript">
			
			function menuClick( menu ){
				$(menu).next().toggle();
			}
		
		</script>
	</head>

	<body style="margin: 0">
	
		<div id="Menu">

			<ul id="MenuUl">
				<%-- 显示一级菜单 --%>
				<s:iterator value="#application.topUrlList">
					<li class="level1">
						<div onClick="menuClick(this);" class="level1Style">
							<img src="${basePath}style/images/MenuIcon/${icon}.gif" class="Icon" />
							${name}
						</div>
						<ul style="" class="MenuLevel2" id="aa">
							<%-- 显示二级菜单 --%>
							<s:iterator value="children">
								<li class="level2">
									<div class="level2Style">
										<img src="${basePath}style/images/MenuIcon/menu_arrow_single.gif" />
										<a target="right" href="${basePath}${url}.action"> ${name}</a>
									</div>
								</li>
							</s:iterator>
						</ul> 
					</li>
				</s:iterator>
				
			</ul>

		</div>
	</body>
</html>