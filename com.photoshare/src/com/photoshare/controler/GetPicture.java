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
	public Picture getPictureById(int id){
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("SELECT * "
					+"FROM picture " 
					+"where picture.id=? and picture.test=1");
			statement.setInt(1, id);
			ResultSet result=statement.executeQuery();
			Picture picture=null;
			while(result.next()){
				picture=new Picture();
				picture.setId(result.getInt("id"));
				picture.setFormat(result.getString("format"));
				picture.setUsername(result.getString("username"));
				picture.setDescription(result.getString("description"));
				picture.setPicname(result.getString("picname"));
				picture.setPosttime(result.getTimestamp("posttime"));
				picture.setTable(result.getString("table"));
				picture.setMd5(result.getString("md5"));
				picture.setTest(result.getBoolean("test"));
			}
			return picture;
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
	public ArrayList<Picture> getPictureOrderByTime(User user,int start,int end){
		pictures=null;
		pictures = new ArrayList<Picture>();
		this.user=user;
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("SELECT * "
					+"FROM picture "
					+"where picture.username=? and picture.test=1 ORDER BY picture.posttime DESC limit ?,?");
			statement.setString(1, this.user.getName());
			statement.setInt(2, start);
			statement.setInt(3, end);
			ResultSet result=statement.executeQuery();
			while(result.next()){
				Picture picture=new Picture();
				picture.setId(result.getInt("id"));
				picture.setFormat(result.getString("format"));
				picture.setUsername(result.getString("username"));
				picture.setDescription(result.getString("description"));
				picture.setPicname(result.getString("picname"));
				picture.setPosttime(result.getTimestamp("posttime"));
				picture.setTable(result.getString("table"));
				picture.setMd5(result.getString("md5"));
				picture.setTest(result.getBoolean("test"));
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

	public ArrayList<Picture> getPictureOrderByTable(String table,int start,int end){
		pictures=null;
		pictures = new ArrayList<Picture>();
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("SELECT * "
					+"FROM picture " 
					+"where picture.table=? and picture.test=1 ORDER BY picture.posttime limit ?,?;");
			statement.setString(1, table);
			System.out.println(table);
			statement.setInt(2, start);
			statement.setInt(3, end);
			ResultSet result=statement.executeQuery();
			while(result.next()){
				Picture picture=new Picture();
				picture.setId(result.getInt("id"));
				picture.setFormat(result.getString("format"));
				picture.setUsername(result.getString("username"));
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
	
	public ArrayList<Picture> getPictureOrderByTest(int start,int end){
		pictures=null;
		pictures = new ArrayList<Picture>();
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("SELECT * "
					+"FROM picture " 
					+"where picture.test=0 ORDER BY picture.posttime limit ?,?");
			statement.setInt(1, start);
			statement.setInt(2, end);
			ResultSet result=statement.executeQuery();
			while(result.next()){
				Picture picture=new Picture();
				picture.setId(result.getInt("id"));
				picture.setFormat(result.getString("format"));
				picture.setUsername(result.getString("username"));
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
