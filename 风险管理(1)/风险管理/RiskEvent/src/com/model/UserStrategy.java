package com.model;

/**
 * UserStrategy entity. @author MyEclipse Persistence Tools
 */

public class UserStrategy implements java.io.Serializable {

	// Fields

	private Integer userStrId;
	private int userRole;
	private Department department;
	private Strategy strategy;
	private String remark;

	// Constructors

	/** default constructor */
	public UserStrategy() {
	}

	/** minimal constructor */
	public UserStrategy(SystemRole systemRole, Department department,
			Strategy strategy) {
		this.userRole = userRole;
		this.department = department;
		this.strategy = strategy;
	}

	/** full constructor */
	public UserStrategy(int userRole, Department department,
			Strategy strategy, String remark) {
		this.userRole = userRole;
		this.department = department;
		this.strategy = strategy;
		this.remark = remark;
	}

	// Property accessors

	public Integer getUserStrId() {
		return this.userStrId;
	}

	public void setUserStrId(Integer userStrId) {
		this.userStrId = userStrId;
	}

	public int getuserRole() {
		return this.userRole;
	}

	public void setuserRole(int userRole) {
		this.userRole = userRole;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Strategy getStrategy() {
		return this.strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}