package com.model;

/**
 * LinkStaView2 entity. @author MyEclipse Persistence Tools
 */

public class LinkStaView2 implements java.io.Serializable {

	// Fields

	private LinkStaView2Id id;

	// Constructors

	/** default constructor */
	public LinkStaView2() {
	}

	/** full constructor */
	public LinkStaView2(LinkStaView2Id id) {
		this.id = id;
	}

	// Property accessors

	public LinkStaView2Id getId() {
		return this.id;
	}

	public void setId(LinkStaView2Id id) {
		this.id = id;
	}

}