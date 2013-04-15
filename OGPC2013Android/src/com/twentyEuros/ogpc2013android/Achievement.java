package com.twentyEuros.ogpc2013android;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Achievement {
	
	public String description;
	public String title;
	public boolean achieved;
	public static Resources res;
	public int textId;
	public int titleId;
	public int descriptionId;
	private int times;
	private int curTimes;
	
	public Achievement(int titleId, int descriptionId, int times) {
		achieved = false;
		curTimes = 0;
		this.titleId = titleId;
		this.descriptionId = descriptionId;
		this.times = times;
	}
	
	public static void setResources(Resources r) {
		res = r;
		firstLevel.loadResources();
		secondLevel.loadResources();
		thirdLevel.loadResources();
		twentyBlock.loadResources();
		cheat.loadResources();
		threeUnderPar.loadResources();
		
	}
	public void loadResources() {
	
		this.description = res.getString(descriptionId);
		this.title = res.getString(titleId);
	}
	
	public void achieve() {
		curTimes++;
		if (curTimes >= times) {
			achieved = true;
			Log.e("Achievement", title + ": " + description);
		}
	}
	
	public static Achievement firstLevel = new Achievement(R.string.cheevo1t, R.string.cheevo1d, 1);
	public static Achievement secondLevel = new Achievement(R.string.cheevo2t, R.string.cheevo2d, 1);
	public static Achievement thirdLevel = new Achievement(R.string.cheevo3t, R.string.cheevo3d, 1);
	public static Achievement twentyBlock = new Achievement(R.string.cheevo4t, R.string.cheevo4d, 1);
	public static Achievement cheat = new Achievement(R.string.cheevo5t, R.string.cheevo5d, 1);
	public static Achievement threeUnderPar = new Achievement(R.string.cheevo6t, R.string.cheevo6d, 3);
}
