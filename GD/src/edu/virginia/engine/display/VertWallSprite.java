package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class VertWallSprite extends Sprite {

	public VertWallSprite(String id) {
		super(id, "platformVert.png");
		super.setXScale(.1);
		super.setYScale(.1);
	}
}