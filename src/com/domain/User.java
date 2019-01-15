package com.domain;

public class User {
	
	private String username;
	private String password;
	private String realname;
	private String role;
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
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public User(String username, String password, String realname, String role) {
		super();
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.role = role;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", realname=" + realname + ", role=" + role
				+ "]";
	}
	
	
	

}
