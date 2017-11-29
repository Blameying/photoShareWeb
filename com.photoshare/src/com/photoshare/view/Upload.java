package com.photoshare.view;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.photoshare.model.Picture;
import com.photoshare.model.User;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user=(User)request.getAttribute("user");
		Picture picture=(Picture)request.getAttribute("picture");
		
		String path = getServletContext().getRealPath("/")+"images"+user.getName()+"temp";
		System.out.println(path);
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		
		
		SmartUpload su = new SmartUpload();
		//初始化对象
		su.initialize(getServletConfig(), request, response);
		//设置上传文件大小  2M
		su.setMaxFileSize(1024*1024*8);
		//设置所有文件的总大小 10M
		su.setTotalMaxFileSize(1024*1024*20);
		//设置允许上传文件的大小
		su.setAllowedFilesList("png,bmp,jpg,gif");
		System.out.println("成功上传！");
		try {
			//设置禁止上传的文件类型
			su.setDeniedFilesList("doc,jsp,php,js,exe,mp4,rmvb");
			//开始上传文件
			su.upload();
			//保存上传文件  返回成功上传的文件数
			su.save(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
