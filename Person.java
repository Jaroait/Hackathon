import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

class Person {
	// Basic test
	boolean gravityTemp = false;
	boolean isCoolingDown = false;	// true if person shot a weapon recently (a second ago?)
	int[] tempY;
	int[] tempX;
	int[] tempWidth;
	int platTempWidth = 1920;
	int previousTempWidth = 1920;
	int platTempX = 0;
	int previousTempX = 0;
	int tempCount;
	Platform p;
	int platTempY = 800;
	int previousTempY = 800;
	int frame = 100;
	int frameCounter;
	int fireCounter = 0;
	Boolean shoot = false;
	int imageNumber = 1;
	Background background;
	int weaponChoice;

	// Boolean Value to Return to Model
	Boolean forward;
	Boolean back;

	// Image Rendering Stuff
	static Image personLeft = null;
	static Image personRight = null;
	static Image stand = null;
	static Image walk = null;
	static Image jump1 = null;
	static Image jump2 = null;
	static Image shoot1 = null;
	static Image shoot2 = null;
	static Image layer = null;
	static Image layer2 = null;
	static Image layer3 = null;
	static Image layer4 = null;
	static Image levelUP = null;
	static Image mannaBar = null;
	static Image healthBar;
	static Image weaponMenu = null;
	static Image weaponStatus = null;
	static Image frameMenu = null;
	static Image levelupBar = null;
	static Image fireballMenu = null;
	static Image freezeballMenu = null;
	static Image arrowMenu = null;
	static Image swordMenu = null;
	static Image weaponFrame = null;
	static Image freezeFrame = null;
	static Image poisonFrame = null;
	static Image bowFrame = null;
	static Image youDiedImage = null;

	int leftCounter, rightCounter, gravityCounter;
	Color customColor;
	Color customColor2;

	// Basics for Collision and Position
	int x, y, width, height;

	// Collision Booleans For Left/Right/Up/Down
	boolean verticalCollision, horizontalCollisionL, horizontalCollisionR, isFacingLeft, jump;

	// Gravity Calculation
	double gravity;

	// Value to execute smooth jump
	int jumpCounter = 3;
	int frameWidth = 54;

	// Boolean values for Walking
	boolean WalkLeft, WalkRight;

	// Level Counter/EXP
	int level = 1;
	double exp = 0.0;
	int levelCounter = -20;

	// HP
	int DefaultHP = 200;
	int HP = 0;

	// MP
	int DefaultMP = 190;
	int MP = 0;

	// Value used when hit and boolean for hit direction.
	int hitCounter;
	boolean hitForward = false;
	
	//ACHIEVEMENT Variables for MAX!
	boolean achievement1 = false;
	boolean achievement2 = false;
	boolean achievement3 = false;
	boolean achievement4 = false;
	boolean achievement5 = false;
	boolean achievement6 = false;
	static Image achieve1 = null;
	static Image achieve2 = null;
	static Image achieve3 = null;
	static Image achieve4 = null;
	static Image achieve5 = null;
	static Image achieve6 = null;
	int MonsterKilled = 0;
	

	// Constructor
	Person(LinkedList<Sprite> s) throws IOException {
		// DEFAULT SPAWN for MAP 1
		x = 100;
		y = 700;
		width = 44;
		height = 64;

		customColor = new Color(225, 10, 10);
		;
		customColor2 = new Color(0, 225, 250);

		layer = ImageIO.read(new File("map1frontLayer.png"));
		layer2 = ImageIO.read(new File("map2frontLayer.png"));
		layer3 = ImageIO.read(new File("map3frontLayer.png"));
		layer4 = ImageIO.read(new File("map4frontLayer.png"));
		healthBar = ImageIO.read(new File("newhealthbar.png"));
		stand = ImageIO.read(new File("walk1.png"));

		weaponMenu = ImageIO.read(new File("weaponsMenu.png"));
		frameMenu = ImageIO.read(new File("frame.png"));
		weaponStatus = ImageIO.read(new File("weaponStatus.png"));
		levelupBar = ImageIO.read(new File("level1.png"));
		jump1 = ImageIO.read(new File("jump1.png"));
		jump2 = ImageIO.read(new File("jump2.png"));
		shoot1 = ImageIO.read(new File("shoot1.png"));
		shoot2 = ImageIO.read(new File("shoot2.png"));
		mannaBar = ImageIO.read(new File("levelupbar.png"));
		levelUP = ImageIO.read(new File("levelUP.png"));
		fireballMenu = ImageIO.read(new File("fireballMenu.png"));
		freezeballMenu = ImageIO.read(new File("freezeballMenu.png"));
		arrowMenu = ImageIO.read(new File("arrowMenu.png"));
		swordMenu = ImageIO.read(new File("swordMenu.png"));
		weaponFrame = ImageIO.read(new File("weaponFrame.png"));
		freezeFrame = ImageIO.read(new File("freezeFrame.png"));
		poisonFrame = ImageIO.read(new File("poisonFrame.png"));
		bowFrame = ImageIO.read(new File("bowFrame.png"));
		achieve1 = ImageIO.read(new File("achieve1.png"));
		achieve2 = ImageIO.read(new File("achieve2.png"));
		achieve3 = ImageIO.read(new File("achieve3.png"));
		achieve4 = ImageIO.read(new File("achieve4.png"));
		achieve5 = ImageIO.read(new File("achieve5.png"));
		achieve6 = ImageIO.read(new File("achieve6.png"));
		youDiedImage = ImageIO.read(new File("youDied.png"));

		// Stuff for collision/gravity
		tempY = new int[s.size() + 1];
		tempX = new int[s.size() + 1];
		tempWidth = new int[s.size() + 1];
		tempX[0] = 0;
		tempWidth[0] = 1920;
		tempY[0] = 800;
	}

