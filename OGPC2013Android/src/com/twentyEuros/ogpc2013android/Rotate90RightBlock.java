package com.twentyEuros.ogpc2013android;

import com.twentyEuros.ogpc2013android.R;
import com.twentyEuros.ogpc2013android.R.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;

public class Rotate90RightBlock extends CommandBlock {

	private Player p;
	
	public Rotate90RightBlock(PointF loc, Context c, Resources res, int id) {
		super(loc, c, res, R.drawable.rotate_90_right, id);
		// TODO Auto-generated constructor stub
		commandBlockType = CommandBlock.ROTATE_90_RIGHT_BLOCK;
	}
	
	@Override
	public void command() {
		p.rotate(90);
	}

	@Override
	public void init(Context c) {
		// TODO Auto-generated method stub
		p = ((RunActivity)c).getPlayer();
	}
}
