package com.model;

/**
 * ReportCheckFile entity. @author MyEclipse Persistence Tools
 */

public class ReportCheckFile {

	// Fields

	private String fileId;
	private String fileReportId;
	private String fileFilename;
	private String fileFilepath;
	private String fileDate;
	private String fileUploadername;
	private String fileUploader;
	private Integer fileSendflag;			// 1表示已经发送
	private Integer filenewflag;			// 1表示文件是新上传的
	
	private Integer fileVersion;   //根据新需求设置的版本号

	// Constructors

	public Integer getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(Integer fileVersion) {
		this.fileVersion = fileVersion;
	}

	/** default constructor */
	public ReportCheckFile() {
	}

	// Property accessors

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileReportId() {
		return this.fileReportId;
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

	public String getFileUploadername() {
		return this.fileUploadername;
	}

	public void setFileUploadername(String fileUploadername) {
		this.fileUploadername = fileUploadername;
	}

	public String getFileUploader() {
		return this.fileUploader;
	}

	public void setFileUploader(String fileUploader) {
		this.fileUploader = fileUploader;
	}

	public Integer getFileSendflag() {
		return fileSendflag;
	}

	public void setFileSendflag(Integer fileSendflag) {
		this.fileSendflag = fileSendflag;
	}

	public Integer getFilenewflag() {
		return filenewflag;
	}

	public void setFilenewflag(Integer filenewflag) {
		this.filenewflag = filenewflag;
	}

}