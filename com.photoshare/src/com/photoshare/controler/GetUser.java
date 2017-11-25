package com.photoshare.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.photoshare.model.User;

//从数据库返回用户数据
public class GetUser extends DBconnect {
	private User user;

	public GetUser(User user,Connection conn){
		this.user=user;
		this.setConnection(conn);
		this.query();
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void query(){
		PreparedStatement statement = null;
		try{
			statement=this.getConnection().prepareStatement("select * from user where name=?");
			statement.setString(1, this.user.getName());
			ResultSet result = statement.executeQuery();
			if(result.next()){
				this.user.setInfo(result.getString("info"));
				this.user.setPost_count(result.getInt("post_count"));
				this.user.setBuildtime(result.getTimestamp("buildtime"));
				this.user.setLast_login(result.getTimestamp("last_login"));
			}
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
