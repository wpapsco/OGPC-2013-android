package com.twentyEuros.ogpc2013android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.shapes.Shape;

public class Arrow extends Shape {
	
	Line baseLine;
	Line arrowLine1;
	Line arrowLine2;

	public Arrow(PointF endPoint, PointF startPoint) {
		this(new Line(startPoint, endPoint));
	}
	
	public Arrow(Line line) {
		baseLine = line;
		PointF endPoint = new PointF(baseLine.getX2(), baseLine.getY2());
		float angle = Functions.pointsToAngle(baseLine.getX1(), baseLine.getY1(), baseLine.getX2(), baseLine.getY2()) - 270;
		angle = angle - 45;
		if (angle < 0) {
			angle+=360;
		}
		float xval = (float) (.25 * Math.toDegrees(Math.cos(Math.toRadians(angle))) + endPoint.x);
		float yval = (float) (.25 * Math.toDegrees(Math.sin(Math.toRadians(angle))) + endPoint.y);
		arrowLine1 = (new Line(endPoint, new PointF(xval, yval)));
		angle = angle + 90;
		if (angle > 360) {
			angle-=360;
		}
		xval = (float) (.25 * Math.toDegrees(Math.cos(Math.toRadians(angle))) + endPoint.x);
		yval = (float) (.25 * Math.toDegrees(Math.sin(Math.toRadians(angle))) + endPoint.y);
		arrowLine2 = new Line(endPoint, new PointF(xval, yval));
	}
	
	@Override
	public void draw(Canvas c, Paint arg1) {
		// TODO Auto-generated method stub
		baseLine.draw(c, arg1);
		arrowLine1.draw(c, arg1);
		arrowLine2.draw(c, arg1);
	}
	
}