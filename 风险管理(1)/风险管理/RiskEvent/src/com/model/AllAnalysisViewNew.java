package com.model;

/**
 * AllAnalysisView entity. @author MyEclipse Persistence Tools
 */

public class AllAnalysisViewNew implements java.io.Serializable {

	// Fields

	private String ReId;
	private String year;
	private String reDate;
	private String reModifydate;
	private String reIndep;
	private String reEventname;
	private String riskId;
	private String reType;
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
	private String fileName;
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
	private String rtId;
	
	// Constructors

	/** default constructor */
	public AllAnalysisViewNew() {
	}

	/** minimal constructor */
	public AllAnalysisViewNew(String reId, String reIndep, String reRiskId,
			String reType, String riskName, String rtName, String depName) {
		this.ReId = reId;
		this.reIndep = reIndep;
		this.riskId = reRiskId;
		this.reType = reType;
		this.riskName = riskName;
		this.rtName = rtName;
		this.depName = depName;
	}

	/** full constructor */
	public AllAnalysisViewNew(String reId, String year, String reIndep,
			String reEventname, String reRiskId, String reType, String riKpi,
			Integer riProdegree, String riProbability, String riBusarea,
			String riFinance, Integer riSafedegree, Integer riOpedegree,
			Integer riEmpdegree, Integer riClidegree, Integer riLawdegree,
			Integer riFamedegree, Integer riFindegree, String riSource,
			String riskName, String rtName, String depName, Integer depQueue,
			Integer rtQueue, Integer riskQueue, Integer riFinvalue,
			Integer riFramevalue, Integer riLawvalue, Integer riClivalue,
			Integer riEmpvalue, Integer riOpevalue, Integer riSafevalue,
			Integer riAllvalue) {
		this.ReId = reId;
		this.year = year;
		this.reIndep = reIndep;
		this.reEventname = reEventname;
		this.riskId = reRiskId;
		this.reType = reType;
		this.riKpi = riKpi;
		this.riProdegree = riProdegree;
		this.riProbability = riProbability;
		this.riBusarea = riBusarea;
		this.riFinance = riFinance;
		this.riSafedegree = riSafedegree;
		this.riOpedegree = riOpedegree;
		this.riEmpdegree = riEmpdegree;
		this.riClidegree = riClidegree;
		this.riLawdegree = riLawdegree;
		this.riFamedegree = riFamedegree;
		this.riFindegree = riFindegree;
		this.riSource = riSource;
		this.riskName = riskName;
		this.rtName = rtName;
		this.depName = depName;
		this.depQueue = depQueue;
		this.rtQueue = rtQueue;
		this.riskQueue = riskQueue;
		this.riFinvalue = riFinvalue;
		this.riFramevalue = riFramevalue;
		this.riLawvalue = riLawvalue;
		this.riClivalue = riClivalue;
		this.riEmpvalue = riEmpvalue;
		this.riOpevalue = riOpevalue;
		this.riSafevalue = riSafevalue;
		this.riAllvalue = riAllvalue;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AllAnalysisViewNew))
			return false;
		AllAnalysisViewNew castOther = (AllAnalysisViewNew) other;

		return ((this.getReId() == castOther.getReId()) || (this.getReId() != null
				&& castOther.getReId() != null && this.getReId().equals(
				castOther.getReId())))
				&& ((this.getYear() == castOther.getYear()) || (this.getYear() != null
						&& castOther.getYear() != null && this.getYear()
						.equals(castOther.getYear())))
				&& ((this.getReIndep() == castOther.getReIndep()) || (this
						.getReIndep() != null
						&& castOther.getReIndep() != null && this.getReIndep()
						.equals(castOther.getReIndep())))
				&& ((this.getReEventname() == castOther.getReEventname()) || (this
						.getReEventname() != null
						&& castOther.getReEventname() != null && this
						.getReEventname().equals(castOther.getReEventname())))
				&& ((this.getRiskId() == castOther.getRiskId()) || (this
						.getRiskId() != null
						&& castOther.getRiskId() != null && this
						.getRiskId().equals(castOther.getRiskId())))
				&& ((this.getReType() == castOther.getReType()) || (this
						.getReType() != null
						&& castOther.getReType() != null && this.getReType()
						.equals(castOther.getReType())))
				&& ((this.getRiKpi() == castOther.getRiKpi()) || (this
						.getRiKpi() != null
						&& castOther.getRiKpi() != null && this.getRiKpi()
						.equals(castOther.getRiKpi())))
				&& ((this.getRiProdegree() == castOther.getRiProdegree()) || (this
						.getRiProdegree() != null
						&& castOther.getRiProdegree() != null && this
						.getRiProdegree().equals(castOther.getRiProdegree())))
				&& ((this.getRiProbability() == castOther.getRiProbability()) || (this
						.getRiProbability() != null
						&& castOther.getRiProbability() != null && this
						.getRiProbability()
						.equals(castOther.getRiProbability())))
				&& ((this.getRiBusarea() == castOther.getRiBusarea()) || (this
						.getRiBusarea() != null
						&& castOther.getRiBusarea() != null && this
						.getRiBusarea().equals(castOther.getRiBusarea())))
				&& ((this.getRiFinance() == castOther.getRiFinance()) || (this
						.getRiFinance() != null
						&& castOther.getRiFinance() != null && this
						.getRiFinance().equals(castOther.getRiFinance())))
				&& ((this.getRiSafedegree() == castOther.getRiSafedegree()) || (this
						.getRiSafedegree() != null
						&& castOther.getRiSafedegree() != null && this
						.getRiSafedegree().equals(castOther.getRiSafedegree())))
				&& ((this.getRiOpedegree() == castOther.getRiOpedegree()) || (this
						.getRiOpedegree() != null
						&& castOther.getRiOpedegree() != null && this
						.getRiOpedegree().equals(castOther.getRiOpedegree())))
				&& ((this.getRiEmpdegree() == castOther.getRiEmpdegree()) || (this
						.getRiEmpdegree() != null
						&& castOther.getRiEmpdegree() != null && this
						.getRiEmpdegree().equals(castOther.getRiEmpdegree())))
				&& ((this.getRiClidegree() == castOther.getRiClidegree()) || (this
						.getRiClidegree() != null
						&& castOther.getRiClidegree() != null && this
						.getRiClidegree().equals(castOther.getRiClidegree())))
				&& ((this.getRiLawdegree() == castOther.getRiLawdegree()) || (this
						.getRiLawdegree() != null
						&& castOther.getRiLawdegree() != null && this
						.getRiLawdegree().equals(castOther.getRiLawdegree())))
				&& ((this.getRiFamedegree() == castOther.getRiFamedegree()) || (this
						.getRiFamedegree() != null
						&& castOther.getRiFamedegree() != null && this
						.getRiFamedegree().equals(castOther.getRiFamedegree())))
				&& ((this.getRiFindegree() == castOther.getRiFindegree()) || (this
						.getRiFindegree() != null
						&& castOther.getRiFindegree() != null && this
						.getRiFindegree().equals(castOther.getRiFindegree())))
				&& ((this.getRiSource() == castOther.getRiSource()) || (this
						.getRiSource() != null
						&& castOther.getRiSource() != null && this
						.getRiSource().equals(castOther.getRiSource())))
				&& ((this.getRiskName() == castOther.getRiskName()) || (this
						.getRiskName() != null
						&& castOther.getRiskName() != null && this
						.getRiskName().equals(castOther.getRiskName())))
				&& ((this.getRtName() == castOther.getRtName()) || (this
						.getRtName() != null
						&& castOther.getRtName() != null && this.getRtName()
						.equals(castOther.getRtName())))
				&& ((this.getDepName() == castOther.getDepName()) || (this
						.getDepName() != null
						&& castOther.getDepName() != null && this.getDepName()
						.equals(castOther.getDepName())))
				&& ((this.getDepQueue() == castOther.getDepQueue()) || (this
						.getDepQueue() != null
						&& castOther.getDepQueue() != null && this
						.getDepQueue().equals(castOther.getDepQueue())))
				&& ((this.getRtQueue() == castOther.getRtQueue()) || (this
						.getRtQueue() != null
						&& castOther.getRtQueue() != null && this.getRtQueue()
						.equals(castOther.getRtQueue())))
				&& ((this.getRiskQueue() == castOther.getRiskQueue()) || (this
						.getRiskQueue() != null
						&& castOther.getRiskQueue() != null && this
						.getRiskQueue().equals(castOther.getRiskQueue())))
				&& ((this.getRiFinvalue() == castOther.getRiFinvalue()) || (this
						.getRiFinvalue() != null
						&& castOther.getRiFinvalue() != null && this
						.getRiFinvalue().equals(castOther.getRiFinvalue())))
				&& ((this.getRiFramevalue() == castOther.getRiFramevalue()) || (this
						.getRiFramevalue() != null
						&& castOther.getRiFramevalue() != null && this
						.getRiFramevalue().equals(castOther.getRiFramevalue())))
				&& ((this.getRiLawvalue() == castOther.getRiLawvalue()) || (this
						.getRiLawvalue() != null
						&& castOther.getRiLawvalue() != null && this
						.getRiLawvalue().equals(castOther.getRiLawvalue())))
				&& ((this.getRiClivalue() == castOther.getRiClivalue()) || (this
						.getRiClivalue() != null
						&& castOther.getRiClivalue() != null && this
						.getRiClivalue().equals(castOther.getRiClivalue())))
				&& ((this.getRiEmpvalue() == castOther.getRiEmpvalue()) || (this
						.getRiEmpvalue() != null
						&& castOther.getRiEmpvalue() != null && this
						.getRiEmpvalue().equals(castOther.getRiEmpvalue())))
				&& ((this.getRiOpevalue() == castOther.getRiOpevalue()) || (this
						.getRiOpevalue() != null
						&& castOther.getRiOpevalue() != null && this
						.getRiOpevalue().equals(castOther.getRiOpevalue())))
				&& ((this.getRiSafevalue() == castOther.getRiSafevalue()) || (this
						.getRiSafevalue() != null
						&& castOther.getRiSafevalue() != null && this
						.getRiSafevalue().equals(castOther.getRiSafevalue())))
				&& ((this.getRiAllvalue() == castOther.getRiAllvalue()) || (this
						.getRiAllvalue() != null
						&& castOther.getRiAllvalue() != null && this
						.getRiAllvalue().equals(castOther.getRiAllvalue())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getReId() == null ? 0 : this.getReId().hashCode());
		result = 37 * result
				+ (getYear() == null ? 0 : this.getYear().hashCode());
		result = 37 * result
				+ (getReIndep() == null ? 0 : this.getReIndep().hashCode());
		result = 37
				* result
				+ (getReEventname() == null ? 0 : this.getReEventname()
						.hashCode());
		result = 37 * result
				+ (getRiskId() == null ? 0 : this.getRiskId().hashCode());
		result = 37 * result
				+ (getReType() == null ? 0 : this.getReType().hashCode());
		result = 37 * result
				+ (getRiKpi() == null ? 0 : this.getRiKpi().hashCode());
		result = 37
				* result
				+ (getRiProdegree() == null ? 0 : this.getRiProdegree()
						.hashCode());
		result = 37
				* result
				+ (getRiProbability() == null ? 0 : this.getRiProbability()
						.hashCode());
		result = 37 * result
				+ (getRiBusarea() == null ? 0 : this.getRiBusarea().hashCode());
		result = 37 * result
				+ (getRiFinance() == null ? 0 : this.getRiFinance().hashCode());
		result = 37
				* result
				+ (getRiSafedegree() == null ? 0 : this.getRiSafedegree()
						.hashCode());
		result = 37
				* result
				+ (getRiOpedegree() == null ? 0 : this.getRiOpedegree()
						.hashCode());
		result = 37
				* result
				+ (getRiEmpdegree() == null ? 0 : this.getRiEmpdegree()
						.hashCode());
		result = 37
				* result
				+ (getRiClidegree() == null ? 0 : this.getRiClidegree()
						.hashCode());
		result = 37
				* result
				+ (getRiLawdegree() == null ? 0 : this.getRiLawdegree()
						.hashCode());
		result = 37
				* result
				+ (getRiFamedegree() == null ? 0 : this.getRiFamedegree()
						.hashCode());
		result = 37
				* result
				+ (getRiFindegree() == null ? 0 : this.getRiFindegree()
						.hashCode());
		result = 37 * result
				+ (getRiSource() == null ? 0 : this.getRiSource().hashCode());
		result = 37 * result
				+ (getRiskName() == null ? 0 : this.getRiskName().hashCode());
		result = 37 * result
				+ (getRtName() == null ? 0 : this.getRtName().hashCode());
		result = 37 * result
				+ (getDepName() == null ? 0 : this.getDepName().hashCode());
		result = 37 * result
				+ (getDepQueue() == null ? 0 : this.getDepQueue().hashCode());
		result = 37 * result
				+ (getRtQueue() == null ? 0 : this.getRtQueue().hashCode());
		result = 37 * result
				+ (getRiskQueue() == null ? 0 : this.getRiskQueue().hashCode());
		result = 37
				* result
				+ (getRiFinvalue() == null ? 0 : this.getRiFinvalue()
						.hashCode());
		result = 37
				* result
				+ (getRiFramevalue() == null ? 0 : this.getRiFramevalue()
						.hashCode());
		result = 37
				* result
				+ (getRiLawvalue() == null ? 0 : this.getRiLawvalue()
						.hashCode());
		result = 37
				* result
				+ (getRiClivalue() == null ? 0 : this.getRiClivalue()
						.hashCode());
		result = 37
				* result
				+ (getRiEmpvalue() == null ? 0 : this.getRiEmpvalue()
						.hashCode());
		result = 37
				* result
				+ (getRiOpevalue() == null ? 0 : this.getRiOpevalue()
						.hashCode());
		result = 37
				* result
				+ (getRiSafevalue() == null ? 0 : this.getRiSafevalue()
						.hashCode());
		result = 37
				* result
				+ (getRiAllvalue() == null ? 0 : this.getRiAllvalue()
						.hashCode());
		return result;
	}


}