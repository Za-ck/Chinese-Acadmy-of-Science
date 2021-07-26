package com.entity;

import javax.persistence.Basic;

public class depStatistic {
	private String reIndep;
	private Float money;
	private String year;
	private Integer quarter;
	private Integer num;
	private String rtName;
	private Float eventHun;
	private Float moneyHun;
	
	public String getReIndep() {
		return reIndep;
	}
	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}
	@Basic
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getQuarter() {
		return quarter;
	}
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getRtName() {
		return rtName;
	}
	public void setRtName(String rtName) {
		this.rtName = rtName;
	}
	public Float getEventHun() {
		return eventHun;
	}
	public void setEventHun(Float eventHun) {
		this.eventHun = eventHun;
	}
	public Float getMoneyHun() {
		return moneyHun;
	}
	public void setMoneyHun(Float moneyHun) {
		this.moneyHun = moneyHun;
	}
	
	
}
