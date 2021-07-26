package com.entity;

/**
 * ReportView entity. @author MyEclipse Persistence Tools
 */

public class ReportViewNew implements java.io.Serializable {

	// Fields
	
    private String riskId;
    private String rtName;
	private String riskName;
	private String riskQueue;
	//二级风险事件数
	private String riskNum;
	//可能性
	private Integer riskPro;
	//影响程度
	private Integer riskValuex;
	//实时概率
	private String freq;
	//综合评定值
	private double riAllvalue;
	//填报概率
	private float reportFreq;

	// Constructors

	/** default constructor */
	public ReportViewNew() {
	}

	/** full constructor */
	public ReportViewNew(String riskId) {
		this.riskId = riskId;
	}

	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getRtName() {
		return rtName;
	}

	public void setRtName(String rtName) {
		this.rtName = rtName;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getRiskQueue() {
		return riskQueue;
	}

	public void setRiskQueue(String riskQueue) {
		this.riskQueue = riskQueue;
	}

	public String getRiskNum() {
		return riskNum;
	}

	public void setRiskNum(String riskNum) {
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

	public String getFreq() {
		return freq;
	}

	public void setFreq(String freq) {
		this.freq = freq;
	}

	public double getRiAllvalue() {
		return riAllvalue;
	}

	public void setRiAllvalue(double riAllvalue) {
		this.riAllvalue = riAllvalue;
	}

	public float getReportFreq() {
		return reportFreq;
	}

	public void setReportFreq(float reportFreq) {
		this.reportFreq = reportFreq;
	}



	// Property accessors

	

	

	
}