package edu.virginia.engine.display;

import edu.virginia.lab1test.TweenableParam;

public class TweenParam {
	private TweenableParam tweenableParam;
	private double start;
	private double end;
	private double duration;
	
	public TweenParam(TweenableParam paramToTween, double startVal, double endVal, double time) {
		this.tweenableParam = paramToTween;
		this.start = startVal;
		this.end = endVal;
		this.duration = time;
	}
	
	public TweenableParam getParam() {
		return this.tweenableParam;
	}
	public double getStartVal() {
		return this.start;
	}
	public double getEndVal() {
		return this.end;
	}
	public double getTweenTime() {
		return this.duration;
	}
	public void setStartVal(double d) {
		this.start = d;
	}

	
}
