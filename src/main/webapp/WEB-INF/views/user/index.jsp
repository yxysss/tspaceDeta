<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>TSpace空间网</title>
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
name="viewport" id="viewport"/>
<!-- 禁用缓存 -->
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0"/>
<meta http-equiv="keywords" content="TSPACE,主页" />
<meta http-equiv="content" content="TSPACE主页" />
<link type="text/css" rel="stylesheet" href="/tspaceX/css/usermobile/usermobile.css">
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
	
	<div data-role="page" id="mainpage">
		<link type="text/css" rel="stylesheet" href="/tspaceX/css/usermobile/index.css">
		<div data-role="header">
			<h1><strong>TSpace空间网</strong></h1>
		</div>
		
		<div data-role="content">
			<div id="myCarousel" class="carousel slide">
				
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				
				<div class="carousel-inner">
				
					<div class="item active">
						<img class="carouselimage" src="/tspaceX/css/usermobile/images/carousel/lichun.jpg" alt="First Slide">
					</div>
					
					<div class="item">
						<img class="carouselimage" src="/tspaceX/css/usermobile/images/carousel/xiaoman.jpg" alt="Second Slide">
					</div>
					
					<div class="item">
						<img class="carouselimage" src="/tspaceX/css/usermobile/images/carousel/bailu.jpg" alt="Third Slide">
					</div>
					
				</div>
				
			</div>
			
			<div class="classifygroup">
				<a class="indexbutton" data-role="button" data-icon="search" data-iconpos="left" href="/tspaceX/notice">TSPACE是什么？</a>
			</div>
			
			<div id="displayarea">
				<%
				
					// request.setCharacterEncoding("utf-8");
					
					List<HashMap<String, Object>> roomlist = (List<HashMap<String, Object>>) request.getAttribute("roomlist");
					if (roomlist == null) {
						
					} else {
						for (int i = 0; i < roomlist.size(); i ++) {
							HashMap<String, Object> roominfo = roomlist.get(i);
				%>
				<div class="divclass" onclick="query(<% out.print(roominfo.get("idroom")); %>)">
					<img class="imgclass" alt="roomimage" src="<% out.print(roominfo.get("thumbnailroom"));%>"/><br/>
					<font class="textclass">房间名：<% out.print(roominfo.get("nameroom")); %></font><br/>
					<font class="textclass">房间地址：<% out.print(roominfo.get("addressroom")); %></font><br/>
					<font class="textclass">房间类型：<% out.print(roominfo.get("typename")); %></font><br/>
					<font class="textclass">房间容量：<% out.print(roominfo.get("capacityroom")); %></font>
				</div>
				<% } } %>
			</div>
			
		
		</div>
		
		<div data-role="footer" data-tap-toggle="false" data-theme="b" data-position="fixed">
			<div data-role="navbar">
				<ul>
					<li><a id="nav0" data-icon="home">首页</a></li>
					<li><a id="nav1" data-ajax="false" href="/tspaceX/myapplication" data-icon="grid">我的申请</a></li>
					<li><a id="nav2" href="/tspaceX/mytspace" data-icon="user">我的TSpace</a></li>
				</ul>
			</div>
		</div>
		<script src="/tspaceX/js/usermobile/index.js"></script>
	</div>
	
	
	
</body>
</html>
