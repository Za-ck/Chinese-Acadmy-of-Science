package com.action.manage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.services.TaskExecutionWebServer;
import java.util.concurrent.Callable;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Holder;

import org.apache.struts2.ServletActionContext;
import org.zhongsoft.pendtasks.PendingTaskPortService;
import org.zhongsoft.pendtasks.PendingTaskPortServiceSoap;


import com.dao.DepartmentDAO;
import com.dao.EventFileDAO;
import com.dao.RiskDAO;
import com.dao.RiskEventDAO;
import com.dao.AllEventManageViewDAO;//////////////
import com.dao.RiskImpactDAO;
import com.dao.RiskManageDAO;
import com.dao.RiskRecordDAO;

import com.model.Department;
import com.model.EventFile;
import com.model.Risk;
import com.model.RiskEvent;
import com.model.AllEventManageView;///////////////
import com.model.RiskImpact;
import com.model.RiskManage;
import com.model.RiskRecord;

import com.model.RiskType;
import com.model.Users;
import com.services.ToDoWebServiceAction;

public class AllEventManageAction {
	
	private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
    private String actionName="riskInput/RiskEventInputQueryAction";
    private String updownflag;
    private String  updownid;
    private List<AllEventManageView> allreList;
    private List<String> idCheck;
    private String riskId;
    private String reIndep="";
    public  String dateFrom="";
	public  String dateTo="";
	private String orderbys="";
	private String stateId="";
	private String reventStr="";
	private String riskDep = "";		//管理责任部门
	private List<Risk> riskList;
	private List<Department> alldepList;		
	private List<Department> depList;
	private List<Department> riskdepList;		//管理责任部门
	private List<Risk> allriskList;
	
	private DepartmentDAO departmentDao;
	private RiskEventDAO riskEventDao;
	private RiskImpactDAO riskImpactDao;
	private RiskManageDAO riskManageDao;
	private RiskRecordDAO riskRecordDao;
	private AllEventManageViewDAO allEventManageViewDao;
	private EventFileDAO eventFileDao;
	private RiskDAO riskDao;
	
	EventFile eventFile=new EventFile();
	RiskEvent riskEvent=new RiskEvent();
	private AllEventManageView allEventManageView;
	
	
	public List<Department> getRiskdepList() {
		return riskdepList;
	}

	public void setRiskdepList(List<Department> riskdepList) {
		this.riskdepList = riskdepList;
	}

	public String getRiskDep() {
		return riskDep;
	}

	public void setRiskDep(String riskDep) {
		this.riskDep = riskDep;
	}

	public String getReventStr() {
		return reventStr;
	}

