package com.action.riskQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.action.ExcelReportAction;
import com.dao.EventFileDAO;
import com.dao.FileManageDAO;
import com.dao.RiskEventQueryViewDAO;
import com.dao.RiskImpactDAO;
import com.dao.RiskManageDAO;
import com.model.EventFile;
import com.model.FileManage;
import com.model.RiskEventQueryView;
import com.model.RiskImpact;
import com.model.RiskManage;
import com.model.Users;

public class RiskEventdepQueryAction {
	private List<RiskEventQueryView> redepList;
	private RiskEventQueryViewDAO riskEventQueryViewDao;
	private String reventStr;		//风险事件编号的一部分
	private String stateId;			//风险事件的状态
	private int current_pagenum = 1;// 当前页码
	private int pageNum = 10;// 每页的显示数据记录数
	public String dateFrom = "";
	public String dateTo = "";
	private String actionName = "riskQuery/RiskEventdepQueryAction";
	private String updownflag = "";
	private String updownid = "";
	private String orderbys = "";
	private String operation;
	private RiskImpactDAO riskimpactDao;
	private RiskManageDAO riskmanageDao;
	private List<RiskEventQueryView> redepexportList;
	private List<EventFile> efList;
	private EventFileDAO eventFileDao;
	EventFile eventFile = new EventFile();
	private FileManageDAO fileManageDao;
	FileManage fileManage = new FileManage();

	public String getReventStr() {
		return reventStr;
	}

	public void setReventStr(String reventStr) {
		this.reventStr = reventStr;
	}

	public FileManageDAO getFileManageDao() {
		return fileManageDao;
	}

	public void setFileManageDao(FileManageDAO fileManageDao) {
		this.fileManageDao = fileManageDao;
	}

	public FileManage getFileManage() {
		return fileManage;
	}

	public void setFileManage(FileManage fileManage) {
		this.fileManage = fileManage;
	}

	public List<EventFile> getEfList() {
		return efList;
	}

	public void setEfList(List<EventFile> efList) {
		this.efList = efList;
	}

	public EventFileDAO getEventFileDao() {
		return eventFileDao;
	}

	public void setEventFileDao(EventFileDAO eventFileDao) {
		this.eventFileDao = eventFileDao;
	}

	public EventFile getEventFile() {
		return eventFile;
	}

	public void setEventFile(EventFile eventFile) {
		this.eventFile = eventFile;
	}

	public List<RiskEventQueryView> getRedepexportList() {
		return redepexportList;
	}

	public void setRedepexportList(List<RiskEventQueryView> redepexportList) {
		this.redepexportList = redepexportList;
	}

	public RiskImpactDAO getRiskimpactDao() {
		return riskimpactDao;
	}

	public void setRiskimpactDao(RiskImpactDAO riskimpactDao) {
		this.riskimpactDao = riskimpactDao;
	}

	public RiskManageDAO getRiskmanageDao() {
		return riskmanageDao;
	}

