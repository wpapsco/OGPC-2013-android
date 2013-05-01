package com.twentyEuros.ogpc2013android;

import java.util.ArrayList;


import android.app.ActionBar.LayoutParams;
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
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public abstract class Block {

	protected PointF loc;
	protected Bitmap image;
	protected Context context;
	protected boolean isEnd = true;
	protected ImageView selectButton;
	protected int blockType;
	protected int deltaSum;
	protected boolean hasNextBlock;
	protected boolean hasPreviousBlock;
	protected ArrayList<Block> previousBlocks;
	protected ArrayList<Shape> connectingLines;
	protected ArrayList<Shape> arrowLines;
	protected int ID;
	public static final int COMMAND_BLOCK = 0;
	public static final int CONDITIONAL_BLOCK = 1;
	
	public Block(PointF loc, Context context, Resources res, int resId, int id) {
		selectButton = new ImageView(context);
		setImage(resId, res);
		this.loc = new PointF(loc.x - Math.round((image.getWidth() / 2f)), loc.y - Math.round((image.getHeight() / 2f)));
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); //The WRAP_CONTENT parameters can be replaced by an absolute width and height or the FILL_PARENT option)
		params.leftMargin = Math.round(this.loc.x); //Your X coordinate
		params.topMargin = Math.round(this.loc.y); //Your Y coordinate
		selectButton.setLayoutParams(params);
		deltaSum = 0;
		hasNextBlock = false;
		connectingLines = new ArrayList<Shape>();
		hasPreviousBlock = false;
		arrowLines = new ArrayList<Shape>();
		previousBlocks = new ArrayList<Block>();
		this.ID = id;
		setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (((FlowChartActivity) arg0.getContext()).getSelectedBlockID() != -1 && ((FlowChartActivity) arg0.getContext()).getSelectedBlockID() != ID && ((FlowChartActivity) arg0.getContext()).getBlocks().get(((FlowChartActivity) arg0.getContext()).getSelectedBlockID()).loc != getLoc()) {
					Log.e(((FlowChartActivity) arg0.getContext()).getSelectedBlockID() + "", ID + "");
					previousBlocks.add(((FlowChartActivity) arg0.getContext()).getBlocks().get(((FlowChartActivity) arg0.getContext()).getSelectedBlockID()));
					if (previousBlocks.size() > 0) {
						if (((FlowChartActivity) arg0.getContext()).getBlocks().get(((FlowChartActivity) arg0.getContext()).getSelectedBlockID()).blockType == Block.COMMAND_BLOCK) {
							((CommandBlock) previousBlocks.get(previousBlocks.size() - 1)).setNextBlock(getThis());
						}
						if (((FlowChartActivity) arg0.getContext()).getBlocks().get(((FlowChartActivity) arg0.getContext()).getSelectedBlockID()).blockType == Block.CONDITIONAL_BLOCK) {
							boolean b = false;
							if (!((ConditionalBlock) previousBlocks.get(previousBlocks.size() - 1)).trueBlockSet) {
								((ConditionalBlock) previousBlocks.get(previousBlocks.size() - 1)).setTrueBlock(getThis());
								b = true;
							}
							if (((ConditionalBlock) previousBlocks.get(previousBlocks.size() - 1)).trueBlockSet && !b && !((ConditionalBlock) previousBlocks.get(previousBlocks.size() - 1)).falseBlockSet) {
								((ConditionalBlock) previousBlocks.get(previousBlocks.size() - 1)).setFalseBlock(getThis());
							}
						}
					}
				}
				((FlowChartActivity) arg0.getContext()).BgView.invalidate();
				((FlowChartActivity) arg0.getContext()).setSelectedBlockID(ID);
			}
		});
	}
	
	public int getID() {
		return ID;
	}

	public PointF getCenterLoc() {
		return new PointF(loc.x + (image.getWidth() / 2), loc.y + (image.getHeight() / 2));
	}
	
	public void setOnClickListener(OnClickListener onCLickListener) {
		selectButton.setOnClickListener(onCLickListener);
	}
	
	public Bitmap getBitmap() {
		return image;
	}
	
	public boolean hasNextBlock() {
		return hasNextBlock;
	}
	
	public Block getThis() {
		return this;
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
	
	public PointF getLoc() {
		return loc;
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
	
	public void drawLines(Canvas c) {
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
				arrowLines.get(i).draw(c, p);
			} else if (this.blockType == CONDITIONAL_BLOCK && i == 2 || this.blockType == CONDITIONAL_BLOCK && i == 3) {
				p.setARGB(255, 255, 0, 0);
				arrowLines.get(i).draw(c, p);
			} else {
				p.setARGB(255, 0, 0, 0);
				arrowLines.get(i).draw(c, p);
			}
		}
	}
	
	public void draw(Canvas c) {
		c.drawBitmap(image, loc.x, loc.y, null);
	}

	public abstract Block incite();
	
	public abstract void init(Context context);
	
	public void prepareForDelete() {
		//remove connecting lines
		//set boolean variables for previous block and next block
		connectingLines.clear();
		for (int i = 0; i < previousBlocks.size(); i++) {
			previousBlocks.get(i).hasNextBlock = false;
			if (previousBlocks.get(i) instanceof ConditionalBlock) {
				((ConditionalBlock) previousBlocks.get(i)).falseBlockSet = false;
				((ConditionalBlock) previousBlocks.get(i)).falseBlock = null;
				((ConditionalBlock) previousBlocks.get(i)).trueBlockSet = false;
				((ConditionalBlock) previousBlocks.get(i)).trueBlock = null;
			}
			previousBlocks.get(i).arrowLines.clear();
			previousBlocks.get(i).connectingLines.clear();
		}
		if (this instanceof CommandBlock) {
			if (((CommandBlock) this).hasNextBlock) {
				((CommandBlock) this).getNextBlock().getPreviousBlocks().remove(this);
				if (((CommandBlock) this).getNextBlock().getPreviousBlocks().size() == 0) {
					((CommandBlock) this).getNextBlock().hasPreviousBlock = false;
				}
			}
		} else if (this instanceof ConditionalBlock) {
			if (((ConditionalBlock) this).falseBlockSet) {
				((ConditionalBlock) this).falseBlock.getPreviousBlocks().remove(this);
				if (((ConditionalBlock) this).falseBlock.getPreviousBlocks().size() == 0) {
					((ConditionalBlock) this).falseBlock.hasPreviousBlock = false;
				}
			}
			if (((ConditionalBlock) this).falseBlockSet) {
				((ConditionalBlock) this).trueBlock.getPreviousBlocks().remove(this);
				if (((ConditionalBlock) this).trueBlock.getPreviousBlocks().size() == 0) {
					((ConditionalBlock) this).trueBlock.hasPreviousBlock = false;
				}
			}
		}
	}

	public void setID(int id) {
		ID = id;
	}
}
