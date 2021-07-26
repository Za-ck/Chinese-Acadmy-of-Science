package com.model;

/**
 * FileManage entity. @author MyEclipse Persistence Tools
 */

public class FlowFileManage {

	// Fields

	private String flowfileId;
	private String flowfileName;

	// Constructors

	/** default constructor */
	public FlowFileManage() {
	}

	
	
	public FlowFileManage(String flowfileId, String flowfileName) {
		super();
		this.flowfileId = flowfileId;
		this.flowfileName = flowfileName;
	}

	public String getFlowfileId() {
		return flowfileId;
	}

	public void setFlowfileId(String flowfileId) {
		this.flowfileId = flowfileId;
	}

	public String getFlowfileName() {
		return flowfileName;
	}

	public void setFlowfileName(String flowfileName) {
		this.flowfileName = flowfileName;
	}
}