<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yxy.tspaceX.bean.Room" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="expire" content="0"/>
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="TSPACE空间管理系统,管理房间">
<meta http-equiv="description" content="TSPACE空间管理系统管理房间">
<title>TSpace空间网管理员系统</title>
<!-- css -->
<link rel="stylesheet" href="/tspaceX/css/admin/bootstrap.min.css">
<link rel="stylesheet" href="/tspaceX/css/admin/adminmanageroom.css">
</head>
<body>

	<jsp:include page="adminheadnav.jsp" flush="true"/>
	
	<div class="roommanage">
		<div class="displaymatrix">
			<a href="/tspaceX/a/insertroom"><img class="imgclass" src="/tspaceX/css/admin/images/addroom.jpg"></a>
		</div>
		<%
		
			// System.out.println("roompage.jsp");
			
			List<HashMap<String, Object>> roomlist = (List<HashMap<String, Object>>) request.getAttribute("roomlist");
			for (int i = 0; i < roomlist.size(); i ++) {
				HashMap<String, Object> room = roomlist.get(i);
				out.println("<div class=\"displaymatrix\">");
				out.println("<img class=\"imgclass\" alt=\"\" src=\"" + (String)room.get("pictureroom") + "\"/>");
				out.println("<span class=\"textclass\"><strong>房间名:</strong><font>" + room.get("nameroom") + "</font></span>");
				out.println("<span class=\"textclass\"><strong>房间地址:</strong><font>" + room.get("addressroom") + "</font></span>");
				out.println("<span class=\"textclass\"><strong>描述:</strong><font>" + room.get("descriptionroom") + "</font></span>");
				out.println("<input style=\"margin:2px\" type=\"button\" id=\"button\" class=\"btn btn-success\" value=\"点击编辑\" onclick='queryId("+  room.get("idroom") + ")' ></td>");
				out.println("<input style=\"margin:2px\" type=\"button\" id=\"button\" class=\"btn btn-danger\" value=\"删除\" onclick=\"deleteId(" + room.get("idroom") + ",'" + room.get("nameroom") + "')\" ></td>");	
				out.println("</div>");
			}
			
		%>
	</div>
	
	<script src="/tspaceX/js/admin/adminmanageroom.js"></script>

</body>
</html>