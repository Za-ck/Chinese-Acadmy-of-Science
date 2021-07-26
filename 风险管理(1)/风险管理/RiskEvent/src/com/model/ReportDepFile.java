package com.model;

/**
 * ReportDepFile entity. @author MyEclipse Persistence Tools
 */

public class ReportDepFile {

	// Fields

	private String fileId;
	private String fileReportId;
	private String fileFilename;
	private String fileFilepath;
	private String fileDate;
	private String fileRemark;
	private String fileUploader;
	private String fileUploadername;
	private Integer fileSendflag;
	private Integer filenewflag;
	private String fileUploaderdep;
	
	private String fileUploaderDepName;
	
	private Integer fileVersion;   //根据新需求设置的版本号

	// Constructors

	public Integer getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(Integer fileVersion) {
		this.fileVersion = fileVersion;
	}

	public String getFileUploaderDepName() {
		return fileUploaderDepName;
	}

	public void setFileUploaderDepName(String fileUploaderDepName) {
		this.fileUploaderDepName = fileUploaderDepName;
	}

	/** default constructor */
	public ReportDepFile() {
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

	public String getFileRemark() {
		return this.fileRemark;
	}

	public void setFileRemark(String fileRemark) {
		this.fileRemark = fileRemark;
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

	public String getFileUploaderdep() {
		return fileUploaderdep;
	}

	public void setFileUploaderdep(String fileUploaderdep) {
		this.fileUploaderdep = fileUploaderdep;
	}
	
}