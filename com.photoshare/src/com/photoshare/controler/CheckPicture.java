package com.photoshare.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.photoshare.model.Picture;

public class CheckPicture extends DBconnect {
	private String md5;
	private String username;
	
	public CheckPicture(String username,String md5,Connection conn){
		this.username=username;
		this.md5=md5;
		this.setConnection(conn);
	}
	
	public CheckPicture(Picture picture,Connection conn){
		this.username=picture.getUsername();
		this.md5=picture.getMd5();
		this.setConnection(conn);
	}
	
	public int check(){
		int flag=0;
		PreparedStatement statement=null;
		
		try {
			statement=this.getConnection().prepareStatement("select * from picture where picture.username = ? and picture.md5=?");
			statement.setString(1, username);
			statement.setString(2, md5);
			ResultSet result=statement.executeQuery();
			if(result.next()){
				flag=1;
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
