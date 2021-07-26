package com.model;

/**
 * RiskManage entity. @author MyEclipse Persistence Tools
 */

public class RiskManage {

	// Fields

	private String rmReId;
	private String rmChance;
	private String rmControl;
	private String rmReply;
	private String rmStrategy;
	private String rmPlanres;
	private String rmPlandate;
	private Integer rmSign;
	private String rmWarnvalue;
    private String rmTaketime;
    private String rmReplyman;
    private String rmSuperviseMan;
    private String rmSuperviseTime;
	// Constructors

	/** default constructor */
	public RiskManage() {
	}

	public String getRmReId() {
		return this.rmReId;
	}

	public void setRmReId(String rmReId) {
		this.rmReId = rmReId;
	}


	public String getRmChance() {
		return this.rmChance;
	}

	public void setRmChance(String rmChance) {
		this.rmChance = rmChance;
	}

	public String getRmControl() {
		return this.rmControl;
	}

	public void setRmControl(String rmControl) {
		this.rmControl = rmControl;
	}

	public String getRmReply() {
		return this.rmReply;
	}

	public void setRmReply(String rmReply) {
		this.rmReply = rmReply;
	}

	public String getRmStrategy() {
		return this.rmStrategy;
	}

	public void setRmStrategy(String rmStrategy) {
		this.rmStrategy = rmStrategy;
	}

	public String getRmPlanres() {
		return this.rmPlanres;
	}

	public void setRmPlanres(String rmPlanres) {
		this.rmPlanres = rmPlanres;
	}

	public String getRmPlandate() {
		return this.rmPlandate;
	}

	public void setRmPlandate(String rmPlandate) {
		this.rmPlandate = rmPlandate;
	}

	public Integer getRmSign() {
		return this.rmSign;
	}

	public void setRmSign(Integer rmSign) {
		this.rmSign = rmSign;
	}

	public String getRmWarnvalue() {
		return this.rmWarnvalue;
	}

	public void setRmWarnvalue(String rmWarnvalue) {
		this.rmWarnvalue = rmWarnvalue;
	}

	public String getRmTaketime() {
		return rmTaketime;
	}

	public void setRmTaketime(String rmTaketime) {
		this.rmTaketime = rmTaketime;
	}

	public String getRmReplyman() {
		return rmReplyman;
	}

	public void setRmReplyman(String rmReplyman) {
		this.rmReplyman = rmReplyman;
	}

	public String getRmSuperviseMan() {
		return rmSuperviseMan;
	}

	public void setRmSuperviseMan(String rmSuperviseMan) {
		this.rmSuperviseMan = rmSuperviseMan;
	}

	public String getRmSuperviseTime() {
		return rmSuperviseTime;
	}

	public void setRmSuperviseTime(String rmSuperviseTime) {
		this.rmSuperviseTime = rmSuperviseTime;
	}
}