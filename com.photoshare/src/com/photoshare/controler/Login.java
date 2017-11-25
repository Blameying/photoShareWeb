package com.photoshare.controler;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		BuildConnection build =new BuildConnection("jdbc:mysql://localhost:3306/shopping", "root", "nihao@@");
		CheckUser check=new CheckUser(name,password,build.getConnection());
		if(check.check()){
			response.setContentType("text/html;charset=GB18030");  
	          
	        //返回html页面  
	        response.getWriter().println("<html>");  
	        response.getWriter().println("<head>");     
	        response.getWriter().println("<title>登录信息</title>");      
	        response.getWriter().println("</head>");    
	        response.getWriter().println("<body>");     
	        response.getWriter().println("欢迎【" + name + "】用户登录成功！！！");    
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
	        response.getWriter().println("没有该用户");    
	        response.getWriter().println("</body>");    
	        response.getWriter().println("</html>"); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
