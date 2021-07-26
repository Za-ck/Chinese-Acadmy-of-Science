package com.entity;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import com.dao.FunctionLimitDAO;
import com.model.FunctionModule;
import com.model.Users;

public class FunctionService {
	private List<FunctionModule> RIList;//风险录入模块
	private List<FunctionModule> RQList;//风险查询模块
	private List<FunctionModule> RFList;//风险流转模块
	private List<FunctionModule> RSList;//风险统计模块
	private List<FunctionModule> RBList;//风险反馈模块
	private List<FunctionModule> PLList;//政策法规模块
	private List<FunctionModule> SMList;//系统管理模块
	private List<FunctionModule> DUList;//数据字典模块
	private List<FunctionModule> PMList;//个人管理模块
	private boolean EmluatorDownloadFlag;
	private FunctionLimitDAO functionLimitdao;
	Users user=new Users();
	private Integer userrole=getSessionUser();//将user表中的user_position读取作为roleid
	
	public FunctionLimitDAO getFunctionLimitdao() {
		return functionLimitdao;
	}
	public void setFunctionLimitdao(FunctionLimitDAO functionLimitdao) {
		this.functionLimitdao = functionLimitdao;
	}
	public boolean isEmluatorDownloadFlag() {
		return EmluatorDownloadFlag;
	}
	public void setEmluatorDownloadFlag(boolean emluatorDownloadFlag) {
		this.getSessionUser();
		EmluatorDownloadFlag = emluatorDownloadFlag;
	}
	
	public List<FunctionModule> getRIList() {
		RIList = new LinkedList<FunctionModule>();
		//userrole=getSessionUser();//将user表中的user_position读取作为roleid
		int pid=11;//风险录入模块id为11
		RIList=functionLimitdao.findbyroleIdPar(userrole,pid);
		return RIList;
	}
	public void setRIList(List<FunctionModule> rIList) {
		RIList = rIList;
	}
	public List<FunctionModule> getRQList() {
		RQList = new LinkedList<FunctionModule>();
		int pid=12;//风险查询模块id为12
		RQList=functionLimitdao.findbyroleIdPar(userrole,pid);
		return RQList;
	}
	public void setRQList(List<FunctionModule> rQList) {
		RQList = rQList;
	}
	public List<FunctionModule> getRFList() {
		RFList = new LinkedList<FunctionModule>();
		int pid=13;//风险流转模块id为13
		RFList=functionLimitdao.findbyroleIdPar(userrole,pid);
		return RFList;
	}
	public void setRFList(List<FunctionModule> rFList) {
		RFList = rFList;
	}
	public List<FunctionModule> getRSList() {
		RSList = new LinkedList<FunctionModule>();
		int pid=14;//风险统计模块id为14
		RSList=functionLimitdao.findbyroleIdPar(userrole,pid);
		return RSList;
	}
	public void setRSList(List<FunctionModule> rSList) {
		RSList = rSList;
	}
	public List<FunctionModule> getRBList() {
		RBList = new LinkedList<FunctionModule>();
		int pid=15;//风险反馈模块id为15
		RBList=functionLimitdao.findbyroleIdPar(userrole,pid);
		return RBList;
	}
	public void setRBList(List<FunctionModule> rBList) {
		RBList = rBList;
	}
	public List<FunctionModule> getPLList() {
		PLList = new LinkedList<FunctionModule>();
		int pid=16;//政策法规模块id为16
		PLList=functionLimitdao.findbyroleIdPar(userrole,pid);
		return PLList;
	}
	public void setPLList(List<FunctionModule> pLList) {
		PLList = pLList;
	}
	public List<FunctionModule> getSMList() {
		SMList = new LinkedList<FunctionModule>();
		int pid=17;//系统管理模块id为17
		SMList=functionLimitdao.findbyroleIdPar(userrole,pid);
		return SMList;
	}
	public void setSMList(List<FunctionModule> sMList) {
		SMList = sMList;
	}
	public List<FunctionModule> getDUList() {
		DUList = new LinkedList<FunctionModule>();
		int pid=18;//数据字典模块id为18
		DUList=functionLimitdao.findbyroleIdPar(userrole,pid);
		return DUList;
	}
	public void setDUList(List<FunctionModule> dUList) {
		DUList = dUList;
	}
	public List<FunctionModule> getPMList() {
		PMList = new LinkedList<FunctionModule>();
		int pid=19;//个人管理模块id为19
		PMList=functionLimitdao.findbyroleIdPar(userrole,pid);
		return PMList;
	}
	public void setPMList(List<FunctionModule> pMList) {
		PMList = pMList;
	}
	public Integer getSessionUser()
	{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		user = (Users)session.getAttribute("loginUser");
		//return user.getSystemRole().getSrId();
		return user.getUserPosition();
	}
	
}
