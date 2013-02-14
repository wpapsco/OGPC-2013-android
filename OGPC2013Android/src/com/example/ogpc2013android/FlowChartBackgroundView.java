package com.example.ogpc2013android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


public class FlowChartBackgroundView extends View {
	
	private int width;
	private int height;
	private PointF invisibitmapLoc;
	private Bitmap invisibitmap;
	private boolean drawInvisibitmap;
	boolean b = false;
	private Arrow selectedBlockTypeArrow;
	Paint p;
	
	public FlowChartBackgroundView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public FlowChartBackgroundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public void setPointerLocation(int loc) {
		selectedBlockTypeArrow = new Arrow(new PointF(115, (50 * loc) + 25), new PointF(165, (50 * loc) + 25));
	}
	
	public FlowChartBackgroundView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public void setDrawTransparentBitmap(boolean b) {
		drawInvisibitmap = b;
	}

	public void setInvisibitmap(int resId, Resources resources) {
		invisibitmap = BitmapFactory.decodeResource(resources, resId);
	}
	
	public void setInvisibitmapLoc(PointF point) {
		invisibitmapLoc = point;
		invalidate();
	}
	
	public void init() {
		Display d = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		height = d.getHeight();
		width = d.getWidth();
		invisibitmapLoc = new PointF(100.f, 100.f);
		p = new Paint();
		selectedBlockTypeArrow = new Arrow(new PointF(-100, -100), new PointF(0, 0));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
//		if (invisibitmap != null && drawInvisibitmap) {
//			p.setAlpha(191);
//			canvas.drawBitmap(invisibitmap, invisibitmapLoc.x, invisibitmapLoc.y, p);
//		}
		p.setColor(Color.BLUE);
		canvas.drawLine(100, 0, 100, height, p);
		p.setColor(Color.RED);
		selectedBlockTypeArrow.draw(canvas, p);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		return super.onTouchEvent(event);
			
	}
}
