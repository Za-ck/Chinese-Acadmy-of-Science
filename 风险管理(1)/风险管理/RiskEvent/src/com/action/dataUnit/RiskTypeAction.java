package com.action.dataUnit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dao.RiskTypeDAO;
import com.model.Client;
import com.model.Department;
import com.model.RiskType;

public class RiskTypeAction {
	private String rtId;
    private String rtName;
    private String rtRemark;
    private Set risks = new HashSet(0);
    private List<RiskType> rtList;
    private List<String> idCheck;
    private String updateFlag;
	private String tem;
    private RiskTypeDAO riskTypeDao;
    RiskType riskType=new RiskType();
    private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
    
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
	public String getRtId() {
		return rtId;
	}
	public void setRtId(String rtId) {
		this.rtId = rtId;
	}
	public String getRtName() {
		return rtName;
	}
	public void setRtName(String rtName) {
		this.rtName = rtName;
	}
	public String getRtRemark() {
		return rtRemark;
	}
	public void setRtRemark(String rtRemark) {
		this.rtRemark = rtRemark;
	}
	public Set getRisks() {
		return risks;
	}
	public void setRisks(Set risks) {
		this.risks = risks;
	}
	public List<RiskType> getRtList() {
		return rtList;
	}
	public void setRtList(List<RiskType> rtList) {
		this.rtList = rtList;
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
	public RiskTypeDAO getRiskTypeDao() {
		return riskTypeDao;
	}
	public void setRiskTypeDao(RiskTypeDAO riskTypeDao) {
		this.riskTypeDao = riskTypeDao;
	}
	public RiskType getRiskType() {
		return riskType;
	}
	public void setRiskType(RiskType riskType) {
		this.riskType = riskType;
	}
    
	public String getTem() {
		return tem;
	}
	public void setTem(String tem) {
		this.tem = tem;
	}
	//显示风险类型信息,得到最新rtList,用于在RiskType.jsp表单中显示
	public String rtManage(){
		
		rtList=new LinkedList<RiskType>();	
		rtList=this.getRiskTypeDao().findAll((current_pagenum-1)*pageNum,pageNum);
		ServletActionContext.getRequest().setAttribute("rtList", rtList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
		if(rtList.size()>0){
			
			return "success";

		}
		else  
			return "fail";
	}
	// 新增|编辑
	public String rtAddUpdate(){
		if(this.updateFlag.equals("update"))
		{		
			riskType.setRtId(this.getRtId());
			riskType.setRtName(this.getRtName());
			riskType.setRtRemark(this.getRtRemark());
			this.getRiskTypeDao().merge(riskType);
			rtManage();//得到最新rtList,用于在RiskType.jsp表单中显示
			return "success";
		}
		else 
		{
			if(riskTypeDao.findById(this.getRtId())!=null)return "fail";
			
			riskType.setRtId(this.getRtId());
			riskType.setRtName(this.getRtName());
			riskType.setRtRemark(this.getRtRemark());
			this.getRiskTypeDao().save(riskType);
			rtManage();//得到最新rtList,用于在RiskType.jsp表单中显示
			return "success";
		}
	}
	//批量删除
	public String rtMultiDel(){	
		int i=0;
		for(i=0;i<idCheck.size();i++)
		{		
			this.setRtId(this.idCheck.get(i));
			riskType=this.getRiskTypeDao().findById(this.getRtId());
			this.getRiskTypeDao().delete(riskType);
		}
		rtManage();//得到最新rtList,用于在RiskType.jsp表单中显示
		return "success";
	}
	//删除
	public String rtDelete(){
		try{
			riskType=this.getRiskTypeDao().findById(this.getRtId());
			this.getRiskTypeDao().delete(riskType);
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		rtManage();//得到最新rtList,用于在RiskType.jsp表单中显示
		return "success";
	}
	// 查看
	public String rtRead(){
		try{
			riskType=this.getRiskTypeDao().findById(this.getRtId());
			this.rtId=riskType.getRtId();
			this.rtName=riskType.getRtName();
			this.rtRemark=riskType.getRtRemark();
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	// 编辑前显示要编辑的对象的信息
	public String rtUpdatePrepare(){
		try{
			riskType=this.getRiskTypeDao().findById(this.getRtId());
			this.rtId=riskType.getRtId();
			this.rtName=riskType.getRtName();
			this.rtRemark=riskType.getRtRemark();
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	public void ajaxRisk(){
		
		RiskType rt=new RiskType();
		RiskType rt2=new RiskType();
		rt.setRtId("1");
		rt.setRtName("zhanlue");
		rt.setRtRemark("1111");
		
		rt2.setRtId("2");
		rt2.setRtName("zhanlue");
		rt2.setRtRemark("1111");
		
		
		List list=new ArrayList();
		list.add(rt);
		list.add(rt2);
		
		JSONArray ajaxarray=JSONArray.fromObject(list);
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		writeToResponse(response,ajaxarray);
		
		
	}
	public void writeToResponse(HttpServletResponse response,JSONArray jsonArr)
	{
		PrintWriter out=null;
		response.setContentType("text/html;charset=utf-8");
		try
		{
			out=response.getWriter();
			out.print(jsonArr.toString());
			out.flush();
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
}
