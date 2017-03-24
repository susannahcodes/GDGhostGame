package edu.virginia.lab1test;

import edu.virginia.engine.display.Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.coinSprite;
import edu.virginia.engine.display.dinoSprite;
import edu.virginia.engine.display.marioSprite;
import edu.virginia.engine.events.Event;

public class LabFiveGame extends Game {

	coinSprite coin1 = new coinSprite("Coin One");
	//marioSprite character = new marioSprite("MarioOne");
	dinoSprite character = new dinoSprite("Jesus riding a dino");
	Sprite questConfirm = new Sprite("Quest completed", "questComplete.png");
	
	Sprite platform1 = new Sprite("platform one", "platform.png");
	marioSprite randomMario = new marioSprite("yo");

	//Event coinPickedUp = new Event(Event.COIN_PICKED_UP, coin1); //place in dispatch event

	QuestManager myQuestManager = new QuestManager();

	private int dx =3;
	private int dy =7;
	
	private double gravity = 0.5;
	private boolean jump = false;
	double restore;
	boolean landing = false;
	double endPos;

	public LabFiveGame() {
		
		super("Lab Five Test Game", 1200, 800);
		
		coin1.setXPos(1100);
		coin1.setYPos(100);
		coin1.addEventListener(myQuestManager, null);
	
		character.setYPos(this.getScenePanel().getHeight()-125);
		character.hasPhysics(true);
		character.setSpeed(5);
		//character.setYVelocity(45);
		character.setXVelocity(30);
		character.setAcceleration(-0.05);

		questConfirm.setXScale(.5);
		questConfirm.setYScale(.5);
		questConfirm.setVisible(false);
		
		platform1.setXScale(0.05);
		platform1.setYScale(0.05);
		platform1.setXPos(600);
		platform1.setYPos(600);
		
		//character.setXScale(5.0);
		//character.add(randomMario);
		
		this.add(character);
		this.add(coin1);
		this.add(platform1);
		this.add(questConfirm);
		
	}

