package com.action.riskInput;

import com.model.RiskEvent;

import com.model.Department;
import com.model.RiskManage;
import com.model.RiskRecord;
import com.model.Users;
import com.services.TaskExecutionWebServer;
import com.services.ToDoWebServiceAction;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import com.dao.DepartmentDAO;
import com.dao.RiskDepQueryViewDAO;
import com.dao.RiskEventDAO;
import com.dao.RiskManageDAO;
import com.dao.RiskRecordDAO;
import com.dao.UsersDAO;
import com.dao.UsersQueryViewDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Holder;

import org.apache.struts2.ServletActionContext;
import org.zhongsoft.pendtasks.PendingTaskPortService;
import org.zhongsoft.pendtasks.PendingTaskPortServiceSoap;

public class ManageInputAction {

	private String idmanage;
	private String chance;
	private String strategy;
	private String plandate;
	private String control;
	private String planresource;
	private String reply;
	private String manageFlag;
	private String statusId;
	private RiskEventDAO riskeventDao;
	private RiskEvent riskevent=new RiskEvent();
	
	private UsersDAO usersDao;
	private String submittodep;
	private String submittopeople;
	private List<Users> usersList;
	
	private RiskRecord riskrecord;
	
	private RiskManageDAO riskmanageDao;
	private RiskManage riskmanage=new RiskManage();
	private String idimpact;
	
	private DepartmentDAO departmentDao;
	private UsersQueryViewDAO usersQueryViewDao;
	private RiskDepQueryViewDAO riskDepQueryViewDao;
	private RiskRecordDAO riskRecordDao;

