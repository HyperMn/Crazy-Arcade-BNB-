package com.whh.model.manager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.whh.model.load.ElementLoad;
import com.whh.model.manager.ElementFactory;
import com.whh.model.manager.ElementManager;
import com.whh.model.vo.MapSquare;
import com.whh.model.vo.SuperElement;

public class ElementManager {
//	集合  NPC元素，场景元素，。。。。
	Map<String,List<SuperElement>> map;
	List<Integer> bubblelist;//全地图泡泡list,0表示无泡泡，1是有泡泡，2是泡泡爆炸的，-1是被炸过的
//	private MoveType moveType;
	
	//	初始化
	protected void init(){
		bubblelist=new ArrayList<>();
		for(int i=0;i<195;i++) {
			bubblelist.add(0); 
		}
		map=new LinkedHashMap<>();
		List<SuperElement> list=new ArrayList<>();
		map.put("bgmap1", list);
		List<SuperElement> list1=new ArrayList<>();
		map.put("player", list1);
		List<SuperElement> listA=new ArrayList<>();
		map.put("playerB", listA);
		List<SuperElement> list2=new ArrayList<>();
		map.put("nbubble", list2);
		for(int i=1;i<=13;i++) {
			List<SuperElement> list3=new ArrayList<>();
			map.put("map1"+i, list3);
		}
		for(int i=1;i<=13;i++) {
			List<SuperElement> list4=new ArrayList<>();
			map.put("items"+i, list4);
		}

	}
//	得到一个完整的 map集合
	public Map<String, List<SuperElement>> getMap() {
		return map;
	}
//	得到一个元素的集合
	public List<SuperElement> getElementList(String key){
		return map.get(key);
	}
//	得到全地图的泡泡list
	public List<Integer> getBubblelist() {
		return bubblelist;
	}
	
//	单例：需要一个唯一的引用
	private static ElementManager elementManager;
//	构造方法私有化，就只有在本类中可以 new
	private ElementManager(){
		init();
	}
	static{//静态的语句块 是在类加载的时候就会执行
		if(elementManager ==null){
			elementManager=new ElementManager();
		}
	}
//	提供出来给予外部访问的唯一入口   synchronized 线程保护锁
	public static /*synchronized*/ ElementManager getManager(){
//		if(elementManager ==null){
//			elementManager=new ElementManager();
//		}
		return elementManager;
	}
//	加载需要的资源
	public void load(int g) {
		ElementLoad.getElementLoad().readImgPro();
		ElementLoad.getElementLoad().readMapPro();
		ElementLoad.getElementLoad().readImgPro2();
		ElementLoad.getElementLoad().readPlayPro();
		ElementLoad.getElementLoad().readtwoPlayPro();
		ElementLoad.getElementLoad().readImgPro3();
//		ElementLoad.getElementLoad().readGamepro();
//		开放一个 状态，界面可以做前面的过度信息
//		......
		
		Set<String> set=map.keySet();
		for(String key:set){//迭代器在遍历的过程中，迭代器内的元素不可以 增加或者删除
			map.get(key).clear();
		}
		
		Map<String, List<String>> map1=
				ElementLoad.getElementLoad().getPlaymap();
		List<String> list1=map1.get("firstMapBG");
		String s1=list1.get(list1.size()-1);
		String[] arr1=s1.split(",");
		for(int i=0;i<arr1.length;i++) {
			map.get("bgmap1").add(MapSquare.createMapSquare((i%15)*40, (i/15)*40, arr1[i]));
		}
		
		map.get("player").add(ElementFactory.elementFactory("onePlayer"));
		map.get("playerB").add(ElementFactory.elementFactory("twoPlayer"));
		
		List<String> list2=map1.get("Map"+g);
		String s2=list2.get(list2.size()-1);
		String[] arr2=s2.split(",");
		for(int i=0;i<13;i++) {
			for(int j=0;j<15;j++) {
				int k=i+1;
				map.get("map1"+k).add(MapSquare.createMapSquare(j*40, i*40, arr2[i*15+j]));
			}
		
		}
	}

}
