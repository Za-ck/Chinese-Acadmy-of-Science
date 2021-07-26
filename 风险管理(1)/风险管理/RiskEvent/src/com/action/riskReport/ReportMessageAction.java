package com.action.riskReport;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.dao.ReportMessageDAO;
import com.model.ReportMessage;

public class ReportMessageAction {

	private ReportMessageDAO reportMessageDao;
	
	public ReportMessageDAO getReportMessageDao() {
		return reportMessageDao;
	}


	public void setReportMessageDao(ReportMessageDAO reportMessageDao) {
		this.reportMessageDao = reportMessageDao;
	}


	public String getReportMessage() {
		
		Map<?,?> session = ServletActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		String userId = session.get("userid").toString();
		
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if(pageNum != null && !pageNum.equals("")) {
			pageSize = Integer.parseInt(pageNum);
		}
		
		String orderbys = request.getParameter("orderbys");
		request.setAttribute("orderbys", orderbys);
		
		String dateFrom = request.getParameter("dateFrom");
		request.setAttribute("dateFrom", dateFrom);
		
		String dateTo = request.getParameter("dateTo");
		request.setAttribute("dateTo", dateTo);
		
		String state = request.getParameter("messagestate");
		request.setAttribute("state", state);
		
		int size = reportMessageDao.getReportMessageSize(state, userId, dateFrom, dateTo);
		int offset = paging(request,size);
		
		List<ReportMessage> messagelist = reportMessageDao.getReportMessage(state, userId, dateFrom, dateTo, offset, pageSize, orderbys);
		
		request.setAttribute("messagelist", messagelist);
		request.setAttribute("actionName", "default/reportMessageAction_getReportMessage");
		
		return "success";
	}
	
	public void updateState() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String messageId =  request.getParameter("messageId");
		
		try {
			
			PrintWriter out = response.getWriter();
			// 先要判断是否已经有人已经填写了报告了
			reportMessageDao.updateMessageState(messageId, "1");
			out.print("success");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
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
