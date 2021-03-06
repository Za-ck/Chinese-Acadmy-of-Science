package com.action.riskReport;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.dao.DepartmentDAO;
import com.dao.ReportDepFileDAO;
import com.dao.ReportDepartmentDAO;
import com.dao.ReportFlowRuleDAO;
import com.dao.ReportFormInfoDAO;
import com.dao.ReportMessageDAO;
import com.dao.ReportRiskDAO;
import com.dao.ReportRiskFileDAO;
import com.dao.ReportTaskDAO;
import com.dao.ReportTaskRiskViewDAO;
import com.dao.UsersDAO;
import com.dao.UsersQueryViewDAO;
import com.model.ReportDepFile;
import com.model.ReportDepartment;
import com.model.ReportFlowRule;
import com.model.ReportMessage;
import com.model.ReportRisk;
import com.model.ReportRiskFile;
import com.model.ReportTask;
import com.model.Users;
import com.model.UsersQueryView;
import com.services.TaskExecutionWebServer;
import com.util.GenerateSequenceUtil;

public class RiskReportDepCheckAction {

	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private ReportDepartmentDAO reportDepartmentDao;
	private ReportFlowRuleDAO reportFlowRuleDao;
	private ReportDepFileDAO reportDepFileDao;
	private ReportRiskDAO reportRiskDao;
	private UsersQueryViewDAO usersQueryViewDao;
	private ReportTaskRiskViewDAO reportTaskRiskViewDao;
	private ReportTaskDAO reportTaskDao;
	private DepartmentDAO departmentDao;
	private ReportFormInfoDAO formInfoDao;
	private ReportMessageDAO reportMessageDao;
	
	private ReportRiskFileDAO reportRiskFileDao;
	private UsersDAO usersDao;
	
	
	public ReportRiskFileDAO getReportRiskFileDao() {
		return reportRiskFileDao;
	}

	public void setReportRiskFileDao(ReportRiskFileDAO reportRiskFileDao) {
		this.reportRiskFileDao = reportRiskFileDao;
	}

