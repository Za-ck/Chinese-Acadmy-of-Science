package com.action.riskReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.DepartmentDAO;
import com.dao.ReportCheckDAO;
import com.dao.ReportDepartmentDAO;
import com.dao.ReportFlowRuleInfoDAO;
import com.dao.ReportRiskDAO;
import com.dao.ReportRiskDepDAO;
import com.dao.ReportTaskRiskViewDAO;
import com.form.FlowForm;
import com.model.ReportCheck;
import com.model.ReportDepartment;
import com.model.ReportFlowRuleInfo;
import com.model.ReportRisk;
import com.model.ReportRiskDep;
import com.model.ReportTaskRiskView;

public class ReportReadAction {

	private static final List<ReportFlowRuleInfo> FLOWRULELIST = new ArrayList<ReportFlowRuleInfo>();
	private static final Map<String,String> FLOWRULEMAP = new HashMap<String,String>();
	
	private ReportRiskDAO reportRiskDao;
	private ReportDepartmentDAO reportDepartmentDao;
	private ReportCheckDAO reportCheckDao;
	private ReportRiskDepDAO reportRiskDepDao;
	private DepartmentDAO departmentDao;
	private ReportFlowRuleInfoDAO reportFlowRuleInfoDao;
	private ReportTaskRiskViewDAO reportTaskRiskViewDao;
	
	public ReportTaskRiskViewDAO getReportTaskRiskViewDao() {
		return reportTaskRiskViewDao;
	}

	public void setReportTaskRiskViewDao(ReportTaskRiskViewDAO reportTaskRiskViewDao) {
		this.reportTaskRiskViewDao = reportTaskRiskViewDao;
	}

	public ReportRiskDAO getReportRiskDao() {
		return reportRiskDao;
	}

	public void setReportRiskDao(ReportRiskDAO reportRiskDao) {
		this.reportRiskDao = reportRiskDao;
	}

	public ReportDepartmentDAO getReportDepartmentDao() {
		return reportDepartmentDao;
	}

	public void setReportDepartmentDao(ReportDepartmentDAO reportDepartmentDao) {
		this.reportDepartmentDao = reportDepartmentDao;
	}

	public ReportCheckDAO getReportCheckDao() {
		return reportCheckDao;
	}

	public void setReportCheckDao(ReportCheckDAO reportCheckDao) {
		this.reportCheckDao = reportCheckDao;
	}

	public ReportRiskDepDAO getReportRiskDepDao() {
		return reportRiskDepDao;
	}

	public void setReportRiskDepDao(ReportRiskDepDAO reportRiskDepDao) {
		this.reportRiskDepDao = reportRiskDepDao;
	}
	
	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	public ReportFlowRuleInfoDAO getReportFlowRuleInfoDao() {
		return reportFlowRuleInfoDao;
	}

	public void setReportFlowRuleInfoDao(ReportFlowRuleInfoDAO reportFlowRuleInfoDao) {
		this.reportFlowRuleInfoDao = reportFlowRuleInfoDao;
	}

	//初始化函数
	@SuppressWarnings("unchecked")
	public void init() {
		List<ReportFlowRuleInfo> list = reportFlowRuleInfoDao.findAll();
		for(ReportFlowRuleInfo info : list) {
			FLOWRULELIST.add(info);
			FLOWRULEMAP.put(info.getFrFlowId(), info.getFrFlowName());
		}
		
	}

