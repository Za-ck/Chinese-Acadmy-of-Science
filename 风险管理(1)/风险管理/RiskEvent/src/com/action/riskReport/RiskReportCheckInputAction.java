package com.action.riskReport;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.dao.ReportCheckDAO;
import com.dao.ReportCheckFileDAO;
import com.dao.ReportDepFileDAO;
import com.dao.ReportFlowRuleDAO;
import com.dao.ReportFormInfoDAO;
import com.dao.ReportRiskDAO;
import com.dao.ReportRiskFileDAO;
import com.dao.ReportTaskDAO;
import com.dao.UsersDAO;
import com.dao.UsersQueryViewDAO;
import com.form.RiskReportCheckForm;
import com.model.ReportCheck;
import com.model.ReportCheckFile;
import com.model.ReportDepFile;
import com.model.ReportFlowRule;
import com.model.ReportFormInfo;
import com.model.ReportRisk;
import com.model.ReportRiskFile;
import com.model.ReportTask;
import com.model.Users;
import com.model.UsersQueryView;
import com.services.TaskExecutionWebServer;
import com.util.FileUtil;
import com.util.GenerateSequenceUtil;
import com.util.TwoTuple;
import com.util.WebUtil;

public class RiskReportCheckInputAction {

	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private ReportFlowRuleDAO reportFlowRuleDao;
	private ReportCheckFileDAO reportCheckFileDao;
	private ReportRiskDAO reportRiskDao;
	private UsersQueryViewDAO usersQueryViewDao;
	private ReportTaskDAO reportTaskDao;
	private ReportCheckDAO reportCheckDao;
	private ReportFormInfoDAO formInfoDao;
	private ReportRiskFileDAO reportRiskFileDao;

	private ReportDepFileDAO reportDepFileDao;
	
	private DepartmentDAO departmentDao;
	
	private UsersDAO usersDao;
	
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

	public ReportDepFileDAO getReportDepFileDao() {
		return reportDepFileDao;
	}

