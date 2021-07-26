package com.action.riskReport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.DepartmentDAO;
import com.dao.ReportFlowRuleDAO;
import com.dao.ReportFlowRuleInfoDAO;
import com.dao.ReportTaskDAO;
import com.dao.ReportTaskRiskViewDAO;
import com.model.Department;
import com.model.ReportFlowRuleInfo;
import com.model.ReportTaskRiskView;

@SuppressWarnings("unchecked")
public class RiskReportProcessAction {

	private static final List<ReportFlowRuleInfo> FLOWRULELIST = new ArrayList<ReportFlowRuleInfo>();
	
	private ReportFlowRuleDAO reportFlowRuleDao;
	private ReportFlowRuleInfoDAO reportFlowRuleInfoDao;
	private ReportTaskRiskViewDAO reportTaskRiskViewDao;
	private ReportTaskDAO reportTaskDao;
	
	private List<Department> alldepList;  //编写部门的列表
	
	private List<Department> depList;
	
	private DepartmentDAO departmentDao;
	
	private String reIndep;
	
	

	public String getReIndep() {
		return reIndep;
	}

	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
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

	public ReportFlowRuleDAO getReportFlowRuleDao() {
		return reportFlowRuleDao;
	}

	public void setReportFlowRuleDao(ReportFlowRuleDAO reportFlowRuleDao) {
		this.reportFlowRuleDao = reportFlowRuleDao;
	}
	
	public ReportFlowRuleInfoDAO getReportFlowRuleInfoDao() {
		return reportFlowRuleInfoDao;
	}

	public void setReportFlowRuleInfoDao(ReportFlowRuleInfoDAO reportFlowRuleInfoDao) {
		this.reportFlowRuleInfoDao = reportFlowRuleInfoDao;
	}
	
	public ReportTaskRiskViewDAO getReportTaskRiskViewDao() {
		return reportTaskRiskViewDao;
	}

	public void setReportTaskRiskViewDao(ReportTaskRiskViewDAO reportTaskRiskViewDao) {
		this.reportTaskRiskViewDao = reportTaskRiskViewDao;
	}

	public ReportTaskDAO getReportTaskDao() {
		return reportTaskDao;
	}

	public void setReportTaskDao(ReportTaskDAO reportTaskDao) {
		this.reportTaskDao = reportTaskDao;
	}

	//初始化函数
	public void init() {
		List<ReportFlowRuleInfo> list = reportFlowRuleInfoDao.findAll();
		for(ReportFlowRuleInfo info : list) {
			FLOWRULELIST.add(info);
		}
	}
	
	//查询任务
	public String getTask() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		String userId = session.get("userid").toString();
		
		request.setAttribute("cur_userId", userId);
		
		
		String pageUrl = "";
		String date = request.getParameter("date");
		String flowId = request.getParameter("flowrules");
		String reportname = request.getParameter("reportname");
		//String reIndep = request.getParameter("reIndep");   
		if(date != null && !date.equals("")) {
			pageUrl += "&date=" + date;
		}
		if(flowId != null && !flowId.equals("") && !flowId.equals("none")) {
			pageUrl += "&flowId=" + flowId;
		}
		if(reportname != null && !reportname.equals("")) {
			pageUrl += "&reportname=" + reportname;
		}
		if(reIndep !=null && !reIndep.equals("") && !reIndep.equals("--请选择--")){
			pageUrl += "&reIndep=" + reIndep;
		}
		request.setAttribute("pageUrl", pageUrl);
		request.setAttribute("date", date);
		request.setAttribute("flowId", flowId);
		request.setAttribute("reportname", reportname);
		//request.setAttribute("reIndep", reIndep);
		request.setAttribute("flows", FLOWRULELIST);
		
		String state = request.getParameter("state");
		
		if(state == null || state.equals("")) {
			int size = reportTaskRiskViewDao.getTaskSize(reportname, userId, date, flowId,reIndep,session.get("userposition").toString());
			int offset = paging(request,size);
			int pageSize = 10;
			String pageNum = request.getParameter("pageNum");
			if(pageNum != null && !pageNum.equals("")) {
				pageSize = Integer.parseInt(pageNum);
			}
			List<ReportTaskRiskView> taskList = reportTaskRiskViewDao.getTask(reportname, userId, date, flowId,reIndep, offset, pageSize,session.get("userposition").toString());
			request.setAttribute("taskList", taskList);
		}
		else {
			// 这条任务线是完成了还是没有完成
			state = request.getParameter("state").substring(0, 1);
			if(state.equals("0")) {
				request.setAttribute("state", "0");
				getUnprocessedTask(reportname, userId, date, flowId,reIndep);
			}
			else {
				request.setAttribute("state", "1");
				getProcessedTask(reportname, userId, date, flowId,reIndep);
			}
		}
		
		request.setAttribute("actionName", "riskReport/riskReportProcessAction_getTask");
		
		//部门下拉列表显示
		Department dep1=new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		alldepList=new LinkedList<Department>();
		alldepList.add(dep1);
		depList=new LinkedList<Department>();
		depList=this.getDepartmentDao().findAll();
		alldepList.addAll(depList);	
		return "task";
	}
	
	
	//查询已完成的任务
	public void getProcessedTask(String reportname, String userId, String date, String flowId,String reIndep) {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		int size = reportTaskRiskViewDao.getProcessedTaskSize(reportname, userId, date,null, flowId,reIndep,session.get("userposition").toString());
		int offset = paging(request,size);
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if(pageNum != null && !pageNum.equals("")) {
			pageSize = Integer.parseInt(pageNum);
		}
		List<ReportTaskRiskView> processedList = reportTaskRiskViewDao.getProcessedTask(reportname, userId, date,null, flowId, reIndep,offset, pageSize,null,session.get("userposition").toString());
		
		request.setAttribute("taskList", processedList);
		
		
	}
	
	//查询未完成的任务
	public void getUnprocessedTask(String reportname, String userId, String date, String flowId,String reIndep) {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		int size = reportTaskRiskViewDao.getUnprocessedTaskSize(reportname, userId, date, flowId,reIndep,session.get("userposition").toString());
		int offset = paging(request,size);
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if(pageNum != null && !pageNum.equals("")) {
			pageSize = Integer.parseInt(pageNum);
		}
		List<ReportTaskRiskView> unprocessedList = reportTaskRiskViewDao.getUnprocessedTask(reportname, userId, date, flowId,reIndep, offset, pageSize,session.get("userposition").toString());
		
		request.setAttribute("taskList", unprocessedList);
		
	}
	
	
	private int paging(HttpServletRequest request, int size) {
		
		request.getSession().setAttribute("pagecount", size);
		String current_pagenum = request.getParameter("current_pagenum");
		String pageNum = request.getParameter("pageNum");
		
		int pageSize = 10;
		int offset = 0;
		
		if(pageNum != null && !pageNum.equals("")) {
			pageSize = Integer.parseInt(pageNum);
		}
		
		int pg = 0;
		if (size % pageSize == 0) {
			pg = size / pageSize;
		} else {
			pg = (size / pageSize) + 1;
		}
		request.setAttribute("pg", pg);
		
		if(current_pagenum != null && !current_pagenum.equals("")) {
			
			offset = (Integer.parseInt(current_pagenum) - 1) * pageSize;
		}
		else {
			current_pagenum = "1";
		}
		request.setAttribute("current_pagenum", current_pagenum);
		return offset;
	}
	
}
