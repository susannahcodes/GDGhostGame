package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class ghostSprite extends Sprite implements IEventListener {
	
	private BufferedImage[] sprites;
	private int counter;
	private int delayCounter;
	private int subWidth;
	private int subHeight;

	public ghostSprite(String id) {
		super(id, "ghostSheet.png");
		sprites = new BufferedImage[5];
		int frame = 0;
		subWidth = super.getUnscaledWidth()/2;
		//System.out.println("subWidth: " +subWidth);
		//System.out.println("subHeaight: " + subHeight);
		subHeight = super.getUnscaledHeight()/3;
		
		for (int j=0; j<3; j++) {
			for (int i=0; i<2; i++) {
				if ( frame < sprites.length ) {					
					sprites[frame] = super.getDisplayImage().getSubimage(i*subWidth, j*subHeight, subWidth, subHeight);
					frame++;
				}
			}
		}
		
		//super.setXScale(3);
		//super.setYScale(3);
		counter = 0;
		delayCounter = 0;
	}
	
	@Override
	public void draw(Graphics g) {

		if (sprites[counter] != null) {
			
			Graphics2D g2d = (Graphics2D) g;
			super.applyTransformations(g2d);

			g2d.drawImage(sprites[counter], 0, 0,
					subWidth,
					subHeight, null);

			if ( 0 == delayCounter%9 ) {
			counter++;
			}
			counter = counter %sprites.length; 
			delayCounter++;
			
			super.reverseTransformations(g2d);
		}
	}
	
	@Override
	public Rectangle getHitBox() {
		this.hitBox.setBounds((int)this.getXPos(), (int)this.getYPos(), subWidth-20, subHeight-10);
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

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
	}

}
