package com.model;

/**
 * FileManage entity. @author MyEclipse Persistence Tools
 */

public class FileManage {

	// Fields

	private String fileId;
	private String fileName;
	private String fileType;

	// Constructors

	/** default constructor */
	public FileManage() {
	}

	/** full constructor */
	public FileManage(String fileId, String fileName) {
		this.fileId = fileId;
		this.fileName = fileName;
	}

	// Property accessors

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}