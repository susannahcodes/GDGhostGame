package edu.virginia.engine.display;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class cherrySprite extends Sprite {
	public cherrySprite(String id) {
		super(id, "cherry.png");
		super.setXScale(0.18);
		super.setYScale(0.18);
	}
}
