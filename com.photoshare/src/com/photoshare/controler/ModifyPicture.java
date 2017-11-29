package com.photoshare.controler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.photoshare.model.Picture;

public class ModifyPicture extends DBconnect {
	private String description;
	private Picture picture;

	public ModifyPicture(Picture picture,String description){
		this.picture=picture;
		this.description=description;
		modify(picture,description);
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Picture getPicture(){
		return this.picture;
	}
	
	public void setPicture(Picture picture){
		this.picture=picture;
	}
	public void modify(Picture picture,String description){
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("UPDATE picture SET description=? "+
					"where id=?");
			statement.setString(1, description);
			statement.setInt(2, picture.getId());
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
