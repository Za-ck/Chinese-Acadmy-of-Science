package com.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;

import com.dao.ReportTaskDAO;
import com.dao.ReportTaskRiskViewDAO;
import com.dao.RiskDepQueryViewDAO;
import com.dao.RiskEventDAO;
import com.dao.RiskReplyViewDAO;
import com.form.ProcessForm;
import com.model.ReportTaskRiskView;
import com.model.RiskDepQueryView;
import com.model.RiskReplyView;
import com.model.Users;

public class ProcessAction {

	private RiskDepQueryViewDAO riskDepQueryViewDao;
	private ReportTaskRiskViewDAO reportTaskRiskViewDao;
	private ReportTaskDAO reportTaskDao;
	private RiskEventDAO riskEventDao;
	private RiskReplyViewDAO riskReplyViewDao;
	
	public RiskDepQueryViewDAO getRiskDepQueryViewDao() {
		return riskDepQueryViewDao;
	}
	public void setRiskDepQueryViewDao(RiskDepQueryViewDAO riskDepQueryViewDao) {
		this.riskDepQueryViewDao = riskDepQueryViewDao;
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
	
	public RiskEventDAO getRiskEventDao() {
		return riskEventDao;
	}
	public void setRiskEventDao(RiskEventDAO riskEventDao) {
		this.riskEventDao = riskEventDao;
	}
	
	
	public RiskReplyViewDAO getRiskReplyViewDao() {
		return riskReplyViewDao;
	}
	public void setRiskReplyViewDao(RiskReplyViewDAO riskReplyViewDao) {
		this.riskReplyViewDao = riskReplyViewDao;
	}
	// 查询风险事件库待办
	@SuppressWarnings("unchecked")
	public String unProcessed() {
		
		Map<?,?> session = ServletActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		String userId = session.get("userid").toString();
		String username = session.get("username").toString();
		
		List<ProcessForm> formlist = new ArrayList<ProcessForm>();
		
		String category = request.getParameter("category");
		request.setAttribute("category", category);
		//2018.12.13
		String userPosition=session.get("userposition").toString();
		Users  loginUser =(Users)session.get("loginUser");
		
		if(category == null || category.equals("") || category.equals("riskevent")) {
			
			// 风险辨识的待办
			
			List<RiskDepQueryView> reList = riskDepQueryViewDao.findeventall(userId,username,userPosition);
			convertRiskEvent(reList,formlist);
			
		}
		
		if(category == null || category.equals("") || category.equals("riskreport")) {
			// 风险报告的待办
			List<ReportTaskRiskView> reportList = reportTaskRiskViewDao.getUnprocessedReport(userId,userPosition);
			convertRiskReport(reportList,formlist);
			
		}
		
		if (category == null || category.equals("") || category.equals("riskreply")){
			//风险应对的待办
			//前端进行判断是否有权限进行风险应对
			List<RiskReplyView> replyList = riskReplyViewDao.getUnprocessedReply(userId,loginUser.getUserDep());
			convertRiskReply(replyList,formlist);
		}
		
		Collections.sort(formlist);
		
		request.setAttribute("formlist", formlist);
		
		return "unprocessed";
		
	}
	
	private void convertRiskReply(List<RiskReplyView> replyList,
			List<ProcessForm> formlist) {
		if (replyList!=null) {
			for(RiskReplyView reply:replyList){
				ProcessForm form = new ProcessForm();
				form.setName(reply.getReEventname());
				form.setCategory(reply.getRiskName());
				form.setDepname(reply.getReIndep());
				form.setWritername(reply.getReCreator());
				
				if(reply.getTakeTime()==null || "".equals(reply.getTakeTime().trim())) {
					form.setFlowstate("待应对");
					form.setActionname("应对");
					//openTab('riskReply/replyManage','应对管理')
					form.setActionurl("riskReply/replyDetailManage?reid="+reply.getReId());
					form.setModifydate(reply.getRemodifydate());
					//form.setDeleteurl(report.getRfFlowActionName() + "_DeleteReport?reportId="+report.getRerReportId()+"&taskId="+report.getRetTaskId());
					form.setFlowurl("riskInput/REIQReadAction?reId="+reply.getReId()+"&backFlag=reply");
					
				}
				else {
					// 已处理
					form.setFlowstate("已应对");
					form.setActionname("查看详情");
					//var url = "riskInput/REIQReadAction?reId="+reId + "&backFlag=reply";
					//window.parent.openTab(url,"风险应对-" + eventname);
					//handle('riskInput/REIQReadAction?reId=YY-0059-2019-0001','cewfe-企业发展与法律事务部')
					form.setActionurl("riskInput/REIQReadAction?reId="+reply.getReId()+"&backFlag=reply");
					form.setModifydate(reply.getRemodifydate());
					form.setFlowurl("riskInput/REIQReadAction?reId="+reply.getReId()+"&backFlag=reply");

					
				}
				form.setDetailurl("riskInput/REIQReadAction?reId="+reply.getReId()+"&backFlag=reply");
				
				
				
				formlist.add(form);
			}
		}
		
	}
	public String processed() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?,?> session = ServletActionContext.getContext().getSession();
		String userId = session.get("userid").toString();
		String userPosition = session.get("userposition").toString();
		Users  loginUser =(Users)session.get("loginUser");
		
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if(pageNum != null && !pageNum.equals("")) {
			pageSize = Integer.parseInt(pageNum);
		}
		
		List<ProcessForm> formlist = new ArrayList<ProcessForm>();
		
		String category = request.getParameter("category");
		request.setAttribute("category", category);
		
		String orderbys = request.getParameter("orderbys");
		request.setAttribute("orderbys", orderbys);
		
		String dateFrom = request.getParameter("dateFrom");
		request.setAttribute("dateFrom", dateFrom);
		
		String dateTo = request.getParameter("dateTo");
		request.setAttribute("dateTo", dateTo);
		
		// 风险事件库的已办
		if(category.equals("riskevent")) {
			
			int current_pagenum = Integer.parseInt(request.getParameter("current_pagenum"));
			List rchList = this.getRiskEventDao().findadcheckedevent(userId,dateFrom,dateTo,(current_pagenum - 1)*pageSize, pageSize,orderbys,userPosition);
			int size = Integer.parseInt(request.getSession().getAttribute("pagecount").toString());
			int pg = 0;
			if (size % pageSize == 0) {
				pg = size / pageSize;
			} else {
				pg = (size / pageSize) + 1;
			}
			request.setAttribute("pg", pg);
			request.setAttribute("current_pagenum", current_pagenum + "");
			Object[] temp;
			for (int i = 0; i < rchList.size(); i++) {
				temp = (Object[]) rchList.get(i);
				ProcessForm form = new ProcessForm();
				//System.out.println(temp[0] + "----------" + temp[10]);
				form.setName(temp[10].toString());
				form.setCategory(temp[12].toString());
				form.setDepname(temp[4].toString());
				form.setWritername(temp[5].toString());
				
				String state = temp[6].toString();
				String reAct = temp[8].toString();
				
				if(state.equals("*")) {
					
					form.setFlowstate("已发布");
					
				}
				else if(reAct.equals("0")) {
					
					if(state.equals("0")) {
						form.setFlowstate("未修改");
					}
					else {
						form.setFlowstate("未通过");
					}
				}
				else {
					if(state.equals("1")) {
						form.setFlowstate("已提交");
					}
					else {
						form.setFlowstate("等待审核中");
					}
				}
				form.setFlowurl("riskFlow/RiskFlowChart?riskId="+temp[9].toString()+"&resId="+temp[0].toString()+"&reState="+state);
				form.setModifydate(temp[11].toString());
				form.setActionname("查看详情");
				form.setActionurl("riskInput/REIQReadAction?reId="+temp[0].toString());
				form.setDetailurl("riskInput/REIQReadAction?reId="+temp[0].toString());
				formlist.add(form);

			}
		}
		
		if(category == null || category.equals("") || category.equals("riskreport")) {
			// 风险报告的已办
			int size = reportTaskRiskViewDao.getProcessedReportSize("", userId, dateFrom, dateTo, "","",session.get("userposition").toString());
			int offset = paging(request,size);
			
			List<ReportTaskRiskView> processedList = reportTaskRiskViewDao.getProcessedReport("", userId, dateFrom, dateTo, "","", offset, pageSize,orderbys,session.get("userposition").toString());
			
			convertRiskReport(processedList,formlist);
		}
		
		if (category.equals("riskreply")){
			//风险应对的已办
			int size = riskReplyViewDao.getProcessedReportSize("",loginUser.getUserName(),dateFrom,dateTo,"","",loginUser.getUserDep());
			int offset = paging(request,size);
			
			List<RiskReplyView> processedList = riskReplyViewDao.getProcessedReply(loginUser.getUserName(),loginUser.getUserDep(),dateFrom,dateTo,offset,pageSize, orderbys);
			convertRiskReply(processedList,formlist);
		}
		
		request.setAttribute("formlist", formlist);
		request.setAttribute("actionName", "default/processAction_processed");
		return "processed";
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
	
	// 转换风险辨识的待办
	public void convertRiskEvent(List<RiskDepQueryView> reList,List<ProcessForm> formlist) {
		
		// 风险辨识的待办
		if(reList != null) {
			
			for(RiskDepQueryView re:reList) {
				
				ProcessForm form = new ProcessForm();
				form.setName(re.getReEventname());
				form.setCategory(re.getRiskName());
				form.setDepname(re.getDepName());
				form.setWritername(re.getReCreator());
				if(re.getReState().equals("*")) {
					
					form.setFlowstate("已发布");
					
				}
				else if(re.getReState().equals("0")) {
					if("0".equals(re.getReAct())) {
						form.setFlowstate("未修改");
					}
					else {
						form.setFlowstate("未提交");
					}
				}
				else if(re.getReAct().equals("0")) {
					
					form.setFlowstate("未通过");
					
				}
				else {
					if(re.getReState().equals("1")) {
						form.setFlowstate("已提交");
					}
					else {
						form.setFlowstate("等待审核中");
					}
				}
				form.setFlowurl("riskFlow/RiskFlowChart?riskId="+re.getReRiskId()+"&resId="+re.getReId()+"&reState="+re.getReState());
				form.setModifydate(re.getReLastdate());
				if(form.getFlowstate().equals("未修改") || form.getFlowstate().equals("未提交")) {
					
					form.setActionname("修改");
					form.setActionurl("riskInput/getRiskEventAction?id="+re.getReId()+"&updateFlag=update");
					form.setDeleteurl("riskInput/ajaxREIQDelAction?reId="+re.getReId());			//未修改的可以在这里删除
				}
				else {
					
					form.setActionname("审批");
					form.setActionurl("riskFlow/DepVerifyAction?riskeventid="+re.getReId());
					
				}
				form.setDetailurl("riskInput/REIQReadAction?reId="+re.getReId());
				formlist.add(form);
			}
			
		}
		
	}
	
	// 转换风险辨识的待办或者已办
	public void convertRiskReport(List<ReportTaskRiskView> reportList,List<ProcessForm> formlist) {
		
		if(reportList != null) {
			
			for(ReportTaskRiskView report:reportList) {
				
				ProcessForm form = new ProcessForm();
				form.setName(report.getRerReportName());
				form.setCategory(report.getFrFlowName());
				form.setDepname(report.getFiWriterDepName());
				form.setWritername(report.getFiWriterName());
				form.setFlowstate(report.getFiStatements());
				// 表示是已处理的任务
				if(report.getRetState().equals("1")) {
					
					form.setActionname("查看详情");
					form.setActionurl(report.getRfFlowActionName() + "_ReadReport?reportId="+report.getRerReportId()+"&taskId="+report.getRetTaskId());
					form.setModifydate(report.getRetProcessTime());
				}
				else {
					
					form.setActionname(report.getRfAction());
					form.setActionurl(report.getRfActionUrl()+"?reportId="+report.getRerReportId()+"&taskId="+report.getRetTaskId());
					form.setModifydate(report.getFiLastdate());
					//form.setDeleteurl(report.getRfFlowActionName() + "_DeleteReport?reportId="+report.getRerReportId()+"&taskId="+report.getRetTaskId());
				}
				form.setDetailurl(report.getRfFlowActionName() + "_ReadReport?reportId="+report.getRerReportId()+"&taskId="+report.getRetTaskId());
				form.setFlowurl(report.getRfFlowActionName()+"_ReadFlow?reportId="+report.getRerReportId()+"&taskId="+report.getRetTaskId());
				
				
				formlist.add(form);
			}
			
		}
		
	}
	
}
