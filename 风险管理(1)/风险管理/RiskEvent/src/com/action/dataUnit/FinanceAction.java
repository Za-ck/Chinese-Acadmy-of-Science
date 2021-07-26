package com.action.dataUnit;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.FinanceDAO;
import com.model.Finance;


public class FinanceAction {
	private Integer finId;
	private String finIdString;
	private String finDetail;
	private String finAsset;
	private String finIncome;
	private String finProfit;
	private String finProperty;
	private List<Finance> finList;
    private List<Integer> idCheck;
    private String updateFlag;
    private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
       
    private FinanceDAO financeDao;
    Finance finance=new Finance();
    
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
	public Integer getFinId() {
		return finId;
	}
	public void setFinId(Integer finId) {
		this.finId = finId;
	}
	public String getFinIdString() {
		return finIdString;
	}
	public void setFinIdString(String finIdString) {
		this.finIdString = finIdString;
	}
	public String getFinDetail() {
		return finDetail;
	}
	public void setFinDetail(String finDetail) {
		this.finDetail = finDetail;
	}
	public String getFinAsset() {
		return finAsset;
	}
	public void setFinAsset(String finAsset) {
		this.finAsset = finAsset;
	}
	public String getFinIncome() {
		return finIncome;
	}
	public void setFinIncome(String finIncome) {
		this.finIncome = finIncome;
	}
	public String getFinProfit() {
		return finProfit;
	}
	public void setFinProfit(String finProfit) {
		this.finProfit = finProfit;
	}
	public String getFinProperty() {
		return finProperty;
	}
	public void setFinProperty(String finProperty) {
		this.finProperty = finProperty;
	}
	public List<Finance> getFinList() {
		return finList;
	}
	public void setFinList(List<Finance> finList) {
		this.finList = finList;
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
	public FinanceDAO getFinanceDao() {
		return financeDao;
	}
	public void setFinanceDao(FinanceDAO financeDao) {
		this.financeDao = financeDao;
	}
	public Finance getFinance() {
		return finance;
	}
	public void setFinance(Finance finance) {
		this.finance = finance;
	}
    
	// 显示针对财务的影响评定等级信息,得到最新finList,用于在Finance.jsp表单中显示
	public String finManage() {
		this.finList = new LinkedList<Finance>();
		this.finList = this.getFinanceDao().findAll((current_pagenum-1)*pageNum,pageNum);
		ServletActionContext.getRequest().setAttribute("finList", finList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	
		if (finList.size() > 0)
			return "success";
		else
			return "fail";
	}
	
	// 新增|编辑
	public String finAddUpdate() {
		if(this.getUpdateFlag().equals("update")){	
			finance.setFinId(Integer.parseInt(this.getFinIdString()));
			finance.setFinDetail(this.getFinDetail());
			finance.setFinAsset(this.getFinAsset());
			finance.setFinIncome(this.getFinIncome());
			finance.setFinProfit(this.getFinProfit());
			finance.setFinProperty(this.getFinProperty());
			this.getFinanceDao().merge(finance);
			finManage();// 得到最新finList,用于在Finance.jsp表单中显示
			return "success";
		}
		else{
			if(financeDao.findById(Integer.parseInt(this.getFinIdString()))!=null)return "fail";
			finance.setFinId(Integer.parseInt(this.getFinIdString()));
			finance.setFinDetail(this.getFinDetail());
			finance.setFinAsset(this.getFinAsset());
			finance.setFinIncome(this.getFinIncome());
			finance.setFinProfit(this.getFinProfit());
			finance.setFinProperty(this.getFinProperty());
			this.getFinanceDao().save(finance);
			finManage();// 得到最新finList,用于在Finance.jsp表单中显示
			return "success";
		}
	}	

	// 批量删除
	public String finMultiDel() {
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			this.setFinId(this.idCheck.get(i));
			finance=this.getFinanceDao().findById(this.getFinId());
			this.getFinanceDao().delete(finance);
		}
		finManage();// 得到最新finList,用于在Finance.jsp表单中显示
		return "success";
	}

	// 删除
	public String finDelete() {
		finance=this.getFinanceDao().findById(this.getFinId());
		this.getFinanceDao().delete(finance);
		finManage();// 得到最新finList,用于在Finance.jsp表单中显示
		return "success";
	}

	// 查看
	public String finRead() {
		try {
			finance=this.getFinanceDao().findById(this.getFinId());
			this.finDetail=finance.getFinDetail();
			this.finAsset=finance.getFinAsset();
			this.finIncome=finance.getFinIncome();
			this.finProfit=finance.getFinProfit();
			this.finProperty=finance.getFinProperty();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 编辑前显示要编辑的对象的信息
	public String finUpdatePrepare() {
		try{
			finance=this.getFinanceDao().findById(this.getFinId());
			this.finDetail=finance.getFinDetail();
			this.finAsset=finance.getFinAsset();
			this.finIncome=finance.getFinIncome();
			this.finProfit=finance.getFinProfit();
			this.finProperty=finance.getFinProperty();
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}  
}
