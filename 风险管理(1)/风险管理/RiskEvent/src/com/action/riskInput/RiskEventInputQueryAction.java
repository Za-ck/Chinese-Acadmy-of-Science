package com.action.riskInput;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.services.TaskExecutionWebServer;
import java.util.concurrent.Callable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Holder;

import org.apache.struts2.ServletActionContext;
import org.zhongsoft.pendtasks.PendingTaskPortService;
import org.zhongsoft.pendtasks.PendingTaskPortServiceSoap;

import com.action.loginAction;
import com.dao.ClientDAO;
import com.dao.DepartmentDAO;
import com.dao.EmployeeDAO;
import com.dao.EventFileDAO;
import com.dao.EventFlowFileDAO;
import com.dao.FileManageDAO;
import com.dao.FinanceDAO;
import com.dao.LawDAO;
import com.dao.OperationDAO;
import com.dao.ProbabilityDAO;
import com.dao.ReputationDAO;
import com.dao.RiskAssessSituationDAO;
import com.dao.RiskDAO;
import com.dao.RiskDepQueryViewDAO;
import com.dao.RiskEventDAO;
import com.dao.RiskEventDepQueryViewDAO;
import com.dao.RiskImpactDAO;
import com.dao.RiskManageDAO;
import com.dao.RiskRecordDAO;
import com.dao.RiskTypeDAO;
import com.dao.SafeDAO;
import com.dao.UsersDAO;
import com.dao.UsersFunctionDAO;
import com.model.Client;
import com.model.Department;
import com.model.Employee;
import com.model.EventFile;
import com.model.EventFlowFile;
import com.model.FileManage;
import com.model.Finance;
import com.model.FlowFileManage;
import com.model.Law;
import com.model.Operation;
import com.model.Probability;
import com.model.Reputation;
import com.model.Risk;
import com.model.RiskDepQueryView;
import com.model.RiskEvent;
import com.model.RiskEventDepQueryView;
import com.model.RiskImpact;
import com.model.RiskManage;
import com.model.RiskRecord;
import com.model.RiskType;
import com.model.Safe;
import com.model.Users;
import com.services.ToDoWebServiceAction;

public class RiskEventInputQueryAction {
	private String reId;
	private Risk risk;
	private String reType;
	private String reEventId;
	private String reRemark;
	private Timestamp reOccurdate;
	private String reState;
	private String reIndep="";
	private Timestamp reDate;
	private String reCreator;
	private Timestamp reModifydate;
	private String reModifier;
	private String verifyview;
	private int current_pagenum=1;//????????????
    private int  pageNum=10;//??????????????????????????????
    private String actionName="riskInput/RiskEventInputQueryAction";
    private String updownflag="";
    private String updownid="";
    private String orderbys="";
    private String reIdStr="";

	private Set riskImpacts = new HashSet(0);
	private Set riskManages = new HashSet(0);
	
	private List<RiskEventDepQueryView> reList;
	private List<Risk> riskList;
	private List<RiskType> rtList;
	private List<EventFile> efList;
	private List<EventFlowFile> effList;
	private List<Department> alldepList;		
	private List<Department> depList;	
	private List<Risk> allriskList;
	private List<String> idCheck;
	private List<RiskRecord> rrList;
	private List<RiskRecord> rnumList;
	private String updateFlag;
	private String backFlag;
	
	private String rtId;
	private String rtName;
	private String riskId;
	private String riskName;
	private String eventname;
	private String eventid;
	private String indep;
	private String manageDepLeader;
	private String inputleader;
	private String riskRemark;
	
	public  String dateFrom="";
	public  String dateTo="";
	public  String monthbegin;
	public  String monthend;

	private RiskEventDAO riskEventDao;
	private RiskEvent riskEvent=new RiskEvent();
	
	private RiskDAO riskDao;
	
	
	private RiskTypeDAO riskTypeDao;
	private RiskType riskType = new RiskType();
	
	private EventFileDAO eventFileDao;
	private EventFlowFileDAO eventFlowFileDao;
	private EventFile eventFile=new EventFile();
	private EventFlowFile eventFlowFile=new EventFlowFile();
	private FileManageDAO fileManageDao;
	private FileManage fileManage=new FileManage();
	private FlowFileManage flowfileManage=new FlowFileManage();
	
	private DepartmentDAO departmentDao;
	
	private RiskDepQueryViewDAO riskDepQueryViewDao;
	private RiskDepQueryView riskDepQueryView;
	
	private String risktype;
	private String riskname;
	private String riskremark;
	private String money;
	private String standard;
	private String controlFile;
	private String source;
	private String kpi;
	private String finance;
	private String probability;
	private String busarea;
	private String fame;
	private String law;
	private String client;
	private String employee;
	private String operation;
	private String safe;

	private String chance;
	private String strategy;
	private String plandate;
	private String control;
	private String planresource;
	private String reply;
	private String feedback;

	private String depverifier;
	private String depview;
	private String fucstaff;
	private String staffview;
	private String fucverifier;
	private String fucview;
	private String planstaff;
	private String planstaffview;
	private String planverifier;
	private String planview;
	private String president;
	private String presidentview;
	//Out_System_Flag=out,??????????????????????????????????????????????????????Out_System_Flag=in,?????????????????????action
	private String Out_System_Flag="in";
	private String userid="";

	
	private RiskRecord riskRecord;
	private RiskManage riskManage;
	private RiskImpact riskImpact;
	private Finance fin;
	private Probability pro;
	private Reputation rep;
	private Law law1;
	private Client cli;
	private Employee emp;
	private Operation ope;
	private Safe saf;
	
