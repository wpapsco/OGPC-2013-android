package com.twentyEuros.ogpc2013android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Event {
	
	protected boolean constant, executed;
	
	public Event(boolean constant) {
		this.constant = constant;
		executed = false;
	}
	
	public void update(Context c) {
		if (condition(c)) {
			if (constant) {
				effect(c);
			}
			if (!constant && !executed) {
				effect(c);
				executed = true;
			}
		}
	}
	
	public boolean isExecuted() {
		return executed;
	}
	
	public void reset() {
		executed = false;
	}
	
	public abstract void draw(Canvas c, Paint p);
	
	public abstract boolean condition(Context c);
	
	public abstract void effect(Context c);
}
