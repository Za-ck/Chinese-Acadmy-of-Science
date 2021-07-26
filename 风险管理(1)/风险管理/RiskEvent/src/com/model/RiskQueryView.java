package com.model;

/**
 * RiskQueryViewId entity. @author MyEclipse Persistence Tools
 */

public class RiskQueryView {

	// Fields

	private String riskId;
	private String riskName;
	private String riskType;
	private String riskDep;
	private String riskRemark;
	private Integer riskQueue;
	private String riskCurrent;
	private String rtName;

	// Constructors

	/** default constructor */
	public RiskQueryView() {
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

	public String getRiskType() {
		return this.riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getRiskDep() {
		return this.riskDep;
	}

	public void setRiskDep(String riskDep) {
		this.riskDep = riskDep;
	}

	public String getRiskRemark() {
		return this.riskRemark;
	}

	public void setRiskRemark(String riskRemark) {
		this.riskRemark = riskRemark;
	}

	public Integer getRiskQueue() {
		return this.riskQueue;
	}

	public void setRiskQueue(Integer riskQueue) {
		this.riskQueue = riskQueue;
	}

	public String getRiskCurrent() {
		return this.riskCurrent;
	}

	public void setRiskCurrent(String riskCurrent) {
		this.riskCurrent = riskCurrent;
	}

	public String getRtName() {
		return this.rtName;
	}

	public void setRtName(String rtName) {
		this.rtName = rtName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((riskCurrent == null) ? 0 : riskCurrent.hashCode());
		result = prime * result + ((riskDep == null) ? 0 : riskDep.hashCode());
		result = prime * result + ((riskId == null) ? 0 : riskId.hashCode());
		result = prime * result
				+ ((riskName == null) ? 0 : riskName.hashCode());
		result = prime * result
				+ ((riskQueue == null) ? 0 : riskQueue.hashCode());
		result = prime * result
				+ ((riskRemark == null) ? 0 : riskRemark.hashCode());
		result = prime * result
				+ ((riskType == null) ? 0 : riskType.hashCode());
		result = prime * result + ((rtName == null) ? 0 : rtName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RiskQueryView other = (RiskQueryView) obj;
		if (riskCurrent == null) {
			if (other.riskCurrent != null)
				return false;
		} else if (!riskCurrent.equals(other.riskCurrent))
			return false;
		if (riskDep == null) {
			if (other.riskDep != null)
				return false;
		} else if (!riskDep.equals(other.riskDep))
			return false;
		if (riskId == null) {
			if (other.riskId != null)
				return false;
		} else if (!riskId.equals(other.riskId))
			return false;
		if (riskName == null) {
			if (other.riskName != null)
				return false;
		} else if (!riskName.equals(other.riskName))
			return false;
		if (riskQueue == null) {
			if (other.riskQueue != null)
				return false;
		} else if (!riskQueue.equals(other.riskQueue))
			return false;
		if (riskRemark == null) {
			if (other.riskRemark != null)
				return false;
		} else if (!riskRemark.equals(other.riskRemark))
			return false;
		if (riskType == null) {
			if (other.riskType != null)
				return false;
		} else if (!riskType.equals(other.riskType))
			return false;
		if (rtName == null) {
			if (other.rtName != null)
				return false;
		} else if (!rtName.equals(other.rtName))
			return false;
		return true;
	}

}