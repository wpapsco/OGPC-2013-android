package com.example.ogpc2013android;

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

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
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
//	private Sound gameMusic;
	private ArrayList<Map> maps;
	private int level;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new RunView(this));
		playerImage = BitmapFactory.decodeResource(getResources(), R.drawable.player);
//		background = new Image("pics/testMap.png");
//		gameMusic = new Sound("sounds/gameplay.ogg");
		level = getIntent().getIntExtra("level", -1);
		if (level == -1) {
			Log.e("Aaahhhh", "Wrong level!!");
		}
		
		maps = DataSingleton.getMaps();
		
//		//tutorial levels
//		map = new Map(new PointF(400, 350));
//		map.addEnemy(new Enemy(getResources(), R.drawable.virus, new PointF(400, 200)));
//		map.addEvent(new EnemiesKilledEvent(false));
//		map.setObjectiveText("Medicate the enemy!");
//		maps.add(map);
//		
//		map = new Map(new PointF(300, 350));
//		map.addEnemy(new Enemy(getResources(), R.drawable.virus, new PointF(450, 200)));
//		map.addEvent(new EnemiesKilledEvent(false));
//		map.setObjectiveText("Can you get this one?");
//		maps.add(map);
//		
//		map = new Map(new PointF(400, 575));
//		map.addEvent(new LocationalEvent(new PointF(25, 25), false));
//		map.setObjectiveText("How about getting around this corner?");
//		map.addObstacle(new Obstacle(new RectF(0, 100, 350, 500)));
//		map.addObstacle(new Obstacle(new RectF(450, 0, 350, 600)));
//		maps.add(map);
//		
//		map = new Map(new PointF(400, 575));
//		map.addEvent(new EnemiesKilledEvent(false));
//		map.addEnemy(new Enemy(getResources(), R.drawable.virus, new PointF(25, 25)));
//		map.setObjectiveText("Now put it all together!");
//		map.addObstacle(new Obstacle(new RectF(0, 100, 350, 500)));
//		map.addObstacle(new Obstacle(new RectF(450, 0, 350, 600)));
//		maps.add(map);
//		
//		//spine levels
//		map = new Map(new PointF(45, 45), R.drawable.level_spine1, getResources());
//		map.addEvent(new EnemiesKilledEvent(false));
//		map.addEvent(new LocationalEvent(new RectF(420, 500, 360, 80), false));
//		map.addEnemy(new Enemy(getResources(), R.drawable.virus, new PointF(650, 45)));
//		map.setObjectiveText("Spine - Level one");
//		map.addObstacle(new Obstacle(new RectF(0, 0, 800, 20)));
//		map.addObstacle(new Obstacle(new RectF(0, 0, 20, 600)));
//		map.addObstacle(new Obstacle(new RectF(0, 580, 800, 20)));
//		map.addObstacle(new Obstacle(new RectF(780, 0, 20, 600)));
//		map.addObstacle(new Obstacle(new RectF(170, 20, 20, 350)));
//		map.addObstacle(new Obstacle(new RectF(400, 130, 20, 450)));
//		map.addObstacle(new Obstacle(new RectF(610, 80, 20, 90)));
//		map.addObstacle(new Obstacle(new RectF(630, 150, 150, 20)));
//		maps.add(map);
//		
//		//Alan, please add spine level 2
//		//sure thing, boss! Spine level 2:
//		map = new Map(new PointF(105, 530), R.drawable.level_spine2, getResources());
//		map.addEvent(new LocationalEvent(new RectF(640, 160, 40, 140), false));
//		map.setObjectiveText("Spine - Level two");
//		map.addObstacle(new Obstacle(new RectF(0, 0, 800, 20)));
//		map.addObstacle(new Obstacle(new RectF(0, 0, 20, 600)));
//		map.addObstacle(new Obstacle(new RectF(0, 580, 800, 20)));
//		map.addObstacle(new Obstacle(new RectF(780, 0, 20, 600)));
//		map.addObstacle(new Obstacle(new RectF(640, 140, 60, 20)));
//		map.addObstacle(new Obstacle(new RectF(680, 160, 20, 160)));
//		map.addObstacle(new Obstacle(new RectF(180, 300, 520, 20)));
//		map.addObstacle(new Obstacle(new RectF(180, 320, 20, 260)));
//		maps.add(map);
//		
//		map = new Map(new PointF(50, 145), R.drawable.level_spine3, getResources());
//		map.addEvent(new EnemiesKilledEvent(false));
//		map.addEvent(new LocationalEvent(new RectF(620, 150, 160, 100), false));
//		map.addEnemy(new Enemy(getResources(), R.drawable.virus, new PointF(400, 290)));
//		map.setObjectiveText("Spine - Level three");
//		map.addObstacle(new Obstacle(new RectF(0, 0, 800, 20)));
//		map.addObstacle(new Obstacle(new RectF(0, 0, 20, 600)));
//		map.addObstacle(new Obstacle(new RectF(0, 580, 800, 20)));
//		map.addObstacle(new Obstacle(new RectF(780, 0, 20, 600)));
//		map.addObstacle(new Obstacle(new RectF(200, 280, 20, 300)));
//		map.addObstacle(new Obstacle(new RectF(220, 280, 120, 20)));
//		map.addObstacle(new Obstacle(new RectF(340, 210, 20, 160)));
//		map.addObstacle(new Obstacle(new RectF(340, 210, 180, 20)));
//		map.addObstacle(new Obstacle(new RectF(340, 350, 180, 20)));
//		map.addObstacle(new Obstacle(new RectF(600, 130, 20, 330)));
//		map.addObstacle(new Obstacle(new RectF(600, 130, 180, 20)));
//		maps.add(map);
//		
//		//brain levels
//		map = new Map(new PointF(300, 145), R.drawable.level_brain1, getResources());
//		map.addEvent(new EnemiesKilledEvent(false));
//		map.addEvent(new LocationalEvent(new RectF(310, 290, 293, 100), false));
//		map.addEnemy(new Enemy(getResources(), R.drawable.virus, new PointF(475, 315)));
//		map.setObjectiveText("Brain - Level one");
//		map.addObstacle(new Obstacle(new RectF(0, 0, 800, 20)));
//		map.addObstacle(new Obstacle(new RectF(0, 0, 20, 600)));
//		map.addObstacle(new Obstacle(new RectF(0, 580, 800, 20)));
//		map.addObstacle(new Obstacle(new RectF(780, 0, 20, 600)));
//		map.addObstacle(new Obstacle(new RectF(600, 290, 20, 130)));
//		map.addObstacle(new Obstacle(new RectF(470, 140, 20, 130)));
//		map.addObstacle(new Obstacle(new RectF(290, 290, 20, 130)));
//		map.addObstacle(new Obstacle(new RectF(110, 270, 510, 20)));
//		map.addObstacle(new Obstacle(new RectF(110, 20, 270, 20)));
//		maps.add(map);
		
		player = new Player(maps.get(level).getPlayerStartLoc(), playerImage);
		
		ut = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					player.update(2, getMap());
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				}
			}
		});
		ut.run();
		
	}
	
	public Context getThis() {
		return this;
	}
	
