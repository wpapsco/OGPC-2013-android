package com.twentyEuros.ogpc2013android;

import java.net.URI;
import java.util.ArrayList;

import com.twentyEuros.ogpc2013android.R;
import com.twentyEuros.ogpc2013android.R.drawable;
import com.twentyEuros.ogpc2013android.R.id;
import com.twentyEuros.ogpc2013android.R.layout;
import com.twentyEuros.ogpc2013android.R.raw;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class LevelSelectActivity extends Activity {
	
	private ArrayList<AnimationDrawable> frameAnimations;
	private ArrayList<ImageView> images;
	private int world;
	private final int WORLD_TUTORIAL = 0;
	private final int WORLD_SPINE = 4;
	private final int WORLD_BRAIN = 7;
	private float width;
	private float height;
	private MediaPlayer m;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (!DataSingleton.cheatMode) {
			SharedPreferences prefs = getSharedPreferences("prefs", 0);
			int level = prefs.getInt("level", 0);
			DataSingleton.setLevel(level);
		}
		
		world = WORLD_TUTORIAL;
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		height = displaymetrics.heightPixels;
		width = displaymetrics.widthPixels;
		frameAnimations = new ArrayList<AnimationDrawable>();
		images = new ArrayList<ImageView>();
		
		setContentView(R.layout.activity_level_select);
		
		images.add((ImageView)findViewById(R.id.imageView2));
		images.add((ImageView)findViewById(R.id.imageView3));
		images.add((ImageView)findViewById(R.id.imageView4));
		images.add((ImageView)findViewById(R.id.imageView5));
		
		findViewById(R.id.next_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//go to next level
				goNextWorld();
			}
		});
		findViewById(R.id.previous_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//go to previous level
				goPreviousWorld();
			}
		});
		
		for (int i = 0; i < images.size(); i++) {
			frameAnimations.add((AnimationDrawable) getResources().getDrawable(R.drawable.level_animation));
		}
		
		for (int i = 0; i < images.size(); i++) {
			images.get(i).setBackgroundDrawable(frameAnimations.get(i));
		}
		
