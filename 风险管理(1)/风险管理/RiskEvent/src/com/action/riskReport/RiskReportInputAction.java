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
	
	// ?????????????????????????????????
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

	// ????????????????????????????????????
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
				out.print("fail");	//????????????????????????
				out.flush();
				return;
			}
			out.print("success");
			out.flush();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//???????????????????????????????????????
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
	
	//???????????????
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
	//?????????????????? add by tyq
    public String build() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			RiskReportForm reportform = WebUtil.handleRequest(request, RiskReportForm.class);
			String Idlist = reportform.getIdlist();
			String leaderIds = reportform.getLeaderIds();
			
			// ??????reportform?????????????????????report??????
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
			
			// ?????????????????????????????????
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
			reporttask.setRetState("1");		//0??????????????????1???????????????
			reporttask.setRetUserId(session.get("userid").toString());
			reporttask.setRetUserName(session.get("username").toString());
			reporttask.setRetLastTime(df.format(new Date()));
			reporttask.setRetFormId(reportId);
			reporttask.setRetModifyFlag(1);
			reportTaskDao.save(reporttask);
			
			//?????????????????????????????????
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
			
			//???formInfo????????????????????????
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
			
			// ??????task?????????????????????
			//reportTaskDao.updateReportname(reportId, reportform.getReportname());
			//
			// ????????????????????????
			//formInfoDao.updateFormLastDate(reportId, df.format(new Date()));
			
			//???selectDepartment.jsp????????????
			request.setAttribute("Idlist", Idlist);
			request.setAttribute("leaderIds", leaderIds);
			request.setAttribute("taskId", taskId);
			request.setAttribute("reportId", reportId);
			request.setAttribute("reportname", reportform.getReportname());
			
			List<Department> deplist = departmentDao.findAll();
			request.setAttribute("deplist", deplist);
			
			// ????????????????????????????????????????????????
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
	//??????????????????(??????????????????)
	public String saveBasicInfo() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			RiskReportForm reportform = WebUtil.handleRequest(request, RiskReportForm.class);
			String Idlist = reportform.getIdlist();
			String leaderIds = reportform.getLeaderIds();
			
			// ??????reportform?????????????????????report??????
			String reportId = reportform.getReportId();
			String taskId = reportform.getTaskId();
			ReportRisk report = reportRiskDao.getReportRiskByReportId(reportId);
						
			report.setRerReportName(reportform.getReportname());
			report.setRerAnnual(reportform.getAnnual());
			report.setRerRemark(reportform.getReportremark());
			reportRiskDao.merge(report);
			
			// ??????task?????????????????????
			reportTaskDao.updateReportname(reportId, reportform.getReportname());
			
			// ????????????????????????
			formInfoDao.updateFormLastDate(reportId, df.format(new Date()));
			
			//???selectDepartment.jsp????????????
			request.setAttribute("Idlist", Idlist);
			request.setAttribute("leaderIds", leaderIds);
			request.setAttribute("taskId", taskId);
			request.setAttribute("reportId", reportId);
			request.setAttribute("reportname", reportform.getReportname());
			
			List<Department> deplist = departmentDao.findAll();
			request.setAttribute("deplist", deplist);
			
			// ????????????????????????????????????????????????
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
	
	// ??????????????????????????????
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
			
			//????????????????????????Id??????
			List<String> depIdList = new ArrayList<String>();
			
			//System.out.println(leaderArray.size());
			if(null == taskId || "".equals(taskId)) {   //??????????????????????????????
				List<ReportRiskDep> rrdList = reportRiskDepDao.getReportRiskDepByReportId(reportId);
				taskId = reportTaskDao.getReportTaskListByReportId(reportId).get(1).getRetTaskId();
				for(int i = 0; i < rrdList.size(); i++) {
					depIdList.add(rrdList.get(i).getRrdDepId());   //?????????????????????
				}
			}
			
			
			ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
			String flowStatus = task.getRetFlowStatus();
			String flowId = task.getRetFlowId();
			if(operation.equals("commit")) {
				flowStatus = reportFlowRuleDao.getNextStatus(flowId, flowStatus);
			}
			
			// ??????ReportRiskDep???
			List<ReportRiskDep> rdlist = new ArrayList<ReportRiskDep>();
			
			// ?????????????????????????????????????????????
			if("0".equals(task.getRetState())) {
				reportRiskDepDao.deleteByReportId(reportId);			//?????????
			}
			
			JSONArray leaderArray = JSONArray.fromObject(leaderlist);
			if(leaderArray.size() > 0) {
				
				for(int i = 0;i < leaderArray.size(); i++) {
					
					JSONObject job = leaderArray.getJSONObject(i);  // ??????jsonarray????????????????????????????????? json??????
					
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
				
				// ??????ReportRisk???
				ReportRisk report = reportRiskDao.getReportRiskByReportId(reportId);
				report.setRerDepNum(leaderArray.size());
				report.setRerDoneNum(0);
				report.setRerDate(df.format(new Date()));		//??????????????????
				//report.setRerCancelNum(0);
				reportRiskDao.merge(report);
				
				// ??????????????????????????????????????????????????????????????????????????????????????????
				task.setRetNextStatus(flowStatus);
				task.setRetProcessTime(df.format(new Date()));
				task.setRetState("1");		//0??????????????????1???????????????
				reportTaskDao.merge(task);
				
				TaskExecutionWebServer.deleteTask(taskId, "RiskEvent");
				// ????????????????????????????????????
				List<ReportTask> tasklist = new ArrayList<ReportTask>();
				for(int i = 0;i < leaderArray.size(); i++) {
					
					JSONObject job = leaderArray.getJSONObject(i);  // ??????jsonarray????????????????????????????????? json??????
					
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
					rt.setRetState("0");//0??????????????????1???????????????
					rt.setRetUserId(job.getString("userid"));
					rt.setRetUserName(job.getString("username"));
					rt.setRetLastTime(df.format(new Date()));			//????????????????????????????????????(????????????????????????????????????)
					rt.setRetPreTaskId(taskId);
					rt.setRetFormId(reportId);
					rt.setRetModifyFlag(0);
					tasklist.add(rt);
					
					
					//?????????????????????????????????????????????????????????
					String TaskId = rt.getRetTaskId();
					String TaskApp = "RiskEvent";
					String TaskUrl = "default/loginSingleSystemAction?userid=" + job.getString("userid");
					String TaskTitle = "?????????????????????" + reportname;
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
				
				//?????????????????????????????????????????????ReportTask??????,????????????????????????
				
				//??????????????????????????????????????????
				List<ReportTask> taskList = reportTaskDao.getTaskList(reportId, "HZBGSP");
				if(null != taskList && taskList.size() > 0) {
					
					//???????????????????????????????????????????????????????????????
					
					
				}
				else {
					ReportTask nextFlowTask = new ReportTask();
					nextFlowTask.setRetTaskId(GenerateSequenceUtil.generateTaskId());
					nextFlowTask.setRetReportId(reportId);
					nextFlowTask.setRetReportName(reportname);
					nextFlowTask.setRetFlowId("HZBGSP");
					nextFlowTask.setRetFlowStatus("0");
					
					nextFlowTask.setRetDepId("QYFZ");
					//????????????id???????????????????????????
					String depName = departmentDao.getdepname("QYFZ");
					nextFlowTask.setRetDepName(depName);
					nextFlowTask.setRetLastTime(df.format(new Date()));
					nextFlowTask.setRetState("0");
					nextFlowTask.setRetModifyFlag(0);
					reportTaskDao.save(nextFlowTask);
					
				}
				
				//??????formInfo???
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
	
	//????????????
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
		//??????files????????????????????????????????????????????????
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
	
	//????????????
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
	
	
	// ????????????????????????
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
		//???files????????????????????????
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
	
	
	// ???????????????????????????????????????????????????????????????)
	@SuppressWarnings("unchecked")
	public void doReceiveReport() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		String reportId = request.getParameter("reportId");
		String taskId = request.getParameter("taskId");
		
		//ReportRisk report = reportRiskDao.findById(reportId);
		//String promoterId = report.getRerPromoterId();
		
		//????????????????????????????????????????????????
		ReportTask task = reportTaskDao.getReportTaskByTaskId(taskId);
		if(task == null) {
			
			out.write("fail");
			out.flush();
			return;		//???????????????????????????
		}
		
		// ????????????ReportTask?????????
		task.setRetState("1");			//???????????????
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
		
		// ??????ReportRiskDep???
		String nextStatus = reportFlowRuleDao.getNextStatus("BGBXQD", task.getRetFlowStatus());
		riskDep.setRrdStatus(nextStatus);
		reportRiskDepDao.merge(riskDep);
		
		// ??????ReportTask?????????
		task.setRetState("1");			//???????????????
		task.setRetNextStatus(nextStatus);
		task.setRetProcessTime(df.format(new Date()));
		task.setRetModifyFlag(1);
		reportTaskDao.merge(task);
		
		// ???????????????????????????????????????????????????
		TaskExecutionWebServer.deleteTask(taskId, "RiskEvent");
		
		// ??????ReportRisk???????????????????????????
		reportRiskDao.updateReceiveNum(reportId);
		
		// ????????????????????????????????????????????????????????????,????????????????????????????????????
		String flowStatus = "*";
		for(ReportRiskDep rrd : rrdList) {
			if(!rrd.getRrdStatus().equals("*")) {
				flowStatus = rrd.getRrdStatus();
				break;
			}
		}
		if(flowStatus.equals("*")) {
			formInfoDao.updateFormState(reportId, flowStatus, "?????????");
		}
		formInfoDao.updateFormLastDate(reportId, df.format(new Date()));
		
		
		
		// ?????????????????????????????????????????????(??????????????????????????????????????????????????????????????????????????????????????????????????????????????????)
		ReportTask newTask = new ReportTask();
		newTask.setRetTaskId(GenerateSequenceUtil.generateTaskId());
		newTask.setRetDepId(session.get("userdepid").toString());
		newTask.setRetDepName(session.get("userdep").toString());
		newTask.setRetFlowId("BMBGTJ");
		newTask.setRetFlowStatus("0");			//?????????????????????
		newTask.setRetReportId(reportId);
		newTask.setRetReportName(task.getRetReportName());
		newTask.setRetState("0");//0??????????????????1???????????????
		//newTask.setRetUserId(user.getUserId());
		//newTask.setRetUserName(user.getUserName());
		newTask.setRetLastTime(df.format(new Date()));
		newTask.setRetModifyFlag(0);
		//newTask.setRetPreTaskId(task.getRetTaskId());
		reportTaskDao.save(newTask);
		//????????????????????????????????????????????????????????????????????????(??????????????????????????????????????????????????????????????????????????????)
		
		out.write("success");
		out.flush();
	}
	
	//????????????
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
	
	//??????????????????
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
			throw new RuntimeException("??????????????????");
		}
		
	}
	
	//??????????????????
	public void deleteFile() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String fileId = request.getParameter("fileId");
		ReportRiskFile file = reportRiskFileDao.findById(fileId);
		reportRiskFileDao.delete(file);
	}
	
	//??????????????????????????????
	public void cancelAction() {
		
		HttpServletRequest request = ServletActionContext.getRequest();

		String taskId = request.getParameter("taskId");
		String reportId = request.getParameter("reportId");
		
		// ?????????????????????????????????????????????????????????????????????
		ReportRisk report = reportRiskDao.getReportRiskByReportId(reportId);
		Integer cancelnum = report.getRerCancelNum();
		//??????????????????????????????
		if(cancelnum == null) {
			cancelnum = 1;
		}
		//?????????0?????????????????????
		if(cancelnum.intValue() == 0) {
			cancelnum += 1;
		}
		report.setRerCancelNum(cancelnum);
		reportRiskDao.merge(report);
		//????????????????????????????????????(??????????????????)
		ReportTask specificTask = reportTaskDao.getReportTaskByTaskId(taskId);
		if(specificTask != null) {
			
			specificTask.setRetLastTime(df.format(new Date()));

			reportTaskDao.merge(specificTask);
			
		}
		
		
	}
	
	
	public String execute() throws UnsupportedEncodingException {
		
		//????????????????????????????????????reportInputList??????
		//???reprotTask???????????????????????????
/*		List<String> sendedReportIdList = reportTaskDao.getSendedRepIdList();
		
		List<ReportRisk> sendedReportList = reportRiskDao.getReportRiskInList(sendedReportIdList);
		
		request.setAttribute("reportInputList", sendedReportList);*/
		//System.out.println("??????execute??????");
		HttpServletRequest request = ServletActionContext.getRequest();
		String reportname = request.getParameter("reportname");
		if(reportname != null) {
			reportname = URLDecoder.decode(reportname,"UTF-8");
		}
		
		request.setAttribute("reportname", reportname);
		return "success";
	}
	
	/*---------------------------------------------------????????????------------------------------------*/
	
	//???????????????
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
		
		// ?????????????????????????????????
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
		reporttask.setRetState("1");		//0??????????????????1???????????????
		reporttask.setRetUserId(session.get("userid").toString());
		reporttask.setRetUserName(session.get("username").toString());
		reporttask.setRetLastTime(df.format(new Date()));
		reporttask.setRetFormId(reportId);
		reporttask.setRetModifyFlag(1);
		reportTaskDao.save(reporttask);
		
		//?????????????????????????????????
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
		
		//???formInfo????????????????????????
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
