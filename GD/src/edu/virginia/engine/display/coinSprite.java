package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class coinSprite extends Sprite {

	public coinSprite(String id) {
		super(id, "Star_Coin.png");
		super.setXScale(.01);
		super.setYScale(.01);
	}
}
