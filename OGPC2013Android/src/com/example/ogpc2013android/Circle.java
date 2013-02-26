package com.example.ogpc2013android;

import android.graphics.RectF;

public class Circle {

	public float x, y;
	private float explosionRadius;
	
	public Circle(float x, float y, float radius) {
		this.x = x;
		this.y = y;
		explosionRadius = radius;
	}
	
	public float getExplosionRadius() {
		return explosionRadius;
	}
	
	public boolean collide(RectF rect) {
		boolean ret = false;
		if (Functions.distance(rect.left, rect.top, x, y) <= explosionRadius) {
			ret = true;
		}
		if (Functions.distance(rect.right, rect.top, x, y) <= explosionRadius) {
			ret = true;
		}
		if (Functions.distance(rect.left, rect.bottom, x, y) <= explosionRadius) {
			ret = true;
		}
		if (Functions.distance(rect.right, rect.bottom, x, y) <= explosionRadius) {
			ret = true;
		}
		return ret;
	}
	
}
