package com.twentyEuros.ogpc2013android;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;

public class CheatActivity extends Activity {
	private MediaPlayer m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub.
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		m = MediaPlayer.create(this, R.raw.rave);
		m.setLooping(true);
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
			DataSingleton.setCheatMode2(((CheckBox) findViewById(R.id.checkBox3)).isChecked());
			if (((CheckBox) findViewById(R.id.checkBox2)).isChecked()) {DataSingleton.setRaveMode(true);}
			if (!((CheckBox) findViewById(R.id.checkBox2)).isChecked()) {DataSingleton.setRaveMode(false);}
			if (((CheckBox) findViewById(R.id.checkBox1)).isChecked() && !Achievement.cheat.achieved) {Achievement.cheat.achieve();}
			if (((CheckBox) findViewById(R.id.checkBox2)).isChecked() && !Achievement.cheat.achieved) {Achievement.cheat.achieve();}
			if (DataSingleton.raveMode) {m.start();}
			if (!DataSingleton.raveMode) {m.stop();}
		}
	}
}
