package com.whh.model.vo;

import java.awt.Graphics;

public abstract class SuperElement {
	private int x;
	private int y;
	private int h;
	private int w;
	private int layer;
	private int variety;
	
	
	private boolean acrossPower;//是否能穿过
	private int burstPower;//是否能炸掉,0为不能,1为能且会阻挡泡泡，2为能但不能阻挡泡泡
	private boolean visible;
	
	protected SuperElement(){}
	
	public SuperElement(int x,int y,int w,int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.layer=y/40;
		visible=true;
		acrossPower=true;
		burstPower=2;
		variety=-1;
	}
	
	public void update(){
		move();
		destroy();
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isAcrossPower() {
		return acrossPower;
	}
	public void setAcrossPower(boolean acrossPower) {
		this.acrossPower = acrossPower;
	}

	public int getBurstPower() {
		return burstPower;
	}

	public void setBurstPower(int burstPower) {
		this.burstPower = burstPower;
	}
	
	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	
	
	public int getVariety() {
		return variety;
	}

	public void setVariety(int variety) {
		this.variety = variety;
	}

	public abstract void move();
	public abstract void destroy();
	public abstract void showElement(Graphics g);
	
	
	
}