	public Person() {
		// TODO Auto-generated constructor stub
		// Fireball requires this, due to not calling super directly but simply
		// using this.

	}

	// Update
	public void update(LinkedList<Sprite> s) throws IOException {

		// Establish Booleans for Hit Re-enabling walking
		boolean redoWalkLeft = false;
		boolean redoWalkRight = false;

    //Check Achievements
		if(MonsterKilled >= 1)
			achievement1 = true;
		if(MonsterKilled >= 10)
			achievement4 = true;
		
		if(level == 5)
			achievement5 = true;
			
			
		// Check Level Up / Experience
		if (exp >= 10 * level) {
			if (DefaultHP < 148) {
				HP = 20;
			}
			level++;
			exp = exp - (10 * level);
			DefaultMP = 190;
			System.out.println("LevelUp!");
			levelCounter = 10;
			if (level < 6)
				levelupBar = ImageIO.read(new File("level" + level + ".png"));
		}
		
		
		// Adds or subtracts health and mana
		if(DefaultHP >0)
		DefaultHP = DefaultHP + HP;
		HP = 0;
		
		DefaultMP = DefaultMP + MP;
		MP = 0;
		// Check if hit and check if able to be hit.
		if (hitCounter > 0) {
			if (hitCounter == 20)
				gravity = -6;
			if (hitForward) {
				if (x + 10 < 1920) {
					x += 10;
				}
			} else {
				if (x - 10 > 0) {
					x -= 10;
				}
			}

			if (jump) {
				jump = false;
			}
			if (WalkRight) {
				redoWalkRight = true;
				WalkRight = false;
			}
			if (WalkLeft) {
				redoWalkLeft = true;
				WalkLeft = false;
			}
			verticalCollision = false;
			hitCounter--;
		} else {
			hitCounter--;
			if (redoWalkRight) {
				WalkRight = true;
				redoWalkRight = false;
			}
			if (redoWalkLeft) {
				WalkLeft = true;
				redoWalkLeft = false;
			}
		}

		// Reduce LevelCounter
		levelCounter--;

		// Image Rendering Frame Counters for Left/Right
		leftCounter -= 1;
		rightCounter -= 1;
		frame = x;

		// Safety net for falling off map. SHOULD NEVER HAPPEN!!!
		if (y + 10 >= 1080) {
			x = 100;
			y = 700;
			gravity = 0;
			tempY = new int[s.size() + 1];
			tempX = new int[s.size() + 1];
			tempWidth = new int[s.size() + 1];
			tempX[0] = 0;
			tempWidth[0] = 1920;
			tempY[0] = 800;
			platTempWidth = 1920;
			previousTempWidth = 1920;
			platTempX = 0;
			previousTempX = 0;
			platTempY = 800;
			previousTempY = 800;
		}

		// Collision detection for edge of map for LEFT
		if (x <= 0) {
			horizontalCollisionL = true;
		} else if (x >= 0) {
			horizontalCollisionL = false;
		}

		// Collision detection for edge of map for RIGHT
		if (x + width >= 1920) {
			horizontalCollisionR = true;
		} else if (x + width <= 1920) {
			horizontalCollisionR = false;
		}

		// Walking Left or Right based off Boolean Value
		if (WalkLeft) {
			if (!horizontalCollisionL) {
				x = x - 6;
			}
		}
		if (WalkRight) {
			if (!horizontalCollisionR) {
				x = x + 6;
			}
		}

		// Jumping
		if (jump) {
			if (tempCount == 0)
				gravity = 0;
			tempCount++;
			gravity -= 4;
			y += gravity;
			jumpCounter += 1;
			verticalCollision = false;
			// System.out.println(gravity);
		}

		// Collision
		collision(s);

		// Horizontal Collision, Temporary
		horizontalCollision(s);

		// Safety Net for falling off platforms
		if (p != null && !jump) {
			for (int i = 0; i < tempX.length; i++) {
				if ((x + width <= platTempX || x >= platTempX + platTempWidth)
						&& (y + height - 25 <= platTempY || y + height <= platTempY)) {

					verticalCollision = false;
					platTempY = previousTempY;
					platTempX = previousTempX;
					platTempWidth = previousTempWidth;
					if (tempX[i] == previousTempX) {
						if (i != 0) {
							previousTempX = tempX[i - 1];
							previousTempY = tempY[i - 1];
							previousTempWidth = tempWidth[i - 1];
						} else {
							previousTempY = 800;
							previousTempX = 0;
							previousTempWidth = 1980;
						}
					}
				}
			}
		}
		// Gravity/Falling
		if (verticalCollision) {
			if (gravity > 0) {
				gravity = 0;
			} else {
			}
		} else if (!jump) {
			if (gravity >= 15.2)
				gravity = 15.2;
			else
				gravity += .8;

			if (x + width >= platTempX && x <= platTempX + platTempWidth && gravity > 0) {

				if ((y + gravity + height >= platTempY || y + gravity >= platTempY)) {
					y = platTempY - height + 2;
				} else
					y += gravity;
			} else
				y += gravity;
		}

		// Variables Reset
		if (jumpCounter >= 3) {
			tempCount = 0;
			jump = false;
		}

		frameCounter += 1;
		
		// Enable player to shoot their weapon again! No spamming u guys
		if (frameCounter % 30 == 0 && isCoolingDown) {
			//System.out.println("You're good to shoot another weapon now!");
			isCoolingDown = false;
		}

		// Creates new images for Person when divisible by 3
		if (frameCounter % 3 == 1) {
			try {
				walk = ImageIO.read(new File("walk" + imageNumber + ".png"));
			} catch (IOException e) {

			}
			imageNumber++;
		}

	}
	// End Update

