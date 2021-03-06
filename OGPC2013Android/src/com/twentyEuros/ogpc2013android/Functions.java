package com.twentyEuros.ogpc2013android;

import android.graphics.PointF;
import android.graphics.RectF;

/**
 * @author William P.
 */

public abstract class Functions {
	/**
	 * @param x1 x of the first point
	 * @param y1 y of the first point
	 * @param x2 x of the second point
	 * @param y2 y of the second point
	 * @return the distance between the two points
	 */
	public static float distance(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	/**
	 * @param from minimum amount
	 * @param to maximum amount
	 * @return a random integer between from and to
	 */
	public static int genRandomInt(int from, int to) {
		return (int) Math.round(Math.random() * (to - from) + from);
	}
	
	/**
	 * @param from minimum amount
	 * @param to maximum amount
	 * @return a random integer between from and to
	 */
	public static int boolToInt(boolean b) {
		if (b) {return 1;} else {return 0;}
	}
	
	/**
	 * @return a random boolean
	 */
	public static boolean genRandomBool() {
		if (Math.random() >= .5) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return the distance between the two points
	 */
	public static float distance(PointF p1, PointF p2) {
		return (float) Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
	}
	
	/**
	 * @param s_x The x of the object that is pointing
	 * @param s_y The y of the object that is pointing
	 * @param x The x of the point to point at
	 * @param y The y of the point to point at
	 * @return the angle in degrees
	 */
	public static float pointsToAngle(float s_x, float s_y, float x, float y) {
		PointF t_imgFloatPoint = new PointF(x, s_y);
		if (x >= s_x && y >= s_y) {
			return(float) ((float) 90.f + (Math.asin(distance(x, y, t_imgFloatPoint.x, t_imgFloatPoint.y) / distance(s_x, s_y, x, y)) * (180 / Math.PI)));
		}
		if (x >= s_x && y < s_y) {
			return(float) ((float) 90 - (Math.asin(distance(x, y, t_imgFloatPoint.x, t_imgFloatPoint.y) / distance(s_x, s_y, x, y)) * (180 / Math.PI)));
		}
		if (x < s_x && y < s_y) {
			return (float) ((float) 270 + (Math.asin(distance(x, y, t_imgFloatPoint.x, t_imgFloatPoint.y) / distance(s_x, s_y, x, y)) * (180 / Math.PI)));
		}
		if (x < s_x && y >= s_y) {
			return (float) ((float) 270 - (Math.asin(distance(x, y, t_imgFloatPoint.x, t_imgFloatPoint.y) / distance(s_x, s_y, x, y)) * (180 / Math.PI)));
		}
		return 0;
	}
	
	/**
	 * @param rectancle the rectangle to be moved
	 * @param destination the point to move the rectangle to
	 * @return the moved rectangle
	 */
	public static RectF getMovedRectangle(RectF rectangle, PointF destination) {
		float width = rectangle.width();
		float height = rectangle.height();
		rectangle.left = destination.x;
		rectangle.top = destination.y;
		rectangle.right = rectangle.left + width;
		rectangle.bottom = rectangle.top + height;
		return rectangle;
	}
}
