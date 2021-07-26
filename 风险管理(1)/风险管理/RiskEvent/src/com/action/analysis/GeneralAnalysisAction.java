package com.action.analysis;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.action.ExcelReportAction;
import com.dao.AllAnalysisViewDAO;
import com.dao.AllAnalysisViewNew2DAO;
import com.dao.AllAnalysisViewNewDAO;
import com.dao.DepartmentDAO;
import com.dao.OperationDAO;
import com.dao.ProbabilityDAO;
import com.dao.ReportViewDAO;
import com.dao.RiskDAO;
import com.dao.RiskTypeDAO;
import com.entity.ReportViewNew;
import com.entity.RiAnalysis;
import com.entity.RuleFile;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.model.AllAnalysisView;
import com.model.Department;
import com.model.Operation;
import com.model.Probability;
import com.model.Risk;
import com.model.RiskType;
import com.model.Users;
import com.opensymphony.xwork2.ActionContext;

public class GeneralAnalysisAction {
	private String year;
	private List<RiAnalysis> ralist;
	private String choosedId;
	private List<AllAnalysisView> riskEventlistall;
	private AllAnalysisViewDAO allAnalysisViewDAO;
	private AllAnalysisViewNewDAO allAnalysisViewNewDAO;
	private AllAnalysisViewNew2DAO allAnalysisViewNew2DAO;
	private List<Department> alldepList; // 录入部门
	private List<Department> deps;
	private DepartmentDAO departmentDao;
	private String riAllValueFrom;
	private String riAllValueTo;
	private String riDegreeFrom;
	private String riDegreeTo;
	private String riProDegree;
	private List<String> allProDegreeList;
	private String riDepName;
	private String dateFrom;
	private String dateTo;
	private String risktype;
	private String riskname;
	private List<RiskType> rtList;
	private List<RiskType> allrtList;
	private RiskTypeDAO riskTypeDao;
	private String path;
	private String piePath;
	private String barPath;
	private String barPath_1;
	private String barPath_2;
	private List<ReportViewNew> reportList;
	private List<Risk> rList;
	private List<Probability> pList;
	private List<Operation> iList; // 风险影响的列表，分为极低、低、中等、高、极高。借用运营的实体
	private RiskDAO riskDAO;
	private ProbabilityDAO probabilityDao;
	private OperationDAO operationDao;
	private ReportViewDAO reportViewDao;
	private String areadetail;
	private String reId;
	private String riNum;
	private String depNum;
	private List<RiAnalysis> eventDetailList;
	private List<RiAnalysis> riskDiskList;
	DecimalFormat dcmFmt = new DecimalFormat("0.00");
	//private HttpServletResponse response;

	
	public String getChoosedId() {
		return choosedId;
	}

	public void setChoosedId(String choosedId) {
		this.choosedId = choosedId;
	}
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<RiAnalysis> getRalist() {
		return ralist;
	}

	public void setRalist(List<RiAnalysis> ralist) {
		this.ralist = ralist;
	}

	public String getBarPath_1() {
		return barPath_1;
	}

	public void setBarPath_1(String barPath_1) {
		this.barPath_1 = barPath_1;
	}

	public String getBarPath_2() {
		return barPath_2;
	}

	public void setBarPath_2(String barPath_2) {
		this.barPath_2 = barPath_2;
	}

	public AllAnalysisViewDAO getAllAnalysisViewDAO() {
		return allAnalysisViewDAO;
	}

	public void setAllAnalysisViewDAO(AllAnalysisViewDAO allAnalysisViewDAO) {
		this.allAnalysisViewDAO = allAnalysisViewDAO;
	}

	public AllAnalysisViewNewDAO getAllAnalysisViewNewDAO() {
		return allAnalysisViewNewDAO;
	}

	public void setAllAnalysisViewNewDAO(
			AllAnalysisViewNewDAO allAnalysisViewNewDAO) {
		this.allAnalysisViewNewDAO = allAnalysisViewNewDAO;
	}

	public AllAnalysisViewNew2DAO getAllAnalysisViewNew2DAO() {
		return allAnalysisViewNew2DAO;
	}

	public void setAllAnalysisViewNew2DAO(
			AllAnalysisViewNew2DAO allAnalysisViewNew2DAO) {
		this.allAnalysisViewNew2DAO = allAnalysisViewNew2DAO;
	}

	public List<AllAnalysisView> getRiskEventlistall() {
		return riskEventlistall;
	}

	public void setRiskEventlistall(List<AllAnalysisView> riskEventlistall) {
		this.riskEventlistall = riskEventlistall;
	}

	public List<Department> getAlldepList() {
		return alldepList;
	}

	public void setAlldepList(List<Department> alldepList) {
		this.alldepList = alldepList;
	}

	public List<Department> getDeps() {
		return deps;
	}

	public void setDeps(List<Department> deps) {
		this.deps = deps;
	}

	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public String getRiAllValueFrom() {
		return riAllValueFrom;
	}

	public void setRiAllValueFrom(String riAllValueFrom) {
		this.riAllValueFrom = riAllValueFrom;
	}

	public String getRiAllValueTo() {
		return riAllValueTo;
	}

	public void setRiAllValueTo(String riAllValueTo) {
		this.riAllValueTo = riAllValueTo;
	}

	public String getRiDegreeFrom() {
		return riDegreeFrom;
	}

	public void setRiDegreeFrom(String riDegreeFrom) {
		this.riDegreeFrom = riDegreeFrom;
	}

	public String getRiDegreeTo() {
		return riDegreeTo;
	}

	public void setRiDegreeTo(String riDegreeTo) {
		this.riDegreeTo = riDegreeTo;
	}

	public String getRiProDegree() {
		return riProDegree;
	}

	public void setRiProDegree(String riProDegree) {
		this.riProDegree = riProDegree;
	}

	public List<String> getAllProDegreeList() {
		return allProDegreeList;
	}

	public void setAllProDegreeList(List<String> allProDegreeList) {
		this.allProDegreeList = allProDegreeList;
	}

	public String getRiDepName() {
		return riDepName;
	}

	public void setRiDepName(String riDepName) {
		this.riDepName = riDepName;
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

	public String getRisktype() {
		return risktype;
	}

	public void setRisktype(String risktype) {
		this.risktype = risktype;
	}

	public String getRiskname() {
		return riskname;
	}

	public void setRiskname(String riskname) {
		this.riskname = riskname;
	}

	public List<RiskType> getRtList() {
		return rtList;
	}

	public void setRtList(List<RiskType> rtList) {
		this.rtList = rtList;
	}

	public List<RiskType> getAllrtList() {
		return allrtList;
	}

	public void setAllrtList(List<RiskType> allrtList) {
		this.allrtList = allrtList;
	}

	public RiskTypeDAO getRiskTypeDao() {
		return riskTypeDao;
	}

	public void setRiskTypeDao(RiskTypeDAO riskTypeDao) {
		this.riskTypeDao = riskTypeDao;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPiePath() {
		return piePath;
	}

	public void setPiePath(String piePath) {
		this.piePath = piePath;
	}

	public String getBarPath() {
		return barPath;
	}

	public void setBarPath(String barPath) {
		this.barPath = barPath;
	}

	public List<ReportViewNew> getReportList() {
		return reportList;
	}

	public void setReportList(List<ReportViewNew> reportList) {
		this.reportList = reportList;
	}

	public List<Risk> getrList() {
		return rList;
	}

	public void setrList(List<Risk> rList) {
		this.rList = rList;
	}

	public List<Probability> getpList() {
		return pList;
	}

	public void setpList(List<Probability> pList) {
		this.pList = pList;
	}

	public List<Operation> getiList() {
		return iList;
	}

	public void setiList(List<Operation> iList) {
		this.iList = iList;
	}

	public RiskDAO getRiskDAO() {
		return riskDAO;
	}

	public void setRiskDAO(RiskDAO riskDAO) {
		this.riskDAO = riskDAO;
	}

	public ProbabilityDAO getProbabilityDao() {
		return probabilityDao;
	}

	public void setProbabilityDao(ProbabilityDAO probabilityDao) {
		this.probabilityDao = probabilityDao;
	}

	public OperationDAO getOperationDao() {
		return operationDao;
	}

	public void setOperationDao(OperationDAO operationDao) {
		this.operationDao = operationDao;
	}

	public ReportViewDAO getReportViewDao() {
		return reportViewDao;
	}

	public void setReportViewDao(ReportViewDAO reportViewDao) {
		this.reportViewDao = reportViewDao;
	}

	public String getAreadetail() {
		return areadetail;
	}

	public void setAreadetail(String areadetail) {
		this.areadetail = areadetail;
	}

	public String getReId() {
		return reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
	}

	public String getRiNum() {
		return riNum;
	}

	public void setRiNum(String riNum) {
		this.riNum = riNum;
	}

	public String getDepNum() {
		return depNum;
	}

	public void setDepNum(String depNum) {
		this.depNum = depNum;
	}

	public List<RiAnalysis> getEventDetailList() {
		return eventDetailList;
	}

	public void setEventDetailList(List<RiAnalysis> eventDetailList) {
		this.eventDetailList = eventDetailList;
	}

	public List<RiAnalysis> getRiskDiskList() {
		return riskDiskList;
	}

	public void setRiskDiskList(List<RiAnalysis> riskDiskList) {
		this.riskDiskList = riskDiskList;
	}

	public String GeneralAnalysisDep() {
		risktype = "none1";
		riskname = "请选择";
		// 给发生概率下拉框赋值
		getProDegreeList();
		this.riProDegree = "请选择";
		this.choosedId="--请选择--";
		// 每个部门具体事件的list，传给页面显示
		Department dep1 = new Department();
		dep1.setDepId("none1");
		dep1.setDepName("请选择");
		dep1.setDepSort(0);
		alldepList = new LinkedList<Department>();
		alldepList.add(dep1);
		deps = new LinkedList<Department>();
		deps = this.getDepartmentDao().findAll();
		alldepList.addAll(deps);
		ralist = new LinkedList<RiAnalysis>();
		// List depListall = new LinkedList();
		List<AllAnalysisView> eventList = new LinkedList<AllAnalysisView>();
		// 根据不同的角色获取不同的查看部门的权限，权限在5以下的只能查看本部门
		int power = 1;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		int position = us.getUserPosition();
		if (position ==1 || position ==3 || position ==5) {
			this.setRiDepName(this.getDepartmentDao().getdepname(
					us.getUserDep()));
			power = 0;
		} else {
			this.setRiDepName("请选择");
		}
		session.setAttribute("power", power);

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		String currdate = formatter.format(new Date()); // 得到年月日 2014-09-02
		year = currdate.substring(0, 4);
		// 首次查询默认为当年
		dateFrom = year + "-01-01";
		dateTo = year + "-12-31";
		// 综合评定输入框默认为通用最小值和最大值
		riAllValueFrom = "0.00";
		riAllValueTo = "175.00";
		// 影响程度输入框默认为通用最小值和最大值
		riDegreeFrom = "0.00";
		riDegreeTo = "5.00";

		String condition = getConditon();
		// 找到存在事件的所有部门
		// depListall = allAnalysisViewDAO.findDepByConditon(condition);
		eventList = allAnalysisViewDAO.findDetailOrderByDep(condition);
		
		// if (!depListall.isEmpty()) {
		// getInfoByCondion(depListall, condition);
		// }
		if (!eventList.isEmpty()) {
			getInfoNew(eventList);
		}
		return "success";
	}

	// 按部门名称查询报表导出
	public String exportExcelByDep() {
		String RiskTypeq="";
		Map session = ActionContext.getContext().getSession();
		// dsarray=(String[][])session.get("exportDepList");
		if("2".equals(choosedId)){
			RiskTypeq="发布时间：";
		}else{
			RiskTypeq="录入时间：";
		}
		if (session.get("exportGenList") == null)
			return "fail";
		else {
			String str = "一般风险分析按部门列表统计" + "    综合评定:" + riAllValueFrom + "至"
					+ riAllValueTo + "    影响程度:" + riDegreeFrom + "至"
					+ riDegreeTo + "    发生概率:" + riProDegree;
			if (riDepName == null || riDepName.equals("请选择")) {
				str += " 部门:全部";
			} else {
				str += " 部门:" + riDepName;
			}
			str += "    "+RiskTypeq + dateFrom + "至" + dateTo;
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("风险分析列表统计查询", "GenAnaDepTemplate",
					(String[][]) session.get("exportGenList"), 3, 5, str);// 4表示从第四行开始，5表示从第五列开始合并，str是查询条件
			return "success";
		}
	}

	public String GeneralAnalysisDepByValue() {
		risktype = "none1";
		riskname = "请选择";
		ralist = new LinkedList<RiAnalysis>();
		String condition = getConditon();
		List<AllAnalysisView> eventList = new LinkedList<AllAnalysisView>();
		eventList = allAnalysisViewDAO.findDetailOrderByDep(condition);
		
		// if (!depListall.isEmpty()) {
		// getInfoByCondion(depListall, condition);
		// }
		if (!eventList.isEmpty()) {
			getInfoNew(eventList);
		}
		return "success";
	}

	private void getInfoNew(List<AllAnalysisView> eventList) {
		// TODO Auto-generated method stub
		String depname1;
		String depname2;
		String riskname1;
		String riskname2;
		// 部门序号
		int depnum = 1;
		// 事件总数
		int allcount = 0;
		// 部门事件数
		int depeventcount = 0;
		// 部门二级风险数
		int riskcount = 1;

		int eventListnum = eventList.size();
		for (int m = 0; m < eventListnum; m++) {
			// 将当前记录放入list中
			RiAnalysis ksone = new RiAnalysis();
			ksone.setDepNum(depnum + "");
			ksone.setDepName(eventList.get(m).getDepName().trim());
			ksone.setRiNum(riskcount + "");
			ksone.setRiskName(eventList.get(m).getRiskName());
			// 使得二级风险和一级风险一一对应
			if (riskcount % 2 != 0) {
				ksone.setRtName(eventList.get(m).getRtName());
			} else {
				ksone.setRtName(eventList.get(m).getRtName() + " ");
			}
			ksone.setReId(eventList.get(m).getReId());
			ksone.setReEventname(eventList.get(m).getReEventname());
			ksone.setRiAlldegree(dcmFmt.format(eventList.get(m)
					.getRiAlldegree()));
			ksone.setRiProdegree(Integer.toString(eventList.get(m)
					.getRiProdegree()));
			ksone.setRiKpi(eventList.get(m).getRiKpi());
			ksone.setRiAllvalue(String.valueOf(dcmFmt.format(eventList.get(m)
					.getRiAllvalue())));
			if("2".equals(choosedId)){
				ksone.setRiEventDate(eventList.get(m).getReModifydate());
			}else{
			ksone.setRiEventDate(eventList.get(m).getReDate());
			}
			ralist.add(ksone);
			depeventcount++;
			allcount++;

			if (m < eventListnum - 1) {
				depname1 = eventList.get(m).getDepName().trim();
				depname2 = eventList.get(m + 1).getDepName().trim();
				if (depname1.equals(depname2)) {
					riskname1 = eventList.get(m).getRiskName();
					riskname2 = eventList.get(m + 1).getRiskName();
					if (!riskname1.equals(riskname2)) {
						riskcount++;
					}
				} else {// 如果当前记录的部门名称与下一个记录不相同，则插入小计
					RiAnalysis ksone2 = new RiAnalysis();
					ksone2.setDepNum(depnum + " ");
					ksone2.setDepName(eventList.get(m).getDepName().trim()
							+ " ");
					ksone2.setRiNum("小计");
					ksone2.setRiskName(riskcount + "");
					ksone2.setRtName("");
					ksone2.setReEventname(depeventcount + "");
					ralist.add(ksone2);
					depnum++;
					riskcount = 1;
					depeventcount = 0;
				}
			}
			if (m == eventListnum - 1)// 最后一条记录特殊处理，直接添加小计
			{
				RiAnalysis ksone2 = new RiAnalysis();
				ksone2.setDepNum(depnum + " ");
				ksone2.setDepName(eventList.get(m).getDepName().trim() + " ");
				ksone2.setRiNum("小计");
				ksone2.setRiskName(riskcount + "");
				ksone2.setRtName("");
				ksone2.setReEventname(depeventcount + "");
				ralist.add(ksone2);
				riskcount = 1;
				depeventcount = 0;
			}
		}
		// 加入总计
		RiAnalysis ksone2 = new RiAnalysis();
		ksone2.setDepNum("总计");
		ksone2.setDepName(depnum + "");
		ksone2.setRiNum("");
		ksone2.setRiskName("");
		ksone2.setRtName("-");
		ksone2.setReEventname(allcount + "");
		ralist.add(ksone2);

		// 数据存放在session中，便于导出excel
		String[][] dsarray = new String[ralist.size()][12];
		for (int m = 0; m < ralist.size(); m++) {
			dsarray[m][0] = ralist.get(m).getDepNum().trim();
			dsarray[m][1] = ralist.get(m).getDepName().trim();
			dsarray[m][2] = ralist.get(m).getRiNum();
			dsarray[m][3] = ralist.get(m).getRiskName();
			dsarray[m][4] = ralist.get(m).getRtName();
			dsarray[m][5] = ralist.get(m).getReId();
			dsarray[m][6] = ralist.get(m).getReEventname();
			dsarray[m][7] = ralist.get(m).getRiAlldegree();
			dsarray[m][8] = ralist.get(m).getRiProdegree();
			dsarray[m][9] = ralist.get(m).getRiKpi();
			dsarray[m][10] = ralist.get(m).getRiAllvalue();
			dsarray[m][11] = ralist.get(m).getRiEventDate();
		}
		Map session = ServletActionContext.getContext().getSession();
		session.put("exportGenList", dsarray);

	}

	@SuppressWarnings("unused")
	public String GeneralAnalysisRisk() throws ServletException {
		this.path = "";
		this.piePath = "";
		this.barPath = "";
		this.barPath_1="";
		this.barPath_2="";
		this.choosedId="--请选择--";
		// 给发生概率下拉框赋值
		getProDegreeList();
		this.riProDegree = "请选择";
		// 每个部门具体事件的list，传给页面显示
		Department dep1 = new Department();
		dep1.setDepId("none1");
		dep1.setDepName("请选择");
		dep1.setDepSort(0);
		alldepList = new LinkedList<Department>();
		alldepList.add(dep1);
		deps = new LinkedList<Department>();
		deps = this.getDepartmentDao().findAll();
		alldepList.addAll(deps);
		// 根据不同的角色获取不同的查看部门的权限，权限在5一下的只能查看本部门
		int power = 1;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletRequest response= ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		int position = us.getUserPosition();
		if (position ==1 || position ==3 || position ==5) {
			this.setRiDepName(this.getDepartmentDao().getdepname(
					us.getUserDep()));
			power = 0;
		} else {
			this.setRiDepName("请选择");
		}
		session.setAttribute("power", power);

		String userId = us.getUserId();
		// 清空上次此用户生成的图片文件
		File folder = new File(ServletActionContext.getServletContext()
				.getRealPath("upload"));
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.getName().contains(userId + "reportImgShadow")
					|| file.getName().contains(userId + "sectionPiechart")
					|| file.getName().contains(userId + "riskNumBarchart")) {
				file.delete();
			}
		}
		// 每个部门具体事件的list，传给页面显示
		ralist = new LinkedList<RiAnalysis>();
		// List riskListall = new LinkedList();
		List<AllAnalysisView> eventList = new LinkedList<AllAnalysisView>();
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		String currdate = formatter.format(new Date()); // 得到年月日 2014-09-02
		year = currdate.substring(0, 4);
		// 首次查询默认为当年
		dateFrom = year + "-01-01";
		dateTo = year + "-12-31";

		// 得到下拉框，风险类型下拉框allrtList
		getDropDownList();
		risktype = "none1";
		riskname = "请选择";

		// 综合评定输入框默认为通用最小值和最大值
		riAllValueFrom = "0.00";
		riAllValueTo = "175.00";
		// 影响程度输入框默认为通用最小值和最大值
		riDegreeFrom = "0.00";
		riDegreeTo = "5.00";

		this.path = null;

		String condition = getConditon();
		// 找到存在事件的所有二级风险
		// riskListall = allAnalysisViewDAO.findRiskByConditon(condition);
		eventList = allAnalysisViewDAO.findDetailOrderByRisk(condition);
		reportList = new LinkedList<ReportViewNew>();
		Map session2 = ServletActionContext.getContext().getSession();
		session2.remove("reportList");
		
		List<RuleFile> ruleFileList = new LinkedList<RuleFile>();
		List<RuleFile> flowRuleFileList = new LinkedList<RuleFile>();
		ruleFileList = allAnalysisViewNewDAO.findRuleFileList(condition);
		flowRuleFileList = allAnalysisViewNew2DAO.findFlowRuleFileList(condition);
		ruleFileList.addAll(flowRuleFileList);
		Collections.sort(ruleFileList, new Comparator<RuleFile>() {
			public int compare(RuleFile r1, RuleFile r2) {
				return Integer.valueOf(r2.getEventNum()) - Integer.valueOf(r1.getEventNum());
			}
		});
		if(!ruleFileList.isEmpty()){
			this.barPath_2=graphicNew.drawRuleBarChart(ruleFileList);

		}
		if (!eventList.isEmpty()) {
			getInfoRiskNew(eventList);
			session2.put("reportList", reportList);
			try {
				this.path = graphicNew.drawTableShadow(900, reportList);
				this.piePath = graphicNew.drawPieChart(800, 500, reportList);
				/*this.barPath = graphicNew.drawBarChart(reportList);*/
				this.barPath = graphicNew.drawBarChart(reportList);
				// System.out.println("path*********"+ServletActionContext.getServletContext().getRealPath(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!reportList.isEmpty()) {
				this.areadetail = riskDetail(reportList);// 获取具体描述
				this.areadetail.replace("。", "<br>");
			} else
				this.areadetail = "";
			session2.put("areadetail", areadetail);
		}
		return "success";
	}

