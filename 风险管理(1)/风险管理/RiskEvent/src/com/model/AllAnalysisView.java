package com.model;

/**
 * AllAnalysisView entity. @author MyEclipse Persistence Tools
 */

public class AllAnalysisView implements java.io.Serializable {

	// Fields

	private String ReId;
	private String year;
	private String reDate;	
	private String reModifydate;
	private String reIndep;
	private String reEventname;
	private String riskId;
	private String reType;
	private String reCreator;
	private String riKpi;
	private Integer riProdegree;
	private String riProbability;
	private String riBusarea;
	private String riFinance;
	private Integer riSafedegree;
	private Integer riOpedegree;
	private Integer riEmpdegree;
	private Integer riClidegree;
	private Integer riLawdegree;
	private Integer riFamedegree;
	private Integer riFindegree;
	private String riSource;
	private String riskName;
	private String rtName;
	private String depName;
	private Integer depQueue;
	private Integer rtQueue;
	private Integer riskQueue;
	private Float riAlldegree;
	private Integer riFinvalue;
	private Integer riFramevalue;
	private Integer riLawvalue;
	private Integer riClivalue;
	private Integer riEmpvalue;
	private Integer riOpevalue;
	private Integer riSafevalue;
	private Integer riAllvalue;

	private String riReply;
	private String riplandate;
	private String riplanres;
	private String ritaketime;
	
	private String rireplyman;
	private int riSign;
	private String rtId;
	
	// Constructors

	/** default constructor */
	public AllAnalysisView() {
	}


	// Property accessors

	public String getReModifydate() {
		return reModifydate;
	}


	public void setReModifydate(String reModifydate) {
		this.reModifydate = reModifydate;
	}

	public String getReId() {
		return this.ReId;
	}

	public int getRiSign() {
		return riSign;
	}


	public void setRiSign(int riSign) {
		this.riSign = riSign;
	}


	public void setReId(String reId) {
		this.ReId = reId;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public String getReDate() {
		return reDate;
	}

	public void setReDate(String reDate) {
		this.reDate = reDate;
	}


	public String getReIndep() {
		return this.reIndep;
	}

	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}

	public String getReEventname() {
		return this.reEventname;
	}

	public void setReEventname(String reEventname) {
		this.reEventname = reEventname;
	}

	public String getRiskId() {
		return this.riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getReType() {
		return this.reType;
	}

	public void setReType(String reType) {
		this.reType = reType;
	}


	public String getReCreator() {
		return reCreator;
	}

	public void setReCreator(String reCreator) {
		this.reCreator = reCreator;
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

	public String getRiFinance() {
		return this.riFinance;
	}

	public void setRiFinance(String riFinance) {
		this.riFinance = riFinance;
	}

	public Integer getRiSafedegree() {
		return this.riSafedegree;
	}

	public void setRiSafedegree(Integer riSafedegree) {
		this.riSafedegree = riSafedegree;
	}

	public Integer getRiOpedegree() {
		return this.riOpedegree;
	}

	public void setRiOpedegree(Integer riOpedegree) {
		this.riOpedegree = riOpedegree;
	}

	public Integer getRiEmpdegree() {
		return this.riEmpdegree;
	}

	public void setRiEmpdegree(Integer riEmpdegree) {
		this.riEmpdegree = riEmpdegree;
	}

	public Integer getRiClidegree() {
		return this.riClidegree;
	}

	public void setRiClidegree(Integer riClidegree) {
		this.riClidegree = riClidegree;
	}

	public Integer getRiLawdegree() {
		return this.riLawdegree;
	}

	public void setRiLawdegree(Integer riLawdegree) {
		this.riLawdegree = riLawdegree;
	}

	public Integer getRiFamedegree() {
		return this.riFamedegree;
	}

	public void setRiFamedegree(Integer riFamedegree) {
		this.riFamedegree = riFamedegree;
	}

	public Integer getRiFindegree() {
		return this.riFindegree;
	}

	public void setRiFindegree(Integer riFindegree) {
		this.riFindegree = riFindegree;
	}

	public String getRiSource() {
		return this.riSource;
	}

	public void setRiSource(String riSource) {
		this.riSource = riSource;
	}

	public String getRiskName() {
		return this.riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getRtName() {
		return this.rtName;
	}

	public void setRtName(String rtName) {
		this.rtName = rtName;
	}

	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Integer getDepQueue() {
		return this.depQueue;
	}

	public void setDepQueue(Integer depQueue) {
		this.depQueue = depQueue;
	}

	public Integer getRtQueue() {
		return this.rtQueue;
	}

	public void setRtQueue(Integer rtQueue) {
		this.rtQueue = rtQueue;
	}

	public Integer getRiskQueue() {
		return this.riskQueue;
	}

	public void setRiskQueue(Integer riskQueue) {
		this.riskQueue = riskQueue;
	}

	public Float getRiAlldegree() {
		return riAlldegree;
	}

	public void setRiAlldegree(Float riAlldegree) {
		this.riAlldegree = riAlldegree;
	}

	public Integer getRiFinvalue() {
		return this.riFinvalue;
	}

	public void setRiFinvalue(Integer riFinvalue) {
		this.riFinvalue = riFinvalue;
	}

	public Integer getRiFramevalue() {
		return this.riFramevalue;
	}

	public void setRiFramevalue(Integer riFramevalue) {
		this.riFramevalue = riFramevalue;
	}

	public Integer getRiLawvalue() {
		return this.riLawvalue;
	}

	public void setRiLawvalue(Integer riLawvalue) {
		this.riLawvalue = riLawvalue;
	}

	public Integer getRiClivalue() {
		return this.riClivalue;
	}

	public void setRiClivalue(Integer riClivalue) {
		this.riClivalue = riClivalue;
	}

	public Integer getRiEmpvalue() {
		return this.riEmpvalue;
	}

	public void setRiEmpvalue(Integer riEmpvalue) {
		this.riEmpvalue = riEmpvalue;
	}

	public Integer getRiOpevalue() {
		return this.riOpevalue;
	}

	public void setRiOpevalue(Integer riOpevalue) {
		this.riOpevalue = riOpevalue;
	}

	public Integer getRiSafevalue() {
		return this.riSafevalue;
	}

	public void setRiSafevalue(Integer riSafevalue) {
		this.riSafevalue = riSafevalue;
	}

	public Integer getRiAllvalue() {
		return this.riAllvalue;
	}

	public void setRiAllvalue(Integer riAllvalue) {
		this.riAllvalue = riAllvalue;
	}
	
	

	public String getRiReply() {
		return riReply;
	}

	public void setRiReply(String riReply) {
		this.riReply = riReply;
	}

	public String getRiplandate() {
		return riplandate;
	}

	public void setRiplandate(String riplandate) {
		this.riplandate = riplandate;
	}

	public String getRiplanres() {
		return riplanres;
	}

	public void setRiplanres(String riplanres) {
		this.riplanres = riplanres;
	}

	public String getRitaketime() {
		return ritaketime;
	}

	public void setRitaketime(String ritaketime) {
		this.ritaketime = ritaketime;
	}

	public String getRireplyman() {
		return rireplyman;
	}

	public void setRireplyman(String rireplyman) {
		this.rireplyman = rireplyman;
	}

	

	public String getRtId() {
		return rtId;
	}

	public void setRtId(String rtId) {
		this.rtId = rtId;
	}

}