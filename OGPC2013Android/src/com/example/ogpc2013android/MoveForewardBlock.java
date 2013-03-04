package com.example.ogpc2013android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.RectF;

public class MoveForewardBlock extends CommandBlock {

	Player p;
	Map m;
	public static String imageString = "pics/MoveForeward.png";
	
	public MoveForewardBlock(PointF loc, Context c, Resources res, int id) {
		super(loc, c, res, R.drawable.move_foreward, id);
		commandBlockType = CommandBlock.MOVE_FOREWARD_BLOCK;
	}
	
	@Override
	public void command() {
		// TODO Auto-generated method stub
		RectF r = p.getForewardRect(2);
		if (!m.isColliding(r)) {
			p.moveForeward(2);
			System.out.println("Moved");
		}
	}

	@Override
	public void init(Context c) {
		// TODO Auto-generated method stub
		p = ((RunActivity)c).getPlayer();
		m = ((RunActivity)c).getMap();
	}
}
