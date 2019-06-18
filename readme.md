# 泡泡堂游戏的设计与实现

## 一、简介
《泡泡堂》是由韩国游戏公司Nexon开发的一款休闲游戏（Casual Game），于2003年在中国大陆上线，由盛大网络运营。游戏讲述了在哈巴森林的一个村落的村民们利用神奇的水泡来打猎和采集宝石，故事由为拯救村民和夺回被海盗抢去的宝石而展开。  
该游戏设有8位基本角色、2位隐藏角色和在基本角色上进阶的新角色。卡通的人物形象、多种道具、饰品和搞怪表情，是一款适合任何年龄的休闲类网游。
本项目通过对该游戏进行分析研究，借助它的游戏规则，使用Java语言模拟开发一个类似的，功能相对简单的泡泡堂小游戏。通过对程序中算法的实现，线程的分配，程序运行中的内存使用情况的控制等技术的综合运用，更深入的了解计算机程序方面的相关知识，熟悉游戏开发的理念和流程，了解整个游戏项目开发的关注点。

## 二、运行环境
1、项目使用Eclipse开发
2、Windows10 家庭版
3、jdk 1.8.0_201

## 三、操作说明
在对战时，玩家一使用WSAD控制上下左右，空格键放炸弹，玩家二使用↑↓←→控制上下左右，Ctrl键放炸弹，人物只能在空地中移动，当遇到可摧毁障碍物时，可以使用对应的放置炸弹键放置炸弹，利用炸弹的爆炸的威力去破坏周围的障碍物。摧毁障碍物有机会刷新出不同功能的道具，同时可以一连串放置多个炸弹，放置炸弹后，炸弹将持续几秒钟才会爆炸，在这段时间玩家可以放置更多的炸弹来攻击对手，在运动过程中还要及时躲开炸弹的爆炸区域。当玩家被炸弹炸中，则该玩家的生命将结束，不能继续进行游戏，另一玩家获胜。

