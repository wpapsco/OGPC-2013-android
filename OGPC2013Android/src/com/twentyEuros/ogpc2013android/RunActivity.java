package com.twentyEuros.ogpc2013android;

//package com.example.ogpc2013android;
//
//import java.util.ArrayList;
//
//import android.app.Activity;
//import android.os.Bundle;
//
//public class RunActivity extends Activity {
//
//	public RunActivity() {
//		// TODO Auto-generated constructor stub
//	}
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_run);
//		ArrayList<Block> blocks = DataSingleton.getBlocks();
//		if (blocks.size() > 0) {
//			Runnable runnable = new RunRunnable(blocks, this);
//			Thread t = new Thread(runnable);
//			t.start();
//		}
//	}
//}


import java.util.ArrayList;

import com.twentyEuros.ogpc2013android.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

public class RunActivity extends Activity {

	private int stateID;

	private ArrayList<Block> blocks;
	private Thread t;
	private Thread ut;
	private RunRunnable runnable;
	private Player player;
	private Bitmap playerImage;
	private Map map;
	private RunView v;
	private MediaPlayer m;
//	private Sound gameMusic;
	private ArrayList<Map> maps;
	private int level;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		m = MediaPlayer.create(this, R.raw.gameplay);
		m.setLooping(true);
		v = new RunView(this);
		setContentView(v);
		playerImage = BitmapFactory.decodeResource(getResources(), R.drawable.player);
//		background = new Image("pics/testMap.png");
//		gameMusic = new Sound("sounds/gameplay.ogg");
		level = getIntent().getIntExtra("level", -1);
		if (level == -1) {
			Log.e("Aaahhhh", "Wrong level!!");
		}
		
		maps = DataSingleton.getMaps();
		
		player = new Player(maps.get(level).getPlayerStartLoc(), playerImage, getResources());
		
	}
	
	public Context getThis() {
		return this;
	}

	private void CheckForAtWall() {
		
	}

	public Map getMap() {
		return maps.get(level);
	}
	
	public void onRunComplete() {
		System.out.println("Complete");
		reset();
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}

	public Player getPlayer() {
		return player;
	}

	public void reset() {
		player.reset(new PointF(maps.get(level).getPlayerStartLoc().x, maps.get(level).getPlayerStartLoc().y));
		maps.get(level).reset();
		if (DataSingleton.hasBlocks) {
			runnable.stop();
		}
		for (int i = 0; i < maps.size(); i++) {
			if (maps.get(i).isCompleted()) {
				DataSingleton.completedLevels[i] = true;
			}
		}
	}
	
	public void invalidateView() {
		v.invalidate();
	}
	
	public void drawItems(Canvas c, Paint p) {
		maps.get(level).draw(c, p);
		player.draw(c, p);
	}

	public static boolean isNull(ArrayList<Block> blocks) {
		return blocks != null;
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			if(!m.isPlaying()) {
				  m.start();
			}
		}
		if (hasFocus && DataSingleton.hasBlocks) {
			ArrayList<Block> blocks = DataSingleton.getBlocks();
			if (blocks.size() > 0) {
				runnable = new RunRunnable(blocks, this, v);
				t = new Thread(runnable);
//				t.start();
			}
			if (player != null) {
				t.start();
			}
			player.setLoc(new PointF(maps.get(level).getPlayerStartLoc().x, maps.get(level).getPlayerStartLoc().y));
		}
		if (!hasFocus) {
			if (m.isPlaying()) {
				m.pause();
			}
			Log.e("leaving", "leaving");
			reset();
		}
	}
}

