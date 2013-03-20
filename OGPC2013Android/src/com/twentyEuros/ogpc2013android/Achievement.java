package com.twentyEuros.ogpc2013android;

import android.util.Log;

public class Achievement {
	
	public String description;
	public String title;
	public boolean achieved;
	
	public Achievement(String title, String description) {
		this.description = description;
		this.title = title;
		achieved = false;
	}
	
	public void achieve() {
		achieved = true;
		Log.e("Achievement", title + ": " + description);
	}
	
	public static Achievement firstLevel = new Achievement("From Humble Beginnings", "Complete the first level");
	public static Achievement secondLevel = new Achievement("Studying Hard", "Complete the second level");
	public static Achievement thirdLevel = new Achievement("Getting Bored", "Complete the third level");
}
