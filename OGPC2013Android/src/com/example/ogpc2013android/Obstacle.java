package com.example.ogpc2013android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Obstacle {

	RectF obstacleShape;
	
	public Obstacle(RectF s) {
		obstacleShape = s;
	}
	
	public void draw(Canvas c, Paint p) {
		c.drawRect(obstacleShape, p);
	}

	public boolean collides(RectF s) {
		return RectF.intersects(obstacleShape, s);
	}
	
}
