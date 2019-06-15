package com.whh.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.whh.model.load.ElementLoad;
import com.whh.model.manager.ElementManager;
import com.whh.model.manager.MoveType;

public class Player extends SuperElement{
	private int speed;//人物速度
	private int bubblenum;//泡泡数量
	private int addbubnum;//已经放的泡泡数量
	private int power;//泡泡威力.
	private int condition;//人物状态1:正常 0：泡泡包围
	private ImageIcon img;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	private int moveX;	
	private int moveY;
	private int moveType=1;
	private int bursttime=0;
	public Player(int x,int y,int w,int z,ImageIcon img){
		super(x,y,w,z);
		this.img=img;//就近原则
		speed=0;
		bubblenum=1;
		addbubnum=0;
		power=1;
		up=false;
		down=false;
		left=false;
		right=false;
		moveX=0;
		moveY=0;
		condition=1;
		list1.add("Stop");
	}
	private List<String> list1=new ArrayList<>();
	public List<String> getList1() {
		return list1;
	}
	public void setList1(List<String> list1) {
		this.list1 = list1;
	}
	public static Player createPlayer(String str){
//		playerA,bubble,300,150,40,40

		String [] arr=str.split(",");
		int x=Integer.parseInt(arr[2]);
		int y=Integer.parseInt(arr[3]);
		int w=Integer.parseInt(arr[4]);
		int h=Integer.parseInt(arr[5]);
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[0]);
		
