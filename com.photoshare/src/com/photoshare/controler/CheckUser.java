package com.photoshare.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckUser extends DBconnect {
	private String name;
	private String password;
	
	public CheckUser(String name,String password,Connection conn){
		this.name=name;
		this.password=password;
		this.setConnection(conn);
	}
	
	public boolean check(){
		boolean flag=false;
		PreparedStatement statement=null;
		
		try {
			statement=this.getConnection().prepareStatement("select * from user where name = ?");
			statement.setString(1, name);
			ResultSet result=statement.executeQuery();
			if(result.next()){
				if(result.getString("password").equals(this.password)){
					flag=true;
				}
				else{
					flag=false;
				}
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