	public void setReportDepFileDao(ReportDepFileDAO reportDepFileDao) {
		this.reportDepFileDao = reportDepFileDao;
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
	
	public ReportCheckFileDAO getReportCheckFileDao() {
		return reportCheckFileDao;
	}

	public void setReportCheckFileDao(ReportCheckFileDAO reportCheckFileDao) {
		this.reportCheckFileDao = reportCheckFileDao;
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

	public ReportTaskDAO getReportTaskDao() {
		return reportTaskDao;
	}

	public void setReportTaskDao(ReportTaskDAO reportTaskDao) {
		this.reportTaskDao = reportTaskDao;
	}

	public ReportCheckDAO getReportCheckDao() {
		return reportCheckDao;
	}

	public void setReportCheckDao(ReportCheckDAO reportCheckDao) {
		this.reportCheckDao = reportCheckDao;
	}

	public ReportFormInfoDAO getFormInfoDao() {
		return formInfoDao;
	}

	public void setFormInfoDao(ReportFormInfoDAO formInfoDao) {
		this.formInfoDao = formInfoDao;
	}

	@SuppressWarnings("unchecked")
	// 跳转到填写汇总报告页面
	public String execute() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		
		String reportId = request.getParameter("reportId");
		String taskId = request.getParameter("taskId");
		
		if(taskId != null && !taskId.equals("")) {
			request.setAttribute("taskId", taskId);
		}
		
		// 1.查询需要编写的报告
		//reportIdList代表需要修改的report
		List<String> reportIdList = reportTaskDao.getUnprocessedReport(session.get("userid").toString(),session.get("userposition").toString(), "HZBGSP");
		//nonWrittenIdList代表由上报流程结束后需要填写的report
		List<String> nonWrittenIdList = reportTaskDao.getNonWrittenReport(session.get("userdepid").toString(),"HZBGSP");
		reportIdList.addAll(nonWrittenIdList);
		
		//需要添加启动流程填写的report
		/*List<String> startReportIdList = reportTaskDao.getStartReport();
		reportIdList.addAll(startReportIdList);*/
		
		List<ReportRisk> reportCheckList = reportRiskDao.getReportRiskInList(reportIdList);
		
		// 2.跳转到报告编写页面之后有些信息需要自动填写
		if(reportId != null && !reportId.equals("")) {
			ReportRisk report = reportRiskDao.findById(reportId);
			request.setAttribute("annual", report.getRerAnnual());
			request.setAttribute("reportId", reportId);
			
			//更换报告之后重新填写流程附件startFiles
			//向startFiles中添加启动流程的附件
			List<ReportRiskFile> startFiles = reportRiskFileDao.findByProperty("fileReportId", reportId);
			
			for(int i=0; i<startFiles.size();i++){
				//获取当前附件中的创建人id
				String creatorId = startFiles.get(i).getFileUploader();
				Users users = usersDao.findById(creatorId);
				startFiles.get(i).setFileUploaderDepName(departmentDao.getdepname(users.getUserDep()));
			}
			
			//按照时间顺序排序
			Collections.sort(startFiles,new Comparator<ReportRiskFile>(){

				@Override
				public int compare(ReportRiskFile o1, ReportRiskFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
				
			});
			Collections.reverse(startFiles);
			request.setAttribute("startFileList", startFiles);
			
			
			//向flowFileList中添加上报流程的附件
			List<ReportDepFile> flowFileList = reportDepFileDao.getReportFile(reportId);
			
			Map<String,Integer> versionMap = new HashMap<String,Integer>();
			for(int i=0;i<flowFileList.size();i++){
				flowFileList.get(i).setFileUploaderDepName(departmentDao.getdepname(flowFileList.get(i).getFileUploaderdep()));
				//如果已经包含在里面
				if(versionMap.containsKey(flowFileList.get(i).getFileUploaderdep())){
					//获取当前部门附件的最高版本
					int maxVersion = versionMap.get(flowFileList.get(i).getFileUploaderdep());
					if(flowFileList.get(i).getFileVersion() > maxVersion){
						maxVersion = flowFileList.get(i).getFileVersion();
					}
					versionMap.put(flowFileList.get(i).getFileUploaderdep(), maxVersion);
					
				}else{  //如果不在map当中
					int maxVersion = flowFileList.get(i).getFileVersion();
					versionMap.put(flowFileList.get(i).getFileUploaderdep(), maxVersion);
				}
			}
			List<ReportDepFile> tempFileList = new ArrayList<ReportDepFile>();
			
			//遍历上报流程的附件flowFileList，只保留每个部门上报流程附件中最高版本的附件
			for(ReportDepFile oneFile : flowFileList){
				int fileVersion = oneFile.getFileVersion();
				int maxVersion = versionMap.get(oneFile.getFileUploaderdep());
				if(fileVersion == maxVersion){
					tempFileList.add(oneFile);
				}
			}
			
			//将flowFileList中的附件按照部门分开
			//String :部门名称    List :对应部门的附件
			Map<String,List<ReportDepFile>> allDepFilesMap = new HashMap<String,List<ReportDepFile>>();
			for(ReportDepFile oneFile : tempFileList){  //将所有的附件进行划分
				if(allDepFilesMap.containsKey(oneFile.getFileUploaderdep())){
					//如果当前附件的上传者所在部门已经在该Map中
					//将当前附件加入该key对应的value中
					List<ReportDepFile> tempList = allDepFilesMap.get(oneFile.getFileUploaderdep());
					tempList.add(oneFile);
					allDepFilesMap.put(oneFile.getFileUploaderdep(), tempList);
				}else{
					//如果当前附件的上传者所在部门已经不在该Map中
					//创建一个新的map元素并加入至allDepFilesMap
					List<ReportDepFile> tempList1 = new ArrayList<ReportDepFile>();
					tempList1.add(oneFile);
					allDepFilesMap.put(oneFile.getFileUploaderdep(), tempList1);
				}
			}
			
			List<ReportDepFile> finalFlowFileList = new ArrayList<ReportDepFile>();
			//将每个部门中的附件按照时间顺序进行排序
			for(String key : allDepFilesMap.keySet()){
				List<ReportDepFile> tempList2 = allDepFilesMap.get(key);
				Collections.sort(tempList2,new Comparator<ReportDepFile>(){

					@Override
					public int compare(ReportDepFile o1, ReportDepFile o2) {
						return o1.getFileDate().compareTo(o2.getFileDate());
					}
					
				});
				Collections.reverse(tempList2);
				finalFlowFileList.addAll(tempList2);
			}
			request.setAttribute("flowFileList", finalFlowFileList);
		}
		request.setAttribute("reportCheckList", reportCheckList);
		request.setAttribute("depname", session.get("userdep").toString());
		request.setAttribute("username", session.get("username").toString());
		
		// 3.设置taskId
		ReportTask specificTask = null;
		if(taskId != null && !taskId.equals("")) {
			specificTask = reportTaskDao.getReportTaskByTaskId(taskId);
			request.setAttribute("taskId", taskId);
		}
		
		else {
			//如果通过taskId获取的任务为空，那么就需要通过reportId来获取任务了
			List<ReportTask> list = reportTaskDao.getSpecificUnprocessedTaskList(reportId, "HZBGSP", session.get("userid").toString(),session.get("userposition").toString());
			// 不为空，如果为空那么说明这个报告已经被撤回
			if(list != null && list.size() > 0) {
				specificTask = list.get(0);
				taskId = specificTask.getRetTaskId();
				request.setAttribute("taskId", specificTask.getRetTaskId());
			}
			else {
				//如果为空,判断该报告是否是没有人填写的
				specificTask = reportTaskDao.getNonWrittenTask(reportId, "HZBGSP", session.get("userdepid").toString());
				if(specificTask != null) {
					
					taskId = specificTask.getRetTaskId();
					request.setAttribute("taskId", specificTask.getRetTaskId());
					
				}
			}
			
		}
		
		// 4.得到需要报告的处理意见
		if(taskId != null && !taskId.equals("")) {
			List<ReportTask> handleList = reportTaskDao.getHandleViewList(specificTask.getRetFormId());
			request.setAttribute("handleList", handleList);
			
			//如果handleList为空,也就是说没有审核记录,说明该报告是新录入的，不是审核不通过的
			if(handleList == null || handleList.size() <= 0) {
				
			}
			else {
				// 是否显示历史版本的table
				request.setAttribute("history", "show");
			}
			
		}
		
		// 6.获取ReportCheck的一条记录
		ReportCheck rc = reportCheckDao.getSpecificRepCheck(reportId);
		if(rc != null) {
			request.setAttribute("repcheck", rc.getRcId());
			request.setAttribute("leaderId", rc.getRcLeaderId());
			request.setAttribute("remark", rc.getRcRemark());
			// 7.获取报告的附带文件
			List<ReportCheckFile> files = reportCheckFileDao.getspecificFile(reportId);
			//新需求
			List<ReportCheckFile> relFiles = new ArrayList<ReportCheckFile>();
			List<ReportCheckFile> historyFiles = new ArrayList<ReportCheckFile>();
			//新需求：让系统能够自动保存上传但是未发送的附件
			//需求在relFiles中添加File_version为0的附件
			if(files != null && files.size() > 0){
				for(ReportCheckFile file : files){
					if(file.getFileVersion() != 0 && file.getFilenewflag() == 0){
						historyFiles.add(file);
					}else{
						relFiles.add(file);
					}
				}
			}
			request.setAttribute("filelist", relFiles);
			//历史版本附件表中需要显示数据库中所有的附件（版本号不为0）
			Collections.sort(historyFiles,new Comparator<ReportCheckFile>(){

				@Override
				public int compare(ReportCheckFile o1, ReportCheckFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
				
			});
			Collections.reverse(historyFiles);
			
			request.setAttribute("historylist", historyFiles);
			
			
			//老版
			/*
			// 抽取最新版本的文件和历史版本的文件
			List<ReportCheckFile> filelist = new ArrayList<ReportCheckFile>();
			List<String> filenames = new ArrayList<String>();
			if(files != null && files.size() > 0) {
				
				for(ReportCheckFile file : files) {
					
					// 如果包括这个文件名,那么比较当前的文件的版本是否是更高
					if(filenames.contains(file.getFileFilename())) {
						
						int index = filenames.indexOf(file.getFileFilename());
						ReportCheckFile rdf = filelist.get(index);
						
						if(rdf.getFileDate().compareTo(file.getFileDate()) < 0) {
							
							// 替换
							filelist.set(index, file);
							
						}
						
					}
					else {
						filenames.add(file.getFileFilename());
						filelist.add(file);
					}
					
				}
				
			}
			//按照时间顺序排序
			Collections.sort(filelist,new Comparator<ReportCheckFile>(){

				@Override
				public int compare(ReportCheckFile o1, ReportCheckFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
				
			});
			Collections.reverse(filelist);
			request.setAttribute("filelist", filelist);
			
			files.removeAll(filelist);
			
			
			//按照时间顺序排序
			
			Collections.sort(files,new Comparator<ReportCheckFile>(){

				@Override
				public int compare(ReportCheckFile o1, ReportCheckFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
				
			});
			Collections.reverse(files);
			
			
			request.setAttribute("historylist", files);*/
			
		}
		return "RiskReportCheckInput";
	}
	
	//第一次保存
	public void firstSave() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		try {
			
			PrintWriter out = response.getWriter();
			String reportId = request.getParameter("reportId");
			// 先要判断是否已经有人已经填写了报告了
			ReportCheck rc = reportCheckDao.getSpecificRepCheck(reportId);
			if(rc != null) {
				out.print("fail");
				out.flush();
			}
			
			RiskReportCheckForm report = WebUtil.handleRequest(request, RiskReportCheckForm.class);
			TwoTuple<String,String> result = firstSave(report);
			
			out.print(result.first + "-" + result.second);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	// 保存并下一页
	public String saveBasicInfo() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		
		String repcheck = request.getParameter("repcheck");
		String reportId = request.getParameter("reportId");
		
		String taskId = request.getParameter("taskId");
		ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
		String flowStatus = task.getRetFlowStatus();
		
		// 修改备注（保存并下一页的时候修改的只有备注）
		ReportCheck recheck = reportCheckDao.findById(repcheck);
		String remark = request.getParameter("remark");
		recheck.setRcRemark(remark);
		recheck.setRcFlowStatus(flowStatus);
		reportCheckDao.merge(recheck);
		
		// 为CheckFlowConfirm准备数据
		String flowId = task.getRetFlowId();
		String nextStatus = reportFlowRuleDao.getNextStatus(flowId, flowStatus);
		ReportFlowRule nextstate = reportFlowRuleDao.getFlowState(flowId, nextStatus);
		
		String depId = nextstate.getRfDepartment();
		
		// 提交到本部门领导(默认是分配的领导)
		if(depId.equals("localdep")) {
			
			depId = session.get("userdepid").toString();
			List<UsersQueryView> leaders = usersQueryViewDao.findLeaderByDepId(depId);
			if(recheck.getRcLeaderId() != null && !recheck.getRcLeaderId().equals("")) {
				request.setAttribute("userId", recheck.getRcLeaderId());
			}
			
			request.setAttribute("users", leaders);
			request.setAttribute("depname", session.get("userdep").toString());
			
		}
		
		request.setAttribute("reportId", reportId);
		request.setAttribute("repcheck", repcheck);
		request.setAttribute("taskId", taskId);
		request.setAttribute("depId", depId);
		request.setAttribute("actionName", "riskReport/riskReportCheckInputAction_saveOrCommitReport");
		
		String fileIds = request.getParameter("newfiles");
		request.setAttribute("fileIds", fileIds);
		
		return "CheckFlowConfirm";
	}
	
	// 保存或者是提交报告
	public void saveOrCommitReport() {
		
		try {
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			
			Map<?, ?> session = ServletActionContext.getContext().getSession();
			String flowId = "HZBGSP";
			
			PrintWriter out = response.getWriter();
			
			String operation = request.getParameter("operation");
			String repcheck = request.getParameter("repcheck");
			String reportId = request.getParameter("reportId");
			String taskId = request.getParameter("taskId");
			String userId = request.getParameter("userId");
			String username = request.getParameter("username");
			String depId = request.getParameter("depId");
			String depname = request.getParameter("depname");
			String fileIds = request.getParameter("fileIds");
			
			ReportCheck report = reportCheckDao.findById(repcheck);
			ReportTask reportTask = reportTaskDao.getReportTaskByTaskId(taskId);
			String flowStatus = reportTask.getRetFlowStatus();
			ReportFlowRule state = reportFlowRuleDao.getFlowState(flowId, flowStatus);
			
			
			//如果是一个过渡状态,那么先要跳转到下一个状态
			if(state.getRfTransitionFlag() == 0) {
				
				flowStatus = reportFlowRuleDao.getNextStatus(flowId, flowStatus);
				report.setRcFlowStatus(flowStatus);
				
				//新增一个任务
				ReportTask task = new ReportTask();
				taskId = GenerateSequenceUtil.generateTaskId();
				task.setRetTaskId(taskId);
				task.setRetDepId(session.get("userdepid").toString());
				task.setRetDepName(session.get("userdep").toString());
				task.setRetFlowId(flowId);
				task.setRetFlowStatus(flowStatus);
				task.setRetLastTime(df.format(new Date()));
				task.setRetReportId(reportId);
				task.setRetReportName(reportTask.getRetReportName());
				task.setRetState("0");
				task.setRetUserId(session.get("userid").toString());
				task.setRetUserName(session.get("username").toString());
				task.setRetFormId(repcheck);
				task.setRetPreTaskId(reportTask.getRetTaskId());
				task.setRetModifyFlag(0);
				reportTaskDao.save(task);
				
				//将待办变成已办
				reportTask.setRetNextStatus(flowStatus);
				reportTask.setRetProcessTime(df.format(new Date()));
				reportTask.setRetState("1");
				reportTask.setRetFormId(repcheck);
				reportTask.setRetModifyFlag(1);
				reportTaskDao.merge(reportTask);
				
			}
			else {
				reportTask.setRetLastTime(df.format(new Date()));
				reportTaskDao.merge(reportTask);
			}
			
			// 如果用户是提交
			if(operation.equals("commit")) {
				
				report.setRcFlowStatus(reportFlowRuleDao.getNextStatus(flowId, flowStatus));
				
				// 删除该用户的待办(需要调用中南电力设计院的接口)
				ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
				task.setRetProcessTime(df.format(new Date()));
				task.setRetState("1");
				task.setRetNextStatus(report.getRcFlowStatus());
				task.setRetFormId(repcheck);
				task.setRetModifyFlag(1);
				reportTaskDao.merge(task);
				
				// 调用接口删除待办
				TaskExecutionWebServer.deleteTask(task.getRetTaskId(), "RiskEvent");
				
				ReportTask newTask = new ReportTask();
				newTask.setRetTaskId(GenerateSequenceUtil.generateTaskId());
				newTask.setRetDepId(depId);
				newTask.setRetDepName(depname);
				newTask.setRetUserId(userId);
				newTask.setRetUserName(username);
				newTask.setRetFlowId(flowId);
				newTask.setRetFlowStatus(report.getRcFlowStatus());				
				newTask.setRetReportId(reportId);
				newTask.setRetReportName(report.getRcReportName());
				newTask.setRetState("0");		//0表示未完成，1表示已完成
				newTask.setRetLastTime(df.format(new Date()));			//设置报告的最后的处理时间(目前只对未处理的任务有效)
				newTask.setRetPreTaskId(taskId);
				newTask.setRetFormId(repcheck);
				newTask.setRetModifyFlag(0);
				reportTaskDao.save(newTask);
				
				//调用中南电力设计院的接口给部门领导或者企业发展部管理岗生成待办
				String TaskId = newTask.getRetTaskId();
				String TaskApp = "RiskEvent";
				String TaskUrl = "default/loginSingleSystemAction?userid=" + userId;
				String TaskTitle = "汇总报告" + report.getRcReportName() + "待填写";
				String ReceiverId = userId;
				String ReceiverName = username;
				String ReceiverDepName = depname;
				String ReceiveTime = df.format(new Date());
				String CreatorName = session.get("username").toString();
				String CreatorDepName = session.get("userdep").toString();
				String PreUserName = session.get("username").toString();
				String PreDepName = session.get("userdep").toString();
				TaskExecutionWebServer.addTask(TaskId, TaskApp, TaskUrl, TaskTitle, ReceiverId, ReceiverName, ReceiverDepName, ReceiveTime, 
						CreatorName, CreatorDepName, PreUserName, PreDepName);
				
				//更新formInfo表
				formInfoDao.updateFormState(task.getRetFormId(), report.getRcFlowStatus(), reportFlowRuleDao.getFlowExplain(flowId, report.getRcFlowStatus()));
				//暂时不更新最近一次的编写人
				
			}
			
			// 如果是提交到本部门领导审核
			if(session.get("userdepid").toString().equals(depId)) {
				
				report.setRcLeaderId(userId);
				report.setRcLeaderName(username);
				
				// 将文件的标志位设置为已经发送,并且将不是最新上传的文件设置标志位
				reportCheckFileDao.updateFileFlag(reportId,Arrays.asList(fileIds.split("@")));
				//提交到本部门领导审核，则需要将附件的版本标识为0的附件设置为当前附件版本号最大数加上一
				
				reportCheckFileDao.updateFileVersion(reportId);
				
			}
			report.setRcModifyDate(df.format(new Date()));
			reportCheckDao.merge(report);
			
			// 更新最后处理时间
			formInfoDao.updateFormLastDate(reportTask.getRetFormId(), df.format(new Date()));
			
			out.print(operation);
			out.flush();
			return;
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	//上传文件
	public String uploadFile() { 
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			Map<?, ?> session = ServletActionContext.getContext().getSession();
			String uploader = session.get("userid").toString();
			String uploadername = session.get("username").toString();
			String reportId = request.getParameter("reportId");
			
			ArrayList<ReportCheckFile> files = new ArrayList<ReportCheckFile>();
			String date = df.format(new Date());
			JSONArray jsonArray = JSONArray.fromObject(FileUtil.uploadFile(request, "\\WEB-INF\\checkupload"));
			
			for(int i = 0;i < jsonArray.size(); i++) {
				String str = jsonArray.getString(i);
				JSONObject obj = JSONObject.fromObject(str);
				
				ReportCheckFile file = new ReportCheckFile();
				file.setFileId(GenerateSequenceUtil.newGenerateFileId());
				file.setFileDate(date);
				file.setFileFilename(obj.getString("filename"));
				file.setFileFilepath(obj.getString("filepath"));
				file.setFileUploader(uploader);
				file.setFileUploadername(uploadername);
				file.setFileReportId(reportId);
				file.setFileSendflag(0);			// 表示该文件还可以删除并没有提交上去
				file.setFilenewflag(1);				// 表示该文件是最新上传的
				
				//新上传的附件，将文件的版本标识设置为0
				//当将此报告发送之后，需要将版本标识为0的附件的版本号设置为当前文件版本的最大值加一
				file.setFileVersion(0);
				
				files.add(file);
			}
			reportCheckFileDao.addFiles(files);
			
			JSONArray fileArray = new JSONArray();
			for(ReportCheckFile file : files) {
				JSONObject object = new JSONObject();
				object.put("fileId", file.getFileId());
				object.put("uploadername", file.getFileUploadername());
				object.put("filename", file.getFileFilename());
				object.put("filedate", file.getFileDate());
				fileArray.add(object.toString());
			}
			
			if(files.size() > 0) {
				request.setAttribute("files", URLEncoder.encode(fileArray.toString(),"UTF-8"));
			}
			return "fileUpload";
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//下载一个文件
	public void downloadFile() throws IOException {
		
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			
			String fileId = request.getParameter("fileId");
			ReportCheckFile file = reportCheckFileDao.findById(fileId);
			String filename = file.getFileFilename();
			String filepath = file.getFileFilepath();
			FileUtil.downloadFile(response, filename, filepath);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("文件下载出错");
		}
	}
	
	//删除一个文件
	public void deleteFile() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String fileId = request.getParameter("fileId");
		
		ReportCheckFile file = reportCheckFileDao.findById(fileId);
		
		reportCheckFileDao.delete(file);
	}
	
	//报告未提交取消了操作
	public void cancelAction() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String taskId = request.getParameter("taskId");
		String repcheck = request.getParameter("repcheck");
		
		ReportTask specificTask = reportTaskDao.getReportTaskByTaskId(taskId);
		if(specificTask != null) {
			
			specificTask.setRetLastTime(df.format(new Date()));
			reportTaskDao.merge(specificTask);
			
		}
		
		//更新汇总报告表
		if(repcheck != null && !repcheck.equals("")) {
			ReportCheck redep = reportCheckDao.findById(repcheck);
			redep.setRcModifyDate(df.format(new Date()));
			reportCheckDao.merge(redep);
		}
	}
	
	
	/*---------------------------------------------------私有方法------------------------------------*/
	
	//第一次保存
	private TwoTuple<String,String> firstSave(RiskReportCheckForm reportform) {
		
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		String flowId = "HZBGSP";
		
		String reportId= reportform.getReportId();
		
		ReportCheck report = new ReportCheck();
		String repcheck = GenerateSequenceUtil.generateSequenceNo();
		report.setRcId(repcheck);
		report.setRcReportId(reportform.getReportId());
		report.setRcReportName(reportform.getReportname());
		report.setRcDepId(session.get("userdepid").toString());
		report.setRcDepName(session.get("userdep").toString());
		report.setRcWriterId(session.get("userid").toString());
		report.setRcWriterName(session.get("username").toString());
		report.setRcFlowId(flowId);
		report.setRcFlowStatus(reportFlowRuleDao.getNextStatus(flowId, "0"));
		report.setRcModifyDate(df.format(new Date()));
		report.setRcRemark(reportform.getRemark());
		reportCheckDao.save(report);
		
		// 将任务变成已经已经处理的任务
		ReportTask reporttask = reportTaskDao.getReportTaskByTaskId(reportform.getTaskId());
		
		reporttask.setRetNextStatus(reportFlowRuleDao.getNextStatus(flowId, "0"));
		reporttask.setRetProcessTime(df.format(new Date()));
		reporttask.setRetState("1");		//0表示未完成，1表示已完成
		reporttask.setRetFormId(repcheck);
		reporttask.setRetModifyFlag(1);
		reporttask.setRetUserId(session.get("userid").toString());
		reporttask.setRetUserName(session.get("username").toString());
		reportTaskDao.merge(reporttask);
		
		//新增一个没有处理的任务
		ReportTask task = new ReportTask();
		String taskId = GenerateSequenceUtil.generateTaskId();
		task.setRetTaskId(taskId);
		task.setRetDepId(session.get("userdepid").toString());
		task.setRetDepName(session.get("userdep").toString());
		task.setRetFlowId(flowId);
		task.setRetFlowStatus(reporttask.getRetNextStatus());
		task.setRetLastTime(df.format(new Date()));
		task.setRetReportId(reportId);
		task.setRetReportName(reportform.getReportname());
		task.setRetState("0");
		task.setRetUserId(session.get("userid").toString());
		task.setRetUserName(session.get("username").toString());
		task.setRetFormId(repcheck);
		task.setRetPreTaskId(reportform.getTaskId());
		task.setRetModifyFlag(0);
		reportTaskDao.save(task);
		
		//在formInfo表中新增一条记录
		ReportFormInfo formInfo = new ReportFormInfo();
		formInfo.setFiFlowId(flowId);
		formInfo.setFiFlowStatus(task.getRetFlowStatus());
		formInfo.setFiFormId(repcheck);
		formInfo.setFiWriterDepId(session.get("userdepid").toString());
		formInfo.setFiWriterDepName(session.get("userdep").toString());
		formInfo.setFiWriterId(session.get("userid").toString());
		formInfo.setFiWriterName(session.get("username").toString());
		formInfo.setFiStatements(reportFlowRuleDao.getFlowExplain(flowId, task.getRetFlowStatus()));
		formInfo.setFiLastdate(df.format(new Date()));
		formInfoDao.save(formInfo);
		
		return new TwoTuple<String,String>(repcheck,taskId);
	}
}
