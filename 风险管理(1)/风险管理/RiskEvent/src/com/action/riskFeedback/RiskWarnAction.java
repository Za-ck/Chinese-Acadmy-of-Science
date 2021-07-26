package com.action.riskFeedback;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.EventWarnViewDAO;
import com.dao.RiskEventDAO;
import com.dao.RiskImpactDAO;
import com.dao.RiskManageDAO;
import com.dao.RiskRecordDAO;
import com.dao.RiskStrategyDAO;
import com.dao.StrategyDAO;
import com.dao.UserStrategyDAO;
import com.model.EventWarnView;
import com.model.Risk;
import com.model.RiskEvent;
import com.model.RiskImpact;
import com.model.RiskManage;
import com.model.RiskRecord;
import com.model.RiskStrategy;
import com.model.Strategy;
import com.model.UserStrategy;
import com.model.Users;



/**
 * @author Administrator
 *
 */
public class RiskWarnAction {
private String reId;
private String riskName;
private String reIndep;
private String rmPlandate;
private String reDate;
private int dateCha;
private List<EventWarnView> riskWarnList;
private List<EventWarnView> riskWarnListChildren;
private EventWarnViewDAO riskWarnDao;
private EventWarnView eventWarnView;
private UserStrategyDAO userStrategyDao;//用户策略
private RiskStrategyDAO riskStrategyDao;
private int strategyId;
private StrategyDAO strategyDao;
private UserStrategy userStrategyTi;
private RiskStrategy riskStrategyTi;
private Strategy strategyTi;
private List<UserStrategy> userStrategyList;
private List<RiskStrategy> riskStrategy;
private List<RiskStrategy> riskStrategyList;
private String riskDep;
public  String dateFrom;
public  String dateTo;
private List<RiskEvent> reList;
private RiskEventDAO riskEventDao;
private RiskImpactDAO riskImpactDao;
private RiskManageDAO riskManageDao;
private String yearBegin;
private String yearEnd;
private String dateFlag;
private String riskId;
private String risktype;
private String riskname;
private String riskremark;
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
private Risk risk;
private RiskEvent riskevent;
private RiskImpact riskimpact;
private int current_pagenum=1;//当前页码
private int  pageNum=10;//每页的显示数据记录数
private RiskRecord riskrecord;
private List<RiskRecord> rrList;
private RiskRecordDAO riskRecordDao;
private String verifier;
private String verifyview;

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
public RiskRecordDAO getRiskRecordDao() {
	return riskRecordDao;
}
public void setRiskRecordDao(RiskRecordDAO riskRecordDao) {
	this.riskRecordDao = riskRecordDao;
}
public RiskRecord getRiskrecord() {
	return riskrecord;
}
public void setRiskrecord(RiskRecord riskrecord) {
	this.riskrecord = riskrecord;
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
public List<EventWarnView> getRiskWarnListChildren() {
	return riskWarnListChildren;
}
public void setRiskWarnListChildren(List<EventWarnView> riskWarnListChildren) {
	this.riskWarnListChildren = riskWarnListChildren;
}
public List<RiskStrategy> getRiskStrategyList() {
	return riskStrategyList;
}
public void setRiskStrategyList(List<RiskStrategy> riskStrategyList) {
	this.riskStrategyList = riskStrategyList;
}
public int getStrategyId() {
	return strategyId;
}
public void setStrategyId(int strategyId) {
	this.strategyId = strategyId;
}
public List<RiskStrategy> getRiskStrategy() {
	return riskStrategy;
}
public void setRiskStrategy(List<RiskStrategy> riskStrategy) {
	this.riskStrategy = riskStrategy;
}
public List<UserStrategy> getUserStrategyList() {
	return userStrategyList;
}
public void setUserStrategyList(List<UserStrategy> userStrategyList) {
	this.userStrategyList = userStrategyList;
}
public UserStrategy getUserStrategyTi() {
	return userStrategyTi;
}
public void setUserStrategyTi(UserStrategy userStrategyTi) {
	this.userStrategyTi = userStrategyTi;
}
public RiskStrategy getRiskStrategyTi() {
	return riskStrategyTi;
}
public void setRiskStrategyTi(RiskStrategy riskStrategyTi) {
	this.riskStrategyTi = riskStrategyTi;
}
public Strategy getStrategyTi() {
	return strategyTi;
}
public void setStrategyTi(Strategy strategyTi) {
	this.strategyTi = strategyTi;
}
public UserStrategyDAO getUserStrategyDao() {
	return userStrategyDao;
}
public void setUserStrategyDao(UserStrategyDAO userStrategyDao) {
	this.userStrategyDao = userStrategyDao;
}
public RiskStrategyDAO getRiskStrategyDao() {
	return riskStrategyDao;
}
public void setRiskStrategyDao(RiskStrategyDAO riskStrategyDao) {
	this.riskStrategyDao = riskStrategyDao;
}
public StrategyDAO getStrategyDao() {
	return strategyDao;
}
public void setStrategyDao(StrategyDAO strategyDao) {
	this.strategyDao = strategyDao;
}
public int getDateCha() {
	return dateCha;
}
public void setDateCha(int dateCha) {
	this.dateCha = dateCha;
}
public EventWarnView getEventWarnView() {
	return eventWarnView;
}
public void setEventWarnView(EventWarnView eventWarnView) {
	this.eventWarnView = eventWarnView;
}
public String getRiskDep() {
	return riskDep;
}
public void setRiskDep(String riskDep) {
	this.riskDep = riskDep;
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
public List<RiskEvent> getReList() {
	return reList;
}
public void setReList(List<RiskEvent> reList) {
	this.reList = reList;
}

public RiskEventDAO getRiskEventDao() {
	return riskEventDao;
}
public void setRiskEventDao(RiskEventDAO riskEventDao) {
	this.riskEventDao = riskEventDao;
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
public Risk getRisk() {
	return risk;
}
public void setRisk(Risk risk) {
	this.risk = risk;
}
public RiskEvent getRiskevent() {
	return riskevent;
}
public void setRiskevent(RiskEvent riskevent) {
	this.riskevent = riskevent;
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
public void setRiskmanage(RiskManage riskmanage) {
	this.riskmanage = riskmanage;
}

private RiskManage riskmanage;



public String getReId() {
	return reId;
}
public void setReId(String reId) {
	this.reId = reId;
}
public String getRiskName() {
	return riskName;
}
public void setRiskName(String riskName) {
	this.riskName = riskName;
}
public String getReIndep() {
	return reIndep;
}
public void setReIndep(String reIndep) {
	this.reIndep = reIndep;
}
public String getRmPlandate() {
	return rmPlandate;
}
public void setRmPlandate(String rmPlandate) {
	this.rmPlandate = rmPlandate;
}
public String getReDate() {
	return reDate;
}
public void setReDate(String reDate) {
	this.reDate = reDate;
}

public List<EventWarnView> getRiskWarnList() {
	return riskWarnList;
}
public void setRiskWarnList(List<EventWarnView> riskWarnList) {
	this.riskWarnList = riskWarnList;
}
public EventWarnViewDAO getRiskWarnDao() {
	return riskWarnDao;
}
public void setRiskWarnDao(EventWarnViewDAO riskWarnDao) {
	this.riskWarnDao = riskWarnDao;
}
/*
 * 获取相应的风险事件列表
 */
	@SuppressWarnings("unchecked")
	public String getWarnList() {
		try {			
			List<EventWarnView> risktemp= (List<EventWarnView>)ServletActionContext.getRequest().getSession().getAttribute("riskWarnList");
			riskWarnListChildren=new LinkedList<EventWarnView>();			
			if(risktemp==null) return "fail";
			for(int i=0;i<risktemp.size();i++){
			if(risktemp.get(i).getStragyId()==strategyId){
					riskWarnListChildren.add(risktemp.get(i));
				}				
			}
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",riskWarnListChildren.size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
		List<EventWarnView> riskWarnListChildren1=new LinkedList<EventWarnView>();
		if(current_pagenum*10>=riskWarnListChildren.size())
		{
			for(int i=(current_pagenum-1)*10;i<riskWarnListChildren.size();i++){
				riskWarnListChildren1.add(riskWarnListChildren.get(i));
			}
		}else{
			for(int i=(current_pagenum-1)*10;i<current_pagenum*10;i++){
				riskWarnListChildren1.add(riskWarnListChildren.get(i));
			}
		}
		ServletActionContext.getRequest().setAttribute("riskWarnListChildren", riskWarnListChildren1);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
	
		return "success";
	}

	/*
	 * 获取风险策略列表
	 */
	@SuppressWarnings("unchecked")
	public String getStrategyList() {
		try {
			Users u = (Users) ServletActionContext.getRequest().getSession()
					.getAttribute("loginUser");   //获得登陆用户的信息
			userStrategyList = new LinkedList<UserStrategy>();
			riskStrategy = new LinkedList<RiskStrategy>();
			userStrategyList = this.userStrategyDao.findByProperty(
					"department.depId", u.getUserDep());
			if (userStrategyList.size() > 0) {
				int stragyId = userStrategyList.get(0).getStrategy().getStrategyId();			
				riskStrategy = this.riskStrategyDao.findByProperty(
						"strategy.strategyId", stragyId, "order by stragValue");
				lawManage();
			}
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
	}

	/*if(riskStrategyList.size()>0)
	{
		riskWarnList.get(i).setStragyId(riskStrategyList.get(0).getRiskStrId());
		System.out.println(riskStrategyList.get(0).getRiskStrId()+"xxxxxxxxxxxxxxxxxx");
		riskWarnList.get(i).setValue(riskStrategyList.get(0).getStragStatue());
		riskWarnList.get(i).setStragColor(riskStrategyList.get(0).getStragColor());
	}
	*/
	
	

	@SuppressWarnings("unchecked")
	public String lawManage() {
		Users us = (Users) ServletActionContext.getRequest().getSession()
		.getAttribute("loginUser");
		this.reIndep=us.getUserDep(); //部门
		riskWarnList = new LinkedList<EventWarnView>();
		//riskWarnList =this.getRiskWarnDao().findWarn();
		//riskWarnList = this.getRiskWarnDao().findAll();
		//riskWarnList = this.getRiskWarnDao().findAll((current_pagenum-1)*pageNum,pageNum);
		int position = us.getUserPosition();
		/*if(position==1||position==3||position==5){
			String userName = us.getUserName();
			riskWarnList = this.getRiskWarnDao().findByUser(userName);
		}else if(position==2||position==4){
			riskWarnList = this.getRiskWarnDao().findYourself(reIndep);
		}else{
			riskWarnList = this.getRiskWarnDao().findAll();
		}*/
		
		if(position<=5){
			riskWarnList = this.getRiskWarnDao().findYourself(reIndep);
		}else{
			riskWarnList = this.getRiskWarnDao().findAll();
		}
		
		ServletActionContext.getRequest().setAttribute("riskWarnList", riskWarnList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
		if(riskWarnList.size()>0){
			for (int i = 0; i < riskWarnList.size(); i++) {
				String planDate = riskWarnList.get(i).getId().getRmPlandate();
				String year = planDate.substring(0, 4);
				String month = planDate.substring(5, 7);
				String day = planDate.substring(8, 10);
				String and = year + month + day;
				int pDate = Integer.parseInt(and);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String ly_time = sdf.format(new java.util.Date());
				String cyear = ly_time.substring(0, 4);
				String cmonth = ly_time.substring(5, 7);
				String cday = ly_time.substring(8, 10);
				String cand = cyear + cmonth + cday;
				int cDate = Integer.parseInt(cand);
				int cha = cDate - pDate;
				//System.out.println(cha);
				int chaZhi = compareDate(ly_time, planDate, 0);    //计算下时间差（计划时间与当前时间）
				riskWarnList.get(i).setChaZhi(chaZhi);
				Users u = (Users) ServletActionContext.getRequest().getSession()
					.getAttribute("loginUser");
			// this.reIndep=u.getDepartment().getDepId(); //部门
				userStrategyList = new LinkedList<UserStrategy>();
				userStrategyList = this.userStrategyDao.findByProperty(
					"department.depId", u.getUserDep());
				if (userStrategyList.size() > 0) {
					int varId = userStrategyList.get(0).getStrategy()
						.getStrategyId();// 得到Strategy的Id,即StrategyId
					riskStrategyList = new LinkedList<RiskStrategy>();
					riskStrategyList = this.riskStrategyDao.FeedBack(varId, chaZhi);
				}
				if (riskStrategyList.size() > 0) {
					riskWarnList.get(i).setStragyId(       //在riskwarnList中把属性StragyId设置成RiskStrId。
						riskStrategyList.get(0).getRiskStrId());
					riskWarnList.get(i).setValue(
						riskStrategyList.get(0).getStragStatue());
					riskWarnList.get(i).setStragColor(
						riskStrategyList.get(0).getStragColor());
				}

			}
		}
		if (riskWarnList.size() > 0)
		{
			ServletActionContext.getRequest().getSession().setAttribute("riskWarnList", riskWarnList);	
			return "success";
		}
			
		else
			return "fail";

	}

	public String riskQuery() {
		riskWarnList = new LinkedList<EventWarnView>();

		riskWarnList = this.getRiskWarnDao().findByIndep(reIndep);

		for (int i = 0; i < riskWarnList.size(); i++) {
			String planDate = riskWarnList.get(i).getId().getRmPlandate();

			//System.out.println("时间" + planDate);

			String year = planDate.substring(0, 4);
			String month = planDate.substring(5, 7);
			String day = planDate.substring(8, 10);
			String and = year + month + day;
			int pDate = Integer.parseInt(and);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String ly_time = sdf.format(new java.util.Date());
			String cyear = ly_time.substring(0, 4);
			String cmonth = ly_time.substring(5, 7);
			String cday = ly_time.substring(8, 10);
			String cand = cyear + cmonth + cday;
			int cDate = Integer.parseInt(cand);
			int cha = cDate - pDate;
			//System.out.println(cha);
			int chaZhi = compareDate(ly_time, planDate, 0);

			//System.out.println(chaZhi);

			riskWarnList.get(i).setChaZhi(chaZhi);

			Users u = (Users) ServletActionContext.getRequest().getSession()
					.getAttribute("loginUser");
			// this.reIndep=u.getDepartment().getDepId(); //部门
			userStrategyList = new LinkedList<UserStrategy>();
			userStrategyList = this.userStrategyDao.findByProperty(
					"department.depId", u.getUserDep());
			if (userStrategyList.size() > 0) {
				int varId = userStrategyList.get(0).getStrategy()
						.getStrategyId();// 得到Strategy的Id,即StrategyId
				riskStrategyList = new LinkedList<RiskStrategy>();
				riskStrategyList = this.riskStrategyDao.FeedBack(varId, chaZhi);
				// 得到riskStrategy中属性为varId的对象列表
			}
			if (riskStrategyList.size() > 0) {
				riskWarnList.get(i).setStragyId(
						riskStrategyList.get(0).getRiskStrId());
				riskWarnList.get(i).setValue(
						riskStrategyList.get(0).getStragStatue());
				riskWarnList.get(i).setStragColor(
						riskStrategyList.get(0).getStragColor());
			}

		}

		//System.out.println(riskWarnList.size());
		if (riskWarnList.size() > 0)
			{		
			return "success";
			}
		else
			return "fail";

	}

	public static int compareDate(String date1, String date2, int stype) {
		int n = 0;

		String[] u = { "天", "月", "年" };
		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			//System.out.println("wrong occured");
		}
		// List list = new ArrayList();
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			// list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}

		n = n - 1;

		if (stype == 2) {
			n = (int) n / 365;
		}

		//System.out.println(date1 + " -- " + date2 + " 相差多少" + u[stype] + ":"+ n);
		return n;
	}

}
