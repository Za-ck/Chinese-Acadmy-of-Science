package com.model;

public class EventStaView4 implements java.io.Serializable{
	private EventStaView2Id id;

	// Constructors

	/** default constructor */
	public EventStaView4() {
	}

	/** full constructor */
	public EventStaView4(EventStaView2Id id) {
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
