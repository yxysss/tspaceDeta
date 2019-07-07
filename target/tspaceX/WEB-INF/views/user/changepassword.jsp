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
<meta http-equiv="keywords" content="TSPACE,修改密码" />
<meta http-equiv="content" content="TSPACE修改密码" />
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
		<script src="/tspaceX/js/usermobile/code.js"></script>
		<link type="text/css" rel="stylesheet" href="/tspaceX/css/usermobile/changepassword.css">
		<div data-role="header">
			<a href="#" data-icon="arrow-l" onclick="goback()">返回</a>
			<h1>修改密码</h1>
		</div>
		
		<div data-role="content">
			<p name="notice" id="notice" class="notice"></p>
			<input type="password" name="oldpassword" id="oldpassword" class="oldpassword" placeholder="原密码"/>
			<input type="password" name="newpassword" id="newpassword" class="newpassword" placeholder="新密码"/>
			<input type="password" name="confirmnpw" id="confirmnpw" class="confirmnpw" placeholder="确认新密码"/>
			<a data-role="button" name="submit" id="submit" class="submit">确认</a>
			
		</div>
		
		<div data-role="footer">
			<h4>Powered By TSpace</h4>
		</div>
			
		<div data-role="popup" data-dismissible="false" id="success" class="ui-content pop" data-position-to="window">
			<p>密码修改成功！</p>
			<a href="#" class="ui-btn ui-corner-all" data-rel="back" onclick="goback()">确定</a>
		</div>
		
		<div data-role="popup" data-dismissible="false" id="opfalse" class="ui-content pop" data-position-to="window">
			<p>原密码错误，请重新输入！</p>
			<a href="#" class="ui-btn ui-corner-all" data-rel="back">确定</a>
		</div>
		
		<div data-role="popup" data-dismissible="false" id="error" class="ui-content pop" data-position-to="window">
			<p>密码修改失败！</p>
			<a href="#" class="ui-btn ui-corner-all" data-rel="back">确定</a>
		</div>
		<script src="/tspaceX/js/usermobile/changepassword.js"></script>
	</div>
</body>
</html>