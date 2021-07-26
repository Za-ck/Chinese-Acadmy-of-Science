package com.model;

/**
 * AllStaView entity. @author MyEclipse Persistence Tools
 */

public class EventFlowFileView implements java.io.Serializable {

	private String reId;
	private String flowFileId;
	private String flowFileName;
	public String getReId() {
		return reId;
	}
	public void setReId(String reId) {
		this.reId = reId;
	}
	public String getFlowFileId() {
		return flowFileId;
	}
	public void setFlowFileId(String flowFileId) {
		this.flowFileId = flowFileId;
	}
	public String getFlowFileName() {
		return flowFileName;
	}
	public void setFlowFileName(String flowFileName) {
		this.flowFileName = flowFileName;
	}
	
}