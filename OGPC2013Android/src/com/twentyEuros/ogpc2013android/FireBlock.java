package com.twentyEuros.ogpc2013android;

import com.twentyEuros.ogpc2013android.R;
import com.twentyEuros.ogpc2013android.R.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;

public class FireBlock extends CommandBlock {

	Player p;
	Context runCo;
	
	public FireBlock(PointF loc, Context context, Resources res, int id) {
		super(loc, context, res, R.drawable.shoot, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void command() {
		// TODO Auto-generated method stub
		p.fire(runCo);
	}

	@Override
	public void init(Context context) {
		// TODO Auto-generated method stub
		p = ((RunActivity)context).getPlayer();
		runCo = context;
	}
	
}