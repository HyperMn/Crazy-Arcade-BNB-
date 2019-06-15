package com.whh.frame;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.whh.thread.GameThread;

public class MyJFrame extends JFrame{
//	定义的属性  采用 set注入的方式
	private KeyListener keyListener;
	private MouseListener mouseListener;
	private MouseMotionListener mouseMotionListener;
	private JPanel jp;  //包
	
	
//	构造方法 (一般继承与父类的方法，构造中调用父类的初始化等不写在构造方法)
	public MyJFrame(){
		init();
	}
//	初始化方法:  构造方法无法被继承，而init方法可以(init方法可以被重写)
	public void init(){
		this.setTitle("PaoPaoTang_class03_group11");
		this.setSize(610, 550);//设置大小
		this.setResizable(false);//设置窗体不可以修改大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭状态
		this.setLocationRelativeTo(null);//居中
	}
//	绑定监听
	public void addListener(){
		if(keyListener !=null)//如果键盘监听不为空，就添加键盘监听
			this.addKeyListener(keyListener);
		if(mouseListener !=null)
			this.addMouseListener(mouseListener);
		if(mouseMotionListener!=null)
			this.addMouseMotionListener(mouseMotionListener);
	}
//	画板绑定
	public void addJPanels(){
		if(jp!=null)
			this.add(jp);
//		else
//			throw new RuntimeException("游戏初始加载失败");
	}
	public void start(){
//		线程启动。。。
		GameThread gt=new GameThread();
		gt.start();
//		界面刷新线程启动
		if(jp instanceof Runnable){//jp引用指向的实体对象 是不是 Runnable的子类
			new Thread((Runnable)jp).start();
		}
		
//		this.show();
		this.setVisible(true);//窗体显示
		
	}
	
//	构造注入
	public MyJFrame(KeyListener keyListener){
		this.keyListener=keyListener;
	}
	
//	set注入  
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}
	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}
	public void setJp(JPanel jp) {
		this.jp = jp;
	}
	

}
