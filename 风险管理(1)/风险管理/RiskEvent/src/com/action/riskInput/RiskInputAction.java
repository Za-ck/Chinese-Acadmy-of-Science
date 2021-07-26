package com.action.riskInput;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
import com.dao.RiskAssessViewDAO;
import com.dao.RiskDepQueryViewDAO;
import com.dao.RiskEventDAO;
import com.dao.RiskImpactDAO;
import com.dao.RiskManageDAO;
import com.dao.RiskTypeDAO;
import com.dao.SafeDAO;
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
import com.model.RiskDepQueryView;
import com.model.RiskEvent;
import com.model.RiskImpact;
import com.model.RiskManage;
import com.model.RiskType;
import com.model.Safe;
import com.model.Users;
import com.dao.RiskDAO;
import com.model.Risk;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class RiskInputAction {

	private String risktype;
	private String riskname;
	private String eventname;
	private String eventid;
	private String riskremark;
	private String indep;
	private String statusId;
	private String updateFlag = "";
	private String rtName;
	private String rtId;
	private String selecrtId;
	private String selecrtName;
	private String fileId;
	private String fileName;
	private String flowfileId;
	private String flowfileName;
	private List<Risk> riskList;
	private List<RiskType> rtList;
	private List<RiskType> allrtList;
	private List<EventFile> efList;
	private List<EventFlowFile> effList;
	private List<Department> depList;
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

	private RiskTypeDAO riskTypeDao;
	private RiskDAO riskDao;
	private Risk risk;
	private RiskEventDAO riskeventDao;
	private RiskImpactDAO riskimpactDao;
	private RiskManageDAO riskmanageDao;
	private EventFileDAO eventFileDao;
	private EventFlowFileDAO eventFlowFileDao;
	private FileManageDAO fileManageDao;
	private DepartmentDAO departmentDao;
	private ReputationDAO reputationDao;
	private ProbabilityDAO probabilityDao;
	private FinanceDAO financeDao;
	private SafeDAO safeDao;
	private LawDAO lawDao;
	private ClientDAO clientDao;
	private EmployeeDAO employeeDao;
	private OperationDAO operationDao;
	private RiskDepQueryViewDAO riskDepQueryViewDao;
	private RiskAssessViewDAO riskAssessViewDao;

	private String id;
	private String depname;
	private String money;
	private String standard;
	private String source;
	private String kpi;
	private String financedegree;
	private String finance;
	private String prodegree;
	private String probability;
	private String busarea;
	private String famedegree;
	private String fame;
	private String lawdegree;
	private String law;
	private String clientdegree;
	private String client;
	private String employeedegree;
	private String employee;
	private String operationdegree;
	private String operation;
	private String safedegree;
	private String safe;
	RiskEvent riskevent = new RiskEvent();
	RiskImpact riskimpact = new RiskImpact();
	RiskManage riskmanage = new RiskManage();
	EventFile ef = new EventFile();
	EventFlowFile eff = new EventFlowFile();
	FileManage fileManage = new FileManage();
	FlowFileManage flowfileManage = new FlowFileManage();
	private String riskeventid;
	private String idimpact;
	private String allFileId = "@";
	private String allFileName = " ";
	private String allflowFileId = "@";
	private String allflowFileName = " ";

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
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

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
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

	public RiskEventDAO getRiskeventDao() {
		return riskeventDao;
	}

	public void setRiskeventDao(RiskEventDAO riskeventDao) {
		this.riskeventDao = riskeventDao;
	}

	public RiskEvent getRiskevent() {
		return riskevent;
	}

	public void setRiskevent(RiskEvent riskevent) {
		this.riskevent = riskevent;
	}

	public RiskDAO getRiskDao() {
		return riskDao;
	}

	public void setRiskDao(RiskDAO riskDao) {
		this.riskDao = riskDao;
	}

	public List<Risk> getRiskList() {
		return riskList;
	}

	public void setRiskList(List<Risk> riskList) {
		this.riskList = riskList;
	}

	public String getIdimpact() {
		return idimpact;
	}

	public void setIdimpact(String idimpact) {
		this.idimpact = idimpact;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}

	public List<RiskType> getRtList() {
		return rtList;
	}

	public void setRtList(List<RiskType> rtList) {
		this.rtList = rtList;
	}

	public String getRtName() {
		return rtName;
	}

	public void setRtName(String rtName) {
		this.rtName = rtName;
	}

	public String getRtId() {
		return rtId;
	}

	public void setRtId(String rtId) {
		this.rtId = rtId;
	}

	public String getSelecrtId() {
		return selecrtId;
	}

	public void setSelecrtId(String selecrtId) {
		this.selecrtId = selecrtId;
	}

	public String getSelecrtName() {
		return selecrtName;
	}

	public void setSelecrtName(String selecrtName) {
		this.selecrtName = selecrtName;
	}

	public RiskManageDAO getRiskmanageDao() {
		return riskmanageDao;
	}

	public void setRiskmanageDao(RiskManageDAO riskmanageDao) {
		this.riskmanageDao = riskmanageDao;
	}

	public RiskImpact getRiskimpact() {
		return riskimpact;
	}

	public void setRiskimpact(RiskImpact riskimpact) {
		this.riskimpact = riskimpact;
	}

	public RiskManage getRiskmanage() {
		return riskmanage;
	}

	public RiskImpactDAO getRiskimpactDao() {
		return riskimpactDao;
	}

	public void setRiskimpactDao(RiskImpactDAO riskimpactDao) {
		this.riskimpactDao = riskimpactDao;
	}

	public void setRiskmanage(RiskManage riskmanage) {
		this.riskmanage = riskmanage;
	}

	public String getDepname() {
		return depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
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

	public String getFamedegree() {
		return famedegree;
	}

	public void setFamedegree(String famedegree) {
		this.famedegree = famedegree;
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

	public String getSafe() {
		return safe;
	}

	public void setSafe(String safe) {
		this.safe = safe;
	}

	public String getAllFileId() {
		return allFileId;
	}

	public void setAllFileId(String allFileId) {
		this.allFileId = allFileId;
	}

	public String getAllFileName() {
		return allFileName;
	}

	public void setAllFileName(String allFileName) {
		this.allFileName = allFileName;
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

	public List<EventFile> getEfList() {
		return efList;
	}

	public void setEfList(List<EventFile> efList) {
		this.efList = efList;
	}

	public EventFlowFile getEff() {
		return eff;
	}

	public void setEff(EventFlowFile eff) {
		this.eff = eff;
	}

	public EventFileDAO getEventFileDao() {
		return eventFileDao;
	}

	public EventFlowFileDAO getEventFlowFileDao() {
		return eventFlowFileDao;
	}

	public void setEventFlowFileDao(EventFlowFileDAO eventFlowFileDao) {
		this.eventFlowFileDao = eventFlowFileDao;
	}

	public List<EventFlowFile> getEffList() {
		return effList;
	}

	public void setEffList(List<EventFlowFile> effList) {
		this.effList = effList;
	}

	public void setEventFileDao(EventFileDAO eventFileDao) {
		this.eventFileDao = eventFileDao;
	}

	public FileManageDAO getFileManageDao() {
		return fileManageDao;
	}

	public void setFileManageDao(FileManageDAO fileManageDao) {
		this.fileManageDao = fileManageDao;
	}

	public EventFile getEf() {
		return ef;
	}

	public void setEf(EventFile ef) {
		this.ef = ef;
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

	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public List<Department> getDepList() {
		return depList;
	}

	public void setDepList(List<Department> depList) {
		this.depList = depList;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public List<Reputation> getRepList() {
		return repList;
	}

	public void setRepList(List<Reputation> repList) {
		this.repList = repList;
	}

	public ReputationDAO getReputationDao() {
		return reputationDao;
	}

	public void setReputationDao(ReputationDAO reputationDao) {
		this.reputationDao = reputationDao;
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

	public ProbabilityDAO getProbabilityDao() {
		return probabilityDao;
	}

	public void setProbabilityDao(ProbabilityDAO probabilityDao) {
		this.probabilityDao = probabilityDao;
	}

	public FinanceDAO getFinanceDao() {
		return financeDao;
	}

	public void setFinanceDao(FinanceDAO financeDao) {
		this.financeDao = financeDao;
	}

	public SafeDAO getSafeDao() {
		return safeDao;
	}

	public void setSafeDao(SafeDAO safeDao) {
		this.safeDao = safeDao;
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

	public List<RiskType> getAllrtList() {
		return allrtList;
	}

	public void setAllrtList(List<RiskType> allrtList) {
		this.allrtList = allrtList;
	}

	public RiskTypeDAO getRiskTypeDao() {
		return riskTypeDao;
	}

	public void setRiskTypeDao(RiskTypeDAO riskTypeDao) {
		this.riskTypeDao = riskTypeDao;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getRiskeventid() {
		return riskeventid;
	}

	public void setRiskeventid(String riskeventid) {
		this.riskeventid = riskeventid;
	}
	
	public RiskDepQueryViewDAO getRiskDepQueryViewDao() {
		return riskDepQueryViewDao;
	}

	public void setRiskDepQueryViewDao(RiskDepQueryViewDAO riskDepQueryViewDao) {
		this.riskDepQueryViewDao = riskDepQueryViewDao;
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

	public RiskAssessViewDAO getRiskAssessViewDao() {
		return riskAssessViewDao;
	}

	public void setRiskAssessViewDao(RiskAssessViewDAO riskAssessViewDao) {
		this.riskAssessViewDao = riskAssessViewDao;
	}

	public String riskInput() {
		if (this.updateFlag.equals("update")) {

			// 更新riskevent的内容
			try {
				// 得到”我院声誉影响”的list，repList
				reputationList();
				// 得到”财务影响”的list，finList
				financeList();
				// 得到”发生可能性”的list，proList
				probabilityList();
				// 得到”人员健康环保影响”的list，safList
				safeList();
				// 得到”对法律法规影响”的list，lawList
				lawList();
				// 得到”对客户关系影响”的list，cliList
				clientList();
				// 得到”员工满意度影响”的list，empList
				employeeList();
				// 得到”对运营影响”的list，opeList
				operationList();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				Users us = (Users) session.getAttribute("loginUser");
				risk = this.riskDao.findById(this.getRiskname());
				if (null == risk)
					return "fail";
				Date date = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
				String event = this.riskeventid;
				riskevent = this.getRiskeventDao().findById(event);
				riskevent.setReId(event);
				riskevent.setReRiskId(risk.getRiskId());
				riskevent.setReType(this.getRisktype());
				riskevent.setReEventId(this.getEventid());
				riskevent.setReEventname(this.getEventname());
				riskevent.setReDetail(this.getRiskremark());// 风险事件描述
				// riskevent.setReRemark(this.getRiskremark());//风险事件描述
				riskevent.setReState("0");
				//riskevent.setDepartment(us.getDepartment());
				riskevent.setReIndep(us.getUserDep());
				// riskevent.setReDate(Timestamp.valueOf(df.format(date)));//当前时间作为录入时间
				riskevent.setReModifydate(df.format(date).toString());
				riskevent.setReModifier(us.getUserName());// 用户作为录入者
				riskevent.setReLastdate(df.format(date));	//设置最后处理时间
				riskevent.setReCheckflag(0);			//0表示未考核
				//this.getRiskeventDao().updateeventinput(riskevent);
				this.getRiskeventDao().merge(riskevent);
				//this.getRiskAssessViewDao().updateeventinput(riskevent);

				// 显示RiskImpact页面的内容
				getImpactShow(event);

			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
			return "success";
		} else {

			// 插入新的风险事件
			try {
				// 得到”我院声誉影响”的list，repList
				reputationList();
				// 得到”财务影响”的list，finList
				financeList();
				// 得到”发生可能性”的list，proList
				probabilityList();
				// 得到”人员健康环保影响”的list，safList
				safeList();
				// 得到”对法律法规影响”的list，lawList
				lawList();
				// 得到”对客户关系影响”的list，cliList
				clientList();
				// 得到”员工满意度影响”的list，empList
				employeeList();
				// 得到”对运营影响”的list，opeList
				operationList();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				Users us = (Users) session.getAttribute("loginUser");
				risk = this.riskDao.findById(this.getRiskname());
				if (null == risk)
					return "fail";
				// String event=this.getRiskname()+"-"+this.getEventid();
				String dep = risk.getRiskDep();// 获取相应的归口部门
				// depList=this.departmentDao.findByDepName(this.getIndep());
				Date date = new Date();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				 //SimpleDateFormat dy = new SimpleDateFormat("yyyy");
				String event = this.getRiskname() + "-" + this.getEventid();
				System.out.println("aaaaaaaaaaaaaaaaaaaaaa"+event);
				riskevent.setReId(event);
				//riskevent.setRisk(risk);
				riskevent.setReRiskId(risk.getRiskId());
				riskevent.setReType(this.getRisktype());
				riskevent.setReEventId(this.getEventid());
				riskevent.setReEventname(this.getEventname());
				riskevent.setReDetail(this.getRiskremark());
				riskevent.setReState("0");
				//riskevent.setDepartment(us.getDepartment());
				riskevent.setReIndep(us.getUserDep());
				
				riskevent.setReDate(df.format(date).toString());
				riskevent.setReModifydate(df.format(date).toString());
				riskevent.setReModifier(us.getUserName());// 用户作为录入者-
				riskevent.setReCreator(us.getUserName());
				riskevent.setReModifier(us.getUserName());
				riskevent.setReModifydate(df.format(date).toString());
				riskevent.setReLastdate(df.format(date));
				riskevent.setReCheckflag(0);			//0表示未考核
				this.riskeventDao.callinputProc(riskevent, dep);// 调用存储过程
				this.idimpact = event;
				this.depname = dep;
				// 显示RiskImpact页面的内容
				initImpactShow(event);
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
			return "success";
		}
	}

	// 显示风险事件的相应信息
	public String getRiskEvent() {
		
		getDropDownList();
		
		try {
			//RiskEvent riskevent = new RiskEvent();
			
			RiskDepQueryView riskDep = this.getRiskDepQueryViewDao().findById(this.getId().split("\\*")[0]);
			if (null == riskDep)
			{
				return "fail";
			}
			if (!riskDep.getReState().equals("0")) 
			{
				return "notallowed";
			}
			// 如果是已发布的风险，不允许修改
			this.riskeventid = this.getId().split("\\*")[0];
			// 得到下拉框，风险类型下拉框allrtList
			this.rtId = riskDep.getRiskType();
			this.rtName = riskDep.getRtName();
			this.selecrtId = riskDep.getReRiskId();
			//this.selecrtName = riskevent.getRisk().getRiskName();
			this.selecrtName = riskDep.getRiskName();
			this.risktype = riskDep.getRiskType();
			this.eventname = riskDep.getReEventname();
			this.eventid = riskDep.getReEventId();
			this.riskremark = riskDep.getReDetail();
			//this.indep = riskevent.getDepartment().getDepName();
			this.indep = riskDep.getDepName();
			
		} catch (Exception e) {
			e.printStackTrace();
			//getDropDownList();
			return "fail";
		}
		
		return "success";
	}

	// 编辑状态点击“下一页”显示风险影响
	public String getImpactShow(String tempid) {
		riskimpact = this.getRiskimpactDao().findById(tempid);
		this.idimpact = tempid;
		if (null == riskimpact)
			return "fail";
		this.kpi = riskimpact.getRiKpi();
		this.prodegree = riskimpact.getRiProdegree().toString();
		this.probability = riskimpact.getRiProbability();
		this.busarea = riskimpact.getRiBusarea();
		this.source = riskimpact.getRiSource();
		this.standard = riskimpact.getRiFlow();
		this.depname = riskimpact.getRiInchargedep();
		this.finance = riskimpact.getRiFinance();
		this.money = riskimpact.getRiMoney().toString();
		this.fame = riskimpact.getRiFame();
		this.law = riskimpact.getRiLaw();
		this.client = riskimpact.getRiClient();
		this.employee = riskimpact.getRiEmployee();
		this.operation = riskimpact.getRiOperation();
		this.safe = riskimpact.getRiSafe();
		this.financedegree = riskimpact.getRiFindegree().toString();
		this.famedegree = riskimpact.getRiFamedegree().toString();
		this.lawdegree = riskimpact.getRiLawdegree().toString();
		this.clientdegree = riskimpact.getRiClidegree().toString();
		this.employeedegree = riskimpact.getRiEmpdegree().toString();
		this.operationdegree = riskimpact.getRiOpedegree().toString();
		this.safedegree = riskimpact.getRiSafedegree().toString();
		getAllFile(tempid);// 得到allFileName和allFileId
		getAllFlowFile(tempid);
		return "success";
	}

	// 新增状态点击“下一页”显示风险影响
	public String initImpactShow(String tempid) {
		this.kpi = "";
		this.prodegree = "";
		this.probability = "";
		this.busarea = "";
		this.finance = "";
		this.money = "";
		this.fame = "";
		this.law = "";
		this.client = "";
		this.employee = "";
		this.operation = "";
		this.safe = "";
		this.financedegree = "";
		this.famedegree = "";
		this.lawdegree = "";
		this.clientdegree = "";
		this.employeedegree = "";
		this.operationdegree = "";
		this.safedegree = "";
		this.allFileId = "@";
		this.allFileName = "";
		return "success";
	}

	public String getDepMessage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		// System.out.printf(us.getUserDep());
		// 得到下拉框，风险类型下拉框allrtList
		getDropDownList();
		this.risktype = "";
		this.updateFlag = "";
		this.indep = this.getDepartmentDao().findById(us.getUserDep()).getDepName();
		this.riskremark = "";
		this.eventname = "";
		this.eventid = "";
		return "success";
	}

	//得到allFlowFileName和allFlowFileId
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
								this.setAllflowFileName(i+"、"+this.getFlowfileId()+"，"+this.getFlowfileManage().getFlowfileName()+"\n");
								i=i+1;							
							}else{
								this.setAllflowFileId(this.getAllflowFileId()+this.getFlowfileId()+"@");
								this.setAllflowFileName(this.getAllflowFileName()+i+"、"+this.getFlowfileId()+"，"+this.getFlowfileManage().getFlowfileName()+"\n");
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
	
	// 得到allFileName和allFileId
	public String getAllFile(String reId) {
		try {
			// this.setAllFile("");
			this.allFileId = "@";
			this.allFileName = "";
			int i = 1;
			int notNullFlag = 0;
			this.efList = new LinkedList<EventFile>();
			if (null != this.getEventFileDao().findByProperty("reId", reId)) {
				this.efList = this.getEventFileDao().findByProperty("reId",reId);
				Iterator it = this.efList.iterator();
				while (it.hasNext()) {
					this.ef = (EventFile) it.next();
					if (null != this.getEf().getFileId()) {
						notNullFlag = 1;
						this.setFileId(this.getEf().getFileId());
						this.setFileManage(this.getFileManageDao().findById(
								this.getFileId()));
						if ("@" == this.getAllFileId()) {
							this.setAllFileId("@" + this.getFileId() + "@");
							this.setAllFileName(i + "、" + this.getFileId()
									+ "，" + this.getFileManage().getFileName()
									+ "\n");
							i = i + 1;
						} else {
							this.setAllFileId(this.getAllFileId()
									+ this.getFileId() + "@");
							this.setAllFileName(this.getAllFileName()
											+ i
											+ "、"
											+ this.getFileId()
											+ "，"
											+ this.getFileManage()
													.getFileName() + "\n");
							i = i + 1;
						}
					}
				}
			}
			if (0 == notNullFlag) {
				this.setAllFileId("@");
				this.setAllFileName(" ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 得到”我院声誉影响”的list，repList
	public void reputationList() {
		// 得到”我院声誉影响”的list，repList
		Reputation rep1 = new Reputation();
		// rep1.setRepId(0);
		rep1.setRepId(-1);

		rep1.setRepDetail("--请选择--");
		rep1.setRepSuperior("--请选择--");
		rep1.setRepPartner("--请选择--");
		rep1.setRepPublic("--请选择--");
		rep1.setRepLevel("--请选择--");
		this.repList = new LinkedList<Reputation>();
		this.repList.add(rep1);
		this.repList1 = new LinkedList<Reputation>();
		this.repList1 = this.getReputationDao().findAll();
		Iterator it = this.repList1.iterator();
		while (it.hasNext()) {
			rep1 = (Reputation) it.next();
			rep1.setRepDetail(rep1.getRepId().toString() + "、"
					+ rep1.getRepLevel() + ":" + rep1.getRepDetail());
			rep1.setRepSuperior(rep1.getRepId().toString() + "、"
					+ rep1.getRepLevel() + ":" + rep1.getRepSuperior());
			rep1.setRepPartner(rep1.getRepId().toString() + "、"
					+ rep1.getRepLevel() + ":" + rep1.getRepPartner());
			rep1.setRepPublic(rep1.getRepId().toString() + "、"
					+ rep1.getRepLevel() + ":" + rep1.getRepPublic());
			this.repList.add(rep1);
		}
	}

	// 得到”财务影响”的list，finList
	public void financeList() {
		// 得到”财务影响”的list，finList
		Finance fin1 = new Finance();
		// fin1.setFinId(0);
		fin1.setFinId(-1);

		fin1.setFinAsset("--请选择--");
		fin1.setFinIncome("--请选择--");
		fin1.setFinProfit("--请选择--");
		fin1.setFinProperty("--请选择--");
		fin1.setFinDetail("--请选择--");
		this.finList = new LinkedList<Finance>();
		this.finList.add(fin1);
		this.finList1 = new LinkedList<Finance>();
		this.finList1 = this.getFinanceDao().findAll();
		Iterator it = this.finList1.iterator();
		while (it.hasNext()) {
			fin1 = (Finance) it.next();
			fin1.setFinAsset(fin1.getFinId().toString() + "、"
					+ fin1.getFinDetail() + ":" + fin1.getFinAsset());
			fin1.setFinIncome(fin1.getFinId().toString() + "、"
					+ fin1.getFinDetail() + ":" + fin1.getFinIncome());
			fin1.setFinProfit(fin1.getFinId().toString() + "、"
					+ fin1.getFinDetail() + ":" + fin1.getFinProfit());
			fin1.setFinProperty(fin1.getFinId().toString() + "、"
					+ fin1.getFinDetail() + ":" + fin1.getFinProperty());
			this.finList.add(fin1);
		}
	}

	// 得到”发生可能性”的list，proList
	public void probabilityList() {
		// 得到”发生可能性”的list，proList
		Probability pro1 = new Probability();
		// pro1.setProId(0);
		pro1.setProId(-1);

		pro1.setProLevel("--请选择--");
		pro1.setProProbability("--请选择--");
		pro1.setProDisasterEvent("--请选择--");
		pro1.setProDailyOperation("--请选择--");
		this.proList = new LinkedList<Probability>();
		this.proList.add(pro1);
		this.proList1 = new LinkedList<Probability>();
		this.proList1 = this.getProbabilityDao().findAll();
		Iterator it = this.proList1.iterator();
		while (it.hasNext()) {
			pro1 = (Probability) it.next();
			pro1.setProProbability(pro1.getProId().toString() + "、"
					+ pro1.getProLevel() + ":" + pro1.getProProbability());
			pro1.setProDisasterEvent(pro1.getProId().toString() + "、"
					+ pro1.getProLevel() + ":" + pro1.getProDisasterEvent());
			pro1.setProDailyOperation(pro1.getProId().toString() + "、"
					+ pro1.getProLevel() + ":" + pro1.getProDailyOperation());
			this.proList.add(pro1);
		}
	}

	// 得到”人员健康环保影响”的list，safList
	public void safeList() {
		// 得到”人员健康环保影响”的list，safList
		Safe saf1 = new Safe();
		// saf1.setSafId(0);
		saf1.setSafId(-1);

		saf1.setSafEnvironment("--请选择--");
		saf1.setSafLevel("--请选择--");
		saf1.setSafSafety("--请选择--");
		this.safList = new LinkedList<Safe>();
		this.safList.add(saf1);
		this.safList1 = new LinkedList<Safe>();
		this.safList1 = this.getSafeDao().findAll();
		Iterator it = this.safList1.iterator();
		while (it.hasNext()) {
			saf1 = (Safe) it.next();
			saf1.setSafEnvironment(saf1.getSafId().toString() + "、"
					+ saf1.getSafLevel() + ":" + saf1.getSafEnvironment());
			saf1.setSafSafety(saf1.getSafId().toString() + "、"
					+ saf1.getSafLevel() + ":" + saf1.getSafSafety());
			this.safList.add(saf1);
		}
	}

	// 得到”对法律法规影响”的list，lawList
	public void lawList() {
		// 得到”对法律法规影响”的list，lawList
		Law law1 = new Law();
		// law1.setLawId(0);
		law1.setLawId(-1);

		law1.setLawLevel("--请选择--");
		law1.setLawDetail("--请选择--");
		this.lawList = new LinkedList<Law>();
		this.lawList.add(law1);
		this.lawList1 = new LinkedList<Law>();
		this.lawList1 = this.getLawDao().findAll();
		this.lawList.addAll(lawList1);
	}

	// 得到”对客户关系影响”的list，cliList
	public void clientList() {
		// 得到”对客户关系影响”的list，cliList
		Client cli1 = new Client();
		// cli1.setCliId(0);
		cli1.setCliId(-1);

		cli1.setCliLevel("--请选择--");
		cli1.setCliDetail("--请选择--");
		this.cliList = new LinkedList<Client>();
		this.cliList.add(cli1);
		this.cliList1 = new LinkedList<Client>();
		this.cliList1 = this.getClientDao().findAll();
		this.cliList.addAll(cliList1);
	}

	// 得到”员工满意度影响”的list，empList
	public void employeeList() {
		// 得到”员工满意度影响”的list，empList
		Employee emp1 = new Employee();
		// emp1.setEmpId(0);
		emp1.setEmpId(-1);

		emp1.setEmpDetail("--请选择--");
		emp1.setEmpLevel("--请选择--");
		this.empList = new LinkedList<Employee>();
		this.empList.add(emp1);
		this.empList1 = new LinkedList<Employee>();
		this.empList1 = this.getEmployeeDao().findAll();
		this.empList.addAll(empList1);
	}

	// 得到”对运营影响”的list，opeList
	public void operationList() {
		// 得到”对运营影响”的list，opeList
		Operation ope1 = new Operation();
		// ope1.setOpeId(0);
		ope1.setOpeId(-1);

		ope1.setOpeDetail("--请选择--");
		ope1.setOpeLevel("--请选择--");
		this.opeList = new LinkedList<Operation>();
		this.opeList.add(ope1);
		this.opeList1 = new LinkedList<Operation>();
		this.opeList1 = this.getOperationDao().findAll();
		this.opeList.addAll(opeList1);
	}

	// 得到下拉框，风险类型下拉框allrtList
	public String getDropDownList() {
		try {
			// 风险类型下拉框allrtList
			RiskType rt1 = new RiskType();
			rt1.setRtId("none1");
			rt1.setRtName("--请选择--");
			rt1.setRtRemark("--请选择--");
			this.allrtList = new LinkedList<RiskType>();
			this.allrtList.add(rt1);
			this.rtList = new LinkedList<RiskType>();
			this.rtList = this.getRiskTypeDao().findAll();
			this.allrtList.addAll(this.rtList);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
}
