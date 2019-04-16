package com.Practice.MyBlog.dao.dto;

public class MemberInfo {

	private String userId;  
	private String passWord;
	private String userName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "MemberInfo [userId=" + userId + ", passWord=" + passWord + ", userName=" + userName + "]";
	}
	
}
