import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

class Map{
	//Camera Purposes
	boolean moveLeft, moveRight, moveDown;
	
	//Number of Platforms (Linked List purpose)???

	//Platforms For MAP1
	Platform base;
	Platform base2;
	Platform base3;
	Platform base4;
	Platform base5;
	Platform base6;
	Platform base7;
	Platform base8;
	Platform base9;
	

	
	//Platforms For MAP 2
	Platform map2Base;
	Platform map2Base2;
	Platform map2Base3;
	Platform map2Base4;
	Platform map2Base5;
	Platform map2Base6;
	Platform map2Base7;
	
	//Platforms For MAP 3
	Platform map3Base;
	Platform map3Base2;
	Platform map3Base3;
	Platform map3Base4;
	Platform map3Base5;
	Platform map3Base6;
	Platform map3Base7;
	Platform map3Base8;
	Platform map3Base9;
	Platform map3Base10;
	Platform map3Base11;
	
	
	//Platforms For MAP 4
	Platform map4Base;
	Platform map4Base2;
	Platform map4Base3;
	Platform map4Base4;
	
	//Portals FOR MAP1
	Portal temp;
	
	//Portals FOR MAP2
	Portal temp2;
	Portal temp3;
	
	//Portals FOR MAP3
	Portal temp4;
	Portal temp5;
	
	//Portals FOR MAP4
	Portal temp6;
	
	//Monsters for MAP1
	Monster map1mon1;
	Monster map1mon2;
	Monster map1mon3;
	Monster map1mon4;
	
	//Monsters for MAP2
	Monster map2mon1;
	Monster map2mon2;
	Monster map2mon3;
	Monster map2mon4;
	Monster map2mon5;
	Monster map2mon6;
	
	//Monsters for MAP3
	Monster map3mon1;
	Monster map3mon2;
	Monster map3mon3;
	Monster map3mon4;
	Monster map3mon5;
	Monster map3mon6;
	Monster map3mon7;
	
	
	//Boss for MAP4
	Boss boss;
	
	Background background1;
	Background background2;
	Background background3;
	Background background4;
	
	
	//Constructor
	Map() throws IOException, FontFormatException{
		//Make all platforms/portals Map1
		base = new Platform(0,800,1, false);
		base2 = new Platform(1500,700,4, false);
		base3 = new Platform(500,600,2, false);
		base4 = new Platform(250, 500, 3, false);
		base5 = new Platform(0, 400, 3, false);
		base6 = new Platform(325, 300, 3, false);
		base7 = new Platform(1150, 350, 2, false);
		base8 = new Platform(650, 200, 3, false);
		map1mon1 = generateRandomMonster(1000,736,0,1920);
		map1mon2 = generateRandomMonster(1800,636,1500,1920);
		map1mon3 = generateRandomMonster(800,536,500,1500);
		map1mon4 = generateRandomMonster(1200,536,500,1500);
		//base7 = new Platform(1150, 350, 2, false);
		//base7 = new Platform(1400, 400, 3, false);
		temp = new Portal(1750, 222, false);
		background1 = new Background(1);
		
		//Make all platforms/portals/monsters Map2
		map2Base = new Platform(0, 800, 1, false);
		map2Base2 = new Platform(400, 700, 4, false);
		map2Base3 = new Platform(980, 600, 2, false);
		map2Base4 = new Platform(840, 500, 3, false);
		map2Base5 = new Platform(-100, 400, 2, false);
		map2Base6 = new Platform(200, 300, 3, false);
		map2Base7 = new Platform(400, 200, 2, false);
		map2mon1 = generateRandomMonster(1500,536,980,1920);
		map2mon2 = generateRandomMonster(400,336,0,900);
		map2mon3 = generateRandomMonster(1000,736,0,1920);
		map2mon4 = generateRandomMonster(600,636,400,1168);
		map2mon5 = generateRandomMonster(1100,536,980,1920);
		map2mon6 = generateRandomMonster(200,336,0,900);
		temp2 = new Portal(100, 672, true);
		temp3 = new Portal(1200, 72, false);
		background2 = new Background(2);
		
		//Make all platforms/portals Map3
		map3Base = new Platform(0, 800, 1, false);
		map3Base2 = new Platform(200, 700, 4, false);
		map3Base3 = new Platform(1000, 600, 3, false);
		map3Base4 = new Platform(1300, 600, 3, false);
		map3Base5 = new Platform(1600, 500, 4, false);
		map3Base6 = new Platform(700, 400, 4, false);
		map3Base7 = new Platform(300, 400, 3, false);
		map3Base8 = new Platform(0, 300, 3, false);
		map3Base9 = new Platform(400, 200, 3, false);
		map3Base10 = new Platform(800, 200, 4, false);
		map3Base11 = new Platform(1750, 200, 3, false);
		map3mon1 = generateRandomMonster(1000,736,0,1920);
		map3mon2 = generateRandomMonster(600,736,0,1920);
		map3mon3 = generateRandomMonster(500,636,200,968);
		map3mon4 = generateRandomMonster(1700,436,1600,1920);
		map3mon5 = generateRandomMonster(800,336,700,1468);
		map3mon6 = generateRandomMonster(1200,336,700,1468);
		map3mon7 = generateRandomMonster(1100,136,800,1568);
		temp4 = new Portal(100, 672, true);
		temp5 = new Portal(1800, 72, false);
		background3 = new Background(3);
		
		//Make all platforms/portals Map4(boss)
		map4Base = new Platform(0, 800, 5, false);
		map4Base2 = new Platform(200, 600, 7, false);
		map4Base3 = new Platform(1600, 600, 7, false);
		map4Base4 = new Platform(500, 400, 6, false);
		boss = new Boss(1000, 600);
		temp6 = new Portal(100, 672, false);
		background4 = new Background(4);
		
		//Instantiate however many platforms im wanting by "Platform 1 = new Platform(xcoord,ycoord,imageNumber)"
	}
	
	void draw(Graphics g, LinkedList<Sprite> s){
		
		//At some point, Link through abstract linked list.
		for(int i = 0; i < s.size(); i++){
			Sprite sprite = s.get(i);
			if(sprite instanceof Background){
				if(sprite != null){
					sprite.draw(g);
				}
			}
			else if(sprite instanceof Platform || sprite instanceof Portal || sprite instanceof Monster){
				if(sprite != null)
					sprite.draw(g);
			}
			else if(sprite instanceof Boss){
				if(sprite != null)
					sprite.draw(g);
			}
			 
		}
	}
	
	void update(LinkedList<Sprite> s, Person p, boolean isPaused){
		// Don't call monster.update() if paused (monsters don't move, hurt anybody,
		// or do anything else until game resumes)
		for(int j = 0; j < s.size() && !isPaused; j++){
			Sprite sprite = s.get(j);
			if(sprite instanceof Monster){
				Monster m = (Monster) sprite;
				m.update(s, p);
			}
		}		
	}
	
	Monster generateRandomMonster(int x, int y, int LBound, int RBound) {
		int randomNum = (int)(Math.random() * 3.0);	// in [0,2]
		
		try {
			if 		(randomNum == 0) return new MonsterFox(x,y,LBound,RBound);
			else if (randomNum == 1) return new MonsterPenguin(x,y,LBound,RBound);
			else 					 return new MonsterGnu(x,y,LBound,RBound);
		} catch (Exception e) { return null; }
	}
	
	//In case of Linked List Abstract
	void collision(){
		//EMPTY ATM
	}

}
