package com.model;



/**
 * SystemRole entity. @author MyEclipse Persistence Tools
 */

public class SystemRole implements java.io.Serializable {

	// Fields

	private Integer srId;
	private String srName;
	private String srreid;
	// Constructors

    
	/** default constructor */
	public SystemRole() {
	}


	/** minimal constructor */
	public SystemRole(Integer srId) {
		this.srId = srId;
	}

	/** full constructor */
	public SystemRole(Integer srId, String srName) {
		this.srId = srId;
		this.srName = srName;
	}

	// Property accessors

	public Integer getSrId() {
		return this.srId;
	}

	public void setSrId(Integer srId) {
		this.srId = srId;
	}

	public String getSrName() {
		return this.srName;
	}

	public void setSrName(String srName) {
		this.srName = srName;
	}


	public String getSrreid() {
		return srreid;
	}


	public void setSrreid(String srreid) {
		this.srreid = srreid;
	}

	
}