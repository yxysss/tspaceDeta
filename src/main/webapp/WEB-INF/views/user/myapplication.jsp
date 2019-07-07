<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.yxy.tspaceX.bean.Application"  %>
<%@ page import="com.yxy.tspaceX.util.dateformat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Date" %>
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
<meta http-equiv="keywords" content="TSPACE,我的申请" />
<meta http-equiv="content" content="TSPACE我的申请" />
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

	<div data-role="page" id="myapplication-running">
		<link type="text/css" rel="stylesheet" href="/tspaceX/css/usermobile/myapplication.css">
		<div data-role="popup" data-dismissible="false" id="detailpopup" class="ui-content pop" data-position-to="window">
  			<h3 class="detailhead">申请详情</h3>
  			<div class="pop">
	  			<p class="detailfont">状态:<span id="cstate"></span></p>
	  			<p class="detailfont">申请时间:<span id="capplytime"></span></p>
	  			<p class="detailfont">审批时间:<span id="caudittime"></span></p>
	  			<p class="detailfont">开门密码:<span id="cpassword"></span></p>   			
	  			<a href="#" class="ui-btn ui-corner-all" data-rel="back">确定</a>
  			</div>
		</div>
		<div data-role="header">
			<div data-role="navbar" class="navbarclass">
				<ul>
					<li><a id="running0">进行中</a></li>
					<li><a id="past0" href="#myapplication-past">历史申请</a></li>		
				</ul>
			</div>
		</div>
		<div data-role="content">
			<ul data-role="listview">
				<%
					List<HashMap<String,Object>> runningapplicationlist = (List<HashMap<String,Object>>) request.getAttribute("runningapplicationlist");
					if (runningapplicationlist == null) {
						out.print("<li>系统异常</li>");
					} else {
						if (runningapplicationlist.size() == 0) {
							out.print("<li>暂无申请</li>");
						} else {
							for (int i = 0; i < runningapplicationlist.size(); i ++) {
								HashMap<String,Object> entry = runningapplicationlist.get(i);
								String nameroom = (String) entry.get("nameroom");
								String pictureroom = (String) entry.get("pictureroom");
								Integer idapplication = (Integer) entry.get("idapplication");
								Date starttime = (Date) entry.get("starttime");
								Date endtime = (Date) entry.get("endtime");
								Integer state = (Integer) entry.get("state");
								String applytime = dateformat.timeToString((Date) entry.get("applytime"));
								String audittime = dateformat.timeToString((Date) entry.get("audittime"));
								String password = (String) entry.get("password"); 
				%>
				<li>
					<!-- js方法传递参数时，如果参数是string类型，需要在参数前后加'' -->
					<a onclick="queryapplication0(<%=state%>,'<%=applytime%>','<%=audittime%>','<%=password%>')">
						<img class="imageclass" src="<% out.print(pictureroom); %>" alt="roomimage"/>
						<h4><% out.print(nameroom); %></h4>
						<p>开始时间:<% out.print(dateformat.timeToString(starttime)); %></p>
						<p>结束时间:<% out.print(dateformat.timeToString(endtime)); %></p>
						<p>状态:<span class="<%
							if (state == 0) {
								out.print("acceptclass");
							}
							if (state == 1) {
								out.print("declineclass");
							}
							if (state == 2) {
								out.print("unsettleclass");
							}
						%>"><% 
							if (state == 0) {
								out.print("接受");
							}
							if (state == 1) {
								out.print("拒绝");
							}
							if (state == 2) {
								out.print("待审核");
							} 
						%></span></p>
						
						<%
							if (state == 2) {
								out.print("<a onclick=\"commitcancel(" + idapplication + ")\" style=\"background:red !important;border:0 !important;\" data-role=\"button\" data-icon=\"delete\"></a>");
							}
						%>
					</a>
				</li>
				<% } } }%>
			</ul>
			
		</div>
		<div data-role="footer" data-tap-toggle="false" data-theme="b" data-position="fixed">
			<div data-role="navbar">
				<ul>
					<li><a id="nav00" href="/tspaceX/" data-icon="home">首页</a></li>
					<li><a id="nav01" data-icon="grid">我的申请</a></li>
					<li><a id="nav02" href="/tspaceX/mytspace" data-icon="user">我的TSpace</a></li>
				</ul>
			</div>
		</div>
		<div data-role="popup" data-dismissible="false" id="cancelpopup" class="ui-content pop" data-position-to="window">
  			<p>确定取消这个申请吗？</p>
  			<a href="#" class="ui-btn ui-corner-all" data-rel="back" onclick="submitcancel()">确定</a>
  			<a href="#" class="ui-btn ui-corner-all" data-rel="back">点错了</a>
		</div>
		<div data-role="popup" data-dismissible="false" id="cancelfailpopup" class="ui-content pop" data-position-to="window">
  			<p>取消申请失败！</p>
  			<a href="#" class="ui-btn ui-corner-all" data-rel="back" onclick="reloadwindow()">确定</a>
		</div>
		<script src="/tspaceX/js/usermobile/myapplication0.js"></script>
	</div>
	
	<div data-role="page" id="myapplication-past">
		<link type="text/css" rel="stylesheet" href="/tspaceX/css/usermobile/myapplication.css">
		<div data-role="header">
			<div data-role="navbar">
				<ul>
					<li><a id="running1" href="/tspaceX/myapplication" data-ajax="false">进行中</a></li>
					<li><a id="past1">历史申请</a></li>		
				</ul>
			</div>
		</div>
		<div data-role="content">
			<ul data-role="listview">
				<%					
					List<HashMap<String, Object>> pastapplicationlist = (List<HashMap<String, Object>>) request.getAttribute("pastapplicationlist");
					if (pastapplicationlist == null) {
						out.print("<li>系统异常</li>");
					} else {
						if (pastapplicationlist.size() == 0) {
							out.print("<li>暂无申请</li>");
						} else {
							for (int i = 0; i < pastapplicationlist.size(); i ++) {
								HashMap<String, Object> entry = pastapplicationlist.get(i);
								String nameroom = (String) entry.get("nameroom");
								String pictureroom = (String) entry.get("pictureroom");
								Integer idapplication = (Integer) entry.get("idapplication");
								Date starttime = (Date) entry.get("starttime");
								Date endtime = (Date) entry.get("endtime");
								// System.out.println(entry.get("state"));
								Integer state = (Integer) entry.get("state");
				%>
				<li>
					<a>
						<img class="imageclass" src="<% out.print(pictureroom); %>" alt="roomimage"/>
						<h4><% out.print(nameroom); %></h4>
						<p>开始时间:<% out.print(dateformat.timeToString(starttime)); %></p>
						<p>结束时间:<% out.print(dateformat.timeToString(endtime)); %></p>
						<p>状态:<span class="<%
							if (state == 0) {
								out.print("acceptclass");
							}
							if (state == 1) {
								out.print("declineclass");
							}
							if (state == 2) {
								out.print("unsettleclass");
							}
							if (state == 3) {
								out.print("cancelclass");
							}
						%>"><% 
							if (state == 0) {
								out.print("接受");
							}
							if (state == 1) {
								out.print("拒绝");
							}
							if (state == 2) {
								out.print("待审核");
							} 
							if (state == 3) {
								out.print("已取消");
							}
						%></span></p>
					</a>
				</li>
				<% } } }%>
			</ul>
		</div>
		<div data-role="footer" data-tap-toggle="false" data-theme="b" data-position="fixed">
			<div data-role="navbar">
				<ul>
					<li><a id="nav10" href="/tspaceX/" data-icon="home"">首页</a></li>
					<li><a id="nav11" data-icon="grid">我的申请</a></li>
					<li><a id="nav12" href="/tspaceX/mytspace" data-icon="user">我的TSpace</a></li>
				</ul>
			</div>
		</div>
		<script src="/tspaceX/js/usermobile/myapplication1.js"></script>
	</div>
	
</body>
</html>