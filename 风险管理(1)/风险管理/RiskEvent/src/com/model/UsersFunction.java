package com.model;

/**
 * UsersFunction entity. @author MyEclipse Persistence Tools
 */
public class UsersFunction implements java.io.Serializable {

	private UsersFunctionId id;

	// Constructors

	/** default constructor */
	public UsersFunction() {
	}

	/** full constructor */
	public UsersFunction(UsersFunctionId id) {
		this.id = id;
	}

	// Property accessors

	public UsersFunctionId getId() {
		return this.id;
	}

	public void setId(UsersFunctionId id) {
		this.id = id;
	}
}
