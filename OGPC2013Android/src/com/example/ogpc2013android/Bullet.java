package com.example.ogpc2013android;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.shapes.Shape;

public class Bullet {
	
	private PointF startPoint;
	private PointF endPoint;
	private PointF currentPoint;
	private RectF collisionRect;
	private Bitmap image;
	private LimitedPath path;
	private boolean isMarkedForDeletion;
	private boolean isFinishedExploding = false;
	private float explosionRadius;
	private Circle explosionCircle;
	private int totalDelta = 0;
	
	public Bullet(PointF startPoint, PointF endPoint, Bitmap b, float explosionRadius) {
		this.startPoint = startPoint;
		this.explosionRadius = explosionRadius;
		this.endPoint = endPoint;
		path = new LimitedPath(startPoint, endPoint);
		currentPoint = path.currentPoint;
		this.image = b;
		collisionRect = new RectF(currentPoint.x, currentPoint.y, image.getWidth() + currentPoint.x, image.getHeight() + currentPoint.y);
		this.explosionRadius = explosionRadius;
		explosionCircle = new Circle(endPoint.x, endPoint.y, explosionRadius);
	}

	public void update(int delta) {
		if (!isMarkedForDeletion) {
			path.update(delta);
			currentPoint = path.currentPoint;
		}
		collisionRect = new RectF(currentPoint.x, currentPoint.y, image.getWidth() + currentPoint.x, image.getHeight() + currentPoint.y);
		if (path.isDone()) {
			this.markDeleted();
		}
		if (isMarkedForDeletion && totalDelta < 100) {
			totalDelta+=delta;
		}
		if (totalDelta >= 100) {
			isFinishedExploding = true;
		}
	}
	
	public void markDeleted() {
		isMarkedForDeletion = true;
	}
	
	public boolean isMarkedForDeletion() {
		return isMarkedForDeletion;
	}
	
	public void setExplosionLocation(PointF currentPointation) {
		explosionCircle = new Circle(currentPointation.x, currentPointation.y, explosionRadius);
	}
	
	public LimitedPath getPath() {
		return path;
	}
	
	public RectF getCollisionRect() {
		return collisionRect;
	}
	
	public void draw(Canvas c, Paint p) {
		if (!isMarkedForDeletion) {
			p.setColor(Color.GREEN);
			Matrix matrix = new Matrix();
			matrix.setTranslate(currentPoint.x, currentPoint.y);
//			matrix.postRotate(path.angle, image.getWidth()/2, image.getHeight()/2);
			c.drawBitmap(image, matrix, new Paint());
//			g.draw(collisionRect);
			p.setColor(Color.GREEN);
			c.drawRect(collisionRect, p);
		}
		if (isMarkedForDeletion && !isFinishedExploding) {
			p.setColor(Color.RED);
			p.setStyle(Paint.Style.STROKE);
			c.drawCircle(explosionCircle.x, explosionCircle.y, explosionCircle.getExplosionRadius(), p);
			p.setStyle(Paint.Style.FILL);
		}
	}

	public boolean isFinishedExploding() {
		// TODO Auto-generated method stub
		return isFinishedExploding;
	}

	public Circle getExplosionCircle() {
		// TODO Auto-generated method stub
		return explosionCircle;
	}
}
