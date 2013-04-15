package com.twentyEuros.ogpc2013android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;

public class CheatActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub.
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
	}
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			((CheckBox) findViewById(R.id.checkBox1)).setChecked(DataSingleton.cheatMode);
		}
		if (!hasFocus) {
			DataSingleton.setCheatMode(((CheckBox) findViewById(R.id.checkBox1)).isChecked());
			if (((CheckBox) findViewById(R.id.checkBox1)).isChecked() && !Achievement.cheat.achieved) {Achievement.cheat.achieve();}
			if (((CheckBox) findViewById(R.id.checkBox2)).isChecked() && !Achievement.cheat.achieved) {Achievement.cheat.achieve();}
		}
	}
}
