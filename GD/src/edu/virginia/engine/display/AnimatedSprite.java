package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class AnimatedSprite extends Sprite {
	private BufferedImage[] sprites;
	private int current;
	private int startFrame;
	private int endFrame;
	private int counter;
	private int delayCounter;

	public AnimatedSprite(String id, String imageFileName) {
		super(id, imageFileName);
		sprites = new BufferedImage[15];
		int frame = 0;

		for (int j=0; j<4; j++) {
			for (int i=0; i<4; i++) {
				if ( frame < 15 ) {
					
					/*
					System.out.println("frame: " + frame);
					System.out.println("i: " + i + " j: " + j );
					System.out.println("i*256: " + i*256);
					System.out.println("j*179: " + j*187);
					*/
					sprites[frame] = super.getDisplayImage().getSubimage(i*256, j*179, 256, 187);
					frame++;
				}
			}
		}
		
		sprites[8] = super.getDisplayImage().getSubimage(0, 376, 256, 187); //cleans up the animation
		sprites[9] = super.getDisplayImage().getSubimage(256, 376, 256, 187);
		
		
		
		/*
		sprites = new BufferedImage[5];
		sprites[0]= super.getDisplayImage().getSubimage(0, 0, 256, 179);
		sprites[1] = super.getDisplayImage().getSubimage(256, 0, 256, 179);
		sprites[2] = super.getDisplayImage().getSubimage(512, 0, 256, 179);
		sprites[3] = super.getDisplayImage().getSubimage(768, 0, 256, 179);
		sprites[4] = super.getDisplayImage().getSubimage(0, 179, 256, 179);
		*/
		counter = 0;
		delayCounter = 0;
	}
	
	public void setAnimation(String s) {
		
	}
	
	public void setSpeed(int speed) {
		
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
			applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */
			g2d.drawImage(sprites[counter], 0, 0,
					245,
					175, null);
			
			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			if ( 0 == delayCounter%6 ) {
				//System.out.println("counter before inc: " + counter);
			counter++;
			//System.out.println("counter after inc: " + counter);
			}
			counter = counter %10; //need to fix this so that all frames are shown and fix the frames themselves because the feet are showing
			delayCounter++;
			
			reverseTransformations(g2d);
		}
	}

}
