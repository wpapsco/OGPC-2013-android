package com.twentyEuros.ogpc2013android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class RunRunnable implements Runnable {
	
	private Block currentBlock;
	private Block startBlock;
	ArrayList<Block> blocks;
	Activity context;
	boolean running = true;
	private View v;
	
	public RunRunnable(ArrayList<Block> blocks, Activity context, View v) {
		this.v = v;
		this.blocks = blocks;
		currentBlock = blocks.get(0);
		startBlock = blocks.get(0);
		this.context = context;
	}
	
	public Block getCurrentBlock() {
		return currentBlock;
	}
	
	public void stop() {
		running = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000); //initial delay
			while (currentBlock.hasNextBlock() && running ) {
				boolean runCurBlock = true;
				if (runCurBlock) {
					currentBlock.init(context);
					currentBlock = currentBlock.incite();
//					((RunActivity) context).invalidateView();
					v.postInvalidate();
				} else {
					currentBlock = ((CommandBlock) currentBlock).getNextBlock();
				}
				Thread.sleep(10);
				((RunActivity) context).getPlayer().update(5, ((RunActivity) context).getMap());
				((RunActivity) context).getMap().update(context);
			}
			if (!currentBlock.hasNextBlock()) {
				currentBlock.init(context);
				currentBlock.incite();
//				((RunActivity) context).invalidateView();
				v.postInvalidate();
			}
			if (!running) {
				currentBlock = startBlock;
			}
			while(((RunActivity) context).getPlayer().hasBullets()) {
				Thread.sleep(10);
				((RunActivity) context).getPlayer().update(5, ((RunActivity) context).getMap());
				((RunActivity) context).getMap().update(context);
				v.postInvalidate();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
