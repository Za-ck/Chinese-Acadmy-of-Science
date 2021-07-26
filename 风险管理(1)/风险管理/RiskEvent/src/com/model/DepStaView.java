package com.model;

/**
 * DepStaView entity. @author MyEclipse Persistence Tools
 */

public class DepStaView implements java.io.Serializable {

	// Fields

	private DepStaViewId id;

	// Constructors

	/** default constructor */
	public DepStaView() {
	}

	/** full constructor */
	public DepStaView(DepStaViewId id) {
		this.id = id;
	}

	// Property accessors

	public DepStaViewId getId() {
		return this.id;
	}

	public void setId(DepStaViewId id) {
		this.id = id;
	}

}