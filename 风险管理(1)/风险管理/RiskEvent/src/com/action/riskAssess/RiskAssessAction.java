package com.action.riskAssess;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.DepartmentDAO;
import com.dao.DepartmentChangedDAO;
import com.dao.InputStandardDAO;
import com.dao.RiskAssessSituationViewDAO;
import com.dao.RiskAssessViewDAO;
import com.dao.RiskDimensionDAO;
import com.dao.RiskEventDAO;
import com.form.RiskAssessInPass;
import com.form.RiskAssessNum;
import com.form.RiskAssessSituationM;
import com.form.RiskAssessSynthesizer;
import com.entity.riskManage;
import com.model.AllAnalysisView;
import com.model.Department;
import com.model.DepartmentChanged;
import com.model.RiskAssessView;
import com.model.RiskDimension;
import com.model.RiskAssessSituationView;
import com.model.RiskEvent;
import com.model.Users;

public class RiskAssessAction {
	private Integer raId;
	private String raDepId;
	private String raDepName;
	private String raDepProperty;
	private String ravAssessResultQuarter;
	private String choosedIdl;
	private String readOnly;
	
	private Integer ravNumberInputAll;
	private Integer ravNumberDealAll;
	private Integer NumInPass;
	private Integer monthNum;
	private String choosedId;
	private String choosedId2;
	private List<RiskAssessInPass> InpassList;//流转中风险事件御用List
	private List<RiskAssessInPass> InpassList1;//流转中风险事件御用List]
	
	public List<RiskAssessInPass> getInpassList1() {
		return InpassList1;
	}

	public void setInpassList1(List<RiskAssessInPass> inpassList1) {
		InpassList1 = inpassList1;
	}

	public List<RiskAssessInPass> getInpassList() {
		return InpassList;
	}

	public void setInpassList(List<RiskAssessInPass> inpassList) {
		InpassList = inpassList;
	}

	public String getChoosedId2() {
		return choosedId2;
	}

	public void setChoosedId2(String choosedId2) {
		this.choosedId2 = choosedId2;
	}

	public Integer getRavNumberInputAll() {
		return ravNumberInputAll;
	}

	public void setRavNumberInputAll(Integer ravNumberInputAll) {
		this.ravNumberInputAll = ravNumberInputAll;
	}

	public String getChoosedIdl() {
		return choosedIdl;
	}

	public void setChoosedIdl(String choosedIdl) {
		this.choosedIdl = choosedIdl;
	}

	public String getChoosedId() {
		return choosedId;
	}

	public void setChoosedId(String choosedId) {
		this.choosedId = choosedId;
	}

	public Integer getNumInPass() {
		return NumInPass;
	}

	public void setNumInPass(Integer numInPass) {
		NumInPass = numInPass;
	}

	public Integer getRavNumberDealAll() {
		return ravNumberDealAll;
	}

	public void setRavNumberDealAll(Integer ravNumberDealAll) {
		this.ravNumberDealAll = ravNumberDealAll;
	}

	public Integer getMonthNum() {
		return monthNum;
	}

	public void setMonthNum(Integer monthNum) {
		this.monthNum = monthNum;
	}

	private String raYear;
	private String raYearTo;
	private String raQuarter;
	private String raQuarterTo;
	private String raYear11;
	private String raYearTo11;
	private String raQuarter11;
	private String raQuarterTo11;
	private String raYear1;
	private String raYearTo1;
	private String raQuarter1;
	private String raQuarterTo1;
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
	private List<RiskAssessNum> raList;// 第一维度考核全部查询御用List
	private List<RiskAssessNum> rcList;// 第一维度考核条件查询御用List
	private List<RiskAssessSituationM> rbList;// 第二维度考核全部查询御用List
	private List<RiskAssessSituationM> rdList;// 第二维度考核条件查询御用List
	private List<RiskAssessSynthesizer> reList;// 综合考核计算第一维度考核分数
	private List<RiskAssessSynthesizer> rfList;// 综合考核计算第二维度考核分数
	private List<RiskAssessSynthesizer> rgList;// 综合考核条件查询计算第一维度考核分数
	private List<RiskAssessSynthesizer> rhList;// 综合考核条件查询计算第二维度考核分数
	private List<RiskAssessSynthesizer> rSumList;// 综合考核全部查询御用List
	private List<RiskAssessSynthesizer> rToList;// 综合考核条件查询御用List
	private List<riskManage> rmlist;
	private List<String> idCheck;
	private DepartmentDAO departmentDao;
	private DepartmentChangedDAO departmentChangedDao;
	private RiskAssessViewDAO riskAssessViewDao;
	private RiskDimensionDAO riskDimensionDao;
	private RiskAssessSituationViewDAO riskAssessSituationViewDao;
	private RiskEventDAO riskEventDao;
	private InputStandardDAO inputStandardDao;
	

	public DepartmentChangedDAO getDepartmentChangedDao() {
		return departmentChangedDao;
	}

	public void setDepartmentChangedDao(DepartmentChangedDAO departmentChangedDao) {
		this.departmentChangedDao = departmentChangedDao;
	}

	public InputStandardDAO getInputStandardDao() {
		return inputStandardDao;
	}

	public void setInputStandardDao(InputStandardDAO inputStandardDao) {
		this.inputStandardDao = inputStandardDao;
	}

	public RiskAssessSituationViewDAO getRiskAssessSituationViewDao() {
		return riskAssessSituationViewDao;
	}

