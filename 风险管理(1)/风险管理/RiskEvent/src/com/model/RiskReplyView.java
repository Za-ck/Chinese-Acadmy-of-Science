package com.model;

import java.util.List;

/**
 * RiskReplyViewId entity. @author MyEclipse Persistence Tools
 */

public class RiskReplyView implements java.io.Serializable {

	// Fields
	private String reIndep;
	private String depName;
	private String rtName;
	private String riskId;
	private String riskName;
	private String reEventId;
	private String reEventname;
	private String year;
	private String reId;
	private String reDetail;
	private String rmStrategy;
	private String rmReply;
	private String rmPlandate;
	private String rmPlanres;
	private String reCreator;
	private String riskDep;
	private String reVerifier;
	private Integer depQueue;
	private String rmdate;
	private String remodifydate;
	private String takeTime;

	private String superviseMan;
	private String superviseTime;
	
	// Constructors

	public String getRmdate() {
		return rmdate;
	}

	public String getRemodifydate() {
		return remodifydate;
	}

	public void setRemodifydate(String remodifydate) {
		this.remodifydate = remodifydate;
	}

	public void setRmdate(String rmdate) {
		this.rmdate = rmdate;
	}

	// Property accessors

	public String getReIndep() {
		return this.reIndep;
	}

	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}

	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getRtName() {
		return this.rtName;
	}

	public void setRtName(String rtName) {
		this.rtName = rtName;
	}

	public String getRiskId() {
		return this.riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getRiskName() {
		return this.riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getReEventId() {
		return this.reEventId;
	}

	public void setReEventId(String reEventId) {
		this.reEventId = reEventId;
	}

	public String getReEventname() {
		return this.reEventname;
	}

	public void setReEventname(String reEventname) {
		this.reEventname = reEventname;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getReId() {
		return this.reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
	}

	public String getReDetail() {
		return this.reDetail;
	}

	public void setReDetail(String reDetail) {
		this.reDetail = reDetail;
	}

	public String getRmStrategy() {
		return this.rmStrategy;
	}

	public void setRmStrategy(String rmStrategy) {
		this.rmStrategy = rmStrategy;
	}

	public String getRmReply() {
		return this.rmReply;
	}

	public void setRmReply(String rmReply) {
		this.rmReply = rmReply;
	}

	public String getRmPlandate() {
		return rmPlandate;
	}

	public void setRmPlandate(String rmPlandate) {
		this.rmPlandate = rmPlandate;
	}

	public String getRmPlanres() {
		return this.rmPlanres;
	}

	public void setRmPlanres(String rmPlanres) {
		this.rmPlanres = rmPlanres;
	}

	public String getReCreator() {
		return this.reCreator;
	}

	public void setReCreator(String reCreator) {
		this.reCreator = reCreator;
	}

	public String getRiskDep() {
		return this.riskDep;
	}

	public void setRiskDep(String riskDep) {
		this.riskDep = riskDep;
	}

	public String getReVerifier() {
		return this.reVerifier;
	}

	public void setReVerifier(String reVerifier) {
		this.reVerifier = reVerifier;
	}

	public Integer getDepQueue() {
		return this.depQueue;
	}

	public void setDepQueue(Integer depQueue) {
		this.depQueue = depQueue;
	}

	/** default constructor */
	public RiskReplyView() {
	}
	
	public String getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}
	
	public String getSuperviseMan() {
		return superviseMan;
	}

	public void setSuperviseMan(String superviseMan) {
		this.superviseMan = superviseMan;
	}

	public String getSuperviseTime() {
		return superviseTime;
	}

	public void setSuperviseTime(String superviseTime) {
		this.superviseTime = superviseTime;
	}

}