import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

abstract class Weapon extends Person {
	// Variables (these are things that all weapons have)
	Person person;		// Person that shoots the weapon
	int range;			// How far weapon goes (in pixels) before it disappears
	
	int weapon_x;		// Where the weapon is in space
	int weapon_y;
	
	LinkedList<Image> imageList;	// this gets initialized in Weapon's children-classes' constructors
	
	Boolean shoot;
	Boolean distance;
	int weaponDistance;
	Image weaponImage = null;
	int flushCount;
	int frameCount;
	int width;
	int imageNumber = 1;
	boolean hit;
	boolean shotToTheLeft;

	public Weapon(Person p) {		
		this.person = p;
		this.range = 300;
		
		distance = false;
		weaponDistance = person.x;
		shotToTheLeft = person.isFacingLeft;
		
		weapon_x = (person.isFacingLeft) ? person.x : person.x + person.width;
		weapon_y = person.y;
		
		width = 59;
		height = 59;
	}

	public void update() {
		/*
		 * Sets distance to true to not render image. Sets shoot to false to
		 * only shoot once
		 */
		if (weapon_x >= weaponDistance + range && !shotToTheLeft) {
			distance = true;
			shoot = false;
		}
		/*
		 * Sets distance to true to not render image. Sets shoot to false to
		 * only shoot once
		 */
		else if (weapon_x <= weaponDistance - range && shotToTheLeft) {
			distance = true;
			shoot = false;
		}
		// Checks if the person is facing left and makes width negative to flip image
		else if (shotToTheLeft) {
			width = -1 * (int)Math.abs(width);
			weapon_x -= 10;
		} else {
			width = Math.abs(width);
			weapon_x += 10;
		}

		frameCount += 1;

		// Handle the animation! A new picture every 5 frames (1/12 of a second)
		if (frameCount % 5 == 0 && distance == false) {
			
			// Reset imageNumber and frameCount if they get too big
			if (imageNumber > 6) {
				imageNumber = 1;
				frameCount = 0;
			}
			
			// Reassign weaponImage to a new image every 5 frames.
			// Creates the illusion of animation
			weaponImage = imageList.get(imageNumber - 1);
			
			// Get ready to draw the next image in 5 frames
			imageNumber++;
		}
	}

	/*
	 * Draws image and makes shoot true to prevent from rendering more than
	 * once
	 */
	void draw(Graphics g) {
		if (distance == false) {
			shoot = true;	// Resets if image is at 6 or greater
			if(hit) { /* Some sort of explosion? */ }
			else g.drawImage(weaponImage, weapon_x, weapon_y, width, height, null);
		}
	}
}
