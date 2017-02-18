
import java.awt.Graphics;
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import java.util.Random;

class Boss extends Sprite {

	// Images
	int calmWidth;
	int calmHeight;

	int slamWidth;
	int slamHeight;

	int chargeWidth;
	int chargeHeight;

	int idleWidth;
	int idleHeight;

	int deadWidth;
	int deadHeight;

	// I dont want you to be able to freeze him.
	boolean laugh;
	boolean isSmashing = false;
	int frozenCounter = 0;
	int laughCounter = 181;

	Sound sound;

	int health = 200;
	boolean dead = false;
	boolean mad = false;
	int madCounter = 0;
	boolean isPoisoned = false;
	int poisonCounter = 0;
	int attack = 0;
	int attackCounter = 0;
	int startingX, startingY;

	// Random variable counter
	int tempCounter;
	int deadCounter = 0;

	// Random value for walking
	Random tempRand = new Random();
	int temp;

	// Boolean value to see where person is
	boolean toLeft = true;

	// Boolean values for Walking
	boolean walkLeft = true;

	boolean done = false;

	int calmCounter;

	// Testing penguin image. shows up if monster is moving left
		Image testPenguin;

	static Image walk;
	static Image slam; // Slam ground thing
	static Image slam2; // Slam ground thing
	static Image slam3; // Slam ground thing
	static Image slam4; // Slam ground thing
	static Image slam5; // Slam ground thing
	static Image slam6; // Slam ground thing
	static Image slam7; // Slam ground thing
	static Image slam8; // Slam ground thing
	static Image idle;
	static Image charge; // Charge to person. idk.

	static Image dead_i; // hahahahah suck it.
	int chargeimageNumber;
	int chargeframeCounter = 0;
	int idleimageNumber;
	int idleframeCounter = 0;
	int dieimageNumber;
	int dieframeCounter = 0;
	int walkimageNumber;
	int walkframeCounter = 0;

	Boss(int xPos, int yPos) throws IOException {
		x = xPos;
		y = yPos;
		width = 150;
		height = 200;
		startingX = xPos;
		startingY = yPos;

		// widths and heights for images
		calmWidth = 270;
		calmHeight = 218;

		slamWidth = 269;
		slamHeight = 218;

		chargeWidth = 374;
		chargeHeight = 218;

		idleWidth = 269;
		idleHeight = 218;

		deadWidth = 359;
		deadHeight = 250;

		slam = ImageIO.read(new File("Attack (1).png"));
		slam2 = ImageIO.read(new File("Attack (2).png"));
		slam3 = ImageIO.read(new File("Attack (3).png"));
		slam4 = ImageIO.read(new File("Attack (4).png"));
		slam5 = ImageIO.read(new File("Attack (5).png"));
		slam6 = ImageIO.read(new File("Attack (6).png"));
		slam7 = ImageIO.read(new File("Attack (7).png"));
		slam8 = ImageIO.read(new File("Attack (8).png"));
		
		testPenguin = ImageIO.read(new File("penguinwalk1.png"));
		chargeimageNumber = 1;
		dieimageNumber = 1;
		idleimageNumber = 1;
		walkimageNumber = 1;
		sound = new Sound();

	}

	void update(LinkedList<Sprite> s) {
		// Need this garbage cause it inherits from sprite
	}

