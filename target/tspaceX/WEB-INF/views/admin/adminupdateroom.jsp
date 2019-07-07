<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.yxy.tspaceX.bean.School" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="TSPACE空间管理系统,更新房间">
<meta http-equiv="description" content="TSPACE空间管理系统更新房间">
<title>TSpace空间管理系统</title>
<!-- css -->
<link rel="stylesheet" href="/tspaceX/css/admin/bootstrap.min.css">
<link rel="stylesheet" href="/tspaceX/css/admin/admininsertroom.css">
</head>
<body>
	<jsp:include page="adminheadnav.jsp" flush="true"/>
     <form name="form1" id="form1">  
    	<h2>更新房间</h2>
    	<% 
    		HashMap<String, Object> roominfo = (HashMap<String, Object>) request.getAttribute("roominfo");
    		if (roominfo == null) {
    			return ;
    		}
    	%>
    	<div><input type="text" id="roomid" name="roomid" type="hidden"></div>
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
        <div><input type="text" id="roomname" name="roomname" placeholder="房间名"></div>  
        <div><input type="text" id="roomaddress" name="roomaddress" placeholder="房间地址"></div>
        <div><input type="text" id="roomcapacity" name="roomcapacity" placeholder="房间容量（填写一个数字）"></div>
        <select name="roomtype" id="roomtype" class="roomtype" placeholder="房间类型">
			<option value="0" selected="selected">请选择房间类型</option>
			<%
				List<HashMap<String, Object>> roomtypelist = (List<HashMap<String, Object>>) request.getAttribute("roomtypelist");
				for (int i = 0; i < roomtypelist.size(); i ++) {
					HashMap<String, Object> roomtype = roomtypelist.get(i);
			%>
			<option value="<% out.print(roomtype.get("idroomtype")); %>">
				<% out.print(roomtype.get("typename")); %>
			</option>
			<% } %>
		</select>
        <div><textarea rows="5" name="roomdescription" id="roomdescription" placeholder="房间描述（不多于100字）" style="resize:none;"></textarea></div>
        <div><input type="file" onchange="loadpreview(this)" name="roompicture" id="roompicture"></div>
        <img id="preview" src="" alt="房间图片"></img>
        <p style="color:red;">*图片大小不超过4MB</p>
        <div><input type="button" id="b1" name="b1" value="submit" onclick="fsubmit()"></div>  
    </form>  

    <script type="text/javascript">
    
		document.getElementById("roomid").value = <%=roominfo.get("idroom")%>;
		// document.getElementById("school").value = data.school;
		document.getElementById("roomname").value = '<%=roominfo.get("nameroom")%>';
		document.getElementById("roomaddress").value = '<%=roominfo.get("addressroom")%>';
		document.getElementById("roomcapacity").value = <%=roominfo.get("capacityroom")%>;
		// document.getElementById("roomtype").value = <%=roominfo.get("typename")%>;
		document.getElementById("roomdescription").value = '<%=roominfo.get("descriptionroom")%>';
		document.getElementById("preview").src = '<%=roominfo.get("pictureroom")%>';

    </script>
    
    <script src="/tspaceX/js/admin/adminupdateroom.js"></script>

</body>
</html>