package com.model;

public class DepStaView1 implements java.io.Serializable{
	private DepStaViewId id;

	// Constructors

	/** default constructor */
	public DepStaView1() {
	}

	/** full constructor */
	public DepStaView1(DepStaViewId id) {
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
