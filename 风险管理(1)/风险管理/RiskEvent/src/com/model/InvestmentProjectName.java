package com.model;

/**
 * Law entity. @author MyEclipse Persistence Tools
 */

public class InvestmentProjectName implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String type;

	// Constructors

	/** default constructor */
	public InvestmentProjectName() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



}