package com.action.riskStatistics;

import java.io.File;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.action.ExcelReportAction;
import com.dao.DepStaViewDAO;
import com.dao.DepartmentDAO;
import com.dao.EventStaView2DAO;
import com.dao.EventStaViewDAO;
import com.dao.LinkStaView2DAO;
import com.dao.LinkStaView3DAO;
import com.dao.LinkStaView4DAO;
import com.dao.LinkStaViewDAO;
import com.dao.RiskDAO;
import com.dao.RiskEventDAO;
import com.dao.RiskTypeDAO;
import com.entity.depStatistic;
import com.entity.eventStatistic;
import com.entity.linkStatistic;
import com.model.DepStaView;
import com.model.DepStaViewId;
import com.model.Department;
import com.model.EventStaView;
import com.model.EventStaView2;
import com.model.LinkStaView;
import com.model.LinkStaView2;
import com.model.LinkStaView3;
import com.model.LinkStaView4;
import com.model.Risk;
import com.model.RiskEvent;
import com.model.RiskType;
import com.model.Users;

public class linkQueryAction {
	private String year;
	private String quarter;
	private String totalFlag="";
	private List<LinkStaView2> linkList;
	private List<LinkStaView4> linkList1;
	private LinkStaView2DAO linkStaView2DAO;
	private LinkStaView3DAO linkStaView3DAO;
	private LinkStaView4DAO linkStaView4DAO;
	private LinkStaViewDAO linkStaViewDAO;
	private List<linkStatistic> linkList2;
	private List<linkStatistic> linkList3;
	private List<Department> depList;
	private String fileid;
	private String rid;
	private DepartmentDAO departmentDao;
	private List<LinkStaView> linkListpart;
	private List<LinkStaView3> linkListpart1;
	private String fileBarPath;
	private String choosedId;
	DecimalFormat dcmFmt = new DecimalFormat("0.00");

	
	public List<LinkStaView3> getLinkListpart1() {
		return linkListpart1;
	}

	public void setLinkListpart1(List<LinkStaView3> linkListpart1) {
		this.linkListpart1 = linkListpart1;
	}

	public String getChoosedId() {
		return choosedId;
	}

	public void setChoosedId(String choosedId) {
		this.choosedId = choosedId;
	}

	public List<LinkStaView4> getLinkList1() {
		return linkList1;
	}

	public void setLinkList1(List<LinkStaView4> linkList1) {
		this.linkList1 = linkList1;
	}

	public LinkStaView3DAO getLinkStaView3DAO() {
		return linkStaView3DAO;
	}

	public void setLinkStaView3DAO(LinkStaView3DAO linkStaView3DAO) {
		this.linkStaView3DAO = linkStaView3DAO;
	}

	public LinkStaView4DAO getLinkStaView4DAO() {
		return linkStaView4DAO;
	}

