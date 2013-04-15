package com.twentyEuros.ogpc2013android;

import java.util.ArrayList;

import com.twentyEuros.ogpc2013android.R;
import com.twentyEuros.ogpc2013android.R.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Bitmap;
import android.graphics.RectF;

public class Player {
	private PointF loc;
	private Bitmap Bitmap;
	private Bitmap bulletBitmap;
	private RectF collisionRect;
	private boolean isColliding = false;
	public static int bulletID = R.drawable.bullet;
	private ArrayList<Bullet> bullets;
	float rotation;
	float fireRecharge = 1000;
	
	public Player(PointF loc, int resID, Resources res) {
		this.loc = loc;
		this.Bitmap = BitmapFactory.decodeResource(res, resID);
		collisionRect = new RectF(0, 0, 50f - 2f, 50f - 2f);
		collisionRect.left = loc.x;
		collisionRect.top = loc.y;
		rotation = 0;
		bulletBitmap = BitmapFactory.decodeResource(res, bulletID);
		bullets = new ArrayList<Bullet>();
	}

	public Player(PointF loc, Bitmap playerImage, Resources res) {
		// TODO Auto-generated constructor stub
		this.loc = loc;
		this.Bitmap = playerImage;
		collisionRect = new RectF(loc.x, loc.y, loc.x + 50f - 2f, loc.y + 50f - 2f);
//		collisionRect.left = loc.x;
//		collisionRect.top = loc.y;
		rotation = 0;
		bulletBitmap = BitmapFactory.decodeResource(res, R.drawable.bullet);
		bullets = new ArrayList<Bullet>();
	}

	public PointF getLoc() {
		return loc;
	}
	
	public boolean hasBullets() {
		return bullets.size() != 0;
	}
	
	public void draw(Canvas c, Paint p) {
		Matrix matrix = new Matrix();
		matrix.setRotate(rotation, Bitmap.getWidth()/2, Bitmap.getHeight()/2);
		matrix.postTranslate(loc.x, loc.y);
		c.drawBitmap(Bitmap, matrix, p);
//		g.draw(collisionRect);
//		p.setColor(Color.GREEN);
//		c.drawRect(collisionRect, p);
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(c, p);
		}
	}
	
	public void setRect(RectF r) {
		collisionRect = r;
	}
	
	public void update(int delta, Map m) {
		if (fireRecharge <= 1000) {
			fireRecharge+=delta;
		}
		ArrayList<Bullet> TList = new ArrayList<Bullet>();
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update(delta);
			if (m.isColliding(bullets.get(i).getCollisionRect())) {
				bullets.get(i).setExplosionLocation(bullets.get(i).getPath().currentPoint);
				bullets.get(i).markDeleted();
			}
			if (!bullets.get(i).isFinishedExploding()) {
				TList.add(bullets.get(i));
			}
		}
//		collisionRect = new RectF(loc.x, loc.y, loc.x + Bitmap.getHeight() - 2, loc.y + Bitmap.getWidth() - 2);
		bullets = TList;
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	public Bitmap getBitmap() {
		return Bitmap;
	}
	
	public void reset(PointF PointF) {
		loc = PointF;
		collisionRect = new RectF(loc.x, loc.y, loc.x + Bitmap.getHeight() - 2, loc.y + Bitmap.getWidth() - 2);
		rotation = 0;
		bullets.clear();
		fireRecharge = 0;
	}
	public RectF getCollisionRect() {
		return collisionRect;
	}
	public void setColliding(boolean b) {
		// TODO Auto-generated method stub
		isColliding = b;
	}
	public void moveForeward(float distance) {
		float tfloat = rotation - 90;
		if (tfloat < 0) {
			tfloat+=360;
		}
		collisionRect = getForewardRect(distance);
		loc.x = (float) (loc.x + distance * Math.cos(Math.toRadians(tfloat)));
		loc.y = (float) (loc.y + distance * Math.sin(Math.toRadians(tfloat)));
	}
	public RectF getForewardRect(float distance) {
		RectF ret = new RectF(collisionRect);
		float height = ret.height();
		float width = ret.width();
		ret.left = getForewardPosition(distance).x;
		ret.top = getForewardPosition(distance).y;
		ret.right = ret.left + width;
		ret.bottom = ret.top + height;
		return ret;
	}
	public PointF getForewardPosition(float distance) {
		float tfloat = rotation - 90;
		if (tfloat < 0) {
			tfloat+=360;
		}
		return new PointF((float) (loc.x + distance * Math.cos(Math.toRadians(tfloat))), (float) (loc.y + distance * Math.sin(Math.toRadians(tfloat))));
	}
	public PointF getMovedPosition(float distance, float angle) {
		float tfloat = rotation - 90 + angle;
		if (tfloat < 0) {
			tfloat+=360;
		}
		if (tfloat > 360) {
			tfloat-=360;
		}
		return new PointF((float) (loc.x + distance * Math.cos(Math.toRadians(tfloat))), (float) (loc.y + distance * Math.sin(Math.toRadians(tfloat))));
	}
	public void setRotate(float r) {
		if (r > 360) {r-=360;}
		rotation = r;
	}
	public void rotate(float r) {
		setRotate(r + rotation);
	}
	public void setBitmap(Bitmap Bitmap) {
		this.Bitmap = Bitmap;
	}
	public boolean isColliding() {
		return isColliding;
	}
	public void fire(Context c) {
		if (fireRecharge >= 1000) {
			bullets.add(new Bullet(new PointF(loc.x + (25 / 2), loc.y + (25 / 2)), new PointF(getForewardPosition(200).x + (25 / 2), getForewardPosition(200).y + (25 / 2)), bulletBitmap, 100.f, c));
			fireRecharge = 0;
		}
	}
	public void dropBomb() {
		
	}
	public void setLoc(PointF PointF) {
		// TODO Auto-generated method stub
		this.loc = PointF;
		collisionRect = new RectF(loc.x, loc.y, loc.x + Bitmap.getHeight() - 2, loc.y + Bitmap.getWidth() - 2);
	}
	
}
