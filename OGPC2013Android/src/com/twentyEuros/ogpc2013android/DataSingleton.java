package com.twentyEuros.ogpc2013android;

import java.io.File;
import java.util.ArrayList;

public class DataSingleton {

	public static int currentLevel = 0;
	public static ArrayList<Block> blocks;
	public static ArrayList<Map> maps;
	public static boolean hasBlocks = false;
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
		return blocks;
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
		for (int i = 0; i <= level; i++) {
			completedLevels[i] = true;
			maps.get(i).Complete();
		}
		currentLevel = level;
	}
	
	public static ArrayList<Map> getMaps() {
		return maps;
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
