package com.action.riskEvaluate;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import com.action.analysis.BarColorRenderer;
import com.dao.DepartmentDAO;
import com.dao.InvestmentProjectDAO;
import com.dao.InvestmentProjectNameDAO;
import com.dao.ProjectElementDAO;
import com.dao.RiskEvaluateInternationDAO;
import com.entity.InvestmentStatistic;
import com.model.Department;
import com.model.InvestmentProject;
import com.model.InvestmentProjectName;
import com.model.ProjectElement;
import com.model.Users;
import com.util.GenerateSequenceUtil;

public class RiskEvaluateContractResultAction {

	private InvestmentProject investmentProject;
	private ProjectElement projectElement;
	private InvestmentProjectDAO investmentProjectDao;
	private ProjectElementDAO projectElementDao;
	private RiskEvaluateInternationDAO riskEvaluateInternationDao;

	private String filename;
	private String graphURL;
	
	//输入数据显示,折叠表格
	
	private Map<String,LinkedList<ProjectElement>> resultMap = null;
	private List<InvestmentStatistic> projectList;
	private List<ProjectElement> projectElementList;
	private String evalDepName;
	private String dateFrom;
	private String dateTo;
	private List<Department> alldepList; // 评估部门
	private List<Department> deps;
	private DepartmentDAO departmentDao;
	private String personName;
	private String ipName;
	private List<String> ipNameList;
	private InvestmentProjectNameDAO investmentProjectNameDAO;
	private List<InvestmentProjectName> projectNameList;
	private int current_pagenum=1;//当前页码
	private int pageNum=10;
	private String projectNameString="";
 

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	

	public String getGraphURL() {
		return graphURL;
	}

	public void setGraphURL(String graphURL) {
		this.graphURL = graphURL;
	}

	public InvestmentProjectDAO getInvestmentProjectDao() {
		return investmentProjectDao;
	}

	public void setInvestmentProjectDao(InvestmentProjectDAO investmentProjectDao) {
		this.investmentProjectDao = investmentProjectDao;
	}



	public ProjectElementDAO getProjectElementDao() {
		return projectElementDao;
	}

	public void setProjectElementDao(ProjectElementDAO projectElementDao) {
		this.projectElementDao = projectElementDao;
	}
	
	

	public RiskEvaluateInternationDAO getRiskEvaluateInternationDao() {
		return riskEvaluateInternationDao;
	}

	public void setRiskEvaluateInternationDao(
			RiskEvaluateInternationDAO riskEvaluateInternationDao) {
		this.riskEvaluateInternationDao = riskEvaluateInternationDao;
	}

	public InvestmentProject getInvestmentProject() {
		return investmentProject;
	}

	public void setInvestmentProject(InvestmentProject investmentProject) {
		this.investmentProject = investmentProject;
	}

	public ProjectElement getProjectElement() {
		return projectElement;
	}

