package com.example.ogpc2013android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FlowChartActivity extends Activity {

	int level;
	private ArrayList<Block> blocks;
	private ImageView printlnBlockButton;
	private int selectedBlockType;
	private int selectedBlockSubType;
	FlowChartBackgroundView BgView;
	private ImageView selectedBlockView;
	private int selectedBlockId;
	private RelativeLayout layout;
	
	public FlowChartActivity() {

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		blocks = new ArrayList<Block>();
		Intent i = getIntent();
		level = i.getIntExtra("level", -1);
		if (level < 0) {
			Log.e("FlowChartActivity", "Level returning default value, or has been calculated wrong");
		}
		layout = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_flowchart, null);
		this.setContentView(layout);
		BgView = (FlowChartBackgroundView) findViewById(R.id.bg_view);
		BgView.setInvisibitmap(R.drawable.println_block, this.getResources());
		BgView.setDrawTransparentBitmap(true);
		
		ImageView printlnButton = (ImageView) findViewById(R.id.println);
		ImageView deleteAllButton = (ImageView) findViewById(R.id.delete_all);
		ImageView runButton = (ImageView) findViewById(R.id.run);
		((ImageView) findViewById(R.id.selected_image)).setVisibility(View.INVISIBLE);
		selectedBlockView = (ImageView) findViewById(R.id.selected_image);
		
		printlnButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((FlowChartActivity) arg0.getContext()).setSelectedBlockType(Block.COMMAND_BLOCK, CommandBlock.PRINTLN_BLOCK);
			}
		});
		
		runButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Block currentBlock = blocks.get(0);
				while (currentBlock.hasNextBlock()) {
					boolean runCurBlock = true;
					if (runCurBlock) {
//						currentBlock.init(c, s);
//						currentBlock.update(0, c, s);
						currentBlock = currentBlock.incite();
					} else {
						currentBlock = ((CommandBlock) currentBlock).getNextBlock();
					}
				}
				if (!currentBlock.hasNextBlock()) {
//					currentBlock.init(c, s);
					currentBlock.incite();
					System.err.println("Finished run");
				}
				
			}
		});
		
		deleteAllButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((FlowChartActivity) arg0.getContext()).deleteAllBlocks();
			}
		});
		
	}
	
	protected void setBlocks(ArrayList<Block> arrayList) {
		blocks = arrayList;
		BgView.invalidate();
	}

	protected void setSelectedBlockType(int blockType, int secondaryBlockType) {
		// TODO Auto-generated method stub
		BgView.setSelectedBlockType(blockType, secondaryBlockType);
		if (blockType == -1 || secondaryBlockType == -1) {
			selectedBlockView.setVisibility(View.INVISIBLE);
		}
		if (blockType == Block.COMMAND_BLOCK) {
			switch(blockType) {
			case CommandBlock.PRINTLN_BLOCK:
				selectedBlockView.setVisibility(View.VISIBLE);
				selectedBlockView.setBackgroundResource(R.drawable.println_block);
			}
		}
	}
	
	protected void setSelectedBlockID(int id) {
		selectedBlockId = id;
		BgView.setSelectionRect(blocks.get(id).loc);
		Log.e("" + selectedBlockId, "SetSelected");
	}
	
	protected void deleteAllBlocks() {
		for (int i = 0; i < blocks.size(); i++) {
			layout.removeView(blocks.get(i).selectButton);
		}
		blocks = new ArrayList<Block>();
		BgView.invalidate();
	}
	
	protected void addBlock(int resID, float x, float y) {
		blocks.add(new PrintlnBlock(new PointF(x, y), this, this.getResources(), blocks.size()));
		layout.addView(blocks.get(blocks.size() - 1).selectButton);
	}
	
	protected ArrayList<Block> getBlocks() {
		return blocks;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		BgView.setInvisibitmapLoc(new PointF(event.getX(), event.getY()));
		return super.onTouchEvent(event);
	}

	public int getSelectedBlockID() {
		// TODO Auto-generated method stub
		return selectedBlockId;
	}
}
