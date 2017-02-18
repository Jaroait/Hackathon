import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.LinkedList;

class Platform extends Sprite {
	// DEFAULT Variables, Image used for "Several sizes of platforms"
	int image;
	static Image Base = null; // 1
	static Image Platform = null; // 2
	static Image Platform187 = null; // 3
	static Image Platform768 = null; // 4
	static Image finalBase = null; // 5
	static Image finalPlatform = null; // 6
	static Image finalPlatform187 = null; // 7

	// Boolean Value for Horizontal or Strictly vertical(Fatter height)
	boolean horizontal;

	// Boss Map stuff
	int startingY;
	boolean down = false;

	// Constructor
	Platform(int x_coordinate, int y_coordinate, int imageNumber, boolean horizon) throws IOException {
		this.x = x_coordinate;
		this.y = y_coordinate;
		this.startingY = y;
		this.image = imageNumber;
		this.horizontal = horizon;
		Base = ImageIO.read(new File("new1920.png"));
		finalBase = ImageIO.read(new File("final1920.png"));
		Platform = ImageIO.read(new File("new1000.png"));
		Platform187 = ImageIO.read(new File("256.png"));
		finalPlatform = ImageIO.read(new File("final1000.png"));
		finalPlatform187 = ImageIO.read(new File("final256.png"));
		Platform768 = ImageIO.read(new File("768.png"));
		if (image == 1 || image == 5) {
			width = 1920;
			height = 10;
		} else if (image == 2 || image == 6) {
			if (horizontal) {
				width = 1000;
				height = 69;
			} else {
				width = 1000;
				height = 5;
			}
		} else if (image == 3 || image == 7) {
			if (horizontal) {
				width = 256;
				height = 69;
			} else {
				width = 256;
				height = 5;
			}
		} else if (image == 4) {
			if (horizontal) {
				width = 768;
				height = 69;
			} else {
				width = 768;
				height = 5;
			}
		}
	}

	void draw(Graphics g) {
		if (image == 1) {
			g.drawImage(Base, x, y, null);
		} else if (image == 2) {
			g.drawImage(Platform, x, y, null);
		} else if (image == 3) {
			g.drawImage(Platform187, x, y, null);
		} else if (image == 4) {
			g.drawImage(Platform768, x, y, null);
		} else if (image == 5) {
			g.drawImage(finalBase, x, y, null);
		} else if (image == 6) {
			g.drawImage(finalPlatform, x, y, null);
		} else if (image == 7) {
			g.drawImage(finalPlatform187, x, y, null);
		}
		// Continue for however many sizes of platforms you want

		// Image platform should be height of 20, but clearly have an indication
		// between the collision and design
	}

	// Collision Detection for Linked List
	void collision(LinkedList<Sprite> s) {

	}

	// Update
	void update(LinkedList<Sprite> s, Person p) {
		if (image == 7) {
			if (startingY < y + 150 && !down) {
				y -= 4;
				if (p.verticalCollision && (p.platTempX == 1600 || p.platTempX == 200)) {
					// p.y -= 4;
					p.y = y - p.height;
					// p.verticalCollision = true;
				}
				if (startingY > y + 140) {
					down = true;
				}
			} else if (startingY > y - 150) {
				y += 4;
				if (p.verticalCollision && (p.platTempX == 1600 || p.platTempX == 200)) {
					// p.y += 4;
					p.y = y - p.height;
					// p.verticalCollision = true;
				}
				if (startingY < y - 140) {
					down = false;
				}
			}
		}

	}

	@Override
	void update(LinkedList<Sprite> s) {
		// TODO Auto-generated method stub

	}
}