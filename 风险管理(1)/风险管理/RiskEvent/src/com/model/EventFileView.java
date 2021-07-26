package com.model;

/**
 * AllStaView entity. @author MyEclipse Persistence Tools
 */

public class EventFileView implements java.io.Serializable {

	private String reId;
	private String fileId;
	private String filename;
	private String filetype;
	public String getReId() {
		return reId;
	}
	public void setReId(String reId) {
		this.reId = reId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	

	
}