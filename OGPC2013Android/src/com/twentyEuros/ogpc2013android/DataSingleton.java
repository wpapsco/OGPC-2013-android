package com.twentyEuros.ogpc2013android;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;

public class DataSingleton {

	public static int currentLevel = 0;
	public static ArrayList<Block> blocks;
	public static ArrayList<Map> maps;
	public static boolean hasBlocks = false;
	public static boolean cheatMode = false;
	public static boolean raveMode = false;
	public static boolean[] completedLevels = {
		false,
		false,
		false,
		false,
		false,
		false,
		false,
		false,
		false,
		false
	};
	
	public DataSingleton() {
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<Block> getBlocks() {
		if (blocks != null) {
			return blocks;
		} else {
			return new ArrayList<Block>();
		}
	}
	
	public static void setRaveMode(boolean mode) {
		if (mode) {
			Player.bulletID = R.drawable.nrg_bullet;
		}
		if (!mode) {
			Player.bulletID = R.drawable.bullet;
		}
		raveMode = mode;
	}
	
	public static int getLevel() {
		int ret = 0;
		for (int i = 0; i < completedLevels.length; i++) {
			if (completedLevels[i]) {
				ret = i;
			}
		}
		return ret;
	}
	
	public static void setLevel(int level) {
		for (int i = 0; i <= level && level != 0; i++) {
			completedLevels[i] = true;
			maps.get(i).Complete();
		}
		currentLevel = level;
	}
	
	public static ArrayList<Map> getMaps() {
		return maps;
	}
	
	public static void setCheatMode(boolean enabled) {
		cheatMode = enabled;
		if (enabled) {
//			completedLevels[0] = true;
//			completedLevels[1] = true;
//			completedLevels[2] = true;
//			completedLevels[3] = true;
			setLevel(2);
		}
	}
	
	public static void setCheatMode2(boolean enabled) {
		cheatMode = enabled;
		if (enabled) {
//			completedLevels[4] = true;
//			completedLevels[5] = true;
//			completedLevels[6] = true;
			setLevel(5);
		}
	}

	public static void setMaps(ArrayList<Map> maps) {
		DataSingleton.maps = maps;
	}

	public static void setBlocks(ArrayList<Block> mblocks) {
		blocks = mblocks;
		if (blocks.size() == 0) {
			hasBlocks = false;
		} else {
			hasBlocks = true;
		}
	}
}