		return new Player(x,y,w,h,img);
	}

	public void update(){
		if(condition==1) {
			move();
			for(int i=0;i<speed;i++) {
				if(i==1)
					move();
				if(i==3)
					move();
				if(i==5)
					move();
			}
		}
		updateImage();
		pick();
		burst();
		destroy();
	}
	public void showElement(Graphics g) {
		if(condition==1) {
			g.drawImage(img.getImage(),
					getX(), getY(),
					getX()+getW(), getY()+getH(),
					100*moveX+27,100*moveY+43,
					100*moveX+73,100*(moveY+1),
					null);
		}
		else if(condition==0) {
			g.drawImage(img.getImage(),
					getX(), getY(),
					getX()+getW(), getY()+getH(),
					100*moveX+19,23,
					100*moveX+79,100,
					null);
		}
	}
	public void addBubble() {
		List<Integer> bubblelist=ElementManager.getManager().getBubblelist();
		if(addbubnum<bubblenum&&bubblelist.get((getX()+23)/40+(getY()+53)/40*15)!=1) {
			List<SuperElement> list=ElementManager.getManager().getElementList("nbubble");
			list.add(Bubble.createBubble(getX()+23, getY()+53, "img/bubble/bubble1.png", "oneplayer", power));
			addbubnum++;
		}
	}
	public void move(){
		String moveType=list1.get(list1.size()-1);
		if(moveType.contains("Left")){
			setLeft(true);
			setUp(false);
			setRight(false);
			setDown(false);
		}
		if(moveType.contains("Up")){
			setLeft(false);
			setUp(true);
			setRight(false);
			setDown(false);
		}
		if(moveType.contains("Right")){
			setLeft(false);
			setUp(false);
			setRight(true);
			setDown(false);
		}
		if(moveType.contains("Down")){
			setLeft(false);
			setUp(false);
			setRight(false);
			setDown(true);
		}
		if(moveType.contains("Stop")){	
			setLeft(false);
			setUp(false);
			setRight(false);
			setDown(false);
		}
		if(up&&getY()>0){
			switch (checkMap("up")) {
			case 1:
				setY(getY()-7);
				setX(getX()-10);
				break;
			case 2:
				setY(getY()-7);
				setX(getX()+10);
				break;
			case 3:
				setY(getY()-7);
				break;
			}
		}
		if(down&&getY()<660){
			switch (checkMap("down")) {
			case 1:
				setY(getY()+7);
				setX(getX()-10);
				break;
			case 2:
				setY(getY()+7);
				setX(getX()+10);
				break;
			case 3:
				setY(getY()+7);
				break;
			}
		}
		if(left&&getX()>0&&checkMap("left")==1){
			setX(getX()-10);
		}
		if(right&&getX()<960&&checkMap("right")==1){
			setX(getX()+10);
		}
		setLayer((getY()+getH())/40+1);
		return;
	}
	
	public int checkMap(String s) {
		int i = -1;
		int j = -1;
		int r = -1;
		int l = -1;
		int m = -1;
		switch (s) {
		case "up":
			i = ((getY()-20+getH())/40)+1;
			j = ((getY()+getH())/40)+1;
			r = (getX()+getW())/40;
			l = getX()/40;
			m = (getX()+getW()/2)/40;
				if((!ElementManager.getManager().getMap().get("map1"+j).get(m).isAcrossPower()&&ElementManager.getManager().getMap().get("map1"+(j-1)).get(m).isAcrossPower())||(ElementManager.getManager().getMap().get("map1"+i).get(r).isAcrossPower()&&ElementManager.getManager().getMap().get("map1"+i).get(l).isAcrossPower()))
					return 3;
				else if(ElementManager.getManager().getMap().get("map1"+i).get(r).isAcrossPower()&&ElementManager.getManager().getMap().get("map1"+i).get(m).isAcrossPower())
					return 2;
				else if(ElementManager.getManager().getMap().get("map1"+i).get(l).isAcrossPower()&&ElementManager.getManager().getMap().get("map1"+i).get(m).isAcrossPower())
					return 1;
			else
				break;
		case "down":
			i = ((getY()+10+getH())/40)+1;
			j = ((getY()+getH())/40)+1;
			r = (getX()+getW())/40;
			l = getX()/40;
			m = (getX()+getW()/2)/40;
				if((!ElementManager.getManager().getMap().get("map1"+j).get(m).isAcrossPower()&&ElementManager.getManager().getMap().get("map1"+(j+1)).get(m).isAcrossPower())||(ElementManager.getManager().getMap().get("map1"+i).get(r).isAcrossPower()&&ElementManager.getManager().getMap().get("map1"+i).get(l).isAcrossPower()))
					return 3;
				else if(ElementManager.getManager().getMap().get("map1"+i).get(r).isAcrossPower()&&ElementManager.getManager().getMap().get("map1"+i).get(m).isAcrossPower())
					return 2;
				else if(ElementManager.getManager().getMap().get("map1"+i).get(l).isAcrossPower()&&ElementManager.getManager().getMap().get("map1"+i).get(m).isAcrossPower())
					return 1;
			else 
				break;
		case "left":
			i = (getY()+getH())/40+1;
			l = (getX()-1)/40;
			m = (getX()+getW()/2)/40;
			if((!ElementManager.getManager().getMap().get("map1"+i).get(m).isAcrossPower()&&ElementManager.getManager().getMap().get("map1"+i).get(m-1).isAcrossPower())||ElementManager.getManager().getMap().get("map1"+i).get(l).isAcrossPower())
				return 1;
			else 
				break;
		case "right":
			i = (getY()+getH())/40+1;
			r = (getX()+1+getW())/40;
			m = (getX()+getW()/2)/40;
			if((!ElementManager.getManager().getMap().get("map1"+i).get(m).isAcrossPower()&&ElementManager.getManager().getMap().get("map1"+i).get(m+1).isAcrossPower())||ElementManager.getManager().getMap().get("map1"+i).get(r).isAcrossPower())
				return 1;
			else 
				break;
		}
		return 0;
	}
	
	public void updateImage(){

		if(condition==0) {
			moveX=(moveX>=3)?0:moveX+1;
		}
		else if(condition==1){
			if(up==true){
				
				moveY=3;
				moveX=(moveX>=3)?0:moveX+1;
	//			System.out.println(getY()+"j"+getX());
			}else
				
			if(down){
				moveY=0;
				moveX=(moveX>=3)?0:moveX+1;
				if(left){
					moveY=1;
					moveX=(moveX>=3)?0:moveX+1;
				}
				if(right){
					moveY=2;
					moveX=(moveX>=3)?0:moveX+1;
				}
			}else
			if(left){
				moveY=1;
				moveX=(moveX>=3)?0:moveX+1;
			}else
			if(right){
				moveY=2;
				moveX=(moveX>=3)?0:moveX+1;
			}else moveX=0;
		}
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getBubblenum() {
		return bubblenum;
	}
	public void setBubblenum(int bubblenum) {
		this.bubblenum = bubblenum;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public int getMoveX() {
		return moveX;
	}
	public void setMoveX(int moveX) {
		this.moveX = moveX;
	}
	public int getMoveY() {
		return moveY;
	}
	public void setMoveY(int moveY) {
		this.moveY = moveY;
	}
	public int getMoveType() {
		return moveType;
	}
	public void setMoveType(int moveType) {
		this.moveType = moveType;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public int getAddbubnum() {
		return addbubnum;
	}
	public void setAddbubnum(int addbubnum) {
		this.addbubnum = addbubnum;
	}
	public void moveType(){
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public void pick() {
		Map<String,List<SuperElement>> map = ElementManager.getManager().getMap();
		List<SuperElement> list=map.get("items"+getLayer());
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getX()<=(getX()+getW()/2)&&(list.get(i).getX()+list.get(i).getW())>=(getX()+getW()/2)) {
				System.out.println("碰到啦");
				switch (list.get(i).getVariety()) {
				case 1://加泡泡
					setBubblenum(getBubblenum()+1);
					break;
				case 2://加威力
					setPower(getPower()+1);
					break;
				case 3://加速度
					setSpeed(getSpeed()+1);
					break;
				}
				
				list.get(i).setVisible(false);
			}
		}
		
	}
	
	public void burst() {
		if(isVisible()) {
			if(condition==1) {
				bursttime = 0;
				if(ElementManager.getManager().getBubblelist().get((getLayer()-1)*15+(getX()+getW()/2)/40)==-1||ElementManager.getManager().getBubblelist().get((getLayer()-1)*15+(getX()+getW()/2)/40)==2||ElementManager.getManager().getBubblelist().get((getLayer()-1)*15+(getX()+getW()/2)/40)==3) {
					setCondition(0);
					setImg(ElementLoad.getElementLoad().getMap().get("burst"));
				}
			}
			if(condition==0) {
				bursttime++;
				if(bursttime==50||(ElementManager.getManager().getElementList("playerB").size()>0&&(ElementManager.getManager().getElementList("playerB").get(0).getLayer()==getLayer()&&ElementManager.getManager().getElementList("playerB").get(0).getX()==getX()))) {
					setVisible(false);
				}
			}
		}
	}
}
