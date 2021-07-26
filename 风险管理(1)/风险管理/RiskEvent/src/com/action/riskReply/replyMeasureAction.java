package com.action.riskReply;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.dao.RiskReplyViewDAO;
import com.entity.measureStatistic;
import com.model.Department;
import com.model.EventFileView;
import com.model.EventFlowFileView;
import com.model.FileManage;
import com.model.RiskReplyView;
import com.model.Users;

public class replyMeasureAction {
	private String reIndep;	// 录入部门编号
	private String reMadep="--请选择--"; //管理责任部门编号
	public String dateFrom;
	public String dateTo;
	private String choosedId;
	private List<RiskReplyView> ksList;
	private RiskReplyViewDAO riskReplyViewDAO;
	private List<measureStatistic> ksList2;	
	private List<FileManage> fmList;
	private List<Department> alldepList; // 录入部门
	private List<Department> depList;
	private DepartmentDAO departmentDao;
	
	public String getReIndep() {
		return reIndep;
	}
	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}
	
	public String getChoosedId() {
		return choosedId;
	}
	public void setChoosedId(String choosedId) {
		this.choosedId = choosedId;
	}
	public String getReMadep() {
		return reMadep;
	}
	public void setReMadep(String reMadep) {
		this.reMadep = reMadep;
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
	public List<RiskReplyView> getKsList() {
		return ksList;
	}
	public void setKsList(List<RiskReplyView> ksList) {
		this.ksList = ksList;
	}
	public RiskReplyViewDAO getRiskReplyViewDAO() {
		return riskReplyViewDAO;
	}
	public void setRiskReplyViewDAO(RiskReplyViewDAO riskReplyViewDAO) {
		this.riskReplyViewDAO = riskReplyViewDAO;
	}
	public List<measureStatistic> getKsList2() {
		return ksList2;
	}
	public void setKsList2(List<measureStatistic> ksList2) {
		this.ksList2 = ksList2;
	}
	public List<FileManage> getFmList() {
		return fmList;
	}
	public void setFmList(List<FileManage> fmList) {
		this.fmList = fmList;
	}
	public List<Department> getAlldepList() {
		return alldepList;
	}
	public void setAlldepList(List<Department> alldepList) {
		this.alldepList = alldepList;
	}
	public List<Department> getDepList() {
		return depList;
	}
	public void setDepList(List<Department> depList) {
		this.depList = depList;
	}
	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	//查询所有已发布的风险事件
	public String MeasureQuery(){
		Users us = getUser();
		int position = us.getUserPosition();  //得到用户的权限
		if(position<=5){
			this.setReIndep(departmentDao.getdepname(us.getUserDep()));
		}else{
			this.setReIndep("--请选择--");
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		Date date = new Date();		
		this.dateFrom=sf.format(date)+"-01-01";
		this.dateTo=sf.format(date)+"-12-31";
		Department dep1 = new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		alldepList = new LinkedList<Department>();
		alldepList.add(dep1);
		depList = new LinkedList<Department>();
		depList = this.getDepartmentDao().findAll();
		alldepList.addAll(depList);
		ksList=new LinkedList<RiskReplyView>();
		ksList2=new LinkedList<measureStatistic>();
		String condition = getCondition();
		//2015.5.11之前的代码
		/*if(position==1||position==3||position==5)
		{
			ksList=riskReplyViewDAO.findRisk135_new(us.getUserName(),condition);
		}else{
			ksList=riskReplyViewDAO.findRisk_measure(condition);
		}*/
		
		//2015.5.11之后的代码
		ksList=riskReplyViewDAO.findRisk_measure(condition);
		List<EventFileView> allEventFileView = new LinkedList<EventFileView>();
		List<EventFlowFileView> allFlowFileView = new LinkedList<EventFlowFileView>();
		allEventFileView = riskReplyViewDAO.getAllEventFile(condition);
		allFlowFileView = riskReplyViewDAO.getAllFlowFile(condition);
		if(ksList != null && ksList.size() > 0)
		{
			getInfo_new(ksList,allEventFileView,allFlowFileView);
		}
		return "success";
	}
	
	
	public String MeasureAdvancedQuery(){
		Users us = getUser();
		int position = us.getUserPosition();  //得到用户的权限
		Department dep1 = new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		alldepList = new LinkedList<Department>();
		alldepList.add(dep1);
		depList = new LinkedList<Department>();
		depList = this.getDepartmentDao().findAll();
		alldepList.addAll(depList);
		ksList=new LinkedList<RiskReplyView>();
		ksList2=new LinkedList<measureStatistic>();
		String condition = getCondition();
		
		//5.18号前的代码
		/*if(position==1||position==3||position==5)
		{
			ksList=riskReplyViewDAO.findRisk135_new(us.getUserName(),condition);
		}else{
			ksList=riskReplyViewDAO.findRisk_measure(condition);
		}*/
		
		//5.18号后的代码
		ksList=riskReplyViewDAO.findRisk_measure(condition);
		
		//System.out.println("江泉1234123"+choosedId);
		List<EventFileView> allEventFileView = new LinkedList<EventFileView>();
		List<EventFlowFileView> allFlowFileView = new LinkedList<EventFlowFileView>();
		allEventFileView = riskReplyViewDAO.getAllEventFile(condition);
		allFlowFileView = riskReplyViewDAO.getAllFlowFile(condition);
		if(ksList != null && ksList.size() > 0)
		{
			getInfo_new(ksList,allEventFileView,allFlowFileView);
		}
		return "success";
	}
	
	//导出风险应对措施列表
	public String measureExportExcel() {
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		if (session.get("measurecondition") == null
				|| session.get("exportMeasureList") == null)
			return "fail";
		else {
			String str = "风险应对措施列表统计--" + session.get("measurecondition");
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("风险应对措施列表统计查询", "replymeasureTemplate",
					(String[][]) session.get("exportMeasureList"), 4, 6, str);// 4表示从第四行开始，3表示从第三列开始合并，str是查询条件
			return "test";
		}
	}
	
	private String getCondition() {
		String queryString = "";
		String dateFrom1 = "";
		String dateTo1 = "";
		String datem="";
		String date1="";
		for(int i=0;i<4;i++){
			date1+=this.dateFrom.charAt(i);
		}

		if("2".equals(choosedId)){
			datem="model.remodifydate";
		}
		else if("3".equals(choosedId)){
			datem="model.takeTime";
		}
		else{
			datem="model.rmdate";
		}
		if("".equals(this.dateFrom)||this.dateFrom == null){
			dateFrom1="2000-01-01 00:00:00";
		}
		else if("3".equals(choosedId)){
			if("".equals(this.dateFrom)||this.dateFrom == null){
				dateFrom1="2017-01-01 00:00:00";
			}
			else if(Integer.parseInt(date1)<2017){
				dateFrom1="2017-01-01 00:00:00";
			}
			else{
				dateFrom1 = this.dateFrom+" 00:00:00";
			}
			
		}

		else{
			dateFrom1=this.dateFrom+" 00:00:00";
		}
		
		if("".equals(dateTo)||dateTo == null){
			dateTo1="2050-12-31 23:59:59";	
		}else{
			dateTo1 = this.dateTo+" 23:59:59";
		}
		queryString +=" " +datem+" between '"+dateFrom1+"' and '"+dateTo1 + "'";
		if("--请选择--".equals(this.reIndep)){
			queryString += "";
		}else{
			queryString += " and model.depName='"+this.reIndep+"'";
		}
		if("--请选择--".equals(this.reMadep)){
			queryString += "";
		}else{
			queryString += " and ((model.riskDep='本部门' and model.depName='"+this.reMadep+"') or model.riskDep='"+this.reMadep+"')";
		}
		return queryString;
	}
	
	String[] getFileName(String reId){ 
		String[] fileName={"","","",""};
		List<EventFileView> fmList11=new LinkedList<EventFileView>();
		fmList11=riskReplyViewDAO.findFile(reId);
		Iterator<EventFileView> it =fmList11.iterator();
		EventFileView model = new EventFileView();
		int[] a = {0,0,0,0,0};
		while(it.hasNext())
		{
			model =(EventFileView)it.next();
			String fileType = model.getFiletype().toString();
			if(fileType!=null&&fileType.length()>0){
				int filetype = Integer.valueOf(fileType);
				switch(filetype)
				{
				case 1:fileName[0] =fileName[0]+(++a[0])+"、"+model.getFilename().toString()+";";break;
				case 2:fileName[1]=fileName[1]+(++a[1])+"、"+model.getFilename().toString()+";";break;
				case 3:fileName[2]= fileName[2]+(++a[2])+"、"+model.getFilename().toString()+";";break;
				case 4:fileName[3]= fileName[3]+(++a[3])+"、"+model.getFilename().toString()+";";break;
				case 5:fileName[4]= fileName[4]+(++a[4])+"、"+model.getFilename().toString()+";";break;
				default:break;
				}
			}			
		}	
		return fileName;
	}	
	
	

	private String getInfo_new(List<RiskReplyView> ksList3,List<EventFileView> allEventFile,List<EventFlowFileView> allflowFile) {
		ksList2=new LinkedList<measureStatistic>();
		if(ksList3!=null && ksList3.size()>0)
		{
			int riskcount = 1;
			int riskeventcount = 0;
			int depcount = 1;
			int alleventcount = 0;
			List<EventFileView> eventFileList = new ArrayList<EventFileView>();	
			List<EventFlowFileView> eventFlowList = new ArrayList<EventFlowFileView>();	
			for(int m=0;m<ksList3.size();m++){
				eventFileList.clear();
				eventFlowList.clear();
				measureStatistic ksone1=new measureStatistic();
				ksone1.setM_riskNum(String.valueOf(riskcount));
				String riskId = ksList3.get(m).getRiskId();
				ksone1.setM_riskId(riskId);
				String riskName = ksList3.get(m).getRiskName();
				ksone1.setM_riskName(riskName);
				String rtName = ksList3.get(m).getRtName();
				ksone1.setM_rtName(rtName);
				String depName = ksList3.get(m).getDepName();
				ksone1.setM_depName(depName);//插入部门名称
				ksone1.setM_reId(ksList3.get(m).getReId());
				ksone1.setM_reEventname(ksList3.get(m).getReEventname());//插入风险事件名称
				ksone1.setM_reDetail(ksList3.get(m).getReDetail());
				ksone1.setM_rmStrategy(ksList3.get(m).getRmStrategy());
				ksone1.setM_rmReply(ksList3.get(m).getRmReply());
				ksone1.setM_rmPlandate(ksList3.get(m).getRmPlandate());
				ksone1.setM_rmPlanres(ksList3.get(m).getRmPlanres());
				//String[] FileName = getFileName(ksList3.get(m).getReId());
				for(int i = 0;i<allEventFile.size();i++){
					if(allEventFile.get(i).getReId().equals(ksList3.get(m).getReId())){
						eventFileList.add(allEventFile.get(i));
					}
				}
				for(int i = 0;i<allflowFile.size();i++){
					if(allflowFile.get(i).getReId().equals(ksList3.get(m).getReId())){
						eventFlowList.add(allflowFile.get(i));
					}
				}
				String[] FileName = getFileName(eventFileList);
				ksone1.setM_tsSystem(FileName[0]);
				ksone1.setM_manageSta(FileName[1]);
				ksone1.setM_workSta(FileName[2]);
				ksone1.setM_emergencyPlan(FileName[3]);
				//String flowFileName = riskReplyViewDAO.getFlowFileName(ksList3.get(m).getReId());
				String flowFileName = getFlowFileName(eventFlowList);
				ksone1.setM_icpIndex(flowFileName);
				ksone1.setM_reCreator(ksList3.get(m).getReCreator());
				if("本部门".equals(ksList3.get(m).getRiskDep())){
					ksone1.setM_riskDep(ksList3.get(m).getDepName());
				}else{
					ksone1.setM_riskDep(ksList3.get(m).getRiskDep());
				}
				ksList2.add(ksone1);
				riskeventcount++;
				if(m<ksList3.size()-1){
					if(!(ksList3.get(m).getRiskName().equals(ksList3.get(m+1).getRiskName()))){
						measureStatistic ksone2=new measureStatistic();
						ksone2.setM_riskNum(riskcount+" ");
						ksone2.setM_riskId(ksList3.get(m).getRiskId()+" ");
						ksone2.setM_riskName(ksList3.get(m).getRiskName()+" ");
						ksone2.setM_rtName(ksList3.get(m).getRtName()+" ");
						ksone2.setM_depName(depcount+"");//插入二级风险对应的部门个数
						ksone2.setM_reId("小计");
						ksone2.setM_reEventname(Integer.toString(riskeventcount));//插入二级风险对应的风险事件个数
						ksList2.add(ksone2);
						alleventcount+=riskeventcount;
						riskcount++;
						riskeventcount = 0;
						depcount = 1;
					}else if(!(ksList3.get(m).getDepName().equals(ksList3.get(m+1).getDepName()))){
						depcount++;
					}
				}
				if(m==ksList3.size()-1){
					measureStatistic ksone3=new measureStatistic();
					ksone3.setM_riskNum(riskcount+" ");
					ksone3.setM_riskId(ksList3.get(m).getRiskId()+" ");
					ksone3.setM_riskName(ksList3.get(m).getRiskName()+" ");
					ksone3.setM_rtName(ksList3.get(m).getRtName()+" ");
					ksone3.setM_depName(depcount+"");//插入二级风险对应的部门个数
					ksone3.setM_reId("小计");
					ksone3.setM_reEventname(Integer.toString(riskeventcount));//插入二级风险对应的风险事件个数
					ksList2.add(ksone3);
					alleventcount+=riskeventcount;
					riskeventcount = 0;
					depcount = 0;
				}
			}			
			measureStatistic ksone6=new measureStatistic();
			ksone6.setM_riskNum("总计");
			ksone6.setM_riskId(" ");
			ksone6.setM_riskName(riskcount+"");
			ksone6.setM_rtName("-");
			ksone6.setM_depName("-");//插入二级风险对应的部门个数
			ksone6.setM_reId("-");
			ksone6.setM_reEventname(Integer.toString(alleventcount));//插入二级风险对应的风险事件个数
			ksList2.add(ksone6);			
		
			// 数据存放在session中，便于导出excel
			String[][] dsarray = new String[ksList2.size()][19];
			for (int m = 0; m < ksList2.size(); m++) {
				dsarray[m][0] = ksList2.get(m).getM_riskNum();
				dsarray[m][1] = ksList2.get(m).getM_riskId();
				dsarray[m][2] = ksList2.get(m).getM_riskName();
				dsarray[m][3] = ksList2.get(m).getM_rtName();
				dsarray[m][4] = ksList2.get(m).getM_depName();
				dsarray[m][5] = ksList2.get(m).getM_reId();
				dsarray[m][6] = ksList2.get(m).getM_reEventname();
				dsarray[m][7] = ksList2.get(m).getM_reDetail();
				dsarray[m][8] = ksList2.get(m).getM_rmStrategy();
				dsarray[m][9] = ksList2.get(m).getM_rmReply();
				dsarray[m][10] = ksList2.get(m).getM_rmPlandate();
				dsarray[m][11] = ksList2.get(m).getM_rmPlanres();
				
				dsarray[m][12] = ksList2.get(m).getM_tsSystem();
				dsarray[m][13] = ksList2.get(m).getM_manageSta();
				dsarray[m][14] = ksList2.get(m).getM_workSta();
				dsarray[m][15] = ksList2.get(m).getM_emergencyPlan();
				dsarray[m][16] = ksList2.get(m).getM_icpIndex();
		
				dsarray[m][17] = ksList2.get(m).getM_reCreator();
				dsarray[m][18] = ksList2.get(m).getM_riskDep();
			}
			Map<String, Object> session = ServletActionContext.getContext().getSession();
			session.put("exportMeasureList", dsarray);
			session.put("measurecondition", this.dateFrom + "至"+this.dateTo);		
		}
		return "success";
	}

	//得到涉及流程文件
	private String getFlowFileName(List<EventFlowFileView> eventFlowList) {
		String name = "";
		if(eventFlowList!=null&&eventFlowList.size()>0){
			for(int j=0;j<eventFlowList.size();j++)
				name += (j+1)+"."+eventFlowList.get(j).getFlowFileName()+";";			
		}
		return name;
	}
	
	String[] getFileName(List<EventFileView> fmList11){ 
		String[] fileName={"","","","",""};
		Iterator<EventFileView> it =fmList11.iterator();
		EventFileView model = new EventFileView();
		int[] a = {0,0,0,0,0};
		while(it.hasNext())
		{
			model =(EventFileView)it.next();
			String fileType = model.getFiletype().toString();
			if(fileType!=null&&fileType.length()>0){
				int filetype = Integer.valueOf(fileType);
				switch(filetype)
				{
				case 1:fileName[0] =fileName[0]+(++a[0])+"、"+model.getFilename().toString()+";";break;
				case 2:fileName[1]=fileName[1]+(++a[1])+"、"+model.getFilename().toString()+";";break;
				case 3:fileName[2]= fileName[2]+(++a[2])+"、"+model.getFilename().toString()+";";break;
				case 4:fileName[3]= fileName[3]+(++a[3])+"、"+model.getFilename().toString()+";";break;
				case 5:fileName[4]= fileName[4]+(++a[4])+"、"+model.getFilename().toString()+";";break;
				default:break;
				}
			}			
		}	
		return fileName;
	}	
	
	//得到用户的信息us
	private Users getUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		return (Users) session.getAttribute("loginUser");
	}
}
