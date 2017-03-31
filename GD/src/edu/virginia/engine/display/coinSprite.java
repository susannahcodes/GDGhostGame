package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class coinSprite extends Sprite {

	public coinSprite(String id) {
		super(id, "orange.png");
		super.setXScale(.45);
		super.setYScale(.5);
	}
}
