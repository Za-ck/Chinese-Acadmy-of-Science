package com.model;

/**
 * ReportFlowRuleInfo entity. @author MyEclipse Persistence Tools
 */

public class ReportFlowRuleInfo {

	// Fields

	private Integer frId;
	private String frFlowId;
	private String frFlowName;
	private String frFlowActionName;

	// Constructors

	/** default constructor */
	public ReportFlowRuleInfo() {
	}

	

	// Property accessors

	public Integer getFrId() {
		return this.frId;
	}

	public void setFrId(Integer frId) {
		this.frId = frId;
	}

	public String getFrFlowId() {
		return this.frFlowId;
	}

	public void setFrFlowId(String frFlowId) {
		this.frFlowId = frFlowId;
	}

	public String getFrFlowName() {
		return this.frFlowName;
	}

	public void setFrFlowName(String frFlowName) {
		this.frFlowName = frFlowName;
	}



	public String getFrFlowActionName() {
		return frFlowActionName;
	}



	public void setFrFlowActionName(String frFlowActionName) {
		this.frFlowActionName = frFlowActionName;
	}
	
	

}