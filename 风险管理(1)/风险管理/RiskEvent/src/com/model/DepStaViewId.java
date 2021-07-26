package com.model;

/**
 * DepStaViewId entity. @author MyEclipse Persistence Tools
 */

public class DepStaViewId implements java.io.Serializable {

	// Fields

	private String reIndep;
	private Float money;
	private String year;
	private Integer quarter;
	private Integer num;
	private String rtName;

	// Constructors

	/** default constructor */
	public DepStaViewId() {
	}

	/** minimal constructor */
	public DepStaViewId(String rtName) {
		this.rtName = rtName;
	}

	/** full constructor */
	public DepStaViewId(String reIndep, Float money, String year,
			Integer quarter, Integer num, String rtName) {
		this.reIndep = reIndep;
		this.money = money;
		this.year = year;
		this.quarter = quarter;
		this.num = num;
		this.rtName = rtName;
	}

	// Property accessors

	public String getReIndep() {
		return this.reIndep;
	}

	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}

	public Float getMoney() {
		return this.money;
	}

	public void setMoney(Float money) {
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

	public String getRtName() {
		return this.rtName;
	}

	public void setRtName(String rtName) {
		this.rtName = rtName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DepStaViewId))
			return false;
		DepStaViewId castOther = (DepStaViewId) other;

		return ((this.getReIndep() == castOther.getReIndep()) || (this
				.getReIndep() != null
				&& castOther.getReIndep() != null && this.getReIndep().equals(
				castOther.getReIndep())))
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
				&& ((this.getRtName() == castOther.getRtName()) || (this
						.getRtName() != null
						&& castOther.getRtName() != null && this.getRtName()
						.equals(castOther.getRtName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getReIndep() == null ? 0 : this.getReIndep().hashCode());
		result = 37 * result
				+ (getMoney() == null ? 0 : this.getMoney().hashCode());
		result = 37 * result
				+ (getYear() == null ? 0 : this.getYear().hashCode());
		result = 37 * result
				+ (getQuarter() == null ? 0 : this.getQuarter().hashCode());
		result = 37 * result
				+ (getNum() == null ? 0 : this.getNum().hashCode());
		result = 37 * result
				+ (getRtName() == null ? 0 : this.getRtName().hashCode());
		return result;
	}

}