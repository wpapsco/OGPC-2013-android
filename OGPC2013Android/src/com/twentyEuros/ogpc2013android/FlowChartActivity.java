package com.twentyEuros.ogpc2013android;

import java.util.ArrayList;

import com.twentyEuros.ogpc2013android.R;
import com.twentyEuros.ogpc2013android.R.drawable;
import com.twentyEuros.ogpc2013android.R.id;
import com.twentyEuros.ogpc2013android.R.layout;
import com.twentyEuros.ogpc2013android.R.raw;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.widget.Button;
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
	private MediaPlayer m;
	
	public FlowChartActivity() {

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		m = MediaPlayer.create(this, R.raw.menu);
		m.setLooping(true);
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
		
		Button shoot = (Button) findViewById(R.id.shoot);
		Button deadEnemies = (Button) findViewById(R.id.enemies_dead);
		Button frontTouching = (Button) findViewById(R.id.front_touching);
		Button moveForeward = (Button) findViewById(R.id.move_foreward);
		Button rotateRightButton = (Button) findViewById(R.id.rotate_right);
		Button rotateLeftButton = (Button) findViewById(R.id.rotate_left);
		Button moveRight = (Button) findViewById(R.id.move_right);
		Button moveLeft = (Button) findViewById(R.id.move_left);
		Button moveUp = (Button) findViewById(R.id.move_up);
		Button moveDown = (Button) findViewById(R.id.move_down);
		ImageView deleteAllButton = (ImageView) findViewById(R.id.delete_all);
		ImageView runButton = (ImageView) findViewById(R.id.run);
		((ImageView) findViewById(R.id.selected_image)).setVisibility(View.INVISIBLE);
		selectedBlockView = (ImageView) findViewById(R.id.selected_image);
		
		shoot.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((FlowChartActivity) arg0.getContext()).setSelectedBlockType(Block.COMMAND_BLOCK, CommandBlock.FIRE_BLOCK);
			}
		});
		
		deadEnemies.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((FlowChartActivity) arg0.getContext()).setSelectedBlockType(Block.CONDITIONAL_BLOCK, ConditionalBlock.ALL_ENEMIES_DEAD);
			}
		});
		
		frontTouching.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((FlowChartActivity) arg0.getContext()).setSelectedBlockType(Block.CONDITIONAL_BLOCK, ConditionalBlock.CHECK_FOREWARD_BLOCK);
			}
		});
		
		moveRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((FlowChartActivity) arg0.getContext()).setSelectedBlockType(Block.COMMAND_BLOCK, CommandBlock.MOVE_RIGHT_BLOCK);
			}
		});
		
		moveLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((FlowChartActivity) arg0.getContext()).setSelectedBlockType(Block.COMMAND_BLOCK, CommandBlock.MOVE_LEFT_BLOCK);
			}
		});
		
		moveUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((FlowChartActivity) arg0.getContext()).setSelectedBlockType(Block.COMMAND_BLOCK, CommandBlock.MOVE_UP_BLOCK);
			}
		});
		
		moveDown.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((FlowChartActivity) arg0.getContext()).setSelectedBlockType(Block.COMMAND_BLOCK, CommandBlock.MOVE_DOWN_BLOCK);
			}
		});
		
		moveForeward.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((FlowChartActivity) arg0.getContext()).setSelectedBlockType(Block.COMMAND_BLOCK, CommandBlock.MOVE_FOREWARD_BLOCK);
			}
		});
		
		rotateRightButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((FlowChartActivity) arg0.getContext()).setSelectedBlockType(Block.COMMAND_BLOCK, CommandBlock.ROTATE_90_RIGHT_BLOCK);
			}
		});
		
		rotateLeftButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((FlowChartActivity) arg0.getContext()).setSelectedBlockType(Block.COMMAND_BLOCK, CommandBlock.ROTATE_90_LEFT_BLOCK);
			}
		});
		
		runButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				toRunState();
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

	public void toRunState() {
		Intent intent = new Intent(this, RunActivity.class);
		DataSingleton.setBlocks(blocks);
		intent.putExtra("level", level);
		startActivity(intent);
	}
	
	protected void setSelectedBlockType(int blockType, int secondaryBlockType) {
		// TODO Auto-generated method stub
		BgView.setSelectedBlockType(blockType, secondaryBlockType);
		if (blockType == -1 || secondaryBlockType == -1) {
			selectedBlockView.setVisibility(View.INVISIBLE);
		}
		if (blockType == Block.COMMAND_BLOCK) {
			switch(secondaryBlockType) {
			case CommandBlock.MOVE_FOREWARD_BLOCK:
				selectedBlockView.setVisibility(View.VISIBLE);
//				selectedBlockView.setBackgroundResource(R.drawable.println_block);
				selectedBlockView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.move_foreward));
				break;
			case CommandBlock.ROTATE_90_RIGHT_BLOCK:
				selectedBlockView.setVisibility(View.VISIBLE);
				selectedBlockView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rotate_90_right));
				break;
			case CommandBlock.ROTATE_90_LEFT_BLOCK:
				selectedBlockView.setVisibility(View.VISIBLE);
				selectedBlockView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rotate_90_left));
				break;
			case CommandBlock.FIRE_BLOCK:
				selectedBlockView.setVisibility(View.VISIBLE);
				selectedBlockView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.shoot));
				break;
			case CommandBlock.MOVE_RIGHT_BLOCK:
				selectedBlockView.setVisibility(View.VISIBLE);
				selectedBlockView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.move_right));
				break;
			case CommandBlock.MOVE_LEFT_BLOCK:
				selectedBlockView.setVisibility(View.VISIBLE);
				selectedBlockView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.move_left));
				break;
			case CommandBlock.MOVE_UP_BLOCK:
				selectedBlockView.setVisibility(View.VISIBLE);
				selectedBlockView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.move_up));
				break;
			case CommandBlock.MOVE_DOWN_BLOCK:
				selectedBlockView.setVisibility(View.VISIBLE);
				selectedBlockView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.move_down));
				break;
			}
		}
		if (blockType == Block.CONDITIONAL_BLOCK) {
			switch(secondaryBlockType) {
			case ConditionalBlock.CHECK_FOREWARD_BLOCK:
				selectedBlockView.setVisibility(View.VISIBLE);
				selectedBlockView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.front_touching));
				break;
			case ConditionalBlock.CHECK_BACKWARD_BLOCK:
				selectedBlockView.setVisibility(View.VISIBLE);
				selectedBlockView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.back_touching));
				break;
			case ConditionalBlock.CHECK_LEFT_BLOCK:
				selectedBlockView.setVisibility(View.VISIBLE);
				selectedBlockView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.left_touching));
				break;
			case ConditionalBlock.CHECK_RIGHT_BLOCK:
				selectedBlockView.setVisibility(View.VISIBLE);
				selectedBlockView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.right_touching));
				break;
			case ConditionalBlock.ALL_ENEMIES_DEAD:
				selectedBlockView.setVisibility(View.VISIBLE);
				selectedBlockView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.enemies_dead));
				break;
			}
		}
	}
	
	protected void setSelectedBlockID(int id) {
		selectedBlockId = id;
		if (id != -1) {
			BgView.setSelectionRect(blocks.get(id).loc);
		} else {
			BgView.setSelectionRect(new PointF(-1, -1));
		}
		Log.e("" + selectedBlockId, "SetSelected");
	}
	
	protected void deleteAllBlocks() {
		for (int i = 0; i < blocks.size(); i++) {
			layout.removeView(blocks.get(i).selectButton);
		}
		blocks = new ArrayList<Block>();
		setSelectedBlockID(-1);
		BgView.invalidate();
	}
	
	protected void addBlock(int selectedBlockType, int selectedBlockSubType, PointF loc) {
//		blocks.add(new PrintlnBlock(new PointF(x, y), this, this.getResources(), blocks.size()));
//		layout.addView(blocks.get(blocks.size() - 1).selectButton);
		switch(selectedBlockType) {
		case Block.COMMAND_BLOCK:
			switch(selectedBlockSubType) {
			case CommandBlock.MOVE_FOREWARD_BLOCK:
				blocks.add(new MoveForewardBlock(loc, this, getResources(), blocks.size()));
				BgView.invalidate();
				break;
			case CommandBlock.ROTATE_90_RIGHT_BLOCK:
				blocks.add(new Rotate90RightBlock(loc, this, getResources(), blocks.size()));
				BgView.invalidate();
				break;
			case CommandBlock.ROTATE_90_LEFT_BLOCK:
				blocks.add(new Rotate90LeftBlock(loc, this, getResources(), blocks.size()));
				BgView.invalidate();
				break;
			case CommandBlock.FIRE_BLOCK:
				blocks.add(new FireBlock(loc, this, getResources(), blocks.size()));
				BgView.invalidate();
				break;
			case CommandBlock.MOVE_RIGHT_BLOCK:
				blocks.add(new MoveBlock(loc, this, getResources(), blocks.size(), 2, 0, CommandBlock.MOVE_RIGHT_BLOCK));
				BgView.invalidate();
				break;
			case CommandBlock.MOVE_LEFT_BLOCK:
				blocks.add(new MoveBlock(loc, this, getResources(), blocks.size(), -2, 0, CommandBlock.MOVE_LEFT_BLOCK));
				BgView.invalidate();
				break;
			case CommandBlock.MOVE_UP_BLOCK:
				blocks.add(new MoveBlock(loc, this, getResources(), blocks.size(), 0, -2, CommandBlock.MOVE_UP_BLOCK));
				BgView.invalidate();
				break;
			case CommandBlock.MOVE_DOWN_BLOCK:
				blocks.add(new MoveBlock(loc, this, getResources(), blocks.size(), 0, 2, CommandBlock.MOVE_DOWN_BLOCK));
				BgView.invalidate();
				break;
			}
			break;
		case Block.CONDITIONAL_BLOCK:
			switch(selectedBlockSubType) {
			case ConditionalBlock.CHECK_FOREWARD_BLOCK:
				blocks.add(new CheckCollisionDirectionally(loc, this, getResources(), blocks.size(), ConditionalBlock.CHECK_FOREWARD_BLOCK));
				BgView.invalidate();
				break;
			case ConditionalBlock.CHECK_BACKWARD_BLOCK:
				blocks.add(new CheckCollisionDirectionally(loc, this, getResources(), blocks.size(), ConditionalBlock.CHECK_BACKWARD_BLOCK));
				BgView.invalidate();
				break;
			case ConditionalBlock.CHECK_LEFT_BLOCK:
				blocks.add(new CheckCollisionDirectionally(loc, this, getResources(), blocks.size(), ConditionalBlock.CHECK_LEFT_BLOCK));
				BgView.invalidate();
				break;
			case ConditionalBlock.CHECK_RIGHT_BLOCK:
				blocks.add(new CheckCollisionDirectionally(loc, this, getResources(), blocks.size(), ConditionalBlock.CHECK_RIGHT_BLOCK));
				BgView.invalidate();
				break;
			case ConditionalBlock.ALL_ENEMIES_DEAD:
				blocks.add(new AllEnemiesDeadBlock(loc, this, getResources(), blocks.size()));
				BgView.invalidate();
				break;
			}
			break;
		}
		if (selectedBlockType != -1 && selectedBlockSubType != -1) {
			layout.addView(blocks.get(blocks.size() - 1).selectButton);
		}
	}
	
	protected ArrayList<Block> getBlocks() {
		return blocks;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			if(!m.isPlaying()) {
				  m.start();
			  }
		} else {
			if (m.isPlaying()) {
				m.pause();
			}
		}
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