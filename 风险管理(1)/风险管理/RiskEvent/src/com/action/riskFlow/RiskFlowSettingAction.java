package com.action.riskFlow;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.DepartmentDAO;
import com.dao.FlowRuleDAO;
import com.dao.RiskDAO;
import com.model.DepWarn;
import com.model.Department;
import com.model.FlowRule;
import com.model.Risk;
import com.model.RiskEvent;
import com.model.RiskStrategy;
import com.model.Strategy;
import com.model.Users;

class flows{
	private String ids;
	private String name;
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
public class RiskFlowSettingAction {
	private FlowRuleDAO flowRuleDao;
	private RiskDAO riskDAO;
    private String processName;
    private String riskType;
	private List<FlowRule> flowRuleLists;
	private List<flows> listFlows;
	private int current_pagenum=1;//当前页码
	private int  pageNum=10;//每页的显示数据记录数
	private String listinfo;
	private String updateFlag;
	private String ids;
    private List<String> idCheck;
    private List<Department> alldepList;
    private DepartmentDAO departmentDao;
    private List<Risk> riskList;
    private String  flownumber;
    private List<Risk> flownumberList;
    public  String dateFrom="";
    private String actionName="";
    private String riskTypeString="";
    
	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getFlownumber() {
		return flownumber;
	}

	public void setFlownumber(String flownumber) {
		this.flownumber = flownumber;
	}

	public List<Risk> getFlownumberList() {
		
		Risk w1=new Risk();
		w1.setRiskId("--请选择--");
		
		flownumberList=new LinkedList<Risk>();
		flownumberList.add(w1);
		List<Risk> eventList =new LinkedList<Risk>();
		eventList=this.getRiskDAO().findAll();
		flownumberList.addAll(eventList);
		
		return flownumberList;
	}

	public void setFlownumberList(List<Risk> flownumberList) {
		this.flownumberList = flownumberList;
	}

	public List<Risk> getRiskList() {
		return riskList;
	}

	public void setRiskList(List<Risk> riskList) {
		this.riskList = riskList;
	}

	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public List<Department> getAlldepList() {
		return alldepList;
	}

	public void setAlldepList(List<Department> alldepList) {
		this.alldepList = alldepList;
	}

	public List<String> getIdCheck() {
		return idCheck;
	}

