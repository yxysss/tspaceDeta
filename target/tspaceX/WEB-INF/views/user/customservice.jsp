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
<meta http-equiv="keywords" content="TSPACE,联系客服" />
<meta http-equiv="content" content="TSPACE联系客服" />
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
	
		<div data-role="popup" data-dismissible="false" id="submitsuccess" class="ui-content pop" data-position-to="window">
  			<p>提交成功！</p>
  			<a href="#" class="ui-btn ui-corner-all" data-rel="back">确定</a>
		</div>
		
		<div data-role="popup" data-dismissible="false" id="submitfail" class="ui-content pop" data-position-to="window">
  			<p>服务器繁忙，请稍后再试！</p>
  			<a href="#" class="ui-btn ui-corner-all" data-rel="back">确定</a>
		</div>
		
		<div data-role="header">
			<a data-icon="arrow-l" onclick="goback()">返回</a>
			<h1>TSpace客服</h1>
		</div>
		
		<div data-role="content">
			<input placeholder="联系方式，仅限邮箱" name="contactmail" id="contactmail" class="contactmail"/>
			<fieldset data-role="controlgroup" data-type="horizontal">
				<input type="radio" name="type" id="problem" value="0" checked="checked">
				<label for="problem">问题报告</label>
				<input type="radio" name="type" id="advice" value="1"> 
				<label for="advice">改进建议</label>
			</fieldset>
			<p name="notice" id="notice" class="notice" style="color:red"></p>
			<textarea placeholder="详细说明，长度在100字以内，提交后我们会尽快给您回复邮件" name="detail" id="detail" class="detail" style="resize:none"></textarea>
			<a data-role="button" name="submit" id="submit" class="submit">提交</a>
			<p><br>HINT：如果您的提交出现问题或者未能及时收到回复，您也可直接发送邮件到tspaceofficial@163.com，工作人员将尽快和您联系！</p>
		</div>
		
		<div data-role="footer">
			<h4>Powered By TSpace</h4>
		</div>
		
		<script src="/tspaceX/js/usermobile/customservice.js"></script>
		
	</div>
	
	
</body>
</html>