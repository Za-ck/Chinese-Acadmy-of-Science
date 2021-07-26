package com.action.analysis;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.action.ExcelReportAction;
import com.dao.DepartmentDAO;
import com.dao.KeyAnalysisViewDAO;
import com.dao.KeyAnalysisViewStaDAO;
import com.dao.OperationDAO;
import com.dao.ProbabilityDAO;
import com.dao.ReportViewDAO;
import com.dao.RiskDAO;
import com.dao.RiskTypeDAO;
import com.entity.ReportViewNew;
import com.entity.RiAnalysis;
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
import com.model.KeyAnalysisView;
import com.model.Operation;
import com.model.Probability;
import com.model.ReportView;
import com.model.Risk;
import com.model.RiskType;
import com.model.Users;
import com.opensymphony.xwork2.ActionContext;

public class KeyAnalysisAction {
	private String year;
	private List<RiAnalysis> ralist;
	private String choosedId;
	private List<KeyAnalysisView> riskEventlistall;
	private KeyAnalysisViewDAO keyAnalysisViewDAO;
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
	private String barPath2;
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
	private KeyAnalysisViewStaDAO keyAnalysisViewStaDAO;
	private List<RiAnalysis> depEventNumList;
	private String depEventNumBarPath;
	private String riskDepDiskPath;
	DecimalFormat dcmFmt = new DecimalFormat("0.00");

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

	public KeyAnalysisViewDAO getKeyAnalysisViewDAO() {
		return keyAnalysisViewDAO;
	}

	public void setKeyAnalysisViewDAO(KeyAnalysisViewDAO keyAnalysisViewDAO) {
		this.keyAnalysisViewDAO = keyAnalysisViewDAO;
	}

	public List<KeyAnalysisView> getRiskEventlistall() {
		return riskEventlistall;
	}

	public void setRiskEventlistall(List<KeyAnalysisView> riskEventlistall) {
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

	public String getBarPath2() {
		return barPath2;
	}

	public void setBarPath2(String barPath2) {
		this.barPath2 = barPath2;
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

	public KeyAnalysisViewStaDAO getKeyAnalysisViewStaDAO() {
		return keyAnalysisViewStaDAO;
	}

	public void setKeyAnalysisViewStaDAO(
			KeyAnalysisViewStaDAO keyAnalysisViewStaDAO) {
		this.keyAnalysisViewStaDAO = keyAnalysisViewStaDAO;
	}

	public List<RiAnalysis> getDepEventNumList() {
		return depEventNumList;
	}

	public void setDepEventNumList(List<RiAnalysis> depEventNumList) {
		this.depEventNumList = depEventNumList;
	}

	public String getDepEventNumBarPath() {
		return depEventNumBarPath;
	}

	public void setDepEventNumBarPath(String depEventNumBarPath) {
		this.depEventNumBarPath = depEventNumBarPath;
	}

	public String getRiskDepDiskPath() {
		return riskDepDiskPath;
	}

	public void setRiskDepDiskPath(String riskDepDiskPath) {
		this.riskDepDiskPath = riskDepDiskPath;
	}

	public String keyAnalysisDep() {
		risktype = "none1";
		riskname = "请选择";
		this.depEventNumBarPath = "";
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
		depEventNumList = new LinkedList<RiAnalysis>();
		// List depListall = new LinkedList();
		List<KeyAnalysisView> eventList = new LinkedList<KeyAnalysisView>();

		// 根据不同的角色获取不同的查看部门的权限，权限在4一下的只能查看本部门
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
		String userId = us.getUserId();
		// 清空上次此用户生成的图片文件
		File folder = new File(ServletActionContext.getServletContext()
				.getRealPath("upload"));
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.getName().contains(userId + "KeyDepEventNumBarchart")) {
				file.delete();
			}
		}

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		String currdate = formatter.format(new Date()); // 得到年月日 2014-09-02
		year = currdate.substring(0, 4);
		// 首次查询默认为当年
		dateFrom = year + "-01-01";
		dateTo = year + "-12-31";

		// 给综合评定和影响程度输入框赋默认最小和最大值
		// List valueListall=new LinkedList();
		// valueListall =keyAnalysisViewDAO.findMaxAndMinValue(year);
		// Object valueList=valueListall.get(0);
		// Object[] value=(Object[])valueList;

		// 综合评定输入框默认为通用取值范围
		riAllValueFrom = "0.00";
		riAllValueTo = "175.00";
		// List degreeListall=new LinkedList();
		// degreeListall =keyAnalysisViewDAO.findMaxAndMinDegree(year);
		// Object degreeList=degreeListall.get(0);
		// Object[] degree=(Object[])degreeList;
		// 影响程度输入框默认为通用取值范围
		riDegreeFrom = "2.00";
		riDegreeTo = "5.00";

		String condition = getConditon();
		eventList = keyAnalysisViewDAO.findDetailOrderByDep(condition);
		if (!eventList.isEmpty()) {
			getInfoNew(eventList);
//			int listNum=depEventNumList.size();
//			int width=0;
//			int height=0;
//			if(listNum<=5){
//			    width=500;
//			    height=550;
//			}else{
//				width=1000;
//				height=550;
//			}
			this.depEventNumBarPath = graphicNew.drawKeyDepEventNumBarChart(
					810, 600, depEventNumList);
//			this.depEventNumBarPath = graphicNew.drawKeyDepEventNumBarChart(
//					1000, 550, depEventNumList);
			Map session2 = ServletActionContext.getContext().getSession();
			session2.put("depEventNumBarPath", depEventNumBarPath);

		}
		// 找到存在事件的所有部门
		// depListall = keyAnalysisViewDAO.findDepByConditon(condition);
		// // System.out.println(depListall.size());
		// if (!depListall.isEmpty()) {
		// getInfoByCondion(depListall, condition);
		// }
		// System.out.println("ralist--------------"+ralist.size());
		// System.out.println("allProDegreeList--------------"+allProDegreeList.size());
		// System.out.println("alldepList--------------"+alldepList.size());

		return "success";
	}

