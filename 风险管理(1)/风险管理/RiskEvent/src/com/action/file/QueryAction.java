package com.action.file;

import java.io.File;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.LawFileDAO;
import com.dao.UsersDAO;

import com.model.LawFile;
import com.model.Users;

public class QueryAction {
	private Integer fileId;
	private Users users;
	private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
	private String filePath;
	private String fileDate;
	private String fileTitle;
	private List<LawFile> queryList;
	private LawFileDAO LawFileDao;
	private UsersDAO UsersDao;
	Users us=new Users();
	
	public int getCurrent_pagenum() {
		return current_pagenum;
	}
	public void setCurrent_pagenum(int currentPagenum) {
		current_pagenum = currentPagenum;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public List<LawFile> getQueryList() {
		return queryList;
	}
	public void setQueryList(List<LawFile> queryList) {
		this.queryList = queryList;
	}
	public LawFileDAO getLawFileDao() {
		return LawFileDao;
	}
	public void setLawFileDao(LawFileDAO lawFileDao) {
		LawFileDao = lawFileDao;
	}
	public UsersDAO getUsersDao() {
		return UsersDao;
	}
	public void setUsersDao(UsersDAO usersDao) {
		UsersDao = usersDao;
	}
	public Users getUs() {
		return us;
	}
	public void setUs(Users us) {
		this.us = us;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	public String getFileTitle() {
		return fileTitle;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	/*
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	*/
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileDate() {
		return fileDate;
	}
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public String lawManage() {
		queryList = new LinkedList<LawFile>();	
		queryList =this.getLawFileDao().findAll((current_pagenum-1)*pageNum,pageNum);		
		ServletActionContext.getRequest().setAttribute("queryList", queryList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
		for (int i=0;i<queryList.size();i++) {
			queryList.get(i).setFileName(URLDecoder.decode(queryList.get(i).getFileName()));
		}
		if (queryList.size() > 0)
			return "success";
		else
			return "fail";
	}
	
	public String findByfileTitle() {
		queryList = new LinkedList<LawFile>();	
		queryList =this.getLawFileDao().findByfileTitle((current_pagenum-1)*pageNum,pageNum,this.fileTitle);
			return "success";
		
	}
}
