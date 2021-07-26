package com.action.riskReply;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Holder;

import org.apache.struts2.ServletActionContext;
import org.zhongsoft.pendtasks.PendingTaskPortService;
import org.zhongsoft.pendtasks.PendingTaskPortServiceSoap;

import com.action.ExcelReportAction;
import com.dao.AllAnalysisViewDAO;
import com.dao.DepartmentDAO;
import com.dao.RiskAssessSituationDAO;
import com.dao.RiskManageDAO;
import com.dao.RiskEventDAO;
import com.dao.UsersDAO;
import com.entity.riskManage;
import com.model.AllAnalysisView;
import com.model.Department;
import com.model.RiskEvent;
import com.model.RiskManage;
import com.model.UserMap;
import com.model.Users;
import com.services.TaskExecutionWebServer;
import com.services.ToDoWebServiceAction;

public class replyManageAction {
	private String state="--请选择--";
	private List<riskManage> rmlist;
	private List<AllAnalysisView> riskList;
	private List<AllAnalysisView> riskEventlistall;
	private List<String> allstateList;
	private AllAnalysisViewDAO allAnalysisViewDAO;
	private RiskAssessSituationDAO riskAssessSituationDAO;
	private RiskManageDAO riskManageDao;
	private RiskEventDAO riskEventDao;
	private List<Department> alldepList; //录入部门
	private List<Department> deps;
	private UsersDAO userDAO;
	public UsersDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UsersDAO userDAO) {
		this.userDAO = userDAO;
	}

	private DepartmentDAO departmentDao;
	private String riDepName;
	DecimalFormat dcmFmt = new DecimalFormat("0.00");
	private String reIndep="--请选择--";
	public String dateFrom;
	public String dateTo;
	private String choosedId;
	private String reId;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getChoosedId() {
		return choosedId;
	}

	public void setChoosedId(String choosedId) {
		this.choosedId = choosedId;
	}

	public List<riskManage> getRmlist() {
		return rmlist;
	}

	public void setRmlist(List<riskManage> rmlist) {
		this.rmlist = rmlist;
	}

	public List<AllAnalysisView> getRiskList() {
		return riskList;
	}

	public void setRiskList(List<AllAnalysisView> riskList) {
		this.riskList = riskList;
	}

	public List<AllAnalysisView> getRiskEventlistall() {
		return riskEventlistall;
	}

	public RiskManageDAO getRiskManageDao() {
		return riskManageDao;
	}

	public void setRiskManageDao(RiskManageDAO riskManageDao) {
		this.riskManageDao = riskManageDao;
	}

	public RiskEventDAO getRiskEventDao() {
		return riskEventDao;
	}

	public void setRiskEventDao(RiskEventDAO riskEventDao) {
		this.riskEventDao = riskEventDao;
	}

	public void setRiskEventlistall(List<AllAnalysisView> riskEventlistall) {
		this.riskEventlistall = riskEventlistall;
	}

	public List<String> getAllstateList() {
		return allstateList;
	}

	public void setAllstateList(List<String> allstateList) {
		this.allstateList = allstateList;
	}

	public AllAnalysisViewDAO getAllAnalysisViewDAO() {
		return allAnalysisViewDAO;
	}

	public void setAllAnalysisViewDAO(AllAnalysisViewDAO allAnalysisViewDAO) {
		this.allAnalysisViewDAO = allAnalysisViewDAO;
	}

	public RiskAssessSituationDAO getRiskAssessSituationDAO() {
		return riskAssessSituationDAO;
	}

	public void setRiskAssessSituationDAO(
			RiskAssessSituationDAO riskAssessSituationDAO) {
		this.riskAssessSituationDAO = riskAssessSituationDAO;
	}

	public List<Department> getAlldepList() {
		return alldepList;
	}

	public void setAlldepList(List<Department> alldepList) {
		this.alldepList = alldepList;
	}

	public List<Department> getDeps() {
		return deps;
	}

	public void setDeps(List<Department> deps) {
		this.deps = deps;
	}

	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public String getRiDepName() {
		return riDepName;
	}

	public void setRiDepName(String riDepName) {
		this.riDepName = riDepName;
	}

	public DecimalFormat getDcmFmt() {
		return dcmFmt;
	}

	public void setDcmFmt(DecimalFormat dcmFmt) {
		this.dcmFmt = dcmFmt;
	}

	public String getReIndep() {
		return reIndep;
	}

	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}

	public String getReId() {
		return reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
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

	public String ManageQuery() {
		this.state = "--请选择--";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		this.dateFrom=sf.format(date)+"-01-01";
		this.dateTo=sf.format(date)+"-12-31";
		Users us = getUser();
		int position = us.getUserPosition();
		if(position<=5){
			this.setReIndep(departmentDao.getdepname(us.getUserDep()));
		}else{
			this.setReIndep("--请选择--");
		}
		rmAdvancedQuery();
		return "success";
	}
	
	public String ManageDetailQuery() {
		this.state = "--请选择--";
		HttpServletRequest request = ServletActionContext.getRequest();
		String reid = request.getParameter("reid");
		System.out.println(reid);
		RiskEvent riskEvent = this.getRiskEventDao().findById(reid);
		this.dateFrom=riskEvent.getReDate().substring(0,10);
		this.dateTo=riskEvent.getReDate().substring(0,10);
		Users us = getUser();
		int position = us.getUserPosition();
		if(position<=5){
			this.setReIndep(departmentDao.getdepname(us.getUserDep()));
		}else{
			this.setReIndep("--请选择--");
		}
		rmAdvancedQuery();
		return "success";
	}
	
	public String rmAdvancedQuery() {
		Department dep1 = new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		alldepList = new LinkedList<Department>();
		alldepList.add(dep1);
		deps = new LinkedList<Department>();
		deps = this.getDepartmentDao().findAll();
		alldepList.addAll(deps);
		rmlist = new LinkedList<riskManage>();
		riskList = new LinkedList<AllAnalysisView>();
		allstateList = new LinkedList<String>();
		allstateList.add("--请选择--");
		allstateList.add("已应对");
		allstateList.add("未应对");
		System.out.println("choosedId: "+choosedId);
		//System.out.println("this.dateTo = "+this.dateTo);
		//System.out.println("this.state = "+this.state);
		//System.out.println("this.reIndep = "+this.reIndep);
		Users us = getUser();
		int position = us.getUserPosition();
		String condition = getCondition();
		/*if(position==1||position==3||position==5)
		{
			riskList=allAnalysisViewDAO.findRisk135_new(us.getUserName(),condition);
		}else{
			riskList=allAnalysisViewDAO.findRisk_new(condition);
		}*/		
		riskList=allAnalysisViewDAO.findRisk_new(condition);
		if(riskList != null && riskList.size() > 0)
		{
			getInfo_new(riskList);
		}
		return "success";
	}

	private String getInfo_new(List<AllAnalysisView> ksList3) {
		rmlist=new LinkedList<riskManage>();
		int depvalue = 0;// 综合评定分子
		if(ksList3!=null && ksList3.size()>0)
		{
			int depcount = 1;
			int riskeventcount = 0;
			int riskcount = 1;
			int alleventcount = 0;
			Users us = getUser();
			int position = us.getUserPosition();
			for(int m=0;m<ksList3.size();m++){		
				riskManage ksone1 = new riskManage();
				ksone1.setDepNum(String.valueOf(depcount));
				ksone1.setDepName(ksList3.get(m).getDepName());
				ksone1.setRiskName(ksList3.get(m).getRiskName());
				if(riskcount%2==0){
					ksone1.setRtName(ksList3.get(m).getRtName());
				}else{
					ksone1.setRtName(ksList3.get(m).getRtName()+" ");
				}				
				ksone1.setReEventname(ksList3.get(m).getReEventname());
				ksone1.setRiAlldegree(dcmFmt.format(ksList3.get(m).getRiAlldegree()));
				ksone1.setRiProdegree(Integer.toString(ksList3.get(m).getRiProdegree()));
				ksone1.setRiKpi(ksList3.get(m).getRiKpi());
				ksone1.setRiAllvalue(String.valueOf(dcmFmt.format(ksList3.get(m).getRiAllvalue())));
				if("2".equals(choosedId)){
					ksone1.setRiEventDate(ksList3.get(m).getReModifydate());
				}else{
					ksone1.setRiEventDate(ksList3.get(m).getReDate());
				}								
				depvalue += ksList3.get(m).getRiAllvalue();
				ksone1.setRiReply(ksList3.get(m).getRiReply());
				ksone1.setRiplandate(ksList3.get(m).getRiplandate());
				ksone1.setRiplanres(ksList3.get(m).getRiplanres());
				String time = ksList3.get(m).getRitaketime();
				if(time==null||time.length()<5)
				{
					ksone1.setRiState("未应对");
				}else{
					ksone1.setRiState("已应对");
				}
				ksone1.setRiPerson(ksList3.get(m).getRireplyman());
				ksone1.setRitaketime(ksList3.get(m).getRitaketime());
				ksone1.setReId(ksList3.get(m).getReId());
				if((position==2||position==4||position==6||position==7||position==8)&&(us.getUserDep().equals(ksList3.get(m).getReIndep()))){
					ksone1.setUserPower("1");
				}else{
					ksone1.setUserPower("0");
				}						
				rmlist.add(ksone1);
				riskeventcount++;
				if(m<ksList3.size()-1){
					if(!(ksList3.get(m).getDepName().equals(ksList3.get(m+1).getDepName()))){
						riskManage ksone2 = new riskManage();
						ksone2.setDepNum(depcount+" ");
						ksone2.setDepName(ksList3.get(m).getDepName()+" ");
						ksone2.setRiskName(riskcount+" ");
						ksone2.setRtName("小计");
						ksone2.setReEventname(riskeventcount+"");
						rmlist.add(ksone2);
						alleventcount+=riskeventcount;
						depcount++;
						riskeventcount = 0;
						riskcount = 1;
					}else if(!(ksList3.get(m).getRiskName().equals(ksList3.get(m+1).getRiskName()))){
						riskcount++;
					}
				}
				if(m==ksList3.size()-1){
					riskManage ksone3 = new riskManage();
					ksone3.setDepNum(depcount+" ");
					ksone3.setDepName(ksList3.get(m).getDepName()+" ");
					ksone3.setRiskName(riskcount+" ");
					ksone3.setRtName("小计");
					ksone3.setReEventname(riskeventcount+"");
					rmlist.add(ksone3);
					alleventcount+=riskeventcount;
					riskeventcount=0;
				}
			}
			riskManage ksone4 = new riskManage();
			ksone4.setDepNum("总计");
			ksone4.setDepName(depcount+" ");
			ksone4.setRiskName("-");//插入二级风险对应的部门个数
			ksone4.setRtName("-");
			ksone4.setReEventname(Integer.toString(alleventcount)+" ");//插入二级风险对应的风险事件个数
			rmlist.add(ksone4);			
		}
		// 数据存放在session中，便于导出excel
		String[][] dsarray = new String[rmlist.size()][15];
		for (int m = 0; m < rmlist.size(); m++) {
			dsarray[m][0] = rmlist.get(m).getDepNum().trim();
			dsarray[m][1] = rmlist.get(m).getDepName().trim();
			dsarray[m][2] = rmlist.get(m).getRiskName();
			dsarray[m][3] = rmlist.get(m).getRtName();
			dsarray[m][4] = rmlist.get(m).getReEventname();
			dsarray[m][5] = rmlist.get(m).getRiAlldegree();
			dsarray[m][6] = rmlist.get(m).getRiProdegree();
			dsarray[m][7] = rmlist.get(m).getRiKpi();
			dsarray[m][8] = rmlist.get(m).getRiAllvalue();
			dsarray[m][9] = rmlist.get(m).getRiReply();
			dsarray[m][10] = rmlist.get(m).getRiplandate();
			dsarray[m][11] = rmlist.get(m).getRiplanres();
			dsarray[m][12] = rmlist.get(m).getRiState();
			dsarray[m][13] = rmlist.get(m).getRiPerson();
			dsarray[m][14] = rmlist.get(m).getRitaketime();
		}
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		session.put("exportManageList", dsarray);		
		return "success";	
		
	}
	
	// 点击“应对”，更新riskmanage的sign字段，1表示已实施应对措施，0表示未实施
	public String riskhandle() {
		try {
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Users us = getUser();
			String name = us.getUserName();
			RiskEvent riskEvent = this.getRiskEventDao().findById(this.reId);
			String inputDepId = riskEvent.getReIndep();
			String inputDepName = departmentDao.getdepname(us.getUserDep());
			this.getRiskManageDao().updatehandled(1, this.reId,df.format(date).toString(),name);
			//调用接口进行事件删除
			Users userOut = (Users) this.getUserDAO().findByProperty("userPosition", 9).get(0);
			inThreads("delete",userOut, riskEvent.getReId(), us.getUserId());
			
			// 获取计划采取措施时间
			RiskManage rm = this.getRiskManageDao().findById(this.reId);
			String plandate = rm.getRmPlandate();
			this.getRiskAssessSituationDAO().update(inputDepId,inputDepName,df.format(date),plandate);
			HttpServletRequest request = ServletActionContext.getRequest();
			this.state=new String(request.getParameter("state").getBytes("ISO-8859-1"),"UTF-8");
			this.reIndep=new String(request.getParameter("reIndep").getBytes("ISO-8859-1"),"UTF-8");
			this.dateFrom = request.getParameter("dateFrom");
			this.dateTo = request.getParameter("dateTo");
			rmAdvancedQuery();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	//导出应对管理结果
	
	public String manageExportExcel() {
		Map session = ServletActionContext.getContext().getSession();
		if (session.get("exportManageList") == null)
			return "fail";
		else {
			String str = "";
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("应对管理结果", "replymanageTemplate",
					(String[][]) session.get("exportManageList"), 3, 5, str);// 3表示从第四行开始显示，6表示从第六列开始合并，str是查询条件
			return "success";
		}
	}
	
	//查询条件
	private String getCondition() {
		String queryString = "";
		String dateFrom1="";
		String dateTo1="";
		String datem="";
		String date1="";
		for(int i=0;i<4;i++){
			date1+=this.dateFrom.charAt(i);
		}
		if("2".equals(choosedId)){
			datem="model.reModifydate";
		}
		else if("3".equals(choosedId)){
			datem="model.ritaketime";
		}
		else{
			datem="model.reDate";
		}
		if("".equals(this.dateFrom)||this.dateFrom == null){
			dateFrom1="2000-01-01 00:00:00";
		}
		else if("3".equals(choosedId)){
			if("".equals(this.dateFrom)||this.dateFrom == null){
				dateFrom1="2017-01-01 00:00:00";
			}
			else if(Integer.parseInt(date1)<2017){
				dateFrom1="2017-01-01 00:00:00";
			}
			else{
				dateFrom1 = this.dateFrom+" 00:00:00";
			}
			
		}

		else{
			dateFrom1 = this.dateFrom+" 00:00:00";
		}
		if("".equals(dateTo)||dateTo == null){
			dateTo1="2050-12-31 23:59:59";	
		}else{
			dateTo1 =this.dateTo+" 23:59:59";
		}
		queryString += " "+datem+" between '"+dateFrom1+"' and '"+dateTo1 + "'";
		if("--请选择--".equals(this.reIndep)){
			queryString += "";
		}else{
			queryString += " and model.depName='"+this.reIndep+"'";
		}
		if("--请选择--".equals(this.state))
		{
			queryString += "";
		}
		else if("已应对".equals(this.state)){
			queryString += " and len(model.ritaketime)>5";
		}else{
			queryString += " and (model.ritaketime is NULL or len(model.ritaketime)<=5)";
		}
		return queryString;
	}
		
	//得到用户信息
	private Users getUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		return us;
	}
	
	/*
	 * 调用待办，将其放入线程池，由Tomcat服务器执行
	 */
	public String inThreads(String taskFlag,final Users Out_us, final String Out_reId,final String Out_userid)
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
			String TaskTitle = "已发布风险事件（" + Out_reId + "）";
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
}
