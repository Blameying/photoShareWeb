<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.photoshare.model.User"%>
<% 
	User user=(User)request.getSession().getAttribute("user");
	if(user==null)
		response.sendRedirect("./index.jsp"); 
%>