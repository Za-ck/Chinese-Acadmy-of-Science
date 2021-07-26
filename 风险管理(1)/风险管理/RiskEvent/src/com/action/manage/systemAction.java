package com.action.manage;

import java.util.LinkedList;
import java.util.List;


import org.apache.struts2.ServletActionContext;

import com.dao.FunctionLimitDAO;
import com.dao.FunctionModuleDAO;
import com.dao.SystemRoleDAO;
import com.model.FunctionLimit;
import com.model.FunctionModule;
import com.model.SystemRole;
public class systemAction {
	private List<SystemRole> roleList;
	private List<FunctionModule> moduleList;
	SystemRole role=new SystemRole();
	private SystemRoleDAO systemRoledao;
	private FunctionModuleDAO functionModuledao;
	private FunctionLimitDAO functionLimitdao;
	private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
    private String orderbys="";
    private static Integer srId;
    private String roleName;
    private String roleType;
    private List<String> idCheck;
    private static String updateFlag;
    private String systemRole;
    private List<SystemRole> queryList;
    
    
    
	public String getSystemRole() {
		return systemRole;
	}

	public void setSystemRole(String systemRole) {
		this.systemRole = systemRole;
	}

	public List<SystemRole> getQueryList() {
		SystemRole w1=new SystemRole();
		w1.setSrId(-1);
		w1.setSrName("--请选择--");
		queryList=new LinkedList<SystemRole>();
		queryList.add(w1);
		List<SystemRole> myList =new LinkedList<SystemRole>();
		myList=this.getSystemRoledao().findAll();
		queryList.addAll(myList);
		return queryList;
	}

	public void setQueryList(List<SystemRole> queryList) {
		this.queryList = queryList;
	}

	public String getOrderbys() {
		return orderbys;
	}

