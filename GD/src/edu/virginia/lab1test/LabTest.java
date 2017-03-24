package edu.virginia.lab1test;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.coinSprite;
import edu.virginia.engine.display.marioSprite;
import edu.virginia.engine.util.GameClock;

public class LabTest extends  Game {
	
	int time;
	JLabel timeLabel;
	JPanel panel;
	//Sprite character = new Sprite("Mario", "Mario.png");
	
	//marioSprite mario1 = new marioSprite("MarioOne");
		coinSprite coin1 = new coinSprite("Coin One");
		marioSprite mario1 = new marioSprite("MarioOne");

		Rectangle marioBounds = new Rectangle();
		Rectangle coinBounds = new Rectangle();

		//Event coinPickedUp = new Event(Event.COIN_PICKED_UP, coin1); //place in dispatch event

		QuestManager myQuestManager = new QuestManager();

		private int dx =7;
		private int dy =7;

	
	public LabTest() {
		super("Lab One Test Game", 1200, 800);

		
		int health = 100;
		time = 30;
		JLabel healthLabel = new JLabel("     HEALTH: " + health);
		timeLabel = new JLabel( "             TIME REMAINING: " + time + " seconds"); 
		panel = new JPanel();
		panel.add(healthLabel);
		panel.add(timeLabel);

		this.getMainFrame().add(panel, BorderLayout.SOUTH);	
		
		coin1.setXPos(this.getScenePanel().getWidth()/2);
		coin1.setYPos(this.getScenePanel().getHeight()/2);

		coin1.addEventListener(myQuestManager, null);
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<String> pressedKeys){
		super.update(pressedKeys);

	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure character gets drawn to
	 * the screen, we need to make sure to override this method and call character's draw method.
	 * */
	@Override
	public void draw(Graphics g ){
		super.draw(g);
		//System.out.println("are we in the draw method?");
		/* Same, just check for null in case a frame gets thrown in before character is initialized */

		//if(mario1 != null) mario1.draw(g);
		//if(coin1 != null) coin1.draw(g);
	}
	

	@Override
	public void start() {
		super.start();
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabTest game = new LabTest();
		game.start();
		System.out.println("LAB TEST");
	}

}
