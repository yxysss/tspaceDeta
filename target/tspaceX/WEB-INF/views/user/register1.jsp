<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.yxy.tspaceX.bean.School" %>
<%@ page import="java.util.List" %>    
<%@ page import="java.util.Date" %>
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
<meta http-equiv="keywords" content="TSPACE,注册" />
<meta http-equiv="content" content="TSPACE注册" />
<!-- 设置20分钟自动刷新，短信验证通过有效时间为20分钟 -->
<meta http-equiv="refresh" content="1200;url=https://www.tspace.top">
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
<body>

	<div data-role="page">
		<script src="/tspaceX/js/usermobile/code.js"></script>
		<link type="text/css" rel="stylesheet" href="/tspaceX/css/usermobile/register.css">
		
		<div data-role="popup" data-dismissible="false" id="fail" class="ui-content pop" data-position-to="window">
  			<p>注册失败！</p>
  			<a href="#" class="ui-btn ui-corner-all" data-rel="back">确定</a>
		</div>
		
		<div data-role="popup" data-dismissible="false" id="duplicate" class="ui-content pop" data-position-to="window">
  			<p>学号已被他人使用，如有问题，请在"我的TSpace"->"联系客服"页面中反馈！</p>
  			<a href="#" class="ui-btn ui-corner-all" data-rel="back">确定</a>
		</div>
		
		<div data-role="header">
			<a onclick="goback()" data-icon="arrow-l" data-rel="back">返回</a>
			<h1>TSpace用户注册</h1>
		</div>
		
		<div data-role="content">
			<p name="notice" id="notice" class="notice"></p>
			<select name="school" id="school" class="school" placeholder="学校">
				<option value="0" selected="selected">请选择学校</option>
				<%
					List<School> schoollist = (List<School>) request.getAttribute("schoollist");
					for (int i = 0; i < schoollist.size(); i ++) {
						School school = schoollist.get(i);
				 %>
				<option value="<% out.print(school.getidschool()); %>">
					<% out.print(school.getschoolname()); %>
				</option>
				<% } %>
			</select>
			<fieldset data-role="controlgroup" data-type="horizontal">
				<input type="radio" name="identity" id="student" value="0" checked="checked">
				<label for="student">学生</label>
				<input type="radio" name="identity" id="teacher" value="1"> 
				<label for="teacher">老师</label>
			</fieldset>
			<input type="text" name="username" id="username" class="username" placeholder="学号/工号">
			<input type="text" name="name" id="name" class="name" placeholder="姓名">
			<input type="password" name="password" id="password" class="password" placeholder="密码" onBlur="pwblur()" onFocus="pwfocus()">
			<p name="pwnotice" id="pwnotice" class="pwnotice"></p>
			<input type="password" name="confirmpassword" id="confirmpassword" class="confirmpassword" placeholder="确认密码">		
			<a data-role="button" name="register" id="register" class="register">注册</a>
		</div>
		
	
		<div data-role="footer">
			<h4>Powered By TSpace</h4>
		</div>
		<script src="/tspaceX/js/usermobile/register1.js"></script>
	</div>

</body>
</html>