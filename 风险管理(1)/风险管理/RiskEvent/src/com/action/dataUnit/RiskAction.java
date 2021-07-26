package com.action.dataUnit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.struts2.ServletActionContext;

import com.action.ExcelReportAction;
import com.dao.DepartmentDAO;
import com.dao.FlowRuleDAO;
import com.dao.RiskDAO;
import com.dao.RiskFileDAO;
import com.dao.RiskQueryViewDAO;
import com.dao.RiskTypeDAO;
import com.model.Department;
import com.model.FlowRule;
import com.model.Risk;
import com.model.RiskQueryView;
import com.model.RiskType;

//369行
public class RiskAction {
	private FlowRuleDAO flowRuleDao;
	private String riskId;
	private String riskType;
	private String riskName;
	private String riskDep;
	private String riskRemark;
	private String riskQueue;
	private Set riskEvents = new HashSet(0);
	private String updateFlag;
	private int current_pagenum = 1;// 当前页码
	private int pageNum = 10;// 每页的显示数据记录数
	private String riskTypeId;
	private String riskNumId;
	private String riskTypeString = "";
	public String dateFrom = "";
	private String actionName = "dataUnit/RiskAction";
	private String year = "";
	private String yearBefore = "";
	private String yearAfter = "";

	Risk risk = new Risk();
	RiskQueryView riskview = new RiskQueryView();
	RiskType rt = new RiskType();

	private List<RiskType> rtList;
	private List<RiskType> allrtList;
	private List<FlowRule> flowRuleLists;
	private List<RiskQueryView> rList;
	private List<RiskQueryView> risList;
	private List<RiskQueryView> allriskList;
	private List<Department> alldepList;
	private List<RiskQueryView> riskList;
	private List<String> idCheck;
	private List<Department> riskdepList;
	private List<Department> depList;

	private DepartmentDAO departmentDao;
	private RiskTypeDAO riskTypeDao;
	private RiskDAO riskDao;
	private RiskQueryViewDAO riskQueryViewDao;
	private RiskFileDAO riskFileDao;

	public RiskQueryViewDAO getRiskQueryViewDao() {
		return riskQueryViewDao;
	}

