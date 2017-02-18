import java.util.LinkedList;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Graphics;
import java.awt.Point;

class Model {
	// Boolean for Direction in Map from Portal
	boolean Back;

	// LinkedList of LinkedList
	LinkedList<LinkedList<Sprite>> Maps;

	// LinkedList to hold Current Map
	LinkedList<Sprite> currentMap;

	// LinkedList for Platforms
	LinkedList<Sprite> sprites1;
	LinkedList<Sprite> sprites2;
	LinkedList<Sprite> sprites3;
	LinkedList<Sprite> sprites4;

	// LinkedList for Monsters
	LinkedList<LinkedList<Monster>> allMonsters;
	LinkedList<Monster> monsters1;
	LinkedList<Monster> monsters2;

	// Hold Current Monster List
	LinkedList<Monster> currentMonster;

	// Define Base Map to use as "variable"
	Map map1;

	// Character creation
	Person person;

	// Weapon creation
	Weapon weapon;
	int whichWeapon = 0; // 0 = fire, 1 = ice, 2 = poison arrow, 3 = sword

	// PauseMenu
	PauseMenu menu;

	// Background creation
	Background background;

	// Weather creation
	Weather weather;

	// Sound Creation
	Sound sound;
	Sound sound2;
	Sound sound3;

	// Boolean for pausing the game
	boolean isPaused = false;
	boolean dead;

	int soundCounter = 29;
	int diedCounter = 0;

	public boolean isReseted = true;

	// Constructor
	Model() throws IOException, FontFormatException {
		// Initialize crap
		map1 = new Map();
		Maps = new LinkedList<LinkedList<Sprite>>();
		allMonsters = new LinkedList<LinkedList<Monster>>();
		sprites1 = new LinkedList<Sprite>();
		sprites2 = new LinkedList<Sprite>();
		sprites3 = new LinkedList<Sprite>();
		sprites4 = new LinkedList<Sprite>();
		currentMap = new LinkedList<Sprite>();
		monsters1 = new LinkedList<Monster>();
		monsters2 = new LinkedList<Monster>();
		currentMonster = new LinkedList<Monster>();

		// Add all maps into list
		Maps.add(sprites1);
		Maps.add(sprites2);
		Maps.add(sprites3);
		Maps.add(sprites4);

		// Map 1
		sprites1.add(map1.background1);
		sprites1.add(map1.base);
		sprites1.add(map1.base2);
		sprites1.add(map1.base3);
		sprites1.add(map1.base4);
		sprites1.add(map1.base5);
		sprites1.add(map1.base6);
		sprites1.add(map1.base7);
		sprites1.add(map1.base8);
		sprites1.add(map1.map1mon1);
		sprites1.add(map1.map1mon2);
		sprites1.add(map1.map1mon3);
		sprites1.add(map1.map1mon4);
		sprites1.add(map1.temp);

		// Map2
		sprites2.add(map1.background2);
		sprites2.add(map1.map2Base);
		sprites2.add(map1.map2Base2);
		sprites2.add(map1.map2Base3);
		sprites2.add(map1.map2Base4);
		sprites2.add(map1.map2Base5);
		sprites2.add(map1.map2Base6);
		sprites2.add(map1.map2Base7);
		sprites2.add(map1.map2mon1);
		sprites2.add(map1.map2mon2);
		sprites2.add(map1.map2mon3);
		sprites2.add(map1.map2mon4);
		sprites2.add(map1.map2mon5);
		sprites2.add(map1.map2mon6);
		sprites2.add(map1.temp2);
		sprites2.add(map1.temp3);

		// Map3
		sprites3.add(map1.background3);
		sprites3.add(map1.map3Base);
		sprites3.add(map1.map3Base2);
		sprites3.add(map1.map3Base3);
		sprites3.add(map1.map3Base4);
		sprites3.add(map1.map3Base5);
		sprites3.add(map1.map3Base6);
		sprites3.add(map1.map3Base7);
		sprites3.add(map1.map3Base8);
		sprites3.add(map1.map3Base9);
		sprites3.add(map1.map3Base10);
		sprites3.add(map1.map3Base11);
		sprites3.add(map1.map3mon1);
		sprites3.add(map1.map3mon2);
		sprites3.add(map1.map3mon3);
		sprites3.add(map1.map3mon4);
		sprites3.add(map1.map3mon5);
		sprites3.add(map1.map3mon6);
		sprites3.add(map1.map3mon7);
		sprites3.add(map1.temp4);
		sprites3.add(map1.temp5);

		// Map4
		sprites4.add(map1.background4);
		sprites4.add(map1.map4Base);
		sprites4.add(map1.map4Base2);
		sprites4.add(map1.map4Base3);
		sprites4.add(map1.map4Base4);
		sprites4.add(map1.boss);
		sprites4.add(map1.temp6);

		// Add monsters to list
		allMonsters.add(monsters1);
		allMonsters.add(monsters2);

		// Current Map Initializing
		currentMap = sprites1;

		person = new Person(currentMap);
		weather = new Weather();
		menu = new PauseMenu();
		pause();
		sound = new Sound();
		sound2 = new Sound();
		sound3 = new Sound();
	}

