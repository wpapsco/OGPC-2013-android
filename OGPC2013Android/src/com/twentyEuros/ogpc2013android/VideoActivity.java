package com.twentyEuros.ogpc2013android;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_layout);
		VideoView v = (VideoView) findViewById(R.id.video);
		v.setVideoURI(Uri.parse("android.resource://" + getPackageName() +"/"+R.raw.tutorial));
		v.setMediaController(new MediaController(this));
		v.requestFocus();
		v.start();
	}
}
