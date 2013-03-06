package com.example.ogpc2013android;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;

public class Map {
	private Bitmap image;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Event> events;
	private ArrayList<Enemy> enemies;
	private ArrayList<Enemy> currentEnemies;
	private PointF playerStartLoc;
	private String objectiveText;
	private boolean isCompleted;
	private boolean hasImage;
	private boolean completeRan = false;
	
	Map(PointF playerStartLoc) {
		obstacles = new ArrayList<Obstacle>();
		events = new ArrayList<Event>();
		enemies = new ArrayList<Enemy>();
		currentEnemies = new ArrayList<Enemy>();
		playerStartLoc.x = playerStartLoc.x - 25;
		playerStartLoc.y = playerStartLoc.y - 25;
		this.playerStartLoc = playerStartLoc;
		isCompleted = false;
		objectiveText = "";
		hasImage = false;
	}
	
	Map(PointF playerStartLoc, int resId, Resources r) {
		obstacles = new ArrayList<Obstacle>();
		events = new ArrayList<Event>();
		enemies = new ArrayList<Enemy>();
		currentEnemies = new ArrayList<Enemy>();
		this.playerStartLoc = playerStartLoc;
		isCompleted = false;
		objectiveText = "";
		this.image = BitmapFactory.decodeResource(r, resId);
		hasImage = true;
	}
	
	public void setObjectiveText(String objectiveText) {
		this.objectiveText = objectiveText;
	}
	
	public void onDone() {
		objectiveText = objectiveText + " Done!~";
	}
	
	public boolean isColliding(RectF r) {
		boolean retVal = false;
		for (int i = 0; i < obstacles.size(); i++) {
			if (obstacles.get(i).collides(r)) {
				retVal = true;
			}
		}
		return retVal;
	}
	
	public PointF getPlayerStartLoc() {
		return playerStartLoc;
	}
	
	public void checkEnemyCollisions(ArrayList<Bullet> bullets) {
		for (int i = 0; i < currentEnemies.size(); i++) {
			for (int j = 0; j < bullets.size(); j++){
				if (RectF.intersects(currentEnemies.get(i).getCollisionRect(), bullets.get(j).getCollisionRect())) {
					currentEnemies.get(i).takeDamage(100);
					bullets.get(i).setExplosionLocation(bullets.get(i).getPath().currentPoint);
					bullets.get(j).markDeleted();
				}
				if (bullets.get(j).getExplosionCircle().collide(currentEnemies.get(i).getCollisionRect()) && bullets.get(j).isMarkedForDeletion() && !bullets.get(j).isFinishedExploding()) {
					currentEnemies.get(i).takeDamage(100);
				}
			}
		}
		for (int i = 0; i < currentEnemies.size(); i+=0) {
			boolean b = false;
			if (currentEnemies.get(i).isMarkedForDeletion()) {
				currentEnemies.remove(i) ;
				b = true;
			}
			if (!b) {
				i++;
			}
		}
	}
	
	public void update(Context c) {
		boolean doComplete = true;
		for (int i = 0; i < events.size(); i++) {
			events.get(i).update(c);
			if (!events.get(i).isExecuted()) {
				doComplete = false;
			}
		}
		if (doComplete && !completeRan) {
			Complete();
			completeRan  = true;
		}
		for (int i = 0; i < currentEnemies.size(); i++) {
			currentEnemies.get(i).update(c);
		}
		checkEnemyCollisions(((RunActivity) c).getPlayer().getBullets());
	}
	
	public void addObstacle(Obstacle obstacle) {
		obstacles.add(obstacle);
	}
	
	public void addEvent(Event event) {
		events.add(event);
	}
	
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
		currentEnemies.add(enemy);
	}
	
	public ArrayList<Enemy> getEnemies() {
		return currentEnemies;
	}
	
	public ArrayList<Enemy> getBaseEnemies() {
		return enemies;
	}
	
	public void reset() {
		currentEnemies.clear();
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).isDead(false);
			currentEnemies.add(enemies.get(i));
		}
		for (int i = 0; i < events.size(); i++) {
			events.get(i).reset();
		}
	}
	
	public void draw(Canvas c, Paint p) {
		p.setColor(Color.GRAY);
		c.drawRGB(0, 0, 0);
		if (hasImage) {
//			c.drawBitmap(image, new Rect(0, 0, image.getWidth(), image.getHeight()), new Rect(0, 0, c.getWidth(), c.getHeight()), null);
			c.drawBitmap(image, 0, 0, null);
		}else {
			drawObstacles(c, p);
		}
		for (int i = 0; i < currentEnemies.size(); i++) {
			currentEnemies.get(i).draw(c, p);
		}
		for (int i = 0; i < events.size(); i++) {
			events.get(i).draw(c, p);
		}
		p.setColor(Color.GREEN);
		p.setTextSize(20);
		c.drawText(objectiveText, 0, 20, p);
	}
	
	public void drawObstacles(Canvas c, Paint p) {
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).draw(c, p);
		}
	}

	public void Complete() {
		if (!isCompleted) {objectiveText = objectiveText + " - Done!";}
		isCompleted = true;
		Log.e("map", "done");
		DataSingleton.currentLevel++;
		for (int i = 0; i < DataSingleton.currentLevel; i++) {
			DataSingleton.completedLevels[i] = true;
		}
	}

	public boolean isCompleted() {
		// TODO Auto-generated method stub
		return isCompleted;
	}
}
