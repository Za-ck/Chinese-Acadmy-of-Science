package com.action.riskReport;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.dao.ReportFlowRuleDAO;
import com.dao.ReportFormInfoDAO;
import com.dao.ReportRiskDAO;
import com.dao.ReportRiskDepDAO;
import com.dao.ReportRiskFileDAO;
import com.dao.ReportTaskDAO;
import com.dao.UsersQueryViewDAO;
import com.form.RiskReportForm;
import com.model.Department;
import com.model.ReportFormInfo;
import com.model.ReportRisk;
import com.model.ReportRiskDep;
import com.model.ReportRiskFile;
import com.model.ReportTask;
import com.model.UsersQueryView;
import com.services.TaskExecutionWebServer;
import com.util.FileUtil;
import com.util.GenerateSequenceUtil;
import com.util.TwoTuple;
import com.util.WebUtil;

public class RiskReportInputAction {
	
	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private DepartmentDAO departmentDao;
	private ReportRiskDAO reportRiskDao;
	private ReportTaskDAO reportTaskDao;
	private ReportRiskDepDAO reportRiskDepDao;
	private ReportRiskFileDAO reportRiskFileDao;
	private ReportFlowRuleDAO reportFlowRuleDao;
	private UsersQueryViewDAO usersQueryViewDao;
	private ReportFormInfoDAO formInfoDao;

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
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
	
	public UsersQueryViewDAO getUsersQueryViewDao() {
		return usersQueryViewDao;
	}

	public void setUsersQueryViewDao(UsersQueryViewDAO usersQueryViewDao) {
		this.usersQueryViewDao = usersQueryViewDao;
	}

	public ReportFormInfoDAO getFormInfoDao() {
		return formInfoDao;
	}

	public void setFormInfoDao(ReportFormInfoDAO formInfoDao) {
		this.formInfoDao = formInfoDao;
	}
	
