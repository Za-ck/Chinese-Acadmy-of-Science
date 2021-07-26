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
import com.dao.ReportCheckDAO;
import com.dao.ReportCheckFileDAO;
import com.dao.ReportDepFileDAO;
import com.dao.ReportFlowRuleDAO;
import com.dao.ReportFormInfoDAO;
import com.dao.ReportRiskDAO;
import com.dao.ReportRiskFileDAO;
import com.dao.ReportTaskDAO;
import com.dao.UsersDAO;
import com.model.ReadFlowView;
import com.model.ReportCheck;
import com.model.ReportCheckFile;
import com.model.ReportDepFile;
import com.model.ReportFlowRule;
import com.model.ReportRisk;
import com.model.ReportRiskFile;
import com.model.ReportTask;
import com.model.Users;
import com.util.FileUtil;

public class RiskReportCheckReadAction {

	private ReportCheckDAO reportCheckDao;
	private ReportFlowRuleDAO reportFlowRuleDao;
	private ReportCheckFileDAO reportCheckFileDao;
	private ReportRiskDAO reportRiskDao;
	private ReportTaskDAO reportTaskDao;
	private ReadFlowViewDAO readFlowViewDao;
	private ReportFormInfoDAO formInfoDao;
	
	
	private UsersDAO usersDao;
	private DepartmentDAO departmentDao;
	private ReportRiskFileDAO reportRiskFileDao;
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

	public ReportCheckDAO getReportCheckDao() {
		return reportCheckDao;
	}

