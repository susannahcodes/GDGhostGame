package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class LongWallSprite extends Sprite {

	public LongWallSprite(String id) {
		super(id, "platform.jpg");
		super.setXScale(3.27);
		//super.setYScale(1.9);
	}
}