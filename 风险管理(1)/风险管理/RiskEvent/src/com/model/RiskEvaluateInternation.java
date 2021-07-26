package com.model;

/**
 * RiskEvaluateInternation entity. @author MyEclipse Persistence Tools
 */

public class RiskEvaluateInternation implements java.io.Serializable {

	// Fields

	private String reiId;
	private String reiName;
	private String reiCategory;
	private String reiCatename;
	private String reiMark;

	// Constructors

	/** default constructor */
	public RiskEvaluateInternation() {
	}

	/** minimal constructor */
	public RiskEvaluateInternation(String reiId, String reiName) {
		this.reiId = reiId;
		this.reiName = reiName;
	}

	/** full constructor */
	public RiskEvaluateInternation(String reiId, String reiName,
			String reiCategory, String reiCatename, String reiMark) {
		this.reiId = reiId;
		this.reiName = reiName;
		this.reiCategory = reiCategory;
		this.reiCatename = reiCatename;
		this.reiMark = reiMark;
	}

	// Property accessors

	public String getReiId() {
		return this.reiId;
	}

	public void setReiId(String reiId) {
		this.reiId = reiId;
	}

	public String getReiName() {
		return this.reiName;
	}

	public void setReiName(String reiName) {
		this.reiName = reiName;
	}

	public String getReiCategory() {
		return this.reiCategory;
	}

	public void setReiCategory(String reiCategory) {
		this.reiCategory = reiCategory;
	}

	public String getReiCatename() {
		return this.reiCatename;
	}

	public void setReiCatename(String reiCatename) {
		this.reiCatename = reiCatename;
	}

	public String getReiMark() {
		return this.reiMark;
	}

	public void setReiMark(String reiMark) {
		this.reiMark = reiMark;
	}

}