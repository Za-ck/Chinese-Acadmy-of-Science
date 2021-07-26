package com.model;


public class AllEventManageView {
	// Fields

	private AllEventManageViewId id;
	private String riskId;
	private String indepid;   //部门编号
	private String depName;   //录入部门
	private String reIndep;
	private String reState;   //审核状态
	private String reDate;    //录入日期
	private String reCreator;  //录入人
	private String reModifydate; //修改日期
	private String reModifier;//修改人
	private String riInchargedep; //责任部门
	private String reAct;
	private String reEventname;	//风险事件名称
	private int reCheckflag;	//是否已考核的标志
	
	public AllEventManageView() {
	}
	public AllEventManageView(AllEventManageViewId id) {
		this.id = id;
	}
	
	public AllEventManageViewId getId() {
		return id;
	}

	public void setId(AllEventManageViewId id) {
		this.id = id;
	}
	
	public String getReEventname() {
		return reEventname;
	}
	public void setReEventname(String reEventname) {
		this.reEventname = reEventname;
	}
	public String getRiskId() {
		return riskId;
	}
	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getIndepid() {
		return indepid;
	}
	public void setIndepid(String indepid) {
		this.indepid = indepid;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	
	public String getReIndep() {
		return reIndep;
	}
	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}
	public String getReState() {
		return reState;
	}
	public void setReState(String reState) {
		this.reState = reState;
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
	public String getReModifydate() {
		return reModifydate;
	}
	public void setReModifydate(String reModifydate) {
		this.reModifydate = reModifydate;
	}
	public String getReModifier() {
		return reModifier;
	}
	public void setReModifier(String reModifier) {
		this.reModifier = reModifier;
	}

	public String getRiInchargedep() {
		return riInchargedep;
	}

	public void setRiInchargedep(String riInchargedep) {
		this.riInchargedep = riInchargedep;
	}

	public String getReAct() {
		return reAct;
	}
	public void setReAct(String reAct) {
		this.reAct = reAct;
	}
	public int getReCheckflag() {
		return reCheckflag;
	}
	public void setReCheckflag(int reCheckflag) {
		this.reCheckflag = reCheckflag;
	}
	
}