	//双击报告台账显示的信息
	@SuppressWarnings("unchecked")
	public String execute() {
		
		// 获取报告的基本信息
		HttpServletRequest request = ServletActionContext.getRequest();
		//Map<?, ?> session = ServletActionContext.getContext().getSession();
		String reportId = request.getParameter("reportId");
		
		ReportRisk report = reportRiskDao.findById(reportId);
		
		List<ReportRiskDep> rrdList = reportRiskDepDao.findByProperty("rrdReportId", reportId);
		
		request.setAttribute("report", report);
		request.setAttribute("leaderlist", rrdList);
		
		
		// 查询部门报告提交流程
		List<ReportDepartment> redeplist = reportDepartmentDao.getRepDepList(reportId);
		List<FlowForm> depflow = new ArrayList<FlowForm>();

		// 部门提交报告
		List<String> depIdList = new ArrayList<String>();
		if(rrdList != null && rrdList.size() > 0) {
			for(ReportRiskDep rrd : rrdList) {
				depIdList.add(rrd.getRrdDepId());
			}
		}
		
		if(redeplist != null && redeplist.size() > 0) {
			
			for(ReportDepartment redep:redeplist) {
				
				depIdList.remove(redep.getRdDepId());
				
				FlowForm form = new FlowForm();
				form.setReportname(report.getRerReportName());
				form.setDepname(redep.getRdDepName());
				form.setBeginner(redep.getRdWriterName());
				form.setFlowname(FLOWRULEMAP.get("BMBGTJ"));
				/*if(redep.getRdFlowstatus().equals("*")) {
					
					form.setFlowstate("已完成");
					
				}
				else {
					form.setFlowstate("未完成");
				}*/
				List<ReportTaskRiskView> flowtasks = reportTaskRiskViewDao.getFlowTask(redep.getRdWriterId(), report.getRerReportName(), "BMBGTJ");
				//System.out.println("---------------" + redep.getRdDepName() + "--------------");
				ReportTaskRiskView flowtask = flowtasks.get(0);
				String url = "<a href=" +flowtask.getRfFlowActionName()+"_ReadFlow?reportId="+flowtask.getRerReportId()+"&taskId="+flowtask.getRetTaskId()+" target=_blank>"+flowtask.getFiStatements()+"</a>"; 
				form.setFlowstate(url);
				form.setReportId(report.getRerReportId());
				form.setDepId(redep.getRdDepId());
				depflow.add(form);
			}
			
		}
		
		// 还未开始编写的部门提交报告
		if(depIdList.size() > 0) {
			for(String depId : depIdList) {
				FlowForm form = new FlowForm();
				form.setReportname(report.getRerReportName());
				form.setDepname(departmentDao.getdepname(depId));
				form.setFlowname(FLOWRULEMAP.get("BMBGTJ"));
				
				String url = "<a href=" + FLOWRULELIST.get(1).getFrFlowActionName()+"_ReadFlow?reportId=" + report.getRerReportId() + " target=_blank>未编写</a>"; 
				form.setFlowstate(url);
				form.setReportId(report.getRerReportId());
				form.setDepId(depId);
				
				depflow.add(form);
			}
			
		}
		request.setAttribute("depflow", depflow);
		
		// 查询汇总报告提交流程
		ReportCheck check = reportCheckDao.getSpecificRepCheck(reportId);
		if(check != null) {
			
			FlowForm endflow = new FlowForm();
			endflow.setBeginner(check.getRcWriterName());
			endflow.setDepname(check.getRcDepName());
			endflow.setFlowname(FLOWRULEMAP.get("HZBGSP"));
			endflow.setReportname(report.getRerReportName());
			/*if(check.getRcFlowStatus().equals("*")) {
				
				endflow.setFlowstate("已完成");
				
			}
			else {
				endflow.setFlowstate("未完成");
			}*/
			endflow.setReportId(report.getRerReportId());
			endflow.setDepId(check.getRcDepId());
			//必须从ReportTaskRiskView中获取到相应的endtask,并将其传递至前台
			List<ReportTaskRiskView> endtasks = reportTaskRiskViewDao.getFirstTask(report.getRerReportName(), "HZBGSP");
			ReportTaskRiskView endtask = endtasks.get(0);
			
			
			if(endtask.getFiStatements().equals("领导未审核")){
				endtask.setFiStatements("企发部领导未审核");
			}
			if(endtask.getFiStatements().equals("审核不通过")){
				endtask.setFiStatements("企发部领导审核不通过");
			}
			request.setAttribute("endtask", endtask);
			
			request.setAttribute("endflow", endflow);
		}
		
		// 查询报告启动流程
		FlowForm startflow = new FlowForm();
		startflow.setBeginner(report.getRerPromoterName());
		startflow.setDepId("QYFZ");
		startflow.setDepname(departmentDao.getdepname(startflow.getDepId()));
		startflow.setFlowname(FLOWRULEMAP.get("BGBXQD"));
		startflow.setReportname(report.getRerReportName());
		//startflow.setFlowstate("已完成");
		/*if(rrdList == null || rrdList.size() <= 0) {
			startflow.setFlowstate("未完成");
		}
		else {
			for(ReportRiskDep rrd : rrdList) {
				if(!rrd.getRrdStatus().equals("*")) {
					startflow.setFlowstate("未完成");
					break;
				}
			}
		}*/
		startflow.setReportId(report.getRerReportId());
		
		//必须从ReportTaskRiskView中获取到相应的starttask,并将其传递至前台
		List<ReportTaskRiskView> starttasks = reportTaskRiskViewDao.getFirstTask(report.getRerReportName(), "BGBXQD");
		ReportTaskRiskView starttask = 	starttasks.get(0);
		
		request.setAttribute("starttask", starttask);
		
		request.setAttribute("startflow", startflow);
		
		return "success";
		
	}
	
}
