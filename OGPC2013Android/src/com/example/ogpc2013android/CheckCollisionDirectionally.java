package com.example.ogpc2013android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.RectF;

public class CheckCollisionDirectionally extends ConditionalBlock {
	
	Player p;
	Map m;
	
	public CheckCollisionDirectionally(PointF loc, Context context, Resources res, int id, int conditionalBlockType) {
		super(loc, context, res, R.drawable.front_touching, id);
		this.conditionalBlockType = conditionalBlockType;
		if (conditionalBlockType == ConditionalBlock.CHECK_FOREWARD_BLOCK) {
			setImage(R.drawable.front_touching, res); 
		}
		if (conditionalBlockType == ConditionalBlock.CHECK_BACKWARD_BLOCK) {
			setImage(R.drawable.back_touching, res); 
		}
		if (conditionalBlockType == ConditionalBlock.CHECK_LEFT_BLOCK) {
			setImage(R.drawable.left_touching, res); 
		}
		if (conditionalBlockType == ConditionalBlock.CHECK_RIGHT_BLOCK) {
			setImage(R.drawable.right_touching, res); 
		}
	}

	@Override
	public boolean condition() {
		// TODO Auto-generated method stub
		if (conditionalBlockType == ConditionalBlock.CHECK_FOREWARD_BLOCK) {
			RectF r = new RectF(p.getCollisionRect());
			r = Functions.getMovedRectangle(r, p.getForewardPosition(2));
			return m.isColliding(r);
		}
		if (conditionalBlockType == ConditionalBlock.CHECK_RIGHT_BLOCK) {
			RectF r = new RectF(p.getCollisionRect());
			r = Functions.getMovedRectangle(r, p.getMovedPosition(2, 90));
			return m.isColliding(r);
		}
		return false;
	}

	@Override
	public void init(Context c) {
		// TODO Auto-generated method stub
		p = ((RunActivity)c).getPlayer();
		m = ((RunActivity)c).getMap();
	}

}
