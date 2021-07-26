package com.action.file;

import com.opensymphony.xwork2.ActionSupport;
import com.dao.LawFileDAO;
import com.dao.UsersDAO;
import com.model.LawFile;


import java.io.InputStream;   
import java.io.UnsupportedEncodingException;   
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;   
  
public class DownLoadAction extends ActionSupport {   
//文件名参数变量   
 private int id;
 private List<LawFile> files;
 private UsersDAO UsersDao;
 private LawFileDAO LawFileDao;
 
 
 
 
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
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public List<LawFile> getFiles() {
	return files;
}
public void setFiles(List<LawFile> files) {
	this.files = files;
}
//从下载文件原始存放路径读取得到文件输出流   


public String getDownloadChineseFileName() { 
    
    String downloadChineseFileName = this.getLawFileDao().findById(this.getId()).getFileName();
    
    try {   
	    downloadChineseFileName = new String(downloadChineseFileName.getBytes(), "iso8859-1");    

    } 
    catch (UnsupportedEncodingException e) {   
         e.printStackTrace();   
     }   
  return downloadChineseFileName;   
   }  

  public InputStream getDownloadFile() throws Exception
   {    
	  String name=this.getDownloadChineseFileName();
	  
      String downloadChineseFileName = this.getLawFileDao().findById(this.getId()).getFileName();
	   String realPath="/upload/"+downloadChineseFileName;
	   InputStream in=ServletActionContext.getServletContext().getResourceAsStream(realPath);
	   if(null==in){
		   //this.getLawFileDao().delete(this.getLawFileDao().findById(id));   
	   }
	   return ServletActionContext.getServletContext().getResourceAsStream(realPath);
   }
   

   
    public String execute() { 
    	
       return "success";   
    }   
    @SuppressWarnings("deprecation")
	public String displayFile()
    {
    	files = new LinkedList<LawFile>();
		
		files =this.getLawFileDao().findAll();
    	for (int i=0;i<files.size();i++) {
			 files.get(i).setFileName(URLDecoder.decode(files.get(i).getFileName()));
			
		}
    	
    	return "disfile";
    }
    /**  
     *  删除文件  
     *  @param  filePathAndName  String  文件路径及名称  如c:/fqf.txt  
     *  @param  fileContent  String  
     *  @return  boolean  
     */  
   public  String  delFile()  {  
       try  {  
           
           String name=this.getDownloadChineseFileName();
     	  
           String downloadChineseFileName = this.getLawFileDao().findById(this.getId()).getFileName();
     	   
     	  String  filePath ="/upload/"+downloadChineseFileName;
           filePath  =  filePath.toString();  
           java.io.File  myDelFile  =  new  java.io.File(filePath);  
           myDelFile.delete();  
           this.getLawFileDao().delete(this.getLawFileDao().findById(this.getId()));
           
 
       }  
       catch  (Exception  e)  {  
            
           e.printStackTrace();  
 
       }  
       return "success";
 
   }  

}  
