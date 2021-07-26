package com.action.riskAssess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.dao.DepartmentDAO;
import com.dao.RiskAssessAccountDAO;
import com.dao.RiskAssessSituationDAO;
import com.dao.RiskAssessViewDAO;
import com.dao.RiskDimensionDAO;
import com.dao.RiskEventDAO;
import com.form.RiskAssessInPass;
import com.form.RiskAssessNum;
import com.form.RiskAssessSituationM;
import com.form.RiskAssessSynthesizer;
import com.entity.riskManage;
import com.model.Department;
import com.model.RiskAssessAccount;
import com.model.RiskEvent;
import com.model.Users;

public class RiskAssessPassAction {
	
	private String reIds;
	private String years;
	private String quarters;
	private String assessStatuss;
	private String raDepName;
	private String reId;
	private String reEventname;
	private String reDate;
	private String reCreator;
	private String raYear;
	private String raYearTo;
	private String raQuarter;
	private String raQuarterTo;
	private Integer raNumberInput;
	private Integer raNumberDeal;
	private String raRemark1;
	private String raRemark2;
	private Integer raDimNum;
	private String raDimName;
	private String Year;
	private String actionName;
	private String updownflag = "";
	private String updownid = "";
	private String orderbys = "";
	private int current_pagenum = 1;// 当前页码
	private int pageNum = 10;// 每页的显示数据记录数

	private List<Department> alldepList; // 录入部门
	private List<Department> depList;
	private List<RiskAssessNum> raList;//第一维度考核全部查询御用List
	private List<RiskAssessNum> rcList;//第一维度考核条件查询御用List
	private ArrayList<RiskAssessSituationM> rbList;//第二维度考核全部查询御用List
	private List<RiskAssessSituationM> rdList;//第二维度考核条件查询御用List
	private List<RiskAssessSynthesizer> reList;//综合考核计算第一维度考核分数
	private List<RiskAssessSynthesizer> rfList;//综合考核计算第二维度考核分数
	private List<RiskAssessSynthesizer> rgList;//综合考核条件查询计算第一维度考核分数
	private List<RiskAssessSynthesizer> rhList;//综合考核条件查询计算第二维度考核分数
	private List<RiskAssessSynthesizer> rSumList;//综合考核全部查询御用List
	private List<RiskAssessSynthesizer> rToList;//综合考核条件查询御用List
	private List<riskManage> rmlist;
	private List<String> idCheck;
	
	private List<RiskAssessInPass> InpassList;//流转中风险事件御用List
//流转中风险事件御用List
	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getQuarters() {
		return quarters;
	}

	public void setQuarters(String quarters) {
		this.quarters = quarters;
	}

	public String getAssessStatuss() {
		return assessStatuss;
	}

	public void setAssessStatuss(String assessStatuss) {
		this.assessStatuss = assessStatuss;
	}

	public List<RiskAssessInPass> getInpassList() {
		return InpassList;
	}

	public void setInpassList(List<RiskAssessInPass> inpassList) {
		InpassList = inpassList;
	}

	private DepartmentDAO departmentDao;
	private RiskAssessViewDAO riskAssessViewDao;
	private RiskDimensionDAO riskDimensionDao;
	private RiskAssessSituationDAO riskAssessSituationDao;
	private RiskEventDAO riskEventDao;
	private RiskAssessAccountDAO riskAssessAccountDAO;

	public RiskAssessSituationDAO getRiskAssessSituationDao() {
		return riskAssessSituationDao;
	}

	public void setRiskAssessSituationDao(
			RiskAssessSituationDAO riskAssessSituationDao) {
		this.riskAssessSituationDao = riskAssessSituationDao;
	}

	public RiskDimensionDAO getRiskDimensionDao() {
		return riskDimensionDao;
	}

	public void setRiskDimensionDao(RiskDimensionDAO riskDimensionDao) {
		this.riskDimensionDao = riskDimensionDao;
	}

	public RiskEventDAO getRiskEventDao() {
		return riskEventDao;
	}

	public void setRiskEventDao(RiskEventDAO riskEventDao) {
		this.riskEventDao = riskEventDao;
	}

	public List<RiskAssessNum> getRaList() {
		return raList;
	}

	public void setRaList(List<RiskAssessNum> raList) {
		this.raList = raList;
	}
	
	public String getReIds() {
		return reIds;
	}