//	@Override
//	public void render(GameContainer container, StateBasedGame game, Graphics g) {
//		// TODO Auto-generated method stub
//		//background.draw();
//		g.setColor(Color.blue);
//		maps.get(OGPC.level).draw(g);
////		g.setColor(Color.blue);
////		maps.get(OGPC.level).drawObstacles(g);
//		player.draw(g);
//	}

//	public void update(Context c) {
//		// TODO Auto-generated method stub
//
//		Input in = container.getInput();
//		maps.get(OGPC.level).update(container, this);
//		maps.get(OGPC.level).checkEnemyCollisions(player.getBullets());
//		
//		if(OGPC.IsMuted) {
//			gameMusic.stop();
//		}
//		
//		if(!OGPC.IsMuted && !gameMusic.playing()) {
//			gameMusic.play();
//		}
//		
//		player.update(delta, maps.get(OGPC.level));
//		
//		CheckForAtWall();
//		
//		runnable.getCurrentBlock().update(delta, container, this);
//	}

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
		runnable.stop();
//		ut.interrupt();
		for (int i = 0; i < maps.size(); i++) {
			if (maps.get(i).isCompleted()) {
				DataSingleton.completedLevels[i] = true;
			}
		}
	}
	
	public void drawItems(Canvas c, Paint p) {
		maps.get(level).draw(c, p);
		player.draw(c, p);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			ArrayList<Block> blocks = DataSingleton.getBlocks();
			if (blocks.size() > 0) {
				Runnable runnable = new RunRunnable(blocks, this);
				t = new Thread(runnable);
//				t.start();
			}
			if (player != null) {
				t.start();
			}
			player.setLoc(new PointF(maps.get(level).getPlayerStartLoc().x, maps.get(level).getPlayerStartLoc().y));
		}
		if (!hasFocus) {
			Log.e("leaving", "leaving");
		}
	}
}

