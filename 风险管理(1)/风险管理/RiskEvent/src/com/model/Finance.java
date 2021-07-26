package com.model;

/**
 * Finance entity. @author MyEclipse Persistence Tools
 */

public class Finance implements java.io.Serializable {

	// Fields

	private Integer finId;
	private String finDetail;
	private String finAsset;
	private String finIncome;
	private String finProfit;
	private String finProperty;

	// Constructors

	/** default constructor */
	public Finance() {
	}

	/** minimal constructor */
	public Finance(Integer finId, String finDetail) {
		this.finId = finId;
		this.finDetail = finDetail;
	}

	/** full constructor */
	public Finance(Integer finId, String finDetail, String finAsset,
			String finIncome, String finProfit, String finProperty) {
		this.finId = finId;
		this.finDetail = finDetail;
		this.finAsset = finAsset;
		this.finIncome = finIncome;
		this.finProfit = finProfit;
		this.finProperty = finProperty;
	}

	// Property accessors

	public Integer getFinId() {
		return this.finId;
	}

	public void setFinId(Integer finId) {
		this.finId = finId;
	}

	public String getFinDetail() {
		return this.finDetail;
	}

	public void setFinDetail(String finDetail) {
		this.finDetail = finDetail;
	}

	public String getFinAsset() {
		return this.finAsset;
	}

	public void setFinAsset(String finAsset) {
		this.finAsset = finAsset;
	}

	public String getFinIncome() {
		return this.finIncome;
	}

	public void setFinIncome(String finIncome) {
		this.finIncome = finIncome;
	}

	public String getFinProfit() {
		return this.finProfit;
	}

	public void setFinProfit(String finProfit) {
		this.finProfit = finProfit;
	}

	public String getFinProperty() {
		return this.finProperty;
	}

	public void setFinProperty(String finProperty) {
		this.finProperty = finProperty;
	}

}