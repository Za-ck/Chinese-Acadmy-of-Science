package com.action.riskReport;

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
import com.model.ReportCheck;
import com.model.ReportCheckFile;
import com.model.ReportDepFile;
import com.model.ReportFlowRule;
import com.model.ReportRisk;
import com.model.ReportRiskFile;
import com.model.ReportTask;
import com.model.Users;
import com.model.UsersQueryView;
import com.services.TaskExecutionWebServer;
import com.util.GenerateSequenceUtil;

public class RiskReportCheckCheckAction {

	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private ReportFlowRuleDAO reportFlowRuleDao;
	private ReportCheckFileDAO reportCheckFileDao;
	private ReportRiskDAO reportRiskDao;
	private UsersQueryViewDAO usersQueryViewDao;
	private ReportTaskDAO reportTaskDao;
	private ReportCheckDAO reportCheckDao;
	private DepartmentDAO departmentDao;
	private ReportFormInfoDAO formInfoDao;
	
	private ReportRiskFileDAO reportRiskFileDao;
	
	private UsersDAO usersDao;
	
	private ReportDepFileDAO reportDepFileDao;

	public ReportDepFileDAO getReportDepFileDao() {
		return reportDepFileDao;
	}
	public void setReportDepFileDao(ReportDepFileDAO reportDepFileDao) {
		this.reportDepFileDao = reportDepFileDao;
	}
	public UsersDAO getUsersDao() {
		return usersDao;
	}
	public void setUsersDao(UsersDAO usersDao) {
		this.usersDao = usersDao;
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
	
	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
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
	public String execute() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		
		String reportId = request.getParameter("reportId");
		String taskId = request.getParameter("taskId");
		
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
		
		ReportTask specificTask = null;
		if(taskId != null && !taskId.equals("")) {
			specificTask = reportTaskDao.getReportTaskByTaskId(taskId);
		}
		else {
			//如果通过taskId获取的任务为空，那么就需要通过reportId来获取任务了
			List<ReportTask> list = reportTaskDao.getSpecificUnprocessedTaskList(reportId, "HZBGSP", session.get("userid").toString(),session.get("userposition").toString());
			// 不为空，如果为空那么说明这个报告已经被撤回
			if(list != null && list.size() > 0) {
				specificTask = list.get(0);
			}
		}
		
		initData(request,specificTask,session);
		
		return "RiskReportCheckCheck";
		
	}
	
	public String checkRiskReport() {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			//HttpServletResponse response = ServletActionContext.getResponse();
			Map<?, ?> session = ServletActionContext.getContext().getSession();
			String taskId = request.getParameter("taskId");
			String handleview = request.getParameter("handleview");
			String reviewStatus = request.getParameter("reviewStatus");			//判断是审核通过还是审核不通过
			//String repcheck = request.getParameter("repcheck");
			
			ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);		//获取当前任务
			
			// 1.获取当前任务的状态以及设置当前任务的部分状态
			String currentStatus = task.getRetFlowStatus();
			
			// 设置当前任务的下一个状态	
			ReportFlowRule rule = reportFlowRuleDao.getFlowState("HZBGSP", currentStatus);
			String nextStatus = "";
			if(reviewStatus.equals("pass")) {
				nextStatus = rule.getRfNextStatus();
			}
			else {
				nextStatus = rule.getRfBackStatus();
			}
			
			task.setRetNextStatus(nextStatus);
			task.setRetView(handleview);
			task.setRetModifyFlag(1);
			reportTaskDao.merge(task);
			
			// 3.生成待办(此处需要调用中南电力设计院的接口)
			ReportCheck check = reportCheckDao.findById(task.getRetFormId());
			
