package com.model;

/**
 * ReportRiskDep entity. @author MyEclipse Persistence Tools
 */

public class ReportRiskDep {

	// Fields

	private Integer rrdId;
	private String rrdReportId;
	private String rrdReportName;
	private String rrdDepId;
	private String rrdDepName;
	private String rrdFlowId;
	private String rrdStatus;
	private String rrdChargeId;
	private String rrdChargeName;

	// Constructors

	/** default constructor */
	public ReportRiskDep() {
	}

	/** minimal constructor */
	public ReportRiskDep(Integer rrdId) {
		this.rrdId = rrdId;
	}

	// Property accessors

	public Integer getRrdId() {
		return this.rrdId;
	}

	public void setRrdId(Integer rrdId) {
		this.rrdId = rrdId;
	}

	public String getRrdReportId() {
		return this.rrdReportId;
	}

	public void setRrdReportId(String rrdReportId) {
		this.rrdReportId = rrdReportId;
	}

	public String getRrdReportName() {
		return this.rrdReportName;
	}

	public void setRrdReportName(String rrdReportName) {
		this.rrdReportName = rrdReportName;
	}

	public String getRrdDepId() {
		return this.rrdDepId;
	}

	public void setRrdDepId(String rrdDepId) {
		this.rrdDepId = rrdDepId;
	}

	public String getRrdDepName() {
		return this.rrdDepName;
	}

	public void setRrdDepName(String rrdDepName) {
		this.rrdDepName = rrdDepName;
	}

	public String getRrdFlowId() {
		return this.rrdFlowId;
	}

	public void setRrdFlowId(String rrdFlowId) {
		this.rrdFlowId = rrdFlowId;
	}

	public String getRrdStatus() {
		return this.rrdStatus;
	}

	public void setRrdStatus(String rrdStatus) {
		this.rrdStatus = rrdStatus;
	}

	public String getRrdChargeId() {
		return rrdChargeId;
	}

	public void setRrdChargeId(String rrdChargeId) {
		this.rrdChargeId = rrdChargeId;
	}

	public String getRrdChargeName() {
		return rrdChargeName;
	}

	public void setRrdChargeName(String rrdChargeName) {
		this.rrdChargeName = rrdChargeName;
	}

}