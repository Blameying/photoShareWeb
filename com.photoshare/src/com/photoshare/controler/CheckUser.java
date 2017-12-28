package com.photoshare.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.photoshare.model.User;

public class CheckUser extends DBconnect {
	private String name;
	private String password;
	
	public CheckUser(String name,String password,Connection conn){
		this.name=name;
		this.password=password;
		this.setConnection(conn);
	}
	
	public CheckUser(User user,Connection conn){
		this.name=user.getName();
		this.password=user.getPassword();
		this.setConnection(conn);
	}
	
	public int check(){
		int flag=0;
		PreparedStatement statement=null;
		
		try {
			statement=this.getConnection().prepareStatement("select * from user where user.name = ?");
			statement.setString(1, name);
			ResultSet result=statement.executeQuery();
			if(result.next()){
				if(result.getString("password").equals(this.password)){
					flag=1;
				}
				else{
					flag=-1;
				}
			}
			else
			{
				flag=0;
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
