package com.form;

public class ProcessForm implements Comparable<ProcessForm>{

	private String name;		//风险事件的名称或者风险报告的名称
	private String category;	//风险辨识的待办还是风险报告的待办
	private String depname;		//填写部门
	private String writername;	//填写人姓名
	private String flowstate;	//流转状态
	private String flowurl;		//点击流转状态的时候的链接
	private String modifydate;	//最后处理时间
	private String actionname;	//操作名称
	private String actionurl;	//操作的链接
	private String detailurl;	//查看详细信息的链接
	private String deleteurl;	//删除待办时候请求的url
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public String getWritername() {
		return writername;
	}
	public void setWritername(String writername) {
		this.writername = writername;
	}
	public String getFlowstate() {
		return flowstate;
	}
	public void setFlowstate(String flowstate) {
		this.flowstate = flowstate;
	}
	public String getFlowurl() {
		return flowurl;
	}
	public void setFlowurl(String flowurl) {
		this.flowurl = flowurl;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	public String getActionname() {
		return actionname;
	}
	public void setActionname(String actionname) {
		this.actionname = actionname;
	}
	public String getActionurl() {
		return actionurl;
	}
	public void setActionurl(String actionurl) {
		this.actionurl = actionurl;
	}
	public String getDetailurl() {
		return detailurl;
	}
	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}
	
	public String getDeleteurl() {
		return deleteurl;
	}
	public void setDeleteurl(String deleteurl) {
		this.deleteurl = deleteurl;
	}
	@Override
	public int compareTo(ProcessForm o) {
		
		if(this.modifydate == null) {
			return -1;
		}
		else if(o != null && o.getModifydate() == null) {
			return 1;
		}
		else {
			return this.modifydate.compareTo(o.getModifydate());
		}
	}
	
}
