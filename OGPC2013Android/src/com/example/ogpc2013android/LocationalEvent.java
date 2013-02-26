package com.example.ogpc2013android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

public class LocationalEvent extends Event {

	PointF loc;
	RectF area;
	private boolean isPoint;
	
	public LocationalEvent(PointF location, boolean constant) {
		super(constant);
		loc = location;
		isPoint = true;
	}

	public LocationalEvent(RectF area, boolean constant) {
		super(constant);
		this.area = area;
		isPoint = false;
	}
	
	@Override
	public void draw(Canvas c, Paint p) {
		// TODO Auto-generated method stub
		if (!isPoint) {
			p.setColor(Color.rgb(255, 175, 175));
			c.drawRect(area, p);
		}
	}

	@Override
	public boolean condition(Context c) {
		// TODO Auto-generated method stub
		if (isPoint) {
			return RectF.intersects(((RunActivity)c).getPlayer().getCollisionRect(), new RectF(loc.x, loc.y, 2, 2));
		}else {
			return RectF.intersects(((RunActivity)c).getPlayer().getCollisionRect(), area);
		}
	}

	@Override
	public void effect(Context c) {
		// TODO Auto-generated method stub
		this.executed = true;
	}

}
