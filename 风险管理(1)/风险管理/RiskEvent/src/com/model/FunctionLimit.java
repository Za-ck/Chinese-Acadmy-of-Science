package com.model;

/**
 * FunctionLimit entity. @author MyEclipse Persistence Tools
 */

public class FunctionLimit implements java.io.Serializable {

	// Fields

	private Integer flId;
	private SystemRole systemRole;
	private FunctionModule functionModule;

	// Constructors

	/** default constructor */
	public FunctionLimit() {
	}

	/** full constructor */
	public FunctionLimit(Integer flId, SystemRole systemRole,
			FunctionModule functionModule) {
		this.flId = flId;
		this.systemRole = systemRole;
		this.functionModule = functionModule;
	}

	// Property accessors

	public Integer getFlId() {
		return this.flId;
	}

	public void setFlId(Integer flId) {
		this.flId = flId;
	}

	public SystemRole getSystemRole() {
		return this.systemRole;
	}

	public void setSystemRole(SystemRole systemRole) {
		this.systemRole = systemRole;
	}

	public FunctionModule getFunctionModule() {
		return this.functionModule;
	}

	public void setFunctionModule(FunctionModule functionModule) {
		this.functionModule = functionModule;
	}

}