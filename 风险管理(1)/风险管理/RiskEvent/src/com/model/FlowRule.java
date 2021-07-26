package com.model;

/**
 * FlowRule entity. @author MyEclipse Persistence Tools
 */

public class FlowRule implements java.io.Serializable {

	// Fields

	private Integer frId;
	private String riskId;
	private String frStatus;
	private String frNextstatus;
	private String frPrestatus;
	private String frDepart;
	private String frRole;
	private String frRemark1;
	private String frRemark2;
	private String deparment;
	private String passuser;
	private String passdate;
	private String yesOrno;
	
	
	// Constructors

	public String getDeparment() {
		return deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	public String getPassuser() {
		return passuser;
	}

	public void setPassuser(String passuser) {
		this.passuser = passuser;
	}

	public String getPassdate() {
		return passdate;
	}

	public void setPassdate(String passdate) {
		this.passdate = passdate;
	}

	public String getYesOrno() {
		return yesOrno;
	}

	public void setYesOrno(String yesOrno) {
		this.yesOrno = yesOrno;
	}

	/** default constructor */
	public FlowRule() {
	}

	/** minimal constructor */
	public FlowRule(Integer frId) {
		this.frId = frId;
	}

	/** full constructor */
	public FlowRule(Integer frId, String riskId, String frStatus,
			String frNextstatus, String frPrestatus, String frDepart,
			String frRole, String frRemark1, String frRemark2) {
		this.frId = frId;
		this.riskId = riskId;
		this.frStatus = frStatus;
		this.frNextstatus = frNextstatus;
		this.frPrestatus = frPrestatus;
		this.frDepart = frDepart;
		this.frRole = frRole;
		this.frRemark1 = frRemark1;
		this.frRemark2 = frRemark2;
	}

	// Property accessors

	public Integer getFrId() {
		return this.frId;
	}

	public void setFrId(Integer frId) {
		this.frId = frId;
	}

	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getFrStatus() {
		return this.frStatus;
	}

	public void setFrStatus(String frStatus) {
		this.frStatus = frStatus;
	}

	public String getFrNextstatus() {
		return this.frNextstatus;
	}

	public void setFrNextstatus(String frNextstatus) {
		this.frNextstatus = frNextstatus;
	}

	public String getFrPrestatus() {
		return this.frPrestatus;
	}

	public void setFrPrestatus(String frPrestatus) {
		this.frPrestatus = frPrestatus;
	}

	public String getFrDepart() {
		return this.frDepart;
	}

	public void setFrDepart(String frDepart) {
		this.frDepart = frDepart;
	}

	public String getFrRole() {
		return this.frRole;
	}

	public void setFrRole(String frRole) {
		this.frRole = frRole;
	}

	public String getFrRemark1() {
		return this.frRemark1;
	}

	public void setFrRemark1(String frRemark1) {
		this.frRemark1 = frRemark1;
	}

	public String getFrRemark2() {
		return this.frRemark2;
	}

	public void setFrRemark2(String frRemark2) {
		this.frRemark2 = frRemark2;
	}

}