package com.model;

/**
 * RiskEventQueryViewId entity. @author MyEclipse Persistence Tools
 */

public class RiskEventQueryViewId implements java.io.Serializable {

	// Fields

	private String reId;
	

	// Constructors

	/** default constructor */
	public RiskEventQueryViewId() {
	}



	/** full constructor */
	public RiskEventQueryViewId(String reId) {
		this.reId = reId;
	}

	// Property accessors

	public String getReId() {
		return this.reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
	}



	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RiskEventQueryViewId))
			return false;
		RiskEventQueryViewId castOther = (RiskEventQueryViewId) other;

		return ((this.getReId() == castOther.getReId()) || (this.getReId() != null
				&& castOther.getReId() != null && this.getReId().equals(
				castOther.getReId())));
				
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getReId() == null ? 0 : this.getReId().hashCode());
		return result;
	}

}