package com.model;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */

public class Employee implements java.io.Serializable {

	// Fields

	private Integer empId;
	private String empLevel;
	private String empDetail;

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** full constructor */
	public Employee(Integer empId, String empLevel, String empDetail) {
		this.empId = empId;
		this.empLevel = empLevel;
		this.empDetail = empDetail;
	}

	// Property accessors

	public Integer getEmpId() {
		return this.empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpLevel() {
		return this.empLevel;
	}

	public void setEmpLevel(String empLevel) {
		this.empLevel = empLevel;
	}

	public String getEmpDetail() {
		return this.empDetail;
	}

	public void setEmpDetail(String empDetail) {
		this.empDetail = empDetail;
	}

}