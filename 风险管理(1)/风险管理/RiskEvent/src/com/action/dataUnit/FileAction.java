package com.action.dataUnit;

import java.net.URLDecoder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.FileManageDAO;
import com.dao.RiskDAO;
import com.dao.RiskFileDAO;
import com.model.FileManage;
import com.model.Risk;
import com.model.RiskFile;
import com.model.RiskType;

public class FileAction {
	private String fileId;
	private String fileName;
	private String fileType;
	private List<FileManage> filList;
	private List<String> idCheck;
	private List<Risk> allriskList;
	private List<Risk> riskList;
	private String updateFlag;
	private String noneFlag;
	private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
    private String fileNameString;
	
	private FileManageDAO fileManageDao;
	FileManage fileManage=new FileManage();
	
	
	private List<String> riskId;
	private List<Risk> risList;
	private RiskDAO riskDao;
	Risk risk=new Risk();
	
	private List<RiskFile> rfList;
	private RiskFileDAO riskFileDao;
	RiskFile riskFile=new RiskFile();
	private Integer rfId;
	
	private String actionName="dataUnit/FileAction";
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<FileManage> getFilList() {
		return filList;
	}
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setFilList(List<FileManage> filList) {
		this.filList = filList;
	}

	public List<String> getIdCheck() {
		return idCheck;
	}

	public void setIdCheck(List<String> idCheck) {
		this.idCheck = idCheck;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public FileManageDAO getFileManageDao() {
		return fileManageDao;
	}

	public void setFileManageDao(FileManageDAO fileManageDao) {
		this.fileManageDao = fileManageDao;
	}

	public FileManage getFileManage() {
		return fileManage;
	}

	public void setFileManage(FileManage fileManage) {
		this.fileManage = fileManage;
	}

	public List<String> getRiskId() {
		return riskId;
	}

	public void setRiskId(List<String> riskId) {
		this.riskId = riskId;
	}

	public List<Risk> getRisList() {
		return risList;
	}

	public void setRisList(List<Risk> risList) {
		this.risList = risList;
	}

	public RiskDAO getRiskDao() {
		return riskDao;
	}

	public void setRiskDao(RiskDAO riskDao) {
		this.riskDao = riskDao;
	}

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}

	public List<RiskFile> getRfList() {
		return rfList;
	}

	public void setRfList(List<RiskFile> rfList) {
		this.rfList = rfList;
	}

	public RiskFileDAO getRiskFileDao() {
		return riskFileDao;
	}

	public void setRiskFileDao(RiskFileDAO riskFileDao) {
		this.riskFileDao = riskFileDao;
	}

	public RiskFile getRiskFile() {
		return riskFile;
	}

	public void setRiskFile(RiskFile riskFile) {
		this.riskFile = riskFile;
	}

	public Integer getRfId() {
		return rfId;
	}

	public void setRfId(Integer rfId) {
		this.rfId = rfId;
	}	
	
	public String getNoneFlag() {
		return noneFlag;
	}

	public void setNoneFlag(String noneFlag) {
		this.noneFlag = noneFlag;
	}	
	public List<Risk> getAllriskList() {
		return allriskList;
	}

	public void setAllriskList(List<Risk> allriskList) {
		this.allriskList = allriskList;
	}
	
	public List<Risk> getRiskList() {
		return riskList;
	}

	public void setRiskList(List<Risk> riskList) {
		this.riskList = riskList;
	}
	
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

	public String getFileNameString() {
		return fileNameString;
	}

