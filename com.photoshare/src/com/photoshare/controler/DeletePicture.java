package com.photoshare.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.photoshare.model.Picture;

public class DeletePicture extends DBconnect {
	private Picture picture;

	public DeletePicture(Picture picture,Connection conn){
		this.picture=picture;
		this.setConnection(conn);
		delete(picture);
	}
	
	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	
	public void delete(Picture picture){
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("delete from picture where picture.id=?");
			statement.setInt(1, picture.getId());
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
	}
}
