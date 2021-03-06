package com.action.riskFlow;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.services.TaskExecutionWebServer;
import java.util.concurrent.Callable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Holder;

import org.apache.struts2.ServletActionContext;
import org.zhongsoft.pendtasks.PendingTaskPortService;
import org.zhongsoft.pendtasks.PendingTaskPortServiceSoap;

import com.action.loginAction;
import com.dao.DepartmentDAO;
import com.dao.EventFileDAO;
import com.dao.EventFlowFileDAO;
import com.dao.FileManageDAO;
import com.dao.FlowRuleDAO;
import com.dao.FunctionLimitDAO;
import com.dao.RiskAssessDAO;
import com.dao.RiskAssessSituationDAO;
import com.dao.RiskDAO;
import com.dao.RiskDepQueryViewDAO;
import com.dao.RiskEventDAO;
import com.dao.RiskImpactDAO;
import com.dao.RiskManageDAO;
import com.dao.RiskRecordDAO;
import com.dao.UserMapDAO;
import com.dao.UsersDAO;
import com.dao.UsersFunctionDAO;
import com.dao.UsersQueryViewDAO;
import com.model.CheckedRisk;
import com.model.Department;
import com.model.EventFile;
import com.model.EventFlowFile;
import com.model.FileManage;
import com.model.FlowRule;
import com.model.Risk;
import com.model.RiskAssessAccount;
import com.model.RiskAssessSituation;
import com.model.RiskDepQueryView;
import com.model.RiskEvent;
import com.model.RiskImpact;
import com.model.RiskManage;
import com.model.RiskRecord;
import com.model.UserMap;
import com.model.Users;
import com.model.UsersQueryView;
import com.services.ToDoWebServiceAction;

public class RiskFlowAction {
	private String choosedId;
	private String reIndep;
	private String statusId;
	private String yearBegin;
	private String yearEnd;
	private String dateFlag;
	private String restatus;
	private String riskeventid;
	private String riskId;// ?????????????????????ID???
	private String risktype;
	private String riskname;
	private String riskremark;
	private String riskdetail;
	private String indep;
	private String updateFlag;
	private String money;
	private String standard;
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
	private String eventname;
	private Integer reCheckFlag;
	private String reAssessYear;
	private String reAssessQuarter;
	private String chance;
	private String strategy;
	private String plandate;
	private String control;
	private String planresource;
	private String reply;
	private String feedback;
	private String verifier;
	private String verifyview;
	private String id;// ???????????????FlowConfirm?????????????????????ID
	private String act;
	private String nextstate;
	private String flowtodep;
	private String flowtoperson;
	private String flowtodepid;
	private int current_pagenum = 1;// ????????????
	private int pageNum = 10;// ??????????????????????????????
	private String indepid;
	private String actionName;
	private String resId;
	private int years;
	private int quarters;
	private String reState;
	private String deparment;
	private String creator;
	private String creatdate;
	public String dateFrom = "";
	public String dateTo = "";
	private String waitfordepart;
	private String waitforperson;
	// Out_System_Flag=out,??????????????????????????????????????????????????????Out_System_Flag=in,?????????????????????action
	// private String Out_System_Flag="in";
	private String userid = "";
	private String recallPage;
	
	private Risk risk;
	private RiskDepQueryView riskdep;
	private RiskEvent riskevent;
	private RiskImpact riskimpact;
	private RiskManage riskmanage;
	private FlowRule flowrule;
	private RiskRecord riskrecord;
	EventFile eventFile = new EventFile();
	FileManage fileManage = new FileManage();
	
	private List<RiskRecord> rrList;
	private List<RiskRecord> rrOneList;// ?????????????????????????????????????????????
	private List<Risk> allriskList;
	private List<Department> riskdepList;
	private List<RiskDepQueryView> reList;
	private List<FlowRule> flowRuleList;
	private List<UsersQueryView> usersList;
	private List<Users> usersOneList;// ?????????????????????????????????????????????
	private List<FlowRule> frList;
	private List<CheckedRisk> rcheckedList;
	private List<EventFile> efList;
	private List<String> idCheck;

	private RiskEventDAO riskEventDao;
	private RiskDepQueryViewDAO riskDepQueryViewDao;
	private RiskImpactDAO riskImpactDao;
	private RiskManageDAO riskManageDao;
	private DepartmentDAO departmentDao;
	private RiskDAO riskDao;
	private FlowRuleDAO flowRuleDao;
	private RiskRecordDAO riskRecordDao;
	private UsersDAO usersDao;
	private FunctionLimitDAO functionLimitDao;
	private UsersFunctionDAO functiondao;
	private UserMapDAO userMapDao;
	private UsersQueryViewDAO usersQueryViewDao;
	private EventFileDAO eventFileDao;
	private EventFlowFileDAO eventFlowFileDao;
	private FileManageDAO fileManageDao;
	private RiskAssessDAO riskAssessDao;
	private RiskAssessSituationDAO riskassesssituationDao;

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public int getQuarters() {
		return quarters;
	}

	public void setQuarters(int quarters) {
		this.quarters = quarters;
	}

	public String getChoosedId() {
		return choosedId;
	}

	public void setChoosedId(String choosedId) {
		this.choosedId = choosedId;
	}

	public EventFlowFileDAO getEventFlowFileDao() {
		return eventFlowFileDao;
	}

	public void setEventFlowFileDao(EventFlowFileDAO eventFlowFileDao) {
		this.eventFlowFileDao = eventFlowFileDao;
	}

	public RiskDepQueryView getRiskdep() {
		return riskdep;
	}

	public void setRiskdep(RiskDepQueryView riskdep) {
		this.riskdep = riskdep;
	}

	public void setRiskevent(RiskEvent riskevent) {
		this.riskevent = riskevent;
	}

	public String getActionName() {
		return actionName;
	}

	public String getWaitfordepart() {
		return waitfordepart;
	}

	public void setWaitfordepart(String waitfordepart) {
		this.waitfordepart = waitfordepart;
	}

	public String getWaitforperson() {
		return waitforperson;
	}

