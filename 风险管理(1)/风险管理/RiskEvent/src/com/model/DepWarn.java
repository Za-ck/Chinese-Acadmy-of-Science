package com.model;

import java.sql.Timestamp;

import com.model.Department;

/**
 * DepWarn entity. @author MyEclipse Persistence Tools
 */

public class DepWarn implements java.io.Serializable {

	// Fields

	private Integer dwId;
	private Department department;
	private String riskEvent;
	private Integer dwWarnnum;
	private String dwTime;
	private String dwPlanTime;
	private String dwReason;
	

	// Constructors

	/** default constructor */
	public DepWarn() {
	}

	/** minimal constructor */
	public DepWarn(Department department, String riskEvent, String dwTime) {
		this.department = department;
		this.riskEvent = riskEvent;
		this.dwTime = dwTime;
	}

	/** full constructor */
	public DepWarn(Department department, String riskEvent,
			Integer dwWarnnum, String dwTime,String dwReason) {
		this.department = department;
		this.riskEvent = riskEvent;
		this.dwWarnnum = dwWarnnum;
		this.dwTime = dwTime;
		this.dwReason=dwReason;
	}

	// Property accessors

	public Integer getDwId() {
		return this.dwId;
	}

	
	public String getDwPlanTime() {
		return dwPlanTime;
	}

	public void setDwPlanTime(String dwPlanTime) {
		this.dwPlanTime = dwPlanTime;
	}

	public String getDwReason() {
		return dwReason;
	}

	public void setDwReason(String dwReason) {
		this.dwReason = dwReason;
	}

	public void setDwId(Integer dwId) {
		this.dwId = dwId;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getRiskEvent() {
		return this.riskEvent;
	}

	public void setRiskEvent(String riskEvent) {
		this.riskEvent = riskEvent;
	}

	public Integer getDwWarnnum() {
		return this.dwWarnnum;
	}

	public void setDwWarnnum(Integer dwWarnnum) {
		this.dwWarnnum = dwWarnnum;
	}

	public String getDwTime() {
		return this.dwTime;
	}

	public void setDwTime(String dwTime) {
		this.dwTime = dwTime;
	}

}