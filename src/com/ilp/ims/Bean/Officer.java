package com.ilp.ims.Bean;

public class Officer {
	
	private int userId;
	private String userName;
	private String password;
	private String officerType;
	
	public Officer() {
		super();
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOfficerType() {
		return officerType;
	}
	public void setOfficerType(String officerType) {
		this.officerType = officerType;
	}
	
	

}
