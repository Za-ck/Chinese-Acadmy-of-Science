package com.model;

/**
 * EventFile entity. @author MyEclipse Persistence Tools
 */

public class EventFlowFile implements java.io.Serializable {

	// Fields

	private Integer id;
	private String reId;
	private String flowfileId;

	// Constructors

	/** default constructor */
	public EventFlowFile() {
	}

	/** full constructor */
	public EventFlowFile(String reId, String flowfileId) {
		this.reId = reId;
		this.flowfileId = flowfileId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReId() {
		return this.reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
	}

	public String getFlowfileId() {
		return flowfileId;
	}

	public void setFlowfileId(String flowfileId) {
		this.flowfileId = flowfileId;
	}
}