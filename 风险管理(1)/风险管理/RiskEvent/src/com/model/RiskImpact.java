package com.model;

/**
 * RiskImpact entity. @author MyEclipse Persistence Tools
 */

public class RiskImpact implements java.io.Serializable {

	// Fields

	private String riReId;
	private String riKpi;
	private Integer riProdegree;
	private String riProbability;
	private String riBusarea;
	private String riSource;
	private String riFlow;
	private String riInchargedep;
	private String riFinance;
	private Double riMoney;
	private String riFame;
	private String riLaw;
	private String riClient;
	private String riEmployee;
	private String riOperation;
	private String riSafe;
	private Integer riFindegree;
	private String riFincriteria;
	private Integer riFamedegree;
	private String riFamecriteria;
	private Integer riLawdegree;
	private String riLawcriteria;
	private Integer riClidegree;
	private String riClicriteria;
	private Integer riEmpdegree;
	private String riEmpcriteria;
	private Integer riOpedegree;
	private String riOpecriteria;
	private Integer riSafedegree;
	private String riSafecriteria;
    private String sessionFactory;
	// Constructors

	/** default constructor */
	public RiskImpact() {
	}

	

	// Property accessors

	public RiskImpact(String riReId) {
		super();
		this.riReId = riReId;
	}

	
	public RiskImpact(String riReId, String riKpi, Integer riProdegree,
			String riProbability, String riBusarea, String riSource,
			String riFlow, String riInchargedep, String riFinance,
			Double riMoney, String riFame, String riLaw, String riClient,
			String riEmployee, String riOperation, String riSafe,
			Integer riFindegree, String riFincriteria, Integer riFamedegree,
			String riFamecriteria, Integer riLawdegree, String riLawcriteria,
			Integer riClidegree, String riClicriteria, Integer riEmpdegree,
			String riEmpcriteria, Integer riOpedegree, String riOpecriteria,
			Integer riSafedegree, String riSafecriteria, String sessionFactory) {
		this.riReId = riReId;
		this.riKpi = riKpi;
		this.riProdegree = riProdegree;
		this.riProbability = riProbability;
		this.riBusarea = riBusarea;
		this.riSource = riSource;
		this.riFlow = riFlow;
		this.riInchargedep = riInchargedep;
		this.riFinance = riFinance;
		this.riMoney = riMoney;
		this.riFame = riFame;
		this.riLaw = riLaw;
		this.riClient = riClient;
		this.riEmployee = riEmployee;
		this.riOperation = riOperation;
		this.riSafe = riSafe;
		this.riFindegree = riFindegree;
		this.riFincriteria = riFincriteria;
		this.riFamedegree = riFamedegree;
		this.riFamecriteria = riFamecriteria;
		this.riLawdegree = riLawdegree;
		this.riLawcriteria = riLawcriteria;
		this.riClidegree = riClidegree;
		this.riClicriteria = riClicriteria;
		this.riEmpdegree = riEmpdegree;
		this.riEmpcriteria = riEmpcriteria;
		this.riOpedegree = riOpedegree;
		this.riOpecriteria = riOpecriteria;
		this.riSafedegree = riSafedegree;
		this.riSafecriteria = riSafecriteria;
		this.sessionFactory = sessionFactory;
	}



	public String getRiReId() {
		return this.riReId;
	}
    
