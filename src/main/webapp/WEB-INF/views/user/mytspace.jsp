<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.yxy.tspaceX.bean.User"  %>
<%@ page import="com.yxy.tspaceX.util.roomupdate" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>TSpace空间网</title>
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
name="viewport" id="viewport"/>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0"/>
<meta http-equiv="keywords" content="TSPACE,个人空间" />
<meta http-equiv="content" content="TSPACE个人空间" />
<script src="/tspaceX/js/usermobile/jquery.min.js"></script><!-- jquery.js 一定要加载在jquery.mobile.js之前 -->
<script>
	$(document).bind("mobileinit", function() {
	    //disable ajax nav
	    $.mobile.ajaxEnabled=false;
	});
</script>
<script src="/tspaceX/js/usermobile/bootstrap.min.js"></script>
<script src="/tspaceX/js/usermobile/jquery.mobile-1.4.5.min.js"></script>
<link type="text/css" rel="stylesheet" href="/tspaceX/css/usermobile/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="/tspaceX/css/usermobile/jquery.mobile-1.4.5.min.css">
</head>
<body>
	<div data-role="page" id="mytspace">
		<link type="text/css" rel="stylesheet" href="/tspaceX/css/usermobile/mytspace.css">
		<div data-role="header">
			<h1><strong>TSpace空间网</strong></h1>
		</div>
		<div data-role="content" class="content">
			<ul data-role="listview">
				<li name="userinfo">
					<%	
						// System.out.println("mytspace session="+session);
						HashMap<String, Object> userinfo = (HashMap<String, Object>) request.getAttribute("userinfo");
						if (userinfo == null) {
							out.println("<a href=\"/tspaceX/login\"><img class=\"imageclass\" src=\"/tspaceX/css/usermobile/images/logo.jpg\" onclick='window.location.href=\"/tspaceX/login\"'>");
							out.println("<h3>点击登录</h3>");
							out.println("<p></p>");
							out.println("<p></p>");
							out.println("<p></p>");
							out.println("<p></p>");
							out.println("<p></p>");
							//out.println("<p></p>");
							out.println("<p></p></a>");
						} else {
							out.println("<img class=\"imageclass\" src=\"/tspaceX/css/usermobile/images/logo.jpg\" alt=\"亲，请设置头像\">");
							String name = (String) userinfo.get("name");
							String username = (String) userinfo.get("username");
							String schoolname = (String) userinfo.get("schoolname");
							out.println("<h3>" + name + "</h3>");
							out.println("<h3>" + username + "</h3>");
							out.println("<p>" + schoolname + "</p>");
						}
					
					%>
				</li>
				<div class="blankclass">
				</div>
				<li><a href="/tspaceX/changepassword" name="changepassword">修改密码</a></li>
				<li><a href="/tspaceX/customservice" name="contactadmin">联系客服</a></li>
				<li><a href="#" name="exit" onclick="exit()">安全退出</a>
			</ul>
		</div>
		<div data-role="footer" data-tap-toggle="false" data-theme="b" data-position="fixed">
			<div data-role="navbar">
				<ul>
					<li><a data-icon="home" id="nav0" href="/tspaceX/">首页</a></li>
					<li><a data-icon="grid" id="nav1" href="/tspaceX/myapplication" data-ajax="false">我的申请</a></li>
					<li><a data-icon="user" id="nav2">我的TSpace</a></li>
				</ul>
			</div>
		</div>
		<script src="/tspaceX/js/usermobile/mytspace.js"></script>
	</div>
</body>
</html>