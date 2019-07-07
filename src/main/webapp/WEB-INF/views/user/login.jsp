<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset= utf-8">
<title>TSpace空间网</title>
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
name="viewport" id="viewport"/>
<!-- 禁用缓存 -->
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0"/>
<meta http-equiv="keywords" content="TSPACE,登录" />
<meta http-equiv="content" content="TSPACE登录" />
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
	<div data-role="page">
	
		<script src="/tspaceX/js/usermobile/code.js"></script>
		<div data-role="header">
			<a href="#"  data-icon="arrow-l" onclick="goback()">返回</a>
			<h1>TSpace用户登录</h1>
		</div>
		
		<div data-role="content">
			<input type="text" placeholder="用户名" name="username" id="username" class="username">
			<input type="password" placeholder="密码" name="password" id="password" class="password">
			<a data-role="button" data-corners="true" id="submit" name="submit" class="submit">提交</a>
			<a href="/tspaceX/forgetpassword0" id="forgetpassword" name="forgetpassword" class="forgetpassword">忘记密码</a>
			<a href="/tspaceX/register0" id="register" name="register" class="register">免费注册</a>
		</div>
		
		<div data-role="footer">
			<h4>Powered By TSpace</h4>
		</div>
		
		<div data-role="popup" data-dismissible="false" id="usernameerror" class="ui-content pop" data-position-to="window">
  			<p>用户名不能为空！</p>
  			<a href="#" class="ui-btn ui-corner-all" data-rel="back">确定</a>
		</div>
		
		<div data-role="popup" data-dismissible="false" id="passworderror" class="ui-content pop" data-position-to="window">
  			<p>密码不能为空！</p>
  			<a href="#" class="ui-btn ui-corner-all" data-rel="back">确定</a>
		</div>
		
		<div data-role="popup" data-dismissible="false" id="systemerror" class="ui-content pop" data-position-to="window">
  			<p>系统异常！</p>
  			<a href="#" class="ui-btn ui-corner-all" data-rel="back">确定</a>
		</div>
		
		<div data-role="popup" data-dismissible="false" id="unperror" class="ui-content pop" data-position-to="window">
  			<p>用户名或密码错误！</p>
  			<a href="#" class="ui-btn ui-corner-all" data-rel="back">确定</a>
		</div>
		
		<script src="/tspaceX/js/usermobile/login.js"></script>
	</div>
	
</body>
</html>