	public String getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(String sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setRiReId(String riReId) {
		this.riReId = riReId;
	}

	

	public String getRiKpi() {
		return this.riKpi;
	}

	public void setRiKpi(String riKpi) {
		this.riKpi = riKpi;
	}

	public Integer getRiProdegree() {
		return this.riProdegree;
	}

	public void setRiProdegree(Integer riProdegree) {
		this.riProdegree = riProdegree;
	}

	public String getRiProbability() {
		return this.riProbability;
	}

	public void setRiProbability(String riProbability) {
		this.riProbability = riProbability;
	}

	public String getRiBusarea() {
		return this.riBusarea;
	}

	public void setRiBusarea(String riBusarea) {
		this.riBusarea = riBusarea;
	}

	public String getRiSource() {
		return this.riSource;
	}

	public void setRiSource(String riSource) {
		this.riSource = riSource;
	}

	public String getRiFlow() {
		return this.riFlow;
	}

	public void setRiFlow(String riFlow) {
		this.riFlow = riFlow;
	}

	public String getRiInchargedep() {
		return this.riInchargedep;
	}

	public void setRiInchargedep(String riInchargedep) {
		this.riInchargedep = riInchargedep;
	}

	public String getRiFinance() {
		return this.riFinance;
	}

	public void setRiFinance(String riFinance) {
		this.riFinance = riFinance;
	}

	public Double getRiMoney() {
		return this.riMoney;
	}

	public void setRiMoney(Double riMoney) {
		this.riMoney = riMoney;
	}

	public String getRiFame() {
		return this.riFame;
	}

	public void setRiFame(String riFame) {
		this.riFame = riFame;
	}

	public String getRiLaw() {
		return this.riLaw;
	}

	public void setRiLaw(String riLaw) {
		this.riLaw = riLaw;
	}

	public String getRiClient() {
		return this.riClient;
	}

	public void setRiClient(String riClient) {
		this.riClient = riClient;
	}

	public String getRiEmployee() {
		return this.riEmployee;
	}

	public void setRiEmployee(String riEmployee) {
		this.riEmployee = riEmployee;
	}

	public String getRiOperation() {
		return this.riOperation;
	}

	public void setRiOperation(String riOperation) {
		this.riOperation = riOperation;
	}

	public String getRiSafe() {
		return this.riSafe;
	}

	public void setRiSafe(String riSafe) {
		this.riSafe = riSafe;
	}

	public Integer getRiFindegree() {
		return this.riFindegree;
	}

	public void setRiFindegree(Integer riFindegree) {
		this.riFindegree = riFindegree;
	}

	public String getRiFincriteria() {
		return this.riFincriteria;
	}

	public void setRiFincriteria(String riFincriteria) {
		this.riFincriteria = riFincriteria;
	}

	public Integer getRiFamedegree() {
		return this.riFamedegree;
	}

	public void setRiFamedegree(Integer riFamedegree) {
		this.riFamedegree = riFamedegree;
	}

	public String getRiFamecriteria() {
		return this.riFamecriteria;
	}

	public void setRiFamecriteria(String riFamecriteria) {
		this.riFamecriteria = riFamecriteria;
	}

	public Integer getRiLawdegree() {
		return this.riLawdegree;
	}

	public void setRiLawdegree(Integer riLawdegree) {
		this.riLawdegree = riLawdegree;
	}

	public String getRiLawcriteria() {
		return this.riLawcriteria;
	}

	public void setRiLawcriteria(String riLawcriteria) {
		this.riLawcriteria = riLawcriteria;
	}

	public Integer getRiClidegree() {
		return this.riClidegree;
	}

	public void setRiClidegree(Integer riClidegree) {
		this.riClidegree = riClidegree;
	}

	public String getRiClicriteria() {
		return this.riClicriteria;
	}

	public void setRiClicriteria(String riClicriteria) {
		this.riClicriteria = riClicriteria;
	}

	public Integer getRiEmpdegree() {
		return this.riEmpdegree;
	}

	public void setRiEmpdegree(Integer riEmpdegree) {
		this.riEmpdegree = riEmpdegree;
	}

	public String getRiEmpcriteria() {
		return this.riEmpcriteria;
	}

	public void setRiEmpcriteria(String riEmpcriteria) {
		this.riEmpcriteria = riEmpcriteria;
	}

	public Integer getRiOpedegree() {
		return this.riOpedegree;
	}

	public void setRiOpedegree(Integer riOpedegree) {
		this.riOpedegree = riOpedegree;
	}

	public String getRiOpecriteria() {
		return this.riOpecriteria;
	}

	public void setRiOpecriteria(String riOpecriteria) {
		this.riOpecriteria = riOpecriteria;
	}

	public Integer getRiSafedegree() {
		return this.riSafedegree;
	}

	public void setRiSafedegree(Integer riSafedegree) {
		this.riSafedegree = riSafedegree;
	}

	public String getRiSafecriteria() {
		return this.riSafecriteria;
	}

	public void setRiSafecriteria(String riSafecriteria) {
		this.riSafecriteria = riSafecriteria;
	}

}