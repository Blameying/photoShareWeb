<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.photoshare.model.User" import="java.io.File"%>
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
    <title>寻图网首页</title>
    <meta name="keywords" content="寻图网首页">
    <meta name="description" content="寻图网首页,寻找分享你所喜爱的图片！">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.js"></script>  
	<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.min.js"></script>  
<link href="css/index.css" rel="stylesheet"></head>

<body>
    <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="#">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
    <header id="navbar">
        <div class="navbar-container">
            <div class="navbar-left">
                <ul class="navbar-list">
                    <li class="navbar-item">
                        <a href="">首页</a>
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
    <section id="content">
        <div class="logo"></div>
        <div class="search-container">
            <input type="text" id="search">
        </div>
        <div class="login-container">
            <div class="head-nav">
                <a href="javascript:;" class="nav login doing">登录</a>
                <a href="javascript:;" class="nav regist">注册</a>
                <a href="javascript:;" class="return">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-close"></use>
                    </svg>
                </a>
            </div>
            <div class="content-container">
                <div id="login-content">
                    <div class="account row">
                        <input type="text" name="account" id="account" placeholder="请输入登录账号">
                    </div>
                    <div class="password row">
                        <input type="password" name="password" id="password" placeholder="6-16位密码，区分大小写，不能用空格">
                    </div>
                    <div class="password-operate row">
                        <input type="checkbox">
                        <span>记住密码</span>
                        <a href="javascript:;">忘记密码</a>
                    </div>
                    <button id="login">登录</button>
                </div>
                <div id="regist-content">
                    <div class="account row">
                        <input type="text" name="account" id="account" placeholder="请输入登录账号">
                    </div>
                    <div class="password row">
                        <input type="password" name="password" id="password" placeholder="6-16位密码，区分大小写，不能用空格">
                    </div>
                    <div class="repassword row">
                        <input type="password" name="re-password" id="repassword" placeholder="重复密码">
                    </div>
                    <button id="regist">注册</button>
                </div>
            </div>
        </div>
    </section>
    <div class="mask-layer">
        <!-- 遮罩层  -->
    </div>
<script type="text/javascript" src="js/module_bind.js"></script><script type="text/javascript" src="js/index.js"></script><script type="text/javascript" src="js/modernizr.js"></script>
<script>
    $(function () {


        //点击登录
        $("#login").click(function () {

            const account = $("#account").val(),
                password = md5($("#password").val());
            $.ajax({
                type: "POST",
                url: "http://localhost:9999/com.photoshare/Login", //接口地址
                dataType: "JSON",
                data: { //传送数据json数据格式
                    account: account,
                    password: password
                },
                success: function (data) { //判断是否成功，建议返回数据加一个success字段来判断是否登录成功，message作为登录失败返回的提示信息
                    if (data.success == true) {
                        //定义登录成功后的操作
                    	alert(data.message);
                        location.reload();
                    } else {
                        alert(data.message);
                    }
                }

            })

        })

    });
</script>
<script>
    $(function () {


        //点击登录
        $("#regist").click(function () {

            const account = $("#account").val(),
                password = $("#password").val();
            $.ajax({
                type: "POST",
                url: "http://localhost:9999/com.photoshare/Rigester", //接口地址
                dataType: "JSON",
                data: { //传送数据json数据格式
                    account: account,
                    password: password
                },
                success: function (data) { //判断是否成功，建议返回数据加一个success字段来判断是否登录成功，message作为登录失败返回的提示信息
                    if (data.success == true) {
                        //定义登录成功后的操作
                    	alert(data.message);
                    	location.reload();
                    } else {
                        alert(data.message);
                    }
                }

            })

        })

    });
</script>

</body>
</html>