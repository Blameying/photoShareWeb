package com.photoshare.controler;

import java.sql.Connection;

public class DBconnect implements DBInterface {

	private Connection conn;
	@Override
	public void setConnection(Connection conn) {
		// TODO Auto-generated method stub
		this.conn=conn;
		
	}
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return this.conn;
	}
}
