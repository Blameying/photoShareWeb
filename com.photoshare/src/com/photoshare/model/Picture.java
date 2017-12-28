package com.photoshare.model;

import java.io.File;
import java.sql.Timestamp;

public class Picture {
	private int id;
	private String format;
	private String username;
	private String picname;
	private Timestamp posttime;
	private String description;
	private String md5;
	private File picfile;
	private boolean test;
	//标签
	private String table;
	
	public String getTable() {
		return table;
	}
	
	public void setTable(String table) {
		this.table = table;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPicname() {
		return picname;
	}

	public void setPicname(String picname) {
		this.picname = picname;
	}

	public Timestamp getPosttime() {
		return posttime;
	}

	public void setPosttime(Timestamp posttime) {
		this.posttime = posttime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public File getPicfile() {
		return picfile;
	}

	public void setPicfile(File picfile) {
		this.picfile = picfile;
	}

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}
	
}
