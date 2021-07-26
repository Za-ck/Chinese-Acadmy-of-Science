package com.action.riskFeedback;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.DepWarnDAO;
import com.dao.DepartmentDAO;
import com.dao.EventWarnViewDAO;
import com.model.DepWarn;
import com.model.Department;
import com.model.EventWarnView;
import com.model.Risk;


public class RiskCaution {
	private List<EventWarnView> riskCautionList;
	private EventWarnViewDAO riskWarnDao;
	private int current_pagenum=1;//当前页码
	private int  pageNum=10;//每页的显示数据记录数
	private DepWarnDAO depWarnDao;
	private String rcId;
	private DepartmentDAO departmentDao;
	private List<event> risknumberList;
	private List<Department> alldepList;
	private List<Department> depList;
	private String riskDeps;
	private String depName;
	 private String  risknumber;
	
	 class event{
			String id;
			String nameValue;
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public String getNameValue() {
				return nameValue;
			}
			public void setNameValue(String nameValue) {
				this.nameValue = nameValue;
			}
			
		}
	
public List<event> getRisknumberList() {
		return risknumberList;
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

public String getRiskDeps() {
	return riskDeps;
}

public void setRiskDeps(String riskDeps) {
	this.riskDeps = riskDeps;
}

public String getDepName() {
	return depName;
}

public void setDepName(String depName) {
	this.depName = depName;
}

	//	public List<event> getRisknumberList() {
//		/*DepWarn w1=new DepWarn();
//		w1.setDwId(-1);
//		w1.setRiskEvent("--请选择--");
//		
//		risknumberList=new LinkedList<DepWarn>();
//		risknumberList.add(w1);*/
//		List<DepWarn> dList =new LinkedList<DepWarn>();
//		//dList=this.getDepWarnDao().findAll();
//		dList=this.getDepWarnDao().findEvent();
//		
//		event e=new event();
//		e.id="--请选择--";
//		e.nameValue="--请选择--";
//		risknumberList=new LinkedList<event>();
//		risknumberList.add(e);
//		for(int i=0;i<dList.size();i++)
//		{
//			event a=new  event();
//			Object o=dList.get(i);
//			
//			a.setId(o.toString());
//			a.setNameValue(o.toString());
//			risknumberList.add(a);
//			
//		}
//		return risknumberList;
//	}
	public void setRisknumberList(List<event> risknumberList) {
		this.risknumberList = risknumberList;
	}
	public String getRisknumber() {
		return risknumber;
	}
	public void setRisknumber(String risknumber) {
		this.risknumber = risknumber;
	}
	public String getRcId() {
		return rcId;
	}
	public void setRcId(String rcId) {
		this.rcId = rcId;
	}
	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}
	public DepWarnDAO getDepWarnDao() {
		return depWarnDao;
	}
	public void setDepWarnDao(DepWarnDAO depWarnDao) {
		this.depWarnDao = depWarnDao;
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
	public List<EventWarnView> getRiskCautionList() {
		return riskCautionList;
	}
	public void setRiskCautionList(List<EventWarnView> riskCautionList) {
		this.riskCautionList = riskCautionList;
	}
	public EventWarnViewDAO getRiskWarnDao() {
		return riskWarnDao;
	}
	public void setRiskWarnDao(EventWarnViewDAO riskWarnDao) {
		this.riskWarnDao = riskWarnDao;
	}

	public String riskCautionShow(){
		//getRisknumberList();
		riskCautionList=new LinkedList<EventWarnView>();
		riskCautionList=this.getRiskWarnDao().findWarn((current_pagenum-1)*pageNum,pageNum);
		
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
		ServletActionContext.getRequest().getSession().setAttribute("riskCautionList", riskCautionList);
		event e=new event();
		e.id="--请选择--";
		e.nameValue="--请选择--";
		risknumberList=new LinkedList<event>();
		risknumberList.add(e);
		Department dep1=new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		alldepList=new LinkedList<Department>();
		alldepList.add(dep1);
		depList=new LinkedList<Department>();
		depList=this.getDepartmentDao().findAll();
		alldepList.addAll(depList);	
		if(riskCautionList.size()>0){
			for(int i=0;i<riskCautionList.size();i++)
			{
				event a=new  event();
				Object o=riskCautionList.get(i).getId().getReId();
				
				a.setId(o.toString());
				a.setNameValue(o.toString());
				risknumberList.add(a);
				
			}
			return "success";
		}
		else
			return "fail";
	}
	public String riskQuery(){
		//getRisknumberList();
		Department dep1=new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		alldepList=new LinkedList<Department>();
		alldepList.add(dep1);
		depList=new LinkedList<Department>();
		depList=this.getDepartmentDao().findAll();
		alldepList.addAll(depList);	
		riskCautionList=new LinkedList<EventWarnView>();
		riskCautionList=this.getRiskWarnDao().bianhaoQuery(this.riskDeps,(current_pagenum-1)*pageNum, pageNum);
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
		if(riskCautionList.size()>0){
			ServletActionContext.getRequest().getSession().setAttribute("riskCautionList", riskCautionList);
			return "success";
		}
		else
			return "fail";                  
	}
	
	public String riskTellCaution()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String ly_time = sdf.format(new java.util.Date()); 
		DepWarn ob=new DepWarn();
		
		EventWarnView ew=new EventWarnView();
		List<EventWarnView> eList=new LinkedList<EventWarnView>();
		eList=this.getRiskWarnDao().findByProperty("id.reId",this.rcId );
		if(eList.size()>0)
	   {
			ew=eList.get(0);
			String riskEventId=ew.getId().getReId();
			ob.setRiskEvent(riskEventId);
			String name=ew.getId().getDepName();
			String planTime=ew.getId().getRmPlandate();
			ob.setDwPlanTime(planTime);
			ob.setDwReason("超时");
			List<Department> deList=new LinkedList<Department>();
			deList=this.getDepartmentDao().findByProperty("id.depName", name);
			if(deList.size()>0)
			{
				ob.setDwTime(ly_time);
			ob.setDepartment(deList.get(0));
			this.getDepWarnDao().save(ob);
			
			}
			
			return "success";
		
	 }
		else
		{
			
			return "fail";
		}
	}
}
