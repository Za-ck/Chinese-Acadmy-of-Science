package com.action.riskStatistics;


import java.io.File;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.action.ExcelReportAction;
import com.dao.DepStaView1DAO;
import com.dao.DepStaViewDAO;
import com.dao.DepartmentDAO;
import com.dao.RiskTypeDAO;
import com.entity.depStatistic;
import com.model.DepStaView;
import com.model.DepStaView1;
import com.model.Department;
import com.model.RiskType;
import com.model.Users;

public class depQueryAction {
	private String year;
	private String quarter;
	private String totalFlag="";
	private List<DepStaView> dsList;
	private List<DepStaView1> dsList1;
	private DepStaViewDAO depStaViewDAO;
	private DepStaView1DAO depStaView1DAO;
	private DepartmentDAO departmentDAO;
	private RiskTypeDAO riskTypeDAO;
	private List<Department> depList;
	private List<RiskType> ryList;
	private List<depStatistic> dsList2;
	private List<depStatistic> dsList3;
	private String depBarPath;
	private String choosedId;
	DecimalFormat dcmFmt = new DecimalFormat("0.00");

	//private List<String> dsArray;
	
	public String getYear() {
		return year;
	}

	public String getChoosedId() {
		return choosedId;
	}

	public void setChoosedId(String choosedId) {
		this.choosedId = choosedId;
	}

	public DepStaView1DAO getDepStaView1DAO() {
		return depStaView1DAO;
	}

	public void setDepStaView1DAO(DepStaView1DAO depStaView1DAO) {
		this.depStaView1DAO = depStaView1DAO;
	}

	public List<DepStaView1> getDsList1() {
		return dsList1;
	}