	// Collision
	void collision(LinkedList<Sprite> s) {
		for (int i = 0; i < s.size(); i++) {
			Sprite sprite = s.get(i);
			if (sprite instanceof Platform) {
				tempY[i + 1] = sprite.y;
				tempX[i + 1] = sprite.x;
				tempWidth[i + 1] = sprite.width;
				if (!verticalCollision & !jump) {
					if (x + width >= sprite.x && x <= sprite.x + sprite.width && y + height >= sprite.y
							&& y + height - 25 <= sprite.y) {
						// System.out.println("COLLIDING");
						p = (Platform) sprite;
						// tempY[i + 1] = sprite.y;
						previousTempY = tempY[i];
						platTempY = tempY[i + 1];
						// tempX[i + 1] = sprite.x;
						previousTempX = tempX[i];
						platTempX = tempX[i + 1];
						// tempWidth[i + 1] = sprite.width;
						previousTempWidth = tempWidth[i];
						platTempWidth = tempWidth[i + 1];
						gravityTemp = true;
						if (gravity > 0)
							verticalCollision = true;
						if (gravity > 0)
							gravity = 0;
						jumpCounter = 3;
					} else {
						// gravityTemp = false;
						verticalCollision = false;
					}
				} else {
					if (x + width <= sprite.x && x >= sprite.x + sprite.width && y + height <= sprite.y
							&& y + height - 25 >= sprite.y) {
						verticalCollision = false;
						gravityTemp = false;
					}
				}

			} else {
			}
		}
	}
	// End Collision

	// Horizontal Collision
	void horizontalCollision(LinkedList<Sprite> s) {
		for (int z = 0; z < s.size(); z++) {
			Sprite sprite = s.get(z);
			if (sprite instanceof Platform) {
				Platform plat = (Platform) sprite;
				if (plat.horizontal) {
					if (y + height <= plat.y + plat.height + 10 && y >= plat.y) {
						// System.out.println("Does this execute");
						if (x + width + 6 >= plat.x && x + width <= plat.x) {
							// System.out.println("Does this execute");
							horizontalCollisionR = true;
							break;
						} else if (x - 6 <= plat.x + plat.width && x >= plat.x + plat.width) {
							// System.out.println("Does this execute2");
							horizontalCollisionL = true;
							break;
						} else {
							// horizontalCollisionL = false;
						}
					} else {
						horizontalCollisionL = false;
						horizontalCollisionR = false;
					}
				}
			}
		}
	}

