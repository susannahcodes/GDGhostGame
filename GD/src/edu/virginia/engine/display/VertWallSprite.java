package edu.virginia.engine.display;

import java.awt.Rectangle;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class VertWallSprite extends Sprite {

	public VertWallSprite(String id) {
		super(id, "updownwall.png");
		super.setYScale(1.9);
	}
}