<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.yxy.tspaceX.bean.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="expire" content="0"/>
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="TSPACE空间管理系统,管理用户">
<meta http-equiv="description" content="TSPACE空间管理系统管理用户">
<title>TSpace空间网管理员系统</title>
<!-- css -->
<link rel="stylesheet" href="/tspaceX/css/admin/bootstrap.min.css">
<link rel="stylesheet" href="/tspaceX/css/admin/adminmanageuser.css">
</head>
<body>

	<jsp:include page="adminheadnav.jsp" flush="true"/>
	<div style="float:right;width:400px;height:30px;margin-right:50px;margin-top:20px" class="form-group">
		<input style="width:270px;height:30px" type="text" id="searchuser" name="searchuser" class="searchuser" placeholder="输入要搜索的学号/工号或姓名">
		<input style="float:right;width:60px;height:30px" type="button" id="search" class="btn btn-success" value="搜索">
	</div>
	<table class="table1">
		<thead>
			<tr>
				<td class="tdclass">用户序号</td>
				<td class="tdclass">学号/工号</td>
				<td class="tdclass">姓名</td>
				<td class="tdclass">身份</td>
				<td class="tdclass">手机</td>
				<td class="tdclass">学校</td>
				<td class="tdclass">加入黑名单</td>
			</tr>
		</thead>
		<tbody>
			<%
				List<HashMap<String, Object>> userlist = (List<HashMap<String, Object>>) request.getAttribute("userlist");
				String searchentry = (String) request.getAttribute("searchentry");
				Integer pagenum = (Integer) request.getAttribute("page");
				Integer pagecount = (Integer) request.getAttribute("pagecount");
				if (userlist == null) return ;
				for (int i = 0; i < userlist.size(); i ++) {
					HashMap<String, Object> userinfo = userlist.get(i);
					out.println("<tr><td class=\"tdclass0\">"+i+"</td>");
					out.println("<td class=\"tdclass0\">"+userinfo.get("username")+"</td>");
					out.println("<td class=\"tdclass0\">"+userinfo.get("name")+"</td>");
					String identity = "学生";
					if ((Boolean) userinfo.get("identity") == true) identity = "老师";
					out.println("<td class=\"tdclass0\">"+identity+"</td>");
					out.println("<td class=\"tdclass0\">"+userinfo.get("mobile")+"</td>");
					// 学校不存在，说明系统故障
					// if (userinfo.get("schoolname") == null) continue;
					out.println("<td class=\"tdclass0\">"+userinfo.get("schoolname")+"</td>");
					out.println("<td class=\"tdclass0\"><input type=\"button\" onclick=\"deleteuser('" +userinfo.get("mobile") + "')\" class=\"btn btn-danger\" value=\"加入黑名单\"></td></tr>");
				}
			 %>
		</tbody>
	</table>
	
	<p><br>当前第<%=pagenum%>页，共<%=pagecount%>页</p>
	<% 
		if (pagenum != 1) {
			out.println("<a href=\"/tspaceX/a/manageuser?searchentry="+searchentry+"&page="+(pagenum-1)+"\">上一页</a>");
		}
		if (pagenum < pagecount) {
			out.println("<a href=\"/tspaceX/a/manageuser?searchentry="+searchentry+"&page="+(pagenum+1)+"\">下一页</a>");
		}
	%>
	
	<script src="/tspaceX/js/admin/adminmanageuser.js"></script>

</body>
</html>