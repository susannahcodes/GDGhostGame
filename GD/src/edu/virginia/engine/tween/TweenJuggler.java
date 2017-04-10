package edu.virginia.engine.tween;

import java.util.ArrayList;

public class TweenJuggler {
	private ArrayList<Tween> ListOfTweens;
	
	public TweenJuggler() {
		ListOfTweens = new ArrayList<Tween>();
	}
	
	public void add(Tween tween) {
		ListOfTweens.add(tween);
	}
	
	public void nextFrame() {
		for (Tween t : ListOfTweens) {
			t.update();
		}
	}

}
