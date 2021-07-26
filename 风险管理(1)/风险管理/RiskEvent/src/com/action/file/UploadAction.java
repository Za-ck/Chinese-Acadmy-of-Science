package com.action.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.LawFileDAO;
import com.dao.UsersDAO;

import com.model.LawFile;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {
	private List<LawFile> reList;
	private LawFileDAO LawFileDao;
	private UsersDAO UsersDao;
	private String filetitle;
	private String fileremark;
	// username属性用来封装用户名
	private String username;

	// myFile属性用来封装上传的文件
	private File myFile;

	// myFileContentType属性用来封装上传文件的类型
	private String myFileContentType;

	// myFileFileName属性用来封装上传文件的文件名
	private String myFileFileName;

	Users users = new Users();

	LawFile lawFile = new LawFile();

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

	public UsersDAO getUsersDao() {
		return UsersDao;
	}

	public void setUsersDao(UsersDAO usersDao) {
		UsersDao = usersDao;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public LawFile getLawFile() {
		return lawFile;
	}

	public void setLawFile(LawFile lawFile) {
		this.lawFile = lawFile;
	}

	public List<LawFile> getReList() {
		return reList;
	}

	public void setReList(List<LawFile> reList) {
		this.reList = reList;
	}

	public LawFileDAO getLawFileDao() {
		return LawFileDao;
	}

	public void setLawFileDao(LawFileDAO lawFileDao) {
		LawFileDao = lawFileDao;
	}

	
	// 获得username值
	public String getUsername() {
		return username;

	}

	// 设置username值
	public void setUsername(String username) {
		this.username = username;
	}

	// 获得myFile值
	public File getMyFile() {
		return myFile;
	}

	// 设置myFile值
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	// 获得myFileContentType值
	public String getMyFileContentType() {
		return myFileContentType;
	}

	// 设置myFileContentType值
	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	// 获得myFileFileName值
	public String getMyFileFileName() {
		return myFileFileName;
	}

	// 设置myFileFileName值
	@SuppressWarnings("deprecation")
	public void setMyFileFileName(String myFileFileName) {
		// this.myFileFileName = URLEncoder.encode(myFileFileName);
		this.myFileFileName = myFileFileName;
	}

	@SuppressWarnings("deprecation")
	public String execute() throws Exception {
		// 基于myFile创建一个文件输入流
		InputStream is = new FileInputStream(myFile);
		// 设置上传文件目录
		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("/upload");
		// 设置目标文件
		File toFile = new File(uploadPath, this.getMyFileFileName());
		// 创建一个输出流
		OutputStream os = new FileOutputStream(toFile);
		// 设置缓存
		byte[] buffer = new byte[102400];
		int length = 0;
		if ((myFile.length() / 1024.0) > 102400) 
		{
			// 关闭输入流
			is.close();
			// 关闭输出流
			os.close();
			return "fail";
		} 
		else 
		{
			try {
				while ((length = is.read(buffer)) > 0) 
				{
					os.write(buffer, 0, length);
				}
				// law.setLawLevel(this.getLawLevel());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String ly_time = sdf.format(new java.util.Date());
				// String name=this.getMyFileFileName();
				lawFile.setFileName(this.getMyFileFileName()); // 得到文件名字
				lawFile.setFileDate(ly_time);// 得到文件上传时间
				lawFile.setFilePath(uploadPath);
				lawFile.setFiletitle(filetitle);
				lawFile.setFileremark(fileremark);
				Users u = (Users) ServletActionContext.getRequest()
						.getSession().getAttribute("loginUser");
				lawFile.setUsers(u);
				this.getLawFileDao().save(lawFile);
				// 关闭输入流
				is.close();
				// 关闭输出流
				os.close();
				return SUCCESS;
			} 
			catch (Exception e) {
				// 关闭输入流
				is.close();
				// 关闭输出流
				os.close();// TODO: handle exception
				return "fail";

			}
		}
	
	}

	/*public String up() {
		// 基于myFile创建一个文件输入流
		// InputStream is = new FileInputStream(myFile);
		// 设置上传文件目录
		try {
			FileInputStream fileInput = new FileInputStream(myFile);
			
			 * FileOutputStream fileOutput = new FileOutputStream(new File("D:/"
			 * + FileName));
			 
			String uploadPath = ServletActionContext.getServletContext()
					.getRealPath("/upload");
			// 设置目标文件
			File toFile = new File(uploadPath, this.getMyFileFileName());
			// 创建一个输出流
			OutputStream fileOutput = new FileOutputStream(toFile);
			byte[] b = new byte[1024];
			int length = 0;
			try {
				while ((length = fileInput.read(b)) != -1) {
					fileOutput.write(b, 0, length);
					fileOutput.flush();
				}
				fileOutput.close();
				fileInput.close();
			} catch (IOException e) {
				e.printStackTrace();
				return "fail";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "fail";
		}

		return "success";
	}*/

}
