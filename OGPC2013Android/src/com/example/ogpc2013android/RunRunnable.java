package com.example.ogpc2013android;

import java.util.ArrayList;

import android.content.Context;

public class RunRunnable implements Runnable {
	
	private Block currentBlock;
	private Block startBlock;
	ArrayList<Block> blocks;
	Context context;
	boolean running = true;
	
	public RunRunnable(ArrayList<Block> blocks, Context context) {
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
			while (currentBlock.hasNextBlock() && running) {
				boolean runCurBlock = true;
				if (runCurBlock) {
//					currentBlock.init(c, s);
//					currentBlock.update(0, c, s);
					currentBlock = currentBlock.incite();
				} else {
					currentBlock = ((CommandBlock) currentBlock).getNextBlock();
				}
				Thread.sleep(500);
			}
			if (!currentBlock.hasNextBlock()) {
				currentBlock.init(context);
				currentBlock.incite();
			}
			if (!running) {
				currentBlock = startBlock;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
