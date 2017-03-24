package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class DisplayObjectContainer extends DisplayObject {
	
	private ArrayList<DisplayObjectContainer> children = new ArrayList<DisplayObjectContainer>();
	//private DisplayObject parent;

	public DisplayObjectContainer(String id, String fileName) {
		super(id, fileName);
	}
	
	public DisplayObjectContainer(String id) {
		super(id);
	}
	
	public void setParent(DisplayObjectContainer p) {
		super.setParent(p);
	}
	
	public DisplayObjectContainer getParent() {
		return super.getParent();
	}
	
	
	public ArrayList<DisplayObjectContainer> getChildren() {
		return children;
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		//System.out.println(super.getId());
		Graphics2D g2d = (Graphics2D) g;
		applyTransformations(g2d);
		
		for ( DisplayObjectContainer child : children ) {
			if ( child != null) {
				child.draw(g);
			}
		}
		reverseTransformations(g2d);
	}
	
	public void add(DisplayObjectContainer child) { 			//this may screw me up later on bc simple display objects will not be allowed to add
		children.add(child);
		child.setParent(this);
	}
	
	public boolean containsChild(DisplayObject p) {		//and same here
		return this.getChildren().contains(p);
	}
	
	public boolean remove(DisplayObjectContainer p) {
		
		if (this.containsChild(p)) {
			this.getChildren().remove(p);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void clearChildren() {
		this.getChildren().clear();
	}
}
