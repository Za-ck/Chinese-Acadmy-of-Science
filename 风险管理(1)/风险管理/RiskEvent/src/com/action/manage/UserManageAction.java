package com.action.manage;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.DepartmentDAO;
import com.dao.SystemRoleDAO;
import com.dao.UserMapDAO;
import com.dao.UsersDAO;
import com.dao.UsersQueryViewDAO;
import com.model.Department;
import com.model.SystemRole;
import com.model.UserMap;
import com.model.Users;
import com.model.UsersQueryView;

public class UserManageAction {
	private String userId;
	private String userName;
	private String userPassword;
	private String userSex;
	private Integer userPosition;
	private String userIdcard;
	private String userTel;
	private String userCellphone;
	private String userEmail;
	private String depId;
	private String publishId;
    private List<UsersQueryView> useList;
    private List<String> idCheck;
    private String updateFlag;
    private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
    private String orderbys="";//department.depQueue asc,systemRole.srId asc,userId asc,userName asc
    private UsersQueryViewDAO usersQueryViewDao;
    private DepartmentDAO departmentDao;
    private SystemRoleDAO roleDao;
    private UsersDAO usersDao;
    private UserMapDAO userMapDao;
    Users users=new Users();
    Department department=new Department();
    private List<Department> alldepList;
    private List<SystemRole> allroleList;
    private SystemRole systemRole;
	private List<Department> depList;
	private List<SystemRole> roleList;
    private String actionName="systemManage/UserManageAction";
    private String updownflag="";
    private String updownid="";
    //private String orderbys="";
    private String usernameString;
    private String userIdString;
    private String depname;
    private String reIndep;
	private List<String> alluserstateList;
	private String userState="已启用";
    
