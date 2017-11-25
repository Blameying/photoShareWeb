package com.photoshare.model;

import java.sql.Timestamp;

public class User {
	private int id;
	private String password;
	private String name;
	private String info;
	private int post_count;
	private Timestamp last_login;
	private Timestamp buildtime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getPost_count() {
		return post_count;
	}
	public void setPost_count(int post_count) {
		this.post_count = post_count;
	}
	public Timestamp getLast_login() {
		return last_login;
	}
	public void setLast_login(Timestamp last_login) {
		this.last_login = last_login;
	}
	public Timestamp getBuildtime() {
		return buildtime;
	}
	public void setBuildtime(Timestamp buildtime) {
		this.buildtime = buildtime;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