	public void setReIds(String reIds) {
		this.reIds = reIds;
	}

	public List<RiskAssessNum> getRcList() {
		return rcList;
	}

	public void setRcList(List<RiskAssessNum> rcList) {
		this.rcList = rcList;
	}

	public ArrayList<RiskAssessSituationM> getRbList() {
		return rbList;
	}

	public void setRbList(ArrayList<RiskAssessSituationM> rbList) {
		this.rbList = rbList;
	}

	public List<RiskAssessSituationM> getRdList() {
		return rdList;
	}

	public void setRdList(List<RiskAssessSituationM> rdList) {
		this.rdList = rdList;
	}

	public List<RiskAssessSynthesizer> getReList() {
		return reList;
	}

	public void setReList(List<RiskAssessSynthesizer> reList) {
		this.reList = reList;
	}

	public List<RiskAssessSynthesizer> getRfList() {
		return rfList;
	}

	public void setRfList(List<RiskAssessSynthesizer> rfList) {
		this.rfList = rfList;
	}

	public List<RiskAssessSynthesizer> getRgList() {
		return rgList;
	}

	public void setRgList(List<RiskAssessSynthesizer> rgList) {
		this.rgList = rgList;
	}

	public List<RiskAssessSynthesizer> getRhList() {
		return rhList;
	}

	public void setRhList(List<RiskAssessSynthesizer> rhList) {
		this.rhList = rhList;
	}

	public List<RiskAssessSynthesizer> getRSumList() {
		return rSumList;
	}

	public void setRSumList(List<RiskAssessSynthesizer> rSumList) {
		this.rSumList = rSumList;
	}
	
	
	public List<RiskAssessSynthesizer> getRToList() {
		return rToList;
	}

	public void setrToList(List<RiskAssessSynthesizer> rToList) {
		this.rToList = rToList;
	}

	public List<riskManage> getRmlist() {
		return rmlist;
	}

	public void setRmlist(List<riskManage> rmlist) {
		this.rmlist = rmlist;
	}

	public RiskAssessViewDAO getRiskAssessViewDao() {
		return riskAssessViewDao;
	}

	public void setRiskAssessViewDao(RiskAssessViewDAO riskAssessViewDao) {
		this.riskAssessViewDao = riskAssessViewDao;
	}

	public String getRaDepName() {
		return raDepName;
	}

	public void setRaDepName(String raDepName) {
		this.raDepName = raDepName;
	}

	public String getReId() {
		return reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
	}

	public String getReEventname() {
		return reEventname;
	}

	public void setReEventname(String reEventname) {
		this.reEventname = reEventname;
	}

	public String getReDate() {
		return reDate;
	}

	public void setReDate(String reDate) {
		this.reDate = reDate;
	}

	public String getReCreator() {
		return reCreator;
	}

	public void setReCreator(String reCreator) {
		this.reCreator = reCreator;
	}

	public String getRaYear() {
		return raYear;
	}

	public void setRaYear(String raYear) {
		this.raYear = raYear;
	}

	public String getRaYearTo() {
		return raYearTo;
	}

	public void setRaYearTo(String raYearTo) {
		this.raYearTo = raYearTo;
	}

	public String getRaQuarter() {
		return raQuarter;
	}

	public void setRaQuarter(String raQuarter) {
		this.raQuarter = raQuarter;
	}

	public String getRaQuarterTo() {
		return raQuarterTo;
	}

	public void setRaQuarterTo(String raQuarterTo) {
		this.raQuarterTo = raQuarterTo;
	}

	public Integer getRaNumberInput() {
		return raNumberInput;
	}

	public void setRaNumberInput(Integer raNumberInput) {
		this.raNumberInput = raNumberInput;
	}

	public Integer getRaNumberDeal() {
		return raNumberDeal;
	}

	public void setRaNumberDeal(Integer raNumberDeal) {
		this.raNumberDeal = raNumberDeal;
	}

	public Integer getRaDimNum() {
		return raDimNum;
	}

	public void setRaDimNum(Integer raDimNum) {
		this.raDimNum = raDimNum;
	}

	public String getRaDimName() {
		return raDimName;
	}

	public void setRaDimName(String raDimName) {
		this.raDimName = raDimName;
	}

	public String getRaRemark1() {
		return raRemark1;
	}

	public void setRaRemark1(String raRemark1) {
		this.raRemark1 = raRemark1;
	}

