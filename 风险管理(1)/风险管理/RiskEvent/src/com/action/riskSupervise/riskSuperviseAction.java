package com.action.riskSupervise;

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
import com.entity.riskSupervise;
import com.model.Department;
import com.model.EventFileView;
import com.model.EventFlowFileView;
import com.model.RiskReplyView;
import com.model.Users;

public class riskSuperviseAction {
	private String reIndep;	// 录入部门编号
	public String dateFrom;
	public String dateTo;
	public String choosedId;
	public String replyDateFrom;
	public String replyDateTo;
	public String replyState;
	public String superviseState;
	private List<String> allReplyList;
	private List<String> allSuperviseList;
	private List<RiskReplyView> ksList;
	private RiskReplyViewDAO riskReplyViewDAO;
	private List<riskSupervise> ksList2;	
	private List<Department> alldepList; // 录入部门
	private List<Department> depList;
	private DepartmentDAO departmentDao;
	private String reId;
	
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

	public String getReplyDateFrom() {
		return replyDateFrom;
	}

	public void setReplyDateFrom(String replyDateFrom) {
		this.replyDateFrom = replyDateFrom;
	}

	public String getReplyDateTo() {
		return replyDateTo;
	}

	public void setReplyDateTo(String replyDateTo) {
		this.replyDateTo = replyDateTo;
	}

	public String getReplyState() {
		return replyState;
	}

	public void setReplyState(String replyState) {
		this.replyState = replyState;
	}
	
	public List<String> getAllReplyList() {
		return allReplyList;
	}

	public void setAllReplyList(List<String> allReplyList) {
		this.allReplyList = allReplyList;
	}

	public List<String> getAllSuperviseList() {
		return allSuperviseList;
	}

