package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class marioSprite extends Sprite implements IEventListener {

	public marioSprite(String id) {
		super(id, "mario.png");
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
	}

}