	public void setRiskmanageDao(RiskManageDAO riskmanageDao) {
		this.riskmanageDao = riskmanageDao;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public int getCurrent_pagenum() {
		return current_pagenum;
	}

	public void setCurrent_pagenum(int currentPagenum) {
		current_pagenum = currentPagenum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public List<RiskEventQueryView> getRedepList() {
		return redepList;
	}

	public void setRedepList(List<RiskEventQueryView> redepList) {
		this.redepList = redepList;
	}

	public RiskEventQueryViewDAO getRiskEventQueryViewDao() {
		return riskEventQueryViewDao;
	}

	public void setRiskEventQueryViewDao(
			RiskEventQueryViewDAO riskEventQueryViewDao) {
		this.riskEventQueryViewDao = riskEventQueryViewDao;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getUpdownflag() {
		return updownflag;
	}

	public void setUpdownflag(String updownflag) {
		this.updownflag = updownflag;
	}

	public String getUpdownid() {
		return updownid;
	}

	public void setUpdownid(String updownid) {
		this.updownid = updownid;
	}

	public String getOrderbys() {
		return orderbys;
	}

	public void setOrderbys(String orderbys) {
		this.orderbys = orderbys;
	}
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	// 用户只查看录入部门是本部门，或者归口部门是本部门的风险事件
	public String requerydep() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			String depName = session.getAttribute("userdep").toString();
			// 风险事件列表
			// 得到RiskEventQuery.jsp页面上的列表reqvvList
			this.redepList = new LinkedList<RiskEventQueryView>();
			this.redepList = this.getRiskEventQueryViewDao().finddepre(
					us.getUserDep(), depName, (current_pagenum - 1) * pageNum,
					pageNum, this.getOrderbys());

			// 导出报表数据
			// this.redepexportList = new LinkedList<RiskEventQueryView>();
			// this.redepexportList=this.getRiskEventQueryViewDao().finddepre(us.getUserDep(),depName);
			// getInfo(redepexportList,"查询条件为：本部门或归口部门为本部门的全部事件");

			ServletActionContext.getRequest().setAttribute("redepList",
					redepList);
			ServletActionContext.getRequest().getSession().setAttribute(
					"current_pagenum", current_pagenum);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		this.actionName = "riskQuery/RiskEventdepQueryAction";
		if (redepList.size() > 0)
			return "success";
		else
			return "fail";
	}

	public String redepadQuery() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			String depName = session.getAttribute("userdep").toString();
			// 风险事件列表
			// 得到RiskEventQuery.jsp页面上的列表reqvvList
			String datefromtem = transformDateFrom(this.getDateFrom());// 对dateFrom的四位年份进行改造,改造成"yyyy-MM-dd 00:00:00"格式
			String datetotem = transformDateTo(this.getDateTo());// 对dateTo的四位年份进行改造,改造成"yyyy-MM-dd 23:59:59"格式
			this.redepList = new LinkedList<RiskEventQueryView>();
			this.redepList = this.getRiskEventQueryViewDao().finddepadvanced(
					us.getUserDep(), depName, datefromtem, datetotem,this.getReventStr(),this.getStateId(),
					(current_pagenum - 1) * pageNum, pageNum,
					this.getOrderbys());
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+this.getStateId());
			// 导出报表的数据
			// this.redepexportList = new LinkedList<RiskEventQueryView>();
			// this.redepexportList
			// =this.getRiskEventQueryViewDao().finddepadvanced(us.getUserDep(),depName,datefromtem,datetotem);
			// getInfo(redepexportList,"录入时间为："+datefromtem+"至"+datetotem);

			ServletActionContext.getRequest().setAttribute("redepList",
					redepList);
			ServletActionContext.getRequest().getSession().setAttribute(
					"current_pagenum", current_pagenum);

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		this.actionName = "riskQuery/REadQueryAction";

		if (redepList.size() > 0)
			return "success";
		else
			return "fail";
	}

	// 对dateFrom的四位年份进行改造,改造成"yyyy-MM-dd HH:mm:ss"格式
	public void transformDateFrom() {
		if (this.getDateFrom().equals("")) {
			this.setDateFrom("1900-01-01 00:00:00");
		} else {
			this.setDateFrom(this.getDateFrom() + " 00:00:00");
		}
	}

	// 对dateTo的四位年份进行改造,改造成"yyyy-MM-dd HH:mm:ss"格式
	public void transformDateTo() {
		if (this.getDateTo().equals("")) {
			Date d = new Date();
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.setDateTo(f.format(d));
		} else {
			this.setDateTo(this.getDateTo() + " 23:59:59");
		}
	}

	// 对dateFrom的四位年份进行改造,改造成"yyyy-MM-dd HH:mm:ss"格式
	public String transformDateFrom(String temfrom) {
		if (temfrom.equals("")) {
			temfrom = "1900-01-01 00:00:00";
		} else {
			temfrom = temfrom + " 00:00:00";
		}
		return temfrom;
	}

	// 对dateTo的四位年份进行改造,改造成"yyyy-MM-dd HH:mm:ss"格式
	public String transformDateTo(String temto) {
		if (temto.equals("")) {
			Date d = new Date();
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			temto = f.format(d);
		} else {
			temto = temto + " 23:59:59";
		}
		return temto;
	}

	public String getStateName(String stateName, String actName) {
		String name = stateName;
		String act = actName;
		if ("*".equals(name)) {
			return "已发布";
		} else if ("0".equals(name)) {
			if ("0".equals(act)) {
				return "未修改";
			} else {
				return "未提交";
			}
		} else if ("0".equals(act)) {
			return "未通过";
		} else {
			if ("1".equals(name)) {
				return "已提交";
			} else {
				return "等待审核中";
			}
		}
	}

