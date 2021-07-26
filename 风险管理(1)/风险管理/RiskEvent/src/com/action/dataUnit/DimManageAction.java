package com.action.dataUnit;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.RiskDimensionDAO;
import com.dao.ProbabilityDAO;
import com.model.Probability;
import com.model.RiskDimension;

public class DimManageAction {
	private Integer rdId;
	private String rdIdString;
	private String rdDimName;
	private Integer rdIncreaseScore;
	private String rdIncreaseScoreString;
	private Integer rdDecreaseScore;
	private String rdDecreaseScoreString;
	private Integer rdWeight;
	private String rdWeightString;
	private String rdDimId;
	private String rdRemark2;
	private List<RiskDimension> riskDimList;
	private List<Integer> idCheck;
	private String updateFlag;
	private int current_pagenum = 1;// 当前页码
	private int pageNum = 10;// 每页的显示数据记录数
	
	private RiskDimensionDAO dimManageDao;
	
	RiskDimension riskDimension = new RiskDimension();

	public String getRdIncreaseScoreString() {
		return rdIncreaseScoreString;
	}

	public void setRdIncreaseScoreString(String rdIncreaseScoreString) {
		this.rdIncreaseScoreString = rdIncreaseScoreString;
	}

	public String getRdDecreaseScoreString() {
		return rdDecreaseScoreString;
	}

	public void setRdDecreaseScoreString(String rdDecreaseScoreString) {
		this.rdDecreaseScoreString = rdDecreaseScoreString;
	}

	public String getRdWeightString() {
		return rdWeightString;
	}

	public void setRdWeightString(String rdWeightString) {
		this.rdWeightString = rdWeightString;
	}

	public RiskDimension getRiskDimension() {
		return riskDimension;
	}
	
	public void setRiskDimension(RiskDimension RiskDimension) {
		this.riskDimension = RiskDimension;
	}
	
	public RiskDimensionDAO getDimManageDao() {
		return dimManageDao;
	}

	public void setDimManageDao(RiskDimensionDAO dimManageDao) {
		this.dimManageDao = dimManageDao;
	}

	public Integer getRdId() {
		return rdId;
	}

	public String getRdIdString() {
		return rdIdString;
	}

	public void setRdIdString(String rdIdString) {
		this.rdIdString = rdIdString;
	}

	public void setRdId(Integer rdId) {
		this.rdId = rdId;
	}

	public String getRdDimName() {
		return rdDimName;
	}

	public void setRdDimName(String rdDimName) {
		this.rdDimName = rdDimName;
	}

	public Integer getRdIncreaseScore() {
		return rdIncreaseScore;
	}

	public void setRdIncreaseScore(Integer rdIncreaseScore) {
		this.rdIncreaseScore = rdIncreaseScore;
	}

	public Integer getRdDecreaseScore() {
		return rdDecreaseScore;
	}

	public void setRdDecreaseScore(Integer rdDecreaseScore) {
		this.rdDecreaseScore = rdDecreaseScore;
	}

	public Integer getRdWeight() {
		return rdWeight;
	}

	public void setRdWeight(Integer rdWeight) {
		this.rdWeight = rdWeight;
	}

	public String getRdDimId() {
		return rdDimId;
	}

	public void setRdDimId(String rdDimId) {
		this.rdDimId = rdDimId;
	}

	public String getRdRemark2() {
		return rdRemark2;
	}

	public void setRdRemark2(String rdRemark2) {
		this.rdRemark2 = rdRemark2;
	}

	public List<RiskDimension> getRiskDimList() {
		return riskDimList;
	}

	public void setRiskDimList(List<RiskDimension> riskDimList) {
		riskDimList = riskDimList;
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

	public RiskDimension DimAll() {
		return riskDimension;
	}

	// 显示维度信息,得到最新riskDimList,用于在DimManage.jsp表单中显示
	@SuppressWarnings("unchecked")
	public String DimManage() {
		this.riskDimList = new LinkedList<RiskDimension>();
		this.riskDimList = this.getDimManageDao().findAll((current_pagenum - 1) * pageNum, pageNum);
		ServletActionContext.getRequest().setAttribute("RiskDimList",riskDimList);
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum", current_pagenum);
		if (riskDimList.size() > 0)
			return "success";
		else
			return "fail";
	}

	// 新增|编辑
	public String dimAddUpdate() {
			riskDimension.setRdId(Integer.parseInt(this.getRdIdString()));
			riskDimension.setRdDimName(this.getRdDimName());
			riskDimension.setRdIncreaseScore(Integer.parseInt(this.getRdIncreaseScoreString()));
			riskDimension.setRdDecreaseScore(Integer.parseInt(this.getRdDecreaseScoreString()));
			riskDimension.setRdWeight(Integer.parseInt(this.getRdWeightString()));
			riskDimension.setRdDimId(this.getRdDimId());
			this.getDimManageDao().merge(riskDimension);
			DimManage();
			return "success";
	}
	
	// 批量删除
	public String dimMultiDel() {
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			this.setRdId(this.idCheck.get(i));
			riskDimension = this.getDimManageDao().findById(this.getRdId());
			this.getDimManageDao().delete(riskDimension);
		}
		DimManage();
		return "success";
	}

	// 删除
	public String dimDelete() {
		riskDimension = this.getDimManageDao().findById(this.getRdId());
		this.getDimManageDao().delete(riskDimension);
		DimManage();
		return "success";
	}

	// 编辑前显示要编辑的对象的信息
	public String dimUpdatePrepare() {
		try{
			riskDimension = this.getDimManageDao().findById(this.getRdId());
			this.rdDimName = riskDimension.getRdDimName();
			this.rdIncreaseScore = riskDimension.getRdIncreaseScore();
			this.rdDecreaseScore = riskDimension.getRdDecreaseScore();
			this.rdWeight = riskDimension.getRdWeight();
			this.rdDimId = riskDimension.getRdDimId();
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
}
