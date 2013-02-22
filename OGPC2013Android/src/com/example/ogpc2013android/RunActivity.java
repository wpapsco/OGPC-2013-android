package com.example.ogpc2013android;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

public class RunActivity extends Activity {

	public RunActivity() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_run);
		ArrayList<Block> blocks = DataSingleton.getBlocks();
		if (blocks.size() > 0) {
			Runnable runnable = new RunRunnable(blocks, this);
			Thread t = new Thread(runnable);
			t.start();
		}
	}
}
