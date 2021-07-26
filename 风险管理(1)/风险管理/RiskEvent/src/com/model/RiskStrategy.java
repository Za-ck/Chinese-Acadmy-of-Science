package com.model;

/**
 * RiskStrategy entity. @author MyEclipse Persistence Tools
 */

public class RiskStrategy implements java.io.Serializable {

	// Fields

	private Integer riskStrId;
	private Strategy strategy;
	private Integer stragValue;
	private String stragStatue;
	private String stragColor;
	private int remark;

	// Constructors

	/** default constructor */
	public RiskStrategy() {
	}

	/** minimal constructor */
	public RiskStrategy(Strategy strategy, Integer stragValue) {
		this.strategy = strategy;
		this.stragValue = stragValue;
	}

	/** full constructor */
	public RiskStrategy(Strategy strategy, Integer stragValue,
			String stragStatue, String stragColor, int remark) {
		this.strategy = strategy;
		this.stragValue = stragValue;
		this.stragStatue = stragStatue;
		this.stragColor = stragColor;
		this.remark = remark;
	}

	// Property accessors

	public Integer getRiskStrId() {
		return this.riskStrId;
	}

	public void setRiskStrId(Integer riskStrId) {
		this.riskStrId = riskStrId;
	}

	public Strategy getStrategy() {
		return this.strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public Integer getStragValue() {
		return this.stragValue;
	}

	public void setStragValue(Integer stragValue) {
		this.stragValue = stragValue;
	}

	public String getStragStatue() {
		return this.stragStatue;
	}

	public void setStragStatue(String stragStatue) {
		this.stragStatue = stragStatue;
	}

	public String getStragColor() {
		return this.stragColor;
	}

	public void setStragColor(String stragColor) {
		this.stragColor = stragColor;
	}

	public int getRemark() {
		return this.remark;
	}

	public void setRemark(int remark) {
		this.remark = remark;
	}

}