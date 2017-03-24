package edu.virginia.lab1test;

import edu.virginia.engine.display.Game;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.coinSprite;
import edu.virginia.engine.display.marioSprite;
import edu.virginia.engine.events.Event;

public class LabFourGame extends Game {

	coinSprite coin1 = new coinSprite("Coin One");
	marioSprite mario1 = new marioSprite("MarioOne");
	Sprite questConfirm = new Sprite("Quest completed", "questComplete.png");

	Rectangle marioBounds = new Rectangle();
	Rectangle coinBounds = new Rectangle();

	//Event coinPickedUp = new Event(Event.COIN_PICKED_UP, coin1); //place in dispatch event

	QuestManager myQuestManager = new QuestManager();

	private int dx =7;
	private int dy =7;

	public LabFourGame() {
		
		super("Lab Four Test Game", 1200, 800);
		
		coin1.setXPos(this.getScenePanel().getWidth()/2);
		coin1.setYPos(this.getScenePanel().getHeight()/2);

		coin1.addEventListener(myQuestManager, null);
		
		questConfirm.setXScale(.5);
		questConfirm.setYScale(.5);
		questConfirm.setVisible(false);
	}

	public void update(ArrayList<String> pressedKeys) {
		
		super.update(pressedKeys);

		if (mario1 != null) {
			if (coin1 != null) {
				
				if (myQuestManager.questCompleted) {
					coin1.setVisible(false);
					questConfirm.setVisible(true);		// I have this instead of g.drawString because that made mario's movements lag
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(38))) {
					if ( !(mario1.getYPos() - dy < -20) )
						mario1.setYPos(mario1.getYPos()-dy);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(40))) {
					if ( !(mario1.getYPos() + dy > this.getScenePanel().getHeight() - 20))
						mario1.setYPos(mario1.getYPos() + dy);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(39))) {
					if ( !(mario1.getXPos() + dx > this.getScenePanel().getWidth() - 60))
						mario1.setXPos(mario1.getXPos() + dx);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(37))) {
					if ( !(mario1.getXPos() - dx < -20) )
						mario1.setXPos(mario1.getXPos() - dx);
				}

				//marioBounds.setBounds((int)mario1.getXPos(),(int) mario1.getYPos(), mario1.getDisplayImage().getWidth()-25, mario1.getDisplayImage().getHeight()-20);
				//mario1.getHitBox();
				//coinBounds.setBounds((int)coin1.getXPos(), (int)coin1.getYPos(), coin1.getScaledWidth(), coin1.getScaledHeight());
				//if ( marioBounds.contains(coinBounds) ){
				//if (mario1.getHitBox().contains(coin1.getHitBox())) {
				if (mario1.collidesWith(coin1)) {
					coin1.dispatchEvent(new Event(Event.COIN_PICKED_UP, coin1));
				}

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

		if (coin1 != null) {
			coin1.draw(g);
		}
		
		if (mario1 != null) {
			mario1.draw(g);
		}
		
		if (questConfirm != null) {
			questConfirm.draw(g);
		}
		
		/*
		if (myQuestManager.questCompleted) {
			System.out.println("eh");
		}*/
	}

	public static void main(String[] args) {
		LabFourGame game = new LabFourGame();
		game.start();

		System.out.println("GAME FOUR TEST");
	}
}
