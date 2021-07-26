package com.model;

/**
 * ReportRisk entity. @author MyEclipse Persistence Tools
 */

public class ReportRisk {

	// Fields
	private String rerReportId;
	private String rerReportName;
	private String rerPromoterId;
	private String rerPromoterName;
	private String rerRemark;
	private String rerAnnual;
	private Integer rerCancelNum;
	private String rerDate;
	private Integer rerDepNum;			//需要填写报告的部门的个数
	private Integer rerReceiveNum;		//接收报告的部门的个数
	private Integer rerDoneNum;			//已经填写报告的部门的个数

	// Constructors

	/** default constructor */
	public ReportRisk() {
		
	}
	
	// Property accessors

	public String getRerReportId() {
		return this.rerReportId;
	}

	public String getRerAnnual() {
		return rerAnnual;
	}

	public void setRerAnnual(String rerAnnual) {
		this.rerAnnual = rerAnnual;
	}

	public void setRerReportId(String rerReportId) {
		this.rerReportId = rerReportId;
	}

	public String getRerReportName() {
		return this.rerReportName;
	}

	public void setRerReportName(String rerReportName) {
		this.rerReportName = rerReportName;
	}
	
	public String getRerPromoterId() {
		return this.rerPromoterId;
	}

	public void setRerPromoterId(String rerPromoterId) {
		this.rerPromoterId = rerPromoterId;
	}

	public String getRerPromoterName() {
		return this.rerPromoterName;
	}

	public void setRerPromoterName(String rerPromoterName) {
		this.rerPromoterName = rerPromoterName;
	}

	public String getRerRemark() {
		return this.rerRemark;
	}

	public void setRerRemark(String rerRemark) {
		this.rerRemark = rerRemark;
	}

	public String getRerDate() {
		return rerDate;
	}

	public void setRerDate(String rerDate) {
		this.rerDate = rerDate;
	}

	public Integer getRerDepNum() {
		return rerDepNum;
	}

	public void setRerDepNum(Integer rerDepNum) {
		this.rerDepNum = rerDepNum;
	}

	public Integer getRerReceiveNum() {
		return rerReceiveNum;
	}

	public void setRerReceiveNum(Integer rerReceiveNum) {
		this.rerReceiveNum = rerReceiveNum;
	}

	public Integer getRerDoneNum() {
		return rerDoneNum;
	}

	public void setRerDoneNum(Integer rerDoneNum) {
		this.rerDoneNum = rerDoneNum;
	}

	public Integer getRerCancelNum() {
		return rerCancelNum;
	}

	public void setRerCancelNum(Integer rerCancelNum) {
		this.rerCancelNum = rerCancelNum;
	}

}