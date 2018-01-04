<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.photoshare.model.Picture" import="com.photoshare.controler.GetPicture" import="java.util.ArrayList"%>
<%@include file="connection.jsp"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>图片分类页面</title>
    <meta name="keywords" content="图片分类页面">
    <meta name="description" content="图片分类页面">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/photoClass.css" rel="stylesheet"></head>

<body>
    <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="#">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
    <header id="navbar">
        <div class="navbar-container">
            <div class="navbar-left">
                <ul class="navbar-list">
                    <li class="navbar-item">
                        <a href="./index.jsp">首页</a>
                    </li>
                    <li class="navbar-item photo-class-nav">
                        <a href="">图片分类</a>
                        <ul class="photo-class-list">
                            <li class="photo-class">
                                <a href="./photoClass0.jsp">建筑商务</a>
                            </li>
                            <li class="photo-class">
                                <a href="./photoClass1.jsp">旅行节庆</a>
                            </li>
                            <li class="photo-class">
                                <a href="./photoClass2.jsp">科技医学</a>
                            </li>
                            <li class="photo-class">
                                <a href="./photoClass3.jsp">行业概念</a>
                            </li>
                            <li class="photo-class">
                                <a href="./photoClass4.jsp">矢量素材</a>
                            </li>
                            <li class="photo-class">
                                <a href="./photoClass5.jsp">生活方式 运动</a>
                            </li>
                            <li class="photo-class">
                                <a href="./photoClass6.jsp">艺术娱乐 美妆健康</a>
                            </li>
                            <li class="photo-class">
                                <a href="./photoClass7.jsp">自然</a>
                            </li>
                            <li class="photo-class">
                                <a href="./photoClass8.jsp">食物饮料</a>
                            </li>
                            <li class="photo-class">
                                <a href="./photoClass9.jsp">动物及宠物</a>
                            </li>
                        </ul>
                    </li>
                    <li class="navbar-item">
                        <a href="">帮助</a>
                    </li>
                    <li class="navbar-item">
                        <a href="">社区</a>
                    </li>
                </ul>
            </div>
            <div class="navbar-search">
                <input type="text" class="search">
            </div>
            <div class="navbar-right">
                <%
            	User user=(User)request.getSession().getAttribute("user");
            	if(user==null){
            %>
              <a href="javascript:;" class="user-name">登录</a>
                <img src="img/login.png" alt="默认头像" title="默认头像" class="default-img">
            <%}else{%>
            	<a class="user-photo" href="./userManageCombine.jsp" style="display:inline-block; width:150px; height:50px;">
            	<%
            		String path=this.getServletContext().getRealPath("/")+"images/"+user.getName()+"/head/user.jpg";
            		File file = new File(path);
            		if(file.exists()){
            	%>
                <img src="images/<%=user.getName()%>/head/user.jpg" alt="用户头像" title="用户头像" class="user-img" style="display:inline-block;">
                <%}else{ %>
                <img src="img/user.jpg" alt="用户头像" title="用户头像" class="user-img" style="display:inline-block;">
                <%} %>
                </a>
            <%}%>
            </div>
        </div>
    </header>
	<% 
		GetPicture getter=new GetPicture();
		getter.setConnection(conn);
		ArrayList<Picture> list = getter.getPictureOrderByTable("自然",0,13);
	%>
    <!-- 图片瀑布流 -->
    <section id="photo-container">
    	<%if(list!=null){%>
    	<%for(Picture pic:list){%>
        <div class="box">
            <div class="pic">
                <a href="./photoDetails.jsp?id=<%=pic.getId()%>">
                    <img src="images/<%=pic.getUsername()%>/<%=pic.getMd5()%>.<%=pic.getFormat()%>">
                </a>
            </div>
        </div>
        <%}%>
        <%}%>
    </section>
<script type="text/javascript" src="js/module_bind.js"></script><script type="text/javascript" src="js/photoClass.js"></script><script type="text/javascript" src="js/modernizr.js"></script></body>

</html>