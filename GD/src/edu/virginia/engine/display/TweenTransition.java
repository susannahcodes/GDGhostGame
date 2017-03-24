package edu.virginia.engine.display;

import java.util.ArrayList;

public class TweenTransition {

	
	public void applyTransition(double percentDone) {
	}
	
	public float fadeIn(float current) {
		return (current+ (float)0.007);
	}
	
	public float fadeOut(float current) {
		return (current- (float)0.007);
	}
	
	public double popUp(double position, double time) {
		double positionOverTime = position - 15 + (.01*time*time);
		return positionOverTime;
	}
	
	public double rotate(double rotation) {
		return rotation+.25;
	}
	
	public double swell(double current) {
		return current+0.0005;
	}
	
	/*
	public Double[] popUpAndSwell(double position, double time, double current) {
		Double[] results = new Double[2];
		results[0] = this.swell(current);
		results[1] = this.popUp(position, time);
		
		return results;
	}*/
}