	// Model Update
	public void update() throws IOException {
		// Update Map. This is outside the pause if-statement because some
		// things in the map have to keep happening (like lightning).
		this.map1.update(currentMap, person, isPaused);
		
		if(person.DefaultHP <= 0){
			//Implement some sort of death screen?
			diedCounter++;
			System.out.println("You died.");
			
			
			if(diedCounter > 240){
				System.exit(0);
			}
			//Some sort of pause in between "Exit"
			//if(Exit)
			//System.exit(0);
		}

		// Spawn portals based off level (Easy progression ATM)
		if (person.level == 2) { // GET LEVEL 2 TO ADVANCE
			map1.temp.able = true;
		} else if (person.level == 3) { // GET LEVEL 3 TO ADVANCE
			map1.temp3.able = true;
		} else if (person.level == 4) { // GET LEVEL 4 TO ADVANCE
			map1.temp5.able = true;
		} else if (map1.boss.dead) { // Door out only spawns if you kill boss
			person.achievement6 = true;  							// MWAHAHAHHA
			//map1.temp6.able = true;
		}

		if (menu.isButtonClicked)
			sound.playSong("clicked.wav");
		

		if (currentMap == sprites4) {
			this.weather.update();
			if (weather.lightningPoints.counter == 1)
				sound.playSong("lightningSound.wav");
			if (!sound.isPlaying())
				sound.playSong("rain_.wav");
			
			if (!sound2.isPlayingEpic) {
				sound2.playSong("epicSong.wav");
				if(sound3.isPlayingtheme)
				sound3.stopSong("Song.wav");
			} 
		}
		else
		{
			if(!sound3.isPlayingtheme)
			{
				sound3.playSong("Song.wav");
				if(sound2.isPlayingEpic)
				sound2.stopSong("epicSong.wav");
			}
		}
		
		if (!isPaused) {
			runSound();

			// Update person
			this.person.update(currentMap);

			// Update weapon
			if (weapon != null) {
				this.weapon.update();
				this.weapon.collision(currentMap);
				if (weapon.distance || weapon.hit) {
					weapon = null;
				}
			}

			// Detect if on Final map
			if (currentMap == sprites4) {
				for (int i = 0; i < currentMap.size(); i++) {
					Sprite s = currentMap.get(i);
					if (s instanceof Platform) {
						Platform p = (Platform) s;
						p.update(currentMap, person);
					} else if (s instanceof Boss) {
						Boss b = (Boss) s;
						b.update(currentMap, person);
					}
				}
			}

			// Detect Collision
			if (collision()) {
				person.gravity = 0;
			}
		}
	}
	// End of Update

