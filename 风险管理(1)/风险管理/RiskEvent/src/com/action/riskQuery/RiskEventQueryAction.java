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
import com.dao.DepartmentDAO;
import com.dao.EventFileDAO;
import com.dao.FileManageDAO;
import com.dao.RiskEventQueryViewDAO;
import com.dao.RiskImpactDAO;
import com.dao.RiskManageDAO;
import com.model.Department;
import com.model.EventFile;
import com.model.FileManage;
import com.model.RiskEventQueryView;
import com.model.RiskEventQueryViewWrapper;
import com.model.RiskImpact;
import com.model.RiskManage;

public class RiskEventQueryAction {
	private String reId; // 风险事件编号
	private String reventStr;		//风险事件编号的一部分
	private String stateId;			//风险事件的状态
	private String choosedId;	
	private String reIndep;// 录入部门
	private String riskDep;// 归口部门
	public String dateFrom = "";
	public String dateTo = "";
	private String operation;
	private List<RiskEventQueryView> reList;// 风险事件查询视图,导出报表的List
	private List<RiskEventQueryView> reList1;// 风险事件查询视图,导出报表的List1
	private List<Department> alldepList; // 录入部门
	private List<Department> riskdepList; // 责任部门
	private List<Department> depList;
	private List<RiskEventQueryView> reqvvList; // 风险事件查询视图
	private List<RiskEventQueryViewWrapper> wrapperList;
	private List<FileManage> fileList;
	private List<EventFile> efList;
	private DepartmentDAO departmentDao;
	private RiskEventQueryViewDAO riskEventQueryViewDao;
	private FileManageDAO fileManageDao;
	private EventFileDAO eventFileDao;
	private int current_pagenum = 1;// 当前页码
	private int pageNum = 10;// 每页的显示数据记录数
	private String yearBegin;
	private String yearEnd;
	private String actionName = "riskQuery/RiskEventQueryAction";
	private String updownflag = "";
	private String updownid = "";
	private String orderbys = "";

	private RiskImpactDAO riskimpactDao;
	private RiskManageDAO riskmanageDao;


	EventFile eventFile = new EventFile();
	FileManage fileManage = new FileManage();
	//private LinkedList<RiskEventQueryViewWrapper> wrapperList;

	
	public List<RiskEventQueryView> getReList1() {
		return reList1;
	}

	public void setReList1(List<RiskEventQueryView> reList1) {
		this.reList1 = reList1;
	}
	public EventFile getEventFile() {
		return eventFile;
	}

	public void setEventFile(EventFile eventFile) {
		this.eventFile = eventFile;
	}

	public FileManage getFileManage() {
		return fileManage;
	}

	public void setFileManage(FileManage fileManage) {
		this.fileManage = fileManage;
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

	public String getReIndep() {
		return reIndep;
	}

	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}

