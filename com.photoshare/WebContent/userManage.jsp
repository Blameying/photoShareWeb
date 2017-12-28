<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.photoshare.model.Picture" import="com.photoshare.controler.GetPicture" import="java.util.ArrayList"%>
    <%@include file="checkuser.jsp" %>
        <%@include file="connection.jsp" %>

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
                <title>用户管理界面(无路由)</title>
                <meta name="keywords" content="用户管理界面(无路由)">
                <meta name="description" content="用户管理界面(无路由)">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <script type="text/javascript" src="http://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
                <script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.js"></script>  
				<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.min.js"></script>  
                <link href="css/userManageCombine.css" rel="stylesheet">
            </head>

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
                        <div class="navbar-search">
                            <input type="text" class="search">
                        </div>
                        <div class="navbar-right">
                            <%
            	if(user==null){
            %>
                <a href="javascript:;" class="user-name">登录</a>
                <img src="img/login.png" alt="默认头像" title="默认头像" class="default-img">
            <%}else{%>
            	<a href="./userManage.jsp">
            	<%
            		String path="image/"+user.getName()+"/head/user.jpg";
            		File file = new File(path);
            		if(file.exists()){
            	%>
                <img src="image/<%=user.getName()%>/head/user.jpg" alt="用户头像" title="用户头像" class="user-img">
                <%}else{ %>
                <img src="img/user.jpg" alt="用户头像" title="用户头像" class="user-img">
                <%} %>
                </a>
            <%}%>
                        </div>
                    </div>
                </header>
                <section id="operation-content">
                    <div class="container">
                        <div id="content">
                            <!-- 图片审核界面 -->
                            <section id="photo-check-content">
                                <h3 class="title">图片审核</h3>
                                <%
                    	GetPicture getter=new GetPicture();
                    	getter.setConnection(conn);
                    	ArrayList<Picture> checklist=getter.getPictureOrderByTest(0, 3);
                    %>
                                    <div class="container">
                                    	<%if(checklist!=null&&!checklist.isEmpty()){ %>
                                        <%for(Picture picture:checklist){ %>
                                            <div class="photo-info">
                                                <div class="photo-class">
                                                    <span>
                                                        <%=picture.getTable() %>
                                                    </span>
                                                </div>
                                                <div class="photo-name">
                                                    <span>
                                                        <%=picture.getPicname() %>
                                                    </span>
                                                </div>
                                                <div class="photo-description">
                                                    <div class="photo">
                                                        <img src="image/<%=picture.getUsername()%>/temp/<%=picture.getMd5()+" . "+picture.getFormat() %>" alt="图片 ">
                                                    </div>
                                                    <div class="photo-description-content">
                                                        <p>
                                                            <%=picture.getDescription() %>
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="check">
                                                    <div class="pass">
                                                        <svg class="icon" aria-hidden="true">
                                                            <use xlink:href="#icon-edit"></use>
                                                        </svg>
                                                        <span>审核通过</span>
                                                    </div>
                                                    <div class="nopass">
                                                        <svg class="icon" aria-hidden="true">
                                                            <use xlink:href="#icon-edit"></use>
                                                        </svg>
                                                        <span>审核不通过</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <%} %>
                                            <%} %>
                                    </div>
                            </section>

                            <!-- 图片查看界面 -->
                            <section id="photo-look-content" class="active">
                                <h3 class="title">图片查看</h3>
                                <%
                                	ArrayList<Picture> showlist=getter.getPictureOrderByTime(user, 0, 10);
                                %>
                                    <div class="container">
                                    	<%if(showlist!=null&&showlist.isEmpty()){%>
                                        <%for(Picture picture:showlist){%>
                                            <div class="photo-info">
                                                <div class="photo-class">
                                                    <span>
                                                        <%=picture.getTable() %>
                                                    </span>
                                                </div>
                                                <div class="photo-name">
                                                    <span>
                                                        <%=picture.getPicname() %>
                                                    </span>
                                                </div>
                                                <div class="photo-description">
                                                    <div class="photo">
                                                        <%if(picture.isTest()){%>
                                                            <img src="image/<%=picture.getUsername() %>/<%=picture.getMd5()+" . "+picture.getFormat()%>" alt="图片">
                                                            <%} %>
                                                    </div>
                                                    <div class="photo-description-content">
                                                        <p>
                                                            <%=picture.getDescription() %>
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="check">
                                                    <div class="check-status pass">
                                                        <svg class="icon" aria-hidden="true">
                                                            <use xlink:href="#icon-success"></use>
                                                        </svg>
                                                        <span>审核通过</span>
                                                    </div>
                                                    <div class="delete">
                                                        <svg class="icon" aria-hidden="true">
                                                            <use xlink:href="#icon-delete"></use>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                            <%} %>
                                            <%} %>
                                    </div>
                            </section>

                            <!-- 图片上传界面 -->
                            <section id="photo-upload-content">
                                <h3 class="title">图片上传</h3>
                                <div class="container">
                                    <div class="row upload">
                                        <span class="operation">
                                            上传图片
                                        </span>
                                        <div class="content">
                                            <div class="photo">
                                                <%
            											String path="image/"+user.getName()+"/head/user.jpg";
            											File file = new File(path);
            											if(file.exists()){
            									%>
                                                    <img src="image/<%=user.getName()%>/head/user.jpg" alt="上传图片">
                                                    <%}else{%>
                                                        <img src="img/user.jpg" alt="上传图片">
                                                        <%} %>
                                            </div>
                                            <div class="upload-photo">
                                                <span>支持jpg，png格式大小5M以内的图片</span>
                                                <div class="file">
                                                    <input type="file" id="uploadpicture" name="uploadpicture">上传图片
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row photo-name">
                                        <span class="operation">图片名称</span>
                                        <input type="text" id="pic-name">
                                    </div>

                                    <div class="row photo-class">
                                        <span class="operation">图片分类</span>
                                        <select name="photo-class" id="class">
                                            <option value="1">建筑商务</option>
                                            <option value="2">旅行节庆</option>
                                            <option value="3">科技医学</option>
                                            <option value="4">行业概念</option>
                                            <option value="5">矢量素材</option>
                                            <option value="6">生活方式 运动</option>
                                            <option value="7">艺术娱乐 美妆健康</option>
                                            <option value="8">自然</option>
                                            <option value="9">食物饮料</option>
                                            <option value="10">动物及宠物</option>
                                        </select>
                                    </div>

                                    <div class="row photo-description">
                                        <span class="operation">图片介绍</span>
                                        <textarea name="description" id="description" cols="60" rows="7"></textarea>
                                    </div>
                                    <button id="uploadp">确认上传</button>
                                </div>
                            </section>

                            <!-- 个人资料界面 -->
                            <section id="personal-info">
                                <h3 class="title">个人资料</h3>
                                <div class="container">
                                    <div class="row upload">
                                        <span class="operation">
                                            头像
                                        </span>
                                        <div class="content">
                                            <div class="photo">
                                                <%
            											if(file.exists()){
            									%>
                                                    <img src="image/<%=user.getName()%>/head/user.jpg" alt="上传头像">
                                                    <%}else{%>
                                                        <img src="img/user.jpg" alt="上传头像">
                                                        <%} %>
                                            </div>
                                            <div class="upload-photo">
                                                <span>支持jpg，png格式大小5M以内的图片</span>
                                                <div class="file">
                                                    <input type="file" id="headpicture" name="headpicture">上传图片
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row user-name">
                                        <span class="operation">昵称</span>
                                        <input type="text" value=<%=user.getName() %>id="user-name">
                                    </div>
                                    <div class="row user-introduce">
                                        <span class="operation">个人介绍</span>
                                        <input type="text" value=<%=user.getInfo() %>id="user-introduce">
                                    </div>
                                    <button id="uploadinfo">确认修改</button>
                                </div>
                            </section>

                            <!-- 修改密码界面 -->
                            <section id="change-password">
                                <h3 class="title">修改密码</h3>
                                <div class="container">
                                    <div class="row old-password">
                                        <span class="operation">旧密码</span>
                                        <input type="text" id="old-password">
                                    </div>
                                    <div class="row new-password">
                                        <span class="operation">新密码</span>
                                        <input type="password" id="new-password">
                                    </div>
                                    <div class="row confirm-password">
                                        <span class="operation">确认新密码</span>
                                        <input type="password" id="confirm-password">
                                    </div>
                                    <button id="commit">确认修改</button>
                                </div>
                            </section>

                        </div>
                        <div id="sidebar">
                            <ul>
                                <li class="sidebar-item" data-type="photo-check-content">
                                    <a href="javacript:;">
                                        <span>图片审核</span>
                                    </a>
                                </li>
                                <li class="sidebar-item active" data-type="photo-look-content">
                                    <a href="javacript:;">
                                        <span>图片查看</span>
                                    </a>
                                </li>
                                <li class="sidebar-item" data-type="photo-upload-content">
                                    <a href="javacript:;">
                                        <span>图片上传</span>
                                    </a>
                                </li>
                                <li class="sidebar-item" data-type="personal-info">
                                    <a href="javacript:;">
                                        <span>个人资料</span>
                                    </a>
                                </li>
                                <li class="sidebar-item" data-type="change-password">
                                    <a href="javacript:;">
                                        <span>修改密码</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </section>
                <script type="text/javascript">
                    $(function () {

                        $("#uploadp").click(function () {
                        	alert("clicked");
                            var formData = new FormData();
                            formData.append('name', document.getElementById('pic-name').value);
                            //选中的分类
                            var obj = document.getElementByIdx_x('class');
                            var selectindex = obj.selectindex;
                            var text = obj.options[index].text;

                            formData.append('table', text);
                            formData.append('description', document.getElementById('description').innerHTML);
                            formData.append('file', $('#uploadpicture')[0].files[0]);

                            $.ajax({
                                type: "POST",
                                url: "http://localhost:9999/com.photoshare/Upload", //接口地址
                                cache: false,
                                processData: false,
                                contentType: false,
                                data: formData,
                                success: function (data) { //判断是否成功，建议返回数据加一个success字段来判断是否登录成功，message作为登录失败返回的提示信息
                                    if (data.success == true) {
                                        //定义登录成功后的操作
                                    	alert(data.message);
                                    } else {
                                        alert(data.message);
                                    }
                                }
                            })
                        });

                        $("#uploadinfo").click(function () {
                            var formData = new FormData();
                            formData.append('username', document.getElementById('user-name').value);
                            formData.append('description', document.getElementById('user-introduce').value);
                            formData.append('file', $('#headpicture')[0].files[0]);
                            $.ajax({
                                type: "POST",
                                url: "http://localhost:9999/com.photoshare/Uploadheader", //接口地址
                                cache: false,
                                processData: false,
                                contentType: false,
                                data: formData,
                                success: function (data) { //判断是否成功，建议返回数据加一个success字段来判断是否登录成功，message作为登录失败返回的提示信息
                                    if (data.success == true) {
                                        //定义登录成功后的操作
                                        alert(data.message)
                                    } else {
                                        alert(data.message);
                                    }
                                }
                            })
                        });

                        $("#commit").click(function () {

                            var oldPassword = md5(document.getElementById('old-password').value);
                            var newPassword = md5(document.getElementById('new-password').value);
                            var checkPassword = md5(document.getElementById('confirm-password').value);

                            if (!(newPassword == checkPassword)) {
                                return;
                            }

                            var formData = new FormData();
                            formData.append('password', oldPassword);
                            formData.append('newpassword', newPassword);
							
                            $.ajax({
                                type: "POST",
                                url: "http://localhost:9999/com.photoshare/ChangePassword", //接口地址
                                cache: false,
                                processData: false,
                                contentType: false,
                                data: formData,
                                success: function (data) { //判断是否成功，建议返回数据加一个success字段来判断是否登录成功，message作为登录失败返回的提示信息
                                    if (data.success == true) {
                                        //定义登录成功后的操作
                                        alert(data.message);
                                    } else {
                                        alert(data.message);
                                    }
                                }
                            })
                        });
                    });
                </script>
                <script type="text/javascript" src="js/module_bind.js"></script>
                <script type="text/javascript" src="js/userManageCombine.js"></script>
                <script type="text/javascript" src="js/modernizr.js"></script>
            </body>

            </html>