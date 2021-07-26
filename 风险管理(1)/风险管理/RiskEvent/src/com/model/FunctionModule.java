package com.model;

/**
 * FunctionModule entity. @author MyEclipse Persistence Tools
 */

public class FunctionModule implements java.io.Serializable {

	// Fields

	private Integer fmId;
	private String fmName;
	private String fmAction;
	private Integer fmCategory;
	private String fmCatename;
	private String fmRemark;
    private String state;
	// Constructors

    
	/** default constructor */
	public FunctionModule() {
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/** minimal constructor */
	public FunctionModule(Integer fmId, String fmName) {
		this.fmId = fmId;
		this.fmName = fmName;
	}

	/** full constructor */
	public FunctionModule(Integer fmId, String fmName,String fmAction, Integer fmCategory,
			String fmCatename, String fmRemark) {
		this.fmId = fmId;
		this.fmName = fmName;
		this.fmAction=fmAction;
		this.fmCategory = fmCategory;
		this.fmCatename = fmCatename;
		this.fmRemark = fmRemark;
	}

	// Property accessors

	public Integer getFmId() {
		return this.fmId;
	}

	public void setFmId(Integer fmId) {
		this.fmId = fmId;
	}

	public String getFmName() {
		return this.fmName;
	}

	public void setFmName(String fmName) {
		this.fmName = fmName;
	}

	public Integer getFmCategory() {
		return this.fmCategory;
	}

	public void setFmCategory(Integer fmCategory) {
		this.fmCategory = fmCategory;
	}

	public String getFmCatename() {
		return this.fmCatename;
	}

	public void setFmCatename(String fmCatename) {
		this.fmCatename = fmCatename;
	}

	public String getFmRemark() {
		return this.fmRemark;
	}

	public void setFmRemark(String fmRemark) {
		this.fmRemark = fmRemark;
	}

	public String getFmAction() {
		return fmAction;
	}

	public void setFmAction(String fmAction) {
		this.fmAction = fmAction;
	}

}