	public void setAllSuperviseList(List<String> allSuperviseList) {
		this.allSuperviseList = allSuperviseList;
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

	public List<riskSupervise> getKsList2() {
		return ksList2;
	}

	public void setKsList2(List<riskSupervise> ksList2) {
		this.ksList2 = ksList2;
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
	
	public String getReId() {
		return reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
	}

	//查询所有关键风险事件
	public String SuperviseQuery(){	
		this.replyState = "已应对";
		this.superviseState = "未监控";
		this.choosedId="--请选择--";
		allReplyList = new LinkedList<String>();
		allReplyList.add("--请选择--");
		allReplyList.add("已应对");
		allReplyList.add("未应对");
		allSuperviseList = new LinkedList<String>();
		allSuperviseList.add("--请选择--");
		allSuperviseList.add("已监控");
		allSuperviseList.add("未监控");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		Date date = new Date();		
		this.dateFrom=sf.format(date)+"-01-01";
		this.dateTo=sf.format(date)+"-12-31";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		int position = us.getUserPosition();
		String dep = departmentDao.getdepname(us.getUserDep());
		if(position<=5&&!("监察审计部".equals(dep))){
			this.setReIndep(dep);
		}else{
			this.setReIndep("--请选择--");
		}
		//得到所有二级风险 的List
		getRiskList();
		return "success";
	}
	
	public String getRiskList(){
		Department dep1 = new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		System.out.println("choosed+fsgdgh   "+choosedId);
		alldepList = new LinkedList<Department>();
		alldepList.add(dep1);
		depList = new LinkedList<Department>();
		depList = this.getDepartmentDao().findAll();
		alldepList.addAll(depList);
		ksList=new LinkedList<RiskReplyView>();
		ksList2=new LinkedList<riskSupervise>();
		String condition = getCondition();
		ksList=riskReplyViewDAO.findSupRisk(condition);
		List<EventFileView> allEventFileView = new LinkedList<EventFileView>();
		List<EventFlowFileView> allFlowFileView = new LinkedList<EventFlowFileView>();
		allEventFileView = riskReplyViewDAO.getAllEventFile(condition);
		allFlowFileView = riskReplyViewDAO.getAllFlowFile(condition);
		if(ksList != null && ksList.size() > 0)
		{
			getInfo_supervise(ksList,allEventFileView,allFlowFileView);
		}
		return "success";
	}
	
	

	private String getInfo_supervise(List<RiskReplyView> ksList3, List<EventFileView> allEventFile, List<EventFlowFileView> allFlowFile) {
		ksList2=new LinkedList<riskSupervise>();
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
				riskSupervise ksone1=new riskSupervise();
				ksone1.setR_riskNum(String.valueOf(riskcount));
				ksone1.setR_riskId(ksList.get(m).getRiskId());
				ksone1.setR_riskName(ksList.get(m).getRiskName());
				ksone1.setR_rtName(ksList.get(m).getRtName());
				ksone1.setR_depName(ksList.get(m).getDepName());//插入部门名称
				ksone1.setR_reEventname(ksList.get(m).getReEventname());//插入风险事件名称
				ksone1.setR_reDetail(ksList.get(m).getReDetail());
				ksone1.setR_rmStrategy(ksList.get(m).getRmStrategy());
				ksone1.setR_rmReply(ksList.get(m).getRmReply());
				ksone1.setR_rmPlandate(ksList.get(m).getRmPlandate());
				ksone1.setR_rmPlanres(ksList.get(m).getRmPlanres());
				for(int i = 0;i<allEventFile.size();i++){
					if(allEventFile.get(i).getReId().equals(ksList3.get(m).getReId())){
						eventFileList.add(allEventFile.get(i));
					}
				}
				for(int i = 0;i<allFlowFile.size();i++){
					if(allFlowFile.get(i).getReId().equals(ksList3.get(m).getReId())){
						eventFlowList.add(allFlowFile.get(i));
					}
				}
				String[] FileName = getFileName(eventFileList);
				ksone1.setR_tsSystem(FileName[0]);
				ksone1.setR_manageSta(FileName[1]);
				ksone1.setR_workSta(FileName[2]);
				ksone1.setR_emergencyPlan(FileName[3]);
				//String flowFileName = riskReplyViewDAO.getFlowFileName(ksList3.get(m).getReId());
				String flowFileName = getFlowFileName(eventFlowList);
				ksone1.setR_icpIndex(flowFileName);
				if("本部门".equals(ksList.get(m).getRiskDep()))
				{
					ksone1.setR_riskDep(ksList.get(m).getDepName());
				}else{
					ksone1.setR_riskDep(ksList.get(m).getRiskDep());
				}				
				ksone1.setR_takeTime(ksList.get(m).getTakeTime());				
				ksone1.setR_supervisor(ksList.get(m).getSuperviseMan());								
				if(ksList.get(m).getSuperviseTime()==null||ksList.get(m).getSuperviseTime().length()<5)
				{
					ksone1.setR_result("未监控");
				}else{
					ksone1.setR_result("已监控");
				}
				ksone1.setR_time(ksList.get(m).getSuperviseTime());
				ksone1.setR_reId(ksList.get(m).getReId());
				Users us = getUser();
				int position = us.getUserPosition();
				String taketime = ksList.get(m).getTakeTime();
				if((position == 7)&&(taketime!=null&&taketime.length()>=5)){
					ksone1.setR_power("1");
				}else{
					ksone1.setR_power("0");
				}
				ksList2.add(ksone1);
				riskeventcount++;
				if(m<ksList3.size()-1){
					if(!(ksList3.get(m).getRiskName().equals(ksList3.get(m+1).getRiskName()))){
						riskSupervise ksone2=new riskSupervise();
						ksone2.setR_riskNum(riskcount+" ");
						ksone2.setR_riskId(ksList.get(m).getRiskId()+" ");
						ksone2.setR_riskName(ksList.get(m).getRiskName()+" ");
						ksone2.setR_rtName(ksList.get(m).getRtName()+" ");
						//ksone2.setR_depName(depcount+"");//插入二级风险对应的部门个数
						ksone2.setR_depName("小计");//插入二级风险对应的部门个数
						ksone2.setR_reEventname(Integer.toString(riskeventcount));//插入二级风险对应的风险事件个数
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
					riskSupervise ksone3=new riskSupervise();
					ksone3.setR_riskNum(riskcount+" ");
					ksone3.setR_riskId(ksList.get(m).getRiskId()+" ");
					ksone3.setR_riskName(ksList.get(m).getRiskName()+" ");
					ksone3.setR_rtName(ksList.get(m).getRtName()+" ");
					ksone3.setR_depName(depcount+"");//插入二级风险对应的部门个数
					ksone3.setR_depName("小计");//插入二级风险对应的部门个数
					ksone3.setR_reEventname(Integer.toString(riskeventcount));//插入二级风险对应的风险事件个数
					ksList2.add(ksone3);
					alleventcount+=riskeventcount;
					riskeventcount = 0;
					depcount = 0;
				}
			}			
			//总计功能
			riskSupervise ksone4=new riskSupervise();
			ksone4.setR_riskNum("总计");
			ksone4.setR_riskId("-");
			ksone4.setR_riskName(riskcount+"");
			ksone4.setR_rtName("-");
			ksone4.setR_depName("-");//插入二级风险对应的部门个数
			ksone4.setR_reEventname(Integer.toString(alleventcount));//插入二级风险对应的风险事件个数
			ksList2.add(ksone4);			
		
			// 数据存放在session中，便于导出excel
			String[][] dsarray = new String[ksList2.size()][21];
			for (int m = 0; m < ksList2.size(); m++) {
				dsarray[m][0] = ksList2.get(m).getR_riskNum();
				dsarray[m][1] = ksList2.get(m).getR_riskId();
				dsarray[m][2] = ksList2.get(m).getR_riskName();
				dsarray[m][3] = ksList2.get(m).getR_rtName();
				dsarray[m][4] = ksList2.get(m).getR_depName();
				dsarray[m][5] = ksList2.get(m).getR_reEventname();
				dsarray[m][6] = ksList2.get(m).getR_reDetail();
				dsarray[m][7] = ksList2.get(m).getR_rmStrategy();
				dsarray[m][8] = ksList2.get(m).getR_rmReply();
				dsarray[m][9] = ksList2.get(m).getR_rmPlandate();
				dsarray[m][10] = ksList2.get(m).getR_rmPlanres();			
				dsarray[m][11] = ksList2.get(m).getR_tsSystem();
				dsarray[m][12] = ksList2.get(m).getR_manageSta();
				dsarray[m][13] = ksList2.get(m).getR_workSta();
				dsarray[m][14] = ksList2.get(m).getR_emergencyPlan();
				dsarray[m][15] = ksList2.get(m).getR_icpIndex();
				dsarray[m][16] = ksList2.get(m).getR_riskDep();
				dsarray[m][17] = ksList2.get(m).getR_takeTime();
				dsarray[m][18] = ksList2.get(m).getR_result();
				dsarray[m][19] = ksList2.get(m).getR_supervisor();
				dsarray[m][20] = ksList2.get(m).getR_time();
			}
			Map<String, Object> session = ServletActionContext.getContext().getSession();
			session.put("exportSuperviseList", dsarray);
			session.put("Supervisecondition", this.dateFrom + "至"+this.dateTo);
//			session.put("Supervisecondition1",this.replyDateFrom + "至" + this.replyDateTo);
		}
		return "success";
		
	}
	
	// 点击“监控”，更新riskmanage的superTime
	public String riskSupervise() {
		try {
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Users us = getUser();
			String name = us.getUserName();
			riskReplyViewDAO.updatesupervised(this.reId,df.format(date).toString(),name);
			getRiskList();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	//导出部门管理监控事件列表
	public String superviseExportExcel() {
		Map<?, ?> session = ServletActionContext.getContext().getSession();
		if (session.get("Supervisecondition") == null
				|| session.get("exportSuperviseList") == null)
			return "fail";
		else {
			String str = "部门管理监控事件列表统计--" + session.get("Supervisecondition");
			ExcelReportAction ex = new ExcelReportAction();
			ex.ReportExcel("部门管理监控事件列表统计查询", "riskSuperviseTemplate",
					(String[][]) session.get("exportSuperviseList"), 4, 6, str);// 4表示从第五行开始，6表示从第三列开始合并，str是查询条件
			return "test";
		}
	}
	
	
	//得到流程文件的函数
	String[] getFileName(String reId){ 
		String[] fileName={"","","",""};
		List<EventFileView> fmList11=new LinkedList<EventFileView>();
		fmList11=riskReplyViewDAO.findFile(reId);
		Iterator<EventFileView> it =fmList11.iterator();
		EventFileView model = new EventFileView();
		int[] a = {0,0,0,0};
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
				default:break;
				}
			}			
		}	
		return fileName;
	}
	
