package com.model;

public class EventStaView3 implements java.io.Serializable{
	private EventStaViewId id;

	// Constructors

	/** default constructor */
	public EventStaView3() {
	}

	/** full constructor */
	public EventStaView3(EventStaViewId id) {
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
