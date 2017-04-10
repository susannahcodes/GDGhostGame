package edu.virginia.lab1test;

import edu.virginia.engine.display.coinSprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.tween.TweenEvent;

public class QuestManager  implements IEventListener {

	public boolean questCompleted = false;
	public boolean tweenComplete = false;
	
	@Override
	public void handleEvent(Event event) {
		if (event.getEventType() == Event.COIN_PICKED_UP) {
			questCompleted = true;
		}
		
		if (event.getEventType() == TweenEvent.TWEEN_EVENT_COMPLETE) {
			tweenComplete = true;
		}
	}
	
}