	private void getInfo(List<RiskEventQueryView> reList, String querycondition) {

		// 将数据存放在数组中，生成excel时获取
		String[][] dsarray = new String[reList.size()][31];

		for (int m = 0; m < reList.size(); m++) {
			dsarray[m][0] = reList.get(m).getId().getReId();// 风险事件编号
			dsarray[m][1] = reList.get(m).getRiskName();
			dsarray[m][2] = reList.get(m).getDepName();
			dsarray[m][3] = reList.get(m).getReinchargedep();
			dsarray[m][4] = getStateName(reList.get(m).getReState(), reList
					.get(m).getReAct());

			dsarray[m][5] = reList.get(m).getReDate();
			dsarray[m][6] = String.valueOf(reList.get(m).getRiAllvalue());

			RiskImpact rimpact = new RiskImpact();
			rimpact = this.riskimpactDao.findById(dsarray[m][0]);
			RiskManage rmanage = new RiskManage();
			rmanage = this.riskmanageDao.findById(dsarray[m][0]);
			dsarray[m][7] = rimpact.getRiKpi();
			dsarray[m][8] = rimpact.getRiProbability();
			dsarray[m][9] = rimpact.getRiBusarea();
			dsarray[m][10] = rimpact.getRiSource();

			this.efList = new LinkedList<EventFile>();
			this.efList = this.getEventFileDao().findByProperty("reId",
					dsarray[m][0]);
			int i = 0;
			String s = "";
			s = "涉及的管理规定文件：\n";
			Iterator it = this.getEfList().iterator();
			while (it.hasNext()) {

				eventFile = (EventFile) it.next();
				if (null != eventFile.getFileId() && !"".equals(eventFile.getFileId())) {
					fileManage = this.getFileManageDao().findById(eventFile.getFileId());// 本句出错
					i = i + 1;
					if(fileManage != null) {
						s = s + i + "、文件编号：《" + fileManage.getFileId() + "》，文件名称：《"
								+ fileManage.getFileName() + "》；\n";
					}
					else {
						s = s + i + "、文件编号：《" + eventFile.getFileId() + "》，文件名称："
								+ "找不到对应的文件名" + "；\n";
					}
					
				} else
					break;
			}
			s = s + "共" + this.getEfList().size() + "个文件。\n";

			dsarray[m][11] = s;
			dsarray[m][12] = rimpact.getRiInchargedep();

			dsarray[m][13] = rimpact.getRiFinance() + "；金额："
					+ String.valueOf(rimpact.getRiMoney());
			dsarray[m][14] = rimpact.getRiFame();
			dsarray[m][15] = rimpact.getRiLaw();
			dsarray[m][16] = rimpact.getRiClient();
			dsarray[m][17] = rimpact.getRiEmployee();
			dsarray[m][18] = rimpact.getRiOperation();
			dsarray[m][19] = rimpact.getRiSafe();

			dsarray[m][20] = rimpact.getRiFincriteria();
			dsarray[m][21] = rimpact.getRiFamecriteria();
			dsarray[m][22] = rimpact.getRiLawcriteria();
			dsarray[m][23] = rimpact.getRiClicriteria();
			dsarray[m][24] = rimpact.getRiEmpcriteria();
			dsarray[m][25] = rimpact.getRiOpecriteria();
			dsarray[m][26] = rimpact.getRiSafecriteria();

			dsarray[m][27] = rmanage.getRmChance();
			dsarray[m][28] = rmanage.getRmControl();
			dsarray[m][29] = "基本态度：" + rmanage.getRmStrategy() + ";应对措施："
					+ rmanage.getRmReply() + ";计划投入资源："
					+ rmanage.getRmPlanres() + ";时间计划："
					+ rmanage.getRmPlandate();
			dsarray[m][30] = rmanage.getRmWarnvalue();

		}

		// 数据存放在session中，便于导出excel
		Map session = ServletActionContext.getContext().getSession();
		session.put("exportDepEventQueryList", dsarray);
		session.put("depquerycondition", querycondition);
	}

	// 风险事件查询的结果报表导出
	@SuppressWarnings("unchecked")
	public String exportExcel() {
		
		HttpSession httpsession = ServletActionContext.getRequest().getSession();
		Users us = (Users) httpsession.getAttribute("loginUser");
		String depName = httpsession.getAttribute("userdep").toString();
		this.redepexportList = new LinkedList<RiskEventQueryView>();
		this.redepexportList = this.getRiskEventQueryViewDao().finddepre(us.getUserDep(), depName);
		getInfo(redepexportList, "查询条件为：全部事件");
		Map session = ServletActionContext.getContext().getSession();
		if (session.get("exportDepEventQueryList") == null)
			return "fail";
		else {
			String str = "风险事件查询报表导出--" + session.get("depquerycondition");
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("风险事件查询报表导出", "eventQueryTemplate",
							(String[][]) session.get("exportDepEventQueryList"),
							3, 0, str);// 3表示从第三行开始，4表示从第4列开始合并
			return "success";
		}
	}
}