	public RiskRecordDAO getRiskRecordDao() {
		return riskRecordDao;
	}
	public void setRiskRecordDao(RiskRecordDAO riskRecordDao) {
		this.riskRecordDao = riskRecordDao;
	}
	public RiskDepQueryViewDAO getRiskDepQueryViewDao() {
		return riskDepQueryViewDao;
	}
	public void setRiskDepQueryViewDao(RiskDepQueryViewDAO riskDepQueryViewDao) {
		this.riskDepQueryViewDao = riskDepQueryViewDao;
	}
	public UsersQueryViewDAO getUsersQueryViewDao() {
		return usersQueryViewDao;
	}
	public void setUsersQueryViewDao(UsersQueryViewDAO usersQueryViewDao) {
		this.usersQueryViewDao = usersQueryViewDao;
	}
	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}
	public String getIdmanage() {
		return idmanage;
	}
	public void setIdmanage(String idmanage) {
		this.idmanage = idmanage;
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
	public RiskManageDAO getRiskmanageDao() {
		return riskmanageDao;
	}
	public void setRiskmanageDao(RiskManageDAO riskmanageDao) {
		this.riskmanageDao = riskmanageDao;
	}
	public RiskManage getRiskmanage() {
		return riskmanage;
	}
	public void setRiskmanage(RiskManage riskmanage) {
		this.riskmanage = riskmanage;
	}
	public String getIdimpact() {
		return idimpact;
	}
	public void setIdimpact(String idimpact) {
		this.idimpact = idimpact;
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
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public UsersDAO getUsersDao() {
		return usersDao;
	}
	public void setUsersDao(UsersDAO usersDao) {
		this.usersDao = usersDao;
	}
	public List<Users> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}
	public String getSubmittodep() {
		return submittodep;
	}
	public void setSubmittodep(String submittodep) {
		this.submittodep = submittodep;
	}
	public String getSubmittopeople() {
		return submittopeople;
	}
	public String getManageFlag() {
		return manageFlag;
	}
	public RiskRecord getRiskrecord() {
		return riskrecord;
	}
	public void setRiskrecord(RiskRecord riskrecord) {
		this.riskrecord = riskrecord;
	}
	public void setManageFlag(String manageFlag) {
		this.manageFlag = manageFlag;
	}
	public void setSubmittopeople(String submittopeople) {
		this.submittopeople = submittopeople;
	}	
	//更新风险管理信息
	public String manageInput() throws java.text.ParseException
	{	
	 try {	
		 
		    //HttpServletRequest request = ServletActionContext.getRequest(); 
			//HttpSession session = request.getSession();
			//Users us = (Users)session.getAttribute("loginUser");
			riskevent=new RiskEvent();
			riskevent=this.getRiskeventDao().findById(this.getIdmanage());//风险事件
			//riskmanage.setRiskEvent(riskeventDao.findById(this.idmanage));
			//riskmanage.setRiskEvent(riskevent.getReId());
			riskmanage.setRmReId(this.idmanage);
			riskmanage.setRmChance(this.getChance());
			riskmanage.setRmControl(this.getControl());
			riskmanage.setRmReply(this.getReply());
			riskmanage.setRmStrategy(this.getStrategy());
			riskmanage.setRmPlanres(this.getPlanresource());
			riskmanage.setRmPlandate(this.getPlandate());
		    this.getRiskmanageDao().updatemanage(riskmanage);
		    
		    // 更新风险事件的最后处理时间
 			Date date = new Date();
 			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 			this.getRiskeventDao().updateLastDate(this.idmanage, df.format(date));
		    
		    
		    //this.submittodep=us.getDepartment().getDepName();
		    //this.usersList=usersDao.findflowtopeople(us.getDepartment().getDepId(), "1");
		    this.submittodep=this.getDepartmentDao().findById(riskevent.getReIndep()).getDepName();
		    this.usersList=this.getUsersQueryViewDao().findflowtopeople(riskevent.getReIndep(), "1");
	        
			return "success";
	    }catch (ParseException e) {
				   e.printStackTrace();
				}
			return "success";
	}
	
	//显示风险管理信息
	public String getRiskManage()
	{
		try{
	    	RiskManage riskmanage=new RiskManage();
			riskmanage=this.getRiskmanageDao().findById(this.getIdmanage());
			this.chance=riskmanage.getRmChance();
			this.control=riskmanage.getRmControl();
			this.reply=riskmanage.getRmReply();
			this.strategy=riskmanage.getRmStrategy();
			this.planresource=riskmanage.getRmPlanres();
			this.plandate=riskmanage.getRmPlandate();
			
			}catch(Exception e){
				e.printStackTrace();
				return "fail";
			}
			return "success";
	}

	//选择提交的人，点击‘提交’，将当前审核人，状态等信息更新到风险事件的信息
//	public String eventSubmit()
//	{
//		
//		try
//		{
//			/*
//			 * 新增待办
//			 */
//			
//			String reId = this.getIdmanage();
//			Integer count = this.getRiskRecordDao().getCount(reId);
//			if(count >0)
//			{
//				HttpServletRequest request = ServletActionContext.getRequest();
//				HttpSession session = request.getSession();
//				Users us = (Users) session.getAttribute("loginUser");
//				riskrecord = new RiskRecord();
//				// 在riskrecord中插入一条审核记录
//				Date date = new Date();
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				riskrecord.setRrBehaviour("1");// 标记动作，1表示通过
//				riskrecord.setRrReId(reId);
//				riskrecord.setRrVerifier(us.getUserId());
//				riskrecord.setRrTime(df.format(date));
//				riskrecord.setRrView("已重填");
//				riskrecord.setRrStatus("1");
//				this.getRiskRecordDao().save(riskrecord);
//
//			}
//			String addTask =addTasks(reId,this.getSubmittopeople());
//			if(addTask.equals("Out_fail"))
//			{
//				/*
//				 * 更新待办
//				 */
//				updateTasks(this.getIdmanage(),this.getSubmittopeople());
//			}
//			this.getRiskeventDao().updatestate(this.getIdmanage(),"1",this.submittopeople);
//		}catch(Exception e){
//			e.printStackTrace();
//			return "fail";
//			
//			
//			
//		}
//		return "success";
//	}
	
	//选择提交的人，点击‘提交’，将当前审核人，状态等信息更新到风险事件的信息
	
	public String eventSubmit()
	{
		try
		{
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			
			// 如果有审核记录表示该风险事件是重填的
			int count = this.getRiskRecordDao().getCount(this.idmanage);
			
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			riskrecord = new RiskRecord();
			riskrecord.setRrBehaviour("1");		// 标记动作，1表示通过
			riskrecord.setRrReId(this.idmanage);
			riskrecord.setRrVerifier(us.getUserId());
			riskrecord.setRrTime(df.format(date));
			riskrecord.setRrStatus("0");
			riskrecord.setRrView("已提交");
			
			if(count >0)
			{
				/*
				 * 更新待办
				 */
				inThreads("update",us,this.idmanage,this.getSubmittopeople());
				//updateTasks(this.getIdmanage(),this.getSubmittopeople());
				riskrecord.setRrView("已重填");
			}
			else
			{
				/*
				 * 新增待办，将其放入线程池，由Tomcat服务器执行
				 */
				inThreads("add",us,this.idmanage,this.getSubmittopeople());
				//addTasks(this.getIdmanage(),this.getSubmittopeople());
			}
			this.getRiskRecordDao().save(riskrecord);
			this.getRiskeventDao().updatestate(this.getIdmanage(),"1",this.submittopeople);
			
			// 更新风险事件的最后处理时间
 			this.getRiskeventDao().updateLastDate(this.idmanage, df.format(date));
			
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	/*
	 * 调用待办，将其放入线程池，由Tomcat服务器执行
	 */
	public String inThreads(String taskFlag,final Users Out_us, final String Out_reId,final String Out_userid)
	{
		
		try{
			Callable<String> add=new Callable<String>(){
				public String call(){
					return addTasks(Out_us, Out_reId, Out_userid);
				}
			};
			Callable<String> update=new Callable<String>(){
				public String call(){
					return updateTasks(Out_us, Out_reId, Out_userid);
				}
			};
			TaskExecutionWebServer tews=TaskExecutionWebServer.getInstance();
			if(taskFlag.equals("add"))
			{
				tews.submit(add);
			}
			else if(taskFlag.equals("update"))
			{
				tews.submit(update);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	
	/*
	 * 新增待办
	 */
	public String addTasks(Users Out_us,String Out_reId,String Out_userid)
	{
		try{
			Users receiver=this.getUsersDao().findById(Out_userid);//流转到人
			riskevent=new RiskEvent();
			riskevent=this.getRiskeventDao().findById(Out_reId);//风险事件
			if(null == riskevent) return "fail";
			/*
			 * 将待办信息，集成到中南院办公系统：调用WebService接口，增删改待办信息
			 */
			//获取接口所需参数
			String TaskId=Out_reId;//reId
			String TaskApp="RiskEvent";//代表风险事件项目
			String TaskTitle="待审核风险事件（"+Out_reId+"）";
			String LogonName="";//接收人Id
			String UserName="";//接收人姓名
			String OrgName="";//接收人部门名称
			Department dModel=new Department();
			if(null!=receiver){
				LogonName=receiver.getUserId();
				UserName=receiver.getUserName();
				dModel=this.getDepartmentDao().findById(receiver.getUserDep());
				if(null != dModel) OrgName=dModel.getDepName();
			}
			//Out_System_Flag=out,表示这条链接是从外部系统链接进来的；Out_System_Flag=in,表示系统内调用action
			String TaskUrl="default/loginSingleSystemAction?userid="+LogonName;//待审核风险事件
			Date d=new Date();
			DateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ReceiveTime=f.format(d);//接收时间
			String CreatorName=riskevent.getReCreator();//录入人姓名
			String CreatorOrgName="";//录入人部门名称
			dModel=new Department();
			dModel=this.getDepartmentDao().findById(riskevent.getReIndep());
			if(null != dModel) CreatorOrgName=dModel.getDepName();
			String PreUserName=Out_us.getUserName();//本人姓名
			String PreOrgName="";////本人部门名称
			dModel=new Department();
			dModel=this.getDepartmentDao().findById(Out_us.getUserDep());
			if(null != dModel) PreOrgName=dModel.getDepName();
			TaskExecutionWebServer.addTask(TaskId, TaskApp, TaskUrl, TaskTitle, LogonName, 
					UserName, OrgName, ReceiveTime, CreatorName, CreatorOrgName, PreUserName, PreOrgName
					);
			
		}catch(Exception e){
				e.printStackTrace();
				return "fail";
		}
		return "success";
	}
	/*
	 * 更新待办
	 */
	public String updateTasks(Users Out_us,String Out_reId,String Out_userid)
	{
		try{
			Users receiver=this.getUsersDao().findById(Out_userid);//流转到人
			riskevent=new RiskEvent();
			riskevent=this.getRiskeventDao().findById(Out_reId);//风险事件
			if(null == riskevent) return "fail";
			/*
			 * 将待办信息，集成到中南院办公系统：调用WebService接口，增删改待办信息
			 */
			//获取接口所需参数
			String TaskId=Out_reId;//reId
			String TaskApp="RiskEvent";//代表风险事件项目
			String TaskTitle="待审核风险事件（"+Out_reId+"）";
			String LogonName="";//接收人Id
			String UserName="";//接收人姓名
			String OrgName="";//接收人部门名称
			Department dModel=new Department();
			if(null!=receiver){
				LogonName=receiver.getUserId();
				UserName=receiver.getUserName();
				dModel=this.getDepartmentDao().findById(receiver.getUserDep());
				if(null != dModel) OrgName=dModel.getDepName();
			}
			//Out_System_Flag=out,表示这条链接是从外部系统链接进来的；Out_System_Flag=in,表示系统内调用action
			String TaskUrl="default/loginSingleSystemAction?userid="+LogonName;//待审核风险事件
			Date d=new Date();
			DateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ReceiveTime=f.format(d);//接收时间
			String CreatorName=riskevent.getReCreator();//录入人姓名
			String CreatorOrgName="";//录入人部门名称
			dModel=new Department();
			dModel=this.getDepartmentDao().findById(riskevent.getReIndep());
			if(null != dModel) CreatorOrgName=dModel.getDepName();
			String PreUserName=Out_us.getUserName();//本人姓名
			String PreOrgName="";////本人部门名称
			dModel=new Department();
			dModel=this.getDepartmentDao().findById(Out_us.getUserDep());
			if(null != dModel) PreOrgName=dModel.getDepName();
			//将参数转换成xml格式数据
			String xml="";
			ToDoWebServiceAction toDoWebServiceAction=new ToDoWebServiceAction();
			xml=toDoWebServiceAction.getXml(TaskId, TaskApp, TaskUrl, TaskTitle, LogonName, 
					UserName, OrgName, ReceiveTime, CreatorName, CreatorOrgName, PreUserName, PreOrgName
					);
			//通过myeclipse自动生成的WebService客户端调用接口
			PendingTaskPortService service = new PendingTaskPortService();  
			PendingTaskPortServiceSoap soap = service.getPendingTaskPortServiceSoap();  
			Holder<Boolean> updateTasksResult = new Holder<Boolean>();//类似C++引用 
			Holder<String> errorMsg = new Holder<String>();//类似C++引用 
			soap.updateTasks(xml, updateTasksResult, errorMsg);
			if(true==updateTasksResult.value)
			{
				System.out.println("成功调用接口："+errorMsg.value+"\n");
			}
			else
			{
				System.out.println("调用接口失败："+errorMsg.value+"\n");
			}
			
		}catch(Exception e){
				e.printStackTrace();
				return "fail";
		}
		return "success";
	}
}
