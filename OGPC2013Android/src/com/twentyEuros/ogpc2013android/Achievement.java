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
	
	public Achievement(int titleId, int descriptionId, int textId) {
		achieved = false;
		this.textId = textId;
		this.titleId = titleId;
		this.descriptionId = descriptionId;
	}
	
	public static void setResources(Resources r) {
		res = r;
		firstLevel.loadResources();
		secondLevel.loadResources();
		thirdLevel.loadResources();
	}
	
	public void loadResources() {
		this.description = res.getString(descriptionId);
		this.title = res.getString(titleId);
	}
	
	public void achieve() {
		achieved = true;
		Log.e("Achievement", title + ": " + description);
	}
	
	public static Achievement firstLevel = new Achievement(R.string.cheevo1t, R.string.cheevo1d, R.id.textView2);
	public static Achievement secondLevel = new Achievement(R.string.cheevo2t, R.string.cheevo2d, R.id.textView4);
	public static Achievement thirdLevel = new Achievement(R.string.cheevo3t, R.string.cheevo3d, R.id.textView6);
}
