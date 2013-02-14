package com.example.ogpc2013android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FlowChartActivity extends Activity {

	int level;
	private ArrayList<Block> blocks;
	private ImageView printlnBlockButton;
	private int selectedBlockType;
	FlowChartBackgroundView BgView;
	
	public FlowChartActivity() {

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		level = i.getIntExtra("level", -1);
		if (level < 0) {
			Log.e("FlowChartActivity", "Level returning default value, or has been calculated wrong");
		}
		
		this.setContentView(R.layout.activity_flowchart);
		
		BgView = (FlowChartBackgroundView) findViewById(R.id.bg_view);
		BgView.setInvisibitmap(R.drawable.println_block, this.getResources());
		BgView.setDrawTransparentBitmap(true);
		
		ImageView printlnButton = (ImageView) findViewById(R.id.println);
		
		printlnButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				selectedBlockType = 0;
				((FlowChartActivity) arg0.getContext()).BgView.setPointerLocation(selectedBlockType);
				Log.e("got here", "");
			}
		});
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		BgView.setInvisibitmapLoc(new PointF(event.getX(), event.getY()));
		return super.onTouchEvent(event);
	}
}