	public String getRiskDep() {
		return riskDep;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getChoosedId() {
		return choosedId;
	}

	public void setChoosedId(String choosedId) {
		this.choosedId = choosedId;
	}
	
	public String getReventStr() {
		return reventStr;
	}

	public void setReventStr(String reventStr) {
		this.reventStr = reventStr;
	}

	public void setRiskDep(String riskDep) {
		this.riskDep = riskDep;
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

	public List<RiskEventQueryView> getReList() {
		return reList;
	}

	public void setReList(List<RiskEventQueryView> reList) {
		this.reList = reList;
	}

	public String getYearBegin() {
		return yearBegin;
	}

	public void setYearBegin(String yearBegin) {
		this.yearBegin = yearBegin;
	}

	public String getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(String yearEnd) {
		this.yearEnd = yearEnd;
	}

	public List<Department> getAlldepList() {
		return alldepList;
	}

	public void setAlldepList(List<Department> alldepList) {
		this.alldepList = alldepList;
	}

	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public List<Department> getRiskdepList() {
		return riskdepList;
	}

	public void setRiskdepList(List<Department> riskdepList) {
		this.riskdepList = riskdepList;
	}

	public List<Department> getDepList() {
		return depList;
	}

	public void setDepList(List<Department> depList) {
		this.depList = depList;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public List<FileManage> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileManage> fileList) {
		this.fileList = fileList;
	}

	public List<EventFile> getEfList() {
		return efList;
	}

	public void setEfList(List<EventFile> efList) {
		this.efList = efList;
	}

	public FileManageDAO getFileManageDao() {
		return fileManageDao;
	}

	public void setFileManageDao(FileManageDAO fileManageDao) {
		this.fileManageDao = fileManageDao;
	}

	public EventFileDAO getEventFileDao() {
		return eventFileDao;
	}

	public void setEventFileDao(EventFileDAO eventFileDao) {
		this.eventFileDao = eventFileDao;
	}

	public RiskEventQueryViewDAO getRiskEventQueryViewDao() {
		return riskEventQueryViewDao;
	}

	public List<RiskEventQueryView> getReqvvList() {
		return reqvvList;
	}

	public void setReqvvList(List<RiskEventQueryView> reqvvList) {
		this.reqvvList = reqvvList;
	}
	
	public List<RiskEventQueryViewWrapper> getWrapperList() {
		return wrapperList;
	}

	public void setWrapperList(List<RiskEventQueryViewWrapper> wrapperList) {
		this.wrapperList = wrapperList;
	}

	public void setRiskEventQueryViewDao(
			RiskEventQueryViewDAO riskEventQueryViewDao) {
		this.riskEventQueryViewDao = riskEventQueryViewDao;
	}

	public String getReId() {
		return reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
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

	// 显示风险事件信息,得到最新reList,用于在RiskEventQuery.jsp表单中显示
	@SuppressWarnings("unchecked")
	public String reqManage() {
		try {
			// 录入部门名称下拉框
			Department dep1 = new Department();
			dep1.setDepId("none1");
			dep1.setDepName("--请选择--");
			dep1.setDepSort(0);
			alldepList = new LinkedList<Department>();
			alldepList.add(dep1);
			depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findAll();
			alldepList.addAll(depList);
			// 管理责任部门名称下拉框
//			Department dep2 = new Department();
//			dep2.setDepId("all1");
//			dep2.setDepName("本部门");
//			dep2.setDepSort(1);
			riskdepList = new LinkedList<Department>();
			riskdepList.add(dep1);
			//riskdepList.add(dep2);
			depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findByDepSort(1);
			riskdepList.addAll(depList);
			depList = this.getDepartmentDao().findByDepSort(2);
			riskdepList.addAll(depList);

			// 风险事件列表
			// 得到RiskEventQuery.jsp页面上的列表reqvvList
			this.reqvvList = new LinkedList<RiskEventQueryView>();
			this.reqvvList = this.getRiskEventQueryViewDao().findAll(
					(current_pagenum - 1) * pageNum, pageNum,
					this.getOrderbys());
			this.wrapperList=new LinkedList<RiskEventQueryViewWrapper>();
			//List<RiskEventQueryViewWrapper> wrapperList = new LinkedList<RiskEventQueryViewWrapper>();
			//	this.wrapperList=new LinkedList<RiskEventQueryViewWrapper>();
				for(RiskEventQueryView riskEventQueryView : reqvvList) {
					RiskEventQueryViewWrapper riskEventQueryViewWrapper = new RiskEventQueryViewWrapper();
					riskEventQueryViewWrapper.setId(riskEventQueryView.getId());
					riskEventQueryViewWrapper.setDepName(riskEventQueryView.getDepName());
					riskEventQueryViewWrapper.setRiskName(riskEventQueryView.getRiskName());
					riskEventQueryViewWrapper.setRmPlandate(riskEventQueryView.getRmPlandate());
					riskEventQueryViewWrapper.setRmDate(riskEventQueryView.getReDate());
					riskEventQueryViewWrapper.setReinchargedep(riskEventQueryView.getReinchargedep());
					riskEventQueryViewWrapper.setRiAllvalue(riskEventQueryView.getRiAllvalue());
					riskEventQueryViewWrapper.setIndepid(riskEventQueryView.getIndepid());
					riskEventQueryViewWrapper.setReState(riskEventQueryView.getReState());
					riskEventQueryViewWrapper.setReAct(riskEventQueryView.getReAct());
					riskEventQueryViewWrapper.setRiskId(riskEventQueryView.getRiskId());
					riskEventQueryViewWrapper.setReEventname(riskEventQueryView.getReEventname());
					wrapperList.add(riskEventQueryViewWrapper);
				}
				
			ServletActionContext.getRequest().setAttribute("wrapperList",
					wrapperList);
			ServletActionContext.getRequest().getSession().setAttribute(
					"current_pagenum", current_pagenum);
			
			
//			ServletActionContext.getRequest().getSession().setAttribute(
//					"pagecount", 2);
			
			this.setActionName("riskQuery/RiskEventQueryAction");
			this.setDateFrom("");
			this.setDateTo("");
			this.setReIndep("");
			this.setRiskDep("");
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		if (wrapperList.size() > 0)
			return "success";
		else
			return "fail";
	}

	// 本年度风险事件,没调用
	@SuppressWarnings("unchecked")
	public String reqCurrentYear() {
		try {
			// 录入部门名称下拉框
			Department dep3 = new Department();
			dep3.setDepId("none1");
			dep3.setDepName("--请选择--");
			dep3.setDepSort(0);
			alldepList = new LinkedList<Department>();
			alldepList.add(dep3);
			depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findAll();
			alldepList.addAll(depList);
			// 管理责任部门名称下拉框
			Department dep4 = new Department();
			dep4.setDepId("all1");
			dep4.setDepName("本部门");
			dep4.setDepSort(1);
			riskdepList = new LinkedList<Department>();
			riskdepList.add(dep3);
			riskdepList.add(dep4);
			depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findByDepSort(1);
			riskdepList.addAll(depList);
			depList = this.getDepartmentDao().findByDepSort(2);
			riskdepList.addAll(depList);
			// 风险事件列表
			Date d = new Date();
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			String today = f.format(d);
			this.setDateFrom(today.substring(0, 4) + "-01-01");
			this.setDateTo(today);
			this.reqvvList = new LinkedList<RiskEventQueryView>();
			this.reqvvList = this.getRiskEventQueryViewDao().reqCurrentYear(
					this.getDateFrom(), this.getDateTo(),
					(current_pagenum - 1) * pageNum, pageNum);

			ServletActionContext.getRequest().setAttribute("reqvvList",
					reqvvList);
			ServletActionContext.getRequest().getSession().setAttribute(
					"current_pagenum", current_pagenum);
			this.setActionName("riskQuery/REQCurrentYearAction");
			this.setDateFrom("");
			this.setDateTo("");
			this.setReIndep("");
			this.setRiskDep("");
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 条件查询
	@SuppressWarnings("unchecked")
	public String reqQuery() {
		try {
			// 录入部门名称下拉框
			Department dep3 = new Department();
			dep3.setDepId("none1");
			dep3.setDepName("--请选择--");
			dep3.setDepSort(0);
			alldepList = new LinkedList<Department>();
			alldepList.add(dep3);
			depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findAll();
			alldepList.addAll(depList);
			// 管理责任部门名称下拉框
			//Department dep4 = new Department();
			
			riskdepList = new LinkedList<Department>();
			riskdepList.add(dep3);
			//riskdepList.add(dep4);
			depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findByDepSort(1);
			riskdepList.addAll(depList);
			depList = this.getDepartmentDao().findByDepSort(2);
			riskdepList.addAll(depList);

			String datefromtem = transformDateFrom(this.getDateFrom());// 对dateFrom的四位年份进行改造,改造成"yyyy-MM-dd 00:00:00"格式
			System.out.println(datefromtem+"*******************************************");
			String datetotem = transformDateTo(this.getDateTo());// 对dateTo的四位年份进行改造,改造成"yyyy-MM-dd 23:59:59"格式
			this.reqvvList = new LinkedList<RiskEventQueryView>();
			this.reqvvList = this.getRiskEventQueryViewDao().reqQuery1(
					datefromtem, datetotem, this.getReIndep(),this.getChoosedId(),
					this.getRiskDep(), this.getReventStr(), this.getStateId(),this.getOrderbys());
			System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+this.getStateId());
			getInfo(reqvvList,"查询条件为：全部事件");
			this.reqvvList = this.getRiskEventQueryViewDao().reqQuery(
					datefromtem, datetotem, this.getReIndep(),this.getChoosedId(),
					this.getRiskDep(), this.getReventStr(), this.getStateId(),(current_pagenum - 1) * pageNum,
					pageNum, this.getOrderbys());
			//List<RiskEventQueryViewWrapper> wrapperList = new LinkedList<RiskEventQueryViewWrapper>();
			this.wrapperList=new LinkedList<RiskEventQueryViewWrapper>();
			for(RiskEventQueryView riskEventQueryView : reqvvList) {
				RiskEventQueryViewWrapper riskEventQueryViewWrapper = new RiskEventQueryViewWrapper();
				riskEventQueryViewWrapper.setId(riskEventQueryView.getId());
				riskEventQueryViewWrapper.setDepName(riskEventQueryView.getDepName());
				riskEventQueryViewWrapper.setRiskName(riskEventQueryView.getRiskName());
				riskEventQueryViewWrapper.setRmPlandate(riskEventQueryView.getRmPlandate());
				if(this.getChoosedId().equals("2")) {
					riskEventQueryViewWrapper.setRmDate(riskEventQueryView.getReModifydate());
				}else {
					riskEventQueryViewWrapper.setRmDate(riskEventQueryView.getReDate());
				}
				riskEventQueryViewWrapper.setReinchargedep(riskEventQueryView.getReinchargedep());
				riskEventQueryViewWrapper.setRiAllvalue(riskEventQueryView.getRiAllvalue());
				riskEventQueryViewWrapper.setIndepid(riskEventQueryView.getIndepid());
				riskEventQueryViewWrapper.setReState(riskEventQueryView.getReState());
				riskEventQueryViewWrapper.setReAct(riskEventQueryView.getReAct());
				riskEventQueryViewWrapper.setRiskId(riskEventQueryView.getRiskId());
				riskEventQueryViewWrapper.setReEventname(riskEventQueryView.getReEventname());
				wrapperList.add(riskEventQueryViewWrapper);
			}
			
			// 导出报表的数据
			String qindep = this.getReIndep();
			String qriskdep = this.getRiskDep();
			if (this.getReIndep().equals("--请选择--"))
				qindep = "全部";
			if (this.getRiskDep().equals("--请选择--"))
				qriskdep = "全部";
			String eventqueryString = "查询条件为--录入部门：" + qindep + "    责任部门："
					+ qriskdep + "    查询时间：" + datefromtem + "至" + datetotem;
			/*ServletActionContext.getRequest().setAttribute("reqvvList",
					reqvvList);*/
			ServletActionContext.getRequest().setAttribute("wrapperList",
					wrapperList);
			ServletActionContext.getRequest().getSession().setAttribute(
					"current_pagenum", current_pagenum);
			
			
			System.out.println("ttttttttttttttttttt");
			this.setActionName("riskQuery/REQQueryAction");
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// "涉及的流程文件"链接处，打开小窗口显示流程文件
	public String reqSelectedFile() {
		try {
			this.fileList = new LinkedList<FileManage>();
			this.efList = new LinkedList<EventFile>();
			this.efList = this.getEventFileDao().findByProperty("reId",
					this.getReId());
			FileManage f = new FileManage();
			EventFile ef = new EventFile();
			Iterator it = this.efList.iterator();
			while (it.hasNext()) {
				ef = (EventFile) it.next();
				f = this.getFileManageDao().findById(ef.getFileId());
				this.fileList.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

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
			this.efList = this.getEventFileDao().findByProperty("reId",dsarray[m][0]);
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

			s = s + "共" + i + "个文件。\n";
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
		session.put("exportEventQueryList", dsarray);
		session.put("querycondition", querycondition);
	}
	
	/*private void getInfo1(List<RiskEventQueryView> reList1, String querycondition1) {

		// 将数据存放在数组中，生成excel时获取
		String[][] dsarray = new String[reList1.size()][31];

		for (int m = 0; m < reList1.size(); m++) {
			dsarray[m][0] = reList1.get(m).getId().getReId();// 风险事件编号
			dsarray[m][1] = reList1.get(m).getRiskName();
			dsarray[m][2] = reList1.get(m).getDepName();
			dsarray[m][3] = reList1.get(m).getReinchargedep();
			dsarray[m][4] = getStateName(reList1.get(m).getReState(), reList1
					.get(m).getReAct());

			dsarray[m][5] = reList1.get(m).getReModifydate();
			dsarray[m][6] = String.valueOf(reList1.get(m).getRiAllvalue());

			RiskImpact rimpact = new RiskImpact();
			rimpact = this.riskimpactDao.findById(dsarray[m][0]);
			RiskManage rmanage = new RiskManage();
			rmanage = this.riskmanageDao.findById(dsarray[m][0]);
			dsarray[m][7] = rimpact.getRiKpi();
			dsarray[m][8] = rimpact.getRiProbability();
			dsarray[m][9] = rimpact.getRiBusarea();
			dsarray[m][10] = rimpact.getRiSource();

			this.efList = new LinkedList<EventFile>();
			this.efList = this.getEventFileDao().findByProperty("reId",dsarray[m][0]);
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

			s = s + "共" + i + "个文件。\n";
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
		session.put("exportEventQueryList1", dsarray);
		session.put("querycondition1", querycondition1);
	}
*/
	// 风险事件录入时间查询的结果报表导出
	public String exportExcel() {
		this.reList = new LinkedList<RiskEventQueryView>();
		this.reList = this.getRiskEventQueryViewDao().findAll();
		//getInfo(this.reList, "查询条件为：全部事件");
		Map session = ServletActionContext.getContext().getSession();
		if (session.get("querycondition") == null|| (session.get("exportEventQueryList") == null))
			return "fail";
		else {
			String str = "风险事件查询报表导出--" + session.get("querycondition");
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("风险事件查询报表导出", "eventQueryTemplate",
							(String[][]) session.get("exportEventQueryList"),
							3, 0, str);// 3表示从第三行开始，4表示从第4列开始合并
			return "success";}
		/*this.reList = new LinkedList<RiskEventQueryView>();
		this.reList = this.getRiskEventQueryViewDao().findAll();
		
		getInfo(reList, "查询条件为：全部事件");
		Map session = ServletActionContext.getContext().getSession();
		if (session.get("exportEventQueryList") == null)
			return "fail";
		else {
			String str = "风险事件查询报表导出--" + session.get("querycondition");
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("风险事件查询报表导出", "eventQueryTemplate",
							(String[][]) session.get("exportEventQueryList"),
							3, 0, str);// 3表示从第三行开始，4表示从第4列开始合并
			return "success";
		}*/
	}
	
	// 风险事件录入时间查询的结果报表导出
		/*public String exportExcel1() {
			
			
			this.reList1 = new LinkedList<RiskEventQueryView>();
			this.reList1 = this.getRiskEventQueryViewDao().findAll();
			
			getInfo1(reList1, "查询条件为：全部事件");
			Map session = ServletActionContext.getContext().getSession();
			if (session.get("exportEventQueryList1") == null)
				return "fail";
			else {
				String str = "风险事件查询报表导出--" + session.get("querycondition1");
				ExcelReportAction ex = new ExcelReportAction();
				ex.ReportExcel("风险事件查询报表导出", "eventQueryTemplate1",
								(String[][]) session.get("exportEventQueryList1"),
								3, 0, str);// 3表示从第三行开始，4表示从第4列开始合并
				return "success";
			}
		}*/
	
}
