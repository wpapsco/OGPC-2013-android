package com.example.ogpc2013android;

import java.util.ArrayList;

public class DataSingleton {

	public static int currentLevel = 0;
	public static ArrayList<Block> blocks;
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
	
	public static void setBlocks(ArrayList<Block> mblocks) {
		blocks = mblocks;
	}
}
