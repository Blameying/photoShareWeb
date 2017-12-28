package com.photoshare.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;

import com.photoshare.controler.BuildConnection;
import com.photoshare.controler.CheckPicture;
import com.photoshare.controler.UploadPicture;
import com.photoshare.model.Picture;
import com.photoshare.model.User;

import net.sf.json.JSONObject;


/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	private static final String [] extensionPermit = {"jpg", "jpeg", "png","bmp","gif"};
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
		User user;
		String filepath=null;
		
		user=(User)request.getSession().getAttribute("user");
		Picture picture=new Picture();
		
		picture.setUsername(user.getName());
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//获取工程目录
		String ProjectPath = this.getServletContext().getRealPath("/"); 
		System.out.println(ProjectPath);
		String uploaddir=ProjectPath + "images"+File.separator+user.getName()+File.separator+"temp";
		System.out.println(uploaddir);
		File dir=new File(uploaddir);
		if(!(dir.exists()&&dir.isDirectory()))
			dir.mkdirs();
		//创建文件工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//临时文件清除追踪器
		FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(this.getServletContext());  
        factory.setFileCleaningTracker(fileCleaningTracker);  
      //创建文件解析器,传入工厂
		ServletFileUpload upload = new ServletFileUpload(factory);
		//文件上传监听器
		//FileProcessListener processListener = new FileProcessListener(request.getSession());  
        //upload.setProgressListener(processListener);
		//设置单文件上传最大尺寸
		upload.setFileSizeMax(5*1024*1024);
		//
		upload.setHeaderEncoding("utf-8");
		
		
		//解析阶段
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for (Iterator<FileItem> iterator = fileItems.iterator(); iterator.hasNext();) {  
                FileItem fileItem = iterator.next();  
                String fieldName = fileItem.getFieldName();
                String name = fileItem.getName();
                if(!fileItem.isFormField()) {  
                    if(fileItem.getSize() > 0) {  
                        String fileExtension = FilenameUtils.getExtension(name);  
                        if(!ArrayUtils.contains(extensionPermit, fileExtension)) {  
                            System.out.println("No right type");
                            throw new IOException();
                        }
                        picture.setFormat(fileExtension);
                        String fileName = FilenameUtils.getName(name);  
                        FileUtils.copyInputStreamToFile(fileItem.getInputStream(),   
                                new File(uploaddir, fileName));
                        filepath=uploaddir+File.separator+fileName;
                    }  
                } else { //Form表单数据  
                     String info = fileItem.getString();
                     if(fieldName.equals("name"));
                     	picture.setPicname(info);
                     if(fieldName.equals("description"));
                     	picture.setDescription(info);
                     if(fieldName.equals("table")){
                    	 picture.setTable(info);
                     }
                }
            } 
			File rename=new File(filepath);
			System.out.println(filepath);
			BuildConnection build =new BuildConnection("jdbc:mysql://localhost:3306/photoweb", "root", "nihao@@");
			if(rename.exists()){
				picture.setMd5(getFileMD5(rename));
				CheckPicture check=new CheckPicture(picture,build.getConnection());
				if(check.check()==1){
					rename.delete();
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json; charset=utf-8"); 
					JSONObject data = new JSONObject();
					data.put("success", false);
					data.put("message", "文件已存在！");
					PrintWriter out=response.getWriter();
					out.println(data);
				}
				else{
					System.out.println(picture.getMd5());
					rename.renameTo(new File(uploaddir,picture.getMd5()+"."+picture.getFormat()));
					System.out.println(uploaddir);
					UploadPicture uploadpicture=new UploadPicture();
					uploadpicture.setConnection(build.getConnection());
					uploadpicture.upload(picture);
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json; charset=utf-8"); 
					JSONObject data = new JSONObject();
					data.put("success", true);
					data.put("message", "上传成功！");
					PrintWriter out=response.getWriter();
					out.println(data);
				}
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8"); 
			JSONObject data = new JSONObject();
			data.put("success", false);
			data.put("message", "上传失败！");
			PrintWriter out=response.getWriter();
			out.println(data);
		} 
		
	}
	
	public  String getFileMD5(File file) {  
        if (!file.isFile()) {  
            return null;  
        }  
          
        MessageDigest digest = null;  
        FileInputStream in = null;  
        byte buffer[] = new byte[1024];  
        int len;  
        try {  
            digest = MessageDigest.getInstance("MD5");  
            in = new FileInputStream(file);  
            while ((len = in.read(buffer, 0, 1024)) != -1) {  
                digest.update(buffer, 0, len);  
            }  
            in.close();  
  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        BigInteger bigInt = new BigInteger(1, digest.digest());  
  
        return bigInt.toString(16);  
    }  
}
