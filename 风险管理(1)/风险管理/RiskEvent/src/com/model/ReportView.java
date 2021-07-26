package com.model;

/**
 * ReportView entity. @author MyEclipse Persistence Tools
 */

public class ReportView implements java.io.Serializable {

	// Fields

	private ReportViewId id;
	private Integer riskQueue;
	private String year;
	private Integer riskNum;
	private Integer riskPro;
	private Integer riskValuex;

	// Constructors

	/** default constructor */
	public ReportView() {
	}

	/** full constructor */
	public ReportView(ReportViewId id) {
		this.id = id;
	}

	// Property accessors

	public ReportViewId getId() {
		return this.id;
	}

	public void setId(ReportViewId id) {
		this.id = id;
	}

	public Integer getRiskQueue() {
		return riskQueue;
	}

	public void setRiskQueue(Integer riskQueue) {
		this.riskQueue = riskQueue;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getRiskNum() {
		return riskNum;
	}

	public void setRiskNum(Integer riskNum) {
		this.riskNum = riskNum;
	}

	public Integer getRiskPro() {
		return riskPro;
	}

	public void setRiskPro(Integer riskPro) {
		this.riskPro = riskPro;
	}

	public Integer getRiskValuex() {
		return riskValuex;
	}

	public void setRiskValuex(Integer riskValuex) {
		this.riskValuex = riskValuex;
	}

}