	void update(LinkedList<Sprite> s, Person p) throws IOException {

		// Stops being mad at you about 30 seconds if you go Up top
		if (p.y + height + 200 > y + height)
			madCounter--;
		if (madCounter <= 0) {
			mad = false;
		}

		// Check death
		if (health <= 0) {
			dead = true;
			if (deadCounter == 0) {
				p.exp += 50;
			}
			deadCounter++;
		}
		if (laugh) {
			laughCounter++;
		}

		calmCounter++;

		if (!dead && laughCounter > 180) { // Please don't try to kill me when
											// you are dead.
			// Lets do some attacks bruh.
			if (attack == 1) {
				pauseSlam(p, toLeft);
			} else if (attack == 2) {
				pauseCharge(p, toLeft);
			}
			// Check if it collides with dat person Yo
			personCollision(p);
			// Generate dem numbers if it calm
			if (!mad) {
				if (tempCounter == 0) {
					temp = (tempRand.nextInt(200) - 100) / 25;
					while (temp == 0) {
						temp = (tempRand.nextInt(200) - 100) / 25;
					}
					if (temp > 0) {
						walkLeft = false;
					} else
						walkLeft = true;
				}
				// Perform that walkin shit
				if (x > 0 && x + width < 1920) {
					x += temp;
				} else if (x < 0) {
					temp = Math.abs(temp);
					x += temp;
				} else if (x + width < 1920) {
					temp = Math.abs(temp);
					x -= temp;
				}
			} else {// let's find this fucker that hit me.
				if (p.x < x) { // Is he to my left???
					toLeft = true;
					walkLeft = true;
					if (x - 200 < p.x && attackCounter <= 0) {// CANT GET TOO
																// CLOSE, GOTTA
																// BE CAREFUL
																// HERE BUT
																// THATS CAUSE I
																// WANNA KILL
																// THIS FUCKER

						attack = 1; // COME ON AND SLAM! AND WELCOME TO THE JAM!
					} else if (x - 250 > p.x && attackCounter <= 0) {// WHY IS
																		// HE SO
																		// FAR
						attack = 2; // Charge closer to this bitch
					} else {// Lets just walk over there, not very fast in case
							// he Superman
						if (attack == 0)
							x -= 4;
						if (attackCounter < 200)
							attack = 0;
					}
				} else if (p.x > x + width) { // HES ON MY RIGHT!
					toLeft = false;
					walkLeft = false;
					if (x + width + 200 > p.x && attackCounter <= 0) {// CANT
																		// GET
																		// TOO
																		// CLOSE,
																		// GOTTA
																		// BE
																		// CAREFUL
																		// HERE
																		// BUT
																		// THATS
																		// CAUSE
																		// I
																		// WANNA
																		// KILL
																		// THIS
																		// FUCKER

						attack = 1; // COME ON AND SLAM! AND WELCOME TO THE JAM!
					} else if (x + width + 250 < p.x && attackCounter <= 0) {// WHY
																				// IS
																				// HE
																				// SO
																				// FAR
						attack = 2; // Charge closer to this bitch
					} else {// Lets just walk over there, not very fast in case
							// he Superman
						if (attack == 0)
							x += 4;
						if (attackCounter < 200)
							attack = 0;
					}
				}
			}
			// Execute Charge movement
			if (attack == 2 && attackCounter > 270) {
				x += temp;
				// If the player overlaps the monster at all ...
				if (p.x + p.width > x && // (player's right edge is to the right
											// of monster's left edge)
						p.x < x + width && // (player's left edge is to the left
											// of monster's right edge)
						p.y + p.height > y && // (player's bottom edge is below
												// monster's top edge)
						p.y < y + height) { // (and player's top edge is above
											// monster's bottom edge))
					if (p.hitCounter < -80) {
						if (toLeft) {
							p.hitCounter = 20; // Hit effects.
							p.HP -= 45; // Take that damage.
							p.hitForward = false; // Which direction you going
						} else {
							p.hitCounter = 20; // Hit effects.
							p.HP -= 45; // Take that damage.
							p.hitForward = true; // Which direction you going
						}
					}
				}
			}
		}
		// Lower that attackCounter
		if (attackCounter > 0)
			attackCounter--;

		// Counter to reset generate random "walk" distance
		tempCounter++;
		if (tempCounter > 100)
			tempCounter = 0;

		// Creates new imags for charge
		if (chargeframeCounter % 5 == 0) {
			if (chargeimageNumber >= 11)
				chargeimageNumber = 1;
			charge = ImageIO.read(new File("Charge (" + chargeimageNumber + ").png"));
			chargeimageNumber++;
		}
		chargeframeCounter++;

		// Creates new imags for idle
		if (idleframeCounter % 5 == 0) {
			if (idleimageNumber >= 11)
				idleimageNumber = 1;
			idle = ImageIO.read(new File("Idle (" + idleimageNumber + ").png"));
			idleimageNumber++;
		}
		idleframeCounter++;

		// Creates new imags for idle
		if (dieframeCounter % 5 == 0 && !done && dead) {

			dead_i = ImageIO.read(new File("Dead (" + dieimageNumber + ").png"));
			dieimageNumber++;
		}
		dieframeCounter++;

		// Creates new imags for idle
		if (walkframeCounter % 5 == 0) {
			if (walkimageNumber >= 11)
				walkimageNumber = 1;
			walk = ImageIO.read(new File("Walk (" + walkimageNumber + ").png"));
			walkimageNumber++;
		}
		walkframeCounter++;
	}

