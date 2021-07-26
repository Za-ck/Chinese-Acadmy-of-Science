package com.model;

import java.util.HashSet;
import java.util.Set;

/**
 * RiskType entity. @author MyEclipse Persistence Tools
 */

public class RiskType implements java.io.Serializable {

	// Fields

	private String rtId;
	private String rtName;
	private String rtRemark;

	// Constructors

	/** default constructor */
	public RiskType() {
	}

	/** minimal constructor */
	public RiskType(String rtId, String rtName) {
		this.rtId = rtId;
		this.rtName = rtName;
	}

	

	// Property accessors

	public RiskType(String rtId, String rtName, String rtRemark) {
		super();
		this.rtId = rtId;
		this.rtName = rtName;
		this.rtRemark = rtRemark;
	}

	public String getRtId() {
		return this.rtId;
	}

	public void setRtId(String rtId) {
		this.rtId = rtId;
	}

	public String getRtName() {
		return this.rtName;
	}

	public void setRtName(String rtName) {
		this.rtName = rtName;
	}

	public String getRtRemark() {
		return this.rtRemark;
	}

	public void setRtRemark(String rtRemark) {
		this.rtRemark = rtRemark;
	}

	

}