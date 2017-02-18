import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import java.util.Random;

class Monster extends Sprite {
	// HP and location and stuff
	int health = 20;
	int startingX;
	int startingY;
	int LeftBoundaryX;
	int RightBoundaryX;
	boolean hit = false;
	boolean isFrozen = false;
	boolean isPoisoned = false;
	int poisonCounter = 0;

	// Temporary Counter for Time stay "angry"
	int hitCounter = 0;
	// Temporary count to count dead
	int count;

	// Random variable counter
	int tempCounter;

	// Random value for walking
	Random tempRand = new Random();
	int temp;

	// boolean dropping hearts/money on death?
	boolean dead = false;

	// Images. There shouldn't be too many static images in the Monster
	// class (I think tombstone + layers would be the only exceptions),
	// because each type of monster has a different set of images
	int frameCounter = 0;
	int imageCounter = 1;
	boolean tookDamageRecently = false;		// for drawing a monster in 'hurt' position real quick

	static Image tombstone = null;
	static Image layer = null;
	static Image layer2 = null;
	
	// All these image things get instantiated in Monster's child-classes
	Image monsterImage = null;
	Image frozenMonsterImage = null;	
	Image hitMonsterImage = null;
	Image poisonedHitMonsterImage = null;
	Image frozenHitMonsterImage = null;
	
	LinkedList<Image> walkingImageList = null;
	LinkedList<Image> poisonedImageList = null;

	// Boolean values for Walking
	boolean walkLeft = true;

	Monster(int xPos, int yPos, int LBound, int RBound) throws IOException {
		// Initializes Monster onto location where Platform is.
		x = xPos;
		y = yPos;
		frameCounter = 0;
		startingX = xPos;
		startingY = yPos;
		LeftBoundaryX = LBound;
		RightBoundaryX = RBound;

		width = 55;
		height = 65;

		// Try to create static images
		try {
			tombstone = ImageIO.read(new File("Tombstone.png"));
			layer = ImageIO.read(new File("map1frontLayer.png"));
			layer2 = ImageIO.read(new File("map2frontLayer.png"));
		} catch (IOException e) {
		}
	}

	public void update(LinkedList<Sprite> s, Person p) {
		// Handle being poisoned lol
		if (poisonCounter > 0 && frameCounter % 60 == 0) {
			health -= 4;
			tookDamageRecently = true;
			poisonCounter--;
			
			if (poisonCounter == 0 || health < 0) {
				isPoisoned = false;
			}
		} 
		
		frameCounter += 1;

		// Falsify 'tookDamageRecently' after, say... 36 frames?
		if (frameCounter % 36 == 0 && tookDamageRecently) {
			tookDamageRecently = false;
		}
		
		// Creates new images for Monster when divisible by 3. This is
		// fucked up doing images is fucked up
		if (frameCounter % 3 == 1) {
			
			// If we can walk...
			if (!isFrozen && !tookDamageRecently && health > 0) {
				
				// ...then cycle through the appropriate images to simulate walking
				if (isPoisoned) monsterImage = poisonedImageList.get(imageCounter - 1);
				else 			monsterImage = walkingImageList.get(imageCounter - 1);
					
				if 		(walkLeft && width > 0)  width *= -1;		// If we're walking to the left, width should be negative
				else if (!walkLeft && width < 0) width *= -1;		// Also, if we're walking to the right, width should be positive
					
				imageCounter++;
				if (imageCounter > 12) imageCounter = 1;
				
			// If we can't walk...
			} else {
				// ... then either we were frozen, in which case draw the frozen image ezpz...
				if (isFrozen && !tookDamageRecently) monsterImage = frozenMonsterImage;
				
				// ... OR BOTH. fucking both. in which case show the frozen hit image.
				else if (isFrozen && tookDamageRecently) monsterImage = frozenHitMonsterImage;
				
				// ... or we were hit recently, in which case show the hit face...
				else if (isPoisoned)  monsterImage = poisonedHitMonsterImage;
				else if (!isPoisoned) monsterImage = hitMonsterImage;
				
				// I hope this doesn't get printed out lol ^_^;
				else System.out.println("An image state that I didn't account for is happening!! :(");
			}
		}

		// Lower hit counter and remove "hit" after time
		hitCounter--;
		if(hitCounter <= 0 || health <= 0){
			hit = false;
			isFrozen = false;	
			tookDamageRecently = false;	
		}

		// Check if dead.
		if (health <= 0) {
			dead = true;
		}

		// Check Direction
		if (temp > 0) {
			walkLeft = false;
		} else
			walkLeft = true;

		// movement!! only move if not frozen
		if (!isFrozen && !tookDamageRecently) {
			// Random "walk" calculator if not on boundary
			if (tempCounter == 0) {
				temp = tempRand.nextInt(14) - 7;
				while (temp == 0) {
					temp = tempRand.nextInt(14) - 7;
				}
				if (temp > 0) {
					walkLeft = false;
				} else
					walkLeft = true;
			}

			// Random Movement and "Angry" movement
			if (!dead) {
				if (!hit) {
					if (x <= LeftBoundaryX || x + 55 >= RightBoundaryX) {
						if (x <= LeftBoundaryX) {
							temp = tempRand.nextInt(4);
							if (temp > 0) {
								walkLeft = false;
							} else
								walkLeft = true;
							x += temp;
						} else {
							temp = tempRand.nextInt(4) - 8;
							if (temp > 0) {
								walkLeft = false;
							} else
								walkLeft = true;
							x += temp;
						}
					} else {
						x += temp;
					}
				} else {
					if (p.x > x) {
						if (x + 55 <= RightBoundaryX)
							x += 5;
						walkLeft = false;
					} else {
						if (x >= LeftBoundaryX)
							x -= 5;
						walkLeft = true;
					}
				}
			}
		}

		// Check collision on person
		personCollision(p);

		// Respawn
		if (count > 200) {
			x = startingX;
			y = startingY;
			health = 20;
			hit = false;
			dead = false;
			count = 0;
		}
		// Give exp to player for kill
		else if (count == 1) {
			p.exp += 2.5;
			p.MonsterKilled++;
		}
		
		//Give Achievement to Player for Iceball
		if(isFrozen){
			if(!p.achievement3)
				p.achievement3 = true;
		}

		// Counter to reset generate random "walk" distance
		tempCounter++;
		if (tempCounter > 200)
			tempCounter = 0;
	}

