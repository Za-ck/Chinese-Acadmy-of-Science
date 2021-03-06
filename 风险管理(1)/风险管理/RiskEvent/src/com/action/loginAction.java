package com.action;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.DepartmentDAO;
import com.dao.ReportMessageDAO;
import com.dao.RiskEventDAO;
import com.dao.SystemRoleDAO;
import com.dao.UsersDAO;
import com.dao.UsersFunctionDAO;
import com.model.Department;
import com.model.RiskEvent;
import com.model.SystemRole;
import com.model.Users;
import com.model.UsersFunction;
import com.opensymphony.xwork2.Action;

public class loginAction implements Action {

	private String userid;
	private String password;
	private String exitFlag = "";

	private UsersDAO userdao;
	private UsersFunctionDAO functiondao;
	private RiskEventDAO riskEventDao;
	private SystemRoleDAO systemRoleDao;
	private DepartmentDAO departmentDao;
	//private ReportTaskRiskViewDAO reportTaskRiskViewDao;
	private ReportMessageDAO reportMessageDao;
	
	private String warnremind;

//	public ReportTaskRiskViewDAO getReportTaskRiskViewDao() {
//		return reportTaskRiskViewDao;
//	}
//
//	public void setReportTaskRiskViewDao(ReportTaskRiskViewDAO reportTaskRiskViewDao) {
//		this.reportTaskRiskViewDao = reportTaskRiskViewDao;
//	}

	public String getWarnremind() {
		return warnremind;
	}

	public void setWarnremind(String warnremind) {
		this.warnremind = warnremind;
	}
	
	public RiskEventDAO getRiskEventDao() {
		return riskEventDao;
	}

	public void setRiskEventDao(RiskEventDAO riskEventDao) {
		this.riskEventDao = riskEventDao;
	}

	public ReportMessageDAO getReportMessageDao() {
		return reportMessageDao;
	}

	public void setReportMessageDao(ReportMessageDAO reportMessageDao) {
		this.reportMessageDao = reportMessageDao;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public UsersFunctionDAO getFunctiondao() {
		return functiondao;
	}

	public void setFunctiondao(UsersFunctionDAO functiondao) {
		this.functiondao = functiondao;
	}

	public UsersDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UsersDAO userdao) {
		this.userdao = userdao;
	}
	

	public SystemRoleDAO getSystemRoleDao() {
		return systemRoleDao;
	}

