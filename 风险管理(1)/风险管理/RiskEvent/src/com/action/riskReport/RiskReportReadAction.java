package com.action.riskReport;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.dao.ReportFormInfoDAO;
import com.dao.ReadFlowViewDAO;
import com.dao.ReportFlowRuleDAO;
import com.dao.ReportRiskDAO;
import com.dao.ReportRiskDepDAO;
import com.dao.ReportRiskFileDAO;
import com.dao.ReportTaskDAO;
import com.model.ReadFlowView;
import com.model.ReportFlowRule;
import com.model.ReportRisk;
import com.model.ReportRiskDep;
import com.model.ReportRiskFile;
import com.model.ReportTask;
import com.util.FileUtil;

public class RiskReportReadAction {

	private ReportRiskDAO reportRiskDao;
	private ReportTaskDAO reportTaskDao;
	private ReportRiskDepDAO reportRiskDepDao;
	private ReportRiskFileDAO reportRiskFileDao;
	private ReportFlowRuleDAO reportFlowRuleDao;
	private ReadFlowViewDAO readFlowViewDao;
	private ReportFormInfoDAO formInfoDao;
	
	public ReportRiskDAO getReportRiskDao() {
		return reportRiskDao;
	}

	public void setReportRiskDao(ReportRiskDAO reportRiskDao) {
		this.reportRiskDao = reportRiskDao;
	}

	public ReportTaskDAO getReportTaskDao() {
		return reportTaskDao;
	}

	public void setReportTaskDao(ReportTaskDAO reportTaskDao) {
		this.reportTaskDao = reportTaskDao;
	}

	public ReportRiskDepDAO getReportRiskDepDao() {
		return reportRiskDepDao;
	}

	public void setReportRiskDepDao(ReportRiskDepDAO reportRiskDepDao) {
		this.reportRiskDepDao = reportRiskDepDao;
	}

	public ReportRiskFileDAO getReportRiskFileDao() {
		return reportRiskFileDao;
	}

	public void setReportRiskFileDao(ReportRiskFileDAO reportRiskFileDao) {
		this.reportRiskFileDao = reportRiskFileDao;
	}

	public ReportFlowRuleDAO getReportFlowRuleDao() {
		return reportFlowRuleDao;
	}

	public void setReportFlowRuleDao(ReportFlowRuleDAO reportFlowRuleDao) {
		this.reportFlowRuleDao = reportFlowRuleDao;
	}

	public ReadFlowViewDAO getReadFlowViewDao() {
		return readFlowViewDao;
	}

	public void setReadFlowViewDao(ReadFlowViewDAO readFlowViewDao) {
		this.readFlowViewDao = readFlowViewDao;
	}

	public ReportFormInfoDAO getFormInfoDao() {
		return formInfoDao;
	}

	public void setFormInfoDao(ReportFormInfoDAO formInfoDao) {
		this.formInfoDao = formInfoDao;
	}

	// ???????????????????????????
	@SuppressWarnings("unchecked")
	public String ReadReport() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		String userId = session.get("userid").toString();
		String reportId = request.getParameter("reportId");
		String taskId = request.getParameter("taskId");
		
		ReportRisk report = reportRiskDao.findById(reportId);
		
		List<ReportRiskDep> rrdList = reportRiskDepDao.findByProperty("rrdReportId", reportId);
		
		request.setAttribute("report", report);
		request.setAttribute("taskId", taskId);
		request.setAttribute("leaderlist", rrdList);
		request.setAttribute("userId", userId);
		
		
		List<ReportRiskFile> files = reportRiskFileDao.findByProperty("fileReportId", reportId);
		//????????????????????????????????????
		Collections.sort(files, new Comparator<ReportRiskFile>(){

			@Override
			public int compare(ReportRiskFile o1, ReportRiskFile o2) {
				return o1.getFileDate().compareTo(o2.getFileDate());
			}});
		
		Collections.reverse(files);
		
		request.setAttribute("filelist", files);
		
