package edu.virginia.engine.display;

import java.util.ArrayList;

/**
 * Nothing in this class (yet) because there is nothing specific to a Sprite yet that a DisplayObject
 * doesn't already do. Leaving it here for convenience later. you will see!
 * */
public class Sprite extends DisplayObjectContainer {
	
	private int xPos;
	private int yPos;
	//private int dx;
	//private int dy;

	public Sprite(String id) {
		super(id);
		//setPivot();
		//xPos = 0;
		//yPos = 0;
	}

	public Sprite(String id, String imageFileName) {
		super(id, imageFileName);
	}
	
	@Override
	public void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
	}
	/*
	public void setPivot() {
		this.setXPiv(this.getUnscaledWidth()/2);
		this.setYPiv(this.getUnscaledHeight()/2);
	}
	*/
	
}