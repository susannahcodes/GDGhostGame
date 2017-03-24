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
import edu.virginia.engine.display.Tween;
import edu.virginia.engine.display.TweenEvent;
import edu.virginia.engine.display.TweenJuggler;
import edu.virginia.engine.display.TweenParam;
import edu.virginia.engine.display.TweenTransition;
import edu.virginia.engine.display.coinSprite;
import edu.virginia.engine.display.marioSprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;

public class LabSixGame extends Game {

	coinSprite coin1 = new coinSprite("Coin One");
	marioSprite mario1 = new marioSprite("MarioOne");
	Sprite questConfirm = new Sprite("Quest completed", "questComplete.png");
	
	//TweenTransition fadeIn = new TweenTransition.fadeIn((float)0.0);
	Tween marioTween = new Tween(mario1, new TweenTransition() );
	Tween coinTween = new Tween(coin1, new TweenTransition() );
	
	TweenJuggler juggler = new TweenJuggler();
	
	//double fadeIn = 0;
	private GameClock clock;

	Rectangle marioBounds = new Rectangle();
	Rectangle coinBounds = new Rectangle();

	//Event coinPickedUp = new Event(Event.COIN_PICKED_UP, coin1); //place in dispatch event

	QuestManager myQuestManager = new QuestManager();

	private int dx =7;
	private int dy =7;

	public LabSixGame() {
		
		super("Lab Six Test Game", 1200, 800);
		
		clock = new GameClock();
		
		mario1.setTrans(0.0f);
		mario1.setXPos(3);
		mario1.setYPos(800-mario1.getUnscaledHeight()-25);
		marioTween.doTween(true);
		marioTween.animate(TweenableParam.FADE_IN, 0.0f, 1.0f, 6000);	
		//marioTween.doTween(false);
		
		coin1.setXPos(this.getScenePanel().getWidth()/2);
		coin1.setYPos(700);
		coin1.addEventListener(myQuestManager, null);
		
		coinTween.addEventListener(myQuestManager, null);
		coinTween.animate(TweenableParam.POP_TO_CENTER, coin1.getYPos(), this.getScenePanel().getHeight()/2-(coin1.getScaledHeight()/2)-50, 6000);
		coinTween.animate(TweenableParam.SWELL, coin1.getXScale(), .05, 6000);
		coinTween.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);
		
		juggler.add(marioTween);
		juggler.add(coinTween);
		
		questConfirm.setXScale(.5);
		questConfirm.setYScale(.5);
		questConfirm.setVisible(false);
	}

	public void update(ArrayList<String> pressedKeys) {
		
		super.update(pressedKeys);

		if (mario1 != null) {
			if (coin1 != null) {
				
				juggler.nextFrame();
				
				if (myQuestManager.questCompleted) {
					coin1.setXPos(this.getScenePanel().getWidth()/2-coin1.getScaledWidth()/2);
					coinTween.doTween(myQuestManager.tweenComplete);
					questConfirm.setVisible(true);		
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(38)) ) {
					if ( !(mario1.getYPos() - dy < -20) && mario1.getTrans() >= 1.0f-0.1)
						mario1.setYPos(mario1.getYPos()-dy);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(40))) {
					if ( !(mario1.getYPos() + dy > this.getScenePanel().getHeight() - 20) && mario1.getTrans() >= 1.0f-0.1)
						mario1.setYPos(mario1.getYPos() + dy);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(39))) {
					if ( !(mario1.getXPos() + dx > this.getScenePanel().getWidth() - 60) && mario1.getTrans() >= 1.0f-0.1)
						mario1.setXPos(mario1.getXPos() + dx);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(37))) {
					if ( !(mario1.getXPos() - dx < -20) && mario1.getTrans() >= 1.0f-0.1)
						mario1.setXPos(mario1.getXPos() - dx);
				}

				if (mario1.collidesWith(coin1)) {
					coin1.dispatchEvent(new Event(Event.COIN_PICKED_UP, coin1));
					coinTween.dispatchEvent(new TweenEvent(TweenEvent.TWEEN_EVENT_COMPLETE, coinTween));
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
	}

	public static void main(String[] args) {
		LabSixGame game = new LabSixGame();
		game.start();

		System.out.println("GAME SIX TEST");
	}
}