	// 获取当前用户发起的报告
	public void getRiskReportList() {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		String userId = session.get("userid").toString();
		
		try {
			PrintWriter out = response.getWriter();
			List<ReportRisk> rrList = reportRiskDao.getReportRiskListByUserId(userId);
			if(null == rrList || rrList.size() <= 0)	return;
			
			JSONArray jsonArray = new JSONArray();
			for(ReportRisk rr : rrList) {
				JSONObject object = new JSONObject();
				object.put("reportId", rr.getRerReportId());
				object.put("reportName", rr.getRerReportName());
				jsonArray.add(object.toString());
			}
			
			out.print(jsonArray.toString());
			out.flush();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	// 检查报告名称是否有重复的
	@SuppressWarnings("unchecked")
	public void checkReportName() throws UnsupportedEncodingException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		try {
			
			PrintWriter out = response.getWriter();
			//String reportname = URLDecoder.decode(request.getParameter("reportname"),"UTF-8");
			String reportname = request.getParameter("reportname");
			//System.out.println(reportname);
			List<ReportRisk> reports = reportRiskDao.findByProperty("rerReportName", reportname);
			if(reports != null && reports.size() > 0) {
				out.print("fail");	//报告名称已经存在
				out.flush();
				return;
			}
			out.print("success");
			out.flush();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//获取某个部门下面的所有领导
	public void getLeaderByDepId() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		try {
			
			PrintWriter out = response.getWriter();
			String deptId = request.getParameter("deptId");
			List<UsersQueryView> users = usersQueryViewDao.findLeaderByDepId(deptId);
			JSONArray jsonArray = new JSONArray();
			for(UsersQueryView user : users) {
				JSONObject object = new JSONObject();
				object.put("userid", user.getUserId());
				object.put("username", user.getUserName());
				object.put("depid",user.getDepId());
				object.put("depname", user.getDepName());
				jsonArray.add(object.toString());
			}
			out.print(jsonArray.toString());
			out.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//第一次保存
	public void firstSave() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		try {
			RiskReportForm report = WebUtil.handleRequest(request, RiskReportForm.class);
			TwoTuple<String,String> result = firstSave(report);
			PrintWriter out = response.getWriter();
			out.print(result.first + "-" + result.second);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	//新建基本信息 add by tyq
    public String build() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			RiskReportForm reportform = WebUtil.handleRequest(request, RiskReportForm.class);
			String Idlist = reportform.getIdlist();
			String leaderIds = reportform.getLeaderIds();
			
			// 根据reportform和登录的用户对report赋值
			String reportId = reportform.getReportId();
			String taskId = reportform.getTaskId();
			ReportRisk report = reportRiskDao.getReportRiskByReportId(reportId);
			String reportId1 = GenerateSequenceUtil.generateSequenceNo();
			report.setRerReportId(reportId1);
						
			report.setRerReportName(reportform.getReportname());
			report.setRerAnnual(reportform.getAnnual());
			report.setRerRemark(reportform.getReportremark());
			//reportRiskDao.merge(report);
			reportRiskDao.save(report);
			
			//add by tyq
			
			Map<?, ?> session = ServletActionContext.getContext().getSession();
			String flowId = "BGBXQD";
			
			/*ReportRisk report1 = new ReportRisk();
			String reportId1 = GenerateSequenceUtil.generateSequenceNo();
			report1.setRerReportId(reportId1);
			report1.setRerReportName(reportform.getReportname());
			report1.setRerPromoterId(session.get("userid").toString());
			report1.setRerPromoterName(session.get("username").toString());
			report1.setRerAnnual(reportform.getAnnual());
			report1.setRerRemark(reportform.getReportremark());
			reportRiskDao.save(report1);*/
			
			// 新增一个已经处理的任务
			ReportTask reporttask = new ReportTask();
			String donetaskId = GenerateSequenceUtil.generateTaskId();
			reporttask.setRetTaskId(donetaskId);
			reporttask.setRetDepId(session.get("userdepid").toString());
			reporttask.setRetDepName(session.get("userdep").toString());
			reporttask.setRetFlowId(flowId);
			reporttask.setRetFlowStatus("0");
			reporttask.setRetNextStatus(reportFlowRuleDao.getNextStatus(flowId, "0"));
			reporttask.setRetProcessTime(df.format(new Date()));
			reporttask.setRetReportId(reportId);
			reporttask.setRetReportName(reportform.getReportname());
			reporttask.setRetState("1");		//0表示未完成，1表示已完成
			reporttask.setRetUserId(session.get("userid").toString());
			reporttask.setRetUserName(session.get("username").toString());
			reporttask.setRetLastTime(df.format(new Date()));
			reporttask.setRetFormId(reportId);
			reporttask.setRetModifyFlag(1);
			reportTaskDao.save(reporttask);
			
			//新增一个没有处理的任务
			ReportTask task = new ReportTask();
			String taskId1 = GenerateSequenceUtil.generateTaskId();
			task.setRetTaskId(taskId1);
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
			task.setRetFormId(reportId);
			task.setRetPreTaskId(donetaskId);
			task.setRetModifyFlag(1);
			reportTaskDao.save(task);
			
			//在formInfo表中新增一条记录
			ReportFormInfo formInfo = new ReportFormInfo();
			formInfo.setFiFlowId(flowId);
			formInfo.setFiFlowStatus(task.getRetFlowStatus());
			formInfo.setFiFormId(reportId);
			formInfo.setFiWriterDepId(session.get("userdepid").toString());
			formInfo.setFiWriterDepName(session.get("userdep").toString());
			formInfo.setFiWriterId(session.get("userid").toString());
			formInfo.setFiWriterName(session.get("username").toString());
			formInfo.setFiStatements(reportFlowRuleDao.getFlowExplain(flowId, task.getRetFlowStatus()));
			formInfo.setFiLastdate(df.format(new Date()));
			formInfoDao.save(formInfo);
			
			//end
			
			// 修改task表中的报告名称
			//reportTaskDao.updateReportname(reportId, reportform.getReportname());
			//
			// 修改最后处理时间
			//formInfoDao.updateFormLastDate(reportId, df.format(new Date()));
			
			//为selectDepartment.jsp准备数据
			request.setAttribute("Idlist", Idlist);
			request.setAttribute("leaderIds", leaderIds);
			request.setAttribute("taskId", taskId);
			request.setAttribute("reportId", reportId);
			request.setAttribute("reportname", reportform.getReportname());
			
			List<Department> deplist = departmentDao.findAll();
			request.setAttribute("deplist", deplist);
			
			// 获取已经选择的部门以及部门领导人
//			if(Idlist != null && !Idlist.equals("")) {
//				List<UsersQueryView> leaders = usersQueryViewDao.getLeadersInDepList(Arrays.asList(Idlist.split("@")));
//				request.setAttribute("leaders", leaders);
//			}
			
			if(leaderIds != null && !leaderIds.equals("")) {
				List<UsersQueryView> leaderlist = usersQueryViewDao.getUsersInList(Arrays.asList(leaderIds.split("@")));
				request.setAttribute("leaderlist", leaderlist);
			}
			
			return "selectDep";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	//保存并下一页(保存基本信息)
	public String saveBasicInfo() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			RiskReportForm reportform = WebUtil.handleRequest(request, RiskReportForm.class);
			String Idlist = reportform.getIdlist();
			String leaderIds = reportform.getLeaderIds();
			
			// 根据reportform和登录的用户对report赋值
			String reportId = reportform.getReportId();
			String taskId = reportform.getTaskId();
			ReportRisk report = reportRiskDao.getReportRiskByReportId(reportId);
						
			report.setRerReportName(reportform.getReportname());
			report.setRerAnnual(reportform.getAnnual());
			report.setRerRemark(reportform.getReportremark());
			reportRiskDao.merge(report);
			
			// 修改task表中的报告名称
			reportTaskDao.updateReportname(reportId, reportform.getReportname());
			
			// 修改最后处理时间
			formInfoDao.updateFormLastDate(reportId, df.format(new Date()));
			
			//为selectDepartment.jsp准备数据
			request.setAttribute("Idlist", Idlist);
			request.setAttribute("leaderIds", leaderIds);
			request.setAttribute("taskId", taskId);
			request.setAttribute("reportId", reportId);
			request.setAttribute("reportname", reportform.getReportname());
			
			List<Department> deplist = departmentDao.findAll();
			request.setAttribute("deplist", deplist);
			
			// 获取已经选择的部门以及部门领导人
//			if(Idlist != null && !Idlist.equals("")) {
//				List<UsersQueryView> leaders = usersQueryViewDao.getLeadersInDepList(Arrays.asList(Idlist.split("@")));
//				request.setAttribute("leaders", leaders);
//			}
			
			if(leaderIds != null && !leaderIds.equals("")) {
				List<UsersQueryView> leaderlist = usersQueryViewDao.getUsersInList(Arrays.asList(leaderIds.split("@")));
				request.setAttribute("leaderlist", leaderlist);
			}
			
			return "selectDep";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	// 保存报告或者发送报告
	public void saveOrCommitReport() {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = response.getWriter();
			Map<?, ?> session = ServletActionContext.getContext().getSession();
			
			String operation = request.getParameter("operation");
			String leaderlist = request.getParameter("leaderlist");
			String reportId = request.getParameter("reportId");
			String taskId = request.getParameter("taskId");
			String reportname = request.getParameter("reportname");
			
			//已经选择的部门的Id列表
			List<String> depIdList = new ArrayList<String>();
			
			//System.out.println(leaderArray.size());
			if(null == taskId || "".equals(taskId)) {   //通过下拉框选择的报告
				List<ReportRiskDep> rrdList = reportRiskDepDao.getReportRiskDepByReportId(reportId);
				taskId = reportTaskDao.getReportTaskListByReportId(reportId).get(1).getRetTaskId();
				for(int i = 0; i < rrdList.size(); i++) {
					depIdList.add(rrdList.get(i).getRrdDepId());   //添加选择的部门
				}
			}
			
			
			ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
			String flowStatus = task.getRetFlowStatus();
			String flowId = task.getRetFlowId();
			if(operation.equals("commit")) {
				flowStatus = reportFlowRuleDao.getNextStatus(flowId, flowStatus);
			}
			
			// 更新ReportRiskDep表
			List<ReportRiskDep> rdlist = new ArrayList<ReportRiskDep>();
			
			// 如果是刚开始新建的报告，先删除
			if("0".equals(task.getRetState())) {
				reportRiskDepDao.deleteByReportId(reportId);			//先删除
			}
			
			JSONArray leaderArray = JSONArray.fromObject(leaderlist);
			if(leaderArray.size() > 0) {
				
				for(int i = 0;i < leaderArray.size(); i++) {
					
					JSONObject job = leaderArray.getJSONObject(i);  // 遍历jsonarray数组，把每一个对象转成 json对象
					
					String depId = job.getString("depid");
					if(depIdList.contains(depId) && !"0".equals(task.getRetState())) continue;
					
					ReportRiskDep rrd = new ReportRiskDep();
					rrd.setRrdChargeId(job.getString("userid"));
					rrd.setRrdChargeName(job.getString("username"));
					
					rrd.setRrdDepId(depId);
					rrd.setRrdDepName(job.getString("depname"));
					rrd.setRrdFlowId("BGBXQD");
					rrd.setRrdReportId(reportId);
					rrd.setRrdReportName(reportname);
					rrd.setRrdStatus(flowStatus);
					rdlist.add(rrd);
				}
				
			}
			reportRiskDepDao.insertBatch(rdlist);
			
			if(operation.equals("commit")) {
				
				// 修改ReportRisk表
				ReportRisk report = reportRiskDao.getReportRiskByReportId(reportId);
				report.setRerDepNum(leaderArray.size());
				report.setRerDoneNum(0);
				report.setRerDate(df.format(new Date()));		//设置发起时间
				//report.setRerCancelNum(0);
				reportRiskDao.merge(report);
				
				// 将任务由未处理变为已处理（此处需要调用中南电力设计院的接口）
				task.setRetNextStatus(flowStatus);
				task.setRetProcessTime(df.format(new Date()));
				task.setRetState("1");		//0表示未完成，1表示已完成
				reportTaskDao.merge(task);
				
				TaskExecutionWebServer.deleteTask(taskId, "RiskEvent");
				// 给各个部门负责人生成待办
				List<ReportTask> tasklist = new ArrayList<ReportTask>();
				for(int i = 0;i < leaderArray.size(); i++) {
					
					JSONObject job = leaderArray.getJSONObject(i);  // 遍历jsonarray数组，把每一个对象转成 json对象
					
					String depId = job.getString("depid");
					if(depIdList.contains(depId) && !"0".equals(task.getRetState())) continue;
					
					ReportTask rt = new ReportTask();
					
					rt.setRetTaskId(GenerateSequenceUtil.generateTaskId());
					rt.setRetDepId(job.getString("depid"));
					rt.setRetDepName(job.getString("depname"));
					rt.setRetFlowId(flowId);
					rt.setRetFlowStatus(flowStatus);
					rt.setRetReportId(reportId);
					rt.setRetReportName(reportname);
					rt.setRetState("0");//0表示未完成，1表示已完成
					rt.setRetUserId(job.getString("userid"));
					rt.setRetUserName(job.getString("username"));
					rt.setRetLastTime(df.format(new Date()));			//设置报告的最后的处理时间(目前只对未处理的任务有效)
					rt.setRetPreTaskId(taskId);
					rt.setRetFormId(reportId);
					rt.setRetModifyFlag(0);
					tasklist.add(rt);
					
					
					//调用中南电力设计院给部门负责人生成待办
					String TaskId = rt.getRetTaskId();
					String TaskApp = "RiskEvent";
					String TaskUrl = "default/loginSingleSystemAction?userid=" + job.getString("userid");
					String TaskTitle = "待接收风险报告" + reportname;
					String ReceiverId = job.getString("userid");
					String ReceiverName = job.getString("username");
					String ReceiverDepName = job.getString("depname");
					String ReceiveTime = df.format(new Date());
					String CreatorName = session.get("username").toString();
					String CreatorDepName = session.get("userdep").toString();
					String PreUserName = session.get("username").toString();
					String PreDepName = session.get("userdep").toString();
					TaskExecutionWebServer.addTask(TaskId, TaskApp, TaskUrl, TaskTitle, ReceiverId, ReceiverName, ReceiverDepName, ReceiveTime, 
							CreatorName, CreatorDepName, PreUserName, PreDepName);
					
				}
				reportTaskDao.insertBatch(tasklist);
				
				//在启动流程发送之后需要新增一条ReportTask记录,表示汇总审批流程
				
				//插入之前先要检查是否已经存在
				List<ReportTask> taskList = reportTaskDao.getTaskList(reportId, "HZBGSP");
				if(null != taskList && taskList.size() > 0) {
					
					//如果存在，则说明是启动流程发起之后进行修改
					
					
				}
				else {
					ReportTask nextFlowTask = new ReportTask();
					nextFlowTask.setRetTaskId(GenerateSequenceUtil.generateTaskId());
					nextFlowTask.setRetReportId(reportId);
					nextFlowTask.setRetReportName(reportname);
					nextFlowTask.setRetFlowId("HZBGSP");
					nextFlowTask.setRetFlowStatus("0");
					
					nextFlowTask.setRetDepId("QYFZ");
					//根据部门id查询对应的部门名称
					String depName = departmentDao.getdepname("QYFZ");
					nextFlowTask.setRetDepName(depName);
					nextFlowTask.setRetLastTime(df.format(new Date()));
					nextFlowTask.setRetState("0");
					nextFlowTask.setRetModifyFlag(0);
					reportTaskDao.save(nextFlowTask);
					
				}
				
				//更新formInfo表
				formInfoDao.updateFormState(reportId, flowStatus, reportFlowRuleDao.getFlowExplain(flowId, flowStatus));
				formInfoDao.updateFormLastDate(reportId, df.format(new Date()));
				
				out.print("commit");
				out.flush();
				return;
			}
			out.print("save");
			out.flush();
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	//修改报告
	@SuppressWarnings("unchecked")
	public String updateRiskReport() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String reportId = request.getParameter("reportId");
		String taskId = request.getParameter("taskId");
		
		ReportRisk report = reportRiskDao.findById(reportId);
		
		String Idlist = "";
		String leaderIds = "";
		List<ReportRiskDep> rrdList = reportRiskDepDao.findByProperty("rrdReportId", reportId);
		
		for(ReportRiskDep rrd : rrdList) {
			Idlist += rrd.getRrdDepId() + "@";
			leaderIds += rrd.getRrdChargeId() + "@";
		}
		
		request.setAttribute("Idlist", Idlist);
		request.setAttribute("leaderIds", leaderIds);
		request.setAttribute("report", report);
		request.setAttribute("taskId", taskId);
		request.setAttribute("reportname", report.getRerReportName());
		
		List<ReportRiskFile> files = reportRiskFileDao.findByProperty("fileReportId", reportId);
		//调整files的位置（按创建时间由近到远排序）
		Collections.sort(files,new Comparator<ReportRiskFile>(){

			@Override
			public int compare(ReportRiskFile o1, ReportRiskFile o2) {
				
				return o1.getFileDate().compareTo(o2.getFileDate());
			}
			
		});
		Collections.reverse(files);
		request.setAttribute("filelist", files);
		return "success";
	}
	
	//删除报告
	public void delRiskReport() {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			String reportId = request.getParameter("reportId");
			ReportRisk report = reportRiskDao.findById(reportId);
			reportRiskDao.delete(report);
			reportRiskDepDao.delReportRiskDepByReportId(reportId);
			reportTaskDao.delReportTaskByReportId(reportId);
			reportRiskFileDao.deleteByReportId(reportId);
			out.print("success");
			out.flush();
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	// 转到接收报告页面
	@SuppressWarnings("unchecked")
	public String receiveRiskReport() {
		
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
		//将files按照时间顺序排序
		Collections.sort(files, new Comparator<ReportRiskFile>(){

			@Override
			public int compare(ReportRiskFile o1, ReportRiskFile o2) {
				return o1.getFileDate().compareTo(o2.getFileDate());
			}
			
		});
		Collections.reverse(files);
		
		request.setAttribute("filelist", files);
		
		return "receiveReport";
	}
	
	
	// 接收报告（需要将接收报告的任务设置为已完成)
	@SuppressWarnings("unchecked")
	public void doReceiveReport() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		String reportId = request.getParameter("reportId");
		String taskId = request.getParameter("taskId");
		
		//ReportRisk report = reportRiskDao.findById(reportId);
		//String promoterId = report.getRerPromoterId();
		
		//判断任务是否已经被撤回或者被删除
		ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
		if(task == null) {
			
			out.write("fail");
			out.flush();
			return;		//表示报告已经被撤回
		}
		
		// 尽快更新ReportTask的记录
		task.setRetState("1");			//表示已处理
		reportTaskDao.merge(task);
		
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		String userId = session.get("userid").toString();
		
		ReportRiskDep riskDep = null;
		List<ReportRiskDep> rrdList = reportRiskDepDao.findByProperty("rrdReportId", reportId);
		for(ReportRiskDep rrd : rrdList) {
			if(rrd.getRrdChargeId().equals(userId)) {
				riskDep = rrd;
			}
		}
		
		// 更新ReportRiskDep表
		String nextStatus = reportFlowRuleDao.getNextStatus("BGBXQD", task.getRetFlowStatus());
		riskDep.setRrdStatus(nextStatus);
		reportRiskDepDao.merge(riskDep);
		
		// 更新ReportTask的记录
		task.setRetState("1");			//表示已处理
		task.setRetNextStatus(nextStatus);
		task.setRetProcessTime(df.format(new Date()));
		task.setRetModifyFlag(1);
		reportTaskDao.merge(task);
		
		// 删除中南电力设计院部门负责人的待办
		TaskExecutionWebServer.deleteTask(taskId, "RiskEvent");
		
		// 更新ReportRisk表中接收报告的数目
		reportRiskDao.updateReceiveNum(reportId);
		
		// 如果接收报告的数目跟发送的部门的数目一样,那就表示第一个流程走完了
		String flowStatus = "*";
		for(ReportRiskDep rrd : rrdList) {
			if(!rrd.getRrdStatus().equals("*")) {
				flowStatus = rrd.getRrdStatus();
				break;
			}
		}
		if(flowStatus.equals("*")) {
			formInfoDao.updateFormState(reportId, flowStatus, "已完成");
		}
		formInfoDao.updateFormLastDate(reportId, df.format(new Date()));
		
		
		
		// 原本是给部门的所有员工生成待办(需求经过修改之后这里需要生成一个没有处理人的待办，便于员工查询需要编写的报告)
		ReportTask newTask = new ReportTask();
		newTask.setRetTaskId(GenerateSequenceUtil.generateTaskId());
		newTask.setRetDepId(session.get("userdepid").toString());
		newTask.setRetDepName(session.get("userdep").toString());
		newTask.setRetFlowId("BMBGTJ");
		newTask.setRetFlowStatus("0");			//表示报告未编写
		newTask.setRetReportId(reportId);
		newTask.setRetReportName(task.getRetReportName());
		newTask.setRetState("0");//0表示未完成，1表示已完成
		//newTask.setRetUserId(user.getUserId());
		//newTask.setRetUserName(user.getUserName());
		newTask.setRetLastTime(df.format(new Date()));
		newTask.setRetModifyFlag(0);
		//newTask.setRetPreTaskId(task.getRetTaskId());
		reportTaskDao.save(newTask);
		//调用中南电力设计院的接口给报告报告编写人生成待办(需求修改之后这里不用调用中南电力设计院的接口生成待办)
		
		out.write("success");
		out.flush();
	}
	
	//上传文件
	public String uploadFile() {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			Map<?, ?> session = ServletActionContext.getContext().getSession();
			String uploader = session.get("userid").toString();
			String uploadername = session.get("username").toString();
			String reportId = request.getParameter("reportId");
			
			ArrayList<ReportRiskFile> files = new ArrayList<ReportRiskFile>();
			String date = df.format(new Date());
			JSONArray jsonArray = JSONArray.fromObject(FileUtil.uploadFile(request, "\\WEB-INF\\upload"));
			
			for(int i = 0;i < jsonArray.size(); i++) {
				String str = jsonArray.getString(i);
				JSONObject obj = JSONObject.fromObject(str);
				
				ReportRiskFile file = new ReportRiskFile();
				file.setFileId(GenerateSequenceUtil.newGenerateFileId());
				file.setFileDate(date);
				file.setFileFilename(obj.getString("filename"));
				file.setFileFilepath(obj.getString("filepath"));
				file.setFileUploader(uploader);
				file.setFileUploadername(uploadername);
				file.setFileReportId(reportId);
				files.add(file);
			}
			reportRiskFileDao.addFiles(files);
			
			JSONArray fileArray = new JSONArray();
			for(ReportRiskFile file : files) {
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
	public void downloadFile() {
		
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			
			String fileId = request.getParameter("fileId");
			ReportRiskFile file = reportRiskFileDao.findById(fileId);
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
		ReportRiskFile file = reportRiskFileDao.findById(fileId);
		reportRiskFileDao.delete(file);
	}
	
	//报告未发送取消了操作
	public void cancelAction() {
		
		HttpServletRequest request = ServletActionContext.getRequest();

		String taskId = request.getParameter("taskId");
		String reportId = request.getParameter("reportId");
		
		// 判断给中南电力设计院的待办接口是否需要进行调用
		ReportRisk report = reportRiskDao.getReportRiskByReportId(reportId);
		Integer cancelnum = report.getRerCancelNum();
		//如果为空需要新增待办
		if(cancelnum == null) {
			cancelnum = 1;
		}
		//如果为0则需要更新待办
		if(cancelnum.intValue() == 0) {
			cancelnum += 1;
		}
		report.setRerCancelNum(cancelnum);
		reportRiskDao.merge(report);
		//给报告发起人生成一个待办(或者修改待办)
		ReportTask specificTask = reportTaskDao.getReportTaskByTaskId(taskId);
		if(specificTask != null) {
			
			specificTask.setRetLastTime(df.format(new Date()));

			reportTaskDao.merge(specificTask);
			
		}
		
		
	}
	
	
	public String execute() throws UnsupportedEncodingException {
		
		//为下拉框准备数据，即填充reportInputList数据
		//在reprotTask中选择已发送的报告
/*		List<String> sendedReportIdList = reportTaskDao.getSendedRepIdList();
		
		List<ReportRisk> sendedReportList = reportRiskDao.getReportRiskInList(sendedReportIdList);
		
		request.setAttribute("reportInputList", sendedReportList);*/
		//System.out.println("执行execute方法");
		HttpServletRequest request = ServletActionContext.getRequest();
		String reportname = request.getParameter("reportname");
		if(reportname != null) {
			reportname = URLDecoder.decode(reportname,"UTF-8");
		}
		
		request.setAttribute("reportname", reportname);
		return "success";
	}
	
	/*---------------------------------------------------私有方法------------------------------------*/
	
	//第一次保存
	private TwoTuple<String,String> firstSave(RiskReportForm reportform) {
		
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		String flowId = "BGBXQD";
		
		ReportRisk report = new ReportRisk();
		String reportId = GenerateSequenceUtil.generateSequenceNo();
		report.setRerReportId(reportId);
		report.setRerReportName(reportform.getReportname());
		report.setRerPromoterId(session.get("userid").toString());
		report.setRerPromoterName(session.get("username").toString());
		report.setRerAnnual(reportform.getAnnual());
		report.setRerRemark(reportform.getReportremark());
		reportRiskDao.save(report);
		
		// 新增一个已经处理的任务
		ReportTask reporttask = new ReportTask();
		String donetaskId = GenerateSequenceUtil.generateTaskId();
		reporttask.setRetTaskId(donetaskId);
		reporttask.setRetDepId(session.get("userdepid").toString());
		reporttask.setRetDepName(session.get("userdep").toString());
		reporttask.setRetFlowId(flowId);
		reporttask.setRetFlowStatus("0");
		reporttask.setRetNextStatus(reportFlowRuleDao.getNextStatus(flowId, "0"));
		reporttask.setRetProcessTime(df.format(new Date()));
		reporttask.setRetReportId(reportId);
		reporttask.setRetReportName(reportform.getReportname());
		reporttask.setRetState("1");		//0表示未完成，1表示已完成
		reporttask.setRetUserId(session.get("userid").toString());
		reporttask.setRetUserName(session.get("username").toString());
		reporttask.setRetLastTime(df.format(new Date()));
		reporttask.setRetFormId(reportId);
		reporttask.setRetModifyFlag(1);
		reportTaskDao.save(reporttask);
		
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
		task.setRetFormId(reportId);
		task.setRetPreTaskId(donetaskId);
		task.setRetModifyFlag(1);
		reportTaskDao.save(task);
		
		//在formInfo表中新增一条记录
		ReportFormInfo formInfo = new ReportFormInfo();
		formInfo.setFiFlowId(flowId);
		formInfo.setFiFlowStatus(task.getRetFlowStatus());
		formInfo.setFiFormId(reportId);
		formInfo.setFiWriterDepId(session.get("userdepid").toString());
		formInfo.setFiWriterDepName(session.get("userdep").toString());
		formInfo.setFiWriterId(session.get("userid").toString());
		formInfo.setFiWriterName(session.get("username").toString());
		formInfo.setFiStatements(reportFlowRuleDao.getFlowExplain(flowId, task.getRetFlowStatus()));
		formInfo.setFiLastdate(df.format(new Date()));
		formInfoDao.save(formInfo);
		
		return new TwoTuple<String,String>(reportId,taskId);
	}
	
}
