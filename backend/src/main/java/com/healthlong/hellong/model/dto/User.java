package com.healthlong.hellong.model.dto;

public class User {
	private int userType;
	private String email;
	private String password;
	private String name;
	private String phNum;
	private String regDate;
	private int userRemainTime;
	private String startTime;
	private int endTime;
	
	public User() {
		
	}

	public User(int userType, String email, String password, String name, String phNum, String regDate,
			int userRemainTime, String startTime, int endTime) {
		super();
		this.userType = userType;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phNum = phNum;
		this.regDate = regDate;
		this.userRemainTime = userRemainTime;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhNum() {
		return phNum;
	}

	public void setPhNum(String phNum) {
		this.phNum = phNum;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getUserRemainTime() {
		return userRemainTime;
	}

	public void setUserRemainTime(int userRemainTime) {
		this.userRemainTime = userRemainTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "User [userType=" + userType + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", phNum=" + phNum + ", regDate=" + regDate + ", userRemainTime=" + userRemainTime + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}

}
