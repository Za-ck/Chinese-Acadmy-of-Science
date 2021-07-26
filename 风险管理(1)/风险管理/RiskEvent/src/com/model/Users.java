package com.model;


/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users {

	// Fields

	private String userId;
	private String userName;
	private String userPassword;
	private String userRname;//此字段用作标记字段。看用户是否有效
	private String userSex;
	private String userIdcard;
	private String userTel;
	private String userCellphone;
	private String userEmail;
	private String userDep;
	private int userPosition;

	// Constructors

	/** default constructor */
	public Users() {
		
	}

	/** minimal constructor */
	public Users(String userId, String userName, String userPassword, String userRname,
			String userSex) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRname = userRname;
		this.userSex = userSex;
	}

	public Users(String userId, String userName, String userPassword,
			String userRname, String userSex, String userIdcard,
			String userTel, String userCellphone, String userEmail,
			String userDep, int userPosition) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRname = userRname;
		this.userSex = userSex;
		this.userIdcard = userIdcard;
		this.userTel = userTel;
		this.userCellphone = userCellphone;
		this.userEmail = userEmail;
		this.userDep = userDep;
		this.userPosition = userPosition;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRname() {
		return this.userRname;
	}

	public void setUserRname(String userRname) {
		this.userRname = userRname;
	}

	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserIdcard() {
		return this.userIdcard;
	}

	public void setUserIdcard(String userIdcard) {
		this.userIdcard = userIdcard;
	}

	public String getUserTel() {
		return this.userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public String getUserCellphone() {
		return userCellphone;
	}

	public void setUserCellphone(String userCellphone) {
		this.userCellphone = userCellphone;
	}

	public String getUserDep() {
		return userDep;
	}

	public void setUserDep(String userDep) {
		this.userDep = userDep;
	}

	public int getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(int userPosition) {
		this.userPosition = userPosition;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}