package com.action.riskReport;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.ReportFlowRuleDAO;
import com.dao.ReportFlowRuleInfoDAO;
import com.dao.ReportTaskDAO;
import com.dao.ReportTaskRiskViewDAO;
import com.model.ReportTaskRiskView;

public class RiskReportAction {
	
	private ReportTaskRiskViewDAO reportTaskRiskViewDao;
	private ReportTaskDAO reportTaskDao;
	private ReportFlowRuleDAO reportFlowRuleDao;
	private ReportFlowRuleInfoDAO reportFlowRuleInfoDao;

	public ReportTaskRiskViewDAO getReportTaskRiskViewDao() {
		return reportTaskRiskViewDao;
	}

	public void setReportTaskRiskViewDao(ReportTaskRiskViewDAO reportTaskRiskViewDao) {
		this.reportTaskRiskViewDao = reportTaskRiskViewDao;
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

	public ReportTaskDAO getReportTaskDao() {
		return reportTaskDao;
	}

	public void setReportTaskDao(ReportTaskDAO reportTaskDao) {
		this.reportTaskDao = reportTaskDao;
	}

	public String getReport() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		String userId = session.get("userid").toString();
		String userPosition=session.get("userposition").toString();
		
		String date = request.getParameter("date");
		String reportname = request.getParameter("reportname");
		
		String state = request.getParameter("state");
		int size = reportTaskRiskViewDao.getReportSize(reportname, date, userId,userPosition, state);
		
		int offset = paging(request,size);
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if(pageNum != null && !pageNum.equals("")) {
			pageSize = Integer.parseInt(pageNum);
		}
		List<ReportTaskRiskView> taskList = reportTaskRiskViewDao.getReport(reportname, date, userId,userPosition, state, offset, pageSize);
		request.setAttribute("taskList", taskList);
		
		request.setAttribute("actionName", "riskReport/riskReportAction_getReport");
		request.setAttribute("date", date);
		request.setAttribute("reportname", reportname);
		
		return "report";
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
