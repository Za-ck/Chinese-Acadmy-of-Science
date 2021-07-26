package com.action.file;

import java.io.File;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.LawFileDAO;
import com.dao.UsersDAO;

import com.model.LawFile;
import com.model.Users;

public class DeleteAction {
	private Integer fileId;
	private Users users;
	private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
	private String filePath;
	private String fileDate;
	private List<LawFile> queryList;
	private LawFileDAO LawFileDao;
	private UsersDAO UsersDao;
	Users us=new Users();
	private List<Integer> idCheck;
	
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
	
	public List<Integer> getIdCheck() {
		return idCheck;
	}
	public void setIdCheck(List<Integer> idCheck) {
		this.idCheck = idCheck;
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
	
	public String lawDelete() {		
		
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
		LawFile file=new LawFile();
		file=this.getLawFileDao().findById(this.idCheck.get(i));
		String filepath=file.getFilePath();
		String filename=file.getFileName();
		this.LawFileDao.delete(file);
		
		File nowFile =new File(filepath+"/"+filename);
		nowFile .delete();
	}
		lawDeleteshow();
			return "success";
	}
	
	public String lawDeleteshow() {
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
}
