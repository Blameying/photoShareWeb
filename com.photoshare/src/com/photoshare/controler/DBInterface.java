package com.photoshare.controler;

import java.sql.Connection;

public interface DBInterface {
	public void setConnection(Connection conn);
	public Connection getConnection();
	
}
