package com.model;

/**
 * ReportViewId entity. @author MyEclipse Persistence Tools
 */

public class ReportViewId implements java.io.Serializable {

	// Fields

	private String riskName;

	// Constructors

	/** default constructor */
	public ReportViewId() {
	}

	

	/** full constructor */
	public ReportViewId(String riskName) {
		this.riskName = riskName;
	}

	// Property accessors

	public String getRiskName() {
		return this.riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ReportViewId))
			return false;
		ReportViewId castOther = (ReportViewId) other;

		return ((this.getRiskName() == castOther.getRiskName()) || (this
				.getRiskName() != null
				&& castOther.getRiskName() != null && this.getRiskName()
				.equals(castOther.getRiskName())))
			;
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRiskName() == null ? 0 : this.getRiskName().hashCode());
		return result;
	}

}