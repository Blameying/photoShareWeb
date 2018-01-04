package com.photoshare.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photoshare.controler.DeletePicture;
import com.photoshare.controler.GetPicture;
import com.photoshare.controler.ModifyPicture;
import com.photoshare.model.Picture;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class TestPicture
 */
@WebServlet("/TestPicture")
public class TestPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestPicture() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn=(Connection)request.getSession().getAttribute("connection");
		Picture picture = null;
		String status = request.getParameter("status");
		String id = request.getParameter("id");
		ModifyPicture updater=new ModifyPicture();
		updater.setConnection(conn);
		try{
			GetPicture getter=new GetPicture();
			getter.setConnection(conn);
			picture=getter.getPictureById(Integer.parseInt(id));
			
			if(status.equals("pass")){
			updater.allow(picture);
			String oldpath=this.getServletContext().getRealPath("/")+"images/"+picture.getUsername()+"/temp/"+picture.getMd5()+'.'+picture.getFormat();
			String newpath=this.getServletContext().getRealPath("/")+"images/"+picture.getUsername()+"/"+picture.getMd5()+'.'+picture.getFormat();
			File oldfile=new File(oldpath);
			if(oldfile.exists()){
				File newfile=new File(newpath);
				newfile.createNewFile();
				FileInputStream in=new FileInputStream(oldfile);
				FileOutputStream out=new FileOutputStream(newfile);
				byte[] buffer=new byte[2097152];
				while(true){
					   int ins=in.read(buffer);
					   if(ins==-1){
					    in.close();
					    out.flush();
					    out.close();
					    break;
				}else
					out.write(buffer,0,ins);
				}
				oldfile.delete();
			}
			}else{
				System.out.println(this.getServletContext().getRealPath("/")+picture.getUsername());
				String path=this.getServletContext().getRealPath("/")+"images/"+picture.getUsername()+"/temp/"+picture.getMd5()+'.'+picture.getFormat();
				File file=new File(path);
				DeletePicture del=new DeletePicture(picture,conn);
				if(file.exists())
					file.delete();
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8"); 
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", "审核通过！");
			PrintWriter out=response.getWriter();
			out.println(data);
		}catch(Exception e){
			e.printStackTrace();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8"); 
			JSONObject data = new JSONObject();
			data.put("success", false);
			data.put("message", "审核失败！");
			PrintWriter out=response.getWriter();
			out.println(data);
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
