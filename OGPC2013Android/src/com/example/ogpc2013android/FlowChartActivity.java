package com.example.ogpc2013android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

public class FlowChartActivity extends Activity {

	int level;
	
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
		RelativeLayout layout = new RelativeLayout(this);
		layout.addView(new FlowChartBackgroundView(this));
		this.setContentView(layout);
	}

}
