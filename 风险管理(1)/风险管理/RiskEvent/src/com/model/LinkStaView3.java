package com.model;

/**
 * LinkStaView entity. @author MyEclipse Persistence Tools
 */

public class LinkStaView3 implements java.io.Serializable {

	// Fields

	private LinkStaViewId id;

	// Constructors

	/** default constructor */
	public LinkStaView3() {
	}

	/** full constructor */
	public LinkStaView3(LinkStaViewId id) {
		this.id = id;
	}

	// Property accessors

	public LinkStaViewId getId() {
		return this.id;
	}

	public void setId(LinkStaViewId id) {
		this.id = id;
	}

}