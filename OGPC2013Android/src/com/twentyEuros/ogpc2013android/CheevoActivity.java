package com.twentyEuros.ogpc2013android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CheevoActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_cheevos);
	}
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			if (Achievement.firstLevel.achieved) {
				((TextView) findViewById(R.id.textView1)).setText(R.string.cheevo1t);
				((TextView) findViewById(R.id.textView2)).setText(R.string.cheevo1d);
			}
			if (Achievement.secondLevel.achieved) {
				((TextView) findViewById(R.id.textView3)).setText(R.string.cheevo2t);
				((TextView) findViewById(R.id.textView4)).setText(R.string.cheevo2d);
			}
			if (Achievement.thirdLevel.achieved) {
				((TextView) findViewById(R.id.textView5)).setText(R.string.cheevo3t);
				((TextView) findViewById(R.id.textView6)).setText(R.string.cheevo3d);
			}
			if (Achievement.twentyBlock.achieved) {
				((TextView) findViewById(R.id.textView7)).setText(R.string.cheevo4t);
				((TextView) findViewById(R.id.textView8)).setText(R.string.cheevo4d);
			}
			if (Achievement.cheat.achieved) {
				((TextView) findViewById(R.id.textView9)).setText(R.string.cheevo5t);
				((TextView) findViewById(R.id.textView10)).setText(R.string.cheevo5d);
			}
			if (Achievement.threeUnderPar.achieved) {
				((TextView) findViewById(R.id.textView11)).setText(R.string.cheevo6t);
				((TextView) findViewById(R.id.textView12)).setText(R.string.cheevo6d);
			}
		}
	}
}
