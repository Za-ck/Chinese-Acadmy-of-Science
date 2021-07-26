package com.model;

/**
 * LawFile entity. @author MyEclipse Persistence Tools
 */

public class LawFile implements java.io.Serializable {

	// Fields

	private Integer fileId;
	private Users users;
	private String fileName;
	private String filePath;
	private String fileDate;
    private String filetitle;
    private String fileremark;
	// Constructors

	/** default constructor */
    
    
    
	public LawFile() {
	}

	public String getFiletitle() {
		return filetitle;
	}

	public void setFiletitle(String filetitle) {
		this.filetitle = filetitle;
	}

	public String getFileremark() {
		return fileremark;
	}

	public void setFileremark(String fileremark) {
		this.fileremark = fileremark;
	}

	/** full constructor */
	public LawFile(Users users, String fileName, String filePath,
			String fileDate) {
		this.users = users;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileDate = fileDate;
		
	}

	// Property accessors

	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileDate() {
		return this.fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

}