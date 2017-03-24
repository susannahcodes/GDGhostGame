package edu.virginia.lab1test;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.dinoSprite;
import edu.virginia.engine.util.GameClock;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabOneGame extends Game implements MouseListener {

	private int dx;
	private int dy;
	//private int px;
	//private int py;
	private JLabel healthLabel;
	private JLabel timeLabel;
	private JPanel panel;
	private int health;
	private int time;
	private GameClock clock;
	//private int elaps;
	private float deltaAlpha;
	private double degree;
	private int delay =0;
	//private Timer clock;
	//private MouseAdapter mouse;

	/* Create a sprite object for our game. We'll use character */
	//Sprite character = new Sprite("Mario", "Mario.png");
	//AnimatedSprite character = new AnimatedSprite("pegasus", "pegasus.png");
	dinoSprite character = new dinoSprite("Jesus riding a dino");
	
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabOneGame() {
		super("Lab One Test Game", 1200, 800);
		
		dx = 7;
		dy = 7;
		//elaps = 0;
		deltaAlpha = (float) 0.05;
		degree = 0.03;
		//int elaps = (int) clock.getElapsedTime()/1000;
		
		health = 100;
		time = 30;
		healthLabel = new JLabel("     HEALTH: " + health);
		//JTextField jt = new JTextField(20);
		//jt.setText("             TIME REMAINING: " + time + " seconds");
		//timeLabel = new JLabel();
		//timeLabel.add(jt);
		timeLabel = new JLabel( "             TIME REMAINING: " + time + " seconds"); //sorry for this sloppy code I was in a rush
		panel = new JPanel();
		//healthLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		panel.add(healthLabel);
		panel.add(timeLabel);
		//panel.
		//panel.setVisible(true);
		this.getMainFrame().add(panel, BorderLayout.SOUTH);
		//super.getMainFrame().add(panel, BorderLayout.SOUTH);
		super.getScenePanel().addMouseListener(this);
		//super.getScenePanel().add(panel);
		//super.getMainFrame().pack();
		//super.getMainFrame().setResizable(true);
		//g.drawString("HEALTH", 300, 300);
		//clock = new Timer();
		//character.setXPos(300);
		//character.setYPos(200);
	}
	/*
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);

		}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		}*/
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<String> pressedKeys){
		super.update(pressedKeys);

		/* Make sure character is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if (character != null) character.update(pressedKeys);
		
		if (pressedKeys.contains(KeyEvent.getKeyText(38))) {
			if ( !(character.getYPos() - dy < -20) )
				character.setYPos(character.getYPos()-dy);
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(40))) {
			if ( !(character.getYPos() + dy > this.getScenePanel().getHeight() - 20))
			character.setYPos(character.getYPos() + dy);
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(39))) {
			if ( !(character.getXPos() + dx > this.getScenePanel().getWidth() - 60))
			character.setXPos(character.getXPos() + dx);
			
			if ( character.getXScale() < 0 ) {
				character.setXScale(-character.getXScale());
			}
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(37))) {
			if ( !(character.getXPos() - dx < -20) )
			character.setXPos(character.getXPos() - dx);
			
			if ( character.getXScale() >= 0 ) {
				// need to save current pivot point, change it to center of the image, do that below, then restore pivot point
				character.setXScale(-character.getXScale());
				character.setXPos(character.getXPos()+63);
			}
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(65))) {
			character.setXScale(character.getXScale()+0.1);
			character.setYScale(character.getYScale()+0.1);
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(83))) {
			if (character.getXScale()-0.1 > 0 ) {
				character.setXScale(character.getXScale()-0.1);
				character.setYScale(character.getYScale()-0.1);
			}
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(88))) {
			if ( character.getTrans()-deltaAlpha > 0.0f ) {
				character.setTrans(character.getTrans()-deltaAlpha);
			}
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(90))) {
			if ( character.getTrans()+deltaAlpha < 1.0f ) {
				character.setTrans(character.getTrans()+deltaAlpha);
			}
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(86))) {
			
			if ( delay%3 ==0 ) {        //this fixes flickering but sometimes the pegasus doesn't reappear/disappear smoothly 
			
			if ( character.isVisible() ) { 											// need to fix the flickering, maybe with a delay counter
				character.setVisible(false);
			}
			else {
				character.setVisible(true);
			}
			}
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(81))) {
			character.setRotation(character.getRotation()+degree);
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(87))) {
			character.setRotation(character.getRotation()-degree);
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(73))) {		
			character.setYPiv(character.getYpiv()-5);
			System.out.println(character.getYpiv());
			
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(74))) {
			character.setXPiv(character.getXpiv()-5);
			System.out.println(character.getXpiv());
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(75))) {
			character.setYPiv(character.getYpiv()+5);
			System.out.println(character.getYpiv());
		}
		
		if (pressedKeys.contains(KeyEvent.getKeyText(76))) {
			character.setXPiv(character.getXpiv()+5);
			System.out.println(character.getXpiv());
		}
		
		//System.out.println((int)clock.getElapsedTime()/1000);
		
		//time = 30 - ( (int) clock.getElapsedTime()/1000);
		
		//timeLabel.setText( "             TIME REMAINING: " + time + " seconds");
		
		if ( time >= 30 ) {
			dx = 10;
			dy = 10;
		}
		
		delay++;
		
		/*if ( time == 0 ) {
			this.getScenePanel().setBackground(Color.CYAN);
    		//super.getScenePanel().remove(timeLabel);
    		healthLabel.setText("  PLAYER ONE WINS WITH REMAINING HEALTH AT " + health + " !!!!" );
    		timeLabel.setText(  "PLAYER TWO LOSES!!!      Please close this window and restart the game.");
			this.stop();
			//System.out.println("please put me out of my misery");
		}*/
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure character gets drawn to
	 * the screen, we need to make sure to override this method and call character's draw method.
	 * */
	@Override
	public void draw(Graphics g ){
		
		//Graphics2D g2d = (Graphics2D) g;
		//System.out.println("position on screen: " + xPos + ", " + yPos);
		//g.translate(xPos, yPos);
		
		time = 60 - ( (int) clock.getElapsedTime()/1000);
		timeLabel.setText( "             TIME REMAINING: " + time + " seconds");
		
		if ( time == 0 ) {
			this.getScenePanel().setBackground(Color.RED);
    		//super.getScenePanel().remove(timeLabel);
    		healthLabel.setText("  PLAYER ONE WINS WITH REMAINING HEALTH AT " + health + " !!!!" );
    		timeLabel.setText(  "PLAYER TWO LOSES!!!      Please close this window and restart the game.");
			this.stop();
			//System.out.println("please put me out of my misery");
		}
		
		super.draw(g);
		//System.out.println("are we in the draw method?");
		/* Same, just check for null in case a frame gets thrown in before character is initialized */
		if(character != null) character.draw(g);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Point clicked = e.getPoint();
		//System.out.println("mouse: " + clicked.getX() + " " + clicked.getY());
		//System.out.println(character.getDisplayImage().getWidth());
	    //Rectangle bounds = new Rectangle(250, 250, character.getDisplayImage().getWidth(), character.getDisplayImage().getHeight());
		Rectangle bounds = new Rectangle((int)character.getXPos(), (int)character.getYPos(), character.getDisplayImage().getWidth()-25, character.getDisplayImage().getHeight()-20);
		
		//System.out.println("mouse: " + clicked.getX() + " " + clicked.getY());
		//System.out.println("character: " + xPos + " " + yPos);
		
	    if (bounds.contains(clicked)) {
	    	
	    	if (time != 0 ) {
	    		health = health-25;	
	    		healthLabel.setText("     HEALTH: " + health);
	    		if (health <= 0) {
	    			//health = 0;
	    			//stop();
		    		JLabel death = new JLabel("YOU DIED");
		    		this.getScenePanel().setBackground(Color.BLACK);
		    		//super.getScenePanel().remove(timeLabel);
		    		healthLabel.setText( "     HEALTH: " + 0 + "  PLAYER ONE DIES!!!!    Please close this window and restart the game" );
		    		//timeLabel.setText(  "PLAYER TWO WINS IN " + time + " SECONDS!!!  Please close this window and restart the game");
		    		//timeLabel.setText("PLAYER TWO WINS IN " + time + " SECONDS!!!  Please close this window and restart the game");
		    		stop();
		    		//super.getScenePanel().remove(timeLabel);
	    		}
	    		
	    	}
	    }
	    
	    //System.out.println(e.getClickCount());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void start() {
		super.start();
		clock = new GameClock();
		//if ( health != 0 ) {
		//timeLabel.setText( "             TIME REMAINING: " + time + " seconds");
		//idk();
	}
	
	public void idk() {
		
		System.out.println((int)clock.getElapsedTime()/1000);
		
		time = 60 - ( (int) clock.getElapsedTime()/1000);
		
		//timeLabel.setText( "             TIME REMAINING: " + time + " seconds");
		
		
		if ( time == 0 ) {
			this.getScenePanel().setBackground(Color.CYAN);
    		//super.getScenePanel().remove(timeLabel);
    		healthLabel.setText("  PLAYER ONE WINS WITH REMAINING HEALTH AT " + health + " !!!!" );
    		timeLabel.setText(  "PLAYER TWO LOSES!!!      Please close this window and restart the game.");
			this.stop();
			//System.out.println("please put me out of my misery");
		}
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabOneGame game = new LabOneGame();
		game.start();
		System.out.println("LAB ONE GAME TEST");
		//System.out.println();

	}
}
