package com.form;

public class FlowForm {

	private String reportname;		//报告名称
	private String flowname;		//流程名称
	private String flowstate;		//流程是否完成
	private String beginner;		//流程发起人
	private String depname;			//发起人所属部门
	private String reportId;		//报告编号
	private String depId;			//发起人的部门编号
	
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	public String getFlowname() {
		return flowname;
	}
	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}
	public String getFlowstate() {
		return flowstate;
	}
	public void setFlowstate(String flowstate) {
		this.flowstate = flowstate;
	}
	public String getBeginner() {
		return beginner;
	}
	public void setBeginner(String beginner) {
		this.beginner = beginner;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	
}
