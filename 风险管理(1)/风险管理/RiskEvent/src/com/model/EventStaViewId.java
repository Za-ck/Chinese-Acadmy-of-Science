package com.model;

/**
 * EventStaViewId entity. @author MyEclipse Persistence Tools
 */

public class EventStaViewId implements java.io.Serializable {

	// Fields

	private String riskId;
	private String riskName;
	private Double money;
	private String year;
	private Integer quarter;
	private Integer num;
	private String riskType;
	private String reIndep;

	// Constructors

	/** default constructor */
	public EventStaViewId() {
	}

	/** minimal constructor */
	public EventStaViewId(String riskId, String riskName, String riskType,
			String reIndep) {
		this.riskId = riskId;
		this.riskName = riskName;
		this.riskType = riskType;
		this.reIndep = reIndep;
	}

	/** full constructor */
	public EventStaViewId(String riskId, String riskName, Double money,
			String year, Integer quarter, Integer num, String riskType,
			String reIndep) {
		this.riskId = riskId;
		this.riskName = riskName;
		this.money = money;
		this.year = year;
		this.quarter = quarter;
		this.num = num;
		this.riskType = riskType;
		this.reIndep = reIndep;
	}

	// Property accessors

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

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getQuarter() {
		return this.quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getRiskType() {
		return this.riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getReIndep() {
		return this.reIndep;
	}

	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EventStaViewId))
			return false;
		EventStaViewId castOther = (EventStaViewId) other;

		return ((this.getRiskId() == castOther.getRiskId()) || (this
				.getRiskId() != null
				&& castOther.getRiskId() != null && this.getRiskId().equals(
				castOther.getRiskId())))
				&& ((this.getRiskName() == castOther.getRiskName()) || (this
						.getRiskName() != null
						&& castOther.getRiskName() != null && this
						.getRiskName().equals(castOther.getRiskName())))
				&& ((this.getMoney() == castOther.getMoney()) || (this
						.getMoney() != null
						&& castOther.getMoney() != null && this.getMoney()
						.equals(castOther.getMoney())))
				&& ((this.getYear() == castOther.getYear()) || (this.getYear() != null
						&& castOther.getYear() != null && this.getYear()
						.equals(castOther.getYear())))
				&& ((this.getQuarter() == castOther.getQuarter()) || (this
						.getQuarter() != null
						&& castOther.getQuarter() != null && this.getQuarter()
						.equals(castOther.getQuarter())))
				&& ((this.getNum() == castOther.getNum()) || (this.getNum() != null
						&& castOther.getNum() != null && this.getNum().equals(
						castOther.getNum())))
				&& ((this.getRiskType() == castOther.getRiskType()) || (this
						.getRiskType() != null
						&& castOther.getRiskType() != null && this
						.getRiskType().equals(castOther.getRiskType())))
				&& ((this.getReIndep() == castOther.getReIndep()) || (this
						.getReIndep() != null
						&& castOther.getReIndep() != null && this.getReIndep()
						.equals(castOther.getReIndep())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRiskId() == null ? 0 : this.getRiskId().hashCode());
		result = 37 * result
				+ (getRiskName() == null ? 0 : this.getRiskName().hashCode());
		result = 37 * result
				+ (getMoney() == null ? 0 : this.getMoney().hashCode());
		result = 37 * result
				+ (getYear() == null ? 0 : this.getYear().hashCode());
		result = 37 * result
				+ (getQuarter() == null ? 0 : this.getQuarter().hashCode());
		result = 37 * result
				+ (getNum() == null ? 0 : this.getNum().hashCode());
		result = 37 * result
				+ (getRiskType() == null ? 0 : this.getRiskType().hashCode());
		result = 37 * result
				+ (getReIndep() == null ? 0 : this.getReIndep().hashCode());
		return result;
	}

}