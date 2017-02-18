import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.imageio.ImageIO;

public class Sword extends Weapon {
	// Images!!
	static Image sword1 = null;
	static Image sword2 = null;
	static Image sword3 = null;
	static Image sword4 = null;
	static Image sword5 = null;
	static Image sword6 = null;

	static LinkedList<Image> images = null;

	// Initialize variables
	public Sword(Person p) {
		// Call super constructor
		super(p);

		// Try to create all the images, and then pass
		// them to the Weapon class as a LinkedList so
		// Weapon class can draw them and everything

		try {
			// If none of the static images have been created...
			if (images == null) {
				// ... create them one by one...
				sword1 = ImageIO.read(new File("sword1.png"));
				sword2 = ImageIO.read(new File("sword2.png"));
				sword3 = ImageIO.read(new File("sword3.png"));
				sword4 = ImageIO.read(new File("sword4.png"));
				sword5 = ImageIO.read(new File("sword5.png"));
				sword6 = ImageIO.read(new File("sword6.png"));

				// ...and add them to the linked list so that the
				// Weapon class can handle drawing them
				images = new LinkedList<Image>();

				images.add(sword1);
				images.add(sword2);
				images.add(sword3);
				images.add(sword4);
				images.add(sword5);
				images.add(sword6);
			}
		} catch (Exception e) {
		}

		imageList = images;

		// Set range (sword range is smaller than other weapons' ranges)
		range = 30;
		height = 21;
		width = 75;
		
		if (!shotToTheLeft) weapon_x -= 60;			// make it look like bear is holding it
		else weapon_x += 60;
		
		weapon_y += 30;			// make it look like bear ISN'T shooting it from his forehead
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
			if (sprite instanceof Monster) {
				Monster m = (Monster) sprite;

				// Forgot that width can be negative.... hehe, that'll
				// really throw off our collision detection
				int absWidth = Math.abs(width);
				int mAbsWidth = Math.abs(m.width);

				// ... and if 'this' overlaps the monster (like, completely
				// overlaps) ...
				if (weapon_x + absWidth > m.x && // (weapon's left edge is to
													// the right of monster's
													// left edge)
						weapon_x < m.x + mAbsWidth && // (weapon's right edge is
														// to the left of
														// monster's right edge)
						weapon_y + 59 > m.y && // (weapon's bottom edge is below
												// the monster's top edge)
						weapon_y < m.y + m.height) { // (and weapon's top edge
														// is below the
														// monster's bottom
														// edge)

					// ... then as long as it's not dead, let's say it's hit.
					if (!m.dead) {
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
