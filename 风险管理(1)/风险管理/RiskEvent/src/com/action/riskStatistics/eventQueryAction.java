package com.action.riskStatistics;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.action.ExcelReportAction;
import com.dao.DepartmentDAO;
import com.dao.EventStaView2DAO;
import com.dao.EventStaViewDAO;
import com.dao.RiskDAO;
import com.dao.RiskTypeDAO;
import com.entity.eventStatistic;
import com.model.Department;
import com.model.EventStaView;
import com.model.EventStaView2;
import com.model.Risk;
import com.model.RiskEvent;
import com.model.RiskType;
import com.model.Users;
import com.dao.EventStaView3DAO;
import com.dao.EventStaView4DAO;
import com.model.EventStaView3;
import com.model.EventStaView4;
public class eventQueryAction {
	private String year;
	private String quarter;
	private List<EventStaView2> esList;
	private List<EventStaView2> esList1;
	private List<RiskEvent> reList;
	private EventStaView2DAO eventStaView2DAO;
	private EventStaViewDAO eventStaViewDAO;
	private EventStaView3DAO eventStaView3DAO;
	private EventStaView4DAO eventStaView4DAO;
	private List<EventStaView4> esvList;
	private List<EventStaView4> esvList1;
	private RiskDAO riskDAO;
	private RiskTypeDAO riskTypeDAO;
	private List<Risk> rList;
	private List<RiskType> ryList;
	private List<eventStatistic> esList2;
	//统计图所用的List
	private List<eventStatistic> esList3;
	private List<Department> depList;
	private String rid;
	private String totalFlag = "";

	private DepartmentDAO departmentDao;
	private List<EventStaView> esListpart;
	private String barPath;
	private String choosedId;
	DecimalFormat dcmFmt = new DecimalFormat("0.00");

	public List<EventStaView4> getEsvList() {
		return esvList;
	}

	public void setEsvList(List<EventStaView4> esvList) {
		this.esvList = esvList;
	}

	public List<EventStaView4> getEsvList1() {
		return esvList1;
	}

	public void setEsvList1(List<EventStaView4> esvList1) {
		this.esvList1 = esvList1;
	}

	public List<EventStaView2> getEsList1() {
		return esList1;
	}

	public void setEsList1(List<EventStaView2> esList1) {
		this.esList1 = esList1;
	}

	public List<RiskEvent> getReList() {
		return reList;
	}

	public void setReList(List<RiskEvent> reList) {
		this.reList = reList;
	}

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

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public List<EventStaView2> getEsList() {
		return esList;
	}

	public void setEsList(List<EventStaView2> esList) {
		this.esList = esList;
	}

	

	public EventStaView3DAO getEventStaView3DAO() {
		return eventStaView3DAO;
	}

	public void setEventStaView3DAO(EventStaView3DAO eventStaView3DAO) {
		this.eventStaView3DAO = eventStaView3DAO;
	}

	public EventStaView4DAO getEventStaView4DAO() {
		return eventStaView4DAO;
	}

	public void setEventStaView4DAO(EventStaView4DAO eventStaView4DAO) {
		this.eventStaView4DAO = eventStaView4DAO;
	}

	public EventStaView2DAO getEventStaView2DAO() {
		return eventStaView2DAO;
	}

	public List<EventStaView> getEsListpart() {
		return esListpart;
	}

	public void setEsListpart(List<EventStaView> esListpart) {
		this.esListpart = esListpart;
	}

	public void setEventStaView2DAO(EventStaView2DAO eventStaView2DAO) {
		this.eventStaView2DAO = eventStaView2DAO;
	}

	public RiskDAO getRiskDAO() {
		return riskDAO;
	}

	public void setRiskDAO(RiskDAO riskDAO) {
		this.riskDAO = riskDAO;
	}

	public RiskTypeDAO getRiskTypeDAO() {
		return riskTypeDAO;
	}

	public void setRiskTypeDAO(RiskTypeDAO riskTypeDAO) {
		this.riskTypeDAO = riskTypeDAO;
	}

	public List<Risk> getrList() {
		return rList;
	}

