package com.model;

/**
 * UserMap entity. @author MyEclipse Persistence Tools
 */

public class UserMap implements java.io.Serializable {

	// Fields

	private Integer umId;
	private String umUser;
	private String umMapUser;
	private String umRemark;

	// Constructors

	/** default constructor */
	public UserMap() {
	}

	/** full constructor */
	public UserMap(String umUser, String umMapUser, String umRemark) {
		this.umUser = umUser;
		this.umMapUser = umMapUser;
		this.umRemark = umRemark;
	}

	// Property accessors

	public Integer getUmId() {
		return this.umId;
	}

	public void setUmId(Integer umId) {
		this.umId = umId;
	}

	public String getUmUser() {
		return this.umUser;
	}

	public void setUmUser(String umUser) {
		this.umUser = umUser;
	}

	public String getUmMapUser() {
		return this.umMapUser;
	}

	public void setUmMapUser(String umMapUser) {
		this.umMapUser = umMapUser;
	}

	public String getUmRemark() {
		return this.umRemark;
	}

	public void setUmRemark(String umRemark) {
		this.umRemark = umRemark;
	}

}