		request.setAttribute("actionName", "riskReport/riskReportReadAction");
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	public void readFile() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		try {
			
			PrintWriter out = response.getWriter();
			String reportId = request.getParameter("reportId");
			List<ReportRiskFile> files = reportRiskFileDao.findByProperty("fileReportId", reportId);
			//????????????????????????
			Collections.sort(files, new Comparator<ReportRiskFile>(){
				@Override
				public int compare(ReportRiskFile o1, ReportRiskFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
			});
			
			JSONArray jsonArray = new JSONArray();
			for(ReportRiskFile file : files) {
				JSONObject object = new JSONObject();
				object.put("filename", file.getFileFilename());
				object.put("flowname", "????????????????????????");
				object.put("uploader",file.getFileUploadername());
				object.put("uploadtime", file.getFileDate());
				object.put("path", "/RiskEvent/riskReport/riskReportInputAction_downloadFile?fileId="+file.getFileId());
				jsonArray.add(object.toString());
			}
			out.print(jsonArray.toString());
			out.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	// ?????????????????????
	@SuppressWarnings("unchecked")
	public void downloadFile() {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			HttpServletResponse response = ServletActionContext.getResponse();
			
			String reportId = request.getParameter("reportId");
			//String writerId = request.getParameter("writerId");
			String reportname = new String(request.getParameter("reportname").getBytes("ISO-8859-1"),"UTF-8");
			
			// ???????????????????????????
			List<ReportRiskFile> files = reportRiskFileDao.findByProperty("fileReportId", reportId);
			//request.setAttribute("filelist", files);
			
			Map<String,String> fileMap = new HashMap<String,String>();
			for(ReportRiskFile file : files) {
				
				fileMap.put(file.getFileFilename(), file.getFileFilepath());
				
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String filename = reportname + "-" + files.get(0).getFileUploadername() + "-" + df.format(new Date());
			//System.out.println(filename);
			FileUtil.downloadFileList(response, fileMap, filename);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("??????????????????");
		}
		
		
	}
	
	// ????????????
	public void revocationRiskReport() {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			String reportId = request.getParameter("reportId");
			String taskId = request.getParameter("taskId");
			//1????????????????????????
			int revocationFlag = reportTaskDao.getRevocationFlag(taskId);
			if(revocationFlag == 0) {
				//??????????????????
				out.write("fail");
				out.flush();
				return;
			}
			//2??????????????????????????????
			ReportTask task = reportTaskDao.revocateTask(taskId);
			//3?????????ReportRiskDep?????????????????????
			reportRiskDepDao.revocationReportRiskDep(reportId);
			//4?????????ReportRisk???????????????
			reportRiskDao.resetCancelNum(reportId);
			
			//5???????????????????????????????????????????????????
			ReportFlowRule rule = reportFlowRuleDao.getFlowState(task.getRetFlowId(), task.getRetFlowStatus());
			
			//6?????????formInfo????????????
			formInfoDao.updateFormState(reportId, task.getRetFlowStatus(), rule.getRfFlowExplain());
			
			out.print(rule.getRfActionUrl() + "?reportId="+ reportId +"&taskId="+task.getRetTaskId());
			out.flush();
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	public String ReadFlow() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		//String reportId = request.getParameter("reportId");
		
		String taskId = request.getParameter("taskId");
		if(taskId != null && !taskId.equals("")) {
			
			ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
			String formId = task.getRetFormId();
			
			List<ReadFlowView> recordlist = readFlowViewDao.getRecordlist(formId);
			request.setAttribute("recordlist", recordlist);
			request.setAttribute("userId", session.get("userid").toString());
			request.setAttribute("flowImage", getFlowImage(formId));
			request.setAttribute("title", task.getRetReportName());
		}
		
		else {
			ReportFlowRule rule = reportFlowRuleDao.getFlowState("BGBXQD", "0");
			request.setAttribute("flowImage", rule.getRfFlowImage());
			request.setAttribute("title", "????????????????????????");
		}
		
		return "readFlow";
	}
	
	private String getFlowImage(String reportId) {
		
		List<ReportRiskDep> list = reportRiskDepDao.getReportRiskDepByReportId(reportId);
		String flowStatus = "*";
		
		if(list == null || list.size() <= 0) {
			flowStatus = "0";
		}
		else {
			for(ReportRiskDep rrd : list) {
				if(!rrd.getRrdStatus().equals("*")) {
					flowStatus = rrd.getRrdStatus();
					break;
				}
			}
		}
		
		ReportFlowRule rule = reportFlowRuleDao.getFlowState("BGBXQD", flowStatus);
		return rule.getRfFlowImage();
	}
 	
}