	public void setFileNameString(String fileNameString) {
		this.fileNameString = fileNameString;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	// 显示管理规定文件的信息,得到最新filList,用于在File.jsp表单中显示
	@SuppressWarnings("unchecked")
	public String filManage() {
		this.filList = new LinkedList<FileManage>();
		this.filList = this.getFileManageDao().findAll((current_pagenum-1)*pageNum,pageNum);
		ServletActionContext.getRequest().setAttribute("filList", filList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
		if (filList.size() > 0)
			return "success";
		else
			return "fail";
	}
	
	// 新增|编辑
	public String filAddUpdate() {
		
		if(this.getUpdateFlag().equals("update")){
			//以下完成编辑后file的保存
			fileManage.setFileId(this.getFileId());
			fileManage.setFileName(this.getFileName());
			fileManage.setFileType(this.getFileType());
			this.getFileManageDao().merge(fileManage);
			//以下完成编辑后riskFile的保存
			//System.out.println(this.getFileId());
			this.rfList=new LinkedList<RiskFile>();
			this.rfList=this.getRiskFileDao().findByProperty("fileId", this.getFileId());//注意是否有错
			Iterator it=this.rfList.iterator();
			while(it.hasNext()){
				riskFile=(RiskFile) it.next();
				this.getRiskFileDao().delete(riskFile);
			}//完成原riskFile的删除
			
			if(this.getNoneFlag().equals("1")){
				riskFile.setFileId(this.getFileId());
				riskFile.setRiskId(null);
				this.getRiskFileDao().save(riskFile);
			}else{
				    for (int i = 0; i < this.getRiskId().size(); i++) {					
					//System.out.println(this.getFileId()+this.getRiskId().get(i));
					riskFile.setFileId(this.getFileId().trim());
					riskFile.setRiskId(this.getRiskId().get(i));
					this.getRiskFileDao().save(riskFile);
				}		
			}//完成新编辑riskFile的保存
			
			filManage();// 得到最新filList,用于在File.jsp表单中显示
			return "success";
		}
		else{
			//以下file的保存
			if(fileManageDao.findById(this.getFileId())!=null){
				// 得到下拉框，风险类型下拉框allriskList
				getDropDownList();
				return "fail";
			}
			fileManage.setFileId(this.getFileId());
			fileManage.setFileName(this.getFileName());
			fileManage.setFileType(this.getFileType());
			this.getFileManageDao().save(fileManage);
			//以下riskFile的保存
			if(null==this.getRiskId()){
				riskFile.setFileId(this.getFileId());
				riskFile.setRiskId(null);
				this.getRiskFileDao().save(riskFile);
			}else{
				for (int i = 0; i < this.getRiskId().size(); i++) {
					riskFile.setFileId(this.getFileId());
					riskFile.setRiskId(this.getRiskId().get(i));
					this.getRiskFileDao().save(riskFile);
				}		
			}
				
			filManage();// 得到最新filList,用于在File.jsp表单中显示
			return "success";
		}
	}

	// 批量删除
	public String filMultiDel() {
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			this.setFileId(this.idCheck.get(i));
			fileManage=this.getFileManageDao().findById(this.getFileId());
			this.getFileManageDao().delete(fileManage);//完成file的删除
			
			this.rfList=new LinkedList<RiskFile>();
			this.rfList=this.getRiskFileDao().findByProperty("fileId", this.getFileId());
			Iterator it=this.rfList.iterator();
			while(it.hasNext()){
				riskFile=(RiskFile) it.next();
				this.getRiskFileDao().delete(riskFile);
			}//完成riskFile的删除	
		}
		filManage();// 得到最新filList,用于在File.jsp表单中显示
		return "success";
	}
	
	// 查看
	public String filRead() {
		try{
			fileManage=this.getFileManageDao().findById(this.getFileId());
			this.fileId=fileManage.getFileId();
			this.fileName=fileManage.getFileName();
			this.fileType=fileManage.getFileType();
			this.risList=new LinkedList<Risk>();
			this.rfList=new LinkedList<RiskFile>();
			this.rfList=this.getRiskFileDao().findByProperty("fileId", this.getFileId());
			Iterator it=this.rfList.iterator();
			while(it.hasNext()){//得到risList,用于在FileAddUpdate.jsp表单中显示
				riskFile=(RiskFile)it.next();
				if(riskFile.getRiskId()!=null){
					risk=this.getRiskDao().findById(riskFile.getRiskId());
					this.risList.add(risk);
				}				
			}
			//当流程文件对应的风险为空时，显示“无”
			if(0==this.getRisList().size()){
				RiskType rt1=new RiskType();
				rt1.setRtId("none1");
				rt1.setRtName("无");
				Risk r1=new Risk();
				r1.setRiskId("none1");
				r1.setRiskName("无");
				r1.setRiskType(rt1.getRtId());
				r1.setRiskDep("无");
				this.risList.add(r1);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	// 编辑前显示要编辑的对象的信息
	public String filUpdatePrepare() {
		try{
			// 得到下拉框，风险类型下拉框allriskList
			getDropDownList();
			
			fileManage=this.getFileManageDao().findById(this.getFileId());
			this.fileId=fileManage.getFileId();
			this.fileName=fileManage.getFileName();
			this.fileType=fileManage.getFileType();
			
			this.risList=new LinkedList<Risk>();
			this.rfList=new LinkedList<RiskFile>();
			this.rfList=this.getRiskFileDao().findByProperty("fileId", this.getFileId());
			Iterator it=this.rfList.iterator();
			while(it.hasNext()){//得到risList,用于在FileAddUpdate.jsp表单中显示
				riskFile=(RiskFile)it.next();
				if(riskFile.getRiskId()!=null){
					risk=this.getRiskDao().findById(riskFile.getRiskId());
					this.risList.add(risk);
				}				
			}			
			/*Iterator it=file.getRisks().iterator();
			while(it.hasNext()){
				this.risList.add((Risk) it.next());
			}*/
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	//新增前显示要编辑的对象的信息
	public String filAddPrepare() {
		try{
			// 得到下拉框，风险类型下拉框allriskList
			getDropDownList();
			this.fileType = "1";
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	// 得到下拉框，风险类型下拉框allriskList
	public String getDropDownList() {
		try{
			//风险名称下拉框allriskList
			RiskType rt1=new RiskType();
			rt1.setRtId("none1");
			rt1.setRtName("--请选择--");
			rt1.setRtRemark("--请选择--");
			Risk r1=new Risk();
			r1.setRiskId("none1");
			r1.setRiskName("--请选择--");
			r1.setRiskRemark("--请选择--");
			r1.setRiskDep("none1");
			r1.setRiskType(rt1.getRtId());
			this.allriskList=new LinkedList<Risk>();
			this.allriskList.add(r1);
			this.riskList=new LinkedList<Risk>();
			this.riskList=this.getRiskDao().findAllCurrent();
			this.allriskList.addAll(this.riskList);
		}
		catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	//根据文件名称查询
	public String selectFile(){
		
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			String fileType = request.getParameter("fileTypeString");
			request.setAttribute("fileType", fileType);
			//得到filList1
			if(null==this.getFileNameString()) this.setFileNameString("");
			this.setFileNameString(URLDecoder.decode(this.getFileNameString(),"UTF-8"));
			this.filList=new LinkedList<FileManage>();
			this.filList=this.getFileManageDao().findAll((current_pagenum-1)*pageNum,pageNum,this.getFileNameString(),fileType);	
			this.actionName="dataUnit/FileSelectAction";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
}
