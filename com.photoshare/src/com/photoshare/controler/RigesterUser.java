package com.photoshare.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.photoshare.model.User;

public class RigesterUser extends DBconnect {
	private User user;

	public RigesterUser(User user,Connection conn){
		this.user=user;
		this.setConnection(conn);
		register(this.user);
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void register(User user){
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("INSERT INTO user (name,password,info) values(?,md5(?),?)");
			statement.setString(1, this.user.getName());
			statement.setString(2, this.user.getPassword());
			statement.setString(3, this.user.getInfo());
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
