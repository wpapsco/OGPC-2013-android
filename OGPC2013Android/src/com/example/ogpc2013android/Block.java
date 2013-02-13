package com.example.ogpc2013android;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public abstract class Block {

	protected Point loc;
	protected Bitmap image;
	protected boolean isEnd = true;
	protected ImageView selectButton;
	protected int blockType;
	protected int deltaSum;
	protected boolean hasNextBlock;
	protected boolean hasPreviousBlock;
	protected ArrayList<Block> previousBlocks;
	protected ArrayList<Shape> connectingLines;
	protected ArrayList<Shape> arrowLines;
	public static final int COMMAND_BLOCK = 0;
	public static final int CONDITIONAL_BLOCK = 1;
	
	public void setOnClickLisener(OnClickListener l) {
		selectButton.setOnClickListener(l);
	}
	
	public Bitmap getBitmap() {
		return image;
	}
	
	public boolean hasNextBlock() {
		return hasNextBlock;
	}
	
	public void setImage(Bitmap image) {
		this.image = image;
		BitmapDrawable b = new BitmapDrawable(image);
		selectButton.setBackgroundDrawable(b);
	}
	
	public void setImage(int resId, Resources res){
		this.image = BitmapFactory.decodeResource(res, resId);
		selectButton.setBackgroundResource(resId);
	}
	
	public int getBlockType() {
		return blockType;
	}
	
	public void addPreviousBlock(Block b) {
		previousBlocks.add(b);
		hasPreviousBlock = true;
	}
	
	public boolean hasPreviousBlock() {
		return hasPreviousBlock;
	}
	
	public ArrayList<Block> getPreviousBlocks() {
		return previousBlocks;
	}
	
	public void addLine(Line line) {
		connectingLines.add(line);
		PointF startPoint = new PointF(line.getCenterX(), line.getCenterY());
		float angle = Functions.pointsToAngle(line.getX1(), line.getY1(), line.getX2(), line.getY2()) - 270;
		angle = angle - 45;
		if (angle < 0) {
			angle+=360;
		}
		float xval = (float) (.25 * Math.toDegrees(Math.cos(Math.toRadians(angle))) + startPoint.x);
		float yval = (float) (.25 * Math.toDegrees(Math.sin(Math.toRadians(angle))) + startPoint.y);
		arrowLines.add(new Line(startPoint, new PointF(xval, yval)));
		angle = angle + 90;
		if (angle > 360) {
			angle-=360;
		}
		xval = (float) (.25 * Math.toDegrees(Math.cos(Math.toRadians(angle))) + startPoint.x);
		yval = (float) (.25 * Math.toDegrees(Math.sin(Math.toRadians(angle))) + startPoint.y);
		arrowLines.add(new Line(startPoint, new PointF(xval, yval)));
	}
	
	void drawLines(Canvas c) {
		Paint p = new Paint();
		for (int i = 0; i < connectingLines.size(); i++) {
			if (this.blockType == CONDITIONAL_BLOCK && i == 0) {
				p.setARGB(255, 0, 255, 0);
				connectingLines.get(i).draw(c, p);
			} else if (this.blockType == CONDITIONAL_BLOCK && i == 1) {
				p.setARGB(255, 255, 0, 0);
				connectingLines.get(i).draw(c, p);
			} else {
				p.setARGB(255, 0, 0, 0);
				connectingLines.get(i).draw(c, p);
			}
		}
		for (int i = 0; i < arrowLines.size(); i++) {
			if (this.blockType == CONDITIONAL_BLOCK && i == 0 || this.blockType == CONDITIONAL_BLOCK && i == 1) {
				p.setARGB(255, 0, 255, 0);
				connectingLines.get(i).draw(c, p);
			} else if (this.blockType == CONDITIONAL_BLOCK && i == 2 || this.blockType == CONDITIONAL_BLOCK && i == 3) {
				p.setARGB(255, 255, 0, 0);
				connectingLines.get(i).draw(c, p);
			} else {
				p.setARGB(255, 0, 0, 0);
				connectingLines.get(i).draw(c, p);
			}
		}
	}
	
	public void draw(Canvas c) {
		c.drawBitmap(image, loc.x, loc.y, null);
	}
	
//	public void update(int delta, GameContainer c, RunState s) {
//		deltaSum+=delta;
//	}
}