	void draw(Graphics g, int mapNumber) {
		if (levelCounter >= -10) {
			// Hahahahahahah. Please change :)
			g.drawImage(levelUP, x - 20, y - 934, null);
		}

		else if (!isFacingLeft) {
			// If shoot is true, person shooting image is drawn
			if (shoot) {
				fireCounter++;
				// Animates shooting
				if (fireCounter < 6) {
					g.drawImage(shoot1, x, y, frameWidth, 70, null);
					fireCounter++;
					// Animates shooting
				} else if (fireCounter < 12) {
					g.drawImage(shoot2, x, y, frameWidth, 70, null);
					fireCounter++;
					// Animates shooting
				} else {
					g.drawImage(shoot1, x, y, frameWidth, 70, null);
					fireCounter = 0;
					shoot = false;
				}
				// If jump is true, person jumping is drawn
			} else if (jump) {
				// Animates jumping when standing still
				if (frame == x)
					g.drawImage(jump1, x, y, frameWidth, 70, null);
				// Animates jumping when running
				else {
					g.drawImage(jump2, x, y, frameWidth, 70, null);
				}

				frameCounter = 0;
				frame = x;
			}
			// If standing still, person standing is drawn
			else if (frame == x) {
				g.drawImage(stand, x, y, frameWidth, 70, null);
			}
			// If images are equal or greater than 12, then reset values
			else if (imageNumber >= 12) {
				g.drawImage(walk, x, y, frameWidth, 70, null);
				imageNumber = 2;
			} else
				g.drawImage(walk, x, y, frameWidth, 70, null);
		} else {
			// If shoot is true, person shooting image is drawn
			if (shoot) {
				fireCounter++;
				// Animates shooting
				if (fireCounter < 6) {
					g.drawImage(shoot1, x - frameWidth, y, frameWidth, 70, null);
					fireCounter++;
					// Animates shooting
				} else if (fireCounter < 12) {
					g.drawImage(shoot2, x - frameWidth, y, frameWidth, 70, null);
					fireCounter++;
					// Animates shooting
				} else {
					g.drawImage(shoot1, x - frameWidth, y, frameWidth, 70, null);
					fireCounter = 0;
					shoot = false;
				}
				// If jump is true, person jumping is drawn
			} else if (jump) {
				// Animates jumping when standing still
				if (frame == x)
					g.drawImage(jump1, x - frameWidth, y, frameWidth, 70, null);
				// Animates jumping when running
				else {
					g.drawImage(jump2, x - frameWidth, y, frameWidth, 70, null);
				}

				frameCounter = 0;
				frame = x;
			}
			// If standing still, person standing is drawn
			else if (frame == x) {
				g.drawImage(stand, x - frameWidth, y, frameWidth, 70, null);
			}
			// If images are equal or greater than 12, then reset values
			else if (imageNumber >= 12) {
				g.drawImage(walk, x - frameWidth, y, frameWidth, 70, null);
				imageNumber = 2;
			} else
				g.drawImage(walk, x - frameWidth, y, frameWidth, 70, null);
		}

		// Draws the front layer for the platform 
		
		if (mapNumber == 1)
			g.drawImage(layer, 0, 0, null);
		else if (mapNumber == 2)
			g.drawImage(layer2, 5, -55, null);
		else if (mapNumber == 3)
			g.drawImage(layer3, 0, 0, null);
		else if (mapNumber == 4)
			g.drawImage(layer4, 0, 0, null);

		// Draws health bar
		g.setColor(Color.GRAY);
		g.fillRect(80, 20, 200, 42);
		g.setColor(customColor);
		g.fillRect(80, 20, DefaultHP, 42);
		g.drawImage(healthBar, 5, 5, null);

		// Draws manna bar
		g.setColor(Color.GRAY);
		g.fillRect(80, 100, 200, 40);
		g.setColor(customColor2);
		g.fillRect(80, 100, DefaultMP, 40);
		g.drawImage(mannaBar, 5, 85, null);

		// Draws level bar 
		g.drawImage(levelupBar, 1600, 8, null);

		// Draws weapon bar
		g.drawImage(weaponMenu, 1600, 820, null);
		g.drawImage(frameMenu, 505, 850, null);

		//Draws each weapon display
		g.drawImage(swordMenu, 1100, 850, null);
		g.drawImage(arrowMenu, 975, 850, null);
		g.drawImage(freezeballMenu, 850, 850, null);
		g.drawImage(fireballMenu, 725, 850, null);
		
		//Draws achievement if completed
		if(achievement1) //Kill a monster
			g.drawImage(achieve1, 1640, 884, null); 
		if(achievement2) //Use a door
			g.drawImage(achieve2, 1719, 884, null);
		if(achievement3) //Freeze an enemy
			g.drawImage(achieve3, 1796, 884, null);
		if(achievement4) //Kill 10 monsters
			g.drawImage(achieve4, 1640, 948, null);
		if(achievement5) //Get level 5
			g.drawImage(achieve5, 1719, 948, null);
		if(achievement6) //Kill boss
			g.drawImage(achieve6, 1796, 948, null);
							
		
		
		//Highlights weapon that is chosen
		if (weaponChoice == 0)
			g.drawImage(weaponFrame, 722, 852, null);
		else if (weaponChoice == 1)
			g.drawImage(freezeFrame, 849, 850, null);
		else if (weaponChoice == 2)
			g.drawImage(poisonFrame, 976, 850, null);
		else if (weaponChoice == 3)
			g.drawImage(bowFrame, 1101, 851, null);
		
		if (DefaultHP <= 0) g.drawImage(youDiedImage, (1920/2 - 626/2), (1080/2 - 144/2), null);
	}

