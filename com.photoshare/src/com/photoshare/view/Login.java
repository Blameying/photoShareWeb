package com.photoshare.view;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photoshare.controler.BuildConnection;
import com.photoshare.controler.CheckUser;
import com.photoshare.controler.GetUser;
import com.photoshare.controler.UpdateUser;
import com.photoshare.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "Login to your account", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		BuildConnection build =new BuildConnection("jdbc:mysql://localhost:3306/photoweb", "root", "nihao@@");
		CheckUser check=new CheckUser(name,password,build.getConnection());
		int flag=check.check();
		if(flag==1){
			User user=new User();
			user.setName(name);
			new GetUser(user,build.getConnection());
	        //返回html页面  
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/userinfo.jsp");
			dispatcher.forward(request, response); 
			//更新上次登陆时间
			Timestamp now = new Timestamp(System.currentTimeMillis());
			user.setLast_login(now);
			UpdateUser updateuser=new UpdateUser(user,build.getConnection());
			updateuser.updateLastLogin();
		}else if(flag==-1){
			response.setContentType("text/html;charset=GB18030");  
	          
	        //返回html页面  
	        response.getWriter().println("<html>");  
	        response.getWriter().println("<head>");     
	        response.getWriter().println("<title>登录信息</title>");      
	        response.getWriter().println("</head>");    
	        response.getWriter().println("<body>");     
	        response.getWriter().println("密码错误");    
	        response.getWriter().println("</body>");    
	        response.getWriter().println("</html>"); 
		}else{
			response.setContentType("text/html;charset=GB18030");  
	          
	        //返回html页面  
	        response.getWriter().println("<html>");  
	        response.getWriter().println("<head>");     
	        response.getWriter().println("<title>登录信息</title>");      
	        response.getWriter().println("</head>");    
	        response.getWriter().println("<body>");     
	        response.getWriter().println("该用户不存在");    
	        response.getWriter().println("</body>");    
	        response.getWriter().println("</html>"); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
