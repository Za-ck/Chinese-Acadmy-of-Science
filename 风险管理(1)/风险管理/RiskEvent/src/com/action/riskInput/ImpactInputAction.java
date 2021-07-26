package com.action.riskInput;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServlet;

import org.apache.struts2.ServletActionContext;

import com.dao.RiskDAO;
import com.dao.RiskEventDAO;
import com.dao.RiskImpactDAO;
import com.dao.RiskManageDAO;

import com.model.Client;
import com.model.Employee;
import com.model.EventFile;
import com.model.Finance;
import com.model.Law;
import com.model.Operation;
import com.model.Probability;
import com.model.Reputation;
import com.model.Risk;
import com.model.RiskEvent;
import com.model.RiskImpact;
import com.model.RiskManage;
import com.model.Safe;
import com.dao.*;
import com.model.*;

public class ImpactInputAction extends HttpServlet {

	private String idimpact;
	private String depname;
	//private String money;
	private String standard;
	private String source;
	private String kpi;
	private String financedegree;
	private String financeshow;
	private String finance;
	private String prodegree;
	private String proshow;
	private String probability;
	private String busarea;
	private String famedegree;
	private String fameshow;
	private String fame;
	private String lawdegree;
	private String lawshow;
	private String law;
	private String clientdegree;
	private String clientshow;
	private String client;
	private String employeedegree;
	private String employeeshow;
	private String employee;
	private String operationdegree;
	private String operationshow;
	private String operation;
	private String safedegree;
	private String safeshow;
	private String safe;
	private String chance;
	private String strategy;
	private String plandate;
	private String control;
	private String planresource;
	private String reply;
	private String reId;
	private String filId;
	private String flowfilId;	
	private String allFileId="@";
	private String allFileName="";
	private String allflowFileId="@";
	private String allflowFileName="";
	private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
    private int recordNum;//总记录数
    private int recordSearchedNum;//当前查询的记录数
	private String idCheckFlag;      
	private String finAsset;
	private String finIncome;
	private String finProfit;
	private String finProperty;
	private String proProbability;
	private String proDisasterEvent;
	private String proDailyOperation;
	private String repCom;
	private String repSup;
	private String repPar;
	private String repPub;
	private String safSafety;
	private String safEnvironment;
	private String fileNameString="";
	private String flowfileNameString="";
	private String filetype="";
	
	private RiskManageDAO riskmanageDao;
	RiskManage riskmanage=new RiskManage();
	
	private String impactFlag;
	private RiskImpactDAO riskimpactDao;
	RiskImpact riskimpact=new RiskImpact();
	private String idmanage;
	
	private String risktype;
	private String riskname;
	private String eventid;
	private String riskremark;
	private String indep;
	Risk risk=new Risk();
	private RiskDAO riskDao;
	Probability proentity=new Probability();
	private ProbabilityDAO probabilityDao;
	Finance finentity=new Finance();
	private FinanceDAO financeDao;
	Reputation repentity=new Reputation();
	private ReputationDAO reputationDao;
	Law lawentity=new Law();
	private LawDAO lawDao;
	Client cliententity=new Client();
	private ClientDAO clientDao;
	Employee empentity=new Employee();
	private EmployeeDAO employeeDao;
	Operation opeentity=new Operation();
	private OperationDAO operationDao;
	Safe safeentity=new Safe();
	private SafeDAO safeDao;
	
	private RiskEventDAO riskeventDao;
	private RiskFileDAO riskFileDao;
	private FileManageDAO fileManageDao;
	private EventFileDAO eventFileDao;
	private EventFlowFileDAO eventFlowFileDao;
	private RiskFileViewDAO riskFileViewDao;
	
	RiskEvent re=new RiskEvent();
	RiskFile riskFile=new RiskFile();
	FileManage fileManage=new FileManage();
	FileManage fileManage1=new FileManage();
	FlowFileManage flowfileManage=new FlowFileManage();
	FlowFileManage flowfileManage1=new FlowFileManage();
	public String getFlowfilId() {
		return flowfilId;
	}
	public void setFlowfilId(String flowfilId) {
		this.flowfilId = flowfilId;
	}
	public FlowFileManage getFlowfileManage() {
		return flowfileManage;
	}
	public void setFlowfileManage(FlowFileManage flowfileManage) {
		this.flowfileManage = flowfileManage;
	}
	public FlowFileManage getFlowfileManage1() {
		return flowfileManage1;
	}
	public void setFlowfileManage1(FlowFileManage flowfileManage1) {
		this.flowfileManage1 = flowfileManage1;
	}
	EventFile ef=new EventFile();
	EventFlowFile eff = new EventFlowFile();
	
	private List<RiskEvent> reList;
	private List<RiskFile> rfList;
	private List<EventFile> efList;
	private List<EventFlowFile> effList;
	private List<FileManage> filList1;
	private List<FileManage> filList2;
	private List<FlowFileManage> flowfilList1;
	private List<FlowFileManage> flowfilList2;
	
