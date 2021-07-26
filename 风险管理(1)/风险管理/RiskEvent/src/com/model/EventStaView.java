package com.model;

/**
 * EventStaView entity. @author MyEclipse Persistence Tools
 */

public class EventStaView implements java.io.Serializable {

	// Fields

	private EventStaViewId id;

	// Constructors

	/** default constructor */
	public EventStaView() {
	}

	/** full constructor */
	public EventStaView(EventStaViewId id) {
		this.id = id;
	}

	// Property accessors

	public EventStaViewId getId() {
		return this.id;
	}

	public void setId(EventStaViewId id) {
		this.id = id;
	}

}