	public void setSystemRoleDao(SystemRoleDAO systemRoleDao) {
		this.systemRoleDao = systemRoleDao;
	}

	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}
	
	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getExitFlag() {
		return exitFlag;
	}

	public void setExitFlag(String exitFlag) {
		this.exitFlag = exitFlag;
	}

	// ??????
	public String execute() throws Exception {
		return "success";
	}

	// ????????????
	public String backMenu() {
		//System.out.println("????????????");
		return "success";
	}

	// ????????????
	public String exitSystem() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.invalidate();// ??????session
		// System.out.println("????????????");
		this.setExitFlag("exit");
		return "success";
	}

	// ????????????
	@SuppressWarnings("unchecked")
	public String login() {
		// System.out.println("userid="+this.userid);
		if (this.userid == null || this.userid.equals("")) {
			this.setExitFlag("exit");
			return "fail";
		}

		Users user = userdao.findById(this.userid);
		if (user == null) {
			this.setExitFlag("exit");
			return "fail";// ?????????????????????????????????????????????
		} 
		else {
			if (user.getUserRname().equals("0")) {
				this.setExitFlag("exit");
				return "fail";// ?????????????????????????????????????????????
			}
			if (user.getUserPassword().equals(this.password))// ??????????????????
			{
				Map session = ServletActionContext.getContext().getSession();
				session.put("loginUser", user);
				session.put("userid", this.userid);
				session.put("username", user.getUserName());
				Department dept = departmentDao.findById(user.getUserDep());
				if(dept != null) {
					//session.put("userdepid", user.getDepartment().getDepId());
					//session.put("userdep", user.getDepartment().getDepName());
					session.put("userdepid", dept.getDepId());
					session.put("userdep", dept.getDepName());
				}
				SystemRole sysRole = systemRoleDao.findById(user.getUserPosition());
				//2018.11.21?????????????????????????????????
				session.put("userposition", user.getUserPosition());
				if(sysRole != null) {
					//session.put("userrole", user.getSystemRole().getSrName());
					//session.put("role", user.getSystemRole().getSrreid());
					
					
					session.put("userrole", sysRole.getSrName());
					session.put("role", sysRole.getSrreid());
				}
				//List<RiskEvent> reList = new LinkedList<RiskEvent>();
				String remindString = "????????????";
//				reList = this.getRiskEventDao().findeventnum(this.userid);
//				int reportnum = reportTaskRiskViewDao.getUnprocessedReportSize("", this.userid, "", "");
				//int count = reportnum + reList.size();
				int count = reportMessageDao.getReportMessageSize("0", this.userid, "", "");
				if (count > 0)
					remindString = "??????" + count + "???????????????";
				session.put("remind", remindString);
				Calendar ca = Calendar.getInstance();
				int tempdate = ca.get(Calendar.DATE);
				if (tempdate > 25) {
					java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					// ???????????????????????????????????????
					cal.add(Calendar.DAY_OF_MONTH, -1);
					cal.set(cal.DATE, 30);
					String lastDate = df.format(cal.getTime());
					cal.set(cal.DATE, 1);
					String firstDate = df.format(cal.getTime());
					List<RiskEvent> tempList = new LinkedList<RiskEvent>();
					
					tempList = this.getRiskEventDao().findnumremind(dept.getDepId(), firstDate, lastDate);
					if (tempList.size() == 0 && sysRole.getSrreid().equals("0")) {
						session.put("inremind", "1");
					}
				}
				GetUsersPrimaryFunction();
				return "success";
			} else {
				this.setExitFlag("exit");
				return "fail";// ????????????
			}

		}
	}

	// ????????????????????????????????????????????????
	@SuppressWarnings("unchecked")
	public String loginSingleSystem() {

		if (this.userid == null || this.userid.equals("")) {
			this.setExitFlag("exit");
			return "fail";
		}

		Users user = userdao.findById(this.userid);
		if (user == null) {
			this.setExitFlag("exit");
			return "fail";// ?????????????????????????????????????????????
		} else {
			if (user.getUserRname().equals("0")) {
				this.setExitFlag("exit");
				return "fail";// ?????????????????????????????????????????????
			}

			Map session = ServletActionContext.getContext().getSession();
			session.put("loginUser", user);
			session.put("userid", this.userid);
			session.put("username", user.getUserName());
			Department dept = departmentDao.findById(user.getUserDep());
			if(dept != null) {
				//session.put("userdepid", user.getDepartment().getDepId());
				//session.put("userdep", user.getDepartment().getDepName());
				session.put("userdepid", dept.getDepId());
				session.put("userdep", dept.getDepName());
			}
			SystemRole sysRole = systemRoleDao.findById(user.getUserPosition());
			//2018.11.21?????????????????????????????????
			session.put("userposition", user.getUserPosition());
			if(sysRole != null) {
				//session.put("userrole", user.getSystemRole().getSrName());
				//session.put("role", user.getSystemRole().getSrreid());
				session.put("userrole", sysRole.getSrName());
				session.put("role", sysRole.getSrreid());
			}
			//List<RiskEvent> reList = new LinkedList<RiskEvent>();
			String remindString = "????????????";
			//reList = this.getRiskEventDao().findeventnum(this.userid);
			//int reportnum = reportTaskRiskViewDao.getUnprocessedReportSize("", this.userid, "", "");
			//int count = reportnum + reList.size();
			int count = reportMessageDao.getReportMessageSize("0", this.userid, "", "");
			if (count > 0)
				remindString = "??????" + count + "???????????????";
			if (count > 0)
				remindString = "??????" + count + "??????????????????";
			session.put("remind", remindString);
			Calendar ca = Calendar.getInstance();
			int tempdate = ca.get(Calendar.DATE);
			if (tempdate > 25) {
				java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
						"yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				// ???????????????????????????????????????
				cal.add(Calendar.DAY_OF_MONTH, -1);
				cal.set(cal.DATE, 30);
				String lastDate = df.format(cal.getTime());
				cal.set(cal.DATE, 1);
				String firstDate = df.format(cal.getTime());
				List<RiskEvent> tempList = new LinkedList<RiskEvent>();
				tempList = this.getRiskEventDao().findnumremind(
						dept.getDepId(), firstDate, lastDate);
				if (tempList.size() == 0
						&& sysRole.getSrreid().equals("0")) {
					session.put("inremind", "1");
				}
			}
			GetUsersPrimaryFunction();
			return "success";

		}
	}

	/*
	 * ??????????????????
	 */
	@SuppressWarnings("unchecked")
	public String GetUsersPrimaryFunction() {
		try {
			Users u = (Users) ServletActionContext.getRequest().getSession()
					.getAttribute("loginUser");
			//int userlevel = u.getSystemRole().getSrId();
			int userlevel = u.getUserPosition();
			List<UsersFunction> UFList = functiondao
					.findUsersFunction(userlevel);
			Map session = ServletActionContext.getContext().getSession();
			session.put("UFList", UFList);
		} catch (Exception e) {
			
			return "";
		}
		return "";
	}

}
