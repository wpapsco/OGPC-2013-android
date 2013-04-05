package com.twentyEuros.ogpc2013android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class RunView extends View {

	public RunView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public RunView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public RunView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init() {
//		TranslateAnimation animation = new TranslateAnimation((float) Functions.genRandomInt(0, 100), (float) Functions.genRandomInt(0, 100), (float) Functions.genRandomInt(0, 100), (float) Functions.genRandomInt(0, 100));
//		animation.setDuration(durationMillis)
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
//		canvas.scale(800f / width, 600f / height);
		canvas.scale(width / 800f, height / 600f);
		((RunActivity)getContext()).drawItems(canvas, new Paint());
//		Paint p = new Paint();
//		p.setColor(Color.GREEN);
//		canvas.drawRect(new Rect(0, 0, 800, 600), p);
	}
	
}
