<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<meta http-equiv="keywords" content="TSPACE,注册成功" />
<meta http-equiv="content" content="TSPACE注册成功" />
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
		
		<div data-role="header">
			<h1>TSpace</h1>
		</div>
		
		<div data-role="content">
			<h4>注册成功！</h4>
			<a href="/tspaceX/" id="index" name="index" class="index">返回首页</a>
			<a href="/tspaceX/login" id="login" name="login" class="login">立即登录</a>
		</div>
		
		<div data-role="footer">
			<h4>Powered By TSpace</h4>
		</div>
		
	</div>
	
	
</body>
</html>