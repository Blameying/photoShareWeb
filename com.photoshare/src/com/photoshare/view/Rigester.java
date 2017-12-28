package com.photoshare.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photoshare.controler.BuildConnection;
import com.photoshare.controler.CheckUser;
import com.photoshare.controler.RigesterUser;
import com.photoshare.model.User;

import net.sf.json.JSONObject;

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
		user.setName(request.getParameter("account"));
		user.setPassword(request.getParameter("password"));
		BuildConnection build =new BuildConnection("jdbc:mysql://localhost:3306/photoweb", "root", "nihao@@");
		CheckUser check=new CheckUser(user,build.getConnection());
		if(check.check()!=0){
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8"); 
			JSONObject data = new JSONObject();
			data.put("success", false);
			data.put("message", "该用户已存在");
			PrintWriter out=response.getWriter();
			out.println(data);
		}else{
			new RigesterUser(user,build.getConnection());
			if(check.check()!=0){
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8"); 
				JSONObject data = new JSONObject();
				data.put("success", true);
				data.put("message", "注册成功！");
				PrintWriter out=response.getWriter();
				out.println(data);
			}else{
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8"); 
				JSONObject data = new JSONObject();
				data.put("success", false);
				data.put("message", "注册失败！");
				PrintWriter out=response.getWriter();
				out.println(data);
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