	private RiskImpactDAO riskImpactDao;
	private RiskManageDAO riskManageDao;
	private RiskRecordDAO riskRecordDao;
	private FinanceDAO financeDao;
	private ProbabilityDAO probabilityDao;
	private ReputationDAO reputationDao;
	private LawDAO lawDao;
	private ClientDAO clientDao;
	private EmployeeDAO employeeDao;
	private OperationDAO operationDao;
	private SafeDAO safeDao;
	private UsersDAO usersDao;
	private UsersFunctionDAO functiondao;
	private RiskEventDepQueryViewDAO riskEventDepQueryViewDao;
	private RiskAssessSituationDAO riskassesssituationDao;
	
	public RiskAssessSituationDAO getRiskassesssituationDao() {
		return riskassesssituationDao;
	}
	public void setRiskassesssituationDao(
			RiskAssessSituationDAO riskassesssituationDao) {
		this.riskassesssituationDao = riskassesssituationDao;
	}
	public RiskEventDepQueryViewDAO getRiskEventDepQueryViewDao() {
		return riskEventDepQueryViewDao;
	}
	public void setRiskEventDepQueryViewDao(
			RiskEventDepQueryViewDAO riskEventDepQueryViewDao) {
		this.riskEventDepQueryViewDao = riskEventDepQueryViewDao;
	}
	
