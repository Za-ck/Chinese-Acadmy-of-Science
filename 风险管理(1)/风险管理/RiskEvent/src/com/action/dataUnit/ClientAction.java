package com.action.dataUnit;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.ClientDAO;
import com.model.Client;
public class ClientAction {
	private Integer cliId;
	private String cliIdString;
    private String cliLevel;
    private String cliDetail;
    private List<Client> cliList;
    private List<Integer> idCheck;
    private String updateFlag;
	private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
    private ClientDAO clientDao;
    Client client=new Client();
    
	public Integer getCliId() {
		return cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}
	public String getCliIdString() {
		return cliIdString;
	}
	public void setCliIdString(String cliIdString) {
		this.cliIdString = cliIdString;
	}
	public String getCliLevel() {
		return cliLevel;
	}
	public void setCliLevel(String cliLevel) {
		this.cliLevel = cliLevel;
	}
	public String getCliDetail() {
		return cliDetail;
	}
	public void setCliDetail(String cliDetail) {
		this.cliDetail = cliDetail;
	}
	public List<Client> getCliList() {
		return cliList;
	}
	public void setCliList(List<Client> cliList) {
		this.cliList = cliList;
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
	public ClientDAO getClientDao() {
		return clientDao;
	}
	public void setClientDao(ClientDAO clientDao) {
		this.clientDao = clientDao;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
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
	// 显示针对客户关系的影响评定等级信息,得到最新cliList,用于在Client.jsp表单中显示
	@SuppressWarnings("unchecked")
	public String cliManage() {
		this.cliList = new LinkedList<Client>();
		this.cliList = this.getClientDao().findAll((current_pagenum-1)*pageNum,pageNum);		
		ServletActionContext.getRequest().setAttribute("cliList", cliList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
		if (cliList.size() > 0)
			return "success";
		else
			return "fail";
	}
	
	// 新增|编辑
	public String cliAddUpdate() {
		if(this.getUpdateFlag().equals("update")){	
			//client=this.getClientDao().findById(this.getCliId()); //自增时用法
			client.setCliId(Integer.parseInt(this.getCliIdString()));//在Client.jsp中得到的cliIdString是String类型，必须转化成Integer后再client.setCliId
			client.setCliLevel(this.getCliLevel());
			client.setCliDetail(this.getCliDetail());
			this.getClientDao().merge(client);
			cliManage();// 得到最新cliList,用于在Client.jsp表单中显示
			return "success";
		}
		else{
			if(clientDao.findById(Integer.parseInt(this.getCliIdString()))!=null)return "fail";
			client.setCliId(Integer.parseInt(this.getCliIdString()));
			client.setCliLevel(this.getCliLevel());
			client.setCliDetail(this.getCliDetail());
			this.getClientDao().save(client);
			cliManage();// 得到最新cliList,用于在Client.jsp表单中显示
			return "success";
		}
	}	
	
	// 批量删除
	public String cliMultiDel() {
		
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			this.setCliId(this.idCheck.get(i));
			client=this.getClientDao().findById(this.getCliId());
			this.getClientDao().delete(client);
		}
		cliManage();// 得到最新cliList,用于在Client.jsp表单中显示
		return "success";
	}

	// 删除
	public String cliDelete() {
		client=this.getClientDao().findById(this.getCliId());
		this.getClientDao().delete(client);
		cliManage();// 得到最新cliList,用于在Client.jsp表单中显示
		return "success";
	}

	// 查看
	public String cliRead() {
		client=this.getClientDao().findById(this.getCliId());
		this.cliLevel=client.getCliLevel();
		this.cliDetail=client.getCliDetail();
		return "success";
	}
	
	// 编辑前显示要编辑的对象的信息
	public String cliUpdatePrepare() {
		try{
			client=this.getClientDao().findById(this.getCliId());
			this.cliLevel=client.getCliLevel();
			this.cliDetail=client.getCliDetail();
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
}
