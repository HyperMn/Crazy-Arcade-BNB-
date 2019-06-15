package com.whh.thread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import com.whh.model.manager.ElementManager;
import com.whh.model.vo.Player;
import com.whh.model.vo.PlayerB;

public class GameListener implements KeyListener{
	private List<?> list;
	private List<?> listB;
	private List<String> list1=new ArrayList<>();
	private List<String> list2=new ArrayList<>();
	@Override
	public void keyTyped(KeyEvent e) {

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("keyPressed"+e.getKeyCode());
		if(e.getKeyCode()==32||e.getKeyCode()==37||e.getKeyCode()==38||e.getKeyCode()==39||e.getKeyCode()==40){
			list=ElementManager.getManager().getElementList("player");
	//		listA=ElementManager.getManager().getElementList("playerB");
			Player oneplayer=(Player)list.get(0);
			list1=oneplayer.getList1();
			switch(e.getKeyCode()){
			case 32:oneplayer.addBubble();break;
			case 37:if(!list1.contains("Left"))list1.add("Left");break;
			case 38:if(!list1.contains("Up"))list1.add("Up");break;
			case 39:if(!list1.contains("Right"))list1.add("Right");break;
			case 40:if(!list1.contains("Down"))list1.add("Down");break;
			}
			oneplayer.setList1(list1);			
		}
		if(e.getKeyCode()==65||e.getKeyCode()==87||e.getKeyCode()==68||e.getKeyCode()==83||e.getKeyCode()==17){
			listB=ElementManager.getManager().getElementList("playerB");
			PlayerB twoplayer=(PlayerB)listB.get(0);
			list2=twoplayer.getList2();
			switch(e.getKeyCode()){
			case 17:twoplayer.addBubble();break;
			case 65:if(!list2.contains("Left"))list2.add("Left");break;
			case 87:if(!list2.contains("Up"))list2.add("Up");break;
			case 68:if(!list2.contains("Right"))list2.add("Right");break;
			case 83:if(!list2.contains("Down"))list2.add("Down");break;
			}
			twoplayer.setList2(list2);						
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("keyReleased"+e.getKeyCode());
		if(e.getKeyCode()==37||e.getKeyCode()==38||e.getKeyCode()==39||e.getKeyCode()==40){
			list=ElementManager.getManager().getElementList("player");
			Player oneplayer=(Player)list.get(0);
			list1=oneplayer.getList1();
			switch(e.getKeyCode()){
			case 37:if(list1.contains("Left")){
				list1.remove("Left");System.out.println("remove");break;
			}
			case 38:list1.remove("Up");break;
			case 39:list1.remove("Right");break;
			case 40:list1.remove("Down");break;
			}
			if(list1.contains("Left")==false&&list1.contains("Up")==false&&list1.contains("Right")==false&&list1.contains("Down")==false&&list1.contains("Stop")==false){
				list1.add("Stop");
			}
			oneplayer.setList1(list1);				
		}
		if(e.getKeyCode()==65||e.getKeyCode()==87||e.getKeyCode()==68||e.getKeyCode()==83){
			listB=ElementManager.getManager().getElementList("playerB");
			PlayerB twoplayer=(PlayerB)listB.get(0);
			list2=twoplayer.getList2();
			switch(e.getKeyCode()){
			case 65:if(list2.contains("Left")){
				list2.remove("Left");System.out.println("remove");break;
			}
			case 87:list2.remove("Up");break;
			case 68:list2.remove("Right");break;
			case 83:list2.remove("Down");break;
			}
			if(list2.contains("Left")==false&&list2.contains("Up")==false&&list2.contains("Right")==false&&list2.contains("Down")==false&&list2.contains("Stop")==false){
				list2.add("Stop");
			}
			twoplayer.setList2(list2);	
		}
	}

}
