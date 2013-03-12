package com.twentyEuros.ogpc2013android;

import java.util.ArrayList;

import com.twentyEuros.ogpc2013android.R;
import com.twentyEuros.ogpc2013android.R.drawable;
import com.twentyEuros.ogpc2013android.R.layout;
import com.twentyEuros.ogpc2013android.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayList<Map> maps = new ArrayList<Map>();
		
		//tutorial levels
		Map map = new Map(new PointF(375, 350));
		map.addEnemy(new Enemy(getResources(), R.drawable.virus, new PointF(375, 200)));
		map.addEvent(new EnemiesKilledEvent(false));
		map.setObjectiveText("Medicate the enemy!");
		maps.add(map);
		
		map = new Map(new PointF(300, 350));
		map.addEnemy(new Enemy(getResources(), R.drawable.virus, new PointF(450, 200)));
		map.addEvent(new EnemiesKilledEvent(false));
		map.setObjectiveText("Can you get this one?");
		maps.add(map);
		
		map = new Map(new PointF(400, 500));
		map.addEvent(new LocationalEvent(new RectF(0, 0, 100, 100), false));
		map.setObjectiveText("How about getting around this corner?");
		map.addObstacle(new Obstacle(new RectF(0, -10, 800, 10)));
		map.addObstacle(new Obstacle(new RectF(-10, 0, 10, 600)));
		map.addObstacle(new Obstacle(new RectF(0, 600, 800, 10)));
		map.addObstacle(new Obstacle(new RectF(800, 0, 10, 600)));
		map.addObstacle(new Obstacle(new RectF(0, 100, 350, 500 + 100)));
		map.addObstacle(new Obstacle(new RectF(450, 0, 350 + 450, 600)));
		maps.add(map);
		
		map = new Map(new PointF(400, 500));
		map.addEvent(new EnemiesKilledEvent(false));
		map.addEnemy(new Enemy(getResources(), R.drawable.virus, new PointF(25, 25)));
		map.setObjectiveText("Now put it all together!");
		map.addObstacle(new Obstacle(new RectF(0, -10, 800, 10)));
		map.addObstacle(new Obstacle(new RectF(-10, 0, 10, 600)));
		map.addObstacle(new Obstacle(new RectF(0, 600, 800, 10)));
		map.addObstacle(new Obstacle(new RectF(800, 0, 10, 600)));
		map.addObstacle(new Obstacle(new RectF(0, 100, 350, 500)));
		map.addObstacle(new Obstacle(new RectF(450, 0, 350, 600)));
		maps.add(map);
		
		//spine levels
		map = new Map(new PointF(45, 45), R.drawable.level_spine1, getResources());
		map.addEvent(new EnemiesKilledEvent(false));
		map.addEvent(new LocationalEvent(new RectF(420, 500, 360, 80), false));
		map.addEnemy(new Enemy(getResources(), R.drawable.virus, new PointF(650, 45)));
		map.setObjectiveText("Spine - Level one");
		map.addObstacle(new Obstacle(new RectF(0, 0, 800, 20)));
		map.addObstacle(new Obstacle(new RectF(0, 0, 20, 600)));
		map.addObstacle(new Obstacle(new RectF(0, 580, 800, 20)));
		map.addObstacle(new Obstacle(new RectF(780, 0, 20, 600)));
		map.addObstacle(new Obstacle(new RectF(170, 20, 20, 350)));
		map.addObstacle(new Obstacle(new RectF(400, 130, 20, 450)));
		map.addObstacle(new Obstacle(new RectF(610, 80, 20, 90)));
		map.addObstacle(new Obstacle(new RectF(630, 150, 150, 20)));
		maps.add(map);
		
		//Alan, please add spine level 2
		//sure thing, boss! Spine level 2:
		map = new Map(new PointF(105, 530), R.drawable.level_spine2, getResources());
		map.addEvent(new LocationalEvent(new RectF(640, 160, 40, 140), false));
		map.setObjectiveText("Spine - Level two");
		map.addObstacle(new Obstacle(new RectF(0, 0, 800, 20)));
		map.addObstacle(new Obstacle(new RectF(0, 0, 20, 600)));
		map.addObstacle(new Obstacle(new RectF(0, 580, 800, 20)));
		map.addObstacle(new Obstacle(new RectF(780, 0, 20, 600)));
		map.addObstacle(new Obstacle(new RectF(640, 140, 60, 20)));
		map.addObstacle(new Obstacle(new RectF(680, 160, 20, 160)));
		map.addObstacle(new Obstacle(new RectF(180, 300, 520, 20)));
		map.addObstacle(new Obstacle(new RectF(180, 320, 20, 260)));
		maps.add(map);
		
		map = new Map(new PointF(50, 145), R.drawable.level_spine3, getResources());
		map.addEvent(new EnemiesKilledEvent(false));
		map.addEvent(new LocationalEvent(new RectF(620, 150, 160, 100), false));
		map.addEnemy(new Enemy(getResources(), R.drawable.virus, new PointF(400, 290)));
		map.setObjectiveText("Spine - Level three");
		map.addObstacle(new Obstacle(new RectF(0, 0, 800, 20)));
		map.addObstacle(new Obstacle(new RectF(0, 0, 20, 600)));
		map.addObstacle(new Obstacle(new RectF(0, 580, 800, 20)));
		map.addObstacle(new Obstacle(new RectF(780, 0, 20, 600)));
		map.addObstacle(new Obstacle(new RectF(200, 280, 20, 300)));
		map.addObstacle(new Obstacle(new RectF(220, 280, 120, 20)));
		map.addObstacle(new Obstacle(new RectF(340, 210, 20, 160)));
		map.addObstacle(new Obstacle(new RectF(340, 210, 180, 20)));
		map.addObstacle(new Obstacle(new RectF(340, 350, 180, 20)));
		map.addObstacle(new Obstacle(new RectF(600, 130, 20, 330)));
		map.addObstacle(new Obstacle(new RectF(600, 130, 180, 20)));
		maps.add(map);
		
		//brain levels
		map = new Map(new PointF(300, 145), R.drawable.level_brain1, getResources());
		map.addEvent(new EnemiesKilledEvent(false));
		map.addEvent(new LocationalEvent(new RectF(310, 290, 293, 100), false));
		map.addEnemy(new Enemy(getResources(), R.drawable.virus, new PointF(475, 315)));
		map.setObjectiveText("Brain - Level one");
		map.addObstacle(new Obstacle(new RectF(0, 0, 800, 20)));
		map.addObstacle(new Obstacle(new RectF(0, 0, 20, 600)));
		map.addObstacle(new Obstacle(new RectF(0, 580, 800, 20)));
		map.addObstacle(new Obstacle(new RectF(780, 0, 20, 600)));
		map.addObstacle(new Obstacle(new RectF(600, 290, 20, 130)));
		map.addObstacle(new Obstacle(new RectF(470, 140, 20, 130)));
		map.addObstacle(new Obstacle(new RectF(290, 290, 20, 130)));
		map.addObstacle(new Obstacle(new RectF(110, 270, 510, 20)));
		map.addObstacle(new Obstacle(new RectF(110, 20, 270, 20)));
		maps.add(map);
		
		DataSingleton.setMaps(maps);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void onImageButtonClicked(View view) {
		Intent intent = new Intent(this, LevelSelectActivity.class);
		startActivity(intent);
	}

}
