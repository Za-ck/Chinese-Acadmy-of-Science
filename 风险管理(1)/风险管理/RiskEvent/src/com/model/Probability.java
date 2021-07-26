package com.model;

/**
 * Probability entity. @author MyEclipse Persistence Tools
 */

public class Probability implements java.io.Serializable {

	// Fields

	private Integer proId;
	private String proLevel;
	private String proProbability;
	private String proDisasterEvent;
	private String proDailyOperation;

	// Constructors

	/** default constructor */
	public Probability() {
	}

	/** full constructor */
	public Probability(Integer proId, String proLevel, String proProbability,
			String proDisasterEvent, String proDailyOperation) {
		this.proId = proId;
		this.proLevel = proLevel;
		this.proProbability = proProbability;
		this.proDisasterEvent = proDisasterEvent;
		this.proDailyOperation = proDailyOperation;
	}

	// Property accessors

	public Integer getProId() {
		return this.proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getProLevel() {
		return this.proLevel;
	}

	public void setProLevel(String proLevel) {
		this.proLevel = proLevel;
	}

	public String getProProbability() {
		return this.proProbability;
	}

	public void setProProbability(String proProbability) {
		this.proProbability = proProbability;
	}

	public String getProDisasterEvent() {
		return this.proDisasterEvent;
	}

	public void setProDisasterEvent(String proDisasterEvent) {
		this.proDisasterEvent = proDisasterEvent;
	}

	public String getProDailyOperation() {
		return this.proDailyOperation;
	}

	public void setProDailyOperation(String proDailyOperation) {
		this.proDailyOperation = proDailyOperation;
	}

}