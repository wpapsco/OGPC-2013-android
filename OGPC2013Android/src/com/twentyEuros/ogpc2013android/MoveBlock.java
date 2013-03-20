package com.twentyEuros.ogpc2013android;

import com.twentyEuros.ogpc2013android.R;
import com.twentyEuros.ogpc2013android.R.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.RectF;

public class MoveBlock extends CommandBlock {
	
	private Player p;
	
	private Map m;
	private int dx;
	private int dy;

	public MoveBlock(PointF loc, Context c, Resources res, int id, int dx, int dy, int moveBlockType) {
		super(loc, c, res, R.drawable.move_up, id);
		// TODO Auto-generated constructor stub	
		this.dx = dx;
		this.dy = dy;
		commandBlockType = moveBlockType;
		if (moveBlockType == CommandBlock.MOVE_UP_BLOCK) {
			this.setImage(R.drawable.move_up, res);
		}
		if (moveBlockType == CommandBlock.MOVE_DOWN_BLOCK) {
			this.setImage(R.drawable.move_down, res);
		}
		if (moveBlockType == CommandBlock.MOVE_LEFT_BLOCK) {
			this.setImage(R.drawable.move_left, res);
		}
		if (moveBlockType == CommandBlock.MOVE_RIGHT_BLOCK) {
			this.setImage(R.drawable.move_right, res);
		}
	}
	
	@Override
	public void command() {
		// TODO Auto-generated method stub
		RectF r = new RectF(p.getCollisionRect());
		float width = r.width();
		float height = r.height();
		r.left = p.getLoc().x + dx;
		r.top = p.getLoc().y + dy;
		r.right = r.left + width;
		r.bottom = r.top + height;
		p.setRect(r);
		if (!m.isColliding(r)) {
			p.setColliding(false);
			p.getLoc().x+=dx;
			p.getLoc().y+=dy;
		} else {
			p.setColliding(true);
		}
	}

	@Override
	public void init(Context c) {
		// TODO Auto-generated method stub
		p = ((RunActivity)c).getPlayer();
		m = ((RunActivity)c).getMap();
	}

}
