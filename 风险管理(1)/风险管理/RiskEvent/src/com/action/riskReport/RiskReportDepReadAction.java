package com.action.riskReport;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.dao.DepartmentDAO;
import com.dao.ReadFlowViewDAO;
import com.dao.ReportDepFileDAO;
import com.dao.ReportDepartmentDAO;
import com.dao.ReportFlowRuleDAO;
import com.dao.ReportFormInfoDAO;
import com.dao.ReportRiskDAO;
import com.dao.ReportRiskFileDAO;
import com.dao.ReportTaskDAO;
import com.dao.UsersDAO;
import com.model.ReadFlowView;
import com.model.ReportDepFile;
import com.model.ReportDepartment;
import com.model.ReportFlowRule;
import com.model.ReportRisk;
import com.model.ReportRiskFile;
import com.model.ReportTask;
import com.model.Users;
import com.util.FileUtil;

public class RiskReportDepReadAction {

	private ReportDepartmentDAO reportDepartmentDao;
	private ReportFlowRuleDAO reportFlowRuleDao;
	private ReportDepFileDAO reportDepFileDao;
	private ReportRiskDAO reportRiskDao;
	private ReportTaskDAO reportTaskDao;
	private ReadFlowViewDAO readFlowViewDao;
	private ReportFormInfoDAO formInfoDao;
	
	private ReportRiskFileDAO reportRiskFileDao;
	private UsersDAO usersDao;
	private DepartmentDAO departmentDao;
	
