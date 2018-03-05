package com.psql.project.vo;

import java.sql.Timestamp;

public class UserDTO {
	private int    userSqPk;
	private String userId;
	private String userName;
	private String userState;
	private String userEmail;
	private String userPhone;
	private String userNation;
	private String userAccountName;
	private String userAccountNumber;
	private String userAccountHolder;
	private String userGender;
 private Timestamp userJoinDate;
	
	
	public int getUserSqPk() {
		return userSqPk;
	}
	public void setUserSqPk(int userSqPk) {
		this.userSqPk = userSqPk;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserNation() {
		return userNation;
	}
	public void setUserNation(String userNation) {
		this.userNation = userNation;
	}
	public String getUserAccountName() {
		return userAccountName;
	}
	public void setUserAccountName(String userAccountName) {
		this.userAccountName = userAccountName;
	}
	public String getUserAccountNumber() {
		return userAccountNumber;
	}
	public void setUserAccountNumber(String userAccountNumber) {
		this.userAccountNumber = userAccountNumber;
	}
	public String getUserAccountHolder() {
		return userAccountHolder;
	}
	public void setUserAccountHolder(String userAccountHolder) {
		this.userAccountHolder = userAccountHolder;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public Timestamp getUserJoinDate() {
		return userJoinDate;
	}
	public void setUserJoinDate(Timestamp userJoinDate) {
		this.userJoinDate = userJoinDate;
	}
	
	

}
