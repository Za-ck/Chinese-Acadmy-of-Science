package com.model;

/**
 * ReportRecord entity. @author MyEclipse Persistence Tools
 */

public class ReportRecord {

	// Fields

	private Integer rrId;
	private String rrReportId;
	private String rrReportName;
	private String rrFlowId;
	private String rrFlowStatus;
	private String rrDepId;
	private String rrUserId;
	private String rrUserName;
	private String rrAction;
	private String rrDepName;
	private String rrProcessTime;
	private String rrView;
	

	// Constructors

	/** default constructor */
	public ReportRecord() {
	}

	// Property accessors

	public Integer getRrId() {
		return this.rrId;
	}

	public void setRrId(Integer rrId) {
		this.rrId = rrId;
	}

	public String getRrReportId() {
		return this.rrReportId;
	}

	public void setRrReportId(String rrReportId) {
		this.rrReportId = rrReportId;
	}

	public String getRrReportName() {
		return this.rrReportName;
	}

	public void setRrReportName(String rrReportName) {
		this.rrReportName = rrReportName;
	}

	public String getRrFlowId() {
		return this.rrFlowId;
	}

	public void setRrFlowId(String rrFlowId) {
		this.rrFlowId = rrFlowId;
	}

	public String getRrFlowStatus() {
		return this.rrFlowStatus;
	}

	public void setRrFlowStatus(String rrFlowStatus) {
		this.rrFlowStatus = rrFlowStatus;
	}

	public String getRrDepId() {
		return this.rrDepId;
	}

	public void setRrDepId(String rrDepId) {
		this.rrDepId = rrDepId;
	}

	public String getRrUserId() {
		return this.rrUserId;
	}

	public void setRrUserId(String rrUserId) {
		this.rrUserId = rrUserId;
	}

	public String getRrUserName() {
		return this.rrUserName;
	}

	public void setRrUserName(String rrUserName) {
		this.rrUserName = rrUserName;
	}

	public String getRrAction() {
		return rrAction;
	}

	public void setRrAction(String rrAction) {
		this.rrAction = rrAction;
	}

	public String getRrDepName() {
		return this.rrDepName;
	}

	public void setRrDepName(String rrDepName) {
		this.rrDepName = rrDepName;
	}

	public String getRrProcessTime() {
		return this.rrProcessTime;
	}

	public void setRrProcessTime(String rrProcessTime) {
		this.rrProcessTime = rrProcessTime;
	}

	public String getRrView() {
		return rrView;
	}

	public void setRrView(String rrView) {
		this.rrView = rrView;
	}

	
}