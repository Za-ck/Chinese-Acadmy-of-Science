package com.model;

/**
 * Safe entity. @author MyEclipse Persistence Tools
 */

public class Safe implements java.io.Serializable {

	// Fields

	private Integer safId;
	private String safLevel;
	private String safSafety;
	private String safEnvironment;

	// Constructors

	/** default constructor */
	public Safe() {
	}

	/** minimal constructor */
	public Safe(Integer safId, String safLevel) {
		this.safId = safId;
		this.safLevel = safLevel;
	}

	/** full constructor */
	public Safe(Integer safId, String safLevel, String safSafety,
			String safEnvironment) {
		this.safId = safId;
		this.safLevel = safLevel;
		this.safSafety = safSafety;
		this.safEnvironment = safEnvironment;
	}

	// Property accessors

	public Integer getSafId() {
		return this.safId;
	}

	public void setSafId(Integer safId) {
		this.safId = safId;
	}

	public String getSafLevel() {
		return this.safLevel;
	}

	public void setSafLevel(String safLevel) {
		this.safLevel = safLevel;
	}

	public String getSafSafety() {
		return this.safSafety;
	}

	public void setSafSafety(String safSafety) {
		this.safSafety = safSafety;
	}

	public String getSafEnvironment() {
		return this.safEnvironment;
	}

	public void setSafEnvironment(String safEnvironment) {
		this.safEnvironment = safEnvironment;
	}

}