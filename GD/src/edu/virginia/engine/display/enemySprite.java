package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class enemySprite extends Sprite implements IEventListener {
	
	private BufferedImage[] sprites;
	private int counter;
	private int delayCounter;
	private int subWidth;
	private int subHeight;
	
	private Rectangle topHitBox;
	private Rectangle bottomHitBox;
	private Rectangle leftHitBox;
	private Rectangle rightHitBox;
	
	private boolean forward;
	private boolean backward;

	public enemySprite(String id) {
		super(id, "enemySheet.png");
		sprites = new BufferedImage[4];
		//int frame = 0;
		subWidth = super.getUnscaledWidth()/2;
		subHeight = super.getUnscaledHeight()/2;
		topHitBox = new Rectangle();			// this is to detect collisions from the top
		bottomHitBox = new Rectangle();	
		leftHitBox = new Rectangle();	
		rightHitBox = new Rectangle();	
		
		forward = false;
		backward = true;
		
		sprites[0] = super.getDisplayImage().getSubimage(0, 0, subWidth, subHeight);
		sprites[1] = super.getDisplayImage().getSubimage(subWidth, 0, subWidth, subHeight);
		sprites[2] = super.getDisplayImage().getSubimage(0, subHeight, subWidth, subHeight);
		sprites[3] = super.getDisplayImage().getSubimage(subWidth, subHeight, subWidth, subHeight);

		counter = 0;
		delayCounter = 0;
	}
	
	@Override
	public void draw(Graphics g) {
		
		if (forward) {
			
			if (sprites[counter] != null) {

				Graphics2D g2d = (Graphics2D) g;
				super.applyTransformations(g2d);

				g2d.drawImage(sprites[counter], 0, 0,
						subWidth,
						subHeight, null);

				if ( 0 == delayCounter%9 ) {
					counter++;
				}
				counter = counter%2 + 2; 
				delayCounter++;

				super.reverseTransformations(g2d);
			}
		}
		
		if (backward) {
			
			if (sprites[counter] != null) {

				Graphics2D g2d = (Graphics2D) g;
				super.applyTransformations(g2d);

				g2d.drawImage(sprites[counter], 0, 0,
						subWidth,
						subHeight, null);

				if ( 0 == delayCounter%9 ) {
					counter++;
				}
				counter = counter%2; 
				delayCounter++;

				super.reverseTransformations(g2d);
			}
		}
		
	}
	
	@Override
	public Rectangle getHitBox() {
		this.hitBox.setBounds((int)this.getXPos()+75, (int)this.getYPos()+60, 75, 113);		// NOTE: 95 and 120 are hardcoded values to match the sprite sheet "enemySheet"
		return hitBox;
	}
	
	@Override
	public int getScaledHeight() {
		return subHeight;
	}
	
	@Override
	public int getScaledWidth() {
		return subWidth;
	}
	
	public void goForward(boolean b) {
		if (b) {
			this.forward = true;
			this.backward = false;
		}
	}
	
	public void goBackward(boolean b) {
		if (b) {
			this.forward = false;
			this.backward = true;
		}
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
	}
	
}
