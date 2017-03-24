package edu.virginia.engine.events;

import edu.virginia.engine.display.DisplayObjectContainer;

public class Event {
	private String eventType;
	//private EventDispatcher source;
	private DisplayObjectContainer source;
	
	public static final String COIN_PICKED_UP = "Coin has been picked up";
	public static final String COLLISION = "A collision has occurred";
	
	public Event(String theType) {
		eventType = theType;
	}
	
	public Event(String theType, DisplayObjectContainer theSource) {
		eventType = theType;
		source = theSource;
	}

	public DisplayObjectContainer getSource() {
		return source;
	}	
	
	public String getEventType() {
		return eventType;
	}
}
