package com.model;

/**
 * KeyStaView entity. @author MyEclipse Persistence Tools
 */

public class KeyStaView implements java.io.Serializable {

	// Fields

	private KeyStaViewId id;
	private String reIndep;
	private String depName;
	private String riskId;
	private String riskName;
	private String year;
	private String riKpi;
	private String riSource;
	private String riBusarea;
	private Integer riProdegree;
	private Integer riFindegree;
	private Integer riFamedegree;
	private Integer riLawdegree;
	private Integer riClidegree;
	private Integer riEmpdegree;
	private Integer riOpedegree;
	private Integer riSafedegree;
	private Integer depQueue;
	private Integer riskQueue;

	// Constructors

	/** default constructor */
	public KeyStaView() {
	}

	/** full constructor */
	public KeyStaView(KeyStaViewId id) {
		this.id = id;
	}

	// Property accessors

	public KeyStaViewId getId() {
		return this.id;
	}

	public void setId(KeyStaViewId id) {
		this.id = id;
	}

	public String getReIndep() {
		return reIndep;
	}

	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRiKpi() {
		return riKpi;
	}

	public void setRiKpi(String riKpi) {
		this.riKpi = riKpi;
	}

	public String getRiSource() {
		return riSource;
	}

	public void setRiSource(String riSource) {
		this.riSource = riSource;
	}

	public String getRiBusarea() {
		return riBusarea;
	}

	public void setRiBusarea(String riBusarea) {
		this.riBusarea = riBusarea;
	}

	public Integer getRiProdegree() {
		return riProdegree;
	}

	public void setRiProdegree(Integer riProdegree) {
		this.riProdegree = riProdegree;
	}

	public Integer getRiFindegree() {
		return riFindegree;
	}

	public void setRiFindegree(Integer riFindegree) {
		this.riFindegree = riFindegree;
	}

	public Integer getRiFamedegree() {
		return riFamedegree;
	}

	public void setRiFamedegree(Integer riFamedegree) {
		this.riFamedegree = riFamedegree;
	}

	public Integer getRiLawdegree() {
		return riLawdegree;
	}

	public void setRiLawdegree(Integer riLawdegree) {
		this.riLawdegree = riLawdegree;
	}

	public Integer getRiClidegree() {
		return riClidegree;
	}

	public void setRiClidegree(Integer riClidegree) {
		this.riClidegree = riClidegree;
	}

	public Integer getRiEmpdegree() {
		return riEmpdegree;
	}

	public void setRiEmpdegree(Integer riEmpdegree) {
		this.riEmpdegree = riEmpdegree;
	}

	public Integer getRiOpedegree() {
		return riOpedegree;
	}

	public void setRiOpedegree(Integer riOpedegree) {
		this.riOpedegree = riOpedegree;
	}

	public Integer getRiSafedegree() {
		return riSafedegree;
	}

	public void setRiSafedegree(Integer riSafedegree) {
		this.riSafedegree = riSafedegree;
	}

	public Integer getDepQueue() {
		return depQueue;
	}

	public void setDepQueue(Integer depQueue) {
		this.depQueue = depQueue;
	}

	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public Integer getRiskQueue() {
		return riskQueue;
	}

	public void setRiskQueue(Integer riskQueue) {
		this.riskQueue = riskQueue;
	}

}