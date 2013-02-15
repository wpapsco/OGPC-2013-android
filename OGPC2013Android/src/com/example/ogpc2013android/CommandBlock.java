package com.example.ogpc2013android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;

public abstract class CommandBlock extends Block {
	
	public CommandBlock(PointF loc, Context context, Resources res, int resId, int id) {
		super(loc, context, res, resId, id);
		// TODO Auto-generated constructor stub
		blockType = COMMAND_BLOCK;
	}

	private Block nextBlock;
	public int commandBlockType;
	public static final int PRINTLN_BLOCK = 0;
	public static final int MOVE_BLOCK = 1;
	public static final int MOVE_UP_BLOCK = 2;
	public static final int MOVE_DOWN_BLOCK = 3;
	public static final int MOVE_LEFT_BLOCK = 4;
	public static final int MOVE_RIGHT_BLOCK = 5;
	public static final int START_BLOCK = 6;
	public static final int ROTATE_90_LEFT_BLOCK = 7;
	public static final int ROTATE_90_RIGHT_BLOCK = 8;
	public static final int MOVE_FOREWARD_BLOCK = 9;
	public static final int FIRE_BLOCK = 10;
	public static final int AIM_AT_NEAREST_ENEMY_BLOCK = 11;
	
	public abstract void command();

	public Block incite() {
		command();
		if (hasNextBlock) {
			return nextBlock;
		}
		return null;
	}
	
	public int getCommandBlockType() {
		return commandBlockType;
	}
	
	public Block getNextBlock() {
		return nextBlock;
	}
	
	public void setNextBlock(Block block) {
		if (!hasNextBlock) {
			nextBlock = block;
			hasNextBlock = true;
			addLine(new Line(loc, block.loc));
		}
	}
}