	public boolean Collision(LinkedList<Sprite> s) {
		return false;
	}

	
	void draw(Graphics g){
		// Alive
		if(monsterImage != null && health > 0) {
			if(width > 0) 	g.drawImage(monsterImage, x, y, width, height, null);
			else 			g.drawImage(monsterImage, x-width, y, width, height, null);
			
		// Dead
		} else if(health <= 0){
			g.drawImage(tombstone, x, y + 14, null);
			count++;
		}
	}

	void collision(LinkedList<Sprite> s) {
	}

	void personCollision(Person p) {
		// Don't hurt the player if the monster is dead or frozen!!
		if (!dead && !isFrozen) {

			// Calculate absolute values for widths since
			// negative widths throw our calculations off
			int absWidth = Math.abs(width);
			int pAbsWidth = Math.abs(p.width);

			// If the player overlaps the monster at all ...
			if (p.x + pAbsWidth > x && // (player's right edge is to the right
										// of monster's left edge)
					p.x < x + absWidth && // (player's left edge is to the left
											// of monster's right edge)
					p.y + p.height > y && // (player's bottom edge is below
											// monster's top edge)
					p.y < y + height) { // (and player's top edge is above
										// monster's bottom edge)

				if (p.hitCounter < -80) {
					if (p.isFacingLeft) {
						p.hitForward = true;
					} else
						p.hitForward = false;
					p.HP -= 15;
					p.hitCounter = 20;
					hit = false;
				}
			}
		}
	}

	@Override
	void update(LinkedList<Sprite> s) {
		// TODO Auto-generated method stub

	}

	// --------------------------------------------------------------
	// | 'MONSTER IS HURT' FUNCTIONS |
	// --------------------------------------------------------------

	public void getHurt() {
		health -= 10;
		tookDamageRecently = true;
		
		hit = true;
		hitCounter = 180;
	}
	
	public void getFrozen() {
		isFrozen = true;
		
		hit = true;
		hitCounter = 180;
	}
	
	public void getPoisoned() {
		isPoisoned = true;
		poisonCounter = 5;	// monster gets hurt slowly over time
		
		hit = true;
		hitCounter = 180;
	}
}
