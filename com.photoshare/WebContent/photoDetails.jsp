<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.photoshare.model.Picture" import="com.photoshare.controler.GetPicture"%>
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
    <title>单张图片浏览页面</title>
    <meta name="keywords" content="单张图片浏览页面">
    <meta name="description" content="单张图片浏览页面">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/photoDetails.css" rel="stylesheet"></head>

<body>
    <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="#">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
	<%
		Picture pic=null;
		String id =request.getParameter("id");
		int ID;
		try{
			ID=Integer.parseInt(id);
		}catch(NumberFormatException e){
			ID=0;
		}
		GetPicture getter=new GetPicture();
		getter.setConnection(conn);
		pic=getter.getPictureById(ID);
	%>
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
    <section id="container">
        <div class="photo-content">
            <div class="photo-path">
                图片分类
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-more"></use>
                </svg>
                <span class="photo-class"><%=pic.getTable()%></span>
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-more"></use>
                </svg>
                <span class="photo-name"><%=pic.getPicname()%></span>
            </div>
            <div class="photo-container">
                <img src="images/<%=pic.getUsername()%>/<%=pic.getMd5()+'.'+pic.getFormat()%>" alt="图片 <%=pic.getPicname() %>">
            </div>
            <div class="photo-information">
                <header class="photo-info">
                    <div class="photo-info-name">
                        <%=pic.getPicname()%>
                    </div>
                    <div class="photo-info-date">
                        <svg class="icon" aria-hidden="true">
                            <use xlink:href="#icon-clock"></use>
                        </svg>
                        <span class="data"><%=pic.getPosttime()%></span>发布
                    </div>
                    <div class="photo-info-download">
                        <svg class="icon" aria-hidden="true">
                            <use xlink:href="#icon-icondownload"></use>
                        </svg>
                    </div>
                </header>
                <section class="photo-description">
                    <p>
                        <%=pic.getDescription()%>
                    </p>
                </section>
                <footer class="photo-uploader">
                    <img src="img/user.jpg" alt="用户头像" class="uploader-img">
                    <span class="uploader-name"><%=pic.getUsername()%></span>
                    <span class="origin">原创</span>
                </footer>
            </div>
        </div>
    </section>
<script type="text/javascript" src="js/module_bind.js"></script><script type="text/javascript" src="js/photoDetails.js"></script><script type="text/javascript" src="js/modernizr.js"></script></body>

</html>