	public void setDsList1(List<DepStaView1> dsList1) {
		this.dsList1 = dsList1;
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

	public List<DepStaView> getDsList() {
		return dsList;
	}

	public void setDsList(List<DepStaView> dsList) {
		this.dsList = dsList;
	}

	public DepStaViewDAO getDepStaViewDAO() {
		return depStaViewDAO;
	}

	public void setDepStaViewDAO(DepStaViewDAO depStaViewDAO) {
		this.depStaViewDAO = depStaViewDAO;
	}	
	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public RiskTypeDAO getRiskTypeDAO() {
		return riskTypeDAO;
	}

	public void setRiskTypeDAO(RiskTypeDAO riskTypeDAO) {
		this.riskTypeDAO = riskTypeDAO;
	}

	public List<Department> getDepList() {
		return depList;
	}

	public void setDepList(List<Department> depList) {
		this.depList = depList;
	}

	public List<RiskType> getRyList() {
		return ryList;
	}

	public List<depStatistic> getDsList2() {
		return dsList2;
	}

	public void setDsList2(List<depStatistic> dsList2) {
		this.dsList2 = dsList2;
	}

	public void setRyList(List<RiskType> ryList) {
		this.ryList = ryList;
	}
	

	public String getTotalFlag() {
		return totalFlag;
	}

	public void setTotalFlag(String totalFlag) {
		this.totalFlag = totalFlag;
	}

	public List<depStatistic> getDsList3() {
		return dsList3;
	}

	public void setDsList3(List<depStatistic> dsList3) {
		this.dsList3 = dsList3;
	}

	public String getDepBarPath() {
		return depBarPath;
	}

	public void setDepBarPath(String depBarPath) {
		this.depBarPath = depBarPath;
	}

	//按年度、季度查询统计
	public String depQuery()
	{
		
		int quar;
		String depname;
		String depid;
		String rtname;
		int allcount=0;
		float allmoney=0f;
		int depeventcount=0;
		float depmoney=0f;
		
		int allcountcheck=0;//检验“小计”的和，是否=“总计”
		this.setTotalFlag("");
		this.depBarPath="";
		
		quar=Integer.parseInt(this.quarter);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String userId=us.getUserId();
		//清空上次此用户生成的图片文件
		File folder = new File(ServletActionContext.getServletContext().getRealPath("upload"));
		File[] files = folder.listFiles();
		for(File file:files){
			if(file.getName().contains(userId+"depStatisticsBarchart")){
				file.delete();
			}
		}
		//DepStaViewId dep=new DepStaViewId();
		//dep.setYear(this.year);
		//dep.setQuarter(quar);
		dsList3=new LinkedList<depStatistic>();
		dsList2=new LinkedList<depStatistic>();
		dsList=new LinkedList<DepStaView>();
		dsList1=new LinkedList<DepStaView1>();
		//dsList=depStaViewDAO.findByYearandQuar(this.year, quar);
		
		//分两种情况，按季度或全年
		if("2".equals(choosedId)){
			System.out.println("bbbbbbbbbbbbbbbbbbbbbb");
		if(5==quar)
		{
			dsList1=depStaView1DAO.findByYear(this.year);
		}
		else
		{
			dsList1=depStaView1DAO.findByYearandQuar(this.year, quar);
		}
		
		//System.out.println("dsList.size()="+dsList.size());
		//System.out.println("dsList.get(1).getId().getReIndep()"+dsList.get(1).getId().getReIndep());
		/*
		 * 风险统计—部门统计：去掉  FB（发布）;YZBGH（院长办公会）。
		 * 因为这两个部门是现实不存在的，只是为了实现功能加上去的。
		 * 管理员操作注意，不要给他们风险事件录入的权限
		 */
		//depList=departmentDAO.findAll();
		depList=departmentDAO.findDepQuery();
		ryList=riskTypeDAO.findAll();
		
		//先循环计算出所有的事件数和所有的金额，便于生成百分比
		for(int m=0;m<dsList1.size();m++)
		{
			allcount=dsList1.get(m).getId().getNum()+allcount;
			allmoney=dsList1.get(m).getId().getMoney()+allmoney;
		}
		//System.out.println("allcount"+allcount);
		//System.out.println("allmoney"+allmoney);
		
		for(int i=0;i<depList.size();i++)
		{
			depname=depList.get(i).getDepName();
			depid=depList.get(i).getDepId();
			depeventcount=0;
			for(int j=0;j<ryList.size();j++)
			{
				rtname=ryList.get(j).getRtName();
				boolean flag=false;
				for(int m=0;m<dsList1.size();m++)
				{	
					if(dsList1.get(m).getId().getReIndep().equals(depid)&&dsList1.get(m).getId().getRtName().equals(rtname))
					{
						depeventcount=dsList1.get(m).getId().getNum()+depeventcount;
						depmoney=dsList1.get(m).getId().getMoney()+depmoney;
					depStatistic dsone=new depStatistic();
					dsone.setReIndep(depname);
					dsone.setRtName(rtname);
					dsone.setYear(this.year);
					dsone.setQuarter(quar);
					dsone.setNum(dsList1.get(m).getId().getNum());
					dsone.setMoney(dsList1.get(m).getId().getMoney());
					if(allcount==0) dsone.setEventHun(0f);
					else dsone.setEventHun(Float.valueOf(dcmFmt.format(dsList1.get(m).getId().getNum()*100/(float)allcount)));
					if(allmoney==0) dsone.setMoneyHun(0f);
					else dsone.setMoneyHun(Float.valueOf(dcmFmt.format(dsList1.get(m).getId().getMoney()*100/allmoney)));
					dsList2.add(dsone);
					flag=true;
					
					//将数据存放在数组中，生成excel时获取
					//break;
				   }	
				}
				if(flag==false)
					{
					depStatistic dsone=new depStatistic();
					dsone.setReIndep(depname);
					dsone.setRtName(rtname);
					dsone.setYear(this.year);
					dsone.setQuarter(quar);
					dsone.setNum(0);
					dsone.setMoney(0f);
					dsone.setEventHun(0f);
					dsone.setMoneyHun(0f);
					dsList2.add(dsone);
					}
				}
			depStatistic dsone=new depStatistic();
			dsone.setReIndep(depname+" ");
			dsone.setRtName("小计");
			dsone.setYear(this.year);
			dsone.setQuarter(quar);
			dsone.setNum(depeventcount);
			dsone.setMoney(depmoney);
			if(allcount==0) dsone.setEventHun(0f);
			else dsone.setEventHun(Float.valueOf(dcmFmt.format(depeventcount*100/(float)allcount)));
			if(allmoney==0) dsone.setMoneyHun(0f);
			else dsone.setMoneyHun(Float.valueOf(dcmFmt.format(depmoney*100/allmoney)));
			
			dsList2.add(dsone);
			dsList3.add(dsone);
			
			
			allcountcheck+=depeventcount;
			depeventcount=0;//事件数置0，下一组开始重新计数
			depmoney=0;//金额总数置0，下一组开始重新计数
			
			}
		depStatistic dsone=new depStatistic();
		dsone.setReIndep("总计");
		dsone.setRtName("总计");
		dsone.setYear(this.year);
		dsone.setQuarter(quar);
		dsone.setNum(allcount);
		dsone.setMoney(allmoney);
	    dsone.setEventHun(100f);
		dsone.setMoneyHun(100f);
		dsList2.add(dsone);
		
		
		//检验“小计”的和，是否=“总计”
		if(allcountcheck==allcount) this.setTotalFlag("Y");
		else this.setTotalFlag("N");
		
		this.depBarPath=graphic.drawDepBarChart(800, 500, dsList3);
		//数据存放在session中，便于导出excel
		String[][] dsarray=new String[dsList2.size()][7];
		for(int m=0;m<dsList2.size();m++)
		{
			dsarray[m][0]=dsList2.get(m).getReIndep().replace(" ", "");
			dsarray[m][1]=dsList2.get(m).getRtName();
			//dsarray[m][2]=dsList2.get(m).getYear()+"年第"+String.valueOf(dsList2.get(m).getQuarter())+"季度";
			
			//分两种情况，按季度或全年
			if(5==quar)
			{
				dsarray[m][2]=dsList2.get(m).getYear()+"年全年";
			}
			else
			{
				dsarray[m][2]=dsList2.get(m).getYear()+"年第"+String.valueOf(dsList2.get(m).getQuarter())+"季度";
			}
			dsarray[m][3]=String.valueOf(dsList2.get(m).getNum());
			dsarray[m][4]=String.valueOf(dsList2.get(m).getEventHun())+"%";
			dsarray[m][5]=String.valueOf(dsList2.get(m).getMoney());
			dsarray[m][6]=String.valueOf(dsList2.get(m).getMoneyHun())+"%";
		}
		Map session2=ServletActionContext.getContext().getSession();	
		session2.put("exportDepList", dsarray);
		//session.put("depcondition", this.year+"年第"+this.quarter+"季度");
		//分两种情况，按季度或全年
		if(5==quar)
		{
			session2.put("depcondition", this.year+"年全年");
		}
		else
		{
			session2.put("depcondition", this.year+"年第"+this.quarter+"季度");
		}
		
		
		this.year=this.year;
		this.quarter=this.quarter;
		//System.out.print("dsList.size()="+dsList.size());
		
		return "success";}
		else{
			if(5==quar)
			{
				dsList=depStaViewDAO.findByYear(this.year);
			}
			else
			{
				dsList=depStaViewDAO.findByYearandQuar(this.year, quar);
			}
			
			//System.out.println("dsList.size()="+dsList.size());
			//System.out.println("dsList.get(1).getId().getReIndep()"+dsList.get(1).getId().getReIndep());
			/*
			 * 风险统计—部门统计：去掉  FB（发布）;YZBGH（院长办公会）。
			 * 因为这两个部门是现实不存在的，只是为了实现功能加上去的。
			 * 管理员操作注意，不要给他们风险事件录入的权限
			 */
			//depList=departmentDAO.findAll();
			depList=departmentDAO.findDepQuery();
			ryList=riskTypeDAO.findAll();
			
			//先循环计算出所有的事件数和所有的金额，便于生成百分比
			for(int m=0;m<dsList.size();m++)
			{
				allcount=dsList.get(m).getId().getNum()+allcount;
				allmoney=dsList.get(m).getId().getMoney()+allmoney;
			}
			//System.out.println("allcount"+allcount);
			//System.out.println("allmoney"+allmoney);
			
			for(int i=0;i<depList.size();i++)
			{
				depname=depList.get(i).getDepName();
				depid=depList.get(i).getDepId();
				depeventcount=0;
				for(int j=0;j<ryList.size();j++)
				{
					rtname=ryList.get(j).getRtName();
					boolean flag=false;
					for(int m=0;m<dsList.size();m++)
					{	
						if(dsList.get(m).getId().getReIndep().equals(depid)&&dsList.get(m).getId().getRtName().equals(rtname))
						{
							depeventcount=dsList.get(m).getId().getNum()+depeventcount;
							depmoney=dsList.get(m).getId().getMoney()+depmoney;
						depStatistic dsone=new depStatistic();
						dsone.setReIndep(depname);
						dsone.setRtName(rtname);
						dsone.setYear(this.year);
						dsone.setQuarter(quar);
						dsone.setNum(dsList.get(m).getId().getNum());
						dsone.setMoney(dsList.get(m).getId().getMoney());
						if(allcount==0) dsone.setEventHun(0f);
						else dsone.setEventHun(Float.valueOf(dcmFmt.format(dsList.get(m).getId().getNum()*100/(float)allcount)));
						if(allmoney==0) dsone.setMoneyHun(0f);
						else dsone.setMoneyHun(Float.valueOf(dcmFmt.format(dsList.get(m).getId().getMoney()*100/allmoney)));
						dsList2.add(dsone);
						flag=true;
						
						//将数据存放在数组中，生成excel时获取
						//break;
					   }	
					}
					if(flag==false)
						{
						depStatistic dsone=new depStatistic();
						dsone.setReIndep(depname);
						dsone.setRtName(rtname);
						dsone.setYear(this.year);
						dsone.setQuarter(quar);
						dsone.setNum(0);
						dsone.setMoney(0f);
						dsone.setEventHun(0f);
						dsone.setMoneyHun(0f);
						dsList2.add(dsone);
						}
					}
				depStatistic dsone=new depStatistic();
				dsone.setReIndep(depname+" ");
				dsone.setRtName("小计");
				dsone.setYear(this.year);
				dsone.setQuarter(quar);
				dsone.setNum(depeventcount);
				dsone.setMoney(depmoney);
				if(allcount==0) dsone.setEventHun(0f);
				else dsone.setEventHun(Float.valueOf(dcmFmt.format(depeventcount*100/(float)allcount)));
				if(allmoney==0) dsone.setMoneyHun(0f);
				else dsone.setMoneyHun(Float.valueOf(dcmFmt.format(depmoney*100/allmoney)));
				
				dsList2.add(dsone);
				dsList3.add(dsone);
				
				
				allcountcheck+=depeventcount;
				depeventcount=0;//事件数置0，下一组开始重新计数
				depmoney=0;//金额总数置0，下一组开始重新计数
				
				}
			depStatistic dsone=new depStatistic();
			dsone.setReIndep("总计");
			dsone.setRtName("总计");
			dsone.setYear(this.year);
			dsone.setQuarter(quar);
			dsone.setNum(allcount);
			dsone.setMoney(allmoney);
		    dsone.setEventHun(100f);
			dsone.setMoneyHun(100f);
			dsList2.add(dsone);
			
			
			//检验“小计”的和，是否=“总计”
			if(allcountcheck==allcount) this.setTotalFlag("Y");
			else this.setTotalFlag("N");
			
			this.depBarPath=graphic.drawDepBarChart(800, 500, dsList3);
			//数据存放在session中，便于导出excel
			String[][] dsarray=new String[dsList2.size()][7];
			for(int m=0;m<dsList2.size();m++)
			{
				dsarray[m][0]=dsList2.get(m).getReIndep().replace(" ", "");
				dsarray[m][1]=dsList2.get(m).getRtName();
				//dsarray[m][2]=dsList2.get(m).getYear()+"年第"+String.valueOf(dsList2.get(m).getQuarter())+"季度";
				
				//分两种情况，按季度或全年
				if(5==quar)
				{
					dsarray[m][2]=dsList2.get(m).getYear()+"年全年";
				}
				else
				{
					dsarray[m][2]=dsList2.get(m).getYear()+"年第"+String.valueOf(dsList2.get(m).getQuarter())+"季度";
				}
				dsarray[m][3]=String.valueOf(dsList2.get(m).getNum());
				dsarray[m][4]=String.valueOf(dsList2.get(m).getEventHun())+"%";
				dsarray[m][5]=String.valueOf(dsList2.get(m).getMoney());
				dsarray[m][6]=String.valueOf(dsList2.get(m).getMoneyHun())+"%";
			}
			Map session2=ServletActionContext.getContext().getSession();	
			session2.put("exportDepList", dsarray);
			//session.put("depcondition", this.year+"年第"+this.quarter+"季度");
			//分两种情况，按季度或全年
			if(5==quar)
			{
				session2.put("depcondition", this.year+"年全年");
			}
			else
			{
				session2.put("depcondition", this.year+"年第"+this.quarter+"季度");
			}
			
			
			this.year=this.year;
			this.quarter=this.quarter;
			//System.out.print("dsList.size()="+dsList.size());
			
			return "success";
		}
		}
	
	//按部门名称查询报表导出
	public String exportExcel(){
	Map session=ServletActionContext.getContext().getSession();	
	//dsarray=(String[][])session.get("exportDepList");
	if(session.get("depcondition")==null||session.get("exportDepList")==null) return "fail";
	else
	{
	String str="按部门统计--"+session.get("depcondition");
	ExcelReportAction ex=new ExcelReportAction();
	ex.ReportExcel("按部门统计查询", "depStatisticTemplate", (String[][])session.get("exportDepList"),3,1, str);//3表示从第三行开始2,1表示从第1列开始合并
	return "success";
	}
	}
	}
				
