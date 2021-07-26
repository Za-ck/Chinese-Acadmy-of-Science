package com.model;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */

public class Department {

	// Fields

	/**
	 * 
	 */
	private String depId;  //部门编号
	private String depName;
	private Integer depSort;  //是否为归口部门
	private String depRemark;  //注备
	private Integer depQueue;
	private Integer depAssess;	//是否作为考核部门
	//private String depbelName;

	// Constructors

	/** default constructor */
	public Department() {
		
	}

	// Property accessors

	public String getDepId() {
		return this.depId;
	}

//	public String getDepbelName() {
//		return depbelName;
//	}
//
//	public void setDepbelName(String depbelName) {
//		this.depbelName = depbelName;
//	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Integer getDepSort() {
		return this.depSort;
	}

	public void setDepSort(Integer depSort) {
		this.depSort = depSort;
	}

	public String getDepRemark() {
		return this.depRemark;
	}

	public void setDepRemark(String depRemark) {
		this.depRemark = depRemark;
	}

	public Integer getDepQueue() {
		return depQueue;
	}

	public void setDepQueue(Integer depQueue) {
		this.depQueue = depQueue;
	}

	public Integer getDepAssess() {
		return depAssess;
	}

	public void setDepAssess(Integer depAssess) {
		this.depAssess = depAssess;
	}


}