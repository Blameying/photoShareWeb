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
					+ "(picture.format,picture.username,picture.picname,picture.description,"
					+"picture.md5,picture.table)"
					+ " values(?,?,?,?,?,?)");
			statement.setString(1, this.picture.getFormat());
			statement.setString(2, this.picture.getUsername());
			statement.setString(3, this.picture.getPicname());
			statement.setString(4, this.picture.getDescription());
			statement.setString(5, this.picture.getMd5());
			statement.setString(6, this.picture.getTable());
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
