package com.form;

import java.util.ArrayList;

import com.model.ReportRiskFile;

public class RiskReportForm {

	private String reportId;
	private String taskId;
	private String reportname;
	private String annual;
	private String namelist;
	private String Idlist;
	private String leaderIds;
	private String reportremark;
	
	private ArrayList<ReportRiskFile> files;
	
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	
	public String getAnnual() {
		return annual;
	}
	public void setAnnual(String annual) {
		this.annual = annual;
	}
	public String getNamelist() {
		return namelist;
	}
	public void setNamelist(String namelist) {
		this.namelist = namelist;
	}
	public String getIdlist() {
		return Idlist;
	}
	public void setIdlist(String idlist) {
		Idlist = idlist;
	}
	public String getReportremark() {
		return reportremark;
	}
	public void setReportremark(String reportremark) {
		this.reportremark = reportremark;
	}
	public ArrayList<ReportRiskFile> getFiles() {
		return files;
	}
	public void setFiles(ArrayList<ReportRiskFile> files) {
		this.files = files;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getLeaderIds() {
		return leaderIds;
	}
	public void setLeaderIds(String leaderIds) {
		this.leaderIds = leaderIds;
	}
	
}
