package com.action.dataUnit;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.LawDAO;
import com.model.Law;

public class LawAction {
	private Integer lawId;
	private String lawIdString;
    private String lawLevel;
    private String lawDetail;
    private List<Law> lawList;
    private List<Integer> idCheck;
    private String updateFlag;
    private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
       
    private LawDAO lawDao;
    Law law=new Law();
    
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
	public Integer getLawId() {
		return lawId;
	}
	public void setLawId(Integer lawId) {
		this.lawId = lawId;
	}
	public String getLawIdString() {
		return lawIdString;
	}
	public void setLawIdString(String lawIdString) {
		this.lawIdString = lawIdString;
	}
	public String getLawLevel() {
		return lawLevel;
	}
	public void setLawLevel(String lawLevel) {
		this.lawLevel = lawLevel;
	}
	public String getLawDetail() {
		return lawDetail;
	}
	public void setLawDetail(String lawDetail) {
		this.lawDetail = lawDetail;
	}
	public List<Law> getLawList() {
		return lawList;
	}
	public void setLawList(List<Law> lawList) {
		this.lawList = lawList;
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
	public LawDAO getLawDao() {
		return lawDao;
	}
	public void setLawDao(LawDAO lawDao) {
		this.lawDao = lawDao;
	}
	public Law getLaw() {
		return law;
	}
	public void setLaw(Law law) {
		this.law = law;
	}
    
	// 显示针对法律法规的影响评定等级信息,得到最新lawList,用于在Law.jsp表单中显示
	public String lawManage() {
		this.lawList = new LinkedList<Law>();
		this.lawList = this.getLawDao().findAll((current_pagenum-1)*pageNum,pageNum);
		ServletActionContext.getRequest().setAttribute("lawList", lawList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
		if (lawList.size() > 0)
			return "success";
		else
			return "fail";
	}
	
	// 新增|编辑
	public String lawAddUpdate() {
		if(this.getUpdateFlag().equals("update")){	
			//law=this.getLawDao().findById(this.getLawId());
			law.setLawId(Integer.parseInt(this.getLawIdString()));
			law.setLawLevel(this.getLawLevel());
			law.setLawDetail(this.getLawDetail());
			
			this.getLawDao().merge(law);
			lawManage();// 得到最新lawList,用于在Law.jsp表单中显示
			return "success";
		}
		else{
			
			if(lawDao.findById(Integer.parseInt(this.getLawIdString()))!=null)return "fail";
			
			law.setLawId(Integer.parseInt(this.getLawIdString()));
			law.setLawLevel(this.getLawLevel());
			law.setLawDetail(this.getLawDetail());
			this.getLawDao().save(law);
			lawManage();// 得到最新lawList,用于在Law.jsp表单中显示
			return "success";
		}
	}	

	// 批量删除
	public String lawMultiDel() {
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			
			law=this.getLawDao().findById(this.idCheck.get(i));
			this.getLawDao().delete(law);
		}
		lawManage();// 得到最新lawList,用于在Law.jsp表单中显示
		return "success";
	}

	// 删除
	public String lawDelete() {
		law=this.getLawDao().findById(this.getLawId());
		this.getLawDao().delete(law);
		lawManage();// 得到最新lawList,用于在Law.jsp表单中显示
		return "success";
	}

	// 查看
	public String lawRead() {
		try {
			law=this.getLawDao().findById(this.getLawId());
			this.lawLevel=law.getLawLevel();
			this.lawDetail=law.getLawDetail();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 编辑前显示要编辑的对象的信息
	public String lawUpdatePrepare() {
		try{
			law=this.getLawDao().findById(this.getLawId());
			this.lawLevel=law.getLawLevel();
			this.lawDetail=law.getLawDetail();
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
}
