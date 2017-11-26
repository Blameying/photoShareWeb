<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.photoshare.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>用户信息</title>
</head>
<body>
<%
	User user=(User)request.getAttribute("user");
%>
<p>用户名：<%=user.getName() %></p>
<p>信息：<%=user.getInfo() %></p>
<p>注册日期：<%=user.getBuildtime() %></p>
<p>上次登录时间：<%=user.getLast_login()%></p>
<p>上传量<%=user.getPost_count() %></p>

</body>
</html>