<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.photoshare.controler.BuildConnection" import="java.sql.Connection" import="java.io.File" import="com.photoshare.model.User"%>
<% 
	Connection conn=(Connection)request.getSession().getAttribute("connection");
	if(conn==null){
		BuildConnection build =new BuildConnection("jdbc:mysql://localhost:3306/photoweb?useUnicode=true&characterEncoding=utf-8", "root", "nihao@@");
		request.getSession().setAttribute("connection", build.getConnection());
		conn=(Connection)request.getSession().getAttribute("connection");
	}
%>