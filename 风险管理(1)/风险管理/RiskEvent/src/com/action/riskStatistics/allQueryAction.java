package com.action.riskStatistics;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.action.ExcelReportAction;
import com.dao.AllStaView1DAO;
import com.dao.AllStaViewDAO;
import com.dao.EventFileDAO;
import com.dao.EventFlowFileDAO;
import com.dao.FileManageDAO;
import com.model.AllStaView;
import com.model.AllStaView1;
import com.model.EventFile;
import com.model.EventFlowFile;
import com.model.FileManage;
import com.model.Users;

public class allQueryAction {
	private String year;
	private String quarter;
	private List allList;
	private List<AllStaView1> allList1;
	private AllStaViewDAO allStaViewDAO;
	private AllStaView1DAO allStaView1DAO;
	private List<EventFile> fileList;
	private EventFileDAO eventFileDao;
	private EventFlowFileDAO eventFlowFileDao;
	private List<FileManage> efileList;// 根据文件编号查找文件名称
	private FileManageDAO fileManageDao;
	private String reid;// 根据风险事件的编号查询相应的涉及流程文件
	private List<EventFile> efList;
	EventFile eventFile = new EventFile();
	FileManage fileManage = new FileManage();
	private String choosedId;
	
	public String getChoosedId() {
		return choosedId;
	}

	public void setChoosedId(String choosedId) {
		this.choosedId = choosedId;
	}

	public List<AllStaView1> getAllList1() {
		return allList1;
	}

	public void setAllList1(List<AllStaView1> allList1) {
		this.allList1 = allList1;
	}

	public AllStaView1DAO getAllStaView1DAO() {
		return allStaView1DAO;
	}

	public void setAllStaView1DAO(AllStaView1DAO allStaView1DAO) {
		this.allStaView1DAO = allStaView1DAO;
	}

	public List<EventFile> getEfList() {
		return efList;
	}

	public void setEfList(List<EventFile> efList) {
		this.efList = efList;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public List<AllStaView> getAllList() {
		return allList;
	}

	public void setAllList(List<AllStaView> allList) {
		this.allList = allList;
	}

	public AllStaViewDAO getAllStaViewDAO() {
		return allStaViewDAO;
	}

	public void setAllStaViewDAO(AllStaViewDAO allStaViewDAO) {
		this.allStaViewDAO = allStaViewDAO;
	}

	public List<EventFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<EventFile> fileList) {
		this.fileList = fileList;
	}

	public EventFileDAO getEventFileDao() {
		return eventFileDao;
	}

	public void setEventFileDao(EventFileDAO eventFileDao) {
		this.eventFileDao = eventFileDao;
	}

	public String getReid() {
		return reid;
	}

	public List<FileManage> getEfileList() {
		return efileList;
	}

	public void setEfileList(List<FileManage> efileList) {
		this.efileList = efileList;
	}

	public FileManageDAO getFileManageDao() {
		return fileManageDao;
	}

	public void setFileManageDao(FileManageDAO fileManageDao) {
		this.fileManageDao = fileManageDao;
	}

	public void setReid(String reid) {
		this.reid = reid;
	}
	
	
	public EventFlowFileDAO getEventFlowFileDao() {
		return eventFlowFileDao;
	}

	public void setEventFlowFileDao(EventFlowFileDAO eventFlowFileDao) {
		this.eventFlowFileDao = eventFlowFileDao;
	}

	public String allQueryNew() {

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		String currdate = formatter.format(new Date());
		this.year = currdate.substring(0, 4);
		int month = Integer.parseInt(currdate.substring(5, 7));
		this.quarter = String.valueOf(((month - 1) / 3) + 1);
		return "success";
	}

