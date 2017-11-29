package com.photoshare.controler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.photoshare.model.Picture;
import com.photoshare.model.User;

public class GetPicture extends DBconnect {
	private ArrayList<Picture> pictures;
	private User user;

	public ArrayList<Picture> getPictureOrderByTime(User user,int count){
		pictures=null;
		pictures = new ArrayList<Picture>();
		this.user=user;
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("SELECT * "
					+"FROM picture" 
					+"ORDER BY posttime DESC where username=? limit 0,?");
			statement.setString(1, this.user.getName());
			statement.setInt(2, count);
			ResultSet result=statement.executeQuery();
			while(result.next()){
				Picture picture=new Picture();
				picture.setId(result.getInt("id"));
				picture.setFormat(result.getString("format"));
				picture.setUsername(this.user.getName());
				picture.setDescription(result.getString("description"));
				picture.setPicname(result.getString("picname"));
				picture.setPosttime(result.getTimestamp("posttime"));
				picture.setTable(result.getString("table"));
				picture.setMd5(result.getString("md5"));
				pictures.add(picture);
			}
			return pictures;
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
		return null;
	}

	public ArrayList<Picture> getPictureOrderByTable(String table,int count){
		pictures=null;
		pictures = new ArrayList<Picture>();
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("SELECT * "
					+"FROM picture" 
					+"ORDER BY posttime DESC where table=? limit 0,?");
			statement.setString(1, table);
			statement.setInt(2, count);
			ResultSet result=statement.executeQuery();
			while(result.next()){
				Picture picture=new Picture();
				picture.setId(result.getInt("id"));
				picture.setFormat(result.getString("format"));
				picture.setUsername(this.user.getName());
				picture.setDescription(result.getString("description"));
				picture.setPicname(result.getString("picname"));
				picture.setPosttime(result.getTimestamp("posttime"));
				picture.setTable(result.getString("table"));
				picture.setMd5(result.getString("md5"));
				pictures.add(picture);
			}
			return pictures;
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
		return null;
	}
	public ArrayList<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<Picture> pictures) {
		this.pictures = pictures;
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
