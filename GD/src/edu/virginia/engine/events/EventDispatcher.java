//WHAT ARE THESE STRINGS
package edu.virginia.engine.events;

import java.util.ArrayList;

public class EventDispatcher implements IEventDispatcher {
	
	private ArrayList<IEventListener> eventListeners = new ArrayList<IEventListener>();

	@Override
	public void addEventListener(IEventListener listener, String eventType) {
		eventListeners.add(listener);    //wtf else am I doing here	 WHY IS THERE A STRING
	}

	@Override
	public IEventListener removeEventListener(IEventListener listener, String eventType) {
		
		if (eventListeners.contains(listener)) {
			eventListeners.remove(listener);
			return listener;
		}
		return null;
	}

	@Override
	public void dispatchEvent(Event event) {
		
		for ( IEventListener listener : eventListeners ) {
			listener.handleEvent(event);
		}	
	}

	@Override
	public boolean hasEventListener(IEventListener listener, String eventType) {
		return eventListeners.contains(listener);
	}
	
	//public void dispatchEvent

}
