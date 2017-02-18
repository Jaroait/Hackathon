import java.util.LinkedList;

import java.awt.Graphics;

abstract class Sprite {
	
	//EMPTY ATM, WILL FIX LATER
	
	//Used in algorithm for collision
	int x, y, width, height;
	boolean verticalCollision, horizontalCollision;
	
	Sprite(){
		
	}
	
	abstract void update(LinkedList<Sprite> s);
	abstract void draw(Graphics g);
	abstract void collision(LinkedList<Sprite> s);
	
}