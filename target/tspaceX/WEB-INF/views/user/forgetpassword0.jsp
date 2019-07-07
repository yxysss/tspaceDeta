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
<meta http-equiv="keywords" content="TSPACE,忘记密码" />
<meta http-equiv="content" content="TSPACE忘记密码" />
<meta http-equiv="refresh"content="800;url=https://www.tspace.top">
<link type="text/css" rel="stylesheet" href="/tspaceX/css/usermobile/usermobile.css">
<script src="/tspaceX/js/usermobile/jquery.min.js"></script>
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
		<div data-role="popup" data-dismissible="false" id="unnull" class="ui-content pop" data-position-to="window">
  			<p>请输入用户名！</p>
  			<a href="#" class="ui-btn ui-corner-all" data-rel="back">确定</a>
		</div>
		
		<div data-role="popup" data-dismissible="false" id="unerror" class="ui-content pop" data-position-to="window">
  			<p>用户名不存在，请重新输入<p>
  			<a href="#" class="ui-btn ui-corner-all" data-rel="back">确定</a>
		</div>
		
		
		<link type="text/css" rel="stylesheet" href="/tspaceX/css/usermobile/forgetpassword.css">
		
		<div data-role="header">
			<a onclick="goback()" data-icon="arrow-l" data-rel="back">返回</a>
			<h1>忘记密码</h1>
		</div>
		
		<div data-role="content">
			<input type="text" name="username" id="username" class="username" placeholder="用户名"/>
			<a data-role="button" name="submit" id="submit" class="submit">确认</a>
		</div>
		
		<div data-role="footer">
			<h4>Powered By TSpace</h4>
		</div>
		
		<script src="/tspaceX/js/usermobile/forgetpassword0.js"></script>
	
	</div>
</body>
</html>