	public void setOrderbys(String orderbys) {
		this.orderbys = orderbys;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public  Integer getSrId() {
		return srId;
	}

	public  void setSrId(Integer srId) {
		systemAction.srId = srId;
	}

	public  String getUpdateFlag() {
		return updateFlag;
	}

	public  void setUpdateFlag(String updateFlag) {
		systemAction.updateFlag = updateFlag;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<String> getIdCheck() {
		return idCheck;
	}

	public void setIdCheck(List<String> idCheck) {
		this.idCheck = idCheck;
	}

	public FunctionModuleDAO getFunctionModuledao() {
		return functionModuledao;
	}

	public void setFunctionModuledao(FunctionModuleDAO functionModuledao) {
		this.functionModuledao = functionModuledao;
	}

	public FunctionLimitDAO getFunctionLimitdao() {
		return functionLimitdao;
	}

	public void setFunctionLimitdao(FunctionLimitDAO functionLimitdao) {
		this.functionLimitdao = functionLimitdao;
	}

	public List<SystemRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SystemRole> roleList) {
		this.roleList = roleList;
	}

	public SystemRole getRole() {
		return role;
	}

	public void setRole(SystemRole role) {
		this.role = role;
	}

	public SystemRoleDAO getSystemRoledao() {
		return systemRoledao;
	}

	public void setSystemRoledao(SystemRoleDAO systemRoledao) {
		this.systemRoledao = systemRoledao;
	}

	public List<FunctionModule> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<FunctionModule> moduleList) {
		this.moduleList = moduleList;
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
	// 显示角色信息
	@SuppressWarnings("unchecked")
	public String role(){
		getQueryList();//显示角色名称下拉列表
		roleList = new LinkedList<SystemRole>();
		roleList=systemRoledao.findAll();
		if(roleList==null){return "fail";}//如果roleList不存在，则直接退出
		else {
				  
			return "success";
		}
	}
	// 显示角色信息
	@SuppressWarnings("unchecked")
	public String roleQuery() {
		setSystemRole(null);
		getQueryList();//显示角色名称下拉列表
		roleList = new LinkedList<SystemRole>();
		roleList=systemRoledao.findAll((current_pagenum-1)*pageNum,pageNum,this.orderbys);
		if(roleList==null){return "fail";}//如果roleList不存在，则直接退出
		else {
			ServletActionContext.getRequest().setAttribute("roleList", roleList);   
			ServletActionContext.getRequest().setAttribute("orderbys", orderbys);
			ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
			return "success";
		}
	}
	public String juesechanxun(){
		getQueryList();//显示角色名称下拉列表
		roleList = new LinkedList<SystemRole>();
		roleList=systemRoledao.jueseQuery(systemRole,(current_pagenum-1)*pageNum, pageNum, this.orderbys);
		if(roleList==null){return "fail";}//如果roleList不存在，则直接退出
		else {
			ServletActionContext.getRequest().setAttribute("roleList", roleList);   
			ServletActionContext.getRequest().setAttribute("orderbys", orderbys);
			ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
			return "success";
		}
	}
	//2019
	public String juesechanxun1(String srole){
		getQueryList();//显示角色名称下拉列表
		roleList = new LinkedList<SystemRole>();
		roleList=systemRoledao.jueseQuery(srole,(current_pagenum-1)*pageNum, pageNum, this.orderbys);
		if(roleList==null){return "fail";}//如果roleList不存在，则直接退出
		else {
			ServletActionContext.getRequest().setAttribute("roleList", roleList);   
			ServletActionContext.getRequest().setAttribute("orderbys", orderbys);
			ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
			return "success";
		}
	}
	// 显示角色的权限信息
	public String limitQuery() 
	{
		
		moduleList = new LinkedList<FunctionModule>();
		moduleList=functionModuledao.findAll();
		if(updateFlag.equals("addnew")) {
			this.roleName="";
			this.roleType="";
			 for(int i=0;i<moduleList.size();i++){
			 moduleList.get(i).setState("false");
			 }			 
			 ServletActionContext.getRequest().setAttribute("moduleList", moduleList);       
			return "success";
			
		}
		if(moduleList==null){return "fail";}//如果roleList不存在，则直接退出
		else {
			 List<FunctionLimit> userfunctions = new LinkedList<FunctionLimit>();
			 SystemRole role=  systemRoledao.findById(this.srId);
			 this.roleName = role.getSrName();
			 this.roleType = role.getSrreid();
			 userfunctions =functionLimitdao.findbyroleId(this.srId);						 
			 for(int i=0;i<moduleList.size();i++){
				 for(int j=0;j<userfunctions.size();j++)
					 if(moduleList.get(i).getFmId().equals(userfunctions.get(j).getFunctionModule().getFmId())) moduleList.get(i).setState("true");
			 }			 
			 ServletActionContext.getRequest().setAttribute("moduleList", moduleList);        
			return "success";
		}
	}	
	
	// 提交角色操作
	public String submitRole(){
		SystemRole sr = new SystemRole();
		if(!this.updateFlag.equals("addnew"))sr.setSrId(srId);
		sr.setSrName(this.roleName);
		sr.setSrreid(this.roleType);
		systemRoledao.saveOrUpdate(sr);
		functionLimitdao.deletebyroleId(sr.getSrId());
		if(null == this.idCheck)
			{
			//2019roleQuery();
			juesechanxun1(this.roleName);
			return "fail";
			}
		for(int i=0;i<this.idCheck.size();i++){
			FunctionLimit limits = new FunctionLimit();
			FunctionModule fm= new FunctionModule();		
			fm.setFmId(Integer.valueOf(this.idCheck.get(i)));		
			limits.setFunctionModule(fm);
			limits.setSystemRole(sr);		
			functionLimitdao.save(limits);
			
		}
		//2019roleQuery();
		juesechanxun1(this.roleName);
		return "success";
	}
	
	// 批量删除角色
	public String roleMultiDel() {
		
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			this.setSrId(Integer.parseInt(this.idCheck.get(i)));
			functionLimitdao.deletebyroleId(this.getSrId());
			role=this.getSystemRoledao().findById(this.getSrId());
			this.systemRoledao.delete(role);
		}
		roleQuery();// 得到最新cliList,用于在Client.jsp表单中显示
		return "success";
	}
	
}	


