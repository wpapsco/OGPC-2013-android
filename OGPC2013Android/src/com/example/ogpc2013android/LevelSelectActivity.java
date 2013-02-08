package com.example.ogpc2013android;

import java.net.URI;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LevelSelectActivity extends Activity {
	
	AnimationDrawable frameAnimation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_select);
		 ImageView img = (ImageView)findViewById(R.id.imageView2);
		 img.setBackgroundResource(R.drawable.level_animation);
		 frameAnimation = (AnimationDrawable) img.getBackground();
	}
	
	@Override
	public void onWindowFocusChanged (boolean hasFocus) {
	   super.onWindowFocusChanged(hasFocus);
	   if (hasFocus){
	      frameAnimation.start();
	   }
	}

}
