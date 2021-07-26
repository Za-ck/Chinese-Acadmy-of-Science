package com.model;

/**
 * Client entity. @author MyEclipse Persistence Tools
 */

public class Client implements java.io.Serializable {

	// Fields

	private Integer cliId;
	private String cliLevel;
	private String cliDetail;

	// Constructors

	/** default constructor */
	public Client() {
	}

	/** full constructor */
	public Client(Integer cliId, String cliLevel, String cliDetail) {
		this.cliId = cliId;
		this.cliLevel = cliLevel;
		this.cliDetail = cliDetail;
	}

	// Property accessors

	public Integer getCliId() {
		return this.cliId;
	}

	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public String getCliLevel() {
		return this.cliLevel;
	}

	public void setCliLevel(String cliLevel) {
		this.cliLevel = cliLevel;
	}

	public String getCliDetail() {
		return this.cliDetail;
	}

	public void setCliDetail(String cliDetail) {
		this.cliDetail = cliDetail;
	}

}