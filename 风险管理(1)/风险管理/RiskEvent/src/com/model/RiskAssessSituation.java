package com.model;

// default package

/**
 * RiskAssess entity. @author MyEclipse Persistence Tools
 */

public class RiskAssessSituation {

	// Fields

	private Integer raId;
	private String raDepId;
	private String raDepName;
	private String raYear;
	private String raQuarter;
	private Integer raNumberDeal;
	private Integer raNumberNeed;

	// Constructors

	/** default constructor */
	public RiskAssessSituation() {
	}

	public Integer getRaId() {
		return raId;
	}

	public void setRaId(Integer raId) {
		this.raId = raId;
	}

	public String getRaDepId() {
		return raDepId;
	}

	public void setRaDepId(String raDepId) {
		this.raDepId = raDepId;
	}

	public String getRaDepName() {
		return raDepName;
	}

	public void setRaDepName(String raDepName) {
		this.raDepName = raDepName;
	}
	
	public String getRaYear() {
		return raYear;
	}
	public void setRaYear(String raYear) {
		this.raYear = raYear;
	}

	public String getRaQuarter() {
		return raQuarter;
	}

	public void setRaQuarter(String raQuarter) {
		this.raQuarter = raQuarter;
	}
	
	
	public Integer getRaNumberNeed() {
		return raNumberNeed;
	}



	public void setRaNumberNeed(Integer raNumberNeed) {
		this.raNumberNeed = raNumberNeed;
	}



	public Integer getRaNumberDeal() {
		return raNumberDeal;
	}

	public void setRaNumberDeal(Integer raNumberDeal) {
		this.raNumberDeal = raNumberDeal;
	}
	
	// Property accessors

}