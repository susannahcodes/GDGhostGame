package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.lab1test.LabOneGame;

public class dinoSprite extends Sprite implements IEventListener {
	
	private BufferedImage[] sprites;
	private int counter;
	private int delayCounter;
	private int subWidth;
	private int subHeight;

	public dinoSprite(String id) {
		super(id, "JesusDino.png");
		sprites = new BufferedImage[13];
		int frame = 0;
		subWidth = super.getUnscaledWidth()/10;
		subHeight = super.getUnscaledHeight()/3;
		
		for (int j=0; j<3; j++) {
			for (int i=0; i<10; i++) {
				if ( frame < sprites.length ) {					
					sprites[frame] = super.getDisplayImage().getSubimage(i*subWidth, j*subHeight, subWidth, subHeight);
					frame++;
				}
			}
		}
		
		counter = 0;
		delayCounter = 0;
	}
	
	@Override
	public void draw(Graphics g) {
		
		//sprites[0]= super.getDisplayImage().getSubimage(0, 0, 245, 175);
		//sprites[1] = super.getDisplayImage().getSubimage(245, 0, 245, 175);
		
		//System.out.println("first if statement draw the counter is: " + counter);
		if (sprites[counter] != null) {
			
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			super.applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */
			g2d.drawImage(sprites[counter], 0, 0,
					subWidth,
					subHeight, null);
			
			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			if ( 0 == delayCounter%6 ) {
				//System.out.println("counter before inc: " + counter);
			counter++;
			//System.out.println("counter after inc: " + counter);
			}
			counter = counter %sprites.length; //need to fix this so that all frames are shown and fix the frames themselves because the feet are showing
			delayCounter++;
			
			super.reverseTransformations(g2d);
		}
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
		System.out.println("DINO");
		dinoSprite jesus = new dinoSprite("Jesus");
	}

}