	public String getRaRemark2() {
		return raRemark2;
	}

	public void setRaRemark2(String raRemark2) {
		this.raRemark2 = raRemark2;
	}

	public List<Department> getAlldepList() {
		return alldepList;
	}

	public void setAlldepList(List<Department> alldepList) {
		this.alldepList = alldepList;
	}

	public List<Department> getDepList() {
		return depList;
	}

	public void setDepList(List<Department> depList) {
		this.depList = depList;
	}

	public DepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
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

	public String getUpdownflag() {
		return updownflag;
	}

	public void setUpdownflag(String updownflag) {
		this.updownflag = updownflag;
	}

	public String getUpdownid() {
		return updownid;
	}

	public void setUpdownid(String updownid) {
		this.updownid = updownid;
	}

	public String getOrderbys() {
		return orderbys;
	}

	public void setOrderbys(String orderbys) {
		this.orderbys = orderbys;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}
	
	public List<String> getIdCheck() {
		return idCheck;
	}

	public void setIdCheck(List<String> idCheck) {
		this.idCheck = idCheck;
	}
	public RiskAssessAccountDAO getRiskAssessAccountDAO() {
		return riskAssessAccountDAO;
	}

	public void setRiskAssessAccountDAO(RiskAssessAccountDAO riskAssessAccountDAO) {
		this.riskAssessAccountDAO = riskAssessAccountDAO;
	}

	//流转中风险事件详细信息
	@SuppressWarnings("unchecked")
	public String riskAssessPass() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			// 获取ravList
			String depId = request.getParameter("depId");
			//System.out.println(depId);
			String depNameInpass="";
			String yearFrom = request.getParameter("yearFrom");
			String yearTo = request.getParameter("yearTo");
			String quarterFrom = request.getParameter("quarterFrom");
			String quarterTo = request.getParameter("quarterTo");
			depNameInpass = this.getDepartmentDao().getdepname(depId);
			request.setAttribute("depNameInpass", depNameInpass);
			List<RiskEvent> InPassVList = new LinkedList<RiskEvent>();
			String date = "";
			String dateTo = "";
			if((yearFrom.compareTo(yearTo)<0)||(yearFrom.compareTo(yearTo)==0&&quarterFrom.compareTo(quarterTo)<=0)){
				quarterFrom = SwitchQuarterFrom(quarterFrom);
				quarterTo = SwitchQuarterTo(quarterTo);
				date = yearFrom+"-"+quarterFrom;
				dateTo = yearTo+"-"+quarterTo;
			}else{
				quarterFrom = SwitchQuarterFrom(quarterFrom);
				quarterTo = SwitchQuarterTo(quarterTo);
				dateTo = yearFrom+"-"+quarterFrom;
				date = yearTo+"-"+quarterTo;
			}
			InPassVList = this.getRiskEventDao().findInpass(depId, date,dateTo);
			InpassList=setInpass(InPassVList);
			request.setAttribute("depId", depId);
			
			Calendar calendar = Calendar.getInstance();
			
			request.setAttribute("year", calendar.get(Calendar.YEAR) + "");
			
			int month = calendar.get(Calendar.MONTH) + 1;
			request.setAttribute("quarter", (month -1) / 3 + 1);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	public String riskAssessPassed() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			// 获取ravList
			String depId = request.getParameter("depId");
			//System.out.println(depId);
			String depNameInpass="";
			String yearFrom = request.getParameter("yearFrom");
			String yearTo = request.getParameter("yearTo");
			String quarterFrom = request.getParameter("quarterFrom");
			String quarterTo = request.getParameter("quarterTo");
			depNameInpass = this.getDepartmentDao().getdepname(depId);
			request.setAttribute("depNameInpass", depNameInpass);
			List<RiskEvent> InPassVList = new LinkedList<RiskEvent>();
			String date = "";
			String dateTo = "";
/*			if((yearFrom.compareTo(yearTo)<0)||(yearFrom.compareTo(yearTo)==0&&quarterFrom.compareTo(quarterTo)<=0)){
				quarterFrom = SwitchQuarterFrom(quarterFrom);
				quarterTo = SwitchQuarterTo(quarterTo);
				date = yearFrom+"-"+quarterFrom;
				dateTo = yearTo+"-"+quarterTo;
			}else{
				quarterFrom = SwitchQuarterFrom(quarterFrom);
				quarterTo = SwitchQuarterTo(quarterTo);
				dateTo = yearFrom+"-"+quarterFrom;
				date = yearTo+"-"+quarterTo;
			}*/
			InPassVList = this.getRiskEventDao().findInpassedd(depId, yearFrom,yearTo,quarterFrom,quarterTo);
			InpassList=setInpass(InPassVList);
			System.out.println("cccccccccccccccccccccccccccccccccccccc"+InpassList.size());
			request.setAttribute("depId", depId);
			
