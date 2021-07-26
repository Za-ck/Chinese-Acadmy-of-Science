package com.model;

/**
 * Law entity. @author MyEclipse Persistence Tools
 */

public class Law implements java.io.Serializable {

	// Fields

	private Integer lawId;
	private String lawLevel;
	private String lawDetail;

	// Constructors

	/** default constructor */
	public Law() {
	}

	/** full constructor */
	public Law(Integer lawId, String lawLevel, String lawDetail) {
		this.lawId = lawId;
		this.lawLevel = lawLevel;
		this.lawDetail = lawDetail;
	}

	// Property accessors

	public Integer getLawId() {
		return this.lawId;
	}

	public void setLawId(Integer lawId) {
		this.lawId = lawId;
	}

	public String getLawLevel() {
		return this.lawLevel;
	}

	public void setLawLevel(String lawLevel) {
		this.lawLevel = lawLevel;
	}

	public String getLawDetail() {
		return this.lawDetail;
	}

	public void setLawDetail(String lawDetail) {
		this.lawDetail = lawDetail;
	}

}