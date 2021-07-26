package com.model;


/**
 * Risk entity. @author MyEclipse Persistence Tools
 */

public class Risk {

	// Fields

	private String riskId;
	private String riskType;
	private String riskName;
	private String riskDep;
	private String riskRemark;
	private Integer riskQueue;
	private String riskCurrent;
	
	// Constructors

	/** default constructor */
	public Risk() {
	}

	public String getRiskId() {
		return this.riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getRiskName() {
		return this.riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getRiskDep() {
		return this.riskDep;
	}

	public void setRiskDep(String riskDep) {
		this.riskDep = riskDep;
	}

	public String getRiskRemark() {
		return this.riskRemark;
	}

	public void setRiskRemark(String riskRemark) {
		this.riskRemark = riskRemark;
	}

	public Integer getRiskQueue() {
		return riskQueue;
	}

	public void setRiskQueue(Integer riskQueue) {
		this.riskQueue = riskQueue;
	}
	
	public String getRiskCurrent() {
		return riskCurrent;
	}

	public void setRiskCurrent(String riskCurrent) {
		this.riskCurrent = riskCurrent;
	}

}