package com.photoshare.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photoshare.controler.BuildConnection;
import com.photoshare.controler.CheckUser;
import com.photoshare.controler.RigesterUser;
import com.photoshare.model.User;

/**
 * Servlet implementation class Rigester
 */
@WebServlet(description = "regester", urlPatterns = { "/Rigester" })
public class Rigester extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rigester() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		user.setName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setInfo(request.getParameter("info"));
		BuildConnection build =new BuildConnection("jdbc:mysql://localhost:3306/photoweb", "root", "nihao@@");
		CheckUser check=new CheckUser(user,build.getConnection());
		if(check.check()!=0){
			response.setContentType("text/html;charset=GB18030");  
	          
	        //返回html页面  
	        response.getWriter().println("<html>");  
	        response.getWriter().println("<head>");     
	        response.getWriter().println("<title>警告</title>");      
	        response.getWriter().println("</head>");    
	        response.getWriter().println("<body>");     
	        response.getWriter().println("该用户已存在");    
	        response.getWriter().println("</body>");    
	        response.getWriter().println("</html>"); 
		}else{
			new RigesterUser(user,build.getConnection());
			if(check.check()!=0){
				response.setContentType("text/html;charset=GB18030");  
		          
		        //返回html页面  
		        response.getWriter().println("<html>");  
		        response.getWriter().println("<head>");     
		        response.getWriter().println("<title>注册信息</title>");      
		        response.getWriter().println("</head>");    
		        response.getWriter().println("<body>");     
		        response.getWriter().println("注册成功");    
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
		        response.getWriter().println("注册失败");    
		        response.getWriter().println("</body>");    
		        response.getWriter().println("</html>"); 
			}
			
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
