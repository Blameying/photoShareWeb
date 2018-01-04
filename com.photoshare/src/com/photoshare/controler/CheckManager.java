package com.photoshare.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.photoshare.model.User;

public class CheckManager extends DBconnect {
	private User user;
	public CheckManager(User user,Connection conn){
		this.setConnection(conn);
		this.user=user;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean check(){
		boolean flag=false;
		PreparedStatement statement=null;
		
		try {
			statement=this.getConnection().prepareStatement("select * from manager where manager.userid = ? ");
			statement.setInt(1, user.getId());
			ResultSet result=statement.executeQuery();
			if(result.next()){
				flag=true;
			}
			else
			{
				flag=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(statement!=null){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return flag;
	}
}
