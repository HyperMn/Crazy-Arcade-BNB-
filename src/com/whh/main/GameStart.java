package com.whh.main;

import com.whh.frame.MyJFrame;
import com.whh.frame.MyJPanel;
import com.whh.thread.GameListener;

public class GameStart {
//	整个游戏的入口，启动
	public static void main(String[] args) {
//		资源加载
//		窗体加载（自动化。。）
		MyJFrame jf=new MyJFrame();
		MyJPanel jp=new MyJPanel();
		GameListener listener=new GameListener();
		jf.setKeyListener(listener);
		jf.setJp(jp);//注入
//		监听加载
		jf.addListener();
		jf.addJPanels();//加载jp
//		游戏启动（开始）
		jf.start();
	}
	
	/**
	 * 1.定义一个 VO类，继承superElement
	 * 2.在工厂中做实例化
	 * 3.配置文件中进行配置
	 * 4.如果需要监听，请在监听中写代码
	 */
}
