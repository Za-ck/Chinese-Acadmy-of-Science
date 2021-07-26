package com.model;

/**
 * AllStaViewId entity. @author MyEclipse Persistence Tools
 */

public class AllStaViewId implements java.io.Serializable {
	private String reId;
	// Fields



	// Constructors

	/** default constructor */
	public AllStaViewId() {
	}

	/** minimal constructor */
	public AllStaViewId( String reId) {		
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
		if (!(other instanceof AllStaViewId))
			return false;
		AllStaViewId castOther = (AllStaViewId) other;

		return ( ((this.getReId() == castOther.getReId()) || (this.getReId() != null
						&& castOther.getReId() != null && this.getReId()
						.equals(castOther.getReId()))));
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result
				+ (getReId() == null ? 0 : this.getReId().hashCode());
		return result;
	}

}