//the pivot point is set correctly for earth but not the sun

package edu.virginia.lab1test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabThreeGame extends Game{

	Sprite theSun = new Sprite("Sun", "sun.png");
	Sprite sunChild1 = new Sprite("SunChild1", "sun.png");
	Sprite sunChild2 = new Sprite("SunChild2", "sun.png");
	Sprite sunChild3 = new Sprite("SunChild3", "sun.png");
	
	Sprite Venus = new Sprite("Venus", "venus.png");
	Sprite Jupiter = new Sprite("Jupiter", "jupiter.png");
	
	Sprite Earth = new Sprite("Earth", "earth.png");
	Sprite Moon = new Sprite("Moon", "moon.png");

	private int dy = 7;
	private int dx = 7;
	
	private int ellipse = -10;
	private int counter = 0;
	
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabThreeGame() {
		super("Lab One Test Game", 1200, 800);

		this.getScenePanel().setBackground(Color.BLACK);
		
		theSun.setXScale(0.05);
		theSun.setYScale(0.05);
		theSun.setXPos((this.getScenePanel().getWidth()/2)-60);
		theSun.setYPos((this.getScenePanel().getHeight()/2)-60);
		
		theSun.add(sunChild1);
		theSun.add(sunChild2);
		theSun.add(sunChild3);
		
		sunChild1.add(Venus);
		sunChild2.add(Earth);
		sunChild3.add(Jupiter);
		
		Jupiter.setXScale(7.0);
		Jupiter.setYScale(7.0);
		
		Earth.add(Moon);
		Earth.setXScale(0.75);
		Earth.setYScale(0.75);
		Moon.setXScale(0.5);
		Moon.setYScale(0.5);
		
		sunChild1.setTrans(0.0f);
		sunChild2.setTrans(0.0f);
		sunChild3.setTrans(0.0f);
		
		int offset = 3000;
		
		for (DisplayObjectContainer child : theSun.getChildren() ) {
			for (DisplayObjectContainer grandchild : child.getChildren()) {
				grandchild.setXPos(grandchild.getParent().getXPos()+offset);
				offset += 2000;
			}
		}
		
		
		Moon.setXPos(Earth.getYPos()-200);
		
		/*
		Moon.setXPiv(Earth.getXpiv()+Earth);
		Moon.setYPiv(Earth.getYpiv());
		
		System.out.println(Moon.getXpiv());
		System.out.println(Earth.getXpiv());
		
		
		//sunChild1.setXPos(theSun.getXPos());
		//sunChild1.setYPos(theSun.getYPos());
		//Earth.setXPos(theSun.getXPos()+4000);
		 
		 */

	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
		
		theSun.setRotation(theSun.getRotation()+Math.PI/600);

		sunChild1.setRotation(sunChild1.getRotation()+Math.PI/300);
		sunChild2.setRotation(sunChild2.getRotation()+Math.PI/600);
		sunChild3.setRotation(sunChild3.getRotation()+Math.PI/800);
		
		Earth.setRotation(Earth.getRotation()+Math.PI/600);
		//Moon.setRotation(Moon.getRotation()+Math.PI/300);
		
		sunChild1.setXPos(sunChild1.getXPos()+ellipse);
		sunChild2.setXPos(sunChild1.getXPos()+ellipse);
		sunChild3.setXPos(sunChild1.getXPos()+ellipse);
		//Venus.setXPos(sunChild1.getXPos()+ellipse);
		
		
		if (pressedKeys.contains(KeyEvent.getKeyText(65))) {
			theSun.setRotation(theSun.getRotation()+0.05);
		}
		
		
		if (pressedKeys.contains(KeyEvent.getKeyText(83))) {
			theSun.setRotation(theSun.getRotation()-0.05);
		}
		
		
		if (pressedKeys.contains(KeyEvent.getKeyText(87))) {
			if (theSun.getXScale()-0.01 > 0 ) {
				theSun.setXScale(theSun.getXScale()-0.01);
				theSun.setYScale(theSun.getYScale()-0.01);
			}
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(81))) {
			theSun.setXScale(theSun.getXScale()+0.01);
			theSun.setYScale(theSun.getYScale()+0.01);
		}
		
		
		if (pressedKeys.contains(KeyEvent.getKeyText(38))) {
			if ( !(theSun.getYPos() - dy < -20) ) {
				theSun.setYPos(theSun.getYPos()-dy);
			}
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(40))) {
			if ( !(theSun.getYPos() + dy > this.getScenePanel().getHeight() - 20))
			theSun.setYPos(theSun.getYPos() + dy);
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(39))) {
			if ( !(theSun.getXPos() + dx > this.getScenePanel().getWidth() - 60))
			theSun.setXPos(theSun.getXPos() + dx);
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(37))) {
			if ( !(theSun.getXPos() - dx < -20) )
			theSun.setXPos(theSun.getXPos() - dx);
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(73))) {		
			Earth.setYPos(Earth.getYPos()-15);
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(74))) {
			Earth.setXPos(Earth.getXPos()-15);
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(75))) {
			Earth.setYPos(Earth.getYPos()+15);
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(76))) {
			Earth.setXPos(Earth.getXPos()+15);
		}
		
		if ( ((counter%150 == 0) && (counter%300 != 0)) ) {
			ellipse = ellipse*-1;
			//System.out.println(ellipse);
		}
		
		if ( counter%150 == 0 ) {
			//System.out.println(ellipse);
		}
		
		/*
		if ( counter%300 == 0 ) {
			ellipse = ellipse*-1;
			System.out.println(ellipse);
		}
		*/
		
		counter++;
		
		/*
		//theSun.setRotation(theSun.getRotation()+Math.PI/300);
		//System.out.println( Math.toDegrees(theSun.getRotation()));
		//Earth.setRotation(theSun.getRotation()+1);
		//System.out.println();
		//this.setRotation(this.getRotation()+0.05);
		//Earth.setXPos(Earth.getXPos()+edx);
		//mario.setYPos(mario.getYPos()+10);
		
		if ( counter%60 == 0 ) {
			
			//System.out.println("at time: " + counter/60 + " sun's position is: " + theSun.getRotation());
			
			/*
			if ( oscillate ) {
				//System.out.println("oscillate: " + oscillate + " counter: " + counter/60);
				//edx = 30;
			}
			else {
				//System.out.println("oscillate: " + oscillate + " counter: " + counter/60);
				//edx = -30;
			}
			oscillate = !oscillate;
		}
		
		/*
		while (  ) {
			//Earth.setXPos(Earth.getXPos()+100);
			//System.out.println(theSun.getRotation());
			//this.setRotation(this.getRotation()+0.5);
			System.out.println("Pi!");
			//System.out.println(counter);
			oscillate = !oscillate;
			if ( oscillate ) {
				Earth.setXPos(Earth.getXPos()+100);
			}
			else {
				Earth.setXPos(Earth.getXPos()-100);
			}
		}
		counter++;
		*/
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		theSun.draw(g);
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabThreeGame game = new LabThreeGame();
		game.start();

		System.out.println("GAME THREE TEST");

	}
}
