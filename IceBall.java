import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.imageio.ImageIO;

public class IceBall extends Weapon {
	// Images!!
	static Image iceball1 = null;
	static Image iceball2 = null;
	static Image iceball3 = null;
	static Image iceball4 = null;
	static Image iceball5 = null;
	static Image iceball6 = null;	
	
	static LinkedList<Image> images = null;
	
	public IceBall(Person p) {
		// Call super constructor
		super(p);
		
		// Try to create all the images, and then pass
		// them to the Weapon class as a LinkedList so
		// Weapon class can draw them and everything
		
		try {
			// If none of the static images have been created...
			if (images == null) {
				// ... create them one by one...
				iceball1 = ImageIO.read(new File("iceball1.png"));
				iceball2 = ImageIO.read(new File("iceball2.png"));
				iceball3 = ImageIO.read(new File("iceball3.png"));
				iceball4 = ImageIO.read(new File("iceball4.png"));
				iceball5 = ImageIO.read(new File("iceball5.png"));
				iceball6 = ImageIO.read(new File("iceball6.png"));
			
				// ...and add them to the  linked list so that the 
				// Weapon class can handle drawing them
				images = new LinkedList<Image>();
				
				images.add(iceball1);
				images.add(iceball2);
				images.add(iceball3);
				images.add(iceball4);
				images.add(iceball5);
				images.add(iceball6);
			}
		} catch (Exception e) {}
		
		imageList = images;
	}
	
	// Collide with Monsters
	void collision(LinkedList<Sprite> s) {
		
		// Look through all of the sprites to see if 'this'
		// collides with anything. Also, iterators are faster
		// than for-loops
		ListIterator<Sprite> i = s.listIterator(0);
		while (i.hasNext()) {
			Sprite sprite = i.next();
			
			// If the sprite is a monster...
			if(sprite instanceof Monster){
				Monster m = (Monster) sprite;
				
				// Forgot that width can be negative.... hehe, that'll
				// really throw off our collision detection
				int absWidth = Math.abs(width);		
				int mAbsWidth = Math.abs(m.width);
				
				// ... and if 'this' overlaps the monster (like, completely overlaps) ...
				if(	weapon_x + absWidth	> m.x 				&& 			// (weapon's left edge is to the right of monster's left edge)
					weapon_x 			< m.x + mAbsWidth 	&& 			// (weapon's right edge is to the left of monster's right edge)
					weapon_y + 59 		> m.y 				&& 			// (weapon's bottom edge is below the monster's top edge)
					weapon_y 			< m.y + m.height	) {			// (and weapon's top edge is below the monster's bottom edge)
					
					// ... then as long as it's not dead, let's say it's hit.
					if(!m.dead){
						hit = true;
						m.getFrozen();
						distance = true;
					}
				}
			}
			else if(sprite instanceof Boss){
				Boss b = (Boss) sprite;
				
				// Forgot that width can be negative.... hehe, that'll
				// really throw off our collision detection
				int absWidth = Math.abs(width);		
				int bAbsWidth = Math.abs(b.width);
				
				// ... and if 'this' overlaps the monster (like, completely overlaps) ...
				if(	weapon_x + absWidth	> b.x 				&& 			// (weapon's left edge is to the right of monster's left edge)
					weapon_x 			< b.x + bAbsWidth 	&& 			// (weapon's right edge is to the left of monster's right edge)
					weapon_y + 59 		> b.y 				&& 			// (weapon's bottom edge is below the monster's top edge)
					weapon_y 			< b.y + b.height	) {			// (and weapon's top edge is below the monster's bottom edge)
					
					System.out.println("FireballCollision");
					// ... then as long as it's not dead, let's say it's hit.
					if(!b.dead){
						b.mad = true;
						b.getFrozen();
						distance = true;
					}
				}
			}
		}
	}	
}
