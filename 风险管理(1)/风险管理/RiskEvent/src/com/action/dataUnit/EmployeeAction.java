package com.action.dataUnit;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.EmployeeDAO;
import com.model.Employee;

public class EmployeeAction {
	private Integer empId;
	private String empIdString;
    private String empLevel;
    private String empDetail;
    private List<Employee> empList;
    private List<Integer> idCheck;
    private String updateFlag;
    private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
    private EmployeeDAO employeeDao;
    Employee employee=new Employee();
    
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
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpIdString() {
		return empIdString;
	}
	public void setEmpIdString(String empIdString) {
		this.empIdString = empIdString;
	}
	public String getEmpLevel() {
		return empLevel;
	}
	public void setEmpLevel(String empLevel) {
		this.empLevel = empLevel;
	}
	public String getEmpDetail() {
		return empDetail;
	}
	public void setEmpDetail(String empDetail) {
		this.empDetail = empDetail;
	}
	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
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
	public EmployeeDAO getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
    
	// 显示针对员工满意度的影响评定等级信息,得到最新empList,用于在Employee.jsp表单中显示
	public String empManage() {
		this.empList = new LinkedList<Employee>();
		this.empList = this.getEmployeeDao().findAll((current_pagenum-1)*pageNum,pageNum);
		ServletActionContext.getRequest().setAttribute("cliList", empList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	
		if (empList.size() > 0)
			return "success";
		else
			return "fail";
	}
	
	// 新增|编辑
	public String empAddUpdate() {
		if(this.getUpdateFlag().equals("update")){	
			//employee=this.getEmployeeDao().findById(this.getEmpId());
			employee.setEmpId(Integer.parseInt(this.getEmpIdString()));
			employee.setEmpLevel(this.getEmpLevel());
			employee.setEmpDetail(this.getEmpDetail());
			this.getEmployeeDao().merge(employee);
			empManage();// 得到最新empList,用于在Employee.jsp表单中显示
			return "success";
		}
		else{
			if(employeeDao.findById(Integer.parseInt(this.getEmpIdString()))!=null)return "fail";	
			employee.setEmpId(Integer.parseInt(this.getEmpIdString()));
			employee.setEmpLevel(this.getEmpLevel());
			employee.setEmpDetail(this.getEmpDetail());
			this.getEmployeeDao().save(employee);
			empManage();// 得到最新empList,用于在Employee.jsp表单中显示
			return "success";
		}
	}	

	// 批量删除
	public String empMultiDel() {
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			this.setEmpId(this.idCheck.get(i));
			employee=this.getEmployeeDao().findById(this.getEmpId());
			this.getEmployeeDao().delete(employee);
		}
		empManage();// 得到最新empList,用于在Employee.jsp表单中显示
		return "success";
	}

	// 删除
	public String empDelete() {
		employee=this.getEmployeeDao().findById(this.getEmpId());
		this.getEmployeeDao().delete(employee);
		empManage();// 得到最新empList,用于在Employee.jsp表单中显示
		return "success";
	}

	// 查看
	public String empRead() {
		try {
			employee=this.getEmployeeDao().findById(this.getEmpId());
			this.empLevel=employee.getEmpLevel();
			this.empDetail=employee.getEmpDetail();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 编辑前显示要编辑的对象的信息
	public String empUpdatePrepare() {
		try{
			employee=this.getEmployeeDao().findById(this.getEmpId());
			this.empLevel=employee.getEmpLevel();
			this.empDetail=employee.getEmpDetail();
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
}
