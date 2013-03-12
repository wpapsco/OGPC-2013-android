package com.twentyEuros.ogpc2013android;

import android.graphics.PointF;

public class LimitedPath {
	public PointF startPoint;
	public PointF endPoint;
	float slope;
	float angle;
	public PointF currentPoint;
	float distance;
	boolean canUpdate;
	public LimitedPath(float s_x, float s_y, float e_x, float e_y) {
		startPoint = new PointF(s_x, s_y);
		endPoint = new PointF(e_x, e_y);
		slope = (float) (startPoint.y - endPoint.y) / (startPoint.x - endPoint.x);
		angle = (float) Math.atan(slope);
		currentPoint = new PointF(startPoint.x, startPoint.y);
		distance = Functions.distance(startPoint, endPoint);
		canUpdate = true;
	}
	
	public LimitedPath(PointF startPoint, PointF endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		slope = (float) (startPoint.y - endPoint.y) / (startPoint.x - endPoint.x);
		angle = (float) Math.atan(slope);
		currentPoint = new PointF(startPoint.x, startPoint.y);
		distance = Functions.distance(startPoint, endPoint);
		canUpdate = true;
	}
	
	public boolean isDone() {
		return !canUpdate;
	}
	
	public void update(float delta) {
		slope = (float) (startPoint.y - endPoint.y) / (startPoint.x - endPoint.x);
		angle = (float) Math.atan(slope);
		distance = Functions.distance(currentPoint, endPoint);
		if (canUpdate) {
			if (slope < 1 && slope > -1 && slope != Float.POSITIVE_INFINITY) {
				//quadrant 4
				if (startPoint.x - endPoint.x <= 0 && startPoint.y - endPoint.y <= 0) {
					currentPoint.y = currentPoint.y + slope*delta;
					currentPoint.x = currentPoint.x + delta;
				}
				//quadrant 2
				if (startPoint.x - endPoint.x > 0 && startPoint.y - endPoint.y > 0) {
					currentPoint.y = currentPoint.y - slope*delta;
					currentPoint.x = currentPoint.x - delta;
				}
				//quadrant 3
				if (startPoint.x - endPoint.x >= 0 && startPoint.y - endPoint.y <= 0) {
					currentPoint.y = currentPoint.y - slope*delta;
					currentPoint.x = currentPoint.x - delta;
				}
				//quadrant 1
				if (startPoint.x - endPoint.x < 0 && startPoint.y - endPoint.y > 0) {
					currentPoint.y = currentPoint.y + slope*delta;
					currentPoint.x = currentPoint.x + delta;
				}
			}
			
			if(slope >= 1 && slope != Float.POSITIVE_INFINITY || slope <= -1 && slope != Float.POSITIVE_INFINITY) {
				//quadrant 4
				if (startPoint.x - endPoint.x <= 0 && startPoint.y - endPoint.y <= 0) {
					currentPoint.y = currentPoint.y + delta;
					currentPoint.x = currentPoint.x + (1/slope)*delta;
				}
				//quadrant 2
				if (startPoint.x - endPoint.x > 0 && startPoint.y - endPoint.y > 0) {
					currentPoint.y = currentPoint.y - delta;
					currentPoint.x = currentPoint.x - (1/slope)*delta;
				}
				//quadrant 3
				if (startPoint.x - endPoint.x >= 0 && startPoint.y - endPoint.y <= 0) {
					currentPoint.y = currentPoint.y + delta;
					currentPoint.x = currentPoint.x + (1/slope)*delta;
				}
				//quadrant 1
				if (startPoint.x - endPoint.x < 0 && startPoint.y - endPoint.y > 0) {
					currentPoint.y = currentPoint.y - delta;
					currentPoint.x = currentPoint.x - (1/slope)*delta;
				}
			}
			if (slope == Float.POSITIVE_INFINITY) {
				currentPoint.y = currentPoint.y - 1;
			}
		}
		if (distance <= Functions.distance(currentPoint, endPoint) && canUpdate == true) {
			canUpdate = false;
		}
	}
}