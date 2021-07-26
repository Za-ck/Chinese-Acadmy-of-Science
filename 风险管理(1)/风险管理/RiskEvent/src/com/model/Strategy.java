package com.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Strategy entity. @author MyEclipse Persistence Tools
 */

public class Strategy implements java.io.Serializable {

	// Fields

	private Integer strategyId;
	private String strategyName;
	private String createDate;
	private String creator;
	private Set userStrategies = new HashSet(0);
	private Set riskStrategies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Strategy() {
	}

	/** full constructor */
	public Strategy(String strategyName, String createDate, String creator,
			Set userStrategies, Set riskStrategies) {
		this.strategyName = strategyName;
		this.createDate = createDate;
		this.creator = creator;
		this.userStrategies = userStrategies;
		this.riskStrategies = riskStrategies;
	}

	// Property accessors

	public Integer getStrategyId() {
		return this.strategyId;
	}

	public void setStrategyId(Integer strategyId) {
		this.strategyId = strategyId;
	}

	public String getStrategyName() {
		return this.strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Set getUserStrategies() {
		return this.userStrategies;
	}

	public void setUserStrategies(Set userStrategies) {
		this.userStrategies = userStrategies;
	}

	public Set getRiskStrategies() {
		return this.riskStrategies;
	}

	public void setRiskStrategies(Set riskStrategies) {
		this.riskStrategies = riskStrategies;
	}

}