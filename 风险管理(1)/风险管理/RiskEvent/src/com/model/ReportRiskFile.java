package com.model;

/**
 * ReportRiskFile entity. @author MyEclipse Persistence Tools
 */

public class ReportRiskFile {

	// Fields

	private String fileId;			
	private String fileReportId;
	private String fileFilename;
	private String fileFilepath;
	private String fileDate;
	private String fileUploader;
	private String fileUploadername;
	
	//新增属性，创建人部门  用户新需求
	private String fileUploaderDepName;
	// Constructors

	public String getFileUploaderDepName() {
		return fileUploaderDepName;
	}

	public void setFileUploaderDepName(String fileUploaderDepName) {
		this.fileUploaderDepName = fileUploaderDepName;
	}

	/** default constructor */
	public ReportRiskFile() {
	}

	// Property accessors

	public String getFileReportId() {
		return this.fileReportId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public void setFileReportId(String fileReportId) {
		this.fileReportId = fileReportId;
	}

	public String getFileFilename() {
		return this.fileFilename;
	}

	public void setFileFilename(String fileFilename) {
		this.fileFilename = fileFilename;
	}

	public String getFileFilepath() {
		return this.fileFilepath;
	}

	public void setFileFilepath(String fileFilepath) {
		this.fileFilepath = fileFilepath;
	}

	public String getFileDate() {
		return this.fileDate;
	}
	
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public String getFileUploader() {
		return fileUploader;
	}

	public void setFileUploader(String fileUploader) {
		this.fileUploader = fileUploader;
	}

	public String getFileUploadername() {
		return fileUploadername;
	}

	public void setFileUploadername(String fileUploadername) {
		this.fileUploadername = fileUploadername;
	}
	
}