package com.photoshare.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.photoshare.model.User;

public class UpdateUser extends DBconnect {
	private User user;
	
	public UpdateUser(User user,Connection conn){
		this.setUser(user);
		this.setConnection(conn);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void updatePassword(){
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("UPDATE user SET password=md5(?) where name=?");
			statement.setString(1, this.user.getPassword());
			statement.setString(2, this.user.getName());
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
	
	public void updateInfo(){
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("UPDATE user SET info=? where name=?");
			statement.setString(1, this.user.getInfo());
			statement.setString(2, this.user.getName());
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
	
	public void updatePostCount(){
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("UPDATE user SET post_count=? where name=?");
			statement.setInt(1, this.user.getPost_count());
			statement.setString(2, this.user.getName());
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
	
	public void updateLastLogin(){
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("UPDATE user SET last_login=? where name=?");
			statement.setTimestamp(1, this.user.getLast_login());
			statement.setString(2, this.user.getName());
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
	
	public void updateTempCount(){
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("UPDATE user SET temp_count=? where name=?");
			statement.setInt(1, this.user.getTemp_count());
			statement.setString(2, this.user.getName());
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
