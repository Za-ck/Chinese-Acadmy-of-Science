package com.model;

/**
 * KeyStaViewId entity. @author MyEclipse Persistence Tools
 */

public class KeyStaViewId implements java.io.Serializable {

	// Fields

	
	private String reId;
	

	// Constructors

	/** default constructor */
	public KeyStaViewId() {
	}

	/** minimal constructor */
	public KeyStaViewId(String reIndep, String depName, String riskName,
			String reId) {
		
		this.reId = reId;
	}

	/** full constructor */
	public KeyStaViewId(String reId) {		
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
		if (!(other instanceof KeyStaViewId))
			return false;
		KeyStaViewId castOther = (KeyStaViewId) other;

		return (((this.getReId() == castOther.getReId()) || (this.getReId() != null
						&& castOther.getReId() != null && this.getReId()
						.equals(castOther.getReId())))
				);
	}

	public int hashCode() {
		int result = 17;
		
		result = 37 * result
				+ (getReId() == null ? 0 : this.getReId().hashCode());
		return result;
	}

}