	//查询条件
	private String getCondition() {
		String queryString = "";
		String dateFrom1 = this.dateFrom;
		String dateTo1 = this.dateTo;
		String replydateFrom1 = this.replyDateFrom;
		String replydateTo1 = this.replyDateTo;
		
		if("".equals(this.dateFrom)||this.dateFrom == null){
			dateFrom1="2000-01-01 00:00:00";
		}else{
			dateFrom1=this.dateFrom+" 00:00:00";
		}
		if("".equals(this.dateTo)||this.dateTo == null){
			dateTo1 = "2050-12-31 23:59:59";		
		}else{
			dateTo1 =this.dateTo+" 23:59:59";
		}
		if("".equals(this.replyDateFrom)||this.replyDateFrom == null){
			replydateFrom1="2000-01-01 00:00:00";
		}else{
			replydateFrom1=this.replyDateFrom+" 00:00:00";
		}
		if("".equals(this.replyDateTo)||this.replyDateTo == null){
			replydateTo1 = "2050-12-31 23:59:59";		
		}else{
			replydateTo1 =this.replyDateTo+" 23:59:59";
		}
		
		if("2".equals(choosedId)){
			queryString += "";
//			this.replyState="--请选择--";
//			this.superviseState="--请选择--";
			this.replyDateFrom=null;
			this.replyDateTo=null;
			queryString += " model.remodifydate between '"+dateFrom1+"' and '"+dateTo1 + "'";	
			if("--请选择--".equals(this.replyState)){
				queryString += "";
				this.superviseState="--请选择--";
			}else if("已应对".equals(this.replyState)){
				queryString += " and len(model.takeTime)>5 ";
			}else{
				queryString += " and (len(model.takeTime)<=5 or model.takeTime is null)";
				this.superviseState="--请选择--";
			}
			if("--请选择--".equals(this.superviseState)){
				queryString += "";
			}else if("已监控".equals(this.superviseState)){
				queryString += " and len(model.superviseTime)>5";
			}else{
				queryString += " and (model.superviseTime is NULL or len(model.superviseTime)<=5)";
			}
		}else{
			queryString += "";
			queryString += " model.rmdate between '"+dateFrom1+"' and '"+dateTo1 + "'";
			if("--请选择--".equals(this.replyState)){
				queryString += "";
				this.superviseState="--请选择--";
			}else if("已应对".equals(this.replyState)){
				queryString += " and len(model.takeTime)>5 and model.takeTime between '"+replydateFrom1+"' and '"+replydateTo1 + "'";
			}else{
				queryString += " and (len(model.takeTime)<=5 or model.takeTime is null)";
				this.superviseState="--请选择--";
			}
			if("--请选择--".equals(this.superviseState)){
				queryString += "";
			}else if("已监控".equals(this.superviseState)){
				queryString += " and len(model.superviseTime)>5";
			}else{
				queryString += " and (model.superviseTime is NULL or len(model.superviseTime)<=5)";
			}
		}
		if("--请选择--".equals(this.reIndep)){
			queryString += "";
		}else{
			queryString += " and model.depName='"+reIndep+"'";
		}
//		queryString += " model.rmdate between '"+dateFrom1+"' and '"+dateTo1 + "'";
//		if("--请选择--".equals(this.reIndep)){
//			queryString += "";
//		}else{
//			queryString += " and model.depName='"+reIndep+"'";
//		}
//		if("--请选择--".equals(this.replyState)){
//			queryString += "";
//			this.superviseState="--请选择--";
//		}else if("已应对".equals(this.replyState)){
//			queryString += " and len(model.takeTime)>5 and model.takeTime between '"+replydateFrom1+"' and '"+replydateTo1 + "'";
//		}else{
//			queryString += " and (len(model.takeTime)<=5 or model.takeTime is null)";
//			this.superviseState="--请选择--";
//		}
//		if("--请选择--".equals(this.superviseState)){
//			queryString += "";
//		}else if("已监控".equals(this.superviseState)){
//			queryString += " and len(model.superviseTime)>5";
//		}else{
//			queryString += " and (model.superviseTime is NULL or len(model.superviseTime)<=5)";
//		}
		return queryString;
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
				