	public void setReportCheckDao(ReportCheckDAO reportCheckDao) {
		this.reportCheckDao = reportCheckDao;
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

	@SuppressWarnings("unchecked")
	public String ReadReport() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String reportId = request.getParameter("reportId");
		String taskId = request.getParameter("taskId");

		// 向startFiles中添加启动流程的附件
		List<ReportRiskFile> startFiles = reportRiskFileDao.findByProperty(
				"fileReportId", reportId);
		
		for (int i = 0; i < startFiles.size(); i++) {
			// 获取当前附件中的创建人id
			String creatorId = startFiles.get(i).getFileUploader();
			Users users = usersDao.findById(creatorId);
			startFiles.get(i).setFileUploaderDepName(departmentDao.getdepname(users.getUserDep()));
		}
		// 按照时间顺序排序
		Collections.sort(startFiles, new Comparator<ReportRiskFile>() {

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
		
		

		// 1.得到ReportRisk
		ReportRisk report = reportRiskDao.findById(reportId);
		request.setAttribute("annual", report.getRerAnnual());
		request.setAttribute("reportId", reportId);
		request.setAttribute("reportname", report.getRerReportName());

		// 2.设置其他字段
		request.setAttribute("taskId", taskId);
		ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);

		ReportCheck check = reportCheckDao.findById(task.getRetFormId());
		request.setAttribute("writername", check.getRcWriterName()); // 填写人的姓名
		request.setAttribute("depname", check.getRcDepName()); // 填写人的部门
		request.setAttribute("repcheck", check.getRcId()); // ReportCheck主键
		request.setAttribute("remark", check.getRcRemark()); // 备注
		request.setAttribute("leadername", check.getRcLeaderName()); // 部门负责人
		request.setAttribute("presidentname", check.getRcDeanName()); // 主管院领导

		// 3.获取报告的附带文件
		List<ReportCheckFile> files = reportCheckFileDao
				.getspecificFile(reportId);
		//新需求
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
		List<ReportCheckFile> filelist = getLatestFile(files);

		// 按照时间顺序排序

		Collections.sort(filelist, new Comparator<ReportCheckFile>() {

			@Override
			public int compare(ReportCheckFile o1, ReportCheckFile o2) {
				return o1.getFileDate().compareTo(o2.getFileDate());
			}

		});
		Collections.reverse(filelist);

		request.setAttribute("filelist", filelist);*/

		// 4.得到需要报告的处理意见
		List<ReportTask> handleList = reportTaskDao.getHandleViewList(task
				.getRetFormId());
		request.setAttribute("handleList", handleList);

		request.setAttribute("actionName",
				"riskReport/riskReportCheckReadAction");

		return "success";
	}

	public void readFile() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");

		try {

			PrintWriter out = response.getWriter();
			String reportId = request.getParameter("reportId");
			List<ReportCheckFile> files = reportCheckFileDao
					.getspecificFile(reportId);
			files = getLatestFile(files);
			// 按照时间顺序排序
			Collections.sort(files, new Comparator<ReportCheckFile>() {
				@Override
				public int compare(ReportCheckFile o1, ReportCheckFile o2) {
					return o1.getFileDate().compareTo(o2.getFileDate());
				}
			});
			JSONArray jsonArray = new JSONArray();
			for (ReportCheckFile file : files) {
				JSONObject object = new JSONObject();
				object.put("filename", file.getFileFilename());
				object.put("flowname", "风险报告审批流程");
				object.put("uploader", file.getFileUploadername());
				object.put("uploadtime", file.getFileDate());
				object.put("path",
						"/RiskEvent/riskReport/riskReportCheckInputAction_downloadFile?fileId="
								+ file.getFileId());
				jsonArray.add(object.toString());
			}
			out.print(jsonArray.toString());
			out.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// 下载最新的附件
	public void downloadFile() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();

			HttpServletResponse response = ServletActionContext.getResponse();

			String reportId = request.getParameter("reportId");
			// String writerId = request.getParameter("writerId");
			String reportname = new String(request.getParameter("reportname")
					.getBytes("ISO-8859-1"), "UTF-8");

			// 获取报告的附带文件
			List<ReportCheckFile> files = reportCheckFileDao
					.getspecificFile(reportId);
			// 抽取最新版本的文件和历史版本的文件
			List<ReportCheckFile> filelist = getLatestFile(files);

			Map<String, String> fileMap = new HashMap<String, String>();
			for (ReportCheckFile file : filelist) {

				fileMap.put(file.getFileFilename(), file.getFileFilepath());

			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String filename = reportname + "-"
					+ files.get(0).getFileUploadername() + "-"
					+ df.format(new Date());
			// System.out.println(filename);
			FileUtil.downloadFileList(response, fileMap, filename);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("文件下载出错");
		}

	}

	// 撤回报告
	public void revocationRiskReport() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			// Map<?, ?> session =
			// ServletActionContext.getContext().getSession();
			PrintWriter out = response.getWriter();

			String reportId = request.getParameter("reportId");
			String taskId = request.getParameter("taskId");

			// 1.检查可撤回标识
			int revocationFlag = reportTaskDao.getRevocationFlag(taskId);
			if (revocationFlag == 0) {
				// 如果不可撤回
				out.write("fail");
				out.flush();
				return;
			}
			// 2.在任务表中撤回任务
			ReportTask task = reportTaskDao.revocateTask(taskId);
			reportCheckDao.revocateReportCheck(task.getRetFormId(),
					task.getRetFlowStatus());

			// 3.修改用户新上传文件的发送标志
			reportCheckFileDao.updateSendFlagInRevocation(reportId);

			// 调用中南电力设计院接口删除领导或者企发部管理岗的待办,还要给当前人员增加待办

			// 4、设置撤回成功之后需要跳转到的页面
			ReportFlowRule rule = reportFlowRuleDao.getFlowState(
					task.getRetFlowId(), task.getRetFlowStatus());

			// 5、更新formInfo中的状态
			formInfoDao.updateFormState(task.getRetFormId(),
					task.getRetFlowStatus(), rule.getRfFlowExplain());

			out.print(rule.getRfActionUrl() + "?reportId=" + reportId
					+ "&taskId=" + task.getRetTaskId());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public String ReadFlow() {

		HttpServletRequest request = ServletActionContext.getRequest();
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		// String reportId = request.getParameter("reportId");
		String taskId = request.getParameter("taskId");
		if (taskId != null && !taskId.equals("")) {

			ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
			String formId = task.getRetFormId();

			List<ReadFlowView> recordlist = readFlowViewDao
					.getRecordlist(formId);
			request.setAttribute("recordlist", recordlist);
			request.setAttribute("userId", session.get("userid").toString());
			request.setAttribute("flowImage", getFlowImage(formId));
			request.setAttribute("title", task.getRetReportName());
		}

		else {
			ReportFlowRule rule = reportFlowRuleDao.getFlowState("HZBGSP", "0");
			request.setAttribute("flowImage", rule.getRfFlowImage());
			request.setAttribute("title", "风险报告审批流程");
		}

		return "readFlow";
	}

	private String getFlowImage(String formId) {

		String flowStatus = "0";

		if (formId != null && !formId.equals("")) {

			ReportCheck check = reportCheckDao.findById(formId);
			flowStatus = check.getRcFlowStatus();
		}

		ReportFlowRule rule = reportFlowRuleDao.getFlowState("HZBGSP",
				flowStatus);

		return rule.getRfFlowImage();
	}

	// 获取最新版本的附件
	public List<ReportCheckFile> getLatestFile(List<ReportCheckFile> files) {

		// 抽取最新版本的文件和历史版本的文件
		List<ReportCheckFile> filelist = new ArrayList<ReportCheckFile>();
		List<String> filenames = new ArrayList<String>();
		if (files != null && files.size() > 0) {

			for (ReportCheckFile file : files) {

				// 如果包括这个文件名,那么比较当前的文件的版本是否是更高
				if (filenames.contains(file.getFileFilename())) {

					int index = filenames.indexOf(file.getFileFilename());
					ReportCheckFile rdf = filelist.get(index);

					if (rdf.getFileDate().compareTo(file.getFileDate()) < 0) {
						// 替换
						filelist.set(index, file);
					}

				} else {
					filenames.add(file.getFileFilename());
					filelist.add(file);
				}

			}

		}
		return filelist;
	}

}