	public void update(ArrayList<String> pressedKeys) {

		super.update(pressedKeys);

		if (character != null) {
			
			if (coin1 != null) {

				if (myQuestManager.questCompleted) {
					coin1.setVisible(false);
					questConfirm.setVisible(true);		// I have this instead of g.drawString because that made mario's movements lag
				}
				
				landing = (character.getXPos() >= platform1.getXPos()-20)  && (character.getXPos() < platform1.getXPos() + platform1.getScaledWidth()-50);
				
				//System.out.println(landing);
				
				//if (!landing && (character.getYPos() < 800)) {

				if (pressedKeys.contains(KeyEvent.getKeyText(38)) && (!jump) ) {
					character.setYVelByVal(-15);
					jump = true;
					//if ( (character.getXPos() >= platform1.getXPos()-20)  && (character.getXPos() < platform1.getXPos() + platform1.getScaledWidth()-50) ) {
						//System.out.println("dino is under the platform");
						//System.out.println("platform height: " +);
						//character.setYPos(platform1.getYPos()-119);
						//character.setYVelByVal(0);
						//character.setXVelByVal(0);
					//}
					restore = character.getYPos();
					System.out.println("restore: " + restore);
				}
				//System.out.println("restore: " + restore);
				
				if (landing) {
					if (character.getYPos() < platform1.getYPos()) {
						//System.out.println("collision");
						//System.out.println(character.getYPos());
						character.setYVelByVal(0);
						character.setXVelByVal(0);
						character.setYPos(platform1.getYPos()-120);
						//jump = !jump;
						
						/****************/
						
						/****************/
					}
				}

				if (jump) {
					
					//System.out.println("in the jump loop");
					
					/*
					if ( (character.getXPos() >= platform1.getXPos()-20)  && (character.getXPos() < platform1.getXPos() + platform1.getScaledWidth()-50) ) {
						System.out.println("dino is under the platform");
						//System.out.println("platform height: " +);
						character.setYPos(platform1.getYPos()-119);
						character.setYVelByVal(0);
						character.setXVelByVal(0);
					}*/
					
					if ( character.getYPos() + character.getYVel() > restore - 300 ) {
						//System.out.println("if statement 1");
						character.setYPos((int) (character.getYPos()+character.getYVel()));
						character.setYVelByVal(character.getYVel() + gravity);
						
						if (character.getXScale() > 0) {
							character.setXPos(character.getXPos()+character.getXVel());
						}
						else {
							character.setXPos(character.getXPos()-character.getXVel());
						}
						//System.out.println("velocity: " + character.getYVel());
					}
					
					//if (character.getYPos() + character.getYVel() <= 500) {
					if (character.getYPos() + character.getYVel() <= restore + 300) {
						//System.out.println("if statement 2");
						//character.setYPos((int) (character.getYPos()-character.getYVel()));
						character.setYVelByVal(character.getYVel() + gravity);
						character.setYPos(character.getYPos()+character.getYVel());
					}
					
					/***********ORIGINAL
					if (character.getYPos() >= restore) {
						character.setYVelByVal(0);
						character.setYPos(restore);
						jump = !jump;
					}
					**********/
					
					if (character.getYPos() >= restore) {
						character.setYVelByVal(0);
						character.setYPos(restore);
						jump = !jump;
					}
					
				}
				
				/**
				if ( (character.getXPos() >= platform1.getXPos()-20)  && (character.getXPos() < platform1.getXPos() + platform1.getScaledWidth()-50) ) {
					//System.out.println("dino is under the platform");
					//System.out.println("platform height: " +);
					character.setYPos(platform1.getYPos()-119);
					character.setYVelByVal(0);
					character.setXVelByVal(0);
					//jump = !jump;
				} 
				**/
				
				/*
				if ( character.getSpeed() != 0 ) {
					character.setSpeed(character.getSpeed()+character.getAcceleration());
					character.setXVelocity(45);
					//character.setYVelocity(45);
					System.out.println("speed: " + character.getSpeed());
					System.out.println("xVelocity: " + character.getXVel());
					character.setXPos((int) (character.getXPos()+character.getXVel()));
					character.setYPos(character.getYPos()-character.getYVel());
				}*/
				
				if ( !landing && !jump) {
					character.setYPos(this.getScenePanel().getHeight()-125);
				}
				

				if (pressedKeys.contains(KeyEvent.getKeyText(39))) {
					if ( !(character.getXPos() + dx > this.getScenePanel().getWidth() - 127)) {
						character.setXPos(character.getXPos() + dx);
					}	

					if ( character.getXScale() < 0 ) { 
						character.setXScale(-character.getXScale()); 
						character.setXPos(character.getXPos()-63);
					}
				}

				
				if (pressedKeys.contains(KeyEvent.getKeyText(37))) {
					if ( !(character.getXPos() - dx < 0) ) {
						character.setXPos(character.getXPos() - dx);
					}
					
					if ( character.getXScale() >= 0 ) {
						//character.setXScale(-character.getXScale());
						//character.setXPos(character.getXPos()+63);
					}
				}

				if (character.collidesWith(coin1)) {
					coin1.dispatchEvent(new Event(Event.COIN_PICKED_UP, coin1));
				}
				
				if (character.collidesWith(platform1) ) {
					//System.out.println("fuck");
				}
				
				/*
				System.out.println("BEFORE COLLISION CHECK: character xpos: " + character.getXPos());
				if (character.collidesWith(platform1)) {
					//character.dispatchEvent(new Event(Event.COLLISION, character));  //this does nothing;
					System.out.println("AHHHHH");
					
					if ( (character.getHitBox().y + character.getHitBox().height) >= platform1.getHitBox().y ) {
						if ( (character.getHitBox().x + character.getHitBox().width/2) <= platform1.getHitBox().x  ) {
							//restore = platform1.getYPos();
							System.out.println("collision!");
							character.setYPos(platform1.getYPos()+character.getScaledHeight());
						}
					}
					
					/*
					if (character.getYPos()-character.getScaledHeight() <= platform1.getYPos()) {
						//System.out.println("AHHHHH");
						System.out.println("character: " + (character.getYPos()-character.getScaledHeight()));
						System.out.println("platform: " + platform1.getYPos());
						if ( character.getXPos() >= platform1.getXPos() ) {
							//System.out.println("AHHHHH");
						}
					}
				
				} */
			
			}
		} 
	} 

	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario1 gets drawn to
	 * the screen, we need to make sure to override this method and call mario1's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		
		super.draw(g);

		/*
		if (coin1 != null) {
			coin1.draw(g);
		}
		
		if (character != null) {
			character.draw(g);
		}
		
		if (questConfirm != null) {
			questConfirm.draw(g);
		}
		
		if (platform1 != null) {
			platform1.draw(g);
		}
		*/
		
	}

	public static void main(String[] args) {
		LabFiveGame game = new LabFiveGame();
		game.start();

		System.out.println("GAME FIVE TEST");
	}
}
