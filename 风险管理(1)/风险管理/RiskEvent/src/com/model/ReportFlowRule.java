package com.model;

/**
 * ReportFlowRule entity. @author MyEclipse Persistence Tools
 */

public class ReportFlowRule {

	// Fields

	private Integer rfId;
	private String rfFlowId;
	private String rfFlowStatus;
	private String rfFlowExplain;
	private String rfBackStatus;
	private String rfNextStatus;
	private String rfDepartment;
	private String rfRole;
	private String rfRemark;
	private String rfAction;
	private String rfActionUrl;
	private Integer rfTransitionFlag;
	private String rfFlowImage;
	// Constructors

	/** default constructor */
	public ReportFlowRule() {
	}

	// Property accessors

	public Integer getRfId() {
		return this.rfId;
	}

	public void setRfId(Integer rfId) {
		this.rfId = rfId;
	}

	public String getRfFlowId() {
		return this.rfFlowId;
	}

	public void setRfFlowId(String rfFlowId) {
		this.rfFlowId = rfFlowId;
	}

	public String getRfFlowStatus() {
		return this.rfFlowStatus;
	}

	public void setRfFlowStatus(String rfFlowStatus) {
		this.rfFlowStatus = rfFlowStatus;
	}

	public String getRfFlowExplain() {
		return this.rfFlowExplain;
	}

	public void setRfFlowExplain(String rfFlowExplain) {
		this.rfFlowExplain = rfFlowExplain;
	}

	public String getRfBackStatus() {
		return this.rfBackStatus;
	}

	public void setRfBackStatus(String rfBackStatus) {
		this.rfBackStatus = rfBackStatus;
	}

	public String getRfNextStatus() {
		return this.rfNextStatus;
	}

	public void setRfNextStatus(String rfNextStatus) {
		this.rfNextStatus = rfNextStatus;
	}

	public String getRfDepartment() {
		return this.rfDepartment;
	}

	public void setRfDepartment(String rfDepartment) {
		this.rfDepartment = rfDepartment;
	}

	public String getRfRole() {
		return this.rfRole;
	}

	public void setRfRole(String rfRole) {
		this.rfRole = rfRole;
	}

	public String getRfRemark() {
		return this.rfRemark;
	}

	public void setRfRemark(String rfRemark) {
		this.rfRemark = rfRemark;
	}

	public String getRfAction() {
		return rfAction;
	}

	public void setRfAction(String rfAction) {
		this.rfAction = rfAction;
	}

	public String getRfActionUrl() {
		return rfActionUrl;
	}

	public void setRfActionUrl(String rfActionUrl) {
		this.rfActionUrl = rfActionUrl;
	}

	public Integer getRfTransitionFlag() {
		return rfTransitionFlag;
	}

	public void setRfTransitionFlag(Integer rfTransitionFlag) {
		this.rfTransitionFlag = rfTransitionFlag;
	}

	public String getRfFlowImage() {
		return rfFlowImage;
	}

	public void setRfFlowImage(String rfFlowImage) {
		this.rfFlowImage = rfFlowImage;
	}
	
}