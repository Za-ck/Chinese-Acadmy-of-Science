package com.entity;

/**
 * GeneralAnalysisViewDep entity. @author MyEclipse Persistence Tools
 */

public class RiAnalysis implements java.io.Serializable {

	// Fields

	private String reId;
	private String depNum;
	private String depName;
	private String riNum;
	private String riskName;
	private String rtName;
	private String reEventname;
	private String riKpi;
	private String riskId;
	private String riEventDate;
	private String riBusarea;
	private String riSource;

	private String riProdegree;
	private String riFindegree;
	private String riFamedegree;
	private String riLawdegree;
	private String riClidegree;
	private String riEmpdegree;
	private String riOpedegree;
	private String riSafedegree;
	private String riAlldegree;

	private String riFinvalue;
	private String riFramevalue;
	private String riLawvalue;
	private String riClivalue;
	private String riEmpvalue;
	private String riOpevalue;
	private String riSafevalue;
	private String riAllvalue;

	// Constructors

	/** default constructor */
	public RiAnalysis() {
	}
	

	public String getReId() {
		return reId;
	}


	public void setReId(String reId) {
		this.reId = reId;
	}


	public String getDepNum() {
		return depNum;
	}

	public void setDepNum(String depNum) {
		this.depNum = depNum;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getRiNum() {
		return riNum;
	}

	public void setRiNum(String riNum) {
		this.riNum = riNum;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getRtName() {
		return rtName;
	}

	public void setRtName(String rtName) {
		this.rtName = rtName;
	}

	public String getReEventname() {
		return reEventname;
	}

	public void setReEventname(String reEventname) {
		this.reEventname = reEventname;
	}

	public String getRiKpi() {
		return riKpi;
	}

	public void setRiKpi(String riKpi) {
		this.riKpi = riKpi;
	}


	public String getRiProdegree() {
		return riProdegree;
	}

	public void setRiProdegree(String riProdegree) {
		this.riProdegree = riProdegree;
	}

	public String getRiFindegree() {
		return riFindegree;
	}

	public void setRiFindegree(String riFindegree) {
		this.riFindegree = riFindegree;
	}

	public String getRiFamedegree() {
		return riFamedegree;
	}

	public void setRiFamedegree(String riFamedegree) {
		this.riFamedegree = riFamedegree;
	}

	public String getRiLawdegree() {
		return riLawdegree;
	}

	public void setRiLawdegree(String riLawdegree) {
		this.riLawdegree = riLawdegree;
	}

	public String getRiClidegree() {
		return riClidegree;
	}

	public void setRiClidegree(String riClidegree) {
		this.riClidegree = riClidegree;
	}

	public String getRiEmpdegree() {
		return riEmpdegree;
	}

	public void setRiEmpdegree(String riEmpdegree) {
		this.riEmpdegree = riEmpdegree;
	}

	public String getRiOpedegree() {
		return riOpedegree;
	}

	public void setRiOpedegree(String riOpedegree) {
		this.riOpedegree = riOpedegree;
	}

	public String getRiSafedegree() {
		return riSafedegree;
	}

	public void setRiSafedegree(String riSafedegree) {
		this.riSafedegree = riSafedegree;
	}

	public String getRiAlldegree() {
		return riAlldegree;
	}

	public void setRiAlldegree(String riAlldegree) {
		this.riAlldegree = riAlldegree;
	}

	public String getRiFinvalue() {
		return riFinvalue;
	}

	public void setRiFinvalue(String riFinvalue) {
		this.riFinvalue = riFinvalue;
	}

	public String getRiFramevalue() {
		return riFramevalue;
	}

	public void setRiFramevalue(String riFramevalue) {
		this.riFramevalue = riFramevalue;
	}

	public String getRiLawvalue() {
		return riLawvalue;
	}

	public void setRiLawvalue(String riLawvalue) {
		this.riLawvalue = riLawvalue;
	}

	public String getRiClivalue() {
		return riClivalue;
	}

	public void setRiClivalue(String riClivalue) {
		this.riClivalue = riClivalue;
	}

	public String getRiEmpvalue() {
		return riEmpvalue;
	}

	public void setRiEmpvalue(String riEmpvalue) {
		this.riEmpvalue = riEmpvalue;
	}

	public String getRiOpevalue() {
		return riOpevalue;
	}

	public void setRiOpevalue(String riOpevalue) {
		this.riOpevalue = riOpevalue;
	}

	public String getRiSafevalue() {
		return riSafevalue;
	}

	public void setRiSafevalue(String riSafevalue) {
		this.riSafevalue = riSafevalue;
	}

	public String getRiAllvalue() {
		return riAllvalue;
	}

	public void setRiAllvalue(String riAllvalue) {
		this.riAllvalue = riAllvalue;
	}
	
	

	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getRiEventDate() {
		return riEventDate;
	}

	public void setRiEventDate(String riEventDate) {
		this.riEventDate = riEventDate;
	}



	public String getRiBusarea() {
		return riBusarea;
	}


	public void setRiBusarea(String riBusarea) {
		this.riBusarea = riBusarea;
	}


	public String getRiSource() {
		return riSource;
	}


	public void setRiSource(String riSource) {
		this.riSource = riSource;
	}


	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RiAnalysis))
			return false;
		RiAnalysis castOther = (RiAnalysis) other;

		return ((this.getReEventname() == castOther.getReEventname()) || (this
				.getReEventname() != null
				&& castOther.getReEventname() != null && this.getReEventname()
				.equals(castOther.getReEventname())))
				&& ((this.getDepName() == castOther.getDepName()) || (this
						.getDepName() != null
						&& castOther.getDepName() != null && this.getDepName()
						.equals(castOther.getDepName())))
				&& ((this.getRiskName() == castOther.getRiskName()) || (this
						.getRiskName() != null
						&& castOther.getRiskName() != null && this
						.getRiskName().equals(castOther.getRiskName())))
				&& ((this.getRtName() == castOther.getRtName()) || (this
						.getRtName() != null
						&& castOther.getRtName() != null && this.getRtName()
						.equals(castOther.getRtName())))
				&& ((this.getRiKpi() == castOther.getRiKpi()) || (this
						.getRiKpi() != null
						&& castOther.getRiKpi() != null && this.getRiKpi()
						.equals(castOther.getRiKpi())))
				&& ((this.getRiAllvalue() == castOther.getRiAllvalue()) || (this
						.getRiAllvalue() != null
						&& castOther.getRiAllvalue() != null && this
						.getRiAllvalue().equals(castOther.getRiAllvalue())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getReEventname() == null ? 0 : this.getReEventname()
						.hashCode());
		result = 37 * result
				+ (getDepName() == null ? 0 : this.getDepName().hashCode());
		result = 37 * result
				+ (getRiskName() == null ? 0 : this.getRiskName().hashCode());
		result = 37 * result
				+ (getRtName() == null ? 0 : this.getRtName().hashCode());
		result = 37 * result
				+ (getRiKpi() == null ? 0 : this.getRiKpi().hashCode());
		result = 37
				* result
				+ (getRiAllvalue() == null ? 0 : this.getRiAllvalue()
						.hashCode());
		return result;
	}

}