	public String getReIdStr() {
		return reIdStr;
	}
	public void setReIdStr(String reIdStr) {
		this.reIdStr = reIdStr;
	}
	public String getReId() {
		return reId;
	}
	public void setReId(String reId) {
		this.reId = reId;
	}
	public Risk getRisk() {
		return risk;
	}
	public void setRisk(Risk risk) {
		this.risk = risk;
	}
	public String getReType() {
		return reType;
	}
	public void setReType(String reType) {
		this.reType = reType;
	}
	public String getReEventId() {
		return reEventId;
	}
	public void setReEventId(String reEventId) {
		this.reEventId = reEventId;
	}
	public String getReRemark() {
		return reRemark;
	}
	public void setReRemark(String reRemark) {
		this.reRemark = reRemark;
	}
	public Timestamp getReOccurdate() {
		return reOccurdate;
	}
	public void setReOccurdate(Timestamp reOccurdate) {
		this.reOccurdate = reOccurdate;
	}
	public String getReState() {
		return reState;
	}
	public void setReState(String reState) {
		this.reState = reState;
	}
	public String getReIndep() {
		return reIndep;
	}
	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}
	public String getManageDepLeader() {
		return manageDepLeader;
	}
	public void setManageDepLeader(String manageDepLeader) {
		this.manageDepLeader = manageDepLeader;
	}
	public String getInputleader() {
		return inputleader;
	}
	public void setInputleader(String inputleader) {
		this.inputleader = inputleader;
	}
	public Timestamp getReDate() {
		return reDate;
	}
	public void setReDate(Timestamp reDate) {
		this.reDate = reDate;
	}
	public String getReCreator() {
		return reCreator;
	}
	public void setReCreator(String reCreator) {
		this.reCreator = reCreator;
	}
	public Timestamp getReModifydate() {
		return reModifydate;
	}
	public void setReModifydate(Timestamp reModifydate) {
		this.reModifydate = reModifydate;
	}
	public String getReModifier() {
		return reModifier;
	}
	public void setReModifier(String reModifier) {
		this.reModifier = reModifier;
	}
	public String getVerifyview() {
		return verifyview;
	}
	public void setVerifyview(String verifyview) {
		this.verifyview = verifyview;
	}
	public Set getRiskImpacts() {
		return riskImpacts;
	}
	public void setRiskImpacts(Set riskImpacts) {
		this.riskImpacts = riskImpacts;
	}
	public Set getRiskManages() {
		return riskManages;
	}
	public void setRiskManages(Set riskManages) {
		this.riskManages = riskManages;
	}
	public List<RiskEventDepQueryView> getReList() {
		return reList;
	}
	public void setReList(List<RiskEventDepQueryView> reList) {
		this.reList = reList;
	}	
	public List<Risk> getRiskList() {
		return riskList;
	}
	public void setRiskList(List<Risk> riskList) {
		this.riskList = riskList;
	}
	public List<RiskType> getRtList() {
		return rtList;
	}
	public void setRtList(List<RiskType> rtList) {
		this.rtList = rtList;
	}
	public List<String> getIdCheck() {
		return idCheck;
	}
	public void setIdCheck(List<String> idCheck) {
		this.idCheck = idCheck;
	}
	public String getUpdateFlag() {
		return updateFlag;
	}
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	public RiskEventDAO getRiskEventDao() {
		return riskEventDao;
	}
	public void setRiskEventDao(RiskEventDAO riskEventDao) {
		this.riskEventDao = riskEventDao;
	}
	public RiskEvent getRiskEvent() {
		return riskEvent;
	}
	public void setRiskEvent(RiskEvent riskEvent) {
		this.riskEvent = riskEvent;
	}	
	public RiskDAO getRiskDao() {
		return riskDao;
	}
	public void setRiskDao(RiskDAO riskDao) {
		this.riskDao = riskDao;
	}
	public RiskTypeDAO getRiskTypeDao() {
		return riskTypeDao;
	}
	public void setRiskTypeDao(RiskTypeDAO riskTypeDao) {
		this.riskTypeDao = riskTypeDao;
	}
	public RiskType getRiskType() {
		return riskType;
	}
	public void setRiskType(RiskType riskType) {
		this.riskType = riskType;
	}	
	public String getRtId() {
		return rtId;
	}
	public void setRtId(String rtId) {
		this.rtId = rtId;
	}
	public String getRtName() {
		return rtName;
	}
	public void setRtName(String rtName) {
		this.rtName = rtName;
	}
	public String getRiskId() {
		return riskId;
	}
	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	public String getIndep() {
		return indep;
	}
	public void setIndep(String indep) {
		this.indep = indep;
	}
	public String getRiskRemark() {
		return riskRemark;
	}
	public void setRiskRemark(String riskRemark) {
		this.riskRemark = riskRemark;
	}	
	public String getMonthbegin() {
		return monthbegin;
	}
	public void setMonthbegin(String monthbegin) {
		this.monthbegin = monthbegin;
	}
	public String getMonthend() {
		return monthend;
	}
	public void setMonthend(String monthend) {
		this.monthend = monthend;
	}		
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
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
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getControlFile() {
		return controlFile;
	}
	public void setControlFile(String controlFile) {
		this.controlFile = controlFile;
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
	public String getFinance() {
		return finance;
	}
	public void setFinance(String finance) {
		this.finance = finance;
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
	public String getFame() {
		return fame;
	}
	public void setFame(String fame) {
		this.fame = fame;
	}
	public String getLaw() {
		return law;
	}
	public void setLaw(String law) {
		this.law = law;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getSafe() {
		return safe;
	}
	public void setSafe(String safe) {
		this.safe = safe;
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
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getDepverifier() {
		return depverifier;
	}
	public void setDepverifier(String depverifier) {
		this.depverifier = depverifier;
	}
	public String getDepview() {
		return depview;
	}
	public void setDepview(String depview) {
		this.depview = depview;
	}
	public String getFucstaff() {
		return fucstaff;
	}
	public void setFucstaff(String fucstaff) {
		this.fucstaff = fucstaff;
	}
	public String getStaffview() {
		return staffview;
	}
	public void setStaffview(String staffview) {
		this.staffview = staffview;
	}
	public String getFucverifier() {
		return fucverifier;
	}
	public void setFucverifier(String fucverifier) {
		this.fucverifier = fucverifier;
	}
	public String getFucview() {
		return fucview;
	}
	public void setFucview(String fucview) {
		this.fucview = fucview;
	}
	public String getPlanstaff() {
		return planstaff;
	}
	public void setPlanstaff(String planstaff) {
		this.planstaff = planstaff;
	}
	public String getPlanstaffview() {
		return planstaffview;
	}
	public void setPlanstaffview(String planstaffview) {
		this.planstaffview = planstaffview;
	}
	public String getPlanverifier() {
		return planverifier;
	}
	public void setPlanverifier(String planverifier) {
		this.planverifier = planverifier;
	}
	public String getPlanview() {
		return planview;
	}
	public void setPlanview(String planview) {
		this.planview = planview;
	}
	public String getPresident() {
		return president;
	}
	public void setPresident(String president) {
		this.president = president;
	}
	public String getPresidentview() {
		return presidentview;
	}
	public void setPresidentview(String presidentview) {
		this.presidentview = presidentview;
	}
	public RiskImpactDAO getRiskImpactDao() {
		return riskImpactDao;
	}
	public void setRiskImpactDao(RiskImpactDAO riskImpactDao) {
		this.riskImpactDao = riskImpactDao;
	}
	public RiskManageDAO getRiskManageDao() {
		return riskManageDao;
	}
	public void setRiskManageDao(RiskManageDAO riskManageDao) {
		this.riskManageDao = riskManageDao;
	}
	
	public String getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}
	
	public RiskRecord getRiskRecord() {
		return riskRecord;
	}
	public void setRiskRecord(RiskRecord riskRecord) {
		this.riskRecord = riskRecord;
	}
	public RiskRecordDAO getRiskRecordDao() {
		return riskRecordDao;
	}
	public void setRiskRecordDao(RiskRecordDAO riskRecordDao) {
		this.riskRecordDao = riskRecordDao;
	}
	public RiskManage getRiskManage() {
		return riskManage;
	}
	public void setRiskManage(RiskManage riskManage) {
		this.riskManage = riskManage;
	}
	public RiskImpact getRiskImpact() {
		return riskImpact;
	}
	public void setRiskImpact(RiskImpact riskImpact) {
		this.riskImpact = riskImpact;
	}	
	public Finance getFin() {
		return fin;
	}
	public void setFin(Finance fin) {
		this.fin = fin;
	}
	public Probability getPro() {
		return pro;
	}
	public void setPro(Probability pro) {
		this.pro = pro;
	}
	public Reputation getRep() {
		return rep;
	}
	public void setRep(Reputation rep) {
		this.rep = rep;
	}
	public Law getLaw1() {
		return law1;
	}
	public void setLaw1(Law law1) {
		this.law1 = law1;
	}
	public Client getCli() {
		return cli;
	}
	public void setCli(Client cli) {
		this.cli = cli;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Operation getOpe() {
		return ope;
	}
	public void setOpe(Operation ope) {
		this.ope = ope;
	}
	public Safe getSaf() {
		return saf;
	}
	public void setSaf(Safe saf) {
		this.saf = saf;
	}
	public FinanceDAO getFinanceDao() {
		return financeDao;
	}
	public void setFinanceDao(FinanceDAO financeDao) {
		this.financeDao = financeDao;
	}
	public ProbabilityDAO getProbabilityDao() {
		return probabilityDao;
	}
	public void setProbabilityDao(ProbabilityDAO probabilityDao) {
		this.probabilityDao = probabilityDao;
	}
	public ReputationDAO getReputationDao() {
		return reputationDao;
	}
	public void setReputationDao(ReputationDAO reputationDao) {
		this.reputationDao = reputationDao;
	}
	public LawDAO getLawDao() {
		return lawDao;
	}
	public void setLawDao(LawDAO lawDao) {
		this.lawDao = lawDao;
	}
	public ClientDAO getClientDao() {
		return clientDao;
	}
	public void setClientDao(ClientDAO clientDao) {
		this.clientDao = clientDao;
	}
	public EmployeeDAO getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}
	public OperationDAO getOperationDao() {
		return operationDao;
	}
	public void setOperationDao(OperationDAO operationDao) {
		this.operationDao = operationDao;
	}
	public SafeDAO getSafeDao() {
		return safeDao;
	}
	public void setSafeDao(SafeDAO safeDao) {
		this.safeDao = safeDao;
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
	public FileManage getFileManage() {
		return fileManage;
	}
	public void setFileManage(FileManage fileManage) {
		this.fileManage = fileManage;
	}	
	public FlowFileManage getFlowfileManage() {
		return flowfileManage;
	}
	public void setFlowfileManage(FlowFileManage flowfileManage) {
		this.flowfileManage = flowfileManage;
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
	public FileManageDAO getFileManageDao() {
		return fileManageDao;
	}
	public void setFileManageDao(FileManageDAO fileManageDao) {
		this.fileManageDao = fileManageDao;
	}	
	public EventFile getEventFile() {
		return eventFile;
	}
	public void setEventFile(EventFile eventFile) {
		this.eventFile = eventFile;
	}
	
	public EventFlowFile getEventFlowFile() {
		return eventFlowFile;
	}
	public void setEventFlowFile(EventFlowFile eventFlowFile) {
		this.eventFlowFile = eventFlowFile;
	}
	public List<RiskRecord> getRrList() {
		return rrList;
	}
	public void setRrList(List<RiskRecord> rrList) {
		this.rrList = rrList;
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
	public List<Department> getAlldepList() {
		return alldepList;
	}
	public void setAlldepList(List<Department> alldepList) {
		this.alldepList = alldepList;
	}
	public List<Department> getDepList() {
		return depList;
	}
	public void setDepList(List<Department> depList) {
		this.depList = depList;
	}	
	public List<Risk> getAllriskList() {
		return allriskList;
	}
	public void setAllriskList(List<Risk> allriskList) {
		this.allriskList = allriskList;
	}
	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getOrderbys() {
		return orderbys;
	}
	public void setOrderbys(String orderbys) {
		this.orderbys = orderbys;
	}	
	public String getUpdownflag() {
		return updownflag;
	}
	public void setUpdownflag(String updownflag) {
		this.updownflag = updownflag;
	}
	public String getUpdownid() {
		return updownid;
	}
	public void setUpdownid(String updownid) {
		this.updownid = updownid;
	}	
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	
	public String getOut_System_Flag() {
		return Out_System_Flag;
	}
	public void setOut_System_Flag(String outSystemFlag) {
		Out_System_Flag = outSystemFlag;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public UsersDAO getUsersDao() {
		return usersDao;
	}
	public void setUsersDao(UsersDAO usersDao) {
		this.usersDao = usersDao;
	}
	public UsersFunctionDAO getFunctiondao() {
		return functiondao;
	}
	public void setFunctiondao(UsersFunctionDAO functiondao) {
		this.functiondao = functiondao;
	}
	public List<RiskRecord> getRnumList() {
		return rnumList;
	}
	public void setRnumList(List<RiskRecord> rnumList) {
		this.rnumList = rnumList;
	}
	public RiskDepQueryViewDAO getRiskDepQueryViewDao() {
		return riskDepQueryViewDao;
	}
	public void setRiskDepQueryViewDao(RiskDepQueryViewDAO riskDepQueryViewDao) {
		this.riskDepQueryViewDao = riskDepQueryViewDao;
	}
	public RiskDepQueryView getRiskDepQueryView() {
		return riskDepQueryView;
	}
	public void setRiskDepQueryView(RiskDepQueryView riskDepQueryView) {
		this.riskDepQueryView = riskDepQueryView;
	}
	
	// ????????????????????????,????????????reList,?????????RiskEventInputQuery.jsp???????????????
	@SuppressWarnings("unchecked")
	public String reiqManage() 
	{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Users us = (Users)session.getAttribute("loginUser");
//		Department department=new Department();
//		department=us.getDepartment();
		// ???????????????????????????????????????alldepList????????????????????????allriskList
		getDropDownList();
		this.reList = new LinkedList<RiskEventDepQueryView>();
		this.reList = this.getRiskEventDepQueryViewDao().findAll((current_pagenum-1)*pageNum,pageNum,us.getUserDep(),this.getOrderbys());
		ServletActionContext.getRequest().setAttribute("reList", reList);
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
		this.setActionName("riskInput/RiskEventInputQueryAction");		
		if(null == reList) 
			return "fail";
		else
			return "success";
	}
	 /*
	  * ????????????????????????????????????????????? 
	  * ????????????????????????,????????????reList,?????????RiskEventInputQuery.jsp???????????????
	  */
	@SuppressWarnings("unchecked")
	public String Out_reiqManage() 
	{
		try
		{
			//?????????????????????????????????????????????
			loginAction loginAct=new loginAction();
			//????????????,????????????userid
			loginAct.setUserid(this.getUserid());
			loginAct.setUserdao(this.getUsersDao());
			loginAct.setFunctiondao(this.getFunctiondao());
			loginAct.setRiskEventDao(this.getRiskEventDao());
			String result=loginAct.loginSingleSystem();
			if(result.equals("fail"))
			{
				return "Out_Fail";
			}
				
			HttpServletRequest request = ServletActionContext.getRequest(); 
			HttpSession session = request.getSession();
			Users us = (Users)session.getAttribute("loginUser");
//			Department department=new Department();
//			department=us.getDepartment();
			// ???????????????????????????????????????alldepList????????????????????????allriskList
			getDropDownList();
			this.reList = new LinkedList<RiskEventDepQueryView>();
			this.reList = this.getRiskEventDepQueryViewDao().findAll((current_pagenum-1)*pageNum,pageNum,us.getUserDep(),this.getOrderbys());
			ServletActionContext.getRequest().setAttribute("reList", reList);
			ServletActionContext.getRequest().getSession().setAttribute("Out_reList",this.reList);
			ServletActionContext.getRequest().getSession().setAttribute("Out_Jsp","/RiskEvent/RiskInput/Out_RiskEventInputQuery.jsp");
			ServletActionContext.getRequest().getSession().setAttribute("Out_alldepList",this.getAlldepList());
			ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
			this.setActionName("riskInput/RiskEventInputQueryAction");
		
		}catch(Exception e){
			//e.printStackTrace();
			return "Out_Fail";
		}
		
		return "Out_Success";
	}
	
	/*
	 * ????????????
	 */
	public String reiqMultiDel() {
		try {
			for (int j = 0; j < this.idCheck.size(); j++) {
				//System.out.println("-------------------------" + this.idCheck.get(j) + "---------------------------");
				// ??????ZL-0006-2013-0003*?????????*0
				String[] checkinfo = this.idCheck.get(j).split("\\*");
				//RiskEvent riskevent = new RiskEvent();
				//riskevent = this.getRiskEventDao().findById(this.idCheck.get(j));
				// ?????????????????????????????????????????????
				if (checkinfo.length < 3 || !"0".equals(checkinfo[2])) {
					return "notallowed";
				}
				// ??????????????????????????????
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				Users us = (Users) session.getAttribute("loginUser");
				if (!us.getUserName().equals(checkinfo[1])) {
					return "notallowedother";
				}
			}
			//???????????????????????????????????????
			for (int i = 0; i < this.idCheck.size(); i++) {
				// ?????????????????????????????????
				// deleteTasks(this.idCheck.get(i));
				/*
				 * ??????????????????????????????????????????Tomcat???????????????
				 */
				inThreads("delete", this.idCheck.get(i));
			}
			
			for (int i = 0; i < this.idCheck.size(); i++) 
			{
				this.setReId(this.idCheck.get(i).split("\\*")[0]);
				riskEvent = this.getRiskEventDao().findById(this.getReId());
				if(null == riskEvent) continue;
				this.getRiskEventDao().delete(riskEvent);
//				if (null != this.getEventFileDao().findByProperty("reId",this.getReId())) {
//					Iterator it = this.getEventFileDao().findByProperty("reId",this.getReId()).iterator();
//					while (it.hasNext()) {
//						this.getEventFileDao().delete((EventFile) it.next());
//					}
//				}
//				if (null != this.getEventFlowFileDao().findByProperty("reId",this.getReId())) {
//					Iterator it = this.getEventFlowFileDao().findByProperty("reId",this.getReId()).iterator();
//					while (it.hasNext()) {
//						this.getEventFlowFileDao().delete((EventFlowFile) it.next());
//					}
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		reiqQuery();// ????????????reList,?????????RiskEventInputQuery.jsp???????????????
		return "success";
	}
	
	
	/*
	 * ??????????????????????????????????????????Tomcat???????????????
	 */
	public String inThreads(String taskFlag, final String Out_reId) {
		try {
			Callable<String> delete = new Callable<String>() {
				public String call() {
					return deleteTasks(Out_reId);
				}
			};
			TaskExecutionWebServer tews = TaskExecutionWebServer.getInstance();
			if (taskFlag.equals("delete")) {
				tews.submit(delete);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	/*
	 * ???????????????????????????????????????????????????????????????????????????  ?????????deleteTasks?????????
	 * ??????????????????updateTasks????????????deleteTasks?????????????????????
	 * updateTasks???????????????Id?????????@@@???
	 */
	public String deleteTasks(String Out_reId) {
		try {
			/*
			 * ?????????????????????????????????????????????????????????WebService??????????????????????????????
			 */
			// ????????????????????????
			String TaskId = Out_reId;// reId
			String TaskApp = "RiskEvent";// ????????????????????????
			String TaskUrl = "";
			String TaskTitle = "????????????????????????" + Out_reId + "???";
			String LogonName = "@@@";// ?????????Id
			String UserName = "";// ???????????????
			String OrgName = "";// ?????????????????????
			String ReceiveTime = "";// ????????????
			String CreatorName = "";// ???????????????
			String CreatorOrgName = "";// ?????????????????????
			String PreUserName = "";// ????????????
			String PreOrgName = "";// ??????????????????
			// ??????????????????xml????????????
			String xml = "";
			ToDoWebServiceAction toDoWebServiceAction = new ToDoWebServiceAction();
			xml = toDoWebServiceAction.getXml(TaskId, TaskApp, TaskUrl,
					TaskTitle, LogonName, UserName, OrgName, ReceiveTime,
					CreatorName, CreatorOrgName, PreUserName, PreOrgName);
			// ??????myeclipse???????????????WebService?????????????????????
			PendingTaskPortService service = new PendingTaskPortService();
			PendingTaskPortServiceSoap soap = service
					.getPendingTaskPortServiceSoap();
			Holder<Boolean> updateTasksResult = new Holder<Boolean>();// ??????C++??????
			Holder<String> errorMsg = new Holder<String>();// ??????C++??????
			soap.updateTasks(xml, updateTasksResult, errorMsg);
			if (true == updateTasksResult.value) {
				System.out.println("?????????????????????" + errorMsg.value + "\n");
			} else {
				System.out.println("?????????????????????" + errorMsg.value + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	// ??????
	public String reiqDelete() {
		try{
			riskEvent=this.getRiskEventDao().findById(this.getReId());
			this.getRiskEventDao().delete(riskEvent);
//			if(null != this.getEventFileDao().findByProperty("reId", this.getReId())){
//				Iterator it=this.getEventFileDao().findByProperty("reId", this.getReId()).iterator();
//				while(it.hasNext()){
//					this.getEventFileDao().delete((EventFile)it.next());
//				}
//			}
//			if(null != this.getEventFlowFileDao().findByProperty("reId", this.getReId())){
//				Iterator it=this.getEventFlowFileDao().findByProperty("reId", this.getReId()).iterator();
//				while(it.hasNext()){
//					this.getEventFlowFileDao().delete((EventFlowFile)it.next());
//				}
//			}
			reiqQuery();// ????????????reList,?????????RiskEventInputQuery.jsp???????????????
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	//??????ajax??????????????????
	public void ajaxReiqDelete() {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			String reId = request.getParameter("reId");
			RiskEvent riskevent = riskEventDao.findById(reId);
			riskEventDao.delete(riskevent);
			inThreads("delete",reId);				//????????????
			
//			//??????RiskAssessSituation???
//			RiskManage rm = this.getRiskManageDao().findById(reId);
//			String plandate = rm.getRmPlandate();
//			String planyear=plandate.substring(0, 4);
//			String planmonth=plandate.substring(5, 7);
//			String planquarter=month2Quarter(planmonth)+"";
//			
//			List checkDeal = this.getRiskassesssituationDao().checkDeal(planyear,planquarter,riskevent.getReIndep());
//			
//			if(checkDeal != null && checkDeal.size() > 0) {
//				
//				RiskAssessSituation rau = (RiskAssessSituation) checkDeal.get(0);
//				int NumberNeed=rau.getRaNumberNeed();
//				if(NumberNeed > 1) {
//					rau.setRaNumberNeed(NumberNeed - 1);
//					this.getRiskassesssituationDao().merge(rau);
//				}
//			}
			
			out.print("success");
			out.flush();
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	private int month2Quarter(String month) {
		
		try {
			int monthInt = Integer.parseInt(month);
			int quarter = (monthInt - 1) / 3;
			return (quarter + 1);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}
	
	// ??????
	@SuppressWarnings("unchecked")
	public String reiqRead()
	{
		try{
			String s="";
			int i=1;
			//riskEvent=this.getRiskEventDao().findById(this.reId);
			riskImpact=this.getRiskImpactDao().findById(this.reId);
			riskManage=this.getRiskManageDao().findById(this.reId);
			riskDepQueryView = this.getRiskDepQueryViewDao().findById(this.reId);
			
			rrList=this.getRiskRecordDao().findAllRecord(this.reId);
			this.efList = new LinkedList<EventFile>();
			this.efList=this.getEventFileDao().findByProperty("reId", this.reId);
			this.effList = new LinkedList<EventFlowFile>();
			this.effList=this.getEventFlowFileDao().findByProperty("reId", this.reId);
			this.riskId = riskDepQueryView.getReId();
			//this.riskId=riskEvent.getReId();
		    //this.risktype=riskEvent.getRisk().getRiskType().getRtName();
			this.risktype = riskDepQueryView.getRtName();
		    this.riskname = riskDepQueryView.getRiskName();
		    this.eventname=riskDepQueryView.getReEventname();
			//this.riskremark=riskEvent.getReRemark();
		    this.riskremark=riskDepQueryView.getReDetail();
			this.indep=riskDepQueryView.getDepName();			
			
			this.manageDepLeader = "";
			List leaderList = this.getEventFlowFileDao().findMLeader(this.reId);
			if(leaderList.size()>0){
				String Leader ="";
				for(int j=0; j<leaderList.size(); j++){
					 String getOne = (String)leaderList.get(j)+" ";
					 Leader = Leader + getOne;
					}
				this.manageDepLeader = Leader;
			}
			
			this.inputleader = "";
			List inputleaderList = this.getEventFlowFileDao().findILeader(this.reId);
			if(inputleaderList.size()>0){
				String iLeader ="";
				for(int j=0; j<inputleaderList.size(); j++){
					 String getOne = (String)inputleaderList.get(j)+" ";
					 iLeader = iLeader + getOne;
					}
				this.inputleader = iLeader;
			}
						
			this.kpi=riskImpact.getRiKpi();			
			this.busarea=riskImpact.getRiBusarea();
			this.source=riskImpact.getRiSource();
			//this.standard=riskImpact.getRiFlow();
			i=0;
			s="";
			s="??????????????????????????????\n";
			Iterator it=this.getEfList().iterator();
			while(it.hasNext()){
				eventFile=(EventFile)it.next();
				if(null==eventFile.getFileId())
				{
					break;
				}
				fileManage=this.getFileManageDao().findById(eventFile.getFileId());
				if(null==fileManage)
				{
					break;
				}
				i=i+1;
				s=s+i+"?????????????????????"+fileManage.getFileId()+"????????????????????????"+fileManage.getFileName()+"??????\n";
			    
			}
			s=s+"???"+i+"????????????\n";
			this.standard=s;
			
			//??????????????????
			Iterator itf=this.getEffList().iterator();
			i=0;s="";
			while(itf.hasNext()){
				eventFlowFile=(EventFlowFile)itf.next();
				if(null==eventFlowFile.getFlowfileId())
				{
					break;
				}
				flowfileManage = this.getFileManageDao().findFlowById(eventFlowFile.getFlowfileId());
				if(null==flowfileManage)
				{
					break;
				}
				i=i+1;
				s=s+i+"?????????????????????"+flowfileManage.getFlowfileId()+"????????????????????????"+flowfileManage.getFlowfileName()+"??????\n";
			    
			}
			s=s+"???"+i+"????????????\n";			
			this.controlFile=s;

			this.probability=riskImpact.getRiProbability();

			this.finance=riskImpact.getRiFincriteria()+"\n"+"???????????????"+riskImpact.getRiFinance();

			this.fame=riskImpact.getRiFamecriteria()+"\n"+"???????????????"+riskImpact.getRiFame();

			this.law=riskImpact.getRiLawcriteria()+"\n"+"???????????????"+riskImpact.getRiLaw();

			this.client=riskImpact.getRiClicriteria()+"\n"+"???????????????"+riskImpact.getRiClient();

			this.employee=riskImpact.getRiEmpcriteria()+"\n"+"???????????????"+riskImpact.getRiEmployee();

			this.operation=riskImpact.getRiOpecriteria()+"\n"+"???????????????"+riskImpact.getRiOperation();
			
			this.safe=riskImpact.getRiSafecriteria()+"\n"+"???????????????"+riskImpact.getRiSafe();
			
			this.money=riskImpact.getRiMoney().toString();
			this.chance=riskManage.getRmChance();
			this.control=riskManage.getRmControl();
			this.reply=riskManage.getRmReply();
			this.strategy=riskManage.getRmStrategy();
			this.planresource=riskManage.getRmPlanres();
			this.plandate=riskManage.getRmPlandate();
			
			String view="";
			//int num=0;
			for(int p=0;p<this.rrList.size();p++)
			{
				//num=0;
				//rnumList=this.getRiskRecordDao().findRecordNum(this.reId,this.rrList.get(p).getUsers().getUserId(),this.rrList.get(p).getRrTime());
				//if(null == rnumList)return 
				//num = rnumList.size()+1;
				view=view+"????????????"+this.getUsersDao().findById(rrList.get(p).getRrVerifier()).getUserName()+"   ???????????????"+this.rrList.get(p).getRrTime()+"\n";
				//view=view+"???"+num+"??????????????????"+this.rrList.get(p).getRrView()+"\n"+"\n";
				view=view+"???????????????"+this.rrList.get(p).getRrView()+"\n"+"\n";
			}
			this.verifyview=view;
		}
		catch(Exception e){
			e.printStackTrace();
			if(this.getBackFlag().equals("query"))
				return "query";
			else
				return "inputQuery";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String backflag = request.getParameter("backFlag");
		if(backflag != null && "reply".equals(backflag))
			return "reply";
		else
			return "success";
	}

	// ??????????????????????????????????????????
	public String reiqUpdatePrepare() {
		try{
			riskDepQueryView = this.getRiskDepQueryViewDao().findById(this.getReId());
			this.rtId = riskDepQueryView.getRiskType();
			this.rtName = riskDepQueryView.getRtName();
			this.riskId = riskDepQueryView.getReRiskId();
			this.riskName = riskDepQueryView.getRiskName();
			this.eventid = riskDepQueryView.getReEventId();
			this.indep = riskDepQueryView.getDepName();
			this.riskRemark = riskDepQueryView.getReRemark();
			
//			riskEvent=this.getRiskEventDao().findById(this.getReId());			
//			riskType=this.getRisk().getRiskType();
//			this.rtId=riskType.getRtId();
//			this.rtName=riskType.getRtName();
			this.rtList = new LinkedList<RiskType>();
			this.rtList=this.getRiskTypeDao().findAll();
//			this.riskId=this.getRisk().getRiskId();
//			this.riskName=this.getRisk().getRiskName();
//			/*this.riskList = new LinkedList<Risk>();
//			this.riskList=this.getRiskDao().findAll();*/
//			this.eventid=riskEvent.getReEventId();
//			//this.indep=riskEvent.getDepartment().getDepName();
//			this.indep = this.getDepartmentDao().findById(riskEvent.getReIndep()).getDepName();
//			this.riskRemark=riskEvent.getReRemark();		
		}
		catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	// ???????????????????????????????????????alldepList????????????????????????alldepList
	public String getDropDownList() {
		try{
			//?????????????????????alldepList
			Department dep1=new Department();
			dep1.setDepId("none1");
			dep1.setDepName("--?????????--");
			dep1.setDepSort(0);
			this.alldepList=new LinkedList<Department>();
			this.alldepList.add(dep1);
			this.depList=new LinkedList<Department>();
			this.depList=this.getDepartmentDao().findAll();
			this.alldepList.addAll(this.depList);	
			//?????????????????????allriskList
			RiskType rt1=new RiskType();
			rt1.setRtId("none1");
			rt1.setRtName("--?????????--");
			rt1.setRtRemark("--?????????--");
			Risk r1=new Risk();
			r1.setRiskId("none1");
			r1.setRiskName("--?????????--");
			r1.setRiskRemark("--?????????--");
			r1.setRiskDep("none1");
			r1.setRiskType(rt1.getRtId());
			this.allriskList=new LinkedList<Risk>();
			this.allriskList.add(r1);
			this.riskList=new LinkedList<Risk>();
			this.riskList=this.getRiskDao().findAll();
			this.allriskList.addAll(this.riskList);
		}
		catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	//???????????????????????????????????????
	@SuppressWarnings("static-access")
	public void GetMonth_BEG_END_Day() {
		Calendar cal = Calendar.getInstance();
		// ?????????1???????????????
		cal.add(cal.MONTH, 1);
		// ????????????1????????????????????????
		cal.set(cal.DATE, 1);
		// ?????????1?????????????????????????????????????????????
		cal.add(cal.DATE, -1);
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		this.setMonthend(df.format(cal.getTime()));
		Calendar c = Calendar.getInstance();
		c.set(c.DATE, 1);
		this.setMonthbegin(df.format(c.getTime()));
		//System.out.println("???????????????>>" + monthbegin);
		//System.out.println("??????????????????>>" + monthend);
	}
	//???????????????????????????????????????????????????reList????????????RiskEventInputQuery.jsp???????????????
	public String reiqShowCurrentMonthInfo(){
		try{
			GetMonth_BEG_END_Day();
			this.reList = new LinkedList<RiskEventDepQueryView>();
			this.reList = this.getRiskEventDepQueryViewDao().findByTimeBetween(this.getMonthbegin(), this.getMonthend());
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	//???????????????????????????:?????????dateFrom????????????dateTo???????????????riskId???????????????reIndep
	@SuppressWarnings("unchecked")
	public String reiqQuery(){
		try{
			HttpServletRequest request = ServletActionContext.getRequest(); 
			HttpSession session = request.getSession();
			Users us = (Users)session.getAttribute("loginUser");
//			Department department1=new Department();
//			department1=us.getDepartment();
			// ???????????????????????????????????????alldepList????????????????????????allriskList
			getDropDownList();			
			//???????????????????????????reList
			String datefromtem=transformDateFrom(this.getDateFrom());//???dateFrom???????????????????????????,?????????"yyyy-MM-dd 00:00:00"??????
			String datetotem=transformDateTo(this.getDateTo());//???dateTo???????????????????????????,?????????"yyyy-MM-dd 23:59:59"??????
			
			this.reList = new LinkedList<RiskEventDepQueryView>();
			this.setRiskId("none1");
			this.reList = this.getRiskEventDepQueryViewDao().findByQueryCondition(datefromtem, datetotem, this.getRiskId(), this.getReIndep(), this.getReIdStr(),(current_pagenum-1)*pageNum,pageNum,us.getUserDep(),this.getOrderbys());
			ServletActionContext.getRequest().setAttribute("reList", reList);
			ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
			this.setActionName("riskInput/REIQQueryAction");
			
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	//???dateFrom???????????????????????????,?????????"yyyy-MM-dd HH:mm:ss"??????
	public void transformDateFrom(){
		if(this.getDateFrom().equals("")){
			this.setDateFrom("1900-01-01 00:00:00");
		}
		else{
			this.setDateFrom(this.getDateFrom()+" 00:00:00");
		}
	}
	//???dateTo???????????????????????????,?????????"yyyy-MM-dd HH:mm:ss"??????
	public void transformDateTo(){
		if(this.getDateTo().equals("")){
			Date d=new Date();
			DateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.setDateTo(f.format(d));
		}
		else{
			this.setDateTo(this.getDateTo()+" 23:59:59");
		}		
	}
	//???dateFrom???????????????????????????,?????????"yyyy-MM-dd HH:mm:ss"??????
	public String transformDateFrom(String temfrom){
		if(temfrom == null || temfrom.equals("")){
			temfrom="1900-01-01 00:00:00";
		}
		else{
			temfrom=temfrom+" 00:00:00";
		}
		return temfrom;
	}
	//???dateTo???????????????????????????,?????????"yyyy-MM-dd HH:mm:ss"??????
	public String transformDateTo(String temto){
		if(temto == null || temto.equals("")){
			Date d=new Date();
			DateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			temto=f.format(d);
		}
		else{
			temto=temto+" 23:59:59";
		}
		return temto;
	}
	
}