//		images.get(0).setX((108f/800f) * width);
//		images.get(0).setY((368f/600f) * height);
//		images.get(1).setX(((668f - 25f)/800f) * width);
//		images.get(1).setY(((247f - 25f)/600f) * height);
//		images.get(2).setX(((527f - 25f)/800f) * width);
//		images.get(2).setY(((453f - 40f)/600f) * height);
//		images.get(3).setX(((400f - 15f)/800f) * width);
//		images.get(3).setY(((140f - 30f)/600f) * height);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); //The WRAP_CONTENT parameters can be replaced by an absolute width and height or the FILL_PARENT option)
		params.leftMargin = Math.round((108f/800f) * width); //Your X coordinate
		params.topMargin = Math.round((368f/600f) * height); //Your Y coordinate
		images.get(0).setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); //The WRAP_CONTENT parameters can be replaced by an absolute width and height or the FILL_PARENT option)
		params.leftMargin = Math.round(((668f - 25f)/800f) * width); //Your X coordinate
		params.topMargin = Math.round(((247f - 25f)/600f) * height); //Your Y coordinate
		images.get(1).setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); //The WRAP_CONTENT parameters can be replaced by an absolute width and height or the FILL_PARENT option)
		params.leftMargin = Math.round(((527f - 25f)/800f) * width); //Your X coordinate
		params.topMargin = Math.round(((453f - 40f)/600f) * height); //Your Y coordinate
		images.get(2).setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); //The WRAP_CONTENT parameters can be replaced by an absolute width and height or the FILL_PARENT option)
		params.leftMargin = Math.round(((400f - 15f)/800f) * width); //Your X coordinate
		params.topMargin = Math.round(((140f - 30f)/600f) * height); //Your Y coordinate
		images.get(3).setLayoutParams(params);
		
		images.get(0).setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				enterLevel(world + 0);
			}
		});
		images.get(1).setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				enterLevel(world + 1);
			}
		});
		images.get(2).setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				enterLevel(world + 2);
			}
		});
		images.get(3).setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				enterLevel(world + 3);
			}
		});
		
		m = MediaPlayer.create(this, R.raw.gameplay);
	}
	
	protected void goPreviousWorld() {
		// TODO Auto-generated method stub
		switch(world) {
		case WORLD_TUTORIAL:
			//do nothing
			break;
		case WORLD_SPINE:
			setWorld(WORLD_TUTORIAL);
			break;
		case WORLD_BRAIN:
			setWorld(WORLD_SPINE);
			break;
		}
		dod(false);
	}

	protected void goNextWorld() {
		// TODO Auto-generated method stub
		switch(world) {
		case WORLD_TUTORIAL:
			setWorld(WORLD_SPINE);
			break;
		case WORLD_SPINE:
			setWorld(WORLD_BRAIN);
			break;
		case WORLD_BRAIN:
			//do nothing
			break;
		}
		dod(false);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		SharedPreferences prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE);
		int level = prefs.getInt("level", 0);
		DataSingleton.setLevel(level);
	}
	
	public void enterLevel(int level) {
		Intent intent = new Intent(this, FlowChartActivity.class);
		intent.putExtra("level", level);
		startActivity(intent);
	}
	
	public void dod(boolean fromHasFocus) {
		for (int i = 0; i < DataSingleton.getLevel() + 1 - world && i < images.size(); i++) {
			images.get(i).setVisibility(View.VISIBLE);
		}
	}
	
	public void setWorld(int world) {
		for (int i = 0; i < images.size(); i++) {
			  images.get(i).setVisibility(View.INVISIBLE);
		}
		if (world == WORLD_BRAIN) {
			//change world
			this.world = WORLD_BRAIN;
			//change locations for level icons
			((ImageView) findViewById(R.id.imageView1)).setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.brain));
		}
		if (world == WORLD_SPINE) {
			//change world
			this.world = WORLD_SPINE;
			//change locations for level icons
			((ImageView) findViewById(R.id.imageView1)).setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.spine));
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); //The WRAP_CONTENT parameters can be replaced by an absolute width and height or the FILL_PARENT option)
			params.leftMargin = Math.round((100f/800f) * width); //Your X coordinate
			params.topMargin = Math.round((200f/600f) * height); //Your Y coordinate
			images.get(0).setLayoutParams(params);
		}
		if (world == WORLD_TUTORIAL) {
			//change world
			this.world = WORLD_TUTORIAL;
			//change locations for level icons
			((ImageView) findViewById(R.id.imageView1)).setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tutorial_level_select));
		}
	}
	
	@Override
	public void onWindowFocusChanged (boolean hasFocus) {
	   super.onWindowFocusChanged(hasFocus);
	   if (hasFocus){
			  for (int i = 0; i < frameAnimations.size(); i++) {
		    	  frameAnimations.get(i).start();
		      }
			  if (DataSingleton.getLevel() > 3) {
				  setWorld(WORLD_SPINE);
			  }
			  if (DataSingleton.getLevel() > 6) {
				  setWorld(WORLD_BRAIN);
			  }
			  if (DataSingleton.getLevel() <= 3) {
				  setWorld(WORLD_TUTORIAL);
			  }
		      dod(true);
	   } else {
		   	Log.e("saving", "" + DataSingleton.getLevel());
			SharedPreferences prefs = getSharedPreferences("prefs", 0);
			SharedPreferences.Editor edit = prefs.edit();
			edit.putInt("level", DataSingleton.getLevel() - 1);
			edit.commit();
	   }
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}
	
	
	
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e("saving", "" + DataSingleton.getLevel());
		SharedPreferences prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = prefs.edit();
		edit.putInt("level", DataSingleton.getLevel() - 1);
		edit.commit();
	}

}
