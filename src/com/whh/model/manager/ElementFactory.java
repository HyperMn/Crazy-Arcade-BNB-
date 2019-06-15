package com.whh.model.manager;

import java.util.List;
import java.util.Map;

import com.whh.model.load.ElementLoad;
import com.whh.model.vo.Player;
import com.whh.model.vo.PlayerB;
import com.whh.model.vo.SuperElement;

public class ElementFactory {
	public static SuperElement elementFactory(String name){
		Map<String, List<String>> map=
			ElementLoad.getElementLoad().getPlaymap();
		Map<String, List<String>> map1=
				ElementLoad.getElementLoad().getTwoplaymap();
		switch(name){
		case "onePlayer":
			List<String> list=map.get(name);
			String s=list.get(0);//player,0,0,40,40
			return Player.createPlayer(s);
		case "twoPlayer":
			List<String> list1=map1.get(name);
			String s1=list1.get(0);//player,0,0,40,40
			return PlayerB.createPlayerB(s1);
		}
		return null;
	}
}
