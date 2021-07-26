package com.model;


public class ReadFlowView {

	// Fields

	private String rfAction;
	private String frFlowName;
	private String retFlowId;
	private String retUserName;
	private String retDepName;
	private String retProcessTime;
	private String retFormId;
	private String retReportName;
	private String retUserId;
	
	private int retId;
	private int retTaskFlag;

	
	public ReadFlowView() {
	}

	public String getRfAction() {
		return this.rfAction;
	}

	public void setRfAction(String rfAction) {
		this.rfAction = rfAction;
	}

	public String getFrFlowName() {
		return this.frFlowName;
	}

	public void setFrFlowName(String frFlowName) {
		this.frFlowName = frFlowName;
	}

	public String getRetFlowId() {
		return this.retFlowId;
	}

	public void setRetFlowId(String retFlowId) {
		this.retFlowId = retFlowId;
	}

	public String getRetUserName() {
		return this.retUserName;
	}

	public void setRetUserName(String retUserName) {
		this.retUserName = retUserName;
	}

	public String getRetDepName() {
		return this.retDepName;
	}

	public void setRetDepName(String retDepName) {
		this.retDepName = retDepName;
	}

	public String getRetProcessTime() {
		return this.retProcessTime;
	}

	public void setRetProcessTime(String retProcessTime) {
		this.retProcessTime = retProcessTime;
	}

	public Integer getRetId() {
		return this.retId;
	}

	public void setRetId(Integer retId) {
		this.retId = retId;
	}

	public String getRetFormId() {
		return retFormId;
	}

	public void setRetFormId(String retFormId) {
		this.retFormId = retFormId;
	}

	public String getRetReportName() {
		return retReportName;
	}

	public void setRetReportName(String retReportName) {
		this.retReportName = retReportName;
	}

	public String getRetUserId() {
		return retUserId;
	}

	public void setRetUserId(String retUserId) {
		this.retUserId = retUserId;
	}

	public int getRetTaskFlag() {
		return retTaskFlag;
	}

	public void setRetTaskFlag(int retTaskFlag) {
		this.retTaskFlag = retTaskFlag;
	}

	public void setRetId(int retId) {
		this.retId = retId;
	}
	
}