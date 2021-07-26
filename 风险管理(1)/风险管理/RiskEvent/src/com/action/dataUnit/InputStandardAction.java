package com.action.dataUnit;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dao.InputStandardDAO;
import com.form.InputStandardform;
import com.model.InputStandard;



public class InputStandardAction {
	private Integer rdId;
	private String rdIdString;
	private String rdDepProperty;
	private Integer rdInputStandard;
	private String rdInputStandardString;
	private String actionName;
	private Integer id;
	private String depProporty;
	private Integer inputNeed;
	private String depProportyName;
	private List<InputStandardform> InputStandardList;
	
	private InputStandardDAO inputStandardDao;
	
	InputStandard inputStandard = new InputStandard();
	InputStandardform inputStandardform = new InputStandardform();

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepProporty() {
		return depProporty;
	}

	public void setDepProporty(String depProporty) {
		this.depProporty = depProporty;
	}

	public Integer getInputNeed() {
		return inputNeed;
	}

	public void setInputNeed(Integer inputNeed) {
		this.inputNeed = inputNeed;
	}

	public String getDepProportyName() {
		return depProportyName;
	}

	public void setDepProportyName(String depProportyName) {
		this.depProportyName = depProportyName;
	}

	public InputStandardform getInputStandardform() {
		return inputStandardform;
	}

	public void setInputStandardform(InputStandardform inputStandardform) {
		this.inputStandardform = inputStandardform;
	}

	public Integer getRdId() {
		return rdId;
	}

	public void setRdId(Integer rdId) {
		this.rdId = rdId;
	}

	public String getRdIdString() {
		return rdIdString;
	}

	public void setRdIdString(String rdIdString) {
		this.rdIdString = rdIdString;
	}

	public String getRdDepProperty() {
		return rdDepProperty;
	}

	public void setRdDepProperty(String rdDepProperty) {
		this.rdDepProperty = rdDepProperty;
	}

	public Integer getRdInputStandard() {
		return rdInputStandard;
	}

	public void setRdInputStandard(Integer rdInputStandard) {
		this.rdInputStandard = rdInputStandard;
	}

	public String getRdInputStandardString() {
		return rdInputStandardString;
	}

	public void setRdInputStandardString(String rdInputStandardString) {
		this.rdInputStandardString = rdInputStandardString;
	}

	public List<InputStandardform> getInputStandardList() {
		return InputStandardList;
	}

	public void setInputStandardList(List<InputStandardform> inputStandardList) {
		InputStandardList = inputStandardList;
	}

	public InputStandardDAO getInputStandardDao() {
		return inputStandardDao;
	}

	public void setInputStandardDao(InputStandardDAO inputStandardDao) {
		this.inputStandardDao = inputStandardDao;
	}

	public InputStandard getInputStandard() {
		return inputStandard;
	}

	public void setInputStandard(InputStandard inputStandard) {
		this.inputStandard = inputStandard;
	}
	
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@SuppressWarnings("unchecked")
	public String InputStandardManage() {
		try{
		List<InputStandard> ravList = new LinkedList<InputStandard>();
		List<InputStandardform> InputStandardList = new LinkedList<InputStandardform>();
		ravList = this.getInputStandardDao().findAll();
		for(int i=0;i<ravList.size();i++){
			InputStandardform rau = new InputStandardform();
			if(ravList.get(i).getRdDepProperty().equals("0"))
				rau.setDepProporty("生产部门");
			else
				rau.setDepProporty("管理部门");
				rau.setInputNeed(ravList.get(i).getRdInputStandard());
				rau.setId(ravList.get(i).getRdId());
				InputStandardList.add(rau);
		}
		ServletActionContext.getRequest().setAttribute("InputStandardList", InputStandardList);
		this.setActionName("riskAssess/riskAssessByNumAction");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "fail";
	}
	return "success";
	}

	// 编辑
	public String standardAddUpdate() {
			try{
				inputStandard = this.inputStandardDao.findById(this.getId());
			inputStandard.setRdInputStandard(Integer.parseInt(this.getRdInputStandardString()));
			this.getInputStandardDao().merge(inputStandard);
			InputStandardManage();
			}catch(Exception e){
				e.printStackTrace();
				return "fail";
			}
			return "success";
	}

	// 编辑前显示要编辑的对象的信息
	public String standardUpdatePrepare() {
		try{
			inputStandard = this.inputStandardDao.findById(this.getId());
			this.depProporty=inputStandard.getRdDepProperty();
			if(this.depProporty.equals("0"))
				this.depProportyName = "生产部门";
			else
				this.depProportyName = "管理部门";
			this.inputNeed = inputStandard.getRdInputStandard();
			this.id = inputStandard.getRdId();
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
}
