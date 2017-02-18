import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Portal extends Sprite{
	//DEFAULT VARIABLES
	//int x, y, width, height;
	static Image portal = null;
	static Image greyPortal = null; 
	boolean able;
	
	//Constructor
	Portal(int x_coordinate, int y_coordinate, boolean ableBack) throws IOException{
		this.x = x_coordinate;
		this.y = y_coordinate;
		able = ableBack;
		width = 64;
		height = 128;
		portal = ImageIO.read(new File("portal.png"));
		greyPortal = ImageIO.read(new File("greyPortal.png"));
	}
	
	void draw(Graphics g){
		if(able)
			g.drawImage(portal, x, y, null);
		else
			g.drawImage(greyPortal, x, y, null);
	}
	
	void collision(LinkedList<Sprite> s){
		
	}
	void update(LinkedList<Sprite> s){
		
	}
}