	public void setLinkStaView4DAO(LinkStaView4DAO linkStaView4DAO) {
		this.linkStaView4DAO = linkStaView4DAO;
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

	

	public LinkStaView2DAO getLinkStaView2DAO() {
		return linkStaView2DAO;
	}

	public void setLinkStaView2DAO(LinkStaView2DAO linkStaView2DAO) {
		this.linkStaView2DAO = linkStaView2DAO;
	}

	public LinkStaViewDAO getLinkStaViewDAO() {
		return linkStaViewDAO;
	}

	public void setLinkStaViewDAO(LinkStaViewDAO linkStaViewDAO) {
		this.linkStaViewDAO = linkStaViewDAO;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public List<LinkStaView2> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<LinkStaView2> linkList) {
		this.linkList = linkList;
	}

	public List<linkStatistic> getLinkList2() {
		return linkList2;
	}

	public void setLinkList2(List<linkStatistic> linkList2) {
		this.linkList2 = linkList2;
	}

	public List<linkStatistic> getLinkList3() {
		return linkList3;
	}

	public void setLinkList3(List<linkStatistic> linkList3) {
		this.linkList3 = linkList3;
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

	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}
	public List<LinkStaView> getLinkListpart() {
		return linkListpart;
	}

	public void setLinkListpart(List<LinkStaView> linkListpart) {
		this.linkListpart = linkListpart;
	}
	
	public String getTotalFlag() {
		return totalFlag;
	}

	public void setTotalFlag(String totalFlag) {
		this.totalFlag = totalFlag;
	}

	public String getFileBarPath() {
		return fileBarPath;
	}

	public void setFileBarPath(String fileBarPath) {
		this.fileBarPath = fileBarPath;
	}

	//??????????????????????????????_???????????????????????????????????????
	public String linkQuery_Part(){
		int quar;
		quar=Integer.parseInt(this.quarter);

		this.fileBarPath="";
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Users us = (Users)session.getAttribute("loginUser");
		String department=us.getUserDep();
		String userId=us.getUserId();
		//??????????????????????????????????????????
		File folder = new File(ServletActionContext.getServletContext().getRealPath("upload"));
		File[] files = folder.listFiles();
		for(File file:files){
			if(file.getName().contains(userId+"fileStatisticsBarchart")){
				file.delete();
			}
		}
	
		linkListpart=new LinkedList<LinkStaView>();
		linkListpart1=new LinkedList<LinkStaView3>();
		linkList2=new LinkedList<linkStatistic>();
		linkList3=new LinkedList<linkStatistic>();
	
		
		//linkListpart=linkStaViewDAO.findByYearandQuar_part(this.year, quar,department);	
		//????????????????????????????????????
		if("2".equals(choosedId)){
			if(5==quar)
			{
				linkListpart1=linkStaView3DAO.findByYear_part(this.year, department);
			}
			else
			{
				linkListpart1=linkStaView3DAO.findByYearandQuar_part(this.year, quar,department);
			}
			
			
			
			if(!linkListpart1.isEmpty())
			{
				getInfo_part1(linkListpart1,quar);
				this.fileBarPath=graphic.drawFileBarChart(linkList3);
			}
			
			return "success";
		}
		else{
		if(5==quar)
		{
			linkListpart=linkStaViewDAO.findByYear_part(this.year, department);
		}
		else
		{
			linkListpart=linkStaViewDAO.findByYearandQuar_part(this.year, quar,department);
		}
		
		
		
		if(!linkListpart.isEmpty())
		{
			getInfo_part(linkListpart,quar);
			this.fileBarPath=graphic.drawFileBarChart(linkList3);
		}
		
		return "success";}
	}
	
	//??????????????????????????????
	public String linkQuery(){
		
		int quar;	
		quar=Integer.parseInt(this.quarter);
		this.fileBarPath="";
		
		linkList=new LinkedList<LinkStaView2>();
		linkList1=new LinkedList<LinkStaView4>();
		linkList2=new LinkedList<linkStatistic>();
		linkList3=new LinkedList<linkStatistic>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String userId=us.getUserId();
		//??????????????????????????????????????????
		File folder = new File(ServletActionContext.getServletContext().getRealPath("upload"));
		File[] files = folder.listFiles();
		for(File file:files){
			if(file.getName().contains(userId+"fileStatisticsBarchart")){
				file.delete();
			}
		}
		//linkList=linkStaView2DAO.findByYearandQuar(this.year, quar);
		//????????????????????????????????????
		if("2".equals(choosedId)){
			System.out.println("ccccccccccccccccccc");
			if(5==quar)
			{
				linkList1=linkStaView4DAO.findByYear(this.year);
			}
			else
			{
				linkList1=linkStaView4DAO.findByYearandQuar(this.year, quar);
			}
			
			if(!linkList1.isEmpty())
			{
				getInfo1(linkList1,quar);
				this.fileBarPath=graphic.drawFileBarChart(linkList3);
			}
			
			return "success";
		}
		else{
		if(5==quar)
		{
			linkList=linkStaView2DAO.findByYear(this.year);
		}
		else
		{
			linkList=linkStaView2DAO.findByYearandQuar(this.year, quar);
		}
		
		if(!linkList.isEmpty())
		{
			getInfo(linkList,quar);
			this.fileBarPath=graphic.drawFileBarChart(linkList3);
		}
		
		return "success";}
	
	}

	private void getInfo(List<LinkStaView2> linkList,int quar) 	
		{	
		
		String linkid1;
		String linkid2;
		int allcount=0;
		double allmoney=0d;
		
		int linkeventcount=0;
		double linkmoney=0f;
		
		
		int allcountcheck=0;//?????????????????????????????????=????????????
		this.setTotalFlag("");
		
			
		//??????????????????????????????????????????????????????????????????????????????
		for(int m=0;m<linkList.size();m++)
		{
			allcount=linkList.get(m).getId().getEventnum()+allcount;
			allmoney=linkList.get(m).getId().getMoney()+allmoney;
		}
		//System.out.println("allcount"+allcount);
		//System.out.println("allmoney"+allmoney);
		
		int linkListnum=linkList.size();//linklist??????
			for(int i=0;i<linkListnum;i++)	
			{
				//?????????????????????list???
				linkStatistic esone=new linkStatistic();
				esone.setFileId(linkList.get(i).getId().getFileId());
				esone.setFileName(linkList.get(i).getId().getFileName().trim());
				esone.setRiskId(linkList.get(i).getId().getReRiskId());
				esone.setRiskName(linkList.get(i).getId().getRiskName());
				esone.setIndepnum(linkList.get(i).getId().getIndepnum());
				esone.setYear(this.year);
				esone.setQuarter(quar);
				int eventnumnow=linkList.get(i).getId().getEventnum();
				double moneynow=linkList.get(i).getId().getMoney();
				esone.setEventnum(eventnumnow);
				esone.setMoney(moneynow);
				if(allcount==0) esone.setEventHun(0f);
				else esone.setEventHun(Float.valueOf(dcmFmt.format(linkList.get(i).getId().getEventnum()*100/(float)allcount)));
				if(allmoney==0) esone.setMoneyHun(0f);
				else esone.setMoneyHun(Float.valueOf(dcmFmt.format(linkList.get(i).getId().getMoney()*100/(float)allmoney)));
				linkList2.add(esone);
				linkeventcount=linkeventcount+eventnumnow;
				linkmoney=linkmoney+moneynow;
				
				
				if(i<linkListnum-1)
				{
					linkid1=linkList.get(i).getId().getFileId();
					linkid2=linkList.get(i+1).getId().getFileId();
				
				
				if(!linkid1.equals(linkid2))//?????????????????????fileId?????????????????????fileId???????????????????????????
				{
					linkStatistic esone2=new linkStatistic();
					esone2.setFileId(linkid1+" ");
					esone2.setFileName(linkList.get(i).getId().getFileName().trim()+" ");
					esone2.setRiskId("??????");
					esone2.setRiskName("");
					esone2.setYear(this.year);
					esone2.setQuarter(quar);
					//?????????????????????
					esone2.setEventnum(linkeventcount);
					esone2.setMoney(linkmoney);
					if(allcount==0) esone2.setEventHun(0f);
					else esone2.setEventHun(Float.valueOf(dcmFmt.format(linkeventcount*100/(float)allcount)));
					if(allmoney==0) esone2.setMoneyHun(0f);
					else esone2.setMoneyHun(Float.valueOf(dcmFmt.format(linkmoney*100/(float)allmoney)));
					
					linkList2.add(esone2);
					linkList3.add(esone2);
					
					allcountcheck+=linkeventcount;
					linkeventcount=0;//????????????0??????????????????????????????
					linkmoney=0;//???????????????0??????????????????????????????
					
				}
				}
				if(i==linkListnum-1)//???????????????????????????????????????????????????
				{
					linkid1=linkList.get(i).getId().getFileId();
					linkStatistic esone2=new linkStatistic();
					esone2.setFileId(linkid1+" ");
					esone2.setFileName(linkList.get(i).getId().getFileName().trim()+" ");
					esone2.setRiskId("??????");
					esone2.setRiskName("");
					esone2.setYear(this.year);
					esone2.setQuarter(quar);
					//?????????????????????
					esone2.setEventnum(linkeventcount);
					esone2.setMoney(linkmoney);
					if(allcount==0) esone2.setEventHun(0f);
					else esone2.setEventHun(Float.valueOf(dcmFmt.format(linkeventcount*100/(float)allcount)));
					if(allmoney==0) esone2.setMoneyHun(0f);
					else esone2.setMoneyHun(Float.valueOf(dcmFmt.format(linkmoney*100/(float)allmoney)));
					
					linkList2.add(esone2);
					linkList3.add(esone2);
					
					
					allcountcheck+=linkeventcount;
					linkeventcount=0;//????????????0??????????????????????????????
					linkmoney=0;//???????????????0??????????????????????????????
				}
			}
			//????????????
			linkStatistic esone2=new linkStatistic();
			esone2.setFileId("?????? ");
			esone2.setFileName("");
			esone2.setRiskId("??????");
			esone2.setRiskName("");
			esone2.setYear(this.year);
			esone2.setQuarter(quar);
			//?????????????????????
			esone2.setEventnum(allcount);
			esone2.setMoney(allmoney);
			esone2.setEventHun(100f);
			esone2.setMoneyHun(100f);
			linkList2.add(esone2);
			
			
			//?????????????????????????????????=????????????
			if(allcountcheck==allcount) this.setTotalFlag("Y");
			else this.setTotalFlag("N");
			
			
		
		//???????????????session??????????????????excel
		String[][] dsarray=new String[linkList2.size()][10];
		for(int m=0;m<linkList2.size();m++)
		{
			dsarray[m][0]=linkList2.get(m).getFileId().trim();
			dsarray[m][1]=linkList2.get(m).getFileName().trim();
			dsarray[m][2]=linkList2.get(m).getRiskId();
			dsarray[m][3]=linkList2.get(m).getRiskName();
			
			if(linkList2.get(m).getIndepnum()!=null)
			dsarray[m][4]=String.valueOf(linkList2.get(m).getIndepnum());
			else
				dsarray[m][4]="";
			//dsarray[m][5]=linkList2.get(m).getYear()+"??????"+String.valueOf(linkList2.get(m).getQuarter())+"??????";
			//????????????????????????????????????
			if(5==quar)
			{
				dsarray[m][5]=linkList2.get(m).getYear()+"?????????";
			}
			else
			{
				dsarray[m][5]=linkList2.get(m).getYear()+"??????"+String.valueOf(linkList2.get(m).getQuarter())+"??????";
			}
			
			
			dsarray[m][6]=String.valueOf(linkList2.get(m).getEventnum());
			dsarray[m][7]=String.valueOf(linkList2.get(m).getEventHun())+"%";
			dsarray[m][8]=String.valueOf(linkList2.get(m).getMoney());
			dsarray[m][9]=String.valueOf(linkList2.get(m).getMoneyHun())+"%";
		}
		Map session2=ServletActionContext.getContext().getSession();	
		session2.put("exportLinkList", dsarray);
		//session.put("linkcondition", this.year+"??????"+this.quarter+"??????");
		//????????????????????????????????????
		if(5==quar)
		{
			session2.put("linkcondition", this.year+"?????????");
		}
		else
		{
			session2.put("linkcondition", this.year+"??????"+this.quarter+"??????");
		}
		
		
		
		this.year=this.year;
		this.quarter=this.quarter;
		//System.out.print("esList.size()="+esList.size());
	
		}
	private void getInfo1(List<LinkStaView4> linkList1,int quar) 	
	{	
	
	String linkid1;
	String linkid2;
	int allcount=0;
	double allmoney=0d;
	
	int linkeventcount=0;
	double linkmoney=0f;
	
	
	int allcountcheck=0;//?????????????????????????????????=????????????
	this.setTotalFlag("");
	
		
	//??????????????????????????????????????????????????????????????????????????????
	for(int m=0;m<linkList1.size();m++)
	{
		allcount=linkList1.get(m).getId().getEventnum()+allcount;
		allmoney=linkList1.get(m).getId().getMoney()+allmoney;
	}
	//System.out.println("allcount"+allcount);
	//System.out.println("allmoney"+allmoney);
	
	int linkListnum=linkList1.size();//linklist??????
		for(int i=0;i<linkListnum;i++)	
		{
			//?????????????????????list???
			linkStatistic esone=new linkStatistic();
			esone.setFileId(linkList1.get(i).getId().getFileId());
			esone.setFileName(linkList1.get(i).getId().getFileName().trim());
			esone.setRiskId(linkList1.get(i).getId().getReRiskId());
			esone.setRiskName(linkList1.get(i).getId().getRiskName());
			esone.setIndepnum(linkList1.get(i).getId().getIndepnum());
			esone.setYear(this.year);
			esone.setQuarter(quar);
			int eventnumnow=linkList1.get(i).getId().getEventnum();
			double moneynow=linkList1.get(i).getId().getMoney();
			esone.setEventnum(eventnumnow);
			esone.setMoney(moneynow);
			if(allcount==0) esone.setEventHun(0f);
			else esone.setEventHun(Float.valueOf(dcmFmt.format(linkList1.get(i).getId().getEventnum()*100/(float)allcount)));
			if(allmoney==0) esone.setMoneyHun(0f);
			else esone.setMoneyHun(Float.valueOf(dcmFmt.format(linkList1.get(i).getId().getMoney()*100/(float)allmoney)));
			linkList2.add(esone);
			linkeventcount=linkeventcount+eventnumnow;
			linkmoney=linkmoney+moneynow;
			
			
			if(i<linkListnum-1)
			{
				linkid1=linkList1.get(i).getId().getFileId();
				linkid2=linkList1.get(i+1).getId().getFileId();
			
			
			if(!linkid1.equals(linkid2))//?????????????????????fileId?????????????????????fileId???????????????????????????
			{
				linkStatistic esone2=new linkStatistic();
				esone2.setFileId(linkid1+" ");
				esone2.setFileName(linkList1.get(i).getId().getFileName().trim()+" ");
				esone2.setRiskId("??????");
				esone2.setRiskName("");
				esone2.setYear(this.year);
				esone2.setQuarter(quar);
				//?????????????????????
				esone2.setEventnum(linkeventcount);
				esone2.setMoney(linkmoney);
				if(allcount==0) esone2.setEventHun(0f);
				else esone2.setEventHun(Float.valueOf(dcmFmt.format(linkeventcount*100/(float)allcount)));
				if(allmoney==0) esone2.setMoneyHun(0f);
				else esone2.setMoneyHun(Float.valueOf(dcmFmt.format(linkmoney*100/(float)allmoney)));
				
				linkList2.add(esone2);
				linkList3.add(esone2);
				
				allcountcheck+=linkeventcount;
				linkeventcount=0;//????????????0??????????????????????????????
				linkmoney=0;//???????????????0??????????????????????????????
				
			}
			}
			if(i==linkListnum-1)//???????????????????????????????????????????????????
			{
				linkid1=linkList1.get(i).getId().getFileId();
				linkStatistic esone2=new linkStatistic();
				esone2.setFileId(linkid1+" ");
				esone2.setFileName(linkList1.get(i).getId().getFileName().trim()+" ");
				esone2.setRiskId("??????");
				esone2.setRiskName("");
				esone2.setYear(this.year);
				esone2.setQuarter(quar);
				//?????????????????????
				esone2.setEventnum(linkeventcount);
				esone2.setMoney(linkmoney);
				if(allcount==0) esone2.setEventHun(0f);
				else esone2.setEventHun(Float.valueOf(dcmFmt.format(linkeventcount*100/(float)allcount)));
				if(allmoney==0) esone2.setMoneyHun(0f);
				else esone2.setMoneyHun(Float.valueOf(dcmFmt.format(linkmoney*100/(float)allmoney)));
				
				linkList2.add(esone2);
				linkList3.add(esone2);
				
				
				allcountcheck+=linkeventcount;
				linkeventcount=0;//????????????0??????????????????????????????
				linkmoney=0;//???????????????0??????????????????????????????
			}
		}
		//????????????
		linkStatistic esone2=new linkStatistic();
		esone2.setFileId("?????? ");
		esone2.setFileName("");
		esone2.setRiskId("??????");
		esone2.setRiskName("");
		esone2.setYear(this.year);
		esone2.setQuarter(quar);
		//?????????????????????
		esone2.setEventnum(allcount);
		esone2.setMoney(allmoney);
		esone2.setEventHun(100f);
		esone2.setMoneyHun(100f);
		linkList2.add(esone2);
		
		
		//?????????????????????????????????=????????????
		if(allcountcheck==allcount) this.setTotalFlag("Y");
		else this.setTotalFlag("N");
		
		
	
	//???????????????session??????????????????excel
	String[][] dsarray=new String[linkList2.size()][10];
	for(int m=0;m<linkList2.size();m++)
	{
		dsarray[m][0]=linkList2.get(m).getFileId().trim();
		dsarray[m][1]=linkList2.get(m).getFileName().trim();
		dsarray[m][2]=linkList2.get(m).getRiskId();
		dsarray[m][3]=linkList2.get(m).getRiskName();
		
		if(linkList2.get(m).getIndepnum()!=null)
		dsarray[m][4]=String.valueOf(linkList2.get(m).getIndepnum());
		else
			dsarray[m][4]="";
		//dsarray[m][5]=linkList2.get(m).getYear()+"??????"+String.valueOf(linkList2.get(m).getQuarter())+"??????";
		//????????????????????????????????????
		if(5==quar)
		{
			dsarray[m][5]=linkList2.get(m).getYear()+"?????????";
		}
		else
		{
			dsarray[m][5]=linkList2.get(m).getYear()+"??????"+String.valueOf(linkList2.get(m).getQuarter())+"??????";
		}
		
		
		dsarray[m][6]=String.valueOf(linkList2.get(m).getEventnum());
		dsarray[m][7]=String.valueOf(linkList2.get(m).getEventHun())+"%";
		dsarray[m][8]=String.valueOf(linkList2.get(m).getMoney());
		dsarray[m][9]=String.valueOf(linkList2.get(m).getMoneyHun())+"%";
	}
	Map session2=ServletActionContext.getContext().getSession();	
	session2.put("exportLinkList", dsarray);
	//session.put("linkcondition", this.year+"??????"+this.quarter+"??????");
	//????????????????????????????????????
	if(5==quar)
	{
		session2.put("linkcondition", this.year+"?????????");
	}
	else
	{
		session2.put("linkcondition", this.year+"??????"+this.quarter+"??????");
	}
	
	
	
	this.year=this.year;
	this.quarter=this.quarter;
	//System.out.print("esList.size()="+esList.size());

	}
	private void getInfo_part(List<LinkStaView> linkList,int quar) 	
	{	
	linkList2=new LinkedList<linkStatistic>();
	
	String linkid1;
	String linkid2;
	int allcount=0;
	double allmoney=0d;
	
	int linkeventcount=0;
	double linkmoney=0f;
		
	
	int allcountcheck=0;//?????????????????????????????????=????????????
	this.setTotalFlag("");
	
	
	//??????????????????????????????????????????????????????????????????????????????
	for(int m=0;m<linkList.size();m++)
	{
		allcount=linkList.get(m).getId().getNum()+allcount;
		allmoney=linkList.get(m).getId().getMoney()+allmoney;
	}
	//System.out.println("allcount"+allcount);
	//System.out.println("allmoney"+allmoney);
	
	int linkListnum=linkList.size();//linklist??????
		for(int i=0;i<linkListnum;i++)	
		{
			//?????????????????????list???
			linkStatistic esone=new linkStatistic();
			esone.setFileId(linkList.get(i).getId().getFileId());
			esone.setFileName(linkList.get(i).getId().getFileName().trim());
			esone.setRiskId(linkList.get(i).getId().getReRiskId());
			esone.setRiskName(linkList.get(i).getId().getRiskName());
			//esone.setIndepnum(linkList.get(i).getId().());
			esone.setYear(this.year);
			esone.setQuarter(quar);
			int eventnumnow=linkList.get(i).getId().getNum();
			double moneynow=linkList.get(i).getId().getMoney();
			esone.setEventnum(eventnumnow);
			esone.setMoney(moneynow);
			if(allcount==0) esone.setEventHun(0f);
			else esone.setEventHun(Float.valueOf(dcmFmt.format(linkList.get(i).getId().getNum()*100/(float)allcount)));
			if(allmoney==0) esone.setMoneyHun(0f);
			else esone.setMoneyHun(Float.valueOf(dcmFmt.format(linkList.get(i).getId().getMoney()*100/(float)allmoney)));
			linkList2.add(esone);
			linkeventcount=linkeventcount+eventnumnow;
			linkmoney=linkmoney+moneynow;
			
			
			if(i<linkListnum-1)
			{
				linkid1=linkList.get(i).getId().getFileId();
			linkid2=linkList.get(i+1).getId().getFileId();
			
			
			if(!linkid1.equals(linkid2))//?????????????????????fileId?????????????????????fileId???????????????????????????
			{
				linkStatistic esone2=new linkStatistic();
				esone2.setFileId(linkid1+" ");
				esone2.setFileName(linkList.get(i).getId().getFileName().trim()+" ");
				esone2.setRiskId("??????");
				esone2.setRiskName("");
				esone2.setYear(this.year);
				esone2.setQuarter(quar);
				//?????????????????????
				esone2.setEventnum(linkeventcount);
				esone2.setMoney(linkmoney);
				if(allcount==0) esone2.setEventHun(0f);
				else esone2.setEventHun(Float.valueOf(dcmFmt.format(linkeventcount*100/(float)allcount)));
				if(allmoney==0) esone2.setMoneyHun(0f);
				else esone2.setMoneyHun(Float.valueOf(dcmFmt.format(linkmoney*100/(float)allmoney)));
				
				linkList2.add(esone2);
				linkList3.add(esone2);
				
				
				allcountcheck+=linkeventcount;
				linkeventcount=0;//????????????0??????????????????????????????
				linkmoney=0;//???????????????0??????????????????????????????
				
			}
			}
			if(i==linkListnum-1)//???????????????????????????????????????????????????
			{
				linkid1=linkList.get(i).getId().getFileId();
				linkStatistic esone2=new linkStatistic();
				esone2.setFileId(linkid1+" ");
				esone2.setFileName(linkList.get(i).getId().getFileName().trim()+" ");
				esone2.setRiskId("??????");
				esone2.setRiskName("");
				esone2.setYear(this.year);
				esone2.setQuarter(quar);
				//?????????????????????
				esone2.setEventnum(linkeventcount);
				esone2.setMoney(linkmoney);
				if(allcount==0) esone2.setEventHun(0f);
				else esone2.setEventHun(Float.valueOf(dcmFmt.format(linkeventcount*100/(float)allcount)));
				if(allmoney==0) esone2.setMoneyHun(0f);
				else esone2.setMoneyHun(Float.valueOf(dcmFmt.format(linkmoney*100/(float)allmoney)));
				
				linkList2.add(esone2);
				linkList3.add(esone2);
				
				allcountcheck+=linkeventcount;
				linkeventcount=0;//????????????0??????????????????????????????
				linkmoney=0;//???????????????0??????????????????????????????
			}
		}
		//????????????
		linkStatistic esone2=new linkStatistic();
		esone2.setFileId("?????? ");
		esone2.setFileName("");
		esone2.setRiskId("??????");
		esone2.setRiskName("");
		esone2.setYear(this.year);
		esone2.setQuarter(quar);
		//?????????????????????
		esone2.setEventnum(allcount);
		esone2.setMoney(allmoney);
		esone2.setEventHun(100f);
		esone2.setMoneyHun(100f);
		linkList2.add(esone2);
		
		
		//?????????????????????????????????=????????????
		if(allcountcheck==allcount) this.setTotalFlag("Y");
		else this.setTotalFlag("N");
	
	//???????????????session??????????????????excel
	String[][] dsarray=new String[linkList2.size()][10];
	for(int m=0;m<linkList2.size();m++)
	{
		dsarray[m][0]=linkList2.get(m).getFileId().trim();
		dsarray[m][1]=linkList2.get(m).getFileName().trim();
		dsarray[m][2]=linkList2.get(m).getRiskId();
		dsarray[m][3]=linkList2.get(m).getRiskName();
		
		if(linkList2.get(m).getIndepnum()!=null)
		dsarray[m][4]=String.valueOf(linkList2.get(m).getIndepnum());
		else
			dsarray[m][4]="";
		//dsarray[m][5]=linkList2.get(m).getYear()+"??????"+String.valueOf(linkList2.get(m).getQuarter())+"??????";
		//????????????????????????????????????
		if(5==quar)
		{
			dsarray[m][5]=linkList2.get(m).getYear()+"?????????";
		}
		else
		{
			dsarray[m][5]=linkList2.get(m).getYear()+"??????"+String.valueOf(linkList2.get(m).getQuarter())+"??????";
		}
		
		
		dsarray[m][6]=String.valueOf(linkList2.get(m).getEventnum());
		dsarray[m][7]=String.valueOf(linkList2.get(m).getEventHun())+"%";
		dsarray[m][8]=String.valueOf(linkList2.get(m).getMoney());
		dsarray[m][9]=String.valueOf(linkList2.get(m).getMoneyHun())+"%";
	}
	Map session2=ServletActionContext.getContext().getSession();	
	session2.put("exportLinkList", dsarray);
	//session.put("linkcondition", this.year+"??????"+this.quarter+"??????");
	//????????????????????????????????????
	if(5==quar)
	{
		session2.put("linkcondition", this.year+"?????????");
	}
	else
	{
		session2.put("linkcondition", this.year+"??????"+this.quarter+"??????");
	}
	
	
	this.year=this.year;
	this.quarter=this.quarter;
	//System.out.print("esList.size()="+esList.size());

	}

	private void getInfo_part1(List<LinkStaView3> linkList1,int quar) 	
	{	
	linkList2=new LinkedList<linkStatistic>();
	
	String linkid1;
	String linkid2;
	int allcount=0;
	double allmoney=0d;
	
	int linkeventcount=0;
	double linkmoney=0f;
		
	
	int allcountcheck=0;//?????????????????????????????????=????????????
	this.setTotalFlag("");
	
	
	//??????????????????????????????????????????????????????????????????????????????
	for(int m=0;m<linkList1.size();m++)
	{
		allcount=linkList1.get(m).getId().getNum()+allcount;
		allmoney=linkList1.get(m).getId().getMoney()+allmoney;
	}
	//System.out.println("allcount"+allcount);
	//System.out.println("allmoney"+allmoney);
	
	int linkListnum=linkList1.size();//linklist??????
		for(int i=0;i<linkListnum;i++)	
		{
			//?????????????????????list???
			linkStatistic esone=new linkStatistic();
			esone.setFileId(linkList1.get(i).getId().getFileId());
			esone.setFileName(linkList1.get(i).getId().getFileName().trim());
			esone.setRiskId(linkList1.get(i).getId().getReRiskId());
			esone.setRiskName(linkList1.get(i).getId().getRiskName());
			//esone.setIndepnum(linkList.get(i).getId().());
			esone.setYear(this.year);
			esone.setQuarter(quar);
			int eventnumnow=linkList1.get(i).getId().getNum();
			double moneynow=linkList1.get(i).getId().getMoney();
			esone.setEventnum(eventnumnow);
			esone.setMoney(moneynow);
			if(allcount==0) esone.setEventHun(0f);
			else esone.setEventHun(Float.valueOf(dcmFmt.format(linkList1.get(i).getId().getNum()*100/(float)allcount)));
			if(allmoney==0) esone.setMoneyHun(0f);
			else esone.setMoneyHun(Float.valueOf(dcmFmt.format(linkList1.get(i).getId().getMoney()*100/(float)allmoney)));
			linkList2.add(esone);
			linkeventcount=linkeventcount+eventnumnow;
			linkmoney=linkmoney+moneynow;
			
			
			if(i<linkListnum-1)
			{
				linkid1=linkList1.get(i).getId().getFileId();
			linkid2=linkList1.get(i+1).getId().getFileId();
			
			
			if(!linkid1.equals(linkid2))//?????????????????????fileId?????????????????????fileId???????????????????????????
			{
				linkStatistic esone2=new linkStatistic();
				esone2.setFileId(linkid1+" ");
				esone2.setFileName(linkList1.get(i).getId().getFileName().trim()+" ");
				esone2.setRiskId("??????");
				esone2.setRiskName("");
				esone2.setYear(this.year);
				esone2.setQuarter(quar);
				//?????????????????????
				esone2.setEventnum(linkeventcount);
				esone2.setMoney(linkmoney);
				if(allcount==0) esone2.setEventHun(0f);
				else esone2.setEventHun(Float.valueOf(dcmFmt.format(linkeventcount*100/(float)allcount)));
				if(allmoney==0) esone2.setMoneyHun(0f);
				else esone2.setMoneyHun(Float.valueOf(dcmFmt.format(linkmoney*100/(float)allmoney)));
				
				linkList2.add(esone2);
				linkList3.add(esone2);
				
				
				allcountcheck+=linkeventcount;
				linkeventcount=0;//????????????0??????????????????????????????
				linkmoney=0;//???????????????0??????????????????????????????
				
			}
			}
			if(i==linkListnum-1)//???????????????????????????????????????????????????
			{
				linkid1=linkList1.get(i).getId().getFileId();
				linkStatistic esone2=new linkStatistic();
				esone2.setFileId(linkid1+" ");
				esone2.setFileName(linkList1.get(i).getId().getFileName().trim()+" ");
				esone2.setRiskId("??????");
				esone2.setRiskName("");
				esone2.setYear(this.year);
				esone2.setQuarter(quar);
				//?????????????????????
				esone2.setEventnum(linkeventcount);
				esone2.setMoney(linkmoney);
				if(allcount==0) esone2.setEventHun(0f);
				else esone2.setEventHun(Float.valueOf(dcmFmt.format(linkeventcount*100/(float)allcount)));
				if(allmoney==0) esone2.setMoneyHun(0f);
				else esone2.setMoneyHun(Float.valueOf(dcmFmt.format(linkmoney*100/(float)allmoney)));
				
				linkList2.add(esone2);
				linkList3.add(esone2);
				
				allcountcheck+=linkeventcount;
				linkeventcount=0;//????????????0??????????????????????????????
				linkmoney=0;//???????????????0??????????????????????????????
			}
		}
		//????????????
		linkStatistic esone2=new linkStatistic();
		esone2.setFileId("?????? ");
		esone2.setFileName("");
		esone2.setRiskId("??????");
		esone2.setRiskName("");
		esone2.setYear(this.year);
		esone2.setQuarter(quar);
		//?????????????????????
		esone2.setEventnum(allcount);
		esone2.setMoney(allmoney);
		esone2.setEventHun(100f);
		esone2.setMoneyHun(100f);
		linkList2.add(esone2);
		
		
		//?????????????????????????????????=????????????
		if(allcountcheck==allcount) this.setTotalFlag("Y");
		else this.setTotalFlag("N");
	
	//???????????????session??????????????????excel
	String[][] dsarray=new String[linkList2.size()][10];
	for(int m=0;m<linkList2.size();m++)
	{
		dsarray[m][0]=linkList2.get(m).getFileId().trim();
		dsarray[m][1]=linkList2.get(m).getFileName().trim();
		dsarray[m][2]=linkList2.get(m).getRiskId();
		dsarray[m][3]=linkList2.get(m).getRiskName();
		
		if(linkList2.get(m).getIndepnum()!=null)
		dsarray[m][4]=String.valueOf(linkList2.get(m).getIndepnum());
		else
			dsarray[m][4]="";
		//dsarray[m][5]=linkList2.get(m).getYear()+"??????"+String.valueOf(linkList2.get(m).getQuarter())+"??????";
		//????????????????????????????????????
		if(5==quar)
		{
			dsarray[m][5]=linkList2.get(m).getYear()+"?????????";
		}
		else
		{
			dsarray[m][5]=linkList2.get(m).getYear()+"??????"+String.valueOf(linkList2.get(m).getQuarter())+"??????";
		}
		
		
		dsarray[m][6]=String.valueOf(linkList2.get(m).getEventnum());
		dsarray[m][7]=String.valueOf(linkList2.get(m).getEventHun())+"%";
		dsarray[m][8]=String.valueOf(linkList2.get(m).getMoney());
		dsarray[m][9]=String.valueOf(linkList2.get(m).getMoneyHun())+"%";
	}
	Map session2=ServletActionContext.getContext().getSession();	
	session2.put("exportLinkList", dsarray);
	//session.put("linkcondition", this.year+"??????"+this.quarter+"??????");
	//????????????????????????????????????
	if(5==quar)
	{
		session2.put("linkcondition", this.year+"?????????");
	}
	else
	{
		session2.put("linkcondition", this.year+"??????"+this.quarter+"??????");
	}
	
	
	this.year=this.year;
	this.quarter=this.quarter;
	//System.out.print("esList.size()="+esList.size());

	}

	//?????????????????????????????????
	public String exportExcel(){
	Map session=ServletActionContext.getContext().getSession();	
	if(session.get("linkcondition")==null||session.get("exportLinkList")==null) return "fail";
	else
	{
	String str="???????????????--"+session.get("linkcondition");
	ExcelReportAction ex=new ExcelReportAction();
	ex.ReportExcel("?????????????????????", "linkStatisticTemplate", (String[][])session.get("exportLinkList"),3,2, str);//3???????????????????????????2??????????????????????????????
	return "success";
	}
	}
	
	//???????????????????????????????????????????????????????????????
	public String depnumLQAction(){
		int quar2;
		quar2=Integer.parseInt(this.quarter);
		
		try{
			depList = new LinkedList<Department>();
		
			//System.out.println("fid==========="+fileid);
			//System.out.println("rid==========="+rid);
			
			
			//List<LinkStaView> linkStaViewList=(List<LinkStaView>) linkStaViewDAO.findDepNum("from LinkStaView where id.fileId='"+this.fileid+"' and id.reRiskId='"+this.rid+"' and id.year='"+this.year+"' and id.quarter="+quar2+"");
			
			List<LinkStaView> linkStaViewList =new LinkedList<LinkStaView>();
			//????????????????????????????????????
			if(5==quar2)
			{
				linkStaViewList=(List<LinkStaView>) linkStaViewDAO.findDepNum("from LinkStaView where id.fileId='"+this.fileid+"' and id.reRiskId='"+this.rid+"' and id.year='"+this.year+"' ");
			}
			else
			{
				linkStaViewList=(List<LinkStaView>) linkStaViewDAO.findDepNum("from LinkStaView where id.fileId='"+this.fileid+"' and id.reRiskId='"+this.rid+"' and id.year='"+this.year+"' and id.quarter="+quar2+"");
			}
			
			
			
			for(int i=0;i<linkStaViewList.size();i++)
			{
				Department de=new Department();
				de=departmentDao.findById(linkStaViewList.get(i).getId().getReIndep());
				depList.add(de);
			}
	      return "success";
			}
			catch(Exception e){
				e.printStackTrace();
				 return "fail";
			}
	}
	//???????????????????????????????????????????????????????????????
		public String depnumLQAction1(){
			int quar2;
			quar2=Integer.parseInt(this.quarter);
			
			try{
				depList = new LinkedList<Department>();
			
				//System.out.println("fid==========="+fileid);
				//System.out.println("rid==========="+rid);
				
				
				//List<LinkStaView> linkStaViewList=(List<LinkStaView>) linkStaViewDAO.findDepNum("from LinkStaView where id.fileId='"+this.fileid+"' and id.reRiskId='"+this.rid+"' and id.year='"+this.year+"' and id.quarter="+quar2+"");
				
				List<LinkStaView3> linkStaViewList1 =new LinkedList<LinkStaView3>();
				//????????????????????????????????????
				if(5==quar2)
				{
					linkStaViewList1=(List<LinkStaView3>) linkStaView3DAO.findDepNum("from LinkStaView3 where id.fileId='"+this.fileid+"' and id.reRiskId='"+this.rid+"' and id.year='"+this.year+"' ");
				}
				else
				{
					linkStaViewList1=(List<LinkStaView3>) linkStaView3DAO.findDepNum("from LinkStaView3 where id.fileId='"+this.fileid+"' and id.reRiskId='"+this.rid+"' and id.year='"+this.year+"' and id.quarter="+quar2+"");
				}
				
				
				
				for(int i=0;i<linkStaViewList1.size();i++)
				{
					Department de=new Department();
					de=departmentDao.findById(linkStaViewList1.get(i).getId().getReIndep());
					depList.add(de);
				}
		      return "success";
				}
				catch(Exception e){
					e.printStackTrace();
					 return "fail";
				}
		}
}
				
