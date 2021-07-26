package com.model;

/**
 * EventWarnViewId entity. @author MyEclipse Persistence Tools
 */

public class EventWarnViewId implements java.io.Serializable {

	// Fields

	private String reId;
	private String riskName;
	private String reDate;
	private String rmPlandate;
	private Integer rmSign;
	private String depName;
	private String depId;
	private String userName;
	private String reEventname;

	// Constructors

	/** default constructor */
	public EventWarnViewId() {
	}

	public String getReId() {
		return reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getReDate() {
		return reDate;
	}

	public void setReDate(String reDate) {
		this.reDate = reDate;
	}

	public String getRmPlandate() {
		return rmPlandate;
	}

	public void setRmPlandate(String rmPlandate) {
		this.rmPlandate = rmPlandate;
	}

	public Integer getRmSign() {
		return rmSign;
	}

	public void setRmSign(Integer rmSign) {
		this.rmSign = rmSign;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReEventname() {
		return reEventname;
	}

	public void setReEventname(String reEventname) {
		this.reEventname = reEventname;
	}
	
}