	@Override
	void draw(Graphics g) {
		// Have walkLeft implemented, just need to draw different image if you
		// want it to change. Simple if statement over this entire thing
		int drawCalmX = x;
		int drawSlamX = x;
		int drawChargeX = x;
		int drawIdleX = x;
		int drawDeadX = x;
		// Flip the images if we need to
				if (!walkLeft) {
					drawCalmX = x;	
					drawSlamX = x;
					drawChargeX = x;
					drawIdleX = x;		
					drawDeadX = x;
						
					calmWidth = Math.abs(calmWidth); // positive
					slamWidth = Math.abs(slamWidth);
					chargeWidth = Math.abs(chargeWidth);
					idleWidth = Math.abs(idleWidth);
					deadWidth = Math.abs(deadWidth);
				} else {
					drawCalmX = x + Math.abs(calmWidth);
					drawSlamX = x + Math.abs(slamWidth);
					drawChargeX = x + Math.abs(chargeWidth);
					drawIdleX = x + Math.abs(idleWidth);
					drawDeadX = x + Math.abs(deadWidth);
					
					calmWidth = -1 * (int)Math.abs(calmWidth); 
					slamWidth = -1 * (int)Math.abs(slamWidth);
					chargeWidth = -1 * (int)Math.abs(chargeWidth);
					idleWidth = -1 * (int)Math.abs(idleWidth);				
					deadWidth = -1 * (int)Math.abs(deadWidth);
				}
		if (!dead) {
			if (!mad) { // WALKING BUT NOT ANGRY AT PERSON
				if (walkimageNumber >= 10) {
					g.drawImage(walk, drawCalmX, y, calmWidth, calmHeight, null);
					walkframeCounter = 0;

				} else {
					g.drawImage(walk, drawCalmX, y, calmWidth, calmHeight, null);
				}

			} else {
				if (laughCounter < 180) { // HAHAHAH I LIKE THIS
					if (!sound.isLaughing)
						sound.playSong("deepLaugh.wav");
					if (idleimageNumber >= 10) {
						g.drawImage(idle, drawIdleX, y, idleWidth, idleHeight, null);
						idleframeCounter = 0;

					} else {

						g.drawImage(idle, drawIdleX, y, idleWidth, idleHeight, null);
					}
				} else if (attack == 0) { // WALKING BUT ANGRY AT PERSON
					if (walkimageNumber >= 10) {
						g.drawImage(walk, drawCalmX, y, calmWidth, calmHeight, null);
						walkframeCounter = 0;

					} else {
						g.drawImage(walk, drawCalmX, y, calmWidth, calmHeight, null);
					}

				} else if (attack == 1) {// I hate the floor.
					if (attackCounter > 350) { // IDLE TILL SLAM
						if (!sound.isMad)
							sound.playSong("mad.wav");

						if (idleimageNumber >= 10) {
							g.drawImage(idle, drawIdleX, y, idleWidth, idleHeight, null);
							idleframeCounter = 0;

						} else {
							g.drawImage(idle, drawIdleX, y, idleWidth, idleHeight, null);
						}
					} else if (attackCounter > 350) { // SLAM FLOOR

						g.drawImage(slam, drawSlamX, y, slamWidth, slamHeight, null);
					} else if (attackCounter > 345) {

						g.drawImage(slam2, drawSlamX, y, slamWidth, slamHeight, null);
					} else if (attackCounter > 340) {

						g.drawImage(slam3, drawSlamX, y, slamWidth, slamHeight, null);
					} else if (attackCounter > 335) {

						g.drawImage(slam4, drawSlamX, y, slamWidth, slamHeight, null);
					} else if (attackCounter > 330) {

						g.drawImage(slam5, drawSlamX, y, slamWidth, slamHeight, null);
					} else if (attackCounter > 325) {

						g.drawImage(slam6, drawSlamX, y, slamWidth, slamHeight, null);
					} else if (attackCounter > 320) {

						g.drawImage(slam7, drawSlamX, y, slamWidth, slamHeight, null);
					} else if (attackCounter > 315) {

						g.drawImage(slam8, drawSlamX, y, slamWidth, slamHeight, null);
					} else { // GO IDLE
						if (!sound.isMad)
							sound.playSong("mad.wav");
						if (idleimageNumber >= 10) {
							g.drawImage(idle, drawIdleX, y, idleWidth, idleHeight, null);
							idleframeCounter = 0;

						} else {

							g.drawImage(idle, drawIdleX, y, idleWidth, idleHeight, null);
						}
					}

				} else if (attack == 2) {// Im going over there.
					System.out.println("2:" + attackCounter);
					if (attackCounter > 390) { // GO TO FLOOR BEFORE CHARGE
						if (attackCounter > 395) {

							g.drawImage(slam, drawSlamX, y, slamWidth, slamHeight, null);
						} else if (attackCounter > 390) {

							g.drawImage(slam2,drawSlamX, y, slamWidth, slamHeight, null);
						} else if (attackCounter > 385) {

							g.drawImage(slam3, drawSlamX, y, slamWidth, slamHeight, null);
						} else if (attackCounter > 380) {

							g.drawImage(slam4, drawSlamX, y, slamWidth, slamHeight, null);
						} else if (attackCounter > 375) {

							g.drawImage(slam5, drawSlamX, y, slamWidth, slamHeight, null);

						}
					} else if (attackCounter > 265) { // CHARGE
						if (chargeimageNumber >= 10) {
							g.drawImage(charge, drawChargeX, y, chargeWidth, chargeHeight, null);
							chargeframeCounter = 0;

						} else {
							g.drawImage(charge, drawChargeX, y, chargeWidth, chargeHeight, null);

						}
					} else if (attackCounter > 250) { // STAND UP
						if (attackCounter > 260) {

							g.drawImage(slam5, drawSlamX, y, slamWidth, slamHeight, null);
						} else if (attackCounter > 255) {

							g.drawImage(slam6, drawSlamX, y, slamWidth, slamHeight, null);
						} else if (attackCounter > 250) {

							g.drawImage(slam7, drawSlamX, y, slamWidth, slamHeight, null);
						} else if (attackCounter > 245) {

							g.drawImage(slam8, drawSlamX, y, slamWidth, slamHeight, null);
						}
					} else { // GO IDLE
						if (!sound.isMad)
							sound.playSong("mad.wav");
						if (idleimageNumber >= 10) {
							g.drawImage(idle, drawIdleX, y, idleWidth, idleHeight, null);
							idleframeCounter = 0;

						} else {

							g.drawImage(idle, drawIdleX, y, idleWidth, idleHeight, null);
						}
					}
				}
			}
		} else {
			if (!sound.isDying)
				sound.playSong("dying.wav");
			if (dieimageNumber >= 10) {
				g.drawImage(dead_i, x, y, null);
				dieframeCounter = 0;
				dieimageNumber = 1;
				done = true;
			} else
				g.drawImage(dead_i, x, y, null);
		}
	}

