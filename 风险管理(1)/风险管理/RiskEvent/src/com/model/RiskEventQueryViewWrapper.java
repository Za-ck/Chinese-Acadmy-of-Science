package com.model;

public class RiskEventQueryViewWrapper {

	private RiskEventQueryViewId id;
	private String riskName;
	private String depName;
	private String rmPlandate;
	private String rmDate;
	private String reinchargedep;
	private Double riAllvalue;
	private String indepid;
	private String reState;
	private String reAct;
	private String riskId;
	private String reEventname;

	
	public String getRmDate() {
		return rmDate;
	}

	public void setRmDate(String rmDate) {
		this.rmDate = rmDate;
	}

	public RiskEventQueryViewId getId() {
		return id;
	}

	public void setId(RiskEventQueryViewId id) {
		this.id = id;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getRmPlandate() {
		return rmPlandate;
	}

	public void setRmPlandate(String rmPlandate) {
		this.rmPlandate = rmPlandate;
	}

	public String getReinchargedep() {
		return reinchargedep;
	}

	public void setReinchargedep(String reinchargedep) {
		this.reinchargedep = reinchargedep;
	}

	public Double getRiAllvalue() {
		return riAllvalue;
	}

	public void setRiAllvalue(Double riAllvalue) {
		this.riAllvalue = riAllvalue;
	}

	public String getIndepid() {
		return indepid;
	}

	public void setIndepid(String indepid) {
		this.indepid = indepid;
	}

	public String getReState() {
		return reState;
	}

	public void setReState(String reState) {
		this.reState = reState;
	}

	public String getReAct() {
		return reAct;
	}

	public void setReAct(String reAct) {
		this.reAct = reAct;
	}

	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getReEventname() {
		return reEventname;
	}

	public void setReEventname(String reEventname) {
		this.reEventname = reEventname;
	}

}
