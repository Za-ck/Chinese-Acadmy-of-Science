package com.model;

/**
 * RiskFileViewId entity. @author MyEclipse Persistence Tools
 */

public class RiskFileView {

	// Fields

	private String riskId;
	private String fileId;
	private String fileName;
	private String fileType;
	private Integer id;

	// Property accessors

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

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

}