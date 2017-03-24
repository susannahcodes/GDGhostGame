package edu.virginia.lab1test;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabThreeGame2 extends Game {

	Sprite theSun = new Sprite("Sun", "sun.png");
	private int dy = 10;
	
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabThreeGame2() {
		super("lab three ugh", 1200, 800);
		theSun.setXScale(0.05);
		theSun.setYScale(0.05);
		theSun.setXPos((this.getScenePanel().getWidth()/2));
		theSun.setYPos((this.getScenePanel().getHeight()/2));
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<String> pressedKeys){
		super.update(pressedKeys);
		
		/* Make sure theSun is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if(theSun != null) theSun.update(pressedKeys);
		
		if (pressedKeys.contains(KeyEvent.getKeyText(38))) {
			if ( !(theSun.getYPos() - dy < -20) )
				theSun.setYPos(theSun.getYPos()-dy );
		}
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		//theSun.draw(g);
		
		/* Same, just check for null in case a frame gets thrown in before theSun is initialized */
		if(theSun != null) theSun.draw(g);
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabThreeGame2 game = new LabThreeGame2();
		game.start();

	}
}