			Calendar calendar = Calendar.getInstance();
			
			request.setAttribute("year", calendar.get(Calendar.YEAR) + "");
			
			int month = calendar.get(Calendar.MONTH) + 1;
			request.setAttribute("quarter", (month -1) / 3 + 1);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	//批量考核
	public void riskAssessAssess() {
		try {
			
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			
			Map session = ServletActionContext.getContext().getSession();
			Users user = (Users)session.get("loginUser");
			int position = user.getUserPosition();
			if(position != 9) {		//如果不是管理员
				out.write("fail");
				out.flush();
				return;
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			String depId = request.getParameter("depId");
			List<String> reIdlist = Arrays.asList(this.reIds.split("@"));
			List<String> quarterList = Arrays.asList(this.quarters.split("@"));
			List<String> yearList = Arrays.asList(this.years.split("@"));
			List<String> assessStatusList=Arrays.asList(this.assessStatuss.split("@"));
			String reIdGet = "";
			for(int i = 0;i < reIdlist.size();i++){
//				System.out.println("是否考核************"+assessStatusList.get(i));
				if(assessStatusList.get(i)!=null&&"1".equals(assessStatusList.get(i))){
					reIdGet = reIdlist.get(i);
					String assessYear = yearList.get(i);
					String assessQuarter = quarterList.get(i);
					RiskEvent riskEvent=this.getRiskEventDao().findById(reIdGet);
					String oldAssessYear=riskEvent.getReAssessYear();
					String oldAssessQuarter=riskEvent.getReAssessQuarter();
					String reDates=riskEvent.getReDate();
					System.out.println(reDates+"121jqqwqq");
					int reYears=Integer.parseInt(reDates.substring(0,4));
					int reQuarters=(Integer.parseInt(reDates.substring(5, 7))+2)/3;
					int reMonths=Integer.parseInt(reDates.substring(5, 7));
					int oldMonth=Integer.valueOf(riskEvent.getReAssessQuarter())*3;
					//风险事件之前考核的季度的记录数减掉
					this.getRiskAssessViewDao().updateRiskAccountByDelete(depId, oldAssessYear, oldMonth+"");
					System.out.println(oldMonth+"121jqqwqq1");
					//修改风险事件考核信息
					this.getRiskEventDao().updateAssess(reIdGet,assessYear,assessQuarter);
					System.out.println(reDates+"121jqqwqq2");
					int quarter = Integer.parseInt(assessQuarter);		//要考核到的季度
					int	Intmonth = quarter * 3;					
					List<?> list = this.getRiskAssessViewDao().findList(depId, reYears+"", reMonths+"");
				    //重新进行考核
					if(list!=null&&list.size()!=0){
						this.getRiskAssessViewDao().updateRiskAccount(depId, reYears+"", reMonths+"");
						System.out.println(reDates+"121jqqwqq3");
					}else{
						Department d = this.getDepartmentDao().findById(depId);
						RiskAssessAccount raa = new RiskAssessAccount();
						raa.setRaDepId(d.getDepId());
						raa.setRaDepName(d.getDepName());
						raa.setRaYear(reYears+"");
						raa.setRaQuarter(reQuarters+"");
						raa.setRaMonth(reMonths+"");
						raa.setRaNumberInput(1);
						raa.setRaDepProperty(d.getDepSort()+"");
						this.getRiskAssessAccountDAO().save(raa);
					}
					
				}else{
				reIdGet = reIdlist.get(i);
				RiskEvent riskEvent=this.getRiskEventDao().findById(reIdGet);
				String reDates=riskEvent.getReDate();
				System.out.println(reDates+"   121jqqwqq4");
				int reYears=Integer.parseInt(reDates.substring(0,4));
				System.out.println(reYears+"   121jqqwqq4");
				int reQuarters=(Integer.parseInt(reDates.substring(5, 7))+2)/3;
				System.out.println(reQuarters+"   121jqqwqq4");
				int reMonths=Integer.parseInt(reDates.substring(5, 7));
				//int oldMonth=Integer.valueOf(riskEvent.getReAssessQuarter())*3;
				//this.getRiskEventDao().updatCheckFlag(reIdGet);   //2015-12-03前代码
				String assessYear = yearList.get(i);
				String assessQuarter = quarterList.get(i);
				this.getRiskEventDao().updateAssess(reIdGet,assessYear,assessQuarter);
				System.out.println(reDates+"121jqqwqq5");
				//int Intmonth = 0;
				int quarter = Integer.parseInt(assessQuarter);		//要考核到的季度
					//Intmonth = quarter * 3;
					List<?> list = this.getRiskAssessViewDao().findList(depId,assessYear, Integer.valueOf(assessQuarter)*3+"");
				    //重新进行考核
					if(list!=null&&list.size()!=0){
						this.getRiskAssessViewDao().updateRiskAccount(depId, assessYear, Integer.valueOf(assessQuarter)*3+"");
						System.out.println(reDates+"121jqqwqq6");
					}else{
						Department d = this.getDepartmentDao().findById(depId);
						RiskAssessAccount raa = new RiskAssessAccount();
						raa.setRaDepId(d.getDepId());
						raa.setRaDepName(d.getDepName());
						raa.setRaYear(assessYear);
						raa.setRaQuarter(reQuarters+"");
						raa.setRaMonth(Integer.valueOf(assessQuarter)*3+"");
						raa.setRaNumberInput(1);
						raa.setRaDepProperty(d.getDepSort()+"");
						this.getRiskAssessAccountDAO().save(raa);
						System.out.println(reDates+"121jqqwqq7");
					}
			}
			out.write("success");
			out.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
//			return "fail";
		}
//		return "success";
	}
	
	public void riskAssessAssessed() {
		try {
					
					PrintWriter out = ServletActionContext.getResponse().getWriter();
					
					Map session = ServletActionContext.getContext().getSession();
					Users user = (Users)session.get("loginUser");
					int position = user.getUserPosition();
					if(position != 9) {		//如果不是管理员
						out.write("fail");
						out.flush();
						return;
					}
					HttpServletRequest request = ServletActionContext.getRequest();
					String depId = request.getParameter("depId");
					List<String> reIdlist = Arrays.asList(this.reIds.split("@"));
					List<String> quarterList = Arrays.asList(this.quarters.split("@"));
					List<String> yearList = Arrays.asList(this.years.split("@"));
					List<String> assessStatusList=Arrays.asList(this.assessStatuss.split("@"));
					String reIdGet = "";
					for(int i = 0;i < reIdlist.size();i++){
//						System.out.println("是否考核************"+assessStatusList.get(i));
						if(assessStatusList.get(i)!=null&&"1".equals(assessStatusList.get(i))){
							reIdGet = reIdlist.get(i);
							String assessYear = yearList.get(i);
							String assessQuarter = quarterList.get(i);

							RiskEvent riskEvent=this.getRiskEventDao().findById(reIdGet);
							String oldAssessYear=riskEvent.getReAssessYear();
							String oldAssessQuarter=riskEvent.getReAssessQuarter();
							//2019.02.21
							if (oldAssessQuarter==null||oldAssessYear==null) {
								//由于项目中存在数据问题，针对于assessYear和assessQuarter为空的情况
								//2019。04.02
								this.getRiskEventDao().updateAssesss(reIdGet,assessYear,assessQuarter);
								
								List<?> list = this.getRiskAssessViewDao().findList(depId, assessYear, Integer.valueOf(assessQuarter)*3+"");
							    //重新进行考核
								if(list!=null&&list.size()!=0){
									this.getRiskAssessViewDao().updateRiskAccount(depId, assessYear, Integer.valueOf(assessQuarter)*3+"");
								}else{
									Department d = this.getDepartmentDao().findById(depId);
									RiskAssessAccount raa = new RiskAssessAccount();
									raa.setRaDepId(d.getDepId());
									raa.setRaDepName(d.getDepName());
									raa.setRaYear(assessYear);
									raa.setRaQuarter(assessQuarter);
									raa.setRaMonth(Integer.valueOf(assessQuarter)*3+"");
									raa.setRaNumberInput(1);
									raa.setRaDepProperty(d.getDepSort()+"");
									this.getRiskAssessAccountDAO().save(raa);
								}
							}
							else{
								int oldMonth=Integer.valueOf(riskEvent.getReAssessQuarter())*3;
								String reDates=riskEvent.getReDate();
								
								this.getRiskEventDao().updateAssesss(reIdGet,assessYear,assessQuarter);
								
								List<RiskAssessAccount> ravLists=new ArrayList<RiskAssessAccount>();
								ravLists=this.getRiskAssessViewDao().reqQuerys(depId, oldAssessYear, oldMonth+"");
								if(ravLists.size()==0){
									Department d = this.getDepartmentDao().findById(depId);
									RiskAssessAccount raa = new RiskAssessAccount();
									raa.setRaDepId(d.getDepId());
									raa.setRaDepName(d.getDepName());
									raa.setRaYear(oldAssessYear);
									raa.setRaQuarter(oldAssessQuarter);
									raa.setRaMonth(oldMonth+"");
									raa.setRaNumberInput(0);
									raa.setRaDepProperty(d.getDepSort()+"");
									this.getRiskAssessAccountDAO().save(raa);
								}
								//风险事件之前考核的季度的记录数减掉
								this.getRiskAssessViewDao().updateRiskAccountByDelete(depId, oldAssessYear, oldMonth+"");
								List<?> list = this.getRiskAssessViewDao().findList(depId, assessYear, Integer.valueOf(assessQuarter)*3+"");
							    //重新进行考核
								if(list!=null&&list.size()!=0){
									this.getRiskAssessViewDao().updateRiskAccount(depId, assessYear, Integer.valueOf(assessQuarter)*3+"");
									System.out.println(reDates+"121jqqwqq3");
								}else{
									Department d = this.getDepartmentDao().findById(depId);
									RiskAssessAccount raa = new RiskAssessAccount();
									raa.setRaDepId(d.getDepId());
									raa.setRaDepName(d.getDepName());
									raa.setRaYear(assessYear);
									raa.setRaQuarter(assessQuarter);
									raa.setRaMonth(Integer.valueOf(assessQuarter)*3+"");
									raa.setRaNumberInput(1);
									raa.setRaDepProperty(d.getDepSort()+"");
									this.getRiskAssessAccountDAO().save(raa);
								}
							}
							
						}
					out.write("success");
					out.flush();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
//					return "fail";
				}
//				return "success";
			}
	private List<RiskAssessInPass> setInpass(List<RiskEvent> inPassVList){
		List<RiskAssessInPass> InpassList = new ArrayList<RiskAssessInPass>();
		if(inPassVList!=null&&inPassVList.size()>0){
			for(int i=0;i<inPassVList.size();i++){
				RiskAssessInPass ras = new RiskAssessInPass();
				ras.setReId(inPassVList.get(i).getReId());
				ras.setReEventname(inPassVList.get(i).getReEventname());
				ras.setReDate(inPassVList.get(i).getReDate());
				ras.setReCreator(inPassVList.get(i).getReCreator());
				ras.setReAct(inPassVList.get(i).getReAct());
				ras.setReState(inPassVList.get(i).getReState());
				ras.setReCheakFlag(inPassVList.get(i).getReCheckflag());
				ras.setReAssessYear(inPassVList.get(i).getReAssessYear());
				ras.setReAssessQuarter(inPassVList.get(i).getReAssessQuarter());
				InpassList.add(ras);
			}
		}
		return InpassList;
	}
	
	private String SwitchQuarterFrom(String quarterFrom){
		if(quarterFrom.equals("1"))
			quarterFrom="01-01 00:00:00";
			
		if(quarterFrom.equals("2"))
			quarterFrom="04-01 00:00:00";
			
		if(quarterFrom.equals("3"))
			quarterFrom="07-01 00:00:00";
			
		if(quarterFrom.equals("4"))
			quarterFrom="10-01 00:00:00";
			
		return quarterFrom;
	}
	private String SwitchQuarterTo(String quarterTo){
		if(quarterTo.equals("1"))
			quarterTo="03-31 23:59:59";

		if(quarterTo.equals("2"))
			quarterTo="06-30 23:59:59";

		if(quarterTo.equals("3"))
			quarterTo="09-30 23:59:59";

		if(quarterTo.equals("4"))
			quarterTo="12-31 23:59:59";

		return quarterTo;
	}
	public void writeToResponse(HttpServletResponse response, JSONArray jsonArr) {
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
}
