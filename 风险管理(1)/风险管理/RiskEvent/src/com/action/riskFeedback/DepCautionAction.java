package com.action.riskFeedback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.DepWarnDAO;
import com.dao.DepartmentDAO;
import com.model.DepWarn;
import com.model.Department;

import com.model.Users;

public class DepCautionAction {
	 private List<Integer> idCheck;
	 private int current_pagenum=1;//当前页码
	    private int  pageNum=10;//每页的显示数据记录数
	    private DepWarnDAO depWarnDao;
	    private List<DepWarn> depWarnList;
	    private List<Department> depList;
	    private List<Department> alldepList;
	    private String riskDeps;
	    private DepartmentDAO departmentDao;
	    private String depName;
	    public  String dateFrom="";
		public  String dateTo="";
	    public List<DepWarn> alleventList;
	    public String eventnumber;
		public List<DepWarn> getAlleventList() {
			DepWarn w1=new DepWarn();
			w1.setDwId(-1);
			w1.setRiskEvent("--请选择--");
			alleventList=new LinkedList<DepWarn>();
			alleventList.add(w1);
			List<DepWarn> eventList =new LinkedList<DepWarn>();
			eventList=this.getDepWarnDao().findEvent();
			alleventList.addAll(eventList);
			return alleventList;
		}
		public void setAlleventList(List<DepWarn> alleventList) {
			this.alleventList = alleventList;
		}
		public String getEventnumber() {
			return eventnumber;
		}
		public void setEventnumber(String eventnumber) {
			this.eventnumber = eventnumber;
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
		public String getDepName() {
			return depName;
		}
		public void setDepName(String depName) {
			this.depName = depName;
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
		public List<Department> getAlldepList() {
			return alldepList;
		}
		public void setAlldepList(List<Department> alldepList) {
			this.alldepList = alldepList;
		}
		public String getRiskDeps() {
			return riskDeps;
		}
		public void setRiskDeps(String riskDeps) {
			this.riskDeps = riskDeps;
		}
		public List<Integer> getIdCheck() {
			return idCheck;
		}
		public void setIdCheck(List<Integer> idCheck) {
			this.idCheck = idCheck;
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
		public DepWarnDAO getDepWarnDao() {
			return depWarnDao;
		}
		public void setDepWarnDao(DepWarnDAO depWarnDao) {
			this.depWarnDao = depWarnDao;
		}
		
	    public List<DepWarn> getDepWarnList() {
			return depWarnList;
		}
		public void setDepWarnList(List<DepWarn> depWarnList) {
			this.depWarnList = depWarnList;
		}
		
		@SuppressWarnings("unchecked")
		public String show(){
			
			this.depWarnList=new LinkedList<DepWarn>();
			depWarnList=this.getDepWarnDao().findAll((current_pagenum-1)*pageNum,pageNum);
			ServletActionContext.getRequest().setAttribute("depWarnList", depWarnList);              
			ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
			Department dep1=new Department();
			dep1.setDepId("none1");
			dep1.setDepName("--请选择--");
			dep1.setDepSort(0);
			alldepList=new LinkedList<Department>();
			alldepList.add(dep1);
			depList=new LinkedList<Department>();
			depList=this.getDepartmentDao().findAll();
			alldepList.addAll(depList);	
			if (depWarnList.size() > 0)
				return "success";
			else
				return "fail";
	    	
	    }
		@SuppressWarnings("unchecked")
		public String showSelfDepInf(){
			//getAlleventList();//得到风险事件编号下拉类表
			Users u = (Users) ServletActionContext.getRequest().getSession()
			.getAttribute("loginUser");
	 
			String selfNameId=u.getUserDep(); //部门Id
			this.depWarnList=new LinkedList<DepWarn>();
			depWarnList=this.getDepWarnDao().reqQuery(selfNameId,(current_pagenum-1)*pageNum,pageNum);
			//ServletActionContext.getRequest().setAttribute("depWarnList", depWarnList);              
			ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
			
			if (depWarnList.size() > 0)
				return "success";
			else
				return "fail";
	    	
	    }
		@SuppressWarnings("unchecked")
		public String showSelfDepForSearch(){
			//getAlleventList();//得到风险事件编号下拉类表
			Users u = (Users) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
			String selfNameId=u.getUserDep(); //部门Id
			this.depWarnList=new LinkedList<DepWarn>();
			String datefromtem=transformDateFrom(this.getDateFrom());//对dateFrom的四位年份进行改造,改造成"yyyy-MM-dd 00:00:00"格式
			String datetotem=transformDateTo(this.getDateTo());//对dateTo的四位年份进行改造,改造成"yyyy-MM-dd 23:59:59"格式
			depWarnList=this.getDepWarnDao().allKindQuery(selfNameId, datefromtem, datetotem, (current_pagenum-1)*pageNum,pageNum);
			ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
			if (depWarnList.size() > 0)
				return "success";
			else
				return "fail";
		}
		public String multiDel() {
			int i = 0;
			for (i = 0; i < this.idCheck.size(); i++) {
				DepWarn dep=new DepWarn();
				dep=this.getDepWarnDao().findById(this.idCheck.get(i));
				this.getDepWarnDao().delete(dep);
			}
			show();// 得到最新lawList,用于在Law.jsp表单中显示
			return "success";
		}
		@SuppressWarnings("unchecked")
		public String query(){
			try {
				Department dep1=new Department();
				dep1.setDepId("none1");
				dep1.setDepName("--请选择--");
				dep1.setDepSort(0);
				alldepList=new LinkedList<Department>();
				alldepList.add(dep1);
				depList=new LinkedList<Department>();
				depList=this.getDepartmentDao().findAll();
				alldepList.addAll(depList);	
				this.depWarnList= new LinkedList<DepWarn>();
				this.depWarnList=this.getDepWarnDao().reqQuery(this.riskDeps,(current_pagenum-1)*pageNum,pageNum);
				
				ServletActionContext.getRequest().setAttribute("depWarnList", depWarnList);              
				ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
			} catch (Exception e) {
				
				e.printStackTrace();
				return "fail";
			}
			
			return "success";
		}
		public void transformDateFrom(){
			if(this.getDateFrom().equals("")){
				this.setDateFrom("1900-01-01 00:00:00");
			}
			else{
				this.setDateFrom(this.getDateFrom()+" 00:00:00");
			}
		}  
		//对dateTo的四位年份进行改造,改造成"yyyy-MM-dd HH:mm:ss"格式
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
		//对dateTo的四位年份进行改造,改造成"yyyy-MM-dd HH:mm:ss"格式
		public String transformDateTo(String temto){
			if(temto.equals("")){
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
