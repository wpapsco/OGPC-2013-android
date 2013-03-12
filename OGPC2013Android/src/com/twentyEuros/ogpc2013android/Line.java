package com.twentyEuros.ogpc2013android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.shapes.Shape;

public class Line extends Shape {
	
	PointF startPoint;
	PointF centerPoint;
	PointF endPoint;
	
	public Line(PointF startPoint, PointF endPoint) {
		// TODO Auto-generated constructor stub
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		centerPoint = new PointF((startPoint.x + endPoint.x)/2, (startPoint.y + endPoint.y)/2);
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawLine(getX1(), getY1(), getX2(), getY2(), paint);
	}

	public float getCenterX() {
		return centerPoint.x;
	}
	
	public float getCenterY() {
		return centerPoint.y;
	}
	
	public float getX1() {
		return startPoint.x;
	}
	
	public float getX2() {
		return endPoint.x;
	}
	
	public float getY1() {
		return startPoint.y;
	}
	
	public float getY2() {
		return endPoint.y;
	}
}
