package com.example.ogpc2013android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;


public abstract class ConditionalBlock extends Block {

	Block trueBlock;
	Block falseBlock;
	public boolean trueBlockSet = false;
	public boolean falseBlockSet = false;
	protected int conditionalBlockType;
	public static final int BOOLEAN_BLOCK = 0;
	public static final int AT_WALL_BLOCK = 1;
	public static final int CHECK_FOREWARD_BLOCK = 2;
	public static final int CHECK_BACKWARD_BLOCK = 3;
	public static final int CHECK_LEFT_BLOCK = 4;
	public static final int CHECK_RIGHT_BLOCK = 5;
	public static final int ALL_ENEMIES_DEAD = 6;
	
	public ConditionalBlock(PointF loc, Context context, Resources res, int resId, int id) {
		super(loc, context, res, resId, id);
		// TODO Auto-generated constructor stub
	}
	
	public void setTrueBlock(Block block) {
		trueBlock = block;
		trueBlockSet = true;
		if (trueBlockSet && falseBlockSet) {
			hasNextBlock = true;
		}
		addLine(new Line(getCenterLoc(), block.getCenterLoc()));
	}
	
	public void setFalseBlock(Block block) {
		falseBlock = block;
		falseBlockSet = true;
		if (trueBlockSet && falseBlockSet) {
			hasNextBlock = true;
		}
		addLine(new Line(getCenterLoc(), block.getCenterLoc()));
	}
	
	public int getConditionalBlockType() {
		return conditionalBlockType;
	}
	
	public abstract boolean condition();
	
	@Override
	public Block incite() {
		if (condition()) {
			return trueBlock;
		} else {
			return falseBlock;
		}
	}

}
