package com.twentyEuros.ogpc2013android;

import java.util.ArrayList;

import android.R.interpolator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MovingCircutsView extends View {

	private final int MAX_NUM_POINTS = 5;
	int width, height;
	View radialGradient;
	ArrayList<Animation> animations;
	ArrayList<Point> points;
	private boolean animating;
	
	public MovingCircutsView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public MovingCircutsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public MovingCircutsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
		animations = new ArrayList<Animation>();
		points = generatePoints();
		invalidate();
		if (animating) {
			animating = false;
			startAnimation(radialGradient);
		}
	}
	public void startAnimation(View v) {
		if (!animating) {
//			v.setX(points.get(0).x);
//			v.setY(points.get(0).y);
			int[] loc = new int[2];
			radialGradient = v;
			for(int i = 0; i < points.size() - 1; i++) {
//				animations.add(new TranslateAnimation((float) points.get(i).x, (float) points.get(i + 1).x - (float) points.get(i).x, (float) points.get(i).y, (float) points.get(i + 1).y - (float) points.get(i).y));
				animations.add(new TranslateAnimation((float) points.get(i).x, (float) points.get(i + 1).x, (float) points.get(i).y, (float) points.get(i + 1).y));

			}
			for (int i = 0; i < animations.size(); i++) {
				animations.get(i).setDuration((long) (Functions.distance((float) points.get(i).x, (float) points.get(i).y, (float) points.get(i + 1).x, (float) points.get(i + 1).y) * 10));
				animations.get(i).setAnimationListener(new AnimListener(i, animations.size() - 1));
				animations.get(i).setInterpolator(new LinearInterpolator());
			}
			v.startAnimation(animations.get(0));
			animating = true;
		}
	}
	private ArrayList<Point> generatePoints() {
		int numPoints = Functions.genRandomInt(2, MAX_NUM_POINTS);
		ArrayList<Point> points = new ArrayList<Point>();
		boolean xSame = true;
		for (int i = 0; i < numPoints; i++) {
			if (points.size() != 0) {
				if (xSame) {
					points.add(new Point(points.get(points.size() - 1).x, Functions.genRandomInt(0, height)));
				} else {
					points.add(new Point(Functions.genRandomInt(0, width), points.get(points.size() - 1).y));
				}
			} else {
				points.add(new Point(Functions.genRandomInt(0, width), Functions.genRandomInt(0, height)));
			}
			xSame = !xSame;
		}
		return points;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
//		radialGradient.draw(canvas);
//		for (int i = 0; i < points.size() - 1; i++) {
//			canvas.drawLine(points.get(i).x, points.get(i).y, points.get(i + 1).x, points.get(i + 1).y, new Paint());
//		}
	}
	private class AnimListener implements AnimationListener {
		int pos;
		int maxpos;
		public AnimListener(int position, int maxpos) {
			pos = position;
			this.maxpos = maxpos;
		}
		@Override
		public void onAnimationEnd(Animation arg0) {
			// TODO Auto-generated method stub
			Log.e("endanim", "animdone" + pos);
			if (pos == maxpos) {
				init();
			} else {
				radialGradient.startAnimation(animations.get(pos + 1));
			}
		}

		@Override
		public void onAnimationRepeat(Animation arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationStart(Animation arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
