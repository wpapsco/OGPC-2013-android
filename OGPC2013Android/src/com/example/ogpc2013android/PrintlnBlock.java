package com.example.ogpc2013android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.util.Log;

public class PrintlnBlock extends CommandBlock {

	public PrintlnBlock(PointF loc, Context context, Resources res, int id) {
		super(loc, context, res, R.drawable.println_block, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void command() {
		// TODO Auto-generated method stub
		System.out.println(ID);
	}

}