	private List<Reputation> repList;
	private List<Reputation> repList1;
	private List<Probability> proList;
	private List<Probability> proList1;
	private List<Finance> finList;
	private List<Finance> finList1;
	private List<Safe> safList;
	private List<Safe> safList1;	
	private List<Law> lawList;
	private List<Law> lawList1;
	private List<Client> cliList;
	private List<Client> cliList1;
	private List<Employee> empList;
	private List<Employee> empList1;
	private List<Operation> opeList;
	private List<Operation> opeList1;
	private String fileId;
	private String fileName;
	private String flowfileId;
	private String flowfileName;
	private List<String> idCheckFile1=new LinkedList<String>();
	private List<String> idCheckFile2=new LinkedList<String>();
	private List<RiskFileView>defaultfilList;
	private List<RiskFileView>defaultfilList2;
	
	
	public int getRecordNum() {
		return recordNum;
	}
	public void setRecordNum(int recordNum) {
		this.recordNum = recordNum;
	}
	public int getRecordSearchedNum() {
		return recordSearchedNum;
	}
	public void setRecordSearchedNum(int recordSearchedNum) {
		this.recordSearchedNum = recordSearchedNum;
	}
	public List<RiskFileView> getDefaultfilList() {
		return defaultfilList;
	}
	public void setDefaultfilList(List<RiskFileView> defaultfilList) {
		this.defaultfilList = defaultfilList;
	}
	public List<RiskFileView> getDefaultfilList2() {
		return defaultfilList2;
	}
	public void setDefaultfilList2(List<RiskFileView> defaultfilList2) {
		this.defaultfilList2 = defaultfilList2;
	}
	public RiskFileViewDAO getRiskFileViewDao() {
		return riskFileViewDao;
	}
	public void setRiskFileViewDao(RiskFileViewDAO riskFileViewDao) {
		this.riskFileViewDao = riskFileViewDao;
	}
	public RiskManageDAO getRiskmanageDao() {
		return riskmanageDao;
	}
	public void setRiskmanageDao(RiskManageDAO riskmanageDao) {
		this.riskmanageDao = riskmanageDao;
	}
	public String getAllflowFileId() {
		return allflowFileId;
	}
	public void setAllflowFileId(String allflowFileId) {
		this.allflowFileId = allflowFileId;
	}
	public String getAllflowFileName() {
		return allflowFileName;
	}
	public void setAllflowFileName(String allflowFileName) {
		this.allflowFileName = allflowFileName;
	}
	public RiskManage getRiskmanage() {
		return riskmanage;
	}
	public void setRiskmanage(RiskManage riskmanage) {
		this.riskmanage = riskmanage;
	}
	public String getChance() {
		return chance;
	}
	public void setChance(String chance) {
		this.chance = chance;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public String getPlandate() {
		return plandate;
	}
	public void setPlandate(String plandate) {
		this.plandate = plandate;
	}
	public String getControl() {
		return control;
	}
	public void setControl(String control) {
		this.control = control;
	}
	public String getPlanresource() {
		return planresource;
	}
	public void setPlanresource(String planresource) {
		this.planresource = planresource;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getIdimpact() {
		return idimpact;
	}
	public void setIdimpact(String idimpact) {
		this.idimpact = idimpact;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getKpi() {
		return kpi;
	}
	public void setKpi(String kpi) {
		this.kpi = kpi;
	}
	public String getFinancedegree() {
		return financedegree;
	}
	public void setFinancedegree(String financedegree) {
		this.financedegree = financedegree;
	}
	public String getFinanceshow() {
		return financeshow;
	}
	public void setFinanceshow(String financeshow) {
		this.financeshow = financeshow;
	}
	public String getFinance() {
		return finance;
	}
	public void setFinance(String finance) {
		this.finance = finance;
	}
	public String getProdegree() {
		return prodegree;
	}
	public void setProdegree(String prodegree) {
		this.prodegree = prodegree;
	}
	public String getProshow() {
		return proshow;
	}
	public void setProshow(String proshow) {
		this.proshow = proshow;
	}
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	public String getBusarea() {
		return busarea;
	}
	public void setBusarea(String busarea) {
		this.busarea = busarea;
	}
	public String getFameshow() {
		return fameshow;
	}
	public void setFameshow(String fameshow) {
		this.fameshow = fameshow;
	}
	public String getFame() {
		return fame;
	}
	public void setFame(String fame) {
		this.fame = fame;
	}
	public String getLawdegree() {
		return lawdegree;
	}
	public void setLawdegree(String lawdegree) {
		this.lawdegree = lawdegree;
	}
	public String getLawshow() {
		return lawshow;
	}
	public void setLawshow(String lawshow) {
		this.lawshow = lawshow;
	}
	public String getLaw() {
		return law;
	}
	public void setLaw(String law) {
		this.law = law;
	}
	public String getClientdegree() {
		return clientdegree;
	}
	public void setClientdegree(String clientdegree) {
		this.clientdegree = clientdegree;
	}
	public String getClientshow() {
		return clientshow;
	}
	public void setClientshow(String clientshow) {
		this.clientshow = clientshow;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getEmployeedegree() {
		return employeedegree;
	}
	public void setEmployeedegree(String employeedegree) {
		this.employeedegree = employeedegree;
	}
	public String getEmployeeshow() {
		return employeeshow;
	}
	public void setEmployeeshow(String employeeshow) {
		this.employeeshow = employeeshow;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getOperationdegree() {
		return operationdegree;
	}
	public void setOperationdegree(String operationdegree) {
		this.operationdegree = operationdegree;
	}
	public String getOperationshow() {
		return operationshow;
	}
	public void setOperationshow(String operationshow) {
		this.operationshow = operationshow;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getSafedegree() {
		return safedegree;
	}
	public void setSafedegree(String safedegree) {
		this.safedegree = safedegree;
	}
	public String getSafeshow() {
		return safeshow;
	}
	public void setSafeshow(String safeshow) {
		this.safeshow = safeshow;
	}
	public String getSafe() {
		return safe;
	}
	public void setSafe(String safe) {
		this.safe = safe;
	}
	
	public String getImpactFlag() {
		return impactFlag;
	}
	public void setImpactFlag(String impactFlag) {
		this.impactFlag = impactFlag;
	}
	public RiskImpactDAO getRiskimpactDao() {
		return riskimpactDao;
	}
	public void setRiskimpactDao(RiskImpactDAO riskimpactDao) {
		this.riskimpactDao = riskimpactDao;
	}
	public RiskImpact getRiskimpact() {
		return riskimpact;
	}
	public void setRiskimpact(RiskImpact riskimpact) {
		this.riskimpact = riskimpact;
	}
	
	public String getIdmanage() {
		return idmanage;
	}
	public void setIdmanage(String idmanage) {
		this.idmanage = idmanage;
	}
	
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	public String getRisktype() {
		return risktype;
	}
	public void setRisktype(String risktype) {
		this.risktype = risktype;
	}
	public String getRiskname() {
		return riskname;
	}
	public void setRiskname(String riskname) {
		this.riskname = riskname;
	}
	public String getRiskremark() {
		return riskremark;
	}
	public void setRiskremark(String riskremark) {
		this.riskremark = riskremark;
	}
	public String getIndep() {
		return indep;
	}
	public void setIndep(String indep) {
		this.indep = indep;
	}

	public Risk getRisk() {
		return risk;
	}
	public void setRisk(Risk risk) {
		this.risk = risk;
	}
	public RiskDAO getRiskDao() {
		return riskDao;
	}
	public void setRiskDao(RiskDAO riskDao) {
		this.riskDao = riskDao;
	}
	public RiskEventDAO getRiskeventDao() {
		return riskeventDao;
	}
	public void setRiskeventDao(RiskEventDAO riskeventDao) {
		this.riskeventDao = riskeventDao;
	}
	
	public String getFamedegree() {
		return famedegree;
	}
	public void setFamedegree(String famedegree) {
		this.famedegree = famedegree;
	}
	
	public Finance getFinentity() {
		return finentity;
	}
	public void setFinentity(Finance finentity) {
		this.finentity = finentity;
	}
	public FinanceDAO getFinanceDao() {
		return financeDao;
	}
	public void setFinanceDao(FinanceDAO financeDao) {
		this.financeDao = financeDao;
	}
	public Reputation getRepentity() {
		return repentity;
	}
	public void setRepentity(Reputation repentity) {
		this.repentity = repentity;
	}
	public ReputationDAO getReputationDao() {
		return reputationDao;
	}
	public void setReputationDao(ReputationDAO reputationDao) {
		this.reputationDao = reputationDao;
	}
	
	public Probability getProentity() {
		return proentity;
	}
	public void setProentity(Probability proentity) {
		this.proentity = proentity;
	}
	public ProbabilityDAO getProbabilityDao() {
		return probabilityDao;
	}
	public void setProbabilityDao(ProbabilityDAO probabilityDao) {
		this.probabilityDao = probabilityDao;
	}
	public Safe getSafeentity() {
		return safeentity;
	}
	public void setSafeentity(Safe safeentity) {
		this.safeentity = safeentity;
	}
	public SafeDAO getSafeDao() {
		return safeDao;
	}
	public void setSafeDao(SafeDAO safeDao) {
		this.safeDao = safeDao;
	}

	public Law getLawentity() {
		return lawentity;
	}
	public void setLawentity(Law lawentity) {
		this.lawentity = lawentity;
	}
	public LawDAO getLawDao() {
		return lawDao;
	}
	public void setLawDao(LawDAO lawDao) {
		this.lawDao = lawDao;
	}
	public Client getCliententity() {
		return cliententity;
	}
	public void setCliententity(Client cliententity) {
		this.cliententity = cliententity;
	}
	public ClientDAO getClientDao() {
		return clientDao;
	}
	public void setClientDao(ClientDAO clientDao) {
		this.clientDao = clientDao;
	}
	public Employee getEmpentity() {
		return empentity;
	}
	public void setEmpentity(Employee empentity) {
		this.empentity = empentity;
	}
	public EmployeeDAO getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}
	public Operation getOpeentity() {
		return opeentity;
	}
	public void setOpeentity(Operation opeentity) {
		this.opeentity = opeentity;
	}
	public OperationDAO getOperationDao() {
		return operationDao;
	}
	public void setOperationDao(OperationDAO operationDao) {
		this.operationDao = operationDao;
	}	
	public String getReId() {
		return reId;
	}
	public void setReId(String reId) {
		this.reId = reId;
	}
	public String getFilId() {
		return filId;
	}
	public void setFilId(String filId) {
		this.filId = filId;
	}	
	public String getAllFileId() {
		return allFileId;
	}
	public void setAllFileId(String allFileId) {
		this.allFileId = allFileId;
	}
	public String getIdCheckFlag() {
		return idCheckFlag;
	}
	public void setIdCheckFlag(String idCheckFlag) {
		this.idCheckFlag = idCheckFlag;
	}
	public RiskFileDAO getRiskFileDao() {
		return riskFileDao;
	}
	public void setRiskFileDao(RiskFileDAO riskFileDao) {
		this.riskFileDao = riskFileDao;
	}
	public FileManageDAO getFileManageDao() {
		return fileManageDao;
	}
	public void setFileManageDao(FileManageDAO fileManageDao) {
		this.fileManageDao = fileManageDao;
	}
	public EventFileDAO getEventFileDao() {
		return eventFileDao;
	}
	public void setEventFileDao(EventFileDAO eventFileDao) {
		this.eventFileDao = eventFileDao;
	}
	public EventFlowFileDAO getEventFlowFileDao() {
		return eventFlowFileDao;
	}
	public void setEventFlowFileDao(EventFlowFileDAO eventFlowFileDao) {
		this.eventFlowFileDao = eventFlowFileDao;
	}
	public RiskEvent getRe() {
		return re;
	}
	public void setRe(RiskEvent re) {
		this.re = re;
	}
	public RiskFile getRiskFile() {
		return riskFile;
	}
	public void setRiskFile(RiskFile riskFile) {
		this.riskFile = riskFile;
	}
	public FileManage getFileManage() {
		return fileManage;
	}
	public void setFileManage(FileManage fileManage) {
		this.fileManage = fileManage;
	}
	public FileManage getFileManage1() {
		return fileManage1;
	}
	public void setFileManage1(FileManage fileManage1) {
		this.fileManage1 = fileManage1;
	}
	public EventFile getEf() {
		return ef;
	}
	public void setEf(EventFile ef) {
		this.ef = ef;
	}
	public EventFlowFile getEff() {
		return eff;
	}
	public void setEff(EventFlowFile eff) {
		this.eff = eff;
	}
	public List<RiskEvent> getReList() {
		return reList;
	}
	public void setReList(List<RiskEvent> reList) {
		this.reList = reList;
	}
	public List<RiskFile> getRfList() {
		return rfList;
	}
	public void setRfList(List<RiskFile> rfList) {
		this.rfList = rfList;
	}
	public List<EventFile> getEfList() {
		return efList;
	}
	public void setEfList(List<EventFile> efList) {
		this.efList = efList;
	}	
	public List<EventFlowFile> getEffList() {
		return effList;
	}
	public void setEffList(List<EventFlowFile> effList) {
		this.effList = effList;
	}
	public List<FileManage> getFilList1() {
		return filList1;
	}
	public void setFilList1(List<FileManage> filList1) {
		this.filList1 = filList1;
	}
	public List<FileManage> getFilList2() {
		return filList2;
	}
	public void setFilList2(List<FileManage> filList2) {
		this.filList2 = filList2;
	}		
	public List<FlowFileManage> getFlowfilList1() {
		return flowfilList1;
	}
	public void setFlowfilList1(List<FlowFileManage> flowfilList1) {
		this.flowfilList1 = flowfilList1;
	}
	public List<String> getIdCheckFile1() {
		return idCheckFile1;
	}
	public void setIdCheckFile1(List<String> idCheckFile1) {
		this.idCheckFile1 = idCheckFile1;
	}
	public List<String> getIdCheckFile2() {
		return idCheckFile2;
	}
	public void setIdCheckFile2(List<String> idCheckFile2) {
		this.idCheckFile2 = idCheckFile2;
	}	
	public String getAllFileName() {
		return allFileName;
	}
	public void setAllFileName(String allFileName) {
		this.allFileName = allFileName;
	}	
	public String getFinAsset() {
		return finAsset;
	}
	public void setFinAsset(String finAsset) {
		this.finAsset = finAsset;
	}
	public String getFinIncome() {
		return finIncome;
	}
	public void setFinIncome(String finIncome) {
		this.finIncome = finIncome;
	}
	public String getFinProfit() {
		return finProfit;
	}
	public void setFinProfit(String finProfit) {
		this.finProfit = finProfit;
	}
	public String getFinProperty() {
		return finProperty;
	}
	public void setFinProperty(String finProperty) {
		this.finProperty = finProperty;
	}
	public String getProProbability() {
		return proProbability;
	}
	public void setProProbability(String proProbability) {
		this.proProbability = proProbability;
	}
	public String getProDisasterEvent() {
		return proDisasterEvent;
	}
	public void setProDisasterEvent(String proDisasterEvent) {
		this.proDisasterEvent = proDisasterEvent;
	}
	public String getProDailyOperation() {
		return proDailyOperation;
	}
	public void setProDailyOperation(String proDailyOperation) {
		this.proDailyOperation = proDailyOperation;
	}
	public String getRepCom() {
		return repCom;
	}
	public void setRepCom(String repCom) {
		this.repCom = repCom;
	}
	public String getRepSup() {
		return repSup;
	}
	public void setRepSup(String repSup) {
		this.repSup = repSup;
	}
	public String getRepPar() {
		return repPar;
	}
	public void setRepPar(String repPar) {
		this.repPar = repPar;
	}
	public String getRepPub() {
		return repPub;
	}
	public void setRepPub(String repPub) {
		this.repPub = repPub;
	}
	public String getSafSafety() {
		return safSafety;
	}
	public void setSafSafety(String safSafety) {
		this.safSafety = safSafety;
	}
	public String getSafEnvironment() {
		return safEnvironment;
	}
	public void setSafEnvironment(String safEnvironment) {
		this.safEnvironment = safEnvironment;
	}
	
	public List<Reputation> getRepList() {
		return repList;
	}
	public void setRepList(List<Reputation> repList) {
		this.repList = repList;
	}
	public List<Reputation> getRepList1() {
		return repList1;
	}
	public void setRepList1(List<Reputation> repList1) {
		this.repList1 = repList1;
	}
	public List<Probability> getProList() {
		return proList;
	}
	public void setProList(List<Probability> proList) {
		this.proList = proList;
	}
	public List<Probability> getProList1() {
		return proList1;
	}
	public void setProList1(List<Probability> proList1) {
		this.proList1 = proList1;
	}
	public List<Finance> getFinList() {
		return finList;
	}
	public void setFinList(List<Finance> finList) {
		this.finList = finList;
	}
	public List<Finance> getFinList1() {
		return finList1;
	}
	public void setFinList1(List<Finance> finList1) {
		this.finList1 = finList1;
	}
	public List<Safe> getSafList() {
		return safList;
	}
	public void setSafList(List<Safe> safList) {
		this.safList = safList;
	}
	public List<Safe> getSafList1() {
		return safList1;
	}
	public void setSafList1(List<Safe> safList1) {
		this.safList1 = safList1;
	}
	public List<Law> getLawList() {
		return lawList;
	}
	public void setLawList(List<Law> lawList) {
		this.lawList = lawList;
	}
	public List<Law> getLawList1() {
		return lawList1;
	}
	public void setLawList1(List<Law> lawList1) {
		this.lawList1 = lawList1;
	}
	public List<Client> getCliList() {
		return cliList;
	}
	public void setCliList(List<Client> cliList) {
		this.cliList = cliList;
	}
	public List<Client> getCliList1() {
		return cliList1;
	}
	public void setCliList1(List<Client> cliList1) {
		this.cliList1 = cliList1;
	}
	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	public List<Employee> getEmpList1() {
		return empList1;
	}
	public void setEmpList1(List<Employee> empList1) {
		this.empList1 = empList1;
	}
	public List<Operation> getOpeList() {
		return opeList;
	}
	public void setOpeList(List<Operation> opeList) {
		this.opeList = opeList;
	}
	public List<Operation> getOpeList1() {
		return opeList1;
	}
	public void setOpeList1(List<Operation> opeList1) {
		this.opeList1 = opeList1;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
	public String getFlowfileId() {
		return flowfileId;
	}
	public void setFlowfileId(String flowfileId) {
		this.flowfileId = flowfileId;
	}
	public String getFlowfileName() {
		return flowfileName;
	}
	public void setFlowfileName(String flowfileName) {
		this.flowfileName = flowfileName;
	}
	public int getCurrent_pagenum() {
		return current_pagenum;
	}
	public void setCurrent_pagenum(int currentPagenum) {
		current_pagenum = currentPagenum;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}		
	public String getFileNameString() {
		return fileNameString;
	}
	public void setFileNameString(String fileNameString) {
		this.fileNameString = fileNameString;
	}	
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getFlowfileNameString() {
		return flowfileNameString;
	}
	public void setFlowfileNameString(String flowfileNameString) {
		this.flowfileNameString = flowfileNameString;
	}
	
	public List<FlowFileManage> getFlowfilList2() {
		return flowfilList2;
	}
	public void setFlowfilList2(List<FlowFileManage> flowfilList2) {
		this.flowfilList2 = flowfilList2;
	}
	//风险影响信息更新
	public String impactInput()
	{
		System.out.println("222222");
		int num=1;
		int numfin=0;
		int numfam=0;
		int numpro=0;
		int numsaf=0;
		riskimpact=this.getRiskimpactDao().findById(this.getIdimpact());
		//通过用户选择的影响程度，查找用户字典获取相应描述
		finentity=financeDao.findById(Integer.parseInt(this.getFinancedegree()));
		proentity=probabilityDao.findById(Integer.parseInt(this.getProdegree()));
		repentity=reputationDao.findById(Integer.parseInt(this.getFamedegree()));
		safeentity=safeDao.findById(Integer.parseInt(this.getSafedegree()));
		lawentity=lawDao.findById(Integer.parseInt(this.getLawdegree()));
		cliententity=clientDao.findById(Integer.parseInt(this.getClientdegree()));
		opeentity=operationDao.findById(Integer.parseInt(this.getOperationdegree()));
		empentity=employeeDao.findById(Integer.parseInt(this.getEmployeedegree()));
		String findetail="影响程度："+finentity.getFinDetail()+"。"+"\n"+"影响描述："+"\n";
		String famedetail="影响程度："+repentity.getRepLevel()+"。"+"\n"+"影响描述："+"\n";
		String prodetail="影响程度："+proentity.getProLevel()+"。"+"\n"+"影响描述："+"\n";
		String safedetail="影响程度："+safeentity.getSafLevel()+"。"+"\n"+"影响描述："+"\n";
			
		//财务标准描述
		if (null != this.getFinancedegree()) {
			num=1;
			numfin=0;
			if(this.getFinancedegree().equals(this.getFinAsset())){
				findetail=findetail+num+"、"+"净资产："+finentity.getFinAsset();
				num=num+1;
				numfin=1;
			}
			if(this.getFinancedegree().equals(this.getFinIncome())){
				if(1 == num){
					findetail=findetail+num+"、"+"营业收入："+finentity.getFinIncome();
				}
				else{
					findetail=findetail+"；"+"\n"+num+"、"+"营业收入："+finentity.getFinIncome();
				}
				num=num+1;
				numfin=1;
			}
			if(this.getFinancedegree().equals(this.getFinProfit())){
				if(1==num){
					findetail=findetail+num+"、"+"利润总额："+finentity.getFinProfit();
				}
				else{
					findetail=findetail+"；"+"\n"+num+"、"+"利润总额："+finentity.getFinProfit();
				}
				num=num+1;
				numfin=1;
			}
			if(this.getFinancedegree().equals(this.getFinProperty())){
				if(1==num){
					findetail=findetail+num+"、"+"资产总额："+finentity.getFinProperty();
				}
				else{
					findetail=findetail+"；"+"\n"+num+"、"+"资产总额："+finentity.getFinProperty();
				}
				num=num+1;
				numfin=1;
			}
			findetail=findetail+"。";
			num=1;
		}
		else {
			//System.out.println ("none");
		}
			
		//声誉标准描述
		if (null != this.getFamedegree()) {
			num=1;
			numfam=0;
			if(this.getFamedegree().equals(this.getRepCom())){
				famedetail=famedetail+num+"、"+"对企业声誉："+repentity.getRepDetail();
				num=num+1;
				numfam=1;
			}
			if(this.getFamedegree().equals(this.getRepSup())){
				if(1==num){
					famedetail=famedetail+num+"、"+"对监管机构/上级单位："+repentity.getRepSuperior();
				}
				else{
					famedetail=famedetail+"；"+"\n"+num+"、"+"对监管机构/上级单位："+repentity.getRepSuperior();
				}
				num=num+1;
				numfam=1;
			}
			if(this.getFamedegree().equals(this.getRepPar())){
				if(1==num){
					famedetail=famedetail+num+"、"+"对合作伙伴："+repentity.getRepPartner();
				}
				else{
					famedetail=famedetail+"；"+"\n"+num+"、"+"对合作伙伴："+repentity.getRepPartner();
				}
				num=num+1;
				numfam=1;
			}
			if(this.getFamedegree().equals(this.getRepPub())){
				if(1==num){
					famedetail=famedetail+num+"、"+"对员工/公众："+repentity.getRepPublic();
				}
				else{
					famedetail=famedetail+"；"+"\n"+num+"、"+"对员工/公众："+repentity.getRepPublic();
				}
				num=num+1;
				numfam=1;
			}
			famedetail=famedetail+"。";
			num=1;
		}
		else {
			//System.out.println ("none");
		}
			
		//发生可能性标准描述
		if (null != this.getProdegree()) {
			num=1;
			numpro=0;
			if(this.getProdegree().equals(this.getProProbability())){
				prodetail=prodetail+num+"、"+"发生概率："+proentity.getProProbability();					
				num=num+1;
				numpro=1;
			}
			if(this.getProdegree().equals(this.getProDisasterEvent())){
				if(1==num){
					prodetail=prodetail+num+"、"+"针对大型灾害/事件类："+proentity.getProDisasterEvent();
				}
				else{
					prodetail=prodetail+"；"+"\n"+num+"、"+"针对大型灾害/事件类："+proentity.getProDisasterEvent();
				}
				num=num+1;
				numpro=1;
			}
			if(this.getProdegree().equals(this.getProDailyOperation())){
				if(1==num){
					prodetail=prodetail+num+"、"+"针对日常运营："+proentity.getProDailyOperation();
				}
				else{
					prodetail=prodetail+"；"+"\n"+num+"、"+"针对日常运营："+proentity.getProDailyOperation();
				}
					num=num+1;
					numpro=1;
				}
				prodetail=prodetail+"。";
				num=1;
			}
			else {
				//System.out.println ("none");
			}
			
				//人员健康安全标准描述
				if (null != this.getSafedegree()) {
					num=1;
					numsaf=0;
					if(this.getSafedegree().equals(this.getSafSafety())){
						safedetail=safedetail+num+"、"+"安全事故："+safeentity.getSafSafety()+"\n";
						num=num+1;
						numsaf=1;
					}
					if(this.getSafedegree().equals(this.getSafEnvironment())){
						safedetail=safedetail+num+"、"+"环境："+safeentity.getSafEnvironment()+"\n";
						num=num+1;
						numsaf=1;
					}
					num=1;
				}
				else {
					//System.out.println ("none");
				}
			//根据影响程度算出综合评定值
			//int pro=Integer.parseInt(this.getFinancedegree());
			int pro=Integer.parseInt(this.getProdegree());
			int sro=Integer.parseInt(this.financedegree)+Integer.parseInt(this.getFamedegree())+Integer.parseInt(this.getLawdegree())+Integer.parseInt(this.getClientdegree())+Integer.parseInt(this.getEmployeedegree())+Integer.parseInt(this.getOperationdegree())+Integer.parseInt(this.getSafedegree());
			double evaluation=pro*sro;
			
			//riskimpact.setRiReId(this.getIdimpact());
			riskimpact.setRiKpi(this.getKpi());
			riskimpact.setRiProdegree(Integer.parseInt(this.getProdegree()));
			if(1==numpro){
				riskimpact.setRiProbability(prodetail);
			}
			riskimpact.setRiBusarea(this.getBusarea());
			riskimpact.setRiSource(this.getSource());
			riskimpact.setRiFlow(this.getStandard());
			riskimpact.setRiInchargedep(this.getDepname());
			riskimpact.setRiFinance(this.getFinance());
            riskimpact.setRiMoney(evaluation);//综合评定值存在原来损失金额的字段中
			riskimpact.setRiFame(this.getFame());			
			riskimpact.setRiLaw(this.getLaw());
			riskimpact.setRiClient(this.getClient());
			riskimpact.setRiEmployee(this.getEmployee());
			riskimpact.setRiOperation(this.getOperation());
			riskimpact.setRiSafe(this.getSafe());
			riskimpact.setRiFindegree(Integer.parseInt(this.getFinancedegree()));
			if(1==numfin){
				riskimpact.setRiFincriteria(findetail);
			}	
			riskimpact.setRiFamedegree(Integer.parseInt(this.getFamedegree()));
			if(1==numfam){
				riskimpact.setRiFamecriteria(famedetail);
			}
			riskimpact.setRiLawdegree(Integer.parseInt(this.getLawdegree()));
			//riskimpact.setRiLawcriteria(lawdetail);
			if(this.getLawshow().length()>5){
				riskimpact.setRiLawcriteria(this.getLawshow().trim());
				
			}
			riskimpact.setRiClidegree(Integer.parseInt(this.getClientdegree()));
			//riskimpact.setRiClicriteria(clientdetail);
			if(this.getClientshow().length()>5){
				riskimpact.setRiClicriteria(this.getClientshow().trim());
			}
			riskimpact.setRiEmpdegree(Integer.parseInt(this.getEmployeedegree()));
			//riskimpact.setRiEmpcriteria(employeedetail);
			if(this.getEmployeeshow().length()>5){
				riskimpact.setRiEmpcriteria(this.getEmployeeshow().trim());
			}
			riskimpact.setRiOpedegree(Integer.parseInt(this.getOperationdegree()));
			//riskimpact.setRiOpecriteria(operaitondetail);
			if(this.getOperationshow().length()>5){
				riskimpact.setRiOpecriteria(this.getOperationshow().trim());
			}
			riskimpact.setRiSafedegree(Integer.parseInt(this.getSafedegree()));
			if(1==numsaf){
				riskimpact.setRiSafecriteria(safedetail);
			}	
			this.getRiskimpactDao().merge(riskimpact);
			
			// 更新风险事件的最后处理时间
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.getRiskeventDao().updateLastDate(this.getIdimpact(), df.format(date));
			
			String s= finish();//存储流程文件
			String t = finishflow();
			idmanage=this.getIdimpact();
			getManageShow(this.getIdimpact());
			return "success";
	}
	
    //显示风险影响
    public String getRiskImpact(){
    	try{
    		
    		
    	RiskImpact riskimpact=new RiskImpact();
    //	RiskImpactDAO impactdao=new RiskImpactDAO();
		//riskimpact=impactdao.findByIdback(this.getIdimpact());
    	//RiskImpactDAO impactDao=new RiskImpactDAO();
    	//得到”我院声誉影响”的list，repList
		reputationList();
		//得到”财务影响”的list，finList
		financeList();
		//得到”发生可能性”的list，proList
		probabilityList();
		//得到”人员健康环保影响”的list，safList
		safeList();
		//得到”对法律法规影响”的list，lawList
		lawList();
		//得到”对客户关系影响”的list，cliList
		clientList();
		//得到”员工满意度影响”的list，empList
		employeeList();
		//得到”对运营影响”的list，opeList
		operationList();
		
    	String tempid=this.getIdimpact();
    	//System.out.printf(tempid+"1245678998765432123456789");
    	riskimpact=this.getRiskimpactDao().findById(tempid);				
		this.kpi=riskimpact.getRiKpi();
		this.prodegree=riskimpact.getRiProdegree().toString();
		this.probability=riskimpact.getRiProbability();
		this.busarea=riskimpact.getRiBusarea();
		this.source=riskimpact.getRiSource();
		this.standard=riskimpact.getRiFlow();
		this.depname=riskimpact.getRiInchargedep();
		this.finance=riskimpact.getRiFinance();
		//this.money=riskimpact.getRiMoney().toString();
		this.fame=riskimpact.getRiFame();
		this.law=riskimpact.getRiLaw();
		this.client=riskimpact.getRiClient();
		this.employee=riskimpact.getRiEmployee();
		this.operation=riskimpact.getRiOperation();
		this.safe=riskimpact.getRiSafe();
		this.financedegree=riskimpact.getRiFindegree().toString();
		this.famedegree=riskimpact.getRiFamedegree().toString();
		this.lawdegree=riskimpact.getRiLawdegree().toString();
		this.clientdegree=riskimpact.getRiClidegree().toString();
		this.employeedegree=riskimpact.getRiEmpdegree().toString();
		this.operationdegree=riskimpact.getRiOpedegree().toString();
		this.safedegree=riskimpact.getRiSafedegree().toString();
		String s= getAllFile(tempid);//得到allFileName和allFileId
		String t= getAllFlowFile(tempid);//得到allFileName和allFileId
		//System.out.printf("获取流转文件:"+s);
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
    }
    
    //点击“下一页”显示风险管理信息
    public String getManageShow(String tempid)
    {
    	try{
			riskmanage=this.getRiskmanageDao().findById(this.getIdmanage());
			this.idmanage=riskmanage.getRmReId();
			this.chance=riskmanage.getRmChance();
			this.control=riskmanage.getRmControl();
			this.reply=riskmanage.getRmReply();
			this.strategy=riskmanage.getRmStrategy();
			this.planresource=riskmanage.getRmPlanres();
			if(null==riskmanage.getRmPlandate()){
				this.plandate="";
			}
			else
				this.plandate=riskmanage.getRmPlandate().toString();
			
			}catch(Exception e){
				e.printStackTrace();
				return "fail";
			}
			return "success";
    }
    
    public String selectFile3()
    {//不分页，这里直接取出了所有数据
    	try{
    		this.setAllFileName(URLDecoder.decode(this.getAllFileName(),"UTF-8"));
        	this.filList1=new LinkedList<FileManage>();
    		String type = this.getFiletype();
    		if(null==this.getFileNameString()) //如果没有有搜索关键字
    		{
    			this.setFileNameString("");
    		}
    		this.recordNum = this.getFileManageDao().findAll("",type).size();//获取总记录数
    		this.filList1=this.getFileManageDao().findAll(this.getFileNameString(),type);
			this.recordSearchedNum = this.filList1.size();//获取搜索到的记录是
    		
			//ServletActionContext.getRequest().setAttribute("filList1", filList1);
			//ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
			//获取跟风险相关的文件
			//String reId = new String(this.idimpact);
			//String riskId = reId.substring(0, reId.lastIndexOf("-"));
			//this.defaultfilList = new LinkedList<RiskFileView>();
	
			//this.defaultfilList = this.getRiskFileViewDao().getRelatedFile(riskId,type,this.getFileNameString());
			//ServletActionContext.getRequest().setAttribute("defaultfilList", defaultfilList);
    	}catch(Exception e){
			e.printStackTrace();
		}
    	return "success";
    }
    
    //RiskImpact录入——涉及流程——单击“添加文件”功能  及 RiskImpact录入——涉及流程——添加文件——查询功能
	public String selectFile1(){
		try{
			
			//System.out.println("罗云1/"+this.allFileId+"/1罗云");
			//得到filList1
			if(null==this.getFileNameString()) this.setFileNameString("");
			
			this.setAllFileName(URLDecoder.decode(this.getAllFileName(),"UTF-8"));
			this.filList1=new LinkedList<FileManage>();
			String type = this.getFiletype();
			this.filList1=this.getFileManageDao().findAll((current_pagenum-1)*pageNum,pageNum,this.getFileNameString(),type);
			ServletActionContext.getRequest().setAttribute("filList1", filList1);
			ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
			//获取跟风险相关的文件
			String reId = new String(this.idimpact);
			String riskId = reId.substring(0, reId.lastIndexOf("-"));
			this.defaultfilList = new LinkedList<RiskFileView>();
		
			this.defaultfilList = this.getRiskFileViewDao().getRelatedFile(riskId,type,this.getFileNameString());
			ServletActionContext.getRequest().setAttribute("defaultfilList", defaultfilList);
			
			//System.out.println("罗云2/"+this.getFileNameString()+"/2罗云");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	
	//添加流程文件
	public String selectFlowFile(){
		try{
			//System.out.println("罗云1/"+this.getFileNameString()+"/1罗云");
			//得到filList1
			if(null==this.getFlowfileNameString()) this.setFlowfileNameString("");
			this.setAllflowFileName(URLDecoder.decode(this.getAllflowFileName(),"UTF-8"));
			this.flowfilList1=new LinkedList<FlowFileManage>();
			this.flowfilList1=this.getFileManageDao().findAllFlowFile(this.getFlowfileNameString());
			this.recordSearchedNum=this.flowfilList1.size();
			this.recordNum=this.getFileManageDao().findAllFlowFile("").size();
			//this.flowfilList1=this.getFileManageDao().findAllFlowFile((current_pagenum-1)*pageNum,pageNum,this.getFlowfileNameString());
			//ServletActionContext.getRequest().setAttribute("flowfilList1", flowfilList1);              
			//ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	 
			
			//获取跟风险相关的文件
			String reId = new String(this.idimpact);
			String riskId = reId.substring(0, reId.lastIndexOf("-"));
			this.defaultfilList2 = new LinkedList<RiskFileView>();
			this.defaultfilList2 = this.getRiskFileViewDao().getRelatedFile(riskId,"5");
			ServletActionContext.getRequest().setAttribute("defaultfilList2", defaultfilList2);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	
	//RiskImpact录入——涉及流程——单击“减少已选文件”功能
	public String selectFile2(){
		try{
			//得到filList2
			this.filList2=new LinkedList<FileManage>();
			String sId[]=this.getAllFileId().split("@");
			if(sId.length>0){
				for(int i=0;i<sId.length;i++){
					if(sId[i].length()>1){
						this.filId=sId[i];
						this.fileManage=this.getFileManageDao().findById(this.getFilId());
						this.filList2.add(this.getFileManage());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	
	//RiskImpact录入——实际内控流程——单击“减少已选文件”功能
	public String deleteFlowFile(){
		try{
			//得到filList2
			this.flowfilList2=new LinkedList<FlowFileManage>();
			String sfId[]=this.getAllflowFileId().split("@");
			if(sfId.length>0){
				for(int i=0;i<sfId.length;i++){
					if(sfId[i].length()>1){
						this.flowfilId=sfId[i];
						//System.out.println(this.getFlowfilId()+"123456987899655+++++++++++");
						this.flowfileManage=this.getFileManageDao().findFlowById(this.getFlowfilId());
						this.flowfilList2.add(this.getFlowfileManage());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}

	//RiskImpact录入——涉及流程--将选中的体系文件存储
	public String finish(){
		try{
			//在保存ef前，先将原有ef删除
			this.efList=new LinkedList<EventFile>();
			if(null!=this.getEventFileDao().findByProperty("reId", this.getIdimpact())){				
				this.efList=this.getEventFileDao().findByProperty("reId", this.getIdimpact());			
				Iterator it2=this.efList.iterator();
				while(it2.hasNext()){
					this.ef=(EventFile) it2.next();
					this.getEventFileDao().delete(ef);
				}
			}
			if(this.getAllFileId().equals("@"))
			{
				ef.setReId(this.getIdimpact());
				ef.setFileId(null);
				this.getEventFileDao().save(ef);		
			}
			else
			{
				String[] fileIdArray=this.getAllFileId().split("@");
				//System.out.println("本风险事件对应的流程文件个数为:"+fileIdArray.length+"个");
				for(int k=1;k<fileIdArray.length;k++)
				{
					ef.setReId(this.getIdimpact());
					ef.setFileId(fileIdArray[k]);
					this.getEventFileDao().save(ef);
				}				
				//System.out.println("保存的ef个数为："+this.getEventFileDao().findByProperty("reId", this.getIdimpact()).size()+"个");
			}			
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	//RiskImpact录入——涉及内控流程--将选中的内控流程文件存储
	public String finishflow(){
		try{
			//在保存ef前，先将原有ef删除
			this.effList=new LinkedList<EventFlowFile>();
			if(null!=this.getEventFlowFileDao().findByProperty("reId", this.getIdimpact())){
				this.effList=this.getEventFlowFileDao().findByProperty("reId", this.getIdimpact());			
				Iterator it3=this.effList.iterator();
				while(it3.hasNext()){
					this.eff=(EventFlowFile) it3.next();
					this.getEventFlowFileDao().delete(eff);
				}
			}
			//System.out.println("删除后剩下的ef个数："+this.getEventFileDao().findByProperty("reId", this.getIdimpact()).size()+"个");
			
			if(this.getAllflowFileId().equals("@"))
			{
				//System.out.println("本风险事件对应的流程文件个数为:0个");
				/*eff.setReId(this.getIdimpact());
				eff.setFlowfileId(null);
				this.getEventFlowFileDao().save(eff);*/		
			}
			else
			{
				String[] flowfileIdArray=this.getAllflowFileId().split("@");
				//System.out.println("本风险事件对应的流程文件个数为:"+flowfileIdArray.length+"个");
				for(int k=1;k<flowfileIdArray.length;k++)
				{
					eff.setReId(this.getIdimpact());
					eff.setFlowfileId(flowfileIdArray[k]);
					this.getEventFlowFileDao().save(eff);
				}				
				//System.out.println("保存的ef个数为："+this.getEventFileDao().findByProperty("reId", this.getIdimpact()).size()+"个");
			}			
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	//得到allFileName和allFileId
	public String getAllFlowFile(String reId)
	{	
		try{
			this.setAllflowFileId("@");
			this.setAllflowFileName("");
			int i=1;
			int notNullFlag=0;
			this.effList=new LinkedList<EventFlowFile>();
			if(null != this.getEventFlowFileDao().findByProperty("reId",reId)){
				this.effList=this.getEventFlowFileDao().findByProperty("reId",reId);
				Iterator it=this.effList.iterator();
				while(it.hasNext()){
					this.eff=(EventFlowFile) it.next();
					if(null != this.getEff().getFlowfileId()){
						notNullFlag=1;
						this.setFlowfileId(this.getEff().getFlowfileId());
						if(null != this.getFileManageDao().findFlowById(this.getFlowfileId())){
							this.setFlowfileManage(this.getFileManageDao().findFlowById(this.getFlowfileId()));
							if("@"==this.getAllflowFileId()){
								this.setAllflowFileId("@"+this.getFlowfileId()+"@");
								//System.out.println(this.getAllflowFileId()+"21111111111111111111111111111111111");
								this.setAllflowFileName("i"+"、"+this.getFlowfileId()+"，"+this.getFlowfileManage().getFlowfileName()+"\n");
								i=i+1;							
							}else{
								this.setAllflowFileId(this.getAllflowFileId()+this.getFlowfileId()+"@");
								//System.out.println(this.getAllflowFileId()+"232222222222222222222222222222222222");
								this.setAllflowFileName(this.getAllflowFileName()+"i"+"、"+this.getFlowfileId()+"，"+this.getFlowfileManage().getFlowfileName()+"\n");
								i=i+1;
							}
						}
						
					}
				}
			}
			if(0==notNullFlag){
				this.setAllflowFileId("@");
				this.setAllflowFileName(" ");
			}
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}		
		return "success";
	}
	
	//得到allFileName和allFileId
	public String getAllFile(String reId)
	{	
		try{
			this.setAllFileId("@");
			this.setAllFileName("");
			int i=1;
			int notNullFlag=0;
			this.efList=new LinkedList<EventFile>();
			if(null != this.getEventFileDao().findByProperty("reId",reId)){
				this.efList=this.getEventFileDao().findByProperty("reId",reId);
				Iterator it=this.efList.iterator();
				while(it.hasNext()){
					this.ef=(EventFile) it.next();
					if(null != this.getEf().getFileId()){
						notNullFlag=1;
						this.setFileId(this.getEf().getFileId());
						if(null != this.getFileManageDao().findById(this.getFileId())){
							this.setFileManage(this.getFileManageDao().findById(this.getFileId()));
							if("@"==this.getAllFileId()){
								this.setAllFileId("@"+this.getFileId()+"@");
								this.setAllFileName("i"+"、"+this.getFileId()+"，"+this.getFileManage().getFileName()+"\n");
								i=i+1;							
							}else{
								this.setAllFileId(this.getAllFileId()+this.getFileId()+"@");
								this.setAllFileName(this.getAllFileName()+"i"+"、"+this.getFileId()+"，"+this.getFileManage().getFileName()+"\n");
								i=i+1;
							}
						}
						
					}
				}
			}
			if(0==notNullFlag){
				this.setAllFileId("@");
				this.setAllFileName(" ");
			}
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}		
		return "success";
	}
	
	//得到”我院声誉影响”的list，repList
	public void reputationList()
	{
		//得到”我院声誉影响”的list，repList
		Reputation rep1=new Reputation();
		//rep1.setRepId(0);
		rep1.setRepId(-1);
		
		rep1.setRepDetail("--请选择--");
		rep1.setRepSuperior("--请选择--");
		rep1.setRepPartner("--请选择--");
		rep1.setRepPublic("--请选择--");
		rep1.setRepLevel("--请选择--");
		this.repList=new LinkedList<Reputation>();
		this.repList.add(rep1);
		this.repList1=new LinkedList<Reputation>();
		this.repList1=this.getReputationDao().findAll();
		Iterator it=this.repList1.iterator();
		while(it.hasNext()){
			rep1=(Reputation)it.next();
			rep1.setRepDetail(rep1.getRepId().toString()+"、"+rep1.getRepLevel()+":"+rep1.getRepDetail());
			rep1.setRepSuperior(rep1.getRepId().toString()+"、"+rep1.getRepLevel()+":"+rep1.getRepSuperior());
			rep1.setRepPartner(rep1.getRepId().toString()+"、"+rep1.getRepLevel()+":"+rep1.getRepPartner());
			rep1.setRepPublic(rep1.getRepId().toString()+"、"+rep1.getRepLevel()+":"+rep1.getRepPublic());
			this.repList.add(rep1);
		}
	}
	//得到”财务影响”的list，finList
	public void financeList()
	{
		//得到”财务影响”的list，finList
		Finance fin1=new Finance();
		//fin1.setFinId(0);
		fin1.setFinId(-1);
		
		fin1.setFinAsset("--请选择--");
		fin1.setFinIncome("--请选择--");
		fin1.setFinProfit("--请选择--");
		fin1.setFinProperty("--请选择--");
		fin1.setFinDetail("--请选择--");
		this.finList=new LinkedList<Finance>();
		this.finList.add(fin1);
		this.finList1=new LinkedList<Finance>();
		this.finList1=this.getFinanceDao().findAll();
		Iterator it=this.finList1.iterator();
		while(it.hasNext()){
			fin1=(Finance)it.next();
			fin1.setFinAsset(fin1.getFinId().toString()+"、"+fin1.getFinDetail()+":"+fin1.getFinAsset());
			fin1.setFinIncome(fin1.getFinId().toString()+"、"+fin1.getFinDetail()+":"+fin1.getFinIncome());
			fin1.setFinProfit(fin1.getFinId().toString()+"、"+fin1.getFinDetail()+":"+fin1.getFinProfit());
			fin1.setFinProperty(fin1.getFinId().toString()+"、"+fin1.getFinDetail()+":"+fin1.getFinProperty());
			this.finList.add(fin1);
		}
	}
	//得到”发生可能性”的list，proList
	public void probabilityList()
	{
		//得到”发生可能性”的list，proList
		Probability pro1=new Probability();
		//pro1.setProId(0);
		pro1.setProId(-1);
		
		pro1.setProLevel("--请选择--");
		pro1.setProProbability("--请选择--");
		pro1.setProDisasterEvent("--请选择--");
		pro1.setProDailyOperation("--请选择--");
		this.proList=new LinkedList<Probability>();
		this.proList.add(pro1);
		this.proList1=new LinkedList<Probability>();
		this.proList1=this.getProbabilityDao().findAll();
		Iterator it=this.proList1.iterator();
		while(it.hasNext()){
			pro1=(Probability)it.next();
			pro1.setProProbability(pro1.getProId().toString()+"、"+pro1.getProLevel()+":"+pro1.getProProbability());
			pro1.setProDisasterEvent(pro1.getProId().toString()+"、"+pro1.getProLevel()+":"+pro1.getProDisasterEvent());
			pro1.setProDailyOperation(pro1.getProId().toString()+"、"+pro1.getProLevel()+":"+pro1.getProDailyOperation());
			this.proList.add(pro1);
		}
	}
	//得到”人员健康环保影响”的list，safList
	public void safeList()
	{
		//得到”人员健康环保影响”的list，safList
		Safe saf1=new Safe();
		//saf1.setSafId(0);
		saf1.setSafId(-1);
		
		saf1.setSafEnvironment("--请选择--");
		saf1.setSafLevel("--请选择--");
		saf1.setSafSafety("--请选择--");
		this.safList=new LinkedList<Safe>();
		this.safList.add(saf1);
		this.safList1=new LinkedList<Safe>();
		this.safList1=this.getSafeDao().findAll();
		Iterator it=this.safList1.iterator();
		while(it.hasNext()){
			saf1=(Safe)it.next();
			saf1.setSafEnvironment(saf1.getSafId().toString()+"、"+saf1.getSafLevel()+":"+saf1.getSafEnvironment());
			saf1.setSafSafety(saf1.getSafId().toString()+"、"+saf1.getSafLevel()+":"+saf1.getSafSafety());			
			this.safList.add(saf1);
		}
	}
	//得到”对法律法规影响”的list，lawList
	public void lawList()
	{
		//得到”对法律法规影响”的list，lawList
		Law law1=new Law();
		//law1.setLawId(0);	
		law1.setLawId(-1);	
		
		law1.setLawLevel("--请选择--");
		law1.setLawDetail("--请选择--");
		this.lawList=new LinkedList<Law>();
		this.lawList.add(law1);
		this.lawList1=new LinkedList<Law>();
		this.lawList1=this.getLawDao().findAll();
		this.lawList.addAll(lawList1);
	}
	//得到”对客户关系影响”的list，cliList
	public void clientList()
	{
		//得到”对客户关系影响”的list，cliList
		Client cli1=new Client();
		//cli1.setCliId(0);	
		cli1.setCliId(-1);
		
		cli1.setCliLevel("--请选择--");
		cli1.setCliDetail("--请选择--");
		this.cliList=new LinkedList<Client>();
		this.cliList.add(cli1);
		this.cliList1=new LinkedList<Client>();
		this.cliList1=this.getClientDao().findAll();
		this.cliList.addAll(cliList1);
	}
	//得到”员工满意度影响”的list，empList
	public void employeeList()
	{
		//得到”员工满意度影响”的list，empList
		Employee emp1=new Employee();
		//emp1.setEmpId(0);
		emp1.setEmpId(-1);
		
		emp1.setEmpDetail("--请选择--");
		emp1.setEmpLevel("--请选择--");
		this.empList=new LinkedList<Employee>();
		this.empList.add(emp1);
		this.empList1=new LinkedList<Employee>();
		this.empList1=this.getEmployeeDao().findAll();
		this.empList.addAll(empList1);
	}
	//得到”对运营影响”的list，opeList
	public void operationList()
	{
		//得到”对运营影响”的list，opeList
		Operation ope1=new Operation();
		//ope1.setOpeId(0);	
		ope1.setOpeId(-1);	
		
		ope1.setOpeDetail("--请选择--");
		ope1.setOpeLevel("--请选择--");
		this.opeList=new LinkedList<Operation>();
		this.opeList.add(ope1);
		this.opeList1=new LinkedList<Operation>();
		this.opeList1=this.getOperationDao().findAll();
		this.opeList.addAll(opeList1);
	}
}
