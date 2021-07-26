package com.model;

/**
 * RiskFile entity. @author MyEclipse Persistence Tools
 */

public class RiskFile implements java.io.Serializable {

	// Fields

	private Integer id;
	private String riskId;
	private String fileId;

	// Constructors

	/** default constructor */
	public RiskFile() {
	}

	/** full constructor */
	public RiskFile(String riskId, String fileId) {
		this.riskId = riskId;
		this.fileId = fileId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRiskId() {
		return this.riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

}