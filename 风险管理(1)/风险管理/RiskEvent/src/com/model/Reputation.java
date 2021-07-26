package com.model;

/**
 * Reputation entity. @author MyEclipse Persistence Tools
 */

public class Reputation implements java.io.Serializable {

	// Fields

	private Integer repId;
	private String repLevel;
	private String repDetail;
	private String repSuperior;
	private String repPartner;
	private String repPublic;

	// Constructors

	/** default constructor */
	public Reputation() {
	}

	/** full constructor */
	public Reputation(Integer repId, String repLevel, String repDetail,
			String repSuperior, String repPartner, String repPublic) {
		this.repId = repId;
		this.repLevel = repLevel;
		this.repDetail = repDetail;
		this.repSuperior = repSuperior;
		this.repPartner = repPartner;
		this.repPublic = repPublic;
	}

	// Property accessors

	public Integer getRepId() {
		return this.repId;
	}

	public void setRepId(Integer repId) {
		this.repId = repId;
	}

	public String getRepLevel() {
		return this.repLevel;
	}

	public void setRepLevel(String repLevel) {
		this.repLevel = repLevel;
	}

	public String getRepDetail() {
		return this.repDetail;
	}

	public void setRepDetail(String repDetail) {
		this.repDetail = repDetail;
	}

	public String getRepSuperior() {
		return this.repSuperior;
	}

	public void setRepSuperior(String repSuperior) {
		this.repSuperior = repSuperior;
	}

	public String getRepPartner() {
		return this.repPartner;
	}

	public void setRepPartner(String repPartner) {
		this.repPartner = repPartner;
	}

	public String getRepPublic() {
		return this.repPublic;
	}

	public void setRepPublic(String repPublic) {
		this.repPublic = repPublic;
	}

}