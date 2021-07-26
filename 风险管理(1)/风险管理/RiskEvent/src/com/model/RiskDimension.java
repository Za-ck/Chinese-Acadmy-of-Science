package com.model;

/**
 * RiskAssess entity. @author MyEclipse Persistence Tools
 */

public class RiskDimension implements java.io.Serializable {

	// Fields

	private Integer rdId;
	private String rdDimName;
	private Integer rdIncreaseScore;
	private Integer rdDecreaseScore;
	private Integer rdWeight;
	private String rdDimId;
	private String rdRemark2;
	// Constructors
	/** full constructor */
	public RiskDimension() {
		// TODO Auto-generated constructor stub
	}
	
	public RiskDimension(Integer rdId, String rdDimName, Integer rdIncreaseScore, Integer rdDecreaseScore,
			Integer rdWeight, String rdDimId, String rdRemark2) {
		this.rdId = rdId;
		this.rdDimName = rdDimName;
		this.rdIncreaseScore = rdIncreaseScore;
		this.rdDecreaseScore = rdDecreaseScore;
		this.rdWeight = rdWeight;
		this.rdDimId = rdDimId;
		this.rdRemark2 = rdRemark2;
	}

	public Integer getRdId() {
		return rdId;
	}

	public void setRdId(Integer rdId) {
		this.rdId = rdId;
	}

	public String getRdDimName() {
		return rdDimName;
	}

	public void setRdDimName(String rdDimName) {
		this.rdDimName = rdDimName;
	}

	public Integer getRdIncreaseScore() {
		return rdIncreaseScore;
	}

	public void setRdIncreaseScore(Integer rdIncreaseScore) {
		this.rdIncreaseScore = rdIncreaseScore;
	}

	public Integer getRdDecreaseScore() {
		return rdDecreaseScore;
	}

	public void setRdDecreaseScore(Integer rdDecreaseScore) {
		this.rdDecreaseScore = rdDecreaseScore;
	}

	public Integer getRdWeight() {
		return rdWeight;
	}

	public void setRdWeight(Integer rdWeight) {
		this.rdWeight = rdWeight;
	}


	public String getRdDimId() {
		return rdDimId;
	}

	public void setRdDimId(String rdDimId) {
		this.rdDimId = rdDimId;
	}

	public String getRdRemark2() {
		return rdRemark2;
	}

	public void setRdRemark2(String rdRemark2) {
		this.rdRemark2 = rdRemark2;
	}
	// Property accessors

}