	public void setProjectElement(ProjectElement projectElement) {
		this.projectElement = projectElement;
	}

	
	public Map<String, LinkedList<ProjectElement>> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, LinkedList<ProjectElement>> resultMap) {
		this.resultMap = resultMap;
	}

	public List<InvestmentStatistic> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<InvestmentStatistic> projectList) {
		this.projectList = projectList;
	}

	public List<ProjectElement> getProjectElementList() {
		return projectElementList;
	}

	public void setProjectElementList(List<ProjectElement> projectElementList) {
		this.projectElementList = projectElementList;
	}

	public String getEvalDepName() {
		return evalDepName;
	}

	public void setEvalDepName(String evalDepName) {
		this.evalDepName = evalDepName;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public List<Department> getAlldepList() {
		return alldepList;
	}

	public void setAlldepList(List<Department> alldepList) {
		this.alldepList = alldepList;
	}

	public List<Department> getDeps() {
		return deps;
	}

	public void setDeps(List<Department> deps) {
		this.deps = deps;
	}

	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getIpName() {
		return ipName;
	}

	public void setIpName(String ipName) {
		this.ipName = ipName;
	}

	public List<String> getIpNameList() {
		return ipNameList;
	}

	public void setIpNameList(List<String> ipNameList) {
		this.ipNameList = ipNameList;
	}

	public InvestmentProjectNameDAO getInvestmentProjectNameDAO() {
		return investmentProjectNameDAO;
	}

	public void setInvestmentProjectNameDAO(
			InvestmentProjectNameDAO investmentProjectNameDAO) {
		this.investmentProjectNameDAO = investmentProjectNameDAO;
	}

	public List<InvestmentProjectName> getProjectNameList() {
		return projectNameList;
	}

	public void setProjectNameList(List<InvestmentProjectName> projectNameList) {
		this.projectNameList = projectNameList;
	}

	public int getCurrent_pagenum() {
		return current_pagenum;
	}

	public void setCurrent_pagenum(int currentPagenum) {
		current_pagenum = currentPagenum;
	}

	public String getProjectNameString() {
		return projectNameString;
	}

	public void setProjectNameString(String projectNameString) {
		this.projectNameString = projectNameString;
	}

	public String getEvaluateResult(){
		try {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession(); 
		InvestmentProject investmentProject = new InvestmentProject();
		String projectId = GenerateSequenceUtil.generateSequenceNo(); //自动生成项目编号
		Date date = new Date();                                       //自动生成项目录入时间
		SimpleDateFormat df = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		investmentProject.setIpId(projectId);
		investmentProject.setIpName(request.getParameter("projectName"));
		investmentProject.setIpMark("总包项目");
		investmentProject.setIpTime(df.format(date).toString());
//		this.investmentProjectDao.save(investmentProject);
		List<ProjectElement> peList=new LinkedList<ProjectElement>();
		String itemsString = request.getParameter("itemsString");
		String[] elementCount = itemsString.split(";");
				
		Map<String,LinkedList<ProjectElement>> photoMap = new LinkedHashMap<String, LinkedList<ProjectElement>>();//存储柱状图信息
		resultMap = new LinkedHashMap<String, LinkedList<ProjectElement>>();//存储折叠表格信息
		for(int i = 0;i<elementCount.length;i++){
			List<ProjectElement> elementList = new LinkedList<ProjectElement>();   //存储折叠表格信息
			List<ProjectElement> photoList = new LinkedList<ProjectElement>();   //存储柱状图信息
			for(int j = 0;j<Integer.parseInt(elementCount[i]);j++){
				if(request.getParameter("text_20"+i+"00"+j)!=null){
					ProjectElement projectElement = new ProjectElement();
					projectElement.setPeProjectid(projectId);
					projectElement.setPeEvaluateid(request.getParameter("Id_20"+i+"00"+j));
					projectElement.setPeProbablity(request.getParameter("select_20"+i+"00"+j));
					
					
					if(request.getParameter("select_20"+i+"00"+j).equals("1")){
						projectElement.setPeProdegree("1（很小）");
					}
					else if(request.getParameter("select_20"+i+"00"+j).equals("2")){
						projectElement.setPeProdegree("2（较小）");
					}
					else if(request.getParameter("select_20"+i+"00"+j).equals("3")){
						projectElement.setPeProdegree("3（一般）");
					}
					else if(request.getParameter("select_20"+i+"00"+j).equals("4")){
						projectElement.setPeProdegree("4（较大）");
					}
					else if(request.getParameter("select_20"+i+"00"+j).equals("5")){
						projectElement.setPeProdegree("5（很大）");
					}
					projectElement.setPeImpactdegree(request.getParameter("text_20"+i+"00"+j));
					projectElement.setPeCateimpact(request.getParameter("text_20"+i));
					projectElement.setPeCategory(request.getParameter("category_20"+i+"00"+j));
					projectElement.setPeEvaluatename(request.getParameter("Idname_20"+i+"00"+j));
					projectElement.setPeCategoryname(request.getParameter("categoryname_20"+i+"00"+j));					
					
					//存储list
					elementList.add(projectElement);
					peList.add(projectElement);
					photoList.add(projectElement);
//					this.projectElementDao.save(projectElement);
				}else{
					//System.out.println("传值为空！");
				}
			}
			// 小计功能
			photoMap.put(200+i+"",(LinkedList<ProjectElement>) photoList);
		
			ProjectElement elementSum = new ProjectElement();
			elementSum.setPeCategoryname(request.getParameter("categoryname_20"+i+"000")+" ");//一级风险名称
			elementSum.setPeCateimpact(request.getParameter("text_20"+i)+" ");
			elementSum.setPeEvaluatename("小计");
			elementSum.setPeProdegree(Integer.toString(elementList.size()));
			elementSum.setPeImpactdegree("1.00");
			elementList.add(elementSum);
			resultMap.put(200+i+"",(LinkedList<ProjectElement>) elementList);
		}
		

		//把结果放入listArray便于后台计算
		List<ProjectElement>[] listArray  = new LinkedList[photoMap.size()];
		int index = 0;
		 for (Entry<String, LinkedList<ProjectElement>> entry : photoMap.entrySet()) {
			   String key = entry.getKey().toString();
			   List<ProjectElement> value = entry.getValue();
			   listArray[index] = value;
			   index++;
			   //System.out.println("key=" + key + " value=" + value.size());
			  }
			
		
		//调用算法
		RiskEvaluate riskEvaluate = new RiskEvaluate();
		String[] finalResult = riskEvaluate.monteCarlo(listArray);
		//结果存入数据库
		investmentProject.setIpExpectedvalue(finalResult[0]);
		investmentProject.setIpVariance(finalResult[1]);
        investmentProject.setIpRegion1(finalResult[2]);
        investmentProject.setIpRegion2(finalResult[3]);
        investmentProject.setIpRegion3(finalResult[4]);
        investmentProject.setIpRegion4(finalResult[5]);
        investmentProject.setIpRegion5(finalResult[6]);
        investmentProject.setIpRegion6(finalResult[7]);
        investmentProject.setIpRegion7(finalResult[8]);
        investmentProject.setIpRegion8(finalResult[9]);
        investmentProject.setIpRegion9(finalResult[10]);
        investmentProject.setIpRegion10(finalResult[11]);
        Map sessionMap = ServletActionContext.getContext().getSession();
        investmentProject.setIpDepId(sessionMap.get("userdepid").toString());
        investmentProject.setIpDepname(sessionMap.get("userdep").toString());
        investmentProject.setIpUsername(sessionMap.get("username").toString());
        investmentProject.setIpUserId(sessionMap.get("userid").toString());
        
//		this.investmentProjectDao.merge(investmentProject);
		
		//结果显示到前台
		//当前项目结果
//		InvestmentProject ipResult = new InvestmentProject();
//		ipResult = this.getInvestmentProjectDao().findById(projectId);
		request.setAttribute("ipResult",investmentProject);  
		request.setAttribute("peList", peList);
		request.setAttribute("resultMap", resultMap);
		
		//计算综合评定值大于0.5的总次数
		int totalNum = 0;
		String advise = "";
		for(int i=7;i<12;i++){
			totalNum = totalNum+Integer.parseInt(finalResult[i]);
		}
		if(totalNum<1000){
			advise = "此投资项目的投资风险很低，可以投资。";
		}else if(totalNum>=1000&&totalNum<3000){
			advise = "此投资项目的投资风险较低，可以考虑投资。";
		}else if(totalNum>=3000&&totalNum<5000){
			advise = "此投资项目的投资风险较高，请慎重考虑。";
		}else if(totalNum>=5000){
			advise = "此投资项目的投资风险很高，建议不要投资。";
		}
		session.setAttribute("totalNum",totalNum);
		session.setAttribute("advise",advise);
		
////////////////////////////////////////////////////////////////////////////////////////开始柱状图
		//柱状图中文乱码问题
		StandardChartTheme theme = new StandardChartTheme("CN" ) {
             // 重写apply(...)方法借机消除文字锯齿.VALUE_TEXT_ANTIALIAS_OFF
             public void apply(JFreeChart chart) {
                  chart.getRenderingHints().put(
                              RenderingHints. KEY_TEXT_ANTIALIASING ,
                              RenderingHints. VALUE_TEXT_ANTIALIAS_OFF );
                   super .apply(chart);
            }
         };
       // 标题乱码解决
       theme.setExtraLargeFont( new Font("宋体" , Font. BOLD, 20));
       // 图例乱码解决
       theme.setRegularFont( new Font("宋体" , Font. PLAIN, 14));
       theme.setLargeFont( new Font("宋体" , Font. BOLD, 14));
       theme.setSmallFont( new Font("宋体" , Font. PLAIN, 10));
       theme.setBaselinePaint(java.awt.Color. white );
       // 应用主题样式
       ChartFactory. setChartTheme(theme);
       
      //柱状图
	   DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	   dataset.addValue(Integer.parseInt(finalResult[2]), "综合评定值" , "(0.0,0.1]" );
  	   dataset.addValue(Integer.parseInt(finalResult[3]), "综合评定值" , "(0.1,0.2]" );
  	   dataset.addValue(Integer.parseInt(finalResult[4]), "综合评定值" , "(0.2,0.3]" );
  	   dataset.addValue(Integer.parseInt(finalResult[5]), "综合评定值" , "(0.3,0.4]" );
  	   dataset.addValue(Integer.parseInt(finalResult[6]), "综合评定值" , "(0.4,0.5]" );
  	   dataset.addValue(Integer.parseInt(finalResult[7]), "综合评定值" , "(0.5,0.6]" );
  	   dataset.addValue(Integer.parseInt(finalResult[8]), "综合评定值" , "(0.6,0.7]" );
  	   dataset.addValue(Integer.parseInt(finalResult[9]), "综合评定值" , "(0.7,0.8]" );
  	   dataset.addValue(Integer.parseInt(finalResult[10]), "综合评定值" , "(0.8,0.9]" );
  	   dataset.addValue(Integer.parseInt(finalResult[11]), "综合评定值" , "(0.9,1.0]" );

  	   JFreeChart chart = ChartFactory. createBarChart3D( "", "综合评定值区间" , "一万次模拟发生次数" ,
  			   dataset, PlotOrientation. VERTICAL , false , true, false );
  	   
  	   
  	   filename=""; 
	   graphURL = "";
	   CategoryPlot cp = chart.getCategoryPlot(); 
	   cp.setBackgroundPaint(new Color(221,253,177)); // 背景色设置
	   cp.setRangeGridlinePaint(ChartColor. GRAY ); // 网格线色设置
	   cp.setNoDataMessage("No data available");
	   // 使纵坐标的最小单位格为整数
	   NumberAxis numberaxis = (NumberAxis) cp.getRangeAxis();
	   numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	   
	   BarColorRenderer barrenderer = new BarColorRenderer();
	   barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	   barrenderer.setBaseItemLabelsVisible(true);
	   barrenderer.setMaximumBarWidth(0.03);
	   // 设置柱子的倒影不可见
	   barrenderer.setShadowVisible(false);
	   barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());//显示每个柱的数值 
	   barrenderer.setBaseItemLabelsVisible(true); 
	   //注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题 
	   barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition( 
	   ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
		barrenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值 
		barrenderer.setItemLabelsVisible(true); 
		barrenderer.setDrawBarOutline(false);
		barrenderer.setItemLabelFont(new Font("宋体", Font.BOLD, 12));// 12号黑体加粗
		barrenderer.setItemLabelPaint(Color.black);// 字体为黑色	
		cp.setRenderer(barrenderer);
		cp.setForegroundAlpha(1);
		
		ValueAxis rangeAxis = cp.getRangeAxis();
		// 设置最高的一个 Item 与图片顶端的距离
		rangeAxis.setUpperMargin(0.1);
		rangeAxis.setMinorTickCount(2);
  	   
  	   try {
  		   HttpSession session2 = request.getSession();
	       Users us = (Users) session2.getAttribute("loginUser");
	       String userId=us.getUserId();
	       boolean havaProjectByuserId=this.getInvestmentProjectDao().haveContractProjectByUserID(investmentProject.getIpName(),userId);
	       request.setAttribute("havaProjectByuserId", havaProjectByuserId);
 		    // 清空上次此用户生成的图片文件
 			File folder = new File(ServletActionContext.getServletContext()
 					.getRealPath("upload"));
 			File[] files = folder.listFiles();
 			for (File file : files) {
 				if (file.getName().contains(userId +"tempInvestmentProject")) {
 					file.delete();
 				}
 			}
 			   graphURL = "upload/"+userId+"tempInvestmentProject" +projectId+".png";  
  		       filename = ServletActionContext.getServletContext().getRealPath(graphURL);
  		       ChartUtilities. saveChartAsPNG( new File(filename), chart, 1000, 500);
  	   } catch (IOException e) {
  		   e.printStackTrace();
  	   }
////////////////////////////////////////////////////////////////////////////////////////结束柱状图
		
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	public String RiskEvaluateGCQuery(){
		this.setIpName("请选择");
		this.setEvalDepName("请选择");
		projectList=new LinkedList<InvestmentStatistic>();
		List<InvestmentProject> tempList=new LinkedList<InvestmentProject>();
		ipNameList=this.getInvestmentProjectDao().findContractIpNameList();
		ipNameList.add(0, "请选择");
		// 每个部门具体事件的list，传给页面显示
		Department dep1 = new Department();
		dep1.setDepId("none1");
		dep1.setDepName("请选择");
		dep1.setDepSort(0);
		alldepList = new LinkedList<Department>();
		alldepList.add(dep1);
		deps = new LinkedList<Department>();
		deps = this.getDepartmentDao().findAll();
		alldepList.addAll(deps);
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
		"yyyy-MM-dd");
		String currdate = formatter.format(new Date()); // 得到年月日 2014-09-02
		String year = currdate.substring(0, 4);
		// 首次查询默认为当年
		dateFrom = year + "-01-01";
		dateTo = year + "-12-31";
		String condition=getConditon();
		tempList=this.getInvestmentProjectDao().findAllGCOrderByName(condition);
		getInfo(tempList);
		return "success";
	}
	
	public String RiskEvaluateGCQueryByCondition(){
		projectList=new LinkedList<InvestmentStatistic>();
		List<InvestmentProject> tempList=new LinkedList<InvestmentProject>();
		String condition=getConditon();
		tempList=this.getInvestmentProjectDao().findAllGCOrderByName(condition);
		getInfo(tempList);
		return "success";
	}
	
	//给项目进行分组和编号，相同项目名称的为一组，前台显示的时候方便合并
	private void getInfo(List<InvestmentProject> tempList) {
		// TODO Auto-generated method stub
		String ipName1;
		String ipName2;
		String depName1;
		String depName2;
		// 项目序号
		int num = 1;
		// 评估总次数
		int allcount = 0;
		// 项目评估次数
		int ipAssessCount = 0;
		//部门数
		int depCount=1;
		allcount= tempList.size();
		for (int m = 0; m < allcount; m++) {
			// 将当前记录放入list中
			InvestmentStatistic ksone = new InvestmentStatistic();
			ksone.setNum(num+"");
			ksone.setIpId(tempList.get(m).getIpId().trim());
			ksone.setIpName(tempList.get(m).getIpName().trim());
			ksone.setIpMark(tempList.get(m).getIpMark().trim());
			ksone.setDepNum(depCount+"");
			ksone.setIpDepname(tempList.get(m).getIpDepname().trim());
			ksone.setIpUsername(tempList.get(m).getIpUsername().trim());
			ksone.setIpTime(tempList.get(m).getIpTime().trim());
			ksone.setResult("查看详细");
			projectList.add(ksone);
			ipAssessCount++;

			if (m < allcount - 1) {
				ipName1 = tempList.get(m).getIpName().trim();
				ipName2 = tempList.get(m+1).getIpName().trim();
				if (ipName1.equals(ipName2)) {
					depName1 = tempList.get(m).getIpDepname();
					depName2 = tempList.get(m + 1).getIpDepname();
					if (!depName1.equals(depName2)) {
						depCount++;
					}
				} else {// 如果当前记录的项目名称与下一个记录不相同，则插入小计
					InvestmentStatistic ksone2 = new InvestmentStatistic();
					ksone2.setNum(num+" ");
					ksone2.setIpName(tempList.get(m).getIpName().trim()+" ");
					ksone2.setIpMark(tempList.get(m).getIpMark().trim()+" ");
					ksone2.setDepNum("小计");
					ksone2.setIpDepname(depCount+"");
					ksone2.setIpUsername(ipAssessCount+"");
					projectList.add(ksone2);
					num++;
					depCount = 1;
					ipAssessCount = 0;
				}
			}
			if (m == allcount - 1)// 最后一条记录特殊处理，直接添加小计
			{
				InvestmentStatistic ksone2 = new InvestmentStatistic();
				ksone2.setNum(num+" ");
				ksone2.setIpName(tempList.get(m).getIpName().trim()+" ");
				ksone2.setIpMark(tempList.get(m).getIpMark().trim()+" ");
				ksone2.setDepNum("小计");
				ksone2.setIpDepname(depCount+"");
				ksone2.setIpUsername(ipAssessCount+"");
				projectList.add(ksone2);
				depCount = 1;
				ipAssessCount = 0;
			}
		}
		if(tempList.size()>0){
		// 加入总计
		InvestmentStatistic ksone2 = new InvestmentStatistic();
		ksone2.setNum("总计");
		ksone2.setIpName(num + "");
		projectList.add(ksone2);
		}

		// 数据存放在session中，便于导出excel
//		String[][] dsarray = new String[ralist.size()][12];
//		for (int m = 0; m < ralist.size(); m++) {
//			dsarray[m][0] = ralist.get(m).getDepNum().trim();
//			dsarray[m][1] = ralist.get(m).getDepName().trim();
//			dsarray[m][2] = ralist.get(m).getRiNum();
//			dsarray[m][3] = ralist.get(m).getRiskName();
//			dsarray[m][4] = ralist.get(m).getRtName();
//			dsarray[m][5] = ralist.get(m).getReId();
//			dsarray[m][6] = ralist.get(m).getReEventname();
//			dsarray[m][7] = ralist.get(m).getRiAlldegree();
//			dsarray[m][8] = ralist.get(m).getRiProdegree();
//			dsarray[m][9] = ralist.get(m).getRiKpi();
//			dsarray[m][10] = ralist.get(m).getRiAllvalue();
//			dsarray[m][11] = ralist.get(m).getRiEventDate();
//		}
//		Map session = ServletActionContext.getContext().getSession();
//		session.put("exportGenList", dsarray);

	}
	
	public String getEvaluateResultByProjectId(){
		try {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession(); 
		InvestmentProject investmentProject = new InvestmentProject();
		String projectId=request.getParameter("projectId");
		investmentProject.setIpId(projectId);
//		System.out.println("projectId***********"+projectId);
		List<ProjectElement> peList=new LinkedList<ProjectElement>();
		peList=this.getProjectElementDao().findByProjectId(projectId);
		Map<String,LinkedList<ProjectElement>> photoMap = new LinkedHashMap<String, LinkedList<ProjectElement>>();//存储柱状图信息
		resultMap = new LinkedHashMap<String, LinkedList<ProjectElement>>();//存储折叠表格信息
		int i=0;
		List<ProjectElement> elementList = new LinkedList<ProjectElement>();   //存储折叠表格信息
		List<ProjectElement> photoList = new LinkedList<ProjectElement>();   //存储柱状图信息
		for(int m=0;m<peList.size();m++){
            String peCategoryname1;
            String peCategoryname2;
            elementList.add(peList.get(m));
            photoList.add(peList.get(m));
//            System.out.println("getPeCategoryname"+"***********"+m+"*******"+peList.get(m).getPeCategoryname());
			if(m<peList.size()-1){
				peCategoryname1=peList.get(m).getPeCategoryname();
				peCategoryname2=peList.get(m+1).getPeCategoryname();
				if(!peCategoryname1.equals(peCategoryname2)){
					// 小计功能
					photoMap.put(100+i+"",(LinkedList<ProjectElement>) photoList);
					ProjectElement elementSum = new ProjectElement();
					elementSum.setPeCategoryname(peList.get(m).getPeCategoryname()+" ");//一级风险名称
					elementSum.setPeCateimpact(peList.get(m).getPeCateimpact()+" ");
					elementSum.setPeEvaluatename("小计");
					elementSum.setPeProdegree(Integer.toString(elementList.size()));
					elementSum.setPeImpactdegree("1.00");
					elementList.add(elementSum);
					resultMap.put(100+i+"",(LinkedList<ProjectElement>) elementList);
					i++;
					elementList = new LinkedList<ProjectElement>();
					photoList = new LinkedList<ProjectElement>();
				}
			}
			if(m==peList.size()-1){
					// 小计功能
					photoMap.put(100+i+"",(LinkedList<ProjectElement>) photoList);
					ProjectElement elementSum = new ProjectElement();
					elementSum.setPeCategoryname(peList.get(m).getPeCategoryname()+" ");//一级风险名称
					elementSum.setPeCateimpact(peList.get(m).getPeCateimpact()+" ");
					elementSum.setPeEvaluatename("小计");
					elementSum.setPeProdegree(Integer.toString(elementList.size()));
					elementSum.setPeImpactdegree("1.00");
					elementList.add(elementSum);
					resultMap.put(100+i+"",(LinkedList<ProjectElement>) elementList);
					i++;
					elementList = new LinkedList<ProjectElement>();
					photoList = new LinkedList<ProjectElement>();
			}
		}
		

		//把结果放入listArray便于后台计算
//		List<ProjectElement>[] listArray  = new LinkedList[photoMap.size()];
//		int index = 0;
//		for (Entry<String, LinkedList<ProjectElement>> entry : photoMap.entrySet()) {
//		   String key = entry.getKey().toString();
//		   List<ProjectElement> value = entry.getValue();
//		   listArray[index] = value;
//		   index++;
//		   //System.out.println("key=" + key + " value=" + value.size());
//		}
			
		
		//调用算法
//		RiskEvaluate riskEvaluate = new RiskEvaluate();
//		String[] finalResult = riskEvaluate.monteCarlo(listArray);

		//结果显示到前台
		//当前项目结果
		InvestmentProject ipResult = new InvestmentProject();
		ipResult = this.getInvestmentProjectDao().findById(projectId);
		request.setAttribute("ipResult",ipResult);  
		request.setAttribute("resultMap", resultMap);
		
		//计算综合评定值大于0.5的总次数
		int totalNum = 0;
		String advise = "";
//		for(int j=7;j<12;j++){
//			totalNum = totalNum+Integer.parseInt(finalResult[j]);
//		}
		totalNum+=Integer.parseInt(ipResult.getIpRegion6())+Integer.parseInt(ipResult.getIpRegion7())+Integer.parseInt(ipResult.getIpRegion8())+Integer.parseInt(ipResult.getIpRegion9())+Integer.parseInt(ipResult.getIpRegion10());
		if(totalNum<1000){
			advise = "此投资项目的投资风险很低，可以投资。";
		}else if(totalNum>=1000&&totalNum<3000){
			advise = "此投资项目的投资风险较低，可以考虑投资。";
		}else if(totalNum>=3000&&totalNum<5000){
			advise = "此投资项目的投资风险较高，请慎重考虑。";
		}else if(totalNum>=5000){
			advise = "此投资项目的投资风险很高，建议不要投资。";
		}
		session.setAttribute("totalNum",totalNum);
		session.setAttribute("advise",advise);
		
//		System.out.println("totalNum***********"+totalNum);
//		System.out.println("advise***********"+advise);
		
////////////////////////////////////////////////////////////////////////////////////////开始柱状图
		//柱状图中文乱码问题
		StandardChartTheme theme = new StandardChartTheme("CN" ) {
             // 重写apply(...)方法借机消除文字锯齿.VALUE_TEXT_ANTIALIAS_OFF
             public void apply(JFreeChart chart) {
                  chart.getRenderingHints().put(
                              RenderingHints. KEY_TEXT_ANTIALIASING ,
                              RenderingHints. VALUE_TEXT_ANTIALIAS_OFF );
                   super .apply(chart);
            }
         };
       // 标题乱码解决
       theme.setExtraLargeFont( new Font("宋体" , Font. BOLD, 20));
       // 图例乱码解决
       theme.setRegularFont( new Font("宋体" , Font. PLAIN, 14));
       theme.setLargeFont( new Font("宋体" , Font. BOLD, 14));
       theme.setSmallFont( new Font("宋体" , Font. PLAIN, 10));
       theme.setBaselinePaint(java.awt.Color. white );
       // 应用主题样式
       ChartFactory. setChartTheme(theme);
       
      //柱状图
	   DefaultCategoryDataset dataset = new DefaultCategoryDataset();
  	   dataset.addValue(Integer.parseInt(ipResult.getIpRegion1()), "综合评定值" , "(0.0,0.1]" );
  	   dataset.addValue(Integer.parseInt(ipResult.getIpRegion2()), "综合评定值" , "(0.1,0.2]" );
  	   dataset.addValue(Integer.parseInt(ipResult.getIpRegion3()), "综合评定值" , "(0.2,0.3]" );
  	   dataset.addValue(Integer.parseInt(ipResult.getIpRegion4()), "综合评定值" , "(0.3,0.4]" );
  	   dataset.addValue(Integer.parseInt(ipResult.getIpRegion5()), "综合评定值" , "(0.4,0.5]" );
  	   dataset.addValue(Integer.parseInt(ipResult.getIpRegion6()), "综合评定值" , "(0.5,0.6]" );
  	   dataset.addValue(Integer.parseInt(ipResult.getIpRegion7()), "综合评定值" , "(0.6,0.7]" );
  	   dataset.addValue(Integer.parseInt(ipResult.getIpRegion8()), "综合评定值" , "(0.7,0.8]" );
  	   dataset.addValue(Integer.parseInt(ipResult.getIpRegion9()), "综合评定值" , "(0.8,0.9]" );
  	   dataset.addValue(Integer.parseInt(ipResult.getIpRegion10()), "综合评定值" , "(0.9,1.0]" );

  	   JFreeChart chart = ChartFactory. createBarChart3D( "", "综合评定值区间" , "一万次模拟发生次数" ,
  			   dataset, PlotOrientation.VERTICAL , false , true, false );
  	   
//  	   chart.getLegend().setPosition(RectangleEdge.RIGHT);
//  	   chart.getLegend().setVisible(false);
//  	   CategoryPlot categoryplot = (CategoryPlot) chart.getPlot(); 
//  	   CategoryAxis domainAxis = categoryplot.getDomainAxis();
//  	   domainAxis.setLabelFont(new Font("宋体" , Font. PLAIN, 14));// X轴的标题文字字体 	   
  	   filename=""; 
  	   graphURL = "";
  	   CategoryPlot cp = chart.getCategoryPlot(); 
  	   cp.setBackgroundPaint(new Color(221,253,177)); // 背景色设置
  	   cp.setRangeGridlinePaint(ChartColor. GRAY ); // 网格线色设置
  	   cp.setNoDataMessage("No data available");
  	   // 使纵坐标的最小单位格为整数
	   NumberAxis numberaxis = (NumberAxis) cp.getRangeAxis();
	   numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	   BarColorRenderer barrenderer = new BarColorRenderer();
	   
	   barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	   barrenderer.setBaseItemLabelsVisible(true);
	   barrenderer.setMaximumBarWidth(0.03);
	   // 设置柱子的倒影不可见
	   barrenderer.setShadowVisible(false);
	   barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());//显示每个柱的数值 
	   barrenderer.setBaseItemLabelsVisible(true); 
	   //注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题 
	   barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition( 
	   ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
		barrenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值 
		barrenderer.setItemLabelsVisible(true); 
		barrenderer.setDrawBarOutline(false);
		barrenderer.setItemLabelFont(new Font("宋体", Font.BOLD, 12));// 12号黑体加粗
		barrenderer.setItemLabelPaint(Color.black);// 字体为黑色	
		cp.setRenderer(barrenderer);
		cp.setForegroundAlpha(1);
		
		ValueAxis rangeAxis = cp.getRangeAxis();
		// 设置最高的一个 Item 与图片顶端的距离
		rangeAxis.setUpperMargin(0.1);
		rangeAxis.setMinorTickCount(2);

  	   
  	   try {
  		   
  		   HttpSession session2 = request.getSession();
	       Users us = (Users) session2.getAttribute("loginUser");
	       String userId=us.getUserId();
		    // 清空上次此用户生成的图片文件
			File folder = new File(ServletActionContext.getServletContext()
					.getRealPath("upload"));
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.getName().contains(projectId)) {
					file.delete();
				}
			}
		       graphURL = "upload/" +projectId+".png"; 
		       filename = ServletActionContext.getServletContext().getRealPath(graphURL);
		       ChartUtilities. saveChartAsPNG( new File(filename), chart, 1000, 500);
  	   } catch (IOException e) {
  		   e.printStackTrace();
  	   }
////////////////////////////////////////////////////////////////////////////////////////结束柱状图
		
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	private String getConditon() {
		// TODO Auto-generated method stub
		String str = "";
		if (evalDepName == null || evalDepName.equals("请选择")) {
			str += "";
		} else {
			str += " and model.ipDepname='" + evalDepName + "'";
		}

		if (dateFrom != null && dateTo != null && !dateFrom.equals("")
				&& !dateTo.equals("")) {
			str += " and model.ipTime between '" + dateFrom + " 00:00:00"
					+ "' and '" + dateTo + " 23:59:59" + "'";
		} else if (dateFrom != null && !dateFrom.equals("")) {
			str += " and model.ipTime>='" + dateFrom + " 00:00:00" + "'";
		} else if (dateTo != null && !dateTo.equals("")) {
			str += " and model.ipTime<='" + dateTo + " 23:59:59" + "'";
		} else {
			str += "";
		}
		if (personName == null || personName.equals("")) {
			str += "";
		} else {
			str += " and (model.ipUsername like '%" + personName + "%' or model.ipUserId like '%"+personName+"%')";
		}
		if (ipName == null || ipName.equals("请选择")) {
			str += "";
		} else {
			str += " and model.ipName='" + ipName + "'";
		}

		return str;
	}
	
	public String selectProjectName(){
		projectNameList=this.getInvestmentProjectNameDAO().findAllGC((current_pagenum-1)*pageNum,pageNum,projectNameString);
		ServletActionContext.getRequest().getSession().setAttribute("current_pagenum",current_pagenum);	 
		return "success";
	}
	
	

	public void addProjectName() {
		try {
			System.out.println("addProjectName*********");
			String pName=(String)ServletActionContext.getRequest().getParameter("pName");
//			System.out.println("pName********"+pName);
			String isAdd="是";
			isAdd=this.getInvestmentProjectNameDAO().insertContractProjectName(pName);
//			System.out.println("isAdd***********"+isAdd);
			JSONArray ajaxarray = new JSONArray();
			JSONObject object = new JSONObject();
			object.put("isAdd", isAdd);
			ajaxarray.add(object.toString());
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, ajaxarray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("chu cuo");
			e.printStackTrace();
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, null);
		}
	}
	
	public void writeToResponse2(HttpServletResponse response, JSONArray jsonArr) {
		PrintWriter out = null;
		response.setContentType("text/html;charset=utf-8");
		try {
			out = response.getWriter();
			out.print(jsonArr.toString());
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	public void saveProject(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session=request.getSession();
			InvestmentProject investmentProject=(InvestmentProject)session.getAttribute("investmentProject");
			Date date = new Date();                                       //修改项目评估时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			investmentProject.setIpTime(df.format(date).toString());
			List<ProjectElement> peList=(List<ProjectElement>)session.getAttribute("peList");
//			System.out.println("investmentProject.ipName**********"+investmentProject.getIpName());
//			System.out.println("peList.size()**********"+peList.size());
			this.getInvestmentProjectDao().save(investmentProject);
			for (ProjectElement projectElement : peList) {
				this.getProjectElementDao().save(projectElement);
			}
			JSONArray ajaxarray = new JSONArray();
			JSONObject object = new JSONObject();	
			object.put("saveProject", "saveProject");
			ajaxarray.add(object.toString());
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, ajaxarray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("chu cuo");
			e.printStackTrace();
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, null);
		}
	}
	
	public void updateProject(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session=request.getSession();
			InvestmentProject investmentProject=(InvestmentProject)session.getAttribute("investmentProject");
			Date date = new Date();                                       //修改项目评估时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			investmentProject.setIpTime(df.format(date).toString());
			//找到旧项目对应的编号
			String projectId=this.getInvestmentProjectDao().findIdByProject(investmentProject.getIpName(), investmentProject.getIpUserId());
			//删除旧项目之前对应的图片
			File folder = new File(ServletActionContext.getServletContext()
					.getRealPath("upload"));
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.getName().contains(projectId)) {
					file.delete();
				}
			}
			this.getInvestmentProjectDao().deleteInvestmentProject(investmentProject);
			this.getInvestmentProjectDao().save(investmentProject);
			List<ProjectElement> peList=(List<ProjectElement>)session.getAttribute("peList");
			for (ProjectElement projectElement : peList) {
				this.getProjectElementDao().save(projectElement);
			}
			JSONArray ajaxarray = new JSONArray();
			JSONObject object = new JSONObject();	
			object.put("updateProject", "updateProject");
			ajaxarray.add(object.toString());
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, ajaxarray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("chu cuo");
			e.printStackTrace();
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse2(response, null);
		}
	}
}
