package edu.virginia.engine.display;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.virginia.engine.events.EventDispatcher;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject extends EventDispatcher {
	
	private double xPos = 0;
	private double yPos = 0;
	//private int pivX = 0;
	//private int pivY = 0;
	private int pivX;
	private int pivY;
	private int visible = AlphaComposite.SRC_OVER;
	private double scaleX =1.0;
	private double scaleY =1.0;
	private double rotation = 0.0;
	private float alpha = 1.0f;
	private boolean physics = false;
	private double xVel = 0;
	private double yVel = 0;
	private double acceleration = 0;
	private double speediness= 0;
	private DisplayObjectContainer parent;
	protected Rectangle hitBox = new Rectangle();
	public int WORLD_SIZE_X = 3600;
	public int WORLD_SIZE_Y = 2400;
	public int VIEWPORT_SIZE_X = 1200;
	public int VIEWPORT_SIZE_Y = 800;
	public int offsetMinY = 0;
	public int offsetMinX = 0;
	public int offsetMaxX = WORLD_SIZE_X - VIEWPORT_SIZE_X;
	public int offsetMaxY = WORLD_SIZE_Y - VIEWPORT_SIZE_Y;
	

	/* All DisplayObject have a unique id */
	private String id;

	/* The image that is displayed by this object */
	private BufferedImage displayImage;

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObject(String id) {
		this.setId(id);
	}

	public DisplayObject(String id, String fileName) {
		
		this.setId(id);
		this.setImage(fileName);
		
		pivX = this.getUnscaledWidth()/2;
		pivY = this.getUnscaledHeight()/2;
		
		//hitBox.setBounds(pivX, pivY, width, height);
	}
	
	public void setParent(DisplayObjectContainer p) {
		parent = p;
	}
	
	public DisplayObjectContainer getParent() {
		return parent;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public double getXPos() {
		return xPos;
	}
	
	public void setXPos(double x) {
		this.xPos = x;
	}
	
	public double getYPos() {
		return yPos;
	}
	
	public void setYPos(double y) {
		this.yPos = y;
	}
	
	public boolean isVisible() {
		if ( visible == AlphaComposite.SRC_OVER ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//public float getAlpha(){
		//return this.alpha;
	//}
	
	//public void setAlpha(float newAlpha) {
		//this.alpha = newAlpha;
	//}
	
	
	
	public void setVisible(boolean b) {
		if (b) {
			this.visible = AlphaComposite.SRC_OVER;
		}
		else {
			this.visible = AlphaComposite.DST_OVER;
		}
	}
	/*
	public Point getPivot() {
		return pivotPoint;
	}
	
	public void setPivot(Point p) {
		this.pivotPoint = p;
	}*/
	
	public int getXpiv() {
		return pivX;
	}
	
	public int getYpiv() {
		return pivY;
	}
	
	public void setXPiv(int x) {
		pivX =x;
	}
	
	public void setYPiv(int y) {
		pivY =y;
	}
	
	public double getXScale() { 
		return scaleX;
	}
	
	public double getYScale() {
		return scaleY;
	}
	
	public void setXScale(double sx) {
		this.scaleX =sx;
	}
	
	public void setYScale(double sy) {
		this.scaleY =sy;
	}
	
	public double getRotation() {
		return rotation;
	}
	
	public void setRotation(double r) {
		this.rotation =r;
	}
	
	public float getTrans() {
		return alpha;
	}
	
	public void setTrans(float f) {
		this.alpha =f;
	}

	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}
	
	public int getScaledWidth() {
		return (int) (getUnscaledWidth() * getXScale());
	}
	
	public int getScaledHeight() {
		return (int) (getUnscaledHeight() * getYScale());
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}
	
	
	public Rectangle getHitBox() {
		this.hitBox.setBounds((int)xPos, (int)yPos, getScaledWidth(), getScaledHeight());
		return hitBox;
	}
	
	public boolean collidesWith(DisplayObject other) {
		return this.getHitBox().intersects(other.getHitBox());
	}

	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
		//displayImage = image.getSubimage(0, 0, 245, 175);
	}

	/**
	 * both returns the state of whether physics are enable AND
	 * sets whether physics are enabled.
	 * default setting is false
	 * @param b
	 * @return
	 */
	public void hasPhysics(boolean b) {
		this.physics = b;
	}
	
	public boolean hasPhysics() {
		return this.physics;
	}
	
	public void setSpeed(double speed) {
		if (this.physics) this.speediness = speed;
	}
	
	public void setXVelocity(double angle) {
		if (this.physics) this.xVel = this.speediness*Math.cos(angle);
	}
	
	public void setYVelocity(double angle) {
		if (this.physics) this.yVel = this.speediness*Math.sin(angle);
	}
	
	public void setXVelByVal(double value) {
		this.xVel = value;
	}
	
	public void setYVelByVal(double value) {
		this.yVel = value;
	}
	
	public void setAcceleration(double d) {
		this.acceleration = d;
	}
	
	public double getAcceleration() {
		return this.acceleration;
	}
	
	public double getXVel() {
		return this.xVel;
	}
	
	public double getYVel() {
		return this.yVel;
	}
	
	public double getSpeed() {
		return this.speediness;
	}

	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	protected void update(ArrayList<String> pressedKeys) {
		
	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {
		
		if (displayImage != null) {
			
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			//g2d.translate(xPos-(getUnscaledWidth()/2), yPos-(getUnscaledHeight()/2));
			applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here 
			g2d.drawImage(displayImage, -(getUnscaledWidth()/2), -(getUnscaledHeight()/2),
					(int) (getUnscaledWidth()),
					(int) (getUnscaledHeight()), null);
			//THE ABOVE CODE IS A HACK, I WILL HAVE TO CHANGE EVENTUALLY */
			
			
			g2d.drawImage(displayImage, 0, 0,
					(int) (getUnscaledWidth()),
					(int) (getUnscaledHeight()), null);
			
			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			//g2d.translate(-xPos+(getUnscaledWidth()/2), -yPos+(getUnscaledHeight()/2));
			reverseTransformations(g2d);
			//g2d.translate(-xPos+(getUnscaledWidth()/2), -yPos+(getUnscaledHeight()/2));
		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		//System.out.println(this.getId() + " has ");
		g2d.translate(xPos, yPos);
		g2d.scale(scaleX, scaleY);
		g2d.setComposite(AlphaComposite.getInstance(visible,
				alpha));
		
		//g2d.rotate(rotation);
		g2d.rotate(rotation, pivX, pivY);
		
		//TRYING THIS FIX
		//g2d.translate(xPos, yPos);
		//g2d.scale(scaleX, scaleY);
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		
		//TRYING THIS FIX
		//g2d.scale(1/scaleX, 1/scaleY);
		//g2d.translate(-xPos, -yPos);
		
		//g2d.rotate(-rotation);
		g2d.rotate(-rotation, pivX, pivY); 			//idk if this be right
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				1.0f));
		g2d.scale(1/scaleX, 1/scaleY);
		g2d.translate(-xPos, -yPos);
		
	}

}