package com.example.ogpc2013android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import android.content.Context;
import android.content.res.Resources;

public class Enemy {

	private final int MAX_HEALTH = 100;
	private int health = MAX_HEALTH;
	private Bitmap image;
	private PointF loc;
	private RectF collisionRect;
	private boolean isDead;
	
	public Enemy(Resources res, int Resid, PointF location) {
		image = BitmapFactory.decodeResource(res, Resid);
		loc = location;
		collisionRect = new RectF(0, 0, image.getWidth(), image.getHeight());
		collisionRect.left = loc.x;
		collisionRect.top = loc.y;
		this.isDead = false;
	}

	public void update(Context c) {
		
	}

	public void takeDamage(int damage) {
		health-=damage;
		if (health <= 0) {onDeath();}
	}
	
	public void onDeath() {
		isDead = true;
	}
	
	public RectF getCollisionRect() {
		return collisionRect;
	}
	
	public void draw(Canvas c, Paint p) {
		c.drawBitmap(image, loc.x, loc.y, null);
	}

	public boolean isMarkedForDeletion() {
		// TODO Auto-generated method stub
		return isDead;
	}

	public PointF getLoc() {
		// TODO Auto-generated method stub
		return loc;
	}

	public boolean isDead() {
		// TODO Auto-generated method stub
		return isDead;
	}

	public void isDead(boolean b) {
		isDead = b;
	}
}
