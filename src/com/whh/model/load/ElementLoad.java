package com.whh.model.load;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;

import com.whh.model.load.ElementLoad;

public class ElementLoad {
	private Map<String,ImageIcon> map;
	private Map<String,List<String>> playmap;
	private List<String> gameList;
	private Map<String,List<String>> twoplaymap;
	
	private static ElementLoad load;
	//	pro文件读取对象
	private Properties pro;
	
	private ElementLoad(){
		map=new HashMap<>();
		playmap=new HashMap<>();
		twoplaymap=new HashMap<>();
		pro=new Properties();
	}
	public static synchronized ElementLoad getElementLoad(){
		if(load==null){
			load=new ElementLoad();
		}
		return load;
	}
	
//	读取主角配置
	public void readPlayPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/whh/pro/player.pro");
		try {
			pro.clear();
			pro.load(in);
			List<String> list=new ArrayList<>();
			for(Object o:pro.keySet()){
				String str=pro.getProperty(o.toString());
				list.add(str);
				playmap.put(o.toString(), list);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(playmap);
	}
//	读取二主角配置
	public void readtwoPlayPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/whh/pro/playerB.pro");
		try {
			pro.clear();
			pro.load(in);
			
			for(Object o:pro.keySet()){
				List<String> list=new ArrayList<>();
				String str=pro.getProperty(o.toString());
				list.add(str);
				twoplaymap.put(o.toString(), list);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(twoplaymap);
	}

//	读取图片的
	public void readImgPro(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/whh/pro/mapimg.pro");
		try {
			pro.clear();
			pro.load(in);
			Set<?> set=pro.keySet();
			for(Object o:set){
				String url=pro.getProperty(o.toString());
				map.put(o.toString(), new ImageIcon(url));
			}
			System.out.println(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	读取图片的
	public void readImgPro2(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/whh/pro/image.pro");
		try {
			pro.clear();
			pro.load(in);
//			System.out.println(pro.keys());
//			System.out.println(pro.keySet());
			Set<?> set=pro.keySet();
			for(Object o:set){
//				pro.getProperty((String)o);
				String url=pro.getProperty(o.toString());
//				System.out.println((String)o+":"+o.toString());
//				System.out.println(o+":"+url);
				map.put(o.toString(), new ImageIcon(url));
			}
			System.out.println(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	读取图片的
	public void readImgPro3(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/whh/pro/itemsImg.pro");
		try {
			pro.clear();
			pro.load(in);
//			System.out.println(pro.keys());
//			System.out.println(pro.keySet());
			Set<?> set=pro.keySet();
			for(Object o:set){
//				pro.getProperty((String)o);
				String url=pro.getProperty(o.toString());
//				System.out.println((String)o+":"+o.toString());
//				System.out.println(o+":"+url);
				map.put(o.toString(), new ImageIcon(url));
			}
			System.out.println(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
// 读取地图	
	public void readMapPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/whh/pro/map.pro");
		try {
			pro.clear();
			pro.load(in);
			for(Object o:pro.keySet()){
				List<String> list=new ArrayList<>();
				String str=pro.getProperty(o.toString());
				list.add(str);
				playmap.put(o.toString(), list);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(playmap);
	}

	public Map<String, ImageIcon> getMap() {
		return map;
	}
	public Map<String, List<String>> getPlaymap() {
		return playmap;
	}
	public Map<String, List<String>> getTwoplaymap() {
		return twoplaymap;
	}

}