	// 按二级风险名称查询报表导出
	public String exportExcelByRisk() {
		String RiskTypep;
		Map<?, ?> session = ActionContext.getContext().getSession();
		// String[][] dsarray = (String[][]) session.get("exportGenList");
		// System.out.println("dsarray.length*********" + dsarray.length);
		if("2".equals(choosedId)){
			RiskTypep="发布时间:";
		}else{
			RiskTypep="录入时间:";
		}
		if (session.get("exportGenRiskList") == null)
			return "fail";
		else {
			String str = "一般风险分析按风险类型列表统计" + "    综合评定:" + riAllValueFrom + "至"
					+ riAllValueTo + "    影响程度:" + riDegreeFrom + "至"
					+ riDegreeTo + "    发生概率:" + riProDegree;
			if (riskname == null || riskname.equals("请选择")) {
				str += " 二级风险:全部";
			} else {
				str += " 二级风险:" + riskname;
			}
			str += "    "+RiskTypep + dateFrom + "至" + dateTo;
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("风险分析列表统计查询", "GenAnaRiskTemplate",
					(String[][]) session.get("exportGenRiskList"), 3, 4, str);// 3表示从第三行开始，5表示从第五列开始合并，str是查询条件
			return "success";
		}
	}

	public String GeneralAnalysisRiskByValue() throws ServletException {
		this.path = "";
		this.piePath = "";
		this.barPath = "";
		this.barPath_1="";
	//	this.choosedId="--请选择--";
		// 根据不同的角色获取不同的查看部门的权限，权限在5以下的只能查看本部门
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String userId = us.getUserId();
		// 清空上次此用户生成的图片文件
		File folder = new File(ServletActionContext.getServletContext()
				.getRealPath("upload"));
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.getName().contains(userId + "reportImgShadow")
					|| file.getName().contains(userId + "sectionPiechart")
					|| file.getName().contains(userId + "riskNumBarchart")) {
				file.delete();
			}
		}
		// 每个部门具体事件的list，传给页面显示
		ralist = new LinkedList<RiAnalysis>();
		// List riskListall = new LinkedList();
		List<AllAnalysisView> eventList = new LinkedList<AllAnalysisView>();
		String condition = getConditon();
		// System.out.println("riskname---------------"+riskname);
		// 找到存在事件的所有二级风险
		// System.out.println("condition********" + condition);
		// riskListall = allAnalysisViewDAO.findRiskByConditon(condition);
		//System.out.println(choosedId+"asdfjqqq");
		eventList = allAnalysisViewDAO.findDetailOrderByRisk(condition);
		reportList = new LinkedList<ReportViewNew>();
		// System.out.println(depListall.size());
		Map session2 = ServletActionContext.getContext().getSession();
		session2.remove("reportList");
		List<RuleFile> ruleFileList = new LinkedList<RuleFile>();
		List<RuleFile> flowRuleFileList = new LinkedList<RuleFile>();
		ruleFileList = allAnalysisViewNewDAO.findRuleFileList(condition);
		flowRuleFileList = allAnalysisViewNew2DAO.findFlowRuleFileList(condition);
		ruleFileList.addAll(flowRuleFileList);
		Collections.sort(ruleFileList, new Comparator<RuleFile>() {
			public int compare(RuleFile r1, RuleFile r2) {
				return Integer.valueOf(r2.getEventNum()) - Integer.valueOf(r1.getEventNum());
			}
		});
		if(!ruleFileList.isEmpty()){
			this.barPath_2=graphicNew.drawRuleBarChart(ruleFileList);

		}
		if (!eventList.isEmpty()) {
			getInfoRiskNew(eventList);
			session2.put("reportList", reportList);
			try {
				this.path = graphicNew.drawTableShadow(900, reportList);
				this.piePath = graphicNew.drawPieChart(800, 500, reportList);
			//this.barPath = graphicNew.drawBarChart(reportList);
				this.barPath = graphicNew.drawBarChart_111(reportList);
				// System.out.println("path*********"+ServletActionContext.getServletContext().getRealPath(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!reportList.isEmpty()) {
				this.areadetail = riskDetail(reportList);// 获取具体描述
				this.areadetail.replace("。", "<br>");
			} else
				this.areadetail = "";
			session2.put("areadetail", areadetail);
		}
		return "success";
	}

	// 得到每个二级风险的简要list(8列)
	@SuppressWarnings("unused")
	private void getInfoRiskByCondition(List riskListall, String condition) {
		// TODO Auto-generated method stub
		String riskName;
		String rtName;
		int allPro = 0;// 记录所有事件权值（发生概率）之和
		int allcount = 0; // 总计栏，包含的元素个数
		float allDegree = 0;// 记录所有风险事件影响程度加权之和
		int allvalue = 0; // 综合评定分子
		int days = 0;// 记录查询时间区间之间的天数

		int riskPro = 0;// 记录一个二级风险包含的所有风险事件权值（发生概率）之和
		int riskcount = 0;// 一个二级风险包含的元素个数
		float riskDegree = 0;// 记录一个二级风险包含的所有风险事件影响程度加权之和
		int riskvalue = 0;// 综合评定分子
		float proValue = 0;// 记录一个二级风险包含的所有风险事件发生概率具体值之和
		float reportFreq = 0;// 记录二级风险填报概率

		if (riskListall != null) {
			// 记录二级风险序号
			int i = 1;
			// 针对每个二级风险生成列表
			for (Object riskList : riskListall) {
				riskPro = 0;// 权值置0
				riskcount = 0;// 事件个数置0
				riskDegree = 0; // 风险影响程度加权和置0
				riskvalue = 0;// 评定值置0
				proValue = 0;// 发生概率具体值之和置0
				reportFreq = 0;

				Object[] risk = (Object[]) riskList;
				riskName = (String) risk[0];
				rtName = (String) risk[1];
				// 使得一级风险与二级风险一一对应
				// if(i%2==0){rtName+=" ";}

				// 遍历二级风险的部门
				List deplistall = allAnalysisViewDAO
						.findDepByRiskName(riskName);
				// System.out.println(risklist.size());
				// 记录部门序号
				int j = 0;
				if (deplistall != null) {
					// 针对每个二级风险生成列表
					// 遍历所有部门
					for (Object deplist : deplistall) {
						Object[] dep = (Object[]) deplist;
						// 每一个Risk，所包含的元素
						riskEventlistall = allAnalysisViewDAO
								.findDetailByCondition((String) dep[0],
										riskName, condition);
						// System.out.println(riskEventlistall);
						if (!riskEventlistall.isEmpty()) {
							// 记录满足查询条件的部门数
							j++;
							// 遍历一个Risk，所包含的元素
							for (int m = 0; m < riskEventlistall.size(); m++) {
								RiAnalysis ksone = new RiAnalysis();
								ksone.setRiNum(Integer.toString(i));
								ksone.setRiskName(riskName);
								ksone.setRtName(rtName);
								ksone.setDepNum(Integer.toString(j));
								ksone.setDepName(riskEventlistall.get(m)
										.getDepName());
								ksone
										.setReId(riskEventlistall.get(m)
												.getReId());
								ksone.setReEventname(riskEventlistall.get(m)
										.getReEventname());
								ksone.setRiAlldegree(dcmFmt
										.format(riskEventlistall.get(m)
												.getRiAlldegree()));
								ksone.setRiProdegree(Integer
										.toString(riskEventlistall.get(m)
												.getRiProdegree()));
								// 将概率等级值换算成具体概率值
								if (riskEventlistall.get(m).getRiProdegree()
										.equals(1)) {
									proValue += 2.5;
									reportFreq += 0.1;
								} else if (riskEventlistall.get(m)
										.getRiProdegree().equals(2)) {
									proValue += 17.5;
									reportFreq += 0.2;
								} else if (riskEventlistall.get(m)
										.getRiProdegree().equals(3)) {
									proValue += 40;
									reportFreq += 0.5;
								} else if (riskEventlistall.get(m)
										.getRiProdegree().equals(4)) {
									proValue += 72.5;
									reportFreq += 1;
								} else {
									proValue += 97.5;
									reportFreq += 2;
								}
								ksone.setRiKpi(riskEventlistall.get(m)
										.getRiKpi());
								ksone.setRiAllvalue(String.valueOf(dcmFmt
										.format(riskEventlistall.get(m)
												.getRiAllvalue())));
								if("2".equals(choosedId)){
									ksone.setRiEventDate(riskEventlistall.get(m)
											.getReModifydate());
								}else{
								ksone.setRiEventDate(riskEventlistall.get(m)
										.getReDate());}
								
								ksone
										.setReId(riskEventlistall.get(m)
												.getReId());
								ralist.add(ksone);
								riskPro += riskEventlistall.get(m)
										.getRiProdegree();
								riskcount++;
								riskDegree += riskEventlistall.get(m)
										.getRiAlldegree()
										* riskEventlistall.get(m)
												.getRiProdegree();
								riskvalue += riskEventlistall.get(m)
										.getRiAllvalue()
										* riskEventlistall.get(m)
												.getRiProdegree();
							}

						}

					}
				}

				// 每一个二级风险小计的位置
				// 小计功能
				RiAnalysis ksone = new RiAnalysis();
				ReportViewNew reportViewNew = new ReportViewNew();
				ksone.setRiNum(Integer.toString(i++) + " ");
				ksone.setRiskName(riskName + " ");
				reportViewNew.setRiskName(riskName);
				ksone.setRtName("小计 ");
				ksone.setDepNum("");
				ksone.setDepName(Integer.toString(j));
				ksone.setReEventname(Integer.toString(riskcount) + " ");
				reportViewNew.setRiskNum(riskcount + "");
				// 计算查询时间区间之间的天数
				try {
					days = daysBetween(dateFrom, dateTo);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				reportViewNew.setFreq(Math.round(365 * riskcount
						/ (double) days)
						+ "");
				reportViewNew.setReportFreq(Float.valueOf(dcmFmt
						.format(reportFreq / riskcount)));
				ksone.setRiAlldegree(String.valueOf(dcmFmt.format(riskDegree
						/ (double) (riskPro))));
				reportViewNew.setRiskValuex((int) Math.ceil(riskDegree
						/ (double) (riskPro)));
				// ksone.setRiProdegree(Integer.toString(riskPro/riskcount));
				proValue /= riskcount;
				// System.out.println(proValue);
				if (proValue > 0 && proValue <= 5) {
					ksone.setRiProdegree(1 + "");
					reportViewNew.setRiskPro(1);
				} else if (proValue > 5 && proValue <= 30) {
					ksone.setRiProdegree(2 + "");
					reportViewNew.setRiskPro(2);
				} else if (proValue > 30 && proValue <= 50) {
					ksone.setRiProdegree(3 + "");
					reportViewNew.setRiskPro(3);
				} else if (proValue > 50 && proValue <= 95) {
					ksone.setRiProdegree(4 + "");
					reportViewNew.setRiskPro(4);
				} else {
					ksone.setRiProdegree(5 + "");
					reportViewNew.setRiskPro(5);
				}

				// 综合评定
				if (riskcount == 0)
					ksone.setRiAllvalue("0.00");
				else {
					ksone.setRiAllvalue(String.valueOf(dcmFmt.format(riskvalue
							/ (double) (riskPro))));
				}
				reportViewNew.setRiskQueue(ksone.getRiNum());
				ralist.add(ksone);
				reportList.add(reportViewNew);

				allcount += riskcount;
				allPro += riskPro;
				allDegree += riskDegree;
				allvalue += riskvalue;

			}
			// 总计的位置

			// 小计功能
			RiAnalysis ksone = new RiAnalysis();
			ksone.setRiNum("总计");
			ksone.setRiskId("");
			ksone.setRiskName(Integer.toString(riskListall.size()));
			ksone.setReEventname(Integer.toString(allcount));
			ksone.setRtName("");
			ksone.setDepName("");
			// ksone.setRiAlldegree(String.valueOf(dcmFmt.format(allDegree
			// / (double) (allPro))));
			// 综合评定
			// if (allcount == 0)
			// ksone.setRiAllvalue("0.00");
			// else {
			// ksone.setRiAllvalue(String.valueOf(dcmFmt.format(allvalue
			// / (double) (allPro))));
			// }
			ralist.add(ksone);

		}

		// 数据存放在session中，便于导出excel
		String[][] dsarray = new String[ralist.size()][11];
		//System.out.println("ralist.size()----------" + ralist.size());
		for (int m = 0; m < ralist.size(); m++) {
			dsarray[m][0] = ralist.get(m).getRiNum().trim();
			dsarray[m][1] = ralist.get(m).getRtName().trim();
			dsarray[m][2] = ralist.get(m).getRiskName().trim();
			dsarray[m][3] = ralist.get(m).getDepName();
			dsarray[m][4] = ralist.get(m).getReId();
			dsarray[m][5] = ralist.get(m).getReEventname();
			dsarray[m][6] = ralist.get(m).getRiAlldegree();
			dsarray[m][7] = ralist.get(m).getRiProdegree();
			dsarray[m][8] = ralist.get(m).getRiKpi();
			dsarray[m][9] = ralist.get(m).getRiAllvalue();
			dsarray[m][10] = ralist.get(m).getRiEventDate();
		}
		Map session = ServletActionContext.getContext().getSession();
		session.put("exportGenList", dsarray);
	}

	// 得到每个二级风险的简要list(8列)
	private void getInfoRiskNew(List<AllAnalysisView> eventList) {
		// TODO Auto-generated method stub
		String depname1;
		String depname2;
		String riskname1;
		String riskname2;
		int days = 0;// 记录查询时间区间之间的天数

		int riskPro = 0;// 记录一个二级风险包含的所有风险事件权值（发生概率）之和
		int riskcount = 0;// 一个二级风险包含的元素个数
		float riskDegree = 0;// 记录一个二级风险包含的所有风险事件影响程度加权之和
		int riskvalue = 0;// 综合评定分子
		float proValue = 0;// 记录一个二级风险包含的所有风险事件发生概率具体值之和
		float reportFreq = 0;// 记录二级风险填报概率
		int risknum = 1; // 二级风险序号
		int allcount = 0;// 记录风险事件总数
		int depcount = 1;// 二级风险部门数
		int eventListnum = eventList.size();
		for (int m = 0; m < eventListnum; m++) {
			// 将当前记录放入list中
			RiAnalysis ksone = new RiAnalysis();
			ksone.setRiNum(risknum + "");
			ksone.setRtName(eventList.get(m).getRtName());
			ksone.setRiskName(eventList.get(m).getRiskName());
			ksone.setDepName(eventList.get(m).getDepName());
			ksone.setReId(eventList.get(m).getReId());
			ksone.setReEventname(eventList.get(m).getReEventname());
			ksone.setRiAlldegree(dcmFmt.format(eventList.get(m)
					.getRiAlldegree()));
			ksone.setRiProdegree(Integer.toString(eventList.get(m)
					.getRiProdegree()));
			ksone.setRiKpi(eventList.get(m).getRiKpi());
			ksone.setRiAllvalue(String.valueOf(dcmFmt.format(eventList.get(m)
					.getRiAllvalue())));
			if("2".equals(choosedId)){
				ksone.setRiEventDate(eventList.get(m).getReModifydate());
			}else{
			ksone.setRiEventDate(eventList.get(m).getReDate());
			}
			// 将概率等级值换算成具体概率值
			if (eventList.get(m).getRiProdegree().equals(1)) {
				proValue += 2.5;
				reportFreq += 0.1;
			} else if (eventList.get(m).getRiProdegree().equals(2)) {
				proValue += 17.5;
				reportFreq += 0.2;
			} else if (eventList.get(m).getRiProdegree().equals(3)) {
				proValue += 40;
				reportFreq += 0.5;
			} else if (eventList.get(m).getRiProdegree().equals(4)) {
				proValue += 72.5;
				reportFreq += 1;
			} else {
				proValue += 97.5;
				reportFreq += 2;
			}
			riskPro += eventList.get(m).getRiProdegree();
			riskDegree += eventList.get(m).getRiAlldegree()
					* eventList.get(m).getRiProdegree();
			riskvalue += eventList.get(m).getRiAllvalue()
					* eventList.get(m).getRiProdegree();
			ralist.add(ksone);
			riskcount++;
			allcount++;

			if (m < eventListnum - 1) {
				riskname1 = eventList.get(m).getRiskName().trim();
				riskname2 = eventList.get(m + 1).getRiskName().trim();
				if (riskname1.equals(riskname2)) {
					depname1 = eventList.get(m).getDepName().trim();
					depname2 = eventList.get(m + 1).getDepName().trim();
					if (!depname1.equals(depname2)) {
						depcount++;
					}
				} else {// 如果当前记录的二级风险名称与下一个记录不相同，则插入小计
					RiAnalysis ksone2 = new RiAnalysis();
					ReportViewNew reportViewNew = new ReportViewNew();
					ksone2.setRiNum(risknum + " ");
					ksone2.setRtName("小计");
					ksone2.setRiskName(riskname1 + " ");
					reportViewNew.setRiskName(riskname1);
					reportViewNew.setRiskNum(riskcount + "");
					reportViewNew.setRtName(eventList.get(m).getRtName());
					ksone2.setDepName(depcount + "");
					// ksone2.setReId("-");
					ksone2.setReEventname(riskcount + "");
					// 计算查询时间区间之间的天数
					try {
						days = daysBetween(dateFrom, dateTo);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					reportViewNew.setFreq(Math.round(365 * riskcount
							/ (double) days)
							+ "");
					reportViewNew.setReportFreq(Float.valueOf(dcmFmt
							.format(reportFreq / riskcount)));
					ksone2.setRiAlldegree(String.valueOf(dcmFmt
							.format(riskDegree / (double) (riskPro))));
					reportViewNew.setRiskValuex((int) Math.ceil(riskDegree
							/ (double) (riskPro)));
					proValue /= riskcount;
					if (proValue > 0 && proValue <= 5) {
						ksone2.setRiProdegree(1 + "");
						reportViewNew.setRiskPro(1);
					} else if (proValue > 5 && proValue <= 30) {
						ksone2.setRiProdegree(2 + "");
						reportViewNew.setRiskPro(2);
					} else if (proValue > 30 && proValue <= 50) {
						ksone2.setRiProdegree(3 + "");
						reportViewNew.setRiskPro(3);
					} else if (proValue > 50 && proValue <= 95) {
						ksone2.setRiProdegree(4 + "");
						reportViewNew.setRiskPro(4);
					} else {
						ksone2.setRiProdegree(5 + "");
						reportViewNew.setRiskPro(5);
					}
					// 综合评定
					if (riskcount == 0) {
						ksone2.setRiAllvalue("0.00");
						reportViewNew.setRiAllvalue(0.00);
					} else {
						ksone2.setRiAllvalue(String.valueOf(dcmFmt
								.format(riskvalue / (double) (riskPro))));
						reportViewNew.setRiAllvalue(Double.valueOf(String
								.valueOf(dcmFmt.format(riskvalue
										/ (double) (riskPro)))));
					}
					reportViewNew.setRiskQueue(ksone2.getRiNum());
					ralist.add(ksone2);
					reportList.add(reportViewNew);
					risknum++;
					depcount = 1;
					riskcount = 0;
					riskPro = 0;// 权值置0
					riskDegree = 0; // 风险影响程度加权和置0
					riskvalue = 0;// 评定值置0
					proValue = 0;// 发生概率具体值之和置0
					reportFreq = 0;
				}
			}
			if (m == eventListnum - 1)// 最后一条记录特殊处理，直接添加小计
			{
				RiAnalysis ksone2 = new RiAnalysis();
				ReportViewNew reportViewNew = new ReportViewNew();
				ksone2.setRiNum(risknum + " ");
				ksone2.setRtName("小计");
				ksone2.setRiskName(eventList.get(m).getRiskName() + " ");
				reportViewNew.setRiskName(eventList.get(m).getRiskName());
				reportViewNew.setRtName(eventList.get(m).getRtName());
				reportViewNew.setRiskNum(riskcount + "");
				ksone2.setDepName(depcount + "");
				// ksone2.setReId("-");
				ksone2.setReEventname(riskcount + "");
				// 计算查询时间区间之间的天数
				try {
					days = daysBetween(dateFrom, dateTo);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				reportViewNew.setFreq(Math.round(365 * riskcount
						/ (double) days)
						+ "");
				reportViewNew.setReportFreq(Float.valueOf(dcmFmt
						.format(reportFreq / riskcount)));
				ksone2.setRiAlldegree(String.valueOf(dcmFmt.format(riskDegree
						/ (double) (riskPro))));
				reportViewNew.setRiskValuex((int) Math.ceil(riskDegree
						/ (double) (riskPro)));
				proValue /= riskcount;
				if (proValue > 0 && proValue <= 5) {
					ksone2.setRiProdegree(1 + "");
					reportViewNew.setRiskPro(1);
				} else if (proValue > 5 && proValue <= 30) {
					ksone2.setRiProdegree(2 + "");
					reportViewNew.setRiskPro(2);
				} else if (proValue > 30 && proValue <= 50) {
					ksone2.setRiProdegree(3 + "");
					reportViewNew.setRiskPro(3);
				} else if (proValue > 50 && proValue <= 95) {
					ksone2.setRiProdegree(4 + "");
					reportViewNew.setRiskPro(4);
				} else {
					ksone2.setRiProdegree(5 + "");
					reportViewNew.setRiskPro(5);
				}
				// 综合评定
				if (riskcount == 0){
					ksone2.setRiAllvalue("0.00");
				    reportViewNew.setRiAllvalue(0.00);
						}
				else {
					ksone2.setRiAllvalue(String.valueOf(dcmFmt.format(riskvalue
							/ (double) (riskPro))));
					reportViewNew.setRiAllvalue(Double.valueOf(String
							.valueOf(dcmFmt.format(riskvalue
									/ (double) (riskPro)))));
				}
				reportViewNew.setRiskQueue(ksone2.getRiNum());
				ralist.add(ksone2);
				reportList.add(reportViewNew);
				depcount = 1;
				riskcount = 0;
				riskPro = 0;// 权值置0
				riskDegree = 0; // 风险影响程度加权和置0
				riskvalue = 0;// 评定值置0
				proValue = 0;// 发生概率具体值之和置0
				reportFreq = 0;
			}
		}
		// 加入总计
		RiAnalysis ksone2 = new RiAnalysis();
		ksone2.setRiNum("总计");
		ksone2.setRtName("");
		ksone2.setRiskName(risknum + "");
		ksone2.setDepName("");
		ksone2.setReEventname(allcount + "");
		ralist.add(ksone2);

		// 数据存放在session中，便于导出excel
		String[][] dsarray = new String[ralist.size()][11];
		//System.out.println("ralist.size()----------" + ralist.size());
		for (int m = 0; m < ralist.size(); m++) {
			dsarray[m][0] = ralist.get(m).getRiNum().trim();
			dsarray[m][1] = ralist.get(m).getRtName().trim();
			dsarray[m][2] = ralist.get(m).getRiskName().trim();
			dsarray[m][3] = ralist.get(m).getDepName();
			dsarray[m][4] = ralist.get(m).getReId();
			dsarray[m][5] = ralist.get(m).getReEventname();
			dsarray[m][6] = ralist.get(m).getRiAlldegree();
			dsarray[m][7] = ralist.get(m).getRiProdegree();
			dsarray[m][8] = ralist.get(m).getRiKpi();
			dsarray[m][9] = ralist.get(m).getRiAllvalue();
			dsarray[m][10] = ralist.get(m).getRiEventDate();
		}
		Map session = ServletActionContext.getContext().getSession();
		session.put("exportGenRiskList", dsarray);

	}

	public String GeneralAnalysisRiskAllByValue() {
		// 每个部门具体事件的list，传给页面显示
		ralist = new LinkedList<RiAnalysis>();
		// List riskListall = new LinkedList();
		List<AllAnalysisView> eventList = new LinkedList<AllAnalysisView>();
		String condition = getConditon();
		// 找到存在事件的所有部门
		// riskListall = allAnalysisViewDAO.findRiskByConditon(condition);
		// System.out.println(depListall.size());
		// if (!riskListall.isEmpty()) {
		// getInfoRiskAllByCondition(riskListall, condition);
		// }
		eventList = allAnalysisViewDAO.findDetailOrderByRisk(condition);
		if (!eventList.isEmpty()) {
			getInfoRiskAllNew(eventList);
		}
		return "success";
	}

	// 得到每个二级风险的详细list(24列)
	private void getInfoRiskAllByCondition(List riskListall, String condition) {
		// TODO Auto-generated method stub
		String riskName;
		String riskId;
		String rtName;
		int allPro = 0;// 记录所有事件权值（发生概率）之和
		int allcount = 0; // 总计栏，包含的元素个数
		float allDegree = 0;// 记录所有风险事件影响程度加权之和
		int allvalue = 0; // 综合评定分子

		int riskPro = 0;// 记录一个二级风险包含的所有风险事件权值（发生概率）之和
		int riskcount = 0;// 一个二级风险包含的元素个数
		float riskDegree = 0;// 记录一个二级风险包含的所有风险事件影响程度加权之和
		int riskvalue = 0;// 综合评定分子
		float proValue = 0;// 记录一个二级风险包含的所有风险事件发生概率具体值之和

		if (riskListall != null) {
			// 记录二级风险序号
			int i = 1;
			// 针对每个二级风险生成列表
			for (Object riskList : riskListall) {
				riskPro = 0;// 权值置0
				riskcount = 0;// 事件个数置0
				riskDegree = 0; // 风险影响程度加权和置0
				riskvalue = 0;// 评定值置0
				proValue = 0;// 发生概率具体值之和置0

				Object[] risk = (Object[]) riskList;
				riskName = (String) risk[0];
				rtName = (String) risk[1];
				// 使得一级风险与二级风险一一对应
				// if(i%2==0){rtName+=" ";}

				// 遍历二级风险的部门
				List deplistall = allAnalysisViewDAO
						.findDepByRiskName(riskName);
				// System.out.println(risklist.size());
				// 记录部门序号
				int j = 0;
				if (deplistall != null) {

					// 针对每个二级风险生成列表
					// 遍历所有部门
					for (Object deplist : deplistall) {
						Object[] dep = (Object[]) deplist;
						// 每一个Risk，所包含的元素
						riskEventlistall = allAnalysisViewDAO
								.findDetailByCondition((String) dep[0],
										riskName, condition);
						if (!riskEventlistall.isEmpty()) {
							j++;
							// 遍历一个Risk，所包含的元素
							for (int m = 0; m < riskEventlistall.size(); m++) {
								RiAnalysis ksone = new RiAnalysis();
								ksone.setRiNum(Integer.toString(i));
								ksone.setRiskId(riskEventlistall.get(m)
										.getRiskId());
								;
								ksone.setRiskName(riskName);
								ksone.setRtName(rtName);
								ksone.setDepNum(Integer.toString(j));
								ksone.setDepName(riskEventlistall.get(m)
										.getDepName());
								ksone.setReEventname(riskEventlistall.get(m)
										.getReEventname());
								ksone.setRiKpi(riskEventlistall.get(m)
										.getRiKpi());

								ksone.setRiProdegree(String
										.valueOf(riskEventlistall.get(m)
												.getRiProdegree()));
								// 将概率等级值换算成具体概率值
								if (riskEventlistall.get(m).getRiProdegree()
										.equals(1)) {
									proValue += 2.5;
								} else if (riskEventlistall.get(m)
										.getRiProdegree().equals(2)) {
									proValue += 17.5;
								} else if (riskEventlistall.get(m)
										.getRiProdegree().equals(3)) {
									proValue += 40;
								} else if (riskEventlistall.get(m)
										.getRiProdegree().equals(4)) {
									proValue += 72.5;
								} else {
									proValue += 97.5;
								}
								ksone.setRiFindegree(String
										.valueOf(riskEventlistall.get(m)
												.getRiFindegree()));
								ksone.setRiFamedegree(String
										.valueOf(riskEventlistall.get(m)
												.getRiFamedegree()));
								ksone.setRiLawdegree(String
										.valueOf(riskEventlistall.get(m)
												.getRiLawdegree()));
								ksone.setRiClidegree(String
										.valueOf(riskEventlistall.get(m)
												.getRiClidegree()));
								ksone.setRiEmpdegree(String
										.valueOf(riskEventlistall.get(m)
												.getRiEmpdegree()));
								ksone.setRiOpedegree(String
										.valueOf(riskEventlistall.get(m)
												.getRiOpedegree()));
								ksone.setRiSafedegree(String
										.valueOf(riskEventlistall.get(m)
												.getRiSafedegree()));
								ksone.setRiAlldegree(String.valueOf(dcmFmt
										.format(riskEventlistall.get(m)
												.getRiAlldegree())));

								ksone.setRiFinvalue(String
										.valueOf(riskEventlistall.get(m)
												.getRiFinvalue()));
								ksone.setRiFramevalue(String
										.valueOf(riskEventlistall.get(m)
												.getRiFramevalue()));
								ksone.setRiLawvalue(String
										.valueOf(riskEventlistall.get(m)
												.getRiLawvalue()));
								ksone.setRiClivalue(String
										.valueOf(riskEventlistall.get(m)
												.getRiClivalue()));
								ksone.setRiEmpvalue(String
										.valueOf(riskEventlistall.get(m)
												.getRiEmpvalue()));
								ksone.setRiOpevalue(String
										.valueOf(riskEventlistall.get(m)
												.getRiOpevalue()));
								ksone.setRiSafevalue(String
										.valueOf(riskEventlistall.get(m)
												.getRiSafevalue()));
								ksone.setRiAllvalue(String.valueOf(dcmFmt
										.format(riskEventlistall.get(m)
												.getRiAllvalue())));
								if("2".equals(choosedId)){
									ksone.setRiEventDate(riskEventlistall.get(m)
											.getReModifydate());
								}else{
								ksone.setRiEventDate(riskEventlistall.get(m)
										.getReDate());}
								ksone
										.setReId(riskEventlistall.get(m)
												.getReId());
								ralist.add(ksone);
								riskPro += riskEventlistall.get(m)
										.getRiProdegree();
								riskcount++;
								riskDegree += riskEventlistall.get(m)
										.getRiAlldegree()
										* riskEventlistall.get(m)
												.getRiProdegree();
								riskvalue += riskEventlistall.get(m)
										.getRiAllvalue()
										* riskEventlistall.get(m)
												.getRiProdegree();
							}

						}

					}
				}

				// 每一个二级风险小计的位置
				// 小计功能
				RiAnalysis ksone = new RiAnalysis();
				ksone.setRiNum(Integer.toString(i++) + " ");
				ksone.setRiskName(riskName + " ");
				ksone.setRtName("小计");
				ksone.setDepName(Integer.toString(j));
				ksone.setReEventname(Integer.toString(riskcount) + " ");
				ksone.setRiKpi("");
				ksone.setRiAlldegree(String.valueOf(dcmFmt.format(riskDegree
						/ (double) (riskPro))));
				ksone.setRiProdegree(Integer.toString(riskPro / riskcount));
				proValue /= riskcount;
				// System.out.println(proValue);
				if (proValue > 0 && proValue <= 5) {
					ksone.setRiProdegree(1 + "");
				} else if (proValue > 5 && proValue <= 30) {
					ksone.setRiProdegree(2 + "");
				} else if (proValue > 30 && proValue <= 50) {
					ksone.setRiProdegree(3 + "");
				} else if (proValue > 50 && proValue <= 95) {
					ksone.setRiProdegree(4 + "");
				} else {
					ksone.setRiProdegree(5 + "");
				}

				// 综合评定
				if (riskcount == 0)
					ksone.setRiAllvalue("0.00");
				else {
					ksone.setRiAllvalue(String.valueOf(dcmFmt.format(riskvalue
							/ (double) (riskPro))));
				}
				ralist.add(ksone);

				allcount += riskcount;
				allPro += riskPro;
				allDegree += riskDegree;
				allvalue += riskvalue;

			}
			// 总计的位置

			// 小计功能
			RiAnalysis ksone = new RiAnalysis();
			ksone.setRiNum("总计");
			ksone.setRiskId("");
			ksone.setRiskName(Integer.toString(riskListall.size()));
			ksone.setReEventname(Integer.toString(allcount));
			ksone.setRtName("");
			ksone.setDepName("");
			// ksone.setRiAlldegree(String.valueOf(dcmFmt.format(allDegree
			// / (double) (allPro))));
			// 综合评定
			// if (allcount == 0)
			// ksone.setRiAllvalue("0.00");
			// else {
			// ksone.setRiAllvalue(String.valueOf(dcmFmt.format(allvalue
			// / (double) (allPro))));
			// }
			ralist.add(ksone);

		}

		// 数据存放在session中，便于导出excel
		String[][] dsarray = new String[ralist.size()][25];
		for (int m = 0; m < ralist.size(); m++) {
			dsarray[m][0] = ralist.get(m).getRiNum().trim();
			dsarray[m][1] = ralist.get(m).getRtName().trim();
			dsarray[m][2] = ralist.get(m).getRiskName().trim();
			dsarray[m][3] = ralist.get(m).getDepName();
			dsarray[m][4] = ralist.get(m).getReId();
			dsarray[m][5] = ralist.get(m).getReEventname();
			dsarray[m][6] = ralist.get(m).getRiAlldegree();
			dsarray[m][7] = ralist.get(m).getRiProdegree();
			dsarray[m][8] = ralist.get(m).getRiKpi();
			dsarray[m][9] = ralist.get(m).getRiFindegree();
			dsarray[m][10] = ralist.get(m).getRiFamedegree();
			dsarray[m][11] = ralist.get(m).getRiLawdegree();
			dsarray[m][12] = ralist.get(m).getRiClidegree();
			dsarray[m][13] = ralist.get(m).getRiEmpdegree();
			dsarray[m][14] = ralist.get(m).getRiOpedegree();
			dsarray[m][15] = ralist.get(m).getRiSafedegree();
			dsarray[m][16] = ralist.get(m).getRiFinvalue();
			dsarray[m][17] = ralist.get(m).getRiFramevalue();
			dsarray[m][18] = ralist.get(m).getRiLawvalue();
			dsarray[m][19] = ralist.get(m).getRiLawvalue();
			dsarray[m][20] = ralist.get(m).getRiEmpvalue();
			dsarray[m][21] = ralist.get(m).getRiOpevalue();
			dsarray[m][22] = ralist.get(m).getRiSafevalue();
			dsarray[m][23] = ralist.get(m).getRiAllvalue();
			dsarray[m][24] = ralist.get(m).getRiEventDate();
		}
		Map session = ServletActionContext.getContext().getSession();
		session.put("exportGenList", dsarray);
	}

	// 得到每个二级风险的简要list(8列)
	private void getInfoRiskAllNew(List<AllAnalysisView> eventList) {
		// TODO Auto-generated method stub
		String depname1;
		String depname2;
		String riskname1;
		String riskname2;
		int days = 0;// 记录查询时间区间之间的天数

		int riskPro = 0;// 记录一个二级风险包含的所有风险事件权值（发生概率）之和
		int riskcount = 0;// 一个二级风险包含的元素个数
		float riskDegree = 0;// 记录一个二级风险包含的所有风险事件影响程度加权之和
		int riskvalue = 0;// 综合评定分子
		float proValue = 0;// 记录一个二级风险包含的所有风险事件发生概率具体值之和
		float reportFreq = 0;// 记录二级风险填报概率
		int risknum = 1; // 二级风险序号
		int allcount = 0;// 记录风险事件总数
		int depcount = 1;// 二级风险部门数

		int eventListnum = eventList.size();
		for (int m = 0; m < eventListnum; m++) {
			// 将当前记录放入list中
			RiAnalysis ksone = new RiAnalysis();
			ksone.setRiNum(risknum + "");
			ksone.setRtName(eventList.get(m).getRtName());
			ksone.setRiskName(eventList.get(m).getRiskName());
			ksone.setDepName(eventList.get(m).getDepName());
			ksone.setReId(eventList.get(m).getReId());
			ksone.setReEventname(eventList.get(m).getReEventname());
			ksone.setRiFindegree(String.valueOf(eventList.get(m)
					.getRiFindegree()));
			ksone.setRiFamedegree(String.valueOf(eventList.get(m)
					.getRiFamedegree()));
			ksone.setRiLawdegree(String.valueOf(eventList.get(m)
					.getRiLawdegree()));
			ksone.setRiClidegree(String.valueOf(eventList.get(m)
					.getRiClidegree()));
			ksone.setRiEmpdegree(String.valueOf(eventList.get(m)
					.getRiEmpdegree()));
			ksone.setRiOpedegree(String.valueOf(eventList.get(m)
					.getRiOpedegree()));
			ksone.setRiSafedegree(String.valueOf(eventList.get(m)
					.getRiSafedegree()));

			ksone.setRiFinvalue(String
					.valueOf(eventList.get(m).getRiFinvalue()));
			ksone.setRiFramevalue(String.valueOf(eventList.get(m)
					.getRiFramevalue()));
			ksone.setRiLawvalue(String
					.valueOf(eventList.get(m).getRiLawvalue()));
			ksone.setRiClivalue(String
					.valueOf(eventList.get(m).getRiClivalue()));
			ksone.setRiEmpvalue(String
					.valueOf(eventList.get(m).getRiEmpvalue()));
			ksone.setRiOpevalue(String
					.valueOf(eventList.get(m).getRiOpevalue()));
			ksone.setRiSafevalue(String.valueOf(eventList.get(m)
					.getRiSafevalue()));
			ksone.setRiAlldegree(dcmFmt.format(eventList.get(m)
					.getRiAlldegree()));
			ksone.setRiProdegree(Integer.toString(eventList.get(m)
					.getRiProdegree()));
			ksone.setRiKpi(eventList.get(m).getRiKpi());
			ksone.setRiAllvalue(String.valueOf(dcmFmt.format(eventList.get(m)
					.getRiAllvalue())));
			if("2".equals(choosedId)){
				ksone.setRiEventDate(eventList.get(m).getReModifydate());
			}else{
			ksone.setRiEventDate(eventList.get(m).getReDate());}
			// 将概率等级值换算成具体概率值
			if (eventList.get(m).getRiProdegree().equals(1)) {
				proValue += 2.5;
				reportFreq += 0.1;
			} else if (eventList.get(m).getRiProdegree().equals(2)) {
				proValue += 17.5;
				reportFreq += 0.2;
			} else if (eventList.get(m).getRiProdegree().equals(3)) {
				proValue += 40;
				reportFreq += 0.5;
			} else if (eventList.get(m).getRiProdegree().equals(4)) {
				proValue += 72.5;
				reportFreq += 1;
			} else {
				proValue += 97.5;
				reportFreq += 2;
			}
			riskPro += eventList.get(m).getRiProdegree();
			riskDegree += eventList.get(m).getRiAlldegree()
					* eventList.get(m).getRiProdegree();
			riskvalue += eventList.get(m).getRiAllvalue()
					* eventList.get(m).getRiProdegree();
			ralist.add(ksone);
			riskcount++;
			allcount++;

			if (m < eventListnum - 1) {
				riskname1 = eventList.get(m).getRiskName().trim();
				riskname2 = eventList.get(m + 1).getRiskName().trim();
				if (riskname1.equals(riskname2)) {
					depname1 = eventList.get(m).getDepName().trim();
					depname2 = eventList.get(m + 1).getDepName().trim();
					if (!depname1.equals(depname2)) {
						depcount++;
					}
				} else {// 如果当前记录的二级风险名称与下一个记录不相同，则插入小计
					RiAnalysis ksone2 = new RiAnalysis();
					ksone2.setRiNum(risknum + " ");
					ksone2.setRtName("小计");
					ksone2.setRiskName(riskname1 + " ");
					ksone2.setDepName(depcount + "");
					ksone2.setReEventname(riskcount + "");
					// 计算查询时间区间之间的天数
					try {
						days = daysBetween(dateFrom, dateTo);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ksone2.setRiAlldegree(String.valueOf(dcmFmt
							.format(riskDegree / (double) (riskPro))));
					proValue /= riskcount;
					if (proValue > 0 && proValue <= 5) {
						ksone2.setRiProdegree(1 + "");
					} else if (proValue > 5 && proValue <= 30) {
						ksone2.setRiProdegree(2 + "");
					} else if (proValue > 30 && proValue <= 50) {
						ksone2.setRiProdegree(3 + "");
					} else if (proValue > 50 && proValue <= 95) {
						ksone2.setRiProdegree(4 + "");
					} else {
						ksone2.setRiProdegree(5 + "");
					}
					// 综合评定
					if (riskcount == 0)
						ksone2.setRiAllvalue("0.00");
					else {
						ksone2.setRiAllvalue(String.valueOf(dcmFmt
								.format(riskvalue / (double) (riskPro))));
					}
					ralist.add(ksone2);
					risknum++;
					depcount = 1;
					riskcount = 0;
					riskPro = 0;// 权值置0
					riskDegree = 0; // 风险影响程度加权和置0
					riskvalue = 0;// 评定值置0
					proValue = 0;// 发生概率具体值之和置0
					reportFreq = 0;
				}
			}
			if (m == eventListnum - 1)// 最后一条记录特殊处理，直接添加小计
			{
				RiAnalysis ksone2 = new RiAnalysis();
				ReportViewNew reportViewNew = new ReportViewNew();
				ksone2.setRiNum(risknum + " ");
				ksone2.setRtName("小计");
				ksone2.setRiskName(eventList.get(m).getRiskName() + " ");
				reportViewNew.setRiskName(eventList.get(m).getRiskName());
				reportViewNew.setRiskNum(riskcount + "");
				ksone2.setDepName(depcount + "");
				ksone2.setReEventname(riskcount + "");
				// 计算查询时间区间之间的天数
				try {
					days = daysBetween(dateFrom, dateTo);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				reportViewNew.setFreq(Math.round(365 * riskcount
						/ (double) days)
						+ "");
				reportViewNew.setReportFreq(Float.valueOf(dcmFmt
						.format(reportFreq / riskcount)));
				ksone2.setRiAlldegree(String.valueOf(dcmFmt.format(riskDegree
						/ (double) (riskPro))));
				reportViewNew.setRiskValuex((int) Math.ceil(riskDegree
						/ (double) (riskPro)));
				proValue /= riskcount;
				if (proValue > 0 && proValue <= 5) {
					ksone2.setRiProdegree(1 + "");
					reportViewNew.setRiskPro(1);
				} else if (proValue > 5 && proValue <= 30) {
					ksone2.setRiProdegree(2 + "");
					reportViewNew.setRiskPro(2);
				} else if (proValue > 30 && proValue <= 50) {
					ksone2.setRiProdegree(3 + "");
					reportViewNew.setRiskPro(3);
				} else if (proValue > 50 && proValue <= 95) {
					ksone2.setRiProdegree(4 + "");
					reportViewNew.setRiskPro(4);
				} else {
					ksone2.setRiProdegree(5 + "");
					reportViewNew.setRiskPro(5);
				}
				// 综合评定
				if (riskcount == 0)
					ksone2.setRiAllvalue("0.00");
				else {
					ksone2.setRiAllvalue(String.valueOf(dcmFmt.format(riskvalue
							/ (double) (riskPro))));
				}
				reportViewNew.setRiskQueue(ksone2.getRiNum());
				ralist.add(ksone2);
				reportList.add(reportViewNew);
				depcount = 1;
				riskcount = 0;
				riskPro = 0;// 权值置0
				riskDegree = 0; // 风险影响程度加权和置0
				riskvalue = 0;// 评定值置0
				proValue = 0;// 发生概率具体值之和置0
				reportFreq = 0;
			}
		}
		// 加入总计
		RiAnalysis ksone2 = new RiAnalysis();
		ksone2.setRiNum("总计");
		ksone2.setRtName("");
		ksone2.setRiskName(risknum + "");
		ksone2.setDepName("");
		ksone2.setReEventname(allcount + "");
		ralist.add(ksone2);

		// 数据存放在session中，便于导出excel
		String[][] dsarray = new String[ralist.size()][25];
		for (int m = 0; m < ralist.size(); m++) {
			dsarray[m][0] = ralist.get(m).getRiNum().trim();
			dsarray[m][1] = ralist.get(m).getRtName().trim();
			dsarray[m][2] = ralist.get(m).getRiskName().trim();
			dsarray[m][3] = ralist.get(m).getDepName();
			dsarray[m][4] = ralist.get(m).getReId();
			dsarray[m][5] = ralist.get(m).getReEventname();
			dsarray[m][6] = ralist.get(m).getRiAlldegree();
			dsarray[m][7] = ralist.get(m).getRiProdegree();
			dsarray[m][8] = ralist.get(m).getRiKpi();
			dsarray[m][9] = ralist.get(m).getRiFindegree();
			dsarray[m][10] = ralist.get(m).getRiFamedegree();
			dsarray[m][11] = ralist.get(m).getRiLawdegree();
			dsarray[m][12] = ralist.get(m).getRiClidegree();
			dsarray[m][13] = ralist.get(m).getRiEmpdegree();
			dsarray[m][14] = ralist.get(m).getRiOpedegree();
			dsarray[m][15] = ralist.get(m).getRiSafedegree();
			dsarray[m][16] = ralist.get(m).getRiFinvalue();
			dsarray[m][17] = ralist.get(m).getRiFramevalue();
			dsarray[m][18] = ralist.get(m).getRiLawvalue();
			dsarray[m][19] = ralist.get(m).getRiLawvalue();
			dsarray[m][20] = ralist.get(m).getRiEmpvalue();
			dsarray[m][21] = ralist.get(m).getRiOpevalue();
			dsarray[m][22] = ralist.get(m).getRiSafevalue();
			dsarray[m][23] = ralist.get(m).getRiAllvalue();
			dsarray[m][24] = ralist.get(m).getRiEventDate();
		}
		Map session = ServletActionContext.getContext().getSession();
		session.put("exportGenRiskAllList", dsarray);

	}

	// 按二级风险名称查询报表导出(24列)
	public String exportExcelAllByRisk() {
		Map session = ActionContext.getContext().getSession();
		// dsarray=(String[][])session.get("exportDepList");
		if (session.get("exportGenRiskAllList") == null)
			return "fail";
		else {
			String str = "一般风险分析按风险类型列表统计-详细" + "    综合评定:" + riAllValueFrom
					+ "至" + riAllValueTo + "    影响程度:" + riDegreeFrom + "至"
					+ riDegreeTo + "    发生概率:" + riProDegree;
			if (riskname == null || riskname.equals("请选择")) {
				str += " 二级风险:全部";
			} else {
				str += " 二级风险:" + riskname;
			}
			str += "    录入时间:" + dateFrom + "至" + dateTo;
			ExcelReportAction ex = new ExcelReportAction();
			ex
					.ReportExcel("风险分析列表统计查询", "GenAnaAllRiskTemplate",
							(String[][]) session.get("exportGenRiskAllList"),
							3, 4, str);// 3表示从第三行开始，5表示从第五列开始合并，str是查询条件
			return "success";
		}
	}

	private String getConditon() {
		// TODO Auto-generated method stub
		String str = "";
		String datem="";
		if (riAllValueFrom != null && riAllValueTo != null
				&& !riAllValueFrom.equals("") && !riAllValueTo.equals("")) {
			str += "model.riAllvalue>=" + riAllValueFrom
					+ " and model.riAllvalue<=" + riAllValueTo;
		} else if (riAllValueFrom != null && !riAllValueFrom.equals("")) {
			str += "model.riAllvalue>=" + riAllValueFrom;
		} else if (riAllValueTo != null && !riAllValueTo.equals("")) {
			str += "model.riAllvalue<=" + riAllValueTo;
		} else {
			str += "";
		}

		if (riDegreeFrom != null && riDegreeTo != null
				&& !riDegreeFrom.equals("") && !riDegreeTo.equals("")) {
			if (!str.equals("")) {
				str += " and ";
			}
			str += "model.riAlldegree>=" + riDegreeFrom
					+ " and model.riAlldegree<=" + riDegreeTo;
		} else if (riDegreeFrom != null && !riDegreeFrom.equals("")) {
			if (!str.equals("")) {
				str += " and ";
			}
			str += "model.riAlldegree>=" + riDegreeFrom;
		} else if (riDegreeTo != null && !riDegreeTo.equals("")) {
			if (!str.equals("")) {
				str += " and ";
			}
			str += "model.riAlldegree<=" + riDegreeTo;
		} else {
			str += "";
		}

		if (riProDegree == null || riProDegree.equals("请选择")) {
			str += "";
		} else if (riProDegree.equals("1(0%~5%)")) {
			if (!str.equals("")) {
				str += " and ";
			}
			str += "model.riProdegree = 1";
		} else if (riProDegree.equals("2(5%~30%)")) {
			if (!str.equals("")) {
				str += " and ";
			}
			str += "model.riProdegree = 2";
		} else if (riProDegree.equals("3(30%~50%)")) {
			if (!str.equals("")) {
				str += " and ";
			}
			str += "model.riProdegree = 3";
		} else if (riProDegree.equals("4(50%~95%)")) {
			if (!str.equals("")) {
				str += " and ";
			}
			str += "model.riProdegree = 4";
		} else {
			if (!str.equals("")) {
				str += " and ";
			}
			str += "model.riProdegree = 5";
		}

		if (riDepName == null || riDepName.equals("请选择")) {
			str += "";
		} else {
			if (!str.equals("")) {
				str += " and ";
			}
			str += "model.depName='" + riDepName + "'";
		}

		if (risktype == null || risktype.equals("none1")) {
			str += "";
		} else {
			if (!str.equals("")) {
				str += " and ";
			}
			str += "model.rtId='" + risktype + "'";
		}

		if (riskname == null || riskname.equals("请选择")) {
			str += "";
		} else {
			if (!str.equals("")) {
				str += " and ";
			}
			str += "model.riskName='" + riskname + "'";
		}
		if("2".equals(choosedId)){
			datem="model.reModifydate";
		}else{
			datem="model.reDate";
		}
		if (dateFrom != null && dateTo != null && !dateFrom.equals("")
				&& !dateTo.equals("")) {
			if (!str.equals("")) {
				str += " and ";
			}
			str += datem+" between '" + dateFrom + " 00:00:00"
					+ "' and '" + dateTo + " 23:59:59" + "'";
		} else if (dateFrom != null && !dateFrom.equals("")) {
			if (!str.equals("")) {
				str += " and ";
			}
			str += datem+">='" + dateFrom + " 00:00:00" + "'";
		} else if (dateTo != null && !dateTo.equals("")) {
			if (!str.equals("")) {
				str += " and ";
			}
			str += datem+"<='" + dateTo + " 23:59:59" + "'";
		} else {
			str += "";
		}

		if (!str.equals(""))
			str = " where " + str + " " + " and model.riAllvalue!=0 ";
		return str;
	}

	public void getProDegreeList() {
		allProDegreeList = new LinkedList<String>();
		allProDegreeList.add("请选择");
		allProDegreeList.add("1(0%~5%)");
		allProDegreeList.add("2(5%~30%)");
		allProDegreeList.add("3(30%~50%)");
		allProDegreeList.add("4(50%~95%)");
		allProDegreeList.add("5(95%~100%)");
	}

	// 得到下拉框，风险类型下拉框allrtList
	public String getDropDownList() {
		try {
			// 风险类型下拉框allrtList
			RiskType rt1 = new RiskType();
			rt1.setRtId("none1");
			rt1.setRtName("请选择");
			rt1.setRtRemark("请选择");
			this.allrtList = new LinkedList<RiskType>();
			this.allrtList.add(rt1);
			this.rtList = new LinkedList<RiskType>();
			this.rtList = this.getRiskTypeDao().findAll();
			//System.out.println("------------" + rtList.size());
			this.allrtList.addAll(this.rtList);

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// public void getReportImg() throws IOException{
	// String condition=getConditon();
	// reportList=getAllAnalysisViewDAO().findReportList(condition);
	// // for(ReportView reportView:reportList){
	// // System.out.println(reportView.getId().getRiskName());
	// // }
	// // System.out.println(reportList.size());
	// // 获取分区图
	// this.path = graphicNew.drawTableShadow(1000, 1000, reportList);
	// // HttpServletResponse response = ServletActionContext.getResponse();
	// // writeToResponse(response, path);
	//		
	// }

	public void writeToResponse(HttpServletResponse response, String str) {
		PrintWriter out = null;
		response.setContentType("text/html;charset=utf-8");
		try {
			out = response.getWriter();
			out.print(str);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	// 全面风险事件坐标图生成
	public String exportWord() {

		rList = new LinkedList<Risk>();// 初始化链表
		pList = new LinkedList<Probability>();
		iList = new LinkedList<Operation>();

		rList = riskDAO.findAll();
		pList = probabilityDao.findAll();
		iList = operationDao.findAll();// 影响程度列表
		String condition = getConditon();

		String FileDefaultName = year + "年度风险坐标图";
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		FileDefaultName += df.format(dt);

		try {
			createWordFile(FileDefaultName, rList, pList, iList, reportList);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 导出统计图
	public String exportWordNew() {
		String FileDefaultName = year + "年度风险统计图";
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		FileDefaultName += df.format(dt);

		try {
			createWordFileNew(FileDefaultName);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String exportWord2() {

		List<RiAnalysis> riskDiskList = getRiskDist();
		String condition = getConditon();

		List fileList = allAnalysisViewNewDAO.findFile(condition);
		String FileDefaultName = year + "年度风险统计报告";
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		FileDefaultName += df.format(dt);

		try {
			createWordFile2(FileDefaultName, riskDiskList, fileList);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 获得风险分布情况
	// public List<RiAnalysis> getRiskDist() {
	// List<RiAnalysis> riskDist = new LinkedList<RiAnalysis>();
	// String condition = getConditon();
	// String riskName;
	// String rtName;
	//
	// int riskPro = 0;// 记录一个二级风险包含的所有风险事件权值（发生概率）之和
	// int riskcount = 0;// 一个二级风险包含的元素个数
	// int riskvalue = 0;// 综合评定分子
	// int rtcount = 0;// 一个一级风险包含的元素个数
	// double rtvalue = 0;// 综合评定分子
	// int allcount = 0;
	// double allvalue = 0;
	//
	// List rtListAll = allAnalysisViewDAO.findRiskTypeByConditon(condition);
	//
	// if (rtList != null) {
	// // 针对每个一级风险
	// for (Object rtList : rtListAll) {
	// rtcount = 0;// 事件个数置0
	// rtvalue = 0;// 评定值置0
	// rtName = (String) rtList;
	// // 遍历一级风险的二级风险
	// List riskListall = allAnalysisViewDAO.findRiskByrtName(rtName,
	// condition);
	// if (riskListall != null) {
	// // 针对每个二级风险
	// for (Object riskList : riskListall) {
	// riskcount = 0;
	// riskvalue = 0;
	// riskPro = 0;
	// riskName = (String) riskList;
	// // 每一个Risk，所包含的元素
	// riskEventlistall = allAnalysisViewDAO
	// .findDetailByRiskName(riskName, condition);
	// if (!riskEventlistall.isEmpty()) {
	// // 遍历一个Risk，所包含的元素
	// for (int m = 0; m < riskEventlistall.size(); m++) {
	// riskcount++;
	// riskPro += riskEventlistall.get(m)
	// .getRiProdegree();
	// riskvalue += riskEventlistall.get(m)
	// .getRiAllvalue()
	// * riskEventlistall.get(m)
	// .getRiProdegree();
	// }
	//
	// }
	// // 记录二级风险信息
	// rtcount += riskcount;
	// RiAnalysis ksone = new RiAnalysis();
	// ksone.setRiskName(riskName);
	// ksone.setReEventname(Integer.toString(riskcount));
	// // 综合评定
	// if (riskcount == 0)
	// ksone.setRiAllvalue("0.00");
	// else {
	// ksone.setRiAllvalue(String.valueOf(dcmFmt
	// .format(riskvalue / (double) (riskPro))));
	// }
	// rtvalue += riskcount
	// * (Double.valueOf(ksone.getRiAllvalue()));
	// riskDist.add(ksone);
	// }
	//
	// }
	//
	// // 记录一级风险信息
	// RiAnalysis ksone = new RiAnalysis();
	// ksone.setRiskName(rtName);
	// ksone.setReEventname(Integer.toString(rtcount));
	// ksone.setRiAllvalue(String.valueOf(dcmFmt.format(rtvalue
	// / rtcount)));
	// riskDist.add(ksone);
	//
	// allcount += rtcount;
	// allvalue += rtcount * (Double.valueOf(ksone.getRiAllvalue()));
	//
	// }
	// // 总计的位置
	// RiAnalysis ksone = new RiAnalysis();
	// ksone.setRiskName("总计");
	// ksone.setReEventname(Integer.toString(allcount));
	// ksone.setRiAllvalue(String.valueOf(dcmFmt.format(allvalue
	// / allcount)));
	// riskDist.add(ksone);
	//
	// }
	// System.out.println("riskDist.size()-------" + riskDist.size());
	// return riskDist;
	// }

	@SuppressWarnings("unchecked")
	public List<RiAnalysis> getRiskDist() {
		List<RiAnalysis> riskDist = new LinkedList<RiAnalysis>();
		Map session = ActionContext.getContext().getSession();
		reportList = (List) session.get("reportList");
		String rtname1="";
		String rtname2="";
		int rtcount=0;
		double rtvalue=0;
		int allcount=0;
		double allvalue=0;
		int reportListSize=reportList.size();
		RiAnalysis riAnalysis;
		for (int i = 0; i < reportListSize; i++) {
               riAnalysis=new RiAnalysis();
               riAnalysis.setRiskName(reportList.get(i).getRiskName());
               riAnalysis.setReEventname(reportList.get(i).getRiskNum());
               riAnalysis.setRiAllvalue(reportList.get(i).getRiAllvalue()+"");
               riskDist.add(riAnalysis);
               rtcount+=Integer.valueOf(reportList.get(i).getRiskNum());
               allcount+=Integer.valueOf(reportList.get(i).getRiskNum());
               rtvalue+=reportList.get(i).getRiAllvalue()*Integer.valueOf(reportList.get(i).getRiskNum());
               allvalue+=reportList.get(i).getRiAllvalue()*Integer.valueOf(reportList.get(i).getRiskNum());
               if(i<reportListSize-1){
            	   rtname1=reportList.get(i).getRtName();
            	   rtname2=reportList.get(i+1).getRtName();
            	   if(!rtname1.equals(rtname2)){
            		   riAnalysis=new RiAnalysis();
                       riAnalysis.setRiskName(reportList.get(i).getRtName());
                       riAnalysis.setReEventname(rtcount+"");
                       riAnalysis.setRiAllvalue(String.valueOf(dcmFmt.format(rtvalue
                    			 / rtcount)));
                       riskDist.add(riAnalysis);
                       rtcount=0;
                       rtvalue=0;
            	   }
               }else{
            	   riAnalysis=new RiAnalysis();
                   riAnalysis.setRiskName(reportList.get(i).getRtName());
                   riAnalysis.setReEventname(rtcount+"");
                   riAnalysis.setRiAllvalue(String.valueOf(dcmFmt.format(rtvalue
                			 / rtcount)));
                   riskDist.add(riAnalysis);
               }
		}
		 riAnalysis=new RiAnalysis();
         riAnalysis.setRiskName("总计");
         riAnalysis.setReEventname(allcount+"");
         riAnalysis.setRiAllvalue(String.valueOf(dcmFmt.format(allvalue
      			 / allcount)));
         riskDist.add(riAnalysis);
		return riskDist;
	}

	/*
	 * 生成word文档
	 */
	public boolean createWordFileNew(String FileDefaultName)
			throws DocumentException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		Users us = (Users) httpSession.getAttribute("loginUser");
		String userId = us.getUserId();
		FileDefaultName = new String(FileDefaultName.getBytes(), "iso8859-1");// 解决中文
		Map session = ActionContext.getContext().getSession();
		reportList = (List) session.get("reportList");
		riskDiskList = (List) session.get("riskDiskList");
		List<RuleFile> ruleFileList = (List) session.get("ruleFileList");
		if(reportList!=null&&!reportList.isEmpty()){
		// 文件名问题
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream os = response.getOutputStream();
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename="
				+ FileDefaultName + ".doc");
		response.setContentType("application/msword");

		// 设置纸张大小
		Document document = new Document(PageSize.A4);
		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
		RtfWriter2.getInstance(document, os);
		document.open();
		// 设置中文字体
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		// 标题字体风格
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);
		// 正文字体风格
		Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
		Paragraph title = new Paragraph("中南电力设计院风险统计图");
		// 设置标题格式对齐方式
		title.setAlignment(Element.ALIGN_CENTER);
		title.setFont(titleFont);
		document.add(title);

		String contextString1 = "将我院风险评估的定性、定量风险得出的风险坐标图分A、B、C三个区域，如图1所示。";
		Paragraph context1 = new Paragraph(contextString1);
		document.add(context1);
		String contextString2 = "风险概率统计表";
		Paragraph context2 = new Paragraph(contextString2);
		context2.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context2);
		Table rTable = new Table(5);

		int rwidth[] = { 10, 40, 10, 20, 20 };
		rTable.setWidths(rwidth);// 设置每列所占比例
		rTable.setWidth(100); // 占页面宽度 90%
		rTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		rTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		rTable.setAutoFillEmptyCells(true); // 自动填满
		rTable.setBorderWidth(1); // 边框宽度
		rTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		rTable.setPadding(2);// 衬距，看效果就知道什么意思了
		rTable.setSpacing(0);// 即单元格之间的间距
		rTable.setBorder(2);// 边框
		// 设置风险种类表格的表头
		Cell rhaderCell1 = new Cell("风险序号");
		rhaderCell1.setHeader(true);
		rTable.addCell(rhaderCell1);
		Cell rhaderCell2 = new Cell("风险名称");
		rhaderCell2.setHeader(true);
		rTable.addCell(rhaderCell2);
		Cell rhaderCell3 = new Cell("风险事件数");
		rhaderCell3.setHeader(true);
		rTable.addCell(rhaderCell3);
		Cell rhaderCell4 = new Cell("实时概率（次/年）");
		rhaderCell4.setHeader(true);
		rTable.addCell(rhaderCell4);
		Cell rhaderCell5 = new Cell("填报概率（次/年）");
		rhaderCell5.setHeader(true);
		rTable.addCell(rhaderCell5);
		rTable.endHeaders();// 结束表头

		for (int i = 0; i < reportList.size(); i++) {
			int riskPro=reportList.get(i).getRiskPro();
			int riskValuex=reportList.get(i).getRiskValuex();
			Cell rcell1 = new Cell(reportList.get(i).getRiskQueue());
			Cell rcell2 = new Cell(reportList.get(i).getRiskName());
			Cell rcell3 = new Cell(reportList.get(i).getRiskNum());
			Cell rcell4 = new Cell(reportList.get(i).getFreq());
			Cell rcell5 = new Cell(reportList.get(i).getReportFreq() + "");
			if(riskPro <= 2 && riskValuex <= 2){
				rcell1.setBackgroundColor(new Color(198,249,184));
				rcell2.setBackgroundColor(new Color(198,249,184));
				rcell3.setBackgroundColor(new Color(198,249,184));
				rcell4.setBackgroundColor(new Color(198,249,184));
				rcell5.setBackgroundColor(new Color(198,249,184));
			}else if(riskPro >= 4 && riskValuex >= 4){
				rcell1.setBackgroundColor(new Color(248,195,195));
				rcell2.setBackgroundColor(new Color(248,195,195));
				rcell3.setBackgroundColor(new Color(248,195,195));
				rcell4.setBackgroundColor(new Color(248,195,195));
				rcell5.setBackgroundColor(new Color(248,195,195));
			}else{
				rcell1.setBackgroundColor(new Color(253,236,182));
				rcell2.setBackgroundColor(new Color(253,236,182));
				rcell3.setBackgroundColor(new Color(253,236,182));
				rcell4.setBackgroundColor(new Color(253,236,182));
				rcell5.setBackgroundColor(new Color(253,236,182));
			}
			rTable.setAlignment(Element.ALIGN_CENTER);
			rTable.addCell(rcell1);
			rTable.addCell(rcell2);
			rTable.addCell(rcell3);
			rTable.addCell(rcell4);
			rTable.addCell(rcell5);
		}
		document.add(rTable);
		document.add(new Paragraph("\n"));
		String reportImgShadowPath = ServletActionContext.getServletContext()
				.getRealPath(this.path);
		// System.out.println("savePath****"+savePath);
		Image img = Image.getInstance(reportImgShadowPath);
		img.setAbsolutePosition(0, 0);
		img.setAlignment(Image.MIDDLE);// 设置图片显示位置
		img.scaleAbsolute(600, 600);// 直接设定显示尺寸
		img.scalePercent(45);// 表示显示的大小为原尺寸的50%
		document.add(img);
		String contextString3 = "图1：风险坐标图分区图";
		Paragraph context3 = new Paragraph(contextString3);
		context3.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context3);
		String contextString4 = riskDetail(reportList);// 获取具体描述
		Paragraph context4 = new Paragraph(contextString4);
		document.add(context4);
		String sectionPiechartPath = ServletActionContext.getServletContext()
				.getRealPath(this.piePath);
		System.out.println("sectionPiechartPath****" + sectionPiechartPath);
		img = Image.getInstance(sectionPiechartPath);
		img.setAbsolutePosition(0, 0);
		img.setAlignment(Image.MIDDLE);// 设置图片显示位置
		img.scaleAbsolute(600, 600);// 直接设定显示尺寸
		img.scalePercent(60);// 表示显示的大小为原尺寸的50%
		document.add(img);
		String contextString5 = "图2：分区二级风险数比例饼状图";
		Paragraph context5 = new Paragraph(contextString5);
		context5.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context5);
		String riskNumBarchartPath = ServletActionContext.getServletContext()
				.getRealPath(this.barPath);
		img = Image.getInstance(riskNumBarchartPath);
		img.setAbsolutePosition(0, 0);
		img.setAlignment(Image.MIDDLE);// 设置图片显示位置
		img.scalePercent(50);// 表示显示的大小为原尺寸的50%
		document.add(img);
		String contextString6 = "图3：二级风险事件数柱状图";
		Paragraph context6 = new Paragraph(contextString6);
		context6.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context6);
		rTable = new Table(3);
		int rwidth2[] = { 30, 50, 30 };
		rTable.setWidths(rwidth2);// 设置每列所占比例
		rTable.setWidth(100); // 占页面宽度 90%
		rTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		rTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		rTable.setAutoFillEmptyCells(true); // 自动填满
		rTable.setBorderWidth(1); // 边框宽度
		rTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		rTable.setPadding(2);// 衬距，看效果就知道什么意思了
		rTable.setSpacing(0);// 即单元格之间的间距
		rTable.setBorder(2);// 边框
		String contextString7 = "表一：风险分布情况及评定情况";
		Paragraph context7 = new Paragraph(contextString7);
		context7.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context7);
		// 设置风险种类表格的表头
		rhaderCell1 = new Cell("风险类型");
		rhaderCell1.setHeader(true);
		// rhaderCell1.setBackgroundColor(Color.GRAY);
		rTable.addCell(rhaderCell1);
		rhaderCell2 = new Cell("风险事件数");
		rhaderCell2.setHeader(true);
		rTable.addCell(rhaderCell2);
		rhaderCell3 = new Cell("综合评定平均值");
		rhaderCell3.setHeader(true);
		rTable.addCell(rhaderCell3);
		rTable.endHeaders();// 结束表头

		for (int i = 0; i < riskDiskList.size(); i++) {

			Cell rcell1 = new Cell(riskDiskList.get(i).getRiskName());
			Cell rcell2 = new Cell(riskDiskList.get(i).getReEventname());
			Cell rcell3 = new Cell(riskDiskList.get(i).getRiAllvalue());
			if (isRiskType(riskDiskList.get(i).getRiskName())) {
				rcell1.setBackgroundColor(Color.decode("#e9f2f7"));
				rcell2.setBackgroundColor(Color.decode("#e9f2f7"));
				rcell3.setBackgroundColor(Color.decode("#e9f2f7"));
			}
			rTable.addCell(rcell1);
			rTable.addCell(rcell2);
			rTable.addCell(rcell3);
		}
		document.add(rTable);
		document.add(new Paragraph("\n"));
		String rtPiechartPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/rtPiechart" + userId + ".png");
		img = Image.getInstance(rtPiechartPath);
		img.setAbsolutePosition(0, 0);
		img.setAlignment(Image.MIDDLE);// 设置图片显示位置
		img.scaleAbsolute(600, 600);// 直接设定显示尺寸
		img.scalePercent(60);// 表示显示的大小为原尺寸的50%
		document.add(img);
		String contextString8 = "图4：一级风险事件数比例饼状图";
		Paragraph context8 = new Paragraph(contextString8);
		context8.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context8);
		String rtBarchartPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/rtBarchart" + userId + ".png");
		img = Image.getInstance(rtBarchartPath);
		img.setAbsolutePosition(0, 0);
		img.setAlignment(Image.MIDDLE);// 设置图片显示位置
		img.scaleAbsolute(600, 600);// 直接设定显示尺寸
		img.scalePercent(60);// 表示显示的大小为原尺寸的50%
		document.add(img);
		String contextString9 = "图5：一级风险综合评定值柱状图";
		Paragraph context9 = new Paragraph(contextString9);
		context9.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context9);
		if(ruleFileList!=null&&!ruleFileList.isEmpty()){
		String contextString10 = "表二：企业标准映射统计表";
		Paragraph context10 = new Paragraph(contextString10);
		context10.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context10);
		rTable = new Table(3);
		int rwidth3[] = { 30, 50, 30 };
		rTable.setWidths(rwidth3);// 设置每列所占比例
		rTable.setWidth(100); // 占页面宽度 90%
		rTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		rTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		rTable.setAutoFillEmptyCells(true); // 自动填满
		rTable.setBorderWidth(1); // 边框宽度
		rTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		rTable.setPadding(2);// 衬距，看效果就知道什么意思了
		rTable.setSpacing(0);// 即单元格之间的间距
		rTable.setBorder(2);// 边框
		// 设置风险种类表格的表头
		rhaderCell1 = new Cell("序号");
		rhaderCell1.setHeader(true);
		// rhaderCell1.setBackgroundColor(Color.GRAY);
		rTable.addCell(rhaderCell1);
		rhaderCell2 = new Cell("管理规定名称");
		rhaderCell2.setHeader(true);
		rTable.addCell(rhaderCell2);
		rhaderCell3 = new Cell("风险事件个数");
		rhaderCell3.setHeader(true);
		rTable.addCell(rhaderCell3);
		rTable.endHeaders();// 结束表头
		for (int i = 0; i < ruleFileList.size(); i++) {

			Cell rcell1 = new Cell((i + 1) + "");
			Cell rcell2 = new Cell(ruleFileList.get(i).getRuleName());
			Cell rcell3 = new Cell(ruleFileList.get(i).getEventNum());
			rcell1.setBackgroundColor(Color.decode("#e9f2f7"));
			rcell2.setBackgroundColor(Color.decode("#e9f2f7"));
			rcell3.setBackgroundColor(Color.decode("#e9f2f7"));
			rTable.addCell(rcell1);
			rTable.addCell(rcell2);
			rTable.addCell(rcell3);
		}
		document.add(rTable);
		document.add(new Paragraph("\n"));
		String ruleBarchartPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/ruleBarchart" + userId + ".png");
		img = Image.getInstance(ruleBarchartPath);
		img.setAbsolutePosition(0, 0);
		img.setAlignment(Image.MIDDLE);// 设置图片显示位置
		img.scaleAbsolute(600, 600);// 直接设定显示尺寸
		img.scalePercent(60);// 表示显示的大小为原尺寸的50%
		document.add(img);
		String contextString11 = "图6：管理规定事件数柱状图";
		Paragraph context11 = new Paragraph(contextString11);
		context11.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context11);
		}
		document.close();
		}
		return true;
	}

	public boolean createWordFile2(String FileDefaultName,
			List<RiAnalysis> riskList, List fileList) throws DocumentException,
			IOException {

		FileDefaultName = new String(FileDefaultName.getBytes(), "iso8859-1");// 解决中文
		// 文件名问题
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream os = response.getOutputStream();
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename="
				+ FileDefaultName + ".doc");
		response.setContentType("application/msword");

		// 设置纸张大小
		Document document = new Document(PageSize.A4);
		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
		RtfWriter2.getInstance(document, os);
		document.open();
		// 设置中文字体
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		// 标题字体风格
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);
		// 正文字体风格
		Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
		Paragraph title = new Paragraph("中南电力设计院风险分布情况表");
		// 设置标题格式对齐方式
		title.setAlignment(Element.ALIGN_CENTER);
		title.setFont(titleFont);
		document.add(title);

		String contextString4 = "表一：风险分布情况及评定情况";
		Paragraph context4 = new Paragraph(contextString4);
		document.add(context4);
		Table rTable = new Table(3);

		int rwidth[] = { 30, 50, 30 };
		rTable.setWidths(rwidth);// 设置每列所占比例
		rTable.setWidth(100); // 占页面宽度 90%
		rTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		rTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		rTable.setAutoFillEmptyCells(true); // 自动填满
		rTable.setBorderWidth(1); // 边框宽度
		rTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		rTable.setPadding(2);// 衬距，看效果就知道什么意思了
		rTable.setSpacing(0);// 即单元格之间的间距
		rTable.setBorder(2);// 边框
		// 设置风险种类表格的表头
		Cell rhaderCell1 = new Cell("风险类型");
		rhaderCell1.setHeader(true);
		rhaderCell1.setBackgroundColor(Color.BLUE);
		rTable.addCell(rhaderCell1);
		Cell rhaderCell2 = new Cell("风险事件数");
		rhaderCell2.setHeader(true);
		rhaderCell2.setBackgroundColor(Color.BLUE);
		rTable.addCell(rhaderCell2);
		Cell rhaderCell3 = new Cell("综合评定平均值");
		rhaderCell3.setHeader(true);
		rhaderCell3.setBackgroundColor(Color.BLUE);
		rTable.addCell(rhaderCell3);
		rTable.endHeaders();// 结束表头

		for (int i = 0; i < riskList.size(); i++) {

			Cell rcell1 = new Cell(riskList.get(i).getRiskName());
			Cell rcell2 = new Cell(riskList.get(i).getReEventname());
			Cell rcell3 = new Cell(riskList.get(i).getRiAllvalue());
			if (isRiskType(riskList.get(i).getRiskName())) {
				rcell1.setBackgroundColor(Color.gray);
				rcell2.setBackgroundColor(Color.gray);
				rcell3.setBackgroundColor(Color.gray);
			}
			rTable.setAlignment(Element.ALIGN_CENTER);
			rTable.addCell(rcell1);
			rTable.addCell(rcell2);
			rTable.addCell(rcell3);
		}
		document.add(rTable);
		document.add(new Paragraph("\n"));
		String path = graphicNew.drawRtPieChart(1000, 1000, riskList);
		String savePatharea = ServletActionContext.getServletContext()
				.getRealPath(path);
		Image imgarea = Image.getInstance(savePatharea);
		imgarea.setAlignment(Image.LEFT);// 设置图片显示位置
		imgarea.scaleAbsolute(600, 600);// 直接设定显示尺寸
		imgarea.scalePercent(50);// 表示显示的大小为原尺寸的50%
		imgarea.scalePercent(50, 50);// 图像高宽的显示比例
		document.add(imgarea);
		document.add(new Paragraph("图示1：风险事件类型分布"));
		String path2 = graphicNew.drawRtBarChart(800, 800, riskList);
		String savePatharea2 = ServletActionContext.getServletContext()
				.getRealPath(path2);
		Image imgarea2 = Image.getInstance(savePatharea2);
		imgarea2.setAlignment(Image.LEFT);// 设置图片显示位置
		imgarea2.scaleAbsolute(600, 600);// 直接设定显示尺寸
		imgarea2.scalePercent(50);// 表示显示的大小为原尺寸的50%
		imgarea2.scalePercent(50, 50);// 图像高宽的显示比例
		document.add(imgarea2);
		document.add(new Paragraph("图示2: 	风险评定（平均值）示意"));
		document.add(new Paragraph("表二：企业标准映射统计表"));
		Table rTable2 = new Table(3);

		int rwidth2[] = { 30, 50, 30 };
		rTable2.setWidths(rwidth2);// 设置每列所占比例
		rTable2.setWidth(100); // 占页面宽度 90%
		rTable2.setAlignment(Element.ALIGN_CENTER);// 居中显示
		rTable2.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		rTable2.setAutoFillEmptyCells(true); // 自动填满
		rTable2.setBorderWidth(1); // 边框宽度
		rTable2.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		rTable2.setPadding(2);// 衬距，看效果就知道什么意思了
		rTable2.setSpacing(0);// 即单元格之间的间距
		rTable2.setBorder(2);// 边框
		// 设置风险种类表格的表头
		Cell rhaderCell4 = new Cell("序号");
		rhaderCell4.setHeader(true);
		rhaderCell4.setBackgroundColor(Color.BLUE);
		rTable2.addCell(rhaderCell4);
		Cell rhaderCell5 = new Cell("管理规定名称");
		rhaderCell5.setHeader(true);
		rhaderCell5.setBackgroundColor(Color.BLUE);
		rTable2.addCell(rhaderCell5);
		Cell rhaderCell6 = new Cell("风险事件个数");
		rhaderCell6.setHeader(true);
		rhaderCell6.setBackgroundColor(Color.BLUE);
		rTable2.addCell(rhaderCell6);
		rTable2.endHeaders();// 结束表头
		// 序号
		int num = 0;
		for (Object file : fileList) {
			Object[] files = (Object[]) file;
			Cell rcell1 = new Cell((++num) + "");
			Cell rcell2 = new Cell((String) files[0]);
			Cell rcell3 = new Cell((Long) files[1] + "");
			rTable2.setAlignment(Element.ALIGN_CENTER);
			rTable2.addCell(rcell1);
			rTable2.addCell(rcell2);
			rTable2.addCell(rcell3);

		}
		document.add(rTable2);

		Collections.sort(fileList, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				Object[] o11 = (Object[]) o1;
				Object[] o22 = (Object[]) o2;
				return (int) ((Long) o22[1] - (Long) o11[1]);
			}
		});
		String path3 = graphicNew.drawRuleBarChart(fileList);
		String savePatharea3 = ServletActionContext.getServletContext()
				.getRealPath(path3);
		Image imgarea3 = Image.getInstance(savePatharea3);
		imgarea3.setAlignment(Image.LEFT);// 设置图片显示位置
		imgarea3.scaleAbsolute(600, 600);// 直接设定显示尺寸
		imgarea3.scalePercent(50);// 表示显示的大小为原尺寸的50%
		imgarea3.scalePercent(50, 50);// 图像高宽的显示比例
		document.add(imgarea3);
		document.close();
		return true;
	}

	/*
	 * 生成word文档
	 */
	public boolean createWordFile(String FileDefaultName, List<Risk> rList,
			List<Probability> list_p, List<Operation> list_i,
			List<ReportViewNew> reportList) throws DocumentException,
			IOException {

		FileDefaultName = new String(FileDefaultName.getBytes(), "iso8859-1");// 解决中文
		// 文件名问题
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream os = response.getOutputStream();
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename="
				+ FileDefaultName + ".doc");
		response.setContentType("application/msword");

		// 设置纸张大小
		Document document = new Document(PageSize.A4);
		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
		RtfWriter2.getInstance(document, os);
		document.open();
		// 设置中文字体
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		// 标题字体风格
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);
		// 正文字体风格
		Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
		Paragraph title = new Paragraph("中南电力设计院风险事件评估表");
		// 设置标题格式对齐方式
		title.setAlignment(Element.ALIGN_CENTER);
		title.setFont(titleFont);
		document.add(title);

		String contextString3 = year + "年我院重大风险量化分析";
		Paragraph context3 = new Paragraph(contextString3);
		document.add(context3);

		String contextString4 = "    我院重大风险成因分析见下表1";
		Paragraph context4 = new Paragraph(contextString4);
		document.add(context4);
		Table rTable = new Table(3);

		int rwidth[] = { 30, 50, 30 };
		rTable.setWidths(rwidth);// 设置每列所占比例
		rTable.setWidth(100); // 占页面宽度 90%
		rTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		rTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		rTable.setAutoFillEmptyCells(true); // 自动填满
		rTable.setBorderWidth(1); // 边框宽度
		rTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		rTable.setPadding(2);// 衬距，看效果就知道什么意思了
		rTable.setSpacing(0);// 即单元格之间的间距
		rTable.setBorder(2);// 边框
		// 设置风险种类表格的表头
		Cell rhaderCell1 = new Cell("重大风险");
		rhaderCell1.setHeader(true);
		// rhaderCell1.setBackgroundColor(Color.GRAY);
		rTable.addCell(rhaderCell1);
		Cell rhaderCell2 = new Cell("风险分类");
		rhaderCell2.setHeader(true);
		rTable.addCell(rhaderCell2);
		Cell rhaderCell3 = new Cell("风险编号");
		rhaderCell3.setHeader(true);
		rTable.addCell(rhaderCell3);
		rTable.endHeaders();// 结束表头

		for (int i = 0; i < rList.size(); i++) {

			Cell rcell1 = new Cell();
			if (null != rList.get(i).getRiskType()) {
				rcell1 = new Cell(this.getRiskTypeDao().findById(
						rList.get(i).getRiskType()).getRtName());
			}

			Cell rcell2 = new Cell(rList.get(i).getRiskName());
			Cell rcell3 = new Cell(String.valueOf(rList.get(i).getRiskQueue()));
			rTable.setAlignment(Element.ALIGN_CENTER);
			rTable.addCell(rcell1);
			rTable.addCell(rcell2);
			rTable.addCell(rcell3);
		}
		document.add(rTable);
		document.add(new Paragraph("\n"));

		String contextString = "表2   风险发生可能性的定性、定量的描述";
		Paragraph context = new Paragraph(contextString);
		// 正文格式左对齐
		context.setAlignment(Element.ALIGN_CENTER);
		context.setFont(contextFont);
		// 离上一段落（标题）空的行数
		// context.setSpacingBefore(5);
		// 设置第一行空的列数
		context.setFirstLineIndent(20);
		document.add(context);
		Table aTable = new Table(list_p.size() + 2);
		int width[] = { 20, 30, 50, 60, 60, 60, 60, 60 };
		aTable.setWidths(width);// 设置每列所占比例
		aTable.setWidth(100); // 占页面宽度 90%
		aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		aTable.setAutoFillEmptyCells(true); // 自动填满
		aTable.setBorderWidth(1); // 边框宽度
		aTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		aTable.setPadding(2);// 衬距，看效果就知道什么意思了
		aTable.setSpacing(0);// 即单元格之间的间距
		aTable.setBorder(2);// 边框
		// 设置风险
		Cell haderCell = new Cell("定量方法");
		haderCell.setHeader(true);
		aTable.addCell(haderCell);
		Cell haderCell2 = new Cell("一定时期发生的概率");
		haderCell2.setHeader(true);
		aTable.addCell(haderCell2);
		for (int i = 0; i < list_p.size(); i++) {
			Cell cell = new Cell(list_p.get(i).getProProbability());
			cell.setHeader(true);
			aTable.addCell(cell);
		}
		aTable.endHeaders();
		Cell cell3 = new Cell("定性方法");
		aTable.addCell(cell3);
		Cell cell4 = new Cell("文字描述");
		aTable.addCell(cell4);
		for (int i = 0; i < list_p.size(); i++) {
			Cell cell = new Cell(list_p.get(i).getProDisasterEvent() + " \n"
					+ list_p.get(i).getProLevel());
			aTable.setAlignment(Element.ALIGN_CENTER);
			aTable.addCell(cell);
		}
		Cell cell5 = new Cell("风险归类");
		aTable.addCell(cell5);
		Cell cell6 = new Cell("序号描述");
		aTable.addCell(cell6);
		String[] infos = { "", "", "", "", "", "" };
		for (int i = 0; i < list_p.size(); i++) {
			for (int j = 0; j < reportList.size(); j++) {
				if (list_p.get(i).getProId().equals(
						reportList.get(j).getRiskPro())) {
					infos[i] += "(" + reportList.get(j).getRiskQueue() + ")";
				}
			}
			Cell cell = new Cell(infos[i]);
			aTable.addCell(cell);
		}
		document.add(aTable);
		document.add(new Paragraph("\n"));

		String contextString2 = "表3   风险对目标影响程度定性、定量的描述";
		Paragraph context2 = new Paragraph(contextString2);
		// 正文格式左对齐
		context2.setAlignment(Element.ALIGN_CENTER);
		context2.setFont(contextFont);
		// 离上一段落（标题）空的行数
		// context2.setSpacingBefore(5);
		// 设置第一行空的列数
		// context.setFirstLineIndent(20);
		document.add(context2);
		Table aTable2 = new Table(list_p.size() + 2);
		int width2[] = { 30, 30, 50, 60, 60, 60, 60, 60 };
		aTable2.setWidths(width2);// 设置每列所占比例
		aTable2.setWidth(100); // 占页面宽度 90%
		aTable2.setAlignment(Element.ALIGN_CENTER);// 居中显示
		aTable2.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		aTable2.setAutoFillEmptyCells(true); // 自动填满
		aTable2.setBorderWidth(1); // 边框宽度
		aTable2.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		aTable2.setPadding(2);// 衬距，看效果就知道什么意思了
		aTable2.setSpacing(0);// 即单元格之间的间距
		aTable2.setBorder(2);// 边框
		Cell haderCell_im1 = new Cell("定量方法");
		haderCell_im1.setHeader(true);
		aTable2.addCell(haderCell_im1);
		Cell haderCell_im2 = new Cell("评分等级");
		haderCell_im1.setHeader(true);
		aTable2.addCell(haderCell_im2);
		for (int i = 0; i < list_i.size(); i++) {
			Cell cell = new Cell(String.valueOf(list_i.get(i).getOpeId()));
			cell.setHeader(true);
			aTable2.addCell(cell);
		}
		aTable2.endHeaders();
		Cell cell_m3 = new Cell("定性方法");
		aTable2.addCell(cell_m3);
		Cell cell_m4 = new Cell("文字描述");
		aTable2.addCell(cell_m4);
		for (int i = 0; i < list_p.size(); i++) {
			Cell cell = new Cell(list_i.get(i).getOpeLevel());
			aTable2.setAlignment(Element.ALIGN_CENTER);
			aTable2.addCell(cell);
		}
		Cell cell_m5 = new Cell("风险归类");
		aTable2.addCell(cell_m5);
		Cell cell_m6 = new Cell("序号描述");
		aTable2.addCell(cell_m6);
		String[] infos_m = { "", "", "", "", "", "" };
		for (int i = 0; i < list_i.size(); i++) {
			for (int j = 0; j < reportList.size(); j++) {
				if (list_i.get(i).getOpeId().equals(
						reportList.get(j).getRiskValuex())) {
					infos_m[i] += "(" + reportList.get(j).getRiskQueue() + ")";
				}
			}
			Cell cell = new Cell(infos_m[i]);
			aTable2.addCell(cell);
		}
		document.add(aTable2);
		document.add(new Paragraph("\n"));

		String contextString5 = year + "年我院重大风险坐标图";
		Paragraph context5 = new Paragraph(contextString5);
		context5.setFont(titleFont);
		document.add(context5);

		String contextString6 = "    依据上节对风险发生可能性高低和风险对目标影响程度进行定性和定量的评估，绘制风险坐标如下图。";
		Paragraph context6 = new Paragraph(contextString6);
		context6.setFirstLineIndent(4);
		document.add(context6);

		// 生成图片
		graphicNew.drawTable(1000, 1000, reportList);
		String savePath = ServletActionContext.getServletContext().getRealPath(
				"/upload/reportImg.jpg");
		Image img = Image.getInstance(savePath);
		img.setAbsolutePosition(0, 0);
		img.setAlignment(Image.LEFT);// 设置图片显示位置
		img.scaleAbsolute(600, 600);// 直接设定显示尺寸
		img.scalePercent(45);// 表示显示的大小为原尺寸的50%
		img.scalePercent(45, 60);// 图像高宽的显示比例
		document.add(img);

		String contextStringp1 = "图1：我院风险坐标图" + "\n";
		Paragraph contextp1 = new Paragraph(contextStringp1);
		contextp1.setAlignment(Element.ALIGN_CENTER);
		contextp1.setFont(contextFont);
		document.add(contextp1);

		String contextString71 = "重大风险管理策略和解决方案" + "\n";
		Paragraph context71 = new Paragraph(contextString71);
		context71.setAlignment(Element.ALIGN_LEFT);
		context71.setFont(titleFont);
		document.add(context71);

		String contextString7 = "（1）风险管理策略"
				+ "\n"
				+ "    我院风险管理主要包括战略、运营、市场、法律、财务风险管理，根据我院的自身条件和外部环境，围绕企业发展战略，分别采用风险承担、风险规避、风险转移、风险控制等方法进行管理。"
				+ "\n" + "    将我院风险评估的定性、定量风险得出的风险坐标图分A、B、C三个区域，如下图所示。" + "\n";
		Paragraph context7 = new Paragraph(contextString7);
		document.add(context7);

		// 插入分区图
		String path2 = graphicNew.drawTableShadow(1000, reportList);
		String savePatharea = ServletActionContext.getServletContext()
				.getRealPath(path2);
		Image imgarea = Image.getInstance(savePatharea);
		imgarea.setAlignment(Image.LEFT);// 设置图片显示位置
		imgarea.scaleAbsolute(600, 600);// 直接设定显示尺寸
		imgarea.scalePercent(45);// 表示显示的大小为原尺寸的50%
		imgarea.scalePercent(45, 60);// 图像高宽的显示比例
		document.add(imgarea);

		String contextStringp2 = "图2：风险坐标图分区图" + "\n";
		Paragraph contextp2 = new Paragraph(contextStringp2);
		contextp2.setAlignment(Element.ALIGN_CENTER);
		contextp2.setFont(contextFont);
		document.add(contextp2);

		String contextString8 = riskDetail(reportList);// 获取具体描述
		Paragraph context8 = new Paragraph(contextString8);
		document.add(context8);
		document.close();
		return true;
	}

	public boolean createWordFile2New(String FileDefaultName,
			List<RiAnalysis> riskList, List fileList) throws DocumentException,
			IOException {

		FileDefaultName = new String(FileDefaultName.getBytes(), "iso8859-1");// 解决中文
		// 文件名问题
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream os = response.getOutputStream();
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename="
				+ FileDefaultName + ".doc");
		response.setContentType("application/msword");

		// 设置纸张大小
		Document document = new Document(PageSize.A4);
		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
		RtfWriter2.getInstance(document, os);
		document.open();
		// 设置中文字体
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		// 标题字体风格
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);
		// 正文字体风格
		Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
		Paragraph title = new Paragraph("中南电力设计院风险分布情况表");
		// 设置标题格式对齐方式
		title.setAlignment(Element.ALIGN_CENTER);
		title.setFont(titleFont);
		document.add(title);

		String contextString4 = "表一：风险分布情况及评定情况";
		Paragraph context4 = new Paragraph(contextString4);
		document.add(context4);
		Table rTable = new Table(3);

		int rwidth[] = { 30, 50, 30 };
		rTable.setWidths(rwidth);// 设置每列所占比例
		rTable.setWidth(100); // 占页面宽度 90%
		rTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		rTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		rTable.setAutoFillEmptyCells(true); // 自动填满
		rTable.setBorderWidth(1); // 边框宽度
		rTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		rTable.setPadding(2);// 衬距，看效果就知道什么意思了
		rTable.setSpacing(0);// 即单元格之间的间距
		rTable.setBorder(2);// 边框
		// 设置风险种类表格的表头
		Cell rhaderCell1 = new Cell("风险类型");
		rhaderCell1.setHeader(true);
		rhaderCell1.setBackgroundColor(Color.BLUE);
		rTable.addCell(rhaderCell1);
		Cell rhaderCell2 = new Cell("风险事件数");
		rhaderCell2.setHeader(true);
		rhaderCell2.setBackgroundColor(Color.BLUE);
		rTable.addCell(rhaderCell2);
		Cell rhaderCell3 = new Cell("综合评定平均值");
		rhaderCell3.setHeader(true);
		rhaderCell3.setBackgroundColor(Color.BLUE);
		rTable.addCell(rhaderCell3);
		rTable.endHeaders();// 结束表头

		for (int i = 0; i < riskList.size(); i++) {

			Cell rcell1 = new Cell(riskList.get(i).getRiskName());
			Cell rcell2 = new Cell(riskList.get(i).getReEventname());
			Cell rcell3 = new Cell(riskList.get(i).getRiAllvalue());
			if (isRiskType(riskList.get(i).getRiskName())) {
				rcell1.setBackgroundColor(Color.gray);
				rcell2.setBackgroundColor(Color.gray);
				rcell3.setBackgroundColor(Color.gray);
			}
			rTable.setAlignment(Element.ALIGN_CENTER);
			rTable.addCell(rcell1);
			rTable.addCell(rcell2);
			rTable.addCell(rcell3);
		}
		document.add(rTable);
		document.add(new Paragraph("\n"));
		String path = graphicNew.drawRtPieChart(1000, 1000, riskList);
		String savePatharea = ServletActionContext.getServletContext()
				.getRealPath(path);
		Image imgarea = Image.getInstance(savePatharea);
		imgarea.setAlignment(Image.LEFT);// 设置图片显示位置
		imgarea.scaleAbsolute(600, 600);// 直接设定显示尺寸
		imgarea.scalePercent(50);// 表示显示的大小为原尺寸的50%
		imgarea.scalePercent(50, 50);// 图像高宽的显示比例
		document.add(imgarea);
		document.add(new Paragraph("图示1：风险事件类型分布"));
		String path2 = graphicNew.drawRtBarChart(800, 800, riskList);
		String savePatharea2 = ServletActionContext.getServletContext()
				.getRealPath(path2);
		Image imgarea2 = Image.getInstance(savePatharea2);
		imgarea2.setAlignment(Image.LEFT);// 设置图片显示位置
		imgarea2.scaleAbsolute(600, 600);// 直接设定显示尺寸
		imgarea2.scalePercent(50);// 表示显示的大小为原尺寸的50%
		imgarea2.scalePercent(50, 50);// 图像高宽的显示比例
		document.add(imgarea2);
		document.add(new Paragraph("图示2: 	风险评定（平均值）示意"));
		document.add(new Paragraph("表二：企业标准映射统计表"));
		Table rTable2 = new Table(3);

		int rwidth2[] = { 30, 50, 30 };
		rTable2.setWidths(rwidth2);// 设置每列所占比例
		rTable2.setWidth(100); // 占页面宽度 90%
		rTable2.setAlignment(Element.ALIGN_CENTER);// 居中显示
		rTable2.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		rTable2.setAutoFillEmptyCells(true); // 自动填满
		rTable2.setBorderWidth(1); // 边框宽度
		rTable2.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		rTable2.setPadding(2);// 衬距，看效果就知道什么意思了
		rTable2.setSpacing(0);// 即单元格之间的间距
		rTable2.setBorder(2);// 边框
		// 设置风险种类表格的表头
		Cell rhaderCell4 = new Cell("序号");
		rhaderCell4.setHeader(true);
		rhaderCell4.setBackgroundColor(Color.BLUE);
		rTable2.addCell(rhaderCell4);
		Cell rhaderCell5 = new Cell("管理规定名称");
		rhaderCell5.setHeader(true);
		rhaderCell5.setBackgroundColor(Color.BLUE);
		rTable2.addCell(rhaderCell5);
		Cell rhaderCell6 = new Cell("风险事件个数");
		rhaderCell6.setHeader(true);
		rhaderCell6.setBackgroundColor(Color.BLUE);
		rTable2.addCell(rhaderCell6);
		rTable2.endHeaders();// 结束表头
		// 序号
		int num = 0;
		for (Object file : fileList) {
			Object[] files = (Object[]) file;
			Cell rcell1 = new Cell((++num) + "");
			Cell rcell2 = new Cell((String) files[0]);
			Cell rcell3 = new Cell((Long) files[1] + "");
			rTable2.setAlignment(Element.ALIGN_CENTER);
			rTable2.addCell(rcell1);
			rTable2.addCell(rcell2);
			rTable2.addCell(rcell3);

		}
		document.add(rTable2);

		Collections.sort(fileList, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				Object[] o11 = (Object[]) o1;
				Object[] o22 = (Object[]) o2;
				return (int) ((Long) o22[1] - (Long) o11[1]);
			}
		});
		String path3 = graphicNew.drawRuleBarChart(fileList);
		String savePatharea3 = ServletActionContext.getServletContext()
				.getRealPath(path3);
		Image imgarea3 = Image.getInstance(savePatharea3);
		imgarea3.setAlignment(Image.LEFT);// 设置图片显示位置
		imgarea3.scaleAbsolute(600, 600);// 直接设定显示尺寸
		imgarea3.scalePercent(50);// 表示显示的大小为原尺寸的50%
		imgarea3.scalePercent(50, 50);// 图像高宽的显示比例
		document.add(imgarea3);
		document.close();
		return true;
	}

	private String riskDetail(List<ReportViewNew> reportList) {
		String areaA = "";
		String areaB = "";
		String areaC = "";
		for (int i = 0; i < reportList.size(); i++) {
			if (reportList.get(i).getRiskPro() < 3
					&& reportList.get(i).getRiskValuex() < 3) {
				areaA = reportList.get(i).getRiskName() + "、" + areaA;// 获取A区域的风险名称
			} else if (reportList.get(i).getRiskPro() > 3
					&& reportList.get(i).getRiskValuex() > 3) {
				areaC = reportList.get(i).getRiskName() + "、" + areaC;// 获取C区域的风险名称
			} else {
				areaB = reportList.get(i).getRiskName() + "、" + areaB;// 获取B区域的风险名称
			}
		}

		if (areaA.equals(""))
			areaA = "目前我院尚无";
		else
			areaA = "包括" + areaA.substring(0, areaA.length() - 1);
		if (areaB.equals(""))
			areaB = "目前我院尚无";
		else
			areaB = "包括" + areaB.substring(0, areaB.length() - 1);
		;
		if (areaC.equals(""))
			areaC = "目前我院尚无";
		else
			areaC = "包括" + areaC.substring(0, areaB.length() - 1);
		;

		String contextString8 = "    其中A区域中的风险"
				+ areaA
				+ "，此类风险发生可能性和事件发生后的影响程度均为中等及以下，对于A区域中的各项风险拟不再增加控制措施，但需认真执行原有风险控制措施；"
				+ "\n"
				+ "    B区域中的风险"
				+ areaB
				+ "，此类风险发生可能性和事件发生后的影响程度均为中等以上及高等以下，对于B区域的各项风险需要严格控制，并需要补充制定各项措施；"
				+ "\n" + "    C区域中的风险" + areaC
				+ "，风险发生可能性和事件发生后的影响程度均为高等及以上，此类风险应严格防范，确保规避。";
		return contextString8;
	}

	public String getRiskEventDetailById() {
		riskEventlistall = allAnalysisViewDAO.findDetailByReId(reId);
		eventDetailList = new LinkedList<RiAnalysis>();
		for (int m = 0; m < riskEventlistall.size(); m++) {
			RiAnalysis ksone = new RiAnalysis();
			ksone.setRiNum(riNum);
			ksone.setRiskId(riskEventlistall.get(m).getRiskId());
			ksone.setRiskName(riskEventlistall.get(m).getRiskName());
			ksone.setRtName(riskEventlistall.get(m).getRtName());
			ksone.setDepName(riskEventlistall.get(m).getDepName());
			ksone.setReEventname(riskEventlistall.get(m).getReEventname());
			ksone.setRiKpi(riskEventlistall.get(m).getRiKpi());

			ksone.setRiProdegree(String.valueOf(riskEventlistall.get(m)
					.getRiProdegree()));
			ksone.setRiFindegree(String.valueOf(riskEventlistall.get(m)
					.getRiFindegree()));
			ksone.setRiFamedegree(String.valueOf(riskEventlistall.get(m)
					.getRiFamedegree()));
			ksone.setRiLawdegree(String.valueOf(riskEventlistall.get(m)
					.getRiLawdegree()));
			ksone.setRiClidegree(String.valueOf(riskEventlistall.get(m)
					.getRiClidegree()));
			ksone.setRiEmpdegree(String.valueOf(riskEventlistall.get(m)
					.getRiEmpdegree()));
			ksone.setRiOpedegree(String.valueOf(riskEventlistall.get(m)
					.getRiOpedegree()));
			ksone.setRiSafedegree(String.valueOf(riskEventlistall.get(m)
					.getRiSafedegree()));
			ksone.setRiAlldegree(String.valueOf(dcmFmt.format(riskEventlistall
					.get(m).getRiAlldegree())));

			ksone.setRiFinvalue(String.valueOf(riskEventlistall.get(m)
					.getRiFinvalue()));
			ksone.setRiFramevalue(String.valueOf(riskEventlistall.get(m)
					.getRiFramevalue()));
			ksone.setRiLawvalue(String.valueOf(riskEventlistall.get(m)
					.getRiLawvalue()));
			ksone.setRiClivalue(String.valueOf(riskEventlistall.get(m)
					.getRiClivalue()));
			ksone.setRiEmpvalue(String.valueOf(riskEventlistall.get(m)
					.getRiEmpvalue()));
			ksone.setRiOpevalue(String.valueOf(riskEventlistall.get(m)
					.getRiOpevalue()));
			ksone.setRiSafevalue(String.valueOf(riskEventlistall.get(m)
					.getRiSafevalue()));
			ksone.setRiAllvalue(String.valueOf(dcmFmt.format(riskEventlistall
					.get(m).getRiAllvalue())));
			if("2".equals(choosedId)){
				ksone.setRiEventDate(riskEventlistall.get(m).getReModifydate());
			}else{
			ksone.setRiEventDate(riskEventlistall.get(m).getReDate());}
			eventDetailList.add(ksone);
		}
		return "success";
	}

	public String GeneralAnalysisDepAllByValue() {
		// 每个部门具体事件的list，传给页面显示
		ralist = new LinkedList<RiAnalysis>();
		// List depListall = new LinkedList();
		List<AllAnalysisView> eventList = new LinkedList<AllAnalysisView>();
		String condition = getConditon();
		// // 找到存在事件的所有部门
		// depListall = allAnalysisViewDAO.findDepByConditon(condition);
		// // System.out.println(depListall.size());
		// if (!depListall.isEmpty()) {
		// getInfoDepAllByCondition(depListall, condition);
		// }
		eventList = allAnalysisViewDAO.findDetailOrderByDep(condition);
		if (!eventList.isEmpty()) {
			getInfoAllNew(eventList);
		}
		return "success";
	}

	private void getInfoAllNew(List<AllAnalysisView> eventList) {
		// TODO Auto-generated method stub
		String depname1;
		String depname2;
		String riskname1;
		String riskname2;
		// 部门序号
		int depnum = 1;
		// 事件总数
		int allcount = 0;
		// 部门事件数
		int depeventcount = 0;
		// 部门二级风险数
		int riskcount = 1;

		int eventListnum = eventList.size();
		for (int m = 0; m < eventListnum; m++) {
			// 将当前记录放入list中
			RiAnalysis ksone = new RiAnalysis();
			ksone.setDepNum(depnum + "");
			ksone.setDepName(eventList.get(m).getDepName().trim());
			ksone.setRiNum(riskcount + "");
			ksone.setRiskName(eventList.get(m).getRiskName());
			// 使得二级风险和一级风险一一对应
			if (riskcount % 2 != 0) {
				ksone.setRtName(eventList.get(m).getRtName());
			} else {
				ksone.setRtName(eventList.get(m).getRtName() + " ");
			}
			ksone.setReId(eventList.get(m).getReId());
			ksone.setReEventname(eventList.get(m).getReEventname());
			ksone.setRiAlldegree(dcmFmt.format(eventList.get(m)
					.getRiAlldegree()));
			ksone.setRiProdegree(Integer.toString(eventList.get(m)
					.getRiProdegree()));
			ksone.setRiKpi(eventList.get(m).getRiKpi());
			ksone.setRiFindegree(String.valueOf(eventList.get(m)
					.getRiFindegree()));
			ksone.setRiFamedegree(String.valueOf(eventList.get(m)
					.getRiFamedegree()));
			ksone.setRiLawdegree(String.valueOf(eventList.get(m)
					.getRiLawdegree()));
			ksone.setRiClidegree(String.valueOf(eventList.get(m)
					.getRiClidegree()));
			ksone.setRiEmpdegree(String.valueOf(eventList.get(m)
					.getRiEmpdegree()));
			ksone.setRiOpedegree(String.valueOf(eventList.get(m)
					.getRiOpedegree()));
			ksone.setRiSafedegree(String.valueOf(eventList.get(m)
					.getRiSafedegree()));
			ksone.setRiAllvalue(String.valueOf(dcmFmt.format(eventList.get(m)
					.getRiAllvalue())));
			ksone.setRiFinvalue(String
					.valueOf(eventList.get(m).getRiFinvalue()));
			ksone.setRiFramevalue(String.valueOf(eventList.get(m)
					.getRiFramevalue()));
			ksone.setRiLawvalue(String
					.valueOf(eventList.get(m).getRiLawvalue()));
			ksone.setRiClivalue(String
					.valueOf(eventList.get(m).getRiClivalue()));
			ksone.setRiEmpvalue(String
					.valueOf(eventList.get(m).getRiEmpvalue()));
			ksone.setRiOpevalue(String
					.valueOf(eventList.get(m).getRiOpevalue()));
			ksone.setRiSafevalue(String.valueOf(eventList.get(m)
					.getRiSafevalue()));
			if("2".equals(choosedId)){
				ksone.setRiEventDate(eventList.get(m).getReModifydate());
			}else{
			ksone.setRiEventDate(eventList.get(m).getReDate());}
			ralist.add(ksone);
			depeventcount++;
			allcount++;

			if (m < eventListnum - 1) {
				depname1 = eventList.get(m).getDepName().trim();
				depname2 = eventList.get(m + 1).getDepName().trim();
				if (depname1.equals(depname2)) {
					riskname1 = eventList.get(m).getRiskName();
					riskname2 = eventList.get(m + 1).getRiskName();
					if (!riskname1.equals(riskname2)) {
						riskcount++;
					}
				} else {// 如果当前记录的部门名称与下一个记录不相同，则插入小计
					RiAnalysis ksone2 = new RiAnalysis();
					ksone2.setDepNum(depnum + " ");
					ksone2.setDepName(eventList.get(m).getDepName().trim()
							+ " ");
					ksone2.setRiNum("小计");
					ksone2.setRiskName(riskcount + "");
					ksone2.setRtName("");
					ksone2.setReEventname(depeventcount + "");
					ralist.add(ksone2);
					depnum++;
					riskcount = 1;
					depeventcount = 0;
				}
			}
			if (m == eventListnum - 1)// 最后一条记录特殊处理，直接添加小计
			{
				RiAnalysis ksone2 = new RiAnalysis();
				ksone2.setDepNum(depnum + " ");
				ksone2.setDepName(eventList.get(m).getDepName().trim() + " ");
				ksone2.setRiNum("小计");
				ksone2.setRiskName(riskcount + "");
				ksone2.setRtName("");
				ksone2.setReEventname(depeventcount + "");
				ralist.add(ksone2);
				riskcount = 1;
				depeventcount = 0;
			}
		}
		// 加入总计
		RiAnalysis ksone2 = new RiAnalysis();
		ksone2.setDepNum("总计");
		ksone2.setDepName(depnum + "");
		ksone2.setRiNum("");
		ksone2.setRiskName("");
		ksone2.setRtName("-");
		ksone2.setReEventname(allcount + "");
		ralist.add(ksone2);

		// 数据存放在session中，便于导出excel
		String[][] dsarray = new String[ralist.size()][26];
		for (int m = 0; m < ralist.size(); m++) {
			dsarray[m][0] = ralist.get(m).getDepNum().trim();
			dsarray[m][1] = ralist.get(m).getDepName().trim();
			dsarray[m][2] = ralist.get(m).getRiNum();
			dsarray[m][3] = ralist.get(m).getRiskName();
			dsarray[m][4] = ralist.get(m).getRtName();
			dsarray[m][5] = ralist.get(m).getReId();
			dsarray[m][6] = ralist.get(m).getReEventname();
			dsarray[m][7] = ralist.get(m).getRiAlldegree();
			dsarray[m][8] = ralist.get(m).getRiProdegree();
			dsarray[m][9] = ralist.get(m).getRiKpi();

			dsarray[m][10] = ralist.get(m).getRiFindegree();
			dsarray[m][11] = ralist.get(m).getRiFamedegree();
			dsarray[m][12] = ralist.get(m).getRiLawdegree();
			dsarray[m][13] = ralist.get(m).getRiClidegree();
			dsarray[m][14] = ralist.get(m).getRiEmpdegree();
			dsarray[m][15] = ralist.get(m).getRiOpedegree();
			dsarray[m][16] = ralist.get(m).getRiSafedegree();

			dsarray[m][17] = ralist.get(m).getRiFinvalue();
			dsarray[m][18] = ralist.get(m).getRiFramevalue();
			dsarray[m][19] = ralist.get(m).getRiLawvalue();
			dsarray[m][20] = ralist.get(m).getRiLawvalue();
			dsarray[m][21] = ralist.get(m).getRiEmpvalue();
			dsarray[m][22] = ralist.get(m).getRiOpevalue();
			dsarray[m][23] = ralist.get(m).getRiSafevalue();
			dsarray[m][24] = ralist.get(m).getRiAllvalue();
			dsarray[m][25] = ralist.get(m).getRiEventDate();
		}
		Map session = ServletActionContext.getContext().getSession();
		session.put("exportGenDepAllList", dsarray);

	}

	// 按二级风险名称查询报表导出(24列)
	public String exportExcelAllByDep() {
		Map session = ActionContext.getContext().getSession();
		// dsarray=(String[][])session.get("exportDepList");
		if (session.get("exportGenList") == null)
			return "fail";
		else {
			String str = "一般风险分析按部门列表统计-详细" + "    综合评定:" + riAllValueFrom
					+ "至" + riAllValueTo + "    影响程度:" + riDegreeFrom + "至"
					+ riDegreeTo + "    发生概率:" + riProDegree;
			if (riskname == null || riskname.equals("请选择")) {
				str += " 二级风险:全部";
			} else {
				str += " 二级风险:" + riskname;
			}
			str += "    录入时间:" + dateFrom + "至" + dateTo;
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("风险分析列表统计查询", "GenAnaAllDepTemplate",
					(String[][]) session.get("exportGenDepAllList"), 3, 5, str);// 3表示从第三行开始，5表示从第五列开始合并，str是查询条件
			return "success";
		}
	}

	public String getRiskEventDetailByIdDep() {
		riskEventlistall = allAnalysisViewDAO.findDetailByReId(reId);
		eventDetailList = new LinkedList<RiAnalysis>();
		for (int m = 0; m < riskEventlistall.size(); m++) {
			RiAnalysis ksone = new RiAnalysis();
			ksone.setDepNum(depNum);
			ksone.setDepName(riskEventlistall.get(m).getDepName());
			ksone.setRiNum(riNum);
			ksone.setRiskId(riskEventlistall.get(m).getRiskId());
			ksone.setRiskName(riskEventlistall.get(m).getRiskName());

			ksone.setRtName(riskEventlistall.get(m).getRtName());
			ksone.setReEventname(riskEventlistall.get(m).getReEventname());
			ksone.setRiFindegree(String.valueOf(riskEventlistall.get(m)
					.getRiFindegree()));
			ksone.setRiFamedegree(String.valueOf(riskEventlistall.get(m)
					.getRiFamedegree()));
			ksone.setRiLawdegree(String.valueOf(riskEventlistall.get(m)
					.getRiLawdegree()));
			ksone.setRiClidegree(String.valueOf(riskEventlistall.get(m)
					.getRiClidegree()));
			ksone.setRiEmpdegree(String.valueOf(riskEventlistall.get(m)
					.getRiEmpdegree()));
			ksone.setRiOpedegree(String.valueOf(riskEventlistall.get(m)
					.getRiOpedegree()));
			ksone.setRiSafedegree(String.valueOf(riskEventlistall.get(m)
					.getRiSafedegree()));
			ksone.setRiAlldegree(dcmFmt.format(riskEventlistall.get(m)
					.getRiAlldegree()));
			ksone.setRiProdegree(Integer.toString(riskEventlistall.get(m)
					.getRiProdegree()));
			ksone.setRiKpi(riskEventlistall.get(m).getRiKpi());
			ksone.setRiFinvalue(String.valueOf(riskEventlistall.get(m)
					.getRiFinvalue()));
			ksone.setRiFramevalue(String.valueOf(riskEventlistall.get(m)
					.getRiFramevalue()));
			ksone.setRiLawvalue(String.valueOf(riskEventlistall.get(m)
					.getRiLawvalue()));
			ksone.setRiClivalue(String.valueOf(riskEventlistall.get(m)
					.getRiClivalue()));
			ksone.setRiEmpvalue(String.valueOf(riskEventlistall.get(m)
					.getRiEmpvalue()));
			ksone.setRiOpevalue(String.valueOf(riskEventlistall.get(m)
					.getRiOpevalue()));
			ksone.setRiSafevalue(String.valueOf(riskEventlistall.get(m)
					.getRiSafevalue()));
			ksone.setRiAllvalue(String.valueOf(dcmFmt.format(riskEventlistall
					.get(m).getRiAllvalue())));
			if("2".equals(choosedId)){
				ksone.setRiEventDate(riskEventlistall.get(m).getReModifydate());
			}else{
			ksone.setRiEventDate(riskEventlistall.get(m).getReDate());}
			eventDetailList.add(ksone);
		}
		return "success";
	}

	public static boolean isRiskType(String riskName) {
		if (riskName.equals("战略风险") || riskName.equals("市场风险")
				|| riskName.equals("法律风险") || riskName.equals("财务风险")
				|| riskName.equals("运营风险"))
			return true;
		return false;
	}

	/**
	 *字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	@SuppressWarnings("unchecked")
	public void ajaxRiskDisk() {
		try {
			riskDiskList = new LinkedList<RiAnalysis>();
			riskDiskList = getRiskDist();
			Map session = ServletActionContext.getContext().getSession();
			session.put("riskDiskList", riskDiskList);
			JSONArray ajaxarray = JSONArray.fromObject(riskDiskList);
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, ajaxarray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("chu cuo");
			e.printStackTrace();
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, null);
		}
	}

	public void ajaxRiskDiskPieAndBar() {
		try {
			riskDiskList = new LinkedList<RiAnalysis>();
			riskDiskList = getRiskDist();
			String path = "";
			String path2 = "";
			if (!riskDiskList.isEmpty()) {
				path = graphicNew.drawRtPieChart(800, 500, riskDiskList);
				path2 = graphicNew.drawRtBarChart(810, 400, riskDiskList);
			}
			JSONArray ajaxarray = new JSONArray();
			JSONObject object = new JSONObject();
			object.put("pieChart", path);
			object.put("barChart", path2);
			ajaxarray.add(object.toString());
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, ajaxarray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("chu cuo");
			e.printStackTrace();
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, null);
		}
	}

	public void ajaxRule() {
		try {
			List<RuleFile> ruleFileList = new LinkedList<RuleFile>();
			List<RuleFile> flowRuleFileList = new LinkedList<RuleFile>();
			String condition = getConditon();
			ruleFileList = allAnalysisViewNewDAO.findRuleFileList(condition);
			flowRuleFileList = allAnalysisViewNew2DAO.findFlowRuleFileList(condition);
			ruleFileList.addAll(flowRuleFileList);
			Map session = ServletActionContext.getContext().getSession();
			session.put("ruleFileList", ruleFileList);
			JSONArray ajaxarray = JSONArray.fromObject(ruleFileList);
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, ajaxarray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("chu cuo");
			e.printStackTrace();
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, null);
		}
	}

	public void ajaxRuleBar() {
		try {
			String condition = getConditon();
			String path = "";
//			List fileList = new LinkedList();
//			fileList = allAnalysisViewNewDAO.findFile(condition);
//			Collections.sort(fileList, new Comparator<Object>() {
//				public int compare(Object o1, Object o2) {
//					Object[] o11 = (Object[]) o1;
//					Object[] o22 = (Object[]) o2;
//					return (int) ((Long) o22[1] - (Long) o11[1]);
//				}
//			});
			List<RuleFile> ruleFileList = new LinkedList<RuleFile>();
			List<RuleFile> flowRuleFileList = new LinkedList<RuleFile>();
//			Map session = ServletActionContext.getContext().getSession();
//			ruleFileList=(List<RuleFile>)session.get("ruleFileList");
//			System.out.println("ruleFileList********"+ruleFileList.size());
			ruleFileList = allAnalysisViewNewDAO.findRuleFileList(condition);
			flowRuleFileList = allAnalysisViewNew2DAO.findFlowRuleFileList(condition);
			ruleFileList.addAll(flowRuleFileList);
			Collections.sort(ruleFileList, new Comparator<RuleFile>() {
				public int compare(RuleFile r1, RuleFile r2) {
					return Integer.valueOf(r2.getEventNum()) - Integer.valueOf(r1.getEventNum());
				}
			});
			if (!ruleFileList.isEmpty()) {
				path = graphicNew.drawRuleBarChart(ruleFileList);
			}
			JSONArray ajaxarray = new JSONArray();
			JSONObject object = new JSONObject();
			object.put("barChart", path);
			ajaxarray.add(object.toString());
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, ajaxarray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, null);
		}
	}

	public void writeToResponse2(HttpServletResponse response, JSONArray jsonArr) {
		PrintWriter out = null;
		response.setContentType("text/html;charset=utf-8");
		try {
			out = response.getWriter();
			out.print(jsonArr.toString());
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	public static void main(String[] args) {
		// String
		// folderName=ServletActionContext.getServletContext().getRealPath("upload");
		File folder = new File("D://zx");
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.getName().contains("张仙"))
				file.delete();
		}
	}

}
