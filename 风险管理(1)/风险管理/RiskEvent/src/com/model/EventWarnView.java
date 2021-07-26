package com.model;

/**
 * EventWarnView entity. @author MyEclipse Persistence Tools
 */

public class EventWarnView implements java.io.Serializable {

	// Fields
	private int chaZhi;
	private int stragyId;
	private String value;
	private String stragColor;
	private EventWarnViewId id;

	
	public int getStragyId() {
		return stragyId;
	}

	public void setStragyId(int stragyId) {
		this.stragyId = stragyId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStragColor() {
		return stragColor;
	}

	public void setStragColor(String stragColor) {
		this.stragColor = stragColor;
	}
	
	// Constructors

	/** default constructor */
	public EventWarnView() {
	}

	/** full constructor */
	public EventWarnView(EventWarnViewId id) {
		this.id = id;
	}

	// Property accessors
	

	public EventWarnViewId getId() {
		return this.id;
	}

	public int getChaZhi() {
		return chaZhi;
	}

	public void setChaZhi(int chaZhi) {
		this.chaZhi = chaZhi;
	}

	public void setId(EventWarnViewId id) {
		this.id = id;
	}

}