	// 按部门名称查询报表导出
	public String exportExcelByDep() {
		Map session = ActionContext.getContext().getSession();
		// dsarray=(String[][])session.get("exportDepList");
		if (session.get("exportKeyList") == null)
			return "fail";
		else {
			String str = "关键风险分析按部门列表统计" + "    综合评定:" + riAllValueFrom + "至"
					+ riAllValueTo + "    影响程度:" + riDegreeFrom + "至"
					+ riDegreeTo + "    发生概率:" + riProDegree;
			if (riDepName == null || riDepName.equals("请选择")) {
				str += " 部门:全部";
			} else {
				str += " 部门:" + riDepName;
			}
			str += "    录入时间:" + dateFrom + "至" + dateTo;
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("风险分析列表统计查询", "GenAnaDepTemplate",
					(String[][]) session.get("exportKeyList"), 3, 5, str);// 4表示从第四行开始，5表示从第五列开始合并，str是查询条件
			return "success";
		}
	}

	public String keyAnalysisDepByValue() {
		risktype = "none1";
		riskname = "请选择";
		this.depEventNumBarPath = "";
		//this.choosedId="--请选择--";
		// 每个部门具体事件的list，传给页面显示
		ralist = new LinkedList<RiAnalysis>();
		depEventNumList = new LinkedList<RiAnalysis>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String userId = us.getUserId();
		// 清空上次此用户生成的图片文件
		File folder = new File(ServletActionContext.getServletContext()
				.getRealPath("upload"));
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.getName().contains(userId + "KeyDepEventNumBarchart")) {
				file.delete();
			}
		}
		// List depListall = new LinkedList();
		String condition = getConditon();
		List<KeyAnalysisView> eventList = new LinkedList<KeyAnalysisView>();
		eventList = keyAnalysisViewDAO.findDetailOrderByDep(condition);
		if (!eventList.isEmpty()) {
			getInfoNew(eventList);
			this.depEventNumBarPath = graphicNew.drawKeyDepEventNumBarChart(
					810, 600, depEventNumList);
//			this.depEventNumBarPath = graphicNew.drawKeyDepEventNumBarChart(
//					1000, 550, depEventNumList);
			Map session2 = ServletActionContext.getContext().getSession();
			session2.put("depEventNumBarPath", depEventNumBarPath);
		}
		return "success";
	}

	private void getInfoByCondion(List depListall, String condition) {
		// TODO Auto-generated method stub
		String depname;
		String depid;
		String rtname;
		int allcount = 0; // 总计栏，包含的元素个数
		int allvalue = 0; // 综合评定分子

		int depcount = 0;// 一个部门，包含的元素个数
		int depvalue = 0;// 综合评定分子

		if (depListall != null) {
			// 记录部门序号
			int i = 1;
			// 针对每个部门生成列表
			for (Object depList : depListall) {
				depcount = 0;// 事件个数置0
				depvalue = 0;// 评定值置0

				Object[] dep = (Object[]) depList;
				depid = (String) dep[0];
				depname = (String) dep[1];

				// 遍历部门的Risk
				List risklistall = keyAnalysisViewDAO
						.findRiskByDepAndCondition(depid, condition);
				// System.out.println(risklist.size());
				if (risklistall != null && !risklistall.isEmpty()) {
					// 记录二级风险序号
					int j = 1;

					// 针对每个部门生成列表
					// 遍历试图的所有Risk
					for (Object riskList : risklistall) {
						// 每一个Risk，所包含的元素
						riskEventlistall = keyAnalysisViewDAO
								.findDetailByCondition(depid,
										(String) riskList, condition);
						if (riskEventlistall != null
								&& !riskEventlistall.isEmpty()) {
							// 遍历一个Risk，所包含的元素
							for (int m = 0; m < riskEventlistall.size(); m++) {
								RiAnalysis ksone = new RiAnalysis();
								ksone.setDepNum(Integer.toString(i));
								ksone.setDepName(riskEventlistall.get(m)
										.getDepName());
								ksone.setRiNum(Integer.toString(j));
								ksone.setRiskName(riskEventlistall.get(m)
										.getRiskName());
								// 使得二级风险和一级风险一一对应
								if (j % 2 != 0) {
									ksone.setRtName(riskEventlistall.get(m)
											.getRtName());
								} else {
									ksone.setRtName(riskEventlistall.get(m)
											.getRtName()
											+ " ");
								}
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
								ksone.setRiKpi(riskEventlistall.get(m)
										.getRiKpi());
								ksone.setRiAllvalue(String.valueOf(dcmFmt
										.format(riskEventlistall.get(m)
												.getRiAllvalue())));
								if("2".equals(choosedId)){ksone.setRiEventDate(riskEventlistall.get(m)
										.getReModifydate());}else{
								ksone.setRiEventDate(riskEventlistall.get(m)
										.getReDate());}
								ralist.add(ksone);
								depcount++;
								depvalue += riskEventlistall.get(m)
										.getRiAllvalue();
							}
							j++;
						}

					}
				}

				// 每一个部门小计的位置
				// 小计功能
				RiAnalysis ksone = new RiAnalysis();
				ksone.setDepNum(Integer.toString(i++) + " ");
				ksone.setDepName(depname + " ");
				ksone.setRiNum("小计");
				ksone.setRiskName(Integer.toString(risklistall.size()));
				ksone.setRtName("");
				ksone.setReEventname(Integer.toString(depcount) + " ");
				// //综合评定
				// if(depcount==0)ksone.setRiAllvalue("0.00");
				// else
				// ksone.setRiAllvalue(String.valueOf(dcmFmt.format(depvalue/(double)(depcount))));
				ralist.add(ksone);

				allcount += depcount;
				allvalue += depvalue;

			}
			// 总计的位置

			// 小计功能
			RiAnalysis ksone = new RiAnalysis();
			ksone.setDepNum("总计");
			ksone.setDepName(Integer.toString(depListall.size()));
			ksone.setRiNum("");
			ksone.setRiskName("");
			ksone.setRtName("");
			ksone.setReEventname(Integer.toString(allcount));
			// 综合评定
			// if(allcount==0)ksone.setRiAllvalue("0.00");
			// else
			// ksone.setRiAllvalue(String.valueOf(dcmFmt.format(allvalue/(double)(allcount))));
			ralist.add(ksone);

		}

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
		session.put("exportKeyList", dsarray);

	}

	private void getInfoNew(List<KeyAnalysisView> eventList) {
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
				ksone.setRiEventDate(eventList.get(m).getReModifydate());}
			else{
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
					depEventNumList.add(ksone2);
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
				depEventNumList.add(ksone2);
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
		session.put("exportKeyList", dsarray);

	}

	public String keyAnalysisRisk() {
//		this.piePath = "";
		this.barPath = "";
		this.barPath2 = "";
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
		// 根据不同的角色获取不同的查看部门的权限，权限在5一下的只能查看本部门
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
		String userId = us.getUserId();
		// 清空上次此用户生成的图片文件
		File folder = new File(ServletActionContext.getServletContext()
				.getRealPath("upload"));
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.getName().contains(userId + "keySectionPiechart")
					|| file.getName().contains(userId + "keyRiskNumBarchart")
					|| file.getName().contains(userId + "barchart2")) {
				file.delete();
			}
		}
		// 每个部门具体事件的list，传给页面显示
		ralist = new LinkedList<RiAnalysis>();
		// List riskListall = new LinkedList();
		List<KeyAnalysisView> eventList = new LinkedList<KeyAnalysisView>();
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

		// 给综合评定和影响程度输入框赋默认最小和最大值
		// List valueListall=new LinkedList();
		// valueListall =keyAnalysisViewDAO.findMaxAndMinValue(year);
		// Object valueList=valueListall.get(0);
		// Object[] value=(Object[])valueList;

		// 综合评定输入框默认为通用取值范围
		riAllValueFrom = "0.00";
		riAllValueTo = "175.00";

		// List degreeListall=new LinkedList();
		// degreeListall =keyAnalysisViewDAO.findMaxAndMinDegree(year);
		// Object degreeList=degreeListall.get(0);
		// Object[] degree=(Object[])degreeList;
		// 影响程度输入框默认为通用取值范围
		riDegreeFrom = "2.00";
		riDegreeTo = "5.00";
        System.out.println(choosedId+"sagfdg江泉");
		String condition = getConditon();
		// 找到存在事件的所有部门
		// riskListall = keyAnalysisViewDAO.findRiskByConditon(condition);
		eventList = keyAnalysisViewDAO.findDetailOrderByRisk(condition);
		reportList = new LinkedList<ReportViewNew>();
		// System.out.println(depListall.size());
		// if (!riskListall.isEmpty()) {
		// getInfoRiskByCondition(riskListall, condition);
		// Map session2 = ServletActionContext.getContext().getSession();
		// session2.put("reportList", reportList);
		// // 复制一个reportList，防止画图排序后顺序变乱
		// List<ReportViewNew> useReportList = new ArrayList<ReportViewNew>();
		// useReportList.addAll(reportList);
		// this.piePath = graphicNew.drawKeyPieChart(750, 500, useReportList);
		// this.barPath = graphicNew.drawKeyBarChart(800, 500, useReportList);
		// this.barPath2 = graphicNew.drawBarChart2(800, 500, useReportList);
		// }

		if (!eventList.isEmpty()) {
			getInfoRiskNew(eventList);
			Map session2 = ServletActionContext.getContext().getSession();
			session2.put("keyReportList", reportList);
			// 复制一个reportList，防止画图排序后顺序变乱
			List<ReportViewNew> useReportList = new ArrayList<ReportViewNew>();
			useReportList.addAll(reportList);
//			this.piePath = graphicNew.drawKeyPieChart(600, 400, useReportList);
			this.barPath = graphicNew.drawKeyBarChart(useReportList);
			this.barPath2 = graphicNew.drawBarChart2(useReportList);
		}
		return "success";
	}

	// 按二级风险名称查询报表导出
	public String exportExcelByRisk() {
		String datenum="";
		if("2".equals(choosedId)){
			datenum="发布时间：";
		}else{
			datenum="录入时间：";
		}
		Map session = ActionContext.getContext().getSession();
		// dsarray=(String[][])session.get("exportDepList");
		if (session.get("exportKeyRiskList") == null)
			return "fail";
		else {
			String str = "关键风险分析按风险类型列表统计" + "    综合评定:" + riAllValueFrom + "至"
					+ riAllValueTo + "    影响程度:" + riDegreeFrom + "至"
					+ riDegreeTo + "    发生概率:" + riProDegree;
			if (riskname == null || riskname.equals("请选择")) {
				str += " 二级风险:全部";
			} else {
				str += " 二级风险:" + riskname;
			}
			str += "    "+datenum + dateFrom + "至" + dateTo;
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("风险分析列表统计查询", "GenAnaRiskTemplate",
					(String[][]) session.get("exportKeyRiskList"), 3, 4, str);// 3表示从第四行开始，5表示从第五列开始合并，str是查询条件
			return "success";
		}
	}

	public String keyAnalysisRiskByValue() {
		this.piePath = "";
		this.barPath = "";
		this.barPath2 = "";
		// 根据不同的角色获取不同的查看部门的权限，权限在4以下的只能查看本部门
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String userId = us.getUserId();
		// 清空上次此用户生成的图片文件
		File folder = new File(ServletActionContext.getServletContext()
				.getRealPath("upload"));
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.getName().contains(userId + "keySectionPiechart")
					|| file.getName().contains(userId + "keyRiskNumBarchart")
					|| file.getName().contains(userId + "barchart2")) {
				file.delete();
			}
		}
		// 每个部门具体事件的list，传给页面显示
		ralist = new LinkedList<RiAnalysis>();
		List riskListall = new LinkedList();
		String condition = getConditon();
		// 找到存在事件的所有部门
		// riskListall = keyAnalysisViewDAO.findRiskByConditon(condition);
		List<KeyAnalysisView> eventList = new LinkedList<KeyAnalysisView>();
		reportList = new LinkedList<ReportViewNew>();
		// System.out.println(depListall.size());
		// if (!riskListall.isEmpty()) {
		// getInfoRiskByCondition(riskListall, condition);
		// // 复制一个reportList，防止画图排序后顺序变乱
		// List<ReportViewNew> useReportList = new ArrayList<ReportViewNew>();
		// useReportList.addAll(reportList);
		// Map session2 = ServletActionContext.getContext().getSession();
		// session2.put("reportList", reportList);
		// this.piePath = graphicNew.drawKeyPieChart(750, 500, useReportList);
		// this.barPath = graphicNew.drawKeyBarChart(800, 500, useReportList);
		// this.barPath2 = graphicNew.drawBarChart2(800, 500, useReportList);
		// }
		eventList = keyAnalysisViewDAO.findDetailOrderByRisk(condition);
		if (!eventList.isEmpty()) {
			getInfoRiskNew(eventList);
			Map session2 = ServletActionContext.getContext().getSession();
			session2.put("keyReportList", reportList);
			// 复制一个reportList，防止画图排序后顺序变乱
			List<ReportViewNew> useReportList = new ArrayList<ReportViewNew>();
			useReportList.addAll(reportList);
//			this.piePath = graphicNew.drawKeyPieChart(600, 400, useReportList);
			this.barPath = graphicNew.drawKeyBarChart( useReportList);
			this.barPath2 = graphicNew.drawBarChart2(useReportList);
		}
		return "success";
	}

	// 得到每个二级风险的简要list(8列)
	private void getInfoRiskByCondition(List riskListall, String condition) {
		// TODO Auto-generated method stub
		// 记录风险的月数
		int n = Integer.valueOf(dateTo.substring(5, 7))
				- Integer.valueOf(dateFrom.substring(5, 7));
		String riskName;
		String riskId;
		String rtName;
		String riskQueue;
		int allPro = 0;// 记录所有事件权值（发生概率）之和
		int allcount = 0; // 总计栏，包含的元素个数
		float allDegree = 0;// 记录所有风险事件影响程度加权之和
		int allvalue = 0; // 综合评定分子

		int riskPro = 0;// 记录一个二级风险包含的所有风险事件权值（发生概率）之和
		int riskcount = 0;// 一个二级风险包含的元素个数
		float riskDegree = 0;// 记录一个二级风险包含的所有风险事件影响程度加权之和
		int riskvalue = 0;// 综合评定分子
		float proValue = 0;// 记录一个二级风险包含的所有风险事件发生概率具体值之和
		int days = 0;

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
				List deplistall = keyAnalysisViewDAO
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
						riskEventlistall = keyAnalysisViewDAO
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
				reportViewNew.setRiAllvalue(Double.valueOf(dcmFmt
						.format(riskvalue / (double) (riskPro))));
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
		session.put("exportKeyList", dsarray);
	}

	// 得到每个二级风险的简要list(8列)
	private void getInfoRiskNew(List<KeyAnalysisView> eventList) {
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
					ReportViewNew reportViewNew = new ReportViewNew();
					ksone2.setRiNum(risknum + " ");
					ksone2.setRtName("小计");
					ksone2.setRiskName(riskname1 + " ");
					reportViewNew.setRiskName(riskname1);
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
					ksone2.setRiAlldegree(String.valueOf(dcmFmt
							.format(riskDegree / (double) (riskPro))));
					reportViewNew.setRiskValuex((int) Math.ceil(riskDegree
							/ (double) (riskPro)));
					reportViewNew.setRiAllvalue(Double.valueOf(dcmFmt
							.format(riskvalue / (double) (riskPro))));
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
						ksone2.setRiAllvalue(String.valueOf(dcmFmt
								.format(riskvalue / (double) (riskPro))));
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
				reportViewNew.setRiAllvalue(Double.valueOf(dcmFmt
						.format(riskvalue / (double) (riskPro))));
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
		session.put("exportKeyRiskList", dsarray);

	}

	public String keyAnalysisRiskAllByValue() {
		// 每个部门具体事件的list，传给页面显示
		ralist = new LinkedList<RiAnalysis>();
		// List riskListall = new LinkedList();
		List<KeyAnalysisView> eventList = new LinkedList<KeyAnalysisView>();
		String condition = getConditon();
		// 找到存在事件的所有部门
		// riskListall = keyAnalysisViewDAO.findRiskByConditon(condition);
		// // System.out.println(depListall.size());
		// if (!riskListall.isEmpty()) {
		// getInfoRiskAllByCondition(riskListall, condition);
		// }
		eventList = keyAnalysisViewDAO.findDetailOrderByRisk(condition);
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
				List deplistall = keyAnalysisViewDAO
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
						riskEventlistall = keyAnalysisViewDAO
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
		session.put("exportKeyList", dsarray);
	}

	// 得到每个二级风险的简要list(8列)
	private void getInfoRiskAllNew(List<KeyAnalysisView> eventList) {
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
		session.put("exportKeyRiskAllList", dsarray);

	}

	// 按二级风险名称查询报表导出(24列)
	public String exportExcelAllByRisk() {
		Map session = ActionContext.getContext().getSession();
		// dsarray=(String[][])session.get("exportDepList");
		if (session.get("exportKeyRiskAllList") == null)
			return "fail";
		else {
			String str = "关键风险分析按风险类型列表统计-详细" + "    综合评定:" + riAllValueFrom
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
							(String[][]) session.get("exportKeyRiskAllList"),
							3, 4, str);// 3表示从第三行开始，5表示从第五列开始合并，str是查询条件
			return "success";
		}
	}

	// 按二级风险名称查询报表导出(24列)
	// public String exportStaExcelAllByRisk() {
	// Map session = ActionContext.getContext().getSession();
	// // dsarray=(String[][])session.get("exportDepList");
	// if (session.get("exportKeyList") == null)
	// return "fail";
	// else {
	// ExcelReportAction ex = new ExcelReportAction();
	// ex.ReportExcelStandard("风险分析列表统计查询", "GenAnaAllRiskTemplate",
	// (String[][]) session.get("exportKeyList"), 3, 5,
	// "");//3表示从第三行开始，5表示从第五列开始合并，str是查询条件
	// return "success";
	// }
	// }

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

	private String getConditonRiskDep() {
		// TODO Auto-generated method stub
		String str = "";
		String datema="";
		if (riskname == null || riskname.equals("请选择")) {
			str += "";
		} else {
			if (!str.equals("")) {
				str += " and ";
			}
			str += "model.riskName='" + riskname + "'";
		}
		if("2".equals(choosedId)){
			datema="model.reModifydate";
		}else{
			datema="model.reDate";
		}
		if (dateFrom != null && dateTo != null && !dateFrom.equals("")
				&& !dateTo.equals("")) {
			if (!str.equals("")) {
				str += " and ";
			}
			str += datema+" between '" + dateFrom + " 00:00:00"
					+ "' and '" + dateTo + " 23:59:59" + "'";
		} else if (dateFrom != null && !dateFrom.equals("")) {
			if (!str.equals("")) {
				str += " and ";
			}
			str += datema+">='" + dateFrom + " 00:00:00" + "'";
		} else if (dateTo != null && !dateTo.equals("")) {
			if (!str.equals("")) {
				str += " and ";
			}
			str += datema+"<='" + dateTo + " 23:59:59" + "'";
		} else {
			str += "";
		}if (riDepName == null || riDepName.equals("请选择")) {
			str += "";
		} else {
			if (!str.equals("")) {
				str += " and ";
			}
			str += "model.depName='" + riDepName + "'";
		}

		if (!str.equals(""))
			str = " where " + str + " " + " and model.riAllvalue!=0 ";
		return str;
	}

	public void getProDegreeList() {
		allProDegreeList = new LinkedList<String>();
		allProDegreeList.add("请选择");
		// allProDegreeList.add("1(0%~5%)");
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
			this.allrtList.addAll(this.rtList);

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	public void getReportImg() throws IOException {
		// String condition=getConditon();
		// reportList=getKeyAnalysisViewDAO().findReportList(condition);
		// for(ReportView reportView:reportList){
		// System.out.println(reportView.getId().getRiskName());
		// }
		// System.out.println(reportList.size());
		// 获取分区图
		// this.path = graphicNew.drawTableShadow(1200, 1000, reportList);
		// HttpServletResponse response = ServletActionContext.getResponse();
		// writeToResponse(response, path);

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
		reportList = (List) session.get("keyReportList");
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
		String contextString2 = "表1：二级风险概率统计表";
		Paragraph context2 = new Paragraph(contextString2);
		context2.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context2);
		Table rTable = new Table(5);

		int rwidth[] = { 30, 50, 30, 10, 10 };
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
		Cell rhaderCell1 = new Cell("风险编号");
		rhaderCell1.setHeader(true);
		// rhaderCell1.setBackgroundColor(Color.GRAY);
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

			Cell rcell1 = new Cell(reportList.get(i).getRiskQueue());
			Cell rcell2 = new Cell(reportList.get(i).getRiskName());
			Cell rcell3 = new Cell(reportList.get(i).getRiskNum());
			Cell rcell4 = new Cell(reportList.get(i).getFreq());
			Cell rcell5 = new Cell(reportList.get(i).getReportFreq() + "");
			rcell1.setBackgroundColor(Color.decode("#e9f2f7"));
			rcell2.setBackgroundColor(Color.decode("#e9f2f7"));
			rcell3.setBackgroundColor(Color.decode("#e9f2f7"));
			rcell4.setBackgroundColor(Color.decode("#e9f2f7"));
			rcell5.setBackgroundColor(Color.decode("#e9f2f7"));
			rTable.setAlignment(Element.ALIGN_CENTER);
			rTable.addCell(rcell1);
			rTable.addCell(rcell2);
			rTable.addCell(rcell3);
			rTable.addCell(rcell4);
			rTable.addCell(rcell5);
		}
		document.add(rTable);
		document.add(new Paragraph("\n"));
		String riskNumBarchartPath = ServletActionContext.getServletContext()
				.getRealPath(barPath);
		Image img = Image.getInstance(riskNumBarchartPath);
		img.setAbsolutePosition(0, 0);
		img.setAlignment(Image.MIDDLE);// 设置图片显示位置
		img.scalePercent(50);// 表示显示的大小为原尺寸的50%
		document.add(img);
		String contextString3 = "图1：二级风险事件数柱状图";
		Paragraph context3 = new Paragraph(contextString3);
		context3.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context3);
		String barchart2Path = ServletActionContext.getServletContext()
				.getRealPath(barPath2);
		img = Image.getInstance(barchart2Path);
		img.setAbsolutePosition(0, 0);
		img.setAlignment(Image.MIDDLE);// 设置图片显示位置
		img.scalePercent(50);// 表示显示的大小为原尺寸的50%
		document.add(img);
		String contextString4 = "图2：二级风险综合评定值柱状图";
		Paragraph context4 = new Paragraph(contextString4);
		context4.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context4);
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
			areaA = "包括" + areaA;
		if (areaB.equals(""))
			areaB = "目前我院尚无";
		else
			areaB = "包括" + areaB;
		if (areaC.equals(""))
			areaC = "目前我院尚无";
		else
			areaC = "包括" + areaC;

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
		riskEventlistall = keyAnalysisViewDAO.findDetailByReId(reId);
		// System.out.println("riNum----------"+riNum);
		// System.out.println("reId-----------"+reId);
		// System.out.println("riskEventlistall-----------"+riskEventlistall.size());
		eventDetailList = new LinkedList<RiAnalysis>();
		for (int m = 0; m < riskEventlistall.size(); m++) {
			RiAnalysis ksone = new RiAnalysis();
			ksone.setRiNum(riNum);
			ksone.setRiskId(riskEventlistall.get(m).getRiskId());
			;
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

	public String KeyAnalysisDepAllByValue() {
		// 每个部门具体事件的list，传给页面显示
		ralist = new LinkedList<RiAnalysis>();
		// List depListall = new LinkedList();
		List<KeyAnalysisView> eventList = new LinkedList<KeyAnalysisView>();
		String condition = getConditon();
		eventList = keyAnalysisViewDAO.findDetailOrderByDep(condition);
		if (!eventList.isEmpty()) {
			getInfoAllNew(eventList);
		}
		// 找到存在事件的所有部门
		// depListall = keyAnalysisViewDAO.findDepByConditon(condition);
		// // System.out.println(depListall.size());
		// if (!depListall.isEmpty()) {
		// getInfoDepAllByCondition(depListall, condition);
		// }
		return "success";
	}

	private void getInfoDepAllByCondition(List depListall, String condition) {
		// TODO Auto-generated method stub
		String depname;
		String depid;
		String rtname;
		int allcount = 0; // 总计栏，包含的元素个数
		int allvalue = 0; // 综合评定分子

		int depcount = 0;// 一个部门，包含的元素个数
		int depvalue = 0;// 综合评定分子

		if (depListall != null) {
			// 记录部门序号
			int i = 1;
			// 针对每个部门生成列表
			for (Object depList : depListall) {
				depcount = 0;// 事件个数置0
				depvalue = 0;// 评定值置0

				Object[] dep = (Object[]) depList;
				depid = (String) dep[0];
				depname = (String) dep[1];

				// 遍历部门的Risk
				List risklistall = keyAnalysisViewDAO
						.findRiskByDepAndCondition(depid, condition);
				//System.out.println("---------------" + risklistall.size());
				if (risklistall != null && !risklistall.isEmpty()) {
					// 记录二级风险序号
					int j = 1;

					// 针对每个部门生成列表
					// 遍历试图的所有Risk
					for (Object riskList : risklistall) {
						// 每一个Risk，所包含的元素
						riskEventlistall = keyAnalysisViewDAO
								.findDetailByCondition(depid,
										(String) riskList, condition);
						//System.out.println("------" + riskEventlistall.size());
						if (riskEventlistall != null
								&& !riskEventlistall.isEmpty()) {
							// 遍历一个Risk，所包含的元素
							for (int m = 0; m < riskEventlistall.size(); m++) {

								RiAnalysis ksone = new RiAnalysis();
								ksone.setDepNum(Integer.toString(i));
								ksone.setDepName(riskEventlistall.get(m)
										.getDepName());
								ksone.setRiNum(Integer.toString(j));
								ksone.setRiskId(riskEventlistall.get(m)
										.getRiskId());
								ksone.setRiskName(riskEventlistall.get(m)
										.getRiskName());
								// 使得二级风险和一级风险一一对应
								if (j % 2 != 0) {
									ksone.setRtName(riskEventlistall.get(m)
											.getRtName());
								} else {
									ksone.setRtName(riskEventlistall.get(m)
											.getRtName()
											+ " ");
								}
								ksone.setReEventname(riskEventlistall.get(m)
										.getReEventname());
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
								ksone.setRiAlldegree(dcmFmt
										.format(riskEventlistall.get(m)
												.getRiAlldegree()));
								ksone.setRiProdegree(Integer
										.toString(riskEventlistall.get(m)
												.getRiProdegree()));
								ksone.setRiKpi(riskEventlistall.get(m)
										.getRiKpi());
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
								depcount++;
								depvalue += riskEventlistall.get(m)
										.getRiAllvalue();
							}
							j++;
						}

					}
				}

				// 每一个部门小计的位置
				// 小计功能
				RiAnalysis ksone = new RiAnalysis();
				ksone.setDepNum(Integer.toString(i++) + " ");
				ksone.setDepName(depname + " ");
				ksone.setRiNum("小计");
				ksone.setRiskId("  ");
				ksone.setRiskName(Integer.toString(risklistall.size()));
				ksone.setRtName("  ");
				ksone.setReEventname(Integer.toString(depcount) + " ");
				// //综合评定
				// if(depcount==0)ksone.setRiAllvalue("0.00");
				// else
				// ksone.setRiAllvalue(String.valueOf(dcmFmt.format(depvalue/(double)(depcount))));
				ralist.add(ksone);

				allcount += depcount;
				allvalue += depvalue;

			}
			// 总计的位置

			// 小计功能
			RiAnalysis ksone = new RiAnalysis();
			ksone.setDepNum("总计");
			ksone.setDepName(Integer.toString(depListall.size()));
			ksone.setRiNum("");
			ksone.setRiskId("");
			ksone.setRiskName("");
			ksone.setRtName("");
			ksone.setReEventname(Integer.toString(allcount));
			// // 综合评定
			// if (allcount == 0)
			// ksone.setRiAllvalue("0.00");
			// else
			// ksone.setRiAllvalue(String.valueOf(dcmFmt.format(allvalue
			// / (double) (allcount))));
			ralist.add(ksone);

		}

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
		session.put("exportKeyDepAllList", dsarray);

	}

	private void getInfoAllNew(List<KeyAnalysisView> eventList) {
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
		session.put("exportKeyDepAllList", dsarray);

	}

	// 按二级风险名称查询报表导出(24列)
	public String exportExcelAllByDep() {
		Map session = ActionContext.getContext().getSession();
		// dsarray=(String[][])session.get("exportDepList");
		if (session.get("exportKeyDepAllList") == null)
			return "fail";
		else {
			String str = "关键风险分析按部门列表统计-详细" + "    综合评定:" + riAllValueFrom
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
					(String[][]) session.get("exportKeyDepAllList"), 3, 5, str);// 3表示从第三行开始，5表示从第五列开始合并，str是查询条件
			return "success";
		}
	}

	public String getRiskEventDetailByIdDep() {
		riskEventlistall = keyAnalysisViewDAO.findDetailByReId(reId);
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

	// 得到每个二级风险的详细list(24列)
	private String[][] getInfoRiskAllStaByCondition(String condition) {
		List riskListall = new LinkedList();
		ralist = new LinkedList<RiAnalysis>();
		// 找到存在事件的所有部门
		riskListall = keyAnalysisViewDAO.findRiskByConditon(condition);
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
				List deplistall = keyAnalysisViewDAO
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
						riskEventlistall = keyAnalysisViewDAO
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
								ksone.setRiEventDate("风险来源："
										+ riskEventlistall.get(m).getRiSource()
										+ "；影响指标："
										+ riskEventlistall.get(m).getRiKpi()
										+ "；业务领域："
										+ riskEventlistall.get(m)
												.getRiBusarea());
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
				ksone.setRiNum("小计");
				ksone.setRiskName(riskName + " ");
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
				i++;

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
			ksone.setRiAlldegree(String.valueOf(dcmFmt.format(allDegree
					/ (double) (allPro))));
			// 综合评定
			if (allcount == 0)
				ksone.setRiAllvalue("0.00");
			else {
				ksone.setRiAllvalue(String.valueOf(dcmFmt.format(allvalue
						/ (double) (allPro))));
			}
			ralist.add(ksone);

		}

		// 数据存放在session中，便于导出excel
		String[][] dsarray = new String[ralist.size()][21];
		for (int m = 0; m < ralist.size(); m++) {
			dsarray[m][0] = ralist.get(m).getRiNum().trim();
			dsarray[m][1] = ralist.get(m).getRiskName().trim();
			dsarray[m][2] = ralist.get(m).getReId();
			dsarray[m][3] = ralist.get(m).getReEventname();
			dsarray[m][4] = ralist.get(m).getRiEventDate();
			dsarray[m][5] = ralist.get(m).getRiProdegree();
			dsarray[m][6] = ralist.get(m).getRiFindegree();
			dsarray[m][7] = ralist.get(m).getRiFamedegree();
			dsarray[m][8] = ralist.get(m).getRiLawdegree();
			dsarray[m][9] = ralist.get(m).getRiClidegree();
			dsarray[m][10] = ralist.get(m).getRiEmpdegree();
			dsarray[m][11] = ralist.get(m).getRiOpedegree();
			dsarray[m][12] = ralist.get(m).getRiSafedegree();
			dsarray[m][13] = ralist.get(m).getRiFinvalue();
			dsarray[m][14] = ralist.get(m).getRiFramevalue();
			dsarray[m][15] = ralist.get(m).getRiLawvalue();
			dsarray[m][16] = ralist.get(m).getRiLawvalue();
			dsarray[m][17] = ralist.get(m).getRiEmpvalue();
			dsarray[m][18] = ralist.get(m).getRiOpevalue();
			dsarray[m][19] = ralist.get(m).getRiSafevalue();
			dsarray[m][20] = ralist.get(m).getRiAllvalue();
		}
		return dsarray;
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

	// 导出关键风险列表
	public String exportExcelByRiskSta() {
		String depName = "";
		String riskAdmin = "";
		String adminTime = "";
		String riskVerifier = "";
		String verifyTime = "";
		String condition = getConditon();
		List depList = keyAnalysisViewStaDAO.findDepByConditon(condition);
		if (depList.size() > 1) {
			depName = "全部";
		} else {
			Object dep = (Object) depList.get(0);
			Object[] deps = (Object[]) dep;
			depName = (String) deps[1];
		}
		List riskAmdinList = keyAnalysisViewStaDAO
				.findRiskAdminByConditon(condition);
		if (riskAmdinList.size() > 1) {
			riskAdmin = "全部";
		} else {
			Object admin = (Object) riskAmdinList.get(0);
			riskAdmin = (String) admin;
		}
		List adminTimeList = keyAnalysisViewStaDAO
				.findAdminTimeByConditon(condition);
		if (adminTimeList.size() > 1) {
			adminTime = "全部";
		} else {
			Object adminTimes = (Object) adminTimeList.get(0);
			adminTime = (String) adminTimes;
		}
		List riskVerifierList = keyAnalysisViewStaDAO
				.findRiskVerifierByConditon(condition);
		if (riskVerifierList.size() > 1) {
			riskVerifier = "全部";
		} else {
			Object riskVerifiers = (Object) riskVerifierList.get(0);
			riskVerifier = (String) riskVerifiers;
		}
		List verifyTimeList = keyAnalysisViewStaDAO
				.findVerifyTimeByConditon(condition);
		if (verifyTimeList.size() > 1) {
			verifyTime = "全部";
		} else {
			Object verifyTimes = (Object) verifyTimeList.get(0);
			verifyTime = (String) verifyTimes;
		}
		String[][] dsarray = new String[ralist.size()][21];
		dsarray = getInfoRiskAllStaByCondition(condition);
		ExcelReportAction ex = new ExcelReportAction();
		ex.ReportRiskStaExcel("关键风险标准列表", "StaKeyAnaAllRiskTemplate", dsarray,
				6, 2, depName, riskAdmin, adminTime, riskVerifier, verifyTime);
		return "success";
	}

	public String keyAnalysisRiskDep() {
		this.choosedId="--请选择--";
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
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		int position = us.getUserPosition();
		if (position ==1 || position ==3 || position ==5) {
			this.setRiDepName(this.getDepartmentDao().getdepname(
					us.getUserDep()));
		} else {
			this.setRiDepName("请选择");
		}
		this.riskDepDiskPath = "";
		return "success";
	}

	public String getRiskDepPie() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String userId = us.getUserId();
		// 清空上次此用户生成的图片文件
		File folder = new File(ServletActionContext.getServletContext()
				.getRealPath("upload"));
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.getName().contains(userId + "riskDepPiechart")) {
				file.delete();
			}
		}
		String condition = getConditonRiskDep();
		this.riskDepDiskPath = "";
		List<KeyAnalysisView> riskDepList = new LinkedList<KeyAnalysisView>();
		//List riskDepList = new LinkedList();
		riskDepList = keyAnalysisViewDAO.findRiskDepDisk(condition);
		if (!riskDepList.isEmpty() && !riskname.equals("请选择")) {
			this.riskDepDiskPath = graphicNew.drawRiskDepPieChart(800, 500,
					riskDepList);
			Map session2 = ServletActionContext.getContext().getSession();
			session2.put("riskDepDiskPath", riskDepDiskPath);
		}
		return "success";
	}

	// 导出统计图
	public String exportWordDep() {
		String FileDefaultName = year + "年度部门风险统计图";
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		FileDefaultName += df.format(dt);

		try {
			createWordFileDep(FileDefaultName);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}

	public boolean createWordFileDep(String FileDefaultName)
			throws DocumentException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		FileDefaultName = new String(FileDefaultName.getBytes(), "iso8859-1");// 解决中文
		Map session = ActionContext.getContext().getSession();
		String depEventNumBarPath = ServletActionContext.getServletContext()
				.getRealPath((String) session.get("depEventNumBarPath"));
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
		Paragraph title = new Paragraph("中南电力设计院部门风险统计图");
		// 设置标题格式对齐方式
		title.setAlignment(Element.ALIGN_CENTER);
		title.setFont(titleFont);
		document.add(title);
		;
		Image img = Image.getInstance(depEventNumBarPath);
		img.setAbsolutePosition(0, 0);
		img.setAlignment(Image.LEFT);// 设置图片显示位置
		img.scalePercent(55);// 表示显示的大小为原尺寸的50%
		document.add(img);
		String contextString = "部门风险事件数柱状图";
		Paragraph context = new Paragraph(contextString);
		context.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context);
		document.close();
		return true;
	}

	// 导出统计图
	public String exportWordRiskDep() {
		String FileDefaultName = year + "年度风险部门综合评定统计图";
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		FileDefaultName += df.format(dt);

		try {
			createWordFileRiskDep(FileDefaultName);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}

	public boolean createWordFileRiskDep(String FileDefaultName)
			throws DocumentException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		FileDefaultName = new String(FileDefaultName.getBytes(), "iso8859-1");// 解决中文
		Map session = ActionContext.getContext().getSession();
		String riskDepDiskPath = ServletActionContext.getServletContext()
				.getRealPath((String) session.get("riskDepDiskPath"));
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
		Paragraph title = new Paragraph("中南电力设计院风险部门综合评定值统计图");
		// 设置标题格式对齐方式
		title.setAlignment(Element.ALIGN_CENTER);
		title.setFont(titleFont);
		document.add(title);
		Image img = Image.getInstance(riskDepDiskPath);
		img.setAbsolutePosition(0, 0);
		img.setAlignment(Image.LEFT);// 设置图片显示位置
		img.scalePercent(60);// 表示显示的大小为原尺寸的50%
		document.add(img);
		String contextString = "二级风险部门综合评定比例饼状图 ";
		Paragraph context = new Paragraph(contextString);
		context.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(context);
		document.close();
		return true;
	}

}
