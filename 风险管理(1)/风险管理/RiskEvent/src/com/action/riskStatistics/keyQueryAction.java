package com.action.riskStatistics;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.action.ExcelReportAction;
import com.dao.DepStaViewDAO;
import com.dao.DepartmentDAO;
import com.dao.KeyStaViewDAO;
import com.dao.RiskDAO;
import com.dao.RiskEventDAO;
import com.dao.RiskTypeDAO;
import com.entity.depStatistic;
import com.entity.keyStatistic;
import com.model.DepStaView;
import com.model.DepStaViewId;
import com.model.Department;
import com.model.EventStaView2;
import com.model.KeyStaView;
import com.model.Risk;
import com.model.RiskEvent;
import com.model.RiskType;
import com.model.Users;
public class keyQueryAction {
	private String year;
	private List<KeyStaView> ksList;
	private KeyStaViewDAO keyStaViewDAO;
	private DepartmentDAO departmentDAO;
	private List<keyStatistic> ksList2;	
	DecimalFormat dcmFmt = new DecimalFormat("0.00");
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<KeyStaView> getKsList() {
		return ksList;
	}
	public void setKsList(List<KeyStaView> ksList) {
		this.ksList = ksList;
	}
	public KeyStaViewDAO getKeyStaViewDAO() {
		return keyStaViewDAO;
	}
	public void setKeyStaViewDAO(KeyStaViewDAO keyStaViewDAO) {
		this.keyStaViewDAO = keyStaViewDAO;
	}
	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}
	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
	public List<keyStatistic> getKsList2() {
		return ksList2;
	}
	public void setKsList2(List<keyStatistic> ksList2) {
		this.ksList2 = ksList2;
	}	
	
	
	public String keyQueryNew(){
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currdate=formatter.format(new Date());  //???????????????  2014-09-02
		this.year=currdate.substring(0,4);            //????????????  2014
		return "success";
	}
	
	//?????????????????????_???????????????????????????????????????
	public String keyQuery_Part(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Users us = (Users)session.getAttribute("loginUser");
		String department=us.getUserDep();
		
		ksList=new LinkedList<KeyStaView>();
		ksList2=new LinkedList<keyStatistic>();
		List depListall=new LinkedList();
		//??????list???size=0
		depListall=keyStaViewDAO.findDep_part(this.year,department);
		if(!depListall.isEmpty())
		{
			getInfo(depListall);	
		}
		return "success";		
	}

	//?????????????????????
	public String keyQuery(){	
		ksList=new LinkedList<KeyStaView>();
		ksList2=new LinkedList<keyStatistic>();
		List depListall=new LinkedList();	
		//??????list??????????????????????????????dep
		
		//??????reIndep,depName,depQueue from KeyStaView as model where model.year ='"+year+"' order by model.depQueue
		depListall=keyStaViewDAO.findDep(this.year);   
		if(!depListall.isEmpty())
		{
			getInfo(depListall);		
		}
		return "success";
	}
	
	private void getInfo(List depListall) 	
	{
		String depname;
		String depid;
		String rtname;
		int allcount=0;	//?????????????????????????????????
		int allvalue=0;	//??????????????????
		
		int depcount=0;//????????????????????????????????????
		int depvalue=0;//??????????????????
		
		int riskeventcount=0;//??????Risk????????????????????????
		int riskvalue=0;//??????????????????
		
		
		if(depListall!=null)
		{
			//??????????????????????????????	
			for(Object depList : depListall)
			{ 
				depcount=0;//???????????????0
				depvalue=0;//????????????0
				
				Object[] dep = (Object[])depList; 
				depid=(String) dep[0];
				depname=(String) dep[1];
				//riskeventcount=0;
				//???????????????Risk
				List risklistall=keyStaViewDAO.findRisk(this.year,depid);
				//System.out.println(risklist.size());
				if (risklistall!=null)
				{
					int j=1;
					//??????????????????????????????	
					//?????????????????????Risk
					for(Object riskList : risklistall)
					{
						riskeventcount=0;//???????????????0
						riskvalue=0;//????????????0
						
						Object[] risk = (Object[])riskList; 
						
						//?????????Risk?????????????????????
						ksList=keyStaViewDAO.findDetail(this.year, depid, (String)risk[0]);
						if(ksList!=null)
						{
							//????????????Risk?????????????????????
							for(int m=0;m<ksList.size();m++)				
							{	
								keyStatistic ksone=new keyStatistic();
								ksone.setDepname(depname);
								ksone.setNumber(Integer.toString(j));//??????1???2
								ksone.setRiskName((String)risk[1]);//??????????????????
								ksone.setYear(this.year);
								ksone.setReId(ksList.get(m).getId().getReId());
								ksone.setDetail("???????????????"+ksList.get(m).getRiSource()+"??????????????????"+ksList.get(m).getRiKpi()+"??????????????????"+ksList.get(m).getRiBusarea());
					
								int prodegree=ksList.get(m).getRiProdegree();//???????????????
					
								int Findegree=ksList.get(m).getRiFindegree();
								int Famedegree=ksList.get(m).getRiFamedegree();
								int Lawdegree=ksList.get(m).getRiLawdegree();
								int Clidegree=ksList.get(m).getRiClidegree();
								int Empdegree=ksList.get(m).getRiEmpdegree();
								int Opedegree=ksList.get(m).getRiOpedegree();
								int Safedegree=ksList.get(m).getRiSafedegree();
					
								ksone.setRiProdegree(String.valueOf(prodegree));
					
								ksone.setRiFindegree(String.valueOf(Findegree));
								ksone.setRiFamedegree(String.valueOf(Famedegree));
								ksone.setRiLawdegree(String.valueOf(Lawdegree));
								ksone.setRiClidegree(String.valueOf(Clidegree));
								ksone.setRiEmpdegree(String.valueOf(Empdegree));
								ksone.setRiOpedegree(String.valueOf(Opedegree));
								ksone.setRiSafedegree(String.valueOf(Safedegree));
					
								ksone.setRiFinvalue(String.valueOf(Findegree*prodegree));
								ksone.setRiFamevalue(String.valueOf(Famedegree*prodegree));
								ksone.setRiLawvalue(String.valueOf(Lawdegree*prodegree));
								ksone.setRiClivalue(String.valueOf(Clidegree*prodegree));
								ksone.setRiEmpvalue(String.valueOf(Empdegree*prodegree));
								ksone.setRiOpevalue(String.valueOf(Opedegree*prodegree));
								ksone.setRiSafevalue(String.valueOf(Safedegree*prodegree));
					
								int riallvalue=Findegree*prodegree+Famedegree*prodegree+Lawdegree*prodegree+
								Clidegree*prodegree+Empdegree*prodegree+Opedegree*prodegree+Safedegree*prodegree;
					
								ksone.setRiAllvalue((float)riallvalue);
								ksList2.add(ksone);
								//depcount++;
								riskeventcount++;
								riskvalue+=riallvalue;
							}
						}
						//?????????risk,??????????????????????????????
						
						//?????????????????????Risk????????????Risk???????????????
					
						//????????????
						keyStatistic ksone=new keyStatistic();
						ksone.setDepname(depname);
						ksone.setNumber(Integer.toString(j++));//??????1???2?????????
						ksone.setRiskName((String)risk[1]);//??????????????????
						ksone.setYear("??????");
						ksone.setReId(String.valueOf(riskeventcount));
						if(riskeventcount==0)ksone.setRiAllvalue(0f);
						else ksone.setRiAllvalue(Float.valueOf(dcmFmt.format(riskvalue/(double)(riskeventcount))));
						ksList2.add(ksone);
					
						depcount+=riskeventcount;
						depvalue+=riskvalue;
						riskeventcount=0;//???????????????0
						riskvalue=0;//????????????0

					}
					
				}
				//??????????????????????????????
				
				
				
				//????????????
				keyStatistic ksone=new keyStatistic();
				ksone.setDepname(depname+" ");
				ksone.setNumber("??????");//??????1???2	
				ksone.setRiskName("");
				ksone.setYear(this.year);
				ksone.setReId(String.valueOf(depcount));		
				//????????????
				if(depcount==0)ksone.setRiAllvalue(0f);
				else ksone.setRiAllvalue(Float.valueOf(dcmFmt.format(depvalue/(double)(depcount))));
				ksList2.add(ksone);

				
				allcount+=depcount;
				allvalue+=depvalue;
				depcount=0;//???????????????0
				depvalue=0;//????????????0
				
				
				//??????????????????????????????
				
			}
			//???????????????
			
			//????????????
			keyStatistic ksone=new keyStatistic();
			ksone.setDepname("??????");
			ksone.setNumber("??????");//??????1???2	
			ksone.setRiskName("");
			ksone.setYear(this.year);
			ksone.setReId(String.valueOf(allcount));		
			//????????????
			if(allcount==0)ksone.setRiAllvalue(0f);
			else ksone.setRiAllvalue(Float.valueOf(dcmFmt.format(allvalue/(double)(allcount))));
			ksList2.add(ksone);
			
			
			allcount=0;//???????????????0
			allvalue=0;//????????????0
		}
		
		//???????????????session??????????????????excel
		String[][] dsarray=new String[ksList2.size()][22];
		for(int m=0;m<ksList2.size();m++)
		{
			dsarray[m][0]=ksList2.get(m).getDepname().trim();
			dsarray[m][1]=ksList2.get(m).getNumber();
			
			dsarray[m][2]=ksList2.get(m).getRiskName();
			dsarray[m][3]=ksList2.get(m).getYear();
			dsarray[m][4]=ksList2.get(m).getReId();
			dsarray[m][5]=ksList2.get(m).getDetail();
			dsarray[m][6]=ksList2.get(m).getRiProdegree();
			
			dsarray[m][7]=ksList2.get(m).getRiFindegree();
			dsarray[m][8]=ksList2.get(m).getRiFamedegree();
			dsarray[m][9]=ksList2.get(m).getRiLawdegree();			
			dsarray[m][10]=ksList2.get(m).getRiClidegree();
			dsarray[m][11]=ksList2.get(m).getRiEmpdegree();
			dsarray[m][12]=ksList2.get(m).getRiOpedegree();			
			dsarray[m][13]=ksList2.get(m).getRiSafedegree();
			
			dsarray[m][14]=ksList2.get(m).getRiFinvalue();
			dsarray[m][15]=ksList2.get(m).getRiFamevalue();
			dsarray[m][16]=ksList2.get(m).getRiLawvalue();
			dsarray[m][17]=ksList2.get(m).getRiClivalue();			
			dsarray[m][18]=ksList2.get(m).getRiEmpvalue();
			dsarray[m][19]=ksList2.get(m).getRiOpevalue();
			dsarray[m][20]=ksList2.get(m).getRiSafevalue();
			
			dsarray[m][21]=String.valueOf(ksList2.get(m).getRiAllvalue());
		}
		Map session=ServletActionContext.getContext().getSession();	
		session.put("exportKeyList", dsarray);
		session.put("keycondition", this.year+"???");
	
		//System.out.print("ksList.size()="+ksList.size());
		
		}
	


	//?????????????????????????????????
	public String exportExcel(){
	Map session=ServletActionContext.getContext().getSession();	
	//dsarray=(String[][])session.get("exportDepList");
	if(session.get("keycondition")==null||session.get("exportKeyList")==null) return "fail";
	else
	{
	String str="????????????????????????--"+session.get("keycondition");
	ExcelReportAction ex=new ExcelReportAction();
	ex.ReportExcel("??????????????????????????????", "keyStatisticTemplate", (String[][])session.get("exportKeyList"),4,3, str);//4???????????????????????????3?????????????????????????????????str???????????????
	return "success";
	}
	}
	}
				
