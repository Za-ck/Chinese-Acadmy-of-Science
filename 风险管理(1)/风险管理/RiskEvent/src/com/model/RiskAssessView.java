package com.model;

/**
 * RiskAssessViewId entity. @author MyEclipse Persistence Tools
 */

public class RiskAssessView {

	// Fields

	private String raDepName;
	private String raYear;
	private String raYearTo;
	private String raQuarter;
	private String raQuarterTo;
	private String raDepProperty;
	private String raDepId;
	private Integer raNumberInput;
	private Integer raNumberDeal;
	private Integer raNumberNeed;
	private String raMonth;
	private Integer raId;
	private Integer depAssess;

	// Constructors

	/** default constructor */
	public RiskAssessView() {
	}

	// Property accessors

	public String getRaDepName() {
		return this.raDepName;
	}

	public void setRaDepName(String raDepName) {
		this.raDepName = raDepName;
	}

	public String getRaYear() {
		return this.raYear;
	}

	public void setRaYear(String raYear) {
		this.raYear = raYear;
	}

	
	public String getRaYearTo() {
		return raYearTo;
	}

	public void setRaYearTo(String raYearTo) {
		this.raYearTo = raYearTo;
	}

	public String getRaQuarter() {
		return this.raQuarter;
	}

	public void setRaQuarter(String raQuarter) {
		this.raQuarter = raQuarter;
	}

	
	public String getRaQuarterTo() {
		return raQuarterTo;
	}

	public void setRaQuarterTo(String raQuarterTo) {
		this.raQuarterTo = raQuarterTo;
	}

	public String getRaDepProperty() {
		return this.raDepProperty;
	}

	public void setRaDepProperty(String raDepProperty) {
		this.raDepProperty = raDepProperty;
	}

	public String getRaDepId() {
		return this.raDepId;
	}

	public void setRaDepId(String raDepId) {
		this.raDepId = raDepId;
	}

	public Integer getRaNumberInput() {
		return this.raNumberInput;
	}

	public void setRaNumberInput(Integer raNumberInput) {
		this.raNumberInput = raNumberInput;
	}

	public Integer getRaNumberDeal() {
		return this.raNumberDeal;
	}

	public void setRaNumberDeal(Integer raNumberDeal) {
		this.raNumberDeal = raNumberDeal;
	}

	public Integer getRaNumberNeed() {
		return raNumberNeed;
	}

	public void setRaNumberNeed(Integer raNumberNeed) {
		this.raNumberNeed = raNumberNeed;
	}

	public String getRaMonth() {
		return this.raMonth;
	}

	public void setRaMonth(String raMonth) {
		this.raMonth = raMonth;
	}

	public Integer getRaId() {
		return this.raId;
	}

	public void setRaId(Integer raId) {
		this.raId = raId;
	}

	public Integer getDepAssess() {
		return depAssess;
	}

	public void setDepAssess(Integer depAssess) {
		this.depAssess = depAssess;
	}

}