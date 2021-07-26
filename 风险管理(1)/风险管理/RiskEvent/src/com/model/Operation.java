package com.model;

/**
 * Operation entity. @author MyEclipse Persistence Tools
 */

public class Operation implements java.io.Serializable {

	// Fields

	private Integer opeId;
	private String opeLevel;
	private String opeDetail;

	// Constructors

	/** default constructor */
	public Operation() {
	}

	/** full constructor */
	public Operation(Integer opeId, String opeLevel, String opeDetail) {
		this.opeId = opeId;
		this.opeLevel = opeLevel;
		this.opeDetail = opeDetail;
	}

	// Property accessors

	public Integer getOpeId() {
		return this.opeId;
	}

	public void setOpeId(Integer opeId) {
		this.opeId = opeId;
	}

	public String getOpeLevel() {
		return this.opeLevel;
	}

	public void setOpeLevel(String opeLevel) {
		this.opeLevel = opeLevel;
	}

	public String getOpeDetail() {
		return this.opeDetail;
	}

	public void setOpeDetail(String opeDetail) {
		this.opeDetail = opeDetail;
	}

}