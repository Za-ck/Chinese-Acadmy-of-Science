package com.action.riskStrategy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.struts2.ServletActionContext;

import com.dao.DepartmentDAO;
import com.dao.RiskStrategyDAO;
import com.dao.StrategyDAO;
import com.dao.UserStrategyDAO;
import com.model.Department;
import com.model.RiskStrategy;
import com.model.Strategy;
import com.model.UserStrategy;

/**
 * @author Administrator
 *
 */
public class RiskStrategyAction {
	private Integer strategyId;
	private String strategyName;
	private StrategyDAO strategyDao;
	private List<Strategy> strategyList;
	private RiskStrategyDAO riskStrategyDao;
	private List<RiskStrategy> riskStrategyList;
	private Integer stragValue;
	private String stragStatue;
	private String stragColor;
	private int remark;
	private String riskDeps;
	private Integer riskStrId;
	RiskStrategy riskStrategy=new RiskStrategy();
	private int current_pagenum=1;//当前页码
    private int  pageNum=10;//每页的显示数据记录数
    private String updateFlag;
    private String listinfo;
    private List<String> idCheck;
    private List<Department> depList;
    private List<Department> alldepList;
    private DepartmentDAO departmentDao;
    private String depName;
    private UserStrategyDAO userStrategyDao;
    private static final int WIDTH = 28;  
	private static final int HEIGHT = 28; 
    
    
	public String getRiskDeps() {
		return riskDeps;
	}
	public void setRiskDeps(String riskDeps) {
		this.riskDeps = riskDeps;
	}
	public UserStrategyDAO getUserStrategyDao() {
		return userStrategyDao;
	}
	public void setUserStrategyDao(UserStrategyDAO userStrategyDao) {
		this.userStrategyDao = userStrategyDao;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public List<Department> getAlldepList() {
		return alldepList;
	}
	public void setAlldepList(List<Department> alldepList) {
		this.alldepList = alldepList;
	}
	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}
	public List<Department> getDepList() {
		return depList;
	}
	public void setDepList(List<Department> depList) {
		this.depList = depList;
	}
	public List<String> getIdCheck() {
		return idCheck;
	}
	public void setIdCheck(List<String> idCheck) {
		this.idCheck = idCheck;
	}
	public String getListinfo() {
		return listinfo;
	}
	public void setListinfo(String listinfo) {
		this.listinfo = listinfo;
	}
	public String getUpdateFlag() {
		return updateFlag;
	}
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	public Integer getRiskStrId() {
		return riskStrId;
	}
	public void setRiskStrId(Integer riskStrId) {
		this.riskStrId = riskStrId;
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
	public Integer getStragValue() {
		return stragValue;
	}
	public void setStragValue(Integer stragValue) {
		this.stragValue = stragValue;
	}
	public String getStragStatue() {
		return stragStatue;
	}
	public void setStragStatue(String stragStatue) {
		this.stragStatue = stragStatue;
	}
	public String getStragColor() {
		return stragColor;
	}
	public void setStragColor(String stragColor) {
		this.stragColor = stragColor;
	}
	public int getRemark() {
		return remark;
	}
	public void setRemark(int remark) {
		this.remark = remark;
	}
	public RiskStrategy getRiskStrategy() {
		return riskStrategy;
	}
	public void setRiskStrategy(RiskStrategy riskStrategy) {
		this.riskStrategy = riskStrategy;
	}
	public RiskStrategyDAO getRiskStrategyDao() {
		return riskStrategyDao;
	}
	public void setRiskStrategyDao(RiskStrategyDAO riskStrategyDao) {
		this.riskStrategyDao = riskStrategyDao;
	}
	public List<RiskStrategy> getRiskStrategyList() {
		return riskStrategyList;
	}
	public void setRiskStrategyList(List<RiskStrategy> riskStrategyList) {
		this.riskStrategyList = riskStrategyList;
	}
	public Integer getStrategyId() {
		return strategyId;
	}
	public void setStrategyId(Integer strategyId) {
		this.strategyId = strategyId;
	}
	public String getStrategyName() {
		return strategyName;
	}
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	public StrategyDAO getStrategyDao() {
		return strategyDao;
	}
	public void setStrategyDao(StrategyDAO strategyDao) {
		this.strategyDao = strategyDao;
	}
	public List<Strategy> getStrategyList() {
		return strategyList;
	}
	public void setStrategyList(List<Strategy> strategyList) {
		this.strategyList = strategyList;
	}
	
	/*
	 * 
	 */
	public String AddStrategyInfo(){
		Department dep1=new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		alldepList=new LinkedList<Department>();
		alldepList.add(dep1);
		depList=new LinkedList<Department>();
		depList=this.getDepartmentDao().findAll();
		alldepList.addAll(depList);	
		
		return "success";
	}
	/*
	 * 读取策略信息列表
	 */
	public String strategyShow(){
		
		strategyList = new LinkedList<Strategy>();	
		strategyList=this.getStrategyDao().findAll((current_pagenum-1)*pageNum,pageNum);
		ServletActionContext.getRequest().setAttribute("strategyList", strategyList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  		
		if (strategyList.size() > 0)
			return "success";
		else
			return "fail";
	}
	
	/*
	 * 修改策略信息是读取信息
	 */
	public String readUpdateinfo(){
		Strategy s=(Strategy) this.getStrategyDao().findByProperty("strategyId", this.strategyId).get(0);
		this.strategyName=s.getStrategyName();
		
		
		
		
		riskStrategyList = new LinkedList<RiskStrategy>();

		//System.out.print(this.strategyId);
		riskStrategyList=this.riskStrategyDao.findByProperty("strategy.strategyId", this.strategyId);
		
		ServletActionContext.getRequest().setAttribute("riskStrategyList", riskStrategyList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
		

		riskStrategyList=this.riskStrategyDao.findByProperty("strategy.strategyId", this.strategyId);
		Department dep1=new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		alldepList=new LinkedList<Department>();
		alldepList.add(dep1);
		depList=new LinkedList<Department>();
		depList=this.getDepartmentDao().findAll();
		alldepList.addAll(depList);	
		
		List<UserStrategy> usList=new LinkedList<UserStrategy>();
		usList=this.getUserStrategyDao().findByProperty("strategy.strategyId",strategyId );
		if(usList.size()>0)
		{
			this.riskDeps=usList.get(0).getDepartment().getDepId();
		}
		if (riskStrategyList.size() > 0)
			return "success";
		else
			return "fail";
		
	}
	//2019
	public String readUpdateinfo1(int id){
		Strategy s=(Strategy) this.getStrategyDao().findByProperty("strategyId", id).get(0);
		this.strategyName=s.getStrategyName();
		
		
		
		
		riskStrategyList = new LinkedList<RiskStrategy>();

		//System.out.print(this.strategyId);
		riskStrategyList=this.riskStrategyDao.findByProperty("strategy.strategyId", id);
		
		ServletActionContext.getRequest().setAttribute("riskStrategyList", riskStrategyList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
		

		riskStrategyList=this.riskStrategyDao.findByProperty("strategy.strategyId", id);
		Department dep1=new Department();
		dep1.setDepId("none1");
		dep1.setDepName("--请选择--");
		dep1.setDepSort(0);
		alldepList=new LinkedList<Department>();
		alldepList.add(dep1);
		depList=new LinkedList<Department>();
		depList=this.getDepartmentDao().findAll();
		alldepList.addAll(depList);	
		
		List<UserStrategy> usList=new LinkedList<UserStrategy>();
		usList=this.getUserStrategyDao().findByProperty("strategy.strategyId",id );
		if(usList.size()>0)
		{
			this.riskDeps=usList.get(0).getDepartment().getDepId();
		}
		if (riskStrategyList.size() > 0)
			return "success";
		else
			return "fail";
		
	}
/*	public String strategyInf(){
		ServletActionContext.getRequest().getSession().setAttribute("stategyId", strategyId);
		riskStrategyList = new LinkedList<RiskStrategy>();
		System.out.print(this.strategyId);
		riskStrategyList=this.riskStrategyDao.findByProperty("strategy.strategyId", this.strategyId);
		//System.out.println(riskStrategyList.get(0).getStrategy().getStrategyId());
		ServletActionContext.getRequest().setAttribute("riskStrategyList", riskStrategyList);              
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	  
		
		if (riskStrategyList.size() > 0)
			return "success";
		else
			return "fail";
		
	}*/
	/*public String strategyRead() {*/

	/*
	 * 增加或修改决策信息
	 */
	public String updateOrAddSetinfo(){
		//System.out.println("listinfo"+this.listinfo);
		System.out.print(this.updateFlag);
        int id=0;
		try {
			if (this.updateFlag.equals("update")) {
				Strategy update=new  Strategy();
				id=strategyId;
				update.setStrategyId(strategyId);
				update.setStrategyName(strategyName);
				this.getStrategyDao().merge(update);
				
				List<UserStrategy> usList=new LinkedList<UserStrategy>();
				usList=this.getUserStrategyDao().findByProperty("strategy.strategyId",strategyId );
				if(usList.size()>0)
				{
					UserStrategy us=new UserStrategy();
					
					us.setStrategy(this.getStrategyDao().findById(update.getStrategyId()));//设置userstrategy的外键Strategy
					Department dep=new Department();
					dep=this.getDepartmentDao().findById(riskDeps);   
					us.setDepartment(dep);                                   //设置userstrategy的列Department
					us.setUserStrId(usList.get(0).getUserStrId());          //设置userstrategy的主键
					this.getUserStrategyDao().merge(us);	
					
				}
				else
				{	
					UserStrategy us=new UserStrategy();
					us.setStrategy(this.getStrategyDao().findById(update.getStrategyId()));//设置外键Strategy
					Department dep=new Department();
					dep=this.getDepartmentDao().findById(riskDeps);
					us.setDepartment(dep);                                          //设置userstrategy的列Department
					this.getUserStrategyDao().save(us);
				}
				
				/*
				 * 添加
				 */
				this.getRiskStrategyDao().delete(strategyId);
				String[] strategys=this.listinfo.replaceFirst(";","").split(";");
				for(int i=0;i<strategys.length;i++){
					RiskStrategy rs = new RiskStrategy();
					String[] infos=strategys[i].split(",");
					rs.setStrategy(update);
					String inf0=URLDecoder.decode(infos[0],"UTF-8");
					String inf1=URLDecoder.decode(infos[1],"UTF-8");
					String inf2=URLDecoder.decode(infos[2],"UTF-8");
					String inf3=URLDecoder.decode(infos[3],"UTF-8");
					
					rs.setStragStatue(inf0);
					rs.setStragValue(Integer.valueOf(inf1));
					rs.setRemark(Integer.valueOf(inf2));
					rs.setStragColor(inf3);
					this.getRiskStrategyDao().save(rs);
					//图片函数
					showPicture(inf3);
					
				}
			}
			else{
				Strategy add=new  Strategy();
				add.setStrategyName(strategyName);
				this.getStrategyDao().save(add);
				//添加部门Id到userStrategy表中
				/*List<Department> listment=new  LinkedList<Department>();
				listment=this.getDepartmentDao().findByDepName(riskDeps);*/
			
				
					
					UserStrategy us=new UserStrategy();
					//System.out.println(add.getStrategyId()+"xxxxxxllllll");
					id=add.getStrategyId();
					us.setStrategy(this.getStrategyDao().findById(add.getStrategyId()));
					Department dep=new Department();
					dep=this.getDepartmentDao().findById(riskDeps);
					us.setDepartment(dep);
					this.getUserStrategyDao().save(us);
					
				
				
				/*
				 * 添加项
				 */
				this.getRiskStrategyDao().delete(add.getStrategyId());
				String[] strategys=this.listinfo.replaceFirst(";","").split(";");
				for(int i=0;i<strategys.length;i++){
					RiskStrategy rs = new RiskStrategy();
					String[] infos=strategys[i].split(",");
					rs.setStrategy(add);
					String inf0=URLDecoder.decode(infos[0],"UTF-8");
					String inf1=URLDecoder.decode(infos[1],"UTF-8");
					String inf2=URLDecoder.decode(infos[2],"UTF-8");
					String inf3=URLDecoder.decode(infos[3],"UTF-8");
					rs.setStragStatue(inf0);
					rs.setStragValue(Integer.valueOf(inf1));
					rs.setRemark(Integer.valueOf(inf2));
					rs.setStragColor(inf3);
					//System.out.println(inf3);
					
					this.getRiskStrategyDao().save(rs);
					//图片函数
					showPicture(inf3);
				
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//2019strategyShow();
		//this.updateFlag="update";
		updateFlag="update";
		strategyId=id;
		System.out.println("======="+id);
		readUpdateinfo1(id);
		return "success";
	}
	
	// 批量删除
	public String strategyMultiDel() {
		int i = 0;
		for (i = 0; i < this.idCheck.size(); i++) {
			this.setStrategyId(Integer.parseInt(this.idCheck.get(i)));
			Strategy str=this.getStrategyDao().findById(this.getStrategyId());
			
			
			this.getStrategyDao().delete(str);//完成Strategy的删除
			/*this.riskStrategyList=new LinkedList<RiskStrategy>();
			this.riskStrategyList=this.getRiskStrategyDao().findByProperty("strategy.strategyId", this.getStrategyId());
			Iterator it=this.riskStrategyList.iterator();
			while(it.hasNext()){
				
				this.getRiskStrategyDao().delete((RiskStrategy) it.next());
			}//完成riskFile的删除	
			 */			//这些代码不用再xml中设置cascade属性为all或delete
		}
		strategyShow();
		return "success";
	}
	public void showPicture(String col)
	{
		BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); 
		Graphics  g = buffImg.getGraphics();        
		g.setColor(Color.WHITE);         
		g.fillRect(0, 0, WIDTH, HEIGHT); 
		String cols="#"+col;
		g.setColor(Color.decode(cols));
		g.fillOval(0, 0, WIDTH-3, HEIGHT-3);  
		g.dispose(); 
		ImageIcon icon = new ImageIcon(buffImg);
		String pictureName=col+".png";
		String savePath =ServletActionContext.getServletContext().getRealPath("/images/colors/"+pictureName);
		File f = new File(savePath);        
		OutputStream out;         
		 try {             
			 	out = new FileOutputStream(f);            
			 	ImageIO.write(buffImg, "png", out);             
			 	out.close();         
			 } catch (FileNotFoundException e)
			 {        
				 // TODO Auto-generated catch block          
				 e.printStackTrace();    
			 }
		 catch (IOException e)
				 {          
					 // TODO Auto-generated catch block             
					 e.printStackTrace();      
				 }                       
	}
}
