package com.action.dataUnit;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.dao.DepartmentDAO;
import com.dao.DepartmentChangedDAO;
import com.dao.RiskAssessAccountDAO;
import com.dao.UserStrategyDAO;
import com.dao.UsersQueryViewDAO;

import com.model.Department;
import com.model.DepartmentChanged;
import com.model.RiskAssessView;
import com.model.UsersQueryView;


public class departmentAction{
	private String depId;
	private String depName;
	private String depbelName;
	private String depSort;
	private Integer depSort2;
	private String depRemark;
	private String depQueue;
	private String depAssess;
	private Integer modifyYear;
	private Integer modifyQuater;
	private Integer depsort1;
	private Integer times;
	private Integer id;
	private List<Department> depList;
	private List<String> idCheck; 
	private String updateFlag;
	private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
	private String depNameString;
	private DepartmentDAO departmentDao;
	private DepartmentChangedDAO departmentChangedDao;
	private UsersQueryViewDAO usersQueryViewDao;
	private UserStrategyDAO userStrategyDao;
	private RiskAssessAccountDAO riskAssessAccountDao;
	Department department=new Department();	
	DepartmentChanged departmentChanged=new DepartmentChanged();
    private String actionName="dataUnit/DepartmentAction";
    
    
  
	public Integer getDepSort2() {
		return depSort2;
	}
	public void setDepSort2(Integer depSort2) {
		this.depSort2 = depSort2;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getModifyYear() {
		return modifyYear;
	}
	public void setModifyYear(Integer modifyYear) {
		this.modifyYear = modifyYear;
	}
	public Integer getModifyQuater() {
		return modifyQuater;
	}
	public void setModifyQuater(Integer modifyQuater) {
		this.modifyQuater = modifyQuater;
	}
	public Integer getDepsort1() {
		return depsort1;
	}
	public void setDepsort1(Integer depsort1) {
		this.depsort1 = depsort1;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public DepartmentChangedDAO getDepartmentChangedDao() {
		return departmentChangedDao;
	}
	public void setDepartmentChangedDao(DepartmentChangedDAO departmentChangedDao) {
		this.departmentChangedDao = departmentChangedDao;
	}
	public DepartmentChanged getDepartmentChanged() {
		return departmentChanged;
	}
	public void setDepartmentChanged(DepartmentChanged departmentChanged) {
		this.departmentChanged = departmentChanged;
	}
	public RiskAssessAccountDAO getRiskAssessAccountDao() {
		return riskAssessAccountDao;
	}
	public void setRiskAssessAccountDao(RiskAssessAccountDAO riskAssessAccountDao) {
		this.riskAssessAccountDao = riskAssessAccountDao;
	}
	public UserStrategyDAO getUserStrategyDao() {
		return userStrategyDao;
	}
	public void setUserStrategyDao(UserStrategyDAO userStrategyDao) {
		this.userStrategyDao = userStrategyDao;
	}
	public UsersQueryViewDAO getUsersQueryViewDao() {
		return usersQueryViewDao;
	}
	public void setUsersQueryViewDao(UsersQueryViewDAO usersQueryViewDao) {
		this.usersQueryViewDao = usersQueryViewDao;
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
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	
	public String getDepbelName() {
		return depbelName;
	}
	public void setDepbelName(String depbelName) {
		this.depbelName = depbelName;
	}
	public String getDepAssess() {
		return depAssess;
	}
	public void setDepAssess(String depAssess) {
		this.depAssess = depAssess;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepSort() {
		return depSort;
	}
	public void setDepSort(String depSort) {
		this.depSort = depSort;
	}
	public String getDepRemark() {
		return depRemark;
	}
	public void setDepRemark(String depRemark) {
		this.depRemark = depRemark;
	}
	
	public String getDepQueue() {
		return depQueue;
	}
	public void setDepQueue(String depQueue) {
		this.depQueue = depQueue;
	}
	public List<Department> getDepList() {
		return depList;
	}
	
	public void setDepList(List<Department> depList) {
		this.depList = depList;
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
	public String getDepNameString() {
		return depNameString;
	}
	public void setDepNameString(String depNameString) {
		this.depNameString = depNameString;
	}
	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	//显示部门信息,得到最新depList,用于在Department.jsp表单中显示
	@SuppressWarnings("unchecked")
	public String depManage(){
		depList=new LinkedList<Department>();	
		depList=this.getDepartmentDao().findAll((current_pagenum-1)*pageNum,pageNum);
		ServletActionContext.getRequest().setAttribute("depList", depList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	 
		depNameString="";
		this.setActionName("dataUnit/DepartmentAction");
		if(depList.size()>0){			
			return "success";
		}
		else  
			return "fail";
	}
	// 新增|编辑
	@SuppressWarnings("unchecked")
	public String depAddUpdate(){
		if(this.updateFlag.equals("update"))
		{		
			List<Department> ravList = new ArrayList<Department>();
			List<DepartmentChanged> ravList1 = new ArrayList<DepartmentChanged>();
			List<DepartmentChanged> ravListx = new ArrayList<DepartmentChanged>();
			//List<Department> depsort11;
			ravList=this.getDepartmentDao().searchDepsort(this.depId);
			//ravList1=this.getDepartmentChangedDao().searchAll();
			ravList1=this.getDepartmentChangedDao().searchAll();
			ravListx=this.getDepartmentChangedDao().searchAllx(this.depId);
			System.out.println(ravList1+":da大小xiao");
			int count1=ravList1.size();
			System.out.println(ravList1.size()+":da大小");
			System.out.println(ravListx.size()+":da大小");
			//ravList.get(0).getDepSort();
			System.out.println(this.depId+":one");
			
			//jq
			this.depSort2=ravList.get(0).getDepSort();
			System.out.println(this.depSort2+":jqjqjqwww");
			if(ravList.get(0).getDepSort()!=Integer.parseInt(this.getDepSort())){
			Calendar now=Calendar.getInstance();
			this.modifyYear=now.get(Calendar.YEAR);
			this.modifyQuater=((now.get(Calendar.MONTH)));
			System.out.println(this.modifyQuater+":da大小sss");
			departmentChanged.setDepId(this.getDepId());
			departmentChanged.setModifyYear(this.modifyYear);
			departmentChanged.setModifyQuarter(this.modifyQuater/3+1);
			departmentChanged.setDepsort1(this.depSort2);
			departmentChanged.setTimes(ravListx.size()+1);
			System.out.println(ravList1.size()+":大小");
			if(count1!=0){
				this.id=count1+1;
				departmentChanged.setId(this.id);
			}else{
				this.id=1;
				departmentChanged.setId(this.id);
			}
			//this.id+=1;
			this.getDepartmentChangedDao().merge(departmentChanged);
			System.out.println(this.depSort+"jq111");
			//this.getDepartmentChangedDao().insert(depId, modifyYear, modifyQuater, depsort1, times);
			//
			}
			
			//depsort11=this.getDepartmentDao().searchDepsort(this.depId);
			//System.out.println(depsort11+":two");
			//System.out.println("jq121");
			department.setDepId(this.getDepId());
			department.setDepName(this.getDepName());
			//department.setDepbelName(this.getDepbelName());
			department.setDepSort(Integer.parseInt(this.getDepSort()));
			department.setDepRemark(this.getDepRemark());
			department.setDepQueue(Integer.parseInt(this.depQueue));
			department.setDepAssess(Integer.parseInt(this.depAssess));
			this.getDepartmentDao().merge(department);
			//更新RiskAssessAccount表中的部门属性
			this.getRiskAssessAccountDao().updateDepProperty(this.getDepId(),this.getDepSort());
			
			
			
			
			depManage();//得到最新depList,用于在Department.jsp表单中显示
			//System.out.println(this.depSort+"jq111");
			return "success";
		}
		else 
		{
			System.out.println("caih121");
			if(departmentDao.findById(this.getDepId())!=null)return "fail";			
			department.setDepId(this.getDepId());
			//department.setDepbelName(this.getDepbelName());
			department.setDepName(this.getDepName());
			department.setDepSort(Integer.parseInt(this.getDepSort()));
			department.setDepRemark(this.getDepRemark());
			department.setDepQueue(departmentDao.findAll().size()+1);
			department.setDepAssess(1);
			this.getDepartmentDao().save(department);
			depManage();//得到最新depList,用于在Department.jsp表单中显示
			return "success";
		}
	}
	//批量删除(没用用到了)
	public String depMultiDel(){	
		int i=0;
		
		for(i=0;i<idCheck.size();i++)
		{		
			this.getDepartmentDao().deleteBySql(this.idCheck.get(i));
		}
		depManage();//得到最新depList,用于在Department.jsp表单中显示
		return "success";
	}
	//利用ajax删除部门
	public void ajaxDepMultiDel() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = response.getWriter();
			String depIds = request.getParameter("depIds");
			List<String> depIdlist = Arrays.asList(depIds.split(","));
			String depNames = "";
			for(String depId : depIdlist) {
				List<UsersQueryView> userlist = this.getUsersQueryViewDao().getUserNum(depId);
				if(userlist != null && userlist.size() > 0) {
					depNames += "," + userlist.get(0).getDepName();
					continue;
				}
				this.getUserStrategyDao().deleteByUserDep(depId);
				this.getDepartmentDao().deleteBySql(depId);
			}
			if("".equals(depNames)) {
				out.write("success");
				out.flush();
				return;
			}
			depNames = depNames.substring(1);
			out.write(URLEncoder.encode(depNames,"UTF-8"));
			out.flush();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	//删除(没用用到了)
	public String depDelete(){
		try{
			this.getDepartmentDao().deleteBySql(this.getDepId());
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		depManage();//得到最新depList,用于在Department.jsp表单中显示
		return "success";
	}
	// 查看
	public String depRead(){
		try{
			department=this.getDepartmentDao().findById(this.getDepId());
			this.depName=department.getDepName();
			//this.depbelName=department.getDepbelName();
			this.depSort=department.getDepSort().toString();
			this.depRemark=department.getDepRemark();
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	// 编辑前显示要编辑的对象的信息
	public String depUpdatePrepare(){
		try{
			department=this.getDepartmentDao().findById(this.getDepId());
			this.depName=department.getDepName();
		//	this.depbelName=department.getDepbelName();
			this.depSort=department.getDepSort().toString();
			this.depRemark=department.getDepRemark();
			this.depQueue=String.valueOf(department.getDepQueue());
			this.depAssess = department.getDepAssess().toString();
			
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	//根据部门名称查询信息
	@SuppressWarnings("unchecked")
	public String depSearch(){
		try{
			depList=new LinkedList<Department>();	
			depList =this.getDepartmentDao().search(this.depNameString,(current_pagenum-1)*pageNum,pageNum);
			ServletActionContext.getRequest().setAttribute("depList", depList);              
			ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	 
			this.actionName="dataUnit/DepartmentAction";
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	public String depAssessPreare() {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			List<Department> depList = departmentDao.findAll();
			List<Department> depAssesslist = departmentDao.getDepAssess();
			request.setAttribute("deplist", depList);
			request.setAttribute("depassesslist", depAssesslist);
			
		} catch(Exception e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "success";
		
	}
	
	public void depAssess() {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = response.getWriter();
			String depIds = request.getParameter("depIds");
			List<String> depIdlist = Arrays.asList(depIds.split(","));
			departmentDao.depAssess(depIdlist);
			out.write("success");
			out.flush();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
}