	public void setWaitforperson(String waitforperson) {
		this.waitforperson = waitforperson;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public List<Risk> getAllriskList() {
		return allriskList;
	}

	public void setAllriskList(List<Risk> allriskList) {
		this.allriskList = allriskList;
	}

	public List<Department> getRiskdepList() {
		return riskdepList;
	}

	public void setRiskdepList(List<Department> riskdepList) {
		this.riskdepList = riskdepList;
	}

	public String getRiskdetail() {
		return riskdetail;
	}

	public void setRiskdetail(String riskdetail) {
		this.riskdetail = riskdetail;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getReState() {
		return reState;
	}

	public void setReState(String reState) {
		this.reState = reState;
	}

	public String getDeparment() {
		return deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatdate() {
		return creatdate;
	}

	public void setCreatdate(String creatdate) {
		this.creatdate = creatdate;
	}

	public List<FlowRule> getFlowRuleList() {
		return flowRuleList;
	}

	public void setFlowRuleList(List<FlowRule> flowRuleList) {
		this.flowRuleList = flowRuleList;
	}

	public String getReId() {
		return riskeventid;
	}

	public void setReId(String reId) {
		this.riskeventid = reId;
	}

	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
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

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
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

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
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

	public RiskAssessSituationDAO getRiskassesssituationDao() {
		return riskassesssituationDao;
	}

	public void setRiskassesssituationDao(
			RiskAssessSituationDAO riskassesssituationDao) {
		this.riskassesssituationDao = riskassesssituationDao;
	}

	public void setRiskmanage(RiskManage riskmanage) {
		this.riskmanage = riskmanage;
	}

	public String getReIndep() {
		return reIndep;
	}

	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
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

	public List<RiskDepQueryView> getReList() {
		return reList;
	}

	public void setReList(List<RiskDepQueryView> reList) {
		this.reList = reList;
	}

	public RiskDepQueryViewDAO getRiskDepQueryViewDao() {
		return riskDepQueryViewDao;
	}

	public void setRiskDepQueryViewDao(RiskDepQueryViewDAO riskDepQueryViewDao) {
		this.riskDepQueryViewDao = riskDepQueryViewDao;
	}

	public RiskEventDAO getRiskEventDao() {
		return riskEventDao;
	}

	public void setRiskEventDao(RiskEventDAO riskEventDao) {
		this.riskEventDao = riskEventDao;
	}

	public String getYearBegin() {
		return yearBegin;
	}

	public void setYearBegin(String yearBegin) {
		this.yearBegin = yearBegin;
	}

	public String getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(String yearEnd) {
		this.yearEnd = yearEnd;
	}

	public String getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	public List<CheckedRisk> getRcheckedList() {
		return rcheckedList;
	}

	public void setRcheckedList(List<CheckedRisk> rcheckedList) {
		this.rcheckedList = rcheckedList;
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

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getNextstate() {
		return nextstate;
	}

	public void setNextstate(String nextstate) {
		this.nextstate = nextstate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public List<FlowRule> getFrList() {
		return frList;
	}

	public void setFrList(List<FlowRule> frList) {
		this.frList = frList;
	}

	public List<RiskRecord> getRrList() {
		return rrList;
	}

	public void setRrList(List<RiskRecord> rrList) {
		this.rrList = rrList;
	}

	public String getFlowtodep() {
		return flowtodep;
	}

	public void setFlowtodep(String flowtodep) {
		this.flowtodep = flowtodep;
	}

	public String getFlowtoperson() {
		return flowtoperson;
	}

	public void setFlowtoperson(String flowtoperson) {
		this.flowtoperson = flowtoperson;
	}

	public String getVerifier() {
		return verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public String getVerifyview() {
		return verifyview;
	}

	public void setVerifyview(String verifyview) {
		this.verifyview = verifyview;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public RiskRecordDAO getRiskRecordDao() {
		return riskRecordDao;
	}

	public void setRiskRecordDao(RiskRecordDAO riskRecordDao) {
		this.riskRecordDao = riskRecordDao;
	}

	public RiskAssessDAO getRiskAssessDao() {
		return riskAssessDao;
	}

	public void setRiskAssessDao(RiskAssessDAO riskAssessDao) {
		this.riskAssessDao = riskAssessDao;
	}

	public RiskRecord getRiskrecord() {
		return riskrecord;
	}

	public void setRiskrecord(RiskRecord riskrecord) {
		this.riskrecord = riskrecord;
	}

	public FlowRuleDAO getFlowRuleDao() {
		return flowRuleDao;
	}

	public void setFlowRuleDao(FlowRuleDAO flowRuleDao) {
		this.flowRuleDao = flowRuleDao;
	}

	public String getRestatus() {
		return restatus;
	}

	public void setRestatus(String restatus) {
		this.restatus = restatus;
	}

	public FlowRule getFlowrule() {
		return flowrule;
	}

	public void setFlowrule(FlowRule flowrule) {
		this.flowrule = flowrule;
	}

	public List<UsersQueryView> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<UsersQueryView> usersList) {
		this.usersList = usersList;
	}

	public UsersDAO getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDAO usersDao) {
		this.usersDao = usersDao;
	}

	public String getIndepid() {
		return indepid;
	}

	public void setIndepid(String indepid) {
		this.indepid = indepid;
	}

	public String getFlowtodepid() {
		return flowtodepid;
	}

	public void setFlowtodepid(String flowtodepid) {
		this.flowtodepid = flowtodepid;
	}

	public String getRiskeventid() {
		return riskeventid;
	}

	public void setRiskeventid(String riskeventid) {
		this.riskeventid = riskeventid;
	}

	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public RiskDAO getRiskDao() {
		return riskDao;
	}

	public void setRiskDao(RiskDAO riskDao) {
		this.riskDao = riskDao;
	}

	public FunctionLimitDAO getFunctionLimitDao() {
		return functionLimitDao;
	}

	public void setFunctionLimitDao(FunctionLimitDAO functionLimitDao) {
		this.functionLimitDao = functionLimitDao;
	}

	public EventFileDAO getEventFileDao() {
		return eventFileDao;
	}

	public void setEventFileDao(EventFileDAO eventFileDao) {
		this.eventFileDao = eventFileDao;
	}

	public EventFile getEventFile() {
		return eventFile;
	}

	public void setEventFile(EventFile eventFile) {
		this.eventFile = eventFile;
	}

	public List<EventFile> getEfList() {
		return efList;
	}

	public void setEfList(List<EventFile> efList) {
		this.efList = efList;
	}

	public FileManageDAO getFileManageDao() {
		return fileManageDao;
	}

	public void setFileManageDao(FileManageDAO fileManageDao) {
		this.fileManageDao = fileManageDao;
	}

	public FileManage getFileManage() {
		return fileManage;
	}

	public void setFileManage(FileManage fileManage) {
		this.fileManage = fileManage;
	}

	public List<Users> getUsersOneList() {
		return usersOneList;
	}

	public void setUsersOneList(List<Users> usersOneList) {
		this.usersOneList = usersOneList;
	}

	public List<RiskRecord> getRrOneList() {
		return rrOneList;
	}

	public void setRrOneList(List<RiskRecord> rrOneList) {
		this.rrOneList = rrOneList;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public UsersFunctionDAO getFunctiondao() {
		return functiondao;
	}

	public void setFunctiondao(UsersFunctionDAO functiondao) {
		this.functiondao = functiondao;
	}

	public UserMapDAO getUserMapDao() {
		return userMapDao;
	}

	public void setUserMapDao(UserMapDAO userMapDao) {
		this.userMapDao = userMapDao;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public Integer getReCheckFlag() {
		return reCheckFlag;
	}

	public void setReCheckFlag(Integer reCheckFlag) {
		this.reCheckFlag = reCheckFlag;
	}

	public String getReAssessYear() {
		return reAssessYear;
	}

	public void setReAssessYear(String reAssessYear) {
		this.reAssessYear = reAssessYear;
	}

	public String getReAssessQuarter() {
		return reAssessQuarter;
	}

	public void setReAssessQuarter(String reAssessQuarter) {
		this.reAssessQuarter = reAssessQuarter;
	}

	public UsersQueryViewDAO getUsersQueryViewDao() {
		return usersQueryViewDao;
	}

	public void setUsersQueryViewDao(UsersQueryViewDAO usersQueryViewDao) {
		this.usersQueryViewDao = usersQueryViewDao;
	}

	public RiskEvent getRiskevent() {
		return riskevent;
	}
	
	public String getRecallPage() {
		return recallPage;
	}

	public void setRecallPage(String recallPage) {
		this.recallPage = recallPage;
	}
	
	public List<String> getIdCheck() {
		return idCheck;
	}

	public void setIdCheck(List<String> idCheck) {
		this.idCheck = idCheck;
	}

	// ????????????????????????,????????????reList,?????????RiskEventQuery.jsp???????????????
	@SuppressWarnings("unchecked")
	public String getCheckRisk() {
		try {
			this.reList = new LinkedList<RiskDepQueryView>();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			//Integer id = us.getUserPosition();

			if (functionLimitDao.findbyroleIdModule(
					us.getUserPosition(), 110020010).isEmpty())
				return "nosee";// 110020010?????????????????????????????????????????????
			this.reList = this.getRiskDepQueryViewDao().findevent(us.getUserId(),us.getUserName(),
					(current_pagenum - 1) * pageNum, pageNum,us.getUserPosition()+"");
			ServletActionContext.getRequest().getSession().setAttribute(
					"current_pagenum", current_pagenum);

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

		return "success";

	}
	//--------------------------------------------------------
		// ????????????????????????,????????????reList,?????????RiskEventQuery.jsp???????????????
		@SuppressWarnings("unchecked")
		public String getCheckRisk1() {
			try {
				System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"+choosedId);
				this.reList = new LinkedList<RiskDepQueryView>();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				Users us = (Users) session.getAttribute("loginUser");
				//Integer id = us.getUserPosition();
	            
				if (functionLimitDao.findbyroleIdModule(
						us.getUserPosition(), 110020010).isEmpty())
					return "nosee";// 110020010?????????????????????????????????????????????
				this.reList = this.getRiskDepQueryViewDao().findevent1(us.getUserId(),us.getUserName(),
						(current_pagenum - 1) * pageNum, pageNum,this.getDateFrom(),this.getDateTo(),us.getUserPosition()+"");
				ServletActionContext.getRequest().getSession().setAttribute(
						"current_pagenum", current_pagenum);

			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}

			return "success";
		}
		
			

	/*
	 * ????????????????????????????????????????????? ????????????????????????,????????????reList,?????????RiskEventQuery.jsp???????????????
	 */
	@SuppressWarnings("unchecked")
	public String Out_getCheckRisk() {
		try {
			// ?????????????????????????????????????????????
			loginAction loginAct = new loginAction();
			// ????????????,????????????userid
			loginAct.setUserid(this.getUserid());
			loginAct.setUserdao(this.getUsersDao());
			loginAct.setFunctiondao(this.getFunctiondao());
			loginAct.setRiskEventDao(this.getRiskEventDao());
			String result = loginAct.loginSingleSystem();
			if (result.equals("fail")) {
				return "Out_Fail";
			}

			this.reList = new LinkedList<RiskDepQueryView>();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			//Integer id = us.getUserPosition();

			if (functionLimitDao.findbyroleIdModule(
					us.getUserPosition(), 131).isEmpty())
				return "Out_Fail";// 131?????????????????????????????????????????????
			this.reList = this.getRiskDepQueryViewDao().findevent(us.getUserId(),us.getUserName(),(current_pagenum - 1) * pageNum, pageNum,us.getUserPosition()+"");
			ServletActionContext.getRequest().getSession().setAttribute(
					"Out_reList", this.reList);
			ServletActionContext.getRequest().getSession().setAttribute(
					"Out_Jsp", "/RiskEvent/RiskFlow/Out_RiskStatusQuery.jsp");
			ServletActionContext.getRequest().getSession().setAttribute(
					"current_pagenum", current_pagenum);

		} catch (Exception e) {
			// e.printStackTrace();
			return "Out_Fail";
		}

		return "Out_Success";

	}

	// ?????????????????????????????????
	public String getCheckedRisk() {
		try {
			getDropDownList();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			rcheckedList = new LinkedList<CheckedRisk>();
			// rrList=this.getRiskRecordDao().findcheckedevent(us.getUserId(),(current_pagenum-1)*pageNum,pageNum);
			// this.reList=new LinkedList<RiskEvent>();

			// ????????????
			List rchList = this.getRiskEventDao().findcheckedevent(us.getUserId(), (current_pagenum - 1) * pageNum, pageNum,us.getUserPosition()+"");
			Object[] temp;
			for (int i = 0; i < rchList.size(); i++) {
				temp = (Object[]) rchList.get(i);
				CheckedRisk rch = new CheckedRisk();
				rch.setReId(temp[0].toString());
				rch.setTime(temp[1].toString());
				rch.setVerifier(temp[2].toString());
				rch.setBehaviour(temp[3].toString());
				rch.setIndep(temp[4].toString());
				rch.setCreator(temp[5].toString());
				rch.setStatus(temp[6].toString());
				rch.setIndate(temp[7].toString());
				rch.setReact(temp[8].toString());
				rch.setRiskId(temp[9].toString());
				rch.setEventname(temp[10].toString());
				rch.setRiskname(temp[12].toString());
				rcheckedList.add(rch);

			}

			this.actionName = "riskFlow/RiskCheckedAction";

			// ????????????
			/*
			 * this.rcheckedList=new LinkedList<CheckedRisk>();
			 * this.rcheckedList
			 * =this.getRiskEventDao().findcheckedevent(us.getUserId
			 * (),(current_pagenum-1)*pageNum,pageNum);
			 */

			// this.rcheckedList.set(0, )=rchList.get(0)
			ServletActionContext.getRequest().getSession().setAttribute(
					"current_pagenum", current_pagenum);

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	public String getadvancedCheckedRisk1() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			reList = new LinkedList<RiskDepQueryView>();
			// rrList=this.getRiskRecordDao().findcheckedevent(us.getUserId(),(current_pagenum-1)*pageNum,pageNum);
			// this.reList=new LinkedList<RiskEvent>();

			List rchList = this.getRiskEventDao().findadcheckedevent(
					us.getUserId(), this.getDateFrom(), this.getDateTo(),
					(current_pagenum - 1) * pageNum, pageNum,null,us.getUserPosition()+"");
			System.out.println("asasasasasasddddd"+us.getUserId());
			Object[] temp;
			for (int i = 0; i < rchList.size(); i++) {
				temp = (Object[]) rchList.get(i);
				RiskDepQueryView rch=new RiskDepQueryView();
				CheckedRisk rch1 = new CheckedRisk();
				rch.setReId(temp[0].toString());
				rch.setReDate(temp[1].toString());
				//rch.setTime(temp[1].toString());
				rch.setReVerifier(temp[2].toString());
				//rch.setVerifier(temp[2].toString());
				rch.setReEventname(temp[10].toString());
				rch.setReRiskId(temp[9].toString());
				//<!00>>>>>>>>rch.setBehaviour(temp[3].toString());
				rch1.setIndep(temp[4].toString());
				rch.setDepName(rch1.getIndep());
				//rch.setCreator(temp[5].toString());
				//rch.setStatus(temp[6].toString());
				//<!00>>>>>>>>rch.setIndate(temp[7].toString());
				//<!00>>>>>>>>rch.setReact(temp[8].toString());
				rch.setReState(temp[6].toString());
				rch.setReCreator(temp[5].toString());
				//rch.setRiskId(temp[9].toString());
				//rch.setEventname();
				rch.setRiskName(temp[12].toString());
				//rch.setRiskname(temp[12].toString());
				reList.add(rch);

			}
			this.actionName = "riskFlow/CheckedAdvancedQuery1";

			ServletActionContext.getRequest().getSession().setAttribute(
					"current_pagenum", current_pagenum);

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// ????????????????????????????????? ,????????????
	public String getadvancedCheckedRisk() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			rcheckedList = new LinkedList<CheckedRisk>();
			// rrList=this.getRiskRecordDao().findcheckedevent(us.getUserId(),(current_pagenum-1)*pageNum,pageNum);
			// this.reList=new LinkedList<RiskEvent>();

			List rchList = this.getRiskEventDao().findadcheckedevent(
					us.getUserId(), this.getDateFrom(), this.getDateTo(),
					(current_pagenum - 1) * pageNum, pageNum,null,us.getUserPosition()+"");
			Object[] temp;
			for (int i = 0; i < rchList.size(); i++) {
				temp = (Object[]) rchList.get(i);
				
				CheckedRisk rch = new CheckedRisk();
				rch.setReId(temp[0].toString());
				rch.setTime(temp[1].toString());
				rch.setVerifier(temp[2].toString());
				rch.setBehaviour(temp[3].toString());
				rch.setIndep(temp[4].toString());
				rch.setCreator(temp[5].toString());
				rch.setStatus(temp[6].toString());
				rch.setIndate(temp[7].toString());
				rch.setReact(temp[8].toString());
				rch.setRiskId(temp[9].toString());
				rch.setEventname(temp[10].toString());
				rch.setRiskname(temp[12].toString());
				rcheckedList.add(rch);

			}
			this.actionName = "riskFlow/CheckedAdvancedQuery";

			ServletActionContext.getRequest().getSession().setAttribute(
					"current_pagenum", current_pagenum);

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// ???????????????????????????????????????alldepList????????????????????????risktypeList
	public String getDropDownList() {
		try {
			// ?????????????????????alldepList
			Department dep1 = new Department();
			dep1.setDepId("0");
			dep1.setDepName("--?????????--");
			this.riskdepList = new LinkedList<Department>();
			this.riskdepList.add(dep1);
			List<Department> depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findAll();
			this.riskdepList.addAll(depList);

			// ?????????????????????allriskList
			Risk r1 = new Risk();
			r1.setRiskId("0");
			r1.setRiskName("--?????????--");
			this.allriskList = new LinkedList<Risk>();
			this.allriskList.add(r1);
			List<Risk> riskList = new LinkedList<Risk>();
			riskList = this.getRiskDao().findAll();
			this.allriskList.addAll(riskList);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// ?????????????????????????????????
	@SuppressWarnings("unchecked")
	public String getriskfordep() {

		riskdep = this.getRiskDepQueryViewDao().findById(this.riskeventid);
		riskevent = this.getRiskEventDao().findById(this.riskeventid);
		riskimpact = this.getRiskImpactDao().findById(this.riskeventid);
		riskmanage = this.getRiskManageDao().findById(this.riskeventid);
		this.efList = new LinkedList<EventFile>();
		this.efList = this.getEventFileDao().findByProperty("reId", this.riskeventid);

		this.restatus = riskdep.getReState();
		this.riskId = riskdep.getReId();
		this.risktype = riskdep.getRtName();
		this.riskname = riskdep.getRiskName();
		this.riskdetail = riskdep.getReDetail();
		this.creator = riskdep.getReCreator();
		this.indep = riskdep.getDepName();
		this.feedback = riskdep.getReRemark().trim();
		this.kpi = riskimpact.getRiKpi();
		this.probability = riskimpact.getRiProbability();
		this.busarea = riskimpact.getRiBusarea();
		this.source = riskimpact.getRiSource();
		this.eventname = riskdep.getReEventname();
		this.reCheckFlag = riskdep.getReCheckFlag();
		this.reAssessYear = riskevent.getReAssessYear();
		this.reAssessQuarter = riskevent.getReAssessQuarter();
		String s = "";
		int i = 0;
		s = "????????????????????????\n";
		Iterator it = this.getEfList().iterator();
		while (it.hasNext()) {
			eventFile = (EventFile) it.next();
			if (null == eventFile.getFileId()) {
				break;
			}
			fileManage = this.getFileManageDao().findById(eventFile.getFileId());
			if (null == fileManage) {
				break;
			}
			i = i + 1;
			s = s + i + "?????????????????????" + fileManage.getFileId() + "????????????????????????"
					+ fileManage.getFileName() + "??????\n";
		}
		List<EventFlowFile> efList2 = new LinkedList<EventFlowFile>();
		efList2 = this.getEventFlowFileDao().findByProperty("reId", this.riskeventid);
		Iterator it2 = efList2.iterator();
		while (it2.hasNext()) {

			EventFlowFile eventFlowFile = (EventFlowFile) it2.next();
			if (null == eventFlowFile.getFlowfileId()) {
				break;
			}
			fileManage = this.getFileManageDao().findById(eventFlowFile.getFlowfileId());
			if (null == fileManage) {
				break;
			}
			s = s + (i+1) + "?????????????????????" + fileManage.getFileId() + "????????????????????????"
					+ fileManage.getFileName() + "??????\n";
			i = i + 1;
		}
		s = s + "???" + i + "????????????\n";
		this.standard = s;
		// this.depname=riskimpact.getRiInchargedep();
		this.money = riskimpact.getRiMoney().toString();

		this.finance = riskimpact.getRiFincriteria() + "\n" + "???????????????"
				+ riskimpact.getRiFinance();
		this.fame = riskimpact.getRiFamecriteria() + "\n" + "???????????????"
				+ riskimpact.getRiFame();
		this.law = riskimpact.getRiLawcriteria() + "\n" + "???????????????"
				+ riskimpact.getRiLaw();
		this.client = riskimpact.getRiClicriteria() + "\n" + "???????????????"
				+ riskimpact.getRiClient();
		this.employee = riskimpact.getRiEmpcriteria() + "\n" + "???????????????"
				+ riskimpact.getRiEmployee();
		this.operation = riskimpact.getRiOpecriteria() + "\n" + "???????????????"
				+ riskimpact.getRiOperation();
		this.safe = riskimpact.getRiSafecriteria() + "\n" + "???????????????"
				+ riskimpact.getRiSafe();
		this.chance = riskmanage.getRmChance();
		this.control = riskmanage.getRmControl();
		this.reply = riskmanage.getRmReply();
		this.strategy = riskmanage.getRmStrategy();
		this.planresource = riskmanage.getRmPlanres();
		this.plandate = riskmanage.getRmPlandate();
		

		rrList = riskRecordDao.findpreRecord(this.riskeventid);
		if (rrList.size() > 0) {
			
			this.verifier = this.getUsersDao().findById(rrList.get(0).getRrVerifier()).getUserName();
			// this.verifyview=rrList.get(0).getRrView();
			rrList = new LinkedList<RiskRecord>();
			rrList = this.getRiskRecordDao().findAllRecord(this.riskeventid);
			String view = "";
			for (int p = 0; p < this.rrList.size(); p++) {
				view = view + "????????????"
						+ this.getUsersDao().findById(rrList.get(p).getRrVerifier()).getUserName()
						+ "   ???????????????" + this.rrList.get(p).getRrTime() + "\n";
				view = view + "???????????????" + this.rrList.get(p).getRrView() + "\n"
						+ "\n";
			}
			this.verifyview = view;
		} else {
			this.verifier = "????????????";
			this.verifyview = "????????????";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		Calendar calendar = Calendar.getInstance();
		
		request.setAttribute("year", calendar.get(Calendar.YEAR) + "");
		
		int month = calendar.get(Calendar.MONTH) + 1;
		
		request.setAttribute("quarter", (month -1) / 3 + 1);
		return "success";
	}

	public String riskviewsave() 
	{
		try {
			riskEventDao.updatetempview(this.riskId, this.feedback);
			//System.out.println(this.verifyview);
			// ???????????????????????????????????????
 			Date date = new Date();
 			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 			riskEventDao.updateLastDate(this.riskId, df.format(date));
			
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
		
	}

	// ?????????????????????????????????
	public String departmentpass() 
	{
	try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			riskevent = this.getRiskEventDao().findById(this.riskId);
			String tempdep;
			String tempstate;
			List<FlowRule> frList1;

			// ??????????????????????????????????????????????????????????????????????????????flowrule????????????????????????
			String currentstate = riskevent.getReState();
			frList1 = flowRuleDao.findnextstatus(riskevent.getReRiskId(), riskevent.getReState());
			if (0 == frList1.size()) {
				// ?????????????????????
				getCheckRisk();
				return "strChanged";
			}
			this.nextstate = frList1.get(0).getFrNextstatus();
			if (this.nextstate.equals("*")) {
				// ????????? ??????????????????????????????????????????????????????
				publishRiskEvent1(this.riskId);
				return "released";
			}

			// ???????????????????????????.????????????????????????????????????????????????????????????????????????????????????List
			// ???????????????????????????flowRule
			this.frList = flowRuleDao.findnextstatus(riskevent.getReRiskId(),this.nextstate);
			if (currentstate.equals("1")) {
				tempdep = this.frList.get(0).getFrDepart();
				tempstate = this.nextstate;
				while (tempdep.equals(riskevent.getReIndep())&& !tempstate.equals("*")) {
					this.frList = flowRuleDao.findnextstatus(riskevent.getReRiskId(), tempstate);// ?????????
					tempdep = this.frList.get(0).getFrDepart();// ????????????dep
					tempstate = this.frList.get(0).getFrNextstatus();// ????????????nextState
				}
			}
			this.nextstate = this.frList.get(0).getFrStatus();// ????????????nextState

			// ???????????????????????????????????????
			if (this.frList.get(0).getFrDepart().equals("localdep")) {
				this.flowtodepid = riskevent.getReIndep();
			} else
				this.flowtodepid = this.frList.get(0).getFrDepart();
			this.flowtodep = departmentDao.findById(this.flowtodepid).getDepName();

			// ???????????????????????????????????????????????????????????????
			// ?????????????????????????????????????????????????????????????????????
			// order by [User_position] desc,???????????????????????????9
			UsersQueryView user = new UsersQueryView();
			user.setUserId("none1");
			user.setUserName("--?????????--");
			
			this.usersList = this.getUsersQueryViewDao().findflowtopeople(this.flowtodepid,this.frList.get(0).getFrRole());
			//2019.01.11
			//???????????????????????????
			if (this.usersList.isEmpty()) {
				return "alert";
			}
			
			if(("FB").equals(this.usersList.get(0).getDepId())){
				this.id=this.riskId;
				this.flowtoperson=this.usersList.get(0).getUserId();
				this.act="1";
				flowtoNext();
				return "released";
			}
			else if(this.usersList.size()>1){
				this.usersList.add(0, user);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// ??????????????????????????????????????????
	public String departmentnotPass() 
	{
	try {
			riskdep = this.getRiskDepQueryViewDao().findById(this.riskId);
			this.setNextstate("0");
			// ????????????????????????????????????
			this.flowtodepid = riskdep.getDepId();
			this.flowtodep = riskdep.getDepName();
			// ???????????????????????????????????????????????????????????????
			
			UsersQueryView user = new UsersQueryView();
			user.setUserId("none1");
			user.setUserName("--?????????--");
			
			this.usersList = this.getUsersQueryViewDao().findflowtopeople(this.flowtodepid, "0");// 0???????????????1?????????
			
			this.usersList.add(0, user);
			
			// ?????????????????????????????????????????????
			List<UsersQueryView> uqvList=new LinkedList<UsersQueryView>();
			uqvList = this.getUsersQueryViewDao().findflowtopeopleOne(this.flowtodepid, "0",riskdep.getReCreator());
			if (uqvList.size() > 0) 
			{
				this.setFlowtoperson(uqvList.get(0).getUserId());// ???????????????????????????????????????
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return "strChanged";
		}
		return "success";
	}

	
	/*
	 * ???????????????????????????????????????????????????????????????
	 */
	public String flowtoNext() {
		try {
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			/*
			 * ??????????????????????????????????????????Tomcat???????????????
			 */
			inThreads("update",us, this.id, this.flowtoperson, this.act);
			/*
			 * ??????????????????
			 */
			riskdep = this.getRiskDepQueryViewDao().findById(this.id);
			riskrecord = new RiskRecord();
			// ???riskrecord???????????????????????????
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//System.out.println(df.format(date));
			riskrecord.setRrBehaviour(this.act);// ???????????????1????????????
			riskrecord.setRrReId(riskdep.getReId());
			riskrecord.setRrVerifier(us.getUserId());
			riskrecord.setRrTime(df.format(date));
			riskrecord.setRrView(this.feedback);
			riskrecord.setRrStatus(riskdep.getReState());
			this.getRiskRecordDao().save(riskrecord);
			// ??????riskevent???????????????
			this.getRiskEventDao().updateevent(this.id, this.nextstate,this.flowtoperson, this.act);
			//???????????????????????????????????????
			this.getRiskEventDao().updateLastDate(this.id, df.format(date));
			
			// ???????????????????????????
			//response.sendRedirect("/RiskEvent/default/processAction_unProcessed");
			
			getCheckRisk();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	/*
	 * ??????????????????????????????????????????Tomcat???????????????
	 */
	public String inThreads(String taskFlag,final Users Out_us, final String Out_reId,final String Out_userid,final String Out_act)
	{
		try{
			Callable<String> update=new Callable<String>(){
				public String call(){
					return updateTasks( Out_us,Out_reId, Out_userid,Out_act);
				}
			};
			Callable<String> delete=new Callable<String>(){
				public String call(){
					return deleteTasks( Out_reId);
				}
			};
			TaskExecutionWebServer tews=TaskExecutionWebServer.getInstance();
			if(taskFlag.equals("update"))
			{
				tews.submit(update);
			}
			else if(taskFlag.equals("delete"))
			{
				tews.submit(delete);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	/*
	 * ????????????
	 */
	public String updateTasks(Users Out_us,String Out_reId,String Out_userid,String Out_act)
	{
		try{
			Users receiver=this.getUsersDao().findById(Out_userid);//????????????
			riskevent=new RiskEvent();
			riskevent=this.getRiskEventDao().findById(Out_reId);//????????????
			if(null == riskevent) return "fail";
			/*
			 * ?????????????????????????????????????????????????????????WebService??????????????????????????????
			 */
			//????????????????????????
			String TaskId=Out_reId;//reId
			String TaskApp="RiskEvent";//????????????????????????
			String LogonName="";//?????????Id
			String LogonName_First=Out_userid;//?????????issuer??????1914?????????LogonName_First??????issuer
			String UserName="";//???????????????
			String OrgName="";//?????????????????????
			
			//????????????Flowtoperson???UserMap?????????issuer????????????receiver??????UserMap??????issuer?????????1914		
			List<UserMap> userMapList=new LinkedList<UserMap>();
			userMapList=this.getUserMapDao().findByProperty("umMapUser", Out_userid);
			if(userMapList.size() > 0)
			{
				receiver=this.getUsersDao().findById(userMapList.get(0).getUmUser());//???1914
			}
			Department dModel=new Department();
			if(null!=receiver)
			{
				LogonName=receiver.getUserId();
				UserName=receiver.getUserName();
				dModel=this.getDepartmentDao().findById(receiver.getUserDep());
				if(null != dModel) OrgName=dModel.getDepName();
			}
			//Out_System_Flag=out,??????????????????????????????????????????????????????Out_System_Flag=in,?????????????????????action
			//String TaskUrl="riskFlow/Out_RiskStatusAction?current_pagenum=1&userid="+LogonName;//?????????????????????
			String TaskUrl="default/loginSingleSystemAction?userid="+LogonName_First;//?????????????????????
			String TaskTitle="????????????????????????"+Out_reId+"???";
			if(Out_act.equals("0") && receiver.getUserName().equals(riskevent.getReCreator()) 
					&& receiver.getUserDep().equals(riskevent.getReIndep())
					)
			{
				//??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
				//?????????????????????????????????1914->1631->...->1914->1631->issuer,??????1631??????????????????????????????????????????   ???????????????
				//Out_System_Flag=out,??????????????????????????????????????????????????????Out_System_Flag=in,?????????????????????action
				//TaskUrl="riskInput/Out_RiskEventInputQueryAction?current_pagenum=1&userid="+LogonName;//??????????????????
				TaskUrl="default/loginSingleSystemAction?userid="+LogonName_First;//??????????????????
				TaskTitle="????????????????????????"+Out_reId+"???";
			}
			Date d=new Date();
			DateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ReceiveTime=f.format(d);//????????????
			String CreatorName=riskevent.getReCreator();//???????????????
			String CreatorOrgName="";//?????????????????????
			dModel=new Department();
			dModel=this.getDepartmentDao().findById(riskevent.getReIndep());
			if(null != dModel) CreatorOrgName=dModel.getDepName();
			String PreUserName=Out_us.getUserName();//????????????
			String PreOrgName="";////??????????????????
			dModel=new Department();
			dModel=this.getDepartmentDao().findById(Out_us.getUserDep());
			if(null != dModel) PreOrgName=dModel.getDepName();
			//??????????????????xml????????????
			String xml="";
			ToDoWebServiceAction toDoWebServiceAction=new ToDoWebServiceAction();
			xml=toDoWebServiceAction.getXml(TaskId, TaskApp, TaskUrl, TaskTitle, LogonName, 
					UserName, OrgName, ReceiveTime, CreatorName, CreatorOrgName, PreUserName, PreOrgName
					);
			//??????myeclipse???????????????WebService?????????????????????
			PendingTaskPortService service = new PendingTaskPortService();  
			PendingTaskPortServiceSoap soap = service.getPendingTaskPortServiceSoap();  
			Holder<Boolean> updateTasksResult = new Holder<Boolean>();//??????C++?????? 
			Holder<String> errorMsg = new Holder<String>();//??????C++?????? 
			soap.updateTasks(xml, updateTasksResult, errorMsg);
			if(true==updateTasksResult.value)
			{
				System.out.println("?????????????????????"+errorMsg.value+"\n");
			}
			else
			{
				System.out.println("?????????????????????"+errorMsg.value+"\n");
			}
		}catch(Exception e){
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
	
	
	
	/*
	 * ?????????????????????
	 */
	public String riskeventpass()
	{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Users us = (Users)session.getAttribute("loginUser");
		
		publishRiskEvent(this.getId());
		getCheckRisk();
		
		return "success";
	}
	
	
	private void publishRiskEvent(String reId) {
		
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Users us = (Users)session.getAttribute("loginUser");
		riskevent=this.getRiskEventDao().findById(reId);	
		String inputdate = riskevent.getReDate();
		RiskManage rm = this.getRiskManageDao().findById(reId);
		String plandate = rm.getRmPlandate();
		String planyear=plandate.substring(0, 4);
		String planmonth=plandate.substring(5, 7);
		String planquarter=month2Quarter(planmonth)+"";
		String depIdDeal=riskevent.getReIndep();
		String depNameDeal=this.departmentDao.getdepname(depIdDeal);
		planmonth=Integer.parseInt(planmonth)+"";
		
		String inputyear = inputdate.substring(0, 4);
		String inputmonth = inputdate.substring(5, 7);
		String inputQuarter = month2Quarter(inputmonth)+"";
		
		
		String year = "2015";
		String quarter = "4";
		if(request.getParameter("year")!=null&&!("".equals(request.getParameter("year")))){
			year = request.getParameter("year");
		}
		if(request.getParameter("quarter")!=null&&!("".equals(request.getParameter("year")))){
			quarter = request.getParameter("quarter");
		}		
		String depId=riskevent.getReIndep();
		inputmonth = Integer.parseInt(inputmonth)+"";
		riskrecord = new RiskRecord();
		RiskAssessSituation riskassesssituation = new RiskAssessSituation();
		// ???riskrecord???????????????????????????
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		riskrecord.setRrBehaviour("1");// ???????????????1????????????
		riskrecord.setRrReId(reId);
		riskrecord.setRrVerifier(us.getUserId());
		riskrecord.setRrTime(df.format(date));
		riskrecord.setRrView(this.feedback);
		riskrecord.setRrStatus(riskevent.getReState());
		this.getRiskRecordDao().save(riskrecord);
		
		String kaoheyear = inputyear;
		String kaohemonth = inputmonth;
		
		//?????????????????????????????????????????????????????????????????????
		if((inputyear + inputQuarter).compareTo((year + quarter)) < 0) {
			
			kaoheyear = year;
			kaohemonth = ((Integer.parseInt(quarter) -1) * 3 + 1) + "";
			
		}
		else if((inputyear + inputQuarter).compareTo((year + quarter)) > 0) {
			
			kaoheyear = year;
			kaohemonth = (Integer.parseInt(quarter) * 3) + "";
			
		}
		else {
			kaoheyear = inputyear;
			kaohemonth = inputmonth;
		}
		
		//2015-12-08?????????
		/*checkinput = this.getRiskAssessDao().CheckInput(kaoheyear,kaohemonth,depId);
		if(checkinput==null || checkinput.size()<=0){
			riskassessaccount.setRaDepId(depId);
			riskassessaccount.setRaDepName(depName);
			riskassessaccount.setRaDepProperty(depProperty);
			riskassessaccount.setRaYear(inputyear);
			riskassessaccount.setRaQuarter(inputQuarter);
			riskassessaccount.setRaMonth(inputmonth);
			riskassessaccount.setRaNumberInput(1);
			this.getRiskAssessDao().save(riskassessaccount);
		}
		else if(riskevent.getReCheckflag().intValue() != 1){
			// ?????????????????????,???????????????????????????????????????????????????????????????
			RiskAssessAccount rau = (RiskAssessAccount) checkinput.get(0);
			int inputNumber=rau.getRaNumberInput();
			rau.setRaNumberInput(inputNumber+1);
			this.getRiskAssessDao().merge(rau);
		}*/
		
		//2015-12-08?????????
		if(riskevent.getReCheckflag().intValue() != 1){
			
			this.getRiskEventDao().updateAssess(reId,year,quarter);
			List<?> list = this.getRiskAssessDao().findList(depId, kaoheyear, kaohemonth);
			if(list!=null&&list.size()!=0){
				this.getRiskAssessDao().updateRiskAccount(depId, kaoheyear, kaohemonth);
			}else{
				Department d = this.getDepartmentDao().findById(depId);
				RiskAssessAccount raa = new RiskAssessAccount();
				raa.setRaDepId(d.getDepId());
				raa.setRaDepName(d.getDepName());
				raa.setRaYear(year);
				raa.setRaQuarter(quarter);
				raa.setRaMonth(kaohemonth);
				raa.setRaNumberInput(1);
				raa.setRaDepProperty(d.getDepSort()+"");
				this.getRiskAssessDao().save(raa);
			}
		}  //2015-12-08?????????

		List checkDeal = this.getRiskassesssituationDao().checkDeal(planyear,planquarter,depIdDeal);
		if(checkDeal==null ||checkDeal.size()<=0){
			riskassesssituation.setRaDepId(depIdDeal);
			riskassesssituation.setRaDepName(depNameDeal);
			riskassesssituation.setRaNumberDeal(0);
			riskassesssituation.setRaNumberNeed(1);
			riskassesssituation.setRaQuarter(planquarter);
			riskassesssituation.setRaYear(planyear);
			this.getRiskassesssituationDao().save(riskassesssituation);
		}
		else{
			RiskAssessSituation rau = (RiskAssessSituation) checkDeal.get(0);
			int NumberNeed=rau.getRaNumberNeed();
			rau.setRaNumberNeed(NumberNeed+1);
			this.getRiskassesssituationDao().merge(rau);
		}
		
		//??????riskevent???????????????
		//this.getRiskEventDao().updatepassevent(this.id,this.nextstate,this.act);
		riskevent.setReState("*");
		riskevent.setReAct("1");
		riskevent.setReVerifier(null);
		riskevent.setReLastdate(df.format(date));
		riskevent.setReCheckflag(1);		//???????????????,???????????????????????????,????????????????????????
		this.getRiskEventDao().merge(riskevent);
		
		//?????????????????????????????????
		//deleteTasks(this.getId());
		/*
		 * ??????????????????????????????????????????Tomcat???????????????
		 */
		
		inThreads("delete",us,reId,"","");
		
	}
	
private void publishRiskEvent1(String reId) {
		
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Users us = (Users)session.getAttribute("loginUser");
		riskevent=this.getRiskEventDao().findById(reId);	
		String inputdate = riskevent.getReDate();
		RiskManage rm = this.getRiskManageDao().findById(reId);
		String plandate = rm.getRmPlandate();
		String planyear=plandate.substring(0, 4);
		String planmonth=plandate.substring(5, 7);
		String planquarter=month2Quarter(planmonth)+"";
		String depIdDeal=riskevent.getReIndep();
		String depNameDeal=this.departmentDao.getdepname(depIdDeal);
		planmonth=Integer.parseInt(planmonth)+"";
		
		String inputyear = inputdate.substring(0, 4);
		String inputmonth = inputdate.substring(5, 7);
		String inputQuarter = month2Quarter(inputmonth)+"";
		
		Calendar now=Calendar.getInstance();
		System.out.println("++++"+request.getParameter("year"));
		System.out.println("++++"+request.getParameter("quarter"));
		this.years=now.get(Calendar.YEAR);
		this.quarters=now.get(Calendar.MONTH)+1;
		
		String year = "2015";
		String quarter = "4";
		if(request.getParameter("year")!=null&&!("".equals(request.getParameter("year")))){
			year = request.getParameter("year");
		}
		if(request.getParameter("quarter")!=null&&!("".equals(request.getParameter("year")))){
			quarter = request.getParameter("quarter");
		}		
		String depId=riskevent.getReIndep();
		inputmonth = Integer.parseInt(inputmonth)+"";
		riskrecord = new RiskRecord();
		RiskAssessSituation riskassesssituation = new RiskAssessSituation();
		// ???riskrecord???????????????????????????
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		riskrecord.setRrBehaviour("1");// ???????????????1????????????
		riskrecord.setRrReId(reId);
		riskrecord.setRrVerifier(us.getUserId());
		riskrecord.setRrTime(df.format(date));
		riskrecord.setRrView(this.feedback);
		riskrecord.setRrStatus(riskevent.getReState());
		this.getRiskRecordDao().save(riskrecord);
		
		String kaoheyear = inputyear;
		String kaohemonth = inputmonth;
		
		//?????????????????????????????????????????????????????????????????????
		if((inputyear + inputQuarter).compareTo((year + quarter)) < 0) {
			
			kaoheyear = year;
			kaohemonth = ((Integer.parseInt(quarter) -1) * 3 + 1) + "";
			
		}
		else if((inputyear + inputQuarter).compareTo((year + quarter)) > 0) {
			
			kaoheyear = year;
			kaohemonth = (Integer.parseInt(quarter) * 3) + "";
			
		}
		else {
			kaoheyear = inputyear;
			kaohemonth = inputmonth;
		}
		
		//2015-12-08?????????
		/*checkinput = this.getRiskAssessDao().CheckInput(kaoheyear,kaohemonth,depId);
		if(checkinput==null || checkinput.size()<=0){
			riskassessaccount.setRaDepId(depId);
			riskassessaccount.setRaDepName(depName);
			riskassessaccount.setRaDepProperty(depProperty);
			riskassessaccount.setRaYear(inputyear);
			riskassessaccount.setRaQuarter(inputQuarter);
			riskassessaccount.setRaMonth(inputmonth);
			riskassessaccount.setRaNumberInput(1);
			this.getRiskAssessDao().save(riskassessaccount);
		}
		else if(riskevent.getReCheckflag().intValue() != 1){
			// ?????????????????????,???????????????????????????????????????????????????????????????
			RiskAssessAccount rau = (RiskAssessAccount) checkinput.get(0);
			int inputNumber=rau.getRaNumberInput();
			rau.setRaNumberInput(inputNumber+1);
			this.getRiskAssessDao().merge(rau);
		}*/
		
		//2015-12-08?????????
		if(riskevent.getReCheckflag().intValue() != 1){
			
			this.getRiskEventDao().updateAssess(reId,year,quarter);
			List<?> list = this.getRiskAssessDao().findList(depId, kaoheyear, kaohemonth);
			if(list!=null&&list.size()!=0){
				this.getRiskAssessDao().updateRiskAccount(depId, kaoheyear, kaohemonth);
			}else{
				Department d = this.getDepartmentDao().findById(depId);
				RiskAssessAccount raa = new RiskAssessAccount();
				raa.setRaDepId(d.getDepId());
				raa.setRaDepName(d.getDepName());
				raa.setRaYear(year);
				raa.setRaQuarter(quarter);
				raa.setRaMonth(kaohemonth);
				raa.setRaNumberInput(1);
				raa.setRaDepProperty(d.getDepSort()+"");
				this.getRiskAssessDao().save(raa);
			}
		}  //2015-12-08?????????

		List checkDeal = this.getRiskassesssituationDao().checkDeal(planyear,planquarter,depIdDeal);
		if(checkDeal==null ||checkDeal.size()<=0){
			riskassesssituation.setRaDepId(depIdDeal);
			riskassesssituation.setRaDepName(depNameDeal);
			riskassesssituation.setRaNumberDeal(0);
			riskassesssituation.setRaNumberNeed(1);
			riskassesssituation.setRaQuarter(planquarter);
			riskassesssituation.setRaYear(planyear);
			this.getRiskassesssituationDao().save(riskassesssituation);
		}
		else{
			RiskAssessSituation rau = (RiskAssessSituation) checkDeal.get(0);
			int NumberNeed=rau.getRaNumberNeed();
			rau.setRaNumberNeed(NumberNeed+1);
			this.getRiskassesssituationDao().merge(rau);
		}
		System.out.print("============="+String.valueOf(this.quarters));
		//??????riskevent???????????????
		//this.getRiskEventDao().updatepassevent(this.id,this.nextstate,this.act);
		riskevent.setReState("*");
		riskevent.setReAct("1");
		riskevent.setReVerifier(null);
		riskevent.setReLastdate(df.format(date));
		//2019.5.9
		//riskevent.setReAssessYear(String.valueOf(this.years));
		//riskevent.setReAssessQuarter(String.valueOf((this.quarters-1)/3+1));
		riskevent.setReAssessYear(request.getParameter("year"));
		riskevent.setReAssessQuarter(request.getParameter("quarter"));
		riskevent.setReCheckflag(1);		//???????????????,???????????????????????????,????????????????????????
		this.getRiskEventDao().merge(riskevent);
		
		//?????????????????????????????????
		//deleteTasks(this.getId());
		/*
		 * ??????????????????????????????????????????Tomcat???????????????
		 */
		
		inThreads("delete",us,reId,"","");
		
	}
	
	@SuppressWarnings("unchecked")
	public String getFlowRule() {
		flowRuleList = new LinkedList<FlowRule>();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			List<RiskDepQueryView> list = this.getRiskDepQueryViewDao().findByProperty("reId", this.resId);
			this.deparment = list.get(0).getDepName();
			this.creator = list.get(0).getReCreator();
			this.creatdate = list.get(0).getReDate();
			request.setAttribute("title", list.get(0).getReEventname()+"-???????????????");
			if (list.get(0).getReVerifier() != null && !list.get(0).getReVerifier().equals("")) {
				String verifier = list.get(0).getReVerifier();
				UsersQueryView user = this.getUsersQueryViewDao().findById(verifier);
				this.waitfordepart = user.getDepName();
				this.waitforperson = user.getUserName();
			}
			else {
				this.waitfordepart = "";
				this.waitforperson = "";
			}

			flowRuleList = this.getFlowRuleDao().findByProperty("riskId",riskId);

			if (flowRuleList.get(0).getFrStatus().equals("1")) {
				while (flowRuleList.size() > 1
						&& flowRuleList.get(1).getFrDepart().equals(list.get(0).getDepId())) {
					flowRuleList.remove(1);
				}
			}

			for (int i = 0; i < flowRuleList.size(); i++) {
				String dep = flowRuleList.get(i).getFrDepart();
				if (dep.equals("localdep")) {
					List<RiskRecord> records = this.getRiskRecordDao().findByProperty("rrReId", "rrStatus",resId, flowRuleList.get(i).getFrStatus());
					if (records.size() != 0) {
						String verifier = records.get(0).getRrVerifier();
						UsersQueryView user = this.getUsersQueryViewDao().findById(verifier);
						flowRuleList.get(i).setDeparment(user.getDepName());
						flowRuleList.get(i).setPassuser(user.getUserName());
						flowRuleList.get(i).setPassdate(records.get(0).getRrTime());
						flowRuleList.get(i).setYesOrno(records.get(0).getRrBehaviour());
					} else {
						flowRuleList.get(i).setDeparment(deparment);
						flowRuleList.get(i).setPassuser("??????");
						flowRuleList.get(i).setPassdate("??????");
						flowRuleList.get(i).setYesOrno("-1");
					}
				} else {
					List<RiskRecord> records = this.getRiskRecordDao().findByProperty("rrReId", "rrStatus",resId, flowRuleList.get(i).getFrStatus());
					if (records.size() == 0) {
						List<Department> deplist = this.getDepartmentDao().findByProperty("depId", dep);
						if (deplist.size() != 0) {
							flowRuleList.get(i).setDeparment(deplist.get(0).getDepName());
						} else {
							flowRuleList.get(i).setDeparment("?????????");
						}
						flowRuleList.get(i).setPassuser("??????");
						flowRuleList.get(i).setPassdate("??????");
						flowRuleList.get(i).setYesOrno("-1");
					} else {
						String verifier = records.get(0).getRrVerifier();
						UsersQueryView user = this.getUsersQueryViewDao().findById(verifier);
						flowRuleList.get(i).setDeparment(user.getDepName());
						flowRuleList.get(i).setPassuser(user.getUserName());
						flowRuleList.get(i).setPassdate(records.get(0).getRrTime());
						flowRuleList.get(i).setYesOrno(records.get(0).getRrBehaviour());
					}
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return "fali";
		}
		return "success";
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
	
	/*
	 * ??????????????????
	 */
	public String rERecall() 
	{
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			riskevent=new RiskEvent();
			riskevent = this.getRiskEventDao().findById(this.getId());
			if(null==riskevent){return this.getRecallPage()+"fail";}
			riskdep = this.getRiskDepQueryViewDao().findById(this.getId());
		
			RiskRecord record = this.getRiskRecordDao().getLastRecord(this.getId());
			// ????????????????????????????????????
			if(record != null && record.getRrVerifier().equals(us.getUserId()) && !record.getRrView().equals("??????")) {
				this.setFlowtoperson(us.getUserId());
				this.setAct("0");
				this.setFeedback("??????");
				riskevent.setReState(record.getRrStatus());
				this.getRiskEventDao().merge(riskevent);
				flowtoNext();
				return this.getRecallPage()+"success";
			}
			else {
				return this.getRecallPage()+"fail";
			}
			
			} catch (Exception e) {
				e.printStackTrace();
				return this.getRecallPage()+"fail";
			}
	}
	
	/*
	 * ??????????????????
	 */
	public void rERevocation() 
	{
	try {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Users us = (Users) session.getAttribute("loginUser");
		String reId = request.getParameter("reId");
		RiskEvent riskevent=new RiskEvent();
		riskevent = this.getRiskEventDao().findById(reId);
		if(null==riskevent) {
			
			out.write("fail");
			out.flush();
			return;
		}
		RiskRecord record = this.getRiskRecordDao().getLastRecord(reId);
		// ????????????????????????????????????
		if(record != null && record.getRrVerifier().equals(us.getUserId()) && !record.getRrView().equals("??????")) {
			this.setFlowtoperson(us.getUserId());
			this.setAct("0");
			this.setFeedback("??????");
			this.setNextstate(record.getRrStatus());
			this.setId(reId);
			riskevent.setReState(record.getRrStatus());
			this.getRiskEventDao().merge(riskevent);
			flowtoNext();
			String responseStr = "riskFlow/DepVerifyAction?riskeventid="+riskevent.getReId();
			//???????????????????????????,??????????????????????????????
			if(record.getRrStatus().equals("0")) {
				responseStr = "riskInput/getRiskEventAction?id="+riskevent.getReId()+"&updateFlag=update";
			}
			
			out.write(responseStr);
			out.flush();
		}
		else {
			out.write("fail");
			out.flush();
		}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