	public void setRiskAssessSituationViewDao(
			RiskAssessSituationViewDAO riskAssessSituationViewDao) {
		this.riskAssessSituationViewDao = riskAssessSituationViewDao;
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

	public List<RiskAssessNum> getRcList() {
		return rcList;
	}

	public void setRcList(List<RiskAssessNum> rcList) {
		this.rcList = rcList;
	}

	public List<RiskAssessSituationM> getRbList() {
		return rbList;
	}

	public void setRbList(List<RiskAssessSituationM> rbList) {
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
	
	public List<String> getIdCheck() {
		return idCheck;
	}
	
	public void setIdCheck(List<String> idCheck) {
		this.idCheck = idCheck;
	}

	public RiskAssessViewDAO getRiskAssessViewDao() {
		return riskAssessViewDao;
	}

	public void setRiskAssessViewDao(RiskAssessViewDAO riskAssessViewDao) {
		this.riskAssessViewDao = riskAssessViewDao;
	}

	public Integer getRaId() {
		return raId;
	}

	public void setRaId(Integer raId) {
		this.raId = raId;
	}

	public String getRaDepId() {
		return raDepId;
	}

	public void setRaDepId(String raDepId) {
		this.raDepId = raDepId;
	}

	public String getRaDepName() {
		return raDepName;
	}

	public void setRaDepName(String raDepName) {
		this.raDepName = raDepName;
	}

	public String getRaDepProperty() {
		return raDepProperty;
	}

	public void setRaDepProperty(String raDepProperty) {
		this.raDepProperty = raDepProperty;
	}

	public String getRavAssessResultQuarter() {
		return ravAssessResultQuarter;
	}

	public void setRavAssessResultQuarter(String ravAssessResultQuarter) {
		this.ravAssessResultQuarter = ravAssessResultQuarter;
	}

	public String getRaYear11() {
		return raYear11;
	}

	public void setRaYear11(String raYear11) {
		this.raYear11 = raYear11;
	}

	public String getRaYearTo11() {
		return raYearTo11;
	}

	public void setRaYearTo11(String raYearT11o) {
		this.raYearTo11 = raYearTo11;
	}

	public String getRaQuarter11() {
		return raQuarter11;
	}

	public void setRaQuarter11(String raQuarter11) {
		this.raQuarter11 = raQuarter11;
	}

	public String getRaQuarterTo11() {
		return raQuarterTo11;
	}

	public void setRaQuarterTo11(String raQuarterTo11) {
		this.raQuarterTo11 = raQuarterTo11;
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
	
	public String getRaYear1() {
		return raYear1;
	}

	public void setRaYear1(String raYear1) {
		this.raYear1 = raYear1;
	}

	public String getRaYearTo1() {
		return raYearTo1;
	}

	public void setRaYearTo1(String raYearTo1) {
		this.raYearTo1 = raYearTo1;
	}

	public String getRaQuarter1() {
		return raQuarter1;
	}

	public void setRaQuarter1(String raQuarter1) {
		this.raQuarter1 = raQuarter1;
	}

	public String getRaQuarterTo1() {
		return raQuarterTo1;
	}

	public void setRaQuarterTo1(String raQuarterTo1) {
		this.raQuarterTo1 = raQuarterTo1;
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
	
	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
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

	// 第一维度考核
	@SuppressWarnings("unchecked")
	public String riskAssessByNum() {
		System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssss");
		/*try {
			// 获取当前年份
			this.setRaYear(CurrentYear());
			this.setRaYearTo(CurrentYear());
			this.setRaQuarter(CurrentQuarter());
			this.setRaQuarterTo(CurrentQuarter());
			this.setRaYear1(CurrentYear());
			this.setRaYearTo1(CurrentYear());
			this.setRaQuarter1(CurrentQuarter());
			this.setRaQuarterTo1(CurrentQuarter());
			this.choosedId="--请选择--";
			// 录入部门名称下拉框
			Department dep1 = new Department();
			dep1.setDepId("none1");
			dep1.setDepName("--请选择部门--");
			dep1.setDepSort(0);
			alldepList = new LinkedList<Department>();
			alldepList.add(dep1);
			depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findAll();
			alldepList.addAll(depList);
			
			Map session = ServletActionContext.getContext().getSession();
			Users user = (Users)session.get("loginUser");
			int position = user.getUserPosition();
			List<RiskAssessView> ravList = new LinkedList<RiskAssessView>();
		//	List<RiskAssessView> ravList1 = new LinkedList<RiskAssessView>();
			//可以查看全部的
			if(position >= 5 && position <= 10) {
				//System.out.println(this.raDepId);
				// 获取raList
				ravList = this.getRiskAssessViewDao().findAll();
				
			}
			
			else {
				this.raDepId = user.getUserDep();
				// 获取raList
				ravList = this.getRiskAssessViewDao().reqQuery(this.raYear,
						this.raYearTo, this.raDepId, this.raQuarter,
						this.raQuarterTo);
				ServletActionContext.getRequest().setAttribute("readonly","readonly");
				
				
			}
			int year = Integer.parseInt(this.raYear);
			int yearto = Integer.parseInt(this.raYearTo);
			int quarter = Integer.parseInt(this.raQuarter);
			int quarterto = Integer.parseInt(this.raQuarterTo);
			List<RiskAssessNum> raList=new ArrayList();
			raList = calcuteQuarterByNumSum(ravList,year,yearto,quarter,quarterto);
			ServletActionContext.getRequest().setAttribute("raList", raList);
			//流转中查询列表
			List<Object[]> InPassCountList = new LinkedList<Object[]>();
			InPassCountList = (List<Object[]>) this.getRiskEventDao().findInPassCount(this.choosedId,this.raYear,
					this.raYearTo, SwitchQuarterFrom(this.raQuarter),
					SwitchQuarterTo(this.raQuarterTo));
			System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmm"+			InPassCountList.size());
			for(int i=0;i<raList.size();i++){
				for (int j=0;j<InPassCountList.size();j++){
					RiskAssessNum List =raList.get(i) ;
					Object[] count = InPassCountList.get(j);
					
					if(count==null)
						List.setNumInPass(0);
					else if(List.getDepId().equals(count[1].toString())){
						List.setNumInPass(Integer.parseInt(count[0].toString()));
					}
				
				}
			}
			this.setActionName("riskAssess/riskAssessQueryAction");
		} catch (Exception e) {
			
			e.printStackTrace();
			return "fail";
		}
		return "success";*/
		try {
			// 获取当前年份
			this.setRaYear(CurrentYear());
			this.setRaYearTo(CurrentYear());
			this.setRaQuarter(CurrentQuarter());
			this.setRaQuarterTo(CurrentQuarter());
			this.setRaYear1(CurrentYear());
			this.setRaYearTo1(CurrentYear());
			this.setRaQuarter1(CurrentQuarter());
			this.setRaQuarterTo1(CurrentQuarter());
			this.choosedId="--请选择--";
			// 录入部门名称下拉框
			Department dep1 = new Department();
			dep1.setDepId("none1");
			dep1.setDepName("--请选择部门--");
			dep1.setDepSort(0);
			alldepList = new LinkedList<Department>();
			alldepList.add(dep1);
			depList = new LinkedList<Department>();
			//获取设置考核的部门
			//2019.01.06
			depList = this.getDepartmentDao().getDepAssess();
			alldepList.addAll(depList);
			
			Map session = ServletActionContext.getContext().getSession();
			Users user = (Users)session.get("loginUser");
			int position = user.getUserPosition();
			List<RiskAssessView> ravList = new LinkedList<RiskAssessView>();
		//	List<RiskAssessView> ravList1 = new LinkedList<RiskAssessView>();
			//可以查看全部的
			if(position >= 5 && position <= 10) {
				//System.out.println(this.raDepId);
				// 获取raList
				//ravList = this.getRiskAssessViewDao().findAll();
				
			}
			
			else {
				
				ServletActionContext.getRequest().setAttribute("readonly","readonly");
				
				
			}
			int year = Integer.parseInt(this.raYear);
			int yearto = Integer.parseInt(this.raYearTo);
			int quarter = Integer.parseInt(this.raQuarter);
			int quarterto = Integer.parseInt(this.raQuarterTo);
			
			raList = calcuteQuarterByNumSum(ravList,year,yearto,quarter,quarterto);
			ServletActionContext.getRequest().setAttribute("raList", raList);
			//流转中查询列表
			List<Object[]> InPassCountList = new LinkedList<Object[]>();
			InPassCountList = (List<Object[]>) this.getRiskEventDao().findInPassCount(this.choosedId,this.raYear,
					this.raYearTo, SwitchQuarterFrom(this.raQuarter),
					SwitchQuarterTo(this.raQuarterTo));
			System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmm"+			InPassCountList.size());
			if(ravList!=null){
				for(int i=0;i<raList.size();i++){
					for (int j=0;j<InPassCountList.size();j++){
						RiskAssessNum List =raList.get(i) ;
						Object[] count = InPassCountList.get(j);
						if(count==null){}
							//List.setNumInPass(0);
						else if(List.getDepId().equals(count[1].toString())){
							
							List.setNumInPass(Integer.parseInt(count[0].toString()));
							
						}
					}
				}
			}
			
			//rau.setNumInPass(InpassList1.size());
				HttpServletRequest request = ServletActionContext.getRequest();
				String order = request.getParameter("order");
				if("desc".equals(order)) {
					
					Collections.sort(raList);
					
				}
				if("asc".equals(order)) {
					Collections.sort(raList);
					Collections.reverse(raList);
				}
				request.setAttribute("order", order);
				this.setActionName("riskAssess/riskAssessQueryAction");
		} catch (Exception e) {
			
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	@SuppressWarnings("unchecked")
	// 第二维度考核
	public String riskAssessBySituation() {
		try {
			// 获取当前年份
			this.setRaYear(CurrentYear());
			this.setRaYearTo(CurrentYear());
			this.setRaQuarter(CurrentQuarter());
			this.setRaQuarterTo(CurrentQuarter());
			this.setRaYear11(CurrentYear());
			this.setRaYearTo11(CurrentYear());
			this.setRaQuarter11(CurrentQuarter());
			this.setRaQuarterTo11(CurrentQuarter());
			this.choosedIdl="--请选择--";
			System.out.println("choosedIdl+dddd江泉："+choosedIdl);
			// 录入部门名称下拉框
			Department dep1 = new Department();
			dep1.setDepId("none1");
			dep1.setDepName("--请选择部门--");
			dep1.setDepSort(0);
			alldepList = new LinkedList<Department>();
			alldepList.add(dep1);
			depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findAll();
			alldepList.addAll(depList);
			// 获取ravList
//			List<RiskAssessSituation> ravList = new LinkedList<RiskAssessSituation>();
//			ravList = this.getRiskAssessSituationDao().findAll();
			
			Map session = ServletActionContext.getContext().getSession();
			Users user = (Users)session.get("loginUser");
			int position = user.getUserPosition();
			List<RiskAssessSituationView> ravList = new LinkedList<RiskAssessSituationView>();
			//可以查看全部的
			if(position >= 5 && position <= 10) {
				// 获取raList
				ravList = this.getRiskAssessSituationViewDao().findAll();
			}
			
			else {
				this.raDepId = user.getUserDep();
				// 获取raList
				ravList = this.getRiskAssessSituationViewDao().reqQuery(this.raYear,
						this.raYearTo, this.raDepId, this.raQuarter,
						this.raQuarterTo);
				ServletActionContext.getRequest().setAttribute("readonly","readonly");			//表示部门不能被更改
			}
			//calcuteQuarterBySituation
			rbList = calcuteQuarterBySituationSum(ravList);
			
			ServletActionContext.getRequest().setAttribute("rbList", rbList);
			// ServletActionContext.getRequest().getSession().setAttribute("raQuarter",
			// raQuarter);
			
			this.setActionName("riskAssess/riskAssessQueryAction2");
		} catch (Exception e) {
			
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 综合考核
	@SuppressWarnings("unchecked")
	public String riskAssessBySynthesize() {
		try {
			// 获取当前年份
			this.setRaYear(CurrentYear());
			this.setRaYearTo(CurrentYear());
			this.setRaQuarter(CurrentQuarter());
			this.setRaQuarterTo(CurrentQuarter());
			// 录入部门名称下拉框
			Department dep1 = new Department();
			dep1.setDepId("none1");
			dep1.setDepName("--请选择部门--");
			dep1.setDepSort(0);
			alldepList = new LinkedList<Department>();
			alldepList.add(dep1);
			depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findAll();
			alldepList.addAll(depList);
			
			Map session = ServletActionContext.getContext().getSession();
			Users user = (Users)session.get("loginUser");
			int position = user.getUserPosition();
			
			List<RiskAssessView> ravList = new LinkedList<RiskAssessView>();
			List<RiskAssessSituationView> rbvList = new LinkedList<RiskAssessSituationView>();
			
			if(position >= 5 && position <= 10) {
				// 获取raList
				ravList = this.getRiskAssessViewDao().findAll();
				rbvList = this.getRiskAssessSituationViewDao().findAll();
			}
			
			else {
				this.raDepId = user.getUserDep();
				
				ravList = this.getRiskAssessViewDao().reqQuery(this.raYear,
						this.raYearTo, this.raDepId, this.raQuarter,
						this.raQuarterTo);
				rbvList = this.getRiskAssessSituationViewDao().reqQuery(this.raYear,
						this.raYearTo, this.raDepId, this.raQuarter,
						this.raQuarterTo);
				ServletActionContext.getRequest().setAttribute("readonly","readonly");
			}
			
			reList = calcuteQuarterTogetherN(ravList);
			rfList = calcuteQuarterTogetherS(rbvList);
			rSumList = calcuteQuarterTogether(reList, rfList);
			// System.out.println(rSumList.size());
			// ServletActionContext.getRequest().setAttribute("rSumList",
			// rSumList);
			this.setActionName("riskAssess/riskAssessQueryAction3");
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 条件查询
	@SuppressWarnings("unchecked")
	// 第一维度条件查询
	public String reQuery() {
		try {
			// 录入部门名称下拉框
			Department dep1 = new Department();
			dep1.setDepId("none1");
			dep1.setDepName("--请选择部门--");
			dep1.setDepSort(0);
			alldepList = new LinkedList<Department>();
			alldepList.add(dep1);
			depList = new LinkedList<Department>();
			//2019.01.06
			depList = this.getDepartmentDao().getDepAssess();
			alldepList.addAll(depList);
			//System.out.println("choosedId:    江泉  :"+choosedId);
			// 获取raList或者rcList
			if("3".equals(choosedId)){
				if(Integer.parseInt(this.raYear)<2017){
					this.raYear="2017";
					this.raQuarter="1";
				}
			}
			//计算季度差
			int year = Integer.parseInt(this.raYear);
			int yearto = Integer.parseInt(this.raYearTo);
			int quarter = Integer.parseInt(this.raQuarter);
			int quarterto = Integer.parseInt(this.raQuarterTo);
			/*int quarterDif = 0;
			int quarterDif1= 0;
			int num1=0;*/
			//quarterDif = (yearto-year)*4 + (quarterto-quarter) + 1;
			List<RiskAssessView> ravList = new ArrayList<RiskAssessView>();
			/*if(year>yearto){
				ravList=null;
			}else if(year==yearto&&quarter>quarterto){
				ravList=null;
			}else{
			
			ravList = this.getRiskAssessViewDao().reqQuery(this.raYear,
					this.raYearTo, this.raDepId, this.raQuarter,
					this.raQuarterTo);
			}*/
			
			if (this.raYear.equals(this.raYearTo)
					&& this.raQuarter.equals(this.raQuarterTo)) {
				ravList = this.getRiskAssessViewDao().reqQuery(this.raYear,
						this.raYearTo, this.raDepId, this.raQuarter,
						this.raQuarterTo);
			} 
			else if(this.raYear.compareTo(this.raYearTo)>0){
				raList=null;
				
			}
			else if((this.raYear.equals(this.raYearTo))
					&& (this.raQuarter.compareTo(this.raQuarterTo)>0)){
				raList=null;
				
			}
			
			else {
				ravList = this.getRiskAssessViewDao().reqQuery(this.raYear,
						this.raYearTo, this.raDepId, this.raQuarter,
						this.raQuarterTo);
			}
			System.out.println("ravList111:"+ravList.size());
			Map session = ServletActionContext.getContext().getSession();
			Users user = (Users)session.get("loginUser");
			int position = user.getUserPosition();
			if(position >= 5 && position <= 10) {
				// 获取raList
				
			}else {
				ServletActionContext.getRequest().setAttribute("readonly","readonly");
			}			
			//raList = calcuteQuarterByNumSum(ravList,quarterDif);
			//raList = calcuteQuarterByNumSum(ravList,quarterDif,quarterDif1);
			//List raList=new ArrayList();
			raList = calcuteQuarterByNumSum(ravList,year,yearto,quarter,quarterto);
			ServletActionContext.getRequest().setAttribute("raList", raList);
			List<Object[]> InPassCountList = new LinkedList<Object[]>();
			InPassCountList = (List<Object[]>) this.getRiskEventDao().findInPassCount(this.choosedId,this.raYear,
					this.raYearTo, SwitchQuarterFrom(this.raQuarter),
					SwitchQuarterTo(this.raQuarterTo));
			if(ravList!=null){
			for(int i=0;i<raList.size();i++){
				for (int j=0;j<InPassCountList.size();j++){
					RiskAssessNum List =raList.get(i) ;
					Object[] count = InPassCountList.get(j);
					if(count==null){}
						//List.setNumInPass(0);
					else if(List.getDepId().equals(count[1].toString())){
						//List.setNumInPass(Integer.parseInt(count[0].toString()));
						List<RiskEvent> InPassVList1 = new LinkedList<RiskEvent>();
						String date = "";
						String dateTo = "";
						
							String quarter1 =SwitchQuarterFrom(String.valueOf(quarter));
							String quarterto1 = SwitchQuarterTo(String.valueOf(quarterto));
							
						
						date = year+"-"+quarter1;
						dateTo = yearto+"-"+quarterto1;
						System.out.println("==============================="+date);
						InPassVList1= this.getRiskEventDao().findInpass(List.getDepId(), date,dateTo);
						
						InpassList1=setInpass(InPassVList1);
						System.out.println("------------------------------------------"+InpassList1.size());
						//List.setNumInPass(Integer.parseInt(count[0].toString()));
						List.setNumInPass(InpassList1.size());
					}
				}
			}
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			String order = request.getParameter("order");
			if("desc".equals(order)) {
				
				Collections.sort(raList);
				
			}
			if("asc".equals(order)) {
				Collections.sort(raList);
				Collections.reverse(raList);
			}
			request.setAttribute("order", order);
			this.setActionName("riskAssess/riskAssessQueryAction");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 第二维度条件查询

	@SuppressWarnings("unchecked")
	public String reQuery2() {
		try {
			// 录入部门名称下拉框
			Department dep1 = new Department();
			dep1.setDepId("none1");
			dep1.setDepName("--请选择部门--");
			dep1.setDepSort(0);
			alldepList = new LinkedList<Department>();
			alldepList.add(dep1);
			depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findAll();
			alldepList.addAll(depList);
			
			Map session = ServletActionContext.getContext().getSession();
			Users user = (Users)session.get("loginUser");
			int position = user.getUserPosition();
			if(position >= 5 && position <= 10) {
				
				
			}else {
				ServletActionContext.getRequest().setAttribute("readonly","readonly");
			}
			
				if(Integer.parseInt(this.raYear)<2017){
					this.raYear="2017";
					this.raQuarter="1";
				}
			
			// 获取raList或者rcList
			List<RiskAssessSituationView> ravList = new ArrayList<RiskAssessSituationView>();
			ravList = this.getRiskAssessSituationViewDao().reqQuery(this.raYear,
					this.raYearTo, this.raDepId, this.raQuarter,
					this.raQuarterTo);
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+ravList.size());
			if (this.raYear.equals(this.raYearTo)
					&& this.raQuarter.equals(this.raQuarterTo)) {//
				rbList = calcuteQuarterBySituationSum(ravList);
				ServletActionContext.getRequest().setAttribute("rbList", rbList);
				this.setActionName("riskAssess/riskAssessQueryAction2");
			} 
			
			else if(this.raYear.compareTo(this.raYearTo)>0){
				rbList=null;
				ServletActionContext.getRequest().setAttribute("rbList", rbList);
				this.setActionName("riskAssess/riskAssessQueryAction2");
			}
			else if((this.raYear.equals(this.raYearTo))
					&& (this.raQuarter.compareTo(this.raQuarterTo)>0)){
				rbList=null;
				ServletActionContext.getRequest().setAttribute("rbList", rbList);
				this.setActionName("riskAssess/riskAssessQueryAction2");
			}
			
			else {
				rbList = calcuteQuarterBySituationSum(ravList);
				ServletActionContext.getRequest().setAttribute("rbList", rbList);

				this.setActionName("riskAssess/riskAssessQueryAction2");
			}
			
			HttpServletRequest request = ServletActionContext.getRequest();
			String order = request.getParameter("order");
			if(rbList!=null){
			if("desc".equals(order)) {
				
				Collections.sort(rbList);
				
			}
			if("asc".equals(order)) {
				Collections.sort(rbList);
				Collections.reverse(rbList);
			}
			}
			request.setAttribute("order", order);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 综合条件查询
	@SuppressWarnings("unchecked")
	public String reQuery3() {
		try {
			// 录入部门名称下拉框
			Department dep1 = new Department();
			dep1.setDepId("none1");
			dep1.setDepName("--请选择部门--");
			dep1.setDepSort(0);
			alldepList = new LinkedList<Department>();
			alldepList.add(dep1);
			depList = new LinkedList<Department>();
			depList = this.getDepartmentDao().findAll();
			alldepList.addAll(depList);
			if("3".equals(choosedId2)){
				if(Integer.parseInt(this.raYear)<2017){
					this.raYear="2017";
					this.raQuarter="1";
				}
			}
			Map session = ServletActionContext.getContext().getSession();
			Users user = (Users)session.get("loginUser");
			int position = user.getUserPosition();
			if(position >= 5 && position <= 10) {
				// 获取raList
				
			}else {
				ServletActionContext.getRequest().setAttribute("readonly","readonly");
			}
			
			// 获取raList或者rcList
			List<RiskAssessView> ravList = new ArrayList<RiskAssessView>();
			ravList = this.getRiskAssessViewDao().reqQuery(this.raYear,
					this.raYearTo, this.raDepId, this.raQuarter,
					this.raQuarterTo);
			//计算季度差
			int year = Integer.parseInt(this.raYear);
			int yearto = Integer.parseInt(this.raYearTo);
			int quarter = Integer.parseInt(this.raQuarter);
			int quarterto = Integer.parseInt(this.raQuarterTo);
			int quarterDif = 0;			//季度差
			quarterDif = (yearto-year)*4 + (quarterto-quarter) + 1;
			
//			//判断终止季度是否小于当前季度
//			Calendar cal = Calendar.getInstance();
//			int month = cal.get(Calendar.MONTH) + 1;
//			int currentQuarter = (month-1)/3 + 1;
//			int currentYear = cal.get(Calendar.YEAR);
//			int quarterDif2 = (currentYear-year)*4 + (currentQuarter-quarter) + 1;
			
			List<RiskAssessSituationView> rbvList = new ArrayList<RiskAssessSituationView>();
			rbvList = this.getRiskAssessSituationViewDao().reqQuery(this.raYear,
					this.raYearTo, this.raDepId, this.raQuarter,
					this.raQuarterTo);
			 
			rgList = calcuteQuarterTogetherNSum(ravList,quarterDif);
			rhList = calcuteQuarterTogetherSSum(rbvList);
			rSumList = calcuteQuarterTogetherSum(rgList, rhList);
			if(this.raYear.compareTo(this.raYearTo)>0){
				rSumList=null;
				
			}
			else if((this.raYear.equals(this.raYearTo))
					&& (this.raQuarter.compareTo(this.raQuarterTo)>0)){
				rSumList=null;
				
			}
			else{
				rSumList = calcuteQuarterTogetherSum(rgList, rhList);
			}
			this.setActionName("riskAssess/riskAssessQueryAction3");
			
			
			HttpServletRequest request = ServletActionContext.getRequest();
			String order = request.getParameter("order");
			if("desc".equals(order)) {
				
				Collections.sort(rSumList);
				
			}
			if("asc".equals(order)) {
				Collections.sort(rSumList);
				Collections.reverse(rSumList);
			}
			request.setAttribute("order", order);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 显示未应对风险事件
	@SuppressWarnings("unchecked")
	public String riskAssessUnFinished() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			// 获取ravList
			String depId = request.getParameter("depId");
			String depNameUnfinished = "";
			String yearFrom = request.getParameter("yearFrom");
			String yearTo = request.getParameter("yearTo");
			String quarterFrom = request.getParameter("quarterFrom");
			String quarterTo = request.getParameter("quarterTo");

			depNameUnfinished = this.getDepartmentDao().getdepname(depId);
			request.setAttribute("depNameUnfinished", depNameUnfinished);
			List<AllAnalysisView> ravList = new LinkedList<AllAnalysisView>();
			rmlist = new LinkedList<riskManage>();
			String date = "";
			String dateTo = "";
			System.out.println(depId+"----------------------"+yearFrom+"------------"+quarterFrom+"-------------");
			if(Integer.parseInt(yearFrom)<2017){
				yearFrom="2017";
			}
			if ((yearFrom.compareTo(yearTo) < 0)
					|| (yearFrom.compareTo(yearTo) == 0 && quarterFrom
							.compareTo(quarterTo) <= 0)) {
				quarterFrom = SwitchQuarterFrom(quarterFrom);
				quarterTo = SwitchQuarterTo(quarterTo);
				date = yearFrom + "-" + quarterFrom;
				dateTo = yearTo + "-" + quarterTo;
			} else {
				quarterFrom = SwitchQuarterFrom(quarterFrom);
				quarterTo = SwitchQuarterTo(quarterTo);
				dateTo = yearFrom + "-" + quarterFrom;
				date = yearTo + "-" + quarterTo;
			}
			/*ravList = this.getRiskAssessSituationViewDao().findAlert(depId, date,
					dateTo);*/
			ravList=this.getRiskAssessSituationViewDao().findAlert(depId, date,
					dateTo);
			rmlist = SetUnfinished(ravList);
			ServletActionContext.getRequest().setAttribute("rmlist", rmlist);
			this.setActionName("riskAssess/riskAssessUnFinishedAction");
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	// 显示待应对风险事件
			@SuppressWarnings("unchecked")
			public String riskAssessUnFinished1() {
				try {
					HttpServletRequest request = ServletActionContext.getRequest();
					// 获取ravList
					String depId = request.getParameter("depId");
					String depNameUnfinished = "";
					String yearFrom = request.getParameter("yearFrom");
					String yearTo = request.getParameter("yearTo");
					String quarterFrom = request.getParameter("quarterFrom");
					String quarterTo = request.getParameter("quarterTo");

					depNameUnfinished = this.getDepartmentDao().getdepname(depId);
					request.setAttribute("depNameUnfinished", depNameUnfinished);
					List<AllAnalysisView> ravList = new LinkedList<AllAnalysisView>();
					rmlist = new LinkedList<riskManage>();
					String date = "";
					String dateTo = "";
					System.out.println(depId+"----------------------"+yearFrom+"------------"+quarterFrom+"-------------");
					if(Integer.parseInt(yearFrom)<2017){
						yearFrom="2017";
					}
					if ((yearFrom.compareTo(yearTo) < 0)
							|| (yearFrom.compareTo(yearTo) == 0 && quarterFrom
									.compareTo(quarterTo) <= 0)) {
						quarterFrom = SwitchQuarterFrom(quarterFrom);
						quarterTo = SwitchQuarterTo(quarterTo);
						date = yearFrom + "-" + quarterFrom;
						dateTo = yearTo + "-" + quarterTo;
					} else {
						quarterFrom = SwitchQuarterFrom(quarterFrom);
						quarterTo = SwitchQuarterTo(quarterTo);
						dateTo = yearFrom + "-" + quarterFrom;
						date = yearTo + "-" + quarterTo;
					}
					/*ravList = this.getRiskAssessSituationViewDao().findAlert(depId, date,
							dateTo);*/
					ravList=this.getRiskAssessSituationViewDao().findAlert1(depId, date,
							dateTo);
					rmlist = SetUnfinished(ravList);
					ServletActionContext.getRequest().setAttribute("rmlist", rmlist);
					this.setActionName("riskAssess/riskAssessUnFinishedAction");
				} catch (Exception e) {
					e.printStackTrace();
					return "fail";
				}
				return "success";
			}
		
	
	
	private List<riskManage> SetUnfinished(List<AllAnalysisView> ravList) {
		List<riskManage> rmList = new ArrayList<riskManage>();
		if (ravList != null && ravList.size() > 0) {
			for (int i = 0; i < ravList.size(); i++) {
				riskManage rau = new riskManage();
				rau.setDepName(ravList.get(i).getDepName());
				rau.setReId(ravList.get(i).getReId());
				rau.setRiskName(ravList.get(i).getRiskName());
				rau.setRtName(ravList.get(i).getRtName());
				rau.setReEventname(ravList.get(i).getReEventname());
				rau.setRiAlldegree(ravList.get(i).getRiAlldegree().toString());
				rau.setRiKpi(ravList.get(i).getRiKpi());
				rau.setRiAllvalue(ravList.get(i).getRiAllvalue().toString());
				rau.setRiReply(ravList.get(i).getRiReply());
				rau.setRiplandate(ravList.get(i).getRiplandate());
				rau.setRiplanres(ravList.get(i).getRiplanres());
				//rau.set
				rau.setRiProdegree(ravList.get(i).getRiProdegree().toString());
				rmList.add(rau);
			}
		}
		return rmList;
	}
	private List<RiskAssessNum> calcuteQuarterByNumSum1(List<RiskAssessView> ravList,int quarterDif) {
		List<RiskAssessNum> raList = new ArrayList<RiskAssessNum>();
		//List<RiskAssessNum> rcList = new ArrayList<RiskAssessNum>();
		
		RiskDimension dim = riskDimensionDao.findDimbyDimId("ByNum");
		int decreaseScore = dim.getRdDecreaseScore();
		int IncreaseScore = dim.getRdIncreaseScore();
		
		int pneed = inputStandardDao.findInputNeedbyProperty("0").getRdInputStandard();		//生产部门每个季度需要录入的数目
		int mneed = inputStandardDao.findInputNeedbyProperty("1").getRdInputStandard();		//管理部门每个季度需要录入的数目
		 
		 if (ravList != null && ravList.size() > 0) {
			 String preDepId = ravList.get(0).getRaDepId();
			 String preDepName = ravList.get(0).getRaDepName();
			 //int depnum = 1;
			 int inputAll = 0;
			 for (int i = 0; i < ravList.size(); i++) {
				 
				 String depId = ravList.get(i).getRaDepId();
				 
				 if(depId.equals(preDepId) && i < ravList.size() -1) {
					 inputAll += ravList.get(i).getRaNumberInput();
					 continue;
				 }
				 else {
					 RiskAssessNum rau = new RiskAssessNum();
					 rau.setDepName(preDepName);
					 rau.setDepId(preDepId);
					 String raDepProperty = "";
					 if(i == ravList.size() -1) {
						 inputAll += ravList.get(i).getRaNumberInput();
						 raDepProperty = ravList.get(i).getRaDepProperty();
					 }
					 else {
						 raDepProperty = ravList.get(i-1).getRaDepProperty();
					 }
					 if (raDepProperty.equals("0")) {
							rau.setDepProperty("生产部门");
							rau.setStandardNum(pneed*quarterDif);
							int assessResultQuarter = 0;
							
							if (inputAll >= pneed*quarterDif) {
								assessResultQuarter = (inputAll - pneed*quarterDif)* IncreaseScore;
							} else {
								assessResultQuarter = (inputAll - pneed*quarterDif)* decreaseScore;
							}
							
							rau.setAssessResultQuarter(assessResultQuarter);
					} else {
						rau.setDepProperty("管理部门");
						rau.setStandardNum(mneed*quarterDif);
						int assessResultQuarter = 0;
							
						if (inputAll >= mneed*quarterDif) {
							assessResultQuarter = (inputAll - mneed*quarterDif) * IncreaseScore;
						} else {
							assessResultQuarter = (inputAll - mneed*quarterDif) * decreaseScore;
						}
						rau.setAssessResultQuarter(assessResultQuarter);
					}
					 
					 
					 
					// 录入条数传值
					rau.setNumberInputAll(inputAll);
					// 未达标条数传值
					Integer raUnfinished = (rau.getStandardNum() - rau.getNumberInputAll());
					rau.setUnFinishedNum(raUnfinished);
					rau.setYear(this.getRaYear());
					rau.setYearTo(this.getRaYearTo());
					rau.setQuarter(this.getRaQuarter());
					rau.setQuarterTo(this.getRaQuarterTo());
					rau.setTimeFrom(this.getRaYear() + "年第" + this.getRaQuarter()
							+ "季度——" + this.getRaYearTo() + "年第" + this.getRaQuarterTo()
							+ "季度");
					raList.add(rau);
					
					/*inputAll = ravList.get(i).getRaNumberInput();
					preDepId = ravList.get(i).getRaDepId();
					preDepName = ravList.get(i).getRaDepName();*/
				 }
				
			}
		}
		return raList;
	}
	private List<RiskAssessNum> calcuteQuarterByNumSum(List<RiskAssessView> ravList,int year,int yearto,int quarter,int quarterto) {
		List<RiskAssessNum> raList = new ArrayList<RiskAssessNum>();
        System.out.println("表格表格："+ravList.size());
		RiskDimension dim = riskDimensionDao.findDimbyDimId("ByNum");
		int decreaseScore = dim.getRdDecreaseScore();
		int IncreaseScore = dim.getRdIncreaseScore();
		
		int pneed = inputStandardDao.findInputNeedbyProperty("0").getRdInputStandard();		//生产部门每个季度需要录入的数目
		int mneed = inputStandardDao.findInputNeedbyProperty("1").getRdInputStandard();		//管理部门每个季度需要录入的数目
		 
		 if (ravList != null && ravList.size() > 0) {
			 String preDepId = ravList.get(0).getRaDepId();
			 String preDepName = ravList.get(0).getRaDepName();
			 //int depnum = 1; 
			 int inputAll = 0;
			 for (int i = 0; i < ravList.size(); i++) {
				 String depId = ravList.get(i).getRaDepId();
				 String depName=ravList.get(i).getRaDepName();
				 if(depId.equals(preDepId) && i < ravList.size() -1) {
					 System.out.println("部门+需填报3："+preDepName+":+:"+inputAll);
					 inputAll += ravList.get(i).getRaNumberInput();
					 System.out.println("部门+需填报1："+preDepName+":+:"+inputAll);
					 continue;
				 }
				 else {
					 //2018.02.20
					 String depId1=preDepId;
					 int quarterDif = 0;
						int quarterDif1= 0;
						int num1=0;	
					 List<DepartmentChanged> ravList1=new ArrayList();
					 try{
						 ravList1=this.getDepartmentChangedDao().searchAllByDepId(depId1);
					 }catch(Exception ex){
						 System.out.println("jjqjjjq");
					 }
						List<Department> ravList2=new ArrayList<Department>();
						ravList2=this.getDepartmentDao().searchAllByDepId(depId1);
						num1=ravList1.size();
						System.out.println("sdg :"+num1);
						if(num1==0){
							if(ravList2.get(0).getDepSort()==1){
								quarterDif=(yearto-year)*4 + (quarterto-quarter) + 1;
								quarterDif1=0;
							}else{
								quarterDif=0;
								quarterDif1=(yearto-year)*4 + (quarterto-quarter) + 1;
							}
						}else{
							if(year==yearto&&quarter==quarterto){
								if(year<ravList1.get(0).getModifyYear()){
									if(ravList1.get(0).getDepsort1()==1){
										quarterDif=1;
										quarterDif1=0;
									}else{
										quarterDif=0;
										quarterDif1=1;
									}
								}else if(year>ravList1.get(0).getModifyYear()){
									if(ravList2.get(0).getDepSort()==1){
										quarterDif=1;
										quarterDif1=0;
									}else{
										quarterDif=0;
										quarterDif1= 1;
									}
								}else if(year==ravList1.get(0).getModifyYear()&&quarter<ravList1.get(0).getModifyQuarter()){
									if(ravList1.get(0).getDepsort1()==1){
										quarterDif=1;
										quarterDif1=0;
									}else{
										quarterDif=0;
										quarterDif1=1;
									}
								}else if(year==ravList1.get(0).getModifyYear()&&quarter>=ravList1.get(0).getModifyQuarter()){
									if(ravList2.get(0).getDepSort()==1){
										quarterDif=1;
										quarterDif1=0;
									}else{
										quarterDif=0;
										quarterDif1= 1;
									}
								}
								
							}else if(ravList1.get(0).getModifyYear()<year){
								if(ravList2.get(0).getDepSort()==1){
									quarterDif=(yearto-year)*4 + (quarterto-quarter) + 1;
									quarterDif1=0;
								}else{
									quarterDif=0;
									quarterDif1=(yearto-year)*4 + (quarterto-quarter) + 1;
								}
							}else if(ravList1.get(0).getModifyYear()>yearto){
								if(ravList1.get(0).getDepsort1()==1){
									quarterDif=(yearto-year)*4 + (quarterto-quarter) + 1;
									quarterDif1=0;
								}else{
									quarterDif=0;
									quarterDif1=(yearto-year)*4 + (quarterto-quarter) + 1;
								}
							}else if(ravList1.get(0).getModifyYear()==yearto&&ravList1.get(0).getModifyQuarter()>quarterto){
								if(ravList1.get(0).getDepsort1()==1){
									quarterDif=(yearto-year)*4 + (quarterto-quarter)+1;
									quarterDif1=0;
								}else{
									quarterDif=0;
									quarterDif1=(yearto-year)*4 + (quarterto-quarter) + 1;
								}
							}else if(ravList1.get(0).getModifyYear()==year&&ravList1.get(0).getModifyQuarter()<=quarter){
								if(ravList2.get(0).getDepSort()==1){
									quarterDif=(yearto-year)*4 + (quarterto-quarter) + 1;
									quarterDif1=0;
								}else{
									quarterDif=0;
									quarterDif1=(yearto-year)*4 + (quarterto-quarter) + 1;
								}
								
							}else if(ravList1.get(0).getModifyYear()==yearto&&ravList1.get(0).getModifyQuarter()==quarterto){
								if(ravList1.get(0).getDepsort1()==1&&ravList2.get(0).getDepSort()==1){
									quarterDif=(yearto-year)*4 + (quarterto-quarter) + 1;
									quarterDif1=0;
								}else if(ravList1.get(0).getDepsort1()==1&&ravList2.get(0).getDepSort()==0){
									quarterDif1=1;
									quarterDif=(ravList1.get(0).getModifyYear()-year)*4+(ravList1.get(0).getModifyQuarter()-quarter);
								}else if(ravList1.get(0).getDepsort1()==0&&ravList2.get(0).getDepSort()==1){
									quarterDif=1;
									quarterDif1=(ravList1.get(0).getModifyYear()-year)*4+(ravList1.get(0).getModifyQuarter()-quarter);
									
								}else if(ravList1.get(0).getDepsort1()==0&&ravList2.get(0).getDepSort()==0){
									quarterDif1=(yearto-year)*4 + (quarterto-quarter) + 1;
									quarterDif=0;
								}
							}else{
								if(ravList1.get(0).getDepsort1()==1&&ravList2.get(0).getDepSort()==1){
									quarterDif=(yearto-year)*4 + (quarterto-quarter) + 1;
									quarterDif1=0;
								}else if(ravList1.get(0).getDepsort1()==1&&ravList2.get(0).getDepSort()==0){
									quarterDif1=(yearto-ravList1.get(0).getModifyYear())*4+(quarterto-ravList1.get(0).getModifyQuarter())+1;
									quarterDif=(ravList1.get(0).getModifyYear()-year)*4+(ravList1.get(0).getModifyQuarter()-quarter);
								}else if(ravList1.get(0).getDepsort1()==0&&ravList2.get(0).getDepSort()==1){
									quarterDif=(yearto-ravList1.get(0).getModifyYear())*4+(quarterto-ravList1.get(0).getModifyQuarter())+1;
									quarterDif1=(ravList1.get(0).getModifyYear()-year)*4+(ravList1.get(0).getModifyQuarter()-quarter);
									
								}else if(ravList1.get(0).getDepsort1()==0&&ravList2.get(0).getDepSort()==0){
									quarterDif1=(yearto-year)*4 + (quarterto-quarter) + 1;
									quarterDif=0;
								}
							}
						}
					 RiskAssessNum rau = new RiskAssessNum();
					 rau.setDepName(preDepName);
					 
					 rau.setDepId(preDepId);
					 
					 String raDepProperty = "";
					 if(i == ravList.size() -1) {
						 inputAll += ravList.get(i).getRaNumberInput();
						 System.out.println("部门+需填报2："+preDepName+":+:"+inputAll);
						 raDepProperty = ravList.get(i).getRaDepProperty();
					 }
					 else {
						 raDepProperty = ravList.get(i-1).getRaDepProperty();
					 }
					 System.out.println(preDepName+":sgd见风使舵:"+raDepProperty+":sdg tfg");
					 if (raDepProperty.equals("0")) {
							rau.setDepProperty("生产部门");
							rau.setStandardNum(pneed*quarterDif1+mneed*quarterDif);
							System.out.println("0427122:"+(pneed*quarterDif1+mneed*quarterDif));
							//2019。01.06
//							int assessResultQuarter = 0;
//							
//							if (inputAll >= pneed*quarterDif) {
//								assessResultQuarter = (inputAll - (pneed*quarterDif1+mneed*quarterDif))* IncreaseScore;
//							} else {
//								assessResultQuarter = (inputAll - (pneed*quarterDif1+mneed*quarterDif))* decreaseScore;
//							}
//							
//							rau.setAssessResultQuarter(assessResultQuarter);
					} else {
						rau.setDepProperty("管理部门");
						rau.setStandardNum(pneed*quarterDif1+mneed*quarterDif);
						System.out.println("042712:"+(pneed*quarterDif1+mneed*quarterDif));
						System.out.println("quarterDif1"+quarterDif1);
						System.out.println("quarterDif"+quarterDif);
						//2019。01.06
//						int assessResultQuarter = 0;
//							
//						if (inputAll >= pneed*quarterDif1+mneed*quarterDif) {
//							assessResultQuarter = (inputAll - (pneed*quarterDif1+mneed*quarterDif)) * IncreaseScore;
//						} else {
//							assessResultQuarter = (inputAll - (pneed*quarterDif1+mneed*quarterDif)) * decreaseScore;
//						}
//						rau.setAssessResultQuarter(assessResultQuarter);
					}
					 
					 
					 System.out.println("部门+需填报："+preDepName+":+:"+inputAll);
					// 录入条数传值
					// rau.setNumberInputAll(inputAll);
					List<RiskEvent> InPassVList = new LinkedList<RiskEvent>();
					/*List<RiskEvent> InPassVList1 = new LinkedList<RiskEvent>();
					//List<RiskEvent> InPassVList1 = new LinkedList<RiskEvent>();
					String date = "";
					String dateTo = "";
					date = year+"-"+quarter;
					dateTo = yearto+"-"+quarterto;
					InPassVList1= this.getRiskEventDao().findInpass(preDepId, date,dateTo);
					InpassList1=setInpass(InPassVList1);
					System.out.println("------------------------------------------"+InpassList1.size());
					//List.setNumInPass(Integer.parseInt(count[0].toString()));
					rau.setNumInPass(InpassList1.size());
					System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"+rau.getNumInPass());*/
					//String year1=year;
					String year1=String.valueOf(year);
					String yearto1=String.valueOf(yearto);
					String quarter1=String.valueOf(quarter);
					String quarterto1=String.valueOf(quarterto);
					InPassVList = this.getRiskEventDao().findInpassedd(preDepId, year1,yearto1,quarter1,quarterto1);
					InpassList=setInpass(InPassVList);
					//rau.setStandardNum(InpassList.size());
					rau.setNumberInputAll(InpassList.size());
					//2019。01.06
					// 录入最终考核分数
					rau.setAssessResultQuarter(rau.getNumberInputAll() - rau.getStandardNum());
					// 未达标条数传值
					Integer raUnfinished = (rau.getStandardNum() - rau.getNumberInputAll());
					rau.setUnFinishedNum(raUnfinished);
					
					rau.setYear(this.getRaYear());
					rau.setYearTo(this.getRaYearTo());
					rau.setQuarter(this.getRaQuarter());
					rau.setQuarterTo(this.getRaQuarterTo());
					rau.setTimeFrom(this.getRaYear() + "年第" + this.getRaQuarter()
							+ "季度——" + this.getRaYearTo() + "年第" + this.getRaQuarterTo()
							+ "季度");
					raList.add(rau);
					
					inputAll = ravList.get(i).getRaNumberInput();
					preDepId = ravList.get(i).getRaDepId();
					preDepName = ravList.get(i).getRaDepName();
				 }
				
			}
		}
		return raList;
	}
//----------------------------------------------------------新加
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
	
	private List<RiskAssessSituationM> calcuteQuarterBySituation(
			List<RiskAssessSituationView> ravList) {
		int StandardAll = 0;
		rbList = new ArrayList<RiskAssessSituationM>();

		if (ravList != null && ravList.size() > 0) {
			for (int i = 0; i < ravList.size(); i++) {
				
				RiskAssessSituationM rau = new RiskAssessSituationM();
				
				// 部门名称传值
				rau.setDepName(ravList.get(i).getRaDepName());
				rau.setDepId(ravList.get(i).getRaDepId());
				// 季度考核分数
				RiskDimension dim = riskDimensionDao.findDimbyDimId("BySituation");
				int decreaseScore = dim.getRdDecreaseScore();
				int IncreaseScore = dim.getRdIncreaseScore();
				int DealAll = 0;
				int Standard=0;

				int assessResultQuarter = 0;

				RiskAssessSituationView rav = ravList.get(i);//i
				int DealNum = rav.getRaNumberDeal();
				System.out.println("pp================================"+DealNum);
				DealAll = DealNum;
				Standard = rav.getRaNumberNeed();
				System.out.println("bb================================"+Standard);
				StandardAll = Standard;
				Standard=0;
				if (DealAll >= StandardAll) {
					assessResultQuarter = (DealAll - StandardAll)* IncreaseScore;
				} else {
					assessResultQuarter = (DealAll - StandardAll)* decreaseScore;
				}
                System.out.println("sb111111111111111111111111111111========"+assessResultQuarter);
               
				rau.setAssessResultQuarter(assessResultQuarter);
				// 应应对条数传值
				rau.setStandardNum(StandardAll);
				System.out.println("ttttttttttttttttttttttttttttttttttttttt"+StandardAll);
				// 已应对条数传值
				rau.setNumberDealAll(DealAll);
				// 未达标条数传值
				Integer raUnfinished = (rau.getStandardNum() - rau
						.getNumberDealAll());
				System.out.println("sb222222222222222222222222222222========"+raUnfinished);
				rau.setUnFinishedNum(raUnfinished);
				// 年份传值
				rau.setYear(ravList.get(i).getRaYear());
				//rau.setYearTo(ravList.get(i).);
				
				// 季度传值
				rau.setQuarter(ravList.get(i).getRaQuarter());
				rau.setTimeFrom(rau.getYear() + "年第" + rau.getQuarter() + "季度");
				StandardAll=0;
				rbList.add(rau);
				
			}
		}

		return rbList;
	}

	private List<RiskAssessSituationM> calcuteQuarterBySituationSum(
			List<RiskAssessSituationView> ravList) {
		List<RiskAssessSituationM> rbList = new ArrayList<RiskAssessSituationM>();
		List<RiskAssessSituationM> rdList = new ArrayList<RiskAssessSituationM>();
		RiskDimension dim = riskDimensionDao.findDimbyDimId("BySituation");
		int decreaseScore = dim.getRdDecreaseScore();
		int IncreaseScore = dim.getRdIncreaseScore();
		int standardall=0;
		
		if (ravList != null && ravList.size() > 0) {
			for (int i = 0; i < ravList.size(); i++) {
				RiskAssessSituationM rau = new RiskAssessSituationM();
				// 部门名称传值
				rau.setDepName(ravList.get(i).getRaDepName());
				rau.setDepId(ravList.get(i).getRaDepId());
				// 季度考核分数
				
				int DealAll = 0;
				
				int StandardAll = 0;
				RiskAssessSituationView rav = ravList.get(i);
				int DealNum = rav.getRaNumberDeal();
				DealAll = DealNum;
				int Standard = rav.getRaNumberNeed();
				StandardAll = Standard;
				System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"+DealNum);
				// 应应对条数传值
				rau.setStandardNum(StandardAll);
				System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"+StandardAll);
				// 已应对条数传值
				rau.setNumberDealAll(DealAll);
				// 年份传值
				rau.setYear(ravList.get(i).getRaYear());
				// 季度传值
				rau.setQuarter(ravList.get(i).getRaQuarter());
				rau.setTimeFrom(rau.getYear() + "年第" + rau.getQuarter() + "季度");
				rbList.add(rau);
			}
			// 合并记录方法
			int ListSize = rbList.size();

			int total = 1;// total部门总数;
			for (int i1 = 0; i1 < ListSize - 1; i1++) {
				if (!rbList.get(i1).getDepId().equals(rbList.get(i1 + 1).getDepId()))
					total += 1;
			}
			int count = 0;// 扫描整个列表;
			int Standard = 0;
			for (int j1 = 0; j1 < total; j1++) {
				RiskAssessSituationM ras = new RiskAssessSituationM();
				int num = 1;// 某部门记录条数;------------------------------1
				int AssessResultQuarTer = 0;
				int DealAll = 0;
				int Unfinished = 0;
				int ToDealNumber=0;
				//int Standard = 0;
				// String Year="";
				// String YearTo="";
				// String QuarTer="";
				// String QuarTerTo="";
				String DepName = "";
				String DepId = "";
				for (int j = 0;; j++) {
					if (count < ListSize - 1) {
						if (rbList.get(count).getDepId().equals(rbList.get(count + 1).getDepId())) {
							num++;
							count++;
						} else {
							break;
						}
					} else {
						break;
					}
				}
				int k = 1;
				// Year = rbList.get(count-num+k).getYear();
				// QuarTer = rbList.get(count-num+k).getQuarter();
				for (; k <= num; k++) {//------------------------------------------+1
					//AssessResultQuarTer += rbList.get(count - num + k).getAssessResultQuarter();
					DealAll+= rbList.get(count - num + k).getNumberDealAll();//+
					//System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppppppppp"+DealAll);
					//Unfinished += rbList.get(count - num + k).getUnFinishedNum();
					//Standard += rbList.get(count - num + k).getStandardNum();
				}
				
				DepName = rbList.get(count).getDepName();
				DepId = rbList.get(count).getDepId();
				List<RiskAssessSituationView> list=null;
				list=this.getRiskAssessSituationViewDao().findRisk(DepId, this.getRaYear(),this.getRaYearTo(), this.getRaQuarter(), this.getRaQuarterTo());
				List<RiskAssessSituationView> list1=null;
				String date1=this.getRaYear();
				if(Integer.parseInt(date1)<2017){
					date1="2017";
				}
				list1=this.getRiskAssessSituationViewDao().findRisk1(DepId, date1,this.getRaYearTo(), this.getRaQuarter(), this.getRaQuarterTo());
				Standard= rbList.get(count).getStandardNum();
				List<RiskAssessSituationView> list2=null;
				list2=this.getRiskAssessSituationViewDao().findRisk2(DepId, date1,this.getRaYearTo(), this.getRaQuarter(), this.getRaQuarterTo());
				Unfinished = list2.size();
				/*Unfinished = Standard-DealAll;
				if (DealAll >= Standard) {
					AssessResultQuarTer = (DealAll - Standard)* IncreaseScore;
				} else {
					AssessResultQuarTer = (DealAll - Standard)* decreaseScore;
				}*/
				if (list1.size() >= list.size()) {
					AssessResultQuarTer = (list1.size() - list.size())* IncreaseScore;
				} else {
					AssessResultQuarTer = (list1.size() - list.size())* decreaseScore;
				}
				// YearTo = rbList.get(count).getYear();
				// QuarTerTo = rbList.get(count).getQuarter();
				//ToDealNumber=list.size()-list1.size()-list2.size();
				ras.setAssessResultQuarter(-(list2.size()));//AssessResultQuarTer-(list2.size())
				ras.setNumberDealAll(list1.size());//DealAll
				//ras.setNumberNeed(standardall);
				ras.setUnFinishedNum(list2.size());
				ras.setStandardNum(list.size());//-----------------------Standard
				ras.setToDealnumber(list.size()-list1.size()-list2.size());
				System.out.println(ras.getToDealnumber());
				System.out.println("=================================================================="+Standard);
				//ras.setStandardNum(standardall);
				ras.setDepName(DepName);
				ras.setDepId(DepId);
				ras.setYear(this.getRaYear());
				ras.setYearTo(this.getRaYearTo());
				ras.setQuarter(this.getRaQuarter());
				ras.setQuarterTo(this.getRaQuarterTo());
				ras.setTimeFrom(ras.getYear() + "年第" + ras.getQuarter()+ "季度——" + ras.getYearTo() + "年第" + ras.getQuarterTo()+ "季度");//
				
				
				rdList.add(ras);
				//System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"+DealAll);
				count++;
			}
		}
		return rdList;
	}


	private List<RiskAssessSynthesizer> calcuteQuarterTogetherN(
			List<RiskAssessView> ravList) {
		List<RiskAssessSynthesizer> reList = new ArrayList<RiskAssessSynthesizer>();
		int year = Integer.parseInt(this.raYear);
		int yearto = Integer.parseInt(this.raYearTo);
		int quarter = Integer.parseInt(this.raQuarter);
		int quarterto = Integer.parseInt(this.raQuarterTo);
		
		raList = calcuteQuarterByNumSum(ravList,year,yearto,quarter,quarterto);
		int sizeN = raList.size();
		for (int total = 0; total <= sizeN - 1; total++) {
			RiskAssessSynthesizer rau = new RiskAssessSynthesizer();

			//RiskDimension wightByNum = riskDimensionDao.findDimbyDimId("ByNum");
			//RiskDimension wightBySituation = riskDimensionDao.findDimbyDimId("BySituation");
			//int wightScoreNum = wightByNum.getRdWeight();
			//int wightScoreSituation = wightBySituation.getRdWeight();
			double scoreN = 0;
			//double wight = 0;
			String Year = "";
			// String YearTo="";
			String QuarTer = "";
			// String QuarTerTo="";

			//wight = (double)wightScoreNum / (wightScoreNum+wightScoreSituation);
			//scoreN = raList.get(total).getAssessResultQuarter() * wight;
			//scoreN = raList.get(total).getAssessResultQuarter() * wightScoreNum;
			scoreN = raList.get(total).getAssessResultQuarter();
			rau.setAssessResultQuarterByNum(scoreN);
			rau.setDepName(raList.get(total).getDepName());
			rau.setDepProperty(raList.get(total).getDepProperty());
			Year = raList.get(total).getYear();
			// YearTo = raList.get(total).getYearTo();
			QuarTer = raList.get(total).getQuarter();
			// QuarTerTo = raList.get(total).getQuarterTo();
			rau.setTimeFrom(Year + "年第" + QuarTer + "季度");
			reList.add(rau);
		}
		return reList;
	}

	private List<RiskAssessSynthesizer> calcuteQuarterTogetherS(
			List<RiskAssessSituationView> rbvList) {
		List<RiskAssessSynthesizer> rfList = new ArrayList<RiskAssessSynthesizer>();
		rbList = calcuteQuarterBySituation(rbvList);
		// int sizeRa = raList.size();
		int sizeS = rbList.size();
		for (int total = 0; total <= sizeS - 1; total++) {
			RiskAssessSynthesizer rau = new RiskAssessSynthesizer();

			//RiskDimension wightByNum = riskDimensionDao.findDimbyDimId("ByNum");
			//RiskDimension wightBySituation = riskDimensionDao.findDimbyDimId("BySituation");
			//int wightScoreNum = wightByNum.getRdWeight();
			//int wightScoreSituation = wightBySituation.getRdWeight();
			double scoreS = 0;
			//double wight = 0;
			String Year = "";
			// String YearTo="";
			String QuarTer = "";
			// String QuarTerTo="";

			//wight = (double)wightScoreSituation / (wightScoreNum+wightScoreSituation);
			//scoreS = rbList.get(total).getAssessResultQuarter() * wight;
			scoreS = raList.get(total).getAssessResultQuarter();
			//scoreS = rbList.get(total).getAssessResultQuarter() * wightScoreSituation;
			rau.setAssessResultQuarterBySituation(scoreS);
			if (raList.size() == 0) {
				rfList = new LinkedList<RiskAssessSynthesizer>();
				return rfList;
			}

			rau.setDepName(rbList.get(total).getDepName());
			rau.setDepProperty(rbList.get(total).getDepProperty());
			Year = rbList.get(total).getYear();
			// YearTo = raList.get(total).getYearTo();
			QuarTer = rbList.get(total).getQuarter();
			// QuarTerTo = raList.get(total).getQuarterTo();
			rau.setTimeFrom(Year + "年第" + QuarTer + "季度");
			rfList.add(rau);
		}
		return rfList;
	}

	private List<RiskAssessSynthesizer> calcuteQuarterTogether(List<RiskAssessSynthesizer> reList,List<RiskAssessSynthesizer> rfList) {
		
		List<RiskAssessSynthesizer> rSumList = new ArrayList<RiskAssessSynthesizer>();
		// RiskAssessSynthesizer rau = new RiskAssessSynthesizer();
		// String Year="";
		// String Quarter="";
		int j = 0;
		DecimalFormat df = new DecimalFormat("######0.00");
		
		RiskDimension wightByNum = riskDimensionDao.findDimbyDimId("ByNum");
		RiskDimension wightBySituation = riskDimensionDao.findDimbyDimId("BySituation");
		int wightScoreNum = wightByNum.getRdWeight();
		int wightScoreSituation = wightBySituation.getRdWeight();
		
		for (int i = 0; i < reList.size(); i++) {
			RiskAssessSynthesizer rau = new RiskAssessSynthesizer();
			rau.setAssessResultQuarterByNum(Double.parseDouble(df.format(reList.get(i).getAssessResultQuarterByNum())));
			j = 0;
			if (rfList.size() != 0) {
				
				while(j <= rfList.size() -1) {
					
					if (reList.get(i).getDepName().equals(rfList.get(j).getDepName())) {
						rau.setAssessResultQuarterBySituation(Double.parseDouble(df.format(rfList.get(j).getAssessResultQuarterBySituation())));
						rau.setAssessResultQuarter(Double.parseDouble(df.format(reList.get(i).getAssessResultQuarterByNum() * wightScoreNum + rfList.get(j).getAssessResultQuarterBySituation() * wightScoreSituation)));
						break;
					}
					j++;
				}
				if(j >= rfList.size()) {
					rau.setAssessResultQuarter(Double.parseDouble(df.format(reList.get(i).getAssessResultQuarterByNum() * wightScoreNum)));
					rau.setAssessResultQuarterBySituation(0.0);
					
				}
				
				
			} else {
				rau.setAssessResultQuarter(Double.parseDouble(df.format(reList.get(i).getAssessResultQuarterByNum() * wightScoreNum)));
				rau.setAssessResultQuarterBySituation(0.0);
			}
			rau.setDepName(reList.get(i).getDepName());
			rau.setDepProperty(reList.get(i).getDepProperty());
			// Year = reList.get(i).getYear();
			// Quarter = reList.get(i).getQuarter();
			rau.setTimeFrom(reList.get(i).getTimeFrom());
			rSumList.add(rau);

		}
		return rSumList;
	}

	private List<RiskAssessSynthesizer> calcuteQuarterTogetherNSum(List<RiskAssessView> ravList,int quarterDif) {
		List<RiskAssessSynthesizer> rgList = new ArrayList<RiskAssessSynthesizer>();
		rcList = calcuteQuarterByNumSum1(ravList,quarterDif);
		//rcList = calcuteQuarterByNumSum(ravList,quarterDif,0);
		int sizeN = rcList.size();
		for (int total = 0; total <= sizeN - 1; total++) {
			RiskAssessSynthesizer rau = new RiskAssessSynthesizer();

			//RiskDimension wightByNum = riskDimensionDao.findDimbyDimId("ByNum");
			//RiskDimension wightBySituation = riskDimensionDao.findDimbyDimId("BySituation");
			//int wightScoreNum = wightByNum.getRdWeight();
			//int wightScoreSituation = wightBySituation.getRdWeight();
			double scoreN = 0;
			//double wight = 0;

			//wight = (double)wightScoreNum / (wightScoreNum+wightScoreSituation);
			//scoreN = rcList.get(total).getAssessResultQuarter() * wightScoreNum;
			scoreN = rcList.get(total).getAssessResultQuarter();
			rau.setAssessResultQuarterByNum(scoreN);
			rau.setDepName(rcList.get(total).getDepName());
			rau.setDepProperty(rcList.get(total).getDepProperty());
			rau.setTimeFrom(rcList.get(total).getTimeFrom());
			rgList.add(rau);
		}
		return rgList;
	}

	private List<RiskAssessSynthesizer> calcuteQuarterTogetherSSum(
			List<RiskAssessSituationView> rbvList) {
		List<RiskAssessSynthesizer> rhList = new ArrayList<RiskAssessSynthesizer>();
		rdList = calcuteQuarterBySituationSum(rbvList);
		int sizeS = rdList.size();
		for (int total = 0; total < sizeS; total++) {
			RiskAssessSynthesizer rau = new RiskAssessSynthesizer();

			//RiskDimension wightByNum = riskDimensionDao.findDimbyDimId("ByNum");
			//RiskDimension wightBySituation = riskDimensionDao.findDimbyDimId("BySituation");
			//int wightScoreNum = wightByNum.getRdWeight();
			//int wightScoreSituation = wightBySituation.getRdWeight();
			double scoreS = 0;
			//double wight = 0;

			//wight = (double)wightScoreSituation / (wightScoreNum+wightScoreSituation);
			//scoreS = rdList.get(total).getAssessResultQuarter() * wightScoreSituation;
			scoreS = rdList.get(total).getAssessResultQuarter();
			rau.setAssessResultQuarterBySituation(scoreS);
			rau.setDepName(rcList.get(total).getDepName());
			rau.setDepProperty(rcList.get(total).getDepProperty());
			rau.setTimeFrom(rdList.get(total).getTimeFrom());
			rhList.add(rau);
		}
		return rhList;
	}

	private List<RiskAssessSynthesizer> calcuteQuarterTogetherSum(
			List<RiskAssessSynthesizer> rgList,
			List<RiskAssessSynthesizer> rhList) {
		List<RiskAssessSynthesizer> rToList = new ArrayList<RiskAssessSynthesizer>();
		int j = 0;
		DecimalFormat df = new DecimalFormat("######0.00");
		
		RiskDimension wightByNum = riskDimensionDao.findDimbyDimId("ByNum");
		RiskDimension wightBySituation = riskDimensionDao.findDimbyDimId("BySituation");
		int wightScoreNum = wightByNum.getRdWeight();
		int wightScoreSituation = wightBySituation.getRdWeight();
		
		for (int i = 0; i < rgList.size(); i++) {
			RiskAssessSynthesizer rau = new RiskAssessSynthesizer();
			rau.setAssessResultQuarterByNum(Double.parseDouble(df.format(rgList.get(i).getAssessResultQuarterByNum())));
			rau.setAssessResultQuarterBySituation(Double.parseDouble(df.format(rhList.get(j).getAssessResultQuarterBySituation())));
			if (rgList.get(i).getDepName().equals(rhList.get(j).getDepName())) {

				rau.setAssessResultQuarter(Double.parseDouble(df.format(rgList.get(i).getAssessResultQuarterByNum() * wightScoreNum + rhList.get(j).getAssessResultQuarterBySituation() * wightScoreSituation)));
				if (j < rhList.size() - 1)
					j++;
			} else {

				rau.setAssessResultQuarter(Double.parseDouble(df.format(rgList.get(i).getAssessResultQuarterByNum() * wightScoreNum)));
			}

			rau.setDepName(rgList.get(i).getDepName());
			rau.setDepProperty(rgList.get(i).getDepProperty());
			// Year = reList.get(i).getYear();
			// Quarter = reList.get(i).getQuarter();
			rau.setTimeFrom(rgList.get(i).getTimeFrom());
			rToList.add(rau);
		}
		return rToList;
	}

	private String CurrentYear() {
		Calendar time = Calendar.getInstance();
		int currentYear;
		int currentQuarter = 0;
		int currentMonth;
		currentYear = time.get(Calendar.YEAR);
		currentMonth = time.get(Calendar.MONTH) + 1;
		switch (currentMonth) {
		case 1:
		case 2:
		case 3:
			currentQuarter += 1;
			break;
		case 4:
		case 5:
		case 6:
			currentQuarter += 2;
			break;
		case 7:
		case 8:
		case 9:
			currentQuarter += 3;
			break;
		case 10:
		case 11:
		case 12:
			currentQuarter += 4;
		}
		//if (currentQuarter == 1) {
			//currentQuarter = 4;
			//currentYear = currentYear - 1;
		//} else
			//currentQuarter = currentQuarter - 1;
		Integer integer = new Integer(currentYear);

		return integer.toString();
	}

	private String CurrentQuarter() {
		Calendar time = Calendar.getInstance();
		int currentQuarter = 0;
		int currentMonth;
		currentMonth = time.get(Calendar.MONTH) + 1;
		switch (currentMonth) {
		case 1:
		case 2:
		case 3:
			currentQuarter += 1;
			break;
		case 4:
		case 5:
		case 6:
			currentQuarter += 2;
			break;
		case 7:
		case 8:
		case 9:
			currentQuarter += 3;
			break;
		case 10:
		case 11:
		case 12:
			currentQuarter += 4;
		}
		//if (currentQuarter == 1) {
			//currentQuarter = 4;
			//currentYear = currentYear - 1;
		//} else
			//currentQuarter = currentQuarter - 1;
		Integer integer = new Integer(currentQuarter);
		return integer.toString();

	}

	private String SwitchQuarterFrom(String quarterFrom) {
		if (quarterFrom.equals("1"))
			quarterFrom = "01-01 00:00:00";

		if (quarterFrom.equals("2"))
			quarterFrom = "04-01 00:00:00";

		if (quarterFrom.equals("3"))
			quarterFrom = "07-01 00:00:00";

		if (quarterFrom.equals("4"))
			quarterFrom = "10-01 00:00:00";

		return quarterFrom;
	}

	private String SwitchQuarterTo(String quarterTo) {
		if (quarterTo.equals("1"))
			quarterTo = "03-31 23:59:59";

		if (quarterTo.equals("2"))
			quarterTo = "06-30 23:59:59";

		if (quarterTo.equals("3"))
			quarterTo = "09-30 23:59:59";

		if (quarterTo.equals("4"))
			quarterTo = "12-31 23:59:59";

		return quarterTo;
	}
}
