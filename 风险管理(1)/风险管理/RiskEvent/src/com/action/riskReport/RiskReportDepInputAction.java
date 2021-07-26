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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.dao.DepartmentDAO;
import com.dao.ReportDepFileDAO;
import com.dao.ReportDepartmentDAO;
import com.dao.ReportFlowRuleDAO;
import com.dao.ReportFormInfoDAO;
import com.dao.ReportRiskDAO;
import com.dao.ReportRiskDepDAO;
import com.dao.ReportRiskFileDAO;
import com.dao.ReportTaskDAO;
import com.dao.ReportTaskRiskViewDAO;
import com.dao.UsersDAO;
import com.dao.UsersQueryViewDAO;
import com.form.RiskReportDepForm;
import com.model.ReportDepFile;
import com.model.ReportDepartment;
import com.model.ReportFlowRule;
import com.model.ReportFormInfo;
import com.model.ReportRisk;
import com.model.ReportRiskDep;
import com.model.ReportRiskFile;
import com.model.ReportTask;
import com.model.Users;
import com.model.UsersQueryView;
import com.services.TaskExecutionWebServer;
import com.util.FileUtil;
import com.util.GenerateSequenceUtil;
import com.util.TwoTuple;
import com.util.WebUtil;

public class RiskReportDepInputAction {

	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private ReportDepartmentDAO reportDepartmentDao;
	private ReportFlowRuleDAO reportFlowRuleDao;
	private ReportDepFileDAO reportDepFileDao;
	private ReportRiskDAO reportRiskDao;
	private UsersQueryViewDAO usersQueryViewDao;
	private ReportTaskRiskViewDAO reportTaskRiskViewDao;
	private ReportTaskDAO reportTaskDao;
	private DepartmentDAO departmentDao;
	private ReportRiskDepDAO reportRiskDepDao;
	private ReportFormInfoDAO formInfoDao;
	
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

	public ReportRiskDepDAO getReportRiskDepDao() {
		return reportRiskDepDao;
	}