			// 如果不是院领导审核通过
			if(!nextStatus.equals("*")) {
				
				ReportFlowRule nextRule = reportFlowRuleDao.getFlowState("HZBGSP", nextStatus);
				
				String depId = nextRule.getRfDepartment();
				
				// 判断是部门的领导还是员工
				String level = nextRule.getRfRole();
				
				if(depId.equals("localdep")) {
					
					depId = check.getRcDepId();
					
				}
				
				// 此处需要跳转到CheckFlowConfirm.jsp页面
				request.setAttribute("reportId", check.getRcReportId());
				request.setAttribute("repcheck", check.getRcId());
				request.setAttribute("taskId", taskId);
				request.setAttribute("depId", depId);
				request.setAttribute("depname", departmentDao.getdepname(depId));
				request.setAttribute("actionName", "riskReport/riskReportCheckCheckAction_saveOrCommitReport");
				
				if(level.equals("0")) {
					
					// 如果是员工
					List<UsersQueryView> users = usersQueryViewDao.findStaffByDepId(depId);
					request.setAttribute("users", users);
					request.setAttribute("userId", check.getRcWriterId());
				}
				
				else {
					// 如果是领导
					List<UsersQueryView> users = usersQueryViewDao.findLeaderByDepId(depId);
					request.setAttribute("users", users);
					
					// 如果是本部门领导
					if(nextRule.getRfDepartment().equals("localdep")) {
						request.setAttribute("userId", check.getRcLeaderId());
					}
					else {
						request.setAttribute("userId", check.getRcDeanId());
					}
				}
				
				// 将所有新上传的文件的标志置为0,表示这些文件在审核不通过之后是不可以修改的
				reportCheckFileDao.updateNewFlag(task.getRetReportId());
				
				return "CheckFlowConfirm";
					
			}
			
			else {
				//更新汇总报告表
				check.setRcModifyDate(df.format(new Date()));
				check.setRcFlowStatus(nextStatus);
				reportCheckDao.merge(check);
				
				//更新formInfo表
				formInfoDao.updateFormState(task.getRetFormId(), nextStatus, "已完成");
			}
			
			// 将待办变成已办
			task.setRetProcessTime(df.format(new Date()));
			task.setRetState("1");
			reportTaskDao.merge(task);
			
			// 调用接口删除待办
			TaskExecutionWebServer.deleteTask(taskId, "RiskEvent");
			
			
			//response.sendRedirect("/RiskEvent/default/processAction_unProcessed");
			
//			request.setAttribute("taskId", taskId);
//			request.setAttribute("reportId", check.getRcReportId());
			