	// Pressed Left Arrow
	public void walkLeft() {
		if (horizontalCollisionR) {
			horizontalCollisionR = false;
		}
		WalkLeft = true;
		frameWidth = -54;
		// WalkRight = false;
		isFacingLeft = true;
	}

	// Pressed Right Arrow
	public void walkRight() {
		if (horizontalCollisionL) {
			horizontalCollisionL = false;
		}
		WalkRight = true;
		frameWidth = 54;
		// WalkLeft = false;
		isFacingLeft = false;
	}

	// Released Left Arrow
	public void stopWalkLeft() {
		// Set Boolean Value to False for Update
		WalkLeft = false;
		if (WalkRight == true) {
			frameWidth = 54;
			isFacingLeft = false;
		}
	}

	// Released Right Arrow
	public void stopWalkRight() {
		// Set Boolean Value to False for Update
		WalkRight = false;
		if (WalkLeft == true) {
			frameWidth = -54;
			isFacingLeft = true;
		}
	}

	// Pressed Shift
	public void jump() {
		if (verticalCollision) {
			horizontalCollisionR = false;
			horizontalCollisionL = false;
			jump = true;
			jumpCounter = 0;
			tempCount = 0;
		}
	}

	// Released Shift
	/*
	 * public void stopJump() { jump = false; jumpCounter = 1; }
	 */

	// Pressed Up Arrow
	public boolean usePortal(boolean portalCollision, boolean BackOrForward, LinkedList<Sprite> s,
			LinkedList<LinkedList<Sprite>> map) {
		if (portalCollision) {
			if (!BackOrForward) {
				for (int i = 0; i < map.size(); i++) {
					LinkedList<Sprite> temp = map.get(i);
					if (temp == s) {
						// Increment
						temp = map.get(i + 1);

						// Reset Values for Gravity/Collision
						tempY = new int[temp.size() + 1];
						tempX = new int[temp.size() + 1];
						tempWidth = new int[temp.size() + 1];
						tempX[0] = 0;
						tempWidth[0] = 1980;
						tempY[0] = 800;

						// Set Value For Model
						forward = true;
						back = false;

						// Reset Values on Person
						for (int j = 0; j < map.get(i + 1).size(); j++) {
							if (map.get(i + 1).get(j) instanceof Portal) {
								if (map.get(i + 1).get(j).x < 1000) {
									x = map.get(i + 1).get(j).x;
									y = map.get(i + 1).get(j).y;
									gravity = 0;
									verticalCollision = false;
								}
							}
						}
						return true;
					}
				}
			} else {
				for (int i = 0; i < map.size(); i++) {
					LinkedList<Sprite> temp = map.get(i);
					if (temp == s) {
						// Back one map?
						temp = map.get(i - 1);
						// Reset Values for Gravity/Collision
						tempY = new int[temp.size() + 1];
						tempX = new int[temp.size() + 1];
						tempWidth = new int[temp.size() + 1];
						tempX[0] = 0;
						tempWidth[0] = 1980;
						tempY[0] = 800;

						// Set Value For Model
						forward = false;
						back = true;

						// Reset Values on Person
						for (int j = 0; j < map.get(i - 1).size(); j++) {
							if (map.get(i - 1).get(j) instanceof Portal) {
								if (map.get(i - 1).get(j).x > 1000) {
									x = map.get(i - 1).get(j).x;
									y = map.get(i - 1).get(j).y;
									gravity = 0;
									verticalCollision = false;
								}
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}
}