	public void setRiskQueryViewDao(RiskQueryViewDAO riskQueryViewDao) {
		this.riskQueryViewDao = riskQueryViewDao;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<FlowRule> getFlowRuleLists() {
		return flowRuleLists;
	}

	public void setFlowRuleLists(List<FlowRule> flowRuleLists) {
		this.flowRuleLists = flowRuleLists;
	}

	public FlowRuleDAO getFlowRuleDao() {
		return flowRuleDao;
	}

	public void setFlowRuleDao(FlowRuleDAO flowRuleDao) {
		this.flowRuleDao = flowRuleDao;
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

	public List<Department> getAlldepList() {
		return alldepList;
	}

	public void setAlldepList(List<Department> alldepList) {
		this.alldepList = alldepList;
	}

	public List<RiskQueryView> getRiskList() {
		return riskList;
	}

	public void setRiskList(List<RiskQueryView> riskList) {
		this.riskList = riskList;
	}

	public void setRisList(List<RiskQueryView> risList) {
		this.risList = risList;
	}

	public void setAllriskList(List<RiskQueryView> allriskList) {
		this.allriskList = allriskList;
	}

	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getRiskDep() {
		return riskDep;
	}

	public void setRiskDep(String riskDep) {
		this.riskDep = riskDep;
	}

	public String getRiskRemark() {
		return riskRemark;
	}

	public void setRiskRemark(String riskRemark) {
		this.riskRemark = riskRemark;
	}

	public String getRiskQueue() {
		return riskQueue;
	}

	public void setRiskQueue(String riskQueue) {
		this.riskQueue = riskQueue;
	}

	public Set getRiskEvents() {
		return riskEvents;
	}

	public void setRiskEvents(Set riskEvents) {
		this.riskEvents = riskEvents;
	}

	public List<String> getIdCheck() {
		return idCheck;
	}

	public void setIdCheck(List<String> idCheck) {
		this.idCheck = idCheck;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getRiskTypeId() {
		return riskTypeId;
	}

	public void setRiskTypeId(String riskTypeId) {
		this.riskTypeId = riskTypeId;
	}

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}

	public RiskTypeDAO getRiskTypeDao() {
		return riskTypeDao;
	}

	public void setRiskTypeDao(RiskTypeDAO riskTypeDao) {
		this.riskTypeDao = riskTypeDao;
	}

	public RiskDAO getRiskDao() {
		return riskDao;
	}

	public void setRiskDao(RiskDAO riskDao) {
		this.riskDao = riskDao;
	}

	public String getRiskTypeString() {
		return riskTypeString;
	}

	public void setRiskTypeString(String riskTypeString) {
		this.riskTypeString = riskTypeString;
	}

	public String getRiskNumId() {
		return riskNumId;
	}

	public void setRiskNumId(String riskNumId) {
		this.riskNumId = riskNumId;
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

	public RiskType getRt() {
		return rt;
	}

	public void setRt(RiskType rt) {
		this.rt = rt;
	}

	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public List<RiskType> getAllrtList() {
		return allrtList;
	}

	public void setAllrtList(List<RiskType> allrtList) {
		this.allrtList = allrtList;
	}

	public List<RiskType> getRtList() {
		return rtList;
	}

	public void setRtList(List<RiskType> rtList) {
		this.rtList = rtList;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getYearBefore() {
		return yearBefore;
	}

	public void setYearBefore(String yearBefore) {
		this.yearBefore = yearBefore;
	}

	public String getYearAfter() {
		return yearAfter;
	}

	public void setYearAfter(String yearAfter) {
		this.yearAfter = yearAfter;
	}

	public List<RiskQueryView> getrList() {
		return rList;
	}

	public void setrList(List<RiskQueryView> rList) {
		this.rList = rList;
	}

	public List<RiskQueryView> getRisList() {
		return risList;
	}

	public List<RiskQueryView> getAllriskList() {
		return allriskList;
	}

	public RiskFileDAO getRiskFileDao() {
		return riskFileDao;
	}

	public void setRiskFileDao(RiskFileDAO riskFileDao) {
		this.riskFileDao = riskFileDao;
	}

	// 显示风险信息,得到最新risList,用于在Risk.jsp表单中显示
	public String risManage() {

		// 得到当年年份
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy");
		this.setDateFrom(formatter.format(new Date()));

		// 报表导出
		this.allriskList = new LinkedList<RiskQueryView>();
		this.allriskList = this.getRiskQueryViewDao().findCurrentYearAll();
		if (!allriskList.isEmpty()) {
			getInfo(allriskList);
		}
		this.risList = new LinkedList<RiskQueryView>();
		this.risList = this.getRiskQueryViewDao().findCurrentYearAll((current_pagenum - 1) * pageNum, pageNum);
		ServletActionContext.getRequest().setAttribute("risList", risList);
		ServletActionContext.getRequest().getSession().setAttribute(
				"current_pagenum", current_pagenum);
		this.setActionName("dataUnit/RiskAction");
		if (risList.size() > 0)
			return "success";
		else
			return "fail";
	}

	// 新增|编辑
	public String risAddUpdate() {

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (this.getUpdateFlag().equals("update")) {
			try {
				// risk.setRiskId(this.getRiskTypeId()+'-'+this.getRiskNumId()+"-"+dy.format(date));
				System.out.println("2019-01-09"+this.getRiskId());
				risk = this.getRiskDao().findById(this.getRiskId());

				risk.setRiskName(this.getRiskName());
				//risk.setRiskType(this.getRiskTypeId());
				// risk.setRiskType(this.getRiskTypeDao().findById(this.getRiskTypeId()));
				risk.setRiskDep(this.getRiskDep());
				// remark字段被用于记录风险建立时间
				risk.setRiskRemark(df.format(date));

				this.getRiskDao().merge(risk);
				risManage();// 得到最新risList,用于在Risk.jsp表单中显示
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
			return "success";
		} else {
			try {
				// Risk tempri =
				// riskDao.findById(this.getRiskTypeId()+'-'+this.getRiskNumId()+"-"+dy.format(date));
				this.setRiskId(this.getRiskTypeId() + '-' + this.getRiskNumId()
						+ "-" + this.getYear());
				Risk tempri = this.getRiskDao().findById(this.getRiskId());
				if (tempri != null) {
					tempri.setRiskName(this.getRiskName());
					//tempri.setRiskType(this.getRiskTypeId());
					// tempri.setRiskType(this.getRiskTypeDao().findById(this.getRiskTypeId()));
					tempri.setRiskDep(this.getRiskDep());
					// remark字段被用于记录风险建立时间
					tempri.setRiskRemark(df.format(date));
					// tempri.setRiskQueue(riskDao.findAll().size()+1);
					// tempri.setRiskCurrent("1");
					this.getRiskDao().merge(tempri);
					risManage();// 得到最新risList,用于在Risk.jsp表单中显示
					return "success";
				}

				risk.setRiskId(this.getRiskId());
				risk.setRiskName(this.getRiskName());
				risk.setRiskType(this.getRiskTypeId());
				risk.setRiskDep(this.getRiskDep());
				// remark字段被用于记录风险建立时间
				risk.setRiskRemark(df.format(date));
				risk.setRiskQueue(riskDao.getRiskMaxQueue() + 1);
				risk.setRiskCurrent("1");
				this.getRiskDao().save(risk);
				risManage();// 得到最新risList,用于在Risk.jsp表单中显示
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
			return "success";
		}
	}

	// 批量新增(这个地方需要后期修改)
	public String risAddBatch() {

		try {
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// remark字段被用于记录风险建立时间
			this.setRiskRemark(df.format(date));

			this.allriskList = new LinkedList<RiskQueryView>();
			this.allriskList = this.getRiskQueryViewDao().searchNew(this.getYearBefore(), "");
			int allsize = this.getRiskDao().getRiskMaxQueue();
			Iterator it = this.allriskList.iterator();
			while (it.hasNext()) {
				riskview = (RiskQueryView) it.next();
				Risk risk1 = new Risk();
				String pre = riskview.getRiskId();
				String risknum = pre.substring(3, 7);
//				this.setRiskId(riskview.getRiskType() + '-'
//						+ getRiskNum(riskview.getRiskType(), this.getYearAfter())
//						+ "-" + this.getYearAfter());
				this.setRiskId(riskview.getRiskType() + '-'+ risknum + "-" + this.getYearAfter());
				// this.setRiskId(getRiskNum(risk.getRiskType().getRtId(),this.getYearAfter()));
				//新增风险
				risk1.setRiskId(this.getRiskId());
				risk1.setRiskName(riskview.getRiskName());
				risk1.setRiskType(riskview.getRiskType());
				risk1.setRiskDep(riskview.getRiskDep());
				risk1.setRiskRemark(this.getRiskRemark());
				risk1.setRiskQueue(++allsize);
				risk1.setRiskCurrent("1");
				this.getRiskDao().merge(risk1);

				//设置流程
				this.flowRuleLists = new LinkedList<FlowRule>();
				this.flowRuleLists = this.getFlowRuleDao().findByRiskId(riskview.getRiskId());
				FlowRule flowRule1 = new FlowRule();
				FlowRule flowRule2 = new FlowRule();
				Iterator it1 = this.flowRuleLists.iterator();
				while (it1.hasNext()) {

					flowRule1 = (FlowRule) it1.next();
					flowRule2.setRiskId(risk1.getRiskId());
					flowRule2.setFrStatus(flowRule1.getFrStatus());
					flowRule2.setFrNextstatus(flowRule1.getFrNextstatus());
					flowRule2.setFrPrestatus(flowRule1.getFrPrestatus());
					flowRule2.setFrDepart(flowRule1.getFrDepart());
					flowRule2.setFrRole(flowRule1.getFrRole());
					flowRule2.setFrRemark1(flowRule1.getFrRemark1());
					flowRule2.setFrRemark2(flowRule1.getFrRemark2());
					this.getFlowRuleDao().save(flowRule2);

				}
				//修改Risk_File表
				riskFileDao.updateRiskId(risk1.getRiskId(), riskview.getRiskId());
			}
			//将老的风险的RiskCurrent字段设置为0
			this.getRiskDao().delOldRisk(this.getYearBefore());
			risManage();// 得到最新risList,用于在Risk.jsp表单中显示

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";

	}

	public String getRiskNum(String temprisktypeid, String searchYear) {

		this.rList = new LinkedList<RiskQueryView>();
		this.rList = this.getRiskQueryViewDao().findByTypeAndYearDESC(temprisktypeid,searchYear);
		String str = "0001";
		int risknum = 0;
		if (this.rList.size() > 0) {
			String pre = rList.get(0).getRiskId();
			risknum = Integer.parseInt(pre.substring(3, 7));
			risknum = risknum + 1;
			str = String.valueOf(risknum);
			int d = 4 - str.length();
			for (int i = 0; i < d; i++) {
				str = "0" + str;
			}
		}

		return str;
	}

	// 批量删除
	public String risMultiDel() {
		try {
			int i = 0;
			for (i = 0; i < this.idCheck.size(); i++) {
				this.setRiskId(this.idCheck.get(i));
				risk = this.getRiskDao().findById(this.getRiskId());
				risk.setRiskCurrent("0");
				//this.getRiskDao().delete(risk);
				this.getRiskDao().merge(risk);
			}
			risManage();// 得到最新risList,用于在Risk.jsp表单中显示
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 删除
	public String risDelete() {
		try {
			risk = this.getRiskDao().findById(this.getRiskId());
			risk.setRiskCurrent("0");
			this.getRiskDao().delete(risk);
			risManage();// 得到最新risList,用于在Risk.jsp表单中显示
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 查看
	public String risRead() {
		try {
			risk = this.getRiskDao().findById(this.getRiskId());
			this.riskId = risk.getRiskId();
			this.riskName = risk.getRiskName();
			// this.riskTypeId=risk.getRiskType().getRtName();
			this.riskTypeId="";
			if (null != risk.getRiskType()) {
				this.riskTypeId = this.getRiskTypeDao().findById(risk.getRiskType()).getRtName();
			}
			this.riskNumId = this.riskId.substring(3, 7);
			this.riskDep = risk.getRiskDep();
			this.riskRemark = risk.getRiskRemark();
		} catch (Exception e) {

			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 编辑前显示要编辑的对象的信息
	public String risUpdatePrepare() {
		try {

			risAddPrepare();// 风险类型和归口部门下拉框
			System.out.println("2019.01.08"+this.getRiskId());
			// 其他信息
			risk = this.getRiskDao().findById(this.getRiskId());
			this.riskId = risk.getRiskId();
			this.riskName = risk.getRiskName();
			this.riskTypeId = risk.getRiskType();
			this.riskNumId = this.riskId.substring(3, 7);
			this.riskDep = risk.getRiskDep();
			this.riskRemark = risk.getRiskRemark();
			this.riskQueue = String.valueOf(risk.getRiskQueue());
			this.year = this.riskId.substring(8, 12);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 新增前显示要编辑的对象的信息
	public String risAddPrepare() {
		try {

			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dy = new SimpleDateFormat("yyyy");
			year = dy.format(date);
			// 风险类型下拉框allrtList
			RiskType rt1 = new RiskType();
			rt1.setRtId("none1");
			rt1.setRtName("--请选择--");
			rt1.setRtRemark("--请选择--");
			this.allrtList = new LinkedList<RiskType>();
			this.allrtList.add(rt1);
			this.rtList = new LinkedList<RiskType>();
			this.rtList = this.getRiskTypeDao().findAll();
			this.allrtList.addAll(this.rtList);
			// 归口部门下拉框
			Department dep1 = new Department();
			dep1.setDepId("none1");
			dep1.setDepName("--请选择--");
			dep1.setDepSort(0);
			Department dep2 = new Department();
			dep2.setDepId("all1");
			dep2.setDepName("本部门");
			dep2.setDepSort(1);
			riskdepList = new LinkedList<Department>();
			riskdepList.add(dep1);
			riskdepList.add(dep2);
			depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findALLrelevantdep();
			riskdepList.addAll(depList);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 批量新增前显示要编辑的对象的信息
	public String risAddBatchPrepare() {
		try {

			Date date = new Date();
			SimpleDateFormat dy = new SimpleDateFormat("yyyy");
			// year=dy.format(date);
			this.setYearAfter(dy.format(date));
			Integer i = Integer.parseInt(this.getYearAfter());
			i--;
			this.setYearBefore(i.toString());
		} catch (Exception e) {
			// e.printStackTrace();
			risManage();// 得到最新risList,用于在Risk.jsp表单中显示
			return "fail";
		}
		return "success";
	}

	// 根据风险类型信息查询风险
	public String risSearch() {

		if (this.getDateFrom().length() < 4) {
			// 为空，和小于四位输入错误的情况
			this.setDateFrom("1900");
		}
		// 正常，和大于四位输入错误的情况
		String year = this.getDateFrom().substring(0, 4);

		this.allriskList = new LinkedList<RiskQueryView>();
		// this.allriskList=this.getRiskDao().search(this.getRiskTypeString());
		this.allriskList = this.getRiskQueryViewDao().searchNew(year,this.getRiskTypeString());

		if (!allriskList.isEmpty()) {
			getInfo(allriskList);
		}

		this.risList = new LinkedList<RiskQueryView>();
		// this.risList
		// =this.getRiskDao().search(this.getRiskTypeString(),(current_pagenum-1)*pageNum,pageNum);
		this.risList = this.getRiskQueryViewDao().searchNew(year,
				this.getRiskTypeString(), (current_pagenum - 1) * pageNum,
				pageNum);

		ServletActionContext.getRequest().setAttribute("risList", risList);
		ServletActionContext.getRequest().getSession().setAttribute(
				"current_pagenum", current_pagenum);
		// this.actionName="dataUnit/RiskAction";
		this.actionName = "dataUnit/RisSearchAction";

		return "success";
	}

	private void getInfo(List<RiskQueryView> allriskList) {
		// 将数据存放在数组中，生成excel时获取
		String[][] dsarray = new String[allriskList.size()][5];

		for (int m = 0; m < allriskList.size(); m++) {
			dsarray[m][0] = allriskList.get(m).getRiskId();
			
			dsarray[m][1] = "";
			if (null != allriskList.get(m).getRiskType()){
				dsarray[m][1] = allriskList.get(m).getRtName();
			}
			dsarray[m][2] = allriskList.get(m).getRiskName();// 二级风险名称
			dsarray[m][3] = allriskList.get(m).getRiskDep();// 风险归口部门
			dsarray[m][4] = allriskList.get(m).getRiskRemark();// 建立时间
		}
		// 数据存放在session中，便于导出excel
		Map session = ServletActionContext.getContext().getSession();
		session.put("exportallriskList", dsarray);
	}

	// 风险报表导出
	public String exportRiskExcel() {
		Map session = ServletActionContext.getContext().getSession();
		if (session.get("exportallriskList") == null) {
			
			return "fail";
		}
			
		else {
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String str = "风险输出报表   " + "制表日期：" + df.format(date);
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("风险输出报表", "allRiskTemplate", (String[][]) session
					.get("exportallriskList"), 3, 2, str);// 3表示从第三行开始，4表示从第4列开始合并
			return "success";
		}
	}

	public String addriskFlow2() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dy = new SimpleDateFormat("yyyy");
		//2019。01.09
		this.riskType = this.riskId;
		System.out.println(this.riskId);
		System.out.println(this.riskType);
		if (risAddUpdate() == "success") {
			alldepList = new LinkedList<Department>();
			alldepList = this.getDepartmentDao().findAll();
			riskList = new LinkedList<RiskQueryView>();
			riskList = this.getRiskQueryViewDao().findAllbyName();
			//操作riskList的riskname属性值
			//2019.01.09
			for (RiskQueryView items : riskList) {
				if (!"NULL".equals(items.getRiskRemark())) {
					items.setRiskName(items.getRiskName()+items.getRiskRemark());
				}
			}
			//riskList = this.getRiskQueryViewDao().findAll();
			flowRuleLists = null;
			flowRuleLists = this.getFlowRuleDao().findByFlow("riskId",riskType);
			return "success";
		} else
			return "fail";
	}

}
