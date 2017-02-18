import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.imageio.ImageIO;

public class FireBall extends Weapon {
	// Static images. I know listing them all out like this
	// is gross, but it's better than reloading them each time :^)
	static Image fireball1 = null;
	static Image fireball2 = null;
	static Image fireball3 = null;
	static Image fireball4 = null;
	static Image fireball5 = null;
	static Image fireball6 = null;	
	
	static LinkedList<Image> images = null;
	
	public FireBall(Person p) {
		// Call super constructor
		super(p);
		
		// Try to create all the images, and then pass
		// them to the Weapon class as a LinkedList so
		// Weapon class can draw them and everything
		
		try {
			// If none of the static images have been created...
			if (images == null) {
				// ... create them one by one...
				fireball1 = ImageIO.read(new File("fireball1.png"));
				fireball2 = ImageIO.read(new File("fireball2.png"));
				fireball3 = ImageIO.read(new File("fireball3.png"));
				fireball4 = ImageIO.read(new File("fireball4.png"));
				fireball5 = ImageIO.read(new File("fireball5.png"));
				fireball6 = ImageIO.read(new File("fireball6.png"));
			
				// ...and add them to the  linked list so that the 
				// Weapon class can handle drawing them
				images = new LinkedList<Image>();
				
				images.add(fireball1);
				images.add(fireball2);
				images.add(fireball3);
				images.add(fireball4);
				images.add(fireball5);
				images.add(fireball6);
			}
		} catch (Exception e) {}
		
		// Now the imageList object in Weapon will only ever point to one of four
		// (fire, ice, arrow, or sword) address spots for the whole 
		// runtime!! :D efficient.
		imageList = images;		
	}
	
	// Collide with Monsters
	void collision(LinkedList<Sprite> s) {
		
		// Look through all of the sprites to see if 'this'
		// collides with anything. Also, iterators are faster
		// than for-loops (ALL WELL! ;) )
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
						m.getHurt();
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
					
					//System.out.println("FireballCollision");
					// ... then as long as it's not dead, let's say it's hit.
					if(!b.dead){
						b.mad = true;
						b.getHurt();
						distance = true;
					}
				}
			}
		}
	}
}
