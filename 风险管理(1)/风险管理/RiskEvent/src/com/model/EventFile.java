package com.model;

/**
 * EventFile entity. @author MyEclipse Persistence Tools
 */

public class EventFile implements java.io.Serializable {

	// Fields

	private Integer id;
	private String reId;
	private String fileId;

	// Constructors

	/** default constructor */
	public EventFile() {
	}

	/** full constructor */
	public EventFile(String reId, String fileId) {
		this.reId = reId;
		this.fileId = fileId;
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

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

}