package com.photoshare.controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BuildConnection implements DBInterface{
	
	private String path;
	private String username;
	private String password;
	private Connection conn;
	
	public BuildConnection(String path,String username,String password){
		this.path=path;
		this.username=username;
		this.password=password;
		build();
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public void build(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.setConnection(DriverManager.getConnection(path,username,password));
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	@Override
	public void setConnection(Connection conn) {
		this.conn=conn;
		
	}
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return this.conn;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