	public void setrList(List<Risk> rList) {
		this.rList = rList;
	}

	public List<RiskType> getRyList() {
		return ryList;
	}

	public void setRyList(List<RiskType> ryList) {
		this.ryList = ryList;
	}

	public List<eventStatistic> getEsList2() {
		return esList2;
	}

	public void setEsList2(List<eventStatistic> esList2) {
		this.esList2 = esList2;
	}

	public List<eventStatistic> getEsList3() {
		return esList3;
	}

	public void setEsList3(List<eventStatistic> esList3) {
		this.esList3 = esList3;
	}

	public List<Department> getDepList() {
		return depList;
	}

	public void setDepList(List<Department> depList) {
		this.depList = depList;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public EventStaViewDAO getEventStaViewDAO() {
		return eventStaViewDAO;
	}

	public void setEventStaViewDAO(EventStaViewDAO eventStaViewDAO) {
		this.eventStaViewDAO = eventStaViewDAO;
	}

	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public String getTotalFlag() {
		return totalFlag;
	}

	public void setTotalFlag(String totalFlag) {
		this.totalFlag = totalFlag;
	}

	public String getBarPath() {
		return barPath;
	}

	public void setBarPath(String barPath) {
		this.barPath = barPath;
	}

	// 按年度、季度查询统计
	public String eventQuery() {

		int quar;
		this.barPath="";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String userId=us.getUserId();
		//清空上次此用户生成的图片文件
		File folder = new File(ServletActionContext.getServletContext().getRealPath("upload"));
		File[] files = folder.listFiles();
		for(File file:files){
			if(file.getName().contains(userId+"rtStatisticsBarchart")){
				file.delete();
			}
		}
		
		quar = Integer.parseInt(this.quarter);
		// DepStaViewId dep=new DepStaViewId();
		// dep.setYear(this.year);
		// dep.setQuarter(quar);
        //我改的-------------------------------------
		reList=new LinkedList<RiskEvent>();
		reList=eventStaView2DAO.find(choosedId, this.year);
		
		HashSet h = new HashSet(reList);      
		reList.clear();      
		reList.addAll(h); 
		esList = new LinkedList<EventStaView2>();
		esList1 = new LinkedList<EventStaView2>();
		
		if("2".equals(choosedId)){
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
			if (5 == quar) {
				esvList = eventStaView4DAO.findByYear(this.year);
			} else {
				
				esvList = eventStaView4DAO.findByYearandQuar(this.year, quar);
				System.out.println("pppppppppppppppppp");
				
			}
		

		esList2 = new LinkedList<eventStatistic>();
		esList3 = new LinkedList<eventStatistic>();

		ryList = riskTypeDAO.findAll();
		//System.out.println("kkkkkkkkkkkkkkkkkkkk"+esList.get(0).getClass().getName()); 
		if ((!ryList.isEmpty()) && (!esvList.isEmpty())) {
			getInfo1(ryList, esvList, quar);
			this.barPath=graphic.drawBarChart(esList3);
		}
		return "success";
		}
		else{
			if (5 == quar) {
				esList = eventStaView2DAO.findByYear(this.year);
			} else {
				
				esList = eventStaView2DAO.findByYearandQuar(this.year, quar);
			}
			esList2 = new LinkedList<eventStatistic>();
			esList3 = new LinkedList<eventStatistic>();

			ryList = riskTypeDAO.findAll();
			//System.out.println("kkkkkkkkkkkkkkkkkkkk"+esList.get(0).getClass().getName()); 
			if ((!ryList.isEmpty()) && (!esList.isEmpty())) {
				getInfo(ryList, esList, quar);
				this.barPath=graphic.drawBarChart(esList3);
			}
			return "success";
		}
		
	}

	// 按年度、季度查询统计_各部门和职能部门的查询页面
	public String eventQuery_Part() {
		int quar;
		this.barPath="";
		quar = Integer.parseInt(this.quarter);
		// DepStaViewId dep=new DepStaViewId();
		// dep.setYear(this.year);
		// dep.setQuarter(quar);

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String department = us.getUserDep();
		String userId=us.getUserId();
		//清空上次此用户生成的图片文件
		File folder = new File(ServletActionContext.getServletContext().getRealPath("upload"));
		File[] files = folder.listFiles();
		for(File file:files){
			if(file.getName().contains(userId+"rtStatisticsBarchart")){
				file.delete();
			}
		}

		esListpart = new LinkedList<EventStaView>();

		// esListpart=eventStaViewDAO.findDepNum("from EventStaView where id.year='"+this.year+"' and id.quarter="+quar+" and id.reIndep='"+department+"' ");

		// 分两种情况，按季度或全年
		if (5 == quar) {
			esListpart = eventStaViewDAO
					.findDepNum("from EventStaView where id.year='" + this.year
							+ "' and id.reIndep='" + department + "' ");
		} else {
			esListpart = eventStaViewDAO
					.findDepNum("from EventStaView where id.year='" + this.year
							+ "' and id.quarter=" + quar + " and id.reIndep='"
							+ department + "' ");
		}

		esList2 = new LinkedList<eventStatistic>();
		esList3 = new LinkedList<eventStatistic>();

		ryList = riskTypeDAO.findAll();
		if ((!ryList.isEmpty()) && (!esListpart.isEmpty())) {
			getInfopart(ryList, esListpart, quar);
			this.barPath=graphic.drawBarChart(esList3);
		}
		return "success";
	}

	private void getInfo(List<RiskType> ryList, List<EventStaView2> esList,
			int quar) {
		String rtname;
		String rtid;
		String rid;
		String rname;
		int allcount = 0;
		double allmoney = 0d;
		int ryeventcount = 0;
		double rymoney = 0f;

		int allcountcheck = 0;// 检验“小计”的和，是否=“总计”
		this.setTotalFlag("");

		// 先循环计算出所有的事件数和所有的金额，便于生成百分比

		for (int m = 0; m < esList.size(); m++) {
			allcount = esList.get(m).getId().getEventnum() + allcount;
			allmoney = esList.get(m).getId().getMoney() + allmoney;
		}
		//System.out.println("allcount" + allcount);
		//System.out.println("allmoney" + allmoney);

		for (int i = 0; i < ryList.size(); i++) {
			rtname = ryList.get(i).getRtName();
			rtid = ryList.get(i).getRtId();

			// rList=riskDAO.findByProperty("riskType", ty);
			rList = riskDAO.findbyInputType(ryList.get(i).getRtId());

			ryeventcount = 0;
			for (int j = 0; j < rList.size(); j++) {
				rid = rList.get(j).getRiskId();
				rname = rList.get(j).getRiskName();
				boolean flag = false;
				eventStatistic esone = new eventStatistic();
				esone.setRiskType(rtname);
				esone.setRiskId(rid);
				esone.setRiskName(rname);
				esone.setYear(this.year);
				esone.setQuarter(quar);
				esone.setIndepnum(0);
				esone.setEventnum(0);
				esone.setMoney(0d);
				esone.setEventHun(0f);
				esone.setMoneyHun(0f);
				for (int m = 0; m < esList.size(); m++) {
					// 如果风险类型和风险编号存在
					if (esList.get(m).getId().getRiskId().equals(rid)&& esList.get(m).getId().getRiskType().equals(rtid)) {
						ryeventcount = esList.get(m).getId().getEventnum()+ ryeventcount;
						rymoney = esList.get(m).getId().getMoney() + rymoney;
						esone.setIndepnum(esList.get(m).getId().getIndepnum() + esone.getIndepnum());
						esone.setEventnum(esList.get(m).getId().getEventnum() + esone.getEventnum());
						esone.setMoney(esList.get(m).getId().getMoney());
						
						flag = true;

						// 将数据存放在数组中，生成excel时获取
						// break;
					}
				}
				if(flag) {
					if (allcount == 0)
						esone.setEventHun(0f);
					else
						esone.setEventHun(Float.valueOf(dcmFmt.format(esone.getEventnum()* 100 / (float) allcount)));
					if (allmoney == 0)
						esone.setMoneyHun(0f);
					else
						esone.setMoneyHun(Float.valueOf(dcmFmt.format(esone.getMoney()* 100 / (float) allmoney)));
					esList2.add(esone);
				}
				
//				if (flag == false) {
//					eventStatistic esone = new eventStatistic();
//					esone.setRiskType(rtname);
//					esone.setRiskId(rid);
//					esone.setRiskName(rname);
//					esone.setYear(this.year);
//					esone.setQuarter(quar);
//					esone.setIndepnum(0);
//					esone.setEventnum(0);
//					esone.setMoney(0d);
//					esone.setEventHun(0f);
//					esone.setMoneyHun(0f);
//					esList2.add(esone);
//				}
			}
			eventStatistic esone = new eventStatistic();
			esone.setRiskType(rtname + " ");
			esone.setRiskId("");
			esone.setRiskName("小计");
			esone.setYear(this.year);
			esone.setQuarter(quar);
			// 部门数字不统计
			esone.setEventnum(ryeventcount);
			esone.setMoney(rymoney);
			if (allcount == 0)
				esone.setEventHun(0f);
			else
				esone.setEventHun(Float.valueOf(dcmFmt.format(ryeventcount
						* 100 / (float) allcount)));
			if (allmoney == 0)
				esone.setMoneyHun(0f);
			else
				esone.setMoneyHun(Float.valueOf(dcmFmt.format(rymoney * 100
						/ (float) allmoney)));

			esList2.add(esone);
			esList3.add(esone);
			

			allcountcheck += ryeventcount;
			ryeventcount = 0;// 事件数置0，下一组开始重新计数
			rymoney = 0;// 金额总数置0，下一组开始重新计数
		}

		// 加入总计
		eventStatistic esone = new eventStatistic();
		esone.setRiskType("总计");
		esone.setRiskId("");
		esone.setRiskName("总计");
		esone.setYear(this.year);
		esone.setQuarter(quar);
		// 部门数字不统计
		esone.setEventnum(allcount);
		esone.setMoney(allmoney);
		esone.setEventHun(100f);
		esone.setMoneyHun(100f);
		esList2.add(esone);

		//System.out.println("allcountcheck:" + allcountcheck + "\n");
		//System.out.println("allcount:" + allcount + "\n");

		// 检验“小计”的和，是否=“总计”
		if (allcountcheck == allcount)
			this.setTotalFlag("Y");
		else
			this.setTotalFlag("N");

		//System.out.println("TotalFlag:" + this.getTotalFlag() + "\n");

		// 数据存放在session中，便于导出excel
		String[][] dsarray = new String[esList2.size()][9];
		for (int m = 0; m < esList2.size(); m++) {
			dsarray[m][0] = esList2.get(m).getRiskType().trim();
			dsarray[m][1] = esList2.get(m).getRiskId();
			dsarray[m][2] = esList2.get(m).getRiskName();
			if (esList2.get(m).getIndepnum() != null)
				dsarray[m][3] = String.valueOf(esList2.get(m).getIndepnum());
			else
				dsarray[m][3] = "";

			// dsarray[m][4]=esList2.get(m).getYear()+"年第"+String.valueOf(esList2.get(m).getQuarter())+"季度";
			// 分两种情况，按季度或全年
			if (5 == quar) {
				dsarray[m][4] = esList2.get(m).getYear() + "年全年";
			} else {
				dsarray[m][4] = esList2.get(m).getYear() + "年第"
						+ String.valueOf(esList2.get(m).getQuarter()) + "季度";
			}

			dsarray[m][5] = String.valueOf(esList2.get(m).getEventnum());
			dsarray[m][6] = String.valueOf(esList2.get(m).getEventHun()) + "%";
			dsarray[m][7] = String.valueOf(esList2.get(m).getMoney());
			dsarray[m][8] = String.valueOf(esList2.get(m).getMoneyHun()) + "%";
		}
		Map session = ServletActionContext.getContext().getSession();
		session.put("exportEventList", dsarray);
		// session.put("eventcondition", this.year+"年第"+this.quarter+"季度");
		// 分两种情况，按季度或全年
		if (5 == quar) {
			session.put("eventcondition", this.year + "年全年");
		} else {
			session.put("eventcondition", this.year + "年第" + this.quarter
					+ "季度");
		}

		this.year = this.year;
		this.quarter = this.quarter;
		// System.out.print("esList.size()="+esList.size());

	}

	private void getInfopart(List<RiskType> ryList, List<EventStaView> esList,
			int quar) {

		String rtname;
		String rtid;
		String rid;
		String rname;
		int allcount = 0;
		double allmoney = 0d;
		int ryeventcount = 0;
		double rymoney = 0f;

		int allcountcheck = 0;// 检验“小计”的和，是否=“总计”
		this.setTotalFlag("");

		// esList2=new LinkedList<eventStatistic>();
		// 先循环计算出所有的事件数和所有的金额，便于生成百分比

		for (int m = 0; m < esList.size(); m++) {
			allcount = esList.get(m).getId().getNum() + allcount;
			allmoney = esList.get(m).getId().getMoney() + allmoney;
		}
		//System.out.println("allcount" + allcount);
		//System.out.println("allmoney" + allmoney);

		for (int i = 0; i < ryList.size(); i++) {
			rtname = ryList.get(i).getRtName();
			rtid = ryList.get(i).getRtId();

			// rList=riskDAO.findByProperty("riskType", ty);
			rList = riskDAO.findbyInputType(ryList.get(i).getRtId());

			ryeventcount = 0;
			for (int j = 0; j < rList.size(); j++) {
				rid = rList.get(j).getRiskId();
				rname = rList.get(j).getRiskName();
				boolean flag = false;
				for (int m = 0; m < esList.size(); m++) {
					// 如果风险类型和风险编号存在
					if (esList.get(m).getId().getRiskId().equals(rid)
							&& esList.get(m).getId().getRiskType().equals(rtid)) {
						ryeventcount = esList.get(m).getId().getNum()
								+ ryeventcount;
						rymoney = esList.get(m).getId().getMoney() + rymoney;
						eventStatistic esone = new eventStatistic();
						esone.setRiskType(rtname);
						esone.setRiskId(rid);
						esone.setRiskName(rname);
						esone.setYear(this.year);
						esone.setQuarter(quar);
						// esone.setIndepnum(esList.get(m).getId().getIndepnum());
						esone.setEventnum(esList.get(m).getId().getNum());
						esone.setMoney(esList.get(m).getId().getMoney());
						if (allcount == 0)
							esone.setEventHun(0f);
						else
							esone.setEventHun(Float.valueOf(dcmFmt
									.format(esList.get(m).getId().getNum()
											* 100 / (float) allcount)));
						if (allmoney == 0)
							esone.setMoneyHun(0f);
						else
							esone.setMoneyHun(Float.valueOf(dcmFmt
									.format(esList.get(m).getId().getMoney()
											* 100 / (float) allmoney)));
						esList2.add(esone);
						flag = true;

						// 将数据存放在数组中，生成excel时获取
						// break;
					}
				}
				if (flag == false) {
					eventStatistic esone = new eventStatistic();
					esone.setRiskType(rtname);
					esone.setRiskId(rid);
					esone.setRiskName(rname);
					esone.setYear(this.year);
					esone.setQuarter(quar);
					esone.setIndepnum(0);
					esone.setEventnum(0);
					esone.setMoney(0d);
					esone.setEventHun(0f);
					esone.setMoneyHun(0f);
					esList2.add(esone);
				}
			}
			eventStatistic esone = new eventStatistic();
			esone.setRiskType(rtname + " ");
			esone.setRiskId("");
			esone.setRiskName("小计");
			esone.setYear(this.year);
			esone.setQuarter(quar);
			// 部门数字不统计
			esone.setEventnum(ryeventcount);
			esone.setMoney(rymoney);
			if (allcount == 0)
				esone.setEventHun(0f);
			else
				esone.setEventHun(Float.valueOf(dcmFmt.format(ryeventcount
						* 100 / (float) allcount)));
			if (allmoney == 0)
				esone.setMoneyHun(0f);
			else
				esone.setMoneyHun(Float.valueOf(dcmFmt.format(rymoney * 100
						/ (float) allmoney)));

			esList2.add(esone);
			esList3.add(esone);

			allcountcheck += ryeventcount;
			ryeventcount = 0;// 事件数置0，下一组开始重新计数
			rymoney = 0;// 金额总数置0，下一组开始重新计数
		}

		// 加入总计
		eventStatistic esone = new eventStatistic();
		esone.setRiskType("总计");
		esone.setRiskId("");
		esone.setRiskName("总计");
		esone.setYear(this.year);
		esone.setQuarter(quar);
		// 部门数字不统计
		esone.setEventnum(allcount);
		esone.setMoney(allmoney);
		esone.setEventHun(100f);
		esone.setMoneyHun(100f);
		esList2.add(esone);

		// 检验“小计”的和，是否=“总计”
		if (allcountcheck == allcount)
			this.setTotalFlag("Y");
		else
			this.setTotalFlag("N");

		// 数据存放在session中，便于导出excel
		String[][] dsarray = new String[esList2.size()][9];
		for (int m = 0; m < esList2.size(); m++) {
			dsarray[m][0] = esList2.get(m).getRiskType().trim();
			dsarray[m][1] = esList2.get(m).getRiskId();
			dsarray[m][2] = esList2.get(m).getRiskName();
			if (esList2.get(m).getIndepnum() != null)
				dsarray[m][3] = String.valueOf(esList2.get(m).getIndepnum());
			else
				dsarray[m][3] = "";
			// dsarray[m][4]=esList2.get(m).getYear()+"年第"+String.valueOf(esList2.get(m).getQuarter())+"季度";

			// 分两种情况，按季度或全年
			if (5 == quar) {
				dsarray[m][4] = esList2.get(m).getYear() + "年全年";
			} else {
				dsarray[m][4] = esList2.get(m).getYear() + "年第"
						+ String.valueOf(esList2.get(m).getQuarter()) + "季度";
			}

			dsarray[m][5] = String.valueOf(esList2.get(m).getEventnum());
			dsarray[m][6] = String.valueOf(esList2.get(m).getEventHun()) + "%";
			dsarray[m][7] = String.valueOf(esList2.get(m).getMoney());
			dsarray[m][8] = String.valueOf(esList2.get(m).getMoneyHun()) + "%";
		}
		Map session = ServletActionContext.getContext().getSession();
		session.put("exportEventList", dsarray);
		// session.put("eventcondition", this.year+"年第"+this.quarter+"季度");

		// 分两种情况，按季度或全年
		if (5 == quar) {
			session.put("eventcondition", this.year + "年全年");
		} else {
			session.put("eventcondition", this.year + "年第" + this.quarter
					+ "季度");
		}

		this.year = this.year;
		this.quarter = this.quarter;
		// System.out.print("esList.size()="+esList.size());

	}

	// 按事件编号查询报表导出
	public String exportExcel() {
		Map session = ServletActionContext.getContext().getSession();
		// dsarray=(String[][])session.get("exportDepList");
		if (session.get("eventcondition") == null
				|| session.get("exportEventList") == null)
			return "fail";
		else {
			String str = "按事件编号统计--" + session.get("eventcondition");
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("按事件编号统计查询", "eventStatisticTemplate",
					(String[][]) session.get("exportEventList"), 3, 1, str);// 3表示从第三行开始，,1表示从第1列开始合并
			return "success";
		}
	}

	// 按事件编号查询后根据部门个数点击后部门名称
	public String depnumSQAction() {

		int quar2;
		quar2 = Integer.parseInt(this.quarter);

		try {
			depList = new LinkedList<Department>();
			// EventStaViewId rid=new EventStaViewId();
			//System.out.println("rid===========" + rid);
			// rid.setRiskId(tempriskid);

			// List<EventStaView> eventStaViewList=(List<EventStaView>)
			// eventStaViewDAO.findDepNum("from EventStaView where id.riskId='"+this.rid+"' and id.year='"+this.year+"' and id.quarter="+quar2+"");

			List<EventStaView> eventStaViewList = new LinkedList<EventStaView>();
			// 分两种情况，按季度或全年
			if (5 == quar2) {
				eventStaViewList = (List<EventStaView>) eventStaViewDAO
						.findDepNum("from EventStaView where id.riskId='"
								+ this.rid + "' and id.year='" + this.year
								+ "' ");
			} else {
				eventStaViewList = (List<EventStaView>) eventStaViewDAO
						.findDepNum("from EventStaView where id.riskId='"
								+ this.rid + "' and id.year='" + this.year
								+ "' and id.quarter=" + quar2 + "");
			}

			for (int i = 0; i < eventStaViewList.size(); i++) {
				//System.out.println(i);
				Department de = new Department();
				de = departmentDao.findById(eventStaViewList.get(i).getId()
						.getReIndep());
				depList.add(de);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	//========================================================================
		private void getInfo1(List<RiskType> ryList, List<EventStaView4> esList,
				int quar) {
			String rtname;
			String rtid;
			String rid;
			String rname;
			int allcount = 0;
			double allmoney = 0d;
			int ryeventcount = 0;
			double rymoney = 0f;

			int allcountcheck = 0;// 检验“小计”的和，是否=“总计”
			this.setTotalFlag("");

			// 先循环计算出所有的事件数和所有的金额，便于生成百分比

			for (int m = 0; m < esList.size(); m++) {
				allcount = esList.get(m).getId().getEventnum() + allcount;
				allmoney = esList.get(m).getId().getMoney() + allmoney;
			}
			//System.out.println("allcount" + allcount);
			//System.out.println("allmoney" + allmoney);

			for (int i = 0; i < ryList.size(); i++) {
				rtname = ryList.get(i).getRtName();
				rtid = ryList.get(i).getRtId();

				// rList=riskDAO.findByProperty("riskType", ty);
				rList = riskDAO.findbyInputType(ryList.get(i).getRtId());

				ryeventcount = 0;
				for (int j = 0; j < rList.size(); j++) {
					rid = rList.get(j).getRiskId();
					rname = rList.get(j).getRiskName();
					boolean flag = false;
					eventStatistic esone = new eventStatistic();
					esone.setRiskType(rtname);
					esone.setRiskId(rid);
					esone.setRiskName(rname);
					esone.setYear(this.year);
					esone.setQuarter(quar);
					esone.setIndepnum(0);
					esone.setEventnum(0);
					esone.setMoney(0d);
					esone.setEventHun(0f);
					esone.setMoneyHun(0f);
					for (int m = 0; m < esList.size(); m++) {
						// 如果风险类型和风险编号存在
						if (esList.get(m).getId().getRiskId().equals(rid)&& esList.get(m).getId().getRiskType().equals(rtid)) {
							ryeventcount = esList.get(m).getId().getEventnum()+ ryeventcount;
							rymoney = esList.get(m).getId().getMoney() + rymoney;
							esone.setIndepnum(esList.get(m).getId().getIndepnum() + esone.getIndepnum());
							esone.setEventnum(esList.get(m).getId().getEventnum() + esone.getEventnum());
							esone.setMoney(esList.get(m).getId().getMoney());
							
							flag = true;

							// 将数据存放在数组中，生成excel时获取
							// break;
						}
					}
					if(flag) {
						if (allcount == 0)
							esone.setEventHun(0f);
						else
							esone.setEventHun(Float.valueOf(dcmFmt.format(esone.getEventnum()* 100 / (float) allcount)));
						if (allmoney == 0)
							esone.setMoneyHun(0f);
						else
							esone.setMoneyHun(Float.valueOf(dcmFmt.format(esone.getMoney()* 100 / (float) allmoney)));
						esList2.add(esone);
					}
					
//					if (flag == false) {
//						eventStatistic esone = new eventStatistic();
//						esone.setRiskType(rtname);
//						esone.setRiskId(rid);
//						esone.setRiskName(rname);
//						esone.setYear(this.year);
//						esone.setQuarter(quar);
//						esone.setIndepnum(0);
//						esone.setEventnum(0);
//						esone.setMoney(0d);
//						esone.setEventHun(0f);
//						esone.setMoneyHun(0f);
//						esList2.add(esone);
//					}
				}
				eventStatistic esone = new eventStatistic();
				esone.setRiskType(rtname + " ");
				esone.setRiskId("");
				esone.setRiskName("小计");
				esone.setYear(this.year);
				esone.setQuarter(quar);
				// 部门数字不统计
				esone.setEventnum(ryeventcount);
				esone.setMoney(rymoney);
				if (allcount == 0)
					esone.setEventHun(0f);
				else
					esone.setEventHun(Float.valueOf(dcmFmt.format(ryeventcount
							* 100 / (float) allcount)));
				if (allmoney == 0)
					esone.setMoneyHun(0f);
				else
					esone.setMoneyHun(Float.valueOf(dcmFmt.format(rymoney * 100
							/ (float) allmoney)));

				esList2.add(esone);
				esList3.add(esone);
				

				allcountcheck += ryeventcount;
				ryeventcount = 0;// 事件数置0，下一组开始重新计数
				rymoney = 0;// 金额总数置0，下一组开始重新计数
			}

			// 加入总计
			eventStatistic esone = new eventStatistic();
			esone.setRiskType("总计");
			esone.setRiskId("");
			esone.setRiskName("总计");
			esone.setYear(this.year);
			esone.setQuarter(quar);
			// 部门数字不统计
			esone.setEventnum(allcount);
			esone.setMoney(allmoney);
			esone.setEventHun(100f);
			esone.setMoneyHun(100f);
			esList2.add(esone);

			//System.out.println("allcountcheck:" + allcountcheck + "\n");
			//System.out.println("allcount:" + allcount + "\n");

			// 检验“小计”的和，是否=“总计”
			if (allcountcheck == allcount)
				this.setTotalFlag("Y");
			else
				this.setTotalFlag("N");

			//System.out.println("TotalFlag:" + this.getTotalFlag() + "\n");

			// 数据存放在session中，便于导出excel
			String[][] dsarray = new String[esList2.size()][9];
			for (int m = 0; m < esList2.size(); m++) {
				dsarray[m][0] = esList2.get(m).getRiskType().trim();
				dsarray[m][1] = esList2.get(m).getRiskId();
				dsarray[m][2] = esList2.get(m).getRiskName();
				if (esList2.get(m).getIndepnum() != null)
					dsarray[m][3] = String.valueOf(esList2.get(m).getIndepnum());
				else
					dsarray[m][3] = "";

				// dsarray[m][4]=esList2.get(m).getYear()+"年第"+String.valueOf(esList2.get(m).getQuarter())+"季度";
				// 分两种情况，按季度或全年
				if (5 == quar) {
					dsarray[m][4] = esList2.get(m).getYear() + "年全年";
				} else {
					dsarray[m][4] = esList2.get(m).getYear() + "年第"
							+ String.valueOf(esList2.get(m).getQuarter()) + "季度";
				}

				dsarray[m][5] = String.valueOf(esList2.get(m).getEventnum());
				dsarray[m][6] = String.valueOf(esList2.get(m).getEventHun()) + "%";
				dsarray[m][7] = String.valueOf(esList2.get(m).getMoney());
				dsarray[m][8] = String.valueOf(esList2.get(m).getMoneyHun()) + "%";
			}
			Map session = ServletActionContext.getContext().getSession();
			session.put("exportEventList", dsarray);
			// session.put("eventcondition", this.year+"年第"+this.quarter+"季度");
			// 分两种情况，按季度或全年
			if (5 == quar) {
				session.put("eventcondition", this.year + "年全年");
			} else {
				session.put("eventcondition", this.year + "年第" + this.quarter
						+ "季度");
			}

			this.year = this.year;
			this.quarter = this.quarter;
			// System.out.print("esList.size()="+esList.size());

		}

}
