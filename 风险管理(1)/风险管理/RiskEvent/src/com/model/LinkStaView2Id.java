package com.model;

/**
 * LinkStaView2Id entity. @author MyEclipse Persistence Tools
 */

public class LinkStaView2Id implements java.io.Serializable {

	// Fields

	private String fileId;
	private String fileName;
	private String reRiskId;
	private String riskName;
	private String year;
	private Integer quarter;
	private Double money;
	private Integer eventnum;
	private Integer indepnum;

	// Constructors

	/** default constructor */
	public LinkStaView2Id() {
	}

	/** minimal constructor */
	public LinkStaView2Id(String fileName, String reRiskId, String riskName) {
		this.fileName = fileName;
		this.reRiskId = reRiskId;
		this.riskName = riskName;
	}

	/** full constructor */
	public LinkStaView2Id(String fileId, String fileName, String reRiskId,
			String riskName, String year, Integer quarter, Double money,
			Integer eventnum, Integer indepnum) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.reRiskId = reRiskId;
		this.riskName = riskName;
		this.year = year;
		this.quarter = quarter;
		this.money = money;
		this.eventnum = eventnum;
		this.indepnum = indepnum;
	}

	// Property accessors

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getReRiskId() {
		return this.reRiskId;
	}

	public void setReRiskId(String reRiskId) {
		this.reRiskId = reRiskId;
	}

	public String getRiskName() {
		return this.riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getQuarter() {
		return this.quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getEventnum() {
		return this.eventnum;
	}

	public void setEventnum(Integer eventnum) {
		this.eventnum = eventnum;
	}

	public Integer getIndepnum() {
		return this.indepnum;
	}

	public void setIndepnum(Integer indepnum) {
		this.indepnum = indepnum;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LinkStaView2Id))
			return false;
		LinkStaView2Id castOther = (LinkStaView2Id) other;

		return ((this.getFileId() == castOther.getFileId()) || (this
				.getFileId() != null
				&& castOther.getFileId() != null && this.getFileId().equals(
				castOther.getFileId())))
				&& ((this.getFileName() == castOther.getFileName()) || (this
						.getFileName() != null
						&& castOther.getFileName() != null && this
						.getFileName().equals(castOther.getFileName())))
				&& ((this.getReRiskId() == castOther.getReRiskId()) || (this
						.getReRiskId() != null
						&& castOther.getReRiskId() != null && this
						.getReRiskId().equals(castOther.getReRiskId())))
				&& ((this.getRiskName() == castOther.getRiskName()) || (this
						.getRiskName() != null
						&& castOther.getRiskName() != null && this
						.getRiskName().equals(castOther.getRiskName())))
				&& ((this.getYear() == castOther.getYear()) || (this.getYear() != null
						&& castOther.getYear() != null && this.getYear()
						.equals(castOther.getYear())))
				&& ((this.getQuarter() == castOther.getQuarter()) || (this
						.getQuarter() != null
						&& castOther.getQuarter() != null && this.getQuarter()
						.equals(castOther.getQuarter())))
				&& ((this.getMoney() == castOther.getMoney()) || (this
						.getMoney() != null
						&& castOther.getMoney() != null && this.getMoney()
						.equals(castOther.getMoney())))
				&& ((this.getEventnum() == castOther.getEventnum()) || (this
						.getEventnum() != null
						&& castOther.getEventnum() != null && this
						.getEventnum().equals(castOther.getEventnum())))
				&& ((this.getIndepnum() == castOther.getIndepnum()) || (this
						.getIndepnum() != null
						&& castOther.getIndepnum() != null && this
						.getIndepnum().equals(castOther.getIndepnum())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFileId() == null ? 0 : this.getFileId().hashCode());
		result = 37 * result
				+ (getFileName() == null ? 0 : this.getFileName().hashCode());
		result = 37 * result
				+ (getReRiskId() == null ? 0 : this.getReRiskId().hashCode());
		result = 37 * result
				+ (getRiskName() == null ? 0 : this.getRiskName().hashCode());
		result = 37 * result
				+ (getYear() == null ? 0 : this.getYear().hashCode());
		result = 37 * result
				+ (getQuarter() == null ? 0 : this.getQuarter().hashCode());
		result = 37 * result
				+ (getMoney() == null ? 0 : this.getMoney().hashCode());
		result = 37 * result
				+ (getEventnum() == null ? 0 : this.getEventnum().hashCode());
		result = 37 * result
				+ (getIndepnum() == null ? 0 : this.getIndepnum().hashCode());
		return result;
	}

}