	public void setReventStr(String reventStr) {
		this.reventStr = reventStr;
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

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public List<AllEventManageView> getAllreList() {
		return allreList;
	}

	public void setAllreList(List<AllEventManageView> allreList) {
		this.allreList = allreList;
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
    
	public AllEventManageViewDAO getAllEventManageViewDao() {
		return allEventManageViewDao;
	}

	public void setAllEventManageViewDao(AllEventManageViewDAO allEventManageViewDao) {
		this.allEventManageViewDao = allEventManageViewDao;
	}

	public AllEventManageView getAllEventManageView() {
		return allEventManageView;
	}

	public void setAllEventManageView(AllEventManageView allEventManageView) {
		this.allEventManageView = allEventManageView;
	}
	
	public String getOrderbys() {
		return orderbys;
	}
	
	public void setOrderbys(String orderbys) {
		this.orderbys = orderbys;
	}
    
	public List<String> getIdCheck() {
		return idCheck;
	}

	public void setIdCheck(List<String> idCheck) {
		this.idCheck = idCheck;
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
    
	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getReIndep() {
		return reIndep;
	}

	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}

	public List<Risk> getRiskList() {
		return riskList;
	}

	public void setRiskList(List<Risk> riskList) {
		this.riskList = riskList;
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

	public RiskDAO getRiskDao() {
		return riskDao;
	}

	public void setRiskDao(RiskDAO riskDao) {
		this.riskDao = riskDao;
	}
	
	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
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
	
	public RiskRecordDAO getRiskRecordDao() {
		return riskRecordDao;
	}

	public void setRiskRecordDao(RiskRecordDAO riskRecordDao) {
		this.riskRecordDao = riskRecordDao;
	}

	// 显示所有的风险事件信息,得到最新reList,用于在RiskEventInputQuery.jsp表单中显示
	@SuppressWarnings("unchecked")
	public String getAllEvent() 
	{
		
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Users us = (Users)session.getAttribute("loginUser");
//		Department department=new Department();
//		department=us.getDepartment();
		// 得到下拉框，录入部门下拉框alldepList和风险名称下拉框allriskList
		getDropDownList();
		Department dep1 = new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		riskdepList = new LinkedList<Department>();
		riskdepList.add(dep1);
		depList = new LinkedList<Department>();
		depList = this.getDepartmentDao().findByDepSort(1);
		riskdepList.addAll(depList);
		depList = this.getDepartmentDao().findByDepSort(2);
		riskdepList.addAll(depList);
		this.allreList = new LinkedList<AllEventManageView>();
		
//		this.allreList = this.getRiskEventDao().findAllEvent((current_pagenum-1)*pageNum,pageNum,this.getOrderbys());
		this.allreList = this.getAllEventManageViewDao().findAllEvent((current_pagenum-1)*pageNum,pageNum,this.getOrderbys());
		
		ServletActionContext.getRequest().setAttribute("allreList", allreList);
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
		this.setActionName("riskInput/AllEventIOQueryAction");
		if (allreList.size() > 0)
			return "success";
		else
			return "fail";
	}
	
	// 批量删除
	public String deleteAllEvent() {
		try {
			//先将删除待办操作放入线程池
			for (int i = 0; i < this.idCheck.size(); i++) {
				// 删除集成系统的待办信息
				// deleteTasks(this.idCheck.get(i));
				/*
				 * 删除待办，将其放入线程池，由Tomcat服务器执行
				 */
				inThreads("delete", this.idCheck.get(i));
			}
			for (int i = 0; i < this.idCheck.size(); i++) 
			{
				riskEvent = this.getRiskEventDao().findById(this.idCheck.get(i));
				if(null != riskEvent)
				{
					this.getRiskEventDao().delete(riskEvent);
				}
				//以下代码的功能由数据库的触发器完成
//				RiskImpact riModel=new RiskImpact();
//				riModel= this.getRiskImpactDao().findById(this.idCheck.get(i));
//				if(null != riModel)this.getRiskImpactDao().delete(riModel);
//				RiskManage rmModel=new RiskManage();
//				rmModel= this.getRiskManageDao().findById(this.idCheck.get(i));
//				if(null != rmModel)this.getRiskManageDao().delete(rmModel);
//				List<EventFile> efList=new LinkedList<EventFile>();
//				efList= this.getEventFileDao().findByProperty("reId", this.idCheck.get(i));
//				Iterator it = efList.iterator();
//				while (it.hasNext()) 
//				{
//					this.getEventFileDao().delete((EventFile) it.next());
//				}
//				List<RiskRecord> rrList=new LinkedList<RiskRecord>();
//				rrList= this.getRiskRecordDao().findByProperty("rrReId", this.idCheck.get(i));
//				Iterator it1 = rrList.iterator();
//				while (it1.hasNext()) 
//				{
//					this.getRiskRecordDao().delete((RiskRecord) it1.next());
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		getEventbycondition();// 得到最新reList,用于在RiskEventInputQuery.jsp表单中显示
		return "success";
	}
	
	
	// 批量删除待办
	public String deleteAllEventTask() {
		try {

			for (int i = 0; i < this.idCheck.size(); i++) {
				/*
				 * 删除待办，将其放入线程池，由Tomcat服务器执行
				 */
				inThreads("delete", this.idCheck.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		getEventbycondition();// 得到最新reList,用于在RiskEventInputQuery.jsp表单中显示
		return "success";
	}
	
	/*
	 * 调用待办，将其放入线程池，由Tomcat服务器执行
	 */
	public String inThreads(String taskFlag, final String Out_reId)
	{
		try{
			Callable<String> delete=new Callable<String>(){
				public String call(){
					return deleteTasks( Out_reId);
				}
			};
			TaskExecutionWebServer tews=TaskExecutionWebServer.getInstance();
			if(taskFlag.equals("delete"))
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
	 * 删除待办，这里因为中南院个人工作台待办集成解决方案  的接口deleteTasks有问题
	 * 故这里用接口updateTasks代替接口deleteTasks实现同样的功能
	 * updateTasks，将接收人Id设为“@@@”
	 */
	public String deleteTasks(String Out_reId) {
		try {
			/*
			 * 将待办信息，集成到中南院办公系统：调用WebService接口，增删改待办信息
			 */
			// 获取接口所需参数
			String TaskId = Out_reId;// reId
			String TaskApp = "RiskEvent";// 代表风险事件项目
			String TaskUrl = "";
			String TaskTitle = "已删除风险事件（"+Out_reId+"）";
			String LogonName = "@@@";// 接收人Id
			String UserName = "";// 接收人姓名
			String OrgName = "";// 接收人部门名称
			String ReceiveTime = "";// 接收时间
			String CreatorName = "";// 录入人姓名
			String CreatorOrgName = "";// 录入人部门名称
			String PreUserName = "";// 本人姓名
			String PreOrgName = "";// 本人部门名称
			// 将参数转换成xml格式数据
			String xml = "";
			ToDoWebServiceAction toDoWebServiceAction = new ToDoWebServiceAction();
			xml = toDoWebServiceAction.getXml(TaskId, TaskApp, TaskUrl,
					TaskTitle, LogonName, UserName, OrgName, ReceiveTime,
					CreatorName, CreatorOrgName, PreUserName, PreOrgName);
			// 通过myeclipse自动生成的WebService客户端调用接口
			PendingTaskPortService service = new PendingTaskPortService();
			PendingTaskPortServiceSoap soap = service
					.getPendingTaskPortServiceSoap();
			Holder<Boolean> updateTasksResult = new Holder<Boolean>();// 类似C++引用
			Holder<String> errorMsg = new Holder<String>();// 类似C++引用
			soap.updateTasks(xml, updateTasksResult, errorMsg);
			if (true == updateTasksResult.value) {
				System.out.println("成功调用接口：" + errorMsg.value + "\n");
			} else {
				System.out.println("调用接口失败：" + errorMsg.value + "\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	// 得到下拉框，录入部门下拉框alldepList和风险类型下拉框alldepList
	public String getDropDownList() {
		try{
			//录入部门下拉框alldepList
			Department dep1=new Department();
			dep1.setDepId("none1");
			dep1.setDepName("--请选择--");
			dep1.setDepSort(0);
			this.alldepList=new LinkedList<Department>();
			this.alldepList.add(dep1);
			this.depList=new LinkedList<Department>();
			this.depList=this.getDepartmentDao().findAll();
			this.alldepList.addAll(this.depList);	
			//风险名称下拉框allriskList,没用到
			RiskType rt1=new RiskType();
			rt1.setRtId("none1");
			rt1.setRtName("--请选择--");
			rt1.setRtRemark("--请选择--");
			Risk r1=new Risk();
			r1.setRiskId("none1");
			r1.setRiskName("--请选择--");
			r1.setRiskRemark("--请选择--");
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
	
	//高级查询，查询条件:日期始dateFrom、日期终dateTo、风险编号riskId、部门名称reIndep
	@SuppressWarnings("unchecked")
	public String getEventbycondition()
	{
		
		try{
			HttpServletRequest request = ServletActionContext.getRequest(); 
			HttpSession session = request.getSession();
			Users us = (Users)session.getAttribute("loginUser");
//			Department department1=new Department();
//			department1=us.getDepartment();
			// 得到下拉框，录入部门下拉框alldepList和审核状态下拉框allriskList
			getDropDownList();	
			Department dep1 = new Department();
			dep1.setDepId("none1");
			dep1.setDepName("--请选择--");
			dep1.setDepSort(0);
			riskdepList = new LinkedList<Department>();
			riskdepList.add(dep1);
			depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findByDepSort(1);
			riskdepList.addAll(depList);
			depList = this.getDepartmentDao().findByDepSort(2);
			riskdepList.addAll(depList);
			//得到符合查询条件的reList
			String datefromtem=transformDateFrom(this.getDateFrom());//对dateFrom的四位年份进行改造,改造成"yyyy-MM-dd 00:00:00"格式
			String datetotem=transformDateTo(this.getDateTo());//对dateTo的四位年份进行改造,改造成"yyyy-MM-dd 23:59:59"格式
		   
			this.allreList = new LinkedList<AllEventManageView>();
			this.setRiskId("none1");
//			this.allreList = this.getRiskEventDao().findEventByQueryCondition(datefromtem, datetotem, this.riskId, this.reIndep, stateId,(current_pagenum-1)*pageNum,pageNum,this.orderbys);
			this.allreList = this.getAllEventManageViewDao().findEventByQueryCondition(datefromtem, datetotem, this.reIndep,this.riskDep,this.reventStr, this.stateId,(current_pagenum-1)*pageNum,pageNum,this.orderbys);
			
			ServletActionContext.getRequest().setAttribute("allreList", allreList);
			ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
			//this.setActionName("riskInput/REIQQueryAction");
			this.setActionName("riskInput/AllEventIOQueryAction");
			
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
