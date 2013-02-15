package com.example.ogpc2013android;

import java.net.URI;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LevelSelectActivity extends Activity {
	
	private ArrayList<AnimationDrawable> frameAnimations;
	private ArrayList<ImageView> images;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		frameAnimations = new ArrayList<AnimationDrawable>();
		images = new ArrayList<ImageView>();
		
		setContentView(R.layout.activity_level_select);
//		ImageView img = (ImageView)findViewById(R.id.imageView2);
//		img.setBackgroundResource(R.drawable.level_animation);
//		frameAnimation = (AnimationDrawable) img.getBackground();
//		
//		ImageView img2 = (ImageView)findViewById(R.id.imageView3);
//		img2.setBackgroundResource(R.drawable.level_animation);
//		frameAnimation2 = (AnimationDrawable) img2.getBackground();
		
		images.add((ImageView)findViewById(R.id.imageView2));
		images.add((ImageView)findViewById(R.id.imageView3));
		images.add((ImageView)findViewById(R.id.imageView4));
		images.add((ImageView)findViewById(R.id.imageView5));
		
		//images.get(3).setVisibility(View.INVISIBLE);
		
		for (int i = 0; i < images.size(); i++) {
			frameAnimations.add((AnimationDrawable) getResources().getDrawable(R.drawable.level_animation));
		}
		
		for (int i = 0; i < images.size(); i++) {
			images.get(i).setBackgroundDrawable(frameAnimations.get(i));
		}
		
		for (int i = 0; i < images.size(); i++) {
			images.get(i).setX((width / 10.f) * i);
			images.get(i).setY((height / 10.f) * i);
		}
		
		images.get(0).setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				setLevel(getLevelNum(0));
				enterLevel(DataSingleton.currentLevel);
			}
		});
		images.get(1).setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				setLevel(getLevelNum(1));
			}
		});
		images.get(2).setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				setLevel(getLevelNum(2));
			}
		});
		images.get(3).setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				setLevel(getLevelNum(3));
//				if (v.getContext() instanceof LevelSelectActivity) {
//					((LevelSelectActivity) v.getContext()).frameAnimations;
//				}
			}
		});
		
//		img2.setX(width / 10.f);
//		img2.setY(height / 10.f);
	}
	
	public int getLevelNum(int buttonPressed) {
		return DataSingleton.currentLevel + buttonPressed;
	}
	
	public void setLevel(int level) {
		
	}
	
	public void enterLevel(int level) {
		Intent intent = new Intent(this, FlowChartActivity.class);
		intent.putExtra("level", level);
		startActivity(intent);
	}
	
	@Override
	public void onWindowFocusChanged (boolean hasFocus) {
	   super.onWindowFocusChanged(hasFocus);
	   if (hasFocus){
	      for (int i = 0; i < frameAnimations.size(); i++) {
	    	  frameAnimations.get(i).start();
	      }
	      for (int i = DataSingleton.currentLevel + 1; i <= 3; i++) {
	    	  images.get(i).setVisibility(View.INVISIBLE);
	      }
//	      frameAnimation2.start();
	   }
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		images.get(0).setX(event.getX());
		return super.onTouchEvent(event);
	}

}
