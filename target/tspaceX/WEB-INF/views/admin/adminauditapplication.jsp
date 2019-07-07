<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.yxy.tspaceX.bean.Application" %>
<%@ page import="com.yxy.tspaceX.bean.Room" %>
<%@ page import="com.yxy.tspaceX.util.dateformat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="TSPACE空间管理系统,审核申请">
<meta http-equiv="description" content="TSPACE空间管理系统审核申请">
<title>TSpace空间网管理员系统</title>
<!-- css -->
<link rel="stylesheet" href="/tspaceX/css/admin/bootstrap.min.css">
<link rel="stylesheet" href="/tspaceX/css/admin/adminaudit.css">
</head>
<body>

	<jsp:include page="adminheadnav.jsp" flush="true"/>
	
	<table class="table1">
		<thead>
			<tr>
				<td class="tdclass">申请序号</td>
				<td class="tdclass">申请房间</td>
				<td class="tdclass">申请人</td>
				<td class="tdclass">参与人员</td>
				<td class="tdclass">申请理由</td>
				<td class="tdclass">申请时间</td>
				<td class="tdclass">同意</td>
				<td class="tdclass">拒绝</td>
				<td class="tdclass">冲突</td>
			</tr>
		</thead>
		<tbody>
			<%
				// out.println("id="+id);
				List<HashMap<String, Object>> unsettleapplicationlist = (List<HashMap<String, Object>>) request.getAttribute("unsettleapplicationlist");
				HashMap<String, Object> chosenapplication = (HashMap<String, Object>) request.getAttribute("chosenapplication");
				Integer chosenidapplication = (Integer) request.getAttribute("chosenidapplication");
				Integer pagenum = (Integer) request.getAttribute("page");
				Integer pagecount = (Integer) request.getAttribute("pagecount");
				Long chosenstarttime = null;
				if (chosenapplication != null) {
					chosenstarttime = ((Date) chosenapplication.get("starttime")).getTime();
				}
				Long chosenendtime = null;
				if (chosenapplication != null) {
					chosenendtime = ((Date) chosenapplication.get("endtime")).getTime();
				}
				HashMap<String, Object> entry = null;
				String color;
				for (int i = 0; i < unsettleapplicationlist.size(); i ++) {
					entry = unsettleapplicationlist.get(i);
					color = "white";
					out.println("<tr>");
					Long starttime = ((Date) entry.get("starttime")).getTime();
					Long endtime = ((Date) entry.get("endtime")).getTime();
					if (chosenapplication != null) {
						if (((Integer)entry.get("idroom")).intValue() == ((Integer)chosenapplication.get("idroom")).intValue()) {
							if (starttime<chosenendtime-5*60*1000 && starttime>chosenstarttime-5*60*1000) {
								color = "red";
							}
							if (endtime<chosenendtime+5*60*1000 && endtime>chosenstarttime+5*60*1000) {
								color = "red";
							}
							if (starttime<chosenstarttime-5*60*1000 && endtime > chosenendtime+5*6*1000) {
								color = "red";
							}
						}
					}
					out.println("<td class=\"tdclass0\" style=\"background:"+color+"\">"+i+"</td>");
					// 房间不存在，说明系统出现故障
					// if (rooms.get(apply.getidroom()) == null) continue;
					out.println("<td class=\"tdclass0\">"+(Integer)entry.get("idroom")+"<br/>"+ (String) entry.get("nameroom") +"</td>");
					out.println("<td class=\"tdclass0\">"+(String)entry.get("username") + "," + (String)entry.get("name") +"</td>");
					if (((String) entry.get("participants")).length() > 25) {
						out.println("<td class=\"tdclass0\">"+((String) entry.get("participants")).substring(0,25)+"<br>"+((String) entry.get("participants")).substring(25)+"</td>");
					}
					else {
						out.println("<td class=\"tdclass0\">"+(String) entry.get("participants")+"</td>");
					}
					if (((String) entry.get("reason")).length() > 25) {
						out.println("<td class=\"tdclass0\">"+((String) entry.get("reason")).substring(0,25)+"<br>"+((String) entry.get("reason")).substring(25)+"</td>");
					} else {
						out.println("<td class=\"tdclass0\">"+(String) entry.get("reason")+"</td>");
					}
					
					out.println("<td class=\"tdclass0\">"+dateformat.timeToString((Date) entry.get("starttime"))+"<br/>"+dateformat.timeToString((Date) entry.get("endtime"))+"</td>");
					out.println("<td class=\"tdclass0\"><input type=\"button\" onclick='acceptapplication(" + (Integer) entry.get("idapplication") + "," + i + ")' class=\"btn btn-success\" value=\"同意\"></td>");
					out.println("<td class=\"tdclass0\"><input type=\"button\" onclick='decline(" + (Integer) entry.get("idapplication") + ","+ i + ")' class=\"btn btn-danger\" value=\"拒绝\"></td>");
					out.println("<td class=\"tdclass0\"><input type=\"button\" onclick='conflict(" + (Integer) entry.get("idapplication") +"," + pagenum+")' class=\"btn btn-warning\" value=\"冲突\"></td>");
					out.println("</tr>");
				}
			%>
		</tbody>
	</table>
	
	<p><br>当前第<%=pagenum%>页，共<%=pagecount%>页</p>
	<%
		if (pagenum != 1) {
			out.println("<a href=\"/tspaceX/a/auditapplication?page="+(pagenum-1)+(chosenidapplication!=null?"&idapplication="+chosenidapplication:"")+"\">上一页</a>");
		}
		
		if (pagenum < pagecount) {
			out.println("<a href=\"/tspaceX/a/auditapplication?page="+(pagenum+1)+(chosenidapplication!=null?"&idapplication="+chosenidapplication:"")+"\">下一页</a>");
		}
	 %>
	
	<script src="/tspaceX/js/admin/adminauditapplication.js"></script>

</body>
</html>