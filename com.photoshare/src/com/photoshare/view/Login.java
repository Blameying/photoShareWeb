package com.photoshare.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photoshare.controler.CheckUser;
import com.photoshare.controler.GetUser;
import com.photoshare.controler.UpdateUser;
import com.photoshare.model.User;

import net.sf.json.JSONObject;

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
		String name = request.getParameter("account");
		String password = request.getParameter("password");
		Connection conn = (Connection)request.getSession().getAttribute("connection");
		CheckUser check=new CheckUser(name,password,conn);
		int flag=check.check();
		if(flag==1){
			User user=new User();
			user.setName(name);
			new GetUser(user,conn);
	        //返回html页面  
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("connection", conn);
			//更新上次登陆时间
			Timestamp now = new Timestamp(System.currentTimeMillis());
			user.setLast_login(now);
			UpdateUser updateuser=new UpdateUser(user,conn);
			updateuser.updateLastLogin();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8"); 
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", "登陆成功！");
			PrintWriter out=response.getWriter();
			out.println(data);
		}else if(flag==-1){
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8"); 
			JSONObject data = new JSONObject();
			data.put("success", false);
			data.put("message", "密码错误！");
			PrintWriter out=response.getWriter();
			out.println(data);
		}else{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8"); 
			JSONObject data = new JSONObject();
			data.put("success", false);
			data.put("message", "该用户不存在！！");
			PrintWriter out=response.getWriter();
			out.println(data);
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
