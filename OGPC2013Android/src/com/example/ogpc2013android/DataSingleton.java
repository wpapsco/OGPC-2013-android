package com.example.ogpc2013android;

import java.util.ArrayList;

public class DataSingleton {

	public static int currentLevel = 0;
	public static ArrayList<Block> blocks;
	public static ArrayList<Map> maps;
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
	
	public static ArrayList<Map> getMaps() {
		return maps;
	}

	public static void setMaps(ArrayList<Map> maps) {
		DataSingleton.maps = maps;
	}

	public static void setBlocks(ArrayList<Block> mblocks) {
		blocks = mblocks;
	}
}