	public UsersDAO getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDAO usersDao) {
		this.usersDao = usersDao;
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

	public UsersQueryViewDAO getUsersQueryViewDao() {
		return usersQueryViewDao;
	}

	public void setUsersQueryViewDao(UsersQueryViewDAO usersQueryViewDao) {
		this.usersQueryViewDao = usersQueryViewDao;
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
	
	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}
	
	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	public ReportFormInfoDAO getFormInfoDao() {
		return formInfoDao;
	}

	public void setFormInfoDao(ReportFormInfoDAO formInfoDao) {
		this.formInfoDao = formInfoDao;
	}
	
	public ReportMessageDAO getReportMessageDao() {
		return reportMessageDao;
	}

	public void setReportMessageDao(ReportMessageDAO reportMessageDao) {
		this.reportMessageDao = reportMessageDao;
	}

	//???????????????????????????????????????????????????
	public String execute() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		
		String reportId = request.getParameter("reportId");
		String taskId = request.getParameter("taskId");
		
		ReportTask specificTask = null;
		if(taskId != null && !taskId.equals("")) {
			specificTask = reportTaskDao.getReportTaskByTaskId(taskId);
		}
		else {
			//????????????taskId?????????????????????????????????????????????reportId??????????????????
			List<ReportTask> list = reportTaskDao.getSpecificUnprocessedTaskList(reportId, "BMBGTJ", session.get("userid").toString(),session.get("userposition").toString());
			// ???????????????????????????????????????????????????????????????
			if(list != null && list.size() > 0) {
				specificTask = list.get(0);
			}
		}
		
		initData(request,specificTask,session);
		
		return "success";
	}
	
	public String prepareCheckReport() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		
		String reportId = request.getParameter("reportId");
		String writerId = request.getParameter("writerId");
		
		ReportDepartment repdep = reportDepartmentDao.getSpecificReDepInput(reportId, writerId);
		ReportTask task = reportTaskDao.getSpecificUnprocessedTask(repdep.getRdId());
		
		initData(request,task,session);
		
		return "success";
	}
	
	public void saveHandleView() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String taskId = request.getParameter("taskId");
		String handleview = request.getParameter("handleview");
		
		ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
		task.setRetView(handleview);
		task.setRetLastTime(df.format(new Date()));
		task.setRetModifyFlag(1);
		reportTaskDao.merge(task);
		
		// ????????????????????????
		formInfoDao.updateFormLastDate(task.getRetFormId(), df.format(new Date()));
		
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			PrintWriter out = response.getWriter();
			out.print("success");
			out.flush();
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}
	
	public String checkRiskReport() {
		
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			//HttpServletResponse response = ServletActionContext.getResponse();
			Map<?, ?> session = ServletActionContext.getContext().getSession();
			String taskId = request.getParameter("taskId");
			String handleview = request.getParameter("handleview");
			String reviewStatus = request.getParameter("reviewStatus");			//??????????????????????????????????????????
			String repdepId = request.getParameter("repdepId");
			
			ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);		//??????????????????
			ReportDepartment redep = reportDepartmentDao.findById(repdepId);
			
			// 1.??????????????????????????????????????????????????????????????????
			String currentStatus = task.getRetFlowStatus();
			task.setRetView(handleview);
			
			// ????????????????????????????????????	
			ReportFlowRule rule = reportFlowRuleDao.getFlowState("BMBGTJ", currentStatus);
			String nextStatus = "";
			if(reviewStatus.equals("pass")) {
				nextStatus = rule.getRfNextStatus();
			}
			else {
				nextStatus = rule.getRfBackStatus();
			}
			task.setRetNextStatus(nextStatus);
			task.setRetModifyFlag(1);
			reportTaskDao.merge(task);
			
			request.setAttribute("reportId", task.getRetReportId());
			request.setAttribute("repdepId", repdepId);
			request.setAttribute("taskId", taskId);
			
			// ????????????????????????????????????
			if(!nextStatus.equals("*")) {
				
				// ???????????????????????????????????????,????????????????????????????????????
				ReportFlowRule nextstate = reportFlowRuleDao.getFlowState("BMBGTJ", nextStatus);
				
				String depId = nextstate.getRfDepartment();
				
				// ??????????????????
				if(depId.equals("localdep")) {
					
					depId = redep.getRdDepId();
					List<UsersQueryView> users = usersQueryViewDao.findStaffByDepId(depId);
					
					request.setAttribute("userId", redep.getRdWriterId());
					
					request.setAttribute("users", users);
					request.setAttribute("depname", session.get("userdep").toString());
					
				}
				// ?????????????????????
				else {
					List<UsersQueryView> users = usersQueryViewDao.findStaffByDepId(depId);
					request.setAttribute("users", users);
					ReportRisk report = reportRiskDao.getReportRiskByReportId(task.getRetReportId());
					request.setAttribute("userId", report.getRerPromoterId());
					request.setAttribute("depname", users.get(0).getDepName());
				}
				request.setAttribute("depId", depId);
				
				// ??????????????????????????????????????????0,??????????????????????????????????????????????????????
				reportDepFileDao.updateNewFlag(task.getRetReportId(), depId);
			}
			
			// ??????????????????
			else {
				
				// ???????????????????????????,?????????????????????
				redep.setRdModifyDate(df.format(new Date()));
				redep.setRdFlowstatus(task.getRetNextStatus());
				reportDepartmentDao.merge(redep);
				
				// ???????????????
				task.setRetProcessTime(df.format(new Date()));
				task.setRetState("1");
				task.setRetModifyFlag(1);
				reportTaskDao.merge(task);
				
				// ????????????????????????
				TaskExecutionWebServer.deleteTask(taskId, "RiskEvent");
				
				//??????formInfo???
				formInfoDao.updateFormState(task.getRetFormId(), task.getRetNextStatus(), "?????????");
				formInfoDao.updateFormLastDate(task.getRetFormId(), df.format(new Date()));
				
				//?????????????????????????????????
				reportRiskDao.updateDoneNum(task.getRetReportId());
				ReportRisk reportRisk = reportRiskDao.getReportRiskByReportId(task.getRetReportId());
				UsersQueryView user = usersQueryViewDao.findById(reportRisk.getRerPromoterId());
				// ??????????????????????????????????????????,???????????????????????????????????????????????????(???????????????????????????)
				if(reportRisk.getRerDepNum().intValue() <= reportRisk.getRerDoneNum().intValue()) {
					/*
					ReportTask nextFlowTask = new ReportTask();
					nextFlowTask.setRetTaskId(GenerateSequenceUtil.generateTaskId());
					nextFlowTask.setRetReportId(task.getRetReportId());
					nextFlowTask.setRetReportName(task.getRetReportName());
					nextFlowTask.setRetFlowId("HZBGSP");
					nextFlowTask.setRetFlowStatus("0");
					
					nextFlowTask.setRetDepId(user.getDepId());
					nextFlowTask.setRetDepName(user.getDepName());
					nextFlowTask.setRetLastTime(df.format(new Date()));
					//nextFlowTask.setRetPreTaskId(task.getRetTaskId());
					nextFlowTask.setRetState("0");
					//nextFlowTask.setRetUserId(user.getUserId());
					//nextFlowTask.setRetUserName(user.getUserName());
					nextFlowTask.setRetModifyFlag(0);
					reportTaskDao.save(nextFlowTask);*/
					
					
					ReportFlowRule rf = reportFlowRuleDao.getFlowState("HZBGSP", "0");
					ReportMessage rm = new ReportMessage();
					rm.setRmId(GenerateSequenceUtil.generateSequenceNo());
					rm.setRmDate(df.format(new Date()));
					rm.setRmActionname(rf.getRfAction());
					rm.setRmActionurl(rf.getRfActionUrl());
					rm.setRmDetail("???????????????????????????????????????????????????????????????????????????????????????????????????");
					rm.setRmCategoryId(rf.getRfFlowId());
					rm.setRmCategoryName("????????????????????????");
					rm.setRmFormId(task.getRetReportId());
					rm.setRmFormName(task.getRetReportName());
					rm.setRmState(0);		//0????????????????????????
					rm.setRmUserId(user.getUserId());
					rm.setRmUsername(user.getUserName());
					reportMessageDao.save(rm);
					
				}
				
				return "success";
				//response.sendRedirect("/RiskEvent/default/processAction_unProcessed");
			}
			
			
			request.setAttribute("actionName", "riskReport/riskReportDepCheckAction");
			
			return "DepFlowConfirm";
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}
	
	public void endDepFlow() {
		
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			String result = checkRiskReport();
			if(result.equals("success")) {
				PrintWriter out = response.getWriter();
				out.write("success");
				out.flush();
			}
			
		}catch(Exception e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
	// ???????????????????????????
	public void saveOrCommitReport() {
		
		try{
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = response.getWriter();
			
			Map<?, ?> session = ServletActionContext.getContext().getSession();
			String flowId = "BMBGTJ";
			
			String repdepId = request.getParameter("repdepId");
			//String reportId = request.getParameter("reportId");
			String taskId = request.getParameter("taskId");
			String userId = request.getParameter("userId");
			String username = request.getParameter("username");
			String depId = request.getParameter("depId");
			
			
			String depname = request.getParameter("depname");
			
			ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);		//??????????????????
			String nextStatus = task.getRetNextStatus();
			
			task.setRetProcessTime(df.format(new Date()));
			task.setRetState("1");
			task.setRetModifyFlag(1);
			reportTaskDao.merge(task);
			
			// ????????????????????????
			TaskExecutionWebServer.deleteTask(taskId, "RiskEvent");
			
			ReportTask newTask = new ReportTask();
			
			// 3.????????????
			newTask.setRetTaskId(GenerateSequenceUtil.generateTaskId());
			newTask.setRetReportId(task.getRetReportId());
			newTask.setRetReportName(task.getRetReportName());
			newTask.setRetFlowId(flowId);
			newTask.setRetFlowStatus(nextStatus);
			newTask.setRetFormId(task.getRetFormId());
				
			newTask.setRetDepId(depId);
			newTask.setRetDepName(depname);
				
			newTask.setRetLastTime(df.format(new Date()));
			newTask.setRetPreTaskId(task.getRetTaskId());
			newTask.setRetState("0");
			newTask.setRetUserId(userId);
			newTask.setRetUserName(username);
			newTask.setRetModifyFlag(0);
			reportTaskDao.save(newTask);
			
			//??????formInfo???
			formInfoDao.updateFormState(task.getRetFormId(), nextStatus, reportFlowRuleDao.getFlowExplain(flowId, nextStatus));
			formInfoDao.updateFormLastDate(task.getRetFormId(), df.format(new Date()));
			
			//?????????????????????
			ReportDepartment redep = reportDepartmentDao.findById(repdepId);
			redep.setRdModifyDate(df.format(new Date()));
			redep.setRdFlowstatus(task.getRetNextStatus());
			reportDepartmentDao.merge(redep);
			
			// ????????????????????????????????????????????????
			String TaskId = newTask.getRetTaskId();
			String TaskApp = "RiskEvent";
			String TaskUrl = "default/loginSingleSystemAction?userid=" + userId;
			String TaskTitle = "?????????????????????" + task.getRetReportName();
			String ReceiverId = userId;
			String ReceiverName = username;
			String ReceiverDepName = depname;
			String ReceiveTime = df.format(new Date());
			String CreatorName = redep.getRdWriterName();
			String CreatorDepName = redep.getRdDepName();
			String PreUserName = session.get("username").toString();
			String PreDepName = session.get("userdep").toString();
			TaskExecutionWebServer.addTask(TaskId, TaskApp, TaskUrl, TaskTitle, ReceiverId, ReceiverName, ReceiverDepName, ReceiveTime, 
					CreatorName, CreatorDepName, PreUserName, PreDepName);
			
			out.write("commit");
			out.flush();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}
		
	}
	
	/*----------------------------------------????????????-------------------------------------------------*/
	// ??????????????????????????????????????????
	@SuppressWarnings("unchecked")
	private void initData(HttpServletRequest request, ReportTask specificTask, Map<?, ?> session) {
		
		String reportId = request.getParameter("reportId");
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
		
		
		
		ReportRisk report = null;
		if(reportId != null && !reportId.equals("")) {
			report = reportRiskDao.findById(reportId);
		}
		if(report != null) {
			request.setAttribute("annual", report.getRerAnnual());
			request.setAttribute("reportId", reportId);
			
		}
		
		if(specificTask != null) {
			
			String taskId = specificTask.getRetTaskId();
			request.setAttribute("taskId", specificTask.getRetTaskId());
			
			request.setAttribute("handleView", specificTask.getRetView());			//?????????????????????????????????
			//??????ReportDepartment???????????????
			ReportDepartment redep = reportDepartmentDao.findById(specificTask.getRetFormId());
			request.setAttribute("writerId", redep.getRdWriterId());
			request.setAttribute("repdepId", redep.getRdId());
			request.setAttribute("depremark", redep.getRdRemark());
			request.setAttribute("depname", redep.getRdDepName());
			request.setAttribute("leadername", redep.getRdChargerName());			//???????????????,????????????????????????
			
			// ???????????????????????????
			List<ReportDepFile> files = reportDepFileDao.getspecificFile(reportId, redep.getRdDepId());
			int maxVersion = reportDepFileDao.queryMaxVersion(reportId, redep.getRdDepId());
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
			
			
			
			//??????
			/*
			// ???????????????????????????????????????????????????
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
			//????????????????????????
			Collections.sort(filelist, new Comparator<ReportDepFile>(){

				@Override
				public int compare(ReportDepFile o1, ReportDepFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
				
			});
			
			Collections.reverse(filelist);
			request.setAttribute("filelist", filelist);
			
			files.removeAll(filelist);
			
			Collections.sort(files, new Comparator<ReportDepFile>(){

				@Override
				public int compare(ReportDepFile o1, ReportDepFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
				
			});
			
			Collections.reverse(files);
			
			request.setAttribute("historylist", files);
			*/
			// ?????????????????????????????????
			List<ReportTask> handleList = reportTaskDao.getHandleViewList(specificTask.getRetFormId());
			request.setAttribute("handleList", handleList);
			
			// ?????????????????????????????????
			request.setAttribute("checkdep", session.get("userdep").toString());
			request.setAttribute("checkname", session.get("username").toString());

			// ??????????????????????????????
			List<UsersQueryView> writerlist = null;
			
			if(session.get("userdepid").toString().equals(redep.getRdDepId())) {
				// ?????????????????????????????????
				writerlist = usersQueryViewDao.findByProperty("userId", redep.getRdWriterId());
			}
			else {
				writerlist = getWriters(reportId);
			}
			request.setAttribute("writerlist", writerlist);
			
			// ??????????????????????????????????????????
			
			String currentStatus = specificTask.getRetFlowStatus();
			String flowId = specificTask.getRetFlowId();
			
			ReportFlowRule rule = reportFlowRuleDao.getFlowState(flowId, currentStatus);
			String nextStatus = rule.getRfNextStatus();
			if(nextStatus.equals("*")) {
				
				request.setAttribute("status", "end");		//????????????????????????
				
			}
			else {
				request.setAttribute("status", "noend");	//?????????????????????????????????
			}
		}
		
		// ???????????????????????????
		List<String> reportIdList = reportTaskDao.getUnprocessedReport(session.get("userid").toString(),session.get("userposition").toString(), "BMBGTJ");
		List<ReportRisk> reportDepList = reportRiskDao.getReportRiskInList(reportIdList);
		request.setAttribute("reportDepList", reportDepList);
		
		
	}
	
	private List<UsersQueryView> getWriters(String reportId) {
		
		List<ReportDepartment> list = reportDepartmentDao.getRepDepList(reportId);
		List<String> userlist = new ArrayList<String>();
		for(ReportDepartment redep : list) {
			
			// ?????????????????????????????????
			if(!redep.getRdFlowstatus().equals("*")) {
				String flowStatus = redep.getRdFlowstatus();
				String flowId = redep.getRdFlowId();
				
				ReportFlowRule rule = reportFlowRuleDao.getFlowState(flowId, flowStatus);
				if(!rule.getRfBackStatus().equals("-1")) {
					userlist.add(redep.getRdWriterId());
				}
				
			}
		}
		
		return usersQueryViewDao.getUsersInList(userlist);
	}
}