	// Detect Portal (Final) POSSIBLY MOVE INTO ITERATION
	boolean portalCollision() {
		// Collision with portal(Only does it with the single one on first map
		// atm)
		for (int i = 0; i < currentMap.size(); i++) {
			Sprite sprite = currentMap.get(i);
			if (sprite instanceof Portal) {
				if (person.x + person.width > sprite.x && person.x < sprite.x + sprite.width
						&& person.y + person.height > sprite.y && person.y < sprite.y + sprite.height) {
					Portal p = (Portal) sprite;
					if (p.able) {
						if (sprite.x <= 600)
							Back = true;
						else
							Back = false;
						return true;
					} else
						return false;
				}
			}
		}
		return false;
	}

	// Draw
	void draw(Graphics g) {
		int mapNumber = 0;
		for (int i = 0; i < currentMap.size(); i++) {
			Sprite sprite = currentMap.get(i);
			if (sprite instanceof Background) {
				mapNumber = ((Background) sprite).mapNumber;
			}
		}
		map1.draw(g, currentMap);
		person.draw(g, mapNumber);
		menu.drawGameMenu(g);
		if (weapon != null)
			weapon.draw(g);
		if (mapNumber == 4) {
			weather.paintComponent(g);
		}
	}

	// Vertical Collision TEMPORARY
	boolean collision() {
		return person.verticalCollision;
	}

	// --------------------------------------------------------------
	// | 'KEY PRESSED' FUNCTIONS |
	// --------------------------------------------------------------

	// Pressed Left
	public void LeftArrow() {
		this.person.walkLeft();
	}

	// Pressed Right
	public void RightArrow() {
		this.person.walkRight();
	}

	// Pressed Shift
	public void Jump() {
		this.person.jump();
	}

	// Pressed Up
	public void usePortal() {
		for (int i = 0; i < Maps.size(); i++) {
			LinkedList<Sprite> s = Maps.get(i);
			if (s == currentMap) {
				if (this.person.usePortal(portalCollision(), Back, currentMap, Maps)) {
					if (person.back == true) {
						currentMap = Maps.get(i - 1);
						break;
					} else if (person.forward == true) {
            if(!person.achievement2)
              person.achievement2 = true;
						currentMap = Maps.get(i + 1);
						break;
					}
				}
			}
		}
	}

	// Pressed Space
	public void Space() throws IOException {
		if (weapon == null && !this.person.isCoolingDown) {
			// Add a new weapon!!
			if (whichWeapon == 0) {
				this.weapon = new FireBall(person);
				sound.playSong("fireballSound.wav");
			} else if (whichWeapon == 1) {
				sound.playSong("freeze.wav");
				this.weapon = new IceBall(person);
			} else if (whichWeapon == 2)
				this.weapon = new PoisonArrow(person);
			else if (whichWeapon == 3)
				this.weapon = new Sword(person);

			// Shoot the weapon
			this.person.shoot = true;
			this.person.isCoolingDown = true;
			if (this.person.DefaultMP > 0 && (whichWeapon == 1 || whichWeapon == 2 || whichWeapon == 0))
				this.person.MP -= 5;
			//System.out.println("You just shot something. Wait 1 second until you can shoot again, okay?");
		}
	}

	// Pressed the 'pause' button
	public void pause() {
		isPaused = !isPaused;
	}

	public void runSound() {
		if (person.WalkLeft || person.WalkRight)
			if (soundCounter > 29) {
				sound.playSong("running.wav");
				soundCounter = 0;
			} else
				soundCounter++;
		else if (!person.WalkLeft || !person.WalkRight || person.jump)
			//sound.clip8.stop();

		if (person.exp >= 10 * person.level) {
			sound.playSong("levelupSound.wav");
		}

	}

	// Pressed the 'switch weapons' button
	public void switchWeapon() {
		sound.playSong("switch.wav");
		whichWeapon++;
		if (whichWeapon > 3)
			whichWeapon = 0; // There are 4 weapons currently! Switch between
								// them

		this.person.weaponChoice = whichWeapon;
	}

	// --------------------------------------------------------------
	// | 'KEY RELEASED' FUNCTIONS |
	// --------------------------------------------------------------

	// Released Left
	public void stopLeftArrow() {
		this.person.stopWalkLeft();
	}

	// Released Right
	public void stopRightArrow() {
		this.person.stopWalkRight();
	}
}
