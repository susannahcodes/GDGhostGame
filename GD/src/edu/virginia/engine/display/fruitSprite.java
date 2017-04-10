package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class fruitSprite extends Sprite {

	public fruitSprite(String id) {
		super(id, "orange.png");
		super.setXScale(.4);
		super.setYScale(.5);
	}
}