	public UsersDAO getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDAO usersDao) {
		this.usersDao = usersDao;
	}

	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public ReportRiskFileDAO getReportRiskFileDao() {
		return reportRiskFileDao;
	}

	public void setReportRiskFileDao(ReportRiskFileDAO reportRiskFileDao) {
		this.reportRiskFileDao = reportRiskFileDao;
	}

	public ReportDepartmentDAO getReportDepartmentDao() {
		return reportDepartmentDao;
	}

	public void setReportDepartmentDao(ReportDepartmentDAO reportDepartmentDao) {
		this.reportDepartmentDao = reportDepartmentDao;
	}

	public ReportFlowRuleDAO getReportFlowRuleDao() {
		return reportFlowRuleDao;
	}

	public void setReportFlowRuleDao(ReportFlowRuleDAO reportFlowRuleDao) {
		this.reportFlowRuleDao = reportFlowRuleDao;
	}

	public ReportDepFileDAO getReportDepFileDao() {
		return reportDepFileDao;
	}

	public void setReportDepFileDao(ReportDepFileDAO reportDepFileDao) {
		this.reportDepFileDao = reportDepFileDao;
	}

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

	public String ReadReport() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String reportId = request.getParameter("reportId");
		String taskId = request.getParameter("taskId");
		
		// 1.??????ReportRisk
		ReportRisk report = reportRiskDao.findById(reportId);
		request.setAttribute("annual", report.getRerAnnual());
		request.setAttribute("reportId", reportId);
		request.setAttribute("reportname", report.getRerReportName());
		
		// 2.??????????????????
		request.setAttribute("taskId", taskId);
		
		ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
		
		ReportDepartment rd = reportDepartmentDao.findById(task.getRetFormId());
		request.setAttribute("depname", rd.getRdDepName());
		request.setAttribute("writername", rd.getRdWriterName());
		request.setAttribute("repdepId", rd.getRdId());
		request.setAttribute("leadername", rd.getRdChargerName());
		request.setAttribute("depremark", rd.getRdRemark());
		
		//????????????????????????????????????????????????
		List<ReportRiskFile> startFiles = reportRiskFileDao.findByProperty("fileReportId", reportId);
		for(int i=0; i<startFiles.size();i++){
			//?????????????????????????????????id
			String creatorId = startFiles.get(i).getFileUploader();
			Users users = usersDao.findById(creatorId);
			startFiles.get(i).setFileUploaderDepName(departmentDao.getdepname(users.getUserDep()));
		}
		
		//????????????????????????
		Collections.sort(startFiles,new Comparator<ReportRiskFile>(){

			@Override
			public int compare(ReportRiskFile o1, ReportRiskFile o2) {
				return o1.getFileDate().compareTo(o2.getFileDate());
			}
			
		});
		Collections.reverse(startFiles);
		request.setAttribute("startFileList", startFiles);
		
		
		// 3.???????????????????????????
		List<ReportDepFile> files = reportDepFileDao.getspecificFile(reportId, rd.getRdDepId());
		//?????????
		int maxVersion = reportDepFileDao.queryMaxVersion(reportId, rd.getRdDepId());
		List<ReportDepFile> relFiles = new ArrayList<ReportDepFile>();
		List<ReportDepFile> historyFiles = new ArrayList<ReportDepFile>();
		if(files != null && files.size() > 0){
			for(ReportDepFile file : files){
				if(file.getFileVersion() == 0) continue;   //????????????????????????0?????????
				if(file.getFileVersion() == maxVersion){
					relFiles.add(file);
				}else{
					historyFiles.add(file);
				} 
			}
		}
		//??????????????????????????????????????????????????????????????????
		//???relFiles????????????????????????
		Collections.sort(relFiles,new Comparator<ReportDepFile>(){

			@Override
			public int compare(ReportDepFile o1, ReportDepFile o2) {
				return o1.getFileDate().compareTo(o2.getFileDate());
			}
			
		});
		Collections.reverse(relFiles);
		request.setAttribute("filelist", relFiles);
		//???????????????????????????????????????????????????????????????????????????????????????0?????????????????????????????????
		Collections.sort(historyFiles,new Comparator<ReportDepFile>(){

			@Override
			public int compare(ReportDepFile o1, ReportDepFile o2) {
				return o1.getFileDate().compareTo(o2.getFileDate());
			}
			
		});
		Collections.reverse(historyFiles);
		
		request.setAttribute("historylist", historyFiles);
		
		//?????????
		/*
		// ???????????????????????????????????????????????????
		List<ReportDepFile> filelist = getLatestFile(files);
		//?????????????????????????????????
		Collections.sort(filelist, new Comparator<ReportDepFile>(){

			@Override
			public int compare(ReportDepFile o1, ReportDepFile o2) {
				return o1.getFileDate().compareTo(o2.getFileDate());
			}
			
		});
		Collections.reverse(filelist);
		request.setAttribute("filelist", filelist);*/
		
		// 4.?????????????????????????????????
		List<ReportTask> handleList = reportTaskDao.getHandleViewList(task.getRetFormId());
		request.setAttribute("handleList", handleList);
		
		request.setAttribute("actionName", "riskReport/riskReportDepReadAction");
		
		return "success";
	}
	
	
	public void readFile() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		try {
			
			PrintWriter out = response.getWriter();
			String reportId = request.getParameter("reportId");
			String depId = request.getParameter("depId");
			List<ReportDepFile> files = reportDepFileDao.getspecificFile(reportId, depId);
			files = getLatestFile(files);
			//????????????????????????
			Collections.sort(files, new Comparator<ReportDepFile>(){
				@Override
				public int compare(ReportDepFile o1, ReportDepFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
			});
			JSONArray jsonArray = new JSONArray();
			for(ReportDepFile file : files) {
				JSONObject object = new JSONObject();
				object.put("filename", file.getFileFilename());
				object.put("flowname", "????????????????????????");
				object.put("uploader",file.getFileUploadername());
				object.put("uploadtime", file.getFileDate());
				object.put("path", "/RiskEvent/riskReport/riskReportDepInputAction_downloadFile?fileId="+file.getFileId());
				jsonArray.add(object.toString());
			}
			out.print(jsonArray.toString());
			out.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	// ?????????????????????
	public void downloadFile() {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			HttpServletResponse response = ServletActionContext.getResponse();
			
			String reportId = request.getParameter("reportId");
			String depId = request.getParameter("depId");
			String reportname = new String(request.getParameter("reportname").getBytes("ISO-8859-1"),"UTF-8");
			
			// ???????????????????????????
			List<ReportDepFile> files = reportDepFileDao.getspecificFile(reportId, depId);
			// ???????????????????????????????????????????????????
			List<ReportDepFile> filelist = getLatestFile(files);
			
			Map<String,String> fileMap = new HashMap<String,String>();
			for(ReportDepFile file : filelist) {
				
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
		
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			Map<?, ?> session = ServletActionContext.getContext().getSession();
			PrintWriter out = response.getWriter();
			
			String reportId = request.getParameter("reportId");
			String taskId = request.getParameter("taskId");
			
			//1.?????????????????????
			int revocationFlag = reportTaskDao.getRevocationFlag(taskId);
			if(revocationFlag == 0) {
				//??????????????????
				out.write("fail");
				out.flush();
				return;
			}
			//2.???????????????????????????
			ReportTask task = reportTaskDao.revocateTask(taskId);
			reportDepartmentDao.revocateReportDep(task.getRetFormId(),task.getRetFlowStatus());
			
			//3.??????????????????????????????????????????????????????
			reportDepFileDao.updateSendFlagInRevocation(reportId, session.get("userdepid").toString());
			reportDepFileDao.updateFileVersionInRevocation(reportId, session.get("userdepid").toString());
			
			//???????????????????????????????????????
			//reportDepFileDao.updateFileVersionInRevocation(reportId,session.get("userdepid").toString());
			
			//??????????????????????????????????????????????????????????????????????????????,?????????????????????????????????
			
			//4???????????????????????????????????????????????????
			ReportFlowRule rule = reportFlowRuleDao.getFlowState(task.getRetFlowId(), task.getRetFlowStatus());
			
			//5?????????formInfo????????????
			formInfoDao.updateFormState(task.getRetFormId(), task.getRetFlowStatus(), rule.getRfFlowExplain());
			
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
		String formId = request.getParameter("formId");
		if(taskId != null && !taskId.equals("")) {
			
			ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
			formId = task.getRetFormId();
		}
		
		if(formId != null && !formId.equals("")) {
			
			List<ReadFlowView> recordlist = readFlowViewDao.getRecordlist(formId);
			request.setAttribute("recordlist", recordlist);
			request.setAttribute("userId", session.get("userid").toString());
			request.setAttribute("flowImage", getFlowImage(formId));
			request.setAttribute("title", recordlist.get(0).getRetReportName());
		}
		
		else {
			ReportFlowRule rule = reportFlowRuleDao.getFlowState("BMBGTJ", "0");
			request.setAttribute("flowImage", rule.getRfFlowImage());
			request.setAttribute("title", "????????????????????????");
		}
		
		return "readFlow";
	}
	
	private String getFlowImage(String formId) {
		
		String flowStatus = "0";
		
		if(formId != null && !formId.equals("")) {
			ReportDepartment redep = reportDepartmentDao.findById(formId);
			flowStatus = redep.getRdFlowstatus();
		}
		ReportFlowRule rule = reportFlowRuleDao.getFlowState("BMBGTJ", flowStatus);
		
		return rule.getRfFlowImage();
	}
	
	// ???????????????????????????
	public List<ReportDepFile> getLatestFile(List<ReportDepFile> files) {
		
		List<ReportDepFile> filelist = new ArrayList<ReportDepFile>();
		List<String> filenames = new ArrayList<String>();
		if(files != null && files.size() > 0) {
			
			for(ReportDepFile file : files) {
				
				// ???????????????????????????,???????????????????????????????????????????????????
				if(filenames.contains(file.getFileFilename())) {
					
					int index = filenames.indexOf(file.getFileFilename());
					ReportDepFile rdf = filelist.get(index);
					
					if(rdf.getFileDate().compareTo(file.getFileDate()) < 0) {
						
						// ??????
						filelist.set(index, file);
						
					}
					
				}
				else {
					filenames.add(file.getFileFilename());
					filelist.add(file);
				}
				
			}
			
		}
		return filelist;
		
	}
}