	public String getReIndep() {
		return reIndep;
	}
	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}
	public SystemRole getSystemRole() {
		return systemRole;
	}
	public void setSystemRole(SystemRole systemRole) {
		this.systemRole = systemRole;
	}
	public List<SystemRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<SystemRole> roleList) {
		this.roleList = roleList;
	}
	public String getUsernameString() {
		return usernameString;
	}
	public void setUsernameString(String usernameString) {
		this.usernameString = usernameString;
	}
	public String getUserIdString() {
		return userIdString;
	}
	public void setUserIdString(String userIdString) {
		this.userIdString = userIdString;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}

	public String getOrderbys() {
		return orderbys;
	}
	public List<Department> getDepList() {
		return depList;
	}
	public void setDepList(List<Department> depList) {
		this.depList = depList;
	}
	public void setOrderbys(String orderbys) {
		this.orderbys = orderbys;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public Integer getUserPosition() {
		return userPosition;
	}
	public void setUserPosition(Integer userPosition) {
		this.userPosition = userPosition;
	}
	public String getUserIdcard() {
		return userIdcard;
	}
	public void setUserIdcard(String userIdcard) {
		this.userIdcard = userIdcard;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
		
	public List<UsersQueryView> getUseList() {
		return useList;
	}
	public void setUseList(List<UsersQueryView> useList) {
		this.useList = useList;
	}
	public List<String> getIdCheck() {
		return idCheck;
	}
	public void setIdCheck(List<String> idCheck) {
		this.idCheck = idCheck;
	}
	public String getUpdateFlag() {
		return updateFlag;
	}
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	
	public UsersQueryViewDAO getUsersQueryViewDao() {
		return usersQueryViewDao;
	}
	public void setUsersQueryViewDao(UsersQueryViewDAO usersQueryViewDao) {
		this.usersQueryViewDao = usersQueryViewDao;
	}
	
	public UsersDAO getUsersDao() {
		return usersDao;
	}
	public void setUsersDao(UsersDAO usersDao) {
		this.usersDao = usersDao;
	}
	
	public UserMapDAO getUserMapDao() {
		return userMapDao;
	}
	public void setUserMapDao(UserMapDAO userMapDao) {
		this.userMapDao = userMapDao;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}	
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}	
	
	public String getPublishId() {
		return publishId;
	}
	public void setPublishId(String publishId) {
		this.publishId = publishId;
	}
	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}
	public List<Department> getAlldepList() {
		return alldepList;
	}
	public void setAlldepList(List<Department> alldepList) {
		this.alldepList = alldepList;
	}
	public String getUserCellphone() {
		return userCellphone;
	}
	public void setUserCellphone(String userCellphone) {
		this.userCellphone = userCellphone;
	}
	public SystemRoleDAO getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(SystemRoleDAO roleDao) {
		this.roleDao = roleDao;
	}
	public List<SystemRole> getAllroleList() {
		return allroleList;
	}
	public void setAllroleList(List<SystemRole> allroleList) {
		this.allroleList = allroleList;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getUpdownflag() {
		return updownflag;
	}
	public void setUpdownflag(String updownflag) {
		this.updownflag = updownflag;
	}
	public String getUpdownid() {
		return updownid;
	}
	public void setUpdownid(String updownid) {
		this.updownid = updownid;
	}
	public List<String> getAlluserstateList() {
		return alluserstateList;
	}
	public void setAlluserstateList(List<String> alluserstateList) {
		this.alluserstateList = alluserstateList;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	// 显示用户信息,得到最新useList,用于在UserManage.jsp表单中显示
	@SuppressWarnings("unchecked")
	public String umManage() {	
		setReIndep(null);
		alluserstateList = new LinkedList<String>();
		alluserstateList.add("--请选择--");
		alluserstateList.add("已启用");
		alluserstateList.add("未启用");
		
		this.useList = new LinkedList<UsersQueryView>();
		String state = "";
		if("--请选择--".equals(userState)){
			state = "";
		}else if("已启用".equals(userState)){
			state = " where userRname='1'";
		}else{
			state = " where userRname='0'";
		}
		this.useList = this.getUsersQueryViewDao().findAll((current_pagenum-1)*pageNum,pageNum,this.orderbys,state);	
		ServletActionContext.getRequest().setAttribute("useList", useList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
		this.setActionName("systemManage/UserManageAction");
		//部门下拉列表显示
		Department dep1=new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		alldepList=new LinkedList<Department>();
		alldepList.add(dep1);
		depList=new LinkedList<Department>();
		depList=this.getDepartmentDao().findAll();
		alldepList.addAll(depList);	
		if (useList.size() > 0)
			return "success";
		else
			return "fail";
	}
	//2019，新增修改后重定位,所有调用该函数的函数都被修改过
	public String umManage1(String dep) {
		System.out.print("vvvvvvvv"+dep);
		setReIndep(null);
		alluserstateList = new LinkedList<String>();
		alluserstateList.add("--请选择--");
		alluserstateList.add("已启用");
		alluserstateList.add("未启用");
		
		this.useList = new LinkedList<UsersQueryView>();
		String state = "";
		if("--请选择--".equals(userState)){
			state = "";
		}else if("已启用".equals(userState)){
			state = " where userRname='1' and Dep_Id='"+dep+"'";
		}else{
			state = " where userRname='0' and Dep_Id='"+dep+"'";
		}
		this.useList = this.getUsersQueryViewDao().findAll1((current_pagenum-1)*pageNum,pageNum,this.orderbys,state);	
		ServletActionContext.getRequest().setAttribute("useList", useList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
		this.setActionName("systemManage/UserManageAction");
		//部门下拉列表显示
		Department dep1=new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		alldepList=new LinkedList<Department>();
		alldepList.add(dep1);
		depList=new LinkedList<Department>();
		depList=this.getDepartmentDao().findAll();
		alldepList.addAll(depList);	
		if (useList.size() > 0)
			return "success";
		else
			return "fail";
	}
	//查询无效用户
	@SuppressWarnings("unchecked")
	public String umQueryDeleted()
	{
		//部门下拉列表显示
		Department dep1=new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		alldepList=new LinkedList<Department>();
		alldepList.add(dep1);
		depList=new LinkedList<Department>();
		depList=this.getDepartmentDao().findAll();
		alldepList.addAll(depList);	
		
		String userRname="0";//表示无效用户
		this.useList=new LinkedList<UsersQueryView>();
		this.useList = this.getUsersQueryViewDao().reqQueryNew(reIndep, usernameString, userIdString, userRname, (current_pagenum-1)*pageNum, pageNum);
		ServletActionContext.getRequest().setAttribute("useList", useList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
		this.setActionName("systemManage/UMQueryDeletedAction");
		if (useList.size() > 0)
			return "success";
		else
			return "fail";
	}
	@SuppressWarnings("unchecked")
	public String allrequery() {		
		//部门下拉列表显示
		Department dep1=new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		alldepList=new LinkedList<Department>();
		alldepList.add(dep1);
		depList=new LinkedList<Department>();
		depList=this.getDepartmentDao().findAll();
		alldepList.addAll(depList);	
		
		//String userRname="1";//表示有效用户
		this.useList=new LinkedList<UsersQueryView>();
		this.useList = this.getUsersQueryViewDao().reqQueryNew(reIndep, usernameString, userIdString,this.userState,(current_pagenum-1)*pageNum, pageNum);
		ServletActionContext.getRequest().setAttribute("useList", useList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
		this.setActionName("systemManage/UserManageAction");
		if (useList.size() > 0)
			return "success";
		else
			return "fail";
	}
	
	// 新增|编辑
	public String umAddUpdate() {
		if(this.getUpdateFlag().equals("update")){	
			
			//department=this.getDepartmentDao().findById(this.getDepId());
			users.setUserId(this.getUserId());
			users.setUserName(this.getUserName());
			users.setUserRname("1");
			//密码为原来密码
			users.setUserPassword(this.getUserPassword());
			users.setUserSex(this.getUserSex());
			//users.setDepartment(this.getDepartment());
			users.setUserDep(this.getDepId());
//			SystemRole role = new SystemRole();
//			role.setSrId(this.getUserPosition());
			users.setUserPosition(this.getUserPosition());
			//users.setSystemRole(role);
			users.setUserIdcard(this.getUserIdcard());
			users.setUserTel(this.getUserTel());
			users.setUserCellphone(this.getUserCellphone());
			users.setUserEmail(this.getUserEmail());
			
			if(this.getDepId().equals("FB")) {
				
				users.setUserPosition(12);			//风险发布人
				UserMap um = (UserMap) this.getUserMapDao().findByProperty("umMapUser", this.getUserId()).get(0);
				if(um == null) {
					um = new UserMap();
				}
				um.setUmUser(this.getPublishId());
				um.setUmMapUser(this.getUserId());
				this.getUserMapDao().merge(um);
			}
			
			this.getUsersDao().merge(users);
			//2019
			//umManage();// 得到最新useList,用于在UserManage.jsp表单中显示liu
			System.out.println("nnnnnnnnnnnn"+this.getUserId());
			umUpdatePrepare1(this.getUserId());
			return "success";
		}
		else{
			if(usersDao.findById(this.getUserId())!=null)
			{
				umAddPrepare();
				return "fail";
			}
			//department=this.getDepartmentDao().findById(this.getDepId());
			users.setUserId(this.getUserId());
			users.setUserName(this.getUserName());
			users.setUserRname("1");
			//新用户，密码初始化为用户登录号
			users.setUserPassword(this.getUserId());
			users.setUserSex(this.getUserSex());
			//users.setDepartment(this.getDepartment());
			users.setUserDep(this.getDepId());
//			SystemRole role = new SystemRole();
//			role.setSrId(this.getUserPosition());
			users.setUserPosition(this.getUserPosition());
			//users.setSystemRole(role);
			users.setUserIdcard(this.getUserIdcard());
			users.setUserTel(this.getUserTel());
			users.setUserCellphone(this.getUserCellphone());
			users.setUserEmail(this.getUserEmail());
			if(this.getDepId().equals("FB")) {
				
				users.setUserPosition(12);			//风险发布人
				UserMap um = new UserMap();
				um.setUmUser(this.getPublishId());
				um.setUmMapUser(this.getUserId());
				this.getUserMapDao().save(um);
			}
			this.getUsersDao().save(users);
			//umManage();// 得到最新useList,用于在UserManage.jsp表单中显示liu
			//2019
			System.out.println("nnnnnnnnnnnn"+this.getUserId());
			umUpdatePrepare1(this.getUserId());
			return "success";
		}
	}	

	// 批量删除
	public String umMultiDel() {
		String dep=null;
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			users=this.getUsersDao().findById(this.idCheck.get(i));
			dep=users.getUserDep();
			users.setUserRname("0");//把该用户置为无效用户
			this.getUsersDao().merge(users);
		}
		//2019umManage();// 得到最新useList,用于在UserManage.jsp表单中显示
		umManage1(dep);
		return "success";
	}
	// 启用无效用户，批量启用
	public String umMultiEnable() 
	{
		int i = 0;
		String dep=null;
		for (i = 0; i < this.idCheck.size(); i++) {
			users=this.getUsersDao().findById(this.idCheck.get(i));
			dep=users.getUserDep();
			users.setUserRname("1");//把该用户置为无效用户
			this.getUsersDao().merge(users);
		}
		//2019umManage();// 得到最新useList,用于在UserManage.jsp表单中显示
		umManage1(dep);
		return "success";
	}

	// 删除，该函数未被调用
	public String umDelete() {
		users=this.getUsersDao().findById(this.getUserId());
		String dep=users.getUserDep();
		users.setUserRname("0");//把该用户置为无效用户
		this.getUsersDao().merge(users);
		//2019umManage();// 得到最新useList,用于在UserManage.jsp表单中显示
		umManage1(dep);
		return "success";
	}

	// 编辑前显示要编辑的对象的信息
	public String umUpdatePrepare() {
		
		alldepList=new LinkedList<Department>();
		alldepList=this.getDepartmentDao().findAll();
		allroleList=new LinkedList<SystemRole>();
		allroleList=this.roleDao.findAll();
		try{
			users=this.getUsersDao().findById(this.getUserId());
			this.userId=users.getUserId();
			this.userName=users.getUserName();
			this.userPassword=users.getUserPassword();
			this.userSex=users.getUserSex();
			this.depId=users.getUserDep();
			this.userPosition=users.getUserPosition();
			this.userIdcard=users.getUserIdcard();
			this.userTel=users.getUserTel();
			this.userCellphone=users.getUserCellphone();
			this.userEmail=users.getUserEmail();
			if(users.getUserDep().equals("FB")) {
				
				UserMap um = (UserMap) this.getUserMapDao().findByProperty("umMapUser", users.getUserId()).get(0);
				this.publishId = um.getUmUser();
				
			}
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	//liu
	public String umUpdatePrepare1(String UserId) {
		
		alldepList=new LinkedList<Department>();
		alldepList=this.getDepartmentDao().findAll();
		allroleList=new LinkedList<SystemRole>();
		allroleList=this.roleDao.findAll();
		try{
			users=this.getUsersDao().findById(UserId);
			this.userId=users.getUserId();
			this.userName=users.getUserName();
			this.userPassword=users.getUserPassword();
			this.userSex=users.getUserSex();
			this.depId=users.getUserDep();
			this.userPosition=users.getUserPosition();
			this.userIdcard=users.getUserIdcard();
			this.userTel=users.getUserTel();
			this.userCellphone=users.getUserCellphone();
			this.userEmail=users.getUserEmail();
			if(users.getUserDep().equals("FB")) {
				
				UserMap um = (UserMap) this.getUserMapDao().findByProperty("umMapUser",UserId).get(0);
				this.publishId = um.getUmUser();
				
			}
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	// 新增时需要显示的信息
	public String umAddPrepare() {
		
		alldepList=new LinkedList<Department>();
		alldepList=this.getDepartmentDao().findAll();//新增时需要显示的部门信息
		allroleList=new LinkedList<SystemRole>();
		allroleList=this.roleDao.findAll();//新增时需要显示的角色信息
		return "success";
	}
	// 密码初始化
	public String umReset() {
		String dep=null;
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			users=this.getUsersDao().findById(this.idCheck.get(i));
			dep=users.getUserDep();
			users.setUserPassword(users.getUserId());
			this.getUsersDao().merge(users);
		}
		//2019umManage();// 得到最新useList,用于在UserManage.jsp表单中显示
		umManage1(dep);
		return "success";
	}
	//返回2019.liu
	public String uUpdateBack()
	{
		System.out.print("+++++++"+this.getDepId());
		umManage1(this.getDepId());
		return "success";
	}

}
