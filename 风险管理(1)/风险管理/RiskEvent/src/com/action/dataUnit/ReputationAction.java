package com.action.dataUnit;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.ReputationDAO;
import com.model.Reputation;

public class ReputationAction {
	private Integer repId;
	private String repIdString;
    private String repLevel;
    private String repDetail;
    private String repSuperior;
	private String repPartner;
	private String repPublic;
    private List<Reputation> repList;
    private List<Integer> idCheck;
    private String updateFlag;
    private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
       
    private ReputationDAO reputationDao;
    Reputation reputation=new Reputation();
    
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
	public Integer getRepId() {
		return repId;
	}
	public void setRepId(Integer repId) {
		this.repId = repId;
	}
	public String getRepIdString() {
		return repIdString;
	}
	public void setRepIdString(String repIdString) {
		this.repIdString = repIdString;
	}
	public String getRepLevel() {
		return repLevel;
	}
	public void setRepLevel(String repLevel) {
		this.repLevel = repLevel;
	}
	public String getRepDetail() {
		return repDetail;
	}
	public void setRepDetail(String repDetail) {
		this.repDetail = repDetail;
	}
	public String getRepSuperior() {
		return repSuperior;
	}
	public void setRepSuperior(String repSuperior) {
		this.repSuperior = repSuperior;
	}
	public String getRepPartner() {
		return repPartner;
	}
	public void setRepPartner(String repPartner) {
		this.repPartner = repPartner;
	}
	public String getRepPublic() {
		return repPublic;
	}
	public void setRepPublic(String repPublic) {
		this.repPublic = repPublic;
	}
	public List<Reputation> getRepList() {
		return repList;
	}
	public void setRepList(List<Reputation> repList) {
		this.repList = repList;
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
	public ReputationDAO getReputationDao() {
		return reputationDao;
	}
	public void setReputationDao(ReputationDAO reputationDao) {
		this.reputationDao = reputationDao;
	}
	public Reputation getReputation() {
		return reputation;
	}
	public void setReputation(Reputation reputation) {
		this.reputation = reputation;
	}
    
	// 显示针对院声誉的影响评定等级信息,得到最新repList,用于在Reputation.jsp表单中显示
	public String repManage() {
		this.repList = new LinkedList<Reputation>();
		this.repList = this.getReputationDao().findAll((current_pagenum-1)*pageNum,pageNum);		
		ServletActionContext.getRequest().setAttribute("repList", repList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	
		if (repList.size() > 0)
			return "success";
		else
			return "fail";
	}
	
	// 新增|编辑
	public String repAddUpdate() {
		if(this.getUpdateFlag().equals("update")){	
			//reputation=this.getReputationDao().findById(this.getRepId());
			reputation.setRepId(Integer.parseInt(this.getRepIdString()));
			reputation.setRepLevel(this.getRepLevel());
			reputation.setRepDetail(this.getRepDetail());
			reputation.setRepSuperior(this.getRepSuperior());
			reputation.setRepPartner(this.getRepPartner());
			reputation.setRepPublic(this.getRepPublic());
			this.getReputationDao().merge(reputation);
			repManage();// 得到最新repList,用于在Reputation.jsp表单中显示
			return "success";
		}
		else{
			if(reputationDao.findById(Integer.parseInt(this.getRepIdString()))!=null)return "fail";
			
			reputation.setRepId(Integer.parseInt(this.getRepIdString()));
			reputation.setRepLevel(this.getRepLevel());
			reputation.setRepDetail(this.getRepDetail());
			reputation.setRepSuperior(this.getRepSuperior());
			reputation.setRepPartner(this.getRepPartner());
			reputation.setRepPublic(this.getRepPublic());
			this.getReputationDao().save(reputation);
			repManage();// 得到最新repList,用于在Reputation.jsp表单中显示
			return "success";
		}
	}	

	// 批量删除
	public String repMultiDel() {
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			this.setRepId(this.idCheck.get(i));
			reputation=this.getReputationDao().findById(this.getRepId());
			this.getReputationDao().delete(reputation);
		}
		repManage();// 得到最新repList,用于在Reputation.jsp表单中显示
		return "success";
	}

	// 删除
	public String repDelete() {
		reputation=this.getReputationDao().findById(this.getRepId());
		this.getReputationDao().delete(reputation);
		repManage();// 得到最新repList,用于在Reputation.jsp表单中显示
		return "success";
	}

	// 查看
	public String repRead() {
		try {
			reputation=this.getReputationDao().findById(this.getRepId());
			this.repLevel=reputation.getRepLevel();
			this.repDetail=reputation.getRepDetail();
			this.repSuperior=reputation.getRepSuperior();
			this.repPartner=reputation.getRepPartner();
			this.repPublic=reputation.getRepPublic();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 编辑前显示要编辑的对象的信息
	public String repUpdatePrepare() {
		try{
			reputation=this.getReputationDao().findById(this.getRepId());
			this.repLevel=reputation.getRepLevel();
			this.repDetail=reputation.getRepDetail();
			this.repSuperior=reputation.getRepSuperior();
			this.repPartner=reputation.getRepPartner();
			this.repPublic=reputation.getRepPublic();
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}  
}
