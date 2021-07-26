package com.model;

/**
 * FormInfo entity. @author MyEclipse Persistence Tools
 */

public class ReportFormInfo {

	// Fields

	private Integer fiId;
	private String fiFormId;
	private String fiFlowId;
	private String fiFlowStatus;
	private String fiWriterId;
	private String fiWriterName;
	private String fiWriterDepId;
	private String fiWriterDepName;
	private String fiStatements;
	private String fiLastdate;

	// Constructors

	/** default constructor */
	public ReportFormInfo() {
	}

	/** minimal constructor */
	public ReportFormInfo(Integer fiId) {
		this.fiId = fiId;
	}

	// Property accessors

	public Integer getFiId() {
		return this.fiId;
	}

	public void setFiId(Integer fiId) {
		this.fiId = fiId;
	}

	public String getFiFormId() {
		return this.fiFormId;
	}

	public void setFiFormId(String fiFormId) {
		this.fiFormId = fiFormId;
	}

	public String getFiFlowId() {
		return this.fiFlowId;
	}

	public void setFiFlowId(String fiFlowId) {
		this.fiFlowId = fiFlowId;
	}

	public String getFiFlowStatus() {
		return this.fiFlowStatus;
	}

	public void setFiFlowStatus(String fiFlowStatus) {
		this.fiFlowStatus = fiFlowStatus;
	}

	public String getFiWriterId() {
		return this.fiWriterId;
	}

	public void setFiWriterId(String fiWriterId) {
		this.fiWriterId = fiWriterId;
	}

	public String getFiWriterName() {
		return this.fiWriterName;
	}

	public void setFiWriterName(String fiWriterName) {
		this.fiWriterName = fiWriterName;
	}

	public String getFiWriterDepId() {
		return this.fiWriterDepId;
	}

	public void setFiWriterDepId(String fiWriterDepId) {
		this.fiWriterDepId = fiWriterDepId;
	}

	public String getFiWriterDepName() {
		return this.fiWriterDepName;
	}

	public void setFiWriterDepName(String fiWriterDepName) {
		this.fiWriterDepName = fiWriterDepName;
	}

	public String getFiStatements() {
		return fiStatements;
	}

	public void setFiStatements(String fiStatements) {
		this.fiStatements = fiStatements;
	}

	public String getFiLastdate() {
		return fiLastdate;
	}

	public void setFiLastdate(String fiLastdate) {
		this.fiLastdate = fiLastdate;
	}
	
}