	void pauseSlam(Person p, boolean Left) { // Basically this is so the moves
		isSmashing = false;
		sound.isDrop = false; // aren't instant.
		// How long until next attack.
		if (attackCounter <= 0)
			attackCounter = 400;

		if (attackCounter == 330) {
			Slam(p, Left);
		}
	}

	void pauseCharge(Person p, boolean Left) { // Basically this is so the moves
												// aren't instant.
		// How long until next attack and pause before charge
		if (attackCounter <= 0) {
			attackCounter = 400;
			temp = 0;
		}

		if (attackCounter == 370) {
			Charge(p, Left);
		}
		if (attackCounter <= 270) {
			temp = 0;

		}
	}

	void Slam(Person p, boolean Left) {
		isSmashing = true;
		if (!sound.isDrop) {
			sound.playSong("drop.wav");
		}
		if (Left) {// Execute the slam for on LEFT
			if (!p.verticalCollision || p.y + p.height + 50 < y + height || p.x + p.width < x - 400) {
				// Grats you avoided the slam
			} else {
				p.hitCounter = 20; // Hit effects.
				p.HP -= 45; // Take that damage.
				p.hitForward = false; // Which direction you going

			}
		} else {// Execute the slam for on RIGHT
			if (!p.verticalCollision || p.y + p.height > y + height + 200 || p.x > x + width + 400) {
				// Grats you avoided the slam
			} else {
				p.hitCounter = 20; // Hit effects.
				p.HP -= 45; // Take that damage.
				p.hitForward = true; // Which direction you going

			}
		}

	}

	void Charge(Person p, boolean Left) {
		if (Left) {
			temp = -7;
		} else {
			temp = 7;
		}
	}

	void personCollision(Person p) {
		// Don't hurt the player if the monster is dead or frozen!!
		if (!dead) {

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
					if (!toLeft) {
						p.hitForward = true;
					} else
						p.hitForward = false;
					p.HP -= 25;
					p.hitCounter = 20;
				}
			}
		}
	}

	public void getFrozen() { // NOT ABLE TO BE FROZEN MWAHAHAH
		if (frozenCounter == 0) {
			laugh = true;
			laughCounter = 0;
		}
		frozenCounter++;
		mad = true;
		madCounter = 1800;
	}

	public void getHurt() {
		health -= 10;
		mad = true;
		madCounter = 1800;
	}

	public void getPoisoned() {
		isPoisoned = true;
		poisonCounter = 5; // monster gets hurt slowly over time. (2HP each
							// time, 5 times)

		mad = true;
		madCounter = 1800;
	}

	@Override
	void collision(LinkedList<Sprite> s) {
		// TODO Auto-generated method stub

	}

}