package com.model;

// default package

/**
 * EventStaView2Id entity. @author MyEclipse Persistence Tools
 */

public class EventStaView2Id implements java.io.Serializable {

	// Fields

	private String riskType;
	private String riskId;
	private String riskName;
	private String year;
	private Integer quarter;
	private Double money;
	private Integer eventnum;
	private Integer indepnum;

	// Constructors

	/** default constructor */
	public EventStaView2Id() {
	}

	/** minimal constructor */
	public EventStaView2Id(String riskType, String riskId, String riskName) {
		this.riskType = riskType;
		this.riskId = riskId;
		this.riskName = riskName;
	}

	/** full constructor */
	public EventStaView2Id(String riskType, String riskId, String riskName,
			String year, Integer quarter, Double money, Integer eventnum,
			Integer indepnum) {
		this.riskType = riskType;
		this.riskId = riskId;
		this.riskName = riskName;
		this.year = year;
		this.quarter = quarter;
		this.money = money;
		this.eventnum = eventnum;
		this.indepnum = indepnum;
	}

	// Property accessors

	public String getRiskType() {
		return this.riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
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

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getEventnum() {
		return this.eventnum;
	}

	public void setEventnum(Integer eventnum) {
		this.eventnum = eventnum;
	}

	public Integer getIndepnum() {
		return this.indepnum;
	}

	public void setIndepnum(Integer indepnum) {
		this.indepnum = indepnum;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EventStaView2Id))
			return false;
		EventStaView2Id castOther = (EventStaView2Id) other;

		return ((this.getRiskType() == castOther.getRiskType()) || (this
				.getRiskType() != null
				&& castOther.getRiskType() != null && this.getRiskType()
				.equals(castOther.getRiskType())))
				&& ((this.getRiskId() == castOther.getRiskId()) || (this
						.getRiskId() != null
						&& castOther.getRiskId() != null && this.getRiskId()
						.equals(castOther.getRiskId())))
				&& ((this.getRiskName() == castOther.getRiskName()) || (this
						.getRiskName() != null
						&& castOther.getRiskName() != null && this
						.getRiskName().equals(castOther.getRiskName())))
				&& ((this.getYear() == castOther.getYear()) || (this.getYear() != null
						&& castOther.getYear() != null && this.getYear()
						.equals(castOther.getYear())))
				&& ((this.getQuarter() == castOther.getQuarter()) || (this
						.getQuarter() != null
						&& castOther.getQuarter() != null && this.getQuarter()
						.equals(castOther.getQuarter())))
				&& ((this.getMoney() == castOther.getMoney()) || (this
						.getMoney() != null
						&& castOther.getMoney() != null && this.getMoney()
						.equals(castOther.getMoney())))
				&& ((this.getEventnum() == castOther.getEventnum()) || (this
						.getEventnum() != null
						&& castOther.getEventnum() != null && this
						.getEventnum().equals(castOther.getEventnum())))
				&& ((this.getIndepnum() == castOther.getIndepnum()) || (this
						.getIndepnum() != null
						&& castOther.getIndepnum() != null && this
						.getIndepnum().equals(castOther.getIndepnum())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRiskType() == null ? 0 : this.getRiskType().hashCode());
		result = 37 * result
				+ (getRiskId() == null ? 0 : this.getRiskId().hashCode());
		result = 37 * result
				+ (getRiskName() == null ? 0 : this.getRiskName().hashCode());
		result = 37 * result
				+ (getYear() == null ? 0 : this.getYear().hashCode());
		result = 37 * result
				+ (getQuarter() == null ? 0 : this.getQuarter().hashCode());
		result = 37 * result
				+ (getMoney() == null ? 0 : this.getMoney().hashCode());
		result = 37 * result
				+ (getEventnum() == null ? 0 : this.getEventnum().hashCode());
		result = 37 * result
				+ (getIndepnum() == null ? 0 : this.getIndepnum().hashCode());
		return result;
	}

}