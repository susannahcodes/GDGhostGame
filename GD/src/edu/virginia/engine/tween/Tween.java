package edu.virginia.engine.tween;

import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.events.EventDispatcher;

public class Tween extends EventDispatcher{
	private DisplayObjectContainer displayObject;
	private TweenTransition tweenTransition;	//so this might need to be made into an array list later
	//private TweenParam paramTween;// might want an array of tweenparams.....seems hella fucking stupid to me but idk
	private ArrayList<TweenParam> tweenParamList;
	private double time = 0;
	private boolean performTween = false;
	
	public Tween(DisplayObjectContainer object) {
		
	}
	
	public Tween(DisplayObjectContainer object, TweenTransition transition) {
		this.displayObject = object;
		tweenTransition = transition;
		tweenParamList = new ArrayList<TweenParam>();
		
	}
	
	public void animate(TweenableParam fieldToAnimate, double startVal, double endVal, double time) {
		TweenParam paramTween = new TweenParam(fieldToAnimate, startVal, endVal, time);
		tweenParamList.add(paramTween);
	}
	
	public void update() {

		for ( TweenParam paramTween : tweenParamList ) {

			if (paramTween.getParam() == TweenableParam.FADE_IN && performTween) {
				if (paramTween.getStartVal()+0.007 < paramTween.getEndVal()) {
					displayObject.setTrans(tweenTransition.fadeIn(displayObject.getTrans()));
					//System.out.println("IN THE UPDATE METHOD FOR TWEEN I GUESS");
					paramTween.setStartVal( (double)displayObject.getTrans() );
				}
			}

			if (paramTween.getParam() == TweenableParam.SWELL && performTween) {
				if (paramTween.getStartVal() < paramTween.getEndVal()) {
					displayObject.setXScale(tweenTransition.swell(displayObject.getXScale()));
					displayObject.setYScale(tweenTransition.swell(displayObject.getYScale()));
					paramTween.setStartVal(displayObject.getXScale());
				}
			}
			
			if (paramTween.getParam() == TweenableParam.POP_TO_CENTER && performTween) {
				if (paramTween.getStartVal() > paramTween.getEndVal()) {
					//System.out.println(time);
					displayObject.setYPos(tweenTransition.popUp(displayObject.getYPos(), time));
					//displayObject.setRotation(tweenTransition.rotate(displayObject.getRotation()));
					paramTween.setStartVal(displayObject.getYPos());
					time += 1;
				}
			}
			
			if (paramTween.getParam() == TweenableParam.FADE_OUT && performTween) {
				if (paramTween.getStartVal()-0.007 > paramTween.getEndVal()) {
					//System.out.println(displayObject.getTrans());
					displayObject.setTrans(tweenTransition.fadeOut(displayObject.getTrans()));
					displayObject.setRotation(tweenTransition.rotate(displayObject.getRotation()));
					paramTween.setStartVal(displayObject.getTrans());
				}
			}
			
			/*
			if (paramTween.getParam() == TweenableParam.MOVE_AND_SWELL) {
				if (paramTween.getStartVal() < paramTween.getEndVal() && ) {
			} */
			
		}

	}
	
	public void doTween(boolean b) {
		performTween =b;
	}
	
	public boolean shallWeTween() {
		return performTween;
	}
	
	public boolean isComplete() {
		return false;
	}
	
	public void setValue(TweenableParam param, double value) {
		
	}
	
}
