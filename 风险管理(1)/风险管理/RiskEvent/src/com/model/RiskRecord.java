package com.model;


/**
 * RiskRecord entity. @author MyEclipse Persistence Tools
 */

public class RiskRecord {

	// Fields

	private Integer rrId;
	private String rrVerifier;
	private String rrReId;
	private String rrTime;
	private String rrBehaviour;
	private String rrView;
	private String rrStatus;

	// Constructors

	/** default constructor */
	public RiskRecord() {
		
	}

	// Property accessors

	public Integer getRrId() {
		return this.rrId;
	}

	public void setRrId(Integer rrId) {
		this.rrId = rrId;
	}

	public String getRrVerifier() {
		return rrVerifier;
	}

	public void setRrVerifier(String rrVerifier) {
		this.rrVerifier = rrVerifier;
	}

	public String getRrReId() {
		return rrReId;
	}


	public void setRrReId(String rrReId) {
		this.rrReId = rrReId;
	}


	public String getRrTime() {
		return rrTime;
	}

	public void setRrTime(String rrTime) {
		this.rrTime = rrTime;
	}

	public String getRrBehaviour() {
		return this.rrBehaviour;
	}

	public void setRrBehaviour(String rrBehaviour) {
		this.rrBehaviour = rrBehaviour;
	}

	public String getRrView() {
		return this.rrView;
	}

	public void setRrView(String rrView) {
		this.rrView = rrView;
	}

	public String getRrStatus() {
		return this.rrStatus;
	}

	public void setRrStatus(String rrStatus) {
		this.rrStatus = rrStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((rrBehaviour == null) ? 0 : rrBehaviour.hashCode());
		result = prime * result + ((rrId == null) ? 0 : rrId.hashCode());
		result = prime * result + ((rrReId == null) ? 0 : rrReId.hashCode());
		result = prime * result
				+ ((rrStatus == null) ? 0 : rrStatus.hashCode());
		result = prime * result + ((rrTime == null) ? 0 : rrTime.hashCode());
		result = prime * result
				+ ((rrVerifier == null) ? 0 : rrVerifier.hashCode());
		result = prime * result + ((rrView == null) ? 0 : rrView.hashCode());
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
		RiskRecord other = (RiskRecord) obj;
		if (rrBehaviour == null) {
			if (other.rrBehaviour != null)
				return false;
		} else if (!rrBehaviour.equals(other.rrBehaviour))
			return false;
		if (rrId == null) {
			if (other.rrId != null)
				return false;
		} else if (!rrId.equals(other.rrId))
			return false;
		if (rrReId == null) {
			if (other.rrReId != null)
				return false;
		} else if (!rrReId.equals(other.rrReId))
			return false;
		if (rrStatus == null) {
			if (other.rrStatus != null)
				return false;
		} else if (!rrStatus.equals(other.rrStatus))
			return false;
		if (rrTime == null) {
			if (other.rrTime != null)
				return false;
		} else if (!rrTime.equals(other.rrTime))
			return false;
		if (rrVerifier == null) {
			if (other.rrVerifier != null)
				return false;
		} else if (!rrVerifier.equals(other.rrVerifier))
			return false;
		if (rrView == null) {
			if (other.rrView != null)
				return false;
		} else if (!rrView.equals(other.rrView))
			return false;
		return true;
	}
}