
/*
package com.action.file;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
/**
* Action which retrieves a file specified in the parameter
* and stores its contents in the request, so that they
* can be displayed.
*/
/**
public class ShowFileAction extends Action {
/** Logging Instance. */
/*
private static final Log log = LogFactory.getLog(ShowFileAction.class);
public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
throws Exception {
// Get the file name
	/**
String fileName = mapping.getParameter();
StringBuffer fileContents = new StringBuffer();
if(fileName != null) {
InputStream input = servlet.getServletContext().getResourceAsStream(fileName);
if (input == null) {
log.warn("File Not Found: "+fileName);
} else {
InputStreamReader inputReader = new InputStreamReader(input);
char[] buffer = new char[1000];
while (true) {
int lth = inputReader.read(buffer);
if (lth < 0) {
break;
} else {
fileContents.append(buffer, 0, lth);
}
}
}

*/
package com.action.file;

import com.opensymphony.xwork2.ActionSupport;
import com.dao.LawFileDAO;
import com.dao.UsersDAO;
import com.model.LawFile;


import java.io.InputStream;   
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;   
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;   
  
public class ShowFileAction extends ActionSupport {   
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
	    downloadChineseFileName = new String(downloadChineseFileName.getBytes(), "utf-8");    

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
	   StringBuffer fileContents = new StringBuffer();
	   if(null==in){
		   //this.getLawFileDao().delete(this.getLawFileDao().findById(id));
	    //System.out.println("Can not find a java.io.InputStream with the name [inputStream] in the invocation stack. Check the <param name=\"inputName\"> tag specified for this action.检查action中文件下载路径是否正确.");   
	   }
	   else{
		   InputStreamReader inputReader = new InputStreamReader(in);
		   
		   char[] buffer = new char[1000];
		   while (true) {
		   int lth = inputReader.read(buffer);
		   if (lth < 0) {
		   break;
		   } else {
		   fileContents.append(buffer, 0, lth);
		   }
		   }
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
}  
