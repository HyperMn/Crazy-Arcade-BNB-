package com.whh.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import com.whh.model.manager.ElementManager;
import com.whh.model.vo.SuperElement;

public class MyJPanel extends JPanel implements Runnable{
	public void paint(Graphics g) {
		
		super.paint(g);
//		this.setBackground(Color.black);
//		给一个判定值  也可以使用枚举
//		1.前动画
//		2.gameRuntime
		gameRunTime(g);//Graphics 画笔
//		3.衔接动画
		
	}

	private void gameRunTime(Graphics g) {
//		List<SuperElement> list=
//				ElementManager.getManager().getElementList("XX");
		Map<String,List<SuperElement>> map=
				ElementManager.getManager().getMap();
		Set<String> set=map.keySet();
		int playerlayer=1;
		int player2layer=1;
		if(!map.get("player").isEmpty()) {
			playerlayer = map.get("player").get(0).getLayer();
		}
		if(!map.get("playerB").isEmpty()) {
			player2layer = map.get("playerB").get(0).getLayer();
		}
//		List<String> list=new ArrayList<>();
//		list.addAll(set);
//		Collections.sort(list);//自然顺序
//		Collections.sort(list,"比较器对象");//自动以顺序
//		知识点：java集合对象的排序规则 和2个接口有关
		for(String key:set){
			if(key.indexOf("player")==0||key.indexOf("items")==0) {
				
			}
			else if(key.indexOf("map1")==0) {
				List<SuperElement> list=map.get(key);
				for(int i=0;i<list.size();i++){
					list.get(i).showElement(g);
				}
				
				if(!map.get(key).isEmpty()) {
					List<SuperElement> list1=map.get("items"+map.get(key).get(0).getLayer());
					for(int i=0;i<list1.size();i++){
						list1.get(i).showElement(g);
					}
					
					if(map.get(key).get(0).getLayer()==playerlayer&&map.get(key).get(0).getLayer()==player2layer) {
						if(map.get("player").get(0).getY()>map.get("playerB").get(0).getY()) {
							List<SuperElement> list2=map.get("playerB");
							for(int i=0;i<list2.size();i++){
								list2.get(i).showElement(g);
							}
							list2=map.get("player");
							for(int i=0;i<list2.size();i++){
								list2.get(i).showElement(g);
							}
						}
						else {
							List<SuperElement> list2=map.get("player");
							for(int i=0;i<list2.size();i++){
								list2.get(i).showElement(g);
							}
							list2=map.get("playerB");
							for(int i=0;i<list2.size();i++){
								list2.get(i).showElement(g);
							}
						}
					}
					else {
						if(map.get(key).get(0).getLayer()==playerlayer) {
							List<SuperElement> list2=map.get("player");
							for(int i=0;i<list2.size();i++){
								list2.get(i).showElement(g);
							}
						}
						if(map.get(key).get(0).getLayer()==player2layer) {
							List<SuperElement> list2=map.get("playerB");
							for(int i=0;i<list2.size();i++){
								list2.get(i).showElement(g);
							}
						}
					}
				}
			}
			else {
				List<SuperElement> list=map.get(key);
				for(int i=0;i<list.size();i++){
					list.get(i).showElement(g);
				}
			}
		}
	}
	
	/**
	 * 什么是重写：
	 * 1.是有继承关系的 类与类之间的语法现象(多态的一种实现)
	 * 2.重写的方法必须和 父类的方法的签名一样(返回值，方法名称，参数序列)
	 * 3.重写的方法访问修饰符只可以比父类的更加开放，不可以比父类更加封闭
	 * 4.重写的方法抛出异常不可以比 父类的更加开放
	 */
	@Override
	public void run(){
		while(true){//死循环:界面会不停止的刷新
//			线程的休眠
			try {
				Thread.sleep(100);//毫秒
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.repaint();//要求 面板再次刷新
		}
	}
}
