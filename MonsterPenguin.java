import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.imageio.ImageIO;

public class MonsterPenguin extends Monster {
	// Images!
	static Image frozen = null;
	static Image frozenHit = null;
	static Image poisonedHit = null;
	static Image hit = null;
	
	static Image poisoned1 = null;
	static Image poisoned2 = null;
	static Image poisoned3 = null;
	static Image poisoned4 = null;
	static Image poisoned5 = null;
	static Image poisoned6 = null;
	static Image poisoned7 = null;
	static Image poisoned8 = null;
	static Image poisoned9 = null;
	static Image poisoned10 = null;
	static Image poisoned11 = null;
	static Image poisoned12 = null;
	
	static Image walking1 = null;
	static Image walking2 = null;
	static Image walking3 = null;
	static Image walking4 = null;
	static Image walking5 = null;
	static Image walking6 = null;
	static Image walking7 = null;
	static Image walking8 = null;
	static Image walking9 = null;
	static Image walking10 = null;
	static Image walking11 = null;
	static Image walking12 = null;
	
	static LinkedList<Image> walkingImages = null;
	static LinkedList<Image> poisonedImages = null;
	
	public MonsterPenguin(int xPos, int yPos, int LBound, int RBound) throws Exception {
		// Call super constructor
		super(xPos, yPos, LBound, RBound);
		
		// Try to create all the images, and then pass
		// them to the Monster class as a LinkedList so
		// Monster class can draw them and everything
		
		try {
			// If none of the static images have been created...
			if (walkingImages == null) {
				
				frozen = ImageIO.read(new File("penguinFrozen.png"));
				hit = ImageIO.read(new File("penguinHit.png"));
				poisonedHit = ImageIO.read(new File("penguinPoisonedHit.png"));
				frozenHit = ImageIO.read(new File("penguinFrozenHit.png"));
				
				walking1 = ImageIO.read(new File("penguinwalk1.png"));
				walking2 = ImageIO.read(new File("penguinwalk2.png"));
				walking3 = ImageIO.read(new File("penguinwalk3.png"));
				walking4 = ImageIO.read(new File("penguinwalk4.png"));
				walking5 = ImageIO.read(new File("penguinwalk5.png"));
				walking6 = ImageIO.read(new File("penguinwalk6.png"));
				walking7 = ImageIO.read(new File("penguinwalk7.png"));
				walking8 = ImageIO.read(new File("penguinwalk8.png"));
				walking9 = ImageIO.read(new File("penguinwalk9.png"));
				walking10 = ImageIO.read(new File("penguinwalk10.png"));
				walking11 = ImageIO.read(new File("penguinwalk11.png"));
				walking12 = ImageIO.read(new File("penguinwalk12.png"));
				
				poisoned1 = ImageIO.read(new File("penguinpoisoned1.png"));
				poisoned2 = ImageIO.read(new File("penguinpoisoned2.png"));
				poisoned3 = ImageIO.read(new File("penguinpoisoned3.png"));
				poisoned4 = ImageIO.read(new File("penguinpoisoned4.png"));
				poisoned5 = ImageIO.read(new File("penguinpoisoned5.png"));
				poisoned6 = ImageIO.read(new File("penguinpoisoned6.png"));
				poisoned7 = ImageIO.read(new File("penguinpoisoned7.png"));
				poisoned8 = ImageIO.read(new File("penguinpoisoned8.png"));
				poisoned9 = ImageIO.read(new File("penguinpoisoned9.png"));
				poisoned10 = ImageIO.read(new File("penguinpoisoned10.png"));
				poisoned11 = ImageIO.read(new File("penguinpoisoned11.png"));
				poisoned12 = ImageIO.read(new File("penguinpoisoned12.png"));
			
				// ...and add them to the linked list so that the 
				// Monster class can handle drawing them
				walkingImages = new LinkedList<Image>();
				poisonedImages = new LinkedList<Image>();
				
				walkingImages.add(walking1);
				walkingImages.add(walking2);
				walkingImages.add(walking3);
				walkingImages.add(walking4);
				walkingImages.add(walking5);
				walkingImages.add(walking6);
				walkingImages.add(walking7);
				walkingImages.add(walking8);
				walkingImages.add(walking9);
				walkingImages.add(walking10);
				walkingImages.add(walking11);
				walkingImages.add(walking12);
				
				poisonedImages.add(poisoned1);
				poisonedImages.add(poisoned2);
				poisonedImages.add(poisoned3);
				poisonedImages.add(poisoned4);
				poisonedImages.add(poisoned5);
				poisonedImages.add(poisoned6);
				poisonedImages.add(poisoned7);
				poisonedImages.add(poisoned8);
				poisonedImages.add(poisoned9);
				poisonedImages.add(poisoned10);
				poisonedImages.add(poisoned11);
				poisonedImages.add(poisoned12);
			}
		} catch (Exception e) {}
		
		// Now the imageList objects in Monster will only ever point to one of three
		// (fox, penguin, or gnu) address spots for the whole 
		// runtime!! :D efficient.
		walkingImageList = walkingImages;
		poisonedImageList = poisonedImages;
		
		frozenMonsterImage = frozen;		// give the frozen monster image to Monster class!		
		hitMonsterImage = hit;
		poisonedHitMonsterImage = poisonedHit;
		frozenHitMonsterImage = frozenHit;	
	}
}