			return "success";
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void endCheckFlow() {
		
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			String result = checkRiskReport();
			if(result.equals("success")) {
				PrintWriter out = response.getWriter();
				out.write("success");
				out.flush();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	// 暂时没有用到
	public void returnRiskReport() {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String taskId = request.getParameter("taskId");
			String handleview = request.getParameter("handleview");
			//String repcheck = request.getParameter("repcheck");
			
			ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);		//获取当前任务
			
			// 1.获取当前任务的状态以及设置当前任务的部分状态
			String currentStatus = task.getRetFlowStatus();
			task.setRetView(handleview);
			task.setRetProcessTime(df.format(new Date()));
			task.setRetState("1");
			
			// 设置当前任务的下一个状态	
			ReportFlowRule rule = reportFlowRuleDao.getFlowState("HZBGSP", currentStatus);
			String nextStatus = rule.getRfNextStatus();
			task.setRetNextStatus(nextStatus);
			reportTaskDao.merge(task);
			
			// 3.生成待办
			ReportCheck check = reportCheckDao.findById(task.getRetFormId());
			ReportTask newTask = new ReportTask();
			newTask.setRetTaskId(GenerateSequenceUtil.generateTaskId());
			newTask.setRetReportId(task.getRetReportId());
			newTask.setRetReportName(task.getRetReportName());
			newTask.setRetFlowId("HZBGSP");
			newTask.setRetFlowStatus(nextStatus);
			newTask.setRetFormId(task.getRetFormId());
			newTask.setRetDepId(check.getRcDepId());
			newTask.setRetDepName(check.getRcDepName());
			newTask.setRetUserId(check.getRcWriterId());
			newTask.setRetUserName(check.getRcWriterName());
			newTask.setRetLastTime(df.format(new Date()));
			newTask.setRetPreTaskId(task.getRetTaskId());
			newTask.setRetState("0");
			newTask.setRetModifyFlag(0);
			reportTaskDao.save(newTask);
			
			//更新汇总报告表
			check.setRcModifyDate(df.format(new Date()));
			check.setRcFlowStatus(task.getRetNextStatus());
			reportCheckDao.merge(check);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = response.getWriter();
			out.print("success");
			out.flush();
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
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
			
			// 更新ReportCheck表
			ReportCheck report = reportCheckDao.findById(repcheck);
			ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
			
			//如果是保存
			if(operation.equals("save")) {
				
				task.setRetLastTime(df.format(new Date()));
				reportTaskDao.merge(task);
				
			}
			
			// 如果用户是提交
			else {
				
				report.setRcFlowStatus(task.getRetNextStatus());
				
				// 删除该用户的待办(需要调用中南电力设计院的接口)
				//ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
				task.setRetProcessTime(df.format(new Date()));
				task.setRetState("1");
				task.setRetModifyFlag(1);
				reportTaskDao.merge(task);
				
				// 调用接口删除待办
				TaskExecutionWebServer.deleteTask(taskId, "RiskEvent");
				
				ReportTask newTask = new ReportTask();
				newTask.setRetTaskId(GenerateSequenceUtil.generateTaskId());
				newTask.setRetDepId(depId);
				newTask.setRetDepName(depname);
				newTask.setRetUserId(userId);
				newTask.setRetUserName(username);
				newTask.setRetFlowId(flowId);
				newTask.setRetFlowStatus(task.getRetNextStatus());				
				newTask.setRetReportId(reportId);
				newTask.setRetReportName(report.getRcReportName());
				newTask.setRetState("0");		//0表示未完成，1表示已完成
				newTask.setRetLastTime(df.format(new Date()));			//设置报告的最后的处理时间(目前只对未处理的任务有效)
				newTask.setRetPreTaskId(taskId);
				newTask.setRetFormId(repcheck);
				newTask.setRetModifyFlag(0);
				reportTaskDao.save(newTask);
				
				//调用中南电力设计院的接口生成待办
				String TaskId = newTask.getRetTaskId();
				String TaskApp = "RiskEvent";
				String TaskUrl = "default/loginSingleSystemAction?userid=" + userId;
				String TaskTitle = "待处理汇总报告" + task.getRetReportName();
				String ReceiverId = userId;
				String ReceiverName = username;
				String ReceiverDepName = depname;
				String ReceiveTime = df.format(new Date());
				String CreatorName = report.getRcWriterName();
				String CreatorDepName = report.getRcDepName();
				String PreUserName = session.get("username").toString();
				String PreDepName = session.get("userdep").toString();
				TaskExecutionWebServer.addTask(TaskId, TaskApp, TaskUrl, TaskTitle, ReceiverId, ReceiverName, ReceiverDepName, ReceiveTime, 
						CreatorName, CreatorDepName, PreUserName, PreDepName);
				
				//更新formInfo表的流程状态
				formInfoDao.updateFormState(task.getRetFormId(), task.getRetNextStatus(), reportFlowRuleDao.getFlowExplain(flowId, task.getRetNextStatus()));
				
			}
			
			// 如果是提交到院领导审核
			ReportFlowRule rule = reportFlowRuleDao.getFlowState("HZBGSP", task.getRetNextStatus());
			if(!rule.getRfDepartment().equals("localdep")) {
				
				report.setRcDeanId(userId);
				report.setRcDeanName(username);
				
			}
			report.setRcModifyDate(df.format(new Date()));
			reportCheckDao.merge(report);
			
			// 更新最后处理时间
			formInfoDao.updateFormLastDate(task.getRetFormId(), df.format(new Date()));
			
			out.print(operation);
			out.flush();
			return;
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	public void saveHandleView() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String taskId = request.getParameter("taskId");
		String handleview = request.getParameter("handleview");
		
		ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
		task.setRetView(handleview);
		task.setRetLastTime(df.format(new Date()));
		reportTaskDao.merge(task);
		
		// 更新最后处理时间
		formInfoDao.updateFormLastDate(task.getRetFormId(), df.format(new Date()));
		
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			PrintWriter out = response.getWriter();
			out.print("save");
			out.flush();
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/*-----------------------------------------私有方法------------------------------------------------*/
	//准备基础数据
	private void initData(HttpServletRequest request, ReportTask specificTask,Map<?, ?> session) {
		
		String reportId = request.getParameter("reportId");
		
		ReportRisk report = null;
		if(reportId != null && !reportId.equals("")) {
			report = reportRiskDao.findById(reportId);
			request.setAttribute("annual", report.getRerAnnual());
			request.setAttribute("reportId", reportId);
		}
		
		// 设置审核意见还有部门提交报告所带的附件
		if(specificTask != null) {
			
			String taskId = specificTask.getRetTaskId();
			request.setAttribute("taskId", taskId);
			
			request.setAttribute("handleView", specificTask.getRetView());			//获取自身保存的处理意见
			//获取ReportCheck得到编写人
			ReportCheck check = reportCheckDao.findById(specificTask.getRetFormId());
			request.setAttribute("writername", check.getRcWriterName());			//填写人的姓名
			request.setAttribute("depname", check.getRcDepName());					//填写人的部门
			request.setAttribute("repcheck", check.getRcId());						//ReportCheck主键
			request.setAttribute("remark", check.getRcRemark());					//备注
			request.setAttribute("leadername", check.getRcLeaderName());			//部门负责人
			//request.setAttribute("presidentId", check.getRcDeanId());				//主管院领导
			
			String userId = session.get("userid").toString();
			if(!userId.equals(check.getRcDeanId())) {
				request.setAttribute("updateFlag", "0");			//表示是部门领导人审核
			}
			else {
				request.setAttribute("updateFlag", "1");			//表示是主管院领导审核
			}
			
			// 判断报告是否是退回去的
			ReportFlowRule rule = reportFlowRuleDao.getFlowState(specificTask.getRetFlowId(), specificTask.getRetFlowStatus());
			if(rule.getRfNextStatus().equals("-1")) {
				request.setAttribute("updateFlag", "2");		//表示报告是要退回去的
			}
			
			// 获取报告的附带文件
			List<ReportCheckFile> files = reportCheckFileDao.getspecificFile(reportId);
			int maxVersion = reportCheckFileDao.queryMaxVersion(reportId);
			List<ReportCheckFile> relFiles = new ArrayList<ReportCheckFile>();
			List<ReportCheckFile> historyFiles = new ArrayList<ReportCheckFile>();
			if(files != null && files.size() > 0){
				for(ReportCheckFile file : files){
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
			Collections.sort(relFiles,new Comparator<ReportCheckFile>(){

				@Override
				public int compare(ReportCheckFile o1, ReportCheckFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
				
			});
			Collections.reverse(relFiles);
			request.setAttribute("filelist", relFiles);
			//历史版本附件表需要显示数据库中所有低版本的附件（版本号不为0，但是低于最高版本号）
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
			Collections.sort(filelist, new Comparator<ReportCheckFile>(){

				@Override
				public int compare(ReportCheckFile o1, ReportCheckFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
				
			});
			Collections.reverse(filelist);
			request.setAttribute("filelist", filelist);
			
			files.removeAll(filelist);
			
			
			Collections.sort(files, new Comparator<ReportCheckFile>(){

				@Override
				public int compare(ReportCheckFile o1, ReportCheckFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
				
			});
			Collections.reverse(files);
			
			request.setAttribute("historylist", files);
			*/
			// 获取报告的历史审核意见
			List<ReportTask> handleList = reportTaskDao.getHandleViewList(specificTask.getRetFormId());
			request.setAttribute("handleList", handleList);
			
			// 判断该流程是否走到了最后一步
			String nextStatus = rule.getRfNextStatus();
			if(nextStatus.equals("*")) {
				
				request.setAttribute("status", "end");		//表示流程将要结束
				
			}
			else {
				request.setAttribute("status", "noend");	//表示流程还没有即将结束
			}

		}
		
		// 查询需要审核的报告
		List<String> reportIdList = reportTaskDao.getUnprocessedReport(session.get("userid").toString(),session.get("userposition").toString(), "HZBGSP");
		List<ReportRisk> reportCheckList = reportRiskDao.getReportRiskInList(reportIdList);
		request.setAttribute("reportCheckList", reportCheckList);
		
		// 设置当前处理的人的姓名和部门
		request.setAttribute("checkdep", session.get("userdep").toString());
		request.setAttribute("checkuser", session.get("username").toString());
		
	}
	
	
}
