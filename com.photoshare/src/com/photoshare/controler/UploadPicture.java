package com.photoshare.controler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.photoshare.model.Picture;

public class UploadPicture extends DBconnect {
	private Picture picture;
	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public void upload(Picture picture){
		this.picture = picture;
		
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("INSERT INTO picture "
					+ "(format,username,picname,description,md5)"
					+ " values(?,?,?,?,?)");
			statement.setString(1, this.picture.getFormat());
			statement.setString(2, this.picture.getUsername());
			statement.setString(3, this.picture.getPicname());
			statement.setString(4, this.picture.getDescription());
			statement.setString(5, this.picture.getMd5());
			statement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if(statement!=null)
					statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//java web写入文件
		
	}
}