	public void setIdCheck(List<String> idCheck) {
		this.idCheck = idCheck;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getListinfo() {
		return listinfo;
	}

	public void setListinfo(String listinfo) {
		this.listinfo = listinfo;
	}

	public List<flows> getListFlows() {
		return listFlows;
	}

	public void setListFlows(List<flows> listFlows) {
		this.listFlows = listFlows;
	}

	public RiskDAO getRiskDAO() {
		return riskDAO;
	}

	public void setRiskDAO(RiskDAO riskDAO) {
		this.riskDAO = riskDAO;
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

	
	public List<FlowRule> getFlowRuleLists() {
		return flowRuleLists;
	}

	public void setFlowRuleLists(List<FlowRule> flowRuleLists) {
		this.flowRuleLists = flowRuleLists;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public FlowRuleDAO getFlowRuleDao() {
		return flowRuleDao;
	}

	public void setFlowRuleDao(FlowRuleDAO flowRuleDao) {
		this.flowRuleDao = flowRuleDao;
	}
	
	
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}	
	public String getRiskTypeString() {
		return riskTypeString;
	}

	public void setRiskTypeString(String riskTypeString) {
		this.riskTypeString = riskTypeString;
	}

	/*
	 * 查找
	 */
	public String getRiskFlow(){
		
		//getFlownumberList();
		try
		{
			//得到当年年份
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy");
			
			this.setDateFrom(formatter.format(new Date()));
			
			
	    this.listFlows=new LinkedList<flows>();
		
	    List<Object[]> lists=this.getFlowRuleDao().findRiskFlowAllNew((current_pagenum-1)*pageNum,pageNum);
	    
		
		for(int i=0;i<lists.size();i++){
			Object[] obj=lists.get(i);
			flows f= new flows();
			f.setIds(obj[0].toString());
			f.setName(obj[1].toString()+"策略");
			listFlows.add(f);
		}
		
		ServletActionContext.getRequest().setAttribute("listFlows",listFlows);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
		this.setActionName("riskFlow/getRiskFlows");
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	//按策略编号查询
	public String celuchaxun()
	{
		getFlownumberList();
		try
		{
	    this.listFlows=new LinkedList<flows>();
		List<Object[]> lists=this.getFlowRuleDao().celuQuery(flownumber, (current_pagenum-1)*pageNum,pageNum);
		for(int i=0;i<lists.size();i++){
			Object obj=lists.get(i);
			flows f= new flows();
			f.setIds(obj.toString());
			f.setName(obj.toString()+"风险策略");
			listFlows.add(f);
		}
		 	
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
		
	}
	/*
	 * 删除
	 */
	public String deleteRiskFlow(){
		try{
			int i = 0;
			for (i = 0; i < this.idCheck.size(); i++) {
				this.getFlowRuleDao().delete(this.idCheck.get(i));
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		getRiskFlow();
		return "success";
	}
	/*
	 * 添加
	 */
	public String addRisk(){
		
		alldepList=new LinkedList<Department>();
		alldepList=this.getDepartmentDao().findAll();
		
		riskList=new LinkedList<Risk>();
		//riskList=this.getRiskDAO().findAll();
		if(this.getDateFrom().length()<4)
		{
			//为空，和小于四位输入错误的情况
			//得到当年年份
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy");
			this.setDateFrom(formatter.format(new Date()));
			//this.setDateFrom("1900");
		}
		//正常，和大于四位输入错误的情况
		String year=this.getDateFrom().substring(0, 4);
		
		//获取年份输入框，的风险
		riskList=this.getRiskDAO().findByYear(year);
		
		flowRuleLists=null;
		return "success";
	}
	/*
	 * 修改遍历
	 */
	public String updateRisk(){
		try{
			this.flowRuleLists=new LinkedList<FlowRule>();
			System.out.println("+++++++"+this.ids);
			//ServletActionContext.getRequest().setAttribute("ids",this.ids);
		    this.flowRuleLists=this.getFlowRuleDao().findByFlow("riskId", this.ids);
		    alldepList=new LinkedList<Department>();
			alldepList=this.getDepartmentDao().findAll();
			riskList=new LinkedList<Risk>();
			riskList=this.getRiskDAO().findAllbyName();
		    this.riskType=this.ids;
		}
		catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	public String updateRisk1(String ids){
		try{
			this.flowRuleLists=new LinkedList<FlowRule>();
			System.out.println("+++++++"+this.ids);
			//ServletActionContext.getRequest().setAttribute("ids",this.ids);
		    this.flowRuleLists=this.getFlowRuleDao().findByFlow("riskId", ids);
		    alldepList=new LinkedList<Department>();
			alldepList=this.getDepartmentDao().findAll();
			riskList=new LinkedList<Risk>();
			riskList=this.getRiskDAO().findAllbyName();
		    this.riskType=ids;
		}
		catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	public String anyearquery()
	{
		if(this.getDateFrom().length()<4)
		{
			//为空，和小于四位输入错误的情况
			this.setDateFrom("1900");
		}
		//正常，和大于四位输入错误的情况
		String year=this.getDateFrom().substring(0, 4);
		
		try
		{
	    this.listFlows=new LinkedList<flows>();
		//List<Object[]> lists=this.getFlowRuleDao().yearQueryNew(year, (current_pagenum-1)*pageNum,pageNum);
	    List<Object[]> lists=this.getFlowRuleDao().yearQueryNew(year,this.getRiskTypeString(), (current_pagenum-1)*pageNum,pageNum);
	    
	    
		for(int i=0;i<lists.size();i++){
			Object[] obj=lists.get(i);
			flows f= new flows();
			f.setIds(obj[0].toString());
			f.setName(obj[1].toString()+"策略");
			listFlows.add(f);
		}
		
		ServletActionContext.getRequest().setAttribute("listFlows", listFlows);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
		
		this.setActionName("riskFlow/yearQueryAction"); 	
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	//对dateFrom的四位年份进行改造,改造成"yyyy-MM-dd HH:mm:ss"格式
	public String transformDateFrom(String temfrom){
		if(temfrom.equals("")){
			temfrom="1900-01-01 00:00:00";
		}
		else{
			temfrom=temfrom+" 00:00:00";
		}
		return temfrom;
	}
	/*
	 * 添加或修改
	 */
	public String updateOrAddRisk(){
		try {
			
			Risk r=new Risk();
			r=this.getRiskDAO().findById(this.riskType);
			if (this.updateFlag.equals("update")) {		
				this.getFlowRuleDao().delete(this.riskType);
				/*
				 */
				int i=0;
				String[] strategys=this.listinfo.replaceFirst(";","").split(";");
				if(strategys.length>0){
					FlowRule update=new  FlowRule();
					//update.setRisk(r);
					update.setRiskId(r.getRiskId());
					update.setFrStatus("1");
					update.setFrNextstatus("2");
					update.setFrPrestatus("0");
					update.setFrDepart("localdep");
					update.setFrRole("1");
					this.getFlowRuleDao().save(update);
				}
				if(this.listinfo.length()>0)
				{
				for(i=0;i<strategys.length;i++){				
					String[] infos=strategys[i].split(",");
					FlowRule updates=new  FlowRule();
					
					//updates.setRisk(r);
					updates.setRiskId(r.getRiskId());
					updates.setFrStatus(String.valueOf(i+2));
					updates.setFrNextstatus(String.valueOf(i+3));
					updates.setFrPrestatus(String.valueOf(i+1));
					updates.setFrDepart(infos[0]);
					updates.setFrRole(infos[1]);
					this.getFlowRuleDao().save(updates);
				}
				}
				if(strategys.length>0)
				{
//					FlowRule adds=new  FlowRule();
//					adds.setRisk(r);
//					adds.setFrStatus(String.valueOf(i+2));
//					adds.setFrNextstatus("*");
//					adds.setFrPrestatus(String.valueOf(i+1));
//					adds.setFrDepart("YZGZ");
//					adds.setFrRole("1");
//					this.getFlowRuleDao().save(adds);
					
					FlowRule adds=new  FlowRule();
					//adds.setRisk(r);
					adds.setRiskId(r.getRiskId());
					adds.setFrStatus(String.valueOf(i+2));
					adds.setFrNextstatus("*");
					adds.setFrPrestatus(String.valueOf(i+1));
					adds.setFrDepart("FB");
					adds.setFrRole("1");
					this.getFlowRuleDao().save(adds);
				}
			}
			else{		
				
				/*
				 * 添加项
				 */
				this.getFlowRuleDao().delete(this.riskType);
				String[] strategys=this.listinfo.replaceFirst(";","").split(";");
				if(strategys.length>0){
					FlowRule update=new  FlowRule();
					//update.setRisk(r);
					update.setRiskId(r.getRiskId());
					update.setFrStatus("1");
					update.setFrNextstatus("2");
					update.setFrPrestatus("0");
					update.setFrDepart("localdep");
					update.setFrRole("1");
					this.getFlowRuleDao().save(update);
				}
				int i=0;
				if(this.listinfo.length()>0)
				{
				for(i=0;i<strategys.length;i++){
					String[] infos=strategys[i].split(",");
					FlowRule add=new  FlowRule();
					//add.setRisk(r);
					add.setRiskId(r.getRiskId());
					add.setFrStatus(String.valueOf(i+2));
					add.setFrNextstatus(String.valueOf(i+3));
					add.setFrPrestatus(String.valueOf(i+1));
					add.setFrDepart(infos[0]);
					add.setFrRole(infos[1]);
					this.getFlowRuleDao().save(add);
				}
				}
				if(strategys.length>0)
				{
//					FlowRule adds=new  FlowRule();
//					adds.setRisk(r);
//					adds.setFrStatus(String.valueOf(i+2));
//					adds.setFrNextstatus("*");
//					adds.setFrPrestatus(String.valueOf(i+1));
//					adds.setFrDepart("YZGZ");
//					adds.setFrRole("1");
//					this.getFlowRuleDao().save(adds);
					
					FlowRule adds=new  FlowRule();
					//adds.setRisk(r);
					adds.setRiskId(r.getRiskId());
					adds.setFrStatus(String.valueOf(i+2));
					adds.setFrNextstatus("*");
					adds.setFrPrestatus(String.valueOf(i+1));
					adds.setFrDepart("FB");
					adds.setFrRole("1");
					this.getFlowRuleDao().save(adds);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		updateRisk1(this.riskType);
		//getRiskFlow();
		return "success";
	}
	
	
	public String updateOrAddRisk1(){
		try {
			
			Risk r=new Risk();
			r=this.getRiskDAO().findById(this.riskType);
			if (this.updateFlag.equals("update")) {		
				this.getFlowRuleDao().delete(this.riskType);
				/*
				 */
				int i=0;
				String[] strategys=this.listinfo.replaceFirst(";","").split(";");
				if(strategys.length>0){
					FlowRule update=new  FlowRule();
					//update.setRisk(r);
					update.setRiskId(r.getRiskId());
					update.setFrStatus("1");
					update.setFrNextstatus("2");
					update.setFrPrestatus("0");
					update.setFrDepart("localdep");
					update.setFrRole("1");
					this.getFlowRuleDao().save(update);
				}
				if(this.listinfo.length()>0)
				{
				for(i=0;i<strategys.length;i++){				
					String[] infos=strategys[i].split(",");
					FlowRule updates=new  FlowRule();
					
					//updates.setRisk(r);
					updates.setRiskId(r.getRiskId());
					updates.setFrStatus(String.valueOf(i+2));
					updates.setFrNextstatus(String.valueOf(i+3));
					updates.setFrPrestatus(String.valueOf(i+1));
					updates.setFrDepart(infos[0]);
					updates.setFrRole(infos[1]);
					this.getFlowRuleDao().save(updates);
				}
				}
				if(strategys.length>0)
				{
//					FlowRule adds=new  FlowRule();
//					adds.setRisk(r);
//					adds.setFrStatus(String.valueOf(i+2));
//					adds.setFrNextstatus("*");
//					adds.setFrPrestatus(String.valueOf(i+1));
//					adds.setFrDepart("YZGZ");
//					adds.setFrRole("1");
//					this.getFlowRuleDao().save(adds);
					
					FlowRule adds=new  FlowRule();
					//adds.setRisk(r);
					adds.setRiskId(r.getRiskId());
					adds.setFrStatus(String.valueOf(i+2));
					adds.setFrNextstatus("*");
					adds.setFrPrestatus(String.valueOf(i+1));
					adds.setFrDepart("FB");
					adds.setFrRole("1");
					this.getFlowRuleDao().save(adds);
				}
			}
			else{		
				
				/*
				 * 添加项
				 */
				this.getFlowRuleDao().delete(this.riskType);
				String[] strategys=this.listinfo.replaceFirst(";","").split(";");
				if(strategys.length>0){
					FlowRule update=new  FlowRule();
					//update.setRisk(r);
					update.setRiskId(r.getRiskId());
					update.setFrStatus("1");
					update.setFrNextstatus("2");
					update.setFrPrestatus("0");
					update.setFrDepart("localdep");
					update.setFrRole("1");
					this.getFlowRuleDao().save(update);
				}
				int i=0;
				if(this.listinfo.length()>0)
				{
				for(i=0;i<strategys.length;i++){
					String[] infos=strategys[i].split(",");
					FlowRule add=new  FlowRule();
					//add.setRisk(r);
					add.setRiskId(r.getRiskId());
					add.setFrStatus(String.valueOf(i+2));
					add.setFrNextstatus(String.valueOf(i+3));
					add.setFrPrestatus(String.valueOf(i+1));
					add.setFrDepart(infos[0]);
					add.setFrRole(infos[1]);
					this.getFlowRuleDao().save(add);
				}
				}
				if(strategys.length>0)
				{
//					FlowRule adds=new  FlowRule();
//					adds.setRisk(r);
//					adds.setFrStatus(String.valueOf(i+2));
//					adds.setFrNextstatus("*");
//					adds.setFrPrestatus(String.valueOf(i+1));
//					adds.setFrDepart("YZGZ");
//					adds.setFrRole("1");
//					this.getFlowRuleDao().save(adds);
					
					FlowRule adds=new  FlowRule();
					//adds.setRisk(r);
					adds.setRiskId(r.getRiskId());
					adds.setFrStatus(String.valueOf(i+2));
					adds.setFrNextstatus("*");
					adds.setFrPrestatus(String.valueOf(i+1));
					adds.setFrDepart("FB");
					adds.setFrRole("1");
					this.getFlowRuleDao().save(adds);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		getRiskFlow();
		return "success";
	}
}
