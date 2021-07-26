package com.action.dataUnit;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.OperationDAO;
import com.model.Operation;
import com.model.Probability;

public class OperationAction {
	private Integer opeId;
	private String opeIdString;
	private String opeLevel;
	private String opeDetail;
	private List<Operation> opeList;
	private List<Integer> idCheck;
	private String updateFlag;
	private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
	
	private OperationDAO operationDao;
	
	Operation operation=new Operation();

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

	public Integer getOpeId() {
		return opeId;
	}
	
	public void setOpeId(Integer opeId) {
		this.opeId = opeId;
	}
	
	public String getOpeIdString() {
		return opeIdString;
	}

	public void setOpeIdString(String opeIdString) {
		this.opeIdString = opeIdString;
	}

	public String getOpeLevel() {
		return opeLevel;
	}

	public void setOpeLevel(String opeLevel) {
		this.opeLevel = opeLevel;
	}

	public String getOpeDetail() {
		return opeDetail;
	}

	public void setOpeDetail(String opeDetail) {
		this.opeDetail = opeDetail;
	}

	public List<Operation> getOpeList() {
		return opeList;
	}

	public void setOpeList(List<Operation> opeList) {
		this.opeList = opeList;
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

	public OperationDAO getOperationDao() {
		return operationDao;
	}

	public void setOperationDao(OperationDAO operationDao) {
		this.operationDao = operationDao;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
	// 显示发生可能性信息,得到最新opeList,用于在Operation.jsp表单中显示
	public String opeManage() {
		this.opeList = new LinkedList<Operation>();
		this.opeList = this.getOperationDao().findAll((current_pagenum-1)*pageNum,pageNum);
		ServletActionContext.getRequest().setAttribute("opeList", opeList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	
		if (opeList.size() > 0)
			return "success";
		else
			return "fail";
	}
	
	// 新增|编辑
	public String opeAddUpdate() {
		if(this.getUpdateFlag().equals("update")){
			//operation=this.getOperationDao().findById(this.getOpeId());
			operation.setOpeId(Integer.parseInt(this.getOpeIdString()));
			operation.setOpeLevel(this.getOpeLevel());
			operation.setOpeDetail(this.getOpeDetail());
			this.getOperationDao().merge(operation);
			opeManage();// 得到最新opeList,用于在Operation.jsp表单中显示
			return "success";
		}
		else{
			if(operationDao.findById(Integer.parseInt(this.getOpeIdString()))!=null)return "fail";
			
			operation.setOpeId(Integer.parseInt(this.getOpeIdString()));
			operation.setOpeLevel(this.getOpeLevel());
			operation.setOpeDetail(this.getOpeDetail());
			this.getOperationDao().save(operation);
			opeManage();// 得到最新opeList,用于在Operation.jsp表单中显示
			return "success";
		}
	}	

	// 批量删除
	public String opeMultiDel() {
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			this.setOpeId(this.idCheck.get(i));
			operation=this.getOperationDao().findById(this.getOpeId());
			this.getOperationDao().delete(operation);
		}
		opeManage();// 得到最新opeList,用于在Operation.jsp表单中显示
		return "success";
	}

	// 删除
	public String opeDelete() {
		operation=this.getOperationDao().findById(this.getOpeId());
		this.getOperationDao().delete(operation);
		opeManage();// 得到最新opeList,用于在Operation.jsp表单中显示
		return "success";
	}

	// 查看
	public String opeRead() {
		try {
			operation=this.getOperationDao().findById(this.getOpeId());
			this.opeLevel=operation.getOpeLevel();
			this.opeDetail=operation.getOpeDetail();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 编辑前显示要编辑的对象的信息
	public String opeUpdatePrepare() {
		try{
			operation=this.getOperationDao().findById(this.getOpeId());
			this.opeLevel=operation.getOpeLevel();
			this.opeDetail=operation.getOpeDetail();
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
}
