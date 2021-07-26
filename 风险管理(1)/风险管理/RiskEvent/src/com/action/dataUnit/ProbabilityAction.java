package com.action.dataUnit;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.ProbabilityDAO;
import com.model.Probability;

public class ProbabilityAction {
	private Integer proId;
	private String proIdString;
	private String proLevel;
	private String proProbability;
	private String proDisasterEvent;
	private String proDailyOperation;
	private List<Probability> proList;
	private List<Integer> idCheck;
	private String updateFlag;
	 private int current_pagenum=1;//当前页码
	    private int  pageNum=10;//每页的显示数据记录数

	private ProbabilityDAO probabilityDao;

	Probability probability = new Probability();

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

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {		
		this.proId=proId;
	}
	
	public String getProIdString() {
		return proIdString;
	}

	public void setProIdString(String proIdString) {
		this.proIdString = proIdString;
	}

	public String getProLevel() {
		return proLevel;
	}

	public void setProLevel(String proLevel) {
		this.proLevel = proLevel;
	}

	public String getProProbability() {
		return proProbability;
	}

	public void setProProbability(String proProbability) {
		this.proProbability = proProbability;
	}

	public String getProDisasterEvent() {
		return proDisasterEvent;
	}

	public void setProDisasterEvent(String proDisasterEvent) {
		this.proDisasterEvent = proDisasterEvent;
	}

	public String getProDailyOperation() {
		return proDailyOperation;
	}

	public void setProDailyOperation(String proDailyOperation) {
		this.proDailyOperation = proDailyOperation;
	}

	public List<Probability> getProList() {
		return proList;
	}

	public void setProList(List<Probability> proList) {
		this.proList = proList;
	}

	public List<Integer> getIdCheck() {
		return idCheck;
	}

	public void setIdCheck(List<Integer> idCheck) {
		this.idCheck = idCheck;
	}
	
	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public ProbabilityDAO getProbabilityDao() {
		return probabilityDao;
	}

	public void setProbabilityDao(ProbabilityDAO probabilityDao) {
		this.probabilityDao = probabilityDao;
	}

	public Probability getProbability() {
		return probability;
	}

	public void setProbability(Probability probability) {
		this.probability = probability;
	}

	// 显示发生可能性信息,得到最新proList,用于在Probability.jsp表单中显示
	public String proManage() {
		this.proList = new LinkedList<Probability>();
		this.proList = this.getProbabilityDao().findAll((current_pagenum-1)*pageNum,pageNum);
		ServletActionContext.getRequest().setAttribute("proList", proList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	
		if (proList.size() > 0)
			return "success";
		else
			return "fail";
	}
	
	// 新增|编辑
	public String proAddUpdate() {
		if(this.getUpdateFlag().equals("update")){
			//probability=this.getProbabilityDao().findById(this.getProId());
			probability.setProId(Integer.parseInt(this.getProIdString()));
			probability.setProLevel(this.getProLevel());
			probability.setProProbability(this.getProProbability());
			probability.setProDisasterEvent(this.getProDisasterEvent());
			probability.setProDailyOperation(this.getProDailyOperation());
			this.getProbabilityDao().merge(probability);
			proManage();// 得到最新proList,用于在Probability.jsp表单中显示
			return "success";
		}
		else{
			
			if(probabilityDao.findById(Integer.parseInt(this.getProIdString()))!=null)return "fail";
			
			probability.setProId(Integer.parseInt(this.getProIdString()));
			//probability.setProId(this.getProId());
			probability.setProLevel(this.getProLevel());
			probability.setProProbability(this.getProProbability());
			probability.setProDisasterEvent(this.getProDisasterEvent());
			probability.setProDailyOperation(this.getProDailyOperation());
			this.getProbabilityDao().save(probability);
			proManage();// 得到最新proList,用于在Probability.jsp表单中显示
			return "success";
		}
	}	

	// 批量删除
	public String proMultiDel() {
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			this.setProId(this.idCheck.get(i));
			probability = this.getProbabilityDao().findById(this.getProId());
			this.getProbabilityDao().delete(probability);
		}
		proManage();// 得到最新proList,用于在Probability.jsp表单中显示
		return "success";
	}

	// 删除
	public String proDelete() {
		probability = this.getProbabilityDao().findById(this.getProId());
		this.getProbabilityDao().delete(probability);
		proManage();// 得到最新proList,用于在Probability.jsp表单中显示
		return "success";
	}

	// 查看
	public String proRead() {
		try {
			probability = this.getProbabilityDao().findById(this.getProId());
			this.proLevel=probability.getProLevel();
			this.proProbability = probability.getProProbability();
			this.proDisasterEvent = probability.getProDisasterEvent();
			this.proDailyOperation = probability.getProDailyOperation();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 编辑前显示要编辑的对象的信息
	public String proUpdatePrepare() {
		try{
			probability = this.getProbabilityDao().findById(this.getProId());
			this.proLevel=probability.getProLevel();
			this.proProbability = probability.getProProbability();
			this.proDisasterEvent = probability.getProDisasterEvent();
			this.proDailyOperation = probability.getProDailyOperation();
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
}
