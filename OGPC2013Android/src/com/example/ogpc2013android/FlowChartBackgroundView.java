package com.example.ogpc2013android;

import java.util.ArrayList;

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
import android.graphics.RectF;
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
	private int selectedBlockType;
	private int selectedBlockSubType;
	private int selectedBlockId;
	private RectF rectangle;
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
	
	public void setSelectedBlockType(int selectedBlockType, int selectedBlockSubType) {
		this.selectedBlockType = selectedBlockType;
		this.selectedBlockSubType = selectedBlockSubType;
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
		selectedBlockType = -1;
		selectedBlockSubType = -1;
		rectangle = new RectF(-100, -100, -50, -50);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		ArrayList<Block> blocks = ((FlowChartActivity) getContext()).getBlocks();
		p.setColor(Color.BLUE);
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).draw(canvas);
			blocks.get(i).drawLines(canvas);
		}
		canvas.drawLine(100, 0, 100, height, p);
		p.setColor(Color.GREEN);
		canvas.drawRect(rectangle, p);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(selectedBlockType) {
		case Block.COMMAND_BLOCK:
			switch(selectedBlockSubType) {
			case CommandBlock.PRINTLN_BLOCK:
				addBlock(R.drawable.println_block, event.getX(), event.getY());
				invalidate();
			}
		case Block.CONDITIONAL_BLOCK:
			switch(selectedBlockSubType) {
			
			}
		}
		((FlowChartActivity) getContext()).setSelectedBlockID(-1);
		((FlowChartActivity) getContext()).setSelectedBlockType(-1, -1);
		return super.onTouchEvent(event);
			
	}

	private void addBlock(int printlnBlock, float x, float y) {
		((FlowChartActivity) getContext()).addBlock(printlnBlock, x, y);
	}

	public void setSelectionRect(PointF loc) {
		// TODO Auto-generated method stub
		int borderWidth = 2;
		if (loc.x == -1) {
			rectangle.left = -100;
			rectangle.top = -100;
			rectangle.right = -50;
			rectangle.bottom = -50;
			return;
		}
		rectangle.left = loc.x - borderWidth;
		rectangle.top = loc.y - borderWidth;
		rectangle.right = loc.x + 100 + borderWidth;
		rectangle.bottom = loc.y + 50 + borderWidth;
	}
}
