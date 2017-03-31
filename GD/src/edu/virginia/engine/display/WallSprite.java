package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class WallSprite extends Sprite {

	public WallSprite(String id) {
		super(id, "platform.png");
		//super.setXScale(.1);
		//super.setYScale(.1);
	}
}