	// 按年度、季度查询汇总统计
	public String allQuery_Part() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String department = us.getUserDep();
		int quar;
		quar = Integer.parseInt(this.quarter);

		
		allList1 = new LinkedList<AllStaView1>();
		// allList=allStaViewDAO.findByYQ_part(this.year, quar,department);
		// 分两种情况，按季度或全年
		if("2".equals(choosedId)){
			allList = new LinkedList<AllStaView1>();
			if (5 == quar) {
				allList= allStaView1DAO.findByYear_part(this.year, department);
			} else {
				allList = allStaView1DAO.findByYQ_part(this.year, quar, department);
			}

			if (!allList.isEmpty()) {
				AllStaView1 asv1 = new AllStaView1();
				asv1.setDepName("总计");
				asv1.setReRiskId(allList.size()+"");
				allList.add(asv1);
				getInfo1(allList, quar);
			}

			return "success";
		}
		else{
		if (5 == quar) {
			allList = new LinkedList<AllStaView>();
			allList = allStaViewDAO.findByYear_part(this.year, department);
		} else {
			allList = allStaViewDAO.findByYQ_part(this.year, quar, department);
		}

		if (!allList.isEmpty()) {
			AllStaView asv = new AllStaView();
			asv.setDepName("总计");
			asv.setReRiskId(allList.size()+"");
			allList.add(asv);
			getInfo(allList, quar);
		}

		return "success";}
	}

	// 按年度、季度查询汇总统计
	public String allQuery() {
		int quar;

		quar = Integer.parseInt(this.quarter);

		
		allList1 = new LinkedList<AllStaView1>();
		// allList=allStaViewDAO.findByYearandQuar(this.year, quar);
		// 分两种情况，按季度或全年
		if("2".equals(choosedId)){
			allList = new LinkedList<AllStaView1>();
			if (5 == quar) {
				allList = allStaView1DAO.findByYear(this.year);
			} else {
				allList = allStaView1DAO.findByYearandQuar(this.year, quar);
			}

			if (!allList.isEmpty()) {
				AllStaView1 asv1 = new AllStaView1();
				asv1.setDepName("总计");
				asv1.setReRiskId(allList.size()+"");
				allList.add(asv1);
				getInfo1(allList, quar);
			}

			return "success";
		}
		else{
		if (5 == quar) {
			allList = new LinkedList<AllStaView>();
			allList = allStaViewDAO.findByYear(this.year);
		} else {
			allList = allStaViewDAO.findByYearandQuar(this.year, quar);
		}

		if (!allList.isEmpty()) {
			AllStaView asv = new AllStaView();
			asv.setDepName("总计");
			asv.setReRiskId(allList.size()+"");
			allList.add(asv);
			getInfo(allList, quar);
		}

		return "success";}
	}

	private void getInfo(List<AllStaView> allList, int quar) {

		// 将数据存放在数组中，生成excel时获取
		String[][] dsarray = new String[allList.size()-1][29];

		for (int m = 0; m < allList.size()-1; m++) {
			dsarray[m][0] = allList.get(m).getDepName() + "("
					+ allList.get(m).getReIndep() + ")";
			dsarray[m][1] = allList.get(m).getRtName() + "("
					+ allList.get(m).getReType() + ")";
			dsarray[m][2] = allList.get(m).getReRiskId();
			dsarray[m][3] = allList.get(m).getRiskName();

			dsarray[m][4] = allList.get(m).getId().getReId();// 风险事件编号
			dsarray[m][5] = allList.get(m).getRiKpi();
			dsarray[m][6] = allList.get(m).getRiProbability();
			dsarray[m][7] = allList.get(m).getRiBusarea();
			dsarray[m][8] = allList.get(m).getRiSource();

			this.efList = new LinkedList<EventFile>();
			this.efList = this.getEventFileDao().findByProperty("reId",dsarray[m][4]);
			
			int i = 1;
			String s = "";
			s = "涉及的管理规定及内控流程文件：\n";
			Iterator it = this.getEfList().iterator();
			while (it.hasNext()) {

				eventFile = (EventFile) it.next();
				if (null == eventFile.getFileId()) {
					break;
				}
				fileManage = this.getFileManageDao().findById(
						eventFile.getFileId());
				if (null == fileManage) {
					break;
				}
				s = s + i + "、文件编号：《" + fileManage.getFileId() + "》，文件名称：《"
						+ fileManage.getFileName() + "》；\n";
				i = i + 1;
			}
			List<EventFlowFile> efList2 = new LinkedList<EventFlowFile>();
			efList2 = this.getEventFlowFileDao().findByProperty("reId", dsarray[m][4]);
			Iterator it2 = efList2.iterator();
			while (it2.hasNext()) {

				EventFlowFile eventFlowFile = (EventFlowFile) it2.next();
				if (null == eventFlowFile.getFlowfileId()) {
					break;
				}
				fileManage = this.getFileManageDao().findById(eventFlowFile.getFlowfileId());
				if (null == fileManage) {
					break;
				}
				s = s + i + "、文件编号：《" + fileManage.getFileId() + "》，文件名称：《"
						+ fileManage.getFileName() + "》；\n";
				i = i + 1;
			}
			s = s + "共" + (this.getEfList().size() + efList2.size())+ "个文件。\n";
			dsarray[m][9] = s;
			dsarray[m][10] = allList.get(m).getRiInchargedep();

			dsarray[m][11] = allList.get(m).getRiFinance() + "；金额："
					+ String.valueOf(allList.get(m).getRiMoney());
			dsarray[m][12] = allList.get(m).getRiFame();
			dsarray[m][13] = allList.get(m).getRiLaw();
			dsarray[m][14] = allList.get(m).getRiClient();
			dsarray[m][15] = allList.get(m).getRiEmployee();
			dsarray[m][16] = allList.get(m).getRiOperation();
			dsarray[m][17] = allList.get(m).getRiSafe();

			dsarray[m][18] = allList.get(m).getRiFincriteria();
			dsarray[m][19] = allList.get(m).getRiFamecriteria();
			dsarray[m][20] = allList.get(m).getRiLawcriteria();
			dsarray[m][21] = allList.get(m).getRiClicriteria();
			dsarray[m][22] = allList.get(m).getRiEmpcriteria();
			dsarray[m][23] = allList.get(m).getRiOpecriteria();
			dsarray[m][24] = allList.get(m).getRiSafecriteria();

			dsarray[m][25] = allList.get(m).getRmChance();
			dsarray[m][26] = allList.get(m).getRmControl();
			dsarray[m][27] = "基本态度：" + allList.get(m).getRmStrategy()
					+ ";应对措施：" + allList.get(m).getRmReply() + ";计划投入资源："
					+ allList.get(m).getRmPlanres() + ";时间计划："
					+ allList.get(m).getRmPlandate();
			dsarray[m][28] = allList.get(m).getRmWarnvalue();

		}
		// 数据存放在session中，便于导出excel
		Map session = ServletActionContext.getContext().getSession();
		session.put("exportAllList", dsarray);
		// session.put("allcondition", this.year+"年第"+this.quarter+"季度");
		// 分两种情况，按季度或全年
		if (5 == quar) {
			session.put("allcondition", this.year + "年全年");
		} else {
			session.put("allcondition", this.year + "年第" + this.quarter + "季度");
		}

		this.year = this.year;
		this.quarter = this.quarter;
		// System.out.print("esList.size()="+esList.size());

	}

	private void getInfo1(List<AllStaView1> allList1, int quar) {

		// 将数据存放在数组中，生成excel时获取
		String[][] dsarray = new String[allList.size()-1][29];

		for (int m = 0; m < allList1.size()-1; m++) {
			dsarray[m][0] = allList1.get(m).getDepName() + "("
					+ allList1.get(m).getReIndep() + ")";
			dsarray[m][1] = allList1.get(m).getRtName() + "("
					+ allList1.get(m).getReType() + ")";
			dsarray[m][2] = allList1.get(m).getReRiskId();
			dsarray[m][3] = allList1.get(m).getRiskName();

			dsarray[m][4] = allList1.get(m).getId().getReId();// 风险事件编号
			dsarray[m][5] = allList1.get(m).getRiKpi();
			dsarray[m][6] = allList1.get(m).getRiProbability();
			dsarray[m][7] = allList1.get(m).getRiBusarea();
			dsarray[m][8] = allList1.get(m).getRiSource();

			this.efList = new LinkedList<EventFile>();
			this.efList = this.getEventFileDao().findByProperty("reId",dsarray[m][4]);
			
			int i = 1;
			String s = "";
			s = "涉及的管理规定及内控流程文件：\n";
			Iterator it = this.getEfList().iterator();
			while (it.hasNext()) {

				eventFile = (EventFile) it.next();
				if (null == eventFile.getFileId()) {
					break;
				}
				fileManage = this.getFileManageDao().findById(
						eventFile.getFileId());
				if (null == fileManage) {
					break;
				}
				s = s + i + "、文件编号：《" + fileManage.getFileId() + "》，文件名称：《"
						+ fileManage.getFileName() + "》；\n";
				i = i + 1;
			}
			List<EventFlowFile> efList2 = new LinkedList<EventFlowFile>();
			efList2 = this.getEventFlowFileDao().findByProperty("reId", dsarray[m][4]);
			Iterator it2 = efList2.iterator();
			while (it2.hasNext()) {

				EventFlowFile eventFlowFile = (EventFlowFile) it2.next();
				if (null == eventFlowFile.getFlowfileId()) {
					break;
				}
				fileManage = this.getFileManageDao().findById(eventFlowFile.getFlowfileId());
				if (null == fileManage) {
					break;
				}
				s = s + i + "、文件编号：《" + fileManage.getFileId() + "》，文件名称：《"
						+ fileManage.getFileName() + "》；\n";
				i = i + 1;
			}
			s = s + "共" + (this.getEfList().size() + efList2.size())+ "个文件。\n";
			dsarray[m][9] = s;
			dsarray[m][10] = allList1.get(m).getRiInchargedep();

			dsarray[m][11] = allList1.get(m).getRiFinance() + "；金额："
					+ String.valueOf(allList1.get(m).getRiMoney());
			dsarray[m][12] = allList1.get(m).getRiFame();
			dsarray[m][13] = allList1.get(m).getRiLaw();
			dsarray[m][14] = allList1.get(m).getRiClient();
			dsarray[m][15] = allList1.get(m).getRiEmployee();
			dsarray[m][16] = allList1.get(m).getRiOperation();
			dsarray[m][17] = allList1.get(m).getRiSafe();

			dsarray[m][18] = allList1.get(m).getRiFincriteria();
			dsarray[m][19] = allList1.get(m).getRiFamecriteria();
			dsarray[m][20] = allList1.get(m).getRiLawcriteria();
			dsarray[m][21] = allList1.get(m).getRiClicriteria();
			dsarray[m][22] = allList1.get(m).getRiEmpcriteria();
			dsarray[m][23] = allList1.get(m).getRiOpecriteria();
			dsarray[m][24] = allList1.get(m).getRiSafecriteria();

			dsarray[m][25] = allList1.get(m).getRmChance();
			dsarray[m][26] = allList1.get(m).getRmControl();
			dsarray[m][27] = "基本态度：" + allList1.get(m).getRmStrategy()
					+ ";应对措施：" + allList1.get(m).getRmReply() + ";计划投入资源："
					+ allList1.get(m).getRmPlanres() + ";时间计划："
					+ allList1.get(m).getRmPlandate();
			dsarray[m][28] = allList1.get(m).getRmWarnvalue();

		}
		// 数据存放在session中，便于导出excel
		Map session = ServletActionContext.getContext().getSession();
		session.put("exportAllList", dsarray);
		// session.put("allcondition", this.year+"年第"+this.quarter+"季度");
		// 分两种情况，按季度或全年
		if (5 == quar) {
			session.put("allcondition", this.year + "年全年");
		} else {
			session.put("allcondition", this.year + "年第" + this.quarter + "季度");
		}

		this.year = this.year;
		this.quarter = this.quarter;
		// System.out.print("esList.size()="+esList.size());

	}
	
	
	
	// 风险事件汇总统计结果报表导出
	public String exportExcel() {
		Map session = ServletActionContext.getContext().getSession();
		if (session.get("allcondition") == null
				|| session.get("exportAllList") == null)
			return "fail";
		else {
			String str = "汇总统计--" + session.get("allcondition");
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("汇总统计查询", "allStatisticTemplate",
					(String[][]) session.get("exportAllList"), 3, 4, str);// 3表示从第三行开始，4表示从第4列开始合并
			return "success";
		}
	}

	// 风险事件汇总统计结果点击每个事件的"涉及流程"项出现相应的环节文件
	@SuppressWarnings("unchecked")
	public String flowAQ() {
		fileList = new LinkedList<EventFile>();
		efileList = new LinkedList<FileManage>();
		String fileid;
		String filename;

		fileList = eventFileDao.findByProperty("reId", this.reid);
		for (int i = 0; i < fileList.size(); i++) {
			fileid = fileList.get(i).getFileId();
			if(fileid != null) {
				FileManage fm = fileManageDao.findById(fileid);
				if(fm != null) {
					efileList.add(fm);
				}
//				filename = fileManageDao.findById(fileid).getFileName();
//
//				//FileManage fm = new FileManage();
//				fm.setFileId(fileid);
//				fm.setFileName(filename);
//				efileList.add(fm);
			}
			
		}

		return "success";

	}

}
