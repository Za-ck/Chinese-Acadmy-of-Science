package com.action.dataUnit;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.SafeDAO;
import com.model.Safe;

public class SafeAction {
	private Integer safId;
	private String safIdString;
	private String safLevel;
	private String safSafety;
	private String safEnvironment;
    private List<Safe> safList;
    private List<Integer> idCheck;
    private String updateFlag;
    private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
    private SafeDAO safeDao;
    Safe safe=new Safe();
	
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

	public Integer getSafId() {
		return safId;
	}

	public void setSafId(Integer safId) {
		this.safId = safId;
	}
	
	public String getSafIdString() {
		return safIdString;
	}

	public void setSafIdString(String safIdString) {
		this.safIdString = safIdString;
	}

	public String getSafLevel() {
		return safLevel;
	}

	public void setSafLevel(String safLevel) {
		this.safLevel = safLevel;
	}

	public String getSafSafety() {
		return safSafety;
	}

	public void setSafSafety(String safSafety) {
		this.safSafety = safSafety;
	}

	public String getSafEnvironment() {
		return safEnvironment;
	}

	public void setSafEnvironment(String safEnvironment) {
		this.safEnvironment = safEnvironment;
	}

	public List<Safe> getSafList() {
		return safList;
	}

	public void setSafList(List<Safe> safList) {
		this.safList = safList;
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

	public SafeDAO getSafeDao() {
		return safeDao;
	}

	public void setSafeDao(SafeDAO safeDao) {
		this.safeDao = safeDao;
	}

	public Safe getSafe() {
		return safe;
	}

	public void setSafe(Safe safe) {
		this.safe = safe;
	}

	// 显示针对安全健康环境的影响评定等级信息,得到最新safList,用于在Safe.jsp表单中显示
	public String safManage() {
		this.safList = new LinkedList<Safe>();
		this.safList = this.getSafeDao().findAll((current_pagenum-1)*pageNum,pageNum);
		ServletActionContext.getRequest().setAttribute("safList", safList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);
		if (safList.size() > 0)
			return "success";
		else
			return "fail";
	}
	
	// 新增|编辑
	public String safAddUpdate() {
		if(this.getUpdateFlag().equals("update")){	
			//safe=this.getSafeDao().findById(this.getSafId());
			safe.setSafId(Integer.parseInt(this.getSafIdString()));
			safe.setSafLevel(this.getSafLevel());
			safe.setSafSafety(this.getSafSafety());
			safe.setSafEnvironment(this.getSafEnvironment());
			this.getSafeDao().merge(safe);
			safManage();// 得到最新safList,用于在Safe.jsp表单中显示
			return "success";
		}
		else{
			if(safeDao.findById(Integer.parseInt(this.getSafIdString()))!=null)return "fail";
			
			safe.setSafId(Integer.parseInt(this.getSafIdString()));
			safe.setSafLevel(this.getSafLevel());
			safe.setSafSafety(this.getSafSafety());
			safe.setSafEnvironment(this.getSafEnvironment());
			this.getSafeDao().save(safe);
			safManage();// 得到最新safList,用于在Safe.jsp表单中显示
			return "success";
		}
	}

	// 批量删除
	public String safMultiDel() {
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			this.setSafId(this.idCheck.get(i));
			safe=this.getSafeDao().findById(this.getSafId());
			this.getSafeDao().delete(safe);
		}
		safManage();// 得到最新safList,用于在Safe.jsp表单中显示
		return "success";
	}

	// 删除
	public String safDelete() {
		safe=this.getSafeDao().findById(this.getSafId());
		this.getSafeDao().delete(safe);
		safManage();// 得到最新safList,用于在Safe.jsp表单中显示
		return "success";
	}

	// 查看
	public String safRead() {
		try {
			safe=this.getSafeDao().findById(this.getSafId());
			this.safLevel=safe.getSafLevel();
			this.safSafety=safe.getSafSafety();
			this.safEnvironment=safe.getSafEnvironment();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 编辑前显示要编辑的对象的信息
	public String safUpdatePrepare() {
		try{
			safe=this.getSafeDao().findById(this.getSafId());
			this.safLevel=safe.getSafLevel();
			this.safSafety=safe.getSafSafety();
			this.safEnvironment=safe.getSafEnvironment();
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
}
