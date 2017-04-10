package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class enemySprite extends Sprite implements IEventListener {

	public enemySprite(String id) {
		super(id, "enemySprite.png");
		super.setXScale(.2);
		super.setYScale(.2);
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
	}

}

