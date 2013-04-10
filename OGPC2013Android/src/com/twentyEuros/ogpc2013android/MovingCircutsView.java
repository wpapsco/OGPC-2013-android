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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MovingCircutsView extends View {

	private final int MAX_NUM_POINTS = 7;
	private final int FADE_LENGTH = 1000;
	int width, height;
	View radialGradient;
	ArrayList<Animation> animations;
	Animation fadeOut;
	Animation fadeIn;
	AnimationSet aSet;
	ArrayList<Point> points;
	private boolean animating;
//	private Point currentPoint;
	private boolean init = false;
//	Thread t;
//	ThreadRunnable r;
	
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
		fadeOut = new AlphaAnimation(1f, 0f);
		fadeIn = new AlphaAnimation(0f, 1f);
		fadeIn.setDuration(100);
		fadeIn.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				init();
			}
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		aSet = new AnimationSet(false);
		fadeOut.setDuration(FADE_LENGTH);
//		r = new ThreadRunnable(points.get(0), points.get(1));
//		t = new Thread(r);
//		t.start();
		if (animating) {
			animating = false;
			startAnimation(radialGradient);
		}
		init = true;
	}
	public void startAnimation(View v) {
		if (!animating) {
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
			aSet.addAnimation(animations.get(animations.size() - 1));
			fadeOut.setStartOffset(animations.get(animations.size() - 1).getDuration() - FADE_LENGTH);
			fadeOut.setFillAfter(false);
			aSet.addAnimation(fadeOut);
			v.startAnimation(animations.get(0));
			animating = true;
		}
	}
	private ArrayList<Point> generatePoints() {
		int numPoints = Functions.genRandomInt(2, MAX_NUM_POINTS);
		ArrayList<Point> points = new ArrayList<Point>();
		boolean xSame = Functions.genRandomBool();
		for (int i = 0; i < numPoints; i++) {
			if (points.size() != 0) {
				if (xSame) {
					points.add(new Point(points.get(points.size() - 1).x, Functions.genRandomInt(0, height)));
				} else {
					points.add(new Point(Functions.genRandomInt(0, width), points.get(points.size() - 1).y));
				}
			} else {
				if (xSame) {
					points.add(new Point(Functions.boolToInt(Functions.genRandomBool()) * width, Functions.genRandomInt(0, height)));
				} else {
					points.add(new Point(Functions.genRandomInt(0, width), Functions.boolToInt(Functions.genRandomBool()) * height));
				}
			}
			xSame = !xSame;
		}
		return points;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
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
			if (pos == maxpos) {
				radialGradient.startAnimation(fadeIn);
			} else if (pos == maxpos - 1) {
				radialGradient.startAnimation(aSet);
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
//	public class ThreadRunnable implements Runnable {
//		private Point direction;
//		private Point curPoint;
//		//speed = .1 px/ms = 1 px/10ms
//		public ThreadRunnable(Point startPoint, Point startDirection) {
//			curPoint = startPoint;
//			direction = startDirection;
//		}
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			//move 2 px
//			if (direction.x == curPoint.x) {
//				if (direction.y - curPoint.y > 0) {
//					curPoint.y+=2;
//				}
//				if (direction.y - curPoint.y <= 0) {
//					curPoint.y-=2;
//				}
//			}
//			if (direction.y == curPoint.y) {
//				if (direction.x - curPoint.x > 0) {
//					curPoint.x+=2;
//				}
//				if (direction.x - curPoint.x <= 0) {
//					curPoint.x-=2;
//				}
//			}
//			invalidate();
//			//wait 20 ms
//			try {
//				Thread.sleep(20);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		public void changeDirection(Point p) {
//			curPoint = direction;
//			direction = p;
//		}
//		public Point getCurrentPoint() {
//			return curPoint;
//		}
//	}
}