	public void setReportRiskDepDao(ReportRiskDepDAO reportRiskDepDao) {
		this.reportRiskDepDao = reportRiskDepDao;
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

	// 用于跳转到报告基本信息页面
	public String execute() {
		
		//标志当前状态是发送给企业发展部还是部门领导
		String stateFlag = "depLeader";
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		
		String reportId = request.getParameter("reportId");
		String taskId = request.getParameter("taskId");
		
		//新增需求，显示上报流程的相关附件
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
		
		
		
		// 1.得到ReportRisk
		if(reportId != null && !reportId.equals("")) {
			ReportRisk report = reportRiskDao.findById(reportId);
			request.setAttribute("annual", report.getRerAnnual());
			request.setAttribute("reportId", reportId);
		}
		
		// 2.设置taskId
		ReportTask specificTask = null;
		if(taskId != null && !taskId.equals("")) {
			specificTask = reportTaskDao.getReportTaskByTaskId(taskId);
			request.setAttribute("taskId", taskId);
		}
		
		else {
			//如果通过taskId为空，那么就需要通过reportId来获取任务了
			List<ReportTask> list = reportTaskDao.getSpecificUnprocessedTaskList(reportId, "BMBGTJ", session.get("userid").toString(),session.get("userposition").toString());
			// 不为空，如果为空那么说明这个报告已经被撤回或者是这个报告还没有人填写
			if(list != null && list.size() > 0) {
				specificTask = list.get(0);
				taskId = specificTask.getRetTaskId();
				request.setAttribute("taskId", specificTask.getRetTaskId());
			}
			else {
				//如果为空,判断该报告是否是没有人填写的
				specificTask = reportTaskDao.getNonWrittenTask(reportId, "BMBGTJ", session.get("userdepid").toString());
				if(specificTask != null) {
					
					taskId = specificTask.getRetTaskId();
					request.setAttribute("taskId", specificTask.getRetTaskId());
					
				}
			}
			
		}
		
		// 3.得到需要填写的报告的列表
		List<String> reportIdList = reportTaskDao.getUnprocessedReport(session.get("userid").toString(),session.get("userposition").toString(),"BMBGTJ");
		List<String> nonWrittenIdList = reportTaskDao.getNonWrittenReport(session.get("userdepid").toString(),"BMBGTJ");
		reportIdList.addAll(nonWrittenIdList);
		List<ReportRisk> reportDepList = reportRiskDao.getReportRiskInList(reportIdList);
		
		request.setAttribute("reportDepList", reportDepList);
		request.setAttribute("depname", session.get("userdep").toString());
		request.setAttribute("username", session.get("username").toString());
		
		
		// 4.得到需要报告的处理意见
		if(specificTask != null) {
			List<ReportTask> handleList = reportTaskDao.getHandleViewList(specificTask.getRetFormId());
			request.setAttribute("handleList", handleList);
			
			//如果handleList不为空,也就是有审核记录,说明该报告不是新录入的
			if(handleList != null && handleList.size() > 0) {
				ReportFlowRule rule = reportFlowRuleDao.getFlowState("BMBGTJ", specificTask.getRetFlowStatus());
				int transitionFlag = rule.getRfTransitionFlag();
				// 表示是一个中转状态,只能转到下一个状态
				if(transitionFlag == 2) {
					request.setAttribute("readonly", "readonly");			//表示该报告需要提交给企业发展部管理岗,那么该报告就不能够修改了
					stateFlag = "QYFZ";
				}
				else {
					request.setAttribute("readonly", "");
				}
				// 是否显示历史版本的table
				request.setAttribute("history", "show");			
			}
			
		}
		
		// 6.获取ReportDepartment的一条记录
		ReportDepartment rd = reportDepartmentDao.getReDepInput(reportId, session.get("userdepid").toString());
		
		if(rd != null) {
			request.setAttribute("repdepId", rd.getRdId());
			request.setAttribute("depremark", rd.getRdRemark());
			request.setAttribute("depleader", rd.getRdChargerName());
			//新需求调整版
			
			//判断下一步是发送给部门领导还是企业发展部
			if(stateFlag.equals("QYFZ")){   //如果发送给企业发展部
				//获取报告的附件
				List<ReportDepFile> files = reportDepFileDao.getspecificFile(reportId, session.get("userdepid").toString());
				int maxVersion = reportDepFileDao.queryMaxVersion(reportId, session.get("userdepid").toString());
				List<ReportDepFile> relFiles = new ArrayList<ReportDepFile>();
				List<ReportDepFile> historyFiles = new ArrayList<ReportDepFile>();
				if(files != null && files.size() > 0){
					for(ReportDepFile file : files){
						if(file.getFileVersion() == 0) continue;   //如果发现版本号为0的附件
						if(file.getFileVersion() == maxVersion){
							relFiles.add(file);
						}else{
							historyFiles.add(file);
						} 
					}
				}
				//相关附件表需要显示数据库中附件版本最高的附件
				//将relFiles按照创建时间排序
				Collections.sort(relFiles,new Comparator<ReportDepFile>(){

					@Override
					public int compare(ReportDepFile o1, ReportDepFile o2) {
						return o1.getFileDate().compareTo(o2.getFileDate());
					}
					
				});
				Collections.reverse(relFiles);
				request.setAttribute("filelist", relFiles);
				
				//历史版本附件表需要显示数据库中所有低版本的附件（版本号不为0，但是低于最高版本号）
				Collections.sort(historyFiles,new Comparator<ReportDepFile>(){

					@Override
					public int compare(ReportDepFile o1, ReportDepFile o2) {
						return o1.getFileDate().compareTo(o2.getFileDate());
					}
					
				});
				Collections.reverse(historyFiles);
				
				request.setAttribute("historylist", historyFiles);
				
				
			}else{    //如果是发送给本部门领导
				List<ReportDepFile> files = reportDepFileDao.getspecificFile(reportId, session.get("userdepid").toString());
				List<ReportDepFile> relFiles = new ArrayList<ReportDepFile>();
				List<ReportDepFile> historyFiles = new ArrayList<ReportDepFile>();
				//新需求：让系统能够自动保存上传但是未发送的附件
				//需求在relFiles中添加File_version为0的附件
				if(files != null && files.size() > 0){
					for(ReportDepFile file : files){
						if(file.getFileVersion()!= 0 && file.getFilenewflag() == 0){
							historyFiles.add(file);
						}else{
							relFiles.add(file);
						}
					}
				}
				request.setAttribute("filelist", relFiles);
				//历史版本附件表中需要显示数据库中所有的附件（版本号不为0）
				Collections.sort(historyFiles,new Comparator<ReportDepFile>(){

					@Override
					public int compare(ReportDepFile o1, ReportDepFile o2) {
						return o1.getFileDate().compareTo(o2.getFileDate());
					}
					
				});
				Collections.reverse(historyFiles);
				
				request.setAttribute("historylist", historyFiles);
			}
			//老版
			/*   
			// 7.获取报告的附带文件
			List<ReportDepFile> files = reportDepFileDao.getspecificFile(reportId, session.get("userdepid").toString());
			
			// 抽取最新版本的文件和历史版本的文件
			List<ReportDepFile> filelist = new ArrayList<ReportDepFile>();
			List<String> filenames = new ArrayList<String>();
			if(files != null && files.size() > 0) {
				
				for(ReportDepFile file : files) {
					
					// 如果包括这个文件名,那么比较当前的文件的版本是否是更高
					if(filenames.contains(file.getFileFilename())) {
						
						int index = filenames.indexOf(file.getFileFilename());
						ReportDepFile rdf = filelist.get(index);
						
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
			
			//将filelist按照创建时间排序
			Collections.sort(filelist,new Comparator<ReportDepFile>(){

				@Override
				public int compare(ReportDepFile o1, ReportDepFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
				
			});
			Collections.reverse(filelist);
			request.setAttribute("filelist", filelist);
			
			files.removeAll(filelist);
			
			//将files按照时间顺序排序
			Collections.sort(files,new Comparator<ReportDepFile>(){

				@Override
				public int compare(ReportDepFile o1, ReportDepFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
				
			});
			Collections.reverse(files);
			
			request.setAttribute("historylist", files);
			*/
		}
		
		return "RiskReportDepInput";
	}
	
	//第一次保存
	public void firstSave() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		
		try {
			
			PrintWriter out = response.getWriter();
			String reportId = request.getParameter("reportId");
			// 先要判断是否已经有人已经填写了报告了
			ReportDepartment rd = reportDepartmentDao.getReDepInput(reportId, session.get("userdepid").toString());
			if(rd != null) {
				out.print("fail");
				out.flush();
			}
			
			RiskReportDepForm report = WebUtil.handleRequest(request, RiskReportDepForm.class);
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
		
		String repdepId = request.getParameter("repdepId");
		String reportId = request.getParameter("reportId");
		
		String taskId = request.getParameter("taskId");
		ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
		String flowStatus = task.getRetFlowStatus();
		
		// 修改备注（保存并下一页的时候修改的只有备注）
		ReportDepartment redep = reportDepartmentDao.findById(repdepId);
		String remark = request.getParameter("depremark");
		redep.setRdRemark(remark);
		redep.setRdFlowstatus(flowStatus);
		reportDepartmentDao.merge(redep);
		
		// 为DepFlowConfirm准备数据
		String flowId = task.getRetFlowId();
		String nextStatus = reportFlowRuleDao.getNextStatus(flowId, flowStatus);
		ReportFlowRule nextstate = reportFlowRuleDao.getFlowState(flowId, nextStatus);
		
		String depId = nextstate.getRfDepartment();
		
		// 提交到本部门领导(默认是分配的领导)
		if(depId.equals("localdep")) {
			
			depId = session.get("userdepid").toString();
			List<UsersQueryView> leaders = usersQueryViewDao.findLeaderByDepId(depId);
			if(redep.getRdChargerId() != null && !redep.getRdChargerId().equals("")) {
				request.setAttribute("userId", redep.getRdChargerId());
			}
			else {
				ReportRiskDep rrd = reportRiskDepDao.findReportRiskDepById(reportId, depId);
				request.setAttribute("userId", rrd.getRrdChargeId());
			}
			request.setAttribute("users", leaders);
			request.setAttribute("depname", session.get("userdep").toString());
			
		}
		// 提交到企发部
		else {
			List<UsersQueryView> users = usersQueryViewDao.findStaffByDepId(depId);
			request.setAttribute("users", users);
			ReportRisk report = reportRiskDao.getReportRiskByReportId(reportId);
			request.setAttribute("userId", report.getRerPromoterId());
			request.setAttribute("depname", users.get(0).getDepName());
		}
		
		request.setAttribute("reportId", reportId);
		request.setAttribute("repdepId", repdepId);
		request.setAttribute("taskId", taskId);
		request.setAttribute("depId", depId);
		
		String fileIds = request.getParameter("newfiles");   //获取上传的文件的ID字符串
		request.setAttribute("fileIds", fileIds);
		
		request.setAttribute("actionName", "riskReport/riskReportDepInputAction");
		
		return "DepFlowConfirm";
	}
	
	// 保存或者是提交报告
	public void saveOrCommitReport() {
		
		try {
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			
			Map<?, ?> session = ServletActionContext.getContext().getSession();
			String flowId = "BMBGTJ";
			
			PrintWriter out = response.getWriter();
			
			String operation = request.getParameter("operation");
			String repdepId = request.getParameter("repdepId");
			String reportId = request.getParameter("reportId");
			String taskId = request.getParameter("taskId");
			String userId = request.getParameter("userId");
			String username = request.getParameter("username");
			String depId = request.getParameter("depId");
			String depname = request.getParameter("depname");
			String fileIds = request.getParameter("fileIds");
			
			ReportDepartment report = reportDepartmentDao.findById(repdepId);
			ReportTask reportTask = reportTaskDao.getReportTaskByTaskId(taskId);
			String flowStatus = reportTask.getRetFlowStatus();
			ReportFlowRule state = reportFlowRuleDao.getFlowState(flowId, flowStatus);
			
			
			//如果是一个过渡状态,那么先要跳转到下一个状态
			if(state.getRfTransitionFlag() == 0) {
				
				flowStatus = reportFlowRuleDao.getNextStatus(flowId, flowStatus);
				report.setRdFlowstatus(flowStatus);
				
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
				task.setRetFormId(repdepId);
				task.setRetPreTaskId(reportTask.getRetTaskId());
				task.setRetModifyFlag(0);
				reportTaskDao.save(task);
				
				//将待办变成已办
				reportTask.setRetNextStatus(flowStatus);
				reportTask.setRetProcessTime(df.format(new Date()));
				reportTask.setRetState("1");
				reportTask.setRetFormId(repdepId);
				reportTask.setRetModifyFlag(1);
				reportTaskDao.merge(reportTask);
				
				
			}
			else {
				reportTask.setRetLastTime(df.format(new Date()));
				reportTaskDao.merge(reportTask);
			}
			
			// 如果用户是提交
			if(operation.equals("commit")) {
				
				report.setRdFlowstatus(reportFlowRuleDao.getNextStatus(flowId, flowStatus));
				
				// 删除该用户的待办(需要调用中南电力设计院的接口)
				ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
				task.setRetProcessTime(df.format(new Date()));
				task.setRetState("1");
				task.setRetNextStatus(report.getRdFlowstatus());
				task.setRetFormId(repdepId);
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
				newTask.setRetFlowStatus(report.getRdFlowstatus());				//领导未审核或者企发部风险管理岗未审核
				newTask.setRetReportId(reportId);
				newTask.setRetReportName(report.getRdReportName());
				newTask.setRetState("0");		//0表示未完成，1表示已完成
				newTask.setRetLastTime(df.format(new Date()));			//设置报告的最后的处理时间(目前只对未处理的任务有效)
				newTask.setRetPreTaskId(taskId);
				newTask.setRetFormId(repdepId);
				newTask.setRetModifyFlag(0);
				reportTaskDao.save(newTask);
				
				//调用中南电力设计院的接口给部门领导或者企业发展部管理岗生成待办
				String TaskId = newTask.getRetTaskId();
				String TaskApp = "RiskEvent";
				String TaskUrl = "default/loginSingleSystemAction?userid=" + userId;
				String TaskTitle = "部门提交报告" + report.getRdReportName() + "待填写";
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
				formInfoDao.updateFormState(task.getRetFormId(), report.getRdFlowstatus(), reportFlowRuleDao.getFlowExplain(flowId, report.getRdFlowstatus()));
				//暂时不更新最近一次的编写人
				
			}
			
			// 如果是提交到本部门领导审核
			if(session.get("userdepid").toString().equals(depId)) {
				
				report.setRdChargerId(userId);
				report.setRdChargerName(username);
				
				// 将文件的标志位设置为已经发送,并且将不是最新上传的文件设置标志位
				reportDepFileDao.updateFileFlag(reportId,session.get("userdepid").toString(),Arrays.asList(fileIds.split("@")));
				
				//提交到本部门领导审核，则需要将附件的版本标识为0的附件设置为当前附件版本号最大数加上一
				
				reportDepFileDao.updateFileVersion(reportId, session.get("userdepid").toString());
				
				
			}
			report.setRdModifyDate(df.format(new Date()));
			reportDepartmentDao.merge(report);
			//更新最后处理时间
			formInfoDao.updateFormLastDate(reportTask.getRetFormId(), df.format(new Date()));
			
			out.print(operation);
			out.flush();
			return;
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
	//报告未提交取消了操作
	public void cancelAction() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String taskId = request.getParameter("taskId");
		String repdepId = request.getParameter("repdepId");
		
		ReportTask specificTask = reportTaskDao.getReportTaskByTaskId(taskId);
		if(specificTask != null) {
			
			specificTask.setRetLastTime(df.format(new Date()));
			reportTaskDao.merge(specificTask);
			
		}
		
		//更新报告部门表
		if(repdepId != null && !repdepId.equals("")) {
			ReportDepartment redep = reportDepartmentDao.findById(repdepId);
			redep.setRdModifyDate(df.format(new Date()));
			reportDepartmentDao.merge(redep);
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
			
			ArrayList<ReportDepFile> files = new ArrayList<ReportDepFile>();
			String date = df.format(new Date());
			JSONArray jsonArray = JSONArray.fromObject(FileUtil.uploadFile(request, "\\WEB-INF\\depupload"));
			
			for(int i = 0;i < jsonArray.size(); i++) {
				String str = jsonArray.getString(i);
				JSONObject obj = JSONObject.fromObject(str);
				
				ReportDepFile file = new ReportDepFile();
				file.setFileId(GenerateSequenceUtil.newGenerateFileId());
				file.setFileDate(date);				// 同一批次上传的文件的日期一样
				file.setFileFilename(obj.getString("filename"));
				file.setFileFilepath(obj.getString("filepath"));
				file.setFileUploader(uploader);
				file.setFileUploadername(uploadername);
				file.setFileUploaderdep(session.get("userdepid").toString());
				file.setFileReportId(reportId);
				file.setFileSendflag(0);			// 表示该文件还可以删除并没有提交上去
				file.setFilenewflag(1);				// 表示该文件是最新上传的
				
				
				//新上传的附件，将文件的版本标识设置为0
				//当将此报告发送之后，需要将版本标识为0的附件的版本号设置为当前文件版本的最大值加一
				file.setFileVersion(0);
				
				files.add(file);
			}
			reportDepFileDao.addFiles(files);
			
			JSONArray fileArray = new JSONArray();
			for(ReportDepFile file : files) {
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
			ReportDepFile file = reportDepFileDao.findById(fileId);
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
		
		ReportDepFile file = reportDepFileDao.findById(fileId);
		
		reportDepFileDao.delete(file);
	}
	
	
	
	
	/*---------------------------------------------------私有方法------------------------------------*/
	
	//第一次保存
	private TwoTuple<String,String> firstSave(RiskReportDepForm reportform) {
		
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		String flowId = "BMBGTJ";
		
		String reportId= reportform.getReportId();
		
		ReportDepartment report = new ReportDepartment();
		String repdepId = GenerateSequenceUtil.generateSequenceNo();
		report.setRdId(repdepId);
		report.setRdReportId(reportform.getReportId());
		report.setRdReportName(reportform.getReportname());
		report.setRdDepId(session.get("userdepid").toString());
		report.setRdDepName(session.get("userdep").toString());
		report.setRdWriterId(session.get("userid").toString());
		report.setRdWriterName(session.get("username").toString());
		report.setRdModifyDate(df.format(new Date()));
		report.setRdRemark(reportform.getDepremark());
		report.setRdFlowId(flowId);
		report.setRdFlowstatus(reportFlowRuleDao.getNextStatus(flowId, "0"));
		reportDepartmentDao.save(report);
		
		// 将任务变成已经已经处理的任务
		ReportTask reporttask = reportTaskDao.getReportTaskByTaskId(reportform.getTaskId());
		
		reporttask.setRetFlowId(flowId);
		reporttask.setRetFlowStatus("0");
		reporttask.setRetNextStatus(reportFlowRuleDao.getNextStatus(flowId, "0"));
		reporttask.setRetProcessTime(df.format(new Date()));
		reporttask.setRetState("1");		//0表示未完成，1表示已完成
		reporttask.setRetUserId(session.get("userid").toString());
		reporttask.setRetUserName(session.get("username").toString());
		reporttask.setRetLastTime(df.format(new Date()));
		reporttask.setRetFormId(repdepId);
		reporttask.setRetModifyFlag(1);
		reportTaskDao.merge(reporttask);
		
		// 删除其他人的待办任务(此处需要调用中南电力设计院的接口)(修改需求之后这个过程就不需要了)
		//reportTaskDao.delOtherTask(repdepId, reportform.getTaskId());
		
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
		task.setRetFormId(repdepId);
		task.setRetPreTaskId(reportform.getTaskId());
		task.setRetModifyFlag(1);
		reportTaskDao.save(task);
		
		//在formInfo表中新增一条记录
		ReportFormInfo formInfo = new ReportFormInfo();
		formInfo.setFiFlowId(flowId);
		formInfo.setFiFlowStatus(task.getRetFlowStatus());
		formInfo.setFiFormId(repdepId);
		formInfo.setFiWriterDepId(session.get("userdepid").toString());
		formInfo.setFiWriterDepName(session.get("userdep").toString());
		formInfo.setFiWriterId(session.get("userid").toString());
		formInfo.setFiWriterName(session.get("username").toString());
		formInfo.setFiStatements(reportFlowRuleDao.getFlowExplain(flowId, task.getRetFlowStatus()));
		formInfo.setFiLastdate(df.format(new Date()));
		formInfoDao.save(formInfo);
		
		return new TwoTuple<String,String>(repdepId,taskId);
	}
	
}
