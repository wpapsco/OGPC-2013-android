package com.twentyEuros.ogpc2013android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public class EnemiesKilledEvent extends Event {

	public EnemiesKilledEvent(boolean constant) {
		// TODO Auto-generated constructor stub
		super(constant);
	}

	@Override
	public boolean condition(Context c) {
		if (((RunActivity)c).getMap().getEnemies().size() <= 0) {
			return true;
		}
		return false;
	}

	@Override
	public void effect(Context c) {
		// TODO Auto-generated method stub
		this.executed = true;
	}

	@Override
	public void draw(Canvas c, Paint p) {
		// TODO Auto-generated method stub
		
	}
}
