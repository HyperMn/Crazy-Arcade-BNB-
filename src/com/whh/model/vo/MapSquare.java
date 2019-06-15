package com.whh.model.vo;

import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import com.whh.model.load.ElementLoad;
import com.whh.model.manager.ElementManager;

public class MapSquare extends SuperElement{//一个地图有15*13=195个格子
	private int variety;//种类
	private ImageIcon img;//图片
	private int times = 0;
	
	public MapSquare(int x,int y,int w,int h,int variety,ImageIcon img) {
		super(x,y,w,h);
		this.variety = variety;
		this.img = img;
		setLayer((y+40)/40);
		switch (variety) {
		case 0:
			setAcrossPower(true);
			setBurstPower(2);
			break;
		case 1:
			setAcrossPower(true);
			setBurstPower(0);
			break;
		case 2:
			setAcrossPower(true);
			setBurstPower(0);
			break;
		case 3:
			setAcrossPower(false);
			setBurstPower(1);
			break;
		case 4:
			setAcrossPower(true);
			setBurstPower(0);
			break;
		case 5:
			setAcrossPower(true);
			setBurstPower(1);
			break;
		case 6:
			setAcrossPower(false);
			setBurstPower(1);
			break;
		case 7:
			setAcrossPower(false);
			setBurstPower(1);
			break;
		case 8:
			setAcrossPower(false);
			setBurstPower(1);
			break;
		case 9:
			setAcrossPower(false);
			setBurstPower(0);
			break;
		case 10:
			setAcrossPower(false);
			setBurstPower(0);
			break;
		}
	}
	
	public static MapSquare createMapSquare(int x,int y,String str){
		int variety = Integer.parseInt(str);
		int w;
		int h;
		if(variety==0) {
			w = 0;
			h = 0;
			return new MapSquare(x,y,w,h,variety,ElementLoad.getElementLoad().getMap().get("1"));
		}
		else if(variety==3||variety==6||variety==8){
			w = 40;
			h = 44;
		}
		else if(variety==4||variety==10){
			w = 40;
			h = 57;
		}
		else if(variety==7){
			w = 40;
			h = 41;
		}
		else if(variety==9){
			w = 40;
			h = 67;
		}
		else {
			w = 40;
			h = 40;
		}
		ImageIcon img = ElementLoad.getElementLoad().getMap().get(str);
		return new MapSquare(x,y,w,h,variety,img);
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

	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), 
				getX(), getY()-getH()+40,        //屏幕左上角坐标   getX()+getW()没问题，getY()-?+getH()=getY()+40
				getX()+getW(), getY()+40,        //屏幕右下角坐标
				0, 0,    			 			 //图片左上角坐标
				getW(), getH(),    		 		 //图片右下角坐标
				null);
	}

	public void move() {
		// TODO 自动生成的方法存根
		
	}


	public void destroy() {
		// TODO 自动生成的方法存根
		if(!isVisible()&&times==0) {
			addItems();
			setW(0);
			setH(0);
			setAcrossPower(true);
			setBurstPower(2);
			times = 1;
		}
	}
	
	public void addItems() {
		Random r=new Random();
		int v = r.nextInt(4);
		if(v!=0) {
			Items item = Items.createItems(getX(), getY(),v);
			List<SuperElement> list=ElementManager.getManager().getElementList("items"+item.getLayer());
			list.add(item);
		}
	}

}
