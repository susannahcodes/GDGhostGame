package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class healthBarSprite extends Sprite {

	public healthBarSprite(String id) {
		super(id, "healthbar.png");
		super.setXScale(.45);
		super.setYScale(.5);
	}
}