## 四、文件结构  
### 目录结构描述  
│&emsp;&emsp;.classpath  
│&emsp;&emsp;.project  
│&emsp;&emsp;game.txt  
│&emsp;&emsp;ppt.iml  
│&emsp;&emsp;ppt.jar  
│&emsp;&emsp;readme.md  
│  
├─.idea  
│&emsp;&emsp;&emsp;&emsp;&emsp;encodings.xml  
│&emsp;&emsp;&emsp;&emsp;&emsp;misc.xml  
│&emsp;&emsp;&emsp;&emsp;&emsp;modules.xml  
│&emsp;&emsp;&emsp;&emsp;&emsp;workspace.xml  
│  
├─.settings  
│&emsp;&emsp;&emsp;&emsp;&emsp;org.eclipse.jdt.core.prefs  
│  
├─bin  
│&emsp;└─com  
│&emsp;&emsp;&emsp;└─whh  
│&emsp;&emsp;&emsp;&emsp;&emsp;├─frame  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;MyJFrame.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;MyJPanel.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;│  
│&emsp;&emsp;&emsp;&emsp;&emsp;├─main  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;GameStart.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;│  
│&emsp;&emsp;&emsp;&emsp;&emsp;├─model  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;├─load  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;│&emsp;&emsp;&emsp;ElementLoad.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;│  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;├─manager  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;│&emsp;&emsp;&emsp;ElementFactory.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;│&emsp;&emsp;&emsp;ElementManager.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;│&emsp;&emsp;&emsp;MoveType.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;│  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;└─vo  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;&emsp;&emsp;Bubble.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;&emsp;&emsp;Items.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;&emsp;&emsp;MapSquare.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;&emsp;&emsp;Player.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;&emsp;&emsp;PlayerB.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;&emsp;&emsp;SuperElement.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;│  
│&emsp;&emsp;&emsp;&emsp;&emsp;├─pro  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;image.pro  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;itemsImg.pro  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;map.pro  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;mapimg.pro  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;player.pro  
│&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;playerB.pro  
│&emsp;&emsp;&emsp;&emsp;&emsp;│  
│&emsp;&emsp;&emsp;&emsp;&emsp;└─thread  
│&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;GameListener.class  
│&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;GameThread.class  
│  
├─img  
│&emsp;├─bubble  
│&emsp;│&emsp;&emsp;&emsp;bubble1.png  
│&emsp;│&emsp;&emsp;&emsp;pp0.png  
│&emsp;│&emsp;&emsp;&emsp;pp1.png  
│&emsp;│&emsp;&emsp;&emsp;pp2.png  
│&emsp;│&emsp;&emsp;&emsp;pp3.png  
│&emsp;│&emsp;&emsp;&emsp;pp4.png  
│&emsp;│&emsp;&emsp;&emsp;pp5.png  
│&emsp;│&emsp;&emsp;&emsp;pp6.png  
│&emsp;│&emsp;&emsp;&emsp;pp7.png  
│&emsp;│&emsp;&emsp;&emsp;pp8.png  
│&emsp;│  
│&emsp;├─items  
│&emsp;│&emsp;&emsp;&emsp;1.png  
│&emsp;│&emsp;&emsp;&emsp;2.png  
│&emsp;│&emsp;&emsp;&emsp;3.png  
│&emsp;│&emsp;&emsp;&emsp;4.png  
│&emsp;│&emsp;&emsp;&emsp;5.png  
│&emsp;│&emsp;&emsp;&emsp;6.png  
│&emsp;│&emsp;&emsp;&emsp;7.png  
│&emsp;│&emsp;&emsp;&emsp;8.png  
│&emsp;│  
│&emsp;├─map  
│&emsp;│&emsp;&emsp;&emsp;1.png  
│&emsp;│&emsp;&emsp;&emsp;10.png  
│&emsp;│&emsp;&emsp;&emsp;2.png  
│&emsp;│&emsp;&emsp;&emsp;3.png  
│&emsp;│&emsp;&emsp;&emsp;4.png  
│&emsp;│&emsp;&emsp;&emsp;5.png  
│&emsp;│&emsp;&emsp;&emsp;6.png  
│&emsp;│&emsp;&emsp;&emsp;7.png  
│&emsp;│&emsp;&emsp;&emsp;8.png  
│&emsp;│&emsp;&emsp;&emsp;9.png  
│&emsp;│  
│&emsp;└─player  
│&emsp;&emsp;&emsp;&emsp;&emsp;1.png  
│&emsp;&emsp;&emsp;&emsp;&emsp;burst.png  
│&emsp;&emsp;&emsp;&emsp;&emsp;player1.png  
│&emsp;&emsp;&emsp;&emsp;&emsp;player2.png  
│&emsp;  
└─src  
&emsp;&emsp;└─com  
&emsp;&emsp;&emsp;&emsp;└─whh  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;├─frame  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;MyJFrame.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;MyJPanel.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp; 
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;├─main  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;GameStart.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;├─model  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;├─load  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;│&emsp;&emsp;&emsp;ElementLoad.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;│  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;├─manager  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;│&emsp;&emsp;&emsp;ElementFactory.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;│&emsp;&emsp;&emsp;ElementManager.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;│&emsp;&emsp;&emsp;MoveType.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;│  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;└─vo  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;&emsp;&emsp;Bubble.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;&emsp;&emsp;Items.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;&emsp;&emsp;MapSquare.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;&emsp;&emsp;Player.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;&emsp;&emsp;PlayerB.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;&emsp;&emsp;SuperElement.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;├─pro  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;image.pro  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;itemsImg.pro  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;map.pro  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;mapimg.pro  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;player.pro  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│&emsp;&emsp;&emsp;playerB.pro  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;│  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;└─thread  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;GameListener.java  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;GameThread.java  

## 五、代码简介
包名称|	类	|描述
-|-|-
com.whh.frame |	MyJFrame.java	| 显示窗体、绑定监听、启动线程
com.whh.frame |	MyJPanel.java	| 不断读取人物信息
com.whh.main |	GameStart.java |	游戏入口，启动线程
com.whh.model	| ElementLoad.java |	加载资源、读取人物配置（从pro配置文件里）
com.whh.model.managor	| ElementFactory.java	| 集合所需元素
com.whh.model.managor |	ElementManager.java	|  
com.whh.model.managor |	MoveType.java	|
com.whh.model.vo	| Bubble.java |	泡泡的放置和爆炸
com.whh.model.vo  |	Items.java |	重写父类的方法，自动生成方法存根
com.whh.model.vo  |	MapSquare.java |	地图
com.whh.model.vo  |	Player.java	| 人物的移动等
com.whh.model.vo  |	PlayerB.java |	
com.whh.model.vo  | SuperElement.java |	父类
com.whh.thread |	GameListener.java |	键盘监听
com.whh.thread |	GameThread.java |	迭代、控制进程

### 类图
<div align=center><img width="100%" height="100%" src="https://github.com/HyperMn/Crazy-Arcade-BNB-/blob/master/%E8%AE%BE%E8%AE%A1.png"/></div>

## 六、运行截图
<div align=center><img width="600"height="600" src="https://github.com/HyperMn/Crazy-Arcade-BNB-/blob/master/RunningScreenShot.png"/></div>

