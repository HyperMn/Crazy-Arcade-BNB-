package com.whh.model.vo;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import com.whh.model.load.ElementLoad;
import com.whh.model.manager.ElementManager;

public class Items extends SuperElement{
	
	private int variety;//种类
	private ImageIcon img;//图片
	private int moveX;
	
	public Items(int x,int y,int w,int h,int variety,ImageIcon img) {
		super(x,y,w,h);
		this.variety = variety;
		this.img = img;
		setLayer((y+h)/40);
	}
	
	public static Items createItems(int x,int y,int v){
		ImageIcon img = ElementLoad.getElementLoad().getMap().get("item"+v);
		return new Items(x,y,32,48,v,img);
	}
	
	public int getVariety() {
		return variety;
	}

	public void setVariety(int variety) {
		this.variety = variety;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	@Override
	public void move() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

	public void update(){
		super.update();
		burst();
		updateImage();
	}
	
	public void updateImage() {
		// TODO 自动生成的方法存根
		moveX=(moveX>=3)?0:moveX+1;
	}

	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根 
		g.drawImage(img.getImage(), 
				getX()+4, getY()+20-getH(),        //屏幕左上角坐标
				getX()+getW()+4, getY()+20,        //屏幕右下角坐标
				32*moveX, 0,    			 			 //图片左上角坐标
				32*(moveX+1), 48,    		 		 //图片右下角坐标
				null);
	}
	
	
	public void burst() {
		if(isVisible()) {
				if(ElementManager.getManager().getBubblelist().get((getLayer()-1)*15+(getX()+getW()/2)/40)==2||ElementManager.getManager().getBubblelist().get((getLayer()-1)*15+(getX()+getW()/2)/40)==3) {
					setVisible(false);
				}
		}
	}
}
