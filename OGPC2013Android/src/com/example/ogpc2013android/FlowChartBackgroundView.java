package com.example.ogpc2013android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class FlowChartBackgroundView extends View {

	public FlowChartBackgroundView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public FlowChartBackgroundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public FlowChartBackgroundView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawRGB(179, 179, 179);
		Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.location1);
		canvas.drawBitmap(b, 0, 0, null);
	}
}
