package com.action.riskInput;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.RiskDAO;
import com.dao.RiskEventDAO;
import com.dao.RiskManageDAO;
import com.model.Risk;
import com.model.RiskHandle;
import com.model.Users;

public class RiskHandleAction {

	private String rhId;
	private List<RiskHandle> rhList;
	private RiskEventDAO riskEventDao;
	private RiskManageDAO riskManageDao;
	private RiskDAO riskDao;
	private int current_pagenum = 1;// 当前页码
	private int pageNum = 10;// 每页的显示数据记录数
	private List<Risk> allriskList;
	public String dateFrom = "";
	public String dateTo = "";
	private String riskId;
	private String handle;
	private String actionName;

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public RiskDAO getRiskDao() {
		return riskDao;
	}

	public void setRiskDao(RiskDAO riskDao) {
		this.riskDao = riskDao;
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

	public List<Risk> getAllriskList() {
		return allriskList;
	}

	public void setAllriskList(List<Risk> allriskList) {
		this.allriskList = allriskList;
	}

	public List<RiskHandle> getRhList() {
		return rhList;
	}

	public void setRhList(List<RiskHandle> rhList) {
		this.rhList = rhList;
	}

	public RiskEventDAO getRiskEventDao() {
		return riskEventDao;
	}

	public void setRiskEventDao(RiskEventDAO riskEventDao) {
		this.riskEventDao = riskEventDao;
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

	public RiskManageDAO getRiskManageDao() {
		return riskManageDao;
	}

	public void setRiskManageDao(RiskManageDAO riskManageDao) {
		this.riskManageDao = riskManageDao;
	}

	public String getRhId() {
		return rhId;
	}

	public void setRhId(String rhId) {
		this.rhId = rhId;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String rhQuery() {
		try {
			getDropDownList();
			this.rhList = new LinkedList<RiskHandle>();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			List rList = this.getRiskEventDao().findhandlerisk(us.getUserDep(),
					(current_pagenum - 1) * pageNum, pageNum);
			ServletActionContext.getRequest().getSession().setAttribute(
					"current_pagenum", current_pagenum);

			Object[] temp;
			for (int i = 0; i < rList.size(); i++) {
				temp = (Object[]) rList.get(i);
				
				RiskHandle rch = new RiskHandle();
				rch.setReId(temp[0].toString());
				rch.setState(temp[1].toString());
				rch.setCreator(temp[2].toString());
				rch.setDate(temp[3].toString());
				rch.setPlandate(temp[4].toString());
				rch.setTaketime(temp[5].toString());
				rch.setSign(temp[6].toString());
				this.rhList.add(rch);

			}
			this.actionName = "riskInput/RiskHandleQueryAction";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 点击“处理”，更新riskmanage的sign字段，1表示已实施应对措施，0表示未实施
	public String riskhandle() {
		try {
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			//System.out.println(us.getUserName());
			String name = us.getUserName();
			this.getRiskManageDao().updatehandled(1, this.rhId,df.format(date).toString(),name);
			rhQuery();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	public String risknothandle() {
		try {
			//System.out.printf("here");
			this.getRiskManageDao().updatehandled(0, this.rhId, "","");
			rhQuery();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 得到下拉框，风险类型下拉框risktypeList
	public String getDropDownList() {
		try {
			// 风险名称下拉框allriskList
			Risk r1 = new Risk();
			r1.setRiskId("0");
			r1.setRiskName("--请选择--");
			this.allriskList = new LinkedList<Risk>();
			this.allriskList.add(r1);
			List<Risk> riskList = new LinkedList<Risk>();
			riskList = this.getRiskDao().findAll();
			this.allriskList.addAll(riskList);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	// 高级查询，查询条件:日期始dateFrom、日期终dateTo、风险编号riskId
	public String rhAdvancedQuery() {
		try {

			this.rhList = new LinkedList<RiskHandle>();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			this.setRiskId("0");
			List rList = this.getRiskEventDao().findhandlebyCondition(
					us.getUserDep(), this.getDateFrom(), this.getDateTo(),
					this.getRiskId(), this.getHandle(),
					(current_pagenum - 1) * pageNum, pageNum);

			Object[] temp;
			for (int i = 0; i < rList.size(); i++) {
				temp = (Object[]) rList.get(i);
				//System.out.printf(temp[0].toString());

				// System.out.printf(rchList[0][0]);
				RiskHandle rch = new RiskHandle();
				rch.setReId(temp[0].toString());
				rch.setState(temp[1].toString());
				rch.setCreator(temp[2].toString());
				rch.setDate(temp[3].toString());
				rch.setPlandate(temp[4].toString());
				rch.setTaketime(temp[5].toString());
				rch.setSign(temp[6].toString());
				this.rhList.add(rch);

			}
			ServletActionContext.getRequest().setAttribute("rhList", rhList);
			ServletActionContext.getRequest().getSession().setAttribute(
					"current_pagenum", current_pagenum);
			this.actionName = "riskInput/REHQueryAction";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
}
