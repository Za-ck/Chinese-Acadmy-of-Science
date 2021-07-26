package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.dao.ClientDAO;
import com.dao.EmployeeDAO;
import com.dao.EventStaViewDAO;
import com.dao.FinanceDAO;
import com.dao.LawDAO;
import com.dao.OperationDAO;
import com.dao.ProbabilityDAO;
import com.dao.ReputationDAO;
import com.dao.RiskDAO;
import com.dao.RiskEventDAO;
import com.dao.SafeDAO;
import com.model.Client;
import com.model.Employee;
import com.model.EventStaView;
import com.model.Finance;
import com.model.Law;
import com.model.Operation;
import com.model.Probability;
import com.model.Reputation;
import com.model.Risk;
import com.model.RiskEvent;
import com.model.Safe;
import com.opensymphony.xwork2.Action;

public class AjaxRiskAction implements Action, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tmprisk;
	private String finId;
	private String proId;
	private String repId;
	private String lawId;
	private String cliId;
	private String empId;
	private String opeId;
	private String safId;
	private Finance finance;
	private Probability probability;
	private Reputation reputation;
	private Law law;
	private Client client;
	private Employee employee;
	private Operation operation;
	private Safe safe;
	private String tempriskid;
	private List<RiskEvent> reList;
	private List<Risk> rList;
	private List<EventStaView> eventStaViewList;
	private String temprisktypeid;

	private RiskDAO riskDao;
	private FinanceDAO financeDao;
	private ProbabilityDAO probabilityDao;
	private ReputationDAO reputationDao;
	private LawDAO lawDao;
	private ClientDAO clientDao;
	private EmployeeDAO employeeDao;
	private OperationDAO operationDao;
	private SafeDAO safeDao;
	private RiskEventDAO riskeventDao;
	private EventStaViewDAO eventStaViewDAO;

	public String getTemprisktypeid() {
		return temprisktypeid;
	}

	public void setTemprisktypeid(String temprisktypeid) {
		this.temprisktypeid = temprisktypeid;
	}

	public String getTmprisk() {
		return tmprisk;
	}

	public void setTmprisk(String tmprisk) {
		this.tmprisk = tmprisk;
	}

	public String getFinId() {
		return finId;
	}

	public void setFinId(String finId) {
		this.finId = finId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getRepId() {
		return repId;
	}

	public void setRepId(String repId) {
		this.repId = repId;
	}

	public String getLawId() {
		return lawId;
	}

	public void setLawId(String lawId) {
		this.lawId = lawId;
	}

	public String getCliId() {
		return cliId;
	}

	public void setCliId(String cliId) {
		this.cliId = cliId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getOpeId() {
		return opeId;
	}

	public void setOpeId(String opeId) {
		this.opeId = opeId;
	}

	public String getSafId() {
		return safId;
	}

	public void setSafId(String safId) {
		this.safId = safId;
	}

	public Finance getFinance() {
		return finance;
	}

	public void setFinance(Finance finance) {
		this.finance = finance;
	}

	public Probability getProbability() {
		return probability;
	}

	public void setProbability(Probability probability) {
		this.probability = probability;
	}

	public Reputation getReputation() {
		return reputation;
	}

	public void setReputation(Reputation reputation) {
		this.reputation = reputation;
	}

	public Law getLaw() {
		return law;
	}

	public void setLaw(Law law) {
		this.law = law;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Safe getSafe() {
		return safe;
	}

	public void setSafe(Safe safe) {
		this.safe = safe;
	}

	public List<Risk> getrList() {
		return rList;
	}

	public void setrList(List<Risk> rList) {
		this.rList = rList;
	}

	public RiskDAO getRiskDao() {
		return riskDao;
	}

	public void setRiskDao(RiskDAO riskDao) {
		this.riskDao = riskDao;
	}

	public FinanceDAO getFinanceDao() {
		return financeDao;
	}

	public void setFinanceDao(FinanceDAO financeDao) {
		this.financeDao = financeDao;
	}

	public ProbabilityDAO getProbabilityDao() {
		return probabilityDao;
	}

	public void setProbabilityDao(ProbabilityDAO probabilityDao) {
		this.probabilityDao = probabilityDao;
	}

	public ReputationDAO getReputationDao() {
		return reputationDao;
	}

	public void setReputationDao(ReputationDAO reputationDao) {
		this.reputationDao = reputationDao;
	}

	public LawDAO getLawDao() {
		return lawDao;
	}

	public void setLawDao(LawDAO lawDao) {
		this.lawDao = lawDao;
	}

	public ClientDAO getClientDao() {
		return clientDao;
	}

	public void setClientDao(ClientDAO clientDao) {
		this.clientDao = clientDao;
	}

	public EmployeeDAO getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}

	public OperationDAO getOperationDao() {
		return operationDao;
	}

	public void setOperationDao(OperationDAO operationDao) {
		this.operationDao = operationDao;
	}

	public SafeDAO getSafeDao() {
		return safeDao;
	}

	public void setSafeDao(SafeDAO safeDao) {
		this.safeDao = safeDao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTempriskid() {
		return tempriskid;
	}

	public void setTempriskid(String tempriskid) {
		this.tempriskid = tempriskid;
	}

	public RiskEventDAO getRiskeventDao() {
		return riskeventDao;
	}

	public void setRiskeventDao(RiskEventDAO riskeventDao) {
		this.riskeventDao = riskeventDao;
	}

	public List<RiskEvent> getReList() {
		return reList;
	}

	public void setReList(List<RiskEvent> reList) {
		this.reList = reList;
	}

	public List<EventStaView> getEventStaViewList() {
		return eventStaViewList;
	}

	public void setEventStaViewList(List<EventStaView> eventStaViewList) {
		this.eventStaViewList = eventStaViewList;
	}

	public EventStaViewDAO getEventStaViewDAO() {
		return eventStaViewDAO;
	}

	public void setEventStaViewDAO(EventStaViewDAO eventStaViewDAO) {
		this.eventStaViewDAO = eventStaViewDAO;
	}

	public void ajaxRisk() {
		try {
			rList = new LinkedList<Risk>();

			List<Risk> risks = riskDao.findbyInputTypeInput(this.tmprisk);
			if (risks != null) {
				for (Risk r : risks) {
					Risk rs2 = new Risk();
					rs2.setRiskId(r.getRiskId());
					rs2.setRiskName(r.getRiskName());
					rList.add(rs2);
				}
			}
			JSONArray ajaxarray = JSONArray.fromObject(rList);
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse(response, ajaxarray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("chu cuo");
			e.printStackTrace();
			HttpServletResponse response = ServletActionContext.getResponse();
			writeToResponse(response, null);
		}
	}

	public void ajaxEvent() {
		try {
			String str = "0001";
			reList = riskeventDao.findByEventIdDESC(this.tempriskid);
			int event = 0;
			if (reList.size() != 0) {
				String pre = reList.get(0).getReEventId();
//				String maxreid = reList.get(0).getReId();
//				String pre = maxreid.substring(maxreid.lastIndexOf("-")+1);
				event = Integer.parseInt(pre);
				event = event + 1;
				str = String.valueOf(event);
				int d = pre.length() - str.length();

				for (int i = 0; i < d; i++) {
					str = "0" + str;
				}
			}

			List list = new ArrayList();
			list.add(str);
			JSONArray ajaxarray = JSONArray.fromObject(list);
			HttpServletResponse response = ServletActionContext.getResponse();

			writeToResponse(response, ajaxarray);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// RiskImpact录入——财务影响
	public void ajaxFin() {
		this.setFinance(this.getFinanceDao().findById(
				Integer.parseInt(this.getFinId())));

		JSONObject ajaxarray = JSONObject.fromObject(this.getFinance());
		HttpServletResponse response = ServletActionContext.getResponse();
		writeToResponse2(response, ajaxarray);
	}

	// RiskImpact录入——发生可能性影响
	public void ajaxPro() {
		this.setProbability(this.getProbabilityDao().findById(
				Integer.parseInt(this.getProId())));
		JSONObject ajaxarray = JSONObject.fromObject(this.getProbability());
		HttpServletResponse response = ServletActionContext.getResponse();
		writeToResponse2(response, ajaxarray);
	}

	// RiskImpact录入——我院声誉影响
	public void ajaxRep() {
		this.setReputation(this.getReputationDao().findById(
				Integer.parseInt(this.getRepId())));
		JSONObject ajaxarray = JSONObject.fromObject(this.getReputation());
		HttpServletResponse response = ServletActionContext.getResponse();
		writeToResponse2(response, ajaxarray);
	}

	// RiskImpact录入——法律法规影响
	public void ajaxLaw() {
		this.setLaw(this.getLawDao()
				.findById(Integer.parseInt(this.getLawId())));
		JSONObject ajaxarray = JSONObject.fromObject(this.getLaw());
		HttpServletResponse response = ServletActionContext.getResponse();
		writeToResponse2(response, ajaxarray);
	}

	// RiskImpact录入——客户关系影响
	public void ajaxCli() {
		this.setClient(this.getClientDao().findById(
				Integer.parseInt(this.getCliId())));
		JSONObject ajaxarray = JSONObject.fromObject(this.getClient());
		HttpServletResponse response = ServletActionContext.getResponse();
		writeToResponse2(response, ajaxarray);
	}

	// RiskImpact录入——员工满意度影响
	public void ajaxEmp() {
		this.setEmployee(this.getEmployeeDao().findById(
				Integer.parseInt(this.getEmpId())));
		JSONObject ajaxarray = JSONObject.fromObject(this.getEmployee());
		HttpServletResponse response = ServletActionContext.getResponse();
		writeToResponse2(response, ajaxarray);
	}

	// RiskImpact录入——运营影响
	public void ajaxOpe() {
		this.setOperation(this.getOperationDao().findById(
				Integer.parseInt(this.getOpeId())));
		JSONObject ajaxarray = JSONObject.fromObject(this.getOperation());
		HttpServletResponse response = ServletActionContext.getResponse();
		writeToResponse2(response, ajaxarray);
	}

	// RiskImpact录入——安全健康环境影响
	public void ajaxSaf() {

		this.setSafe(this.getSafeDao().findById(
				Integer.parseInt(this.getSafId())));
		JSONObject ajaxarray = JSONObject.fromObject(this.getSafe());
		HttpServletResponse response = ServletActionContext.getResponse();
		writeToResponse2(response, ajaxarray);
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

	public void writeToResponse2(HttpServletResponse response,
			JSONObject jsonObj) {
		PrintWriter out = null;
		response.setContentType("text/html;charset=utf-8");
		try {
			out = response.getWriter();
			out.print(jsonObj.toString());
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void ajaxRiskNum() {
		try {

			rList = riskDao.findByInputTypeDESC(this.temprisktypeid);
			String str = "0001";
			int risknum = 0;
			if (rList.size() != 0) {
				String pre = rList.get(0).getRiskId();
				risknum = Integer.parseInt(pre.substring(3, 7));
				risknum = risknum + 1;
				str = String.valueOf(risknum);
				int d = 4 - str.length();
				for (int i = 0; i < d; i++) {
					str = "0" + str;
				}
			}

			List list = new ArrayList();
			list.add(str);
			JSONArray ajaxarray = JSONArray.fromObject(list);
			HttpServletResponse response = ServletActionContext.getResponse();

			writeToResponse(response, ajaxarray);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
