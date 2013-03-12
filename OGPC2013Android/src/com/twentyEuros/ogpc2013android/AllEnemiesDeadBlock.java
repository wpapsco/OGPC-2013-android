package com.twentyEuros.ogpc2013android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;

public class AllEnemiesDeadBlock extends ConditionalBlock {

	public AllEnemiesDeadBlock(PointF loc, Context context, Resources res, int id) {
		super(loc, context, res, R.drawable.enemies_dead, id);
		// TODO Auto-generated constructor stub
	}

	Map m;
	
	@Override
	public boolean condition() {
		boolean b = true;
		for (int i = 0; i < m.getEnemies().size(); i++) {
			if (!m.getEnemies().get(i).isDead()) {
				b = false;
			}
		}
		return b;
	}

	@Override
	public void init(Context context) {
		// TODO Auto-generated method stub
		m = ((RunActivity)context).getMap();
	}
}
