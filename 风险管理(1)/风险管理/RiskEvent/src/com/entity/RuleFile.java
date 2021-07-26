package com.entity;

import java.io.Serializable;

public class RuleFile implements Serializable{
	private String ruleName;
	private String eventNum;
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getEventNum() {
		return eventNum;
	}
	public void setEventNum(String eventNum) {
		this.eventNum = eventNum;
	}
	

}
