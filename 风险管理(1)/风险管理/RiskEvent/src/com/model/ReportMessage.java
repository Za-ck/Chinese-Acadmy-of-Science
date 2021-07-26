package com.model;

/**
 * ReportMessage entity. @author MyEclipse Persistence Tools
 */

public class ReportMessage {

	// Fields

	private String rmId;
	private String rmFormId;
	private String rmFormName;
	private String rmCategoryId;
	private String rmCategoryName;
	private String rmDetail;
	private Integer rmState;
	private String rmDate;
	private String rmActionname;
	private String rmActionurl;
	private String rmUserId;
	private String rmUsername;

	// Constructors

	/** default constructor */
	public ReportMessage() {
	}

	// Property accessors
	
	public String getRmFormId() {
		return rmFormId;
	}

	public String getRmId() {
		return rmId;
	}

	public void setRmId(String rmId) {
		this.rmId = rmId;
	}

	public void setRmFormId(String rmFormId) {
		this.rmFormId = rmFormId;
	}

	public String getRmFormName() {
		return rmFormName;
	}

	public void setRmFormName(String rmFormName) {
		this.rmFormName = rmFormName;
	}

	public String getRmCategoryId() {
		return rmCategoryId;
	}

	public void setRmCategoryId(String rmCategoryId) {
		this.rmCategoryId = rmCategoryId;
	}

	public String getRmCategoryName() {
		return rmCategoryName;
	}

	public void setRmCategoryName(String rmCategoryName) {
		this.rmCategoryName = rmCategoryName;
	}

	public String getRmDetail() {
		return this.rmDetail;
	}

	public void setRmDetail(String rmDetail) {
		this.rmDetail = rmDetail;
	}

	public Integer getRmState() {
		return this.rmState;
	}

	public void setRmState(Integer rmState) {
		this.rmState = rmState;
	}

	public String getRmDate() {
		return this.rmDate;
	}

	public void setRmDate(String rmDate) {
		this.rmDate = rmDate;
	}

	public String getRmActionname() {
		return this.rmActionname;
	}

	public void setRmActionname(String rmActionname) {
		this.rmActionname = rmActionname;
	}

	public String getRmActionurl() {
		return this.rmActionurl;
	}

	public void setRmActionurl(String rmActionurl) {
		this.rmActionurl = rmActionurl;
	}

	public String getRmUserId() {
		return rmUserId;
	}

	public void setRmUserId(String rmUserId) {
		this.rmUserId = rmUserId;
	}

	public String getRmUsername() {
		return rmUsername;
	}

	public void setRmUsername(String rmUsername) {
		this.rmUsername = rmUsername;
	}

	
}