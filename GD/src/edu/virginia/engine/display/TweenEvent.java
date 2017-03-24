package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;

public class TweenEvent extends Event{
	public final static String TWEEN_EVENT_COMPLETE = "Tween event complete";
	//private String tweenEvType;
	private Tween theSource;
	
	public  TweenEvent(String event, Tween source){
		super(event);
		//tweenEvType = event;
		theSource = source;
	}
	
	public Tween getTween() {
		return this.theSource;
	}
}
