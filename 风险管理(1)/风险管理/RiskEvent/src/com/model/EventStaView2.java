package com.model;

// default package

/**
 * EventStaView2 entity. @author MyEclipse Persistence Tools
 */

public class EventStaView2 implements java.io.Serializable {

	// Fields

	private EventStaView2Id id;

	// Constructors

	/** default constructor */
	public EventStaView2() {
	}

	/** full constructor */
	public EventStaView2(EventStaView2Id id) {
		this.id = id;
	}

	// Property accessors

	public EventStaView2Id getId() {
		return this.id;
	}

	public void setId(EventStaView2Id id) {
		this.id = id;
	}

}